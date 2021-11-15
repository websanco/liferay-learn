# Liferay Commerceを既存のLiferayインストールにデプロイする

この記事では、Liferay Commerceを既存のLiferay DXPインスタンスにデプロイする方法について説明します。 Liferay CommerceはLiferay DXP上に構築されているため、既存のDXP 7.1または7.2インスタンスを実行している必要があります。 このガイドに従う前に、システム要件を確認してください。

Liferay Commerceの使用を開始するには：

1.  最新のLiferay Commerce `LPKG`をダウンロードします。

      - エンタープライズの加入者は、最新の`LPKG`を[ヘルプセンター](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118190997&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191001)からダウンロードできます。
          - 加入者の方は、Liferay Commerceの注文番号またはアクティベーションキーも必要です。 [Activating a Marketplace App Through a Proxy Server](https://help.liferay.com/hc/en-us/articles/360018427391)を参照してください。
      - 最新のオープンソース `LPKG` は、 [Liferay Commerce Communityダウンロードページ](https://www.liferay.com/downloads-community)から入手できます。

2.  `LPKG`を`${liferay.home}/deploy`フォルダにデプロイします。

    ```{note}
    アプリケーションのLiferay DXPへのデプロイについては、 <https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/liferay-home.html>Liferay Home</0> を参照してください。
    ```

3.  （*サブスクライバー*） `${liferay.home}/ deploy` フォルダーにアクティベーションキーを展開します。

4.  アプリケーションサーバーコンソールに次のメッセージが表示されることを確認します。
   
       2019-08-09 00:25:10.662 INFO  [fileinstall-/../../liferay-dxp-7.1.10.1-sp1/osgi/marketplace][LPKGArtifactInstaller:202] The portal instance needs to be restarted to complete the installation of file:/../../liferay-dxp-7.1.10.1-sp1/osgi/marketplace/Liferay%20Commerce%20-%20API.lpkg
       2019-08-09 00:25:10.664 INFO  [fileinstall-/../..//liferay-dxp-7.1.10.1-sp1/osgi/marketplace][LPKGArtifactInstaller:202] The portal instance needs to be restarted to complete the installation of file:/../../liferay-dxp-7.1.10.1-sp1/osgi/marketplace/Liferay%20Commerce%20-%20Impl.lpkg

5.  アプリケーションサーバーを完全にシャットダウンします。

6.  アプリケーションサーバーを起動します。

7.  次のメッセージを探して、`LPKG`がインストールを開始したことを確認します。
   
       2019-08-09 00:32:48.850 INFO  [main][BaseDeployer:877] Deploying minium-theme.war

8.  **最初の**デプロイでは、一連のコマンドの後に次のメッセージが続きます。
   
       2019-08-09 00:34:51.753 INFO  [main][ThemeHotDeployListener:108] 1 theme for minium-theme is available for use

9.  初回立ち上げ時には、Liferay Commerceのデフォルトコンテンツを投入するためにreindexを実行し、*[Control Panel]* → *[Configuration]* → *[Search]* に移動します。

10. *[Reindex all search indexes]* の隣にある*[Execute]* をクリックします。

Liferay Commerceがデプロイされ、使用できる状態になりました。 ストア管理者は、ストアフロントの構築とカタログの作成を開始できます。

## 追加情報

アプリケーションサーバーを再起動した場合、Liferay Commerceは`VerifyProcess`コマンドを再度実行しないことに注意してください。

  - [Liferay Commerce 3.0 Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360049238151)
  - [インストールの概要](../installation-overview.md)
  - [Using the Liferay Commerce Tomcat Bundle](./using-the-liferay-commerce-tomcat-bundle.md)
