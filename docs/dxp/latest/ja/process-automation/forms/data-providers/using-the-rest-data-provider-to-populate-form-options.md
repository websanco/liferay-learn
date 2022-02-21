# RESTデータプロバイダーを使用してフォームオプションを入力する

［**リストから選択**］ フィールドには、多くのオプションを含めることができます。 これらのオプションは、Liferay DXPに登録されているJSON Web サービスや、その他のサードパーティーのREST Webサービスを使用して、自動的に提供することができます。 データプロバイダーの一般的な説明については、 [データプロバイダーの概要](./data-providers-overview.md) を参照してください。 RESTデータプロバイダーをフォームに適用するために、JSONウェブサービスを呼び出し、そこからデータを引き出すようにデータプロバイダーを設定し、フォームでデータプロバイダーを使用する方法を学びます。

<a name="前提条件" />

## 前提条件

一般的なニーズは、［選択］フィールドにオプションのリストを入力することです。たとえば、フォームで個人情報を収集する場合は、ユーザーの国が必要です。

1. **国** という 単一選択フィールドを含む[フォームを作成します](../creating-and-managing-forms/creating-forms.md)。


2. 次に、`get-countries` JSON Web サービスを使用します（2つありますが、どちらか一方を使用してください）。

   * ローカルでLiferayを動かしている場合は、 <http://localhost:8080/api/jsonws>に移動してください。
   * 「get-countries］と検索します。

   ![get-countries Webサービスを検索します。](./using-the-rest-data-provider-to-populate-form-options/images/02.png)

   ［***Invoke**］ をクリックします。

これで、国のリストが使用可能になりました。

### ローカルネットワーク上のデータへのアクセスを有効にする

デフォルトでは、ユーザーはデータプロバイダーがローカルネットワーク上のURLを使用するように設定することはできません。 このデフォルト設定は本番環境におけるセキュリティにとっては適切ですが、テストがしずらくなります。

データプロバイダーからローカルネットワークへのアクセスを有効にするには：

1. ［**コントロールパネル**］ → ［**設定**］ → ［**システム設定**］ に移動します。
1. ［コンテンツ & データ］の下にある ［**データプロバイダー**］ をクリックします。
1. ［**ローカルネットワークへのアクセス**］ チェックボックスにチェックを入れます。

    ![ローカルネットワークへのアクセス権限を付与します。](./using-the-rest-data-provider-to-populate-form-options/images/01.png)

1. 完了したら、 ［**保存**］ をクリックします。

<a name="基本的なrestデータプロバイダーの追加" />

## 基本的なRESTデータプロバイダーの追加

**Countries of the World** データプロバイダーを追加するには：

1. **プロダクトメニュー**（![Product Menu](../../../images/icon-product-menu.png)）を開き、 **サイト管理** メニューのコンパスアイコン（![Compass](../../../images/icon-compass.png)）をクリックします。
1. フォームを作成するサイトを選択します。
1. ［**コンテンツ & データ**］ &rarr; ［**フォーム**］ をクリックします。
1. ［**データプロバイダー**］ タブをクリックします。

    ![［データプロバイダー］タブに移動します。](./using-the-rest-data-provider-to-populate-form-options/images/03.png)

1. 追加ボタン（![Add](../../../images/icon-add.png)）をクリックし、RESTデータ・プロバイダーを追加します。
1. このデータを入力してください：

    ***名前** : Countries of the World
    ***URL** : `http://localhost:8080/api/jsonws/country/get-countries/`
    ***ユーザー名** : `adminuser@liferay.com`
    ***パスワード** : adminuserpass
    ***タイムアウト** : 1000
    ***出力のラベル** : Country Name
    ***出力パス** : `$..nameCurrentValue`
    ***出力タイプ** : List

    ![値を入力します。](./using-the-rest-data-provider-to-populate-form-options/images/04.png)

1. 完了したら、 ［**保存**］ をクリックします。

```{note}
   `nameCurrentValue` の前の `$..` は、JSONデータストラクチャーをナビゲートし、出力へのパスを指定するJsonPath構文です。 詳しくは [JsonPath](https://github.com/json-path/JsonPath) と [こちら](http://goessner.net/articles/JsonPath/) を参照してください。
```

<a name="restデータプロバイダーのフィルターとしての入力の使用" />

## RESTデータプロバイダーのフィルターとしての入力の使用

上記の例では、 ［**リストから選択］フィールド** に入力するための出力のみを使用しています。 多くの場合、RESTプロバイダーからの応答をフィルタリングしてから［リストから選択］フィールドに表示する必要があります。 そのためには、データプロバイダーの ［**Input**］ フィールドが必要です。

例えば、世界の国々に地域（アメリカ、ヨーロッパ、オセアニアなど）のフィルターを適用するとします。

1. このデータを入力してください：
   ***名前** : `restcountries`
   ***URL** : `https://restcountries.eu/rest/v2/region/{region}?fields=name`（別のRESTプロバイダーを使用）
   ***入力ラベル** : Region
   ***パラメーター** : Region
   ***インプットタイプ** : Text
   ***出力ラベル** : Countries of the World with Regions
   ***出力パス** : `$..name`
   ***出力タイプ** : List

   これらの値の詳細については、 [データプロバイダーの概要](./data-providers-overview.md) 参照してください。

<a name="データプロバイダーへの権限付与" />

## データプロバイダーへの権限付与

フォームに入力する前にユーザーの認証が必要かどうかによって、ユーザーはデータプロバイダーに追加の権限を付与する必要があります。 例えば、ゲストがフォームに記入する場合、ゲストには **表示** 権限が必要で、その権限がないと、データプロバイダーが提供するオプションを確認できません。

データプロバイダーの権限を設定するには：

1. ［**サイト管理**］ &rarr; ［**コンテンツ & データ**］ &rarr; ［**フォーム**］ に移動します。
1. ［データプロバイダー］タブをクリックします。
1. アクションボタン（![Actions](../../../images/icon-actions.png)）をクリックし、データプロバイダーの横にある ［**権限設定**］ をクリックします。

    ![ゲストの表示権限を付与します。](./using-the-rest-data-provider-to-populate-form-options/images/05.png)

1. 必要な権限を付与します。
1. 完了したら、 ［**保存**］ をクリックします。

<a name="選択フィールドでのデータプロバイダーを使用する" />

## 選択フィールドでのデータプロバイダーを使用する

データプロバイダーを設定したら、それを使用して［リストから選択］フィールドを入力します。

1. ［**コンテンツ & データ**］ &rarr; ［**フォーム**］ をクリックします。
1. ［**リストから選択**］ フィールドをフォームにドラッグします。
1. ［リストを作成］ドロップダウンメニューから ［**データプロバイダーから**］ を選択します。
1. ［データプロバイダーを選択］ドロップダウンメニューから ［**Countries of the World**］ を選択します。
1. ［出力パラメータの選択］ドロップダウンメニューから ［**Country Name**］ を選択します。

    ![［リストから選択］フィールドのデータプロバイダーの値を設定します。](./using-the-rest-data-provider-to-populate-form-options/images/06.png)

1. 完了したら、［**フォームを保存**］クリックします。

データプロバイダは、正しい権限を持ってフォームにアクセスしているユーザに対して、セレクトフィールドを生成するようになりました。

![フォームのユーザーは、データプロバイダーによって入力されたリストからオプションを選択します。](./using-the-rest-data-provider-to-populate-form-options/images/07.png)

<a name="データプロバイダーのエラーのトラブルシューティング" />

## データプロバイダーのエラーのトラブルシューティング

データプロバイダーの障害によるエラーを発見するには、これらのサービスに対して[ログレベルを設定](../../../system-administration/using-the-server-administration-panel/configuring-logging.md)します。

1. **コントロールパネル** &rarr; **システム** &rarr; **サーバ管理** へ行きます。
1. ［**ログレベル**］ タブをクリックします。
1. ロギングカテゴリ `com.liferay.dynamic.data.mapping.data.provider.internal.DDMDataProviderInvokerImpl` を追加し、 **WARN** レベルでログを記録するように設定します。 完了したら **保存** します。
1. ロギングカテゴリ `com.liferay.dynamic.data.mapping.form.field.type.internal.DDMFormFieldOptionsFactoryImpl` を追加し、 **WARN** レベルでログを記録するように設定します。 完了したら **保存** します。

データプロバイダーでエラーが発生した場合、コンソールが警告メッセージを送信するようになりました。

<a name="追加情報" />

## 追加情報

* [データプロバイダーの概要](./data-providers-overview.md)
* [自動入力ルールの使用](../form-rules/using-the-autofill-rule.md)
