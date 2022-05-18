# DDMフォームのアノテーション

自動生成された [configuration interface](./setting-and-accessing-configurations.html#creating-the-configuration-interface) UIは、構成によっては単純すぎる場合があります。  動的データマッピング（DDM）フォームのアノテーションを使用して、レイアウトのUIをカスタマイズできます。

<a name="see-a-sample-configuration-ui" />

## サンプルの構成UIを参照する

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [DDMフォームのアノテーション](./liferay-v1d9.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/en/building-applications/core-frameworks/configuration-framework/liferay-v3d9.zip -O
    ```

    ```bash
    unzip liferay-v1d9.zip
    ```

1. モジュールのルートから、ビルドおよびデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、デプロイされたjarをDockerコンテナの/opt/liferay/osgi/modulesにコピーするのと同じです。
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```
    STARTED com.acme.v1d9.impl_1.0.0 [1650]
    ```

1. ブラウザで`https://localhost:8080` を開き、 ［**コントロールパネル**］ &rarr; ［**設定**］ &rarr; ［**システム設定**］ に移動します。 ［プラットフォーム］で ［**サードパーティー**］ をクリックします。 左側の ［**V1D9 Configuration**］ をクリックします 。

    ![UIレイアウトは、DDMフォームのアノテーションによってカスタマイズされます。](./ddm-form-annotations/images/01.png)

DDMフォームのアノテーションの仕組みを以下に示します。

<a name="write-the-configuration-form" />

## 構成フォームを作成する

設定UIのすべてのフォーム項目を含む構成フォームインターフェイスを作成します。 フィールドごとに`@DDMFormField`アノテーションを使用します。 各フィールドの`label`、`properties`、`type`などの属性を定義します。

```{literalinclude} ./ddm-form-annotations/resources/liferay-v1d9.zip/v1d9-impl/src/main/java/com/acme/v1d9/internal/configuration/admin/definition/V1D9ConfigurationForm.java
:dedent: 1
:language: java
:lines: 37-58
```

使用可能なすべての項目タイプの詳細については、[Form Field Types Reference](../../../process-automation/forms/creating-and-managing-forms/forms-field-types-reference.md)にアクセスしてください。 各項目タイプの`type`変数名については、 [Field Type Constants](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dynamic-data-mapping/dynamic-data-mapping-form-field-type-api/src/main/java/com/liferay/dynamic/data/mapping/form/field/type/constants/DDMFormFieldTypeConstants.java) を参照してください。

各フォーム項目にアノテーションを付けた後、`DDMFormLayout`アノテーションを使用してクラス宣言のすぐ上にフォームのレイアウトを定義します。

```{literalinclude} ./ddm-form-annotations/resources/liferay-v1d9.zip/v1d9-impl/src/main/java/com/acme/v1d9/internal/configuration/admin/definition/V1D9ConfigurationForm.java
:language: java
:lines: 10-34
```

`DDMFormLayoutRow`アノテーションと`DDMFormLayoutColumn`を使用して、UIに必要な行と列にフォーム項目を配置します。

<a name="write-the-form-declaration" />

## フォーム宣言を書く

`ConfigurationDDMFormDeclaration`の新しい実装を作成して、新しい構成フォームクラスを登録します。

```{literalinclude} ./ddm-form-annotations/resources/liferay-v1d9.zip/v1d9-impl/src/main/java/com/acme/v1d9/internal/configuration/admin/definition/V1D9ConfigurationDDMFormDeclaration.java
:language: java
:lines: 7-19
```

`Component`アノテーションの`configurationPid`は、構成インターフェイスの完全修飾クラス名と一致する必要があることに注意してください。
