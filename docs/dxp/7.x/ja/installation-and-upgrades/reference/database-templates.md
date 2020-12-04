# データベーステンプレート

以下は、Liferay DXPの組み込みデータソースとしてさまざまなデータベースを構成するためのテンプレート（[ポータルプロパティ](./portal-properties.md)の例）です。

## MariaDB

``` properties
jdbc.default.driverClassName=org.mariadb.jdbc.Driver
jdbc.default.url=jdbc:mariadb://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
jdbc.default.username=
jdbc.default.password=
```

## MySQL

``` important::
   MySQLコネクタ/J 8.0は、MySQL Server 8.0および5.7で使用することを強くお勧めします。
```

``` properties
jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.default.url=jdbc:mysql://localhost/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&serverTimezone=GMT&useFastDateParsing=false&useUnicode=true
jdbc.default.username=
jdbc.default.password=
```

## PostgreSQL

``` properties
jdbc.default.driverClassName=org.postgresql.Driver
jdbc.default.url=jdbc:postgresql://localhost:5432/lportal
jdbc.default.username=
jdbc.default.password=
```

その他のデータベーステンプレートについては、[デフォルトのポータルプロパティ](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#JDBC)を参照してください。
