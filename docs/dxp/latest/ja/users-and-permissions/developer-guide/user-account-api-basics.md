# ユーザーアカウントAPIの基礎知識

コントロールパネルから [ユーザーの追加と管理](../users/adding-and-managing-users.md) ができますが、LiferayのREST APIを利用することもできます。 これらのサービスを呼び出して、ユーザーの追加、編集、削除を行うことができます。

まず、新しいユーザーを追加します。

<a name="adding-users" />

## ユーザーの追加

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. [User Account API Basics](./liferay-y6q4.zip) をダウンロードし解答します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/users-and-permissions/developer-guide/liferay-y6q4.zip -O
   ```

   ```bash
   unzip liferay-y6q4.zip
   ```

1. cURLスクリプトを使用して、Liferayインスタンスに新しいUserを追加します。 コマンドラインで、 `curl` フォルダに移動します。 `User_POST_ToInstance.sh` スクリプトを実行します。

    ```bash
    ./User **POST** ToInstance.sh
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
    javac -classpath .: *** .java
    ```

1. `User_POST_ToInstance` クラスを以下のコマンドで実行します。

    ```bash
    java -classpath .: **User***POST** ToInstance
    ```

    コントロールパネルで、別のユーザーが追加されていることを確認します。

    ![コントロールパネルに、もう一人のユーザーが追加されました。](user-account-api-basics/images/02.png)

cURLコマンドとJavaクラスの仕組みをご覧ください。

<a name="examine-the-curl-command" />

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
| `-u "test@liferay.com:learn"`                                                                                                                   | 基本認証の資格情報                   |

```{note}
   ここでは、デモンストレーションの目的で基本認証を使用しています。 本番環境の場合は、[OAuth](../../../headless-delivery/using-oauth2/using-oauth2.md) 経由でユーザーを認証する必要があります。
```

他のcURLコマンドも同様のJSON引数を使用しています。

<a name="examine-the-java-class" />

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

```{note}
   main``メソッドのコメントでは、クラスの実行を実演しています。
```

他のJavaクラスの例はこれと似ていますが、異なる `UserAccountResource` メソッドを呼び出しています。

```{important}
   サービスの詳細は、 [UserAccountResource](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/headless/headless-admin-user/headless-admin-user-client/src/main/java/com/liferay/headless/admin/user/client/resource/v1_0/UserAccountResource.java) を参照してください。
```

以下は、cURLとJavaを使って、他のUser RESTサービスを呼び出す例です。

<a name="get-instance-users" />

## インスタンスユーザーの取得

以下のcURLとJavaのコマンドで全ユーザーのリストを取得します。

### Users **GET** FromInstance.sh

コマンド:

```bash
./Users_GET_FromInstance.sh
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/Users_GET_FromInstance.sh
   :language: bash
```

### Users **GET** FromInstance.java

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

<a name="get-a-user" />

## ユーザーの取得

以下のcURLとJavaコマンドで特定のUserを取得します。 なお、 `1234` は、ユーザーのIDに置き換えてください。

### User **GET** ById.sh

コマンド:

```bash
./User_GET_ById.sh 1234
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/User_GET_ById.sh
   :language: bash
```

### User **GET** ById.java

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

<a name="patch-a-user" />

## ユーザーへのパッチ

以下のcURLとJavaコマンドで、既存のUserの部分編集を行います。 なお、 `1234` は、ユーザーのIDに置き換えてください。

### User **PATCH** ById.sh

コマンド:

```bash
./User_PATCH_ById.sh 1234
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/User_PATCH_ById.sh
   :language: bash
```

### User **PATCH** ById.java

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

<a name="put-a-user" />

## ユーザーの配置

以下のcURLとJavaコマンドで、既存のUserを完全に上書きします。 なお、 `1234` は、ユーザーのIDに置き換えてください。

### User **PUT** ById.sh

コマンド:

```bash
./User_PUT_ById.sh 1234
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/User_PUT_ById.sh
   :language: bash
```

### User **PUT** ById.java

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

<a name="delete-a-user" />

## ユーザーの削除

以下のcURLおよびJavaコマンドで既存のUserを削除します。 なお、 `1234` は、ユーザーのIDに置き換えてください。

### User **DELETE** ById.sh

コマンド:

```bash
./User_DELETE_ById.sh 1234
```

Code:

```{literalinclude} ./user-account-api-basics/resources/liferay-y6q4.zip/curl/User_DELETE_ById.sh
   :language: bash
```

### User **DELETE** ById.java

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

<a name="additional-information" />

## 追加情報

[APIエクスプローラー](../../headless-delivery/consuming-apis/consuming-rest-services.md) でユーザー関連のRESTサービスの一覧を確認できます。
