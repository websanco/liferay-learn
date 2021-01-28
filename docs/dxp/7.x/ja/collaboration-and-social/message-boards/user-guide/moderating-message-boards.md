# メッセージボードのモデレート

*メッセージボード*には、フォーラムを効果的にモデレートできる機能が含まれています。 これには、スレッドのロック、スレッドの移動とマージ、ユーザーの禁止、スレッドのフラグ付けが含まれます。 適切に指定された権限を持つユーザーは、これらのツールを使用できます。 *メッセージボード*権限をロールに割り当てる詳細については、[Message Boards Permissions Reference](./message-boards-permissions-reference.md)を参照してください。 一般的なロールと権限の詳細については、[Roles and Permissions](https://help.liferay.com/hc/articles/360017895212-Roles-and-Permissions)を参照してください 。

## スレッドのロック

サイト管理者は、スレッドの[Actions]メニューから*[Lock]* を選択して、スレッドのアクティビティを停止できます。 スレッドがロックされると、追加の応答は許可されません。

スレッドをロックするには：

1.  ロックするスレッドの横にある（![Actions](../../../images/icon-actions.png)）をクリックします。

2.  *[Lock]* をクリックします。

    ![スレッドのロック](./moderating-message-boards/images/02.png)

スレッドのロックを解除するには：

1.  ロックを解除するスレッドの横にある（![Actions](../../../images/icon-actions.png)）をクリックします。
2.  *[Unlock]* をクリックします。

スレッドのロックが解除されました。

## スレッドの移動

モデレーターは、スレッドをあるカテゴリから別のカテゴリに移動できます。 スレッドを移動するには、次の手順に従います。

1.  スレッドのアクションメニュー（![Actions](../../../images/icon-actions.png)）から*[Move]* を選択します。

2.  *[Select]* ボタンをクリックして、新しいカテゴリを選択します。

3.  移動を説明する投稿を追加するには、*[Add explanation post]* をオンにします。

    ![スレッドの移動](./moderating-message-boards/images/03.png)

4.  *[Move]* をクリックしてスレッドを移動します。

これでスレッドは新しいカテゴリに分類されました。

## ユーザーの禁止

1.  禁止するユーザーを含むスレッドをクリックします。
2.  投稿のアクションメニュー（![Actions](../../../images/icon-actions.png)）をクリックし、*[Ban this User]* を選択します。

![ユーザーを禁止する](./moderating-message-boards/images/04.png)

ユーザーが禁止されました。

禁止されたユーザーを元に戻すには：

1.  *サイト管理* に移動します。

2.  *[Content & Data]* → *[Message Boards]* の順にクリックします。 （ページスコープがある場合は、*スコープセレクター*から範囲を選択します。）

3.  *[Banned Users]* タブをクリックします。

4.  ユーザーの横にあるアクションメニュー（Actions）をクリックし、*[Unban This User]* をクリックします。

    ![ユーザーの禁止を解除する](./moderating-message-boards/images/05.png)

このユーザーが元に戻されました。

## フラグが付いたスレッドの確認

スレッドが不正使用の可能性があると報告された場合、*メッセージボード*アプリは*[Notifications]* ウィジェットを使用して、モデレーターにスレッドの確認を通知します。 赤い数字がプロフィールアイコンの周りに表示されます。

![通知アイコン](./moderating-message-boards/images/01.png)

フラグの付いたスレッドを確認するには：

1.  *ユーザーのアバター*をクリックします 。

2.  *[Notifications]* をクリックします。

    ![[Notifications]ウィジェットにメッセージボードが表示されます](./moderating-message-boards/images/06.png)

3.  報告された返信をクリックします。

モデレーターには、フラグの付いた返信を削除するオプションがあります。 フラグが付いた返信を削除するには：

1.  *アクション*（![Actions](../../../images/icon-actions.png)）をクリックします

    ![フラグの付いた返信を削除する](./moderating-message-boards/images/07.png)

2.  *[Delete]* をクリックします

3.  *[OK]* をクリックして削除を確認します。

これで返信が削除されました。

## 追加情報

  - [Managing Notifications and Requests](../../notifications-and-requests/user-guide/managing-notifications-and-requests.md)
