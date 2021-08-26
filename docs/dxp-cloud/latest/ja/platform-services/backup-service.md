# バックアップサービス

バックアップサービスは、Liferay DXPデータベースとドキュメントライブラリの定期的なバックアップを作成します。 これらのバックアップには、データベースと、Liferayイメージの `LIFERAY_HOME/data` フォルダーの完全な内容の両方が含まれます。

ここでは、必要に応じてバックアップサービスを使用および設定する方法を学習します。

  - [バックアップのダウンロード](#downloading-a-backup)
  - [スケジューリング](#scheduling)
  - [バックアップAPI](#backup-apis)
  - [環境変数リファレンス](#environment-variables-reference)

バックアップを手動で作成および復元する手順については、「 [バックアップと復元](./backup-and-restore.md)」を参照してください。

![図1：バックアップサービスは、DXP Cloudで利用可能ないくつかのサービスの1つです。](./backup-service/images/01.png)

## バックアップのダウンロード

本番環境の *バックアップ* ページは、DXP Cloudにバックアップをダウンロードする主要な方法です。 これには、データベースとデータボリューム（ドキュメントライブラリおよびLiferayイメージのファイルシステムからの他のファイルを含む）の両方のバックアップが含まれます。

次の手順に従って、本番インスタンスのバックアップをダウンロードします。

1.  DXP Cloudコンソールにログインし、本番環境に移動します。

2.  左側のメニューから[ *Backups* ]をクリックします。

    ![本番環境の左側のメニューから[バックアップ]をクリックします。](./backup-service/images/02.png)

3.  リストされているバックアップの[アクション]メニューをクリックし、[ *ダウンロード*]をクリックします。

    ![リストからバックアップを選択し、[ダウンロード]をクリックします。](./backup-service/images/03.png)

4.  表示されたオプションから、両方のオプションをクリックして、データベースとデータ ボリュームの両方をそれぞれ zip アーカイブとしてダウンロードします。

    ![選択したバックアップのデータベースとデータボリュームの両方をダウンロードします。](./backup-service/images/04.png)

ダウンロードした2つの `.tgz` アーカイブがバックアップを設定　しています。 これらを抽出してローカルのLiferay環境にインポートできます。

ダウンロードAPIを使用してバックアップをダウンロードすることもできます。 詳細は、 [このセクション](#download-database-api) を参照してください。

## スケジューリング

バックアップサービスのスケジューリングをカスタマイズするには、 [のcronスケジューリング構文](https://crontab.guru/)を使用します。 これらの変数にはスケジューリングを使用できます。

  - `LCP_BACKUP_CREATE_SCHEDULE`
  - `LCP_BACKUP_CLEANUP_SCHEDULE`

### スケジューリングのカスタマイズ

次の構文を使用して、カスタマイズされたスケジュールを作成します。

    * * * * * ┬ ┬ ┬ ┬ ┬ │ │ │ │ │ │ │ │ │ └ day of week (0 - 7) (0 or 7 is Sun) │ │ │ └───── month (1 - 12) │ │ └────────── day of month (1 - 31) │ └─────────────── hour (0 - 23) └──────────────────── minute (0 - 59)

例えば、以下は12時間ごと（午前12時と午後12時）にバックアップを実行します。

    0 0,12 * * *

### スケジューリング構文の短縮形

一般的な使用例については、以下の短縮構文を使用してください。

`@yearly`：毎年年明けに実行する。

`@monthly`：毎月開始時に実行します。

`@weekly`：毎週の開始時に実行します。

`@daily`：毎日の開始時に実行します。

`@hourly`：毎時間の開始時に実行します。

## バックアップAPI

バックアップサービスには、バックアップのダウンロードとアップロードに使用できるAPIがあります。 これらのAPIは、 `curl`などのコマンドラインツールを使用して呼び出すことができます。

### ホスト名の取得

バックアップAPIを呼び出すには、バックアップサービスのホスト名が必要です。ホスト名は [Services] ページで確認できます。

![バックアップAPIを呼び出すには、バックアップサービスのホスト名が必要です。](./backup-service/images/05.png)

サービス名、プロジェクト名、環境名を組み合わせてホスト名を設定　します。

この例を考えてみましょう。

  - サービス名： `backup`
  - プロジェクト名： `lfrjoebloggs`
  - 環境名： `prd`
  - ホスト名： `backup-lfrjoebloggs-prd.lfr.cloud`

### 認証

基本認証またはユーザーアクセストークンを使用してリクエストを認証できます。 SSOが有効な場合、トークン認証が必要であることに注意してください。 このトークンをクッキー `access_token` から取得し、 `dxpcloud-authorization` ヘッダーで使用することができます。

アップロードAPIでトークン認証を利用した例をご紹介します。

``` bash
curl -X POST \
  https://backup-<PROJECT-NAME>-prd.lfr.cloud/backup/upload \
  -H 'Content-Type: multipart/form-data' \
  -H 'dxpcloud-authorization: Bearer <USER_TOKEN>' \
  -F 'database=@/my-folder/database.tgz' \
  -F 'volume=@/my-folder/volume.tgz'
```

``` note::
   注：ヘッダー dxpcloud-authorization にユーザートークンを渡すことは、バックアップサービスのバージョン 3.2.0 以降でのみ機能します。 それ以前のバージョンは、少なくとも ``3.2.0`` にアップグレードする必要があります。 以前のバージョンへのリクエストには ``Authorization.Bearer <PROJECT_MASTER_TOKEN>``を使用します。 プロジェクトマスタートークンは、Liferay DXP Cloudコンソールの任意のシェルで ``env | grep LCP_PROJECT_MASTER_TOKEN`` コマンドを実行することで見つけることができます。
```

### データベースAPIのダウンロード

データベースをダウンロードするためのAPIには、TGZファイルを返すエンドポイントが含まれています。 `id` パラメータはバックアップIDを表し、[バックアップ]ページで確認できます。 このIDは、2つのダッシュで区切られた3つの文字列で設定　されています(例： `dxpcloud-lqgqnewltbexuewymq-201910031723`)。

#### パラメーター

| 名前   | タイプ      | 必須 |
| ---- | -------- | -- |
| `id` | `String` | はい |

#### curlの例

``` bash
curl -X GET \
  https://backup-<PROJECT-NAME>-prd.lfr.cloud/backup/download/database/id \
  -u user@domain.com:password \
  --output database.tgz
```

### Data Volume APIをダウンロード

データボリュームをダウンロードするためのAPIには、TGZファイルを返すエンドポイントが含まれています。 `id` パラメータはバックアップIDを表し、[バックアップ]ページで確認できます。 このIDは、2つのダッシュで区切られた3つの文字列で設定　されています(例えば、 `dxpcloud-lqgqnewltbexuewymq-201910031723`)。

#### パラメーター

| 名前   | タイプ      | 必須 |
| ---- | -------- | -- |
| `id` | `String` | はい |

#### カールの例

``` bash
curl -X GET \
  https://backup-<PROJECT-NAME>-prd.lfr.cloud/backup/download/volume/id \
  -u user@domain.com:password \
  --output volume.tgz
```

### バックアップAPIのアップロード

アップロードバックアップAPIを使用すると、DXP Cloudにバックアップをアップロードすることができます。 バックアップをアップロードするには、 次の手順に従う必要があります。

1.  [データベースファイル作成します](#creating-the-database-file)。
2.  [ボリュームファイル作成します](#creating-the-volume-file)。
3.  [データベースとボリュームファイルを使用してバックアップAPI](#invoking-the-backup-api) を呼び出します。

#### データベースファイルの作成

MySQLダンプを作成し、それを `.tgz` アーカイブに圧縮するには、次のコマンドを実行します。

``` bash
mysqldump -uroot -ppassword --databases --add-drop-database lportal | gzip -c | cat > database.gz
```

``` bash
tar zcvf database.tgz database.gz
```

`データベース` および `add-drop-database` オプションは、バックアップの復元が正しく機能するために必要です（ `/ backup/download` APIを使用して、バックアップサービスがMySQLダンプファイルを作成する方法を確認することもできます）。 これらのオプションを使用すると、作成されるダンプファイルには、create tableステートメントの直前に次のコードが含まれます。

``` sql
--
-- Current Database: `lportal`
--

/*!40000 DROP DATABASE IF EXISTS `lportal`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lportal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lportal`;
```

#### ボリュームファイルの作成

このコマンドを実行して、データ ボリュームを圧縮します。

``` bash
cd $LIFERAY_HOME/data && tar -czvf volume.tgz document_library
```

#### バックアップAPIの呼び出し

**パラメーター**

| 名前       | タイプ    | 必須 |
| -------- | ------ | -- |
| `データベース` | `ファイル` | はい |
| `ボリューム`  | `ファイル` | はい |

**curlの例**

``` bash
curl -X POST \
  https://backup-<PROJECT-NAME>-prd.lfr.cloud/backup/upload \
  -H 'Content-Type: multipart/form-data' \
  -F 'database=@/my-folder/database.tgz' \
  -F 'volume=@/my-folder/volume.tgz' \
  -u user@domain.com:password
```

## 環境変数リファレンス

| 名前                            | デフォルト値                     | 説明                                                                                      |
| ----------------------------- | -------------------------- | --------------------------------------------------------------------------------------- |
| `LCP_BACKUP_CREATE_SCHEDULE`  | `[5-55][0-1] * * *`        | バックアップを作成するためのcronスケジュール。 バックアップサービスのバージョン `3.2.1` 以降では、値が指定されていない場合、ランダムなデフォルトが作成されます。 |
| `LCP_BACKUP_FOLDER`           | `/opt/liferay/data`        | バックアップするLiferayフォルダ。                                                                    |
| `LCP_BACKUP_RETENTION_PERIOD` | `30`                       | バックアップを保持する日数。 より長い期間に設定する場合でも、最大保持期間は30日間です。                                           |
| `LCP_DATABASE_SERVICE`        | `database`                 | データベースサービスのID。                                                                          |
| `LCP_DBNAME`                  | `lportal`                  | データベース名。                                                                                |
| `LCP_MASTER_USER_NAME`        | `dxpcloud`                 | マスターユーザー名。                                                                              |
| `LCP_MASTER_USER_PASSWORD`    | `LCP_PROJECT_MASTER_TOKEN` | マスターパスワード。                                                                              |

## 追加情報

  - [Configuration via LCP JSON](../reference/configuration-via-lcp-json.md)
