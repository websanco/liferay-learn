# オブジェクトエントリの表示

Liferay Objectでは、既存のDXPフレームワークを使用して、オブジェクトエントリをサイト内に動的に表示できます。 これには、複数のエントリーのコレクション表示や、個々のエントリーの表示ページが含まれます。

オブジェクトが公開されると、Liferayはコレクションプロバイダーを作成します。このコレクションプロバイダーを [コレクション表示フラグメント](../../site-building/displaying-content/additional-content-display-options/displaying-collections.md#adding-a-collection-display-fragment-to-a-page) で使用することで、複数のオブジェクトエントリーを表示できます。 また、コレクションをスタイル、フィルター、ページ分割するようフラグメントを設定できます。 また、公開されたオブジェクトはInfo Frameworkに統合されているので、 [表示ページテンプレート](../../site-building/displaying-content/using-display-page-templates/about-display-page-templates-and-display-pages.md)のコンテンツタイプとして選択することができます。 これを選択すると、オブジェクトがテンプレートのマッピングソースとして設定されるため、フラグメントフィールドをオブジェクトフィールドにマッピングしやすくなり、個々のオブジェクトエントリーの柔軟なページ表示を作成することができます。

これらの統合により、パーソナライズされたユーザーエクスペリエンスを提供しながら、ユーザーはLiferayのWYSIWYGページ構築機能を活用できます。

## オブジェクトのコレクションプロバイダーの使用

オブジェクトを公開した後、以下の手順でコレクション表示フラグメントを使用してオブジェクトのエントリーを表示します。

1. 新しい [コンテンツページ](../../site-building/creating-pages/building_and_managing_content_pages.html) または [表示ページテンプレート](../../site-building/displaying-content/using-display-page-templates/creating-and-managing-display-page-templates.md)を作成します。 または、既存のページを*編集*（![編集ボタン](../../images/icon-edit-pencil.png)）することから開始します。

   ```{note}
   また、コレクションプロバイダーを使ってコレクションページを作成することもできます。 詳しくは、[Collections and Collection Pages](../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md)をご覧ください。
   ```

1. *コレクション表示*フラグメントを*［フラグメントとウィジェット］*（![Fragments and Widgets](../../images/icon-plus.png)）サイドバーからページまたはテンプレートにドラッグアンドドロップします。

   ![コレクション表示フラグメントをページまたはテンプレートにドラッグアンドドロップします。](./displaying-object-entries/images/01.png)

1. *［コレクション表示フラグメント］*を選択して設定オプションにアクセスし、［一般］タブの *［コレクションを選択］* をクリックします。

   ![フラグメントを選択し、［コレクションを選択］をクリックします。](../objects/displaying-object-entries/images/02.png)

1. モーダルウィンドウで、 *［Collection Providers ］*タブをクリックし、*オブジェクトのプロバイダー*を選択します。

   ![［Collection Providers］タブで、オブジェクトのプロバイダーを選択します。](./displaying-object-entries/images/03.png)

1. (オプション) どのオブジェクトエントリーを表示するかフィルタリングします。 オブジェクト内の任意のピックリストまたはブール値フィールドでフィルタリングすることができます。

   これを行うには、 *［Collection Options］*ボタン(![Collection Options Button](../../images/icon-actions.png))をクリックし、 *［ Filter Collection］*を選択します。

   ![［Collection Options］ボタンをクリックして、［Filter Collection］を選択します。](./displaying-object-entries/images/04.png)

   次に、 目的の*フィルター*を選択し、 *［適用］*をクリックします。

   ![適用するフィルターを選択します。](./displaying-object-entries/images/05.png)

目的のプロバイダーを選択した後、追加のフラグメントオプションを使用して、オブジェクトエントリーの表示方法を決定します。これには、*［リストのスタイル］*および*［ページネーション］*が含まれます。

![フラグメントオプションを使用して、オブジェクトエントリーの表示方法を決定します。](./displaying-object-entries/images/06.png)

## オブジェクトの表示ページテンプレートの作成

1. *サイトメニュー*（![Site Menu](../../images/icon-menu.png)）を開きます。 *［デザイン］* &rarr; *［ページテンプレート］*に移動し、*［表示ページテンプレート］*タブをクリックします。

1. *追加*ボタン（![Add Button](../../images/icon-add.png)）をクリックし、*空白*のテンプレートを選択します。

   これにより、モーダルウィンドウが開きます。

1. *名前*を入力し、コンテンツの種類に目的の*オブジェクト*を選択します。

   ![名前を入力し、コンテンツの種類に目的のオブジェクトを選択します](./displaying-object-entries/images/07.png)

1. *［保存］*をクリックします。

保存されると、選択されたオブジェクトがテンプレートのマッピングソースとして設定され、テンプレートの編集画面にリダイレクトされます。 ここでは、オブジェクトフィールドに対応するフラグメントを使って、個々のオブジェクトエントリーのページ表示を柔軟にデザインすることができます。 詳しくは、 [Using Display Page Templates](../../site-building/displaying-content/using-display-page-templates/about-display-page-templates-and-display-pages.md) をご覧ください。

![フラグメント要素をオブジェクトフィールドにマップして、個々のエントリーの表示ページに動的に入力します。](./displaying-object-entries/images/08.png)

## 追加情報

* [Creating Objects](./creating-and-managing-objects/creating-objects.md)
* [コレクションとコレクションページについて](../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md)
* [About Display Page Templates and Display Pages](../../site-building/displaying-content/using-display-page-templates/about-display-page-templates-and-display-pages.md)
