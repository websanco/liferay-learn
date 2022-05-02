# ページコメントの使用

コンテントページを作成する際、あなたとあなたのチームはLiferayのページコメント機能を使用して共同作業を行うことができます。 コメントは、Liferay DXP 7.3以降では標準で有効になっていますが、Liferay DXP 7.2ではデフォルトで無効になっています。 詳しくは、 [Enabling Comments](#enabling-comments) を参照してください。

![ページコメントを使用して共同作業します。](./using-page-comments/images/01.png)

<a name="adding-page-comments" />

## ページコメントの追加

次の手順に従って、ページコメントを追加します。

1. ページ要素（フラグメントまたはウィジェットなど）を含むコンテントページに移動し、 ［**編集**］ ボタン（![Edit Button](../../../images/icon-edit-pencil.png)）をクリックして、ページの編集を開始します。

1. サイドバーメニューの **コメント** パネル（![Comments Icon](../../../images/icon-comments-w.png)）を開き、目的のフラグメントまたはウィジェットを選択します。

   または、目的のページ要素を選択して、そのコメントアイコンをクリックすることもできます。 クリックすると、コメントパネルが開きます。

   ![ページ要素のコメントアイコンをクリックします。](./using-page-comments/images/02.png)

   ```{note}
   [フラグメントのサブ要素](../page-fragments-and-widgets/using-fragments/configuring-fragments/fragment-sub-elements-reference.md)、コンテナ、またはグリッドにコメントを追加することはできません。 
   ```

1. サイドパネルにコメントを入力し、 ［**コメント**］ をクリックします。

コメントを追加したら、 **アクション** ボタン（![Actions Button](../../../images/icon-actions.png)）を使用してコメントを **編集** または **削除** できます。 ユーザーはそれに返信を追加することもできます。

![コメントを編集、削除、または返信します。](./using-page-comments/images/03.png)

```{note}
コメントを削除すると、コメントへのすべての返信が削除されます。
```

<a name="reviewing-page-comments" />

## ページコメントの確認

コメントがページフラグメントまたはウィジェットに追加されると、コメントサイドパネルでそれらを確認できます。

![コメントサイドパネルでページのコメントを確認します。](./using-page-comments/images/04.png)

共同作業する場合、 ［**解決する**］ ボタン（![Resolve Button](../../../images/icon-resolve.png)）をクリックして、コメントを解決済みとしてマークできます。 デフォルトでは、解決されたコメントとその返信は非表示になります。 ただし、 ［**解決済みコメントの表示**］ をオンにすると、解決済みのコメントを表示できます。

![コメントを解決します。](./using-page-comments/images/05.png)

解決されたコメントを表示しているときに、緑色の ［**解決する**］ ボタンをクリックしてコメントを再度開くことができます。

![解決されたコメントを表示して再度開きます。](using-page-comments/images/06.png)

<a name="enabling-comments" />

## コメントの有効化

コンテントページのコメントを有効にするには、次の手順に従います。

1. コントロールパネルに移動して、 ［**設定**］ &rarr; ［**システム設定**］ &rarr; ［**コンテンツとデータ**］ &rarr; ［**Pages**］ に移動します。

1. 仮想インスタンススコープの下の ［**Content Page Editor**］ を選択します。 ［**Comments Enabled**］ チェックボックスをオンにして、 ［**Save**］ ボタンをクリックします。

   ![コンテントページエディタに移動し、［コメントを有効にする］チェックボックスをオンにします。](./using-page-comments/images/07.png)

   これで、コンテントページのコメントを使用できるようになります。

```{note}
これにより、すべてのインスタンスでコンテントページのコメントが有効になります。 これをインスタンスごとに制御するには、*Instance Settings*（*システム設定*ではなく） で同じ設定に移動します。
```

<a name="additional-information" />

## 追加情報

* [コンテントページの使用](../using-content-pages.md)
* [コンテンツページへの要素の追加](./adding-elements-to-content-pages.md)
* [コンテントページエディタUIリファレンス](./content-page-editor-ui-reference.md)
