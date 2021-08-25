# WebLogicへのインストール

あなたは、アプリケーションサーバーとしてWebLogicを使用することを選択している場合、それは *強くお勧め* WebLogic管理対象サーバーへのDXPをインストールします。 管理対象サーバーは、DXPをすばやく起動または停止でき、クラスター構成に変換できます。 以下の手順は、管理対象サーバーへのDXPのインストールについて説明しています。

DXPをインストールする前に、 [WebLogicのドキュメント](http://www.oracle.com/technetwork/middleware/weblogic/documentation/index.html)に従って、管理サーバーと管理対象サーバーを設定します。

Liferay DXPにはJava JDK 8または11が必要です。 JDKの選択には [互換性マトリックス](https://www.liferay.com/documents/10182/246659966/Liferay+DXP+7.2+Compatibility+Matrix.pdf/ed234765-db47-c4ad-7c82-2acb4c73b0f9) を参照してください。

これらのファイルを [ヘルプセンター](https://customer.liferay.com/downloads) （サブスクリプション）または [Liferayコミュニティダウンロード](https://www.liferay.com/downloads-community)からダウンロードします。

  - DXP WARファイル
  - 依存関係のZIPファイル
  - OSGi依存関係のZIPファイル

WebLogicにLiferay DXPをインストールするには、次の手順が必要です。

1.  [DXP用にWebLogicを構成する](#configure-weblogic)
2.  [Liferayホームフォルダーを宣言する](#declare-the-liferay-home-folder)
3.  [依存関係をインストールする](#install-dxp-dependencies)
4.  [データベースに接続](#connect-to-database)
5.  [メールサーバーに接続](#connect-to-mail-server)
6.  [WARをデプロイする](#deploy-the-war)

## WebLogicの構成

### WebLogicのノードマネージャのコンフィグレーション

WebLogicのノードマネージャは、管理対象サーバーを起動および停止します。

SolarisまたはLinux以外のUNIXシステムでWebLogicを実行している場合は、`domains/your_domain_name/nodemanager/nodemanager.properties` ファイル:でこれらのノードマネージャプロパティを設定することにより、ノードマネージャのネイティブバージョンの代わりにJavaノードマネージャを使用します。：

``` properties
NativeVersionEnabled=false

StartScriptEnabled=true
```

``` note::
   By default, SSL is used with Node Manager. If you want to disable SSL during development, for example, set ``SecureListener=false`` in your ``nodemanager.properties`` file.
```

詳細については、Oracleの [Configuring Java Node Manager](https://docs.oracle.com/middleware/1212/wls/NODEM/java_nodemgr.htm#NODEM173) ドキュメントを参照してください。

### WebLogicのJVMの構成

WebLogicスクリプトおよび管理対象サーバーの変数とオプションを使用して、JVMを構成します。

1.  管理サーバーの起動スクリプト（`your-domain/startWebLogic.sh`）と管理対象サーバーの起動スクリプト（`your-domain/bin/startWebLogic.sh`）の両方に次の変数を追加します。

    ``` bash
    export DERBY_FLAG="false"
    export JAVA_OPTIONS="${JAVA_OPTIONS} -Dfile.encoding=UTF-8 -Duser.timezone=GMT -da:org.apache.lucene... -da:org.aspectj..."
    export MW_HOME="[/your WebLogic folder]"
    export USER_MEM_ARGS="-Xmx2560m -Xms2560m"
    ```

    DXPはこのサーバーを必要としないため、 `DERBY_FLAG` 設定は、WebLogicに組み込まれたDerbyサーバーを無効にします。 残りの設定は、DXPのメモリ要件、UTF-8要件、Luceneの使用、およびAspectJによるアスペクト指向プログラミングをサポートしています。

    ``` important::
       DXPが適切に機能するには、アプリケーションサーバーJVMがGMTタイムゾーンとUTF-8ファイルエンコーディングを使用する必要があります。
    ```

    また、マシン上のWebLogicサーバーを含むフォルダーに `MW_HOME` を設定してください。 例:

    ``` bash
    export MW_HOME="/Users/ray/Oracle/wls12210"
    ```

2.  `your-domain/bin/SetDomainEnv.sh` が次のメモリ設定を使用していることを確認してください：

    ``` bash
    WLS_MEM_ARGS_64BIT="-Xms2560m -Xmx2560m"
    export WLS_MEM_ARGS_64BIT

    WLS_MEM_ARGS_32BIT="-Xms2560m -Xmx2560m"
    export WLS_MEM_ARGS_32BIT
    ```

3.  他のJavaプロパティの前に `-Dfile.encoding=UTF-8`  追加して、Javaファイルのエンコーディングを `your-domain/bin/SetDomainEnv.sh`  UTF-8に設定します。

    ``` bash
    JAVA_PROPERTIES="-Dfile.encoding=UTF-8 ${JAVA_PROPERTIES} ${CLUSTER_PROPERTIES}"
    ```

4.  管理対象サーバの起動時に、ノードマネージャがDXPのメモリ要件を設定していることを確認してください。 管理サーバーのコンソールUIで、DXPがデプロイされる管理対象サーバーに移動し、[ *サーバーの開始* ]タブを選択します。 *Arguments* フィールドに次のパラメータを入力します。

    ``` bash
    -Xmx2560m -Xms2560m -XX:MaxMetaspaceSize=512m
    ```

5.  *[保存]* をクリックします。

## Liferayホームフォルダーを宣言する

DXPをインストールする前に、 [*Liferay Home*](../../reference/liferay-home.md) フォルダーの場所を設定します。

1.  [`portal-ext.properties`](../../reference/portal-properties.md)というファイルを作成します。 （ [ポータルプロパティ](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html)オーバーライドします。）

2.  `portal-ext.properties` ファイルで、 `liferay.home` プロパティをLiferayホームフォルダパスに設定します。 WebLogicでは、ドメインのフォルダーは通常Liferayホームですが、マシン上の他のフォルダーを使用できます。

    ``` properties
    liferay.home=/full/path/to/your/liferay/home/folder
    ```

3.  DXP WARファイルを展開し、 `portal-ext.properties` ファイルを `WEB-INF/classes` フォルダーにコピーして、 `portal-ext.properties` をDXP WARファイルにパッケージ化します。

4.  オプションで、拡張されたDXP WARを再度WARできます。 DXPをデプロイする準備ができたら、それを拡張アーカイブまたはWARファイルとしてデプロイできます。 どちらの場合も、DXPは起動後にプロパティ設定を読み取ります。

<!-- end list -->

``` note::
   If you need to update ``portal-ext.properties`` after DXP deploys, it is in the user domain's ``autodeploy/ROOT/WEB-INF/classes`` folder. Note that the ``autodeploy/ROOT`` folder contains the DXP deployment.
```

## DXP依存関係のインストール

DXPは、ライブラリ（依存関係ZIP）およびOSGiモジュール（OSGi依存関係ZIP）に依存しています。

1.  Dependencies ZIPファイルをWebLogicドメインの `lib` フォルダーに解凍します。
2.  OSGi Dependencies ZIPファイルを `[Liferay Home]/osgi` フォルダーに解凍します（このフォルダーが存在しない場合は作成します）。

DXPはJDBCを介してデータベースと通信します。 データベースのJDBCドライバーJARファイルをドメインの `lib` フォルダーに追加します。

サポートされるデータベースのリストについては、 [互換性マトリックス](https://www.liferay.com/documents/10182/246659966/Liferay+DXP+7.2+Compatibility+Matrix.pdf/ed234765-db47-c4ad-7c82-2acb4c73b0f9) を参照してください。

## データベースに接続

DXPには、デモンストレーション目的で組み込みのHypersonicデータベースが含まれていますが、 *は本番環境では使用しないでください*。 フル機能のサポートされているデータベースを使用します。 データベースのセットアップについては、[Configure a Database](../configuring-a-database.md)を参照してください。

Liferay DXPは、DXPの組み込みデータソース（推奨）またはアプリサーバー上のJNDIデータソースを使用してデータベースに接続できます。

初めてDXPを実行するときにDXPの組み込みデータソースを構成するには、 [セットアップウィザード](../../../getting-started/using-the-setup-wizard.md)使用します。 必要に応じて、データベースの [データベーステンプレート](../../reference/database-templates.md) 基づいて、 `portal-ext.properties` ファイルでデータソースを設定します。

それ以外の場合は、WebLogicでデータソースを構成できます。

1.  AdminServerコンソールにログインします。

2.  *Domain Structure* ツリーでドメインを見つけ、 *Services* → *JDBC* → *Data Sources*ます。

3.  新しいデータソースを作成するには、[ *New*]をクリックします。

4.  `Liferayデータソース` を含む *Name* フィールドと `jdbc/LiferayPool`*JNDI Name* フィールドを入力します。

5.  データベースのタイプとドライバーを選択します。 例えば、MySQLは*MySQLのドライバ(タイプ4)バージョン:com.mysql.cjdbcを使用しています。ドライバー*。

6.  *Next* をクリックして続行します。

7.  このページのデフォルト設定を受け入れ、 *Next* をクリックして次に進みます。

8.  MySQLデータベースのデータベース情報を入力します。

9.  MySQLを使用する場合は、`?useUnicode=true&&char@@2 char@@3 char@@4 \useFastDateParsing=false` をURL行に追加し、接続をテストします。 機能する場合は、 *次へ*クリックします。

10. データソースのターゲットを選択し、 *終了*をクリックします。

11. DXPをJDBCデータソースに接続します。 `portal-ext.properties` ファイル（上記を参照）に、次の行を入力します。

    ``` properties
    jdbc.default.jndi.name=jdbc/LiferayPool
    ```

## メールサーバーに接続

組み込みのメールセッションを使用して、Liferay DXP [をメールサーバー](../../setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md) 接続できます。 WebLogicのメールセッションを使用することもできます。

1.  WebLogicを起動し、管理サーバーのコンソールにログインします。

2.  管理サーバーのコンソールUIの左側にある*ドメイン構造*ボックスから*サービス*→ *メールセッション*を選択します。

3.  [ *新規* をクリックして、新しいメールセッションの作成を開始します。

4.  セッションに*LiferayMail*という名前を付け、JNDI名`mail/MailSession`を付けます。

5.  メールサーバーに必要に応じて、*セッションユーザー名*、*セッションパスワード*、*セッションパスワード*、および*JavaMailのプロパティ*フィールドを入力します。 これらのフィールドの詳細については、 [WebLogicのドキュメント](http://docs.oracle.com/middleware/1221/wls/FMWCH/pagehelp/Mailcreatemailsessiontitle.html) を参照してください。

6.  *次へ*をクリックします。

7.  DXPをインストールする管理対象サーバーを選択し、 *終了*をクリックします。

8.  管理対象サーバーと管理サーバーをシャットダウンします。

9.  管理対象サーバーと管理サーバーをシャットダウンした状態で、次のプロパティをLiferayホームの `portal-ext.properties` ファイルに追加します。

    ``` properties
    mail.session.jndi.name=mail/MailSession
    ```

DXPがすでにデプロイされている場合、`portal-ext.properties`ファイルは、ドメインの`autodeploy/ROOT/WEB-INF/classes`フォルダにあります。

管理サーバーと管理サーバーを再起動すると、変更が有効になります。

## WARをデプロイする

次の手順に従って、DXP WARファイルをデプロイします。

1.  DXPをデプロイしている指定の管理対象サーバーがシャットダウンされていることを確認します。
2.  管理サーバーのコンソールUIで、左側の*ドメイン構造*ボックスから*デプロイ*を選択します。
3.  [ *インストール* ]をクリックして、新しい展開を開始します。
4.  マシンでDXP WARファイルまたはその拡張コンテンツを選択します。 または、 *ファイルのアップロード* リンクをクリックして、WARファイルをアップロードします。 *次へ*をクリックします。
5.  [ *この展開をアプリケーションとしてインストールする* ]を選択し、[ *次へ*]をクリックします。
6.  あなたはDXPを展開している指定管理対象サーバーを選択し、クリックしてください *次の*。
7.  デフォルトの名前がインストールに適している場合は、そのままにしておきます。 それ以外の場合は、別の名前を入力して *次へ*をクリックします。
8.  [*完了*]をクリックします。
9.  配置が完了したら、構成が正しい場合は、[ *Save* ]をクリックします。
10. DXPをデプロイした管理対象サーバーを起動します。 DXPはすべてのJSPをプリコンパイルしてから起動します。

Liferay DXP Enterpriseサブスクリプションをお持ちの場合、DXPはアクティベーションキーを要求します。 [Liferay DXP](../../setting-up-liferay/activating-liferay-dxp.md)を参照してください。

WebLogicでDXPを実行しています。

``` note::
   Adjust the application server's logging level or log filters to avoid excessive benign log messages such as the ones below involving ``PhaseOptimizer``.
```

``` 
 May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
     WARNING: Skipping pass gatherExternProperties
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
     WARNING: Skipping pass checkControlFlow
May 02, 2018 9:12:27 PM com.google.javascript.jscomp.PhaseOptimizer$NamedPass process
     INFO: pass supports: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, modules, exponent operator (**), async function, trailing comma in param list]
     current AST contains: [ES3 keywords as identifiers, getters, reserved words as properties, setters, string continuation, trailing comma, array pattern rest, arrow function, binary literal, block-scoped function declaration, class, computed property, const declaration, default parameter, destructuring, extended object literal, for-of loop, generator, let declaration, member declaration, new.target, octal literal, RegExp flag 'u', RegExp flag 'y', rest parameter, spread expression, super, template literal, exponent operator (**), async function, trailing comma in param list, object literals with spread, object pattern rest]
```

## 次のステップ

[管理者ユーザーとしてサインイン](../../../getting-started/introduction-to-the-admin-account.md)して、[DXPでソリューションの構築](../../../building-solutions-on-dxp/README.md)を開始できます。 または、[Liferay DXPのその他のセットアップ](../../setting-up-liferay/setting-up-liferay.md)トピックを参照できます。

  - [Installing the Marketplace Plugin](../../../system-administration/installing-and-managing-apps/getting-started/using-marketplace.md#appendix-installing-the-marketplace-plugin)
  - [試用期間中のEEプラグインへのアクセス](../../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
  - [検索エンジンのインストール](../../../using-search/installing-and-upgrading-a-search-engine/introduction-to-installing-a-search-engine.md)
  - [Securing Liferay DXP](../../securing-liferay/introduction-to-securing-liferay.md)
  - [高可用性のクラスタリング](../../setting-up-liferay/clustering-for-high-availability.md)
