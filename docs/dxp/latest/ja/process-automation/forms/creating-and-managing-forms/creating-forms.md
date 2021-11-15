# フォームの作成

*フォーム*アプリケーションで、複数フィールドのフォームを作成することができます。 必要な[プロセス自動化権限](./forms-permissions-reference.md)を持つ認証されたユーザーのみがフォームを作成できます。 最低でも、 *サイト管理*メニューと*フォーム*アプリケーションにアクセスする権限を持っている必要があります。 DXPのロールと権限の詳細は、[ロールと権限](../../../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)をご覧ください 。

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
      - 選択リストのフォーム作成では手動オプションをオンのままにします。 データプロバイダーを使用してフィールドに入力する方法については、[Data Providers](../data-providers/using-data-providers-to-populate-form-options.md)を参照してください。

10. *[Options]* セクションに、アンケートの質問の値を入力します。

      - **Excellent**
      - **Good**
      - **Neutral**
      - **Bad**
    
    <!-- end list -->
    
    ```{note}
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

### フォームページの追加

フォームに複数のページが必要な場合、Liferayのフォームは複数ページのフォームに対応しています。

フォームページを追加するには

1.  フォームビルダービューに移動します。

2.  最初のフォームページの下部にある*[新規ページ]* ボタンをクリックします。

    ![フォームページを追加することができます。](./creating-forms/images/06.png)

3.  新しいフォームページは、フォームビルダーの下部に追加されます（ただし、成功ページの前）。

4.  ページ名を入力します。

5.  追加の要素をドラッグアンドドロップします。

6.  必要に応じてさらにページを追加します。

Liferay 7.3では、既存のフォームページを並び替えることができます。 フォームビルダーでは、ページの右隅にある上下の矢印をクリックします。

![フォームページの並び替えができます。](./creating-forms/images/13.png)

### 成功ページの追加

*成功ページ*は、フォームの送信者に、フォームの終わりに到達したことや、フォームが正常に送信されたことを知らせることで、解決策を提供します。

デフォルトの成功ページは使いやすくなっています。

![デフォルトの成功ページでは、フォーム送信が成功したことが明確に示されます。](./creating-forms/images/08.png)

成功ページが不要な場合は、デフォルトの成功ページのアクション（![Actions](../../../images/icon-actions.png)）ボタンをクリックし、*[Remove Success Page]* を選択します。

成功ページをカスタマイズするには、

1.  タイトルフィールド（デフォルトでは*[Thank You]* ）をクリックし、エディタボックスを使用します。
2.  メッセージフィールド（デフォルトでは、*[Your information was...]* ）をクリックし、エディタを使用して成功メッセージをカスタマイズします。

![デフォルトの成功ページはカスタマイズ可能です。](./creating-forms/images/12.png)

### フォームの公開

フォームに入力したら、ユーザーが回答の送信を開始できるようにフォームを公開する必要があります。 *[フォームを公開する]* をクリックして公開します。

フォームを公開すると、ユーザーに送信できるフォーム入力用のURLが生成されます。

![フォームが公開されると、共有できるURLが生成されます。](creating-forms/images/11.png)

詳細は、[フォームの共有](../sharing-forms-and-managing-submissions/sharing-forms.md)を参照してください。

## フォームの複製

フォームを複製して、ゼロから作成することなく、同様のフォームを作成できます。 フォームを複製するには、次の手順に従います。

1.  *[Site Administration]* → *[Content & Data]* → *[Forms]* に移動します。

2.  元のフォームの横にある*アクション*ボタン（![Actions](../../../images/icon-actions.png)）をクリックします。

    ![フォームの複製](./creating-forms/images/10.png)

3.  *[Duplicate]* をクリックします。

これで、元の調査フォームのコピーが生成されます。 ユーザーは複製フォームの変更を開始できます。

![複製フォームの生成](./creating-forms/images/05.png)

## 次のステップ

  - [フォームの共有](../sharing-forms-and-managing-submissions/sharing-forms.md)
  - [Managing Form Entries](../sharing-forms-and-managing-submissions/managing-form-entries.md)
  - [Validating Text and Numeric Field Entries](./validating-text-and-numeric-field-entries.md)
  - [Enabling CAPTCHA on Form Submissions](../sharing-forms-and-managing-submissions/enabling-captcha-on-form-submissions.md)
