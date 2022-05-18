# MVCアクションコマンド

MVCアクションコマンドは、アクションを個別のクラスとして処理します。 アクションコマンドを使用すると、多くのアクションを持つ`MVCPortlet`でアクションロジックを整理できます。 ポートレットのJSPのアクションURLは、指定されたMVCアクションコマンドクラスを呼び出します。

MVCアクションコマンドを使用するサンプルポートレットをデプロイしてから、それを調べます。

<a name="invoke-a-portlets-mvc-action-commands" />

## ポートレットのMVCアクションコマンドを呼び出す

```{include} /_snippets/run-liferay-portal.md
```

サンプルポートレットの2つのアクションは、呼び出されているMVCアクションコマンドクラスとメソッドを示すメッセージをログに記録します。 ポートレットをデプロイしてそのアクションをトリガーする方法は次のとおりです。

1. サンプルをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/building-applications/developing-a-java-web-application/using-mvc/liferay-l6y9.zip -O
   ```

   ```bash
   unzip liferay-l6y9.zip
   ```

1. サンプルをビルドしてデプロイします。

    ```bash
    cd liferay-l6y9
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.l6y9.web_1.0.0
    ```

1. **L6Y9 ポートレット** ウィジェットを ［**Samples**］ カテゴリからウィジェットページに追加します。 L6Y9ポートレットが表示されます。

   ![L6Y9ポートレットをページに追加しました。](./mvc-action-command/images/01.png)

   リンクは、さまざまな`MVCActionCommand`クラスのメソッドを呼び出します。 学習の目的で、メソッドは自分自身を識別するメッセージをログに記録します。

1. ［**Do L6Y9 Able**］ をクリックします。 `DoL6Y9AbleMVCActionCommand`は、その`doProcessAction`メソッドの呼び出しをログに記録します。

    ```bash
    [DoL6Y9AbleMVCActionCommand:26] Invoke #doProcessAction(ActionRequest, ActionResponse)
    ```

1. ［**Do L6Y9 Baker**］ をクリックします。 `DoL6Y9BakerMVCActionCommand`は、その`doProcessAction`メソッドの呼び出しをログに記録します。

    ```bash
    [DoL6Y9BakerMVCActionCommand:26] Invoke #doProcessAction(ActionRequest, ActionResponse)
    ```

MVCアクションコマンドの動作を見てきました。 次に、それらがどのように機能するかを確認します。

<a name="examine-the-portlet" />

## ポートレットを調べる

`L6Y9Portlet`は最小の [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java) です。

```{literalinclude} ./mvc-action-command/resources/liferay-l6y9.zip/l6y9-web/src/main/java/com/acme/l6y9/web/internal/portlet/L6Y9Portlet.java
:language: java
:lines: 9-19
```

[`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html) `javax.portlet.name`プロパティはポートレットの名前を設定します。

```{literalinclude} ./mvc-action-command/resources/liferay-l6y9.zip/l6y9-web/src/main/java/com/acme/l6y9/web/internal/portlet/L6Y9Portlet.java
:dedent: 2
:language: java
:lines: 14
```

ポートレットはデフォルトで`/view.jsp`をレンダリングします。

```{note}
`MVCActionCommand`は、ポートレットの名前（たとえば、ポートレットコンポーネントの` javax.portlet.name`プロパティ値）によってポートレットにバインドします。
```

サンプルポートレットは、デフォルトで`view.jsp`をレンダリングします。 次に、JSPがMVCアクションコマンドクラスを呼び出す方法を確認します。

<a name="examine-the-portlet-action-urls" />

## ポートレットアクションのURLを調べる

ポートレットの`view.jsp`ファイルは、ポートレットのMVCアクションコマンドを呼び出すためのリンクをレンダリングします。

```{literalinclude} ./mvc-action-command/resources/liferay-l6y9.zip/l6y9-web/src/main/resources/META-INF/resources/view.jsp
:language: jsp
```

最初の行は、`portlet`のプレフィックスを介してポートレット2.0タグライブラリを使用できるようにしています。 このJSPは、タグライブラリの`portlet:actionURL`タグを使用してアクションをUIコンポーネントにバインドします。 各タグは、MVCアクションコマンドの`mvc.command.name`プロパティ値をタグの`name`属性に割り当てることにより、UIコンポーネントをMVCコマンドにマップします。

| `view.jsp` ポートレットアクションURL                          | `DoL6Y9AbleMVCActionCommand` コンポーネントプロパティ |
| -------------------------------------------------- | ----------------------------------------- |
| `<portlet:actionURL name="/do_l6y9_able" />` | `mvc.command.name=/l6y9/do_l6y9_able`     |

| `view.jsp` ポートレットアクションURL                           | `DoL6Y9BakerMVCActionCommand` コンポーネントプロパティ |
| --------------------------------------------------- | ------------------------------------------ |
| `<portlet:actionURL name="/do_l6y9_baker" />` | `mvc.command.name=/l6y9/do_l6y9_baker`     |

たとえば、 **Do L6Y9 Able** リンクをクリックすると、`DoL6Y9AbleMVCActionCommand`の`doProcessAction`メソッドが呼び出されます。

<a name="examine-the-mvcactioncommand-classes" />

## MVCActionCommandクラスを調べる

MVCアクションコマンドクラスは、 [`MVCActionCommand`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCActionCommand.java) を直接実装することも、 [`BaseMVCActionCommand`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCActionCommand.java) を拡張することによって実装することもできます。 `DoL6Y9AbleMVCActionCommand`は`BaseMVCActionCommand`を拡張します。

```{literalinclude} ./mvc-action-command/resources/liferay-l6y9.zip/l6y9-web/src/main/java/com/acme/l6y9/web/internal/portlet/action/DoL6Y9AbleMVCActionCommand.java
:language: java
:lines: 13-34
```

`DoL6Y9AbleMVCActionCommand`は、`MVCActionCommand`サービスを提供する [`Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) です。 `DoL6Y9AbleMVCActionCommand`のコンポーネントプロパティは、プロパティ`javax.portlet.name=com_acme_l6y9_web_internal_portlet_L6Y9Portlet`を持つポートレットにコンポーネントを適用し、コンポーネントを`/do_l6y9_able`という名前のMVCコマンドにマップします。 ユーザーがそのコマンド名にバインドされたアクションをトリガーすると、`DoL6Y9AbleMVCActionCommand`の`doProcessAction`メソッドが実行されます。 デモンストレーションの目的で、上記の``doProcessAction</0メソッドは、それ自体を識別するメッセージをログに記録します。</p>

<pre><code class="{note}">ポートレットごとに個別の `javax.portlet.name`プロパティを宣言することにより、`MVCActionCommand`コンポーネントを複数のポートレットに関連付けることができます。 


      @Component(
         property = {
            "javax.portlet.name=com **acme** l6y9 **web** internal **portlet** L6Y9Portlet",
            "javax.portlet.name=com **acme** l6y9 **web** internal **portlet** L6Y0Portlet",
            "mvc.command.name=/l6y9/download"
         },
         service = MVCActionCommand.class
      )
``</pre>

`DoL6Y9BakerMVCActionCommand`は`DoL6Y9AbleMVCActionCommand`に似ていますが、名前に`Able`または`able`ではなく`Baker`または`baker`が含まれている点が異なります。

次に、ポートレットのJSPがUIコンポーネントのアクションをコマンドにマップする方法を確認します。

<a name="whats-next" />

## 次のステップ

MVCアクションコマンドの使用方法がわかったので、[MVC Render Commands](./mvc-render-command.md)と[MVC Resource Commands](./mvc-resource-command.md)を確認することをお勧めします。 アプリのコンテンツのローカライズを開始する場合は、[Using Localized Messages](./using-localized-messages-in-an-mvc-portlet.md)を参照してください。 モデルレイヤー、永続レイヤー、およびサービスレイヤーの開発を開始する準備ができている場合は、 [サービスビルダー](../../data-frameworks/service-builder.md) をご覧ください。

<a name="additional-information" />

## 追加情報

* [MVCレンダーコマンド](./mvc-render-command.md)
* [MVCリソースコマンド](./mvc-resource-command.md)
* [MVCコマンドのオーバーライド (近日公開！)](../../../liferay-internals/extending-liferay/overriding-mvc-commands.md)
