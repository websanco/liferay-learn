# Liferay Commerce Tomcatバンドルの使用

Liferay Commerceバンドルは、[[Liferay Commerce Community Downloads]](https://commerce.liferay.dev/download)ページからダウンロードできます。 バンドルには、Apache Tomcatに事前デプロイされたLiferay CommerceおよびLiferay Portal CEの最新バージョンが含まれています。

バンドルを入手する前に、[Liferay Commerce互換性マトリックス](https://web.liferay.com/documents/14/21598941/Liferay+Commerce+2.0+Compatibility+Matrix/0ed97477-f5a7-40a6-b5ab-f00d5e01b75f)を読んで、サポートされているテクノロジーのリストを確認してください。

Liferay Commerceバンドルを使用するには、次の手順を実行します。

1.  Liferay Commerce Tomcatバンドルを入手します。

      - （サブスクライバー） [エンタープライズダウンロード](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118190997&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191001)
      - [コミュニティダウンロードページ](https://www.liferay.com/downloads-community)

2.  バンドルをターゲットの場所に解凍します。

3.  Tomcatバンドルを開始します

      - `$CATALINA_HOME/bin`に移動します。
      - `./catalina.sh run`を実行します。 以下のための *Windowsの* ユーザ、実行： `カタリナラン`
    
    <!-- end list -->
    
    ``` tip::
       「org.apache.catalina.startup.Catalina.startサーバーの起動が [x] ミリ秒以内」と表示されるまで待ちます
    ```

4.  ブラウザで`https://localhost:8080`を開きます。

![ウェルカムイメージ](./using-the-liferay-commerce-tomcat-bundle/images/01.png)

## 追加情報

  - [Installation Overview](./installation-overview.md)
  - [Installing Liferay DXP](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/installing-liferay/installing-a-liferay-tomcat-bundle.html)
  - [Liferay Commerce 2.0 互換性マトリクス](https://web.liferay.com/documents/14/21598941/Liferay+Commerce+2.0+Compatibility+Matrix/0ed97477-f5a7-40a6-b5ab-f00d5e01b75f)
