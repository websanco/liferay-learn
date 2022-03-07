# バックアップのダウンロードとアップロード

DXP Cloudのバックアップサービスでは、環境のデータベースと `LIFERAY_HOME/data` フォルダの全コンテンツのバックアップを作成します。 このコンテンツはアーカイブファイル（`.gz` 、 `.tgz` ）として保存されており、DXP Cloudのコンソールからダウンロードすることができます。

また、ユーザーはDXP Cloudコンソール</a>、または [バックアップAPI](#backup-service-apis) を使用して、環境のバックアップをダウンロードまたはアップロードすることができます
。</p> 



```{note}
   バックアップページは、バックアップサービスのバージョンが4.3.5よりも古い場合、実稼働環境でのみ利用できます。
```




<a name="downloading-backups-via-the-console" />

## コンソールによるバックアップのダウンロード

以下の手順で、選択した環境の ［**バックアップ**］ ページからバックアップをダウンロードします：

1. ダウンロードしたいバックアップの **Actions** ボタン( ⋮ )をクリックします。

1. ［**ダウンロード**］ をクリックします。
   
   ![ ［Actions］ ボタンをクリックして、［Download］ をクリックします。](./downloading-and-uploading-backups/images/01.png)

1. **Database**(`.gz`) または **Liferay**(`.tgz`) のファイルをクリックすると、ダウンロードが始まります。 これらのZIPアーカイブを合わせて、環境のバックアップとします。 
   
   

    ```{note}
       バックアップサービスがまだバージョン4.2以上にアップデートされていない場合、データベースボリュームは gz` ではなく `.tgz` アーカイブとしてダウンロードされます。
    ```


![クリックすると、データベースとLiferayのデータボリュームファイルをダウンロードできます。](./downloading-and-uploading-backups/images/02.png)



```{note}
   選択した環境の管理者のみが、バックアップページからバックアップをダウンロードできます。
```




<a name="uploading-backups-via-the-console" />

## コンソールによるバックアップのアップロード

また、 選択した環境の [**バックアップ**] ページから、プロジェクトにバックアップをアップロードすることもできます。

DXP Cloudにバックアップをアップロードする前に、データベースダンプとドキュメントライブラリを別々のアーカイブに圧縮する必要があります。 オンプレミス環境でのアップロードの準備については、 [データベースとドキュメント・ライブラリーのアップロードの準備](#preparing-the-database-and-document-library-for-upload) を参照してください。



```{warning}
   アップロードが開始されると、アップロードが完了するまで、バックアップサービスは他のバックアップの生成やリストアに利用できません。
```


**バックアップ** のページから以下の手順を行います。

1. 画面上部の 「**バックアップをアップロード...」をクリックします。画面上部の** をクリックします。

1. バックアップのアップロードページで、該当する環境を展開し、データベースとドキュメントライブラリの両方の `［+］` アイコンをクリックしてアップロードします。
   
   ![アイコンをクリックすると、データベースとドキュメントライブラリの両方が.gz形式のアーカイブとしてアップロードされます。](./downloading-and-uploading-backups/images/03.png)

1. データベース・ダンプとドキュメント・ライブラリの両方がアップロードされたら、 ［**アップロードの開始**］ をクリックします。

DXP Cloudはアップロードしたファイルを使用してバックアップの生成を開始し、環境にリストアできるリストに追加します。 バックアップが生成されている間は、他のバックアップの生成や復元はできません。

バックアップが生成されると、ページに成功のメッセージが表示され、サービスが正常に動作するようになります。

![お使いの環境でバックアップのリストへの追加が完了すると、成功メッセージが表示されます。](./downloading-and-uploading-backups/images/04.png)



<a name="backup-service-apis" />

## バックアップサービスのAPI

バックアップサービスにはAPIが用意されており、バックアップのダウンロードやアップロードにも利用できます。 これらのAPIは、 `curl`のようなコマンドラインツールを使って呼び出すことができます。



<a name="getting-the-host-name" />

### ホスト名の取得

バックアップAPIを呼び出すには、バックアップサービスのホスト名が必要です。 これは、 **サービス** のページにあります。

![サービス」ページから、バックアップサービスのホスト名を表示します。](./downloading-and-uploading-backups/images/05.png)

バックアップサービスのホスト名は、サービス名、プロジェクト名、環境名を組み合わせたものです。

次の例を考えてみましょう：

* サービス名： `backup`
* プロジェクト名： `lfrjoebloggs`
* 環境名： `prd`
* ホスト名： `backup-lfrjoebloggs-prd.lfr.cloud`



<a name="authentication" />

### 認証

ベーシック認証やユーザーアクセストークンを使って、リクエストを認証することができます。

SSOが有効な場合、トークン認証が必要であることに注意してください。 このトークンをcookie `access_token` から取得し、 `dxpcloud-authorization` ヘッダーで使用できます。

アップロードAPIでトークン認証を使用する例を次に示します。



```bash
curl -X POST \
  https://backup-<PROJECT-NAME>-<ENV>.lfr.cloud/backup/upload \
  -H 'Content-Type: multipart/form-data' \
  -H 'dxpcloud-authorization: Bearer <USER_TOKEN>' \
  -F 'database=@/my-folder/database.gz' \
  -F 'volume=@/my-folder/volume.tgz'
```




```{note}
   注：ヘッダー dxpcloud-authorization にユーザートークンを渡すことは、バックアップサービスのバージョン 3.2.0 以降でのみ機能します。 それ以前のバージョンは、少なくとも .2.0` にアップグレードする必要があります。 以前のバージョンへのリクエストには ` [Authorization.Bearer](PROJECT_MASTER_TOKEN) `を使用します。 プロジェクトマスタートークンは、Liferay DXP Cloudコンソールの任意のシェルで `env | grep LCP_PROJECT_MASTER_TOKEN` コマンドを実行することで見つけることができます。
```




<a name="download-database-api" />

### データベースAPIのダウンロード

データベースをダウンロードするAPIには、 `.gz` ファイルを返すエンドポイントがあります。 `id` パラメータはバックアップIDを表しており、これは「バックアップ」ページで確認できます。 このIDは、2つのダッシュで区切られた3つの文字列で設定　されています（例： `dxpcloud-lqgqnewltbexuewymq-201910031723`）。



#### パラメーター

| 名前   | タイプ      | 必須 |
| ---- | -------- | -- |
| `id` | `String` | はい |




#### curlの例



```bash
curl -X GET \
  https://backup-<PROJECT-NAME>-<ENV>.lfr.cloud/backup/download/database/id \
  -u user@domain.com:password \
  --output database.gz
```




```{note}
   バックアップサービスがまだバージョン4.2以上にアップデートされていない場合、データベースボリュームは gz` ではなく `.tgz` アーカイブとしてダウンロードされます。
```




<a name="download-data-volume-api" />

### Data Volume APIのダウンロード

データボリュームをダウンロードするAPIには、 `.tgz` ファイルを返すエンドポイントがあります。 `id` パラメータはバックアップIDを表しており、これは「バックアップ」ページで確認できます。 このIDは、2つのダッシュで区切られた3つの文字列で設定　されています（例： `dxpcloud-lqgqnewltbexuewymq-201910031723`）。



#### パラメーター

| 名前   | タイプ      | 必須 |
| ---- | -------- | -- |
| `id` | `String` | はい |




#### カールの例



```bash
curl -X GET \
  https://backup-<PROJECT-NAME>-<ENV>.lfr.cloud/backup/download/volume/id \
  -u user@domain.com:password \
  --output volume.tgz
```




<a name="upload-backup-api" />

### バックアップAPIのアップロード

アップロードバックアップAPIを使ってDXP Cloudにバックアップをアップロードするには、以下の手順に従ってください。

1. [データベースファイル作成します](#creating-the-database-file) 。

1. [ボリュームファイル作成します](#creating-the-volume-file) 。

1. [データベースとボリュームのファイルを使って、](#invoking-the-backup-api) バックアップAPIを起動します。

アップロードAPIを使用する前に、データベースダンプとドキュメントライブラリを別々のアーカイブに圧縮する必要があります。 オンプレミス環境でのアップロードの準備については、 [データベースとドキュメント・ライブラリーのアップロードの準備](#preparing-the-database-and-document-library-for-upload) を参照してください。



#### バックアップAPIの呼び出し

**パラメーター**

| 名前         | タイプ    | 必須 |
| ---------- | ------ | -- |
| `database` | `ファイル` | はい |
| `体積`       | `File` | はい |


**curlの例**



```bash
curl -X POST \
  https://backup-<PROJECT-NAME>-<ENV>.lfr.cloud/backup/upload \
  -H 'Content-Type: multipart/form-data' \
  -F 'database=@/my-folder/database.gz' \
  -F 'volume=@/my-folder/volume.tgz' \
  -u user@domain.com:password
```




<a name="preparing-the-database-and-document-library-for-upload" />

## データベースとドキュメントライブラリのアップロード準備

お客様の環境のバックアップをDXP Cloudにアップロードするためには、その環境のデータベースとドキュメントライブラリが別々のアーカイブファイルとして準備されている必要があります。



<a name="creating-the-database-file" />

### データベースファイルの作成

MySQLのダンプを（ `.sql` スクリプトとして）作成し、それを `.gz` アーカイブに圧縮するには、以下のコマンドを実行します。



```bash
mysqldump -uroot -ppassword --add-drop-database --databases lportal | gzip -c | cat > database.gz
```




```{note}
   お使いのBackupサービスが少なくともバージョン  にアップデートされていない場合には、以下のコマンドを実行して、アーカイブを `.tgz` ファイルに変換する必要があります。tar zcvf database.tgz database.gz`. そして、出来上がった［`.tgz`］のアーカイブを使ってアップロードします。
```


`データベース` および `add-drop-database` オプションは、バックアップの復元が正しく機能するために必要です。 また、 `/backup/download` APIを使用して、バックアップサービスがどのようにMySQLのダンプファイルを作成するかを確認することができます。

これらのオプションを指定した場合、作成されるダンプファイルには、create tableステートメントの直前に以下のコードが含まれます。



```sql
--
-- Current Database: `lportal`
--

/*!40000 DROP DATABASE IF EXISTS `lportal`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `lportal` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `lportal`;
```




<a name="creating-the-volume-file" />

### ボリュームファイルの作成

このコマンドを実行すると、データボリュームが圧縮されます。



```bash
cd $LIFERAY_HOME/data && tar -czvf volume.tgz document_library
```




<a name="additional-information" />

## 追加情報

* [バックアップサービスの概要](./backup-service-overview.md)
* [バックアップからのデータの復元](./restoring-data-from-a-backup.md)
* [データベースサービス(MySQL)](../database-service/database-service.md)
