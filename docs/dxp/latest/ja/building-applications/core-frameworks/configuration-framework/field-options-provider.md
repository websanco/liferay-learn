# フィールドオプションプロバイダー

[ドロップダウンリスト](./setting-and-accessing-configurations.html#implementing-a-dropdown-selection-ui) は、構成インターフェイスの`@Meta.AD`アノテーションに手動で入力できます。 ただし、`ConfigurationFieldOptionsProvider`クラスを使用して、オプションのラベルと値を自動的に入力することもできます。 これは、ドロップダウンリストに動的にデータを入力する場合に役立ちます。 たとえば、Webサービスからオブジェクトのリストをフェッチしたり、データベースを反復処理してドロップダウンリストに動的にデータを入力したりできます。

<a name="deploy-the-tutorial-code" />

## チュートリアルコードをデプロイする

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [フィールドオプションプロバイダー](./liferay-z4h3.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/core-frameworks/configuration-framework/liferay-z4h3.zip -O
    ```

    ```bash
    unzip liferay-z4h3.zip
    ```

1. モジュールのルートから、ビルドおよびデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、デプロイされたjarをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```
    STARTED com.acme.z4h3.impl_1.0.0 [1150]
    ```

1. ブラウザで`https://localhost:8080` を開き、 ［**コントロールパネル**］ &rarr; ［**設定**］ &rarr; ［**システム設定**］ に移動します。 ［プラットフォーム］で ［**サードパーティー**］ をクリックします。 左側の ［**Z4H3 Configuration**］ をクリックします 。

    ![設定UIには、2つのドロップダウンリストが表示されます。](./field-options-provider/images/01.png)

最初のドロップダウンリストには、`@Meta.AD`アノテーションが手動で入力されます。 2番目のドロップダウンリストには、フィールドオプションプロバイダーが表示されます。

<a name="setting-the-configuration-interface" />

## 構成インターフェイスを設定する

[構成インターフェイスを作成](./setting-and-accessing-configurations.html#creating-the-configuration-interface) し、入力する構成フィールド名を設定します。

```{literalinclude} ./field-options-provider/resources/liferay-z4h3.zip/z4h3-impl/src/main/java/com/acme/z4h3/internal/configuration/Z4H3Configuration.java
:dedent: 1
:language: java
:lines: 11-22
```

サンプルプロジェクトでは、`providerPopulatedColors`は入力される構成フィールド名です。

<a name="implement-the-field-options-provider" />

## フィールドオプションプロバイダーを実装する

`ConfigurationFieldOptionsProvider`クラスを実装する新しいクラスを作成します。

```{literalinclude} ./field-options-provider/resources/liferay-z4h3.zip/z4h3-impl/src/main/java/com/acme/z4h3/internal/configuration/admin/definition/Z4H3ConfigurationFieldOptionsProvider.java
:language: java
:lines: 14-20
```

`@Component`アノテーションを使用して、サービスを登録します。 前のステップの`configuration.field.name`を含めます。 `configuration.pid`を、構成インターフェイスの完全修飾クラス名に設定します。

```{literalinclude} ./field-options-provider/resources/liferay-z4h3.zip/z4h3-impl/src/main/java/com/acme/z4h3/internal/configuration/admin/definition/Z4H3ConfigurationFieldOptionsProvider.java
:dedent: 1
:language: java
:lines: 24-46
```

`getOptions`メソッドを追加して、 `Option`のリストを返します。 サンプルプロジェクトには、`optionValue`を色の文字列として設定し、`optionLabel`をその色の`Langauge.properties`ファイルに格納されている文字列として設定する配列が含まれています。

チュートリアルコードでは、文字列配列の簡単な例を使用していますが、より複雑なユースケースも可能です。

[`EnabledClassNamesConfigurationFieldOptionsProvider.java`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/asset/asset-auto-tagger-service/src/main/java/com/liferay/asset/auto/tagger/internal/configuration/admin/definition/EnabledClassNamesConfigurationFieldOptionsProvider.java) を使用した実際のLiferayの例を参照してください。 このコードは、`AssetRendererFactory`オブジェクトのリストを取得し、リストを反復処理して、アセットのタイプ名をラベルとして、クラス名を値として使用して、`Option`の新しいリストを作成します。
