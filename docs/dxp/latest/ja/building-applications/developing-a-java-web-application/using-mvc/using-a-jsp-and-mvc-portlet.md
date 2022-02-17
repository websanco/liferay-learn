# JSPおよびMVCポートレットの使用

Webアプリケーションの開発を開始する簡単な方法は、JSPファイルにマークアップを追加し、ポートレットJavaクラスを使用してそれをレンダリングすることです。

サンプルのW3E7アプリケーションでは、このアプローチを示しています。

![これは、ポートレットアプリケーションの例です。](./using-a-jsp-and-mvc-portlet/images/01.png)

アプリケーションには、マークアップコンテンツを含むJSPと、JSPをレンダリングする`MVCPortlet`クラスがあります。 サンプルをデプロイして、MVCポートレットを含むJSPを使用してアプリケーションを作成する方法を学習します。

<a name="シンプルなmvcポートレットモジュールをデプロイする" />

## シンプルなMVCポートレットモジュールをデプロイする

サンプルをデプロイすることから始めます。

1. サンプルをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/developing-applications/developing-a-java-web-application/using-mvc/liferay-w3e7.zip -O
   ```

   ```bash
   unzip liferay-w3e7.zip
   ```

1. [Liferay Dockerコンテナ](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md)を起動します。

   ```bash
   docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
   ```

1. サンプルをビルドしてデプロイします。

    ```bash
    cd liferay-w3e7
    ```

    ```bash
     ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.w3e7.web_1.0.0
    ```

1. ブラウザで`http://localhost:8080`を開きます。

1. デフォルトの認証情報を使用してサインインします。

    **ユーザー名** : `test@liferay.com`

    **パスワード：** `test`

1. ［**Samples**］ カテゴリからウィジェットページに ［**W3E7ポートレット**］ ウィジェットを追加して、アプリケーションの可用性を確認します。

![これは、ポートレットWebアプリケーションの例です。](./using-a-jsp-and-mvc-portlet/images/02.png)

新しいアプリケーションの構築とデプロイが完了しました。

次に、このポートレットアプリケーションを作成する方法を学習します。

<a name="mvcポートレットを使用してアプリケーションを作成する方法" />

## MVCポートレットを使用してアプリケーションを作成する方法

サンプルポートレットを構築するには、次の2つの手順があります。

1. JSPを使用してビューを作成する。
2. [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java) を作成して、アプリケーションを登録し、レンダリングする。

### ビューテンプレートを作成する

モジュールの`src/main/resources/META-INF/resources`フォルダに、ビューテンプレートとなるJSPファイルを作成します。 ファイル名は任意です。サンプルのJSPファイルは`view.jsp`です。 JSPコンテンツは次のとおりです。

```{literalinclude} ./using-a-jsp-and-mvc-portlet/resources/liferay-w3e7.zip/w3e7-web/src/main/resources/META-INF/resources/view.jsp
   :language: jsp
```

上のマークアップには、「Hello W3E7」という見出しが表示されています。

### MVCPortletを作成する

モジュールの`src/main/java`フォルダに、`com.acme.w3e7.web.internal.portlet`というパッケージを作成します。 そのパッケージに、 [`MVCPortlet`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/portlet/bridges/mvc/MVCPortlet.java) を拡張する`W3E7Portlet`というクラスを追加します。

```{literalinclude} ./using-a-jsp-and-mvc-portlet/resources/liferay-w3e7.zip/w3e7-web/src/main/java/com/acme/w3e7/web/internal/portlet/W3E7Portlet.java
   :language: java
   :lines: 17
```

```{note}
パッケージ名の`*.web.internal.portlet`の部分はコンベンションです。つまり、Webモジュールタイプなので` web`、ポートレット実装がプライベートであるため `internal`、クラスがポートレットであるため` portlet`となっています。
```

この拡張機能には、追加のメソッドは必要ありません。 `MVCPortlet`の組み込みメソッドは、コンポーネントのアノテーション（次で追加）を使用して`view.jsp`テンプレートをレンダリングします。

### アノテーションを使用してポートレットを構成する

[`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html) アノテーションは、ポートレットを構成します。

```{literalinclude} ./using-a-jsp-and-mvc-portlet/resources/liferay-w3e7.zip/w3e7-web/src/main/java/com/acme/w3e7/web/internal/portlet/W3E7Portlet.java
   :language: java
   :lines: 9-18
```

`service = Portlet.class`属性は、クラスを`Portlet`として登録します。

`property`属性の値は、ポートレットWebアプリケーションを記述します。 `com.liferay.portlet.display-category = category.sample`プロパティは、アプリをサンプルのウィジェットカテゴリに追加します。 `javax.portlet.display-name=W3E7 Portlet`プロパティは、アプリの名前を指定します。

`javax.portlet.init-param.view-template=/view.jsp`プロパティは、アプリケーションの`resources/META-INF/resources`フォルダに関するビューテンプレートパスを宣言します。 ポートレットをページに追加すると、`resources/META-INF/resources/view.jsp`ビューテンプレートがレンダリングされます。

```{note}
[Portlet Descriptor to OSGi Service Property Map](../../reference/portlet-descriptor-to-osgi-service-property-map.md)では、OSGiコンポーネントのプロパティ値を従来のポートレット記述子にマップする方法を指定します。
```

<a name="次のステップ" />

## 次のステップ

　 JSPと1つの単純なJavaクラスを使用してWebアプリケーションを作成しました。 ここから先は、いろいろな選択があります。 ビューをさらに追加するには、 [MVCポートレットを使用したビューのレンダリング](./rendering-views-with-mvc-portlet.md) を参照してください。 アプリケーションにアクションを追加するには、 [MVCアクションコマンド](./mvc-action-command.md) を参照してください。 バックエンドのデータモデルを実装するには、 [サービスビルダー](../../data-frameworks/service-builder.md) を参照してください。

<a name="追加情報" />

## 追加情報

* [OSGiサービスのプロパティマップへのポートレット記述子](../../reference/portlet-descriptor-to-osgi-service-property-map.md)
* [MVCの使用](../using-mvc.md)
