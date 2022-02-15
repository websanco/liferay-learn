# 掲示板のモデレート

**掲示板** には、フォーラムを効果的にモデレートできる機能が含まれています。 これには、スレッドのロック、スレッドの移動とマージ、ユーザーの禁止、スレッドのフラグ付けが含まれます。 適切に指定された権限を持つユーザーは、これらのツールを使用できます。 **掲示板** 権限をロールに割り当てる詳細は、 [掲示板の権限リファレンス](./message-boards-permissions-reference.md) を参照してください。 一般的なロールと権限の詳細は、 [Roles and Permissions](https://help.liferay.com/hc/articles/360017895212-Roles-and-Permissions) を参照してください 。

<a name="locking-threads" />

## スレッドのロック

サイト管理者は、スレッドのアクションメニューから ［**Lock**］ を選択して、スレッドのアクティビティを停止できます。 スレッドがロックされると、追加の応答は許可されません。

スレッドをロックするには：

1. ロックするスレッドの横にある（![Actions](../../../images/icon-actions.png)）をクリックします。
1. ［**Lock**］ をクリックします。

    ![スレッドのロック](./moderating-message-boards/images/02.png)

スレッドのロックを解除するには：

1. ロックを解除するスレッドの横にある（![Actions](../../../images/icon-actions.png)）をクリックします。
1. ［**Unlock**］ をクリックします。

スレッドのロックが解除されました。

<a name="moving-threads" />

## スレッドの移動

モデレーターは、スレッドをあるカテゴリから別のカテゴリに移動できます。 スレッドを移動するには、次の手順に従います。

1. スレッドのアクションメニュー（![Actions](../../../images/icon-actions.png)）から ［**Move**］ を選択します。
1. ［**Select**］ ボタンをクリックして、新しいカテゴリを選択します。
1. 移動を説明する投稿を追加するには、 ［**Add explanation post**］ をオンにします。

    ![スレッドの移動](./moderating-message-boards/images/03.png)

1. ［**Move**］ をクリックしてスレッドを移動します。

これでスレッドは新しいカテゴリに分類されました。

<a name="banning-users" />

## ユーザーの禁止

1. 禁止するユーザーを含むスレッドをクリックします。
1. 投稿のアクションメニュー（![Actions](../../../images/icon-actions.png)）をクリックし、 ［**Ban this User**］ を選択します。

![ユーザーを禁止する](./moderating-message-boards/images/04.png)

ユーザーが禁止されました。

禁止されたユーザーを元に戻すには：

1. **サイト管理** に移動します。
1. ［**コンテンツ & データ**］ &rarr; ［**掲示板**］ の順にクリックします。 （ページスコープがある場合は、 **スコープセレクター** から範囲を選択します。）
1. ［**Banned Users**］ タブをクリックします。
1. ユーザーの横にあるアクションメニュー（Actions）をクリックし、 ［**Unban This User**］ をクリックします。

    ![ユーザーの禁止を解除する](./moderating-message-boards/images/05.png)

このユーザーが元に戻されました。

<a name="reviewing-flagged-threads" />

## フラグが付いたスレッドの確認

スレッドが不正使用の可能性があると報告された場合、 **掲示板** アプリは ［**Notifications**］ ウィジェットを使用して、モデレーターにスレッドの確認を通知します。 赤い数字がプロフィールアイコンの周りに表示されます。

![通知アイコン](./moderating-message-boards/images/01.png)

フラグの付いたスレッドを確認するには：

1. **ユーザーのアバター** をクリックします 。

1. ［**Notifications**］ をクリックします。

    ![［Notifications］ウィジェットにメッセージボードが表示されます](./moderating-message-boards/images/06.png)

1. 報告された返信をクリックします。

モデレーターには、フラグの付いた返信を削除するオプションがあります。 フラグが付いた返信を削除するには：

1. **アクション**（![Actions](../../../images/icon-actions.png)）をクリックします

    ![フラグの付いた返信を削除する](./moderating-message-boards/images/07.png)

1. ［**Delete**］ をクリックします
1. ［**OK**］ をクリックして削除を確認します。

これで返信が削除されました。

<a name="additional-information" />

## 追加情報

* [通知とリクエストの管理](../../notifications-and-requests/user-guide/managing-notifications-and-requests.md)
