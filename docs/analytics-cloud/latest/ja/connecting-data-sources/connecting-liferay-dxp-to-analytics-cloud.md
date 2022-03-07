# Liferay DXPをAnalytics Cloudに接続する

お客様のLiferay DXPインスタンスには、ウェブアナリティクスデータや訪問者データが豊富に含まれています。 ウェブサイトの訪問者情報を監視・収集するためには、データソースを追加してLiferay DXPサイトとAnalytics Cloudとの接続を設定する必要があります。

Analytics Cloudはアクセストークンを使用してLiferay DXPと接続します。 Liferay DXPのインストールは、以下のフィックスパックの最小要件を満たしている必要があります：

* 7.3 Fix Pack 1
* 7.2 Fix Pack 11
* 7.1 Fix Pack 21
* 7.0 Fix Pack 97

<a name="adding-a-data-source" />

## データソースの追加

1. ［**設定**］ > ［**データソース**］ > ［**データソースの追加**］ にナビゲートしてデータソースを作成します。 このアクションを実行するには、Adminロールが必要です。

1. データソースタイプとしてLiferay DXPを選択します。 コピーするトークンを提供する画面が表示されます。

      ![Analytics Cloudは、コピーするためのトークンを提供します。](connecting-liferay-dxp-to-analytics-cloud/images/02.png)

1. トークンをコピーして、Liferay DXPインスタンスにナビゲートします。 ［**設定**］ > ［**Instance Setting**］ 、もしくは ［**プラットフォーム**］ セクションにある ［**コントロールパネル**］ から、 ［**Analytics Cloud**］ をクリックします。 以下の画像のように、Analytics Cloudのトークンフィールドにアクセストークンを貼り付け、 ［**Connect**］ をクリックします。

      ![AnalyticsCloudトークンをLiferayDXPインストールのインスタンス設定構成に追加します。](connecting-liferay-dxp-to-analytics-cloud/images/03.png)

```{note}
   Liferay DXP 7.0の場合、Analytics Cloud Adminは、*Configuration* > *Analytics Cloud*の下にあります。
```

接続が成功すると、 `Your DXP instance is connected to Analytics Cloud`というメッセージが表示されます。

```{note}
   分析データの抑制については、 [Do Not Track Feature](.././workspace-data/data-control-and-privacy.md#do-not-track-feature) を参照してください。
```

![DXPとAnalytics Cloudの接続が正しく設定されたことを示す成功メッセージが表示されます。](connecting-liferay-dxp-to-analytics-cloud/images/04.png)

これでDXPがACワークスペースに接続されました。

注：新しいデータソースが追加されると、同じ名前のプロパティが自動的に作成されます。 重複している場合は、増分のサフィックスが追加されます（例：Liferay DXP（1））。 データソースの名前もプロパティの名前も、後でいつでも変更できます。

<a name="next-steps" />

## 次のステップ

* [サイトと個人をプロパティでスコープする](./scoping-sites-and-individuals-using-properties.md)
