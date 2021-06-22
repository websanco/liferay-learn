# 商品公開の管理

Liferay Commerceは、ストアマネージャーが商品を**期限切れにする**または**非公開**にすることで、商品の公開ステータスを管理できる2つの異なる方法を提供しています。

商品が**有効期限が切れる**と 、将来的に商品が販売できなくなるように、将来の有効期限が設定されます。 商品は*期限切れ*の状態になり、ストアの検索結果には表示されません。 ストアマネージャーは、商品全体（すべてのSKU）または特定の1つのSKUのみの有効期限プロセスを設定できます。

商品を**非公開**にすると、その商品は*下書き*状態に設定され、検索結果から削除されます。 商品の在庫が最小しきい値を下回った場合に商品が自動的に非公開になるように設定できます。詳細については、記事[「Low Stock Action」](./low-stock-action.md)を参照してください。

## 商品の有効期限のスケジューリング

商品の有効期限のスケジュールは、*コントロールパネル*を使用して行います。 商品の有効期限をスケジュールするには：

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品をクリックします（たとえば、*U-Joint*）。

3.  *[Products Details]*タブで*[Never Expire]*チェックボックスをオフにします。 この例では、この設定がすべてのSKUに影響します。

4.  将来の日付で有効期限を入力します。 すべての時間はGMTタイムゾーンを使用して設定されます。

    ![商品の有効期限の設定](./managing-product-publication/images/03.png)

5.  *[Publish]*をクリックします。

変更が行われた後、チェック間隔があるため、商品が*[Catalog]*ページから削除されるまでに時間がかかる場合があります。 デフォルトでは、チェック間隔は15分に設定されています。 有効期限日が過ぎた後は、*[Catalog]*ページで検索しても本商品は返されません。 *[Control Panel]*では、商品のステータスは*[Expired]*と表示されます。

![期限切れステータス](./managing-product-publication/images/04.png)

新しい有効期限を入力して商品を再公開することにより、商品を再び表示できます。

## 商品を非公開にする

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品をクリックします（たとえば、*U-Joint*）。

    > 必要に応じて*[Schedule]*セクションを展開します。

3.  *[Published]*チェックボックスをオフにします。

    ![[Published]ボックスをオフにする](./managing-product-publication/images/01.png)

4.  *[Save as Draft]* をクリックします。

5.  ストアサイトに移動します。

6.  商品を検索します（この例では*U-Joint*）。

![非公開になったU-Joint](./managing-product-publication/images/02.png)

商品はカタログで検索できません。 *[Control Panel]*で、商品のステータスが*[Draft]*に設定されています。

![非公開になったU-Joint](./managing-product-publication/images/05.png)

## 追加情報

  - [商品タイプについて](../creating-and-managing-products/product-types/introduction-to-product-types.md)
  - [チャネルを使用した商品の可視性の構成](../../starting-a-store/channels/configuring-product-visibility-using-channels.md)
  - [シンプル商品を作成する](../creating-and-managing-products/product-types/creating-a-simple-product.md)
  - [Creating a Grouped Product](../creating-and-managing-products/product-types/creating-a-grouped-product.md)
  - [仮想製品の作成](../creating-and-managing-products/product-types/creating-a-virtual-product.md)
  - [Low Stock Action](./low-stock-action.md)
