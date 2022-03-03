# 自動入力ルールの使用

**自動入力** ルールは、事前に設定された条件に基づいて、フォーム項目のオプションを絞り込みます。 すでに[データプロバイダー](../data-providers/data-providers-overview.md)を有効にしている場合、 **自動入力** ルールはフォームを拡張するための次のステップです。 詳細は、、 [RESTデータプロバイダーを使用したフォームオプションの入力](../data-providers/using-the-rest-data-provider-to-populate-form-options.md) を参照してください。

以下のサンプルフォームでは、回答者に現金報酬か休暇のどちらかを選択するよう求めています。 後者を選択した場合、回答者は地域を選択し、次に国を選択する必要があります。 このフォームでは、自動入力ルールを使用して、地域に基づいて国をフィルタリングしています。

以下の手順に従ってください。

1. [地域フィルターを含む世界の国々をインポートするデータプロバイダー](../data-providers/using-the-rest-data-provider-to-populate-form-options.md)を設定します。
1. 次のフィールドを使用して[フォームを作成](../creating-and-managing-forms/creating-forms.md)します。

    ［***Cash**］ または ［**All expenses paid trip**］ の2つのオプションがある ［**Rewards**］ という単一選択フィールド
    ［***Region**］ という名前のテキストフィールド
    * [restcountries.eu](https://restcountries.eu) データプロバイダーを使用する ［**Choose a Destination Country**］ という名前の［リストから選択］フィールド

<a name="configuring-the-autofill-rule" />

## 自動入力ルールの設定

1. ［**ルール**］ タブをクリックします。

1. **追加**（![Add](../../../images/icon-add.png)）ボタンをクリックします。

1. If 条件から ［**Reward**］ を選択します。

1. ルールの作成： **Is equal to** &rarr; **Value** &rarr; **All expense paid trip** .

1. ［**Do**］ アクションセレクタから ［**自動入力**］ を選択します。

1. ［**From Data Provider**］ セレクタからデータプロバイダーを選択します。

1. ［**地域(都道府県**)］ セレクタから ［**地域(都道府県**)］ を選択します。

1. ［**国**］ セレクタから ［**国**］ を選択します。

    ![自動入力ルールを作成します。](./using-the-autofill-rule/images/01.png)

1. 完了したら、 ［**保存**］ をクリックします。

<a name="verifying-the-autofill-rule" />

## 自動入力ルールの検証

1. フォームを公開します。

1. フォームが表示されているサイトに移動します。

1. ［地域(都道府県)］フィールドに有効な地域を入力し、［リストから選択］フィールドのオプションが地域に応じてフィルタリングされることを確認します。 [restcountries.eu](https://restcountries.eu) サービスには、アフリカ、アメリカ、アジア、ヨーロッパ、オセアニア、極地の地域があります。

    ![世界の地域別に国を絞り込みます。](./using-the-autofill-rule/images/02.gif)

ユーザーは、地域を検索した後、フォーム内の国を検索できるようになりました。

<a name="additional-information" />

## 追加情報

* [フォームの作成](../creating-and-managing-forms/creating-forms.md)
* [データプロバイダーの概要](../data-providers/data-providers-overview.md)
* [RESTデータプロバイダーを使用してフォームオプションを入力する](../data-providers/using-the-rest-data-provider-to-populate-form-options.md)
