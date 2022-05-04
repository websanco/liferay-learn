# DXPのナビゲート

Liferay DXPのすぐに使えるナビゲーションは、 **個人用メニュー** 、 **グローバルメニュー** 、 **サイトメニュー** の3つの主要な領域に編成されています。

<a name="site-menu" />

## サイトメニュー

画面の左側にあるDXP **サイトメニュー** には、現在表示されている特定のサイトを対象とするアクション、コンテンツ、および構成が含まれています。 画面の左上隅にあるアイコン（![Site Menu icon](../images/icon-menu.png)）をクリックして展開するまで、サイトメニューを非表示にしておくことができます。

![サイトメニューを展開して、すべてのオプションを表示します。](./navigating-dxp/images/05.png)

```{note}
   *サイトメニュー*は選択したサイトを示します。 この例では、サイトは「Liferay」です。 サイトメニューのコンパスアイコンをクリックして、作業しているサイトを変更できます。
```

次の一般的なサイト構築および管理アクティビティは、 **サイトメニュー** で実行できます。

* [ページを追加する](../site-building/creating-pages/adding-pages/adding-a-page-to-a-site.md)
* [コンテンツを作成する](../content_authoring_and_management.html)
* [サイトメンバーシップを管理する](../site-building/building-sites/site-membership/adding-members-to-sites.md)
* [サイトの動作と機能の設定と最適化](../site_building.html)
* 等々・・・

<a name="applications-bar" />

## アプリケーションバー

**アプリケーションバー** は、 [サイトメニュー](#site-menu) のさまざまなページとアプリケーションに表示されます。 アプリケーションバーには、現在開いているアプリケーションに応じて、さまざまなオプションが含まれるアクション（![Actions](../images/icon-actions.png)）メニューが表示されます。

![アプリケーションバーで使用できるアクションは、現在開いているアプリケーションによって異なります。](./navigating-dxp/images/07.png)

閲覧モードでページを開くと、アプリケーションバーで使用できるツールはページのタイプによって異なります。

![コンテンツページとウィジェットページには、アプリケーションバーに異なるツールセットが表示されます。](./navigating-dxp/images/08.png)

[コンテンツページ](../site-building/creating-pages/building-and-managing-content-pages/content-pages-overview.md) （A）の場合、使用可能なツールは次のとおりです。

- 編集（![Edit](../images/icon-edit.png)）
- ページ設定（![Configure Page](../images/icon-settings.png)）
- シミュレーション（![Simulation](../images/icon-simulation.png)）
- コンテンツパフォーマンス（![Performance](../images/icon-analytics.png)）
- A/Bテスト（![A/B Test](../images/icon-ab-testing.png)）
- ページ監査（![Page Audit](../images/icon-information.png)）

[ウィジェットページ](../site-building/creating-pages/using-widget-pages/adding-widgets-to-a-page.md) （B）の場合、使用可能なオプションは次のとおりです。

- ページ設定（![Configure Page](../images/icon-settings.png)）
- トグルコントロール（![Toggle Controls](../images/icon-preview.png)）
- Add (![Add](../images/icon-plus.png))
- シミュレーション（![Simulation](../images/icon-simulation.png)）
- コンテンツパフォーマンス（![Performance](../images/icon-analytics.png)）
- ページ監査（![Page Audit](../images/icon-information.png)）

```{note}
   コンテンツページとウィジェットページのアプリケーションバーオプションは、Lifera yDXPのバージョンによって異なります。 [コンテンツパフォーマンス](../content-authoring-and-management/content-performance-tool/about-the-content-performance-tool.md)は、Liferay DXP7.3以降のコンテンツページとLiferay DXP7.4以降のウィジェットページで利用できます。 ページ監査は、Liferay DXP7.4以降で利用できます。 トグルコントロールオプションは、Liferay DXP7.3以降で使用できます。
```

<a name="personal-menu" />

## パーソナルメニュー

パーソナルメニューは、ログインしているユーザーの情報とアクティビティのハブです。

![Liferay DXPのパーソナルメニューは、右上のアバターアイコンをクリックすると利用できます。](./navigating-dxp/images/01.png)

ユーザーはパーソナルメニューを使用して次のことができます。

* [アカウント情報を変更する](./introduction-to-the-admin-account.md#changing-account-information) （名前、パスワードなど）
* [メンバーとなっているサイトを確認する](../site-building/building-sites/site-membership/adding-members-to-sites.md)
* [サイト通知を確認する](../collaboration-and-social/notifications-and-requests/user-guide/managing-notifications-and-requests.md)
* [サインアウトする](./introduction-to-the-admin-account.md#signing-out)
* 等々・・・

<a name="global-menu" />

## グローバルメニュー

DXPの **グローバルメニュー** には、 **アプリケーションメニュー** と **コントロールパネル** が含まれています。 画面上部の **アプリケーションメニュー** アイコン（![Applications Menu icon](../images/icon-applications-menu.png)）をクリックしてアクセスします。

```{note}
   デフォルトでは、*ゲスト*または*ユーザー*のロールを持つユーザーは、グローバルメニューにアクセスできません。
```

右側の **グローバルメニュー** の ［**Applications**］ タブまたは ［**コントロールパネル**］ タブから、利用可能な他のサイトに移動することもできます。

### グローバルメニュー

**アプリケーションメニュー** には、Liferay DXPサーバー内のさまざまなアプリケーションを管理するためのさまざまなリンクが含まれています。

![アプリケーションメニューには、すべてのサイトに適用できる多くのグローバル設定と重要な機能が含まれています。](./navigating-dxp/images/02.png)

**アプリケーションメニュー** から管理できるアプリケーションの一部を次に示します。

* [コレクション](../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md)とコンテンツダッシュボード
* [ワークフロー](../process-automation/workflow/introduction-to-workflow.md)
* [アプリビルダー](../developing-applications/developing-low-code-applications/app-builder-overview.md)

### コマースメニュー

Liferay 7.3 CE GA6およびLiferay DXP 7.3 GA1以降、Liferay Commerce 3.0はLiferay Portal 7.3 CE GA6およびLiferay DXP 7.3 GA1にバンドルされるようになりました。

![コマースメニューには、すべてのストア機能が含まれています。](./navigating-dxp/images/03.png)

**コマース** メニューから管理できる機能は次のとおりです。

* [新しいカタログを作成する](https://learn.liferay.com/commerce/latest/ja/product-management/catalogs/creating-a-new-catalog.html)
* [新しいチャンネルを作成する](https://learn.liferay.com/commerce/latest/ja/starting-a-store/channels/managing-channels.html)
* [在庫を管理する](https://learn.liferay.com/commerce/latest/ja/product-management/managing-inventory/introduction-to-managing-inventory.html)
* [注文を処理する](https://learn.liferay.com/commerce/latest/ja/order-management/orders/processing-an-order.html)
* その他

Liferay Commerceの詳細については、 [Liferay Commerceの概要](https://learn.liferay.com/commerce/latest/ja/starting-a-store/introduction-to-liferay-commerce.html) を参照してください。

### コントロールパネル

**コントロールパネル** には、Liferay DXPインストールまたは特定のインスタンスに **グローバル** にスコープできる一般的な管理アクションと構成が含まれています。

![これで、コントロールパネルがグローバルメニューの一部になりました。](./navigating-dxp/images/04.png)

コントロールパネルでは、次の一般的な管理アクティビティを実行できます。

* [ユーザー、権限、ロールを管理する](../users-and-permissions/users/adding-and-managing-users.md)
* [ユーザーのログイン方法を変更する](../installation-and-upgrades/securing-liferay/authentication-basics.md)
* [新しいサイトを作成する](../site-building/building-sites/adding-a-site.md)
* サイト上のアプリケーションに新しいカスタムフィールドを追加する
* 等々・・・

<a name="dxp-71-and-72" />

## DXP7.1および7.2

7.3より前のLiferay DXPバージョンでは、代わりにグローバルメニューのすべてのコンテンツがコントロールパネルに含まれています。 これらのバージョンでは、コントロールパネル自体が画面左側のサイトメニューの上にあります。

![DXP 7.1および7.2のコントロールパネルは、サイトメニューの上にあります。](./navigating-dxp/images/06.png)

<a name="whats-next" />

## 次のステップ

引き続きスタートガイドを進め、 [最初のサイトを作成する](./creating-your-first-site.md) 方法について学習します。

<a name="related-information" />

## 関連情報

- [Creating your first Site](./creating-your-first-site.md)
- [Changing your Site's Appearance](./changing-your-sites-appearance.md)
