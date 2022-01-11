# 定額配送方法の使用

購入者は、任意の商品を配送するための定額配送オプションを選択できます。 複数の定額オプションを作成することにより、顧客に費用、速さ、またはセキュリティの選択肢を提供できます。

定額配送では、カタログ内のすべての商品に同じ費用がかかります。 ただし、商品の[［設定］](../../managing-a-catalog/managing-inventory/product-inventory-configuration-reference.md)タブの ［_配送追加費用_］ フィールドから個々の商品について例外を作成できます。

デフォルトでは、定額配送方法が有効になっています。

新しい定額配送オプションを追加するには：

1. ［_コントロールパネル_］ → ［_コマース_］ → ［_チャネル_］ に移動します。

    ![コントロールパネルのチャネルに移動します。](./using-the-flat-rate-shipping-method/images/02.png)

1. 目的のチャネル（たとえば、Sahara.com）をクリックします。 （Miniumなどのアクセラレータを使用してサイトを作成した場合は、対応するチャネルがデフォルトですでに作成されています。）
1. ［_発送方法 _］ までスクロールします。

    ![配送方法はチャネルで設定されます。](./using-the-flat-rate-shipping-method/images/03.png)

1. ［_編集_］ をクリックします。
1. _有効_ トグルを _はい_ に切り替えます。
1. ［_配送オプション_］ タブをクリックします。
1. 追加（![Add Icon](../../images/icon-add.png)）ボタンをクリックして、新しい配送オプションを追加します。
1. 次のように入力します：
    * **Name**：7日発送
    * **Amount**：3.95
    * **Priority**：0.0

    ![新しい配送方法オプションを追加します。](./using-the-flat-rate-shipping-method/images/04.png)

1. ［_保存_］ をクリックします。
1. ウィンドウを閉じます。

![新しい配送方法オプションが作成されていることを確認します。](./using-the-flat-rate-shipping-method/images/05.png)

新しい配送方法オプションが追加されました。

![新しい配送方法オプションが利用可能であることを確認します。](./using-the-flat-rate-shipping-method/images/06.png)

## Commerce 2.0以前

新しい定額配送オプションを追加するには：

1. ［_サイト管理_］ → ［_コマース_］ → ［_設定_］ に移動します。
1. ［_配送方法_］ タブをクリックします。
1. ［_一律料金_］ をクリックします。
1. ［_配送オプション_］ タブをクリックします。 （Miniumアクセラレータを使用している場合、 ［_標準配送_］ および ［_速達_］ がデフォルトで作成されます。）
1. 追加（![Add Icon](../../images/icon-add.png)）ボタンをクリックして、新しい配送オプションを追加します。
1. 次のように入力します：
    * **Name**：7日発送
    * **Amount**：3.95
    * **Priority**：0.0
1. ［_保存_］ をクリックします。

新しい配送オプションが追加され、精算プロセスで利用可能になりました。

## 追加情報

* [可変レート配送方法を使う](./using-the-variable-rate-shipping-method.md)
* [FedExを運送業者の方法として使用する](./using-the-fedex-shipping-method.md)
* [配送方法の制限の適用](./applying-shipping-method-restrictions.md)
* [新しい配送エンジンの実装](../../developer-guide/implementing-a-new-shipping-engine.md)
