# MVCポートレットでのローカライズされたメッセージの使用

Liferayのローカライズフレームワークは、MVCポートレットでローカライズされたメッセージを作成するためのものです。

<a name="deploy-the-sample-code" />

## サンプルコードをデプロイする

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. サンプルをダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/building-applications/developing-a-java-web-application/using-mvc/liferay-b6f5.zip -O
    ```

    ```bash
    unzip liferay-b6f5.zip
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
    STARTED com.acme.b6f5.web.0.0 [1009]
    ```

1. サンプルのモジュールが機能していることを確認します。 ブラウザで`https://localhost:8080`を開きます。

1. B6F5ポートレットをページに追加します。 サンプルポートレットは、サンプルウィジェットの下にあります。

    ![B6F5ポートレットをページに追加します。](./using-localized-messages-in-an-mvc-portlet/images/01.png)

    例のウェルカムメッセージのヘッダが表示されます。

1. このサンプルプロジェクトには、ポルトガル語と日本語のロケールも含まれています。 たとえば、言語セレクターを使用してブラジルポルトガル語または日本語を選択すると、ウェルカムメッセージが表示されます。

    ![このサンプルでは、ポルトガル語と日本語のロケールが表示されています。](./using-localized-messages-in-an-mvc-portlet/images/02.png)

次に、どのように機能するかを確認します。

<a name="create-the-language-properties-file" />

## 言語プロパティファイルを作成する

`Language.properties`ファイルを作成し、モジュールの`src/main/resources/content`フォルダに追加します。 ファイルで、MVCポートレットで使用するキーを定義します。

他のロケールの言語プロパティファイルもフォルダに含めることができます。 たとえば、日本語の言語キーを含めるには、`Language_ja.properties`ファイルをフォルダに追加します。

<a name="create-the-jsp-file" />

## JSPファイルを作成する

JSPファイルを作成し、モジュールの`/src/main/resources/META-INF/resources`フォルダに追加します。

サンプルプロジェクトには、liferay-uiタグライブラリを使用する簡単なウェルカムメッセージを含むview.jsp</code>ファイルが含まれています。

```jsp
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>

<h4>B6F5 Portlet</h4>

<h5><liferay-ui:message key="b6f5-portlet-welcome" /></h5>
```

サンプルのjspファイルに追加の`liferay-ui:message`が含まれていることに注意してください。

```jsp
<liferay-ui:message key="supercalifragilisticexpialidocious" />
```

これは、`Language.properties`ファイルにそのようなキーが見つからない場合、代わりにラベルが表示されることを示しています。

タグライブラリの詳細については、 [Liferay Tag Library Reference](https://learn.liferay.com/reference/latest/en/dxp/taglibs/util-taglib/index.html) をご覧ください。 各タグライブラリには、タグに渡すことができる属性のリストがあります。

<a name="add-the-component-definition" />

## コンポーネント定義を追加する

カスタムMVCポートレットを作成するときは、クラスの`@Component`定義に言語リソースバンドルプロパティを含めるようにしてください。

```java
@Component(
    property = {
      ...
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.supported-locale=en_US,ja,pt_BR"
    },
    service = Portlet.class
)
```

サンプルプロジェクトには、言語リソースバンドルを使用するための次のプロパティが含まれています。

```java
"javax.portlet.resource-bundle=content.Language"
```

ポートレットがサポートするすべての言語に対して、さまざまなロケールも定義されています。

```java
"javax.portlet.supported-locale=en_US,ja,pt_BR"
```
