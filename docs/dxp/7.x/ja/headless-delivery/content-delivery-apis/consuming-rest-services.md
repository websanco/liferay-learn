# RESTサービスの使用

Liferay DXPには、ほとんどのアプリケーションに対応しているRESTサービスが含まれています。 これらのサービスは完全に[OpenAPI](https://app.swaggerhub.com/apis/liferayinc/headless-delivery)に準拠しています。 ここでは、それらを使用する方法を学びます。 必要なステップはわずか3つです。

1.  使用するサービスを特定します。
2.  必要なデータを含むサイトを特定します。
3.  データにアクセスできる資格情報を使用してサービス呼び出しを行います。

この例では、Liferay DXPの新規インストールでDockerイメージを使用しています。

## 使用するサービスを特定する

RESTサービスを呼び出すには、実行中のLiferay DXPが必要です。 Dockerを使用して取得するには、次のコマンドを実行します。

``` bash
docker run -it -p 8080:8080 liferay/portal:7.3.2-ga3
```

Liferay DXPのRESTサービスは、次のURLで公開されています。

    http[s]://[hostname]:[port]/o/api

Dockerインスタンスでは、次の場所にあります。

    http://localhost:8080/o/api

APIはいくつかのカテゴリに分類されます。 この例では、`BlogPosting`サービスを使用して[Blogs]ウィジェットからブログ投稿を取得していますが、この手順は公開されているどのサービスでも使用できます。

1.  *Headless Delivery* カテゴリを選択します。 このカテゴリには `Blog Posting` サービスが含まれています。 フィルターを使用してサービスを検索できます。

2.  [ *スキーマ表示*]ボタンをクリックすると、画面の右側に、このカテゴリのすべてのスキーマのリストが表示されます。

3.  ブラウザーのタブをスキーマブラウザーに開いたままにします。 `Blog Posting`をPUTする場合は、そのスキーマが必要です。

![スキーマブラウザを使用すると、必要なサービスを見つけて呼び出すことができます。 ](./consuming-rest-services/images/01.png)

## データを含むサイトを特定する

Liferay DXPの初期化後、ブラウザで`http://localhost:8080`にアクセスします。

ここで、デフォルトのサイトIDを見つける必要があります。

1.  デフォルトの認証情報を使用してサインインします（**ユーザー名：** `test@liferay.com` **パスワード：** `test`）。
2.  [Control Panel] → [Sites] → [Sites]に移動します。
3.  [Actions]ボタンをクリックし、*[Go to Site Settings]* を選択します。

サイトIDが[Details]セクションの上部に表示されます。 `20122`のような整数になります。

## データにアクセスできる認証情報を使用してサービス呼び出しを行う

これで、呼び出しを行うために必要なものがすべて揃いました。 すべてのWebサービスには、要求しているデータにアクセスできる資格情報を使用してアクセスする必要があります。 最も簡単な方法は、URLで資格情報データを渡す基本認証を使用することです。 これは安全ではないため、この方法は開発時にのみ使用すべきです。 本番環境では、アプリケーションは[OAuth2](../../installation-and-upgrades/securing-liferay/configuring-sso/using-oauth2/introduction-to-using-oauth2.md)を介してユーザーを承認する必要があります。

以下の例では[ curl](https://curl.haxx.se)を使用しています。

### 基本認証を使用したサービスの呼び出し（開発中のみ）

基本認証を使用してサービスを呼び出すには、URLに資格情報を指定します。

``` bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20122/blog-postings/" -u 'test@liferay.com:test'
```

### OAuth2を使用してサービスを呼び出す

本番環境では、[OAuth2アプリケーション](../../installation-and-upgrades/securing-liferay/configuring-sso/using-oauth2/creating-oauth2-applications.md)を作成し、OAuth2プロセスを使用して認証トークンを取得します。 トークンを取得したら、それをHTTPヘッダーに指定します。

``` bash
curl -H "Authorization: Bearer d5571ff781dc555415c478872f0755c773fa159" http://localhost:8080/o/headless-delivery/v1.0/sites/20122/blog-postings
```

## データの取得と投稿

上記のクエリを実行してすべてのブログ投稿を取得すると、何もないことがわかります。

``` json
{
  "actions" : {
    "subscribe" : {
      "method" : "PUT",
      "href" : "http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/subscribe"
    },
    "unsubscribe" : {
      "method" : "PUT",
      "href" : "http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/unsubscribe"
    },
    "create" : {
      "method" : "POST",
      "href" : "http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings"
    }
  },
  "items" : [ ],
  "lastPage" : 1,
  "page" : 1,
  "pageSize" : 20,
  "totalCount" : 0
}
```

まず、ブログエントリを投稿します。

### ブログエントリの投稿

スキーマブラウザーを使用して、ブログエントリを投稿する方法を学ぶことができます。

![サービスのスキーマは、Liferay DXPインスタンスで公開されます。](./consuming-rest-services/images/02.png)

1.  スキーマブラウザを含むブラウザタブに戻ります。 右側にある `BlogPosting` エントリをクリックして、そのスキーマを表示します（上記を参照）。 これは `BlogPosting`データ構造全体を示していますが、必須フィールドは2つだけです。

      - `articleBody`
      - `headline`

2.  ブログエントリを投稿する単純なJSONドキュメントを作成します。

    ``` json
    {
        "headline": "Test Blog Entry from REST Services",
        "articleBody": "This article was posted via REST services provided by Liferay DXP."
    }
    ```

3.  リクエストを行います。

    ``` bash
    curl --header "Content-Type: application/json" --request POST --data '{ "headline": "Test Blog Entry from REST Services", "articleBody": "This article was posted via REST services provided by Liferay DXP." }' http://localhost:8080/o/headless-delivery/v1.0/sites/20122/blog-postings -u test@liferay.com:test
    ```

Liferay DXPは、ブログエントリの完全なJSON表現を返します。

``` json
{
  "actions" : {
    "get" : {
      "method" : "GET",
      "href" : "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/{blogPostingId}"
    },
    "replace" : {
      "method" : "PUT",
      "href" : "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/{blogPostingId}"
    },
    "update" : {
      "method" : "PATCH",
      "href" : "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/{blogPostingId}"
    },
    "delete" : {
      "method" : "DELETE",
      "href" : "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/{blogPostingId}"
    }
  },
  "alternativeHeadline" : "",
  "articleBody" : "This article was posted via REST services provided by Liferay DXP.",
  "creator" : {
    "additionalName" : "",
    "contentType" : "UserAccount",
    "familyName" : "Test",
    "givenName" : "Test",
    "id" : 20125,
    "name" : "Test Test",
    "profileURL" : "/web/test"
  },
  "customFields" : [ ],
  "dateCreated" : "2020-03-06T18:02:26Z",
  "dateModified" : "2020-03-06T18:02:27Z",
  "datePublished" : "2020-03-06T18:02:00Z",
  "description" : "",
  "encodingFormat" : "text/html",
  "friendlyUrlPath" : "test-blog-entry-from-rest-services",
  "headline" : "Test Blog Entry from REST Services",
  "id" : 35215,
  "keywords" : [ ],
  "numberOfComments" : 0,
  "relatedContents" : [ ],
  "siteId" : 20122,
  "taxonomyCategories" : [ ]
}
```

### すべてのブログエントリを取得する

ここで、最初のクエリを繰り返して、投稿したブログエントリが表示されることを確認できます。

``` bash
curl "http://localhost:8080/o/headless-delivery/v1.0/sites/20122/blog-postings/" -u 'test@liferay.com:test'
```

ブログエントリのリストが返されます。 追加したエントリは、リスト内の唯一のエントリです。

``` json
{
  "actions" : {
    "subscribe" : {
      "method" : "PUT",
      "href" : "http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/subscribe"
    },
    "unsubscribe" : {
      "method" : "PUT",
      "href" : "http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings/unsubscribe"
    },
    "create" : {
      "method" : "POST",
      "href" : "http://localhost:8080/o/headless-delivery/v1.0/sites/{siteId}/blog-postings"
    }
  },
  "items" : [ {
    "actions" : {
      "get" : {
        "method" : "GET",
        "href" : "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/{blogPostingId}"
      },
      "replace" : {
        "method" : "PUT",
        "href" : "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/{blogPostingId}"
      },
      "update" : {
        "method" : "PATCH",
        "href" : "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/{blogPostingId}"
      },
      "delete" : {
        "method" : "DELETE",
        "href" : "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/{blogPostingId}"
      }
    },
    "alternativeHeadline" : "",
    "articleBody" : "This article was posted via REST services provided by Liferay DXP.",
    "creator" : {
      "additionalName" : "",
      "contentType" : "UserAccount",
      "familyName" : "Test",
      "givenName" : "Test",
      "id" : 20125,
      "name" : "Test Test",
      "profileURL" : "/web/test"
    },
    "customFields" : [ ],
    "dateCreated" : "2020-03-06T18:02:26Z",
    "dateModified" : "2020-03-06T18:02:27Z",
    "datePublished" : "2020-03-06T18:02:00Z",
    "description" : "",
    "encodingFormat" : "text/html",
    "friendlyUrlPath" : "test-blog-entry-from-rest-services",
    "headline" : "Test Blog Entry from REST Services",
    "id" : 35215,
    "keywords" : [ ],
    "numberOfComments" : 0,
    "relatedContents" : [ ],
    "siteId" : 20122,
    "taxonomyCategories" : [ ]
  } ],
  "lastPage" : 1,
  "page" : 1,
  "pageSize" : 20,
  "totalCount" : 1
}
```

### 単一のブログエントリを取得する

リクエストを行うたびに、Liferay DXPは他の考えられるエンドポイントを返します。 そのうちの1つは、IDによって単一のブログエントリを取得することです。 エントリのIDがわかっている場合は、それを取得できます。

``` bash
curl "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/35215" -u test@liferay.com:test
```

同じブログエントリが返されます。

### ブログエントリの削除

IDがわかっている場合は、ブログエントリを削除することもできます。

``` bash
curl -X DELETE "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/35215" -u test@liferay.com:test
```

この場合、何も返されませんが、上記のようにリクエストすることで、エントリが削除されたことを確認できます。

``` bash
curl "http://localhost:8080/o/headless-delivery/v1.0/blog-postings/35215" -u test@liferay.com:test
```

次に、Liferay DXPは、応答として次のJSONドキュメントを返します。

``` json
{
  "status" : "NOT_FOUND",
  "title" : "No BlogsEntry exists with the primary key 35215"
}
```

Liferay DXPのRESTサービスを呼び出す方法を学びました。 上記の例では基本認証を使用していることに注意してください。本番環境では、OAuth2を使用して安全な方法でサービスを呼び出します。
