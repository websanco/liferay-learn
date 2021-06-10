# OAuthを使ったLiferay DXPの接続

``` warning::
   The OAuth connection method described here will be deprecated in the near future. If you are setting up a new data source for the first time, please use the `token connection <./connecting-liferay-dxp-to-analytics-cloud.md#using-the-access-token>`_ if possible. If you already are using OAuth to connect a data source, please see instructions on `upgrading from an OAuth based connection <./upgrading-a-data-source-connection-from-oauth-to-token-based.md>`_ to the token based connection.
```

以下の手順に従って、OAuth を使用してワークスペースを Liferay DXP インスタンスに接続します。

## Liferay DXP データソースの前提条件

### 必要なLiferay DXPフィックスパックのインストール

Liferay DXP 7.2.該当なし

Liferay DXP 7.1：Fix Pack 12を[ダウンロード](https://customer.liferay.com/downloads) 、[インストール](https://help.liferay.com/hc/articles/360018176571-Installing-Patches-)します。

Liferay DXP 7.0： Fix Pack 79を[ダウンロード](https://customer.liferay.com/downloads%20target=)、[インストール](https://help.liferay.com/hc/articles/360017896272-Using-the-Patching-Tool-#installing-patches)します。

## Liferay DXPインスタンスでAnalytics Cloudを登録する

> Liferay DXP 7.2およびDXP 7.1

1.  [Liferay Plugin for OAuth 2.0](https://web.liferay.com/marketplace/-/mp/application/109571986) バージョン1.1.0 (またはそれ以降)をダウンロードし、 [インストール](https://learn.liferay.com/dxp/7.x/en/system-administration/installing-and-managing-apps/installing-apps/installing-apps.html)してください。

2.  プラグインにはAnalytics Cloudの事前登録が付属しています。 次項で説明するように、DXPとAnalytics Cloudを接続するためのクライアントIDとクライアントシークレットをコピーします。

> Liferay DXP 7.0

1.  [Liferay Connector to OAuth 1.0a](https://web.liferay.com/marketplace/-/mp/application/45261909) と [をダウンロードし、](https://help.liferay.com/hc/articles/360017877192-Installing-Apps-Manually-) をインストールします。

2.  書き込みアクセスレベルを持つOAuthアプリケーションとしてAnalytics Cloudを[登録します](https://help.liferay.com/hc/en-us/articles/360018175331-OAuth-)。

3.  次項で説明するように、DXPとAnalytics Cloudを接続するためのConsumer IDとConsumer Secretをコピーします。

Liferay DXPインスタンスに接続するためにAnalytics Cloudを承認したことをお祝いします。 これで、Liferay DXPインスタンスをデータソースとして追加できるようになりました。

``` tip::
   If you have problems connecting to your DXP data source, refer to `Troubleshooting Liferay DXP <../../troubleshooting/connecting-data-sources.md>`_ Data Sources.
```

## DXPデータソースの追加

Liferay DXPデータソースを追加すると、Analytics CloudプロジェクトをLiferay DXPインスタンスに接続します。

1.  *設定* → *データソース*を選択します。 データソースのリストが表示されます。

2.  *データソースの追加*をクリックします。 データソースの追加] ページが表示されます。

3.  Liferay DXPアイコンを選択します。 *Liferay DXPの構成* ページが表示されます。

認証タブはデフォルトで選択されています。

### DXPデータソースオーソライズ

DXP インスタンスをデータソースとして認証するには、以下の手順に従います。

1.  データソースとクライアントの資格情報フィールドを入力します。

      - *説明*

          - **名前：** データ ソースの名前。
          - **URL：** Liferay DXPインスタンスのURL。

      - *クライアント認証情報*

          - **コンシューマー キー/クライアント ID：** Liferay DXPインスタンスにアクセスするためのAnalytics Cloudのキー/ID。
          - **コンシューマーシークレット/クライアントシークレット：** Liferay DXPインスタンスにアクセスするためのAnalytics Cloudのシークレット。

    Liferay DXP 7.1では、クライアントIDとシークレットは *コントロールパネル* → *設定* → *OAuth 2管理*にあります。

    Liferay DXP 7.0では、 *コントロールパネル* → *OAuth 管理*にConsumer KeyとSecretがあります。

2.  *認証*をクリックします。 ウィンドウが表示され、DXP インスタンスへのサインインを促すプロンプトが表示されます。

3.  DXP 管理者（管理者ロールを持つユーザー）の資格情報を入力してサインインし、*認証*をクリックします。

4.  保存をクリックして、認証オプションを保存します。 Analytics Cloudでは、[データ ソースの構成] タブの [データ構成] ページに進みます。 データ ソースの現在の状態は **AUTHENTICATED**です。

データソースの構成オプションがあります。

**アナリティクスを構成します。** アセットとタッチポイントのみを構成します。

**連絡先を構成します。** 連絡先データのみを構成します。

Analyticsの設定から始めます。

### 解析の設定

アナリティクスを設定することで、ウェブサイトで訪問者の閲覧イベントをAnalytics Cloudに送信できるようになります。 イベントは、クライアントブラウザ経由で `analytics.js`と送信されます。

1.  *アナリティクスの設定*は、*設定*をクリックします。 Liferay DXPサイトアナリティクスの登録ページが表示されます。

2.  アナリティクスに登録するLiferay DXPサイトを選択し、*設定*をクリックします。

3.  完了をクリックします。

選択したサイトがAnalytics Cloudに同期されるようになりました。

### Do Not Track の実装

GDPRや多くのデータプライバシー法によると、企業は訪問者がウェブサイトを閲覧する際にアナリティクスデータを収集する前に同意を求めることが義務付けられています。

特定のブラウザからアナリティクスデータが送信されないようにするには、サイトのjavascriptで次のウィンドウ変数を設定します。

    window['ac_client_disable_tracking'] = true

``` important::
   You must implement your own logic to persist user consent either by using a cookie or by saving and loading from your database. Please make sure that the window variable described above is set before the client page is fully loaded.
```

### 連絡先の設定

連絡先を設定すると、DXPのユーザーデータがインポートされます。 Liferay DXPインスタンスからAnalytics Cloudに連絡先を同期するには、次の手順を実行します。

1.  *連絡先の設定*は*設定*ボタンを選択します。 連絡先の設定オプションが表示されます。

2.  Liferay DXPインスタンスから同期する連絡先を選択します。 すべてのユーザーを選択するか、ユーザーグループまたは組織で選択します。 複数のユーザーグループや組織に所属する連絡先は、1回しかカウントされません。

    **すべての連絡先を同期します：** すべてのLiferay DXPインスタンスの連絡先を選択し、特定のユーザー・グループと組織を選択するためのオプションを無効にします。

    **ユーザーグループで同期します：** ユーザーグループごとに連絡先を選択します。

    **組織別に同期します：** 組織別に連絡先を選択します。

    ![Analytics Cloudでは、Liferay DXPインスタンスとその組織およびユーザーグループから連絡先を選択してインポートすることができます。](connecting-liferay-dxp-using-oauth/images/01.png)

3.  *保存*と*続ける*をクリックし、選択した連絡先をインポートします。 Analytics Cloudはコンタクト データをインポートして、Analytics Cloudのコンタクト データ モデルにマッピングします。 最初の連絡先データのインポートには、1,000人あたり5分半ほどかかることがあります。

4.  [コンタクトデータのマッピング](../../individuals-and-segments/individual-profiles/mapping-contact-data.md) の手順に従って、Liferay DXPインスタンスからAnalytics Cloudのコンタクトデータモデルにコンタクトデータをマッピングします。 データをマッピングしたら、 *保存*をクリックします。

5.  *データ構成* ページが再び表示されます。

Liferay DXPのコンタクトを使用するようにAnalytics Cloudを設定しました。

連絡先データは、Analytics Cloudへの同期が開始されます。 最初の同期の後、変更は定期的に同期されます。

## 次のステップ

  - [コンタクトデータのマッピング](../../individuals-and-segments/individual-profiles/mapping-contact-data.md)
  - [CSVデータソースを追加する](../../individuals-and-segments/individual-profiles/adding-a-csv-data-source.md)
  - [Salesforce データソースの追加](../../individuals-and-segments/individual-profiles/adding-a-salesforce-data-source.md)
