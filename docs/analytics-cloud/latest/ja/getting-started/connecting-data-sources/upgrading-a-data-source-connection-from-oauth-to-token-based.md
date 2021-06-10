# データソース接続をOAuthからトークンベースにアップグレードする

``` warning::
   OAuth接続方式は今後、非推奨となります。 新しいデータソースを初めて設定する場合は、可能な限り `token connection <./connecting-liferay-dxp-to-analytics-cloud.md#using-theaccess-token>`_ を使用してください。
```

## Analytics Cloudでのアップグレードの開始

OAuthを使用してデータソース接続を実装しているユーザーは、トークンベースの接続方法からアップグレードすることを強くお勧めします。 OAuth 接続を使用しているワークスペースの管理者には、ワークスペース ダッシュボードにメッセージが表示されます。

![OAuth 接続を使用しているユーザーに警告メッセージが表示されます。](upgrading-a-data-source-connection-from-oauth-to-token-based/images/01.png)

1.  警告メッセージをクリックするか、 *設定* -> *データソース*に進み、 *Action Needed* ラベルで示された、接続のアップグレードが必要なすべてのデータ ソースを確認します。

    ![アップグレードを必要とするデータソースには、アクションが必要であることを示すラベルが表示されます。](upgrading-a-data-source-connection-from-oauth-to-token-based/images/02.png)

2.  *Action Needed* ラベルのあるデータソースをクリックします。

3.  データソース設定ページの右側にあるアップグレードウォークスルーボタンをクリックします。 トークン接続へのアップグレードのメリットと要件が表示されます。

    ![アップグレードを実行する前に、メリットと要件を提示します。](upgrading-a-data-source-connection-from-oauth-to-token-based/images/03.png)

    ``` important::
       次のステップに進む前に、LIferay DXPのインストールに必要なこれらのfixpackがインストールされていることを確認してください。 これらのfixpacksは、DXPコントロールパネルに適切なAnalytics Cloud Configuration Portletをインストールします。

       * Liferay DXP 7.1 Fix Pack 18/SP4
       * Liferay DXP 7.1 Fix Pack 18/SP4
       * Liferay DXP 7.0 Fix Pack 93/SP13
    ```

4.  *Continue with Upgrade* をクリックすると、トークン文字列が表示されますので、コピーしてDXPインスタンスに貼り付ける必要があります。 このトークン文字列には、DXPからAnalytics Cloudワークスペースに正しいエンドポイントに接続するために必要なセキュリティ情報が含まれています。

    ![トークンはユーザーに提示され、コピーしてDXPインスタンスの設定に貼り付けることができます。](upgrading-a-data-source-connection-from-oauth-to-token-based/images/04.png)

5.  トークン文字列をコピーして、Liferay DXPインスタンスに入力します。

## Liferay DXPの設定

トークン文字列をコピーしたら、Liferay DXPインスタンス上で以下の手順を実行します。

1.  *コントロールパネル* に移動し> *構成* - へ> *Instance Settings* - Instance Settings> > *Analytics Cloud* へ行きます。 次の手順を実行するには、インスタンスへの管理者レベルのアクセスが必要です。

    ![インスタンス設定画面で、Liferay DXPへのAnalytics Cloud接続を設定します。](upgrading-a-data-source-connection-from-oauth-to-token-based/images/05.png)

2.  トークン文字列を *Analytics Cloud Token* フィールドに貼り付けます。

3.  *Connect*をクリックします。 正常に接続するために、DXPサーバーがAnalytics Cloudサーバーへのアウトバウンドインターネットアクセスを持っていることを確認してください。 DXP がファイアウォールの後ろにある場合は、 [トラブルシューティングガイド](../../troubleshooting/connecting-data-sources.md)を参照してください。

4.  成功したメッセージが表示されます： *Analytics Cloud Workspace Connected*.

## サイトと連絡先の設定

アップグレードプロセスを完了するには、Analytics Cloudに同期するサイトと連絡先を再度設定する必要があります。

### サイトの同期

サイトを同期するには、以下の手順に従います。

1.  Analytics Cloudの設定で、 *Select Sites* をクリックします。

2.  次の画面では、Analytics Cloud ワークスペースに追加されたプロパティの一覧が表示されます。 アップグレードするデータ・ソースと同じ名前の [プロパティ](./tracking-sites-and-individuals-using-properties.md) を選択します。

    ![プロパティのリストが表示されたら、アップグレードするデータ ソースに一致するプロパティを選択します。](upgrading-a-data-source-connection-from-oauth-to-token-based/images/06.png)

3.  このプロパティに同期するDXPサイトを選択します。 アップグレード前に同期していたものと同じものを選択する必要がありますが、必要に応じていくつかのサイトを追加したり削除したりすることもできます。

4.  *[保存]* をクリックします。 Analytics Cloudは、選択されたDXPサイトのトラッキング分析を開始します。

    ![同期するサイトを選択します。](upgrading-a-data-source-connection-from-oauth-to-token-based/images/07.png)

### 連絡先の同期

連絡先を同期するには、以下の手順に従います。

1.  *Select Contacts*をクリックします。

    DXP内のすべてのユーザーを同期させることも、ユーザーグループや組織だけを同期させることもできます。

2.  同期するユーザーを選択し、保存をクリックします。 DXPが再びAnalytics Cloudに新しいコンタクトデータの送信を開始します。

![Analytics Cloudと同期するユーザーを選択します。](upgrading-a-data-source-connection-from-oauth-to-token-based/images/08.png)

## アップグレードされた接続を検証する

すべてが正しくアップグレードされていることを確認するには、以下の手順に従ってください。

1.  Analytics Cloud ワークスペースに戻ります。

2.  *設定* > *データソース*に移動して、アップグレードしたデータ・ソースに移動します。

3.  新しいデータソースの設定画面を表示します。 接続状態は *Connected*と表示されているはずです。

<!-- end list -->

``` note::
   データの同期に時間がかかります。 トラブルシューティングを行い、Analytics Cloudにデータが正しく同期されていることを確認するには、`データソースの接続 <.../.../troubleshooting/connecting-data-sources.md>`_ を参照してください。
```

これでDXPデータソースのトークン接続にアップグレードできました。
