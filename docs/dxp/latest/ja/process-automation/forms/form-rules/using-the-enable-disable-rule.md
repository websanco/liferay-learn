# 有効/無効ルールの使用

有効/無効ルールは、1つまたは複数の条件に基づいてフィールドを編集できるようにします。 任意の情報を入力したり、特定のグループに該当するユーザーのみからデータを収集する場合に便利です。 例えば、キャンプ場や病院では、治療に影響を与える可能性のある薬やアレルギーを記入してもらうことがあります。 このフィールドは、ユーザーが［Yes］を選択した場合のみ編集可能です。

この例では、以下のフィールドを持つ登録フォームを想定しています。

* _Are you taking any medications?_: _［Yes］_と_［No］_の2つのオプションを持つ必須の単一選択フィールドです。
* _List all medications_: テキストフィールド要素です。

有効/無効ルールを設定します。

1. _［ルール］_タブをクリックします。
1. 追加（![Add](../../../images/icon-add.png)）ボタンをクリックします。
1. ［条件］ドロップダウンメニューから_［Are you taking any medications?］_を選択します。
1. 条件の作成： _Is equal to_ &rarr; _Value_ &rarr; _Yes_.
1. ［アクション］ドロップダウンメニューで_［Enable］_を選択します。
1. _［List all medications］_を選択します。

    ![条件に基づいて、有効/無効のルールを作成します。](./using-the-enable-disable-rule/images/01.png)

1. 完了したら、_［保存］_をクリックします。

有効ルールが作成され、ユーザーは［Yes］を選択した場合のみフィールドを編集できます。 それ以外の場合は、フィールドがグレーアウトしています。

![ユーザーが［No］を選択した場合、フィールドは無効になります。](./using-the-enable-disable-rule/images/02.png)

回答者が［YES］と回答した場合は、そのフィールドを編集することができます。

![ユーザーが［Yes］を選択すると、フィールドが有効になります。](./using-the-enable-disable-rule/images/03.png)

## 追加情報

* [フォームの作成](../creating-and-managing-forms/creating-forms.md)
* [Form Rules Overview](./form-rules-overview.md)
* [Using the Require Rule](./using-the-require-rule.md)
* [Using the Enable Rule](./using-the-enable-disable-rule.md)
* [Using the Jump to Page Rule](./using-the-jump-to-page-rule.md)
* [Using the Autofill Rule](./using-the-autofill-rule.md)
* [Using the Calculate Rule](./using-the-calculate-rule.md)
