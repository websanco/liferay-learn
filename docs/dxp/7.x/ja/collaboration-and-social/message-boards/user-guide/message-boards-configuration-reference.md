# メッセージボード設定リファレンス

この記事では、*メッセージボード*アプリで使用できる構成設定について説明します。 *メッセージボード*アプリの[Configuration]画面を表示するには：

1.  *[Site Administration]* → *[Content & Data]* → *[Message Boards]* に移動します。
2.  *[Options]* メニュー → *[Configuration]* の順にクリックします。

## [General]タブ

![[General]タブ](./message-boards-configuration-reference/images/01.png)



| 名前                                  | 説明                                                                                       |
| ----------------------------------- | ---------------------------------------------------------------------------------------- |
| Allow Anonymous Posting             | ゲスト（認証されていない）ユーザーがスレッドを投稿できるようにします。                                                      |
| Subscribe by Default                | デフォルトで、ユーザーが参加しているスレッドにユーザーをサブスクライブします。                                                  |
| Message Format                      | エディターとしてBBCodeまたはHTMLを選択するためのドロップダウンメニュー                                                 |
| Enable Report Inappropriate Content | ユーザーが利用規約違反のスレッドを報告できるようにします <!-- ここにサイトのToSを定義する方法についての記事へのリンクを作成すると便利です--> |
| Enable Ratings                      | ユーザーがスレッドを評価できるようにします                                                                    |
| Thread as Question by Default       | 有効にすると、最初のスレッドは常に質問になり、後続のスレッドを回答としてマークできます。デフォルトでは無効になっています                             |
| Show Recent Posts from Last         | 時間に基づいて以前の投稿の表示数を選択するためのドロップダウンメニュー（*[24 Hours]*、*[7 Days]*、*[30 Days]*、または*[365 Days]*） |

## Email From

![[Email From]タブ](./message-boards-configuration-reference/images/02.png)

| 名前          | 説明                         |
| ----------- | -------------------------- |
| Name        | フォーラムの所有者またはモデレーターの名前      |
| Address     | フォーラムの所有者またはモデレーターのメールアドレス |
| HTML Format | メールの形式を選択するためのチェックボックス     |

各スレッドからデータとメタデータをインポートする自動メールを作成する方法の詳細については、*[Definition of Terms]* メニューを展開してください。

![[Email From]の用語の定義](./message-boards-configuration-reference/images/08.png)

## Message Added Email

*[Message Added Email]* タブには、メッセージボードのスレッドが作成されるたびに送信される電子メールのテンプレートが含まれています。

![Message Added Email](./message-boards-configuration-reference/images/03.png)

変数を使用して各スレッドからデータとメタデータをインポートする自動メールを作成する方法の詳細については、*[Definition of Terms]* メニューを展開してください。

![[Message Added Email]の用語の定義](./message-boards-configuration-reference/images/09.png)

## Message Updated Email

*[Message Added Email]* タブには、メッセージボードのスレッドがアップデートされるたびに送信される電子メールのテンプレートが含まれています。

![Message Updated Email](./message-boards-configuration-reference/images/04.png)

各スレッドからデータとメタデータをインポートする自動メールを作成する方法の詳細については、*[Definition of Terms]* メニューを展開してください。

![[Message Added Email]の用語の定義](./message-boards-configuration-reference/images/09.png)

## Thread Priorities

![Message Updated Email](./message-boards-configuration-reference/images/05.png)

| 名前                 | 説明                                                   |
| ------------------ | ---------------------------------------------------- |
| Default Language   | インスタンスのデフォルト言語は英語に設定されており、ここでは変更できません。               |
| Localized Language | サポートされているすべての言語のドロップダウンメニュー。管理者は異なる言語で異なるランクを入力できます。 |
| Name               | 優先度レベルの名前                                            |
| Image              | 優先度レベルに関連付けられたアイコンの名前。完全なURLまたはテーマに関連するパスを指定できます     |
| Priority           | スレッドの優先度レベル。番号が大きいスレッドほど重要です。降順で入力してください。            |

## User Ranks

![User Ranks](./message-boards-configuration-reference/images/06.png)

メッセージボードのユーザーには、投稿数に応じたランクを表示するように構成できます。

| 名前                   | 説明                                                   |
| -------------------- | ---------------------------------------------------- |
| Default Language     | インスタンスのデフォルト言語は英語に設定されており、ここでは変更できません。               |
| Localized Language   | サポートされているすべての言語のドロップダウンメニュー。管理者は異なる言語で異なるランクを入力できます。 |
| User Ranks Text Area | ユーザー定義のランクが入力されるテキスト領域                               |

## RSS

![RSS](./message-boards-configuration-reference/images/07.png)

| 名前                       | 説明                                                  |
| ------------------------ | --------------------------------------------------- |
| Enable RSS Subscription  | RSSサブスクリプションを有効または無効にするトグル                          |
| Maximum Items to Display | RSSフィードのスレッドの最大数を選択するためのドロップダウンメニュー                 |
| Display Style            | スレッドの表示方法を要約、完全なコンテンツ、またはタイトルのみから選択するためのドロップダウンメニュー |
| Format                   | 形式をAtom 1.0、RSS 1.0、またはRSS 2.0から選択するためのドロップダウンメニュー  |
