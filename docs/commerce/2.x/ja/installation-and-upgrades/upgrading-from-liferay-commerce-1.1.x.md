# Liferay Commerce 1.1.xからのアップグレード

この記事では、Liferay Commerceを最新のCommerce 2.xバージョンにアップグレードする方法について説明します。 ストア管理者は、バグ修正と新機能を入手するために、利用可能な最新の商品バージョンに定期的にアップデートすることを検討する必要があります。

## ロードマップ

1.  ダウンロードとデプロイ。
2.  最新のフィックスパックを適用します（加入者のみ）。
3.  古いデータを消去し、アップグレードプロセスを確認します。
4.  アップグレード後のインデックス再作成を実行します。

## ダウンロードとデプロイ

1.  最新のLiferay Commerce Enterprise `LPKG`を[ヘルプセンター](https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118190997&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191001)からダウンロードします。

    > 最新のLiferay Commerce `LPKG`は、[Liferay Commerceコミュニティのダウンロードページ](https://commerce.liferay.dev/download)から取得できます。

2.  `LPKG`を`${liferay.home}/deploy`フォルダにデプロイします。

    > Liferay DXPにアプリケーションをデプロイする詳細については、[ Liferay Home](https://help.liferay.com/hc/en-us/articles/360028712272-Liferay-Home)を参照してください。

3.  以下に示すようなメッセージがアプリケーションサーバーコンソールに表示されることを確認します。
   
        Processing Liferay Commerce Enterprise x.x.x.lpkg
       
        The portal instance needs to be restarted to complete the installation of file:/../../liferay-commerce-enterprise-1.1.6/osgi/marketplace/Liferay%20Commerce%20-%20API.lpkg
       
        The portal instance needs to be restarted to complete the installation of file:/../../liferay-commerce-enterprise-1.1.6/osgi/marketplace/Liferay%20Commerce%20-%20Impl.lpkg

4.  アプリケーションサーバーをシャットダウンします。

## 最新のフィックスパックを適用する（加入者のみ）

Liferay Commerceをアップグレードする前に、Liferay Digital Experience Platform（DXP）を利用可能な最新のフィックスパックリリースにアップデートします。 たとえば、Liferay Commerce Enterprise 2.0.6にアップグレードする場合は、Liferay DXPをフィックスパック14にアップグレードする必要があります。 最新のフィックスパックリリースは、[ヘルプセンター](https://customer.liferay.com/downloads)から入手できます。

フィックスパックは、Liferayパッチングツールを使用してLiferay DXPインストールに適用されます。 詳細については、[Using the Patching Tool](https://help.liferay.com/hc/articles/360018176551-Using-the-Patching-Tool)および[Configuring the Patching Tool](https://help.liferay.com/hc/articles/360018176611-Configuring-the-Patching-Tool)を参照してください。

次に、フィックスパックを適用します。 詳細については、[Installing Patches](https://help.liferay.com/hc/en-us/articles/360028810512-Installing-Patches)を参照してください。 Liferay DXPを[手動でインストールした場合](https://help.liferay.com/hc/articles/360017896672-Installing-Liferay-DXP-Manually-)（WebLogicなどで）は、[Installing Patches on the Liferay DXP 7.1 WAR](https://help.liferay.com/hc/articles/360018176651-Installing-patches-on-the-Liferay-DXP-7-1-WAR)を参照してください。

フィックスパックのインストールを検証するには、以下を実行します。

1.  `${liferay.home}/patching-tool`フォルダに移動します。

2.  以下を実行して、フィックスパックが適用されたことを確認します。

      - Linux/Unix：`./patching-tool.sh info`
      - Windows：`patching-tool info`
    
    <!-- end list -->
    
        詳細なパッチリスト： [ -] dxp-12-7110 :: Currently not installed; Won't be installed: dxp-14 contains the fixes included in this one :: Built for LIFERAY [*I] dxp-14-7110 :: Installed; Will be installed. :: Built for LIFERAY

フィックスパックは本質的に累積的であり、以前にリリースされたすべてのフィックスパックが含まれています。 パッチ適用後、`${liferay.home}/work`フォルダの内容を削除して、Liferay DXPのデプロイされたコードのキャッシュを削除します。 他の古いデータを削除する方法については、次のセクションを参照してください。

## 古いデータを消去し、アップグレードプロセスを確認する

1.  `${liferay.home}/osgi/state`フォルダを削除します。

    > OSGiフォルダの詳細については、[Installing Apps Manually](https://help.liferay.com/hc/en-us/articles/360017895412-Installing-Apps-Manually#using-your-file-system-to-install-apps)を参照してください。

2.  アプリケーションサーバーを起動します。

3.  アプリケーションサーバーのコンソールログで次のようなメッセージを探して、アップグレードプロセスが開始されたことを確認します。
   
        Upgrading com.liferay.commerce.account.internal.upgrade.v1_2_0.CommerceAccountGroupCommerceAccountRelUpgradeProcess
        Completed upgrade process com.liferay.commerce.account.internal.upgrade.v1_2_0.CommerceAccountGroupCommerceAccountRelUpgradeProcess in 24 ms
        Upgrading com.liferay.commerce.account.internal.upgrade.v1_2_0.CommerceAccountGroupRelUpgradeProcess
        Completed upgrade process com.liferay.commerce.account.internal.upgrade.v1_2_0.CommerceAccountGroupRelUpgradeProcess in 8 ms
        Upgrading com.liferay.commerce.account.internal.upgrade.v1_2_0.CommerceAccountGroupUpgradeProcess
        Completed upgrade process com.liferay.commerce.account.internal.upgrade.v1_2_0.CommerceAccountGroupUpgradeProcess in 12 ms
        Upgrading com.liferay.commerce.account.internal.upgrade.v1_3_0.CommerceAccountNameUpgradeProcess
        Starting com.liferay.portal.kernel.upgrade.UpgradeProcess#alter
        Completed com.liferay.portal.kernel.upgrade.UpgradeProcess#alter in 40 ms
        Completed upgrade process com.liferay.commerce.account.internal.upgrade.v1_3_0.CommerceAccountNameUpgradeProcess in 60 ms
        Starting com.liferay.portal.upgrade.internal.index.updater.IndexUpdaterUtil#updateIndexes#Updating database indexes for com.liferay.commerce.account.service
        Dropping stale indexes
        Adding indexes
       
        Verifying com.liferay.commerce.product.internal.verify.CommerceCatalogServiceVerifyProcess
        Starting com.liferay.commerce.product.internal.verify.CommerceCatalogServiceVerifyProcess#verifyMasterCommerceCatalog
        Completed com.liferay.commerce.product.internal.verify.CommerceCatalogServiceVerifyProcess#verifyMasterCommerceCatalog in 2 ms
        Completed verification process com.liferay.commerce.product.internal.verify.CommerceCatalogServiceVerifyProcess in 7 ms
        1 theme for admin-theme is available for use
        1 theme for classic-theme is available for use
        1 theme for minium-theme is available for use

Liferay Commerceインスタンスがアップグレードされました。

## アップグレード後のインデックス再作成を実行する

Liferay Commerce 1.1.xから最新バージョンにアップグレードした後、完全な検索インデックスの再作成を実行します。

検索インデックスの再作成を実行するには：

1.  *[Control Panel]* → *[Configuration]* → *[Search]*に移動します。
2.  *[Reindex all search indexes]*の隣にある*[Execute]*をクリックします。
3.  インデックスの再作成が完了するまで待ちます。
4.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。
5.  すべての商品が再び表示されることを確認します。

インデックスの再作成が完了すると、アップグレードされたLiferay Commerceインスタンスを使用する準備が整います。

## 追加情報

  - [Installing Apps Manually](https://help.liferay.com/hc/en-us/articles/360017895412-Installing-Apps-Manually#using-your-file-system-to-install-apps)
  - [Liferay Commerce Fix Delivery Method](../../get-help/commerce-enterprise-support/liferay-commerce-fix-delivery-method.md)
