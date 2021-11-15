# 表示ページテンプレートの作成と管理

表示ページテンプレートは、コンテンツのレイアウトとフォーマットを指定し、一意のフレンドリURLで表示します。

![表示ページテンプレートは、作成したフォーマットとデザインを使用して、フレンドリURLでコンテンツを表示します。](./creating-and-managing-display-page-templates/images/01.png)

```{note}
表示ページテンプレートは、Webコンテンツ、ドキュメント、ブログエントリーでのみサポートされています。
```

## 表示ページテンプレートの作成

1.  プロダクトメニューを開き、*[デザイン]* → *[ページテンプレート]* をクリックします。

2.  *[表示ページテンプレート]* タブをクリックします。

    ![[ページテンプレート] に移動し、[表示ページテンプレート] タブをクリックします。](./creating-and-managing-display-page-templates/images/02.png)

3.  *追加*（![Add](./../../../images/icon-add.png)）をクリックします。

4.  テンプレートの名前を入力し、必要に応じてコンテンツタイプとサブタイプを選択します。 次に、* [保存]* をクリックします。 これにより、新しい空白の表示ページテンプレートにリダイレクトされます。

    ![新しいテンプレートのコンテンツ タイプとサブタイプを選択します。](./creating-and-managing-display-page-templates/images/03.png)

5.  [コンテンツページ](../../creating-pages/building-and-managing-content-pages/building-content-pages.md)と同じ編集インターフェイスとページ要素を使用して、テンプレートの編集を開始します。

    ![コンテンツ ページの要素を使用してテンプレートを作成します。](./creating-and-managing-display-page-templates/images/04.png)

6.  ページ要素を追加したら、編集可能なフィールドをダブルクリックして、それらをコンテンツストラクチャーフィールド (タイトル、説明など) に[マップ](../../creating-pages/building-and-managing-content-pages/building-content-pages.md#mapping-content)できます。 マップされたフィールドは紫の輪郭で示されます。

    ![ページ要素をストラクチャーフィールドにマップします。](./creating-and-managing-display-page-templates/images/05.png)

    表示ページの SEO および Open Graph 設定を構成するときにこれらのマップされたストラクチャーフィールドを使用する方法については、[Configuring SEO and Open Graph](./configuring-seo-and-open-graph.md) を参照してください。

7.  完了したら、* [Publish]* をクリックして作業内容を保存します。

表示ページテンプレートが作成されたので、[それを使ってコンテンツを公開](./publishing-content-with-display-pages.md)することができます。


<!-- ## Viewing Display Page Template Usage

> Liferay 7.4+

You can manage the different Display Page Templates using the *Actions* menu (![Actions](../../../images/icon-actions.png)). From here, the *View Usages* option provides a list of content that uses a specific Display Page Template.

```{note}
The *View Usages* option does not provide the usage of content assigned to the default Display Page Template.
```

![Managing your Display Page Template using the Actions menu](./creating-and-managing-display-page-templates/images/06.png)

Before you delete a Display Page Template in use by some of your content, you have two ways to unassign the Display Page Template from the content: 

- Assign to Default: Your content is unassigned from the current Display Page Template and assigned to the default Display Page Template for the content type and subtype (if applicable.)
- Unassign: Your content is not assigned to any Display Page Template.

To view your Display Page Template usage and unassign your content,

1. Open the Product Menu and go to *Design* &rarr; *Page Templates*.
1. Click the *Display Page Templates* tab.
1. Click the Display Page Template's *Actions* menu (![Actions](../../../images/icon-actions.png)) and select *View Usages*.
1. From the list of content using the Display Page Template, select one or more elements.
1. Click the *Actions* menu (![Actions](../../../images/icon-actions.png)) in the top-right corner and select *Assign to Default* or *Unassigned*.
1. Click *OK*.

If you assign your content to a new Display Page Template, review that the content displays as expected. To preview and publish your content, see [Publishing Content with Display Pages](./publishing-content-with-display-pages.md). -->

## 追加情報

  - [Publishing Content with Display Pages](./publishing-content-with-display-pages.md)
  - [Configuring SEO and Open Graph](./configuring-seo-and-open-graph.md)
  - [Content Page Overview](./../../creating-pages/building-and-managing-content-pages/content-pages-overview.md)
  - [Building Content Pages](../../creating-pages/building-and-managing-content-pages/building-content-pages.md)
