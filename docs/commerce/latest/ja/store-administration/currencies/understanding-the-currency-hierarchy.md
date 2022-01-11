# 通貨階層を理解する

Liferay Commerceでは、カタログ、価格表、およびチャネルで異なる通貨を使用することができます。 各通貨は、Commerceの通貨階層の中で独自の役割を持っています。

[カタログ](../../managing-a-catalog/catalogs/creating-a-new-catalog.md)の 通貨は、各商品の基本価格を決定する基本価格リストの初期通貨を設定します。 カスタム[価格表](../../managing-a-catalog/managing-prices/creating-a-price-list.md)と代替通貨を使って、特定のチャネル、アカウント、およびアカウントグループの基本価格表を上書きすることができます。

商品が[チャネル](../../starting-a-store/channels/managing-channels.md)に追加されると [為替レートプロバイダー](./managing-exchange-rates.md)を使用してチャネルの通貨に変換されます。 変換された価格はチャネルに保存され、表示価格や注文価格に使用されます。

例えば、米国を拠点とする企業が、米国の顧客向けとEUの顧客向けの2つのチャネルを持っているとします。 このビジネスのカタログと基本価格表はどちらもUSDを使用していますが、チャネルはUSDとEURと、異なる通貨を使用しています。 商品がこれらのチャネルで利用可能になると、Commerceの為替レートプロバイダーは、基本となる米ドル価格をチャネルの通貨に自動的に変換します。

この例では、米ドルの価格がユーロの価格に変換され、EUチャネルに保存されます。 これらの保存された価格は、チャネルの接続されたサイトに使用されます。 これにより、ドイツの顧客はEUチャネルのサイトを閲覧するときにユーロの価格を確認でき、米国の顧客は米国のチャネルのサイトにアクセスするときに米ドルの価格を確認することができます 注文が作成されると、各チャネルは適切な通貨で保存された価格を使用します。

```{note}
   利用可能な通貨は、*グローバルメニュー*の*Commerce*タブにある*通貨*ページで確認できます。 ここで、通貨の編集、優先順位付け、追加/削除、有効化/無効化を行うことができます。 詳しくは、 [通貨リファレンス](./currencies-reference.md) と [新しい通貨の追加](./adding-a-new-currency.md) を参照してください。
```

<!-- Update article once \[COMMERCE-5171\](https://issues.liferay.com/browse/COMMERCE-5171) is implemented. It removes Catalog Currency and uses the Base Price List currency alone as currency basis. --> 

## 追加情報

* [新しい通貨の追加](./adding-a-new-currency.md)
* [為替レートの管理](./managing-exchange-rates.md)
* [通貨リファレンス](./currencies-reference.md)
