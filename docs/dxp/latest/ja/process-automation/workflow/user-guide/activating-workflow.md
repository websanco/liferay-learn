# ワークフローのアクティブ化

DXPインスタンス全体または特定のサイトのみで、アセットタイプのワークフロープロセスをアクティブ化できます。 ワークフロープロセスが*Wikiページ*などの特定のアセットタイプのインスタンス全体でアクティブ化されると、*Wikiページ*が作成されたサイトに関係なく、公開するために送信されたすべての*Wikiページ*はレビュープロセスを通過する必要があります。

または、個々のサイトでワークフローを有効または無効にすることを選択できます。 デフォルトでは、ワークフローはサイトで無効になっています。

## DXPインスタンス全体でワークフローをアクティブ化する

1.  *製品メニュー*（![Product Menu](../../../images/icon-product-menu.png)）を開き、*[Control Panel]* をクリックします。

2.  *[Workflow]*、*[Process Builder]* の順にクリックします。

3.  *[Configurations]* タブをクリックします。

    ![インスタンス全体の設定は[Configurations]タブにあります](./activating-workflow/images/02.png)

4.  ワークフロープロセスを実行するアセットタイプ（*Knowledge Base Article*など）の横にある*[Edit]* をクリックします。

    ![ナレッジベースの記事のインスタンス全体でワークフローをアクティブ化する](./activating-workflow/images/03.png)

5.  このアセットタイプに必要なワークフローを選択します（*[Single Approver]* は追加設定なしで使用できます）。

6.  *[保存]* をクリックします。

7.  ワークフローを必要とする他のすべてのアセットタイプについて繰り返します。

## サイトでのワークフローのアクティブ化

ワークフロープロセスがサイトで有効になっている場合、同じインスタンス上の他のサイトは無効のままになります。 サイトごとに各アセットタイプのワークフロープロセスを有効にするには：

1.  *製品メニュー*（![Product Menu](../../../images/icon-product-menu.png)）を開き、*サイト管理*メニューのコンパスアイコン（![Compass](../../../images/icon-compass.png)）をクリックします。

2.  フォームを作成するサイトを選択します（例：*Community Site* ）。

3.  *サイト管理*メニューで、*[Configuration]* → *[Workflow]* をクリックします。

4.  ワークフロープロセスを実行するアセットタイプの横にある*[Edit]* をクリックします。

5.  このアセットタイプに必要なワークフローを選択します。

    ![コミュニティサイトでブログエントリをアクティブ化する](./activating-workflow/images/01.png)

6.  *[保存]* をクリックします。

7.  ワークフローを必要とする他のすべてのアセットタイプについて繰り返します。

有効にすると、選択したアセットタイプは、このサイトで公開する前に必ずレビュープロセスが必要になります。 特定のアセットタイプに対して以前にワークフローをアクティブにしたことがある場合、それらの設定がここに反映されます。

``` tip::
   インスタンス全体で有効になっている場合でも、サイト上のアセットタイプのワークフローはいつでも無効にできます。
```

## 特定のアプリケーションのワークフローをアクティブにする

それぞれのアプリケーションでワークフローが有効になっているアセットタイプがいくつかあります。

### Webコンテンツフォルダー

*Webコンテンツフォルダー*のワークフローをアクティブ化するには：

1.  サイトの*サイト管理*メニューに移動します。

2.  *[Content & Data]* → *[Web Content]* の順にクリックします。

3.  （![Actions](../../../images/icon-actions.png)）をクリックし、目的のフォルダの横にある*[Edit]* をクリックします。

    ![フォルダの編集画面からWebコンテンツフォルダーのワークフローをアクティブ化する。](./activating-workflow/images/04.png)

4.  *[Structure Restrictions and Workflow]* メニューを展開します。

5.  *[Default Workflow for This Folder (Lunar Resort Holiday Getaway Content)]* のラジオボタンをクリックします。

    ![Webコンテンツフォルダーのワークフローを選択。](./activating-workflow/images/05.png)

6.  ワークフロー定義を選択します（例：*Single Approver* ）。

7.  *[保存]* ボタンをクリックします。

### ドキュメントとメディアフォルダ

ワークフローは、*ルート*フォルダ内の*ドキュメントとメディア*アプリケーション全体または個々のフォルダに対して有効にできます。

*ドキュメントとメディアフォルダ*のワークフローをアクティブ化するには：

1.  サイトの*サイト管理*メニューに移動します。

2.  *[Content & Data]* → *[Documents and Media]* の順にクリックします。

3.  （![Actions](../../../images/icon-actions.png)）をクリックし、目的のフォルダの横にある*[Edit]* をクリックします。

    ![DMフォルダのワークフローを選択。](./activating-workflow/images/06.png)

4.  *[Document Type Restrictions and Workflow]* メニューを展開します。

5.  *[Default Workflow for This Folder (DM Folder 1)]* のラジオボタンをクリックします。

    ![DMフォルダのワークフローを選択。](./activating-workflow/images/07.png)

6.  ワークフロー定義を選択します（例：*Single Approver* ）。

7.  *[保存]* ボタンをクリックします。

### 動的データリスト

*DDLフォーム*のワークフローをアクティブ化するには：

1.  サイトの*サイト管理*メニューに移動します。

2.  *[Content & Data]* → *[Dynamic Data Lists]* の順にクリックします。

3.  （![Add](../../../images/icon-add.png)）をクリックして新しいフォームを追加します。

4.  DDLフォームの名前を入力します。

5.  *[Select]* をクリックして、このフォームに関連付けられているデータ定義を選択します。

6.  [Data Definition]を選択します（*Contacts*）。

7.  *[Workflow]* で、目的のワークフロー（*Single Approver*）を選択します。

    ![個々の動的データリストごとにワークフローをアクティブ化します。](./activating-workflow/images/08.png)

8.  *[保存]* をクリックします。

この特定のDDLフォームが送信プロセスを通過するようになりました。

### フォーム

個々のフォームのエントリごとにワークフローをアクティブ化するには

1.  サイトの*サイト管理*メニューに移動します。

2.  *[Content & Data]* → *[Forms]* の順にクリックします。

3.  *フォームビルダー*ビューで（新しいフォームの場合も既存のフォームの場合も同様）、*オプション* (![Options](../../../images/icon-options.png))、*[Settings]* の順にクリックします。

    ![フォームの設定に移動](./activating-workflow/images/09.png)

4.  *[Select a workflow]* で、目的のワークフロー（*Single Approver*）を選択します。

    ![フォームの設定ウィンドウから各フォームのエントリでワークフローをアクティブにします。](./activating-workflow/images/10.png)

5.  *[Done]* をクリックして、ダイアログウィンドウを閉じます。

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

  - [Reviewing Assets](./reviewing-assets.md)
