# バックアップからのデータの復元

プロジェクトの開発中には、データを復元したり、プロジェクトを以前の状態にロールバックする必要がある場合があります。

また、カスタム SQL スクリプトを使用して、データ復元の一環としてデータベースの追加更新を行うこともできます。

バックアップページの詳細は、 [バックアップサービス](./backup-service-overview.md) および [バックアップのダウンロードとアップロード](./downloading-and-uploading-backups.md) を参照してください。

``` important::
   バックアップ」のページは、プロダクション（``prd``）環境でのみ利用可能です。 prod環境のAdminロールを持つユーザーのみが、DXP Cloudコンソールを介して環境を手動でリストアできます。
```

## バックアップから環境を復元するページ

以下の手順で、バックアップから環境を復元します。

1.  あなたのプロジェクトの `prd` 環境に移動します。

2.  環境メニューの *バックアップ* をクリックします。

3.  プロジェクト環境の復元に使用したいバックアップの *アクション* ボタン（⋮）をクリックします。

4.  *Restore to...*をクリックします。

    ![図1：「Actions」ボタンをクリックして、「Restore To...」をクリックします。](./restoring-data-from-a-backup/images/01.png)

5.  ドロップダウンの *環境* メニューをクリックして、復元したい環境を選択します。

    ![図2：復元したい環境を選択する。](./restoring-data-from-a-backup/images/02.png)

    ``` note::
       本番環境の管理者は、自分がアクセスできる環境のみを復元できます。
    ```

6.  下に表示される *のチェックボックス* をすべてクリックします。 復元を開始するボタンを有効にするには、これらのボックスをチェックする必要があります。

7.  * [Restore to Environment ] * をクリックして、復元処理を開始します。

    ![図3：すべてのチェックボックスをクリックして、復元を確認する。](./restoring-data-from-a-backup/images/03.png)

復元プロセス中に、ターゲット環境のサービスが再起動します。

バックアップ サービスの *ログ* や、アクティビティ ページの *一般* セクションで、リストアのステータスを追跡することができます。

## データリストアでのカスタムSQLスクリプトの適用

また、カスタム SQL スクリプトを使用して、通常のデータ復元でデータベースの追加更新を行うこともできます。 この方法では、別々に管理しているデータベースのバックアップにスクリプトを適用できるため、機密データのサニタイズに最適です。

``` note::
   この機能を使用するには、バックアップサービスのバージョン3.0.7以降が必要です。
```

### SQLスクリプトの準備

SQLスクリプトでサポートされているフォーマットは以下の通りです。

  - `.sql` は個々のスクリプトに使われます。
  - `.zip`、`.tgz`、`.gz` は、圧縮ファイル内の複数のスクリプトに使用されます。

なお、スクリプトは実行時に英数字の順に実行されます。 SQLスクリプトは、実行するデータベースを正確に参照する必要があります（例えば、 `USE lportal;` または `lportal.User_`など）。

SQLスクリプトを適切な、環境固有の `backup/configs/{ENV}/scripts/` フォルダに入れます。

``` note::
   バージョン3.x.xのサービスを使用している場合、SQLスクリプトは適切な ``lcp/backup/script/{ENV}/`` フォルダに置かれます。 See `Understanding Service Stack Versions <../../reference/understanding-service-stack-versions.md>`__ for more information on checking the version.
```

### データリストアの実行

SQLスクリプトの準備ができたら、以下の手順でカスタムSQLスクリプトをデータリストアに適用します。

1.  [バックアップサービスをデプロイして](../../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md) カスタムSQLスクリプトをオンラインに含める。

2.  上記の [バックアップからの環境の復元ページ](#restoring-an-environment-from-the-backups-page)の手順に従ってください。

データベースがリストアされると、バックアップ サービスの `scripts` フォルダにある SQL スクリプトが実行されます。

``` bash
Jun 20 14:46:41.795 build-39 [backup-57488f8b8-rjq4f] Running Script: SanitizeOrg.sql
Jun 20 14:46:41.970 build-39 [backup-57488f8b8-rjq4f] Running Script: SanitizeUsers.sql
```

## 追加情報

  - [バックアップサービス](./backup-service-overview.md)
  - [バックアップのダウンロードとアップロード](./downloading-and-uploading-backups.md)
  - [データベースサービス(MySQL)](../database-service/database-service.md)
