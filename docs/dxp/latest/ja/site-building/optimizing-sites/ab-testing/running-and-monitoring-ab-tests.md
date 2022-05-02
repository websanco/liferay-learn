# A/Bテストの実行と監視

A/Bテストを実行するときに、次のパラメーターを設定できます。

- *トラフィック分割：* ページを訪問するときに、ランダムにバリアント間で分割される訪問者の割合。 訪問者はランダムにバリアントに割り当てられ、A/Bテストが終了するまで常に同じバリアントが表示されます。
- *必要信頼度*：テストの精度を表します。 必要信頼度が高いほど、勝者のバリアントを宣言するまでの時間が長くなります。

*勝者を宣言するための予想時間*は、試験時間の予測値を提供します。 この予測値は、*トラフィック分割*と*必要信頼度*の設定、および予想ページトラフィック（Analytics Cloudによって提供されるトラフィック履歴に基づく）に基づいています。

![A/Bテスト実行の設定は、要件に合わせて調整できます。](running-and-monitoring-ab-tests/images/01.png)

テストを作成したら、*［A/Bテスト］*ボタン（![A/B Test icon](../../../images/icon-ab-testing.png)）をクリックして、テストステータスと完了および終了したA/Bテストの履歴を確認します。 Liferay DXP 7.3以降では、[エクスペリエンス選択ダイアログ](../../personalizing-site-experience/experience-personalization/creating-and-managing-experiences.md)からエクスペリエンスのA/Bテストのステータスを表示することもできます。

Liferay DXPは、テストが終了したときに、テストのステータスと勝利したバリアントのみを表示します。 Analytics CloudでA/Bテストの他の側面を管理できます。 詳細については、Analytics Cloudのドキュメントの [A/Bテスト](https://learn.liferay.com/analytics-cloud/latest/ja/optimization/a-b-testing.html) を参照してください。

A/Bテストが終了したら、テスト結果を確認して、優先テストバリアントを公開できます。 詳細は、 [A/Bテスト結果のレビューとテストバリアントの公開](./reviewing-ab-test-results-and-publishing-test-variants.md) を参照してください。

## A/Bテストの実行

```{note}
テストを実行する前に、A/Bテストを作成してバリアントをテストする必要があります。 詳細については、 [A/Bテストの作成](./creating-ab-tests.md) をご覧ください。
```

1. A/Bテストを実行するコンテントページに移動します。
1. コントロールメニューで、*［A/Bテスト］*ボタン（![A/B Test icon](../../../images/icon-ab-testing.png)）をクリックします。
1. コンテントページに他のエクスペリエンスがある場合は、［エクスペリエンス］を選択します。
1. *［有効なテスト］*セクションの下で、*［テストをレビューして実行する］*をクリックします。
1. オプションで、テストの*［トラフィック分割］*および*［必要信頼度］*の設定を構成します。
1. *［Run］*をクリックし、*［OK］*をクリックします。

*［テストを終了］*をクリックすると、実行中のテストをいつでもキャンセルできます。 完了したテストやキャンセルされたテストの記録は、［履歴］タブで確認できます。 A/Bテストを削除するには、最初にテストを終了する必要があります。

## 関連情報

- [A/Bテスト](./ab-testing.md)
- [A/Bテスト要件の確認](./verifying-ab-test-requirements.md)
- [A/Bテストの実行と監視](./running-and-monitoring-ab-tests)
- [A/Bテスト結果のレビューとテストバリアントの公開](./reviewing-ab-test-results-and-publishing-test-variants.md)
