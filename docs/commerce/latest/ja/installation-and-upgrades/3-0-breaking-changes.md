# 3.0 旧バージョンと非互換の変更

このドキュメントは、サードパーティのLiferay Commerce開発者またはCommerce 3.0.のユーザーとの既存の機能、API、または契約に違反する変更の時系列リストを示しています。 私たちはこれらの混乱を最小限にするために最善を尽くしていますが、時には避けられないこともあります。

このファイルに記載されている変更の種類の一部を次に示します。

  - 削除または置換される機能

  - APIの非互換性：パブリックJavaまたはJavaScript APIの変更.

  - テンプレートで利用可能なコンテキスト変数の変更.

  - Liferayテーマおよびポートレットで利用可能なCSSクラスの変更.

  - 設定の変更：`com.liferay.commerce.*.cfg`などの設定ファイルの変更

  - 実行要件：Javaバージョン、J2EEバージョン、ブラウザーバージョンなど

  - 非推奨またはサポート終了：たとえば、次のバージョンで特定の機能またはAPIが停止されると警告している

  - 推奨事項：たとえば、後方互換性のためにLiferay Portalに古いAPIが保持されているにもかかわらず、古いAPIに代わる、新しく導入されたAPIを使用することを推奨している

## Commerceメニューの変更

  - **日付：** 2020年9月3日

  - **JIRAチケット：** [COMMERCE-4565](https://issues.liferay.com/browse/COMMERCE-4565)

### 何が変わりましたか？

Liferay DXP 7.3では、Commerceのメニュー項目がコントロールパネルから新しいグローバルメニューに移動しました。 新しい *Commerce* タブ（アプリケーションやコントロールパネルタブと同じレベル）には、これらのメニュー項目があります。

`commerce-admin-api`および`commerce-admin-web`モジュールも削除されます。

### 誰が影響を受けますか？

Commerce → Settingsに追加されたメニュー項目に影響します。 開発者は、`CommerceAdminModule`を実装して、Commerce → Settingsにメニュー項目を追加することができなくなります。

これはエンドユーザーにも影響し、エンドユーザーはグローバルメニューの中の新しいCommerceメニューからCommerce管理アイテムにアクセスできるようになります。

### なぜこの変更が行われたのですか？

Liferay DXP 7.3の規格に準拠しつつ、ナビゲーションスコープをヘッダーバーに残すためメニューを変更しました。 `commerce-admin-api`と`commerce-admin-web`モジュールは、古いメニューの場所が削除されたことにより、使用されなくなったため削除されました。

## 移動したファイル

  - **日付：** 2020年8月20日

  - **JIRAチケット：** [COMMERCE-4052](https://issues.liferay.com/browse/COMMERCE-4052)

### 何が変わりましたか？

以下のファイルが移動します。

  - `com.liferay.commerce.pricing.web.servlet.taglib.ui.CommerceDiscountScreenNavigationConstants`

  - `com.liferay.commerce.pricing.web.servlet.taglib.ui.CommercePricingClassScreenNavigationConstants`

### 誰が影響を受けますか？

これは、これらのファイルをコードで参照または使用している開発者に影響します。

### コードを更新するにはどうすればよいですか？

これらのファイルへの古い参照を、新しいパスに置き換えます。

ファイルはこれらのパスに配置されています。

  - `com.liferay.commerce.pricing.web.internal.constants.CommerceDiscountScreenNavigationConstants`

  - `com.liferay.commerce.pricing.web.internal.constants.CommercePricingClassScreenNavigationConstants`

### なぜこの変更が行われたのですか？

これらのファイルはLiferayのコーディング規約に沿って移動されました。

## 宛先名の変更

  - **日付：**2020年9月10日

  - **JIRAチケット：** [COMMERCE-4762](https://issues.liferay.com/browse/COMMERCE-4762)

### 何が変わりましたか？

プレフィックス `commerce_` が `com.liferay.commerce.constants.CommerceDestinationNames`で定義された Commerceの宛先に追加されました。

  - `liferay/commerce_order_status`

  - `liferay/commerce_payment_status`

  - `liferay/commerce_shipment_status`

  - `liferay/commerce_stock_quantity`

  - `liferay/commerce_subscription_status`

### 誰が影響を受けますか？

この変更は、コード内でこれらの宛先を参照または使用するすべての人に影響します。

### コードを更新するにはどうすればよいですか？

プレフィックス`commerce_`を持つCommerceの宛先への明示的な参照を更新します。

### なぜこの変更が行われたのですか？

宛先名はLiferayの命名規則に従って変更されました。
