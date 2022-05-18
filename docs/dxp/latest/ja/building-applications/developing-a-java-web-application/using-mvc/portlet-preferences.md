# ポートレットのルック&フィール

管理者とユーザーに、ポートレットのルック&フィールを使用してポートレットをカスタマイズする方法を提供できます。 ポートレットのルック&フィールを任意のMVCポートレットに追加して、ユーザーがプリファレンスにアクセスして設定するためのUIを提供できます。

ポートレットのルック&フィールは、アプリケーションの構成とは別に保存されるプロパティであることに注意してください。 詳細については、 [ポートレットレベルの設定](../../core-frameworks/configuration-framework/portlet-level-configuration.md) を参照してください。

<a name="see-a-sample-implementation" />

## サンプル実装を参照する

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. サンプルをダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/developing-a-java-web-application/using-mvc/liferay-p1z2.zip -O
    ```

    ```bash
    unzip liferay-p1z2.zip
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
    STARTED com.acme.p1z2.web_1.0.0 [2725]
    ```

1. サンプルのモジュールが機能していることを確認します。 ブラウザで`https://localhost:8080`を開きます。

1. P1Z2ポートレットをページに追加します。 サンプルポートレットは、サンプルウィジェットの下にあります。 デフォルトのカラー変数が青に設定されていることに注意してください。

1. ポートレットのオプションアイコン（![options icon](../../../images/icon-options.png)）をクリックし、 ［**設定**］ をクリックします。 ポートレットの設定ウィンドウが開きます。

    ![構成をクリックして、ポートレットの設定を開きます](./portlet-preferences/images/01.png)

1. 別の色を選択し、 ［**保存**］ をクリックします。 設定メニューを閉じると、ポートレットに新しい選択が表示されます。

次に設定の仕組みを確認します。

<a name="create-the-configuration-jsp" />

## 設定JSPを作成する

ポートレットのルック&フィールのユーザーインターフェイスは、`configuration.jsp`ファイルによって提供されます。

```{literalinclude} ./portlet-preferences/resources/liferay-p1z2.zip/p1z2-web/src/main/resources/META-INF/resources/configuration.jsp
:language: jsp
:lines: 8-30
```

JSPファイルは、`<liferay-portlet:actionURL />`および`<liferay-portlet:renderURL />`タグを使用して、変数`configurationActionURL`および`configurationRenderURL`にURLを作成します。

誰かがフォームを送信すると、`configurationActionURL`が呼び出され、リクエストパラメータとして含まれている`color`変数を使用してアプリケーションの`processAction`メソッドがトリガーされます。

リクエストの目的を示す`cmd`という名前のURLパラメーターが提供されます。 `cmd`パラメーターの値は`update`です。

<a name="create-the-configuration-action" />

## 設定アクションを作成する

カスタム構成アクションクラスを作成して、ポートレットの設定にアクセスできるようにします。

```{literalinclude} ./portlet-preferences/resources/liferay-p1z2.zip/p1z2-web/src/main/java/com/acme/p1z2/web/internal/portlet/action/P1Z2ConfigurationAction.java
:language: java
:lines: 14-34
```

`@Component`アノテーションで、アクションクラスが適用されるポートレットを`property`タグで指定します。

構成フォームからポートレットのルック&フィールを読み取り、それらをデータベースに保管する`processAction()`メソッドを追加します。  サンプルのポートレットでは、メソッドは`color` URLパラメーターを読み取り、その値をポートレット設定として設定します。

<a name="add-the-preference-logic" />

## プリファレンスロジックを追加する

`view.jsp`ファイルにロジックを追加して、ポートレットの設定にアクセスします。

```{literalinclude} ./portlet-preferences/resources/liferay-p1z2.zip/p1z2-web/src/main/resources/META-INF/resources/view.jsp
:language: jsp
:lines: 7
```

JSPファイルは、選択されたポートレット設定をチェックし、値を返します。 値がまだ保存されていない場合は、デフォルト値として`blue`が返されます。

`<portlet:defineObjects />`タグを使用すると、`portletPreferences`が使用可能になります。これを使用して、JSPで`color`の設定を取得します。

<a name="add-the-portlets-path-parameters" />

## ポートレットのパスパラメータを追加する

ポートレットの`@Component`アノテーションで、ビューテンプレートと設定用テンプレートのパスパラメーターを追加します。

```{literalinclude} ./portlet-preferences/resources/liferay-p1z2.zip/p1z2-web/src/main/java/com/acme/p1z2/web/internal/portlet/P1Z2Portlet.java
:language: java
:lines: 9-18
```

<a name="related-information" />

## 関連情報

* [ポートレットレベルの設定](../../core-frameworks/configuration-framework/portlet-level-configuration.md)
