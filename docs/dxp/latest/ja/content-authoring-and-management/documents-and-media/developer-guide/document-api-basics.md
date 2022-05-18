# ドキュメントAPIの基本

LiferayのHeadless Deliveryアプリケーションは、[ドキュメントとメディア](../documents-and-media-overview.md)のRESTサービスを提供し、ドキュメントとフォルダの追加、情報の一覧表示、変更、削除などを行います。 ここでは、cURLコマンドとJavaクラスを使用してこれらのサービスを呼び出します。

サンプルのcURLコマンドとJavaクラスを使用してドキュメントをアップロードすることから始めます。

<a name="post-a-document" />

## ドキュメントを投稿する

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. [サイトのIDを検索します](../../../headless-delivery/consuming-apis/consuming-rest-services.md#identify-the-site-containing-the-data) 。 このIDは、いくつかのサービス呼び出しで使用します。

1. [サンプルのプロジェクト](https://learn.liferay.com/dxp/latest/ja/content-authoring-and-management/documents-and-media/developer-guide/liferay-g9i6.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/content-authoring-and-management/documents-and-media/developer-guide/liferay-g9i6.zip -O
    ```

    ```bash
    unzip liferay-g9i6.zip
    ```

cURLスクリプトを使用して、ファイルを[ドキュメントとメディア](../documents-and-media-overview.md)にアップロードします。

1. コマンドラインで、`curl`フォルダに移動します。

    ```bash
    cd liferay-g9i6.zip/curl
    ```

1. サイトIDをパラメーターとして使用して`Document_POST_ToSite.sh`スクリプトを実行し、ファイルをアップロードします。 例:

    ```bash
    ./Document **POST** ToSite.sh 1234
    ```

スクリプトは、それ自体をサイトのドキュメントとメディアにアップロードします。

![ドキュメントとメディアにアップロードされたファイル。](./document-api-basics/images/01.png)

コマンド応答は、次のように、JSONで新しいドキュメントとメディアファイルを記述します。

```bash 
{
    ...
    "description" : "",
    ...
    "id" : 38301,
    ...
    "title" : "Document_POST_ToSite.sh"
}
```

応答には、ファイルの説明、新しく割り当てられたID、タイトルなどが含まれます。 `id`の値を後のコマンドのためにメモしておきます。

次に、Javaクラスを使用してファイルをアップロードします。

1. `java`フォルダに移動し、Javaソースファイルをコンパイルします。

    ```bash
    cd ../java
    ```

    ```bash
    javac -classpath .: *** .java
    ```

1. 以下の`Document_POST_ToSite`クラスを実行し、`siteId`システムプロパティ値をサイトのIDに置き換えて、ファイルをドキュメントとメディアにアップロードします。

    ```bash
    java -classpath .: **-DsiteId=1234 Document****POST** ToSite
    ```

クラスは、ソースファイル`Document_POST_ToSite.java`をドキュメントとメディアにアップロードします。

![JavaクラスはJavaソースファイルをアップロードしました。](./document-api-basics/images/02.png)

cURLコマンドとJavaクラスの仕組みをご覧ください。

<a name="examine-the-curl-command" />

## cURLコマンドを調べる

`Document_POST_ToSite.sh`スクリプトは、cURLを使用して`headless-delivery`アプリケーションのRESTサービスを呼び出すことにより、ファイルをアップロードします。

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/curl/Document_POST_ToSite.sh
   :language: bash
```

コマンドの引数は次のとおりです。

| 引数                                                                      | 説明                                                                   |
|:----------------------------------------------------------------------- |:-------------------------------------------------------------------- |
| `-F "file=@Document_POST_ToSite.sh"`                                    | 投稿するファイル。                                                            |
| `-H "Content-Type: multipart/form-data"`                                | 投稿されているメディアタイプ（ [MIME 種別](https://en.wikipedia.org/wiki/Media_type) ）。 |
| `-X POST`                                                               | 指定されたエンドポイントで呼び出すHTTPメソッド。                                           |
| `"http://localhost:8080/o/headless-delivery/v1.0/sites/${1}/documents"` | RESTサービスエンドポイント。 サイトIDのパラメーターが`${1}`に置き換わります。                        |
| `-u "test@liferay.com:test"`                                            | 基本認証の資格情報。                                                           |

```{note}
   ここでは、デモンストレーションの目的で基本認証を使用しています。 本番環境の場合は、 [OAuth 2.0](../../../headless-delivery/using-oauth2/using-oauth2.md) 経由でユーザーを認証する必要があります。
```

`Document`および`DocumentFolder` RESTサービスの他のcURLコマンドは、同様の引数を使用します。

次に、Javaの呼び出しがいかに似ているかを見てみましょう。

<a name="examine-the-java-class" />

## Javaクラスを調べる

`Document_POST_ToSite.java`クラスは、`headless-delivery`アプリケーションのRESTサービスを呼び出してファイルをアップロードします。

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/java/Document_POST_ToSite.java
   :dedent: 1
   :language: java
   :lines: 10-29
```

このクラスは、次の3行のコードのみを使用してRESTサービスを呼び出します。

| 行（省略形）                                                                     | 説明                                                                                                                                                                                      |
|:-------------------------------------------------------------------------- |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `DocumentResource.Builder builder = ...`                                   | `Builder`を取得し、`DocumentResource`サービスインスタンスを生成します。                                                                                                                                       |
| `DocumentResource documentResource = builder.authentication(...).build();` | 基本認証を指定し、`DocumentResource`サービスインスタンスを生成します。                                                                                                                                            |
| `Document document = documentResource.postSiteDocument(...);`              | `DocumentResource.postSiteDocument`メソッドを呼び出し、サイトID、アップロードされたファイルを表す`Document`オブジェクト、およびアップロードするファイルを指定するハッシュマップを渡します。  ファイルは任意です。この例では、便宜上、ローカルファイル`Document_POST_ToSite.java`を使用しています。 |

```{note}
   `` main``メソッドのコメントは、クラスの実行を示しています。
```

他の例のJavaクラスはこれと類似していますが、異なる`DocumentResource`メソッドを呼び出します。

```{important}
   サービスの詳細は、 [DocumentResource](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/headless/headless-delivery/headless-delivery-client/src/main/java/com/liferay/headless/delivery/client/resource/v1_0/DocumentResource.java) を参照してください。
```

以下は、cURLとJavaを使って、他の`Document` RESTサービスを呼び出す例です。

<a name="get-site-documents" />

## サイトドキュメントを取得する

次のcURLまたはJavaコマンドを実行すると、サイトのドキュメントを一覧表示できます。 上記のように、`1234`をサイトのIDに置き換えてください。

### Documents **GET** FromSite.sh

コマンド:

```bash
./Documents_GET_FromSite.sh 1234
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/curl/Documents_GET_FromSite.sh
   :language: bash
```

### Documents **GET** FromSite.java

コマンド:

```bash 
java -classpath .:* -DsiteId=1234 Documents_GET_FromSite
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/java/Documents_GET_FromSite.java
   :dedent: 1
   :language: java
   :lines: 11-23
```

サイトの`Document`オブジェクトがJSONに一覧表示されます。

<a name="get-a-document" />

## ドキュメントを取得する

次のcURLまたはJavaコマンドを実行すると、`Document`のフィールドを取得できます。 `1234`を`Document`のIDに置き換えてください。

```{tip} 
   ``Documents_GET_FromSite.[java|sh]``を使用して、サイトの``Document`` IDを取得します。
```

### Document **GET** ById.sh

コマンド:

```bash
./Document_GET_ById.sh 1234
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/curl/Document_GET_ById.sh
   :language: bash
```

### Document **GET** ById.java

コマンド:

```bash
java -classpath .:* -DdocumentId=1234 Document_GET_ById
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/java/Document_GET_ById.java
   :dedent: 1
   :language: java
   :lines: 9-20
```

`Document`フィールドがJSONに一覧表示されます。

<a name="get-document-content" />

## ドキュメントの内容を取得する

`Document`コンテンツはBase64でエンコードされ、`Document`の`nestedFields`に埋め込まれます。 次のcURLまたはJavaコマンドを実行すると、コンテンツを取得できます。 `1234`を`Document`のIDに置き換えてください。

### Document **GET** ById_ContentValue.sh

コマンド:

```bash
./Document_GET_ById_ContentValue.sh 1234
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/curl/Document_GET_ById_ContentValue.sh
   :language: bash
```

最初の引数行は、サービスエンドポイントと認証クレデンシャルをそれぞれ指定します。 URLの`/o/headless-delivery/v1.0/documents/${1}`部分は、IDで`Document`を取得するためのRESTサービスエンドポイントです。 このURLは、`Document_GET_ById.sh`スクリプトのURLと同じです。 `?nestedFields=contentValue`部分は、`Document`の`nestedFields`に埋め込まれた`contentValue`を要求します。 最後に、`&fields=contentValue`部分が`contentValue`フィールドで絞り込みを行い、コンテンツフィールドのみが返されます。 ただし、サービスのみを呼び出すと、次のように、JSONでラップされたBase64でエンコードされたコンテンツが返されます。

```bash
{
  "contentValue" : "Y3VybCBcCgktRiAiZmlsZT1ARG9jdW1lbnRfUE9TVF9Ub1NpdGUuc2giIFwKCS1IICJDb250ZW50LVR5cGU6IG11bHRpcGFydC9mb3JtLWRhdGEiIFwKCS1YIFBPU1QgXAoJImh0dHA6Ly9sb2NhbGhvc3Q6ODA4MC9vL2hlYWRsZXNzLWRlbGl2ZXJ5L3YxLjAvc2l0ZXMvJHsxfS9kb2N1bWVudHMiIFwKCS11ICJ0ZXN0QGxpZmVyYXkuY29tOnRlc3Qi"
}
```

サービス呼び出しに続くルーチンで、エンコードされたコンテンツが処理されます。 `sed`ルーチンと`awk`ルーチンは、`Document`コンテンツ値を分離し、`tr`ルーチンはそれをデコードします。 アップロードした`Document_POST_ToSite.sh` `Document`に対して返されるデコードされたコンテンツは次のとおりです。

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/curl/Document_GET_ById_ContentValue.sh
   :language: bash
```

### Document **GET** ById_ContentValue.java

`Document`コンテンツを取得してデコードするJavaコードは、前のcURLコマンドよりも簡単です。

コマンド:

```bash
java -classpath .:* -DdocumentId=1234 Document_GET_ById_ContentValue
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/java/Document_GET_ById_ContentValue.java
   :dedent: 1
   :language: java
   :lines: 11-27
```

ほとんどのコードは、`Document_POST_ToSite.java`のコードに似ています。 主な違いがいくつかあります。

次の行は、`contentValue`のネストフィールドをリクエストパラメータとして追加します。

```java
builder.parameter("nestedFields", "contentValue");
```

IDで`Document`を取得した後、`Base64.Decoder`は`Document`のコンテンツをデコードします。

```java
Base64.Decoder decoder = Base64.getDecoder();
```

<a name="patch-a-document" />

## ドキュメントにパッチを適用する

`Document`のPATCHサービスは、`Document`とそのフィールドを更新します。 次のcURLまたはJavaコマンドを実行して、`Document`を更新できます。 `1234`を`Document`のIDに置き換えてください。

### Document **PATCH** ById.sh

コマンド:

```bash
./Document_PATCH_ById.sh 1234
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/curl/Document_PATCH_ById.sh
   :language: bash
```

最初のフォームデータ部分（-Fに続く）は、`Document`の`description`フィールドに新しい値を指定します。 2番目のフォームデータ部分は、アップロードする更新されたファイルを指定します。

### Document **PATCH** ById.java

コマンド:

```bash 
java -classpath .:* -DdocumentId=1234 Document_PATCH_ById
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/java/Document_PATCH_ById.java
   :dedent: 1
   :language: java
   :lines: 13-34
```

上記のJavaコードは、`DocumentResource`の`patchDocument`メソッドを呼び出し、`Document`'のID、更新するフィールドを含む`Document`オブジェクト、およびアップロードする更新されたファイルを渡します。

上記のコマンドは、`Document`の説明を"Bar"に更新します。

![cURLコマンドは、ドキュメントの説明を変更しました。](./document-api-basics/images/03.png)

<a name="put-a-document" />

## ドキュメントを置き換える

`Document`のPUTサービスは、`Document`とそのフィールドを完全に置き換えます。 次のcURLまたはJavaコマンドを実行して、`Document`を置き換えることができます。 `1234`を`Document`のIDに置き換えてください。

### Document **PUT** ById.sh

コマンド:

```bash
./Document_PUT_ById.sh 1234
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/curl/Document_PUT_ById.sh
   :language: bash
```

最初のフォームデータ部分は、新しい`description`と`title`フィールドの値を設定します。 2番目のフォームデータ部分は、アップロードする置換ファイルを指定します。

### Document **PUT** ById.java

コマンド:

```bash
java -classpath .:* -DdocumentId=1234 Document_PUT_ById
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/java/Document_PUT_ById.java
   :dedent: 1
   :language: java
   :lines: 13-35
```

上記のJavaコードは、`DocumentResource`の`putDocument`メソッドを呼び出し、`Document`のID、`Document`の`description`フィールドと`title`フィールドの値を含む`Document`オブジェクト、およびアップロードする置換ファイルを渡します。

上記のcURLコマンドとJavaクラスは、`Document`インスタンスを、それぞれ新しいタイトル"Document **PUT** ById.sh"と"Document **PUT** ById.java"を持ち、説明が"Goo"である完全に新しいインスタンスに置き換えます。

```{warning}
   現在の``Document``のタイトルを使用する場合を除いて、置換する``Document``に使用する ``title``の値を必ず指定してください。
```

![cURLコマンドがドキュメントを置き換えました。](./document-api-basics/images/04.png)

<a name="delete-a-document" />

## ドキュメントを削除する

次のcURLまたはJavaコマンドを実行して、`Document`を削除できます。 `1234`を`Document`のIDに置き換えてください。

### Document **DELETE** ById.sh

コマンド:

```bash
./Document_DELETE_ById.sh 1234
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/curl/Document_DELETE_ById.sh
   :language: bash
```

### Document **DELETE** ById.java

コマンド

```bash 
java -classpath .:* -DdocumentId=1234 Document_DELETE_ById
```

Code:

```{literalinclude} ./document-api-basics/resources/liferay-g9i6.zip/java/Document_DELETE_ById.java
   :dedent: 1
   :language: java
   :lines: 8-17
```

`Document`は、ドキュメントとメディアから削除されます。

<a name="more-document-and-document-folder-services" />

## その他のドキュメントおよびドキュメントフォルダサービス

次のcURLコマンドとJavaクラスは、その他の`Document`サービスと`DocumentFolder`サービスの詳細を示しています。

| ファイル                                       | 説明                      |
|:------------------------------------------ |:----------------------- |
| `Document_POST_ToDocumentFolder.[java|sh]` | ドキュメントをフォルダに投稿します。      |
| `DocumentFolder_GET_ById.[java|sh]`        | フォルダのフィールドを一覧表示します。     |
| `DocumentFolder_PATCH_ById.[java|sh]`      | フォルダとそのフィールドを更新します。     |
| `DocumentFolder_POST_ToSite.[java|sh]`     | ドキュメントフォルダをサイトに投稿します。   |
| `DocumentFolder_PUT_ById.[java|sh]`        | フォルダとそのフィールドを完全に置き換えます。 |
| `DocumentFolders_GET_FromSite.[java|sh]`   | サイトのフォルダを一覧表示します。       |

[API Explorer](../../../headless-delivery/consuming-apis/consuming-rest-services.md)には、`Document`および`DocumentFolder`のすべてのサービスとスキーマが一覧表示され、各サービスを試すためのインターフェイスがあります。

[DocumentResource](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/headless/headless-delivery/headless-delivery-client/src/main/java/com/liferay/headless/delivery/client/resource/v1 **0/DocumentResource.java) および [DocumentFolderResource](https://github.com/liferay/liferay-portal/blob/[$LIFERAY** LEARN_PORTAL_GIT_TAG$]/modules/apps/headless/headless-delivery/headless-delivery-client/src/main/java/com/liferay/headless/delivery/client/resource/v1_0/DocumentFolderResource.java) のJavaインターフェイスも参照してください。

<a name="additional-information" />

## 追加情報

* [「ドキュメントとメディア」の概要](../documents-and-media-overview.md)
* [RESTサービスの使用](../../../headless-delivery/consuming-apis/consuming-rest-services.md)
* [APIヘッダーリファレンス](../../../headless-delivery/consuming-apis/api-headers-reference.md)
* [GraphQL APIの使用](../../../headless-delivery/consuming-apis/consuming-graphql-apis.md)
