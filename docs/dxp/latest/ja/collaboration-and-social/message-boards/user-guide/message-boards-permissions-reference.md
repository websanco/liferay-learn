# 掲示板の権限リファレンス

この記事では、 ［**掲示板**］ ウィジェットでの権限について説明し、ユーザーおよびロールに権限を割り当てる際のリファレンスとして機能します。 DXPのロールと権限の詳細は、 [Roles and Permissions](https://help.liferay.com/hc/articles/360017895212-Roles-and-Permissions) をご覧ください 。

<a name="home-category-permissions" />

## ホームカテゴリの権限

**掲示板** の権限画面は、 **掲示板** の機能へのアクセスを許可および取り消すためのものです。

1. ホストサイトの **サイト管理** に移動します（たとえば、Liferay DXPのゲストサイト）。
1. ［**コンテンツ & データ**］ &rarr; ［**掲示板**］ の順にクリックします。
1. **オプション** アイコン（![Options](./message-boards-permissions-reference/images/01.png)）、 ［**Home Category Permissions**］ の順にクリックします。

    ![権限をサイトロールに割り当てる](./message-boards-permissions-reference/images/03.png)

権限により、特定のロールを持つユーザーは次のアクションを実行できます。

| 権限名                         | 説明                                 |
| :--- | :--- |
| **Permissions：** | 権限を表示および変更する機能を付与します。              |
| **Add File：** | メッセージにファイルを添付する機能を付与します。           |
| **Ban User：** | ユーザーがメッセージボードに参加することを禁止する機能を付与します。 |
| **Add Category：** | メッセージボードに新しいカテゴリを追加します。            |
| **Reply to Message：** | 既存のメッセージに応答します。                    |
| **Lock Thread：** | スレッドのメッセージへの追加または変更を停止します。         |
| **Subscribe：** | 新規投稿および変更された投稿に関する通知を受け取ります。       |
| **View：** | メッセージスレッドのすべての内容を表示します。            |
| **Add Message：** | 新しいスレッドを投稿します。                     |
| **Move Thread：** | スレッドを別のカテゴリまたはサブカテゴリに移動します。        |
| **Update Thread Priority：** | スレッドの優先順位を変更します。                   |

<a name="widget-permissions" />

## ウィジェットの権限

［掲示板］ウィジェットをページに追加した後、管理者はそのウィジェットインスタンスの通常権限にアクセスできます。 これを行うには、ウィジェットの **オプション** メニュー（![Options](./message-boards-permissions-reference/images/02.png)）を選択し、 ［**Permissions**］ を選択します。

![ウィジェット権限を割り当てる](./message-boards-permissions-reference/images/04.png)

| 権限名                | 説明                           |
| :--- | :--- |
| **Permissions：** | ウィジェットの権限を表示および変更する機能を付与します。 |
| **Preferences：** | ウィジェットの設定を表示および変更する機能を付与します。 |
| **Configuration：** | ウィジェットの設定を表示および変更する機能を付与します。 |
| **View：** | ウィジェットを表示する機能を付与します。         |
| **Add to Page：** | サイトページにウィジェットを追加する機能を付与します。  |

<a name="general-category-permissions" />

## 一般的なカテゴリの権限

この一連の権限は、ゲストおよびサイトメンバーがカテゴリに対して行うことができる変更を決定します。

![一般的なカテゴリの権限](./message-boards-permissions-reference/images/05.png)

| 権限名                         | 説明                           |
| :--- | :--- |
| **Delete：** | カテゴリを削除します。                  |
| **Permissions：** | 権限を表示および変更します。               |
| **Add File：** | メッセージにファイルを添付します。            |
| **Reply to Message：** | 既存のメッセージに応答します。              |
| **Lock Thread：** | スレッドのメッセージへの追加または変更を停止します。   |
| **Update：** | カテゴリを編集します。                  |
| **Subscribe：** | 新規投稿および変更された投稿に関する通知を受け取ります。 |
| **View：** | カテゴリを表示します。                  |
| **Add Message：** | 新しいスレッドを投稿します。               |
| **Move Thread：** | スレッドを別のカテゴリまたはサブカテゴリに移動します。  |
| **Add Subcategory：** | このカテゴリ内に新しいカテゴリを追加します。       |
| **Update Thread Priority：** | スレッドの優先順位を変更します。             |

<a name="thread-permissions" />

## スレッドの権限

 この一連の権限は、ユーザーがスレッドに対して行える変更を決定します。

| 権限名              | 説明                     |
| :--- | :--- |
| **Delete：** | スレッドを削除します。            |
| **Permissions：** | スレッドの権限の付与や取り消しを行います。  |
| **Update：** | スレッドを編集します。            |
| **Subscribe：** | スレッドアクティビティの通知を受け取ります。 |
| **View：** | スレッドを表示します。            |
