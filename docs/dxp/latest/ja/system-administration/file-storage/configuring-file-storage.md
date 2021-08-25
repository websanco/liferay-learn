# ファイルストレージの構成

ファイル（[ドキュメントとメディア](../../../collaboration-and-social/collaboration-and-social-overview.md)など）を格納し、添付ファイル（[メッセージボード](../../../collaboration-and-social/collaboration-and-social-overview.md)など）をサポートするウィジェットは、DXPのファイルストアを使用します。 ファイルはファイルストアに保存され、取得されます。 ファイルストアは、ローカルマシン上、ネットワークにマウントされたファイルシステム上、データベース、またはクラウドでホストできます。

``` important::
   本番環境に移行する場合は、さまざまなファイルストア構成オプションを確認し、本番環境に移行する前に、ニーズに最適なオプションを選択することを強くお勧めします。 そうすることで、プロジェクトの後半でファイル保管場所を移行する手間を省くことができます。
```

## Advanced File System Storeの構成

Advanced File System Storeは、フォルダ内のファイルをアルファベット順にネストすることにより、プログラムで数百万のファイルに拡張できるフォルダ構造を作成します。 これにより、より多くのファイルを保存できるようになり、一部のオペレーティングシステムで発生するフォルダごとに保存できるファイル数の制限を回避できます。 フォルダあたりの保存ファイル数が少ないと、ファイル検索のパフォーマンスも向上します。

![Advanced File System Storeのフォルダ構造](./configuring-file-storage/images/01.png)

Advanced File System Store方式を使用するには、次の手順に従います。

1.  次のプロパティを使用して[`portal-ext.properties`](../../../installation-and-upgrades/reference/portal-properties.md)を設定します。

    ``` properties
    dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
    ```

2.  DXPを再起動します。

3.  コントロールパネルで、*[Configuration]* → *[System Settings]* → *[File Storage]* に移動します。

4.  *[Advanced File System Store]* 画面で、*[Root Directory]* を[Liferay Home](../../../installation-and-upgrades/reference/liferay-home.md)パス（絶対または相対）に設定します。

    ![Advanced File System Store画面](./configuring-file-storage/images/02.png)

5.  *[保存]* をクリックします。

DXPがAdvanced File System Storeを使用してファイルを保存するようになりました。

### クラスター環境でのファイルストレージ

[クラスター環境](../../../installation-and-upgrades/setting-up-liferay/clustering-for-high-availability.md)では、すべてのノードがアクセスできるネットワークマウントファイルシステムをストアに指定します。 ネットワーク化されたファイルシステムは、すべてのノードからアクセス可能で、同時要求をサポートし、ファイルロックをサポートしている必要があります。 そのようなファイルシステムなしでAdvanced File System Storeを使用し、複数のユーザーが同時に同じファイルに書き込もうとすると、データが破損する可能性があります。

## その他のファイルストレージトピック

### その他のファイルストレージメソッド

Liferay DXPには、プロジェクトのニーズに応じて設定可能な他のいくつかのファイルストレージメソッドも付属しています。

  - [Simple File System Store](./other-file-store-types/simple-file-system-store.md)は、ファイルシステム（ローカルまたはマウントされた共有）を使用してファイルを格納します。 これは*デフォルト*のファイルストアです。

  - [S3ストア（Amazon Simple Storage Service）](./other-file-store-types/amazon-s3-store.md)は、Amazonのクラウドベースのストレージを使用しています。

  - [DBStore（データベースストレージ）](./other-file-store-types/dbstore.md)は、ファイルを`BLOB`としてDXPデータベースに保存します。 DBStoreのファイルサイズ制限は1ギガバイトです。 1ギガバイトを超えるファイルを保存するには、Simple File System StoreまたはAdvanced File System Storeを使用します。

``` warning::
   ファイルシステムベースのストア（Simple、Advanced、S3）には、トランザクションロールバック機能がありません。 ドキュメントライブラリでデータベーストランザクションのロールバックが発生した場合、トランザクションのファイルシステムの変更は元に戻されません。 ドキュメントライブラリファイルとファイルシステムストア内のファイルとの間に不整合が発生する可能性があり、手動での同期が必要になる場合があります。 `DBStore <./other-file-store-types/dbstore.md>`_ を除くすべてのストアは、この制限に対して脆弱です。
```

### ファイルストア間でのファイルの移行

データ移行ユーティリティは、ファイルをあるストアオプションから別のストアオプションに移動します。 たとえば、この機能を使用してSimple File System Store（デフォルトのストア）からAdvanced File System Storeにファイルを移行すると、パフォーマンスとスケーラビリティのメリットを活用できます。 詳細については、[File Store Migration](./file-store-migration.md)を参照してください。
