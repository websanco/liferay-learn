# 基本的なWebコンテンツの記事の追加

Webコンテンツは、サイトコンテンツを作成するための最も実用的で不可欠な方法の1つです。 Webコンテンツの記事は、[ウィジェット](../../../site-building/displaying-content/additional-content-display-options/using-the-web-content-display-widget.md)、[フラグメント](../../../site-building/displaying-content/using_fragments.html)または[表示ページ](../../../site-building/displaying-content/using-display-page-templates/creating-and-managing-display-page-templates.md)を介して表示できます。

各Webコンテンツの記事は、使用可能なフィールドを定義するストラクチャーに基づいています。 Liferayには、 すぐに使える_基本Webコンテンツ_ストラクチャーが含まれていますが、独自のストラクチャーを作成することもできます。 詳しくは [Web コンテンツストラクチャーを理解する](../web-content-structures/understanding-web-content-structures.md) を参照してください。

## 基本的なWebコンテンツの記事の作成

1. サイトメニュー（![Site Menu](../../../images/icon-menu.png)）を開き、 _コンテンツ & データ_ → _Webコンテンツ_に移動します。

1. *追加*ボタン（![Add icon](../../../images/icon-add.png)）をクリックして、_[基本Webコンテンツ]_を選択します。

    ![Webコンテンツ管理ページ。](./adding-a-basic-web-content-article/images/01.png).

1. 新しい記事の*タイトル*（必須）を入力し、それぞれのフィールドに目的の*コンテンツ*を追加します。

    ![新しいWebコンテンツ記事の編集画面。](./adding-a-basic-web-content-article/images/02.png)

1. 完了したら、_［公開］_をクリックします。

    ```{tip}
    デフォルトテンプレートと表示ページテンプレートを使用した場合のコンテンツの見え方を確認するには、_Preview_ (![Preview](../../../images/icon-preview.png)) ボタンを使用します。

    ![デフォルトテンプレートと表示ページテンプレートのコンテンツをプレビューします。](./adding-a-basic-web-content-article/images/03.png)
    ```

　 サイトに基本的なWebコンテンツの記事を追加しました。 この記事をページに表示する方法の詳細は、[Webコンテンツの表示ウィジェットの使用](../../../site-building/displaying-content/additional-content-display-options/using-the-web-content-display-widget.md)を参照してください。

## [プロパティ]タブ

_基本Webコンテンツ_の記事を作成または編集する場合、サイドバーメニューの_[プロパティ]_タブから次のオプションにアクセスできます。

| **プロパティ**           | **目的**                                              | **追加メモ**                                                                                                                                                     |
| ------------------- | --------------------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| **基本情報**            | Webコンテンツの基礎となる構造、および簡単な説明を入力できるSummaryフィールドが表示されます。 | 記事の作成後は、構造を編集できません。 詳細は、[Web コンテンツストラクチャー](../web-content-structures/understanding-web-content-structures.md)を参照してください。                                      |
| **デフォルトのテンプレート**    | デフォルトで、このWebコンテンツの表示に使用するテンプレートを選択するために使用します。       | 詳細は、[Webコンテンツのテンプレートを追加する](../web-content-templates/creating-web-content-templates.md)を参照してください。                                                             |
| **ディスプレイページテンプレート** | 必要に応じて、この記事の表示ページテンプレートを選択するために使用します。               | 詳細は、[表示ページテンプレートの作成と管理](../../../site-building/displaying-content/using-display-page-templates/creating-and-managing-display-page-templates.md)を参照してください。    |
| **メイン画像**           | 記事のサムネイルとして使用する小さな画像を選択するために使用します。                  | 画像はURLから取得するか、システムからアップロードできます。                                                                                                                              |
| **メタデータ**           | 記事のタグまたは優先度を設定するために使用します。                           | 詳細は、[コンテンツのタグ付け](../../tags_and_categories.html)を参照してください。                                                                                                   |
| **フレンドリURL**        | この記事のカスタムフレンドリーURLを設定するために使用します。                    |                                                                                                                                                              |
| **スケジュール**          | 必要に応じて、記事を表示または期限切れにする日付をスケジュールするために使用します。          | 詳細は、[Scheduling Web Content](https://help.liferay.com/hc/en-us/articles/360029042011-Scheduling-Web-Content-Publication)を参照してください。                           |
| **検索機能**            | この記事を検索用に索引付けするかどうかを設定するために使用します。                   |                                                                                                                                                              |
| **関連するアセット**        | 必要に応じて、関連するアセットを選択するために使用します。                       |                                                                                                                                                              |
| **権限設定**            | 記事に固有のゲストまたはサイトメンバーの権限を設定するために使用します。                | ［More Options］をクリックすると、記事を閲覧できる人が表示されます。 詳細は、[ロールと権限](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)を参照してください。 |

## 追加情報

* [表示ページテンプレートの作成と管理](../../../site-building/displaying-content/using-display-page-templates/creating-and-managing-display-page-templates.md)
* [Introduction to Web Content Structures](../web-content-structures/understanding-web-content-structures.md)
* [Filtering and Sorting Web Content Articles](./filtering-and-sorting-web-content-articles.md)
