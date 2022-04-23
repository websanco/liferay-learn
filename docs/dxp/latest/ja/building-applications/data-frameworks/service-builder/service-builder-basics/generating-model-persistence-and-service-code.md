# モデルコード、永続性コード、およびサービスコードの生成

サービスビルダーを使用すると、モデルを簡単に定義し、モデルコード、永続性コード、およびサービスコードを生成できます。 これは、`Y7G4Entry`というモデルを定義し、サービスビルダーを使用してコードを生成することで体験できます。 次に、コードをDXPにデプロイし、そのコードを使用するサービスを呼び出します。

## サンプルプロジェクトをダウンロードする

サンプルプロジェクトをダウンロードして解凍します。

```bash
curl https://learn.liferay.com/dxp/latest/ja/building-applications/data-frameworks/service-builder/service-builder-basics/liferay-y7g4.zip -O
```

```bash
unzip liferay-y7g4.zip
```

`liferay-y7g4` プロジェクトには、次の2つのモジュールがあります。

* `y7g4-api`
* `y7g4-service`

APIモジュール（`-api`）は、パブリックインターフェイスとユーティリティを提供します。 サービスモジュール（`-service`）は実装を提供します。

## APIモジュールを調べる

APIモジュールには、bndメタデータファイルとGradleビルドファイルしかありません。

```
y7g4-api
 ├── bnd.bnd // Defines the module artifact, package exports, and includes the service XML file
 └── build.gradle // Declares dependencies
```

`bnd.bnd`ファイルは次のとおりです。
```{literalinclude} ./generating-model-persistence-and-service-code/resources/liferay-y7g4.zip/y7g4-api/bnd.bnd
```

The `Bundle-` headers describe the module artifact. The `Export-Package` header specifies the API packages to publish. See [モジュールプロジェクト](../../../../liferay-internals/fundamentals/module-projects.md) for details on bnd metadata and how it's used.

The `build.gradle` file declares the module's dependency on DXP/Portal.

```{literalinclude} ./generating-model-persistence-and-service-code/resources/liferay-y7g4.zip/y7g4-api/build.gradle
:language: groovy
```

## サービスモジュールを調べる

サービスモジュールには、bndメタデータファイル、Gradleビルドファイル、およびサービス定義ファイルがあります。

```
y7g4-service
 ├── bnd.bnd // Defines the module artifact, data schema version, and more
 ├── build.gradle // Declares dependencies and code generation parameters
 └── service.xml // Specifies models and their relationships
```

`bnd.bnd`ファイルは次のとおりです。
```{literalinclude} ./generating-model-persistence-and-service-code/resources/liferay-y7g4.zip/y7g4-service/bnd.bnd
```

Once again, the `Bundle-` headers describe the module artifact. Service metadata and a directive follow.

| Metadata | Description |
| :------- | :---------- |
| `Liferay-Require-SchemaVersion: 1.0.0` | Your application's data schema version. When you release application versions that have database schema changes, you'll increment the version. |
| `Liferay-Service: true` | The module provides a Liferay Service. |
| `-dsannotations-options: inherit` | OSGi service component classes inherit [OSGi Declarative Services](../../../../liferay-internals/fundamentals/apis-as-osgi-services.md) annotations from their class hierarchy. For example, extension classes can access all the services that ancestor fields reference via the `@Reference` annotation. |

Here's the `build.gradle` file:

```{literalinclude} ./generating-model-persistence-and-service-code/resources/liferay-y7g4.zip/y7g4-service/build.gradle
:language: groovy
```

`buildService`タスクは、サービスのAPIクラスを`apiDir`で指定されたAPIモジュールのJavaソースフォルダに生成します。 サービスモジュールは、DXP/PortalおよびAPIモジュール（兄弟フォルダ`y7g4-api`内）に依存します。

## サービスモデルの定義を調べる

`service.xml`ファイルは、`Y7G4Entry`モデルエンティティを定義します。 サービスビルダーは、`service.xml`ファイルの仕様に従って、モデル、永続性、およびサービスクラスを生成します。

`service.xml`ファイルは次のとおりです。
```{literalinclude} ./generating-model-persistence-and-service-code/resources/liferay-y7g4.zip/y7g4-service/service.xml
```

This file defines a `Y7G4Entry` model that has an ID (the primary key), name, and description.

### `service-builder` Element

The `service-builder` element attributes affect all model entities in the `service.xml` file.

| `service-builder` attribute | Description |
| :-------------------------- | :---------- |
| `dependency-injector` | Declares the dependency injector type. Declarative Services (`ds`) is the default. |
| `package-path` | Declares the leading package path for the generated classes. |
| `short-no-such-exception-enabled` | If set to `true`, use a truncated version of the entity name in `NoSuchY7G4EntryException` messages; otherwise use the complete entity name. |

### `namespace` Element

The global `namespace` element specifies the prefix for all the model entity database tables.

### `entity` Element

`entity` elements define model database tables and service types.

| `entity` attributes | Description |
| :------------------ | :---------- |
| `name` | The entity's name. Service Builder generates an entity table using the naming format `[namespace]_[name]` (for example, `Y7G4_Y7G4Entry`). |
| `local-service` | If `true`, generate service classes to call from within the JVM. |
| `remote-service` | If `true`, generate service classes, including web services classes, to call from outside of the JVM. |

### `column` Elements
Each `column` element defines a column in the entity's table. Here are the `Y7G4Entry` entity column elements:

| Column | Description |
| :----- | :---------- |
| `y7g4EntryId` | the model instance's ID (long integer) and primary key. |
| `name` | the instance's name (string). |
| `description` | the instance's description (string). |

For more information on `service.xml` elements, see the [Liferay Service Builder DTD](https://learn.liferay.com/reference/latest/en/dxp/definitions/liferay-service-builder_7_4_0.dtd.html) .

## Generate the Persistence Code

Invoke Service Builder to generate persistence code and database scripts.

```bash
cd liferay-y7g4
```

```bash
./gradlew y7g4-service:buildService
```

出力:

```
> Task :y7g4-service:buildService
Building Y7G4Entry
Writing src/main/java/com/acme/y7g4/service/persistence/impl/Y7G4EntryPersistenceImpl.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/service/persistence/Y7G4EntryPersistence.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/service/persistence/Y7G4EntryUtil.java
Writing src/main/java/com/acme/y7g4/service/persistence/impl/Y7G4EntryModelArgumentsResolver.java
Writing src/main/java/com/acme/y7g4/model/impl/Y7G4EntryModelImpl.java
Writing src/main/java/com/acme/y7g4/model/impl/Y7G4EntryBaseImpl.java
Writing src/main/java/com/acme/y7g4/model/impl/Y7G4EntryImpl.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/model/Y7G4EntryModel.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/model/Y7G4Entry.java
Writing src/main/java/com/acme/y7g4/model/impl/Y7G4EntryCacheModel.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/model/Y7G4EntryWrapper.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/model/Y7G4EntrySoap.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/model/Y7G4EntryTable.java
Writing src/main/java/com/acme/y7g4/service/impl/Y7G4EntryLocalServiceImpl.java
Writing src/main/java/com/acme/y7g4/service/base/Y7G4EntryLocalServiceBaseImpl.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/service/Y7G4EntryLocalService.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/service/Y7G4EntryLocalServiceUtil.java
Writing ../y7g4-api/src/main/java/com/acme/y7g4/service/Y7G4EntryLocalServiceWrapper.java
Writing src/main/resources/META-INF/module-hbm.xml
Writing src/main/resources/META-INF/portlet-model-hints.xml
Writing ../y7g4-api/src/main/java/com/acme/y7g4/exception/NoSuchY7G4EntryException.java
Writing src/main/java/com/acme/y7g4/service/persistence/impl/constants/Y7G4PersistenceConstants.java
Writing src/main/resources/META-INF/sql/tables.sql
Writing src/main/resources/META-INF/sql/tables.sql
Writing src/main/resources/service.properties

BUILD SUCCESSFUL in 3s
1 actionable task: 1 executed
```

サービスビルダーは、モデル、永続性、およびサービス用のJavaクラス、データベーススクリプト、および構成ファイルを生成します。 ファイルパスは、`y7g4-service`モジュールからの相対パスです。

生成されたストラクチャーの概要は次のとおりです。

```
liferay-y7g4
├── y7g4-api
│   └── src
│       └── main
│           └── java
│               └── com
│                   └── acme
│                       └── y7g4
│                           ├── exception // Public exception classes & interfaces
│                           ├── model // Public model classes & interfaces
│                           └── service // Public persistence and service classes
│                                       // & interfaces
└── y7g4-service
    └── src/main
        ├── java/com/acme/y7g4
        │                 ├── model // Model implementation
        │                 └── service // Persistence and service implementation
        └── resources
            ├── META-INF
            │   ├── module-hbm.xml // Hibernate object relational map configuration
            │   ├── portlet-model-hints.xml // Provides field type information for the UI
            │   └── sql
            │       ├── indexes.sql
            │       ├── sequences.sql
            │       └── tables.sql
            └── service.properties // Tracks the service build version
```

モデル、永続性、およびサービス実装クラスは、Javaパッケージパス`com.acme.y7g4`に対して生成されました。 クラスについては、[Understanding Service Builder Generated Classes](./understanding-service-builder-generated-classes.md)をご覧ください。

SQLスクリプトと永続性構成が`resources/META-INF`フォルダに生成されました。

`module-hbm.xml`ファイルは、Hibernateオブジェクトリレーショナルマップを指定します。

```xml
<?xml version="1.0"?>
<!DOCTYPE hibernate-mapping PUBLIC "-//Hibernate/Hibernate Mapping DTD 3.0//EN" "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">

<hibernate-mapping auto-import="false" default-lazy="false">
    <import class="com.acme.y7g4.model.Y7G4Entry" />
    <class name="com.acme.y7g4.model.impl.Y7G4EntryImpl" table="Y7G4_Y7G4Entry">
        <id access="com.liferay.portal.dao.orm.hibernate.LiferayPropertyAccessor" name="y7g4EntryId" type="long">
            <generator class="assigned" />
        </id>
        <property access="com.liferay.portal.dao.orm.hibernate.LiferayPropertyAccessor" name="description" type="com.liferay.portal.dao.orm.hibernate.StringType" />
        <property access="com.liferay.portal.dao.orm.hibernate.LiferayPropertyAccessor" name="name" type="com.liferay.portal.dao.orm.hibernate.StringType" />
    </class>
</hibernate-mapping>
```

`module-hbm.xml`ファイルは、`Y7G4EntryImpl`オブジェクトを`Y7G4_Y7G4Entry`テーブルにマップします。 Hibernateでのマッピングの詳細については、 [Hibernate](https://hibernate.org) をご覧ください。

`tables.sql`スクリプトは、`Y7G4_Y7G4Entry`テーブルを指定します。

```sql
create table Y7G4_Y7G4Entry (
    y7g4EntryId LONG not null primary key,
    description VARCHAR(75) null,
    name VARCHAR(75) null
);
```

`y7g4EntryId`はプライマリーキーです。 `name`と`description`は属性です。 モジュールをデプロイすると、DXP/Portalは`tables.sql`スクリプトを実行してテーブルを作成します。

この`service.xml`ファイルの要素はインデックスまたはシーケンスを指定しないため、`indexes.sql`または`sequences.sql`スクリプトは空です。

## 永続レイヤーとサービスをデプロイする

次に、生成されたコードをDXPサーバーにデプロイして、永続レイヤーとサービスを作成します。 サーバーは、別のMariaDBデータベースサーバー上のデータソースを使用します。バンドルされているHypersonicサーバーよりもMariaDB上のデータベースを調べる方が簡単です。 すべてをデプロイした後、テーブルを検証し、サービスをテストします。

### データベースを作成する

1. MariaDB Dockerコンテナを起動します。

    ```bash
    docker run --name some-mariadb -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mariadb:10.2
    ```

1. MariaDB Dockerコンテナ内から[DXPデータベースを作成](../../../../installation-and-upgrades/reference/database-configurations.md)します。

    データベースサーバーにサインインします。

    ```bash
    docker exec -it some-mariadb bash -c "/usr/bin/mysql -uroot -pmy-secret-pw"
    ```

    DXP用のデータベースを作成します。

    ```sql
    create database dxp_db character set utf8;
    ```

    データベースセッションを終了します。

    ```bash
    quit
    ```

1. デフォルトのネットワーク（`bridge`）でDockerの`network inspect`](https://docs.docker.com/engine/reference/commandline/network_inspect/)コマンドを呼び出して、MariaDBコンテナのIPアドレスを取得します。

    ```bash
    Dockerネットワークはブリッジを実行します。
    ```

出力例：

```
"Containers": {
    "162f5350ee9ba7c47c1ba91f54a84543aeada7feb35eb8153743b13ef54cb491": {
        "Name": "some-mariadb",
        "EndpointID": "8e97e35fb118e2024a52f2ecbfd40b0a879eba8dc3bc5ffceea8bb117c10bebc",
        "MacAddress": "02:42:ac:11:00:02",
        "IPv4Address": "172.17.0.2/16",
        "IPv6Address": ""
    },
```

`some-mariadb`コンテナの`IPv4Address`値の最初の部分を使用します。 例のIPアドレスは`172.17.0.2`です。

### サーバーを起動する

別の端末で、次のコマンドを使用してDXPを起動します。 必ず`[IP address]`を`some-mariadb`コンテナのIPアドレスに置き換えてください。

```bash
docker run -it \
--add-host some-mariadb:[IP address] \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_JNDI_PERIOD_NAME="" \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_DRIVER_UPPERCASEC_LASS_UPPERCASEN_AME=org.mariadb.jdbc.Driver \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_URL="jdbc:mariadb://some-mariadb:3306/dxp_db?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false" \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_USERNAME=root \
-e LIFERAY_JDBC_PERIOD_DEFAULT_PERIOD_PASSWORD=my-secret-pw \
-m 8g \
-p 8080:8080 \
liferay/portal:7.4.2-ga3
```

### モジュールをデプロイする

モジュールをデプロイしてデータベーステーブルを作成し、サービスをインストールします。

```bash
./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
```

コンソール出力：

```bash
STARTED com.acme.y7g4.service_1.0.0 [1423]
STARTED com.acme.y7g4.api_1.0.0 [1422]
```

### テーブルを確認する

データベーステーブルを確認し、検証します。

1. データベースサーバーにサインインします。

    ```bash
    docker exec -it some-mariadb bash -c "/usr/bin/mysql -uroot -pmy-secret-pw"
    ```

1. データベースに接続します。

    ```sql
    connect dxp_db;
    ```

1. データベーステーブルを一覧表示して、`Y7G4_Y7G4Entry`テーブルを確認します。

    ```sql
    show tables;
    ```

    結果：

    ```
    +--------------------------------+
    | Tables_in_dxp_db               |
    +--------------------------------++
    | AMImageEntry                   |
    | AccountEntry                   |
    | AccountEntryOrganizationRel    |
    | ...                            |
    | Y7G4_Y7G4Entry                 |
    +--------------------------------+
    ```

1. `Y7G4_Y7G4Entry`テーブルの列を一覧表示します。

    ```sql
    SHOW COLUMNS FROM Y7G4_Y7G4Entry;
    ```

    結果：

    ```
    +-------------+-------------+------+-----+---------+-------+
    | Field       | Type        | Null | Key | Default | Extra |
    +-------------+-------------+------+-----+---------+-------+
    | y7g4EntryId | bigint(20)  | NO   | PRI | NULL    |       |
    | description | varchar(75) | YES  |     | NULL    |       |
    | name        | varchar(75) | YES  |     | NULL    |       |
    +-------------+-------------+------+-----+---------+-------+
    ```

    すべてが揃っています。

1. データベースセッションを終了します。

    ```bash
    quit
    ```

### サービスをテストする

サービスを呼び出して、データベースに`Y7G4Entry`データを入力します。

1. ブラウザでDXPにアクセスします（`http://localhost:8080`）。

1. デフォルトの認証情報を使用してサインインします。

    **ユーザー名** : `test@liferay.com`

    **パスワード：** `test`

1. ［**コントロールパネル**］ &rarr; ［**サーバ管理**］ &rarr; ［**スクリプト**］ でスクリプトコンソールに移動します。

1. 次のスクリプトを実行して、エントリーを追加します。

    ```groovy
    import com.acme.y7g4.service.Y7G4EntryLocalServiceUtil;

    import com.liferay.portal.kernel.dao.orm.QueryUtil;

    entry = Y7G4EntryLocalServiceUtil.createY7G4Entry(1234);

    entry.setName("Mop floors");
    entry.setDescription("Mop the kitchen and bathroom floors with soap and water.");

    Y7G4EntryLocalServiceUtil.addY7G4Entry(entry);

    entries = Y7G4EntryLocalServiceUtil.getY7G4Entries(QueryUtil.ALL_POS, QueryUtil.ALL_POS);

    for (entry in entries){
        out.println(entry);
    }
    ```

    出力:

    ```
    {y7g4EntryId=1234, description=Mop the kitchen and bathroom floors with soap and water., name=Mop floors}
    ```

    新しく追加されたY7G4EntryはJSON形式で印刷されます。

スクリプトが行ったことは次のとおりです。

1. 生成された静的ユーティリティクラス `Y7G4EntryLocalServiceUtil` をインポートしました。
1. ID（`long`）`1234`で`Y7G4Entry`インスタンスを作成しました。
1. `Y7G4Entry`インスタンスの`name`と`description`の属性を入力しました。
1. `Y7G4Entry`をデータベースに追加しました。
1. データベースからすべての`Y7G4Entry`インスタンスを取得し、それらを印刷しました。

## 次のステップ

モデルを定義し、その永続コードとサービスコードを生成する方法がわかったので、生成されたサービスクラスを調べる必要があります。 [生成されたクラスの理解と拡張](./understanding-service-builder-generated-classes.md) に進んでください。

## 追加情報

* [ローカルでサービスを呼び出す](./invoking-a-service-locally.md)
* [What is Liferay Workspace](../../../tooling/liferay-workspace/what-is-liferay-workspace.md)
