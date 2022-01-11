# 商品構成リファレンス

<!--TASK: Add Intro-->

## 商品の詳細

商品詳細のタブでは、商品のSEO、適用カテゴリー、仕様などを設定できます。

![商品の詳細を表示・設定することができます。](./product-configuration-reference/images/01.png)

### 一般商品の詳細

| フィールド             | 説明                         |
| ----------------- | -------------------------- |
| カタログ              | 商品に関連付けられたカタログで、この関連付けは永続的 |
| 名前                | 商品名                        |
| Short Description | 商品の短い要約                    |
| Full Description  | 商品の詳細な説明                   |

### SEO

| フィールド            | 説明                        |
| ---------------- | ------------------------- |
| Friendly URL     | 商品ページのURL                 |
| Meta Title       | 検索エンジンが商品を見つけるのに役立つ商品タイトル |
| Meta Description | 検索エンジンで使用される商品の説明         |
| Meta Keyword     | 検索エンジンで使用されるキーワード         |

### 仕様

| フィールド | 説明                 |
| ----- | ------------------ |
| Label | 仕様の名前              |
| Value | 仕様の値               |
| Group | 仕様を他の仕様グループにリンクする  |
| 配置    | 小さい番号から順に表示順序を決定する |

<!--TASK: ### Categorization

| Field | Description |
| --- | --- |
| [Catalog Name] | Determines which Catalog categories are applied to the Product |
| Topic |  |
| Tags |  | -->

### 別紙

| フィールド           | 説明                                        |
| --------------- | ----------------------------------------- |
| 公開済み            | カタログ上での商品の表示と販売を有効にする                     |
| Display Date    | 商品がカタログで最初に販売可能になった日付                     |
| Expiration Date | *[Never Expire]* がオフになっている場合、商品の販売終了日を決定する |
| Never Expire    | 商品の有効期限をスケジュールできるかどうかを決定する                |

## オプション

商品オプションは、商品にカスタムフィールドを追加する便利な方法です。 オプションを利用して、属性（サイズ、数量、色、素材など）やバンドルなどに基づいた商品バリアントを作成することができます。 詳細は、 [商品オプションの使用](./using-product-options.md) を参照してください。

![商品オプションを作成します。](./product-configuration-reference/images/02.png)

| フィールド    | 説明                                               |
| -------- | ------------------------------------------------ |
| オプションを追加 | 商品に既存のオプションを作成・適用するためのフィールド                      |
| オプション    | 商品に追加されたオプションの一覧。オプションをクリックすると、その設定や値を編集することができる |

## SKU

SKUは、商品のバリアントを表します。 詳しくは、 ［ [商品バリアントのSKU作成](./creating-skus-for-product-variants.md) ］を参照してください。

デフォルトでは、商品のSKUは親商品の仕様と構成を継承します。 しかし、これらの設定の一部は、個別に上書きすることができます。 詳細は、 [商品レベルの情報を上書きする](./overriding-product-level-information.md) を参照してください。

![商品バリアントに対して複数のSKUを生成します。](./product-configuration-reference/images/03.png)

## メディア

ユーザーは商品画像と添付ファイルをアップロードできます。 詳細は、 [商品イメージ](./product-images.md) および [商品添付](./product-attachments.md) を参照してください。

![画像と添付ファイルをアップロードします。](./product-configuration-reference/images/04.png)

## 関連商品

関連する商品を結合するには、関連商品を使用します。 結合されると、これらの関係は商品パブリッシャーウィジェットを使って、商品の表示ページに表示することができます。 詳細は、[関連商品, Up-Sells, and Cross-Sells](./related-products-up-sells-and-cross-sells.md)を参照してください。

![プロダクトリレーションシップを設定します。](./product-configuration-reference/images/05.png)

<!--TASK: ## Product Groups

![](./product-configuration-reference/images/06.png) -->

## サブスクリプション

商品のサブスクリプションオプションを作成および管理します。 例えば、雑誌、更新オプションのあるサービス契約、定期的に消費される商品などがあります。 詳細は、 [商品](./enabling-subscriptions-for-a-product.md) サブスクリプションの有効化を参照してください。

![サブスクリプションを有効にします。](./product-configuration-reference/images/07.png)

## 可視性

デフォルトでは、商品はすべてのチャネルとカタログに表示されますが、特定のチャネルやアカウントグループに表示を制限することもできます。 詳細は、 [チャネルを使用した商品の可視性の構成](../../../starting-a-store/channels/configuring-product-visibility-using-channels.md) および [アカウントグループを使用した商品の可視性の構成](./configuring-product-visibility-using-account-groups.md) を参照してください。

![商品の可視性の管理](./product-configuration-reference/images/08.png)

<!--TASK: ## Configuration

![](./product-configuration-reference/images/09.png) -->

<!--TASK: ## Grouped

> For Grouped Products Only -->

<!--TASK: ## Virtual

> For Virtual Products Only 

See [Virtual Product Reference](./../product-types/virtual-product-reference.md) -->

## 追加情報

* [シンプル商品の作成](../product-types/creating-a-simple-product.md)
* [グループ商品の作成](../product-types/creating-a-grouped-product.md)
* [仮想商品の作成](../product-types/creating-a-virtual-product.md)
* [仮想商品リファレンス](../product-types/virtual-product-reference.md)
