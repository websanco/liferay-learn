# ワークフローのアクティブ化

DXPインスタンス全体または特定のサイトのみで、アセットタイプのワークフロープロセスをアクティブ化できます。 ワークフロープロセスが_Wikiページ_などの特定のアセットタイプのインスタンス全体でアクティブ化されると、_Wikiページ_が作成されたサイトに関係なく、公開するために送信されたすべての_Wikiページ_はレビュープロセスを通過する必要があります。

または、個々のサイトでワークフローを有効または無効にすることを選択できます。 デフォルトでは、ワークフローはサイトで無効になっています。

## DXPインスタンス全体でワークフローをアクティブ化する

1. _プロダクトメニュー_（![Product Menu](../../../images/icon-product-menu.png)）を開き、_［コントロールパネル］_をクリックします。
1. _［ワークフローワークフロー］_、_［プロセスビルダー］_の順にクリックします。
1. _［設定］_ タブをクリックします。

    ![インスタンス全体の設定は［Configurations］タブにあります](./activating-workflow/images/02.png)

1. ワークフロープロセスを実行するアセットタイプ（_ナレッジベースの記事_など）の横にある_［Edit］_をクリックします。

    ![ナレッジベースの記事のインスタンス全体でワークフローをアクティブ化する](./activating-workflow/images/03.png)

1. このアセットタイプに必要なワークフローを選択します（_［Single Approver］_は追加設定なしで使用できます）。
1. _［保存］_ をクリックします。
1. ワークフローを必要とする他のすべてのアセットタイプについて繰り返します。

## サイトでのワークフローのアクティブ化

ワークフロープロセスがサイトで有効になっている場合、同じインスタンス上の他のサイトは無効のままになります。 サイトごとに各アセットタイプのワークフロープロセスを有効にするには：

1. _プロダクトメニュー_（![Product Menu](../../../images/icon-product-menu.png)）を開き、_サイト管理_メニューのコンパスアイコン（![Compass](../../../images/icon-compass.png)）をクリックします。
1. フォームを作成するサイトを選択します（例：_Community Site_ ）。
1. _［サイト管理］_メニューで&rarr;_［設定］_&rarr;_［ワークフロー］_をクリックします。
1. ワークフロープロセスを実行するアセットタイプの横にある_［Edit］_をクリックします。
1. このアセットタイプに必要なワークフローを選択します。

    ![コミュニティサイトでブログエントリをアクティブ化する](./activating-workflow/images/01.png)

1. _［保存］_ をクリックします。
1. ワークフローを必要とする他のすべてのアセットタイプについて繰り返します。

有効にすると、選択したアセットタイプは、このサイトで公開する前に必ずレビュープロセスが必要になります。 特定のアセットタイプに対して以前にワークフローをアクティブにしたことがある場合、それらの設定がここに反映されます。

```tip::
   インスタンス全体で有効になっている場合でも、サイト上のアセットタイプのワークフローはいつでも無効にできます。
```

## 特定のアプリケーションのワークフローをアクティブにする

それぞれのアプリケーションでワークフローが有効になっているアセットタイプがいくつかあります。

### Webコンテンツフォルダー

_Webコンテンツフォルダー_のワークフローをアクティブ化するには：

1. サイトの_サイト管理_メニューに移動します。
1. _［コンテンツ&データ］_&rarr;_［Webコンテンツ］_をクリックします。
1. （![Actions](../../../images/icon-actions.png)）をクリックし、目的のフォルダの横にある_［Edit］_をクリックします。

    ![フォルダの編集画面からWebコンテンツフォルダーのワークフローをアクティブ化する。](./activating-workflow/images/04.png)

1. _［ストラクチャー制約と、ワークフロー］_メニューを展開します。
1. _［デフォルトワークフロー（Lunar Resort Holiday Getaway Content）］_のラジオボタンをクリックします。

    ![Webコンテンツフォルダーのワークフローを選択。](./activating-workflow/images/05.png)

1. ワークフロー定義を選択します（例：_Single Approver_ ）。
1. _［保存］_ボタンをクリックします。

### ドキュメントとメディアフォルダ

ワークフローは、_ルート_フォルダ内の_ドキュメントとメディア_アプリケーション全体または個々のフォルダに対して有効にできます。

_ドキュメントとメディアフォルダ_のワークフローをアクティブ化するには：

1. サイトの_サイト管理_メニューに移動します。
1. _［Content & Data］_ &rarr; _［ドキュメントとメディア］_の順にクリックします。
1. （![Actions](../../../images/icon-actions.png)）をクリックし、目的のフォルダの横にある_［Edit］_をクリックします。

    ![DMフォルダのワークフローを選択。](./activating-workflow/images/06.png)

1. _［ドキュメントタイプ制限およびワークフロー］_メニューを展開します。
1. _［このフォルダのデフォルトワークフロー(DM Folder 1)］_のラジオボタンをクリックします。

    ![DMフォルダのワークフローを選択。](./activating-workflow/images/07.png)

1. ワークフロー定義を選択します（例：_Single Approver_ ）。
1. _［保存］_ボタンをクリックします。

### 動的データリスト

_DDLフォーム_のワークフローをアクティブ化するには：

1. サイトの_サイト管理_メニューに移動します。
1. _［Content & Data］_ &rarr; _［動的データリスト(DDL)］_の順にクリックします。
1. （![Add](../../../images/icon-add.png)）をクリックして新しいフォームを追加します。
1. DDLフォームの名前を入力します。
1. _［Select］_をクリックして、このフォームに関連付けられているデータ定義を選択します。
1. ［Data Definition］を選択します（_Contacts_）。
1. _［Workflow］_で、目的のワークフロー（_Single Approver_）を選択します。

    ![個々の動的データリストごとにワークフローをアクティブ化します。](./activating-workflow/images/08.png)

1. _［保存］_ をクリックします。

この特定のDDLフォームが送信プロセスを通過するようになりました。

### フォーム

個々のフォームのエントリごとにワークフローをアクティブ化するには

1. サイトの_サイト管理_メニューに移動します。
1. _［コンテンツ & データ］_ &rarr; _ ［フォーム］_をクリックします。
1. _フォームビルダー_ビューで（新しいフォームの場合も既存のフォームの場合も同様）、_オプション_ (![Options](../../../images/icon-actions.png))、_［Settings］_の順にクリックします。

    ![フォームの設定に移動](./activating-workflow/images/09.png)

1. _［ワークフローを選択］_で、目的のワークフロー（_Single Approver_）を選択します。

    ![フォームの設定ウィンドウから各フォームのエントリでワークフローをアクティブにします。](./activating-workflow/images/10.png)

1. _［完了］_をクリックして、ダイアログウィンドウを閉じます。

情報を送信する前に、フォームの承認が必要になりました。


<!-- Leaving this commented out until Staging materials are pushed up.
## Page Variations

In a [staged environment](https://help.liferay.com/hc/en-us/articles/360029041851-Staging-Content), you can activate workflow for _Page Variations_.

As a prerequisite, [Staging](https://help.liferay.com/hc/articles/360029041811-Enabling-Staging) **and** [Page Versioning](https://help.liferay.com/hc/articles/360028721532-Enabling-Page-Versioning-and-Staged-Content) have been enabled.

When a Page Variation or Site Page Variation is created, its creator must click _Submit for Publication_ at the top of the page, and the variation must be approved in the workflow before it can be published to the live Site.

To enable a workflow for Page Variations:

1. Navigate to the _Site Administration_ menu for your site.
1. Verify that the Staging has been enabled for this site.

    ![Verify that the site is now staged.](./activating-workflow/images/11.png)

1. Click _Configuration_ &rarr; _Workflow_.
1. Select the workflow desired for _Page Revision_.

    ![Verify that the site is now staged.](./activating-workflow/images/12.png)

1. Click _Save_.

Page revisions now have to go through the workflow process.

![With workflow enabled on Page Revisions, the Site administrator must submit their page variation for publication before it can go live.](./activating-workflow/images/13.png)
-->

## 次のステップ

* [Reviewing Assets](./reviewing-assets.md)
