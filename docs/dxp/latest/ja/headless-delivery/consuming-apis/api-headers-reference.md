# APIヘッダーリファレンス

この記事では、Headless APIリクエストを行うときに使用できるヘッダーについて説明します。

  - [`Accept`](#accept)
  - [`Accept-Language`](#accept-language)
  - [`Authorization`](#authorization)
  - [`Content-Type`](#content-type)
  - [`Cookie`](#cookie)

## `Accept`

応答コンテンツの形式を示します。 デフォルトは`json`です。

### 有効なオプション

  - `json`
  - `xml`

### 例

``` bash
curl --header 'Accept: application/xml' 'example.com/o/headless-admin-user/v1.0/user-accounts'
```

## `Accept-Language`

複数の言語に翻訳されたコンテンツを含むリソースの場合、返される応答コンテンツの言語を示します。

### 有効なオプション

すべての有効な言語タグ（ISO-639言語識別子とISO-3166-1 alpha-2国識別子）。例えば `EN-US`、 `ES-ES`。 デフォルトは、要求されたコンテンツを格納するサイトのデフォルト言語です。

### 例

    curl --header 'Accept-Language: pt-BR' 'example.com/o/headless-delivery/v1.0/sites/20124/blog-postings'

## `Authorization`

DXPの認証フレームワークを使用して、リクエストを行っているユーザーを識別します。 （[`Cookie`](#cookie)も参照してください。） これと`Cookie`の両方が提供されていない場合は、リクエストはゲスト（未認証）ユーザーとしてのみ試行されます。

### 有効なオプション

  - `Basic` + Base64エンコードされた認証情報
  - `Bearer` + OAuthトークン

### 例

    curl --header 'Authorization: Basic dXNlcm5hbWU6cGFzc3dvcmQK' 'example.com/o/headless-admin-user/v1.0/user-accounts'

## `Content-Type`

バイナリデータのアップロードを許可します。

### 有効なオプション

  - `multipart/form-data`

### 例

    curl --form 'file=@myfile.txt' --header 'Content-Type: multipart/form-data; boundary=ARBITRARY' 'example.com'

## `Cookie`

DXPの認証フレームワークを使用して、リクエストを行っているユーザーを識別します。 （[`Authorization`](#authorization)も参照してください。） これと`Authorization`の両方が提供されていない場合は、リクエストはゲスト（未認証）ユーザーとしてのみ試行されます。

### 有効なオプション

  - `JSESSIONID`

### 例

    curl --header 'Cookie: JSESSIONID=6349351B37C3EE1F6BA4E128107E9A34' 'example.com/o/headless-admin-user/v1.0/user-accounts'

## `X-Accept-All-Languages`

サーバーが要求されたコンテンツを利用可能なすべての言語で返す必要があることを示します。 （[`Accept-Language`](#accept-language)も参照してください。）

### 有効なオプション

  - `true`
  - `false`

### 例

    curl --header 'X-Accept-All-Languages: true' 'example.com/o/headless-admin-user/v1.0/user-accounts'
