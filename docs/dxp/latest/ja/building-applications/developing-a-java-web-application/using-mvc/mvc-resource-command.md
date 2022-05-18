# MVCリソースコマンド

MVCリソースコマンドのクラスは、アクションやレンダーをトリガーせずに、DXP / Portalインスタンスからリソース（画像、XML、またはその他の種類のリソース）を取得します。 リクエストまたはポートレットリソースURLは、MVCリソースコマンドを呼び出します。

MVCリソースコマンドを使用するサンプルポートレットをデプロイしてから、それを調べます。

<a name="invoke-an-mvc-resource-command" />

## MVCリソースコマンドを呼び出す

サンプルポートレットは、MVCリソースコマンドを使用して単純なファイルをダウンロードします。

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. サンプルをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/building-applications/developing-a-java-web-application/using-mvc/liferay-p8v5.zip -O
   ```

   ```bash
   unzip liferay-p8v5.zip
   ```

1. サンプルをビルドしてデプロイします。

    ```bash
    cd liferay-p8v5
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.p8v5.web_1.0.0
    ```

1. **P8V5ポートレット** ウィジェットを ［**Samples**］ カテゴリからウィジェットページに追加します。 P8V5ポートレットが表示されます。

    ![P8V5ポートレットをページに追加しました。](./mvc-resource-command/images/01.png)

    リンクはMVCリソースコマンドを呼び出して、単純なテキストファイルをダウンロードします。

1. ［**Download**］ をクリックします。 `p8v5.txt`というファイルがマシンにダウンロードされます。

1. `p8v5.txt`ファイルを開きます。 コンテンツは次のとおりです。

    ```
    Hello P8V5!
    ```

MVCリソースコマンドを使用してファイルをダウンロードしました。 次に、それらがどのように機能するかを確認します。

<a name="examine-the-portlet" />

## ポートレットを調べる

`P8V5Portlet`は最小の [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java) です。

```{literalinclude} ./mvc-resource-command/resources/liferay-p8v5.zip/p8v5-web/src/main/java/com/acme/p8v5/web/internal/portlet/P8V5Portlet.java
:language: java
:lines: 9-19
```

[`@Component`](https://osgi.org/javadoc/r6/residential/org/osgi/service/component/annotations/Component.html) `javax.portlet.name`プロパティはポートレットの名前を設定します。

```{literalinclude} ./mvc-resource-command/resources/liferay-p8v5.zip/p8v5-web/src/main/java/com/acme/p8v5/web/internal/portlet/P8V5Portlet.java
:dedent: 2
:language: java
:lines: 14
```

```{note}
`MVCResourceCommand`は、ポートレットの名前（たとえば、ポートレットコンポーネントの` javax.portlet.name`プロパティ値）によってポートレットにバインドします。
```

次に、ポートレットのMVCリソースコマンドクラスを調べます。

<a name="examine-the-mvcresourcecommand-class" />

## MVCResourceCommandクラスを調べる

MVCリソースコマンドクラスは、 [`MVCResourceCommand`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCResourceCommand.java) を直接実装することも、 [`BaseMVCResourceCommand`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/BaseMVCResourceCommand.java) を拡張することによって間接的に実装することもできます。  `P8V5DownloadMVCResourceCommand`は、`MVCResourceCommand`を直接実装します。

```{literalinclude} ./mvc-resource-command/resources/liferay-p8v5.zip/p8v5-web/src/main/java/com/acme/p8v5/web/internal/portlet/action/P8V5DownloadMVCResourceCommand.java
:language: java
:lines: 16-47
```

`P8V5DownloadMVCResourceCommand`は、`MVCResourceCommand`サービスを提供する [`Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) です。 コンポーネントプロパティは、`P8V5DownloadMVCResourceCommand`を`com_acme_p8v5_web_internal_portlet_P8V5Portlet`という名前のポートレットに適用し、`P8V5DownloadMVCResourceCommand`をMVCコマンド名`/p8v5/download`にマップします。 ここで指定する名前は、ポートレットで宣言されている名前と一致する必要があります。

```{note}
ポートレットごとに個別の `javax.portlet.name`プロパティを宣言することにより、`MVCResourceCommand`コンポーネントを複数のポートレットに関連付けることができます。 

      @Component(
         property = {
            "javax.portlet.name=com_acme_p8v5_web_internal_portlet_P8V5Portlet",
            "javax.portlet.name=com_acme_p8v5_web_internal_portlet_P8V6Portlet",
            "mvc.command.name=/p8v5/download"
         },
         service = MVCResourceCommand.class
      )
```

サンプルの`serveResource`メソッドは、単純なテキストファイルを作成し、 [`PortletResponseUtil`](https://github.com/liferay/liferay-portal/blob/master/portal-kernel/src/com/liferay/portal/kernel/portlet/PortletResponseUtil.java) を介してユーザーに送信します。 このメソッドは、エラーが発生した場合は`true`を返し、それ以外の場合は`false`を返します。

次に、ポートレットのJSPがUIコンポーネントアクションをコマンドにマップする方法を学習します。

<a name="examine-the-portlet-resource-url" />

## ポートレットリソースURLを調べる

ポートレットの`view.jsp`ファイルは、MVCリソースコマンドを呼び出すためのリンクをレンダリングします。

```{literalinclude} ./mvc-resource-command/resources/liferay-p8v5.zip/p8v5-web/src/main/resources/META-INF/resources/p8v5/view.jsp
:language: javascript
```

最初の行は、`portlet`のプレフィックスを介してポートレット2.0タグライブラリを使用できるようにしています。 このJSPは、タグライブラリの`portlet:resourceURL`タグを使用してアクションをUIコンポーネントにバインドします。 タグの`id`属性は、MVCリソースコマンドの`mvc.command.name`プロパティ値を使用します。 次の表に、相関関係を示します。

| `view.jsp`ポートレットリソースURL                                                                | `P8V5DownloadMVCResourceCommand`コンポーネントプロパティ |
| -------------------------------------------------------------------------------------- | -------------------------------------------- |
| `<a href="<portlet:resourceURL id="/p8v5/download" />">Download</a>` | `mvc.command.name=/p8v5/download`            |

ビューの **ダウンロード** リンクをクリックすると、`P8V5DownloadMVCResourceCommand`の`serveResource`メソッドが呼び出されます。

<a name="whats-next" />

## 次のステップ

これで、MVCリソースコマンドの使用方法がわかりました。 ポートレットのコンテンツをローカライズする場合は、[Using Localized Messages](./using-localized-messages-in-an-mvc-portlet.md)を参照してください。 モデルレイヤー、永続レイヤー、およびサービスレイヤーを開発する準備ができている場合は、 [サービスビルダー](../../data-frameworks/service-builder.md) をご覧ください。

<a name="additional-information" />

## 追加情報

* [MVCの使用](../using-mvc.md)
* [MVCレンダーコマンド (近日公開！)](./mvc-render-command.md)
* [MVCアクションコマンド (近日公開！)](./mvc-action-command.md)
* [MVCコマンドのオーバーライド (近日公開！)](../../../liferay-internals/extending-liferay/overriding-mvc-commands.md)
