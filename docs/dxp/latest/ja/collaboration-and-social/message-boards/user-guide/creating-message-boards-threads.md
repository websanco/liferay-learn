# 掲示板スレッドの作成

デフォルトでは、必要な権限（少なくとも、 **メッセージの追加** 、 **メッセージへの返信** 、 **ファイルの追加** 、および **表示**）を持つ認証されたユーザーのみがスレッドを作成できます。 詳細は、 [掲示板の権限リファレンス](./message-boards-permissions-reference.md) を参照してください。 管理者は、ゲストがスレッドを投稿できるようにする機能を有効にすることができます。

<a name="posting-a-new-thread" />

## 新しいスレッドを投稿する

スレッドは、掲示板自体のルートレベルと、[作成されたカテゴリ](./creating-message-boards-categories.md)内に作成できます。

新規スレッドを作成するには：

1. 掲示板の ［**新規スレッド**］ ボタンをクリックします。 ［**Add Message**］ フォームが表示されます。
1. ［**Subject**］ フィールドにタイトルを入力します。
1. ［**Body**］ フィールドでスレッドのコンテンツを作成します。

    ```{note}
      This field uses the same editor as the Blogs app, except that it uses BBCode instead of HTML. For further instructions, see the documentation on [using the editor](https://help.liferay.com/hc/articles/360018173051-Using-the-Blog-Entry-Editor-) .
    ```

    ![図1. 最初の投稿を作成する](./creating-message-boards-threads/images/01.png)

### 添付ファイルをアップロードする

ユーザーは、フォーラムの投稿にファイルを添付できます。

1. 添付ファイルを追加するには、 ［**Attachments**］ セクションを展開します。
1. ファイルをドラッグアンドドロップしてアップロードするか、 ［**Select Files**］ ボタンを使用してファイルの場所に移動します。

    ![図2. 添付ファイルをアップロードする](./creating-message-boards-threads/images/03.png)

```{important}
   You can enable automatic antivirus scanning to scan files on upload. For details, please see [アップロードされたファイルのウイルス対策スキャンを有効にする](../../../system-administration/file-storage/enabling-antivirus-scanning-for-uploaded-files.md) .
```

### **タグ** と **関連アセット** を使用してスレッドを整理する

**カテゴリ** を使用してスレッドを整理する以外に、ユーザーはフォーラム投稿にタグを追加できます。 このタグを使用してコンテンツを検索すると（たとえば、 **Toyota**）、 **Toyota** を含むすべての投稿がより速く返されます。

1. ［**Categorization**］ セクションを展開します。
1. ［**Select**］ ボタンをクリックして、既存のタグを選択します。 または、 ［**Tags**］ フィールドにタグ名を入力して ［**追加**］ をクリックし、新しいタグを作成します。 詳細は、 [タグに関するドキュメント](https://help.liferay.com/hc/articles/360028820472-Tagging-Content) を参照してください。

掲示板の投稿にタグが付きました。

掲示板スレッドは、DXP内の他の既存のアセットにリンクできます。

1. ポータルに存在するアセット（メディアファイル、ブログ投稿など）を選択してスレッドに関連付けるには、 ［**Related Assets**］ セクションを展開し、 ［**Select**］ ボタンを使用してそのアセットを選択します。

    ![図3. タグと関連アセットを追加する](./creating-message-boards-threads/images/04.png)

### スレッドの優先順位を設定する

メッセージボードのスレッドは、 ［**Urgent**］ 、 ［**Sticky**］ 、または ［**Announcement**］ の優先順位を付けることができます。 デフォルトでは、「None」に設定されています。

1. ［**More Settings**］ セクションを展開します。 <!-- Broken Image Link !\[Figure 4. Setting a thread priority\](./creating-message-boards-threads/images/07.png) -->1. スレッドの優先度を選択します。
1. ［**公開**］ をクリックします。

新しい投稿が作成されました。 サイト管理者は、後でこの投稿を別のカテゴリに移動することができます。

<a name="using-threads-as-questions" />

## スレッドを質問として使用する

掲示板スレッドは、アイデアや意見を共有するだけのものではありません。 フォーラムのメンバーは、自分の投稿を他のサイトメンバーへの質問に変えることができます。 質問に対し最適な回答をした返信を回答としてマークできます。

スレッドの投稿を質問に変えるには：

1. ［**New Thread**］ ボタンをクリックします。
1. ［**More Settings**］ セクションを展開します。
1. ［**Mark as a Question**］ チェックボックスをオンにします。

    ![図5. スレッドを質問としてマークする](./creating-message-boards-threads/images/05.png)

返信を回答として選択するには：

1. 3ドットアイコンをクリックします。
1. ［**Mark as an Answer**］ をクリックします。

    ![図6. 返信を、メッセージボードの質問に対する回答としてマークできます。](./creating-message-boards-threads/images/02.png)

1. ［**公開**］ をクリックして、スレッドを公開します。

<a name="message-board-thread-replies" />

## 掲示板スレッドへの返信

表示するスレッドをクリックします。 メッセージはスレッドビューに表示されるため、返信は親スレッドの下に配置されます。 これにより、会話を簡単に追跡できます。 スレッドへの返信は、親スレッドの下にインデントされます。

![図7：スレッドのビューには、スレッドの作成者情報とスレッドの内容、そのスレッドへのすべての返信が表示されます。](./creating-message-boards-threads/images/06.png)

スレッド内のメッセージに返信するには：

1. ［**返信**］ ボタンをクリックします。 クイック返信フォームが開き、返信を入力するためのテキストフィールドだけが表示されます。
1. テキストフィールドに返信を入力します。 返信のその他のオプションにアクセスするには、 ［**詳細な返信**］ リンクをクリックします。 スレッドの追加/編集フォームから完全なエディターが開きます。
1. ［**公開**］ をクリックします。

メッセージに返信するだけでなく、メッセージを評価したり、好ましくないメッセージとしてフラグを付けたりすることができます。 掲示板のモデレーターは、フラグが設定されたメッセージを評価し、メッセージとその作成者の処理方法を決定できます。

<a name="additional-information" />

## 追加情報

* [掲示板カテゴリの作成](./creating-message-boards-categories.md)

<!-- Is there a placeholder for an article on "Enabling User Mentions for Collaboration Applications"? This should be link to a placeholder.
* User can [mention other users](https://help.liferay.com/hc/en-us/articles/360028720892-Mentioning-Users) by entering the `@` character and their user name.
-->
* [掲示板設定リファレンス](./message-boards-configuration-reference.md)
