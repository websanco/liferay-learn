# バージョン4におけるDXP Cloudプロジェクトの変更

DXP Cloudスタックのバージョン3.xと4.xの間で、サービスのDockerイメージバージョンが定義されている場所、リポジトリの構造、 `Jenkinsfiles` 使用方法など、いくつかの変更が行われています。

**内容：**

  - [Dockerイメージ定義の変更](#changes-to-docker-image-definitions)
  - [プロジェクト組織の変更](#project-organization-changes)
  - [Liferayサービスの変更](#liferay-service-changes)
  - [検索サービスの変更](#search-service-changes)
  - [CIサービスの変更](#ci-service-changes)
  - [ウェブサーバーサービスの変更](#webserver-service-changes)
  - [バックアップサービスの変更](#backup-service-changes)

## Dockerイメージ定義の変更

DXP Cloudのバージョン4.xのサービス用Dockerイメージは、プロジェクトの `gradle.properties` ファイルに定義されなくなりました。 これらは、各サービスの `LCP.json` で定義されるようになりました。

DXP Cloud Stackバージョン4にアップグレードすると、すべてのサービスのDockerイメージバージョンが `3.xx` から `4.xx`ます。 これらのイメージの変更は、DXP Cloudプロジェクトの構造と機能の方法に対する組織の変更に加えて行われます。

## プロジェクト組織の変更
リポジトリの最大の変更点は、各サービスのファイルがリポジトリのルートにあるフォルダーに移動されたことです。 `lcp` フォルダーが削除され、これらのサービスのパスに含まれなくなりました。 これらのフォルダー自体は、Liferayのワークスペース構造に類似するように再編成されています。 <!-- TODO: Point to workspace documentation -->

リポジトリのルートにあった他のいくつかのファイル（ `gradle.properties`、 `build.gradle`、 `settings.gradle`を含む）も、 `liferay` サービスに移動されました。

## Liferayサービスの変更
`liferay` サービスフォルダーは、Liferayワークスペースの機能構造に従います。 <!-- TODO: Point to workspace documentation -->

`liferay` サービス内のすべての設定は、プロジェクトのDXP Cloud環境に対応する環境固有の `configs` ディレクトリに属します。 さらに、 `ライセンス` フォルダーが削除されました。 代わりに、 `デプロイ` フォルダーにライセンスを追加してください。

次の表は、 `liferay` サービス設定の新しい設定をまとめたものです。

| **ファイル**                   | **3.xでの場所**                | **4.xでの場所**                         |
| -------------------------- | -------------------------- | ----------------------------------- |
| デプロイするファイル                 | lcp/liferay/deploy/{ENV}/  | liferay/configs/{ENV}/deploy/       |
| OSGi設定ファイル（.cfgまたは.config） | lcp/liferay/config/{ENV}/  | liferay/configs/{ENV}/osgi/configs/ |
| その他の設定オーバーライド              | lcp/liferay/config/{ENV}/  | liferay/configs/{ENV}/              |
| カスタムシェルスクリプト               | lcp/liferay/script /{ENV}/ | liferay/configs/{ENV}/scripts/      |
| ホットフィックスとパッチツール            | lcp/liferay/hotfix/{ENV}/  | liferay/configs/{ENV}/patching/     |
| ライセンス                      | lcp/liferay/license/{ENV}/ | lcp/configs/{ENV}/deploy/           |

``` note::
   configs/{ENV}/`` ディレクトリ内のファイルは、オーバーライドとして DXP Cloud の Liferay コンテナ内の ``LIFERAY_HOME`` ディレクトリにコピーされます。
```

### カスタムスクリプトの実行

`liferay/configs/{ENV}/scripts /` 配置されたスクリプトは、rootユーザーではなく `liferay` ユーザーとして実行されるようになりました。 スクリプトをrootとして実行する必要がある場合は、代わりにスクリプトを `Jenkinsfile` に追加する必要があります。

## 検索サービスの変更

`search` サービス内のすべての設定は、環境固有の `configs` ディレクトリに属します。 `search` サービス設定の新しい設定については、次の表を参照してください。

| **ファイル**                      | **3.xでの場所**               | **4.xでの場所**                   |
| ----------------------------- | ------------------------- | ----------------------------- |
| Elasticsearch の設定             | lcp/search/config/{ENV}/  | search/configs/{ENV}/config/  |
| カスタムシェルスクリプト                  | lcp/search/script/{ENV}/  | search/configs/{ENV}/scripts/ |
| Elasticsearchライセンス（.json）ファイル | lcp/search/license/{ENV}/ | search/configs/{ENV}/license/ |

``` note::
   検索コンテナ内の ``search/configs/{ENV}/`` にあるファイルは、DXP Cloud の検索コンテナ内の ``usr/shared/elasticsearch/`` にオーバーライドとしてコピーされます。 例えば、 ``search/configs/{ENV}/config/``` の設定、例えば ``elasticsearch.yml`` のような設定は ``usr/shared/elasticsearch/config/`` にコピーされ、既存のデフォルト値を上書きします。
```

### Elasticsearchプラグイン

Elasticsearchプラグインを `search` サービスにインストールできるようになりました。 インストールされているElasticsearchプラグインを表示するには、 `search` サービス内のシェルを使用して、次のコマンドを実行します。

``` bash
bin/elasticsearch-plugin list
```

イメージがデフォルトでインストールするプラグイン以外のElasticsearchプラグインを追加でインストールする場合は、 `search` サービスの `LCP_SERVICE_SEARCH_ES_PLUGINS` 環境変数を、インストールするプラグイン名のカンマ区切りリストに設定できます。 これらは、サービスのデプロイ中にインストールされます。

## CIサービスの変更

デフォルトのパイプラインが定義されたため、デフォルトの `Jenkinsfile` はリポジトリで不要になりました。 任意の `Jenkinsfile` は、リポジトリのルートではなく `ci` フォルダに定義されるようになりました。
複数の `Jenkinsfile` 拡張ポイントが `ci` フォルダ内で利用できるようになり、ビルド作成のさまざまな段階で手順を定義できるようになりました。 <!-- TODO: Add reference to Jenkinsfile-specific article -->

## ウェブサーバーサービスの変更

`webserver` サービス内のすべての設定は、環境固有の `configs` ディレクトリに属します。 さらに、静的コンテンツ用の `deploy` フォルダーが削除されました。 代わりに、カスタムコンテンツを `パブリック` フォルダーに追加します。

`webserver` サービス設定の新しい設定については、次の表を参照してください。

| **ファイル**  | **3.xでの場所**                 | **4.xでの場所**                      |
| --------- | --------------------------- | -------------------------------- |
| ウェブサーバー設定 | lcp/webserver/config/{ENV}/ | webserver/configs/{ENV}/conf.d/  |
| カスタムスクリプト | lcp/webserver/script/{ENV}/ | webserver/configs/{ENV}/scripts/ |
| 静的コンテンツ   | lcp/webserver/deploy/{ENV}/ | webserver/configs/{ENV}/public/  |

``` note::
   webserver/configs/{ENV}/``内のファイルは、DXP Cloudのウェブサーバコンテナ内の ``/etc/nginx/``にオーバーライドとしてコピーされます。 ``/webserver/configs/{ENV}/public/``` のファイルはオーバーライドとして ``var/www/html/`` にコピーされます。
```

### Webサーバー設定の上書き

`webserver/configs/{ENV}/conf.d/`に `liferay.conf` ファイルを追加することで、 `webserver` サービスのルートの場所をカスタマイズすることができます。 これにより、 `webserver` サービスイメージのコンテナで利用可能なデフォルトの `liferay.conf` が上書きされます。 ルートの場所をカスタマイズするときに、DXP Cloud Consoleのシェルにアクセスして、デフォルトとして `liferay.conf` ファイルを参照として表示します。

``` warning::
   ルートの場所を ``liferay.conf`` 以外のファイル名でカスタマイズしないでください。これにより、デフォルトの``liferay.conf``を上書きします。 そうでない場合は、両方のファイルがコンテナ内に一緒に存在していて、2つのルート位置が見つかってエラーになることがあります。

   他のファイル名は、代わりにウェブサーバの追加の場所を定義するために使用されます。
```

また、 `webserver/configs/{ENV}/`に `nginx.conf` ファイルを追加することで、デフォルトの NGINX 設定を上書きすることもできます。 これを使用して、Webサーバーの動作をさらに定義できます。 詳細は、 [公式のNGINXドキュメント](https://docs.nginx.com/nginx/admin-guide/basic-functionality/managing-configuration-files/) を参照してください。

### パブリックディレクトリの設定

カスタムの静的コンテンツを追加したい場合は、これらのファイルを `webserver/configs/{ENV}/public/`に配置してください。 DXP Cloudはこのパブリックフォルダを探し、その中のすべてのファイルを `/var/www/html`にコピーします。

パブリックフォルダーを設定するには、 `conf.d` フォルダー内に場所を追加する必要があります。 例えば、 `.html` ファイル（ `index.html`など）を新しい `webserver/configs/{ENV}/public/static` フォルダに追加するには、一意の `.confを <code>webserver/configs/{ENV}/conf.d` /conf.d {ENV}に追加します。conf</code> の設定ファイルを ` webserver/configs/ {ENV} /conf.d ` に以下の内容で追加します。

``` apacheconf
location /static/ {
  root /var/www/html;
}
```

## バックアップサービスの変更

`バックアップ` サービス内のすべての設定は、環境固有の `コンフィグ` ディレクトリに属するようになりました。 これは主にカスタムSQLスクリプトに関係しています。

| **ファイル**     | **3.xでの場所**              | **4.xでの場所**                   |
| ------------ | ------------------------ | ----------------------------- |
| カスタムSQLスクリプト | lcp/backup/script/{ENV}/ | backup/configs/{ENV}/scripts/ |

## 追加情報

  - [DXP Cloudスタックのアップグレード](./upgrading-your-dxp-cloud-stack.md)
