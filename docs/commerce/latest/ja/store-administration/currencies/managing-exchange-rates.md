# 為替レートの管理

為替レートは、手動で管理するか、オンラインサービスによって自動的に更新できます。 ストアの為替レートが最新であることを確認するために、管理者は自動通貨換算を有効にできます。

## 自動通貨変換を有効にする

デフォルトでは、Liferay Commerceは外国為替レートについて欧州中央銀行に依存しています。 ECBは約24時間ごとに為替レートを更新します。 代替の為替レートプロバイダを実装する方法については、[Implementing an Exchange Rate Provider](../../developer-guide/implementing-an-exchange-rate-provider.md)を参照してください。

自動為替レート更新を有効にするには：

1.  *[Control Panel]* → *[Commerce]* → *[Settings]*に移動します。

2.  *[Currencies]* タブをクリックします。

3.  *[Exchange Rate]*画面をクリックします。

4.  *[Exchange Rate Provider]*ドロップダウンメニューから*[European Central Bank]*を選択します。

5.  *[Enable Auto-Update]*ラジオボタンを*[Yes]*に切り替えます。

    ![欧州中央銀行の換算を有効にする](./managing-exchange-rates/images/01.png)

6.  *[Save]*をクリックします。

自動為替レートの更新が有効になりました。

### 更新間隔の変更

デフォルトでは、為替レートは60分ごとに更新されます。 為替レートが更新される頻度を増やすには：

1.  *[Control Panel]* → *[Configuration]* → *[System Settings]*に移動します。

2.  *[Commerce]*セクションで*[Pricing]*をクリックします。

3.  *[Exchange Rate Auto-Update]*をクリックします。

4.  値を分単位で入力します：120（2時間ごと）。

5.  *[Enable Auto-Update]*チェックボックスをオンにします。

    ![通貨更新間隔の変更](./managing-exchange-rates/images/02.png)

6.  *[Save]*をクリックします。

為替レートの自動更新が有効になり、2時間ごとに為替レートが更新されます。

## 為替レートの手動設定

為替レートは、ストア管理者が手動で設定できます。

1.  *[Control Panel]* → *[Commerce]* → *[Settings]*に移動します。

2.  *[Currencies]* タブをクリックします。

3.  既存の通貨をクリックします（たとえば、*Chinese Yuan Renminbi* ）。

4.  *[Exchange Rate with US Dollar]*フィールドに新しい値を入力します（例：*6.96*）。

    ![為替レートを手動で変更する](./managing-exchange-rates/images/03.png)

5.  *[Save]*をクリックします。

## 追加情報

  - [Implementing an Exchange Rate Provider](../../developer-guide/implementing-an-exchange-rate-provider.md)
