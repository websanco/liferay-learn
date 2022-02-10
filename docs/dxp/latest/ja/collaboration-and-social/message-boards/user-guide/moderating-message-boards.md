# 掲示板のモデレート

_掲示板_には、フォーラムを効果的にモデレートできる機能が含まれています。 これには、スレッドのロック、スレッドの移動とマージ、ユーザーの禁止、スレッドのフラグ付けが含まれます。 適切に指定された権限を持つユーザーは、これらのツールを使用できます。 _掲示板_権限をロールに割り当てる詳細は、[Message Boards Permissions Reference](./message-boards-permissions-reference.md)を参照してください。 一般的なロールと権限の詳細は、 [Roles and Permissions](https://help.liferay.com/hc/articles/360017895212-Roles-and-Permissions) を参照してください 。

<a name="スレッドのロック" />

## スレッドのロック

サイト管理者は、スレッドのアクションメニューから_［Lock］_を選択して、スレッドのアクティビティを停止できます。 スレッドがロックされると、追加の応答は許可されません。

スレッドをロックするには：

1. ロックするスレッドの横にある（![Actions](../../../images/icon-actions.png)）をクリックします。
1. _［Lock］_ をクリックします。

    ![スレッドのロック](./moderating-message-boards/images/02.png)

スレッドのロックを解除するには：

1. ロックを解除するスレッドの横にある（![Actions](../../../images/icon-actions.png)）をクリックします。
1. _［Unlock］_ をクリックします。

スレッドのロックが解除されました。

<a name="スレッドの移動" />

## スレッドの移動

モデレーターは、スレッドをあるカテゴリから別のカテゴリに移動できます。 スレッドを移動するには、次の手順に従います。

1. スレッドのアクションメニュー（![Actions](../../../images/icon-actions.png)）から_［Move］_を選択します。
1. _［Select］_ボタンをクリックして、新しいカテゴリを選択します。
1. 移動を説明する投稿を追加するには、_［Add explanation post］_をオンにします。

    ![スレッドの移動](./moderating-message-boards/images/03.png)

1. _［Move］_をクリックしてスレッドを移動します。

これでスレッドは新しいカテゴリに分類されました。

<a name="ユーザーの禁止" />

## ユーザーの禁止

1. 禁止するユーザーを含むスレッドをクリックします。
1. 投稿のアクションメニュー（![Actions](../../../images/icon-actions.png)）をクリックし、_［Ban this User］_を選択します。

![ユーザーを禁止する](./moderating-message-boards/images/04.png)

ユーザーが禁止されました。

禁止されたユーザーを元に戻すには：

1. _サイト管理_ に移動します。
1. _［コンテンツ & データ］_ &rarr; _［掲示板］_の順にクリックします。 （ページスコープがある場合は、_スコープセレクター_から範囲を選択します。）
1. _［Banned Users］_タブをクリックします。
1. ユーザーの横にあるアクションメニュー（Actions）をクリックし、_［Unban This User］_をクリックします。

    ![ユーザーの禁止を解除する](./moderating-message-boards/images/05.png)

このユーザーが元に戻されました。

<a name="フラグが付いたスレッドの確認" />

## フラグが付いたスレッドの確認

スレッドが不正使用の可能性があると報告された場合、_掲示板_アプリは_［Notifications］_ウィジェットを使用して、モデレーターにスレッドの確認を通知します。 赤い数字がプロフィールアイコンの周りに表示されます。

![通知アイコン](./moderating-message-boards/images/01.png)

フラグの付いたスレッドを確認するには：

1. _ユーザーのアバター_をクリックします 。

1. _［Notifications］_をクリックします。

    ![［Notifications］ウィジェットにメッセージボードが表示されます](./moderating-message-boards/images/06.png)

1. 報告された返信をクリックします。

モデレーターには、フラグの付いた返信を削除するオプションがあります。 フラグが付いた返信を削除するには：

1. _アクション_（![Actions](../../../images/icon-actions.png)）をクリックします

    ![フラグの付いた返信を削除する](./moderating-message-boards/images/07.png)

1. _［Delete］_ をクリックします
1. _［OK］_をクリックして削除を確認します。

これで返信が削除されました。

<a name="追加情報" />

## 追加情報

* [通知とリクエストの管理](../../notifications-and-requests/user-guide/managing-notifications-and-requests.md)
