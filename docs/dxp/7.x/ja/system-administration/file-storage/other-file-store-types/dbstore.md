# DBStore

DBStoreは、ファイルをBLOBとしてDXPデータベースに保存します。 DBStoreには、ファイルベースのストアタイプ（Simple、Advanced、S3）が持つトランザクションロールバックの脆弱性はありません。

DBStoreのファイルサイズ制限は1ギガバイトです。 1ギガバイトを超えるファイルを保存するには、[Simple File System Store](./simple-file-system-store.md)または[Advanced File System Store](../configuring-file-storage.md#configuring-advanced-file-system-store)を使用します。

## ストアの構成

1.  次のプロパティを使用して[`portal-ext.properties`](../../../installation-and-upgrades/reference/portal-properties.md)を設定します。

    ``` properties
    dl.store.impl=com.liferay.portal.store.db.DBStore
    ```

2.  DXPを再起動します。

DXPがファイルをDBStoreに保存するようになりました。

## 追加情報

  - [Configuring File Storage](../configuring-file-storage.md)
  - [File Store Migration](../file-store-migration.md)
