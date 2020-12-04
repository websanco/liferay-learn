# コンテンツページでのWebコンテンツの管理

> 対応可能：Liferay DXP 7.3以降

コンテンツページの[ [コンテンツ]パネル](./content-pages-overview.md#contents) から、ページで使用されているWebコンテンツを管理および編集できます（[マップされている](./building-content-pages.md#mapping-content)、またはウィジェットに表示されている）。 コンテンツページで使用されるWebコンテンツを管理するには、次の手順に従います。

1.  サイドバーの[ *コンテンツ* ]ボタン（![Contents](../../../images/icon-contents.png)）をクリックして、コンテンツパネルを開きます。 このページで使用されているコンテンツと、そのページが使用されているページの数がここに表示されます。 リストのコンテンツにカーソルを合わせると、ページにコンテンツを表示するウィジェット、マップされたコンテンツフィールド、およびフラグメントが強調表示されます。

    ![[コンテンツ]パネルからコンテンツページのWebコンテンツを管理できます。](./managing-content-on-content-pages/images/01.png)

    ``` note::
      Web content that's displayed in an Asset Publisher with dynamic selection isn't listed in the Contents panel. You must manage this content directly from the Web Content admin app in the Control Panel.
    ```

2.  リストされたコンテンツの横にあるアクションメニューを開いて管理します。 次のアクションを使用できます。

      - *編集：* Webコンテンツを編集します
      - *権限：* Webコンテンツの権限を更新します
      - *状況を表示：* Webコンテンツがサイトで使用されている場所を確認します

<!-- end list -->

``` note::
  To edit inline content, you must have the "Edit Inline Content" permission assigned to your Role. However, if you don't have permission to edit the page, you can still edit content (web content, blog, etc.) that you have permission to edit that's displayed through one of these methods: mapped content, web content display, or an Asset Publisher (with manual selection).
```
