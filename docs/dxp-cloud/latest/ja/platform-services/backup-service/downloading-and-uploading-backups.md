# バックアップのダウンロードとアップロード

DXP Cloudのバックアップサービスでは、環境のデータベースと `LIFERAY_HOME/data` フォルダの全コンテンツのバックアップを作成します。 このコンテンツはアーカイブファイル（`.gz` 、 `.tgz` ）として保存されており、DXP Cloudのコンソールからダウンロードすることができます。

また、ユーザーはDXP Cloudコンソール</a>、または [バックアップAPI](#backup-service-apis)を使用して、環境のバックアップをダウンロードまたはアップロードすることができます
。</p> 



## コンソールによるバックアップのダウンロード

以下の手順で、 `prd` 環境の *Backups* ページからバックアップをダウンロードします。

1.  ダウンロードしたいバックアップの *Actions* ボタン( ⋮ )をクリックします。

2.  *ダウンロード*をクリックします。
   
   ![ [Actions] ボタンをクリックして、[Download] をクリックします。](./downloading-and-uploading-backups/images/01.png)

3.  *Database* (`.gz`) または *Liferay* (`.tgz`) のファイルをクリックすると、ダウンロードが始まります。 これらのZIPアーカイブを合わせて、環境のバックアップとします。 
   
   

    ``` note::
       バックアップサービスがまだバージョン4.2以上にアップデートされていない場合、データベースボリュームは ``.gz`` ではなく ``.tgz`` アーカイブとしてダウンロードされます。
    ```


![クリックすると、データベースとLiferayのデータボリュームファイルをダウンロードできます。](./downloading-and-uploading-backups/images/02.png)

<!-- end list -->

``` note::
   本番環境の管理者のみが、「バックアップ」ページからバックアップをダウンロードできます。
```




## コンソールによるバックアップのアップロード

また、 `prd` 環境の [*Backups*] ページから、プロジェクトにバックアップをアップロードすることもできます。

DXP Cloudにバックアップをアップロードする前に、データベースダンプとドキュメントライブラリを別々のアーカイブに圧縮する必要があります。 オンプレミス環境でのアップロードの準備については、 [データベースとドキュメント・ライブラリーのアップロードの準備](#preparing-the-database-and-document-library-for-upload) を参照してください。



``` warning::
   アップロードが開始されると、アップロードが完了するまで、バックアップサービスは他のバックアップの生成やリストアに利用できません。
```


*バックアップ* のページから以下の手順を行います。

1.  画面上部の *「バックアップをアップロード...」をクリックします。画面上部の* をクリックします。

2.  バックアップのアップロード」ページで、該当する本番環境を展開し、データベースとドキュメント・ライブラリの両方の `` アイコンをクリックしてアップロードします。
   
   ![アイコンをクリックすると、データベースとドキュメントライブラリの両方が.gz形式のアーカイブとしてアップロードされます。](./downloading-and-uploading-backups/images/03.png)

3.  データベース・ダンプとドキュメント・ライブラリの両方がアップロードされたら、 *[アップロードの開始]* をクリックします。

DXP Cloudはアップロードしたファイルを使用してバックアップの生成を開始し、環境にリストアできるリストに追加します。 バックアップが生成されている間は、他のバックアップの生成や復元はできません。

バックアップが生成されると、ページに成功のメッセージが表示され、サービスが正常に動作するようになります。

![お使いの環境でバックアップのリストへの追加が完了すると、成功メッセージが表示されます。](./downloading-and-uploading-backups/images/04.png)



## バックアップサービスのAPI

バックアップサービスにはAPIが用意されており、バックアップのダウンロードやアップロードにも利用できます。 これらのAPIは、 `curl`のようなコマンドラインツールを使って呼び出すことができます。



### ホスト名の取得

バックアップAPIを呼び出すには、バックアップサービスのホスト名が必要です。 これは、 *サービス* のページにあります。

![サービス」ページから、バックアップサービスのホスト名を表示します。](./downloading-and-uploading-backups/images/05.png)

バックアップサービスのホスト名は、サービス名、プロジェクト名、環境名を組み合わせたものです。

次の例を考えてみましょう：

  - サービス名： `backup`
  - プロジェクト名： `lfrjoebloggs`
  - 環境名： `prd`
  - ホスト名： `backup-lfrjoebloggs-prd.lfr.cloud`



### 認証

ベーシック認証やユーザーアクセストークンを使って、リクエストを認証することができます。

SSOが有効な場合、トークン認証が必要であることに注意してください。 このトークンをcookie `access_token` から取得し、 `dxpcloud-authorization` ヘッダーで使用できます。

アップロードAPIでトークン認証を使用する例を次に示します。



``` bash
curl -X POST \
  https://backup-<PROJECT-NAME>-prd.lfr.cloud/backup/upload \
  -H 'Content-Type: multipart/form-data' \
  -H 'dxpcloud-authorization: Bearer <USER_TOKEN>' \
  -F 'database=@/my-folder/database.gz' \
  -F 'volume=@/my-folder/volume.tgz'
```




``` note::
   注：ヘッダー dxpcloud-authorization にユーザートークンを渡すことは、バックアップサービスのバージョン 3.2.0 以降でのみ機能します。 Previous versions should be upgraded to at least ``3.2.0``. Requests to earlier versions must use the header ``Authorization: Bearer <PROJECT_MASTER_TOKEN>``. You can find the project master token by running the command ``env | grep LCP_PROJECT_MASTER_TOKEN`` in any shell in the Liferay DXP Cloud console.
```




### データベースAPIのダウンロード

データベースをダウンロードするAPIには、 `.gz` ファイルを返すエンドポイントがあります。 `id` パラメータはバックアップIDを表しており、これは「バックアップ」ページで確認できます。 このIDは、2つのダッシュで区切られた3つの文字列で設定　されています（例： `dxpcloud-lqgqnewltbexuewymq-201910031723`）。



#### パラメーター

| 名前   | タイプ      | 必須 |
| ---- | -------- | -- |
| `id` | `String` | はい |




#### curlの例



``` bash
curl -X GET \
  https://backup-<PROJECT-NAME>-prd.lfr.cloud/backup/download/database/id \
  -u user@domain.com:password \
  --output database.gz
```




``` note::
   バックアップサービスがまだバージョン4.2以上にアップデートされていない場合、データベースボリュームは ``.gz`` ではなく ``.tgz`` アーカイブとしてダウンロードされます。
```




### Data Volume APIのダウンロード

データボリュームをダウンロードするAPIには、 `.tgz` ファイルを返すエンドポイントがあります。 `id` パラメータはバックアップIDを表しており、これは「バックアップ」ページで確認できます。 このIDは、2つのダッシュで区切られた3つの文字列で設定　されています（例： `dxpcloud-lqgqnewltbexuewymq-201910031723`）。



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

アップロードバックアップAPIを使ってDXP Cloudにバックアップをアップロードするには、以下の手順に従ってください。

1.  [データベースファイル作成します](#creating-the-database-file)。

2.  [ボリュームファイル作成します](#creating-the-volume-file)。

3.  [データベースとボリュームのファイルを使って、](#invoking-the-backup-api) バックアップAPIを起動します。

アップロードAPIを使用する前に、データベースダンプとドキュメントライブラリを別々のアーカイブに圧縮する必要があります。 オンプレミス環境でのアップロードの準備については、 [データベースとドキュメント・ライブラリーのアップロードの準備](#preparing-the-database-and-document-library-for-upload) を参照してください。



#### バックアップAPIの呼び出し

**パラメーター**

| 名前         | タイプ    | 必須 |
| ---------- | ------ | -- |
| `database` | `File` | はい |
| `volume`   | `File` | はい |


**curlの例**



``` bash
curl -X POST \
  https://backup-<PROJECT-NAME>-prd.lfr.cloud/backup/upload \
  -H 'Content-Type: multipart/form-data' \
  -F 'database=@/my-folder/database.gz' \
  -F 'volume=@/my-folder/volume.tgz' \
  -u user@domain.com:password
```




## データベースとドキュメントライブラリのアップロード準備

お客様の環境のバックアップをDXP Cloudにアップロードするためには、その環境のデータベースとドキュメントライブラリが別々のアーカイブファイルとして準備されている必要があります。



### データベースファイルの作成

MySQLのダンプを（ `.sql` スクリプトとして）作成し、それを `.gz` アーカイブに圧縮するには、以下のコマンドを実行します。



``` bash
mysqldump -uroot -ppassword --add-drop-database --databases lportal | gzip -c | cat > database.gz
```




``` note::
   お使いのBackupサービスが少なくともバージョン ``4.2`` にアップデートされていない場合には、以下のコマンドを実行して、アーカイブを ``.tgz`` ファイルに変換する必要があります。tar zcvf database.tgz database.gz``. Then use the resulting ``.tgz`` archive to upload.
```


`データベース` および `add-drop-database` オプションは、バックアップの復元が正しく機能するために必要です。 また、 `/backup/download` APIを使用して、バックアップサービスがどのようにMySQLのダンプファイルを作成するかを確認することができます。

これらのオプションを指定した場合、作成されるダンプファイルには、create tableステートメントの直前に以下のコードが含まれます。



``` sql
--
-- Current Database: `lportal`
--

/*!40000 DROP DATABASE IF EXISTS `lportal`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lportal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lportal`;
```




### ボリュームファイルの作成

このコマンドを実行すると、データボリュームが圧縮されます。



``` bash
cd $LIFERAY_HOME/data && tar -czvf volume.tgz document_library
```




## 追加情報

  - [バックアップサービスの概要](./backup-service-overview.md)
  - [バックアップからのデータの復元](./restoring-data-from-a-backup.md)
  - [データベースサービス(MySQL)](../database-service/database-service.md)
