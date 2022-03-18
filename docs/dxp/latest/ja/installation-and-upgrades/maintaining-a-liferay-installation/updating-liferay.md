# Liferayのアップデート

常に最新の情報を得ることで、最高のセキュリティと品質を得ることができます。

* **セキュリティアップデート**は、最新のセキュリティ問題に即座に対応するためのリリースです。

* **アップデート** は、最新のセキュリティアップデート、確認されたバグの修正、新機能の追加などを行ったリリースです。 これらの機能はデフォルトでは無効になっていますが、必要に応じてUIでオプトインすることができます。

ここでは、新しいLiferay Dockerイメージへのアップデート、新しいLiferay Tomcat Bundleへのアップデート、アプリケーションサーバーのLiferayインストールのアップデートの方法をご紹介します。

```{warning}
Liferay DXP/Portalをアップデートする前に、**必ず** データベースとインストールを[バックアップ](./backing-up.md)してください。
```

```{important}
Liferay DXP 7.3 SP3以前のバージョンでは、代わりにパッチモデルを使用しています。 Liferay DXP 7.3 SP3以前のバージョンをお使いの方は、[Patching DXP](./patching-dxp-7-3-and-earlier.md)をご覧ください。
```

```{note}
Liferay DXP/Portal の General Availability (GA) リリースは、ソースコードからビルドされます。 アップデートとセキュリティアップデートはGAに沿って行われ、またソースコードから構築されます。
```

## 新しいDocker Imageへのアップデート

1. 現在のDockerコンテナをシャットダウンします。

1. Liferayのキャッシュを削除します。

    `[Liferay Home]/osgi/state` フォルダーを削除します。

    ```bash
    cd [Liferay Home]
    rm -rf osgi/state
    ```

    `[Liferay Home]/work` フォルダーを空にします。

    ```bash
    rm -rf work/*
    ```

    アプリケーションサーバーのキャッシュを削除します。 キャッシュの場所については、アプリケーションサーバーのベンダーのドキュメントを参照してください。

    ```{note}
    モジュールの変更が内部のみである場合、変更はOSGiフレームワークからは見えず、モジュールはインストールされたままであり、モジュールの状態は保持されます。 次回のサーバー起動前にOSGiバンドルの状態情報をクリアすることで、そのようなモジュールが適切な状態で再インストールされるようになります。
    ```

1. Docker HubでLiferayのDockerイメージとタグ情報を検索します。

   * [Liferay DXPイメージ](https://hub.docker.com/r/liferay/dxp)
   * [Liferay Portalイメージ](https://hub.docker.com/r/liferay/portal)

1. データベースの変更やインデックスの変更については、リリースノートを確認してください。

    データベースに変更があった場合は、 `docker run` コマンドでこの環境設定を使用して、データベースのアップグレードが自動的に実行できるようにします。

    ```bash
    -e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true
    ```

    インデックスの変更がある場合は、 `docker run` コマンドで、この環境設定を使用してインデックスの更新を有効にします。

    ```bash
    -e LIFERAY_DATABASE_PERIOD_INDEXES_PERIOD_UPDATE_PERIOD_ON_PERIOD_STARTUP=true
    ```

1. 新しいDockerイメージを、現在の環境とパラメータ、および必要なデータベース/インデックス環境の設定（前のステップから）で実行します。 例えば、新しいイメージに`liferay`というローカルフォルダを[バインドマウント](../installing-liferay/using-liferay-docker-images/providing-files-to-the-container.md)するイメージを実行するコマンドは次のとおりです。

    ```bash
    docker run -it -m 8g -p 8080:8080 \
     -v $(pwd)/liferay:/mnt/liferay \
     liferay/[place image name here]:[place tag here]
    ```

1. `docker run` コマンドでデータベースのアップグレードやインデックスの更新を有効にした場合、コンソールやログにアップグレードの失敗やエラー、追加でアップグレードすべきオプションモジュールなどがすべて報告されます。 [Gogo Shellのコマンド](../upgrading-liferay/upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md) を使ってアドレスを指定することができます。 アップグレードが正常に完了したら、Dockerコンテナを停止して新しいコンテナを作成し、 `docker run` コマンドを で実行し、データベースのアップグレードやインデックスの更新の環境設定を*行わない* ようにします。

新しいLiferayアップデートDockerイメージで動作しています。

## 新しいLiferay Tomcatバンドルへの更新

1. 修正したシステム設定（ [ファイルストレージ](../../system-administration/file-storage/configuring-file-storage.md) と [Elasticsearch](../../using-search/installing-and-upgrading-a-search-engine/elasticsearch/connecting-to-elasticsearch.md) の設定を含む）を [`.config ` ファイル](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md#creating-configuration-files) にエクスポートし、 `[Liferay Home]/osgi/configs/` フォルダにコピーします。

    例えば、 [高度なファイルシステムストア](../../system-administration/file-storage/configuring-file-storage.md) または [簡易ファイルシステムストア](../../system-administration/file-storage/other-file-store-types/simple-file-system-store.md)を使用している場合、ファイルストアの設定を [`.config` ファイル](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md#creating-configuration-files) にエクスポートし、それを `[Liferay Home]/osgi/configs/` フォルダにコピーします。 以下に例を示します。 `com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config` ファイルに必要な `rootDir` パラメータ。

    ```properties
    rootDir="data/document_library"
    ```

1. [Commerce](https://learn.liferay.com/commerce/latest/en/index.html) を使用していて、リリースノートにCommerceのデータベースのアップグレードが記載されている場合は、アップグレードの準備をしてください。 詳細については、[Upgrading Liferay Commerce](https://learn.liferay.com/commerce/latest/en/installation-and-upgrades/upgrading-liferay-commerce.html)を参照してください。

1. アプリケーションサーバーをシャットダウンします。

    理由:

    * Unixスタイルのシステムでは、通常、実行中のファイルを置き換えることができますが、古いファイルはメモリに常駐します。
    * Windowsシステムでは、使用中のファイルはロックされており、パッチを適用できません。

1. インストールを[バックアップ](./backing-up.md)してください。

1. [ヘルプセンター](https://help.liferay.com/hc) (サブスクライバー) または [コミュニティダウロード](https://www.liferay.com/downloads-community) から、ご希望のLiferay DXP/Portal Tomcatバンドルのアップデートをダウンロードしてください。

1. バンドルを任意の場所に解凍します。

1. 新しいバンドルの `[Liferay Home]/data` フォルダを、 [バックアップの](./backing-up.md)の `[Liferay Home]/data` フォルダに置き換えます。

1. これらのファイルを[バックアップ](./backing-up.md)から新しいインストールにコピーします。

    * 設定ファイル（`.config` ファイル）。
    * DXPアクティベーションキー（サブスクライバー）
    * [ポータルプロパティ](../reference/portal-properties.md) (例： `portal-ext.properties`)

    詳細は、[構成とプロパティの移行](../upgrading-liferay/migrating-configurations-and-properties.md)を参照してください。

1. Tomcatのカスタマイズ内容（ `[tomcat version]/conf` フォルダのコンテンツや追加したライブラリなど）を、 [バックアップ](./backing-up.md) から新しいインストールに複製します。

1. カスタムウィジェットとモジュールを新しいインストールにコピーします。

1. リリースノートにデータベースの変更が記載されている場合は、互換性のある [database upgrade option](../upgrading-liferay/reference/database-upgrade-options.md) を使用して、すべての必要な変更と必要なオプションの変更を適用します。

1. アプリケーションサーバーを起動します。

LiferayアップデートTomcatバンドルで動作しています。

## アプリケーションサーバのインストールのアップデート

1. アップデートの `.war` ファイルとOSG依存関係のZIPファイルをダウンロードします。

    * DXP:ヘルプセンター [ダウンロード](https://customer.liferay.com/downloads)。
    * ポータル:Liferay コミュニティ [ダウンロード](https://www.liferay.com/downloads-community)。

1. アプリケーションサーバーをシャットダウンします。

    理由:

    * Unixスタイルのシステムでは、通常、実行中のファイルを置き換えることができますが、古いファイルはメモリに常駐します。
    * Windowsシステムでは、使用中のファイルはロックされており、パッチを適用できません。

1. アプリケーションサーバーのインストールで、既存のLiferay Webアプリケーションの上に`.war` ファイルのコンテンツを展開します。

    サポートされているアプリケーションサーバーへのLiferayのインストールに関する詳細な情報へのリンクです。

    * [Tomcat](../installing-liferay/installing-liferay-on-an-application-server/installing-on-tomcat.md)
    * [WildFly](../installing-liferay/installing-liferay-on-an-application-server/installing-on-wildfly.md)
    * [JBoss EAP](../installing-liferay/installing-liferay-on-an-application-server/installing-on-jboss-eap.md)
    * [WebLogic](../installing-liferay/installing-liferay-on-an-application-server/installing-on-weblogic.md)
    * [WebSphere](../installing-liferay/installing-liferay-on-an-application-server/installing-on-websphere.md)

1. OSGi依存関係のZIPファイルのコンテンツを、 `[Liferay Home]/osgi` フォルダにマージします。

1. Liferayのキャッシュを削除します。

    `[Liferay Home]/osgi/state` フォルダーを削除します。

    ```bash
    cd [Liferay Home]
    rm -rf osgi/state
    ```

    `[Liferay Home]/work` フォルダーを空にします。

    ```bash
    rm -rf work/*
    ```

    アプリケーションサーバーのキャッシュを削除します。 キャッシュの場所については、アプリケーションサーバーのベンダーのドキュメントを参照してください。

    ```{note}
    モジュールの変更が内部のみである場合、変更はOSGiフレームワークからは見えず、モジュールはインストールされたままであり、モジュールの状態は保持されます。 次回のサーバー起動前にOSGiバンドルの状態情報をクリアすることで、そのようなモジュールが適切な状態で再インストールされるようになります。
    ```

1. リリースノートにデータベースの変更が記載されている場合は、互換性のある [database upgrade option](../upgrading-liferay/reference/database-upgrade-options.md) を使用して、すべての必要な変更と必要なオプションの変更を適用します。

1. アプリケーションサーバーを再度起動します。

　 Liferayインスタンスがアップデートされ、実行されています。

## 追加情報

* [Backing Up](./backing-up.md)
* [ホットフィックスの適用](./applying-a-hotfix.md)
* [Database Upgrade Options](../upgrading-liferay/reference/database-upgrade-options.md)
