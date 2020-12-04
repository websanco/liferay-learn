# データベースアップグレードツールのリファレンス

この記事では、アプリケーションサーバー内のアップグレードツールの概要について説明します。

`[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client`フォルダ（Windowsでは`db_upgrade.bat`）にある`db_upgrade.sh`スクリプトを使用して、アップグレードツールを起動します。

## 概要

  - [アップグレードツールの使用](#database-upgrade-tool-usage)
  - [アップグレードツールの設定](#configuring-the-upgrade-tool)

## データベースアップグレードツールの使用

次のコマンドは、アップグレードツールの使用状況を出力します。

``` bash
db_upgrade.sh --help
```

次に、アップグレードツールのすべてのコマンドラインオプションを示します。

**--help**または**-h**：ツールのヘルプメッセージを出力します。

**--jvm-opts**または**-j** + **\[arg\]**：アップグレードプロセスのJVMオプションを設定します。

**--log-file**または**-l** + **\[arg\]**：ツールのログファイル名を指定します。デフォルト名は`upgrade.log`です。

**--shell**または**-s**：アップグレードプロセスの完了後、自動的にGogoシェルに接続します。

### ログ出力

`-l`オプションは、ツールのログファイル名を指定します。

``` bash
db_upgrade.sh -l "output.log"
```

### 推奨されるJVMオプション

必ずファイルエンコーディングを`UTF-8`に、タイムゾーンを`GMT`に設定してください。 データベースアップグレードツールはDXPデータベース上で動作するため、DXPアプリケーションサーバーで使用するものと同じJVMオプションを使用してアップグレードツールを構成する必要もあります。 国と言語のJVMオプションを使用した場合は、それらをアップグレードツールに指定してください。

また、アップグレードツール用の初期メモリ(`-Xmx value`)も割り当てます。 少なくとも2 GBを使用してください。 DXPデータベースに10 GBを超えるデータがある場合は、初期メモリを増やしてください。

3.2 GBのデータベースと15 GBのドキュメントライブラリを使用したテストシナリオでは、次のJavaプロセス設定が最適でした。

  - Xmx 8 GBのRAM
  - ファイルエンコーディングUTF-8
  - ユーザーのタイムゾーンGMT

これらの設定に対応する`db_upgrade.sh`コマンドは次のとおりです。

``` bash
db_upgrade.sh -j "-Xmx8000m -Dfile.encoding=UTF-8 -Duser.timezone=GMT"
```

## アップグレードツールの設定

コアのアップグレードには設定が必要です。 最も簡単な方法は、アップグレードツールを使用して構成ファイルをオンザフライで作成することです。 次に、アップグレードツールのコマンドラインインターフェイスとの対話の例を示します。

    Please enter your application server (tomcat):
    tomcat
    
    Please enter your application server directory (../../tomcat-9.0.17):
    
    Please enter your extra library directories (../../tomcat-9.0.17/bin):
    
    Please enter your global library directory (../../tomcat-9.0.17/lib):
    
    Please enter your portal directory (../../tomcat-9.0.17/webapps/ROOT):
    
    [ db2 mariadb mysql oracle postgresql sqlserver sybase ]
    Please enter your database (mysql):
    mariadb
    
    Please enter your database host (localhost):
    
    (etc.)

``` note::
   省略された値は、括弧内に表示されているデフォルトを使用します。
```

### 手動設定

また、アップグレードツールを事前設定して、ツールが生成するよりも多くの値を設定することもできます。 `[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client/`にある次のファイルを使用して、コアアップグレードを手動で設定します。

  - `app-server.properties`：サーバーの場所とライブラリを指定します。
  - `portal-upgrade-database.properties`：データベース接続を構成します。
  - `portal-upgrade-ext.properties`：アップグレードに必要な残りのポータルプロパティを設定します。 現在のDXPサーバーを複製するには、現在のポータルプロパティ（データベースプロパティを除く）をこのファイルにコピーします。 現在のプロパティを使用する前に、必ず[現在のDXPバージョンに合わせて更新してください](./preparing-a-new-application-server.md#migrate-your-portal-properties)。

#### app-server.propertiesの構成

DXPのアプリケーションサーバーを設定するには、次の情報を指定します。

| プロパティ名                      | 意味                                           | メモ                                                                            |
| --------------------------- | -------------------------------------------- | ----------------------------------------------------------------------------- |
| `dir`                       | アプリケーションサーバーフォルダの絶対パス。                       |                                                                               |
| `extra.lib.dirs`            | クラスパスに追加するバイナリまたはリソースを含む追加のディレクトリのカンマ区切りリスト。 | すべての絶対パスまたは `dir`に関連するすべてのパスを使用します。                                           |
| `global.lib.dir`            | アプリケーションサーバーのグローバルライブラリディレクトリ。               | 絶対パスまたは `dir`に関連するパスを使用します。                                                   |
| `portal.dir`                | アプリケーションサーバーでポータルがインストールされているディレクトリ。         | 絶対パスまたは `dir`に関連するパスを使用します。                                                   |
| `server.detector.server.id` | サポートされているアプリケーションサーバーのID。                    | サポートされているID：`jboss`、`jonas`、`resin`、`tomcat`、`weblogic`、`websphere`、`wildfly` |

相対パスには、Unixスタイルの形式を使用する必要があります。 たとえば、次のプロパティはWindows用であり、相対パスを使用します。

``` properties
dir=D:\
extra.lib.dirs=Liferay/liferay-portal-master/tomcat-9.0.10/bin
global.lib.dir=Liferay/liferay-portal-master/tomcat-9.0.10/lib
portal.dir=Liferay/liferay-portal-master/tomcat-9.0.10/webapps/ROOT
server.detector.server.id=tomcat
```

別の例として、次のプロパティはLinux用であり、絶対パスを使用します。

``` properties
dir=/
extra.lib.dirs=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/bin
global.lib.dir=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/lib
portal.dir=/home/user/liferay/liferay-portal-master/tomcat-9.0.10/webapps/ROOT
server.detector.server.id=tomcat
```

#### portal-upgrade-database.propertiesの構成

次の情報を指定して、アップグレードするデータベースを構成します。 これらのプロパティは、`portal-ext.properties`ファイルで使用する[JDBCポータルプロパティ](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#JDBC)に対応することに注意してください。

  - `jdbc.default.driverClassName`
  - `jdbc.default.url`
  - `jdbc.default.username`
  - `jdbc.default.password`

これらの値のリファレンスについては、最新の[ポータルプロパティリファレンス](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html)を参照してください。

#### portal-upgrade-ext.propertiesの構成

次の情報を指定して、アップグレードを構成します。

  - `liferay.home`：[LIFERAY\_HOMEフォルダ](../../reference/liferay-home.md)

  - `dl.store.impl`：ドキュメントをドキュメントライブラリストアに保持するための実装。 このプロパティは、`*FileSystemStore` 実装を使用している場合にのみ必須です。 `portal-ext.properties`でこのプロパティを更新した場合は、ここに新しい値をコピーします。 それ以外の場合は、次のいずれかの方法でプロパティを設定します。

    ``` properties
    dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
    dl.store.impl=com.liferay.portal.store.db.DBStore
    dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
    dl.store.impl=com.liferay.portal.store.s3.S3Store
    ```

  - `hibernate.jdbc.batch_size`：パフォーマンスを向上させるために使用されるJDBCバッチサイズ（デフォルトでは*250*に設定）。 *このプロパティを使用するとアップグレードのパフォーマンスが向上しますが、必須ではありません。*

#### アップグレード構成の例

以下は、カスタマイズして`[LIFERAY_HOME]/tools/portal-tools-db-upgrade-client/`にコピーできるアップグレード構成ファイルの例です。

  - `app-server.properties`：

    ``` properties
    dir=../../tomcat-9.0.17
    global.lib.dir=/lib
    portal.dir=/webapps/ROOT
    server.detector.server.id=tomcat
    extra.lib.dirs=/bin
    ```

  - `portal-upgrade-database.properties`：

    ``` properties
    jdbc.default.url=jdbc:mysql://lportal62?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&serverTimezone=GMT&useFastDateParsing=false&useUnicode=true
    jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
    jdbc.default.username=root
    jdbc.default.password=
    ```

  - `portal-upgrade-ext.properties`：

    ``` properties
    liferay.home=/home/user/servers/liferay7
    module.framework.base.dir=/home/user/servers/liferay7/osgi
    dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
    ```
