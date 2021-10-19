# 自動入力ルールの使用

*自動入力*ルールは、事前に設定された条件に基づいて、フォーム項目のオプションを絞り込みます。 すでに[データプロバイダー](../data-providers/data-providers-overview.md)を有効にしている場合、*自動入力*ルールはフォームを拡張するための次のステップです。 詳しくは、[Using Data Providers to Populate Form Options](../data-providers/using-data-providers-to-populate-form-options.md)を参照してください。

以下のサンプルフォームでは、回答者に現金報酬か休暇のどちらかを選択するよう求めています。 後者を選択した場合、回答者は地域を選択し、次に国を選択する必要があります。 このフォームでは、自動入力ルールを使用して、地域に基づいて国をフィルタリングしています。

以下の手順に従ってください。

1.  [地域フィルターを含む世界の国々をインポートするデータプロバイダー](../data-providers/using-data-providers-to-populate-form-options.md)を設定します。

2.  次のフィールドを使用して[フォームを作成](../creating-and-managing-forms/creating-forms.md)します。

      - *[Cash]* または*[All expenses paid trip]* の2つのオプションがある*[Rewards]* という単一選択フィールド
      - *[Region]* という名前のテキストフィールド
      - [restcountries.eu](https://restcountries.eu)データプロバイダーを使用する*[Choose a Destination Country]* という名前の[リストから選択]フィールド

## 自動入力ルールの設定

1.  *[Rules]* タブをクリックします。

2.  *追加*（![Add](../../../images/icon-add.png)）ボタンをクリックします。

3.  If 条件から*[Reward]* を選択します。

4.  ルール：*[と等しい]* → *[Value]* → *[All expenses paid trip]* を作成します。

5.  *[Do]* アクションセレクタから*[自動入力]* を選択します。

6.  *[From Data Provider]* セレクタからデータプロバイダーを選択します。

7.  *[Region]* セレクタから*[Region]* を選択します。

8.  *[Country]* セレクタから*[Country]* を選択します。

    ![自動入力ルールを作成します。](./using-the-autofill-rule/images/01.png)

9.  完了したら、*[保存]* をクリックします。

## 自動入力ルールの検証

1.  フォームを公開します。

2.  フォームが表示されているサイトに移動します。

3.  [Region]フィールドに有効な地域を入力し、[リストから選択]フィールドのオプションが地域に応じてフィルタリングされることを確認します。 [restcountries.eu](https://restcountries.eu)サービスには、アフリカ、アメリカ、アジア、ヨーロッパ、オセアニア、極地の地域があります。

    ![世界の地域別に国を絞り込みます。](./using-the-autofill-rule/images/02.gif)

ユーザーは、地域を検索した後、フォーム内の国を検索できるようになりました。

## 追加情報

  - [フォームの作成](../creating-and-managing-forms/creating-forms.md)
  - [Data Providers Overview](../data-providers/data-providers-overview.md)
  - [Using Data Providers to Populate Form Options](../data-providers/using-data-providers-to-populate-form-options.md)
