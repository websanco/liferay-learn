# A/Bテスト要件の確認

コンテンツページでA/Bテストを実行する前に、次の要件が満たされていることを確認する必要があります。

- Liferay DXPがAnalytics Cloudに接続されている。 この接続を設定する方法については、 [Liferay DXPをAnalytics Cloudに接続する](https://learn.liferay.com/analytics-cloud/latest/ja/connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.html) を参照してください。

- ページが[コンテントページ](../../creating-pages/understanding-pages/understanding-pages.md)である。 ウィジェットページは、異なるセグメントのエクスペリエンスをサポートしていません。
- テストするコンテントページが公開されている。
- コンテントページの*アップデート*権限がある。

    ![Analytics CloudのLiferay DXP構成でサイトを選択する](verifying-ab-test-requirements/images/01.png)

    ```{note}
    Analytics Cloudへの接続を設定するときは、テストするコンテンツを含むサイトを選択する必要があります。 *コントロールパネル*→*設定*→*Instance Settings*→* Analytics Cloud *→*Analytics Cloud接続*をクリックします。
    ```

*アップデート*権限を確認または設定するには、

1. *［サイト管理者］* &rarr; *［サイトビルダー］* &rarr; *［Pages］*に移動します。
1. コンテンツページの横にあるアクションメニュー（![Action Menu](../../../images/icon-actions.png)）をクリックし、*［権限設定］*を選択します。
1. コンテントページにアクセスする必要があるロールの*［アップデート］*権限ボックスをオンまたは確認します。
1. *［保存］*をクリックします。

## 関連情報

- [A/Bテスト](./ab-testing.md)
- [A/Bテストの作成](./creating-ab-tests.md)
- [A/Bテストの実行と監視](./running-and-monitoring-ab-tests)
- [A/Bテスト結果のレビューとテストバリアントの公開](./reviewing-ab-test-results-and-publishing-test-variants.md)
