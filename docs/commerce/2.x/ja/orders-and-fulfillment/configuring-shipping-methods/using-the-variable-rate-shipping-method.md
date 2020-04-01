# 変動レート配送方法の使用

変動レート配送では、重量、注文小計（配送前の費用、税金、割引）、および設定可能な均一料金の3つの要素を使用して配送料を計算できます。 ストア管理者は、出荷計算で各要素の重み付けを設定できます。

変動レートの複数の異なる配送オプションを作成できます。 例えば、重量あたりのコストが低い「Standard Ground」オプションを作成できます。 一方で、「Standard Ground」と同じロジックを使用するが、重量あたりのコストが高くなる「Two-Day Air」オプションを作成できます。

## 変動レート配送オプションの作成

変動レートの配送料は、次の式で決定されます。

`配送料= [固定価格] +（[注文の合計重量] * [レート単位の重量価格]）*（[注文の小計] x [レートのパーセンテージ]）`

新しい変動レート配送オプションを作成するには：

1.  *[Site Administration]* → *[Commerce]* → *[Settings]*に移動します。

2.  *[Shipping Methods]*タブをクリックします。

3.  *[Variable Rate]*をクリックします。

4.  *[Shipping Options]*タブをクリックします。

5.  （+）ボタンをクリックして、新しい配送オプションを追加します。

6.  次のように入力します：

      - **Name**：2 Day Ground
      - **Description**：2 Day Ground
      - **Priority**：3.0

    ![新しい2 Day Ground配送オプション](./using-the-variable-rate-shipping-method/images/01.png)

7.  *[Save]*をクリックします。

新しい配送オプションが作成されました。 この配送オプションの設定を完了するには、変動送料を適用します。

## 変動送料の適用

1.  *[Shipping Option Settings]*タブをクリックします。

2.  （+）ボタンをクリックして、変動レートの原価公式を追加します。

3.  次のフィールドに入力します。

      - **Shipping Option**：2 Day Ground

      - **Warehouse**：方法を1つの場所からの出荷のみに適用する場合は、倉庫を選択します。 すべての倉庫でこの方法を使用するには、空白のままにします。

      - **Country**：配送方法を指定した国に制限する必要がある場合は、このフィールドを使用します。

      - **Region**：配送方法を指定した地域に制限する必要がある場合は、このフィールドを使用します。

      - **Zip**：配送方法を指定した郵便番号に制限する必要がある場合は、このフィールドを使用します。

      - **Weight From**：このオプションを使用できる注文の最小重量を入力します。

      - **Weight To**：このオプションを使用できる注文の最大重量を入力します。

      - **Fixed Price**：このフィールドに入力すると、最低価格が設定され、送料計算式の固定成分となります。 空白のままにすることができます。

      - **Rate Unit Weight Price**：このフィールドに入力すると、重量あたりのコストが課せられます。 空白のままにすることができます。

      - **Rate Percentage**：このフィールドに入力すると、注文の小計のパーセンテージに基づいて送料が課せられます。 空白のままにすることができます。

        ![2 Day Ground設定](./using-the-variable-rate-shipping-method/images/02.png)

4.  *[Save]*をクリックします。

複数のオプションを作成するには、*[Shipping Options]*タブに戻り、手順2〜4を繰り返します。

## 発送方法の有効化

1.  *[Details]* タブをクリックします。
2.  *[Active]*ボタンを*[YES]*に切り替えます。
3.  *[Save]*をクリックします。

発送方法が有効になりました。

## 追加情報

  - [Using a Flat Rate Shipping Method](./using-the-flat-rate-shipping-method.md)
  - [Using FedEx as a Carrier Method](./using-fedex-as-a-carrier-method.md)
  - [Applying Shipping Method Restrictions](./applying-shipping-method-restrictions.md)
  - [Measurement Units](./measurement-units.md)
  - [Shipping Method Reference](./shipping-method-reference.md)
  - [Creating a New Shipment](../managing-shipments/creating-a-shipment.md)
