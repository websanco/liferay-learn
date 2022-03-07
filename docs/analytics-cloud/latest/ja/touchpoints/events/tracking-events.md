# イベントの追跡

Liferay DXPのサイトページにJavaScriptのコードを追加することで、分析のためにイベントを追跡することができます。 追加したコードは、Analytic CloudのAPIと連動して、ユーザーがWebサイトで何をしているかを把握します。 これらのイベントは、 [イベント分析](./events-analysis.md) ツールを使用して分析できます。

そのためには、JavaScriptのコードで `Analytics.track` を使用し、イベント名と属性を定義します。

| フィールド  | 説明                                                                             |
| ------ | ------------------------------------------------------------------------------ |
| `イベント` | イベントの名前。 イベント分析を行う際に理解しやすいように、人間が理解できる名前にすることをお勧めします（例：「Add to Cart Click」など）。 |
| `属性`   | 追跡したいイベントのさまざまな属性（例：価格、商品名、数量など）。                                              |

例えば、ユーザーがどのようにショッピングカートにアイテムを追加しているかを追跡することができます。 これらのイベントを追跡するには、Webサイトの製品ページにJavaScriptコードを追加します。 イベントで追跡する価格、製品名、数量などの属性を選択します。 以下は簡単な例です：

```javascript

// Add some event listener logic for when a user clicks the Add to Cart button

Analytics.track("Add to Cart Click",{
    'price': productPrice(),
    'productName': productName(),
    'quantity': productQuantity(),
});
```

属性に設定できるデータタイプキャストは以下の通りです：ブール値、日付、継続期間、数値、文字列。 Analytics Cloudは、その属性に対して収集された最初のデータに基づいて、自動的にデータタイプを検出してキャストしようとします。 必要に応じてAnalytics Cloudの設定ページにある [イベント属性の定義](../../workspace-data/definitions/definitions-for-event-attributes.md) でタイプキャストを変更してください。

| 属性タイプ      | 説明                                            |
| ---------- | --------------------------------------------- |
| `ブール値`     | `true`または`false`、大文字でも小文字でもOK                 |
| `Date`     | ISO-8061 format ((yyyy-MM-dd'T'HH:mm:ss.SSSX) |
| `Duration` | ミリ秒単位、非負                                      |
| `数値`       | 千の位のセパレーターがなくても、10の位のセパレーターがあればOK             |
| `String`   | 他のフォーマットが一致しない場合、Analytics Cloudは文字列を想定する     |

なお、指定した属性に加えて、すべてのイベントに自動的に関連付けられる [グローバル属性](../../workspace-data/definitions/definitions-for-event-attributes.md) があります。

<a name="adding-javascript-code" />

## JavaScriptコードの追加

JavaScriptコードを追加して、イベントの追跡を有効にする方法がいくつかあります。 個々のページの変更、ページフラグメントの追加、またはカスタム実装の使用。

<a name="modify-an-individual-page" />

### 個々のページを変更する

JavaScriptコードを追加する最も簡単な方法は、個々のページを変更することです。 この時、ページにJavaScriptのコードを追加する機能を持つウィジェットページを使用するようにしてください。

1. ［プロダクトメニュー］（![Product Menu icon.](../../images/icon-product-menu.png)）をクリックして、 ［**サイトビルダー**］ &rarr; ［**ページ**］ に移動します。

1. 個々のページの ［**アクション**］ アイコン（![Actions icon.](../../images/icon-actions.png)）をクリックして、 ［**構成**］ をクリックします。

1. JavaScriptコードをテキストボックスエリアの **JavaScript** に貼り付けます。 これは［詳細設定］タブの下にあります。

    ![JavaScriptコードをテキストボックスに貼り付けます。](./tracking-events/images/01.png)

1. ［**保存**］ をクリックすると、ページでイベントを追跡する準備が整います。

詳しくは、 [個々のページの構成](https://learn.liferay.com/dxp/latest/ja/site-building/creating-pages/page-settings/configuring-individual-pages.html) を参照してください。

<a name="add-a-page-fragment" />

### ページフラグメントの追加

Javascriptのコードを追加するもう一つの方法は、コンテントページに追加できるページフラグメントを作成することです。 ウェブサイトのどのページにも素早くフラグメントを追加することができるため、より高いスケーラビリティを得ることができます。

1. ［プロダクトメニュー］（![Product Menu icon.](../../images/icon-product-menu.png)）をクリックして、 ［**デザイン**］ &rarr; ［**フラグメント**］ に移動します。

1. コレクションの下にある ［**プラス**］ アイコン（![Plus icon.](../../images/icon-plus.png)）をクリックして、新しいフラグメントコレクションを作成します。

1. ［**追加**］ アイコン (![Add icon.](../../images/icon-add.png)) をクリックして、新しいフラグメントを作成します。

1. ［**コード**］ タブをクリックし、JavaScriptフィールドにJavaScriptコードを貼り付けます。

    ![JavaScriptコードをJavaScriptフィールドに貼り付けます。](./tracking-events/images/02.png)

    なお、属性値をフェッチするコードや、 `Analytics.track` コードをトリガーするロジックを追加する必要がある場合もあります。

1. ［**保存**］ をクリックすると、ページフラグメントが使えるようになります。

1. フラグメントを追加したいコンテントページに移動します。 ［**編集**］ アイコン（![Edit icon.](../../images/icon-edit.png)）をクリックして、ページエディタを開きます。 先ほど作成したフラグメントを見つけ、自分のページにドラッグします。 ［**Publish**］ をクリックします。

詳細については、 [フラグメントの開発](https://learn.liferay.com/dxp/latest/ja/site-building/developer-guide/developing-page-fragments/developing-fragments-intro.html) を参照してください。

<a name="use-a-custom-implementation" />

### カスタムインプリメンテーションの使用

最後に、JavaScriptコードをWebサイトに実装するために、独自の開発ツールやアプローチを使用することができます。
