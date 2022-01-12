# Liferay Commerceを既存のLiferayインストールにデプロイする

この記事では、Liferay Commerceを既存のLiferay DXPインスタンスにデプロイする方法について説明します。 Liferay CommerceはLiferay DXP上に構築されているため、既存のDXP 7.1または7.2インスタンスを実行している必要があります。 このガイドに従う前に、システム要件を確認してください。

Liferay Commerceの使用を開始するには：

1. 最新のLiferay Commerce `LPKG`をダウンロードします。
    * エンタープライズの加入者は、最新の`LPKG`を [ヘルプセンター](https://customer.liferay.com/downloads?p **p** id=com **liferay** osb **customer** downloads **display** web **DownloadsDisplayPortlet&** com **liferay** osb **customer** downloads **display** web **DownloadsDisplayPortlet** productAssetCategoryId=118190997& **com** liferay **osb** customer **downloads** display **web** DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191001) からダウンロードできます。
      * 加入者の方は、Liferay Commerceの注文番号またはアクティベーションキーも必要です。 [Activating a Marketplace App Through a Proxy Server](https://help.liferay.com/hc/en-us/articles/360018427391) を参照してください。
    * 最新のオープンソース `LPKG` は、 [Liferay Commerce Communityダウンロードページ](https://www.liferay.com/downloads-community) から入手できます。

1. `LPKG`を`${liferay.home}/deploy`フォルダにデプロイします。

    ```{note}
       LiferayのDXPへのアプリケーションの展開の詳細は、 `Liferayのホームページを参照 してください<https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/liferay-home.html>` _。
    ```

1. （**サブスクライバー**） `${liferay.home}/ deploy` フォルダーにアクティベーションキーを展開します。
1. アプリケーションサーバーコンソールに次のメッセージが表示されることを確認します。

    ```
    2019-08-09 00:25:10.662 INFO  [fileinstall-/../../liferay-dxp-7.1.10.1-sp1/osgi/marketplace][LPKGArtifactInstaller:202] The portal instance needs to be restarted to complete the installation of file:/../../liferay-dxp-7.1.10.1-sp1/osgi/marketplace/Liferay%20Commerce%20-%20API.lpkg
    2019-08-09 00:25:10.664 INFO  [fileinstall-/../..//liferay-dxp-7.1.10.1-sp1/osgi/marketplace][LPKGArtifactInstaller:202] The portal instance needs to be restarted to complete the installation of file:/../../liferay-dxp-7.1.10.1-sp1/osgi/marketplace/Liferay%20Commerce%20-%20Impl.lpkg
    ```

1. アプリケーションサーバーを完全にシャットダウンします。
1. アプリケーションサーバーを起動します。
1. 次のメッセージを探して、`LPKG`がインストールを開始したことを確認します。

    ```
    2019-08-09 00:32:48.850 INFO  [main][BaseDeployer:877] Deploying minium-theme.war
    ```

1. **最初の** デプロイでは、一連のコマンドの後に次のメッセージが続きます。

    ```
    2019-08-09 00:34:51.753 INFO  [main][ThemeHotDeployListener:108] 1 theme for minium-theme is available for use
    ```

1. 初回立ち上げ時には、Liferay Commerceのデフォルトコンテンツを投入するためにインデックスの再構築を実行し、［**コントロールパネル**］ → ［**設定**］ → ［**検索機能**］に移動します。
1. ［**全ての検索インデクスの再構築**］の隣にある［**実行**］をクリックします。

Liferay Commerceがデプロイされ、使用できる状態になりました。 ストア管理者は、ストアフロントの構築とカタログの作成を開始できます。

<a name="additional-information" />

## 追加情報

アプリケーションサーバーを再起動した場合、Liferay Commerceは`VerifyProcess`コマンドを再度実行しないことに注意してください。

* [Liferay Commerce3.0互換性マトリクス](https://help.liferay.com/hc/en-us/articles/360049238151)
* [インストールの概要](../installation-overview.md)
* [Liferay CommerceTomcatバンドルの使用](./using-the-liferay-commerce-tomcat-bundle.md)
