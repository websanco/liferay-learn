# Liferay DXPとAnalytics Cloudの接続

お客様のLiferay DXPインスタンスには、ウェブアナリティクスデータや訪問者データが豊富に含まれています。 ウェブサイトの訪問者情報を監視・収集するためには、データソースを追加してLiferay DXPサイトとAnalytics Cloudとの接続を設定する必要があります。

Analytics Cloudはアクセストークンを使用してLiferay DXPと接続します。 Liferay DXPのインストールは、以下のフィックスパックの最小要件を満たしている必要があります。

-   7.2 Fix Pack 5/SP2
-   7.1 Fix Pack 18/SP4
-   7.0 Fix Pack 90/SP13

## データソースの追加

1.  *設定* &gt; *データソース* &gt; *データソースの追加*にナビゲートしてデータソースを作成します。 このアクションを実行するには、管理者ロールを持っている必要があります。

2.  データソースの種類としてLiferay DXPを選択します。 コピーするためのトークンを提供する画面が表示されます。

    ![Analytics Cloudはコピーするためのトークンを提供しています。](connecting-liferay-dxp-to-analytics-cloud/images/02.png)

3.  トークンをコピーし、Liferay DXPインスタンスに移動します。 コントロールパネルにある、設定 ＞ インスタンス設定 ＞ Analytics Cloudに移動します。 下の画像のように、Analytics Cloud TokenフィールドにAccess Tokenを貼り付け、Connectをクリックします。

    ![Liferay DXPインストールのインスタンス設定構成にAnalytics Cloudトークンを追加します。](connecting-liferay-dxp-to-analytics-cloud/images/03.png)

``` note::
   For Liferay DXP 7.0, Analytics Cloud Admin is under *Configuration* > *Analytics Cloud*.
```

接続が成功すると、 `Your DXP instance is connected to Analytics Cloud`というメッセージが表示されます。

``` note::
   See `Do Not Track Feature <../../workspace-data/data-control-and-privacy.md#do-not-track-feature>`_ to learn more about suppressing analytics data.
```

![DXPとAnalytics Cloudの接続が正しく設定されたことを示す成功メッセージが表示されます。](connecting-liferay-dxp-to-analytics-cloud/images/04.png)

これでDXPがACワークスペースに接続されました。

## 次のステップ

-   [プロパティでサイトや個人を追跡](./scoping-sites-and-individuals-using-properties.md)
