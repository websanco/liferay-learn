# アップグレードのためのデータベース調整

アップグレードの実行は、本番環境で日常的に実行する場合とは異なる形でデータベースに影響を与えます。 このため、アップグレードプロセスを実行する前にそのプロセス用にデータベースを調整し、アップグレードの完了後に本番環境設定を再適用する必要があります。

```note::
The tips given in this article worked well in test runs on specific versions of each database. Optimal tuning depends on your own data, infrastructure conditions, and database vendor. Analyze your data, tune for upgrade, and time your test upgrades to determine the best database and Java process configuration for your Liferay DXP data upgrade.
```

データのアップグレード中に実行される更新ステートメントの数は本番環境よりも多くなります。 そのため、データベースをアップグレードするためにデータベースを調整する方法をいくつか次に示します。

* パフォーマンスに影響を与えるデータ整合性測定を非アクティブにします。 障害が発生した場合はバックアップに復元します。

* トランザクションログはデータのアップグレードには重要ではないため、無効にするか最小限に抑えます。

* コミット関連のトランザクションI/O操作を非同期にします。

* コミットをディスクにフラッシュする間隔を増やします。

```warning::
   一部のデータベースプロパティと構成はグローバルであり、同じデータベース内のスキーマに影響します。
```

以下のセクションは、上記の方法で各データベースを調整する際のベンダー固有の情報にリンクしています。

## IBM DB2

[IBMの公式DB2ドキュメント](https://www.ibm.com/support/pages/db2-database-product-documentation-4)を参照してください。

## MariaDB

InnoDBの二重書き込みをオフにし、トランザクションコミット時のInnoDBフラッシュログを`0`に設定します。

## Microsoft SQL Server

[トランザクションの耐久性](https://docs.microsoft.com/en-us/sql/relational-databases/logs/control-transaction-durability)を`FORCED`に設定します。

## MySQL

[InnoDBの二重書き込み](https://dev.mysql.com/doc/refman/5.7/en/innodb-parameters.html#sysvar_innodb_doublewrite)をオフにし、[トランザクションコミット時のInnoDBフラッシュログ](https://dev.mysql.com/doc/refman/5.7/en/innodb-parameters.html#sysvar_innodb_flush_log_at_trx_commit)を`0`に設定します。

## Oracle Database

デフォルトの設定で適切に機能します。 [ディスクへの非同期I/O](https://docs.oracle.com/database/121/REFRN/GUID-FD8D1BD2-0F85-4844-ABE7-57B4F77D1608.htm#REFRN10048)を自動的に構成します。

## PostgreSQL

[同期コミット](https://www.postgresql.org/docs/10/wal-async-commit.html)をオフにし、[ログ先行書き込みライター遅延](https://www.postgresql.org/docs/10/wal-async-commit.html)を`1000`ミリ秒に設定します。

## まとめ

アップグレードが完了したら、必ずデータベース構成を本番環境設定に戻してください。
