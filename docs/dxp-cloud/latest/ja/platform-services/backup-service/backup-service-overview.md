# バックアップサービスの概要

プロジェクトのデータを守るためには、定期的にバックアップを取ることが重要です。 DXP Cloudのバックアップサービスでは、環境データの反復を保存し、必要に応じて環境の復元に使用できます。 これらのバックアップには、Liferay DXPデータベースと、 `LIFERAY_HOME/data` フォルダの全コンテンツの両方が含まれます。

![図1：バックアップサービスは、DXP Cloudで利用可能ないくつかのサービスの1つです。](./backup-service-overview/images/01.png)

`prd` 環境の [バックアップ] ページでは、バックアップの作成、保持されたバックアップの表示またはダウンロード、バックアップからの環境の復元を行うことができます。

また、DXP Cloudのコンソールや、バックアップサービスの `LCP.json` ファイルから、プロジェクトのニーズに合わせてバックアップサービスを設定することができます。

詳細は、 [バックアップサービスの制限](../../reference/dxp-cloud-limitations.md#backup-service) のセクションを参照してください。

## バックアップのページ

`prd` 環境の [バックアップ] ページでは、バックアップサービスの情報や保持されているバックアップの確認、手動バックアップの作成などを行うことができます。

``` note::
   バックアップページは本番環境でのみ利用可能です。
```

以下の手順で、[バックアップ]ページにアクセスします。

1.  あなたのプロジェクトの `prd` 環境に移動します。

2.  環境メニューの *バックアップ* をクリックします。

![図2：prd環境の「バックアップ」ページでは、バックアップ履歴の表示や手動バックアップの作成などが可能。](./backup-service-overview/images/02.png)

ここから、以下の作業を行います。

  - **バックアップ情報の表示**: `prd` 環境のバックアップサービス情報をすばやく表示できます。 これには、自動バックアップの頻度、バックアップの保持期間、次回のスケジュールされたバックアップ、最新の作成されたバックアップ、最も古い保持されたバックアップのタイムスタンプ情報が含まれます。
  - **View Backup History**：`prd` 環境で保持しているバックアップの全リストを表示できます。 各エントリには、バックアップの名前、サイズ、作成日時が表示されます。
  - **Create Manual Backups**：`prd` 環境のバックアップを手動で作成することができます。 詳しくは[Creating a Manual Backup](#creating-a-manual-backup) をご覧ください。

<!-- end list -->

``` note::
   バックアップのタイムスタンプはブラウザの位置情報に基づいて自動的に表示され、バックアップのスケジュールはUTC±00のタイムゾーンに基づいて表示されます。
```

環境管理者は、「バックアップ」ページから「アクション」ボタン（⋮）にアクセスし、保持しているバックアップのダウンロードや環境の復元を行うことができます。

これらのアクションの詳細と実行方法については、 [バックアップのダウンロードとアップロード](./downloading-and-uploading-backups.md) および [バックアップからのデータの復元](./restoring-data-from-a-backup.md)を参照してください。

![図3：本番環境の管理者は、「バックアップ」ページからバックアップのダウンロードや環境の復元を行うことができる。](./backup-service-overview/images/03.png)

## 手動バックアップの作成

`prd` 環境を手動でバックアップするには、「バックアップ」ページから、「 *Backup Now*」をクリックします。 このプロセスは、サービスの規模に応じて数分から数時間かかります。

起動すると、バックアップサービスのアイコンはバックアップが進行中であることを示し、新しいバックアップが *バックアップ履歴*に表示されます。

![図4：バックアップサービスのアイコンは、バックアップが進行中であることを示し、バックアップ履歴に新しいバックアップが表示されます。](./backup-service-overview/images/04.png)

*View logs* をクリックするとLogsページにリダイレクトされ、リアルタイムでバックアップステージを確認することができます。 また、バックアップのログは、バックアップサービスのページの *ログ* タブで見ることができます。

サービスログの閲覧については、 [ログ管理](../../troubleshooting/log-management.md) を参照してください。

## バックアップサービスの設定

DXP Cloudのコンソールや、バックアップサービスの `LCP.json` ファイルから、プロジェクトのニーズに合わせてバックアップサービスを設定することができます。

バックアップサービスの設定に使用できる変数の一覧については、 [環境変数のリファレンス](#environment-variables-reference) を参照してください。

``` important::
   バックアップサービスを再設定するたびに、バックアップサービスが再起動し、数分間リクエストを受け取らなくなったり、設定によっては挙動が異なる場合があります。
```

### DXP Cloudコンソールによるバックアップサービスの設定

以下の手順で、DXP Cloud Consoleを使ってバックアップサービスを設定します。

1.  バックアップサービスが導入されている環境に移動します。

2.  環境メニューの *サービス* をクリックします。

3.  *Backup* サービスをクリックします。

4.  *環境変数* タブをクリックします。

    ![図5：本番環境で、バックアップサービスの「変数」タブに移動します。](./backup-service-overview/images/05.png)

    また、環境 *概要* ページの *バックアップ* をクリックすると、バックアップサービスのページにアクセスできます。

5.  [Environment Variables Reference](#environment-variables-reference) のリストから変数を追加して、バックアップサービスを設定します。

6.  *変更を保存*をクリックします。

通常の環境変数とは別に、DXP Cloudのコンソールから *シークレット* 変数を設定することができます。 詳しくは、 [Managing Secure Environment Variables with Secrets](../../infrastructure-and-operations/security/managing-secure-environment-variables-with-secrets.md) をご覧ください。

### バックアップによるバックアップサービスの設定 `LCP.json` ファイル

以下の手順で、バックアップサービスの `LCP.json` ファイルを介してバックアップサービスを設定します。

1.  テキストエディターを使って、以下のパスにあるバックアップの `LCP.json` ファイルを開きます。 `/{your_project_name}/backup/LCP.json`

    ``` note::
       バージョン3.x.xのサービスを利用している場合、バックアップ用の ``LCP.json`` ファイルは次のパスにあります。``/{your_project_name}/lcp/backup/LCP.json`` になります。  
    ```

2.  環境の項目までスクロールしてください。

    ``` 
     "env": {
       "LCP_BACKUP_FOLDER": "/opt/liferay/data",
       "LCP_DATABASE_SERVICE": "mydatabase",
       "LCP_MASTER_USER_PASSWORD": "mypassword"
     },
    ```

3.  [Environment Variables Reference](#environment-variables-reference) のリストから変数を追加して、バックアップサービスを設定します。

4.  ファイルを保存してプロジェクトにデプロイすると、設定が反映されます。

環境サービスの`LCP.json`ファイルによる設定の詳細については、[LCP.jsonによる設定](../../reference/configuration-via-lcp-json.md)を参照してください。

### 自動バックアップとクリーンアップのスケジューリング

バックアップの作成と削除の頻度を決定することは、データの保護とストレージの最適化に役立ちます。

バックアップの作成と削除のタイミングをカスタマイズするには、以下の変数を使用します。

  - **自動バックアップ**: 自動バックアップの頻度を設定するために、 `LCP_BACKUP_CREATE_SCHEDULE` 変数に [cron スケジューリング](https://crontab.guru/) の値を追加します。
  - **自動化されたクリーンアップ**: 自動化されたバックアップ・クリーンアップの頻度を設定するために、 `LCP_BACKUP_CLEANUP_SCHEDULE` 変数に [cron スケジューリング](https://crontab.guru/) の値を追加します。
  - **Retention Period**: `LCP_BACKUP_RETENTION_PERIOD` 変数に数値（1～30の間）を追加して、自動クリーンアップで削除される前にバックアップを保持する日数を設定します。

<!-- end list -->

``` note::
   標準的な`cronのスケジューリング構文 <https://crontab.guru/>`_も非標準的な`cronのスケジューリング構文<https://crontab.guru/> `_もUTC±00のタイムゾーンに基づいています。 When using non-standard cron syntax, automated backups and cleanups run at the start of the specified value. For example, @daily runs backups every day at 00:00 UTC.
```

次の `LCP.json` の例では、12時間ごと（つまりUTCの00:00と12:00）にバックアップを作成し、30日以上前のバックアップを削除するクリーンアップを毎月行います。

``` 
 "env": {
   "LCP_BACKUP_FOLDER": "/opt/liferay/data",
   "LCP_DATABASE_SERVICE": "mydatabase",
   "LCP_MASTER_USER_PASSWORD": "mypassword",
   "LCP_BACKUP_CREATE_SCHEDULE": "0 0,12 * * *",
   "LCP_BACKUP_CLEANUP_SCHEDULE": "@monthly",
   "LCP_BACKUP_RETENTION_PERIOD": "30"
 },
```

## 環境変数リファレンス

| 名前                            | デフォルト値                     | 説明                                                                                                                                        |
| ----------------------------- | -------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------- |
| `LCP_BACKUP_CLEANUP_SCHEDULE` | 0 1 \* \* \*         | この変数は、 [Cronスケジューリングシンタックス](https://crontab.guru/)を使って、自動クリーニングをスケジュールします。 クリーンアップでは、バックアップ保持期間を超えたバックアップをすべて削除します。                       |
| `LCP_BACKUP_CREATE_SCHEDULE`  | `[5-55][0-1] * * *`        | この変数は、 [Cronスケジューリングシンタックス](https://crontab.guru/)を使用して、自動バックアップをスケジュールします。 バックアップサービスのバージョン `3.2.1` 以降では、値が指定されていない場合、ランダムなデフォルトが作成されます。 |
| `LCP_BACKUP_FOLDER`           | `/opt/liferay/data`        | バックアップするLiferayフォルダ。                                                                                                                      |
| `LCP_BACKUP_RESTORE_SCHEDULE` | 該当なし                       | この変数は、 [Cronスケジューリングシンタックス](https://crontab.guru/)を使用して、自動復元をスケジュールします。                                                                   |
| `LCP_BACKUP_RETENTION_PERIOD` | `30`                       | この変数は、スケジュールされたクリーンアップでどのバックアップを削除するかを決定します。 クリーンアップによって削除される前にバックアップを保持する日数を選択します。 最大保存期間は30日です。                                         |
| `LCP_DATABASE_SERVICE`        | `database`                 | データベースサービスのID。                                                                                                                            |
| `LCP_DBNAME`                  | `lportal`                  | データベース名。                                                                                                                                  |
| `LCP_DEBUG_LOG`               | `false`                    | Backupサービスのデバッグログを有効にします。 `true` または `false`に設定します。                                                                                       |
| `LCP_MASTER_USER_NAME`        | `dxpcloud`                 | マスターユーザー名。                                                                                                                                |
| `LCP_MASTER_USER_PASSWORD`    | `LCP_PROJECT_MASTER_TOKEN` | マスターパスワード。                                                                                                                                |

## 追加情報

  - [バックアップのダウンロードとアップロード](./downloading-and-uploading-backups.md)
  - [バックアップからのデータの復元](./restoring-data-from-a-backup.md)
  - [LCP.jsonを介した設定](../../reference/configuration-via-lcp-json.md)
  - [データベースサービス(MySQL)](../database-service/database-service.md)
