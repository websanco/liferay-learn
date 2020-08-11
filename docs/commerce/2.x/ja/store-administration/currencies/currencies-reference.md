# 通貨リファレンス

Liferay Commerceには、いくつかの通貨が事前設定されており、すぐに使用できます。 注文を処理できるようにするには、各ストアで主要通貨を有効にする必要があります。 この記事は、各通貨に関連付けられたメタデータのリファレンスとして機能します。

通貨を管理するには、*[Control Panel]* → *[Commerce]* → *[Settings]*に移動し、*[Currencies]*タブをクリックします。

![新しい通貨を追加する](./currencies-reference/images/01.png)

| フィールド                        | 説明                                                                                                                                                                              |
| ---------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Name                         | 通貨の名前                                                                                                                                                                           |
| Code                         | 通貨の3文字の[ISOコード](https://www.currency-iso.org/en/home/tables/table-a1.html)                                                                                                      |
| Format Pattern               | 通貨記号、コンマの間にある桁数、および表示される桁数。 たとえば、USドルの場合、$\#\#\#,\#\#0.00という形式では、*0*が保持する桁数は常に表示されますが（他の桁が存在しない場合は0）、*\#*が保持する桁数は、占有されている場合にのみ表示されます。 上記の形式の0.01の値は、$0.01として表示されます。 |
| Maximum Decimal Places       | 許容される小数点の右側の最大桁数                                                                                                                                                                |
| Minimum Decimal Places       | 許容される小数点の右側の最小桁数                                                                                                                                                                |
| Rounding Mode                | ドロップダウンメニューからの[丸めモード](https://en.wikipedia.org/wiki/Rounding#Directed_rounding_to_an_integer)のタイプ                                                                               |
| Primary                      | この通貨をストアのデフォルトの通貨に指定するためのトグル                                                                                                                                                    |
| Exchange Rate with US Dollar | ストアの主要通貨と比較した通貨の為替レート。デフォルトでは、USDが主要通貨として指定されていますが、[UIで変更できます](../../orders-and-fulfillment/mercanet.md#set-eur-as-the-primary-store-currency) 。                                |
| Priority                     | 通貨が表に表示される順序。数字が大きいほど、下に表示されます                                                                                                                                                  |
| Active                       | この通貨を有効にするトグル                                                                                                                                                                   |

## 追加情報

  - [Adding a New Currency](./adding-a-new-currency.md)
  - [Mercanet](../configuring-payment-methods/mercanet.md)
