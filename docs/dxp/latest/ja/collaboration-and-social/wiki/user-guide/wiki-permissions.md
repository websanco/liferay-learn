# Wiki権限リファレンス

この記事では、*[Wiki]* ウィジェットでの権限について説明し、ユーザーおよびロールに権限を割り当てる際のリファレンスとして機能します。 DXPのロールと権限の詳細については、[Roles and Permissions](https://help.liferay.com/hc/articles/360017895212-Roles-and-Permissions)をご覧ください 。

## Wikiノードの権限

1.  *[Site Administration]* → *[Content & Data]* → *[Wiki]* に移動します。

2.  ノード（たとえば*New Node*）の横にある*オプション*（![Options](../../../images/icon-options.png)）をクリックし、*[Permissions]* をクリックします。

    ![Wikiノードの権限の表示](./wiki-permissions/images/06.png)

利用可能な* wikiノード*権限は次のとおりです。

![Wikiノードの権限の表示](./wiki-permissions/images/05.png)

| 権限             | 説明                                                                                                                   |
| -------------- | -------------------------------------------------------------------------------------------------------------------- |
| Add Page       | ノードにWikiページを追加する権限をユーザーに付与します                                                                                        |
| Import         | wikiページを`.lar`ファイルとしてインポートする権限をユーザーに付与します（[Exporting and Importing a Wiki](./exporting-and-importing-a-wiki.md)を参照）。 |
| Delete         | * Wikiノード*を削除する権限をユーザーに付与します                                                                                         |
| Permissions    | Wikiノードの権限を表示する機能をユーザーに付与します                                                                                         |
| Update         | * Wikiノード*を変更する権限をユーザーに付与します                                                                                         |
| Add Attachment | 添付ファイルを追加する権限をユーザーに付与します                                                                                             |
| Subscribe      | ノードにサブスクライブする機能をユーザーに付与します                                                                                           |
| View           | ユーザーが* Wikiノード*を表示できるようにします                                                                                          |

## Wikiページの権限

Wikiページの権限を表示するには：

1.  任意のWikiページで、*[Details]* をクリックします。
2.  *[Advanced Actions]* セクションで*[Permissions]* をクリックします。

![Wikiページの権限の表示](./wiki-permissions/images/04.png)

| 権限                | 説明                                 |
| ----------------- | ---------------------------------- |
| Update Discussion | * Wikiページ*へのコメントを更新する機能をユーザーに付与します |
| Delete            | * Wikiページ*を削除する権限をユーザーに付与します       |
| Permissions       | ユーザーにページの権限を表示する機能を付与します           |
| Delete Discussion | コメントを削除する権限をユーザーに付与します             |
| Update            | * Wikiページ*を変更する権限をユーザーに付与します       |
| View              | ユーザーが* Wikiページ*を表示できるようにします        |
| Add Discussion    | ユーザーが* Wikiページ*にコメントできるようにします      |

## Wikiウィジェットの権限

1.  *[Wiki]* ウィジェットがデプロイされているサイトページに移動します。
2.  *[Wiki]* の上にマウスを置き、*オプション*（![Options](../../../images/icon-widget-options.png)）をクリックします。
3.  *[Permissions]* をクリックします。

![Wikiウィジェットの権限の表示](./wiki-permissions/images/03.png)

| 権限                   | 説明                                                     |
| -------------------- | ------------------------------------------------------ |
| Add Display Template | 表示テンプレートをWikiページに追加する機能を付与します                          |
| Permissions          | *[Wiki]* ウィジェットの権限を表示する機能をユーザーに付与します                    |
| Preferences          | *[Wiki]* ウィジェットの設定を表示する機能をユーザーに付与します                    |
| Configuration        | *[Wiki]* ウィジェットの[Configuration]メニューにアクセスする機能をユーザーに付与します |
| View                 | ユーザーが*[Wiki]* ウィジェットを表示できるようにします                        |
| Add to Page          | *[Wiki]* ウィジェットをサイトページに追加する機能を付与します                     |

## Wikiの権限

これらの権限は、*サイト管理*にあります。

1.  *[Site Administration]* → *[Content & Data]* → *[Wiki]* に移動します。
2.  *オプション*（![Widget Options](../../../images/icon-options.png)） → *[Wikis Permissions]* の順にクリックします。

![Wikiオプションの権限](./wiki-permissions/images/01.png)

使用可能な権限は次のとおりです。

![Wikiオプションの権限](./wiki-permissions/images/02.png)

| 権限          | 説明                        |
| ----------- | ------------------------- |
| Permissions | ユーザーにページの権限を表示する機能を付与します  |
| Add Node    | Wikiノードを追加する権限をユーザーに付与します |
