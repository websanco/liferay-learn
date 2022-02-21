# 計算ルールの使用

ユーザーは、他のフィールドに基づいて値を計算することで、数値フィールドに値を読み込む計算ルールを作成できます。 例えば、計算ルールでは、参加者数と登録料を掛け合わせることで（1人130ドル × 17人）、キャンプやカンファレンスの総登録料を算出することができます。

```important::
   計算の対象となるのは数値フィールドに限られます。
```

次の例では、3つの数値フィールドを持つフォームがあります。

* _Number of attendees_: 数値フィールド。
* _Registration Fee_: 150が[定義済みの値](../creating-and-managing-forms/providing-help-text-and-predefined-values.md)として設定されている数値フィールド。
* _Total_: 合計が表示される数値フィールド。

## 計算ルールの設定

計算は、内蔵されている電卓を使って定義されます。 数値フィールドの値、数学演算子、定数を組み合わせて計算ルールを定義します。

以下の手順に従ってください。

1. _［ルール］_タブをクリックします。
1. 追加（![Add](../../../images/icon-add.png)）ボタンをクリックします。
1. _［If］_ドロップダウンメニューから_［参加者の数］_を選択します。
1. 条件の作成：_Is greater than_ &rarr; _Value_ &rarr; _0_.
1. _［Do］_ドロップダウンメニューから_［計算］_を選択します。
1. _［フィールドを選択して結果を表示］_ドロップダウンメニューから_［合計］_を選択します。
1. 組み込みの電卓を使って、 **(NumberofAttendees*RegistrationFee)**と入力してください。

    ![2つのフィールドを掛け合わせて、総登録料を計算します。](./using-the-calculate-rule/images/01.png)

1. 完了したら、_［保存］_をクリックします。

計算ルールが作成されました。

![2つのフィールドを掛け合わせて、総登録料を計算します。](./using-the-calculate-rule/images/02.png)

## 追加情報

* [フォームの作成](../creating-and-managing-forms/creating-forms.md)
* [Form Rules Overview](./form-rules-overview.md)
* [Providing Predefined Values for a Form](../creating-and-managing-forms/providing-help-text-and-predefined-values.md)
