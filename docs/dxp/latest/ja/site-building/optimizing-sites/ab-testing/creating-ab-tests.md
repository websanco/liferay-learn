# A/Bテストの作成

A/Bテストを作成する前に、次の点に留意してください。

  - デフォルトのエクスペリエンスまたはセグメントにマップされたパーソナライズされたエクスペリエンスのテストを作成できます。
  - A/Bテストは、一度に1つのページまたはエクスペリエンスに対してのみ作成できます。
  - 実行中のA/Bテストの一部であるエクスペリエンスを編集することはできません。
  - アクティブなA/Bテストがすでに実行されているエクスペリエンスに対しテストを作成することはできません。
  - A/Bテストの一部であるコンテンツページまたはエクスペリエンスを削除すると、テストも削除されます。

A/Bテストを作成するには、最初に[テストを作成](#creating-the-test)してから、1つ以上の[テストバリアント](#creating-the-test-variant)を作成します。

```{important}
Verify that your environment [meets the requirements](./verifying-ab-test-requirements.md) for A/B Testing before creating the test.
```

## テストの作成

1.  テストするコンテンツページに移動します。

2.  コントロールメニューで、*[A/Bテスト]*（![A/B Test icon](../../../images/icon-ab-testing.png)）をクリックします。

3.  コンテンツページに追加のエクスペリエンスがある場合は、テストするエクスペリエンスを選択します。

4.  *[テストを作成]* をクリックします。

5.  テストの*[名前]* を入力し、オプションで*[説明]* を入力します。

6.  テストで追跡する目標を選択します。

      - *直帰率*：訪問したがアクティビティ（*クリック*や*スクロール*など）を示さず、別のページにアクセスせずに去った人の割合。
      - *クリック*：セッションごとのページをクリックした人の割合。

7.  *[保存]* をクリックして、テストを*下書き*として保存します（訪問者にはまだ表示されません）。

    ```{note}
    You can always edit or delete the new A/B test by clicking the *Actions* button in the top right of the A/B Test menu. Deleted tests are not recoverable (i.e., not sent to the Recycle Bin). These options are not available for an active running test.
    ```

## テストバリアントの作成

テストバリアントは、A/Bテストで最適化するエクスペリエンスをカスタマイズしたものです。 各A/Bテストには、少なくとも1つのバリアントが必要です。

1.  コンテンツページのA/Bテストサイドバーパネルを開きます。

2.  A/Bテストで*クリック*目標を選択した場合は、テストする要素も選択する必要があります。

    1.  *[有効なテスト]* タブの*[目標をクリック]* セクションで、*[要素を設定]* をクリックします。

        ![[要素を設定]をクリックして、テスト用の要素を設定します](./creating-ab-tests/images/03.png)

    2.  コンテンツページでテストする要素をクリックします。

    3.  *[要素をクリック対象に設定]* をクリックします。

        ![[要素をクリック対象に設定]ボタンをクリックして選択します。](./creating-ab-tests/images/01.png)
    
    <!-- end list -->
    
    ```{note}
    Only links and buttons with an ID attribute can be selected as a target for the click goal. The click target element applies to the whole A/B Test, and must be present in all variants.
    ```

3.  *[有効なテスト]* タブの*[バリアント]* セクションで、*[バリエーションを作成]* をクリックします。

4.  バリアントの名前を入力し、*[保存]* をクリックします。

5.  バリアント名の横にある*編集*ボタン（![Edit icon](../../../images/icon-edit.png)）をクリックします。 現在のコンテンツページがバリアントのベースラインとして表示されます。

6.  テストする変更を加えてバリアントを編集します。 たとえば、別のCTAテキストをテストする場合は、ボタンを新しいテキストで更新します。

7.  *[バリエーションを保存]* をクリックします。

テストとテストバリアントを作成した後、[テストを実行](./running-and-monitoring-ab-tests)できます。

## 関連情報

  - [Running and Monitoring A/B Tests](./running-and-monitoring-ab-tests)
  - [A/B Testing](./ab-testing.md)
  - [Configuring A/B Testing](./creating-ab-tests.md)
  - [Reviewing A/B Test Results and Publishing Test Variants](./reviewing-ab-test-results-and-publishing-test-variants.md)
