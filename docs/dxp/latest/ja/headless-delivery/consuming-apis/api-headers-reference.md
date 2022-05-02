# APIヘッダーリファレンス

この記事では、Headless APIリクエストを行うときに使用できるヘッダーについて説明します。

* [`Accept`](#accept)
* [`Accept-Language`](#accept-language)
* [`Authorization`](#authorization)
* [`Content-Type`](#content-type)
* [`Cookie`](#cookie)

<a name="accept" />

<a name="accept" />

## `Accept`

応答コンテンツの形式を示します。 デフォルトは`json`です。

<a name="valid-options" />

### 有効なオプション

* `json`
* `xml`

<a name="example" />

### 例

```bash
curl --header 'Accept: application/xml' 'example.com/o/headless-admin-user/v1.0/user-accounts'
```

<a name="accept-language" />

## `Accept-Language`

複数の言語に翻訳されたコンテンツを含むリソースの場合、返される応答コンテンツの言語を示します。

<a name="valid-options-1" />

### 有効なオプション

すべての有効な言語タグ（ISO-639言語識別子とISO-3166-1 alpha-2国識別子）。例えば `EN-US`、 `ES-ES`。 デフォルトは、要求されたコンテンツを格納するサイトのデフォルト言語です。

<a name="example-1" />

### 例

```
curl --header 'Accept-Language: pt-BR' 'example.com/o/headless-delivery/v1.0/sites/20124/blog-postings'
```

<a name="authorization" />

<a name="authorization" />

## `Authorization`

DXPの認証フレームワークを使用して、リクエストを行っているユーザーを識別します。 （ [`Cookie`](#cookie) も参照してください。） これと`Cookie`の両方が提供されていない場合は、リクエストはゲスト（未認証）ユーザーとしてのみ試行されます。

<a name="valid-options-2" />

### 有効なオプション

* `Basic` + Base64エンコードされた認証情報
* `Bearer` + OAuthトークン

<a name="example-2" />

### 例

```
curl --header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQK' 'example.com/o/headless-admin-user/v1.0/user-accounts'
```

<a name="content-type" />

<a name="content-type" />

## `Content-Type`

バイナリデータのアップロードを許可します。

<a name="valid-options-3" />

### 有効なオプション

* `multipart/form-data`

<a name="example-3" />

### 例

```
curl --form 'file=@myfile.txt' --header 'Content-Type: multipart/form-data; boundary=ARBITRARY' 'example.com'
```

<a name="cookie" />

<a name="cookie" />

## `Cookie`

DXPの認証フレームワークを使用して、リクエストを行っているユーザーを識別します。 （ [`Authorization`](#authorization) も参照してください。） これと`Authorization`の両方が提供されていない場合は、リクエストはゲスト（未認証）ユーザーとしてのみ試行されます。

<a name="valid-options-4" />

### 有効なオプション

* `JSESSIONID`

<a name="example-4" />

### 例

```
curl --header 'Cookie: JSESSIONID=6349351B37C3EE1F6BA4E128107E9A34' 'example.com/o/headless-admin-user/v1.0/user-accounts'
```

<a name="x-accept-all-languages" />

<a name="x-accept-all-languages" />

## `X-Accept-All-Languages`

サーバーが要求されたコンテンツを利用可能なすべての言語で返す必要があることを示します。 （ [`Accept-Language`](#accept-language) も参照してください。）

<a name="valid-options-5" />

### 有効なオプション

* `true`
* `false`

<a name="example-5" />

### 例

```
curl --header 'X-Accept-All-Languages: true' 'example.com/o/headless-admin-user/v1.0/user-accounts'
```
