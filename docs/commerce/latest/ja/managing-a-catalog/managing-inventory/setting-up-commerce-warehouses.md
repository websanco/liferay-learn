# Commerce倉庫のセットアップ

Commerce倉庫は、商品の在庫が保管されている物理的な場所を表します。 各倉庫は複数のチャネルにリンクすることができ、有効にするには位置情報が設定されている必要があります。 これらの位置情報は、Fedexの配送方法で顧客への送料を計算する際に使用されます。

[Accelerator ](../../starting-a-store/accelerators.md)を使用して Commerce Site を作成する場合、3つのサンプル倉庫が作成されます。

```{note}
   Commerce倉庫を設定するとき、ユーザーは最初に倉庫の該当する国および地域の場所（州または県）を有効にする必要がある場合があります。 詳細は、 `リージョンの追加 <../../store-administration/adding-regions.md>`_ を参照してください。
```

## 倉庫の追加

次の手順に従って、新しい倉庫を追加します：

1. ［*グローバルメニュー*］ (![Global Menu](../../images/icon-applications-menu.png))を開き、［*Commerce*］タブをクリックして、［*倉庫*］に移動します。

1. *追加*ボタン（![Add Button](../../images/icon-add.png)）をクリックします。

   ![追加ボタンをクリックして、新しい倉庫を作成します。](./setting-up-commerce-warehouses/images/01.png)

1. 新しい倉庫の*［名前］*と*［説明］*（オプショナル）を入力します。

1. トグルを *［Active］*に切り替えます。 倉庫を有効にするには、位置情報が必要です。

1. どの *［チャネル］* が倉庫に関連付けられているかを選択します。

   チャネルの商品在庫は、関連する倉庫によって決定されます。

1. 倉庫 の*住所*を入力します。

1. 倉庫の *［位置情報］* を入力します（有効化するために必要です）。

   または、Bing ジオコーダを使用して、倉庫の位置情報を自動的に生成することもできます。 この機能を使用するには、倉庫に住所がある必要があります。 詳細は [下記](#using-bing-geocoder-to-set-a-warehouses-geolocation) を参照してください。

1. *［保存］*をクリックします。

倉庫を有効にすると、 倉庫に[［商品在庫を追加する］](./setting-inventory-by-warehouse.md)ことができます。

## Bingジオコーダを使用して倉庫の位置情報を設定する

Commerceでは、Bing Maps APIとの連携により、倉庫の位置情報を自動的に生成します。 この機能を利用するには、 [［Bing Maps アカウント］](https://docs.microsoft.com/en-us/bingmaps/getting-started/bing-maps-dev-center-help/creating-a-bing-maps-account) と [［Bing Maps Key］](https://docs.microsoft.com/en-us/bingmaps/getting-started/bing-maps-dev-center-help/getting-a-bing-maps-key) が必要です。

その後、以下の手順に従ってインスタンスにBing ジオコーダを設定します:

1. *グローバルメニュー*（![Global Menu](../../images/icon-applications-menu.png)）を開き、*［コントロールパネル］* &rarr; *［システム設定］* &rarr; *［コマース］* &rarr; *［出荷先］* &rarr; *［Bing ジオコーダ］*に移動します。

1. *［API キー］*を入力します。

   ![APIキーを入力します](./setting-up-commerce-warehouses/images/02.png)

1. *［保存］*をクリックします。

ジオコーダを設定すると、住所が設定されている倉庫に対して倉庫の位置情報を生成することができます。 倉庫のページに移動し、目的の倉庫の *［アクション］* ボタン（![Actions Button](../../images/icon-actions.png)）をクリックし、 *［ジオロケーション］*を選択します。 ジオコーダは、住所を緯度と経度の座標に変換します。

![ジオロケーションを選択します](./setting-up-commerce-warehouses/images/03.png)

## Commerce 2.1以前

［_コントロールパネル_］に倉庫を追加するには：

1. ［_コントロールパネル_］ → ［_コマース_］ → ［_設定_］に移動します。
1. ［_倉庫_］ タブをクリックします。
1. _倉庫の追加_（![Add Icon](../../images/icon-add.png)）ボタンをクリックします。
1. 次のように入力します：
   * *Name：* _倉庫の名前_（例：ノースラスベガスの倉庫）
   * *Description：* _倉庫の説明_（例：ノースラスベガスの処理センター）
1. トグルを ［_有効_］に切り替えます。
1. 倉庫の住所を入力します。
1. チャネルを選択します。 チャネルの商品在庫は、関連する倉庫に基づいています。
1. 地理位置情報データを入力します（必須）：
    * *Latitude*：36.282974
    * *Longitude*: -115.136

    ![Commerce倉庫のセットアップ](./setting-up-commerce-warehouses/images/04.png)

1. 完了したら、［_保存_］をクリックします。

新しい倉庫がアクティブになり、［_Shipment_］タブでオプションとして利用可能になりました。

## 追加情報

* [リージョンの追加](../../store-administration/adding-regions.md)
* [請求先または配送先として国を無効にする](../../store-administration/deactivating-a-country-for-billing-or-shipping.md)
* [チャネルについて](../../starting-a-store/channels/introduction-to-channels.md)
