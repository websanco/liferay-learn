# ユーザーグループサイト

<!-- The main struggle I have with this article is I have no idea what the value proposition is for this functionality - what is the problem or use case this solves for? -->

[ユーザーグループフォーム](./creating-and-managing-user-groups.md) の下部にある2つのフィールドで、サイトテンプレートを選択して2つのページセット（ *マイプロファイル* と *マイダッシュボード*を入力できます。

![ユーザーグループの作成中にユーザーグループサイトを作成できます。](./user-group-sites/images/01.png)

ユーザーグループサイトは、ユーザー *マイプロファイル* （パブリック）と *マイダッシュボード* （プライベート）の個人用サイトに入力する特定のページセットです。

ユーザーは、パブリック（プロフィール）ページと、場合によってはプライベート（ダッシュボード）ページで構成される個人用サイトを持つことができます。 これらは、 [パブリック](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/portal.properties.html#Default%20User%20Public%20Layouts) サイトと [プライベート](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/portal.properties.html#Default%20User%20Private%20Layouts) サイトの両方で有効にする必要があります。

<!-- The first part of this paragraph feels redundant to information presented a couple paragraphs prior.
A *User Group Site* isn't really a Site: it's a set of pages that gets added to either the public (profile) or private (dashboard) Sites of Users. A mixed approach can also be used, where both private and public pages are added for the User Group Site. If Users belong to multiple User Groups, all the pages from those User Group Sites are made part of their personal Sites.
-->

![ユーザーのサイトは、ユーザーの個人メニューで利用できます。](./user-group-sites/images/02.png)

*マイプロファイル* および *マイダッシュボード* セレクターメニューから [サイトテンプレート](../../site-building/building-sites/building-sites-with-site-templates.md) 選択することにより、 [がユーザーグループ](./creating-and-managing-user-groups.md) 作成している間にユーザーグループサイトを作成できます。 手動で、またはサイトテンプレートを使用して、後でユーザーグループサイトを作成することもできます。

## サイトテンプレートからのユーザーグループサイトの作成

[サイトテンプレート](../../site-building/building-sites/building-sites-with-site-templates.md)）からユーザーグループサイトを作成するには、既に存在するユーザーグループに対して、

1.  オープン *コントロールパネル* → *ユーザー* → *ユーザーグループ*。

2.  ユーザーグループの *アクション* （![Actions](../../images/icon-actions.png)）→ *編集* をクリックして、編集フォームを開きます。

3.  *My Profile* メニューからサイトテンプレートを選択して、ユーザーにパブリックページを提供します。

4.  *マイダッシュボード* メニューからサイトテンプレートを選択して、ユーザーにプライベートページを提供します。

5.  *[保存]* をクリックします。

これで、グループのいずれかのユーザーが *マイプロファイル* または *マイダッシュボード* サイトに移動すると、それらのサイトのコンテンツには、選択したサイトテンプレートが反映されます。

ユーザーグループサイトページは、通常のサイトテンプレートページと同様に機能しますが、重要な例外があります。ユーザーグループサイトページは、ユーザーごとにコピーされません。 これらは、ユーザーが個人用サイトに表示する可能性のあるカスタムページとともに動的に表示されます。 このため、ユーザーはユーザーグループから継承されたページを変更できません。 必要に応じて、ユーザーグループ管理者は、通常のサイトと同様に、ページの特定の領域をカスタマイズ可能として定義できます。その後、ユーザーは、ページの指定された領域にウィジェットを追加および構成できます。

これにより、直接変更することなく、ユーザーの個人用サイトを柔軟に構成できます。 ユーザーがユーザーグループに割り当てられると、ユーザーは個人サイトからユーザーグループのサイトページにすぐにアクセスできます。

``` note::
   Site Templates have an option that propagates changes made to the Site Template. If you use a Site Template with this option enabled, the User Group Sites update automatically when that template changes. If you disable this option but enable it again later, the template's pages are copied to the Users' Sites, overwriting any changes they may have made.
```

## ユーザーグループサイトを手動で作成する

ユーザーグループサイトをサイトテンプレートに基づく代わりに、手動で作成できます。

1.  *コントロールパネル*→ *ユーザー*→ *ユーザーグループ*に移動します。

2.  ユーザーグループの *アクション* （![Actions](../../images/icon-actions.png)）→ *ページの管理* をクリックします。 この*ページ*ウィンドウは、[ページを作成](../../site-building/creating-pages/understanding-pages/understanding-pages.md)に使用するものと同じです。

    ![ユーザーグループサイトを手動で追加するためのコントロールは、ページを作成するためのコントロールと同じです。](./user-group-sites/images/03.png)

3.  ユーザーの*マイプロフィール*および/または*マイダッシュボード*サイトに使用する公開および/または非公開ページを作成します。 ここで作成するパブリックページはユーザーの *マイプロファイル* サイトのページになり、プライベートページはユーザーの *マイダッシュボード* サイトのページになります。

コントロールパネルでユーザーグループに戻ると、ユーザーグループの *アクション* ボタン（![Actions](../../images/icon-actions.png)）にあるこれらのリンクを介して、ユーザーグループのパブリックページやプライベートページにアクセスできます。

**プロファイルページに移動：** ユーザーグループの公開 *マイプロファイル* ページを新しいブラウザウィンドウで開きます。

**ダッシュボードページに移動：** ユーザーグループのプライベート *マイダッシュボード* ページを新しいブラウザウィンドウで開きます。

新しいウィンドウで、ページやポートレットを追加し、サイト設定を構成できます。

## 従来のユーザーグループサイトの動作

何百万ものユーザーがいる場合でも、ユーザーグループサイトページの継承によるパフォーマンスへの影響はありません。 ただし、7.0より前のバージョンのLiferayポータルとLiferay DXPでは、各ユーザーの個人用サイトにユーザーグループページをコピーする必要がありました。

Liferay DXP 7.2を使用していて、その動作を維持する必要がある場合は、次の行を `portal-ext.properties` ファイルに追加して有効にします。

    user.groups.copy.layouts.to.user.personal.site=true

このプロパティはLiferay DXP 7.3で削除されました。

このプロパティが `true`設定されている場合、テンプレートページはユーザーの個人用サイトに一度コピーされた後、ユーザーによって変更される可能性があります。 これは、後でテンプレートページに変更が加えられた場合、変更後にユーザーグループに追加されたユーザーにのみ影響することを意味します。 あれば自分の個人サイトに対する管理権限を持つユーザーは、ページとその内容を変更することができます *サイト管理者は、このサイトのテンプレートを使用したページの関連を変更することを許可する* ボックステンプレートにチェックされています。 ユーザーがユーザーグループから削除されると、関連するページがユーザーの個人用サイトから削除されます。 ユーザーがグループから削除され、その後再び追加された場合、グループのテンプレートページがユーザーのサイトに再度コピーされます。 ユーザーグループのサイトがサイトテンプレートに基づいており、ユーザーが既にグループに追加された後に管理者がユーザーグループのサイトテンプレートを変更した場合、これらの変更は *サイトテンプレート* からの変更の伝達を有効にする場合にのみ有効になることに注意してください。ユーザーグループがチェックされました。
