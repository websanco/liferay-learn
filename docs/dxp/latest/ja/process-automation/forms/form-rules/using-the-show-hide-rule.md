# 表示/非表示ルールの使用

ユーザーは、_表示_アクションを使って、条件を満たした場合にフィールドを表示することができます。 条件を満たさない場合、このフィールドは非表示になります。

この例では、2つのテキストフィールドを持つ[キャンプ登録フォーム](../creating-and-managing-forms/creating-forms.md)を使用しています。

* _I am 18 Years Old or Older_: _［Yes］_と_［No］_の2つのオプションを持つ必須の単一選択フィールド。
* _Legal Guardian Email Address_: 有効なメールアドレスを受け付けるテキストフィールド。

## 表示アクションルールの設定

表示/非表示アクションルールを設定するには：

1. _［ルール］_タブをクリックします。
1. 追加（![Add](../../../images/icon-add.png)）ボタンをクリックします。
1. _［If］_ドロップダウンメニューから_［I am 18 Years Old or Older］_を選択します。
1. 次のドロップダウンメニューから_［と等しい］_を選択します。
1. 3番目のドロップダウンメニューから_［Value］_を選択します。
1. 最後のドロップダウンメニューから_［No］_を選択します。

    ![条件やアクションを定義することで、フォームルールを素早く構築できます。](./using-the-show-hide-rule/images/01.png)

1. _［アクション］_セクションで、ドロップダウンメニューから_［表示］_を選択します。
1. 2つ目の［Options］ドロップダウンメニューから_［Legal Guardian Email Address］_を選択します。
1. 完了したら、_［保存］_をクリックします。

     ![保存されたルールは、その機能が簡単に理解できるように表示されます。](./using-the-show-hide-rule/images/02.png)

これで、ユーザーが*［I am 18 years old or older］*フィールドで*［No］*を選択した場合にのみ、*［Legal Guardian Email Address］*フィールドが表示されます。

## 追加情報

* [フォームの作成](../creating-and-managing-forms/creating-forms.md)
* [Form Rules Overview](./form-rules-overview.md)
* [Using the Require Rule](./using-the-require-rule.md)
* [Using the Enable Rule](./using-the-enable-disable-rule.md)
* [Using the Jump to Page Rule](./using-the-jump-to-page-rule.md)
* [Using the Autofill Rule](./using-the-autofill-rule.md)
* [Using the Calculate Rule](./using-the-calculate-rule.md)
