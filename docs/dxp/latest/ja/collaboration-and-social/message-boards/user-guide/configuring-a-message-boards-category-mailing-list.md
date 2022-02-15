# 掲示板カテゴリのメーリングリストの設定

この記事では、 **サイトスコープ** または **ページスコープの掲示板** で掲示板のカテゴリのメーリングリストを設定する方法について説明します。 スコープの使用方法については、 [メッセージボードのスコープ](./scoping-your-message-boards.md) の記事を参照してください。 グローバルスコープの掲示板は、サブスクリプションとメーリングリストをサポートしていません。 掲示板の全てのカテゴリに独自のメーリングリストを作成できます。

<a name="前提条件" />

## 前提条件

掲示板アプリのメーリングリストを設定する前に、[Connecting to a Mail Server](../../../installation-and-upgrades/setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md)の記事を参照し、Liferay DXPインスタンス全体にメールサーバーを設定する一般的な手順について確認してください。

<a name="掲示板カテゴリにメーリングリストを追加する" />

## 掲示板カテゴリにメーリングリストを追加する

掲示板カテゴリにメーリングリストを追加するには：

1. ［**Message Boards**］ ウィジェットで、カテゴリ（この例では **Category 1**）の隣にある **アクション**（![Actions](../../../images/icon-actions.png)）アイコンをクリックします。

    ![カテゴリの編集](./configuring-a-message-boards-category-mailing-list/images/02.png)

1. 既存のカテゴリを変更するには、 ［**Edit**］ をクリックします。
1. ［**Mailing List**］ セクションを展開します。
1. ［**Active**］ トグルを ［**YES**］ に切り替えます。
1. ［**Allow Anonymous Emails**］ トグルは ［**NO**］ のままにします。

    ![カテゴリメーリングリストの設定](./configuring-a-message-boards-category-mailing-list/images/01.png)

1. 次のように入力します：

   ***Email address** ：このカテゴリの専用メールアドレスまたはエイリアス（例：replies@lunar-resort.com）
   ***Protocol** ：POP
   ***Server Name** ：メールサーバーのホスト名
   ***Server Port** ：メールサービスが実行されているポート（110）
   ***Use a Secure Network Connection** ：該当する場合はオンにします
   ***User Name** ：info@lunar-resort.com
   ***Password** ： *****
   ***Read Interval (Minutes**) ：5
   ***Email Address (Outgoing**) ：ユーザーがメールを使用してカテゴリに返信するようにする場合は、同じメールを使用します
   ***Use Custom Outgoing Server** ：グローバルデフォルト以外のメールサーバーを使用する場合は空白のままにします

1. ［**保存**］ をクリックします。

メーリングリストがこのカテゴリでアクティブになりました。 このカテゴリに登録したユーザーは、カテゴリ内の新しいスレッドに関する電子メール通知を受け取るようになりました。

```{important}
   カテゴリのメーリングリストにIMAP <https://support.google.com/mail/answer/7126229?hl=en>プロトコルを使用している場合は、メッセージをメーリングリストのユーザーに送信する電子メールクライアントによってメッセージがプルされたときにメッセージ<https://support.google.com/mail/answer/78892?hl=en>が削除されるようにIMAP受信トレイを設定してください。 そうしないと、サーバーに保持されている各電子メールメッセージは、カテゴリに新しい投稿や更新があるたびにメーリングリストに送信されます。
```

<a name="関連情報" />

## 関連情報

* [Connecting to a Mail Server](../../../installation-and-upgrades/setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md)
* [掲示板カテゴリの作成](./creating-message-boards-categories.md)
