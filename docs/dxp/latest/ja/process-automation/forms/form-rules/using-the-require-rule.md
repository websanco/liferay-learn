# 必須ルールの使用

_必須_ルールを使用すると、1つまたは複数の条件に基づいてフィールドを必須にすることができます。 必須ルールは、_表示_ルールのように、他のルールと関連して機能します。

例えば、キャンプの登録フォームに以下の項目が含まれているとします。

* _I am 18 Years Old or Older_: _［Yes］_と_［No］_の2つのオプションを持つ必須の単一選択フィールド。
* _Legal Guardian Email Address_: 有効なメールアドレスを受け付けるテキストフィールド。
* _［I am 18 Years Old or Older］_の値が［NO］の場合に_［Legal Guardian Address］_フィールドを表示する[表示ルール](./using-the-show-hide-rule.md)。

回答者が_［No］_と答えた場合、_［Legal Guardian Email Address］_が表示され、回答者は有効なメールアドレスを入力しなければなりません。

## 必須ルールの設定

以下の手順に従ってください。

1. _［ルール］_タブをクリックします。
1. _［I am 18 Years Old or Older］_ルールの横にあるアクション（![Actions](../../../images/icon-actions.png)）をクリックします。

    ![既存の表示/非表示ルールを変更します。](./using-the-require-rule/images/01.png)

1. _［Edit］_をクリックします。
1. _［アクション］_の下で_［ルールの追加］_ボタンをクリックします。
1. _［アクション］_ドロップダウンメニューから_［Require］_を選択します。
1. 2つ目のドロップダウンメニューから_［Legal Guardian Email Address］_を選択します。

    ![必須ルールを追加します。](./using-the-require-rule/images/02.png)

1. 完了したら、_［保存］_をクリックします。

## 追加情報

* [フォームの作成](../creating-and-managing-forms/creating-forms.md)
* [Using the Show-Hide Rule](./using-the-show-hide-rule.md)
