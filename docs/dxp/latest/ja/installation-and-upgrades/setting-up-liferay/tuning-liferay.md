# Liferayの調整

Liferayのパフォーマンスを調整する方法はいくつかあります。 これには、LiferayアプリケーションをサポートするJava仮想マシンとフレームワークの設定、パフォーマンスとリソースの監視、およびニーズに合わせた設定の調整が含まれます。 ここでは、調整トピックの概要を示します。

## 開発者設定の無効化

一部の開発者機能は本番環境用ではないため、パフォーマンスを最適化するには無効にする必要があります。 これらには、次のことを行う機能が含まれます。

  - デバッガーに対応
  - システムチェックの実施
  - 起動時に自動的にデータをアップグレード
  - コード変更をポーリングして自動的に適用

すべての開発者ポータルプロパティを無効にすることから始めます。

### ポータル開発者プロパティ

Liferayの[ポータルプロパティ](../reference/portal-properties.md)は、開発を容易にするいくつかのプロパティが含まれています。 Liferayのインストールに含まれている[`portal-developer.properties`](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-impl/src/portal-developer.properties)は、すべてのプロパティを宣言するものですが、デフォルトでは無効になっています。 このファイルは、次の設定を使って、`portal-ext.properties`ファイルで参照した場合のみ有効になります。

``` properties
include-and-override=portal-developer.properties
```

Liferayの`portal-developer.properties`ファイルを含めた場合、または独自の開発者プロパティファイル（例：`[Liferay Home]/portal-developer.properties`）を含めた場合は、`portal-ext.properties`ファイルでそれらをコメントアウトして無効にします。

``` properties
#include-and-override=portal-developer.properties
#include-and-override=${liferay.home}/portal-developer.properties
```

同様に、開発者プロパティを個別に有効にした場合は、それらもコメントアウトします。

次に、JSPエンジンで開発者設定を無効にします。

### JSPエンジン設定

多くのアプリケーションサーバーのJSPエンジンは、デフォルトで開発用に設定されています。 本番環境に入る前に、次の設定を無効にしてください。

**開発モード：** これにより、JSPコンテナはJSPファイルへの変更についてファイルシステムをポーリングします。 本番環境ではこのようにJSPをオンザフライで変更しないため、このモードをオフにしてください。

**マップされたファイル:** 開発では、通常、JSPテキストの1行あたり1つのステートメントに対して、1つの印刷ステートメントを使用して静的コンテンツを生成します。 本番環境では、後者を選択します。

例えば、Tomcatで開発モードとマッピングされたファイルを無効にするには、 `$CATALINA_HOME/conf/web.xml`ファイルのJSPサーブレット構成を次のように更新します。

``` xml
<servlet>
    <servlet-name>jsp</servlet-name>
    <servlet-class>org.apache.jasper.servlet.JspServlet</servlet-class>   
    <init-param>
        <param-name>development</param-name>
        <param-value>false</param-value>
    </init-param>
    <init-param>
        <param-name>mappedFile</param-name>
        <param-value>false</param-value>
    </init-param>
    <load-on-startup>3</load-on-startup>
</servlet>
```

開発モードとマップされたファイルは無効になります。

開発環境用開発者機能を無効にしたので、アプリケーションサーバーのスレッドプールを設定します。

## スレッドプールの設定

アプリケーションサーバーへの各リクエストは、リクエストの期間中、ワーカースレッドを消費します。 リクエストを処理するために使用できるスレッドがない場合、リクエストは次に使用可能なワーカースレッドを待機するためにキューに入れられます。 微調整されたシステムでは、スレッドプール内のスレッドの数は、同時リクエストの総数とバランスが取れています。 リクエストを処理するためにアイドル状態のままになっているスレッドが大量にある状態は避けるべきです。

初期スレッドプール設定（50スレッド）を使用してから、アプリケーションサーバーの監視コンソール内で監視します。 平均ページ時間が2〜3秒の範囲にある場合は、より大きな数値（250など）を使用することをお勧めします。 スレッドプール内のスレッドが少なすぎると、過剰なリクエストがキューに入れられる可能性があります。スレッドが多すぎると、過度のコンテキスト切り替えが発生する可能性があります。

Tomcatでは、スレッドプールは`$CATALINA_HOME/conf/server.xml`ファイルの`コネクタ`要素で設定します。 [Apache Tomcatのドキュメンテーション](https://tomcat.apache.org/tomcat-9.0-doc/config/http.html)に詳細が記載されています。 スレッドプール設定の例を次に示します。

``` xml
<Connector
    address="xxx.xxx.xxx.xxx"
    connectionTimeout="600000"
    maxConnections="16384"
    maxKeepAliveRequests="-1"
    maxThreads="75"
    minSpareThreads="50"
    port="8080"
    redirectPort="8443"
    socketBuffer="-1"
    URIEncoding="UTF-8"
/>
```

CPUベースの負荷をテストする場合、またはCPU容量が心配な場合は、使用可能なすべてのハードウェアスレッドに対して約75のスレッドを使用してテストします。 たとえば、ハードウェアスレッドが4つあるマシンの場合は、`maxThreads=300`と設定します。 I/Oベースの負荷をテストしている場合、またはI/O容量が心配な場合は、さらに多くのスレッドを使用するか、I/O以外のブロッキングコネクタの使用に切り替えてください。 システムをテストし、ニーズに合わせて接続プールの設定を調整します。

コネクタタイプ、接続タイムアウト、TCPキューなど、`コネクタ`付近の追加の調整パラメーターを使用できます。 詳細は、アプリケーションサーバーのドキュメンテーションを参照してください。

## データベース接続プールの設定

データベース接続プールは、再利用のためにデータベース接続を管理し、新しいリクエストごとに新しい接続を開く必要がないようにします。 Liferayがデータベースからデータを取得する必要があるときはいつでも、プールは接続を提供します。 プールのサイズが小さすぎる場合は、リクエストがサーバーでデータベースの接続を待機するキューに入れられます。 しかし、サイズが大きすぎると、アイドル状態のデータベース接続がリソースを浪費します。

スレッドプールサイズが大きくない限り（たとえば、200以上）、プールサイズはスレッドプールサイズよりわずかに大きく設定してください。 通常の使用では、ほとんどのワーカースレッドは一度に最大1つのJDBC接続を使用します。 ただし、ネストされたトランザクションを持つスレッドなど、一部のスレッドは複数のデータベース接続を使用します。 接続プールのサイズをスレッドプールのサイズよりわずかに大きくすると、そのようなスレッドが考慮されます。

```{note}
スレッドプールサイズが大きい場合、接続プールを同じサイズにしてもパフォーマンスは向上しません。
```

接続数がデータベース接続制限に違反している場合は、カウンターデータソースのプールサイズを縮小してください。 カウンターデータベースのトランザクション数は少なく、高速で、ネストされることはないため、カウンター接続プールは削減に適した候補となります。 カウンターデータソースの詳細は、[クラスタノードのデータベース構成](./clustering-for-high-availability/database-configuration-for-cluster-nodes.md)を参照してください。

Liferayは、接続プールにC3PO、DBCP、HikariCP、またはTomcatを使用できます。 接続プールプロバイダーは、[`jdbc.default.liferay.pool.provider`](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#JDBC)[ポータルプロパティ](../reference/portal-properties.md)を使用して設定します。 HikariCPがデフォルトです。

``` properties
jdbc.default.liferay.pool.provider=hikaricp
jdbc.default.maximumPoolSize=85
jdbc.default.minimumIdle=10
```

サポートされているすべての接続プールに対して、[JDBC接続プールポータルプロパティ](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#JDBC)があります。 設定の詳細は、接続プールベンダーの情報を参照してください。

スレッドプールと同様に、接続プールを監視し、パフォーマンステストに基づいて調整します。

## Java仮想マシンの設定

アプリケーションサーバーはJava仮想マシン（JVM）で実行されます。 メモリ管理とガベージコレクションは、Liferayがユーザーリクエストに応答する速度に影響します。 手順については、次の[JVMの調整](./tuning-your-jvm.md)を参照してください。