# 構成UIを非表示にする

Liferayは、 [構成インターフェイスがデプロイ](./setting-and-accessing-configurations.html#creating-the-configuration-interface) された後、構成UIを自動的に生成します。 ただし、UIを非表示にしたい特定のユースケースがある場合があります。 たとえば、管理者に特定の構成へのアクセスを許可したくない場合や、特定の基準に基づいて構成を非表示にしたい場合です。 構成UIを非表示にするには、2つの異なるオプションがあります。

* `generateUI`アノテーションプロパティを使用する
* 構成の可視性インターフェイスを使用する

<a name="using-generateui" />

## `generateUI`を使用する

すべての状況で構成UIを非表示にする場合は、`ExtendedObjectClassDefinition`アノテーションプロパティ`generateUI`を構成インターフェイスに含めます。 プロパティを`false`に設定します。 これにより、すべてのスコープの構成UIが非表示になることに注意してください。

```java
@ExtendedObjectClassDefinition(generateUI=false)
```

<a name="using-the-configuration-visibility-interface" />

## 構成の可視性インターフェイスを使用する

構成UIを選択的に非表示にする場合は、`ConfigurationVisibilityController`インターフェイスを使用します。

<a name="see-an-example-implementation" />

### 実装例を参照してください

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. [構成UIの非表示](./liferay-g8v3.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/core-frameworks/configuration-framework/liferay-g8v3.zip -O
    ```

    ```bash
    unzip liferay-g8v3.zip
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
    STARTED com.acme.g8v3.impl_1.0.0 [1650]
    ```

1. ブラウザで`https://localhost:8080` を開き、 ［**コントロールパネル**］ &rarr; ［**設定**］ &rarr; ［**システム設定**］ に移動します。 ［プラットフォーム］で ［**サードパーティー**］ をクリックします。 左側の ［**G8V3 Able Configuration**］ をクリックします 。 ［Enable G8V3 Baker Configuration］のチェックボックスをオンにします。 [**アップデート**] ボタンをクリックします。

    ![チェックボックスをクリックすると、他の構成UIが表示されます](./hiding-the-configuration-ui/images/01.png)

1. このチェックボックスが有効になっていない場合、G8V3 Baker構成は非表示になっていることに注意してください。

<a name="implement-the-interface" />

### インターフェイスを実装する

アプリケーションの構成の可視性インターフェイスを作成します。

```{literalinclude} ./hiding-the-configuration-ui/resources/liferay-g8v3.zip/g8v3-impl/src/main/java/com/acme/g8v3/internal/configuration/admin/display/G8V3BakerConfigurationVisibilityController.java
:language: java
:lines: 17-48
```

`@Component`アノテーションを使用して構成インターフェイスを識別します。 `Component`アノテーションの`configuration.pid`は、構成インターフェイスの完全修飾クラス名と一致する必要があることに注意してください。

インターフェイスの`isVisible()`メソッド用に独自のロジックを記述します。 サンプルプロジェクトでは、単純なロジックを使用して、G8V3 Able構成のブール値設定を確認します。 アプリケーションで、構成UIを表示または非表示にする独自のプログラミングロジックを設計します。
