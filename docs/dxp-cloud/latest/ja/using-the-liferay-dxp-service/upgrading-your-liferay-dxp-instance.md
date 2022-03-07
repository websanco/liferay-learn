# Liferay DXPインスタンスのアップグレード

Liferayは定期的にLiferay DXPの新しいマイナーバージョンとメジャーバージョンをリリースします。 このバージョンには、セキュリティとバグ修正、および拡張機能が含まれています。 Liferay DXPの新しいメジャーバージョンの増分にアップグレードするには、DXPデータベースをアップグレードする必要があります。

```{note}
   本番環境で大規模なデータセットを使用する場合、スムーズなアップグレードを行うためには、いくつかの考慮すべき点があります。 コアアップグレードの包括的な概要については、 [the guide to upgrading Liferay DXP](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.html) を参照してください。
```

```{note}
   新しいマイナーバージョンやサービスパックにアップデートするには、[Updating to a New Version of Liferay DXP](./updating-your-dxp-instance-to-a-new-minor-version.md)を参照してください。
```

```{important}
   DXP Cloudの環境をアップグレードするには、アップグレードしたデータベースを復元する必要があるため、Liferayサービスの再起動にダウンタイムが発生します。 Plan ahead for this downtime for production environments.
```

次の手順を確認して、データベースのアップグレードを実行します。

1. [前提条件をインストールする](#install-prerequisites)
1. [バックアップをダウンロード](#download-a-backup)
1. [データを抽出してインポートする](#extract-and-import-the-data)
1. [データのアップグレードを実行する](#perform-the-data-upgrade)
1. [データベースとドキュメントライブラリを圧縮する](#compress-the-database-and-document-library)
1. [Upload APIを呼び出す](#call-the-upload-api)
1. [バックアップを復元する](#restore-the-backup)

## 前提条件をインストールする

アップグレード手順を開始する前に、次の前提条件を満たしていることを確認してください：

* [ローカルで利用可能なMySQLインストール](https://dev.mysql.com/doc/mysql-installation-excerpt/5.7/en/) 。
* アップグレードするバージョンのDXPに対応する、 [ダウンロードしたLiferay DXPバンドル](https://customer.liferay.com/en_US/downloads) 。 このバンドルを選択した場所に抽出します。

```{important}
   古いものを再利用するのではなく、アップグレード用の新しいバンドルをダウンロードしてください。 そうしないと、以前にバンドルを使用していたときのデータが、データのアップグレードに支障をきたす可能性があります。
```

## バックアップをダウンロード

次の手順を実行して、現在 `prd` 環境で実行されているDXPインスタンスのバックアップ（データベースとデータボリュームの両方で設定される）をダウンロードします。

1. [DXP Cloudコンソール](https://console.liferay.cloud/login) にログインします。

1. 本番環境に移動し、メニューから［**バックアップ**］を選択します。

    ![運用環境の［バックアップ］ページに移動します。](./upgrading-your-liferay-dxp-instance/images/01.png)

1. リストされているバックアップの1つを選択し、［アクション］メニューから［**ダウンロード**］を選択します。 データボリュームとデータベースのzipファイルをダウンロードします。

    ![各オプションをクリックして、データボリュームとデータベースアーカイブの両方をダウンロードします。](./upgrading-your-liferay-dxp-instance/images/02.png)

## データの抽出とインポート

次のステップは、ダウンロードしたアーカイブからデータを抽出し、アップグレードに必要な場所にデータを移動することです。

### データ量を抽出する

バックアップからデータボリュームを抽出するには、次の手順を実行します：

1. ダウンロードしたデータボリュームの `.tgz` アーカイブ（ `backup-lfr-<PROJECT_NAME>-prd-<BACKUP_ID>.tgz`という名前）を、先に抽出したLiferayバンドルの `LIFERAY_HOME/data` フォルダーに移動します。

1. 以下のコマンドを実行して、アーカイブを抽出します：

    ```bash
    tar -xvzf ARCHIVE_NAME.tgz
    ```

### データベースの抽出とインポート

ダウンロードしたデータベースアーカイブ（名前は `backup-db-<PROJECT_NAME>-prd-<BACKUP_ID>.tgz`）の場所でコマンドプロンプトを開き、以下の手順を実行してMySQLにインポートします：

1. データベースアーカイブを抽出します：

    ```bash
    tar -xvzf ARCHIVE_NAME.tgz
    ```

1. ローカルシステムのMySQLクライアントにログインします：

    ```bash
    mysql -u root -ppassword
    ```

1. ファイル名（拡張子を除く）をデータベース名として使用して、データをインポートするデータベースを作成します：

    ```
    create database DATABASE_NAME;
    ```

1. 抽出された `.sql` ダンプからデータベースをインポートします：

    ```
    use DATABASE_NAME;
    ```

    ```
    source DATABASE_NAME.sql;
    ```

1. 最後に、MySQLクライアントから切断します：

    ```
    exit
    ```

データベースとドキュメントライブラリが配置され、データのアップグレードを実行する準備が整いました。

## データアップグレードの実行

DXPバンドルは、データのアップグレードに使用されるアップグレードツールを提供します。 このツールは、バンドルに含まれるスクリプト `db_upgrade.sh`を介して呼び出されます。

```{note}
   データベースのアップグレードツールを事前に設定しておくことで、実行時の柔軟性を高めることができます。 高度な使い方については、 [データベースアップグレードツールの使用](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/upgrading-liferay/upgrade-basics/using-the-database-upgrade-tool.html) を参照してください。
```

`LIFERAY_HOME/tools/portal-tools-db-upgrade-client` フォルダー内のコマンドプロンプトを開きます。 次に、以下のコマンドを実行します：

```bash
db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.timezone=GMT -Xmx2048m" -l "output.log"
```

アップグレードツールは、データのアップグレードを開始する前に、インストールに関する情報を要求します。 Tomcatを含むLiferayバンドルをダウンロードした場合は、一部のディレクトリがデフォルト値として自動的に検出されます。

以下は、この情報を入力するアップグレードツールとの相互作用の例です：

```
Please enter your application server (tomcat):
tomcat

Please enter your application server directory (../../tomcat-9.0.17):

Please enter your extra library directories (../../tomcat-9.0.17/bin):

Please enter your global library directory (../../tomcat-9.0.17/lib):

Please enter your portal directory (../../tomcat-9.0.17/webapps/ROOT):

[ db2 mariadb mysql oracle postgresql sqlserver sybase ]
Please enter your database (mysql):
mysql

Please enter your database host (localhost):

(etc.)
```

必要な情報を入力すると、アップグレードツールによってデータのアップグレードが実行されます。 次のメッセージがコンソールに表示されたら、アップグレードは完了です：

```
Completed Liferay core upgrade and verify processes in 64 seconds
Checking to see if all upgrades have completed... done.
```

バンドルをローカルでテストして、アップグレードがスムーズに完了したことを確認します。 `LIFERAY_HOME/tomcat-9.xx/bin /`から次のコマンドを実行して、インスタンスをローカルでテストできます。

```bash
./catalina.sh run
```

アップグレードが完了して検証されると、データベースとデータボリュームをDXP Cloudにアップロードしてアップロードできるようになります。

## ドキュメントライブラリを圧縮する

これで、データベースとLiferayデータボリュームは、 `バックアップ` サービスのアップロードAPIを使用してアップロードする準備ができました。

### ドキュメントライブラリの圧縮

1. `LIFERAY_HOME/data` フォルダー内のコマンドプロンプトを開きます。

1. 次のコマンドを使用して、このファイルを `.tgz` アーカイブに圧縮します：

    ```bash
    tar -czvf volume.tgz document_library
    ```

    ```{important}
       もしダウンロードしたデータボリュームにさらに多くのフォルダ（例えば icense/` フォルダ）が含まれていた場合は、`document_library` の後に追加の引数として追加してください。
    ```

### データベースアップグレードツールの使用

1. 次のコマンドを実行して、データベースダンプを実行します：

    ```
    mysqldump -uroot -ppassword --databases --add-drop-database lportal | gzip -c | cat > database.gz
    ```

1. このファイルを以下のコマンドで `.tgz` のアーカイブに圧縮します：

    ```bash
    tar zcvf database.tgz database.gz
    ```

アップロードAPIを呼び出して、データベースとドキュメントライブラリのアーカイブを `バックアップ` サービスにアップロードします。

## Upload APIを呼び出す

アップロードAPIを呼び出して、データベースとドキュメントライブラリのアーカイブを `バックアップ` サービスにアップロードします。

1. まだログインしていない場合は、 [DXP Cloudコンソール](https://console.liferay.cloud/login) にログインします。

1. ブラウザで `https://api.liferay.cloud/user` を開きます。

1. このURLに表示されているJSON文字列からユーザーセッショントークンをコピーします。 `トークン` プロパティの値のみをコピーします（引用符を削除します）。

1. プロジェクト用に変更した後、次のコマンドを実行してアップロードAPIを呼び出します：

    ```bash
    curl -X POST https://backup-<PROJECT-NAME>-<ENV>.lfr.cloud/backup/upload -H 'Content-Type: multipart/form-data' -H 'Authorization: Bearer <USER-TOKEN>' -F 'database=@/path/to/folder/database.tgz' -F 'volume=@/path/to/folder/volume.tgz'
    ```

    `<PROJECT-NAME>`とプロジェクトの名前を、`/path/to/folder/`と`.tgz`アーカイブが配置されているパスを置換します。 `<ENV>` を、アップグレードしたバックアップをアップロードする環境の略語に置き換えてください（例： `dev`）。

通話が完了すると、アップロードからの新しいバックアップが、DXP Cloudコンソールの **Backups** ページに表示されます。

## バックアップを復元する

次の手順に従って、選択した環境にバックアップを復元します：

1. まだログインしていない場合は、DXP Cloudコンソールにログインします。

1. [バックアップをアップロードした](#call-the-upload-api) 環境に移動し、サイドメニューから ［**バックアップ**］ をクリックします。

1. リストからバックアップを選択し、そのバックアップのアクションメニューから ［**復元**］ をクリックします。

    ![アップロードしたバックアップの［アクション］メニューから［復元先...］を選択します。](./upgrading-your-liferay-dxp-instance/images/03.png)

1. ドロップダウンリストから復元先の環境の1つを選択します（たとえば、 `dev` 環境）。

    ![バックアップを展開する環境を選択します。](./upgrading-your-liferay-dxp-instance/images/04.png)

1. ［**環境への復元**］ をクリックします。

    ```{note}
       選択した環境は、バックアップがデプロイされている間は利用できなくなります。
    ```


<!-- I'd also want to know if there is a zero downtime way to do an upgrade - because that's one of the next questions I would ask if I put myself in the shoes of someone trying to run a prod and business critical env. We may not be ready to say anything about that - but just a thought to put in your mind as potentially a future iteration of this - or let's say if we find out that you CAN do a zero downtime upgrade using a DR environment, then we should update this article to say so. An example:

Upgrading the liferay service requires a database upgrade and restoring the liferay service using the upgraded database. The process of restoring the upgraded database from backup requires some downtime and we recommend testing your upgrade on the DEV or UAT environments first. Zero downtime upgrades are possible using a DR environment. 

-->

　 これでDXPデータベースを新しいバージョンにアップグレードし、選択した環境にデプロイできました。 また、必要に応じて、 [同じバックアップ](#restore-the-backup) を他の環境に再度リストアすることもできます。

## 追加情報

DXPアップグレードの詳細：

* [Liferay DXPアップグレードの概要](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/upgrading-liferay/upgrade-basics/upgrade-overview.html)
* \[DXPインスタンスを新しいマイナーバージョンに更新する\](./updating-your-dxp-instance-toa-new-minor-version.md)
* [データベースアップグレードツールの使用](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/upgrading-liferay/upgrade-basics/using-the-database-upgrade-tool.html)
