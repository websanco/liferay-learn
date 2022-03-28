# 商品バリアントのSKU作成

Liferay Commerceでは、複数のSKUを商品に追加して商品バリアントを表すことができます。 これを行うためには、商品には少なくとも1つの ［[ Option ](../products/using-product-options.md)］ と ［**SKU コントリビューター**］が有効になっている必要があります。 その後、オプションの定義された値を使用して、商品の複数の有効なSKUを手動または自動で作成することができます。 SKUが作成されると、各SKUは、「商品詳細」ウィジェットに表示される商品の購入可能なバージョンを表します。

![商品バリアントは、商品詳細ウィジェットに表示されます。](./creating-skus-for-product-variants/images/01.png)

```{note}
   商品オプションがない場合、1つの商品に対して同時に1つのSKUしか有効にできません。
```

```{tip}
   商品オプションの値は、SKUの作成に使用する前に確定することを強くお勧めします。 詳しくは、`Using Product Options <./using-product-options.md`>_ を参照してください。
```

<a name="automatically-generating-multiple-skus" />

## 複数のSKUを自動的に生成

1つの商品に複数のSKUを生成して有効にするには、以下の手順に従ってください：

1. ［**グローバルメニュー**］ (![Global Menu](../../../images/icon-applications-menu.png))を開き、［**Commerce**］タブをクリックして、［**商品**］に移動します。

1. SKUコントリビューターが有効になっているオプションが1つ以上ある商品をクリックし、 ［**SKUs**］タブに移動します。

1. ［**追加**］ボタン（![Add Button](../../../images/icon-add.png)）をクリックし、 [**SKU のすべての組み合わせを生成**] を選択します。

   ![「SKU のすべての組み合わせを生成」を選択します。](./creating-skus-for-product-variants/images/02.png)

   SKUはオプション値ごとに自動的に生成されます。 これらのSKUは、値の名前を使用し、基本価格や在庫なしで作成されます。 SKUを構成するには、SKUの［**Actions**］ボタン（![Actions Button](../../../images/icon-actions.png))をクリックし、［**Edit**］を選択します。 詳しくは［ [SKU Fields Reference](#sku-fields-reference) ］を参照してください。

   ![生成されたSKUを編集します。](./creating-skus-for-product-variants/images/03.png)

1. 完了したら、 [**公開**] をクリックします。

<a name="manually-adding-an-sku-to-a-product" />

## 商品にSKUを手動で追加する

以下の手順に従って、個々のオプション値の商品SKUを手動で作成します：

   ```{important}
      SKUコントリビューターが有効になっている間は、手動で作成された各SKUをオプション値にマップする必要があります。
   ```

1. ［**グローバルメニュー**］（![Global Menu](../../../images/icon-applications-menu.png)）を開き、 ［**Commerce**］タブをクリックして、 ［**商品**］に移動します。

1. SKUコントリビューターが有効になっているオプションが1つ以上ある商品をクリックして、［**SKUs**］タブに移動します。

1. ［**追加**］ボタン（![Add Button](../../../images/icon-add.png)）をクリックして、 [**SKUを追加**] を選択します。

   ![「SKUを追加」を選択します。](./creating-skus-for-product-variants/images/04.png)

1. SKUのフィールドを設定します。 詳しくは［ [SKU Fields Reference](#sku-fields-reference) を参照してください。

   ![SKUの設定を行います。](./creating-skus-for-product-variants/images/05.png)

1. 完了したら、 [**公開**] をクリックします。

<a name="adding-sku-inventory" />

## SKU在庫の追加

商品の「バックオーダーを許可」が有効になっている場合、基本価格が設定された公開済みのすべてのSKUは、商品の詳細ウィジェットから顧客がすぐに購入できるようになります。 ただし、「バックオーダーを許可」が無効になっている場合は、最初にSKUに在庫を追加して、購入できるようにする必要があります。 詳しくは［ [在庫管理の概要](../../managing-inventory/introduction-to-managing-inventory.md) ］ を参照してください。

![商品のSKUごとに在庫を追加します。](./creating-skus-for-product-variants/images/06.png)

```{note}
   すべてのSKUの在庫が0で、「バックオーダーを許可」が無効になっている場合、商品詳細ウィジェットに商品のバリアントが表示されません。 ただし、1つのSKUのみが0になった場合は、すべてのバリアントが表示されます。 

   「バックオーダーを許可」が有効になっている場合、在庫に関係なく、すべてのオプションが商品詳細ウィジェットに表示されます。 
```

<a name="sku-fields-reference" />

## SKUフィールドリファレンス

### Details

| フィールド               | 説明                                              |
| :--- | :--- |
| SKU（必須）             | 商品バリアントのSKUを入力してください。                           |
| \[オプションフィールド\] (必須) | SKUコントリビューターが有効な場合に、SKUをオプション値にマップ するために使用されます。 |
| 購入可能                | SKUを購入できるかどうかを決定します。                            |
| 商品識別コード (GTIN)      | SKUにGTINを設定します。                                 |
| 製造者パート番号            | SKUにMPNを設定します。                                  |
| UNSPSC              | SKUに国連の標準商品およびサービスコードを設定します。                    |

### 価格

| フィールド | 説明                       |
| :--- | :--- |
| 基本価格  | SKUの基本価格エントリを設定します。      |
| 販売価格  | SKUの基本価格エントリに販売価格を設定します。 |
| コスト   | ネット計算に使用されるSKUコストを設定します。 |

### 配送情報の個別設定

これらのフィールドは、個々のSKUの商品レベルの仕様を上書きし、出荷時に使用されます。

| フィールド | 説明            |
| :--- | :--- |
| 幅     | 商品の幅を上書きします。  |
| 高さ    | 商品の高さを上書きします。 |
| 奥行    | 商品の奥行を上書きします。 |
| 重量    | 商品の重量を上書きします。 |

### スケジュール

| フィールド           | 説明                               |
| :--- | :--- |
| 公開済み            | SKUを閲覧可能にし、購入できるようにします。          |
| Display Date    | SKUがサイト内に表示され、購入できるようになる時期を決めます。 |
| Expiration Date | SKUが購入できなくなる時期を決めます。             |
| Never Expire    | SKUが自動的に公開終了になるようにできるかどうかを決定します。 |

<a name="additional-information" />

## 追加情報

* [商品の概要](./products-overview.md)
* [商品オプションの使用](./using-product-options.md)
* [商品レベルの情報を上書きする](./overriding-product-level-information.md)
