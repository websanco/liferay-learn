# Accounts APIの基本

アプリケーションメニューから [アカウント](../accounts.md) を管理することができますが、LiferayのREST APIを利用することもできます。 これらのサービスを呼び出して、アカウントの作成や管理を行います。

<a name="adding-an-account" />

## アカウントの追加

```{include} /_snippets/run-liferay-dxp.md
```

次に、次の手順を実行します。

1. ダウンロードして解凍する [Accounts API Basics](./liferay-t5p9.zip) 。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/users-and-permissions/developer-guide/liferay-t5p9.zip -O
   ```

   ```bash
   unzip liferay-t5p9.zip
   ```

2. cURLスクリプトを使用して、インスタンスに新しいAccountを追加します。 コマンドラインで、 `curl` フォルダに移動します。 `Account_POST_ToInstance.sh` スクリプトを実行します。

    ```bash
    ./Account **POST** ToInstance.sh
    ```

    JSONレスポンスでは、新しいAccountが追加されたことを示しています。

    ```bash
    "description" : "Foo",
    "domains" : [ ],
    "externalReferenceCode" : "",
    "id" : 39302,
    "name" : "Able",
    "numberOfUsers" : 0,
    "organizationIds" : [ ],
    "parentAccountId" : 0,
    "status" : 0,
    "type" : "business"

    ```

3. ［**グローバルメニュー**］ &rarr; ［**アプリケーション**］ &rarr; ［**アカウント**］ に移動します。 新しいアカウントが追加されたことを確認してください。

   ![新しいアカウントが追加されたことを確認します。](./accounts-api-basics/images/01.png)

4. RESTサービスは、Javaクライアントを使って呼び出すこともできます。 `curl` フォルダから、 `java` フォルダに移動します。 以下のコマンドでソースファイルをコンパイルします。

    ```bash
    javac -classpath .: *** .java
    ```

5. `Account_POST_ToInstance.java` クラスを以下のコマンドで実行します。

    ```bash
    java -classpath .: **Account***POST** ToInstance
    ```

<a name="examine-the-curl-command" />

## cURLコマンドの検証

`Account_POST_ToInstance.sh` スクリプトは、cURLコマンドでRESTサービスを呼び出します。

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/curl/Account_POST_ToInstance.sh
    :language: bash
```

ここでは、コマンドの引数を紹介します。

| 引数                                                            | 説明                            |
| ------------------------------------------------------------- | ----------------------------- |
| `-H "Content-Type: application/json"`                         | リクエストボディのフォーマットがJSONであることを示す。 |
| `-X POST`                                                     | 指定されたエンドポイントで起動するHTTPメソッド     |
| `"http://localhost:8080/o/headless-admin-user/v1.0/accounts"` | RESTサービスのエンドポイント              |
| `-d "{\"description\": \"Foo\", \"name\": \"Able\"}"` | 投稿をリクエストしているデータ               |
| `-u "test@liferay.com:learn"`                                  | 基本的な認証情報                      |

```{note}
ここでは、デモのためにベーシック認証を使用しています。 本番環境では、 [OAuth2](./../../installation-and-upgrades/securing-liferay/configuring-sso/using-oauth2/introduction-to-using-oauth2.md)でユーザーを認証する必要があります。
```

他のcURLコマンドも同様のJSON引数を使用しています。

<a name="examine-the-java-class" />

## Javaクラスを調べる

`Account_POST_ToInstance.java` クラスは、Account関連のサービスを呼び出してアカウントを追加します。

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/java/Account_POST_ToInstance.java
   :dedent: 1
   :language: java
   :lines: 9-25
```

このクラスは、わずか3行のコードでRESTサービスを呼び出します。

| 行（省略形）                                                                   | 説明                                                     |
|:------------------------------------------------------------------------ |:------------------------------------------------------ |
| `AccountResource.Builder builder = ...`                                  | ` AccountResourc`サービスインスタンスを生成するための`Builder`を取得します。    |
| `AccountResource accountResource = builder.authentication(...).build();` | ベーシック認証を指定し、 `AccountResource` サービスインスタンスを生成します。       |
| `Account account = accountResource.postAccount(...);`                    | `accountResource.postAccount` メソッドを呼び出し、データをpostに渡します。 |

```{note}
main`メソッドのコメントでは、クラスの実行を実演しています。
```

他のJavaクラスの例はこれと似ていますが、異なる `AccountResource` メソッドを呼び出しています。

```{important}
サービスの詳細は、 [AccountResource](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/headless/headless-admin-user/headless-admin-user-client/src/main/java/com/liferay/headless/admin/user/client/resource/v1_0/AccountResource.java) を参照してください。
```

以下は、cURLとJavaを使って、他の `Account` RESTサービスを呼び出す例です。

<a name="get-accounts-from-instance" />

## インスタンスからアカウントを取得

以下のcURLまたはJavaコマンドを実行することで、アカウントを一覧表示することができます。

### Accounts **GET** FromInstance.sh

コマンド:

```bash
./Accounts_GET_FromInstance.sh
```

Code:

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/curl/Accounts_GET_FromInstance.sh
   :language: bash
```

### Accounts **GET** FromInstance.java

コマンド:

```bash
java -classpath .:* Accounts_GET_FromInstance
```

Code:

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/java/Accounts_GET_FromInstance.java
   :dedent: 1
   :language: java
   :lines: 11-22
```

インスタンスの `アカウント` オブジェクトがJSONで表示されます。

<a name="get-an-account" />

## アカウントの取得

以下のcURLまたはJavaコマンドで特定のアカウントを取得します。

```{tip}
インスタンスのアカウントIDを取得するには、Accounts_GET_FromInstance.[java|sh]を使用します。
```

### Account **GET** ById.sh

コマンド:

```bash
./Account_GET_ById.sh 1234
```

Code:

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/curl/Account_GET_ById.sh
   :language: bash
```

### Account **GET** ById.java

コマンド:

```bash
java -classpath .:* -DaccountId=1234 Account_GET_ById
```

Code:

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/java/Account_GET_ById.java
   :dedent: 1
   :language: java
   :lines: 9-20
```

`アカウント` フィールドはJSONで表示されます。

<a name="patch-an-account" />

## アカウントへのパッチ適用

以下のcURLおよびJavaコマンドで、既存のAccountの部分編集を行います。 なお、 `1234` は、アカウントのIDに置き換えてください。

### Account **PATCH** ById.sh

コマンド:

```bash
./Account_PATCH_ById.sh 1234
```

Code:

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/curl/Account_PATCH_ById.sh
   :language: bash
```

### Account **PATCH** ById.java

コマンド:

```bash
java -classpath .:* -DaccountId=1234 Account_PATCH_ById
```

Code:

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/java/Account_PATCH_ById.java
   :dedent: 1
   :language: java
   :lines: 9-25
```

<a name="put-an-account" />

## アカウントの作成

以下のcURLとJavaコマンドで、既存のAccountを完全に上書きします。 なお、 `1234` は、アカウントのIDに置き換えてください。

### Account **PUT** ById.sh

コマンド:

```bash
./Account_PUT_ById.sh 1234
```

Code:

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/curl/Account_PUT_ById.sh
   :language: bash
```

### Account **PUT** ById.java

コマンド:

```bash
java -classpath .:* -DaccountId=1234 Account_PUT_ById
```

Code:

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/java/Account_PUT_ById.java
   :dedent: 1
   :language: java
   :lines: 9-25
```

<a name="delete-an-account" />

## アカウントの削除

以下のcURLおよびJavaコマンドで既存のAccountを削除します。 なお、 `1234` は、アカウントのIDに置き換えてください。

### Account **DELETE** ById.sh

コマンド:

```bash
./Account_DELETE_ById.sh 1234
```

Code:

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/curl/Account_DELETE_ById.sh
   :language: bash
```

### Account **DELETE** ById.java

コマンド

```bash
java -classpath .:* -DaccountId=1234 Account_DELETE_ById
```

Code:

```{literalinclude} ./accounts-api-basics/resources/liferay-t5p9.zip/java/Account_DELETE_ById.java
   :dedent: 1
   :language: java
   :lines: 8-17
```

[APIエクスプローラー](../../../headless-delivery/consuming-apis/consuming-rest-services.md)には、`アカウント`のすべてのサービスとスキーマが一覧表示され、各サービスを試すためのインターフェイスがあります。
