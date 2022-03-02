# ホスティングLiferay

Liferayはアプリケーションサーバー上で実行されます。 Liferayをホストする方法は3つあります。

- Dockerコンテナを使用する
- オンプレミスでのLiferay Tomcatバンドルを使用する
- オンプレミスで、選択したサポートされているアプリケーションサーバー上で実行する

Liferayをホストする最速の方法は、クラウド上で事前構成されたDockerイメージを使用することです。 クラウドプロバイダーでイメージを使用し、環境変数を使用して[イメージを構成](./using-liferay-docker-images/docker-container-basics.md)します。

Liferay Tomcatバンドルは、任意のサーバーに抽出、構成、および実行できるアーカイブです。 これは、Liferayがすでにインストールされ、構成の準備ができている軽量のTomcatアプリケーションサーバーです。

もちろん、オンプレミスでサポートされている任意のアプリケーションサーバーにLiferayをいつでもインストールできます。

```{note}
エンタープライズサブスクライバーは、Liferay DXP Cloud上で [Liferay DXPをサービスとしてデプロイ](https://learn.liferay.com/dxp-cloud/latest/ja/using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.html) できます。 DXP Cloudは、インフラストラクチャコスト（ハードウェア、電気料金）を削減し、より多くの顧客を処理するために迅速に拡張し、市場投入までの時間を短縮し、使用方法も簡単です。

DXPクラウドにご興味のある方は、DXP Cloud [製品情報](https://www.liferay.com/products/dxp-cloud) をご覧いただくか、Liferayの担当者にお問い合わせください。
```

自分のマシンでLiferayを開発したい場合は、次の便利なオプションを検討してください。

* [Liferay Dockerイメージ](#docker-image)
* [Liferay Tomcat バンドル](#liferay-tomcat-bundle)

<a name="dockerイメージ" />

## Dockerイメージ

LiferayのDockerイメージは、Liferayの使用を開始するための最速の方法です。 イメージには、Tomcatアプリケーションサーバーに事前インストールされたLiferayが付属しています。 開始するには、[Docker Container Basics](./using-liferay-docker-images/docker-container-basics.md)をご覧ください。

<a name="liferay-tomcat-バンドル" />

## Liferay Tomcat バンドル

Liferay Tomcatバンドルは、LiferayがTomcatに事前インストールされているZIPファイルです。 これは、コンテナの外でLiferayの使用を開始する簡単な方法です。

バンドルを開始するには、[Installing a Liferay Tomcat Bundle](./installing-a-liferay-tomcat-bundle.md)を参照してください。

```{warning}
DockerイメージとTomcatバンドルでは、Liferayはデフォルトで組み込みHSQLデータベースを使用するように構成されています。 デモ目的以外では、フル機能の [サポートされているRDBMS](https://help.liferay.com/hc/en-us/articles/360049238151) を使用することをお勧めします。 構成手順については、[Database Configurations](../reference/database-configurations.md)を参照してください。
```

<a name="アプリケーションサーバーへのliferayのインストール" />

## アプリケーションサーバーへのLiferayのインストール

Liferayは、 [サポートされている任意のアプリケーションサーバー](https://help.liferay.com/hc/en-us/articles/360049238151) にインストールできます。 これは通常、DevOpsおよび高可用性環境で使用する最も実用的なインストールタイプです。

開始するには、

1. [互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360049238151) からサポートされているアプリケーションサーバーを選択します。
1. [アプリケーションサーバー](./installing_liferay_on_an_application_server.html) にLiferayをインストールするための手順に従います。

<a name="次のステップ" />

## 次のステップ

Liferayをインストールした後、 [Liferayのセットアップ](../setting-up-liferay.md) に進み、次のオプションを構成します。

* 検索機能
* ローカリゼーション
* メール
* ファイル ストレージ
* マーケットプレイスアプリ
* 高可用性
* もっと

<a name="追加情報" />

## 追加情報

* [バックアップ](../maintaining-a-liferay-dxp-installation/backing-up.md)
* [Patching Liferay DXP](../maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md)
* [Liferayの保護](../securing-liferay.md)
