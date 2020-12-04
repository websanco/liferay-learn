# Liferay Home

*Liferay Home*は、Liferay DXPがアプリケーションの起動、構成の読み取り・適用、JARファイルのロード、ログの生成などを行うフォルダです。

Liferay Homeの場所は、インストールタイプによって異なります。

  - *DXPバンドルでは*、Liferay Homeはインストールの最上位フォルダであり、アプリケーションサーバーが含まれています。
  - *アプリケーションサーバー上のインストールでは*、Liferay Homeフォルダはアプリケーションサーバーによって異なります。 アプリケーションサーバー上にインストールしている場合、Liferay Homeの場所については、そのアプリケーションサーバーに関する記事（例：*\[app server\]でのDXPのインストール*）を参照してください。

## Liferay Homeの構造

DXPのインストールには、アプリケーションサーバーに関係なく、次のフォルダ構造が含まれています。

    [LIFERAY_HOME]
        ├── data
        ├── deploy
        ├── license
        ├── logs
        ├── osgi
        │   └── configs
        │   └── core
        │   └── marketplace
        │   └── modules
        │   └── portal
        │   └── state
        │   └── static
        │   └── war
        ├── patching-tool
        ├── tools
        └── work

## Liferay Homeリファレンス

以下は、各フォルダの内訳とその目的の簡単な説明です。

  - `data`（HSQLデータベースが選択されている場合）：埋め込みHSQLデータベース、DXPのファイルリポジトリ、およびサーチインデックスを格納します。 組み込みのHSQLデータベースはデフォルトで設定されていますが、デモンストレーションと試用のみを目的としています。 [ポータルプロパティ`jdbc.default.url`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#JDBC)は、Hypersonic組み込みHSQLデータベースの場所を設定します。
  - `deploy`：デフォルトでは、このフォルダはプラグインを自動デプロイします。 自動デプロイは、Liferay マーケットプレイスのアプリケーション`.lpkg`ファイル、プラグイン`.war`ファイル、およびプラグイン`.jar`ファイルをサポートしています。 [ポータルプロパティ`auto.deploy.deploy.dir`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Auto%20Deploy)を設定することにより、`deploy`フォルダの代替パスを設定できます。
  - `license`：DXPの著作権およびバージョンファイルがここにあります。
  - `logs`：DXPはこのフォルダを作成し、ここにログファイルを書き込みます。 問題を診断するときに、それらを調べます。 `portal-impl.jar`の`portal-impl/src/META-INF/portal-log4j.xml`ファイルは、ログファイルの場所を設定します。 ログファイルの場所を上書きするには、[Extプラグインで`ext-impl/src/META-INF/portal-log4j-ext.xml`ファイルを使用する](https://help.liferay.com/hc/articles/360029030791-Customizing-Core-Functionality-with-Ext)必要があります。
  - `osgi`：OSGiランタイムのすべてのJARファイルといくつかの構成ファイルは、このフォルダに属しています。 [ポータルプロパティ`module.framework.base.dir`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework)は、OSGiフォルダの場所を設定します。 以下がそのサブフォルダです。
      - `構成`：コンポーネント [構成ファイル](../../system-administration/system-settings/system-settings.md#exporting-and-deploying-configurations)。
      - `core`：DXPのコアモジュール。
      - `marketplace`：マーケットプレイスアプリケーションとアプリケーションスイート。
      - `modules`：デプロイしたモジュール。
      - `portal`：DXPの非コアモジュール。
      - `state`：OSGiバンドルのインストール、バンドルストレージなどのOSGi内部状態ファイルが含まれています。
      - `static`：カスタマイゼーションをJARファイルとしてここにデプロイします。
      - `war`：デプロイしたWARプラグイン。
  - `patching-tool`：（サブスクリプション）このフォルダには、パッチとパッチをインストールするためのユーティリティが含まれています。
  - `tools`：データベースアップグレードツールおよびターゲットプラットフォームインデクサー用。
  - `work`：モジュールJasper作業ファイル。

``` note::
   DXPがLiferay Homeフォルダにリソースを作成できない場合、またはDXPが特定のアプリケーションサーバーで実行されている場合は、DXPを実行しているオペレーティングシステムユーザーのホームフォルダに「liferay」と呼ばれるフォルダが作成されます。 この場合、その``liferay``フォルダがLiferay Homeになります。 たとえば、オペレーティングシステムのユーザー名が*jbloggs*の場合、`liferay`フォルダのパスは``/home/jbloggs/liferay``または``C:\Users\jbloggs\liferay``になります。
```
