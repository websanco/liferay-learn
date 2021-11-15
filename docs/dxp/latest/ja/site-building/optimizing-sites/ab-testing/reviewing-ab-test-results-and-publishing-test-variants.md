# A/Bテスト結果のレビューとテストバリアントの公開

A/Bテストが終了すると、Analytics CloudとLiferay DXPでテスト結果を確認できます。 Analytics Cloudは、テスト期間中に*必要信頼度*が達成されたときに、勝利のバリアントを宣言します。 テストに*必要信頼度*を設定する方法については、[Creating A/B Tests](./creating-ab-tests.md)を参照してください。

```{note}
When you publish one of the A/B Test Variants, the Variant becomes active for all users visiting the Content Page.
```

A/Bテストの結果を確認し、バリアントを公開するには、

1.  A/Bテストが実行されているコンテンツページに移動します。

2.  コントロールメニューで、*[A/Bテスト]* フラスコアイコン（![A/B Test icon](../../../images/icon-ab-testing.png)）をクリックします。 Liferay DXP 7.3以降では、（フラスコアイコンを介して）A/Bテストパネルにアクセスし、[エクスペリエンス選択ダイアログ](../../personalizing-site-experience/experience-personalization/content-page-personalization.md)からエクスペリエンスのテストステータスを表示することもできます。

3.  コンテンツページに追加のエクスペリエンスがある場合は、*[エクスペリエンス]* を選択します。

4.  *[有効なテスト]* セクションでテスト結果を確認します。

      - *Winner Declared*：テストバリアントの1つが必要信頼度を満たしました。
      - *No Winner*：テスト期間中に必要信頼度を満たしたテストバリアントはありませんでした。

    ![A/BテストパネルからA/Bテストの結果を確認します。](reviewing-ab-test-results-and-publishing-test-variants/images/01.png)

5.  *[バリアント]* セクションには、次のオプションがあります。

      - チェックマークで強調表示されている勝利のバリアントを*公開*する。
      - 勝利していないバリアントを*公開*する。
      - *テストを破棄*して、A/Bテストの推奨事項を無視し、現在のコンテンツページを維持する。

    ![勝利したバリアントを公開するか、A/Bテストの結果を破棄することができます。](reviewing-ab-test-results-and-publishing-test-variants/images/02.png)

A/Bテストパネルの*[Analytics Cloudでデータを表示]* ボタンをクリックして、Analytics Cloudのダッシュボードに移動し、他のテスト統計を表示できます。 詳細は、[A/B Testing Analytics](https://learn.liferay.com/analytics-cloud/latest/en/touchpoints/a-b-testing.html#summary)を参照してください。

## 関連情報

  - [A/B Testing](./ab-testing.md)
  - [Creating A/B Tests](./creating-ab-tests.md)
  - [Verifying A/B Test Requirements](./verifying-ab-test-requirements.md)
  - [Running and Monitoring A/B Tests](./running-and-monitoring-ab-tests)
