# Amazon Simple Storage Service

Amazonのシンプルストレージサービス（S3）は、DXPのS3ストアがファイルをクラウドにシームレスに保存するために使用するクラウドベースのストレージソリューションです。 AWSアカウントを取得し、S3*バケット*を作成したら、S3ストアを構成できます。

[AWSアカウント](https://aws.amazon.com/s3/)を作成すると、Amazonから自分のアカウントにリンクする[一意のキー](https://docs.aws.amazon.com/general/latest/gr/aws-sec-cred-types.html/)が割り当てられます。 これらのキーを使用して、次のセクションでS3ストアを構成します。

S3はファイルストレージに*バケット*の概念を使用しています。 AmazonのUIで、DXPファイルの[バケット](https://docs.aws.amazon.com/AmazonS3/latest/user-guide/create-bucket.html)を作成します。 バケットは、DXPサーバーホストにできるだけ近い[地理的地域](https://docs.aws.amazon.com/general/latest/gr/s3.html)に設定してください。

``` note::
   AWS署名バージョン4リクエストの承認をサポートするためのアクションは必要ありません。
```

## ストアの構成

次の手順に従ってストアを構成します。

1.  Amazon S3にはSAXParserが必要です。 アプリケーションサーバーにSAXParserがまだない場合は、[Xerces SAXParser](https://xerces.apache.org/mirrors.cgi)をアプリケーションサーバーのグローバルライブラリフォルダ（Tomcatの場合は`/lib/ext`、JBoss EAPやWildFlyの場合は`/module`など）にダウンロードできます。

2.  次のプロパティを使用して、[`system-ext.properties`](../../../installation-and-upgrades/reference/system-properties.md)ファイルでSAXParserを指定します。

    ``` properties
    org.xml.sax.driver=com.sun.org.apache.xerces.internal.parsers.SAXParser
    ```

3.  `system-ext.properties`ファイルを、DXPアプリケーションのクラスパス（例：`/WEB-INF/classes/`）にあるフォルダにコピーします。

4.  次のプロパティを使用して[`portal-ext.properties`](../../../installation-and-upgrades/reference/portal-properties.md)を設定します。

    ``` properties
    dl.store.impl=com.liferay.portal.store.s3.S3Store
    ```

5.  DXPを再起動します。

6.  コントロールパネルで、*[Configuration]* → *[System Settings]* → *[File Storage]* に移動します。

7.  *[S3 Store]* 画面で、ストアを構成します。

      - **Bucket Name**：バケットの名前。
      - **Access Key**：AWSアクセスキー。
      - **Secret Key**：秘密鍵。
      - **S3 Region**：[地理的地域](https://docs.aws.amazon.com/general/latest/gr/s3.html)。デフォルトは`us-east-1`です。

    ![Amazon S3ストアの構成](./amazon-s3-store/images/01.png)

8.  他のフィールドはデフォルト設定のままにします。

9.  *[保存]* をクリックします。

DXPインスタンスがAmazon S3ストアを使用するようになりました。

## クラスター環境でのストアの使用

クラスター環境でS3ストアを使用するには、以下の手順に従います。

1.  アプリケーションサーバーにまだSAXParserがない場合は、[Xerces SAXParser](https://xerces.apache.org/mirrors.cgi)を各ノードのアプリケーションサーバーのグローバルライブラリフォルダにコピーします。

2.  `system-ext.properties`ファイルをDXPアプリケーションの`/WEB-INF/classes/`フォルダにコピーします。

DXPがクラスター全体でAmazon S3ストアを使用するようになりました。

## データベースのロールバックの制限

**警告**：ドキュメントライブラリでデータベーストランザクションのロールバックが発生した場合、トランザクションのファイルシステムの変更は元に戻され**ません**。 ドキュメントライブラリファイルとファイルシステムストア内のファイルとの間に不整合が発生する可能性があり、手動での同期が必要になる場合があります。 [DBStore](./dbstore.md)を除くすべてのDXPストアは、この制限に対して脆弱です。

Amazonのサービスの使用に関する詳細については、Amazon Simple Storageのドキュメントを参照してください。

## 追加情報

  - [Configuring File Storage](../configuring-file-storage.md)
  - [Bucket Restrictions and Limitations](https://docs.aws.amazon.com/AmazonS3/latest/dev//BucketRestrictions.html#bucketnamingrules)
  - [DBStore](./dbstore.md)
  - [File Store Migration](../file-store-migration.md)
