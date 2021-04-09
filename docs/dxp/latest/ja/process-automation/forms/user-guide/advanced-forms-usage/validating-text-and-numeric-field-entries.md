# テキストおよび数値フィールドエントリの検証

入力検証を有効にして、特定の値のみがテキストまたは数値フィールドで受け入れられるようにします。

## フィールド検証の有効化

検証を有効にするには：

1.  フォームを表示しているときに、テキストまたは数値フィールドをクリックして、フィールド構成メニューを開きます。

2.  *[Advanced]* タブをクリックします。

3.  検証を有効にするには、 *検証* トグルを右に切り替えます。

    ![図1：データを検証して、有用な情報のみを収集していることを確認します。](./validating-text-and-numeric-field-entries/images/01.png)

検証を有効にしたら、 *If Input* 条件を指定し、検証がどのように失敗したかを示す *Show Error Message* を入力します。

<!-- When are the validation rules active? Does a person need to "Save" the form for the validation to be active? If yes, then we should explicitly say so. -->

## テキストフィールド

その他の検証条件の詳細については、 [検証条件リファレンス](./validation-conditions-reference.md) を参照してください。

### メールアドレスの検証

メールアドレスを検証するようにフォームを設定できます。 ユーザーが有効なメールアドレスを入力したことを確認するには：

1.  *If Input* ドロップダウンから *Is not email* 条件を選択します。

2.  エラーメッセージを[ *Show Error Message* フィールドに入力します。有効なメールアドレスを入力してください。

3.  完了したら、[ *Save Form* クリックします。

    ![テキストフィールドの検証を使用して、ユーザーが有効なメールアドレスまたはURLを入力していることを確認します。](./validating-text-and-numeric-field-entries/images/04.png)

### URLの検証

メールと同様に、フォームはURLを検証するように構成できます。

URLを検証するには：

1.  *If Input* ドロップダウンから *Is not URL* 条件を選択します。
2.  エラーメッセージを入力してください。
3.  完了したら、[ *Save Form* クリックします。

### 正規表現の使用

フォームは、 [正規表現](https://en.wikipedia.org/wiki/Regular_expression) を使用してカスタム検証基準を作成するように構成できます。 たとえば、次の正規表現を使用して、電話番号フィールドに10桁の連続した数字が入力されるようにします。

    ^[0-9]{10}$

正規表現を使用してテキストフィールドを検証するには：

1.  [ *If Input* ]ドロップダウンメニューから[ *Does Not Match* 条件を選択します。

2.  正規表現を入力します。

3.  *Show Error Message* フィールドにエラーメッセージを入力します。

    ![図4：正規表現のテキスト検証は、無数の可能性を切り開きます。](./validating-text-and-numeric-field-entries/images/05.png)

4.  完了したら、[ *Save Form* クリックします。

## 数値フィールドの検証

数値フィールドの検証はテキストフィールドの検証に似ていますが、条件は入力された数値の値を他の値と比較します。

数値フィールドを検証するには：

1.  *If Input* ドロップダウンメニューから目的の条件を選択します（たとえば、 *は*より大きい）。

2.  制限となる数値（*10*）を入力します。

3.  *Show Error Message* フィールドにエラーメッセージを入力します。

    ![数値条件は、ユーザーが入力した数値データを制約します。](./validating-text-and-numeric-field-entries/images/02.png)

4.  完了したら、[ *Save Form*]クリックします。

### 整数のみが必要

数値が整数であることを要求するようにフォームを構成できます。 デフォルトでは、 *My numeric type is* 値はDecimalに設定されています。

整数のみを受け入れるように数値フィールドを構成するには：

1.  フォームを表示しているときに、数値フィールドをクリックしてフィールド構成メニューを開きます。

2.  *Basic* タブで、 *Integer* オプションボタンをクリックします。

    ![数値が10進数か整数のみかを指定します。](./validating-text-and-numeric-field-entries/images/03.png)

3.  変更を適用するには、[ *Save Form* ]をクリックします。

## 追加情報

  - [検証条件リファレンス](./validation-conditions-reference.md)
  - [Creating Forms](../creating-forms.md)
