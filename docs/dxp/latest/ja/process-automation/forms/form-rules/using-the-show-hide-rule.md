# 表示/非表示ルールの使用

ユーザーは、*表示*アクションを使って、条件を満たした場合にフィールドを表示することができます。 条件を満たさない場合、このフィールドは非表示になります。

この例では、2つのテキストフィールドを持つ[キャンプ登録フォーム](../creating-and-managing-forms/creating-forms.md)を使用しています。

  - *I am 18 Years Old or Older*: *[Yes]* と*[No]* の2つのオプションを持つ必須の単一選択フィールド。
  - *Legal Guardian Email Address*: 有効なメールアドレスを受け付けるテキストフィールド。

## 表示アクションルールの設定

表示/非表示アクションルールを設定するには：

1.  *[Rules]* タブをクリックします。

2.  追加（![Add](../../../images/icon-add.png)）ボタンをクリックします。

3.  *[If]* ドロップダウンメニューから*[I am 18 Years Old or Older]* を選択します。

4.  次のドロップダウンメニューから*[と等しい]* を選択します。

5.  3番目のドロップダウンメニューから*[Value]* を選択します。

6.  最後のドロップダウンメニューから*[No]* を選択します。

    ![条件やアクションを定義することで、フォームルールを素早く構築できます。](./using-the-show-hide-rule/images/01.png)

7.  *[アクション]* セクションで、ドロップダウンメニューから*[表示]* を選択します。

8.  2つ目の[Options]ドロップダウンメニューから*[Legal Guardian Email Address]* を選択します。

9.  完了したら、*[Save]* をクリックします。

    ![保存されたルールは、その機能が簡単に理解できるように表示されます。](./using-the-show-hide-rule/images/02.png)

これで、ユーザーが*[I am 18 years old or older]* フィールドで*[No]* を選択した場合にのみ、*[Legal Guardian Email Address]* フィールドが表示されます。

## 追加情報

  - [フォームの作成](../creating-and-managing-forms/creating-forms.md)
  - [Form Rules Overview](./form-rules-overview.md)
  - [Using the Require Rule](./using-the-require-rule.md)
  - [Using the Enable Rule](./using-the-enable-disable-rule.md)
  - [Using the Jump to Page Rule](./using-the-jump-to-page-rule.md)
  - [Using the Autofill Rule](./using-the-autofill-rule.md)
  - [Using the Calculate Rule](./using-the-calculate-rule.md)
