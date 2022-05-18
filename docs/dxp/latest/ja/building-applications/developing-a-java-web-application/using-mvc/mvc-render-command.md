# MVCレンダーコマンド

MVCレンダーコマンドは、レンダリングするページを処理するクラスです。 これらは、`MVCPortlet`レンダーURLとリクエストによって呼び出されます。 レンダーロジックが単純な場合は、[すべてをポートレットクラスに実装](./rendering-views-with-mvc-portlet.md)できます。 レンダーロジックが複雑な場合、またはレンダーパスを明確に分離したい場合は、MVCレンダーコマンドを使用してください。

<a name="invoke-an-mvc-render-command" />

## MVCレンダーコマンドを呼び出す

ここでは、MVCレンダーコマンドを使用してビューをレンダリングするサンプルポートレットをデプロイします。

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. サンプルをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/building-applications/developing-a-java-web-application/using-mvc/liferay-a4p1.zip -O
   ```

   ```bash
   unzip liferay-a4p1.zip
   ```

1. サンプルをビルドしてデプロイします。

    ```bash
    cd liferay-a4p1
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.a4p1.web_1.0.0
    ```

1. **A4P1ポートレット** ウィジェットを ［**Samples**］ カテゴリからウィジェットページに追加します。 A4P1ポートレットが表示されます。

    ![A4P1ポートレットをページに追加しました。](./mvc-render-command/images/01.png)

1. MVCレンダーコマンドを呼び出して、 ［**Go to Baker**］ をクリックしてBakerビューにアクセスします。 `A4P1BakerMVCRenderCommand`は、その`render`メソッドの呼び出しをログに記録し、Bakerビューをレンダリングします。

    ```bash
    [A4P1BakerMVCRenderCommand:26] Invoking #render(RenderRequest, RenderResponse)
    ```

    ![Bakerビューをレンダリングしています。](./mvc-render-command/images/02.png)

1. 他のMVCレンダーコマンドを呼び出し、 ［**Go to Able**］ をクリックしてAbleビューに再度アクセスします。 `A4P1AbleMVCRenderCommand`は、その`render`メソッドの呼び出しをログに記録し、Ableビューを再度レンダリングします。

    ```bash
    [A4P1AbleMVCRenderCommand:26] Invoking #render(RenderRequest, RenderResponse)
    ```

MVCレンダーコマンドの動作を見てきました。 次に、それらがどのように機能するかを確認します。


<a name="examine-the-portlet" />

## ポートレットを調べる

`A4P1Portlet`は最小の [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java) です。

```{literalinclude} ./mvc-render-command/resources/liferay-a4p1.zip/a4p1-web/src/main/java/com/acme/a4p1/web/internal/portlet/A4P1Portlet.java
:language: java
:lines: 9-19
```

[`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html) `javax.portlet.name`プロパティはポートレットの名前を設定します。

```{literalinclude} ./mvc-render-command/resources/liferay-a4p1.zip/a4p1-web/src/main/java/com/acme/a4p1/web/internal/portlet/A4P1Portlet.java
:dedent: 2
:language: java
:lines: 14
```

ポートレットはデフォルトで`/a4p1/able.jsp`をレンダリングします。

```{note}
`MVCRenderCommand`は、ポートレットの名前（たとえば、ポートレットコンポーネントの` javax.portlet.name`プロパティ値）によってポートレットにバインドします。
```

次に、ポートレットのMVCレンダーコマンドクラスを調べます。

<a name="examine-the-mvcrendercommand-classes" />

## MVCRenderCommandクラスを調べる

MVCレンダーコマンドのクラスは、 [`MVCRenderCommand`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCRenderCommand.java) を直接実装することも、 [`BaseMVCRenderCommand`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCRenderCommand.java) を拡張することもできます。  `A4P1AbleMVCRenderCommand`は、`MVCRenderCommand`を直接実装します。  `A4P1AbleMVCRenderCommand`は次のとおりです。

```{literalinclude} ./mvc-render-command/resources/liferay-a4p1.zip/a4p1-web/src/main/java/com/acme/a4p1/web/internal/portlet/action/A4P1AbleMVCRenderCommand.java
:language: java
:lines: 12-35
```

`A4P1AbleMVCRenderCommand`は、`MVCRenderCommand`サービスを提供する [`Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) です。 コンポーネントプロパティは、`A4P1AbleMVCRenderCommand`を`com_acme_a4p1_web_internal_portlet_A4P1Portlet`という名前のポートレットに適用し、`A4P1AbleMVCRenderCommand`をMVCコマンド名`/a4p1/able`にマップします。

```{note}
ポートレットごとに個別の `javax.portlet.name`プロパティを宣言することにより、`MVCRenderCommand`コンポーネントを複数のポートレットに関連付けることができます。 


      @Component(
         property = {
            "javax.portlet.name=com_acme_a4p1_web_internal_portlet_A4P1Portlet",
            "javax.portlet.name=com_acme_a4p1_web_internal_portlet_A4P2Portlet",
            "mvc.command.name=/a4p1/download"
         },
         service = MVCRenderCommand.class
      )        
```

ポートレットがMVCコマンド名`/a4p1/able`を指定するリクエストパラメータを受け取ると、`A4P1AbleMVCRenderCommand`の`render`メソッドが実行されます。 この`render`メソッドは、それ自体を識別するメッセージをログに記録し、レンダリングするビューのパスを返します。

`A4P1BakerMVCRenderCommand`は`A4P1AbleMVCRenderCommand`に似ていますが、MVCコマンド名が`/a4p1/baker`であり、renderメソッドがビューパス`/a4p1/baker.jsp`を返す点が異なります。

これらのMVCレンダーコマンドの例は、デモンストレーションの目的で簡単な機能を提供します。 ビューのレンダリングに必要なロジックを使用して、MVCレンダーコマンドの`render`メソッドを実装します。

サンプルURLは、MVCレンダーコマンドをトリガーします。

<a name="examine-the-portlet-render-urls" />

## ポートレットレンダーURLを調べる

`able.jsp`ファイルと`baker.jsp`ファイルは、ポートレットレンダーURLを使用して間接的に相互にリンクします。 以下は`able.jsp`です。

```{literalinclude} ./mvc-render-command/resources/liferay-a4p1.zip/a4p1-web/src/main/resources/META-INF/resources/a4p1/able.jsp
:language: jsp
```

`portlet:renderURL`タグはポートレットtaglibから利用可能で、プレフィックス`portlet`が割り当てられます。 このレンダーURLは、`mvcRenderCommandName`ポートレットパラメーター値`/a4p1/baker`を宣言します。これは`A4P1AbleMVCRenderCommand`のMVCコマンド名です。 変数`bakerURL`は、このレンダーURLを参照します。

ハイパーリンク`<a href="<%= bakerURL %>">Go to Baker</a>`は、レンダーURLをアクションにバインドします。 ユーザーがハイパーリンクをクリックすると、その`mvc.command.name`コンポーネントプロパティ値`/a4p1/baker`が`mvcRenderCommandName`パラメーター値と一致するため、ポートレットは [`RenderRequest`](https://docs.liferay.com/portlet-api/2.0/javadocs/javax/portlet/RenderRequest.html) を`A4P1BakerMVCRenderCommand`に送信します。

`baker.jsp`は`able.jsp`に似ていますが、ポートレットレンダーURLの`mvcRenderCommandName`パラメーター値が`/a4p1/able`である点が異なります。 各JSPの`portlet:renderURL`タグは、MVCレンダーコマンドの`mvc.command.name`プロパティ値をタグの`mvcRenderCommandName`ポートレットパラメーターに割り当てることにより、MVCレンダーコマンドにマップされます。

| `able.jsp`ポートレットレンダーURLパラメーター                                             | `A4P1BakerMVCRenderCommand`コンポーネントプロパティ |
| ------------------------------------------------------------------------- | --------------------------------------- |
| `<portlet:param name="mvcRenderCommandName" value="/a4p1/baker" />` | `mvc.command.name=/a4p1/baker`          |

| `baker.jsp`ポートレットレンダーURLパラメーター                                           | `A4P1AbleMVCRenderCommand`コンポーネントプロパティ |
| ------------------------------------------------------------------------ | -------------------------------------- |
| `<portlet:param name="mvcRenderCommandName" value="/a4p1/able" />` | `mvc.command.name=/a4p1/able`          |

<a name="whats-next" />

## 次のステップ

これで、MVCレンダーコマンドクラスにレンダーロジックを実装する方法がわかりました。 次に、[MVCリソースコマンド](./mvc-resource-command.md)クラスを使用してファイルなどのリソースを操作できます。

<a name="additional-information" />

## 追加情報

* [MVCリソースコマンド](./mvc-resource-command.md)
* [MVCアクションコマンド (近日公開！)](./mvc-action-command.md)
* [MVCポートレットでのローカライズされたメッセージの使用](./using-localized-messages-in-an-mvc-portlet.md)
* [ポートレット](../reference/portlets.md)
