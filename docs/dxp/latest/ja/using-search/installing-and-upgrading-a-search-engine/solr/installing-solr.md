# Solrのインストール

Solrは、Apache Luceneをベースに開発されたエンタープライズサーチプラットフォームとして人気があります。 信頼性、スケーラビリティ、フォールトトレラントに優れています。 詳しくは [こちら](http://lucene.apache.org/solr/) です。

ここでは、Liferay 7.1～7.3用のSolr 8のインストールについて説明します。 これらはLiferay 7.1-7.2用のSolr 7をインストールする際にも同様に適用されますが、バージョン名の調整が必要な場合があります。

```{important}
   - LiferayのSolrサポートは非推奨で、Solr 8が最後にサポートされたSolrバージョンです。 [Elasticsearch](../elasticsearch/getting-started-with-elasticsearch.md)  に移行することを検討してください。

   - Solrをインストールする際には、注意すべき重要な制限事項があります。 インストールを進める前に、 [Solrの制限](./solr-limitations.md) をお読みください。
```

これらの用語を覚えておいてください。

**Solr Home** : Solrのメイン構成ディレクトリである `solr-[version]/server/solr/`を示します。 ここでは、Liferayコアの設定を行います。

**Liferay Home** : Liferayインストールのルートフォルダ。 `osgi`, `deploy`, `data`, and `license` フォルダなどが含まれています。

<a name="compatibility" />

## 互換性

パッチレベルごとの互換性のあるSolrとLiferayのバージョンに関する詳細な情報については、 [検索エンジン互換性マトリクス](https://help.liferay.com/hc/ja/articles/360016511651) を参照してください。

<a name="disabling-elasticsearch-only-features" />

## Elasticsearchのみの機能を無効にする

Liferay Connector to Solrをインストールする前に、ブラックリストまたはその他の方法で

- DXPの機能のうち、Elasticsearchでのみ動作するモジュール
- Liferay Connector to Elasticsearchモジュールの組み合わせ

```{tip}
   OSGiランタイムにデプロイされたモジュールを検索し、シンボリックネームでリストアップするには、Gogoシェルのコマンド b [substring-to-search] -s`を使用します。 シンボリックネームは、`blacklistBundleSymbolicNames`プロパティに入力する値です。

   以下のコマンドは、ブラックリストに登録しなければならないモジュールのリストを返します。

   `lb -s | grep 'search' | grep 'elasticsearch|tuning'`
```

<a name="blacklisting-elasticsearch-only-features" />

### Elasticsearchのみの機能のブラックリスト化

Liferay DXPをご利用の方は、ブラックリスト機能を使ってElasticsearchのみの機能を無効にしてください。 CEユーザーもこの方法を使うことができます。

1. 以下の設定ファイルを作成します

   ```bash
   com.liferay.portal.bundle.blacklist.internal.BundleBlacklistConfiguration.config
   ```

1. 以下のコンテンツを指定します

   ```properties
   blacklistBundleSymbolicNames=[\
        "com.liferay.portal.search.elasticsearch6.api",\
        "com.liferay.portal.search.elasticsearch6.impl",\
        "com.liferay.portal.search.elasticsearch6.spi",\
        "com.liferay.portal.search.elasticsearch7.api",\
        "com.liferay.portal.search.elasticsearch7.impl",\
        "com.liferay.portal.search.elasticsearch7.spi",\
        "com.liferay.portal.search.tuning.rankings.web",\
        "com.liferay.portal.search.tuning.synonyms.web",\
        "com.liferay.portal.search.tuning.web",\
        "com.liferay.portal.search.tuning.web.api"\
   ]
   ```

1. `Liferay Home/osgi/configs`に配置してください。

<a name="stopping-the-modules-with-elasticsearch-only-features" />

### Elasticsearchのみの機能を持つモジュールの停止

アプリケーションマネージャとGogoシェルは、 `osgi/state` フォルダを頼りに、バンドルの状態を「記憶」しています。 このフォルダを削除すると（ [Liferay DXP](../../../installation-and-upgrades/maintaining-a-liferay-dxp-installation/patching-liferay/patching-liferay.md)のパッチ適用時に推奨）、Elasticsearchコネクタが再インストールされ、自動的に起動されます。 Liferay CEのユーザーは、ブラックリスト方式を使用するか、App ManagerまたはGogoシェルでElasticsearchおよび検索チューニングモジュールを無効にすることができます。

アプリケーションマネージャで無効にするには

1. ［コントロールパネル］ &rarr; ［アプリ］ &rarr; ［アプリマネージャー］ に移動します。

1. アプリケーションマネージャで、 **elasticsearch** を検索します。 Elasticsearch 6/7モジュールへのLiferayコネクタを見つけて、［アクション（![Actions](../../../images/icon-actions.png)）］メニューを開きます。 **無効にする** を選択します。  これにより、バンドルはインストールされたままになりますが、OSGiランタイムでは停止されます。 検索チューニングモジュールも同様です。

[Felix Gogo shellの](../../../liferay-internals/fundamentals/using-the-gogo-shell.md) を使用して、Elasticsearchモジュールと検索の調整モジュールを停止する場合。

1. `lb -s | grep 'search' | grep 'elasticsearch|tuning'`を入力します。

   Elasticsearchへのコネクタや検索チューニングモジュールなど、いくつかのアクティブなバンドルが表示されます。

1. リストアップされた各バンドルに対して、 `stop [bundle ID]`と入力します。

<a name="downloading-the-solr-connector" />

## Solrコネクタのダウンロード

Liferay Connector to Solr [7、8]をインストールするには、 [Liferay Marketplace](https://web.liferay.com/marketplace/) に移動し、Liferayのバージョンに対応するアプリのバージョンをダウンロードします。

   - **Liferay CE：**
      - [Liferay CE Connector to Solr 8](https://web.liferay.com/marketplace/-/mp/application/181462322)
      - [Liferay CE Connector to Solr 7](https://web.liferay.com/marketplace/-/mp/application/118014614)

   - **Liferay DXP：**
      - [Liferay Connector to Solr 8](https://web.liferay.com/marketplace/-/mp/application/181462183)
      - [Liferay Connector to Solr 7](https://web.liferay.com/marketplace/-/mp/application/117931595)

お使いのLiferayのバージョンとパッチレベルで互換性のあるアプリケーションのバージョンについては、 [検索エンジン互換性マトリクス](https://help.liferay.com/hc/ja/articles/360016511651) を参照してください。

<a name="installing-and-configuring-solr" />

## Solrのインストールと設定

**先に進む前に、Liferayインスタンスを停止します。**

Liferay用のSolrをインストールし、適切に設定するために。

1. [互換性のある](https://help.liferay.com/hc/ja/articles/360016511651) Solrサーバーをダウンロードし、解凍します。 以下のリンクは便宜上のものであり、執筆時に互換性のある最新のSolrバージョンを表しています。 新しい互換性のあるバージョンがあるかどうかは、 [検索エンジン互換性マトリクス](https://help.liferay.com/hc/ja/articles/360016511651) を参照してください。 このガイドでは、以下のバージョンを例に挙げています。
   - Liferay 7.1-7.3： [Solr 8.6.3](https://archive.apache.org/dist/lucene/solr/8.6.3/) となります。
   - Liferay 7.1-7.2： [Solr 7.5.0](http://archive.apache.org/dist/lucene/solr/7.5.0/) です。

1. Solrホーム（`solr-[version]/server/solr`）に移動し、 `liferay`という新しいフォルダを作成します。

1. 2つの新しいサブフォルダーを作成します。 `liferay/conf` 、 `liferay/data`

1. `Solr_Home/configsets/_default/conf` の内容を、 `Solr_Home/liferay/conf`にコピーします。

1. Liferay Connector to Solr 8のLPKGファイルをアーカイブマネージャーで開きます。

   次に、 `Liferay Connector to Solr 7/8 - Impl.lpkg`を開きます。

   最後に、 `com.liferay.portal.search.solr7/8.impl.jar` ファイルを開き、抽出します。

   ```
   META-INF/resources/solrconfig.xml
   ```

   そして

   ```
   META-INF/resources/schema.xml
   ```

   変更後

   ```
   Solr_Home/liferay/conf
   ```

   これは、現在の `solrconfig.xml` と `schema.xml` ファイルを、Liferayから来るデータをどのようにインデックスするかをSolrに伝えるもので置き換えます。

1. `core.properties` ファイルを `Solr_Home/liferay` に作成し、この設定を追加します。

   ```properties
   config=solrconfig.xml
   dataDir=data
   name=liferay
   schema=schema.xml
   ```

1. チェックポイント: `Solr_Home/liferay` フォルダがこのような構造になっていることを確認してください。

   ```bash
   liferay
   ├── conf
   │   ├── lang
   │   │   ├── contractions_ca.txt
   │   │   ├── ....txt
   │   ├── managed-schema
   │   ├── protwords.txt
   │   ├── schema.xml
   │   ├── solrconfig.xml
   │   ├── stopwords.txt
   │   └── synonyms.txt
   ├── core.properties
   └── data
   ```

1. Solrをインストールしたトップレベルのフォルダー（`solr-[version]`）から次のように入力して、Solrサーバーを起動します。

   ```bash
   ./bin/solr start -f
   ```

1. Solrサーバーは、デフォルトではポート `8983` で待機します。 <http://localhost:8983/solr/#/~cores> へ行きます。(ローカルでテストしていると仮定して`localhost` をホストとします） `liferay` コアが利用可能なことを確認します。

これでSolrがインストールされ、起動しました。 次に、Liferay用のSolrコネクタを構成し、インストールします。

<a name="installing-and-configuring-the-solr-connector" />

## Solrコネクタのインストールと設定

Liferay Connector to Solrのデフォルト構成は、Solr自身のデフォルトと一致するため、デフォルトのコネクタ構成はテストインストールでも機能します。 利用可能な設定の完全なリストは、 [設定のリファレンス](#solr-connector-configuration-reference) を参照してください。 本番環境のコネクターでは、最低でも読み取り用と書き込み用のURLが設定されている必要があります。

本番環境では、 `Liferay_Home/osgi/configs` フォルダに配置された設定ファイルを使用して、Solrコネクタのデフォルト設定を編集するのが一般的です。

1. ファイルに名前を付けます。

```
com.liferay.portal.search.solr8.configuration.SolrConfiguration.config
```


    または

```
com.liferay.portal.search.solr7.configuration.SolrConfiguration.config
```

   また、UIを使ってコネクタを構成することもできます。 ［コントロールパネル］の &rarr; ［構成］ &rarr; ［システム設定］で、Solr 7システム設定のエントリーを見つけます。

   ![Solrの設定は、Liferayのシステム設定アプリケーションから行うことができます。 これは、開発やテストの際に最も有効です。](./installing-solr/images/02.png)

1. アプリのLPKGがダウンロードされたら、それを `Liferay_Home/osgi/marketplace` にコピーし、任意の設定ファイルを `Liferay_Home/osgi/configs`に置きます。

1. Liferayを起動します。

1. LiferayのデータをSolrに再インデックスします。 グローバルメニューを開き、 ［**コントロールパネル**］ &rarr; ［**設定**］ &rarr; ［**検索**］ へ行きます。 ［インデックス アクション］ウィンドウで、 ［**実行**］ オプションの隣にある ［**すべての検索インデックスを再作成**］ をクリックします。

   スペルチェック用のインデックスも再作成してください。

   ![Solrの接続は、Search管理コンソールで確認できます。](./installing-solr/images/01.png)

<a name="high-availability-with-solrcloud" />

## SolrCloudによる高可用性

Solrサーバーのクラスターが必要な場合は、SolrCloudを使用します。 なお、SolrCloudを実運用で使用するには、 [外部のZooKeeperアンサンブル](https://cwiki.apache.org/confluence/display/solr/Setting+Up+an+External+ZooKeeper+Ensemble) を設定する必要があります。 [ZooKeeper](http://zookeeper.apache.org/) は、SolrCloudクラスターのような分散システムを管理するための、集中型のコーディネーションサービスです。

ここで紹介する手順は、LiferayでSolrCloudを設定するために必要な最低限のものと考えてください。 例えば、ここでは1台のマシンでSolrCloudを構成する方法を説明していますが、本番環境では複数の物理マシンや仮想マシンを使用します。 ここでは、 **Solrのインストールと設定** のセクションに沿って説明しています。 詳しくは [SolrCloudガイドをご参照ください](https://cwiki.apache.org/confluence/display/solr/SolrCloud) .

1. Solrサーバーが稼働している場合は停止します。

1. `Solr_Home/configsets` フォルダに移動し、以下のフォルダを作成します。

   `liferay_configs`

1. `conf` フォルダを、 `Solr_Home/liferay` から、先ほど作成した `liferay_configs` フォルダにコピーします。

   `configset/liferay_configs` フォルダには、SolrCloudのLiferayコレクションの設定が含まれており、ZooKeeperにアップロードされます。 先に設定した `conf` フォルダを、 `liferay` サーバーからコピーする際には、Liferay Solr Adapterに付属する `schema.xml` と `solrconfig.xml` ファイルを使用します。

1. インタラクティブなSolrCloudセッションを起動して、SolrCloudクラスターを構成します。

   ```bash
   ./bin/solr -e cloud
   ```

1. セットアップ・ウィザードを完了する。 ここでは、2ノードのクラスターを作成する手順を説明します。

    -  ノード数には `2` を入力してください。
    -  ポートは `8983` と `7574` （デフォルト）を指定します。 両ノードは、ログに出力されるスタートコマンドで起動されます。

       ```
       Starting up Solr on port 8983 using command:
       "bin/solr" start -cloud -p 8983 -s "example/cloud/node1/solr"

       Waiting up to 180 seconds to see Solr running on port 8983 [|]  [-]  
       Started Solr server on port 8983 (pid=8846). Happy searching!


       Starting up Solr on port 7574 using command:
       "bin/solr" start -cloud -p 7574 -s "example/cloud/node2/solr" -z localhost:9983

       Waiting up to 180 seconds to see Solr running on port 7574 [|]  [/]  
       Started Solr server on port 7574 (pid=9026). Happy searching!
       ```

    -  コレクション名 **liferay**
    -  コレクションを2つのシャードに分割します。
    -  シャードごとに2つのレプリカを指定します。
    -  コンフィギュレーションを選択するプロンプトが表示されたら、 **liferay_configs** と入力します。 クラスタが起動すると、以下のようなログメッセージが表示されます。

    ```bash
    SolrCloud example running, please visit http://localhost:8983/solr
    ```

これで、ローカルのSolrCloudクラスターに **liferay** という新しいコレクションができました。 **status** コマンドを実行して、その状態を確認します。

```bash
./bin/solr status
```

ログ出力では、ノードが見つかったことと、各ノードの情報が一覧表示されます。

```
Found 2 Solr nodes: 

Solr process 223597 running on port 8983
{
  "solr_home":"/home/user/liferay-bundles/solr8_7.3/solr-8.6.3/example/cloud/node1/solr",
  "version":"8.6.3 e001c2221812a0ba9e9378855040ce72f93eced4 - jasongerlowski - 2020-10-03 18:12:03",
  "startTime":"2021-03-23T18:26:45.450Z",
  "uptime":"0 days, 0 hours, 25 minutes, 4 seconds",
  "memory":"306.5 MB (%59.9) of 512 MB",
  "cloud":{
    "ZooKeeper":"localhost:9983",
    "liveNodes":"2",
    "collections":"1"}}
...
```

SolrCloudモードで実行中のSolrを停止するには、次のように **stop** コマンドを使用します。

```bash
./bin/solr stop -all
```

<a name="configure-the-solr-connector-for-solrcloud" />

## SolrCloud用Solrコネクタの設定

あとは、LiferayのSolrコネクタで、クライアントタイプを **CLOUD** と指定するだけです。

1. システム設定」またはOSGiの設定ファイルから、 **Client Type** を **CLOUD** に設定します。

   ```properties
   clientType="CLOUD"
   ```

1. Liferayが起動していない場合は、Liferayを起動します。 SolrコネクタLPKGをインストールした後に初めて起動した場合は、ログの指示に従ってLiferayを再起動してください。

![［Solr システム設定］のエントリーから、「Client Type」を「Cloud」に設定します。](./installing-solr/images/03.png)

<a name="solr-connector-configuration-reference" />

## Solr コネクタ設定リファレンス

```{note}
   Solr コネクタのプロパティは、Solr 7 および Solr 8 コネクタにも同様に適用されます。 設定ファイル名を（`solr8`ではなく）`solr7`に変更します。 システム設定で、_Solr 7_で始まるエントリを参照してください。
```

以下に、デフォルトの設定と、Solrコネクタ・アプリケーションをインストールしたときに利用可能になる設定（システム設定の検索カテゴリ、または `.config` ファイルで設定可能）を示します。 これらの設定は、限られた値を受け入れるものです。

**Configuration File:** `com.liferay.portal.search.solr8.configuration.SolrConfiguration.config` \ **System Settings Entry:****Solr 8**
```properties
authenticationMode=["BASIC" or "CERT"]
clientType=["REPLICATED" or "CLOUD"]
defaultCollection="liferay"
logExceptionsOnly=B"true"
readURL="http://localhost:8983/solr/liferay"
writeURL="http://localhost:8983/solr/liferay"
zkHost="localhost:9983"
```

**Configuration File:** `com.liferay.portal.search.solr8.configuration.SolrHttpClientFactoryConfiguration.config` \ **System Settings Entry:****Solr 8 HTTP Client Factory**

```properties
basicAuthPassword="solr"
basicAuthUserName="solr"
defaultMaxConnectionsPerRoute="20"
maxTotalConnections="20"
```

**Configuration File:** `com.liferay.portal.search.solr8.configuration.SolrSSLSocketFactoryConfiguration.config` \ **System Settings Entry:****Solr 8 SSL Factory**

```properties
keyStorePassword="secret"
keyStorePath="classpath:/keystore.jks"
keyStoreType="JKS"
trustStorePassword="secret"
trustStorePath="classpath:/truststore.jks"
trustStoreType="JKS"
verifyServerCertificate=B"true"
verifyServerName=B"true"
```

これで、Liferay for SolrとSolr for Liferayの設定ができました。 Solrは [廃止予定](../../../installation-and-upgrades/upgrading-liferay/reference/maintenance-mode-and-deprecations-in-7-3.md)なので、ご注意ください。 Solrを使うことに制約がない場合は、Elasticsearchを使ってください。
