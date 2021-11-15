# A/Bテストの実行と監視

A/Bテストを実行するときに、次のパラメーターを設定できます。

  - *トラフィック分割：* ページを訪問するときに、ランダムにバリアント間で分割される訪問者の割合。 ランダムにバリアントを割り当てられた訪問者は、テストが終了するまで常に同じバリアントを見ています。
  - *必要信頼度*：テストの精度を表します。 必要信頼度が高いほど、勝者のバリアントを宣言するまでの時間が長くなります。

*勝者を宣言するための予想時間*は、試験時間の予測値を提供します。 この予測値は、*トラフィック分割*と*必要信頼度*の設定、および予想ページトラフィック（Analytics Cloudによって提供されるトラフィック履歴に基づく）に基づいています。

![A/Bテスト実行の設定は、要件に合わせて調整できます。](running-and-monitoring-ab-tests/images/01.png)

テストを作成したら、*[A/Bテスト]* ボタン（![A/B Test icon](../../../images/icon-ab-testing.png)）をクリックして、テストステータスと完了および終了したA/Bテストの履歴を確認します。 Liferay DXP 7.3以降では、[エクスペリエンス選択ダイアログ](../../personalizing-site-experience/experience-personalization/content-page-personalization.md)からエクスペリエンスのA/Bテストのステータスを表示することもできます。

Liferay DXPは、テストが終了したときに、テストのステータスと勝利したバリアントのみを表示します。 Analytics CloudでA/Bテストの他の側面を管理できます。 詳細は、[A/B Testing Analytics](https://learn.liferay.com/analytics-cloud/latest/en/touchpoints/a-b-testing.html)を参照してください。

A/Bテストが終了したら、テスト結果を確認して、優先テストバリアントを公開できます。 詳細は、[Reviewing A/B Test Results and Publishing Test Variants](./reviewing-ab-test-results-and-publishing-test-variants.md)を参照してください。

## A/Bテストの実行

```{note}
Before running the test, you must create the A/B Test and test Variant. For more information, read [Creating A/B Tests](./creating-ab-tests.md).
```

1.  A/Bテストを実行するコンテンツページに移動します
2.  コントロールメニューで、*[A/Bテスト]* ボタン（![A/B Test icon](../../../images/icon-ab-testing.png)）をクリックします。
3.  コンテンツページに追加のエクスペリエンスがある場合は、[エクスペリエンス]を選択します。
4.  *[有効なテスト]* セクションの下で、*[テストをレビューして実行する]* をクリックします。
5.  オプションで、テストの*[トラフィック分割]* および*[必要信頼度]* の設定を構成します。
6.  *[Run]* をクリックし、*[OK]* をクリックします。

*[テストを終了]* ボタンを使用して、実行中のテストをいつでもキャンセルできます。 A/Bテストを削除するには、最初にテストをキャンセルする必要があります。

## 関連情報

  - [A/B Testing](./ab-testing.md)
  - [Verifying A/B Test Requirements](./verifying-ab-test-requirements.md)
  - [Running and Monitoring A/B Tests](./running-and-monitoring-ab-tests)
  - [Reviewing A/B Test Results and Publishing Test Variants](./reviewing-ab-test-results-and-publishing-test-variants.md)
