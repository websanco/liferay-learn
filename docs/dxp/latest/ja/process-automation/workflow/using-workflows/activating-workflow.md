# ワークフローのアクティブ化

DXPインスタンス全体または特定のサイトのみで、アセットタイプのワークフロープロセスをアクティブ化できます。 ワークフロープロセスが **Wikiページ** などの特定のアセットタイプのインスタンス全体でアクティブ化されると、 **Wikiページ** が作成されたサイトに関係なく、公開するために送信されたすべての **Wikiページ** はレビュープロセスを通過する必要があります。

または、個々のサイトでワークフローを有効または無効にすることを選択できます。 デフォルトでは、ワークフローはサイトで無効になっています。

<a name="activating-workflow-across-a-dxp-instance" />

## DXPインスタンス全体でワークフローをアクティブ化する

1. **プロダクトメニュー**（![Product Menu](../../../images/icon-product-menu.png)）を開き、 ［**コントロールパネル**］ をクリックします。
1. ［**ワークフローワークフロー**］ 、 ［**プロセスビルダー**］ の順にクリックします。
1. ［**設定**］ タブをクリックします。

    ![インスタンス全体の設定は［Configurations］タブにあります](./activating-workflow/images/02.png)

1. ワークフロープロセスを実行するアセットタイプ（**ナレッジベースの記事** など）の横にある ［**Edit**］ をクリックします。

    ![ナレッジベースの記事のインスタンス全体でワークフローをアクティブ化する](./activating-workflow/images/03.png)

1. このアセットタイプに必要なワークフローを選択します（［**Single Approver**］ は追加設定なしで使用できます）。
1. ［**保存**］ をクリックします。
1. ワークフローを必要とする他のすべてのアセットタイプについて繰り返します。

<a name="activating-workflow-on-a-site" />

## サイトでのワークフローのアクティブ化

ワークフロープロセスがサイトで有効になっている場合、同じインスタンス上の他のサイトは無効のままになります。 サイトごとに各アセットタイプのワークフロープロセスを有効にするには：

1. **プロダクトメニュー**（![Product Menu](../../../images/icon-product-menu.png)）を開き、 **サイト管理** メニューのコンパスアイコン（![Compass](../../../images/icon-compass.png)）をクリックします。
1. フォームを作成するサイトを選択します（例： **Community Site**）。
1. ［**サイト管理**］ メニューで&rarr; ［**設定**］ &rarr; ［**ワークフロー**］ をクリックします。
1. ワークフロープロセスを実行するアセットタイプの横にある ［**Edit**］ をクリックします。
1. このアセットタイプに必要なワークフローを選択します。

    ![コミュニティサイトでブログエントリをアクティブ化する](./activating-workflow/images/01.png)

1. ［**保存**］ をクリックします。
1. ワークフローを必要とする他のすべてのアセットタイプについて繰り返します。

有効にすると、選択したアセットタイプは、このサイトで公開する前に必ずレビュープロセスが必要になります。 特定のアセットタイプに対して以前にワークフローをアクティブにしたことがある場合、それらの設定がここに反映されます。

```tip::
   インスタンス全体で有効になっている場合でも、サイト上のアセットタイプのワークフローはいつでも無効にできます。
```

<a name="activating-workflow-for-specific-applications" />

## 特定のアプリケーションのワークフローをアクティブにする

それぞれのアプリケーションでワークフローが有効になっているアセットタイプがいくつかあります。

### Webコンテンツフォルダー

**Webコンテンツフォルダー** のワークフローをアクティブ化するには：

1. サイトの **サイト管理** メニューに移動します。
1. ［**コンテンツ&データ**］ &rarr; ［**Webコンテンツ**］ をクリックします。
1. （![Actions](../../../images/icon-actions.png)）をクリックし、目的のフォルダの横にある ［**Edit**］ をクリックします。

    ![フォルダの編集画面からWebコンテンツフォルダーのワークフローをアクティブ化する。](./activating-workflow/images/04.png)

1. ［**ストラクチャー制約と、ワークフロー**］ メニューを展開します。
1. ［**デフォルトワークフロー（Lunar Resort Holiday Getaway Content**）］ のラジオボタンをクリックします。

    ![Webコンテンツフォルダーのワークフローを選択。](./activating-workflow/images/05.png)

1. ワークフロー定義を選択します（例： **Single Approver**）。
1. ［**保存**］ ボタンをクリックします。

### ドキュメントとメディアフォルダ

ワークフローは、 **ルート** フォルダ内の **ドキュメントとメディア** アプリケーション全体または個々のフォルダに対して有効にできます。

**ドキュメントとメディアフォルダ** のワークフローをアクティブ化するには：

1. サイトの **サイト管理** メニューに移動します。
1. ［**Content & Data**］ &rarr; ［**ドキュメントとメディア**］ の順にクリックします。
1. （![Actions](../../../images/icon-actions.png)）をクリックし、目的のフォルダの横にある ［**Edit**］ をクリックします。

    ![DMフォルダのワークフローを選択。](./activating-workflow/images/06.png)

1. ［**ドキュメントタイプ制限およびワークフロー**］ メニューを展開します。
1. ［**このフォルダのデフォルトワークフロー(DM Folder 1**)］ のラジオボタンをクリックします。

    ![DMフォルダのワークフローを選択。](./activating-workflow/images/07.png)

1. ワークフロー定義を選択します（例： **Single Approver**）。
1. ［**保存**］ ボタンをクリックします。

### 動的データリスト

**DDLフォーム** のワークフローをアクティブ化するには：

1. サイトの **サイト管理** メニューに移動します。
1. ［**Content & Data**］ &rarr; ［**動的データリスト(DDL**)］ の順にクリックします。
1. （![Add](../../../images/icon-add.png)）をクリックして新しいフォームを追加します。
1. DDLフォームの名前を入力します。
1. ［**Select**］ をクリックして、このフォームに関連付けられているデータ定義を選択します。
1. ［Data Definition］を選択します（**Contacts**）。
1. ［**Workflow**］ で、目的のワークフロー（**Single Approver**）を選択します。

    ![個々の動的データリストごとにワークフローをアクティブ化します。](./activating-workflow/images/08.png)

1. ［**保存**］ をクリックします。

この特定のDDLフォームが送信プロセスを通過するようになりました。

### フォーム

個々のフォームのエントリごとにワークフローをアクティブ化するには

1. サイトの **サイト管理** メニューに移動します。
1. ［**コンテンツ & データ**］ &rarr; ［**フォーム**］ をクリックします。
1. **フォームビルダー** ビューで（新しいフォームの場合も既存のフォームの場合も同様）、 **オプション**(![Options](../../../images/icon-actions.png))、 ［**Settings**］ の順にクリックします。

    ![フォームの設定に移動](./activating-workflow/images/09.png)

1. ［**ワークフローを選択**］ で、目的のワークフロー（**Single Approver**）を選択します。

    ![フォームの設定ウィンドウから各フォームのエントリでワークフローをアクティブにします。](./activating-workflow/images/10.png)

1. ［**完了**］ をクリックして、ダイアログウィンドウを閉じます。

情報を送信する前に、フォームの承認が必要になりました。


<!-- Leaving this commented out until Staging materials are pushed up.
## Page Variations

In a [staged environment](https://help.liferay.com/hc/en-us/articles/360029041851-Staging-Content), you can activate workflow for **Page Variations** .

As a prerequisite, [Staging](https://help.liferay.com/hc/articles/360029041811-Enabling-Staging)**and**[Page Versioning](https://help.liferay.com/hc/articles/360028721532-Enabling-Page-Versioning-and-Staged-Content) have been enabled.

When a Page Variation or Site Page Variation is created, its creator must click **Submit for Publication** at the top of the page, and the variation must be approved in the workflow before it can be published to the live Site.

To enable a workflow for Page Variations:

1. Navigate to the **Site Administration** menu for your site.
1. Verify that the Staging has been enabled for this site.

    ![Verify that the site is now staged.](./activating-workflow/images/11.png)

1. Click **Configuration** &rarr; **Workflow** .
1. Select the workflow desired for **Page Revision** .

    ![Verify that the site is now staged.](./activating-workflow/images/12.png)

1. Click **Save** .

Page revisions now have to go through the workflow process.

![With workflow enabled on Page Revisions, the Site administrator must submit their page variation for publication before it can go live.](./activating-workflow/images/13.png)
-->

<a name="whats-next" />

## 次のステップ

* [アセットの確認](./reviewing-assets.md)
