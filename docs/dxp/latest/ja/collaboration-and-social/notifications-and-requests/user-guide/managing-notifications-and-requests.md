# 通知とリクエストの管理

ユーザーは、ブログやメッセージボードをサブスクライブしている場合や、投稿にメンションされた場合、通知とリクエストを受け取ります。 また、プライベートメッセージ、サイトへの参加の招待、またはイベントのリマインダーが送信された場合にも通知されます。 最後に、Liferayの組み込みの[ワークフロー](../../../process-automation/workflow/user-guide/introduction-to-workflow.md)機能を使用してコンテンツやアセットの作成といったアクティビティが使用された場合、それらを確認するようにユーザーに通知されます。

通知とリクエストにアクセスするには：

1.  アバターをクリックして、*[Notifications]* を選択します 。

    ![ユーザーのアバターと通知](./managing-notifications-and-requests/images/01.png)

2.  *[Notifications List]* タブには、未読の通知がすべて表示されます。 ここで、ユーザーはメッセージボードのコメントにメンションされました。

    ![ユーザーのアバターと通知](./managing-notifications-and-requests/images/02.png)

## 通知の管理

蓄積された通知を管理およびソートするには：

1.  *[Filter and Order]* ドロップダウンメニューをクリックします。

2.  以下から選択します。

      - **All**：デフォルトのオプション。 既読と未読の両方の通知を表示します。

      - **Unread**：既読としてマークされていない通知を表示します。 未読の通知は、通知の左側にある青い境界線で示されます。

      - **Read**：既読としてマークされた通知を表示します。

      - **Date**：通知を日付順に並べ替えます。

        ![[Filter and Order]メニュー](./managing-notifications-and-requests/images/03.png)

デフォルトでは、通知は日付の降順で一覧表示されます。 通知を昇順で並べ替えるには、管理バーの上下矢印アイコンをクリックします。

通知のステータスを変更するには：

1.  通知の横にある*アクション*メニュー（![Actions](../../../images/icon-actions.png)）をクリックします。

    ![通知ステータス](./managing-notifications-and-requests/images/04.png)

2.  *[Mark as Read]* または*[Mark as Unread]* をクリックして、通知を最新の状態に保ちます。

3.  または、*[Delete]* をクリックして通知を完全に削除します。

### 複数の通知の管理

複数の通知を一度に管理することもできます。

1.  *[Filter and Order]* ドロップダウンメニューの横にあるチェックボックスを選択します。 これにより、すべての通知メッセージが選択されます。

2.  いくつかのアイコンが表示されます：

      - **既読としてマーク**（![Open Envelope](./managing-notifications-and-requests/images/icon-envelope-open.png)）
      - ** 	未読としてマーク**（![Closed Envelope](./managing-notifications-and-requests/images/icon-envelope-closed.png)）
      - **削除**（![Delete Button](./managing-notifications-and-requests/images/icon-delete.png)）

    ![複数の通知ステータスの管理](./managing-notifications-and-requests/images/05.png)

## サイトへの参加リクエストの管理

ユーザーがサイトへの参加リクエストを受け取ると、そのリクエストが*[Requests List]* タブに表示されます。 ユーザーは*[Confirm]* をクリックしてサイトに参加するか、*[Ignore]* をクリックして辞退することができます。

![複数の通知ステータスの管理](./managing-notifications-and-requests/images/06.png)

## 追加情報

  - [Mentioning Users](./mentioning-users.md)
