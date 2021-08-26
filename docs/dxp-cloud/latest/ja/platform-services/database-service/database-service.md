# データベースサービス(MySQL)

データベースサービス(MySQL)は、アプリケーションのセットアップ、運用、スケーリングを簡素化する分散型リレーショナルデータベースサービスです。 これはアプリケーション環境内のプライベートなサービスであり、公共のインターネットではなく、他のサービスとのみ通信することができます。

![図1：データベースサービスは、DXP Cloudで利用可能なサービスの1つです。](./database-service/images/01.png)

詳細は、 [データベースのサービス制限](../../reference/dxp-cloud-limitations.md#database-service) のセクションを参照してください。

## 環境変数

これらの環境変数を設定することで、データベースサービスの設定を行うことができます。 `LCP_MASTER_USER_NAME`、 `LCP_MASTER_USER_PASSWORD`、 `LCP_DBNAME`を設定する際には、データベース・サービスに依存する他のサービス（バックアップ・サービスやLiferay DXPサービスなど）にも同じ値を使用するようにしてください。 これらの変数は、最初のデプロイメントの前に設定しておく必要があります。 これらの変数に新しい値を設定してビルドを生成すると、その後のデプロイに失敗します。 開発環境では、サービスを削除して、これらの変数の新しい値で `LCP.json` ファイルを更新することができるかもしれませんが、本番環境では実行できません。

| 名前                         | デフォルト値                     | 説明         |
| -------------------------- | -------------------------- | ---------- |
| `LCP_DBNAME`               | `lportal`                  | データベース名です。 |
| `LCP_MASTER_USER_NAME`     | `dxpcloud`                 | マスターユーザー名。 |
| `LCP_MASTER_USER_PASSWORD` | `LCP_PROJECT_MASTER_TOKEN` | マスターパスワード。 |

### Google Cloud MySQL フラグ

MySQLのフラグを環境変数として渡すことができます。 利用可能なフラグは、 [Google Cloudのドキュメント](https://cloud.google.com/sql/docs/mysql/flags)に記載されています。 各フラグは、Liferay DXP Cloudで動作させるためには、 `LCP_GCP_DATABASE_FLAG_` を前置する必要があります。 以下は、開発環境でのデバッグに役立つ一般的なフラグですが、本番環境ではパフォーマンスに大きな影響を与えるため、使用しないでください。

``` warning::
   As noted in Google's documentation, some database flag settings can affect instance availability or stability. Be very careful when using these flags and follow Google's `Operational Guidelines <https://cloud.google.com/sql/docs/mysql/operational-guidelines>`_.
```

| 名前                                     | 許容値       | デフォルト値 |
| -------------------------------------- | --------- | ------ |
| `LCP_GCP_DATABASE_FLAG_GENERAL_LOG`    | `on, off` | `off`  |
| `LCP_GCP_DATABASE_FLAG_SLOW_QUERY_LOG` | `on, off` | `off`  |

## 関連情報

  - [データベースのユーザー名の変更](./changing-your-database-username.md)
  - [データベースパスワードの変更](./changing-your-database-password.md)
  - [データベースサービスの制限事項](../../reference/dxp-cloud-limitations.md#database-service)
  - [MySQLクライアントの使用](../../using-the-liferay-dxp-service/using-the-mysql-client.md)
