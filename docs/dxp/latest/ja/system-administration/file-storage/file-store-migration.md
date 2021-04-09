# ファイルストアの移行

ファイルは、あるファイルストアタイプから別のタイプに移行できます。 次に、いくつかの移行シナリオを示します。

  - Simple File System StoreからAdvanced File System Storeに移行すると、パフォーマンスとスケーラビリティが向上します。
  - トランザクションロールバックの脆弱性を回避するには、Simple、Advanced、またはS3からDBStoreに移行します。
  - 1ギガバイトを超えるファイルに対応するには、DBStoreからAdvanced File System Storeに移行します。

[サーバー管理](../using-the-server-administration-panel/using-the-server-administration-panel.md) のデータ移行ユーティリティは、移行を容易にします。

``` warning::
   移行する前に、**必ず** `ファイルストアとデータベースをバックアップ <../../installation-and-upgrades/maintaining-a-liferay-dxp-installation/backing-up.md>`_ してください。 バックアップコピーで移行プロセスをテストすることをお勧めします。
```

## ファイルの移行

移行手順は次のとおりです。

1.  *[System Settings]* → *[File Storage]* で新しいファイルストアを設定します。

2.  *[Server Administration]* → *[Data Migration]* で、リポジトリフック（ストア実装クラス）を選択し、*[Execute]* をクリックします。

    ![データ移行ユーティリティ](./file-store-migration/images/01.png)

3.  データが正しく移行されたことを確認します。

4.  次のいずれかの設定を使用して、[`portal-ext.properties`](../../installation-and-upgrades/reference/portal-properties.md)をターゲットファイルのストアタイプに設定します。

    ``` properties
    dl.store.impl=com.liferay.portal.store.db.DBStore
    dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
    dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
    dl.store.impl=com.liferay.portal.store.s3.S3Store
    ```

5.  DXPを再起動します。

DXPインスタンスが、新しく設定されたファイルストア上に移行されたファイルを使用するようになりました。

``` warning::
   システム設定で新しいストア構成を必ず保存してください。 そうしないと、新しいストアを使用できず、次の例外が発生します：`java.lang.IllegalStateException: Store is not available`
```

## 追加情報

  - [ファイルストレージの構成](./configuring-file-storage.md)
  - [Amazon S3 Store](./other-file-store-types/amazon-s3-store.md)
  - [DBStore](./other-file-store-types/dbstore.md)
