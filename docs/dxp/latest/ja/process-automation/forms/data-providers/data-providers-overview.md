# データプロバイダーの概要

データ プロバイダーは、データをインポートするための[REST Webサービス](https://en.wikipedia.org/wiki/Representational_state_transfer)です。 特に大規模なデータセットでは、各データポイントを手動で入力する代わりに、データプロバイダーを使用してフィールドを自動入力することができます。 よくある例としては、データプロバイダーを使用して、国とその行政区域のリストをインポートできます。 このチュートリアルでは、DXP*フォーム*でデータプロバイダーを使用する方法を説明します。

## 登録済みのJSON Webサービス

データソースの中には、 [restcountries.eu](https://restcountries.eu)データプロバイダーのようなサードパーティーのソースから取得するものもあります。 Liferay DXPには、データプロバイダーのサービスとして機能する独自の登録済みのWebサービスもあります。  ローカルサーバーを実行している場合は、<http://localhost:8080/api/jsonws>でリストを参照してください。 国のリストを入力する場合、2つの`get-countries` JSON Web サービスがありますが、どちらでも動作します。 *[Invoke]* をクリックすると、結果が生成されます。

*[Result]* タブには、次のアフガニスタンのレコードのように、JSON構文を使用した国のリストが表示されます。

``` json
[
  {
    "a2": "AF",
    "a3": "AFG",
    "countryId": "20",
    "idd": "093",
    "mvccVersion": "0",
    "name": "afghanistan",
    "nameCurrentValue": "Afghanistan",
    "number": "4"
  },
    ...
```

Webサービスから選択可能なフィールドを選択します。 `get-countries`の場合は、適切に大文字で表記された国の完全な名前が含まれているため、ほとんどの場合`nameCurrentValue`となります。

[URL例]タブで、データプロバイダーの作成時に使用するURLを探します。 これは、`get-countries` JSON Web サービスにアクセスする際に生成されるものと同じです。 ユーザーは、登録されているJSON Web サービスのURLを、これと同じ手順で見つけることができます。

![[URL例]タブには、対応するJSON Webサービスが表示されます。](./data-providers-overview/images/02.png)

## データプロバイダー設定リファレンス

フォームアプリケーションからデータプロバイダーを設定するには、*[サイト管理]* → *[Content & Data]* → *[フォーム]* に移動します。 *[データプロバイダー]* タブをクリックし、（![Add icon](../../../images/icon-add.png)）アイコンをクリックして開始します。 データプロバイダーを設定する際には、いくつかのフィールドに入力する必要があります。

![このデータサービスでは、国を返します。](./data-providers-overview/images/03.png)

### URL

内部または外部のRESTサービスのエンドポイントのURLを入力します。 上記の例では、<https://restcountries.eu/>にあるRESTサービスを示していて、このサービスには`region`で国を検索するエンドポイントが含まれています。

`https://restcountries.eu/rest/v2/region/{region}`

データプロバイダーのURLには、パスパラメーターとクエリパラメーターの2種類のパラメーターを指定できます。

パスパラメーターは、REST Webサービスを呼び出すURLの一部で、`https://service-url.com/service/{path_parameter_name}`というパターンを使用して追加されます。

たとえば、 `restcountries.eu`サービスの`region`エンドポイントのパスパラメーターは`{region}`です。 パスパラメーターはURLの必須部分なので、URLのパスパラメーターに一致する *[パラメーター]* フィールド値を持つ入力（下記参照）を指定してください。

クエリパラメーターは、サービス呼び出しの出力を絞り込むためのURLの補完的な部分で、`?query_parameter=query_parameter_value`というパターンに従います。

    https://restcountries.eu/rest/v2/all?fields=capital

パスパラメーターとは異なり、クエリパラメーターはオプションです。

### ユーザー名とパスワード

必要に応じて、REST Webサービスへの認証に使用する認証情報を入力します。

### Cache data on the first request

データがキャッシュされている場合は、RESTサービス・プロバイダーへの2回目の呼び出しが不要になるため、リストを選択フィールドの2回目のロードがより速くなります。

### タイムアウト

リクエストを中止する前にREST呼び出しの応答を待機する時間（ミリ秒単位）を入力します。

### Inputs

RESTサービスからのパスパラメーターまたはクエリパラメーターを設定して、RESTサービスの応答を絞り込みます。 ラベル、パラメーター、タイプ（テキストまたは数値）を指定し、データプロバイダーを使用するために入力が必要かどうかを選択します。

### Outputs

出力は、オートコンプリートが有効なリストから選択フィールドまたはテキストフィールドに表示するパラメーターです。 複数の出力を追加することができます。 出力は入力によって絞り込むことができますが（上記参照）、入力フィルタリングを設定せずに表示することもできます。 ラベル、パス、タイプ（テキスト、数字、リスト）を指定します。

複数の入力を追加することができます。 ユーザーが入力値を指定できるようにするには、[フォームに自動入力ルール](../form-rules/using-the-autofill-rule.md)を使用します。 ユーザーが1つのフィールドに入力すると、その入力内容がRESTサービスに送信されます。 RESTサービスの応答データは、入力パラメータによってフィルタリングされます。

出力パスフィールドは[JsonPath構文](https://github.com/json-path/JsonPath)で指定されるため、必ず`$`で始まる必要があります。 パスが返すデータのタイプは、[Type]フィールドで選択したタイプと一致する必要があります。 `restcountries.eu` サービスを使用して、[パス]フィールドに`$..name`と入力することで、`name`フィールドを出力として指定します。 より複雑なJsonPath式を構築する必要がある場合（たとえば、人口1億人以上のすべての国の名前が必要な場合 --- `restcountries.eu`サービスを使用した `$..[?(@.population>100000000)].name`）は、[こちら](http://jsonpath.herokuapp.com/)や[こちら](https://jsonpath.com/)のように`JsonPath`エバリュエーターを使用することを検討してください。

```{tip}
ある値を表示し、別の値をデータベースに保存するには、[Paths]フィールドにセミコロンで区切って両方を入力します（`$..name;$..numericCode`）。
```

`restcountries.eu`データプロバイダーを使用している場合は、ユーザーには国名が表示され、データベースには数字の国コードが保存されます。

![RESTサービスから取得したデータを表示するためのデータプロバイダーを設定します。](./data-providers-overview/images/01.png)

## 次のステップ

  - [Using Data Providers to Populate Form Options](./using-data-providers-to-populate-form-options.md)
  - [Using the Autofill Rule](../form-rules/using-the-autofill-rule.md)
