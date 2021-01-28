# フォームの作成

*フォーム*アプリケーションを使用すると、ユーザーはマルチフィールドフォームを作成できます。 必要な[プロセス自動化権限](./forms-permissions-reference.md)を持つ認証されたユーザーのみがフォームを作成できます。 （少なくとも、*サイト管理*メニューと*フォーム*アプリケーションにアクセスできる必要があります。）DXPのロールと権限の詳細については、[Roles and Permissions](https://help.liferay.com/hc/articles/360017895212-Roles-and-Permissions)を参照してください。

![デフォルトでは、フォームはリスト形式で表示されます。](./creating-forms/images/01.png)

## フォームの作成

以下のサンプルは、ホテルのゲスト用のフィードバック調査フォームです。

1.  *製品メニュー*（![Product Menu](../../../images/icon-product-menu.png)）を開き、*サイト管理*メニューのコンパスアイコン（![Compass](../../../images/icon-compass.png)）をクリックします。 フォームを作成するサイトを選択します。

2.  *[Content & Data]* → *[Forms]* の順にクリックします。

3.  *追加*ボタン（![Add](../../../images/icon-add.png)）をクリックします。 フォームビルダービューが表示されます。

4.  フォームの名前を入力します（**Guest Feedback Survey**）。

5.  簡単な説明を入力します。

6.  ページ名を入力します。 入力しないと、デフォルト値*Untitled Page (1 of X)*が使用されます。

7.  *追加*ボタン（![Add](../../../images/icon-add.png)）をクリックして、*[Add Elements]* サイドバーを表示します（まだ開いていない場合）。

    ![フォーム作成フォームでは、9つのフィールドタイプから選択できます。](./creating-forms/images/02.png)

8.  *[Select from List]* フィールドをフォームビルダーにドラッグします。

9.  次の値を入力します。

      - **Label**：*訪問したホテルを評価してください。*
      - **Help Text**：現時点では空白のままにします。 フィールドの小見出しに追加のガイダンスを提供する場合は、これが役立ちます。
      - **[Required Field]**セレクターでトグルを*[YES]* に切り替えます。
      - 選択リストのフォーム作成では手動オプションをオンのままにします。 データプロバイダーを使用してフィールドに入力する方法については、[Data Providers](./advanced-forms-usage/using-data-providers-to-populate-form-options.md)の記事をご覧ください。

10. *[Options]* セクションに、アンケートの質問の値を入力します。

      - **Excellent**
      - **Good**
      - **Neutral**
      - **Bad**
    
    <!-- end list -->

    ``` note::
       いずれかのフィールドに入力すると、自動的に別の空白の選択行が追加されます。 完了したら、最後の1つは空白にしておいてください。
    ```

    ![リストオプションから選択](./creating-forms/images/03.png)

11. テキストフィールドなどの要素を追加するには、*[Select from List]* 要素の下に*テキストフィールド*要素をドラッグアンドドロップします。

12. *テキストフィールド*の*[Basic]* タブで、次のように入力します。

      - **Label**：*コメント*
      - **Help Text**：空白のままにします。
      - **Field Type**：長文のコメントを許可するには、*[Multiple Lines]* ラジオボタンをクリックします。
      - **Required Field：**トグルを*[NO]* のままにします。

13. サイドバーを閉じます。

14. *[Save Form]* をクリックして、フォームをドラフトとして保存します。

### ページを追加する

フォームに複数のページが必要な場合、Liferayの*フォーム*は複数ページのフォームに対応しています。

フォームに後続のページを追加するには：

1.  フォームビルダービューに移動します。

2.  フォームの右上隅にある*アクション*ボタン(![Actions](../../../images/icon-actions.png))をクリックします。

3.  *[Add New Page]* をクリックします。

    ![[Page Actions]メニューから、新しいページを追加したり、現在のページをリセットしたりできます。](./creating-forms/images/06.png)

4.  ページ番号をクリックします。

5.  新しいページで、ページ名を入力します（たとえば、*Page 2* ）。 入力しないと、*Untitled Page (2 of 2)*のままになります。

6.  追加の要素をドラッグアンドドロップします。

    ![2ページ目を追加。](./creating-forms/images/07.png)

### 成功ページの追加

非常に便利なページの1つが、ユーザーがフォームの最後に到達したこと、およびフォームが正常に送信されたことをユーザーに通知する*成功ページ*です。

*成功ページ*を追加するには：

1.  フォームの右上隅にある*アクション*ボタン(![Actions](../../../images/icon-actions.png))をクリックします。

2.  *[Add Success Page]* をクリックします。

3.  デフォルトの*[Title]* および*[Content]* フィールドの値を変更します。 *成功ページ*の名前は変更できないことに注意してください。

    ![成功ページの追加](./creating-forms/images/08.png)

### フォームの公開

フォームに入力したら、ユーザーが回答の送信を開始できるようにフォームを公開する必要があります。 *[Publish Form]* をクリックして公開します。

フォームを公開すると、ユーザーに送信できるフォーム入力用のURLが生成されます。

![フォームが公開されると、共有できるURLが生成されます。](creating-forms/images/11.png)

詳細については、[Sharing Forms](./sharing-forms.md)を参照してください。

## フォームの複製

フォームを複製して、ゼロから作成することなく、同様のフォームを作成できます。 フォームを複製するには、次の手順に従います。

1.  *[Site Administration]* → *[Content & Data]* → *[Forms]* に移動します。

2.  元のフォームの横にある*アクション*ボタン（![Actions](../../../images/icon-actions.png)）をクリックします。

    ![フォームの複製](./creating-forms/images/10.png)

3.  *[Duplicate]* をクリックします。

これで、元の調査フォームのコピーが生成されます。 ユーザーは複製フォームの変更を開始できます。

![複製フォームの生成](./creating-forms/images/05.png)

## 次のステップ

  - [Sharing Forms](./sharing-forms.md)
  - [Managing Form Entries](./managing-form-entries.md)
  - [テキストおよび数値フィールドエントリの検証](./advanced-forms-usage/validating-text-and-numeric-field-entries.md)
  - [フォーム送信でのCAPTCHAの有効化](./advanced-forms-usage/enabling-captcha-on-form-submissions.md)
