# データベースの構成

デモンストレーション用のデフォルトでは、Liferay DXPは組み込みHSQLデータベースを使用するように構成されています。 デモの目的以外に、次のようなフル機能のサポートされているRDBMSを使用することをお勧めします。

  - MySQL
  - MariaDB
  - Oracle
  - PostgreSQL

```{important}
[Liferay DXP互換性マトリックス](https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7) には、サポートされているデータベースとバージョンがリストされています。
```

<!-- TODO Update the matrix link to the 7.3 matrix, when it's published - jhinkey -->

## データベースを構成する

1.  UTF-8を使用するデータベースを作成します。 MySQLコマンドの例を次に示します。

    ```sql
    create database lportal character set utf8;
    ```

2.  完全なデータベース権限を持つデータベースユーザーを設定します。 組織でDXPデータベースユーザーのデータベースアクセスを制限する必要がある場合は、[High Security Database User Practices](../reference/database-configurations.md#high-security-database-user-practices) を参照してください。

3.  JDBCコネクタをインストールします。 DXPバンドルには、`/lib/ext` フォルダにいくつかのオープンソースJDBCコネクタが含まれています。 OracleやDB2などの専用データベースのコネクタ（下の表を参照）は、ベンダーからダウンロードする必要があります。

**専用データベース：**

| データベース | コネクタ          | ベンダーサイト                                     | メモ                                                                                                                                                |
|:------ |:------------- |:------------------------------------------- |:------------------------------------------------------------------------------------------------------------------------------------------------- |
| Oracle | `ojdbc8.jar`  | [Oracle](https://www.oracle.com/index.html) | [データ切り捨ての問題](https://issues.liferay.com/browse/LPS-79229)がCLOB列からデータを読み取って検出されたため、少なくともOracle 12.2.0.1.0 JDBC 4.2バージョンを備えた`ojdbc8.jar`ライブラリが必要です。 |
| DB2    | `db2jcc4.jar` | [IBM](https://www.ibm.com/)                 | `dbc2jcc` コネクタは3.72以降廃止されました。                                                                                                                     |

Liferay DXPのデータベースを構成しました。

```{note}
データベース構成の詳細については、 [Database Configuration Reference](../reference/database-configurations.md) を参照してください。
```

## 次のステップ

  - [Liferayを初めて実行する](./running-liferay-for-the-first-time.md)
