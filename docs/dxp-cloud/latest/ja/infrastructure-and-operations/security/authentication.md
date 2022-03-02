# 認証

この記事では、Liferay DXP Cloudで使用される2種類の認証リクエストについて説明します。

最初の方法は [基本認証](https://swagger.io/docs/specification/authentication/basic-authentication/) です。これは、サーバーがクライアントに認証を要求するときにエンコードされる承認ヘッダーのHTTPプロトコルです。

2つ目は、ユーザーアクセストークンによるものです。 SSOが有効な場合、トークン認証が必要であることに注意してください。 cookie `access_token` からトークンが取得され、 `dxpcloud-authorization` ヘッダーで使用されます。

アップロードAPIでトークン認証を使用する例を次に示します。

```bash
curl -X POST /
  http://<HOST-NAME>/backup/upload /
  -H 'Content-Type: multipart/form-data' /
  -H 'dxpcloud-authorization: Bearer <USER_TOKEN>' /
  -F 'database=@/my-folder/database.tgz' /
  -F 'volume=@/my-folder/volume.tgz'
```

> **注** ：ヘッダー `dxpcloud-authorization` にユーザートークンを渡すことは、バックアップサービスのバージョン `3.2.0` 以降でのみ機能します。 以前のバージョンは、少なくとも `3.2.0` にアップグレードする必要があります。 以前のバージョンへのリクエストでは、ヘッダー `Authorization: Bearer <PROJECT_MASTER_TOKEN>` を使用する必要があります。 プロジェクトマスタートークンを見つけるには、Liferay DXP Cloudコンソールの任意のシェルでコマンド `env grep LCP_PROJECT_MASTER_TOKEN` を実行します。
