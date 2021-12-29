# Liferay Commerce Enterpriseのアクティベーション

Liferay DXP/Portal 7.3から、CommerceはすべてのLiferayバンドルとDockerコンテナに同梱されているため、別途インストールする必要はありません。 ただし、Commerceにアクセスして使用するには、お使いのDXPのバージョンによって手順が異なります。 Liferay DXP 7.4 GA1では、すべてのCommerceモジュールがデフォルトで有効になっており、すぐに使用できます。 DXP 7.3 FP3/SP2では、Commerceは [portal property](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/portal-properties.html)を使用して有効化されますが、それ以前のバージョンではCommerceライセンスファイルが使用されます。

## Liferay DXP 7.4 GA1のLiferay Commerce Enterpriseの無効化について

> Liferay DXPおよびCommerceのサブスクリプションが必要

Liferay DXP 7.4 GA1では、すべてのCommerceモジュールがデフォルトで有効になっています。 ユーザーは、 `enterprise.product.commerce.enabled` ポータル プロパティを ［`false`］に設定することで、これらを無効にすることができます。

### Liferay バンドルの Commerce の無効化

Liferayバンドルを使用している場合、`portal-ext.properties` ファイルを使用して`enterprise.product.commerce.enabled` プロパティを構成することができます。 構成するには、Liferayサーバーの `portal-ext.properties` ファイルに以下のプロパティを追加するだけです。

```properties
enterprise.product.commerce.enabled=false
```

プロパティを追加したら、Liferayサーバーを再起動して変更を適用します。

`portal-ext.properties` ファイルが存在しない場合は、 `[LIFERAY_HOME]`または`[USER_HOME]`に作成してください。

```{note}
バンドルにセットアップウィザードを使用した場合、ポータルプロパティは`[LIFERAY_HOME]`フォルダの中の`portal-setup-wizard.properties` にあります。 このファイルは `portal-ext.properties` ファイルよりも優先されるので、`enterprise.product.commerce.enabled` プロパティの値が競合していないことを確認してください。 詳しくは[ポータル・プロパティ](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/portal-properties.html)を参照してください。
```

### DockerコンテナのCommerceの無効化

Dockerコンテナ上では、`enterprise.product.commerce.enabled`プロパティを`portal-ext.properties`ファイル、またはDockerの `env`変数で設定することができます。

`portal-ext.properties`ファイルには、このプロパティを追加します。

```properties
enterprise.product.commerce.enabled=false
```

または、このDockerの`env`変数を使用します。

```properties
LIFERAY_ENTERPRISE_PERIOD_PRODUCT_PERIOD_COMMERCE_PERIOD_ENABLED=false
```

詳しくは、[コンテナの設定](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/using-liferay-docker-images/configuring-containers.html#portal-properties)を参照してください。

正しく行われると、グローバルメニューにCommerceモジュールが表示されなくなります（![Global Menu](../images/icon-applications-menu.png)）。

## Liferay DXP 7.3 FP3/SP3+用のLiferay Commerce Enterpriseの有効化

> Liferay DXPおよびCommerceのサブスクリプションが必要

DXP 7.3 FP3/SP2+では、`enterprise.product.commerce.enabled` ポータルプロパティを `true`に設定して、Commerceを有効にします。

### Liferay バンドルの Commerce の有効化

`portal-ext.properties` ファイルを使用して`enterprise.product.commerce.enabled` プロパティを構成することができます。 Liferayサーバーの `portal-ext.properties` ファイルに以下のプロパティを追加してください。

```properties
enterprise.product.commerce.enabled=true
```

プロパティを追加したら、Liferayサーバーを再起動して変更を適用します。

`portal-ext.properties` ファイルが存在しない場合は、 `[LIFERAY_HOME]`または`[USER_HOME]`に作成してください。

### DockerコンテナのCommerceの有効化

Dockerコンテナを使用している場合、 `enterprise.product.commerce.enabled` プロパティは`portal-ext.properties`ファイルを使用して構成するか、Dockerの `env`変数を使用してオーバーライドすることができます。

`portal-ext.properties`ファイルを使用するには、以下のプロパティを新しい行に追加するだけです。

```properties
enterprise.product.commerce.enabled=true
```

ポータルプロパティをオーバーライドするには、以下のDocker `env` 変数を使用します。

```properties
LIFERAY_ENTERPRISE_PERIOD_PRODUCT_PERIOD_COMMERCE_PERIOD_ENABLED=true
```

### アクティベーションの成功の確認

プロパティが正常に追加されたことを確認するためには、Liferay DXPを開き、 ［*グローバルメニュー*］ (![Global Menu](../images/icon-applications-menu.png)) をクリックします。 正常に追加されていると、［ *Commerce*］ タブでCommerceモジュールを表示し、アクセスすることができます。 または、 ［*グローバルメニュー*］を開いて［*コントロールパネル* ］&rarr;［ *サーバー管理*］&rarr; ［*プロパティ*］ &rarr; *ポータルプロパティ*にアクセスできます。 新規プロパティは、他のプロパティと一緒にリストアップされているはずです。

## Liferay DXP 7.1-7.3 FP2用のLiferay Commerce Enterpriseの有効化

> Liferay DXPおよびCommerceのサブスクリプションが必要

Liferay DXP 7.1-7.3 FP2の場合、Commerceを使用するには有効なライセンスが必要です。 このライセンスは、XML (`.xml`) アクティベーションキーとして提供され、Commerceモジュールを有効にするためには、Liferayサーバーにデプロイする必要があります。

Commerceライセンスには `product-version`、 `license-type`、そして`expiration-date`を含むDXPライセンスと同じパラメータが多く使用されています。 ただし、システムリソース（プロセッサコア数など）やプラグインのバージョンによる制限は、Commerceライセンスには実装されていません。

```{important}
DXPとCommerceのアクティベーションキーは，両方とも同じ`license-type`（例：`production`，`developer`，`enterprise`）でなければなりません。 アクティベーションキータイプが一致しない場合は、サーバーの起動ログに警告が出力されます。 

*本番環境*ライセンスでは、入力値の検証のためにホスト名、IPアドレス、MACアドレスのいずれかが一致する必要があります。
```

* [Commerceのアクティベーションキーの入手](#obtaining-commerce-activation-keys)
* [Commerceのアクティベーションキーの展開](#deploying-commerce-activation-keys)
* [期限切れのCommerceエンタープライズライセンスの更新](#updating-an-expired-commerce-enterprise-license)

### Commerceのアクティベーションキーの入手

すでにCommerceサブスクリプションが[購入済](https://www.liferay.com/contact-sales) の場合は、以下のいずれかの方法でアクティベーションキーをダウンロードして入手できます。

* [ヘルプセンター](https://liferay-support.zendesk.com/agent/)チケットを*アクティベーションキー/Project Administration* コンポーネントを使って開きます。

* 各地域のプロビジョニングチーム（例：provisioning-[region]@liferay.com）にメールでリクエストを送信してください。

* Commerceのアクティベーションキーは、 [こちら](https://customer.liferay.com/en_US/activation-key)からダウンロードできます。

### Commerceのアクティベーションキーの展開

XMLアクティベーションキーを取得したら、それをDXPインスタンスの`デプロイ`フォルダにコピーすることで、Commerceのエンタープライズを有効にできます。 このプロセスは、Liferay DXPを有効化するのと同じです。

```{note}
Liferay 7.3 SP1では、Commerceを有効にした後、ユーザーはインスタンスのインデックスを再作成する必要がなくなりました。
```

#### DXP Bundlesへのデプロイ

XMLファイルをDXPインスタンスの[`${liferay.home}/deploy`](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/liferay-home.html) フォルダにコピーしてください。 アクティベーションキーの処理中、Liferayはこのファイルを `${liferay.home}/osgi/modules`フォルダに移動させ、ライセンスファイル(`.li`）`${liferay.home}/data/license`フォルダに生成します。 詳細は、[Activating Liferay DXP](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/setting-up-liferay/activating-liferay-dxp.html) を参照してください。

#### Dockerコンテナへのデプロイ

XMLファイルをコンテナの`/opt/liferay/deploy`フォルダにコピーします。 アクティベーションキーの処理中、Liferayはこのファイルを `opt/liferay/osgi/modules`フォルダに移動させ、ライセンスファイル(`.li`を`opt/liferay/data/license`フォルダに生成します。 Dockerコンテナへのファイルのデプロイについては、[Providing Files to a Container](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/using-liferay-docker-images/providing-files-to-the-container.html?highlight=opt)を参照してください。

#### DXP Cloudへのデプロイ

プロジェクトのセントラルGitリポジトリ内の[`liferay/configs/{ENV}/deploy/`](https://learn.liferay.com/dxp-cloud/latest/en/using-the-liferay-dxp-service/deploying-to-the-liferay-service.html#deploying-licenses)フォルダにキーをコピーし、変更をコミットします。 次に、LiferayサービスのJenkinsビルドを起動し、目的のプロジェクト環境にデプロイします。 環境のLiferayサービスへのファイルのデプロイに関する詳細は、 [Overview of DXP Cloud Deployment Workflow](https://learn.liferay.com/dxp-cloud/latest/en/build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.html) を参照してください。

```{important}
DXPクラウド契約でCommerceを購入した場合、アクティベーションキーはDXPクラウドチームがクラウド基盤で管理しますので、ご自身でキーを管理する必要はありません。 ただし、既存のDXP CloudプロジェクトにCommerceを追加する場合は、自分でライセンスをデプロイする必要があります。
```

#### デプロイメントの確認

キーが正常にデプロイされたことをコンソールで確認します。

   ```
   INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:271] Processing activation-key-commercesubscriptiondevelopment-1-developeractivationkeys.xml
   ...
   INFO  [fileinstall-directory-watcher][LicenseManager:?] Commerce Subscription Development license validation passed
   INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for Commerce Subscription Development
   ```

### 期限切れのCommerceエンタープライズライセンスの更新

Commerce エンタープライズのライセンスは、ユーザーのサブスクリプションの条件に基づいて、一定期間のみ有効となります。 ライセンスの有効期限が近づくと(i.e., < ほとんどのライセンスの場合は３０日、< ３０日のライセンスの場合は７日間)、管理者向けのCommerceアプリケーションに警告メッセージが表示されます。 ライセンスは、与えられた有効期限の後、2日間の猶予期間を経て失効します。

ライセンスの有効期限が切れても、Commerceモジュールは有効なままですが、UIでは使用できなくなり、ライセンスが更新されるまではAPIの呼び出しもできなくなります。 すべてのユーザーのCommerceアプリケーションに、Commerceアプリケーションが利用できないことを示す通知が表示されます。 管理者はライセンスの更新を、その他のユーザーは管理者への問い合わせを求められます。

また、サーバーの再起動時に、コンソールにエラーメッセージが表示されます：

   ```
   ERROR [main][LicenseManager:?] Liferay Commerce license is expired
   ```

Liferay Commerce Enterpriseを再起動するには、まずサーバーから期限切れのファイルを削除し、新しいキーを[デプロイ](#deploying-commerce-activation-keys)します。

   ```{tip}
   アクティベーションキーの削除や追加は、サーバーの稼動中に行うことができます。
   ```

#### DXPバンドルの更新

期限切れのXMLキーを`{liferay.home}/osgi/modules`フォルダから削除し、期限切れのライセンスファイルは`${liferay.home}/data/license`フォルダから削除します。 次に、新しいアクティベーションキーを `${liferay.home}/deploy`フォルダに追加します。

#### Dockerコンテナの更新

期限切れのXMLキーを`opt/liferay/osgi/modules`フォルダから削除し、期限切れのライセンスファイルは `opt/liferay/data/licenses`フォルダから削除します。 次に、新しいアクティベーションキーを、コンテナ内の `/opt/liferay/deploy`フォルダに追加します。

#### DXPクラウドプロジェクトでのLiferayサービスの更新

DXPクラウドチームがDXPとCommerceの両方のライセンスを管理しているため、ユーザーが自分で更新する必要はありません。

## 追加情報

* [インストールの概要](./installation-overview.md)
* [Liferay Commerceのアップグレード](./upgrading-liferay-commerce.md)
* [プロキシサーバーを介したMarketplaceアプリのアクティブ化](https://help.liferay.com/hc/en-us/articles/360018427391)
