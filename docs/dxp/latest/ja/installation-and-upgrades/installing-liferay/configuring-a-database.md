# データベースの構成

デモンストレーション用のデフォルトでは、Liferay DXPは組み込みHSQLデータベースを使用するように構成されています。 デモの目的以外では、次のようなフル機能のサポートされているRDBMSを使用することをお勧めします。

* MySQL
* MariaDB
* Oracle
* PostgreSQL

```{important}
   The [Liferay DXP Compatibility Matrix](https://help.liferay.com/hc/ja/articles/360049238151) lists the supported databases and versions.
```

<a name="configure-the-database" />

## データベースを構成する

1. UTF-8を使用するデータベースを作成します。 MySQLコマンドの例を次に示します。

    ```sql
    create database lportal character set utf8;
    ```

1. 完全なデータベース権限を持つデータベースユーザーを設定します。 組織でDXPデータベースユーザーのデータベースアクセスを制限する必要がある場合は、 [High Security Database User Practices](../reference/database-configurations.md#high-security-database-user-practices) を参照してください。

    ```{important}
       Liferay requires reading from and writing to the database. The Liferay database user must therefore have permissions to read and write data.
    ```

1. JDBCコネクタをインストールします。 DXPバンドルには、`/lib/ext`フォルダにいくつかのオープンソースJDBCコネクタが含まれています。 OracleやDB2などの専用データベースのコネクタ（下の表を参照）は、ベンダーからダウンロードする必要があります。

**専用データベース：**

| データベース | コネクタ          | ベンダーサイト                                     | メモ                                                                                                                                                |
|:------ |:------------- |:------------------------------------------- |:------------------------------------------------------------------------------------------------------------------------------------------------- |
| Oracle | `ojdbc8.jar`  | [Oracle](https://www.oracle.com/index.html) | [データ切り捨ての問題](https://issues.liferay.com/browse/LPS-79229) がCLOB列からデータを読み取って検出されたため、少なくともOracle 12.2.0.1.0 JDBC 4.2バージョンを備えた`ojdbc8.jar`ライブラリが必要です。 |
| DB2    | `db2jcc4.jar` | [IBM](https://www.ibm.com/)                 | ` dbc2jcc`コネクタは3.72以降廃止されました。                                                                                                                     |

　 Liferay DXPのデータベースを構成しました。

```{note}
   データベース構成の詳細は、[Database Configuration Reference](../reference/database-configurations.md)を参照してください。
```

<a name="next-steps" />

## 次のステップ

* [Liferayを初めて実行する](./running-liferay-for-the-first-time.md)
