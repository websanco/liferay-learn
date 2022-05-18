# ポートレットレベルの設定

構成フレームワークを使用すると、[さまざまなレベルのスコープに合わせてアプリケーションの構成](./scoping-configurations.md)を設定できます。 インスタンススコープおよびサイトスコープの構成が`ConfigurationProvider`を使用する場合、以下の例に示すように、ポートレットスコープの構成は`PortletDisplay`を使用します。

構成フレームワークをポートレットのルック&フィールと組み合わせて使用できるため、アプリには、システム設定の構成UIと、ポートレットのセットアップタブのプリファレンスUIの両方を設定できます。

ポートレットのルック&フィールがユーザーによって実装および設定されている場合、アプリケーションの構成はオーバーライドされることに注意してください。 詳細については、 [ポートレットのルック&フィール](../../developing-a-java-web-application/using-mvc/portlet-preferences.md) を参照してください。

<a name="see-the-example-code" />

## サンプルコードを参照する

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [ローカライズされたメッセージの共有](./liferay-x7y2.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/core-frameworks/configuration-framework/liferay-x7y2.zip -O
    ```

    ```bash
    unzip liferay-x7y2.zip
    ```

1. モジュールのルートから、ビルドおよびデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、デプロイされたjarをDockerコンテナの/opt/liferay/osgi/modulesにコピーするのと同じです。
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.x7y2.web_1.0.0 [1651]
    ```

1. サンプルのモジュールが機能していることを確認します。 ブラウザで`https://localhost:8080`を開きます。

1. X7Y2ポートレットをページにデプロイします。 サンプルポートレットは、サンプルウィジェットの下にあります。 デフォルトの色が緑に設定されていることに注意してください。

1. ［**コントロールパネル**］ → ［**設定**］ → ［**システム設定**］ に移動します。 ［Other］セクションの下部にあるX7Y2構成をクリックします。

    ![システム設定でアプリケーションの構成に移動します。](./portlet-level-configuration/images/01.png)

    別のデフォルトの色を選択して保存します。 ウィジェットがデプロイされているページに戻ります。 色変数が更新されました。

1. ポートレットのオプションアイコン（![options icon](../../../images/icon-options.png)）をクリックし、 ［**設定**］ をクリックします。 ポートレットの設定ウィンドウが開きます。

    ![構成をクリックして、ポートレットの設定を開きます](./portlet-level-configuration/images/02.png)

    別の色を選択して保存します。 ウィンドウを閉じると、ポートレット設定が表示されます。 ポートレットの設定が選択された後、システム設定で設定されたアプリケーション設定は表示されなくなることに注意してください。

アプリケーションの構成がポートレット設定とどのように連携するかを調べてみましょう。

<a name="create-the-configuration-interface" />

## 構成インターフェイスを作成する

まず、システム設定で構成UIを自動生成する構成インターフェイスファイルを作成します。

```{literalinclude} ./portlet-level-configuration/resources/liferay-x7y2.zip/x7y2-web/src/main/java/com/acme/x7y2/web/internal/configuration/X7Y2PortletInstanceConfiguration.java
:language: java
:lines: 7-24
```

この例では、スコープは`PORTLET_INSTANCE`に設定されています。 インターフェイスは、ドロップダウンリストで使用可能な構成オプションも定義します。

この例は、より高いレベルに設定されたスコープでも機能することに注意してください（つまり、 サイト、インスタンス、システムスコープ）。 ベストプラクティスは、ポートレット設定で使用する予定のアプリケーションをポートレットスコープとしてマークすることです。

詳細については、 [Creating the Configuration Interface](./setting-and-accessing-configurations.html#Creating-the-Configuration-Interface) を参照してください。

<a name="add-the-configuration-bean-declaration" />

## 構成Bean宣言を追加する

`ConfigurationProvider`と同様に、`PortletDisplay`は、構成クラスを登録するために構成Bean宣言ファイルを必要とします。 詳細については、 [構成Bean宣言](./setting-and-accessing-configurations.html#Create-a-Configuration-Bean-Declaration) を参照してください。

<a name="read-the-configuration-with-portletdisplay" />

## PortletDisplayで構成を読み取る

[構成プロバイダーAPIから構成値を読み取る](./setting-and-accessing-configurations.html#Reading-the-Configuration-from-the-Application) のと同様に、ポートレット表示APIを使用してアプリケーションの構成値にアクセスできます。

```{literalinclude} ./portlet-level-configuration/resources/liferay-x7y2.zip/x7y2-web/src/main/java/com/acme/x7y2/web/internal/portlet/X7Y2Portlet.java
:language: java
:lines: 20-63
```

`X7Y2PortletInstanceConfiguration`メソッドは、`PortletDisplay`を使用してポートレットインスタンス設定を取得します。 `render()`メソッドは、JSPファイルの要求から読み取ることができるように、構成をリクエストオブジェクトに追加します。

<a name="set-up-portlet-preferences" />

## ポートレットのルック&フィールを設定する

ポートレットにポートレットのルック&フィールを設定するには、構成JSPファイルと構成アクションもアプリケーションに追加する必要があります。 これらのファイルがポートレットでどのように機能するかについて詳しくは、 [ポートレットのルック&フィール](../../developing-a-java-web-application/using-mvc/portlet-preferences.md) を参照してください。

<a name="related-information" />

## 関連情報

* [Scoping Configuration](./scoping-configurations.md)
* [ポートレットのルック&フィール](../../developing-a-java-web-application/using-mvc/portlet-preferences.md)
