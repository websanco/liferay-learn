# データベースパスワードの変更

[`データベース` サービス](./database-service.md) の MySQL パスワードを変更すると、データベースに接続しなければならない他のサービスにも影響します。

``` warning::
   Updating your database password requires a period of downtime for you to restore a backup to your database service, and then restart all of your services. Plan ahead to make time for this before rotating your database password.
```

## バックアップの作成

本番環境のパスワードを変更するのであれば、アップデート後に復元できるように、最新のバックアップを確保しておく必要があります。

バックアップを作成するには、本番環境（`prd`）に移動し、左側のメニューから「 *バックアップ* 」をクリックします。

![本番環境の「バックアップ」ページに移動します。](./changing-your-database-password/images/01.png)

その後、 *[ 今すぐバックアップ ]* をクリックし、 *[ バックアップ ]* ページに移動します。

![ [ 今すぐバックアップ] をクリックすると、新しいバックアップが作成されます。](./changing-your-database-password/images/02.png)

## データベースのパスワード変更

データベースのパスワードは、選択した環境内の [secrets](../../infrastructure-and-operations/security/managing-secure-environment-variables-with-secrets.md) 機能を使って直接変更されます。 ただし、データベースのパスワードシークレットについては、サービスが新しい値で正しく更新されるように、追加の手順を踏む必要があります。

<!-- Mention required service versions, and link to possible section on the procedure for 3.x services -->

準備ができたら、以下の手順でデータベースのパスワードを変更します。

1.  選択した環境にナビゲートします。

2.  左側のメニューから *設定* をクリックします。

    ![お使いの環境の「設定」ページに移動します。](./changing-your-database-password/images/03.png)

3.  *Secrets* セクションで、 *Edit... をクリックします。 `lcp-secret-database-password` のシークレットの横にある Actions メニューから* をクリックします。

    ![lcp-secret-database-password secretを編集します。](./changing-your-database-password/images/04.png)

4.  *値* セクションで、値の横にある *表示* をクリックすると、シークレットが表示され、編集が可能になります。

    ![シークレットを表示することで、編集が可能になります。](./changing-your-database-password/images/05.png)

5.  シークレットの値を編集します。

6.  スクロールダウンして、シークレットの値を変更することによる影響を認めるすべてのボックスにチェックを入れてください。

    ![[ シークレットの公開 ] ボタンを有効にする場合はチェックを入れます。](./changing-your-database-password/images/06.png)

7.  *シークレットの公開*をクリックします。

    ``` note::
       The services attempt to restart when you click `Publish secret`. However, the database service can only update its password upon image creation, so the service must be deleted and redeployed to update it.
    ```

8.  お使いの環境の *サービス* のページに戻ります。

9.  `データベース` サービスの[アクション]メニューから [*サービスの削除*]をクリックします。

    ![サービスを削除して、再配置し、更新されたパスワードで起動できるようにします。](./changing-your-database-password/images/07.png)

10. リポジトリのローカルクローン内で、コマンドプロンプトを開きます。

11. `データベース` サービスを、 [コマンドライン・インターフェース](../../reference/command-line-tool.md)を使って環境にデプロイします。

    ``` bash
    cd database
    ```

    ``` bash
    lcp deploy
    ```

12. まだログインしていない場合は、プロンプトで `y` を入力し、ブラウザで認証します。

13. プロンプトが表示されたら、あなたの環境に対応する番号を入力してください。

    ``` bash
    #      Project             Status
    1      lfrlearn-infra      Ready
    2      lfrlearn-uat        Ready
    3      lfrlearn-dev        Ready
    4      lfrlearn-prd        Ready

    ? Type a number (#) or project name: 2
    ```

    更新されたパスワードを使用して、データベースサービスが起動します。 `liferay` と `バックアップ` のサービスも、正しいパスワードでデータベースサービスに再接続するために再起動します。

14. バックアップを [用意した場合は](#creating-a-backup)、本番環境の **、** 環境の *バックアップ* ページに移動し、 [バックアップ](../backup-service/restoring-data-from-a-backup.md)に復元します。

あなたの `データベース` サービスが新しいパスワードで更新され、他のサービスが適切に接続するように同期されました。

## 追加情報

  - [データベースサービス](./database-service.md)
  - [データベースのユーザー名の変更](./changing-your-database-username.md)
  - [シークレットで安全な環境変数を管理](../../infrastructure-and-operations/security/managing-secure-environment-variables-with-secrets.md)
