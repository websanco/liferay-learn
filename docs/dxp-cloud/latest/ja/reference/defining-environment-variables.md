# 環境変数の定義

環境変数は動的なプレースホルダーのセットであり、環境内でのサービスの動作に影響を与える可能性があります。

環境変数を定義する方法は2つあります。 DXP Cloud管理コンソールを使用する方法と、サービスごとに `LCP.json` ファイルを設定する方法です。

> **注：** DXP Cloudは常に設定に対する最新の変更を適用します。 LCP.jsonファイルで最新の変更が行われた場合、再起動時に、環境変数がWebコンソールに反映されます。 ただし、Webコンソールで環境変数が変更された場合、コンテナはそれらの新しい設定で再起動されます。

## DXP Cloud管理コンソールを介した環境変数の定義

DXP Cloud管理コンソールにログインしている間：

1.  環境（たとえば、UAT）の左側のメニューで[ *Services* ]をクリックします。
2.  「*環境変数* タブをクリックします。
3.  各環境変数をキーと値のペアとして入力します（例：クラスタリングを無効にするため）。
      - **キー**： `LCP_PROJECT_LIFERAY_CLUSTER_ENABLED`
      - **値**： `false`
4.  [ *環境変数の更新*]クリックします。

これで、サービスは更新された環境変数で再起動します。

![図1：Webコンソールを介した環境変数の定義](./defining-environment-variables/images/01.png)

## LCP.jsonによる環境変数の定義

`env` プロパティを使用して `LCP.json` 設定することにより、環境変数を追加できます。 この例では、環境変数 `MYSQL_ROOT_PASSWORD` および `MYSQL_ROOT_USER` をそれぞれ値 `pass` および `example`で追加します。

``` json
{
  "id"."db",
  "image"."mysql",
  "env".{
    "MYSQL_ROOT_PASSWORD"."pass",
    "MYSQL_ROOT_USER"."example"
  } }
}。
```

## 秘密の環境変数

通常の環境変数には、特別なセキュリティ対策はありません。 DXP Cloudプロジェクトにアクセスできるすべてのユーザーは、サービスの変数も見ることができます。

ただし、機密情報（資格情報など）を変数として格納するには、代わりにシークレットを使用できます。 シークレットはバックエンドで暗号化され、 *管理者* 役割がないユーザーから非表示にすることができます。 詳しくは、 [この記事](../infrastructure-and-operations/security/managing-secure-environment-variables-with-secrets.md) を参照してください。

## 追加情報

  - [Configuration via LCP JSON](../reference/configuration-via-lcp-json.md)
  - [データベースサービス](../platform-services/database-service.md)
  - [シークレットによる安全な環境変数の管理](../infrastructure-and-operations/security/managing-secure-environment-variables-with-secrets.md)
