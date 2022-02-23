# ユーザーアカウントAPIの基礎知識

コントロールパネルから [ユーザーの追加と管理](../users/adding-and-managing-users.md) ができますが、LiferayのREST APIを利用することもできます。 これらのサービスを呼び出して、ユーザーの追加、編集、削除を行うことができます。

まず、新しいユーザーを追加します。

## ユーザーの追加

1. Liferay DXPを起動します。 まだDockerコンテナがない場合は、以下を使用します。

   ```bash
   docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
   ```

1. [User Account API Basics](./liferay-y6q4.zip)をダウンロードし解答します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/users-and-permissions/developer-guide/liferay-y6q4.zip -O
   ```

   ```bash
   unzip liferay-y6q4.zip
   ```

1. cURLスクリプトを使用して、Liferayインスタンスに新しいUserを追加します。 コマンドラインで、 `curl` フォルダに移動します。 `User_POST_ToInstance.sh` スクリプトを実行します。

    ```bash
    ./User_POST_ToInstance.sh
    ```

    JSONレスポンスは、新しいUserが追加されたことを示しています。

    ```bash
    {
        "additionalName" : "",
        "alternateName" : "able",
        "birthDate" : "1977-01-01T00:00:00Z",
        "customFields" : [ ],
        "dashboardURL" : "",
        "dateCreated" : "2021-05-19T16:04:46Z",
        "dateModified" : "2021-05-19T16:04:46Z",
        "emailAddress" : "able@liferay.com",
        "familyName" : "Foo",
        "givenName" : "Able",
        "id" : 39321,
        "jobTitle" : "",
        "keywords" : [ ],
        "name" : "Able Foo",
        "organizationBriefs" : [ ],
        "profileURL" : "",
        "roleBriefs" : [ {
            "id" : 20113,
            "name" : "User"
        } ],
        "siteBriefs" : [ {
            "id" : 20127,
            "name" : "Global"
        }, {
            "id" : 20125,
            "name" : "Guest"
        } ],
        "userAccountContactInformation" : {
            "emailAddresses" : [ ],
            "facebook" : "",
            "jabber" : "",
            "postalAddresses" : [ ],
            "skype" : "",
            "sms" : "",
            "telephones" : [ ],
            "twitter" : "",
            "webUrls" : [ ]
        }
    }%   
    ```

    コントロールパネルで、新しく追加されたユーザーを確認します。 ユーザーの `id` の番号を後のコマンドのためにメモしておきます。

    ![コントロールパネルに追加されたユーザーを参照してください。](./user-account-api-basics/images/01.png)

1. RESTサービスは、Javaクラスで呼び出すこともできます。 `curl` フォルダから、 `java` フォルダに移動します。 以下のコマンドでソースファイルをコンパイルします。

    ```bash
    javac -classpath .:* *.java
    ```

1. `User_POST_ToInstance` クラスを以下のコマンドで実行します。

    ```bash
    java -classpath .:* User_POST_ToInstance
    ```

    コントロールパネルで、別のユーザーが追加されていることを確認します。

    ![コントロールパネルに、もう一人のユーザーが追加されました。](user-account-api-basics/images/02.png)

cURLコマンドとJavaクラスの仕組みをご覧ください。

## cURLコマンドの検証

`User_POST_ToInstance.sh` スクリプトは、cURLコマンドでRESTサービスを呼び出します。

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/User_POST_ToInstance.sh
    :language: bash
```

ここでは、コマンドの引数を紹介します。

| 引数                                                                                                                                             | 説明                          |
| ---------------------------------------------------------------------------------------------------------------------------------------------- | --------------------------- |
| `-H "Content-Type: application/json"`                                                                                                          | リクエストの本文の形式がJSONであることを示します。 |
| `-X POST`                                                                                                                                      | 指定されたエンドポイントで呼び出すHTTPメソッド   |
| `"http://localhost:8080/o/headless-admin-user/v1.0/user-accounts"`                                                                             | RESTサービスのエンドポイント            |
| `-d "{\"alternateName\": \"Able\", \"emailAddress\": \"able@liferay.com\", \"familyName\": \"Foo\", \"givenName\": \"Able\"}"` | 投稿をリクエストしているデータ             |
| `-u "test@liferay.com:test"`                                                                                                                   | 基本認証の資格情報                   |

```note::
   ここでは、デモンストレーションの目的で基本認証を使用しています。 本番環境の場合は、`OAuth  <../../../headless-delivery/using-oauth2/using-oauth2.md>`_経由でユーザーを認証する必要があります。
```

他のcURLコマンドも同様のJSON引数を使用しています。

## Javaクラスを調べる

`User_POST_ToInstance.java` クラスは、ユーザー関連サービスを呼び出して、ユーザーを追加します。

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/java/User_POST_ToInstance.java
   :dedent: 1
   :language: java
   :lines: 9-27
```

このクラスは、わずか3行のコードでRESTサービスを呼び出します。

| 行（省略形）                                                                          | 説明                                                             |
| ------------------------------------------------------------------------------- | -------------------------------------------------------------- |
| `UserAccountResource.Builder builder = ...`                                     | ` UserAccountResource `サービスインスタンスを生成するための`Builder`を取得します。      |
| `UserAccountResource userAccountResource = builder.authentication(...).build()` | ベーシック認証を指定し、 `UserAccountResources` サービスインスタンスを生成します。          |
| `UserAccount userAccount = userAccountResource.postUserAccount(...)`            | `userAccountResource.postUserAccount` メソッドを呼び出し、データをpostに渡します。 |

```note::
   main``メソッドのコメントでは、クラスの実行を実演しています。
```

他のJavaクラスの例はこれと似ていますが、異なる `UserAccountResource` メソッドを呼び出しています。

```important::
   サービスの詳細は、`UserAccountResource <https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/headless/headless-admin-user/headless-admin-user-client/src/main/java/com/liferay/headless/admin/user/client/resource/v1_0/UserAccountResource.java>`_を参照してください。
```

以下は、cURLとJavaを使って、他のUser RESTサービスを呼び出す例です。

## インスタンスユーザーの取得

以下のcURLとJavaのコマンドで全ユーザーのリストを取得します。

### Users_GET_FromInstance.sh

コマンド:

```bash
./Users_GET_FromInstance.sh
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/Users_GET_FromInstance.sh
   :language: bash
```

### Users_GET_FromInstance.java

コマンド:

```bash 
java -classpath .:* Users_GET_FromInstance
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/java/Users_GET_FromInstance.java
   :dedent: 1
   :language: java
   :lines: 11-22
```

JSON レスポンスには、そのインスタンスのすべての Users がリストアップされます。

## ユーザーの取得

以下のcURLとJavaコマンドで特定のUserを取得します。 なお、 `1234` は、ユーザーのIDに置き換えてください。

### User_GET_ById.sh

コマンド:

```bash
./User_GET_ById.sh 1234
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/User_GET_ById.sh
   :language: bash
```

### User_GET_ById.java

コマンド:

```bash 
java -classpath .:* -DuserId=1234 User_GET_ById
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/java/User_GET_ById.java
   :dedent: 1
   :language: java
   :lines: 9-20
```

User は JSON レスポンスで返されます。

## ユーザーへのパッチ

以下のcURLとJavaコマンドで、既存のUserの部分編集を行います。 なお、 `1234` は、ユーザーのIDに置き換えてください。

### User_PATCH_ById.sh

コマンド:

```bash
./User_PATCH_ById.sh 1234
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/User_PATCH_ById.sh
   :language: bash
```

### User_PATCH_ById.java

コマンド:

```bash 
java -classpath .:* -DuserId=1234 User_PATCH_ById
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/java/User_PATCH_ById.java
   :dedent: 1
   :language: java
   :lines: 9-25
```

この例では、AbleとBakerの名字がFooからBarに変わっていることに注意してください。

## ユーザーの配置

以下のcURLとJavaコマンドで、既存のUserを完全に上書きします。 なお、 `1234` は、ユーザーのIDに置き換えてください。

### User_PUT_ById.sh

コマンド:

```bash
./User_PUT_ById.sh 1234
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/User_PUT_ById.sh
   :language: bash
```

### User_PUT_ById.java

コマンド:

```bash 
java -classpath .:* -DuserId=1234 User_PUT_ById
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/java/User_PUT_ById.java
   :dedent: 1
   :language: java
   :lines: 9-28
```

なお、この例では、以前のデータがAble GooとBaker Gooに置き換えられています。

![これまでのユーザーデータは、パッチサービスに置き換えられています。](./user-account-api-basics/images/03.png)

## ユーザーの削除

以下のcURLおよびJavaコマンドで既存のUserを削除します。 なお、 `1234` は、ユーザーのIDに置き換えてください。

### User_DELETE_ById.sh

コマンド:

```bash
./User_DELETE_ById.sh 1234
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/User_DELETE_ById.sh
   :language: bash
```

### User_DELETE_ById.java

コマンド:

```bash 
java -classpath .:* -DuserId=1234 User_DELETE_ById
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/java/User_DELETE_ById.java
   :dedent: 1
   :language: java
   :lines: 8-17
```

ユーザー［Able Goo］と［Baker Goo］は削除されました。

## 追加情報

[APIエクスプローラー](../../headless-delivery/consuming-apis/consuming-rest-services.md) でユーザー関連のRESTサービスの一覧を確認できます。
