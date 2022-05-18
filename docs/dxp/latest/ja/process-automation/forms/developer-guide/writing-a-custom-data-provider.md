# カスタムデータプロバイダーの作成

Liferay Formsのフィールドは、 [データプロバイダー](../data-providers/data-providers-overview.md)を使用して入力することができます。 初期設定のRESTデータプロバイダーは、ほとんどのRESTエンドポイントからデータを消費するための柔軟な方法を提供します。 詳細は、 [RESTデータプロバイダーを使用したフォームオプションの入力](../data-providers/using-the-rest-data-provider-to-populate-form-options.md) を参照してください。

RESTデータプロバイダーが目的に合わない場合は、`DDMDataProvider`拡張ポイントを使用して、独自のデータプロバイダーを作成します。

```{note}
このデータプロバイダーの例では、 [GeoDataSource™ Location Search Web Service](https://www.geodatasource.com/web-service/location-search) から XML データを消費します。 このサンプルには、Liferay社員のAPIキーがハードコードされています。 サンプルを使いすぎないようにしてください。 本番環境では絶対に使用しないでください。
```

<a name="deploy-a-custom-data-provider" />

## カスタムデータプロバイダーをデプロイする

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. Acme XML Data Providerをダウンロードし、解凍する。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/process-automation/forms/developer-guide/liferay-b4d8.zip -O
   ```

   ```bash
   unzip liferay-b4d8.zip
   ```

1. モジュールのルートから、ビルドおよびデプロイします。

   ```bash
   ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   ```tip::
   このコマンドは、デプロイされたjarをDockerコンテナの/opt/liferay/osgi/modulesにコピーするのと同じです。
   ```

1. LiferayのDockerコンテナコンソールで各モジュールのデプロイを確認します。

   ```bash
   STARTED com.acme.n4g6.impl_1.0.0
   ```

<a name="test-the-data-provider" />

## データプロバイダをテストする

フォームでデータプロバイダーを使用するには

1. データプロバイダーのインスタンスを追加します。

   1. サイトメニューの［コンテンツとデータ］&rarr; ［フォーム］を選択します。

   1. ［データプロバイダー］タブを開き、 **追加** ボタンをクリックします。

      ![カスタムデータプロバイダがLiferay Formsで使用できるようになりました。](./writing-a-custom-data-provider/images/01.png)

   1. 下記のように設定します：
      - **名前:** Cites Near Diamond Bar, CA (USA)
      - **説明:** GeoDataSource Location Search- Liferay本社の20キロ内の都市を取得します。
      - **出力**
         - **ラベル：** 都市
         - **パス：** 都市
         - **タイプ：** リスト

      ![カスタムデータプロバイダーを設定し、その出力を指定します。](./writing-a-custom-data-provider/images/02.png)

   1. ［**保存**］ をクリックします。

1. Diamond Bar付近のデータプロバイダーを使用するフォームを追加します。

   1. サイトメニューの［コンテンツとデータ］&rarr; ［フォーム］を選択します。

   1. ［フォーム］タブで、［追加］ボタンをクリックします。

   1. 次の設定で［リストから選択］フィールドを追加します。

      1. **ラベル：** Liferay周辺の都市を選択してください。

      1. **リストの作成：** データプロバイダーから

      1. **データ・プロバイダーを選択してください。** Diamond Bar, CA (USA)周辺の都市

      1. **出力パラメータを選択してください。** 都市

   1. フォームを公開し、データプロバイダからリストが入力されていることを確認します。

   ![データプロバイダは、Liferayから20km以内の都市のリストを返します。](./writing-a-custom-data-provider/images/03.png)

これは良い例ですが、データプロバイダーのURLをハードコードしています。 URLを設定できるようにしておけば、この同じデータプロバイダーを他の都市や、XMLを提供する他のURLでも使用することができます。

<a name="understanding-the-b4d8-ddm-data-provider" />

## B4D8 DDMデータプロバイダーについて

`Acme B4D8 Implementation` プロジェクトには、特定のURLからXMLを返すためのカスタムデータプロバイダが含まれています。  `B4D8DDMDataProvider`、 `B4D8DDMDataProviderSettingsProovider`、 `B4D8DDMDataProviderSettings`の３つのクラスが含まれています。

### `DDMDataProvider`の実装

データプロバイダクラスは、 `com.liferay.dynamic.data.mapping.data.provider.DDMDaProvider` インターフェースを実装し、`getData` と `getSettings`の2つのメソッドをオーバーライドします。  これらのメソッド名は、設定に基づいてデータを提供するという、データプロバイダーの本質を捉えています（設定は任意です）。

インターフェースのメソッドを実装し、2つの `@Component` 設定を提供するだけで、データプロバイダをLiferay Formsアプリケーションに登録することができ、Liferayのデフォルトデータプロバイダと一緒にフォームUIに表示されます。

```{literalinclude} ./writing-a-custom-data-provider/resources/liferay-b4d8.zip/b4d8-impl/src/main/java/com/acme/b4d8/dynamic/data/mapping/data/provider/internal/B4D8DDMDataProvider.java
   :language: java
   :lines: 38-41,43-46,65-66,67-68,70-71
```

`getData` メソッドがほとんどの作業を行います。 これは、Formsフレームワークが理解できる `DDMDaProviderResponse` を返さなければなりません。 B4D8のデータプロバイダーとしては、下記のような特徴があります。

1. XMLデータソースのURLが構築されます。

   ```{literalinclude} ./writing-a-custom-data-provider/resources/liferay-b4d8.zip/b4d8-impl/src/main/java/com/acme/b4d8/dynamic/data/mapping/data/provider/internal/B4D8DDMDataProvider.java
      :dedent: 3
      :language: java
      :lines: 49-53
   ```

1. `_createDDMDataProviderResponse` メソッドが呼び出されます。 ここでは、レスポンスオブジェクトの構築が行われます。 このメソッドを呼び出すには、データプロバイダーの設定と、リモートAPIから返されたXMLドキュメントの2つのパラメータを与えます。 両者のロジックは別々のプライベートユーティリティーメソッドにあります。 重要なのは、 `HttpUtil.URLtoString(url)` は、XMLを取得するためにURLを実行する呼び出しです。

1. これで、（データプロバイダのインスタンスの出力パラメータ設定に基づいて）条件付きでレスポンスを構築するための準備が整いました。 ロジックには以下が含まれます。
   - 静的な内部 `Builder` クラスの `newBuilder `メソッドを使って、レスポンスの構築を開始します。

      ```{literalinclude} ./writing-a-custom-data-provider/resources/liferay-b4d8.zip/b4d8-impl/src/main/java/com/acme/b4d8/dynamic/data/mapping/data/provider/internal/B4D8DDMDataProvider.java
         :dedent: 2
         :language: java
         :lines: 77-78
      ```

   - データプロバイダーの出力パラメータ設定をループします。 [データプロバイダーのテスト](#test-the-data-provider) では、1つの出力セット（3つのネストされたフィールド）のみを追加しました。データプロバイダ設定フォームのプラスボタンをクリックして、追加の出力を持つデータプロバイダを作成すると、このループは各出力を解析します。

   - 各出力について、返されたXMLドキュメントのXMLノード、出力パラメータID、要求された出力データのタイプ（上記の例では［リスト］を選択）を取得します。

   - 出力パラメータの種類を確認し、レスポンスビルダーの `withOutput` メソッドを呼び出します。 各コールでは、出力パラメータIDとマッチするノード（リストが要求されている場合は、複数のノード）のコンテンツが提供されます。

      ```{literalinclude} ./writing-a-custom-data-provider/resources/liferay-b4d8.zip/b4d8-impl/src/main/java/com/acme/b4d8/dynamic/data/mapping/data/provider/internal/B4D8DDMDataProvider.java
         :dedent: 2
         :language: java
         :lines: 80-118
      ```


   - メソッドの最後に、レスポンス `return builder.build()`を返します。

### `DDMDataProviderSettings`で設定を定義します。

データプロバイダー設定クラスは、このデータプロバイダーが必要とする設定を2つの部分に分けて定義します。

1. 設定フォーム自体のレイアウトは、 `@DDMForm*` クラスレベルのアノテーションを使って定義されます。

   ```{literalinclude} ./writing-a-custom-data-provider/resources/liferay-b4d8.zip/b4d8-impl/src/main/java/com/acme/b4d8/dynamic/data/mapping/data/provider/internal/B4D8DDMDataProviderSettings.java
      :language: java
      :lines: 10-25
   ```

   データプロバイダーを設定するフィールドは、この `@DDMForm` の設定フォームに追加する必要があります。 このスニペットは現在、継承済みの `outputParameters` フィールドのみを使用していますが。これは `B4D8DDMDataProviderSettings` クラスが `DDMDataProviderParameterSettings`を継承しているため、アクセス可能です。 フォームに設定を追加する方法については、 [データプロバイダーの設定を追加する](writing-a-custom-data-provider.md#add-data-provider-settings) を参照してください。

1. クラスの宣言とボディによって、どのようなフィールドが利用できるかが決まります。 現在、追加の設定は必要ありませんので、クラス本体は空白です。

   ```{literalinclude} ./writing-a-custom-data-provider/resources/liferay-b4d8.zip/b4d8-impl/src/main/java/com/acme/b4d8/dynamic/data/mapping/data/provider/internal/B4D8DDMDataProviderSettings.java
      :language: java
      :lines: 26-28
   ```

   ```{note} 
      `outputParameters` フィールドに加えて、 `inputParameters` フィールドも [DDMDataProviderParameterSettings](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/data/provider/DDMDataProviderParameterSettings.java) で提供されます。 
   ```

![データプロバイダー設定フォームは、作業の準備ができています。](./writing-a-custom-data-provider/images/05.png)

現在、設定フォームには、Forms UIに表示されるすべてのデータプロバイダーが必要とするいくつかのデフォルトフィールドが含まれています。名前、説明、そして権限を定義するセクションです。 これらは、 `_ddmDataProviderInstanceSettings.getSettings(...)` の呼び出しで設定を追加することで得られます。 Outputsフィールドは、レイアウトに追加した `outputParameters` フィールドを継承したもので、実際にはLabel、Path、Typeで構成されるネストしたフィールドです。

### `DDMDataProviderSettingsProvider` を実装する。

設定プロバイダークラスには `getSettings` というメソッドがあり、与えられたデータプロバイダーの `DDMDataProviderSettings` クラスを返します。 これは、データプロバイダーの設定クラスをインスタンス化するために使用されます。これにより、設定値を取得し、それに応じてデータプロバイダーを設定することができます。

`B4D8DDMDataProviderSettingsProvider` への参照を取得し、その `getSettings` メソッドを、データ プロバイダ クラスの同名の `getSettings` メソッドから呼び出します。

```{literalinclude} ./writing-a-custom-data-provider/resources/liferay-b4d8.zip/b4d8-impl/src/main/java/com/acme/b4d8/dynamic/data/mapping/data/provider/internal/B4D8DDMDataProvider.java
   :dedent: 1
   :language: java
   :lines: 67-70,158-160
```

<a name="add-data-provider-settings" />

## データプロバイダー設定を追加する

データプロバイダー設定を追加するには、 `DataProviderSettings` インターフェースにアノテーションフィールドを追加し、設定値に反応するように `DataProvider` クラスを更新します。

### 設定にURLフィールドを追加

1. まず、 `URL` フィールドを `DataProviderSettings` に追加します。 クラス本体に、このアノテーション付きのメソッドを追加します。

    ```java
    @DDMFormField(
        label = "%url", required = true,
        validationErrorMessage = "%please-enter-a-valid-url",
        validationExpression = "isURL(url)"
    )
    public String url();
    ```

    それにはこのインポートが必要です。

    ```java
    import com.liferay.dynamic.data.mapping.annotations.DDMFormField;
    ```

1. フォームのレイアウトを作成するクラスレベルのアノテーションでは、 `@DDMFormLayoutColumn` を次のように置き換えます。

    ```java
    @DDMFormLayoutColumn(
        size = 12, value = {"url", "outputParameters"}
    )
    ```

これで、 `DataProvider` クラスで使用するための設定が整いました。

### データプロバイダーの `getData` メソッドで設定を処理します。

ここで、 `B4D8DDMDataProvider#getData` メソッドを更新する必要があります。

- ハードコードされた文字列 `url` 変数を削除します。
- `B4D8DDMDataProviderSettings` を先にインスタンス化して、URL設定を取得するようにメソッドをリファクタリングしています。
- レスポンスにURLを設定します。

ローカルで編集する場合は、以下の説明文の下にある `try` ブロックを完全にコピーしてください。

1. ユーザー入力が可能になったことで、有効なURLを得ることができるようになりました。

    `key` 変数を定義している行を削除しました---これは現在、URL設定フィールドで設定できます。

    ```java
    String key = "LAOOBDZVQ5Z9HHYC4OCXHTGZGQLENMNA";
    ```

1. URLを定義している `文字列` 変数を、データプロバイダーの設定フィールドで入力された `Http.Options` で置き換えます。

    ```java
    Http.Options options = new Http.Options();

    options.setLocation(b4d8DDMDataProviderSettings.url());
    ```

1. 新しい `オプション` を、 `url` の代わりに使用して、return文の `_createdDDMDataProviderResponse` の呼び出しに使用します。 既存のreturn文を置き換える。

    ```java
    return _createDDMDataProviderResponse(
        b4d8DDMDataProviderSettings,
        _toDocument(HttpUtil.URLtoString(options)));
    ```

上記の手順では、メソッドのリファクタリングを省略しています。 これらの手順をコンパイルしてテストするには、 `try` ブロック全体を `getData` メソッドで上書きします。

```java
try {
    B4D8DDMDataProviderSettings b4d8DDMDataProviderSettings =
        _ddmDataProviderInstanceSettings.getSettings(
            _getDDMDataProviderInstance(
                ddmDataProviderRequest.getDDMDataProviderId()),
            B4D8DDMDataProviderSettings.class);

    Http.Options options = new Http.Options();

    options.setLocation(b4d8DDMDataProviderSettings.url());

    return _createDDMDataProviderResponse(
        b4d8DDMDataProviderSettings,
        _toDocument(HttpUtil.URLtoString(options)));
}
```

Liferayの `Http` クラスをインポートします。

```java
import com.liferay.portal.kernel.util.Http;
```

これで、アップデートデータプロバイダをテストする準備が整いました。

<a name="deploy-and-test-the-updated-data-provider" />

## 更新されたデータプロバイダのデプロイとテスト

更新されたデータプロバイダーをフォームで使用するには

1. モジュールルートから、リビルドして再デプロイします。

   ```bash
   ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

1. データプロバイダーのインスタンスを追加します。
      - **名前：** Cites Near Recife, Pernambuco (Brazil)
      - **説明：** GeoDataSource Location Search--Liferayのブラジルオフィスから20km以内の都市を取得します。
      - **URL:**
        ```
        https://api.geodatasource.com/cities?key=LAOOBDZVQ5Z9HHYC4OCXHTGZGQLENMNA&format=xml&lat=-8.0342896&lng=-34.9239708
        ```
      - **出力**
         - **ラベル：** 都市
         - **パス：** 都市
         - **タイプ：** リスト

1. Cities Near Recife 付近のデータプロバイダを使用するフォームを追加します。

   1. サイトメニューの［コンテンツとデータ］ &rarr; ［フォーム］を選択します。

   1. ［フォーム］タブで、［追加］ボタンをクリックします。

   1. 次の設定で［リストから選択］フィールドを追加します。

      1. **ラベル：** Liferay, BRの近くの都市を選択してください。

      1. **リストの作成：** データプロバイダーから

      1. **データプロバイダーを選択：** レシフェ（ペルナンブコ州、ブラジル）に近い都市

      1. **出力パラメータを選択：** 都市

   1. フォームを公開し、データプロバイダからリストが入力されていることを確認します。

   ![データプロバイダは、ブラジルのLiferayから20km以内にある都市のリストを返します。](./writing-a-custom-data-provider/images/04.png)
