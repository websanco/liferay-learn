# 計算ルールの使用

ユーザーは、他のフィールドに基づいて値を計算することで、数値フィールドに値を読み込む計算ルールを作成できます。 例えば、計算ルールでは、参加者数と登録料を掛け合わせることで（1人130ドル × 17人）、キャンプやカンファレンスの総登録料を算出することができます。

```{important}
計算の対象となるのは数値フィールドに限られます。
```

次の例では、3つの数値フィールドを持つフォームがあります。

  - *Number of attendees*: 数値フィールド。
  - *Registration Fee*: 150が[定義済みの値](../creating-and-managing-forms/providing-help-text-and-predefined-values.md)として設定されている数値フィールド。
  - *Total*: 合計が表示される数値フィールド。

## 計算ルールの設定

計算は、内蔵されている電卓を使って定義されます。 数値フィールドの値、数学演算子、定数を組み合わせて計算ルールを定義します。

以下の手順に従ってください。

1.  *[Rules]* タブをクリックします。

2.  追加（![Add](../../../images/icon-add.png)）ボタンをクリックします。

3.  *[If]* ドロップダウンメニューから*[Number of attendees]* を選択します。

4.  条件：*[Is greater than]* → *[Value]* → *[0]* を作成します。

5.  *[Do]* ドロップダウンメニューから*[計算]* を選択します。

6.  *[フィールドを選択して結果を表示]* ドロップダウンメニューから*[Total]* を選択します。

7.  組み込みの電卓を使って、**(NumberofAttendees\*RegistrationFee)**と入力します。

    ![2つのフィールドを掛け合わせて、総登録料を計算します。](./using-the-calculate-rule/images/01.png)

8.  完了したら、*[保存]* をクリックします。

計算ルールが作成されました。

![2つのフィールドを掛け合わせて、総登録料を計算します。](./using-the-calculate-rule/images/02.png)

## 追加情報

  - [フォームの作成](../creating-and-managing-forms/creating-forms.md)
  - [Form Rules Overview](./form-rules-overview.md)
  - [Providing Predefined Values for a Form](../creating-and-managing-forms/providing-help-text-and-predefined-values.md)
