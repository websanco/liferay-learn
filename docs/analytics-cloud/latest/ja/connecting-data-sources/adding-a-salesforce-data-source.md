# Salesforce データソースの追加

Analytics Cloudは、SalesforceのデータとLiferay DXPなどのデータを統合し、自動的にデータをブレンドして包括的な顧客プロファイルを構築することができます。 これにより、より正確なセグメンテーションを作成し、サイトのパーソナライズ戦略を強化することができます。 これを行うには、まず、Salesforceインスタンスをデータソースとして追加する必要があります。

<a name="adding-the-data-source" />

## データソースの追加

1. ［**設定**］ → ［**データソース**］ → ［**データソースを追加**］ を選択します。 データソースの追加画面が開きます。

1. **Salesforce** を選択します。 これで、［Configure Salesforce］画面が開きます。

    ![データソースの追加画面からSalesforceを選択します。](adding-a-salesforce-data-source/images/01.png)

1. **AUTHORIZATION** タブで、Salesforceインスタンスの名前と URLを入力します。 **CLIENT CREDENTIALS** セクションで、Salesforce で OAuth 接続されているアプリのクライアントIDとクライアントシークレットを入力します。

    ```{note}
       Salesforceインスタンスの管理者は、次の設定でこの接続アプリケーションを作成する必要があります

       * コールバックURL: https://analytics.liferay.com/oauth/receive
       * OAuthスコープ： - 基本情報（ID、プロファイル、電子メールアドレス、電話）- データへのアクセスと管理（api）- いつでもユーザーに代わってリクエストを実行（refresh **token、offline** access）

       SalesforceでOAuth接続アプリを作成し、そのクライアントIDとクライアントシークレットを見つける手順については、 [Salesforceのドキュメント](https://help.salesforce.com/articleView?id=connected_app_overview.htmtype=5) を参照してください。
    ```

1. **Authorize & Save** をクリックします。 これにより、Salesforceからリード、連絡先、アカウントのデータのインポートが開始されます。 このデータは、以下のようにAnalytics Cloudのデータと統合されています。

    * Salesforceのリードやコンタクトからのデータと、Analytics Cloudで個人をマッチングさせるためのデータをマージします。 メールアドレスで一致しています。 たとえば、コンタクトおよび/またはリードがAnalytics Cloudのユーザーと同じメールアドレスを持っている場合、そのコンタクトおよび／またはリードのデータは、その個人のAnalytics Cloudのデータにマージされます。
    * リードおよび／またはコンタクトのメールアドレスが、Analytics Cloudの既存のユーザーのメールアドレスと一致しない場合、リードおよび／またはコンタクトからのデータを使用して、Analytics Cloudに新しいユーザーが作成されます。
    * コンタクトからのデータはリードよりも優先されます。 たとえば、マッチングしたリードとコンタクトに同じフィールドが入力されている場合、コンタクトからのデータはAnalytics Cloudのユーザーにインポートされます。

  データのインポートには、Salesforceインスタンスにどれだけのデータが存在するかによっては、時間がかかる場合があることに注意してください。

1. インポートの進行状況は、 ［**CONFIGURE DATA SOURCE**］ タブに表示されます。 ［View］をクリックすると、各Salesforceフィールド (［Accounts］と［Individuals］） と対応するAnalytics Cloudフィールドのマッピングが表示されます。 また、この情報は後から、 **設定** → **データソース** → (**Salesforce**) → **CONFIGURE DATA SOURCE** から見ることができます。

![Salesforce インスタンスに接続するために必要な情報を入力します。](adding-a-salesforce-data-source/images/02.png)

![CONFIGURE DATA SOURCE タブには、Salesforce からインポートされたアカウントと個人のステータス、およびフィールドマッピングが表示されます。](adding-a-salesforce-data-source/images/03.png)
