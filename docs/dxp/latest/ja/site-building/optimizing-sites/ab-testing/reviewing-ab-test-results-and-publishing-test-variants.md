# A/Bテスト結果のレビューとテストバリアントの公開

A/Bテストが終了すると、Analytics CloudとLiferay DXPでテスト結果を確認できます。 Analytics Cloudは、テスト期間中に*必要信頼度*が達成されたときに、勝利のバリアントを宣言します。 テストに*必要信頼度*を設定する方法については、 [A/Bテストの作成](./creating-ab-tests.md) を参照してください。

```{tip}
A/Bテストが終了すると[Liferayで通知](../../../collaboration-and-social/notifications-and-requests/user-guide/managing-notifications-and-requests.md) が送信されます。
```

A/Bテストの結果を確認し、バリアントを公開するには、

1. A/Bテストが実行されているコンテンツページに移動します。
1. コントロールメニューで、*A/Bテスト*フラスコアイコン（![A/B Test icon](../../../images/icon-ab-testing.png)）をクリックします。 Liferay DXP 7.3以降では、（フラスコアイコンを介して）A/Bテストパネルにアクセスし、[エクスペリエンス選択ダイアログ](../../personalizing-site-experience/experience-personalization/creating-and-managing-experiences.md)からエクスペリエンスのテストステータスを表示することもできます。
1. コンテントページに他のエクスペリエンスがある場合は、*［エクスペリエンス］*を選択します。
1. *［Active Test］*セクションでテスト結果を確認します。

    * *Winner Declared*：テストバリアントの1つが必要信頼度を満たしました。
    * *No Winner*：テスト期間中に必要信頼度を満たしたテストバリアントはありませんでした。

    ![A/BテストパネルからA/Bテストの結果を確認します。](reviewing-ab-test-results-and-publishing-test-variants/images/01.png)

1. ［バリエーション］セクションから、次のことができます

    * チェックマークで強調表示されている勝利のバリアントを*公開*する。
    * 勝利していないバリアントを*公開*する。
    * *テストを破棄*して、A/Bテストの推奨事項を無視し、現在のコンテントページを維持する。

        ![勝利したバリアントを公開するか、A/Bテストの結果を破棄することができます。](reviewing-ab-test-results-and-publishing-test-variants/images/02.png)

    ```{note}
    A/Bテストバリアントの1つを公開すると、コンテンツページにアクセスするすべてのユーザーに対してバリアントが有効になります。
    ```

A/Bテストパネルの*［Analytics Cloudでデータを表示］*ボタンをクリックして、Analytics Cloudのダッシュボードに移動し、他のテスト統計を表示できます。 詳細は、 [A/Bテスト](https://learn.liferay.com/analytics-cloud/latest/ja/optimization/a-b-testing.html) を参照してください。

## 関連情報

* [A/Bテスト](./ab-testing.md)
* [A/Bテストの作成](./creating-ab-tests.md)
* [A/Bテスト要件の確認](./verifying-ab-test-requirements.md)
* [A/Bテストの実行と監視](./running-and-monitoring-ab-tests)
