# データベースサービス（MySQL）

データベースサービス（MySQL）は、分散型リレーショナルデータベースサービスであり、アプリケーションのセットアップ、運用、スケーリングを簡素化します。 アプリケーション環境内のプライベートサービスであり、公共のインターネットではなく、他のサービスとしか通信できません。

![図1：データベースサービスは、DXP Cloudで利用可能なサービスの1つです。](./database-service/images/01.png)

## 環境変数

これらの環境変数を設定して、データベースサービスを設定できます。 設定する場合は `LCP_MASTER_USER_NAME`、 `LCP_MASTER_USER_PASSWORD`、および `LCP_DBNAME`、データベースサービスに依存する他のサービス（例えば、バックアップとLiferayのDXPサービス）に同じ値を使用してください。 最初の展開の前にこれらの変数を設定する必要があります。 これらの変数に新しい値を使用してビルドを生成すると、その後のデプロイメントは失敗します。 開発環境では、サービスを削除して `LCP.json` ファイルをこれらの変数の新しい値で更新することは可能ですが、本番環境では実行できません。

| 名前                         | デフォルト値                     | 説明         |
| :--- | :--- | :--- |
| `LCP_DBNAME`               | `lportal`                  | データベース名。   |
| `LCP_MASTER_USER_NAME`     | `dxpcloud`                 | マスターユーザー名。 |
| `LCP_MASTER_USER_PASSWORD` | `LCP_PROJECT_MASTER_TOKEN` | マスターパスワード。 |

### Google Cloud MySQLフラグ

MySQLフラグを環境変数として渡すことができます。 利用可能なフラグは、 [Google Cloud のドキュメント](https://cloud.google.com/sql/docs/mysql/flags)に記載されています。 Liferay DXP Cloudで動作させるには、各フラグの前に `LCP_GCP_DATABASE_FLAG_` を付ける必要があります。 以下に、開発環境でデバッグに役立つ一般的なフラグを示しますが、本番環境ではパフォーマンスに大きなコストがかかるため、使用すべきではありません。

> **警告：** Googleのドキュメントに記載されているように、一部のデータベースフラグ設定はインスタンスの可用性または安定性に影響を与える可能性があります。 これらのフラグを使用するときは十分に注意し、Googleの [運用ガイドライン](https://cloud.google.com/sql/docs/mysql/operational-guidelines)従ってください。

| 名前                                     | 許容値       | デフォルト値 |
| :--- | :--- | :--- |
| `LCP_GCP_DATABASE_FLAG_GENERAL_LOG`    | `on, off` | `off`  |
| `LCP_GCP_DATABASE_FLAG_SLOW_QUERY_LOG` | `on, off` | `off`  |

## 関連情報

  - [MySQLクライアントの使用](../using-the-liferay-dxp-service/using-the-mysql-client.md)
