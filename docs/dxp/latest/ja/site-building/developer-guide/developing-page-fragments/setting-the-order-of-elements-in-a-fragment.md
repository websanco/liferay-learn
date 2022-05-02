# フラグメント内の要素の順序を設定する

> 対応可能：Liferay 7.4以降

[スライダー](../../creating-pages/page-fragments-and-widgets/using-fragments/default-fragments-reference.md) または [タブ](../../creating-pages/page-fragments-and-widgets/using-fragments/default-fragments-reference.md) フラグメントは、通常、情報の異なる領域を表示するために使用されるものです。 スライダーは、この情報をスライドのカルーセルのように表示します。 タブは複数あり、それぞれ異なる情報が表示されます。

ページまたはテンプレートでスライダーまたはタブフラグメントを構成する場合、それらのサブ要素はサイドバーメニューの **選択** パネル（![Selection](../../../images/icon-pages-tree.png)）に一覧表示されます。 Liferay DXP 7.4以前では、これらの要素は順番に表示されるものではありませんでした。 そのため、特に多くの要素を持つフラグメントでは、スライダーやタブの要素がどのようにグループ化されているかを識別することが困難でした。

![Liferay DXP 7.4 では、フラグメント内の要素の順番が順番に表示されます。](./setting-the-order-of-elements-in-a-fragment/images/01.png)

Liferay DXP 7.4から、スライダーとタブフラグメントはその要素を順番に表示するようになりました。 さらに、Liferay DXP 7.4 には新しい `data-lfr-priority` HTML 属性があり、フラグメント内の要素の順序を設定することができます。 `data-lfr-priority` の値が小さい要素は、HTMLコード内の順序とは無関係に、セレクションリスト内で優先されます。

## スライダーフラグメントの要素の順序をカスタマイズする

1. ［**サイト管理**］ &rarr; ［**デザイン**］ &rarr; ［**フラグメント**］ へ移動します。

1. ［**フラグメントセット**］ で、カスタマイズしたいフラグメントのあるセットをクリックします。

1. スライダーまたはタブフラグメントの [**アクション**](![Action](./../../../images/icon-actions.png)) ボタンをクリックして [**編集**] を選択して [［フラグメントエディター］](./using-the-fragments-editor.md)を開いてください。

    ![フラグメントエディタを開くには、インポートしたフラグメントを編集します。](./setting-the-order-of-elements-in-a-fragment/images/06.png)

1. HTMLコードエリアでは、スライダーフラグメントのコードを編集することができます。 `data-lfr-priority` 属性を編集可能領域またはドロップゾーン領域に追加します。 この例では、この順序を使用するようにカルーセル要素を設定します。

   1. `First Slide Title`, with `data-lfr-priority="1"`
   1. `First Slide Subtitle`, with `data-lfr-priority="2"`
   1. `First Responsive Image`, with `data-lfr-priority="3"`

    ```html
        <div class="carousel-inner text-break" id="${fragmentEntryLinkNamespace}-carouselInner" role="group">
            <div class="carousel-item active">
                <img
                    alt="First Responsive Image"
                    class="w-100"
                    data-lfr-editable-id="01-01-image"
                    data-lfr-editable-type="image"
                    data-lfr-priority="3"
                    src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAABAAAAAJCAYAAAA7KqwyAAAACXBIWXMAAAsTAAALEwEAmpwYAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAkSURBVHgB7cxBEQAACAIwtH8Pzw52kxD8OBZgNXsPQUOUwCIgAz0DHTyygaAAAAAASUVORK5CYII="
                />

                <div class="carousel-caption d-none d-md-block">
                    <h2
                        data-lfr-editable-id="01-02-title"
                        data-lfr-editable-type="rich-text"
                        data-lfr-priority="1"
                    >
                        First Slide Title
                    </h2>

                    <p
                        data-lfr-editable-id="01-03-subtitle"
                        data-lfr-editable-type="rich-text"
                        data-lfr-priority="2"
                    >
                        First Slide Subtitle
                    </p>
                </div>
            </div>
    ```

    コンテントページエディタで ［**Selection**］(![Selection](../../../images/icon-pages-tree.png) ボタンをクリックすると、このような結果になります。

    ![スライダーフラグメントの要素は、HTMLコードで定義した順序で使用されます。](./setting-the-order-of-elements-in-a-fragment/images/07.png)

    ```{note}
    JavaScript、CSS、設定など、フラグメントの他の部分のコードを編集する必要はありません。
    ```

## コンテントページエディタで要素の新しい並び順を確認する

1. ［**サイト管理**］ &rarr; ［**サイトビルダー**］ &rarr; ［**Pages**］ に移動します。

1. スライダーフラグメントのサンプルを追加したいコンテントページを編集します（または [新規ページの追加](../../creating-pages/adding-pages/adding-a-page-to-a-site.md)）。

1. コンテントページのサイドバーから、 ［**フラグメントとウィジェット**］(![Fragments and Widgets](../../../images/icon-add-widget.png))をクリックします。

1. フラグメントの欄で、表示したいスライダーやタブのフラグメントを探します。

1. フラグメントを編集エリアにドラッグ＆ドロップします。

1. コンテンツページサイドバーで、 **Selection** をクリックします(![選択](../../../images/icon-pages-tree.png)) ボタンを押すと、フラグメント に含まれる要素のリストが表示されます。

    ```{note}
    Liferay DXP 7.4 より前のバージョンからインポートしたスライダーやタブフラグメントは、コンテントページエディタのサイドバーに順番に表示されますが、 `data-lfr-priority` HTML属性が含まれません。 この属性を含めるには、HTML フラグメントのコードを編集する必要があります。
    ```

## 関連情報

- [フラグメントツールキットの使用](./using-the-fragments-toolkit.md)
- [フラグメントエディターの使用](./using-the-fragments-editor.md)
- [フラグメントの開発](./developing-fragments-intro.md)
