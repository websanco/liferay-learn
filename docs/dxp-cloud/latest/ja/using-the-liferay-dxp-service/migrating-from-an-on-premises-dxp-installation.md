# オンプレミスDXPインストールからの移行

この記事では、既存のLiferay DXPインスタンスをDXP Cloudに移行するための基本的な手順について説明します。

1.  [データベースとドキュメントライブラリを移行する](#migrate-the-database-and-document-library)
2.  [Liferay DXP設定のコピー](#copy-liferay-dxp-configurations)
3.  [サービス設定を追加](#add-service-configurations)
4.  [VPNを使用して外部サービスを接続する](#use-a-vpn-to-connect-external-services)

## データベースとドキュメントライブラリを移行する

DXP Cloudに移行するプロセスの最も重要な部分は、データベースとドキュメントライブラリのインポートです。 データベースとドキュメントライブラリの両方を同時にインポートして、データベース内のデータとレコードの同期を維持する必要があります。

この手順は、データの損失を防ぐために、データベースが更新されていないときに実行する必要があります。 これには通常、移行のためにデータを凍結するためのウィンドウを予約する必要があります。

### データベースダンプのエクスポート

まず、データをデータベースダンプにエクスポートします。 MySQLからのエクスポートは、次のコマンドを使用して実行できます。

``` bash
mysqldump -uroot -ppassword --databases --add-drop-database lportal | gzip -c | cat > database.gz
```

``` important::
   バックアップの復元が正しく機能するためには ``databases`` と ``add-drop-database`` オプションが必要です。
```

DXP Cloudにインポートされたデータベースダンプは、データベースサービスが使用するためにMySQL形式である必要があります。 必要に応じて、 [DBeaver](http://dbeaver.io) などのツールを使用して、インポートのために他のタイプのデータベースをMySQLに変換できます。

### ドキュメントライブラリを圧縮する

ドキュメントライブラリは、バックアップサービスにアップロードするためにファイルに圧縮する必要もあります。

次のコマンドを使用して、ドキュメントライブラリをzipファイルに追加します。

``` bash
cd $LIFERAY_HOME/data && tar -czvf volume.tgz document_library
```

``` important::
   現在 `Amazon S3 <https://help.liferay.com/hc/en-us/articles/360028810172-Using-Amazon-Simple-Storage-Service>`_, `CMIS <https://help.liferay.com/hc/en-us/articles/360018176171-Using-the-CMIS-Store>`_, `DBStore <https://help.liferay.com/hc/en-us/articles/360028810192-Using-the-DBStore>`_ のストレージメソッドを使用している場合は、まず `File System Store <https://help.liferay.com/hc/en-us/articles/360028810132-Using-the-Simple-File-System-Store>`_ に移行する必要があります。
```

### バックアップサービスAPIを呼び出す

データベースダンプとドキュメントライブラリを圧縮して準備ができたら、バックアップサービスAPIを呼び出して、両方を同時にバックアップサービスにアップロードします。

次のコマンドを実行してAPIを呼び出し、zipファイルをアップロードします。

``` bash
curl -X POST \
  https://backup-<PROJECT-NAME>-prd.lfr.cloud/backup/upload \
  -H 'Content-Type: multipart/form-data' \
  -F 'database=@/my-folder/database.gz' \
  -F 'volume=@/my-folder/volume.tgz' \
  -u user@domain.com:password
```

`<PROJECT-NAME>` を、DXP Cloudプロジェクトの適切な名前に置き換えます。 `/my-folder` を、zipファイルへの正しいパスに置き換えます。

これらがアップロードされると、バックアップサービスはDXP Cloudバックアップを初期化します。

``` note::
   バックアップは `prd`` 環境の `Backups` ページに表示されますが、復元するまではどの環境にも適用されません。
```

## Liferay DXP設定のコピー

ポータルプロパティとOSGi設定　は、`lcp/liferay/config /`内の環境ごとに適切なフォルダにそれらを置くことによってDXP Cloudにコピーすることができます（例えば、 `DEV`、 `UAT`、または `PRD`、又は `の共通` 全てに適用する）。

``` 
    |-- lcp
        |-- liferay
            |-- LCP.json
            |-- config
                |-- common
                |-- dev
                |-- local
                |-- prd
                |-- uat
```

適切なフォルダに配置された `portal-*.properties` という形式のポータルプロパティは、該当する環境のLiferay DXPサービス内の `$LIFERAY_HOME` に自動的にコピーされます。 OSGiプロパティ（.cfgまたは.configファイル）は、該当する環境のLiferay DXPサービス内の `osgi/configs` フォルダーにコピーされます。

## サービス設定を追加

残りの設定は、主にDXP Cloudで提供されるサービスを通じて処理されます。 ウェブサーバーと検索設定を翻訳するには、DXP Cloudのサービスを利用しなければならないので、その方法を決定するには計画が必要かもしれません。

Webサーバーの設定は、Nginxを使用して、webserverサービスを通じて行う必要があります。 このサービスに設定を追加する方法の詳細は、 [ウェブサーバーサービス](../platform-services/web-server-service.md) を参照してください。 設定自体の詳細は、 [公式Nginxドキュメント](https://docs.nginx.com/) を参照してください。

検索設定は、Elasticsearchを使用して、検索サービスを介して行う必要があります。 このサービスに設定を追加する方法の詳細は、 [検索サービス](../platform-services/search-service.md) を参照してください。 設定自体の詳細は、 [Elasticsearchの公式ドキュメント](https://www.elastic.co/guide/index.html) を参照してください。

## バックアップを復元する

バックアップがアップロードされ、サービス設定が適用されたので、環境の1つにバックアップを復元できます。 準備ができたら、次の手順に従って、選択した環境にバックアップを適用します。

1.  まだログインしていない場合は、DXP Cloudコンソールにログインします。

2.  本番環境に移動し、サイドメニューから[ *Backups* ]をクリックします。

3.  リストで新しくアップロードされたバックアップを選択し、そのバックアップのアクションメニューから *復元* をクリックします。

    ![アップロードしたバックアップの[アクション]メニューから[復元先...]を選択します。](./migrating-from-an-on-premises-dxp-installation/images/01.png)

4.  ドロップダウンリストから復元先の環境の1つを選択します（例： `dev` 環境）：

    ![バックアップを展開する環境を選択します。](./migrating-from-an-on-premises-dxp-installation/images/02.png)

5.  [ *環境に復元*]をクリックします。

<!-- end list -->

``` note::
   選択した環境は、バックアップが展開されている間は利用できなくなります。
```

復元プロセスが完了すると、選択した環境でDXPの移行されたインスタンスが使用可能になります。 この時点で、DXP Cloudへの移行のほとんどは完了しています。

## VPNを使用して外部サービスを接続する

DXP Cloudの既存のサービス（SSOやLDAP統合など）に簡単にマッピングできない外部サービスは、VPNを使用して接続できます。 VPNの設定の詳細は、「 [VPNをDXP Cloud](../infrastructure-and-operations/networking/connecting-a-vpn-to-dxp-cloud.md) 接続する」を参照してください。
