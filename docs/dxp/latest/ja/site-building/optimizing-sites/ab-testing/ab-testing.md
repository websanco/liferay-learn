# A/Bテスト

> Liferay DXP 7.2 SP1以降で対応可能です。

A/Bテストでは、さまざまなユーザーのバリエーションをテストすることで、コンテンツの最も効果的なバージョンを判断できます。 訪問者の大多数はページの現在のコンテンツとデザインを表示し、一部のグループは代替の*バリアント*を表示します。 また、現在のページとバリアントのパフォーマンスをテストして、*直帰率*や*クリックスルー*など、特定の目標に対してどのバージョンのパフォーマンスが優れているかを判断できます。

次の例を考えてみましょう。 銀行のマーケティングチームが、新しいクレジットカードを宣伝するコンテンツページを作成します。 このページは数週間公開されていますが、再設計することで新しいクレジットカードをさらに宣伝できる可能性があります。 マーケティングチームは、A/Bテストを使用して、訪問者に対してランダムに新しいページのバリアントをテストし、各ページバージョンのクリック率を比較できます。 新しいバリアントが元のページよりも効果的である場合は、それを公開して元のページを置き換えることができます。

![A/Bテストを使用して2つの異なるCTAボタンの効率を比較します](./ab-testing/images/01.png)

A/Bテストを実行するには、Liferay DXPインスタンスを[Liferay Analytics Cloudに接続する](https://learn.liferay.com/analytics-cloud/latest/en/getting-started/connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.html)必要があります。 プロセスは次のとおりです。

  - Liferay DXPでA/Bテストを作成します。
  - A/BテストはAnalytics Cloudと自動的に同期します。
  - Liferay DXPまたはAnalytics CloudでA/Bテストを開始または終了します。
  - Liferay DXPとAnalytics Cloudは、テストが終了すると、テストのステータスと勝利したバリアントを表示します。
  - Analytics CloudでA/Bテストの他の側面（テスト履歴、統計、バリアント比較など）を管理します。

Analytics CloudでA/Bテストを行う詳細は、[A/B Testing](https://learn.liferay.com/analytics-cloud/latest/en/touchpoints/a-b-testing.html)を参照してください。

## 関連情報

  - [Verifying A/B Test Requirements](./verifying-ab-test-requirements.md)
  - [Creating A/B Tests](./creating-ab-tests.md)
  - [Running and Monitoring A/B Tests](./running-and-monitoring-ab-tests)
  - [Reviewing A/B Test Results and Publishing Test Variants](./reviewing-ab-test-results-and-publishing-test-variants.md)
