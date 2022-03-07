# バックアップサービスの概要

プロジェクトのデータを守るためには、定期的にバックアップを取ることが重要です。 DXP Cloudのバックアップサービスでは、環境データの反復を保存し、必要に応じて環境の復元に使用できます。 これらのバックアップには、Liferay DXPデータベースと、 `LIFERAY_HOME/data` フォルダの全コンテンツの両方が含まれます。

![バックアップサービスは、DXP Cloudで利用可能ないくつかのサービスの1つです。](./backup-service-overview/images/01.png)

どの環境のバックアップページからでも、バックアップの作成、保持されたバックアップの表示またはダウンロード、そしてバックアップから環境を復元することができます。

また、DXP Cloudのコンソールや、バックアップサービスの `LCP.json` ファイルから、プロジェクトのニーズに合わせてバックアップサービスを設定することができます。

詳細は、 [バックアップサービスの制限](../../reference/platform-limitations.md#backup-service) のセクションを参照してください。

<a name="the-backups-page" />

## バックアップのページ

どの環境のバックアップページからでも、バックアップサービスの情報や保持されているバックアップの確認、手動でバックアップを作成したりすることができます。

```{note}
バックアップページは、バックアップサービスのバージョンが4.3.5よりも古い場合、実稼働環境でのみ利用できます。
```

以下の手順で、バックアップページにアクセスします。

1. どの環境からでもいいので、左のメニューにある ［**バックアップ**］ をクリックします。

![どの環境でも、バックアップページからバックアップ履歴を表示したり、手動バックアップを作成したりできます。](./backup-service-overview/images/02.png)

ここから、以下の作業を行います。

***バックアップ情報の表示** : 選択した環境のバックアップサービス情報をすばやく表示できます。 これには、自動バックアップの頻度、バックアップの保持期間、次回のスケジュールされたバックアップ、最新の作成されたバックアップ、最も古い保持されたバックアップのタイムスタンプ情報が含まれます。
***バックアップ履歴の表示** ：選択した環境で保持されているバックアップの完全なリストを表示できます。 各エントリには、バックアップの名前、サイズ、作成日時が表示されます。
***手動バックアップの作成** ：選択した環境のバックアップを手動で作成できます。 詳しくは [Creating a Manual Backup](#creating-a-manual-backup) をご覧ください。

```{note}
バックアップのタイムスタンプはブラウザの位置情報に基づいて自動的に表示され、バックアップのスケジュールはUTC±00のタイムゾーンに基づいて表示されます。
```

環境管理者は、「バックアップ」ページから「アクション」ボタン（⋮）にアクセスし、保持しているバックアップのダウンロードや環境の復元を行うことができます。

これらのアクションの詳細と実行方法については、 [バックアップのダウンロードとアップロード](./downloading-and-uploading-backups.md) および [バックアップからのデータの復元](./restoring-data-from-a-backup.md)を参照してください。

![管理者は、バックアップページからバックアップをダウンロードしたり、環境を復元したりできます。](./backup-service-overview/images/03.png)

<a name="creating-a-manual-backup" />

## 手動バックアップの作成

バックアップページから環境を手動でバックアップするには、 ［**Backup Now**］ をクリックします。 このプロセスは、サービスの規模に応じて数分から数時間かかります。

起動すると、バックアップサービスのアイコンはバックアップが進行中であることを示し、新しいバックアップが **バックアップ履歴** に表示されます。

![バックアップサービスのアイコンは、バックアップが進行中であることを示し、バックアップ履歴に新しいバックアップが表示されます。](./backup-service-overview/images/04.png)

```{warning}
Liferayインスタンス上でデータが能動的に変更されている間に作成されたバックアップは、一貫性のないデータを作成する危険性があります。 完全に一貫したバックアップを行うためには、データベース管理者と調整して、手動バックアップを行っている間は更新をフリーズするようにしてください。
```

**View logs** をクリックするとLogsページにリダイレクトされ、リアルタイムでバックアップステージを確認することができます。 また、バックアップのログは、バックアップサービスのページの **ログ** タブで見ることができます。

サービスログの閲覧については、 [ログ管理](../../troubleshooting/reading-dxp-cloud-service-logs.md) を参照してください。

<a name="configuring-the-backup-service" />

## バックアップサービスの設定

DXP Cloudのコンソールや、バックアップサービスの `LCP.json` ファイルから、プロジェクトのニーズに合わせてバックアップサービスを設定することができます。

バックアップサービスの設定に使用できる変数の一覧については、 [環境変数のリファレンス](#environment-variables-reference) を参照してください。

```{important}
バックアップサービスを再設定するたびに、バックアップサービスが再起動し、数分間リクエストを受け取らなくなったり、設定によっては挙動が異なる場合があります。
```

<a name="configuring-the-backup-service-via-the-dxp-cloud-console" />

### DXP Cloudコンソールによるバックアップサービスの設定

以下の手順で、DXP Cloud Consoleを使ってバックアップサービスを設定します。

1. バックアップサービスが導入されている環境に移動します。

1. 環境メニューの **サービス** をクリックします。

1. ［**Backup**］ サービスをクリックします。

1. ［**環境変数**］ タブをクリックします。

   ![ご使用の環境で、バックアップサービスの変数タブに移動します。](./backup-service-overview/images/05.png)

   また、環境 ［**概要**］ ページの ［**バックアップ**］ をクリックすると、バックアップサービスのページにアクセスできます。

1. [Environment Variables Reference](#environment-variables-reference) のリストから変数を追加して、バックアップサービスを設定します。

1. ［**変更を保存**］ をクリックします。

通常の環境変数とは別に、DXP Cloudのコンソールから **シークレット** 変数を設定することができます。 詳しくは、 [シークレットで安全な環境変数を管理](../../infrastructure-and-operations/security/managing-secure-environment-variables-with-secrets.md) をご覧ください。

<a name="configuring-the-backup-service-via-the-backup-lcpjson-file" />

### バックアップによるバックアップサービスの設定 `LCP.json` ファイル

以下の手順で、バックアップサービスの `LCP.json` ファイルを介してバックアップサービスを設定します。

1. テキストエディターを使って、以下のパスにあるバックアップの `LCP.json` ファイルを開きます。 `/{your_project_name}/backup/LCP.json`

   ```{note}
   バージョン3.x.xのサービスを利用している場合、バックアップ用の［LCP.json］ファイルは次のパスにあります：［/{your **project** name}/lcp/backup/LCP.json］。  
   ```

1. 環境の項目までスクロールしてください。

   ```
    "env": {
      "LCP **BACKUP** FOLDER": "/opt/liferay/data",
      "LCP **DATABASE** SERVICE": "mydatabase",
      "LCP **MASTER** USER_PASSWORD": "mypassword"
    },
   ```

1. [Environment Variables Reference](#environment-variables-reference) のリストから変数を追加して、バックアップサービスを設定します。

1. ファイルを保存してプロジェクトにデプロイすると、設定が反映されます。

環境サービスの`LCP.json`ファイルによる設定の詳細については、[LCP.jsonによる設定](../../reference/configuration-via-lcp-json.md)を参照してください。

<a name="scheduling-automated-backups-and-cleanups" />

### 自動バックアップとクリーンアップのスケジューリング

バックアップの作成と削除の頻度を決定することは、データの保護とストレージの最適化に役立ちます。 **本番環境のみスケジュールされたバックアップを行うことができます。**

```{warning}
Liferayインスタンス上でデータが能動的に変更されている間に作成されたバックアップは、一貫性のないデータを作成する危険性があります。 データの不整合のリスクを軽減するために、アクティビティが少ない時間帯にバックアップを作成するように、バックアップスケジュールを設定します。 完全に一貫したバックアップを行うためには、データベース管理者と調整して、［手動バックアップ］を行っている間は更新をフリーズするようにしてください。(./backup-service-overview.md#creating-a-manual-backup).
```

バックアップの作成と削除のタイミングをカスタマイズするには、環境ごとに以下の変数を使用します：

***自動バックアップ** : 自動バックアップの頻度を設定するために、 `LCP_BACKUP_CREATE_SCHEDULE` 変数に [cron スケジューリング](https://crontab.guru/) の値を追加します。
***自動化されたクリーンアップ** : 自動化されたバックアップ・クリーンアップの頻度を設定するために、 `LCP_BACKUP_CLEANUP_SCHEDULE` 変数に [cron スケジューリング](https://crontab.guru/) の値を追加します。
***Retention Period** : `LCP_BACKUP_RETENTION_PERIOD` 変数に数値（1～30の間）を追加して、自動クリーンアップで削除される前にバックアップを保持する日数を設定します。

```{note}
標準および非標準の[cronスケジューリング構文]（https://crontab.guru/）は、UTC±00タイムゾーンに基づいています。 非標準のcron構文を使用する場合、自動バックアップとクリーンアップは指定された値の先頭で実行されます。 例えば、@daily は毎日 UTC の 00:00 にバックアップを実行します。
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

<a name="environment-variables-reference" />

## 環境変数リファレンス

| 名前                            | デフォルト値                     | 説明                                                                                                                                                                                          |
| ----------------------------- | -------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `LCP_BACKUP_CLEANUP_SCHEDULE` | 0 1 **** | この変数は、 [Cronスケジューリングシンタックス](https://crontab.guru/) を使って、自動クリーニングをスケジュールします。 クリーンアップでは、バックアップ保持期間を超えたバックアップをすべて削除します。                                                                         |
| `LCP_BACKUP_CREATE_SCHEDULE`  | `[5-55][0-1] * * *`        | この変数は、 [Cronスケジューリングシンタックス](https://crontab.guru/) を使用して、自動バックアップをスケジュールします。 バックアップサービスのバージョン `3.2.1` 以降では、値が指定されていない場合、ランダムなデフォルトが作成されます。                                                   |
| `LCP_BACKUP_FOLDER`           | `/opt/liferay/data`        | バックアップするLiferayフォルダ。                                                                                                                                                                        |
| `LCP_BACKUP_RESTORE_SCHEDULE` | 該当なし                       | この変数は、 [Cronスケジューリングシンタックス](https://crontab.guru/) を使用して、自動復元をスケジュールします。 [Disaster Recovery environments](../../troubleshooting/configuring-cross-region-disaster-recovery.md)での使用を目的としています。 |
| `LCP_BACKUP_RETENTION_PERIOD` | `30`                       | この変数は、スケジュールされたクリーンアップでどのバックアップを削除するかを決定します。 クリーンアップによって削除される前にバックアップを保持する日数を選択します。 最大保存期間は30日です。                                                                                           |
| `LCP_DATABASE_SERVICE`        | `database`                 | データベースサービスのID。                                                                                                                                                                              |
| `LCP_DBNAME`                  | `lportal`                  | データベース名。                                                                                                                                                                                    |
| `LCP_DEBUG_LOG`               | `false`                    | Backupサービスのデバッグログを有効にします。 `true` または `false`に設定します。                                                                                                                                         |
| `LCP_MASTER_USER_NAME`        | `dxpcloud`                 | マスターユーザー名。                                                                                                                                                                                  |
| `LCP_MASTER_USER_PASSWORD`    | `LCP_PROJECT_MASTER_TOKEN` | マスターパスワード。                                                                                                                                                                                  |

<a name="additional-information" />

## 追加情報

* [バックアップのダウンロードとアップロード](./downloading-and-uploading-backups.md)
* [バックアップからのデータの復元](./restoring-data-from-a-backup.md)
* [LCP.jsonを介した設定](../../reference/configuration-via-lcp-json.md)
* [データベースサービス(MySQL)](../database-service/database-service.md)
