# バックアップ

DXPインストールを実行したら、予期しないイベントを考慮した包括的なバックアップ計画を実装する必要があります。 バックアップコピーは、アップデートのテスト（DXPアップグレード、新しいプラグインなど）にも役立ちます。

Liferay DXPのバックアップ計画を検討するときは、次の領域をバックアップすることをお勧めします。

  - [Liferayホームディレクトリ](#liferay-home)
  - [アプリケーション・サーバー](#application-server)
  - [データベース](#database)
  - [検索インデックス](#search-indexes)
  - [ソースコード](#source-code)

``` note::
   DXP Cloud automates and simplifies the process of creating and managing backups of the Document Library and Liferay DXP database. Because DXP Cloud uses a Git-based DevOps pipeline, all configurations for Liferay are also automatically backed up as well. To learn more, see `DXP Cloud Backup Service <https://learn.liferay.com/dxp-cloud/latest/en/platform-services/backup-service.html>`_ for more information.
```

## Liferay Home

Liferayホームフォルダーには次のファイルが含まれているため、バックアップすることが重要です。

  - **ポータルのプロパティとシステムのプロパティ:**Liferayホームフォルダには、DXP[ポータルのプロパティファイル](../reference/portal-properties.md)(例:`portal-ext.properties`、`portal-setup-wizard.properties`など)とDXP[システムのプロパティファイル](../reference/system-properties.md)が格納されています(例:`system-ext.properties`)。

  - **`/data`フォルダ:**DXPは、Liferay Homeの`/data`フォルダに設定ファイル、検索インデックス、キャッシュ情報を保存します。 [ドキュメントとメディアリポジトリ](https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration)にアップロードされたアセットは、`/data`フォルダにも保存されます。 デフォルトのストレージ構成(ファイルシステムストアまたは高度なファイルシステムストア)を使用している場合。

  - **`/license`フォルダ(Subscription):**Liferay Enterpriseサブスクリプションのアクティベーションキーを保持します。

  - **`/osgi`フォルダ:**DXPのOSGiランタイムを構成するファイルは、Liferay Homeの`/osgi`フォルダに保存されます。 DXPにデプロイされたすべてのアプリとモジュールのJARファイルが含まれています。 `/osgi`フォルダには、他の必要なJARファイル、[設定ファイル](https://help.liferay.com/hc/en-us/articles/360029131651-Understanding-System-Configuration-Files)、およびログファイルも含まれています。

  - **`/logs`フォルダ:**DXPのログファイルが含まれています。 DXPで問題が発生した場合、ログファイルは、問題の原因を特定するための情報を提供します。

Git、BitBucket、Subversion、CVSなどのソース管理リポジトリを使用すると、Liferayホームフォルダーをバックアップできます。

``` important::
   If you configured your `Documents and Media repository <https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration>`_ to a location other than the default location, back up that location.
```

## アプリケーション・サーバー

アプリケーションサーバーには、カスタマイズした可能性のあるDXP記述子、デプロイメント、および依存関係があります。 たとえば、DXPの `web.xml` ファイルをカスタマイズした場合、DXPパッチの `web.xml` 常に既存のものを上書きするため、バックアップする必要があります。 アプリケーションサーバー全体をバックアップすることをお勧めします。

## データベース

DXPのデータベースは、ポータルのすべての情報の中央リポジトリです。 バックアップする最も重要なコンポーネントです。 データベースをライブでバックアップする（データベースで許可されている場合）か、データベースをファイルにエクスポート（ダンプ）してから、エクスポートしたファイルをバックアップします。

たとえば、 [MySQLの `mysqldump`](https://dev.mysql.com/doc/refman/5.7/en/using-mysqldump.html) ユーティリティは、データベース全体とデータを大きなSQLファイルにエクスポートします。

``` bash
mysqldump --databases my-liferay-database > my-liferay-database-backup.sql
```

その後、このファイルをバックアップできます。 データベースの復元時に、このファイルをデータベースにインポートして、データベースをエクスポートしたときの状態にデータベースの状態を再作成できます。

## 検索インデックス

``` important::
   If you've placed your search index into a database (not recommended; see the `DXP Clustering <../setting-up-liferay/clustering-for-high-availability.md>`_ article for information on using Cluster Link or Solr), you should back up that database too.
```

データセットが大きいユーザーは、バックアップから復元するときに、すべてのコンテンツとアセットのインデックスを再作成することを避けたい場合があります。 この場合、検索インデックスをバックアップすることをお勧めします。 これは、インデックスが格納されている別の [ElasticsearchまたはSolr](https://help.liferay.com/hc/en-us/articles/360028711092-Introduction-to-Installing-a-Search-Engine) 環境がある場合に最も簡単です。 クラスター構成で、インデックスを複製している場合は、各インデックスレプリカをバックアップする必要があります。

## ソースコード

DXPを拡張した場合、またはプラグインを作成した場合は、GitやBitBucketなどのソースコードリポジトリに保存する必要があります。 進行中の作業を維持するために、ソースコードリポジトリを定期的にバックアップする必要があります。

## まとめ

アプリケーションサーバー、Liferayホームフォルダー、ファイルシステムベースのメディアリポジトリの場所、およびバックアップシステムからのデータベースを復元すると、ポータルが機能するようになります。 検索インデックスを復元すると、致命的な障害の後にサイトを復旧するときに、インデックスを再作成する必要がなくなります。 このような適切で一貫性のあるバックアップ手順を実行することは、ハードウェア障害から正常に回復するための鍵です。
