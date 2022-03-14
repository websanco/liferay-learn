# データベース設定

デモンストレーション用のデフォルトでは、Liferay DXPは組み込みHSQLデータベースを使用するように構成されています。 デモの目的以外では、次のようなフル機能のサポートされているRDBMSを使用することをお勧めします。

* MySQL
* MariaDB
* Oracle
* PostgreSQL

```{note}
[Liferay DXP Compatibility Matrix](https://help.liferay.com/hc/ja/articles/360049238151) には、サポートされているデータベースとバージョンが一覧表示されています。
```

Liferay DXPをデータベースに接続するには、次の手順が必要です。

* [データベース設定](#database-configuration)
* [JDBCコネクタのインストール](#installing-a-jdbc-connector)
* [データソースの構成](#configure-a-data-source)

<a name="database-configuration-1" />

## データベース設定

データベースを選択したら、次の手順に従って構成します。

* [UTF-8に対応している空のデータベースを作成する](#creatre-a-blank-database-with-utf-8-support)
* [データベースユーザーアクセスを設定する](#configure-database-user-access)
* [クエリ結果の並べ替え順序を設定する（オプション）](#configure-the-query-result-sort-order-optional)

```{important}
データベースを変更する前に、必ずデータベースベンダーのドキュメントを参照してください。
```

### UTF-8に対応している空のデータベースを作成する

多言語文字セットでは、UTF-8を使用する必要があります。 UTF-8を使用するデータベースを作成します。 MySQLコマンドの例を次に示します。

```sql
create database lportal character set utf8;
```

### データベースユーザーアクセスを設定する

Liferay DXPがデータを保持するには、データベースユーザーの資格情報が必要です。 最も単純で簡単な方法は、テーブルを作成および削除する権限を含む完全なデータベース権限を持つデータベースユーザーを使用することです。 このようなユーザーの場合、DXPプラグインはデータベースとシームレスに対話し、アップグレード操作は自動的に行われます。 データベースユーザーの権限をこれ以上制限することはお勧めしません。

ただし、組織によってデータベースの初期化後にLiferay DXPデータベースのユーザー権限を制限する必要がある場合は、次の高セキュリティデータベースユーザーのプラクティスを参照してください。

#### 高セキュリティデータベースユーザーのプラクティス

組織によっては、データベースの初期化後に、データベースLiferay DXPのデータベースユーザー権限を制限する必要がある、より厳格なセキュリティポリシーがある場合があります。 選択、挿入、更新、および削除操作の権限のみをユーザーに許可する場合は、データベースを手動で初期化および保守する必要があります。 これを実行するための推奨事項は次のとおりです。

1. Liferay DXPデータベースユーザーにデータベースに対して行うための完全な権限を付与します。
1. Liferay DXPをインストールして起動し、データベースに自動的にデータが入力されるようにします。
1. データベースにLiferay DXPテーブルが挿入されたら、選択、挿入、更新、および削除操作を実行する権限を除き、Liferay DXPデータベースユーザーからすべての権限を削除します。

```{warning}
これらの制約でLiferay DXPを実行する場合、いくつかの注意点があります。 多くのプラグインは、デプロイされると新しいテーブルを作成します。 さらに、Liferay DXPをアップグレードするには、データベースアップグレード機能を手動で実行する必要があります。 Liferay DXPデータベースユーザーがデータベース内のテーブルを作成、変更、削除するための適切な権限を持っていない場合は、これらのプラグインのいずれかをデプロイしたり、Liferay DXPのアップグレードを開始する前に、それらの権限をそのユーザーに付与する必要があります。 テーブルが作成されるか、アップグレードが完了すると、次のデプロイまたはアップグレードまでこれらの権限を削除できます。 チームが独自のテーブルを作成するプラグインを作成する場合は、プラグインをデプロイする前に、同様にLiferay DXPデータベースユーザーに一時的な権限を付与する必要があります。
```

### クエリ結果の並べ替え順序を設定する（オプション）

すべてのデータベースには、結果をソートするためのデフォルトの順序があります（ [この記事](https://help.liferay.com/hc/ja/articles/360029315971-Sort-Order-Changed-with-a-Different-Database) を参照）。 この順序が気になる場合は、データベースベンダーのドキュメントを参照して並べ替え順序を確認し、必要に応じて、Liferay DXPエンティティに適したデフォルトのクエリ結果順序を使用するようにデータベースを構成してください。

データベースサーバー、データベース、およびデータベースユーザーを構成しました。 Liferay DXPがデータベースとの通信に使用するJDBCコネクタをインストールする準備が整いました。

<a name="install-a-jdbc-connector" />

## JDBCコネクタをインストールする

Liferay DXPには、データベースと通信するためのJDBCコネクタが必要です。

### オープンソースデータベース

Liferay DXPバンドルには、いくつかのオープンソースJDBCコネクタが含まれています。 コネクタファイルは通常、Tomcatの`/lib/ext`またはJBoss EAPおよびWildFlyの`/module`などのアプリケーションサーバー上のグローバルフォルダに提供およびインストールされます。

OracleやDB2などの専用データベースに接続している場合は、ベンダーからコネクタをダウンロードして、アプリケーションサーバーのグローバルフォルダにインストールします。

**専用データベース：**

| データベース | コネクタ          | ベンダーサイト                                     | メモ                                                                                                                                                |
|:------ |:------------- |:------------------------------------------- |:------------------------------------------------------------------------------------------------------------------------------------------------- |
| DB2    | `db2jcc4.jar` | [IBM](https://www.ibm.com/)                 | ` dbc2jcc`コネクタは3.72以降廃止されました。                                                                                                                     |
| Oracle | `ojdbc8.jar`  | [Oracle](https://www.oracle.com/index.html) | [データ切り捨ての問題](https://issues.liferay.com/browse/LPS-79229) がCLOB列からデータを読み取って検出されたため、少なくともOracle 12.2.0.1.0 JDBC 4.2バージョンを備えた`ojdbc8.jar`ライブラリが必要です。 |

<a name="configuring-a-data-source" />

## データソースの構成

次の表にリストされている方法のいずれかを使用して、データソース接続を組み込んだDXPを構成できます。

| 方法          | Dockerイメージで利用可能 | 本番環境に推奨 |
|:----------- |:--------------- |:------- |
| Docker環境変数  | はい              | はい      |
| ポータルプロパティ   | はい*             | はい      |
| セットアップウィザード | 無               | 無       |

### Docker環境変数

DXP環境変数をDockerイメージに渡すことで、組み込みのデータソース接続を構成できます。 例として、 [データベーステンプレート](./database-templates.md) を参照してください。

### ポータルプロパティ

[ポータルプロパティ](./portal-properties.md) ファイルを使用して、Liferay Tomcatバンドル、アプリケーションサーバーのインストール、またはDockerイメージでデータソース接続を構成できます。 例として、 [データベーステンプレート](./database-templates.md) を参照してください。

```{note}
Dockerイメージでポータルプロパティファイルを使用するには、バインドマウントまたはボリュームを使用してファイルを渡す必要があります。 詳細については、 [コンテナへのファイルの提供](../installing-liferay/using-liferay-docker-images/providing-files-to-the-container.md) を参照してください。
```

### セットアップウィザード

バンドルまたはアプリケーションサーバーで本番環境以外の目的でDXPを実行している場合は、セットアップウィザードを使用して[DXPの起動](../installing-liferay/running-liferay-for-the-first-time.md)時にデータソース接続を構成できます。

![セットアップウィザードのデータベースセクションでは、DXPの組み込みデータソースを構成できます。](./database-configurations/images/01.png)

```{note}
お使いのアプリケーションサーバーで管理されているデータソースを使用している場合は、アプリケーションサーバーの手順を参照してください：[Tomcat](../installing-liferay/installing-liferay-on-an-application-server/installing-on-tomcat.md)、[WildFly](../installing-liferay/installing-liferay-on-an-application-server/installing-on-wildfly.md)、[JBoss EAP](../installing-liferay/installing-liferay-on-an-application-server/installing-on-jboss-eap.md)、[WebLogic](../installing-liferay/installing-liferay-on-an-application-server/installing-on-weblogic.md)、または[WebSphere](../installing-liferay/installing-liferay-on-an-application-server/installing-on-websphere.md)
```
