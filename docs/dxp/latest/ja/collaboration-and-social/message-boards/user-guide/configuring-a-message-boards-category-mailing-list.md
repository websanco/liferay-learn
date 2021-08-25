# メッセージボードカテゴリのメーリングリストの設定

この記事では、**サイトスコープ**または**ページスコープのメッセージボード**でメッセージボードカテゴリのメーリングリストを設定する方法について説明します。 スコープの使用方法については、[Scoping Your Message Boards](./scoping-your-message-boards.md)の記事を参照してください。 グローバルスコープのメッセージボードは、サブスクリプションとメーリングリストをサポートしていません。 メッセージボードの任意のカテゴリに独自のメーリングリストを作成できます。

## 前提条件

メッセージボードアプリのメーリングリストを設定する前に、[Connecting to a Mail Server](../../../installation-and-upgrades/setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md)の記事を参照し、DXPインスタンス全体にメールサーバーを設定する一般的な手順について確認してください。

## メッセージボードカテゴリにメーリングリストを追加する

メッセージボードカテゴリにメーリングリストを追加するには：

1.  *[Message Boards]* ウィジェットで、カテゴリ（この例では*Category 1*）の隣にある*アクション*（![Actions](../../../images/icon-actions.png)）アイコンをクリックします。

    ![カテゴリの編集](./configuring-a-message-boards-category-mailing-list/images/02.png)

2.  既存のカテゴリを変更するには、*[Edit]* をクリックします。

3.  *[Mailing List]* セクションを展開します。

4.  *[Active]* トグルを *[YES]* に切り替えます。

5.  *[Allow Anonymous Emails]* トグルは *[NO]* のままにします。

    ![カテゴリメーリングリストの設定](./configuring-a-message-boards-category-mailing-list/images/01.png)

6.  次のように入力します：

      - **Email address**：このカテゴリの専用メールアドレスまたはエイリアス（例：<replies@lunar-resort.com>）
      - **Protocol**：POP
      - **Server Name**：メールサーバーのホスト名
      - **Server Port**：メールサービスが実行されているポート（110）
      - **Use a Secure Network Connection**：該当する場合はオンにします
      - **User Name**：<info@lunar-resort.com>
      - **Password**：\*\*\*\*\*
      - **Read Interval (Minutes)**：5
      - **Email Address (Outgoing)**：ユーザーがメールを使用してカテゴリに返信するようにする場合は、同じメールを使用します
      - **Use Custom Outgoing Server**：グローバルデフォルト以外のメールサーバーを使用する場合は空白のままにします

7.  *[保存]* をクリックします。

メーリングリストがこのカテゴリでアクティブになりました。 このカテゴリに登録したユーザーは、カテゴリ内の新しいスレッドに関する電子メール通知を受け取るようになりました。

``` important::
   カテゴリのメーリングリストにIMAP <https://support.google.com/mail/answer/7126229?hl=en>プロトコルを使用している場合は、メッセージをメーリングリストのユーザーに送信する電子メールクライアントによってメッセージがプルされたときにメッセージ<https://support.google.com/mail/answer/78892?hl=en>が削除されるようにIMAP受信トレイを設定してください。 そうしないと、サーバーに保持されている各電子メールメッセージは、カテゴリに新しい投稿や更新があるたびにメーリングリストに送信されます。
```

## 関連情報

  - [Connecting to a Mail Server](../../../installation-and-upgrades/setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md)
  - [Creating Message Board Categories](./creating-message-boards-categories.md)
