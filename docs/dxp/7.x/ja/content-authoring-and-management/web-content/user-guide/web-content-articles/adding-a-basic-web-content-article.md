# 基本的なWebコンテンツの記事の追加

Webコンテンツは、DXPでサイトコンテンツを作成するための最も実用的で不可欠な方法の1つです。 Webコンテンツの記事は、ページ上のウィジェットを介して表示したり、コンテンツを表示するために特別に設計された特別な[表示ページ](https://help.liferay.com/hc/en-us/articles/360029133291-Creating-Display-Page-Templates)に表示できます。
すべてのWebコンテンツの記事は、利用可能なフィールドを定義する構造に基づいています。 <!-- TODO: See the [Introduction to Web Content Structures](../web-content-structures/introduction-to-web-content-structures.md) for more information.--> DXPには、デフォルトの構造である

* Basic Web Contentが用意されています。 *以下の手順では、このデフォルトの構造を使用します。

## 基本的なWebコンテンツの記事の作成

次の手順を使用して、*基本的なWebコンテンツ*の記事を作成します。

1.  [Site]メニューを開き、 *[Content & Data]* → *[Web Content]* に移動します。

2.  *[Web Content]* 画面で、![Add icon](../../../../images/icon-add.png)ボタンをクリックし、表示されるメニューで*[Basic Web Content]* をクリックします。

    ![Webコンテンツ管理ページ。](./adding-a-basic-web-content-article/images/01.png).

3.  タイトルとコンテンツをそれぞれのフィールドに追加します。

    ![新しいWebコンテンツ記事の編集画面。](./adding-a-basic-web-content-article/images/02.png).

4.  *[Publish]* をクリックします。
サイトに基本的なWebコンテンツの記事を追加しました。 <!-- TODO: See [Using the Web Content Display Widget](../../../../site-building/displaying-content/using-the-web-content-display-widget.md) for more information on displaying this article on a page. -->

## [Properties]タブ

画面右側の *[Properties]* タブを開くと、編集中の記事の詳細を表示して編集できます。 ここでは次のプロパティを使用できます。

| **プロパティ**                 | **目的**                                                | **その他の注意事項**                                                                                                                                                    |
| ------------------------- | ----------------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **Basic Information**     | Webコンテンツの基礎となる構造、および簡単な説明を入力できる[Summary]フィールドが表示されます。 | 記事の作成後は、構造を編集できません。 詳細については、[Web Content Structures](https://help.liferay.com/hc/en-us/articles/360029133211-Editing-Structures)を参照してください。                      |
| **Default Template**      | デフォルトで、このWebコンテンツの表示に使用するテンプレートを選択するために使用します。         | 詳細については、[Adding Web Content Templates](https://help.liferay.com/hc/en-us/articles/360028820252-Adding-Templates-with-Structures)を参照してください。                      |
| **Display Page Template** | 必要に応じて、この記事の表示ページテンプレートを選択するために使用します。                 | 詳細については、[Display Page Templates](https://help.liferay.com/hc/en-us/articles/360029133291-Creating-Display-Page-Templates)を参照してください。                             |
| **Featured Image**        | 記事のサムネイルとして使用する小さな画像を選択するために使用します。                    | 画像はURLから取得するか、システムからアップロードできます。                                                                                                                                 |
| **Metadata**              | 記事のタグまたは優先度を設定するために使用します。                             | 詳細については、[Tagging Content](../../../05-tags-and-categories/01-user-guide/README.md)を参照してください。                                                                    |
| **Friendly URL**          | この記事のカスタムフレンドリーURLを設定するために使用します。                      |                                                                                                                                                                 |
| **Schedule**              | 必要に応じて、記事を表示または期限切れにする日付をスケジュールするために使用します。            | 詳細については、[Scheduling Web Content](https://help.liferay.com/hc/en-us/articles/360029042011-Scheduling-Web-Content-Publication)を参照してください。                          |
| **Search**                | この記事を検索用に索引付けするかどうかを設定するために使用します。                     |                                                                                                                                                                 |
| **Related Assets**        | 必要に応じて、関連するアセットを選択するために使用します。                         |                                                                                                                                                                 |
| **Permissions**           | 記事に固有のゲストまたはサイトメンバーの権限を設定するために使用します。                  | [More Options]をクリックすると、その記事を表示できるユーザー以外の権限が表示されます。 詳細については、[Roles and Permissions](../../../../users-and-permissions/roles-and-permissions/README.md)を参照してください。 |

## 追加情報

  - [Display Page Templates](https://help.liferay.com/hc/en-us/articles/360029133291-Creating-Display-Page-Templates)

<!-- TODO: * [Introduction to Web Content Structures](../web-content-structures/introduction-to-web-content-structures.md) 
* [Using the Web Content Display Widget](../../../../site-building/displaying-content/using-the-web-content-display-widget.md)-->
