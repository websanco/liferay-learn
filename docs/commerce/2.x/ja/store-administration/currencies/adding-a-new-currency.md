# Adding a New Currency

Liferay Commerceでは、すぐに使用できる複数の通貨を事前設定しています。 希望の通貨がまだ含まれていない場合、ストア管理者は新しい通貨を追加できます。 特定の値を正しい形式で入力する方法については、[Currencies Reference](./currencies-reference.md)を参照してください。

新しい通貨を追加するには：

1.  *[Control Panel]* → *[Commerce]* → *[Settings]*に移動します。

2.  *[Currencies]* タブをクリックします。

3.  *[Add Currency]*ボタンをクリックします。

4.  次のように入力します：

      - *Name：*ニュージーランドドル
      - *Code：*NZD（ISOコード）
      - *Format Pattern：*$\#\#\#,\#\#0.00
      - *Maximum Decimal places：* 2
      - *Minimum Decimal places：* 2
      - *Rounding Mode：* HALF EVEN
      - *Exchange Rate：* 1.49701
      - *Priority：* 11

5.  *[Primary]*トグルは *[NO]*のままにします。

    ![通貨を追加する](./adding-a-new-currency/images/01.png)

6.  *[Active]* トグルを *[Yes]*に切り替えます。

7.  *[Save]*をクリックします。

## 追加情報

### 注

  - *[Format Pattern]*フィールドには、通貨記号、コンマの間にある桁数、および表示される桁数が必要です。 たとえば、USドルの場合、$\#\#\#,\#\#0.00という形式では、*0*が保持する桁数は常に表示されますが（他の桁が存在しない場合は0）、*\#*が保持する桁数は、占有されている場合にのみ表示されます。 上記の形式の0.01の値は、$0.01として表示されます。

  - *[Rounding Mode]*には、ドロップダウンメニューから[丸めモード](https://en.wikipedia.org/wiki/Rounding#Directed_rounding_to_an_integer)のタイプが必要です。

  - 一部の支払い方法では、ストアが機能するために特定の主要通貨を設定する必要があります。 [Mercanet](../../store-administration/configuring-payment-methods/mercanet.md)では、主要通貨としてEURが必要です。
