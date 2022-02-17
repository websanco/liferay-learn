# WebLogicへのインストール

アプリケーションサーバーとしてWebLogicを使用している場合は、DXPをWebLogic管理対象サーバーにインストールすることを **強くお勧め** します。 管理対象サーバーは、DXPをすばやく起動または停止でき、クラスター構成に変換できます。 ここでは、DXPを管理対象サーバーにインストールします。

<a name="前提条件" />

## 前提条件

[WebLogicのドキュメンテーション](http://www.oracle.com/technetwork/middleware/weblogic/documentation/index.html) に従って、管理サーバーと管理対象サーバーを構成します。

Liferay DXPにはJava JDK 8または11が必要です。 JDKの選択には [互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360049238151) を参照してください。 推奨される設定については、[JVM Configuration](../../reference/jvm-configuration.md)を参照してください。

これらのファイルを [ヘルプセンター](https://customer.liferay.com/downloads) （サブスクリプション）または [Liferayコミュニティダウンロード](https://www.liferay.com/downloads-community) からダウンロードします。

* DXP WARファイル
* OSGi依存関係のZIPファイル
* 依存関係のZIPファイル（DXP 7.3以前）

<a name="dxp-warの準備" />

## DXP WARの準備

1. DXP WARファイルを任意の場所に解凍します。

1. 拡張されたWARの`WEB-INF/classes`フォルダに[`portal-ext.properties`](../../reference/portal-properties.md)というファイルを作成します。

1. `portal-ext.properties`ファイルで、`liferay.home`プロパティを[**Liferay Home**](../../reference/liferay-home.md)フォルダパスに設定します。 WebLogicでは、[`[Liferay Home]`](../../reference/liferay-home.md)は通常、ドメインのフォルダに設定されていますが、任意のローカルフォルダを使用できます。 例:

    ```properties
    liferay.home=/full/path/to/your/liferay/home/folder
    ```

1. DXP WARファイルを展開し、 `portal-ext.properties` ファイルを `WEB-INF/classes` フォルダーにコピーして、 `portal-ext.properties` をDXP WARファイルにパッケージ化します。

1. オプションで、拡張されたDXP WARを再度WARできます。 DXPをデプロイする準備ができたら、それを拡張アーカイブまたはWARファイルとしてデプロイできます。 どちらの場合も、DXPは起動後にプロパティ設定を読み取ります。

```{note}
DXPのデプロイ後に`portal-ext.properties`を更新する必要がある場合は、ユーザードメインの`autodeploy/ROOT/WEB-INF/classes`フォルダにあります。 `autodeploy/ROOT`フォルダにはDXPデプロイメントが含まれていることに注意してください。
```

<a name="weblogicの構成" />

## WebLogicの構成

### WebLogicのノードマネージャのコンフィグレーション

WebLogicのノードマネージャは、管理対象サーバーを起動および停止します。

SolarisまたはLinux以外のUNIXシステムでWebLogicを実行している場合は、`domains/your_domain_name/nodemanager/nodemanager.properties` ファイル:でこれらのノードマネージャプロパティを設定することにより、ノードマネージャのネイティブバージョンの代わりにJavaノードマネージャを使用します。：

```properties
NativeVersionEnabled=false

StartScriptEnabled=true
```

```{note}
デフォルトでは、ノードマネージャーはSSLを使用します。 開発中にSSLを無効にする場合は、`nodemanager.properties`ファイルで`SecureListener=false`を設定します。
```

詳細は、Oracleの [Configuring Java Node Manager](https://docs.oracle.com/middleware/1212/wls/NODEM/java_nodemgr.htm#NODEM173) ドキュメントを参照してください。

### WebLogicのJVMの構成

`setUserOverridesLate` WebLogic起動スクリプトおよび管理対象サーバーUIでJVMおよびその他のオプションを構成します。

1. `[Your Domain]/bin`で`setUserOverridesLate.sh`スクリプトを作成します。

1. 以下の設定を追加します。

    ```bash
    export DERBY_FLAG="false"
    export JAVA **OPTIONS="${JAVA** OPTIONS} -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -da:org.apache.lucene... -da:org.aspectj..."
    export JAVA **PROPERTIES="-Dfile.encoding=UTF-8 ${JAVA** PROPERTIES} ${CLUSTER_PROPERTIES}"
    export MW_HOME="[place your WebLogic Server folder path here]"
    export USER **MEM** ARGS="-Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"
    ```

    `DERBY_FLAG`設定は、WebLogicの組み込みDerbyサーバーを無効にします（DXPはこのサーバーを必要としません）。

    `JAVA_OPTIONS`は、DXPのUTF-8要件、Luceneの使用法、およびAspectJを介したアスペクト指向プログラミングを設定します。

    `JAVA_PROPERTIES`は、DXPのUTF-8要件も設定します。 TODOはliferay-portalごとに小文字を使用しますか？

    ```{important}
    DXPでは、アプリケーションサーバーのJVMが `GMT`タイムゾーンと` UTF-8`ファイルエンコーディングを使用する必要があります。
    ```

    `MW_HOME`をWebLogicサーバーを含むフォルダに設定します。 例:

    ```bash
    export MW_HOME="/Users/ray/Oracle/wls12210"
    ```

    `* _MEM_ARGS`変数は、DXPの開始および最大ヒープメモリ容量を設定します。

1. 管理対象サーバの起動時に、ノードマネージャがDXPのメモリ要件を設定していることを確認してください。 管理サーバーのコンソールUIで、DXPがデプロイされる管理対象サーバーに移動し、［**サーバーの開始**］タブを選択します。 **Arguments** フィールドに次のパラメータを入力します。

    ```bash
    -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7
    ```

1. ［**保存**］ をクリックします。

Javaオプションとメモリ引数について以下に説明します。

**JVMオプションの説明**

| オプション                                     | 説明                                            |
|:----------------------------------------- |:--------------------------------------------- |
| `-Dfile.encoding=UTF-8`                   | DXPにはUTF-8ファイルエンコーディングが必要です。                  |
| `-Djava.locale.providers=JRE,COMPAT,CLDR` | これは、JDK 11で4桁の日付を表示するために必要です。                 |
| `-Djava.net.preferIPv4Stack=true`         | IPv6よりもIPv4スタックを優先します。                        |
| `-Duser.timezone=GMT`                     | DXPでは、アプリケーションサーバーのJVMがGMTタイムゾーンを使用する必要があります。 |

**メモリ引数の説明**

| メモリ引数                  | 説明                                                                     |
|:---------------------- |:---------------------------------------------------------------------- |
| `-Xms`                 | ヒープの初期スペース。                                                            |
| `-Xmx`                 | ヒープの最大スペース。                                                            |
| `-XX:NewSize`          | 最初の新しいスペース。 通常、新しいサイズをヒープ全体の半分に設定すると、より小さな新しいサイズを使用するよりもパフォーマンスが向上します。 |
| `-XX:MaxNewSize`       | 最大の新しいスペース。                                                            |
| `-XX:MetaspaceSize`    | 静的コンテンツ用の初期スペース。                                                       |
| `-XX:MaxMetaspaceSize` | 静的コンテンツ用の最大スペース。                                                       |
| `-XX:SurvivorRatio`    | 新しいスペースとSurvivor領域の比率。 Survivor領域は、古い世代の領域に昇格する前に、若い世代のオブジェクトを保持します。   |

```{note}
DXPのインストール後、これらの構成（これらのJVMオプションを含む）をさらに調整して、パフォーマンスを向上させることができます。 詳細については、[Tuning Liferay](../../setting-up-liferay/tuning-liferay.md)および[Tuning Your JVM](../../setting-up-liferay/tuning-your-jvm.md)を参照してください。
```

<a name="依存関係をインストールする" />

## 依存関係をインストールする

DXPは、OSGiモジュール（OSGi依存関係ZIP）とデータベースドライバーに依存しています。

1. OSGi Dependencies ZIPファイルを `［Liferay Home］/osgi` フォルダーに解凍します（このフォルダーが存在しない場合は作成します）。 LiferayのOSGiランタイムは、これらのモジュールに依存しています。
1. DXP 7.4+ WARファイルには、MariaDB、MySQL、およびPostgreSQLのドライバーが含まれています。 以前のDXP WARにはそれらがありません。 DXP WARに必要なドライバーがない場合は、データベースベンダーのJDBC JARファイルをドメインの`lib`フォルダにダウンロードします。 サポートされるデータベースのリストについては、 [互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360049238151) を参照してください。

```{note}
HypersonicデータベースはDXPにバンドルされており、テスト目的で役立ちます。 本番環境インスタンスにはHSQLを**使用しないでください**。
```

```{note}
DXP 7.3以前の場合、依存関係のZIPファイルをWebLogicドメインの `lib`フォルダに解凍します。
```

<a name="elasticsearchのインストール" />

## Elasticsearchのインストール

DXPが起動すると、デフォルトの[sidecar](../../../using-search/installing-and-upgrading-a-search-engine/elasticsearch/using-the-sidecar-or-embedded-elasticsearch.md) Elasticsearchサーバーがインストールされて起動します。 インストールを成功させるには、いくつかのアーカイブを提供する必要があります。

以下のアーカイブを`[Liferay Home]`フォルダにダウンロードします。

* [Elasticsearch OSS No JDK 7.9](https://www.elastic.co/guide/en/elasticsearch/reference/7.9/release-notes-7.9.0.html) （ [こちらで入手可能--7.9.0](https://www.elastic.co/downloads/past-releases/elasticsearch-oss-no-jdk-7-9-0) )
* [ICU Analysis Plugin](https://www.elastic.co/guide/en/elasticsearch/plugins/7.9/analysis-icu.html) （ [ダウンロード](https://artifacts.elastic.co/downloads/elasticsearch-plugins/analysis-icu/analysis-icu-7.9.0.zip) ）
* [Japanese (kuromoji) Analysis Plugin](https://www.elastic.co/guide/en/elasticsearch/plugins/7.9/analysis-kuromoji.html) （ [ダウンロード](https://artifacts.elastic.co/downloads/elasticsearch-plugins/analysis-kuromoji/analysis-kuromoji-7.9.0.zip) ）
* [Smart Chinese Analysis Plugin](https://www.elastic.co/guide/en/elasticsearch/plugins/7.9/analysis-smartcn.html) （ [ダウンロード](https://artifacts.elastic.co/downloads/elasticsearch-plugins/analysis-smartcn/analysis-smartcn-7.9.0.zip) ）
* [Stempel Polish Analysis Plugin](https://www.elastic.co/guide/en/elasticsearch/plugins/7.9/analysis-stempel.html) （ [ダウンロード](https://artifacts.elastic.co/downloads/elasticsearch-plugins/analysis-stempel/analysis-stempel-7.9.0.zip) ）

DXPの起動時に、DXPはアーカイブを解凍してインストールし、SidecarのElasticsearchサーバーを起動します。

<a name="データベースに接続" />

## データベースに接続

DXPには、デモンストレーション目的で組み込みのHypersonicデータベースが含まれていますが、 **は本番環境では使用しないでください** 。 フル機能のサポートされているデータベースを使用します。 データベースのセットアップについては、[Configure a Database](../configuring-a-database.md)を参照してください。

Liferay DXPは、DXPの組み込みデータソース（推奨）またはアプリサーバー上のJNDIデータソースを使用してデータベースに接続できます。

[セットアップウィザード](../running-liferay-for-the-first-time.md)を使用して、DXPを初めて実行するときに、データベースを使用してDXPの組み込みデータソースを構成できます。 または、データベースの [データベーステンプレート](../../reference/database-templates.md) 基づいて、データソースを [`portal-ext.properties` ファイル](../../reference/portal-properties.md) で構成できます。

それ以外の場合は、WebLogicでデータソースを構成できます。

1. DXP WAR（7.4以降）またはデータベースベンダーからJDBC JARを取得し、ドメインの`lib`フォルダにコピーします。
1. AdminServerコンソールにログインします。
1. **ドメイン構造** ツリーでドメインを見つけ、 ［**Services**］ &rarr; ［**JDBC**］ &rarr; ［**Data Sources**］ に移動します。
1. 新しいデータソースを作成するには、［**New**］をクリックします。
1. `Liferayデータソース` を含む **Name** フィールドと `jdbc/LiferayPool` **JNDI Name** フィールドを入力します。
1. データベースのタイプとドライバーを選択します。 たとえば、MySQLは **MySQL's Driver (Type 4) Versions:using com.mysql.cj.jdbc.Driver** です。
1. **Next** をクリックして続行します。
1. このページのデフォルト設定を受け入れ、 **Next** をクリックして次に進みます。
1. MySQLデータベースのデータベース情報を入力します。
1. MySQLを使用する場合は、`?useUnicode=true&&char@@2 char@@3 char@@4 \useFastDateParsing=false` をURL行に追加し、接続をテストします。 機能する場合は、 **次へ** クリックします。
1. データソースのターゲットを選択し、 **終了** をクリックします。
1. DXPをJDBCデータソースに接続します。 `portal-ext.properties`ファイル（上記を参照）に、データソースのJNDI名を入力します。 例:

    ```properties
    jdbc.default.jndi.name=jdbc/LiferayPool
    ```

<a name="メールサーバーに接続" />

## メールサーバーに接続

組み込みのメールセッションを使用して、Liferay DXP [をメールサーバー](../../setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md) 接続できます。 それ以外の場合は、WebLogicのメールセッションを使用できます。

1. WebLogicを起動し、管理サーバーのコンソールにログインします。
1. 管理サーバーのコンソールUIの左側にある ［**Domain Structure**］ ボックスから ［**Services**］ &rarr; ［**Mail Sessions**］ を選択します。
1. ［**新規** をクリックして、新しいメールセッションの作成を開始します。
1. セッションに **LiferayMail** という名前を付け、JNDI名`mail/MailSession`を付けます。
1. メールサーバーに必要に応じて、 **セッションユーザー名** 、 **セッションパスワード** 、 **セッションパスワード** 、および **JavaMailのプロパティ** フィールドを入力します。 これらのフィールドの詳細は、 [WebLogicのドキュメント](http://docs.oracle.com/middleware/1221/wls/FMWCH/pagehelp/Mailcreatemailsessiontitle.html) を参照してください。
1. **次へ** をクリックします。
1. DXPをインストールする管理対象サーバーを選択し、 **終了** をクリックします。
1. 管理対象サーバーと管理サーバーをシャットダウンします。
1. 管理対象サーバーと管理サーバーをシャットダウンした状態で、次のプロパティをLiferayホームの `portal-ext.properties` ファイルに追加します。

    ```properties
    mail.session.jndi.name=mail/MailSession
    ```

```{note}
DXPがデプロイされると、`portal-ext.properties`ファイルはドメインの`autodeploy/ROOT/WEB-INF/classes`フォルダにあります。
```

管理サーバーと管理サーバーを再起動すると、変更が有効になります。

<a name="dxpのデプロイ" />

## DXPのデプロイ

次の手順に従って、DXP WARファイルをデプロイします。

1. DXPをデプロイしている指定の管理対象サーバーがシャットダウンされていることを確認します。
1. 管理サーバーのコンソールUIで、左側の **ドメイン構造** ボックスから **デプロイ** を選択します。
1. ［**インストール**］をクリックして、新しい展開を開始します。
1. マシンでDXP WARファイルまたはその拡張コンテンツを選択します。 または、 **ファイルのアップロード** リンクをクリックして、WARファイルをアップロードします。 **次へ** をクリックします。
1. ［**この展開をアプリケーションとしてインストールする**］を選択し、［**次へ**］をクリックします。
1. あなたはDXPを展開している指定管理対象サーバーを選択し、クリックしてください **次の** 。
1. デフォルトの名前がインストールに適している場合は、そのままにしておきます。 それ以外の場合は、別の名前を入力して **次へ** をクリックします。
1. ［**完了** クリックし* 。</li>
1 配置が完了したら、構成が正しい場合は、［**Save**］をクリックします。
1 DXPをデプロイした管理対象サーバーを起動します。 DXPはすべてのJSPをプリコンパイルしてから起動します。</ol>

DXPの導入後、 `PhaseOptimizer`含む以下のような警告やログメッセージが過剰に表示される場合があります。 これらは良性なので無視することができます。 これらのメッセージは、アプリサーバーのログレベルまたはログフィルターを調整することでオフにできます。

```
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
     WARNING: Skipping pass gatherExternProperties
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
     WARNING: Skipping pass checkControlFlow
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
     INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
     current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
```

Liferay DXP Enterpriseサブスクリプションをお持ちの場合、DXPはアクティベーションキーを要求します。 詳細は、[Activating Liferay DXP](../../setting-up-liferay/activating-liferay-dxp.md)を参照してください。

　 WebLogicでDXPを実行しています。

<a name="次のステップ" />

## 次のステップ

[管理者ユーザーとしてサインイン](../../../getting-started/introduction-to-the-admin-account.md)して、[DXPでソリューションの構築](../../../building-solutions-on-dxp/README.md)を開始できます。 または、[Liferay DXPのその他のセットアップ](../../setting-up-liferay.md)トピックを参照できます。

* [Installing the Marketplace Plugin](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md#appendix-installing-the-marketplace-plugin)
* [試用期間中のEEプラグインへのアクセス](../../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
* [検索エンジンのインストール](../../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md)
* [Securing Liferay DXP](../../securing-liferay.md)
* [高可用性のクラスタリング](../../setting-up-liferay/clustering-for-high-availability.md)