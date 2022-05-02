# サイトにページを追加する

以下の手順では、サイトに新しいページを追加し、一般的なオプションを設定する方法について説明します。 残りのページ設定を設定する方法については、 [ページ単位での設定](../page-settings/configuring-individual-pages.md) を参照するか、または [ページセット](../understanding-pages/understanding-pages.md#page-sets) のオプションを設定する方法について [ページセットの設定](../page-settings/configuring-page-sets.md) を参照してください。

<a name="adding-a-new-page" />

## 新しいページを追加する

ページの管理画面やページツリー（Liferay DXP 7.3以降のバージョンに限る）から、新しいページを追加することができます。

<a name="adding-a-new-page-from-pages-administration" />

### ページ管理から新しいページを追加する

1. **プロダクトメニュー**(![Product Menu](../../../images/icon-menu.png)) をクリックして、 ［**Site Builder**］ &rarr; ［**Pages**］ に移動します。

1. **追加** ボタン (![Add](../../../images/icon-add.png)) をクリックし、 ［**ページの追加**］ をクリックします。

    ![［Pages］画面では、サイトページ全体を編集できます。](./adding-a-page-to-a-site/images/02.png)

    ```{note}
    7.3から、デフォルトのホームページはウィジェットページではなくコンテントページになりました。
    ```

1. ［ Page Template Sets］下の、ページに使用する ［Basic Templates］または ［Global Templates］を選択します。 空の [コンテントページ](../understanding-pages/understanding-pages.md#page-types) から始めるには、 **空白の**[マスターページテンプレート](../defining-headers-and-footers/master-page-templates.md)をクリックしてください。 または、その他の見出しの下にあるページテンプレートのいずれかをクリックします。

   ![ページの基本テンプレートまたはグローバルテンプレートを選択します。](./adding-a-page-to-a-site/images/03.png)

1. 追加ページダイアログで、名前を入力し、 ［**追加**］ をクリックします。

1. 新規の［ウィジェットページ］の場合、残りの設定を行い、 ［**保存**］ をクリックします。

```{tip}
デフォルトでは、新しいページはサイトのナビゲーションメニューに含まれます。 このナビゲーションメニューを設定するには、[サイトナビゲーションの管理](../../site-navigation/managing-site-navigation.md)を参照してください。
```

<a name="adding-a-new-page-from-the-page-tree-menu" />

### ページツリーメニューから新しいページを追加する

> 対応可能：Liferay DXP/Portal 7.3以降

1. **プロダクトメニュー**(![Product Menu](../../../images/icon-menu.png)) をクリックし、画面左側の **ページツリー**(![Page Tree](../../../images/icon-page-tree.png))をクリックします。

1. **追加**(![Add](../../../images/icon-add-app.png)) ボタンをクリックすると、新しいページが追加されます。

   ![ページツリーメニューを使用して新規ページを追加する。](adding-a-page-to-a-site/images/04.png)

1. [上記](#adding-a-new-page) のページ作成の標準的な手順に従ってください。

```{tip}
下書き状態のページには、ページツリーで名前の横にアスタリスク（*）が表示されます。
```

<a name="adding-a-child-page" />

## サブページを追加する

サブページは、ページ管理またはページツリー（Liferay DXP 7.3以降のバージョンに限る）から追加することができます。

<a name="adding-a-child-page-from-pages-administration" />

### ページ管理からサブページを追加する

1. **プロダクトメニュー**(![Product Menu](../../../images/icon-menu.png)) をクリックして < ［**Site Builder**］ &rarr; ［**Pages**］ に移動します。

1. 既存のページの横にある **追加** ボタン（![Add](../../../images/icon-duplicate.png)）をクリックします。

   ![終了するページの横にある［Add］ボタンをクリックして、新しい子ページを作成します。](./adding-a-page-to-a-site/images/05.png)

1. [上記](#adding-a-new-page) のページ作成の標準的な手順に従ってください。

```{tip}
ページの横にあるアクションメニュー (![Actions Menu](../../../images/icon-actions.png)) を使用すると、コンテントページのドラフトをプレビュー（Liferay DXP 7.2以降）したり、下書きを承認（Liferay DXP 7.2 のみ）することができます。 これらのオプションは、下書き状態のページと、ページの編集権限を持つユーザーで利用できます。
```

<a name="adding-a-child-page-from-the-pages-tree" />

### ページツリーからサブページを追加する

> 対応可能：Liferay DXP/Portal 7.3以降

1. **プロダクトメニュー**(![Product Menu](../../../images/icon-menu.png)) をクリックし、 画面左側の **ページツリー**(![Page Tree](../../../images/icon-page-tree.png))をクリックします。

1. 既存のページの横にあるアクションメニュー（![Actions icon](../../../images/icon-actions.png)）をクリックし、 ［**Add Child Page**］ を選択します。

    ![ページツリーメニューを使用して子ページを追加する。](adding-a-page-to-a-site/images/01.png)

1. [上記](#adding-a-new-page) のページ作成の標準的な手順に従ってください。

<a name="related-information" />

## 関連情報

- [ページについて](../understanding-pages/understanding-pages.md)
- [コンテントページの使用](../using-content-pages.md)
- [サイトナビゲーションの管理](../../site-navigation/managing-site-navigation.md)
