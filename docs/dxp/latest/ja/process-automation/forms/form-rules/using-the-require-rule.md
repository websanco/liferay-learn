# 必須ルールの使用

*必須*ルールを使用すると、1つまたは複数の条件に基づいてフィールドを必須にすることができます。 必須ルールは、*表示*ルールのように、他のルールと関連して機能します。

例えば、キャンプの登録フォームに以下の項目が含まれているとします。

  - *I am 18 Years Old or Older*: *[Yes]* と*[No]* の2つのオプションを持つ必須の単一選択フィールド。
  - *Legal Guardian Email Address*: 有効なメールアドレスを受け付けるテキストフィールド。
  - *[I am 18 Years Old or Older]* の値が[NO]の場合に*[Legal Guardian Address]* フィールドを表示する[表示ルール](./using-the-show-hide-rule.md)。

回答者が*[No]* と答えた場合、*[Legal Guardian Email Address]* が表示され、回答者は有効なメールアドレスを入力しなければなりません。

## 必須ルールの設定

以下の手順に従ってください。

1.  *[Rules]* タブをクリックします。

2.  *[I am 18 Years Old or Older]* ルールの横にあるアクション（![Actions](../../../images/icon-actions.png)）をクリックします。

    ![既存の表示/非表示ルールを変更します。](./using-the-require-rule/images/01.png)

3.  *[編集する]* をクリックします。

4.  *[アクション]* の下で*[ルールの追加]* ボタンをクリックします。

5.  *[アクション]* ドロップダウンメニューから*[Require]* を選択します。

6.  2つ目のドロップダウンメニューから*[Legal Guardian Email Address]* を選択します。

    ![必須ルールを追加します。](./using-the-require-rule/images/02.png)

7.  完了したら、*[保存]* をクリックします。

## 追加情報

  - [フォームの作成](../creating-and-managing-forms/creating-forms.md)
  - [Using the Show-Hide Rule](./using-the-show-hide-rule.md)
