# Salesforce データソースの追加

Analytics Cloudは、SalesforceのデータとLiferay DXPなどのデータを統合し、自動的にデータをブレンドして包括的な顧客プロファイルを構築することができます。 これにより、より正確なセグメンテーションを作成し、サイトのパーソナライズ戦略を強化することができます。 これを行うには、まず、Salesforce インスタンスをデータソースとして追加する必要があります。

## データソースの追加

1.  *設定* → *データソース* → *データソースを追加*を選択します。 データソースの追加画面が開きます。

2.  *Salesforce*を選択します。 これで、Salesforce の構成画面が開きます。

    ![データソースの追加画面からSalesforceを選択します。](adding-a-salesforce-data-source/images/01.png)

3.  *権限* タブで、Salesforce インスタンスの名前と URL を入力します。 *CLIENT CREDENTIALS* セクションで、Salesforce で OAuth 接続されているアプリのクライアント ID とクライアントシークレットを入力します。

    ``` note::
       the Salesforce instance’s administrator must create this connected app with the following settings:

       * Callback URL: https://analytics.liferay.com/oauth/receive
       * OAuth Scopes: - Access your basic information (id, profile, email address, phone) - Access and manage your data (api) - Perform requests on your behalf at any time (refresh_token, offline_access)

       For instructions on creating an OAuth connected app in Salesforce and locating its client ID and client secret, see the `Salesforce documentation <https://help.salesforce.com/articleView?id=connected_app_overview.htm&type=5>`_.
    ```

4.  *Authorize & Save*をクリックします。 これにより、Salesforceからリード、連絡先、アカウントのデータのインポートが開始されます。 このデータは、以下のようにAnalytics Cloudのデータと統合されています。

      - Salesforceのリードやコンタクトからのデータと、Analytics Cloudで個人をマッチングさせるためのデータをマージします。 メールアドレスで一致しています。 たとえば、コンタクトおよび/またはリードがAnalytics Cloudの個人と同じメールアドレスを持っている場合、そのコンタクトおよび/またはリードのデータは、その個人のAnalytics Cloudのデータにマージされます。
      - リードおよび/またはコンタクトのメールアドレスが、Analytics Cloudの既存の個人のメールアドレスと一致しない場合、リードおよび/またはコンタクトからのデータを使用して、Analytics Cloudに新しい個人が作成されます。
      - コンタクトからのデータはリードよりも優先されます。 たとえば、マッチングしたリードとコンタクトに同じフィールドが入力されている場合、コンタクトからのデータはAnalytics Cloudの個人にインポートされます。

データのインポートには、Salesforce インスタンスにどれだけのデータが存在するかによっては、時間がかかる場合があることに注意してください。

1.  インポートの進行状況は、 *CONFIGURE DATA SOURCE* タブに表示されます。 表示] をクリックすると、各 Salesforce フィールド (アカウントと個人用) と対応する Analytics Cloud フィールドのマッピングが表示されます。 また、この情報は後から、*設定* → *データソース* → *(Salesforce)* → *CONFIGURE DATA SOURCE*から見ることができます。

![Salesforce インスタンスに接続するために必要な情報を入力します。](adding-a-salesforce-data-source/images/02.png)

![CONFIGURE DATA SOURCE タブには、Salesforce からインポートされたアカウントと個人のステータス、およびフィールドマッピングが表示されます。](adding-a-salesforce-data-source/images/03.png)
