# IBM Cloud Object Storage

Liferay DXPは、IBMの[Cloud Object Storage](https://cloud.ibm.com/docs/cloud-object-storage?topic=cloud-object-storage-getting-started-cloud-object-storage)ストアをS3互換性レイヤーとともに実装しているため、AWS S3 Storeと同じ設定を使用します。 IBMアカウントを設定してバケットを作成したら、インスタンスのS3 Storeを設定する準備が整います。

## ストアの構成

1.  グローバルメニューの*[コントロールパネル]* タブを開き、*[システム設定]* → *[ファイルストレージ]* → *[S3 Store]* に移動します。

2.  少なくとも、次の構成値を入力してください。

      - **バケット名**：S3がファイルを保存するために使用するバケット名を設定します。
      - **アクセスキー**：アカウントアクセスキーを設定します。
      - **シークレットキー**：アカウントのシークレットキーを設定します。
      - **S3エンドポイント**：S3への接続に使用するデフォルトのエンドポイントを設定します。
      - **S3リージョン**：S3リージョンを設定します。これは、S3がユーザーが作成したバケットを格納する地理的リージョンを表します。
    
    <!-- end list -->
    
    ```{important}
    Currently, the store only supports *HMAC credentials*. See official [IBM Cloud Object Storage documentation](https://cloud.ibm.com/docs/cloud-object-storage?topic=cloud-object-storage-uhc-hmac-credentials-main) for more information.
    ```

3.  完了したら、*[保存]* をクリックします。

システム設定の構成を行ったら、IBM Cloud Object Storageのストアをデフォルトとして設定する必要があります。 これを行うには、次のプロパティを`portal-ext.properties`ファイルに設定します。

``` properties
dl.store.impl=com.liferay.portal.store.s3.IBMS3Store
```

## クラスター環境でのストアの使用

クラスター環境でIBM Cloud Object Storageのストアを使用するには、すべてのノードの構成が同一である必要があります。 特に、 `portal-ext.properties`ファイルと、システム設定のS3 Storeの設定が同じであることを確認してください。

## データベースのロールバックの制限

ドキュメントライブラリでデータベーストランザクションのロールバックが発生した場合、トランザクションのファイルシステムの変更は元に戻されません。 ドキュメントライブラリファイルとファイルシステムストア内のファイルとの間に不整合が発生する可能性があり、手動での同期が必要になる場合があります。 DBStoreを除くすべてのDXPストアは、この制限に対して脆弱です。

IBMのサービスの使用に関する追加の詳細については、IBM Cloud Object Storageの公式資料を参照してください。

## 追加情報

  - [ファイル ストレージ](../../file-storage.md)
  - [Amazon S3 Store](./amazon-s3-store.md)
  - [DBStore](./dbstore.md)
