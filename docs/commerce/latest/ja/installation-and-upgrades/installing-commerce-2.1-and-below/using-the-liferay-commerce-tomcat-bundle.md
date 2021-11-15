# Liferay Commerce Tomcatバンドルの使用

Liferay Commerceバンドルは、[[Liferay Commerce Community Downloads]](https://commerce.liferay.dev/download)ページからダウンロードできます。 バンドルには、Apache Tomcatに事前デプロイされたLiferay CommerceおよびLiferay Portal CEの最新バージョンが含まれています。

バンドルを入手する前に、[Liferay Commerce互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360049238151)を読んで、サポートされているテクノロジーのリストを確認してください。

Liferay Commerceバンドルを使用するには、次の手順を実行します。

1.  Liferay Commerce Tomcatバンドルを入手します。

      - （サブスクライバー） [エンタープライズダウンロード](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118190997&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191001)
      - [コミュニティダウンロードページ](https://www.liferay.com/downloads-community)

2.  バンドルをターゲットの場所に解凍します。

3.  Tomcatバンドルを開始します

      - `$CATALINA_HOME/bin`に移動します。
      - `./catalina.sh run`を実行します。 以下のための *Windowsの* ユーザ、実行： `カタリナラン`
    
    <!-- end list -->
    
    ```{tip}
    Wait until you see `org.apache.catalina.startup.Catalina.start Server startup in [x] milliseconds`
    ```

4.  ブラウザで`https://localhost:8080`を開きます。

![ウェルカムイメージ](./using-the-liferay-commerce-tomcat-bundle/images/01.png)

## 追加情報

  - [インストールの概要](../installation-overview.md)
  - [Installing Liferay DXP](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/installing-liferay/installing-a-liferay-tomcat-bundle.html)
  - [Liferay Commerce 3.0 Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360049238151)
