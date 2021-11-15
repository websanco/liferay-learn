# スタイルブックを使用してサイトの外観を標準化する

スタイルブックは、ページ全体で一貫したエクスペリエンスを提供するためにサイトに適用される一連の視覚的なルールです。 これらのルールによって、要素（ウィジェットなど）間の間隔、色、フォントなど、サイトのさまざまな視覚的設定が決定まります。 これらの各設定は、スタイルブックを使用するすべてのページに等しく適用されます。

サイトの公開ページに割り当てるテーマによって、サイトのスタイルブックで使用できる設定のカテゴリが決まります。 開発者は、スタイルブックを構成するための新しいオプションを使用してカテゴリをカスタマイズできます。 詳細については、[Style Book Token Definitions](./developer-guide/style-book-token-definitions.md)を参照してください。

## ページのスタイルブックの選択

ページに特定のスタイルブックを選択しない場合、ページは[マスターページ](../creating-pages/defining-headers-and-footers/master-page-templates.md)のスタイルブックを使用します。 どちらも設定されていない場合、ページはサイトのデフォルトのスタイルブックを使用します。

次の手順に従って、ページのスタイルブックを設定します。

1.  ページ上部にある編集アイコン（![Edit icon](../../images/icon-edit.png)）をクリックします。

2.  画面右側のアイコン（![Page Design Options](../../images/icon-format.png)）をクリックし、*[ページデザインオプション]* メニューを開きます。

3.  *[スタイルブック]* タブをクリックして開きます。

    ![[スタイルブック]タブを開き、リストから目的のスタイルブックを選択します。](./style-books/using-a-style-book-to-standardize-site-appearance/images/01.png)

4.  リストから目的のスタイルブックを選択します。

5.  *[公開]* をクリックします。

このページは、新しいスタイルブックを使用して公開されます。

## 新しいスタイルブックの作成

1.  *[サイトメニュー]* → *[デザイン]* → *[スタイルブック]* に移動します。

2.  ページ上部にある追加アイコン（![Add icon](../../images/icon-add.png)）をクリックします。

3.  スタイルブックの名前を入力し、*[保存]* をクリックします。

4.  画面右側のメニューにあるドロップダウンボックスのカテゴリからオプションを変更します。

    ![利用可能なカテゴリのいずれかを選択し、スタイルブックをカスタマイズします。](./style-books/using-a-style-book-to-standardize-site-appearance/images/02.png)

    これらのオプションは、サイトで使用されているテーマに基づいています。 デフォルトの標準テーマで使用可能なカテゴリの詳細については、[デフォルトの標準テーマのカテゴリ](#default-classic-theme-categories)を参照してください。

5.  *[公開]* をクリックします。

編集時に、スタイルブックを任意のページのオプションとして選択できるようになりました。

このスタイルブックをサイトの新しいデフォルトにするには、新しく作成したスタイルブックの横にあるアクションメニュー（![Actions icon](../../images/icon-actions.png)）を開き、*[Mark as Default]* をクリックします。

## デフォルトの標準テーマカテゴリ

スタイルブックを編集しているとき、ドロップダウンボックスから利用できるカテゴリには、ページのスタイルを設定するためのさまざまなオプションが表示されます。

```{tip}
You can place the `Style Guide Sample` widget on your pages to help test out the various options in your Style Book categories. This sample widget contains many different UI elements that leverage most of the features in the Classic theme, so you can use it to test your page styles. 
```

ここにリストされているカテゴリは、標準テーマのオプションとして利用できます。

### 色体系

テーマで定義されているフォントと視覚要素に使用される色は、[色体系]カテゴリでカスタマイズできます。 通常、これは、ページの本文、背景、およびテキストの色を変更できることを意味します。

![[色体系]カテゴリのオプションを使用して、ディスプレイまたはUI要素に使用される色を変更します。](./style-books/using-a-style-book-to-standardize-site-appearance/images/03.png)

```{note}
Changing the colors for alert-related elements (such as *Success* or *Warning*) may not affect the color of incoming alerts on your Site because each type of alert may use one of many colors. In order to fully customize the color of alerts, you must define a variety of customizable colors and use them in your own theme's categories.
```

### 間隔

[Spacing]カテゴリのオプションを使用して、ページの主要な要素間の間隔を調整します。

### 一般

これらのオプションは、サイトのすべてのページに表示される要素の色と間隔を変更します。 たとえば、*[本文の背景]* オプションは、すべてのページの背景色を変更します。

![[一般]カテゴリの[本文の背景]オプションを使用して、サイトページの背景の色をカスタマイズします。](./style-books/using-a-style-book-to-standardize-site-appearance/images/04.png)

### レイアウト

これらのオプションは、ページ上のコンテナの幅を変更します。 たとえば、新しい空白ページのデフォルトコンテナは、特大設定のサイズから設定できます。

### 文字体裁

これらのオプションは、ページの新しいフォントを定義します。 このカテゴリのオプションを使用して、ヘッダのサイズを調整することもできます。

### ボタン

これらのオプションは、デフォルトタイプのボタンのスタイルを変更します。 標準テーマで使用される各ボタンの背景色、前景色、および線の色をカスタマイズできます。

![標準テーマを使用するときに、[Button Primary]で色を変更すると、すぐに使用できるウィジェットの多くの一般的なボタンに作用します。](./style-books/using-a-style-book-to-standardize-site-appearance/images/05.png)

## 追加情報

  - [Style Book Token Definitions](./developer-guide/style-book-token-definitions.md)
  - [Adding a New Token Set for Your Style Book](./developer-guide/adding-a-new-token-set-for-your-style-book.md)
