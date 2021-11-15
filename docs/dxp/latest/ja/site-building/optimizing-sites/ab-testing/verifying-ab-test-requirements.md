# A/Bテスト要件の確認

コンテンツページでA/Bテストを実行する前に、次の要件が満たされていることを確認する必要があります。

  - Liferay DXPがAnalytics Cloudに接続されている。 この接続を設定する方法については、[Connecting your Liferay DXP site to Analytics Cloud](https://learn.liferay.com/analytics-cloud/latest/en/getting-started/connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.html)を参照してください。

  - ページが[コンテンツページ](../../creating-pages/understanding-pages/understanding-pages.md)である。 ウィジェットページは、異なるセグメントのエクスペリエンスをサポートしていません。

  - テストするコンテンツページが公開されている。

  - コンテントページの*アップデート*権限がある。

    ![Analytics CloudのLiferay DXP構成でサイトを選択する](verifying-ab-test-requirements/images/01.png)

    ```{note}
    When setting up the connection to Analytics Cloud, you must select the Site containing the content you want to test. Click *Control Panel* → *Configuration* → *Instance Settings* → *Analytics Cloud* → *Analytics Cloud Connection*.
    ```

*アップデート*権限を確認または設定するには、

1.  *[サイト管理者]* → *[サイトビルダー]* → *[Pages]* に移動します。
2.  コンテンツページの横にあるアクションメニュー（![Action Menu](../../../images/icon-actions.png)）をクリックし、*[権限設定]* を選択します。
3.  コンテンツページにアクセスする必要があるロールの*[アップデート]* 権限ボックスをオンまたは確認します。
4.  *[保存]* をクリックします。

## 関連情報

  - [A/B Testing](./ab-testing.md)
  - [Creating A/B Tests](./creating-ab-tests.md)
  - [Running and Monitoring A/B Tests](./running-and-monitoring-ab-tests)
  - [Reviewing A/B Test Results and Publishing Test Variants](./reviewing-ab-test-results-and-publishing-test-variants.md)
