# MVCポートレットを使用したビューのレンダリング

ユーザーがポートレットのビューにアクセスできるようにする場合は、ビューへのナビゲーションを実装する必要があります。 ポートレットレンダーURLは、このような場合に役立ちます。

ここでは、ポートレットレンダーURLを使用するサンプルアプリケーションをデプロイします。 ポートレットが、MVCポートレットの`mvcPath`パラメーターを使用してビューパスを設定するURLを作成する方法を見ていきます。

サンプルポートレットには2つのビューがあります。 **ビュー1** にはその名前と **ビュー2** へのリンクが表示され、その逆も同様です。

![これがビュー1です。](./rendering-views-with-mvc-portlet/images/01.png)

<a name="deploy-an-mvc-portlet-with-multiple-views" />

## 複数のビューを持つMVCポートレットをデプロイする

```{include} /_snippets/run-liferay-portal.md
```

サンプルポートレットをデプロイし、そのビュー間を移動する方法は次のとおりです。 学習の目的で、ポートレットは`mvcPath`パラメーター値をログに記録します。

1. サンプルをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/building-applications/developing-a-java-web-application/using-mvc/liferay-c8m3.zip -O
   ```

   ```bash
   unzip liferay-c8m3.zip
   ```

1. サンプルをビルドしてデプロイします。

    ```bash
    cd liferay-c8m3
    ```

    ```bash
     ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.c8m3.web_1.0.0
    ```

1. **C8M3ポートレット** ウィジェットを ［**Samples**］ カテゴリからウィジェットページに追加します。 **ビュー1**（ポートレットのデフォルトビュー）が表示されます。

    ![ビュー1をページに追加しました。](./rendering-views-with-mvc-portlet/images/02.png)

    次のメッセージがログに出力されます。

    ```bash
    MVC path null
    ```

    描画リクエストには、MVCパスパラメーターがまだ含まれていません。 これは予想通りです。 ポートレットは、デフォルトでポートレットクラス（後述）を使用してビュー1をレンダリングします。 次のステップでは、MVCパスパラメーターを使用します。

1. ［**Go to View 2**］ をクリックします。 ビュー2が表示されます。

    ![ビュー2に移動しました。](./rendering-views-with-mvc-portlet/images/03.png)

    ログメッセージには、MVCパス値`/view_2.jsp`（ビュー2テンプレートへのパス）が表示されます。

    ```bash
    MVC path /view_2.jsp
    ```

1. ［**Go to View 1**］ をクリックします。 ビュー1が再び表示されます。

   ![ビュー1に戻りました。](./rendering-views-with-mvc-portlet/images/04.png)

   ログメッセージには、MVCパス`/view_1.jsp`が出力されます。

   ```bash
    MVC path /view_1.jsp
   ```

ポートレットビュー間を往復しました。 次に、ポートレットがデフォルトのビューを設定し、レンダーURLとMVCパスリクエストパラメータを使用してビューへのパスを設定する方法を学習します。

<a name="setting-the-default-view" />

## デフォルトビューの設定

ポートレットのデフォルトビューは、ユーザーが最初にポートレットのページにアクセスしたときにレンダリングされます。 ポートレットクラスの初期化パラメーターは、デフォルトビューを設定します。 `C8M3Portlet.java`のサンプルクラスは、 [`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html) でデフォルトビューを設定します。

```{literalinclude} ./rendering-views-with-mvc-portlet/resources/liferay-c8m3.zip/c8m3-web/src/main/java/com/acme/c8m3/web/internal/portlet/C8M3Portlet.java
   :language: java
   :lines: 16-24
```

[`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html) プロパティ`"javax.portlet.init-param.view-template=/view1.jsp"`は、デフォルトのビューテンプレートとして`/view1.jsp`を指定します。 テンプレートパスは、ポートレットの`src/main/resources/META-INF/resources`フォルダへの相対パスです。

次に、ビュー1がビュー2にリンクする方法を学習します。

<a name="linking-to-a-view-using-a-portlet-render-url" />

## ポートレットレンダーURLを使用したビューへのリンク

ビュー1には、 ［**View 1**］ という見出しと、 ［**Go to View 2**］ というラベルの付いたリンクしかありません。 `view1.jsp`コードは次のとおりです。

```{literalinclude} ./rendering-views-with-mvc-portlet/resources/liferay-c8m3.zip/c8m3-web/src/main/resources/META-INF/resources/view_1.jsp
   :language: jsp
```

`portlet:renderURL`タグはポートレットtaglibから取得され、プレフィックス`portlet`が割り当てられます。 上記のレンダーURLは、変数`view2URL`に割り当てられています。 レンダーURLは、`mvcPath`という名前の`portlet:param`に値`/view_2.jsp`を宣言します。 ポートレットがレンダリングされると、`mvcPath`パラメーターがポートレットの [`RenderRequest`](https://docs.liferay.com/portlet-api/2.0/javadocs/javax/portlet/RenderRequest.html) オブジェクトに追加されます。 ポートレットリクエストの処理時に、 [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java) は`mvcPath`に割り当てられたテンプレートをレンダリングします。

ポートレットがレンダーURLを使用するには、そのURLをハイパーリンクやボタンなどのUIコンポーネントにバインドする必要があります。 ハイパーリンク`<a href="<%= view2URL %>">［Go to View 2］</a>`は、レンダーURLをUIコンポーネントにバインドします。

ユーザーが ［**Go to View 2**］ ハイパーリンクをクリックすると、`mvcPath`パラメーターを含むポートレットリクエストがポートレットクラスに送信されます。

<a name="handling-the-mvcpath-portlet-parameter" />

## mvcPathポートレットパラメーターの処理

ポートレットがリクエストオブジェクトを受信すると、リクエストオブジェクトのパラメーターに応答できます。 `C8M3Portlet`の`render`メソッドは、描画リクエストに応答します。

```{literalinclude} ./rendering-views-with-mvc-portlet/resources/liferay-c8m3.zip/c8m3-web/src/main/java/com/acme/c8m3/web/internal/portlet/C8M3Portlet.java
   :dedent: 1
   :language: java
   :lines: 26-40
```

`C8M3Portlet`の`render`メソッドは、`mvcPath`パラメーター値をログに記録してから、ポートレットのレンダリングをスーパークラス`MVCPortlet`に委任します。  [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java) は、`mvcPath`ポートレットリクエストパラメータに割り当てられたビューをレンダリングします。

```{note}
`mvcPath`リクエストパラメータがない場合、` MVCPortlet`はデフォルトのテンプレート（つまり、 `javax.portlet.init-param.view-template`コンポーネントプロパティが指定するテンプレート）を表示します。
```

ビュー2のテンプレート`view2.jsp`は、ポートレットレンダーURLも使用して`view1.jsp`にマップし直します。

```{literalinclude} ./rendering-views-with-mvc-portlet/resources/liferay-c8m3.zip/c8m3-web/src/main/resources/META-INF/resources/view_2.jsp
   :language: jsp
```

ビュー1に戻るラウンドトリップを実装します。

<a name="whats-next" />

## 次のステップ

これで、ポートレットビューをレンダリングする方法がわかりました。 次に、個別の`RenderCommand`クラスを使用して、ポートレットでアクションを実行したり、ビューをレンダリングしたりできます。

<a name="additional-information" />

## 追加情報

* [MVCレンダーコマンド (近日公開！)](./mvc-render-command.md)
* [MVCアクションコマンド (近日公開！)](./mvc-action-command.md)
* [MVCポートレットでのローカライズされたメッセージの使用](./using-localized-messages-in-an-mvc-portlet.md)
* [ポートレット](../reference/portlets.md)
