# Wikiのスコーピング

[Wikiノード](./creating-a-node.md)を使用してコンテンツを整理する以外に、Wikiのスコープを [グローバル、サイト、またはページのスコープ](https://help.liferay.com/hc/articles/360028819992-Widget-Scope) に設定できます。

<a name="global" />

## グローバル

グローバルサイトのスコープレベルで作成されたWikiページは、他のすべてのサイトで利用できます。 ただし、コンテンツは自動的に公開されません。 管理者は、 ［**Wiki**］ ウィジェットのスコープを ［**Global**］ に設定する必要があります。

### グローバルスコープのWikiページの作成

1. ［**Wiki**］ ウィジェットがデプロイされているサイトページに移動します。
1. ［**Wiki**］ ウィジェットの名前の上にマウスを置き、 **オプション**（![Icon Options](../../images/icon-widget-options.png)）、 ［**Configuration**］ の順にクリックします。

    ![Wikiオプション](./scoping-your-wikis/images/10.png)

1. ［**Scope**］ をクリックします。
1. ［**Scope**］ ドロップダウンメニューから ［**Global**］ を選択します。

   ![グローバルスコープの選択](./scoping-your-wikis/images/01.png)

1. ［**保存**］ をクリックしてウィンドウを閉じます。

［**Wiki**］ ウィジェットが、 **グローバルスコープ** のコンテンツを表示するように設定されました。

![グローバルスコープの表示](./scoping-your-wikis/images/02.png)

### グローバルスコープの **Wiki** ページの管理

1. **プロダクトメニュー**（![Product Menu](../../images/icon-product-menu.png)）を開き、サイト管理メニューのコンパスアイコン（![Compass](../../images/icon-compass.png)）をクリックします。 ［Select Site］ダイアログが開きます。
1. ［**My Sites**］ タブを選択し、 ［**Global**］ を選択します。

    ![グローバルサイトの選択](./scoping-your-wikis/images/06.png)

1. ［**Content & Data**］ &rarr; ［**Wiki**］ の順にクリックします。

![グローバルスコープのWiki](./scoping-your-wikis/images/07.png)

ここで行った変更はグローバルスコープに適用されます。

<a name="site" />

## サイト

デフォルトでは、 **Wiki** アプリは現在選択されているサイトにスコープ指定されています。 同じインスタンスの別のサイトに作成されている可能性のあるWikiを表示すると、特定のサイトにスコープ指定されているWikiは表示されません。

<a name="page" />

## ページ

ページスコープの掲示板を作成すると、フォーラムメンバーは、同じサイトに配置されているさまざまなページのさまざまなWikiを表示できます。

### ページスコープのWikiの作成

1. スコープ指定するページに移動します（**Second Wiki**）。

    ![スコーピング前の2ページ](./scoping-your-wikis/images/03.png)

1. ウィジェットのタイトルバーにある **オプション** アイコン（![Options](../../images/icon-widget-options.png)）をクリックし、 ［**設定**］ を選択します。
1. ［**Scope**］ タブをクリックします。
1. ページの名前を選択するか、ページのスコープがまだ存在しない場合は ［**YOUR-PAGE］（新規作成**） を選択します。

    ![ページスコープの選択](./scoping-your-wikis/images/04.png)

1. ［**保存**］ をクリックしてダイアログウインドウを閉じます。

2番目のページがページスコープに設定されました。

![新しいページスコープのWikiの表示](./scoping-your-wikis/images/05.png)

### ページスコープのWikiの管理

管理者は、引き続き **サイト管理** メニューを使用して、 **サイト管理メニュー** のアクティブスコープを設定することにより、ページスコープの［Wiki］ウィジェットを管理する必要があることに注意してください。 次の手順を実行します：

1. **プロダクトメニュー**（![Product Menu](../../images/icon-product-menu.png)）を開き、サイトのメニューを展開し、 ［**Content & Data**］ をクリックします。
1. 現在のスコープが［Content & Data］見出しのすぐ下に表示されます。 デフォルトのスコープは現在のサイトです。 これを変更するには、歯車のアイコン（![Gear](../../images/icon-control-menu-gear.png)）をクリックし、目的のスコープを選択します。 これにより、サイト管理メニューが変更され、選択したスコープが反映されます。 たとえば、ページのスコープで作業するには、歯車アイコンからそのページを選択します。 そのページの名前がサイト管理メニューのタイトルになります。

    ![新しいページスコープのWikiの選択](./scoping-your-wikis/images/08.png)

1. ［**Wiki**］ をクリックします。

ここで行う変更は、前のステップで選択したスコープに適用されます。

![新しいページスコープのWikiの選択](./scoping-your-wikis/images/09.png)

<a name="additional-information" />

## 追加情報

* [Widget Scopes](https://help.liferay.com/hc/articles/360028819992-Widget-Scope)
