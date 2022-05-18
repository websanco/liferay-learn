# Wiki APIの基本

LiferayのHeadless Deliveryアプリケーションは、[Wiki](../getting-started-with-wikis.md)アプリケーションにRESTサービスを提供します。 これらのサービスを使用すると、Wikiノードとページを追加したり、それらの情報を一覧表示したり、コンテンツを変更したり、完全に削除したりできます。 ここでは、cURLコマンドとJavaクラスを使用してこれらのサービスを呼び出します。

<a name="preparing-tutorial-resources" />

## チュートリアルリソースの準備

チュートリアルに進む前に、まずクリーンなLiferay Dockerコンテナをセットアップし、提供されたチュートリアルコードを使用できるように準備します。

### Liferay Dockerコンテナ

次のチュートリアルでは、チュートリアルコードをテストするためにDXP/Portal 7.3+インスタンスが必要です。 Wiki・アプリケーションはサイトスコープであるため、チュートリアル全体でサイトIDも必要になります。

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

開始したら、Site IDを取得します。 サイトIDを見つけるには、 **サイトメニュー**（![Site Menu](../../../images/icon-menu.png)）を開き、 ［**Configuration**］ &rarr; ［**Site Settings**］ &rarr; ［**Site Configuration**］ に移動します。

![サイトの構成設定でサイトIDを見つけます。](./wiki-api-basics/images/01.png)

### チュートリアルコード

このチュートリアルでは、Headless APIを示すサンプルコードを提供します。 このコードには、チュートリアル全体で使用するためのサンプルのcURLファイルとJavaファイルの両方が含まれています。

次のコマンドを実行して、 [サンプルコード](https://learn.liferay.com/dxp/latest/ja/collaboration-and-social/wiki/developer-guide/liferay-q8u2.zip) をダウンロードして解凍します。

```bash
curl https://learn.liferay.com/dxp/latest/ja/collaboration-and-social/wiki/developer-guide/liferay-q8u2.zip -O
```

```bash
unzip liferay-q8u2.zip
```

cURLスクリプトを使用する準備ができたら、実行する前にJavaソースファイルを手動でコンパイルする必要があります。 これを行うには、プロジェクトの`java`フォルダに移動し、`javac`コマンドを実行します。

```bash
cd liferay-q8u2/java
```

```bash
javac -classpath .:* *.java
```

<a name="using-wikinode-and-wikipage-headless-apis" />

## WikiNodeおよびWikiPage Headless APIの使用

サンプルコードには、次のAPIを呼び出すcURLスクリプトとJavaクラスが含まれています。

| サービス     | HTTPメソッド | HTTPエンドポイント                                      | Javaメソッド                   | 説明                                                                              |
| -------- | -------- | ------------------------------------------------ | -------------------------- | ------------------------------------------------------------------------------- |
| WikiNode | `POST`   | `/v1.0/sites/{siteId}/wiki-nodes`                | `postSiteWikiNode`         | API呼び出しで提供された詳細を使用して、指定されたサイトに新しいWikiノードを作成します                                  |
| WikiNode | `GET`    | `/v1.0/sites/{siteId}/wiki-nodes`                | `getSiteWikiNodesPage`     | 指定されたサイト内のすべてのWikiノードの完全なリストを返します。結果に対して、ページ分割、絞り込み、検索、およびソートをすることができます         |
| WikiNode | `GET`    | `/v1.0/wiki-nodes/{wikiNodeId}`                  | `getWikiNode`              | 指定されたノードエンティティの詳細を返します                                                          |
| WikiNode | `PUT`    | `/v1.0/wiki-nodes/{wikiNodeId}`                  | `putWikiNode`              | 指定されたノードの詳細をAPI呼び出しで提供されたものに置き換えます                                              |
| WikiNode | `DELETE` | `​/v1.0​/wiki-nodes​/{wikiNodeId}`               | `deleteWikiNode`           | 指定されたノードを削除し、操作が成功した場合は204を返します                                                 |
| WikiPage | `POST`   | `/v1.0/wiki-nodes/{wikiNodeId}/wiki-pages`       | `postWikiNodeWikiPage`     | API呼び出しで提供された詳細を使用して、指定されたノードに新しいWikiページを作成します                                  |
| WikiPage | `POST`   | `/v1.0/wiki-pages/{parentWikiPageId}/wiki-pages` | `postWikiPageWikiPage`     | API呼び出しで提供された詳細を使用して、指定されたWikiページの新しいWiki子ページを作成します。                            |
| WikiPage | `GET`    | `/v1.0/wiki-nodes/{wikiNodeId}/wiki-pages`       | `getWikiNodeWikiPagesPage` | 指定されたWikiノードに追加されたすべてのWikiページの完全なリストを返します。結果に対して、ページ分割、絞り込み、検索、およびソートをすることができます |
| WikiPage | `GET`    | `/v1.0/wiki-pages/{parentWikiPageId}/wiki-pages` | `getWikiPageWikiPagesPage` | 指定されたWikiページに追加されたすべてのWiki子ページの完全なリストを返します。結果に対して、ページ分割、絞り込み、検索、およびソートすることができます |
| WikiPage | `GET`    | `/v1.0/wiki-pages/{wikiPageId}`                  | `getWikiPage`              | 指定されたWikiページエンティティの詳細を返します                                                      |
| WikiPage | `PUT`    | `/v1.0/wiki-pages/{wikiPageId}`                  | `putWikiPage`              | 指定されたページの詳細をAPI呼び出しで提供されたものに置き換えます                                              |
| WikiPage | `DELETE` | `/v1.0/wiki-pages/{wikiPageId}`                  | `deleteWikiPage`           | 指定されたページを削除し、操作が成功した場合は204を返します                                                 |

```{important}
WikiページにPOSTメソッドとPUTメソッドを使用する場合は、 `headline`フィールドと` encodingFormat`フィールドの両方を定義する必要があります。 `headline`フィールドはページのメインタイトルを設定し、` encodingFormat`フィールドはページのメディアフォーマット（HTML、BBCodeなど）を決定します。
```

### WikiNode APIの呼び出し

この演習では、cURLコマンドまたはJavaクラスのいずれかを使用してWikiNode APIを呼び出すことができます。 次の出力例はcURLコマンドに対応しており、提供されているJavaクラスの出力とは少し異なります。

1. `liferay-q8u2`プロジェクトの`curl`または`java`フォルダに移動します。

   **cURLの場合：**

   ```bash
   cd liferay-q8u2/curl
   ```

   **Javaの場合：**

   ```bash
   cd liferay-q8u2/java
   ```

1. サイトIDをパラメーターとして使用して、`WikiNode_POST_ToSite`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定したサイトに新しいWikiノードが作成されます。

   **cURLの場合：**

   ```bash
   ./WikiNode **POST** ToSite.sh {site-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DsiteId={site-id} WikiNode***POST** ToSite
   ```

   ターミナルには、新しく作成されたWikiノードの完全なスキーマが表示されます。 提供されているAPI呼び出しでは、新規ノードの`description`および`name`フィールドのみを定義していますが、以下のGET、PUT、DELETEメソッドで使用するためにノードのIDをコピーする必要があります。

   ```bash
   {
     ...
     "description" : "Foo",
     ...
     "id" : 38405,
     "name" : "Able Node",
     ...
   }
   ```

1. サイトIDをパラメーターとして使用して、`WikiNodes_GET_FromSite`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定したサイト内のすべてのWikiノードのリストが返されます。

   **cURLの場合：**

   ```bash
   ./WikiNodes **GET** FromSite.sh {site-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DsiteId={site-id} WikiNodes***GET** FromSite
   ```

   ```{note}
   すべてのDXP/ポータルインスタンスには、 `Main`と呼ばれるデフォルトのWikiノードが付属しています。 このノードは、作成したノードとともに上記の出力に表示されます。
   ```

1. WikiのノードIDをパラメーターとして使用して、`WikiNode_PUT_ById`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定されたWikiノードの詳細がAPI呼び出しで提供された詳細に置き換えられます。

   **cURLの場合：**

   ```bash
   ./WikiNode **PUT** ById.sh {wiki-node-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DwikiNodeId={wiki-node-id} WikiNode***PUT** ById
   ```

   ```bash
   {
     ...
     "description" : "Bar",
     ...
     "id" : 38405,
     "name" : "Baker Node",
     ...
   }
   ```

1. WikiのノードIDをパラメーターとして使用して、`WikiNode_DELETE_ById`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定したノードが削除されます。

   **cURLの場合：**

   ```bash
   ./WikiNode **DELETE** ById.sh {wiki-node-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DwikiNodeId={wiki-node-id} WikiNode***DELETE** ById
   ```

1. 以前のWikiのノードIDをパラメーターとして使用して、`WikiNode_GET_ById`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定されたノードが存在する場合はその詳細が返されます。

   **cURLの場合：**

   ```bash
   ./WikiNode **GET** ById.sh {wiki-node-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DwikiNodeId={wiki-node-id} WikiNode***GET** ById
   ```

   前の手順でノードを削除したため、次のメッセージが返されます。

   ```bash
   {
     "status" : "NOT_FOUND",
     "title" : "No WikiNode exists with the primary key 38405"
   }
   ```

### WikiPage APIの呼び出し

cURLコマンドまたはJavaクラスのいずれかを使用して、WikiPage APIを呼び出すことができます。 以下の出力例は、cURLコマンドに対応したもので、Javaクラスの出力とは異なります。

1. `liferay-q8u2`プロジェクトの`curl`または`java`フォルダに移動します。

   **cURLの場合：**

   ```bash
   cd liferay-q8u2/curl
   ```

   **Javaの場合：**

   ```bash
   cd liferay-q8u2/java
   ```

1. `WikiNode_POST_ToSite`シェルスクリプトまたはJavaクラスを実行して、Wikiページのノードを作成します。

   **cURLの場合：**

   ```bash
   ./WikiNode **POST** ToSite.sh {site-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DsiteId={site-id} WikiNode***POST** ToSite
   ```

   ```{note}
   WikiページはWikiノードに保存されるため、Wikiページを作成する前に少なくとも1つのノードが存在する必要があります。
   ```

1. `WikiPage_POST_ToNode` 呼び出しで使用するために、出力からノードのIDをコピーします。

   ```bash
   {
     ...
     "description" : "Foo",
     ...
     "id" : 38504,
     "name" : "Able Node",
     ...
   }
   ```

1. 上記のノードIDをパラメーターとして使用して、`WikiPage_POST_ToNode`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定したノードの新しいWikiページが作成されます。

   **cURLの場合：**

   ```bash
   ./WikiPage **POST** ToNode.sh {wiki-node-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DwikiNodeId={wiki-node-id} WikiPage***POST** ToNode
   ```

1. `WikiPage_POST_ToParent`呼び出しで使用するためにページのIDをコピーします。

   ```bash
   {
     ...
     "content" : "Foo",
     ...
     "description" : "Able Page",
     "encodingFormat" : "text/x-wiki",
     ...
     "headline" : "Able Page",
     "id" : 38506,
     ...
   }
   ```

1. 上記のWikiページIDをパラメーターとして使用して、`WikiPage_POST_ToParent`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定したWikiページの子ページが作成されます。

   **cURLの場合：**

   ```bash
   ./WikiPage **POST** ToNode.sh {wiki-page-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DparentWikiPageId={wiki-page-id} WikiPage***POST** ToParent
   ```

   ```bash
   {
      ...
      "content" : "Foo",
      ...
      "description" : "Charlie Page",
      "encodingFormat" : "text/x-wiki",
      ...
      "headline" : "Charlie Page",
      "id" : 38510,
      ...
   }
   ```

1. WikiのノードIDをパラメーターとして使用して、`WikiPages_GET_FromNode`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定したノードに追加されたすべてのWikiページのリストが返されます。これには、新しく作成された両方のWikiページが含まれます。

   **cURLの場合：**

   ```bash
   ./WikiPages **GET** FromNode.sh {wiki-node-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DwikiNodeId={wiki-node-id} WikiPages***GET** FromNode
   ```

1. Wikiの親ページIDをパラメーターとして使用して、`WikiPages_GET_FromParent`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定したWikiページの既存の子ページが返されます。

   **cURLの場合：**

   ```bash
   ./WikiPages **GET** FromParent.sh {wiki-page-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DparentWikiPageId={wiki-page-id} WikiPages***GET** FromParent
   ```

1. 上記のWikiページIDのいずれかをパラメーターとして使用して、`WikiPage_PUT_ById`シェルスクリプトまたはJavaクラスを実行します。 これにより、元のページのコンテンツがAPI呼び出しで定義されたコンテンツに置き換えられます。

   **cURLの場合：**

   ```bash
   ./WikiPage **PUT** ById.sh {wiki-page-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DwikiPageId={wiki-page-id} WikiPage***PUT** ById
   ```

   ```bash
   {
     "content" : "Bar",
     ...
     "encodingFormat" : "text/x-wiki",
     ...
     "headline": "Baker Page",
     "id" : 38515,
     ...
   }
   ```

1. 目的のWikiページIDをパラメーターとして使用して、`WikiPage_DELETE_ById`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定したWikiページが削除されます。

   **cURLの場合：**

   ```bash
   ./WikiPage **DELETE** ById.sh {wiki-page-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DwikiPageId={wiki-page-id} WikiPage***DELETE** ById
   ```

1. 削除されたWikiページのIDをパラメーターとして使用して、`WikiPage_GET_ById`シェルスクリプトまたはJavaクラスを実行します。 これにより、指定されたページが存在する場合はその詳細が返されます。

   **cURLの場合：**

   ```bash
   ./WikiPage **GET** ById.sh {wiki-page-id}
   ```

   **Javaの場合：**

   ```bash
   java -classpath .: **-DwikiPageId={wiki-page-id} WikiPage***GET** ById
   ```

   前の手順でページが削除されたため、次のメッセージが表示されます。

   ```bash
   {
     "status" : "NOT_FOUND",
     "title" : "No WikiNode exists with the primary key 38515"
   }
   ```

<a name="examining-the-sample-curl-scripts" />

## サンプルのcURLスクリプトの検証

以下は、チュートリアルのcURLコマンドの代表的な例です。

### `WikiNode_POST_ToSite.sh`

```{literalinclude} ./wiki-api-basics/resources/liferay-q8u2.zip/curl/WikiNode_POST_ToSite.sh
   :language: bash
```

### `WikiPage_POST_ToNode.sh`

```{literalinclude} ./wiki-api-basics/resources/liferay-q8u2.zip/curl/WikiPage_POST_ToNode.sh
   :language: bash
```

### `WikiPages_GET_FromNode.sh`

```{literalinclude} ./wiki-api-basics/resources/liferay-q8u2.zip/curl/WikiPages_GET_FromNode.sh
   :language: bash
```

<a name="examining-the-sample-java-classes" />

## サンプルのJavaクラスの検証

以下は、チュートリアルのJavaコマンドの代表的な例です。

### `WikiNode_POST_ToSite.java`

```{literalinclude} ./wiki-api-basics/resources/liferay-q8u2.zip/java/WikiNode_POST_ToSite.java
   :dedent: 1
   :language: java
   :lines: 4-28
```

### `WikiPage_POST_ToNode.java`

```{literalinclude} ./wiki-api-basics/resources/liferay-q8u2.zip/java/WikiPage_POST_ToNode.java
   :dedent: 1
   :language: java
   :lines: 4-29
```

### `WikiPages_GET_FromNode.java`

```{literalinclude} ./wiki-api-basics/resources/liferay-q8u2.zip/java/WikiPages_GET_FromNode.java
   :dedent: 1
   :language: java
   :lines: 6-25
```

<a name="additional-information" />

## 追加情報

* [ヘッドレスプラットフォームとしてのLiferayの使用](../../../headless-delivery/using-liferay-as-a-headless-platform.md)
* [RESTサービスの使用](../../../headless-delivery/consuming-apis/consuming-rest-services.md)
