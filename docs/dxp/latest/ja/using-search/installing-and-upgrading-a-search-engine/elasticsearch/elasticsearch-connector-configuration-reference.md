# Elasticsearchコネクターの設定リファレンス

> ここに記載されている設定情報は、Liferay Portal 7.2-7.4 CEおよびLiferay DXP 7.2-7.4用のElasticsearch 6およびElasticsearch 7コネクタの最新の入手可能なバージョン（バンドル版またはMarketplace経由）に適用されます。 必要に応じて、正確なGA/Service Pack/Fix PackおよびMarketplaceのバージョンに関する適切な情報が提供されます。

Elasticsearchへの接続は、システム設定（または [対応する構成ファイル](#configuration-files-and-system-settings-entries) 介して）の **Elasticsearch 6/7** の設定エントリーで主に定義されます。 Liferay 7.3では、 [ファクトリー設定の](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-factory-configuration.md)**Elasticsearch接続** により、Elasticsearchへの複数の接続を定義できるようになりました。 両方の設定エントリーは [システム設定](../../../system-administration/configuring-liferay/system-settings.md) または [のOSGi構成ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)で設定可能です。 本番環境では、設定ファイルを使用することをお勧めします。

<a name="設定ファイルとシステム設定項目" />

## 設定ファイルとシステム設定項目

| サーバーの接続                                    | <div style="width:380px">システム設定エントリー/コンフィグファイル</div>                                                                                                                                                                                                                                                                                       |
| ------------------------------------------ | --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Liferay 7.2.x<br />Elasticsearch 6.x | Elasticsearch 6<br />`com.liferay.portal.search.elasticsearch6.config.ElasticsearchConfiguration.config`                                                                                                                                                                                                  |
| Liferay 7.2.x<br />Elasticsearch 7.x | Elasticsearch 7<br />`com.liferay.portal.search.elasticsearch7.config.ElasticsearchConfiguration.config`                                                                                                                                                                                                  |
| Liferay 7.3.x<br />Elasticsearch 7.x | Elasticsearch 7<br />`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConfiguration.config`<br /><br />Elasticsearch Connections (factory)<br />`com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-[connectionId].config` |

Liferay 7.3以降では、Elasticsearch接続という接続設定項目が追加されています。 これを使用してElasticsearchへの任意の接続を定義することができますが、1つの接続のみを設定する場合は、メインのElasticsearch 7設定エントリを使用することができます。 7.3で複数の接続を使用する場合は、それに応じたファイル名で接続を定義します。

```
com.liferay.portal.search.elasticsearch7.configuration.ElasticsearchConnectionConfiguration-[connectionId].config
```

Elasticsearch 6にセキュリティを設定する場合は、別途Liferayの設定（およびLESのサブスクリプション）が必要です。 詳しくは、 [Elasticsearchの保護](securing-elasticsearch.md) を参照してください。

設定ファイルを `[Liferay_Home]/osgi/configs` にデプロイし、リスナーが設定を自動検出してデータベースに書き込みます。

<a name="構成プロパティ" />

## 構成プロパティ

<table spaces-before="0">
  <tr>
    <th>
      システム設定 フィールド名
    </th>
    
    <th>
      <div style="width:290px">設定ファイルのシンタックスとデフォルト値<br />説明（クリックして展開）
    </th>
    
    <th>
      利用可能性
    </th>
  </tr>
  
  <tr>
    <td>
    </td>
    
    <td>
      <a href="#general-connection-settings" id="general-connection-settings">一般的な接続設定</a>
    </td>
    
    <td>
    </td>
  </tr>
  
  <tr>
    <td>
      合計ヒット数を記録
    </td>
    
    <td>
      <details><summary>`trackTotalHits=B "true"`</summary>有効にすると、検索結果が10,000件以上になったときにヒット数が正確にカウントされます。 この機能を有効にしておくと、検索結果の件数が多い場合にパフォーマンスに影響を与えることがあります。</details>
    </td>
    
    <td>
      Liferay 7.2+<br />(Connector to Elasticsearch 7)
    </td>
  </tr>
  
  <tr>
    <td>
      本番モードが有効
    </td>
    
    <td>
      <details><summary>`productionModeEnabled=B "false"`</summary>プロダクションモードを有効にします。 Liferay 7.3では、 <code>productionModeEnabled</code> が、非推奨の設定である <code>operationMode</code>を置き換えています。 これをチェックすると、プロダクションモードが有効になり、Operation Modeの設定は無視されます。 本番モードを有効にするには、リモートのスタンドアロンElasticsearchクラスタに接続する必要があります。 無効のままだと動作モードの設定が使用されます。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      インデックス名プレフィックス
    </td>
    
    <td>
      <details><summary>`indexNamePrefix="liferay-"`</summary>検索インデックス名のプレフィックスとして使用する文字列値を設定します。 初期値は通常の状態では変更しないでください。 変更した場合は、ポータルに対して <em x-id="3">reindex all</em> の操作も行い、その後Elasticsearch管理コンソールを使って古いインデックスを手動で削除する必要があります。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      7.3.x&rarr;Number of Company and System Index Replicas<br />7.2.x&rarr;Index Number of Replicas
    </td>
    
    <td>
      <details><summary>`indexNumberOfReplicas="0-all"`</summary>Liferayの会社やシステムのインデックスごとにレプリカの数を設定します。 設定されていない場合は、レプリカを使用しません。 この値を変更するには、完全な再インデックスが必要です。 デフォルト値は、コネクターに同梱されている "index-settings-defaults.json "というファイルに定義されています。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      7.3.x&rarr;Number of Company and System Index Shards<br />7.2.x&rarr;Index Number of Shards
    </td>
    
    <td>
      <details><summary>`indexNumberOfShards="1"`</summary>Liferayの会社やシステムのインデックスが作成されるときに使用するシャードの数を設定します。 設定されていない場合は、1つのシャードが使用されます。 この値を変更するには、完全な再インデックスが必要です。 デフォルト値は、コネクターに同梱されている "index-settings-defaults.json "というファイルに定義されています。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      例外のみロギングする
    </td>
    
    <td>
      <details><summary>`logExceptionsOnly=B "true"`</summary>trueの場合、Elasticsearchからの例外をログにのみ記録し、再スローしないようにするブール値の設定です。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      コンフリクト時の再試行
    </td>
    
    <td>
      <details><summary>`retryOnConflict="5"`</summary>ドキュメントを取得してから更新するまでの間に、ドキュメントが更新されたためにバージョンの競合が発生した場合に、再試行する回数を整数値で設定します（詳細は、 <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.x/docs-update.html#docs-update-api-query-params">こちらの</a> を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.1では使用されなくなりました。
    </td>
  </tr>
  
  <tr>
    <td>
    </td>
    
    <td>
      <a href="#security-settings" id="security-settings">セキュリティ設定</a>
    </td>
    
    <td>
    </td>
  </tr>
  
  <tr>
    <td>
      認証が有効
    </td>
    
    <td>
      <details><summary>`authenticationEnabled=B "false"`</summary>ユーザー名とパスワードによるElasticsearchへの認証を有効または無効にします。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      ユーザ名
    </td>
    
    <td>
      <details><summary>`username="elastic"`</summary>Authentication Enabledがチェックされている場合に、Elasticsearchを認証するためのユーザー名を設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      パスワード
    </td>
    
    <td>
      <details><summary>`password=""`</summary>Authentication Enabledがチェックされている場合、Elasticsearchを認証するためのパスワードを設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      HTTP SSLが有効
    </td>
    
    <td>
      <details><summary>`httpSSLEnabled="false"`</summary>TLS/SSLを有効にするかどうかを設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      トラストストアの種類
    </td>
    
    <td>
      <details><summary>`truststoreType="pkcs12"`</summary>「HTTP SSL Enabled」がチェックされている場合に、トラストストアのタイプを設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      トラストストアのパス
    </td>
    
    <td>
      <details><summary>`truststorePath="/path/to/localhost.p12"`</summary>HTTP SSL Enabledがチェックされている場合、トラストストアファイルのパスを設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      トラストストアのパスワード
    </td>
    
    <td>
      <details><summary>`truststorePassword=""`</summary>HTTP SSL Enabledがチェックされている場合、トラストストアのパスワードを設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
    </td>
    
    <td>
      <a href="#elasticsearch-connections-settings" id="elasticsearch-connections-settings">elasticsearchの接続設定</a>
    </td>
    
    <td>
    </td>
  </tr>
  
  <tr>
    <td>
      Active
    </td>
    
    <td>
      <details><summary>`active=B "false"`</summary>必要に応じて、接続を有効化または無効化します。 Elasticsearch 7の設定のRemote Cluster Connection IDの設定で選択されている場合は、接続を無効にしないでください。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      接続ID
    </td>
    
    <td>
      <details><summary>`connectionId=""`</summary>接続のためのユニークなIDを設定します。 アクティブな場合、この接続はElasticsearch 7構成のRemote Cluster Connection IDプロパティで選択できるようになります。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
    </td>
    
    <td>
      <a href="#rest-client-settings" id="rest-client-settings">レストクライアント設定</a>
    </td>
    
    <td>
    </td>
  </tr>
  
  <tr>
    <td>
      ネットワークホストアドレス
    </td>
    
    <td>
      <details><summary>`networkHostAddresses="[http://localhost:9200]"`</summary>接続先のリモートHTTPホストを設定します。 これは、Liferay 7.3ではRESTクライアントの接続を構成するために必要です。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      Restクライアントのロガーレベル
    </td>
    
    <td>
      <details><summary>`RESTClientLoggerLevel="ERROR"`</summary>Elasticsearch RESTクライアントのロギングレベルを設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
    </td>
    
    <td>
      <a href="#transport-client-settings" id="transport-client-settings">トランスポートクライアントの設定（組み込み</a>
    </td>
    
    <td>
    </td>
  </tr>
  
  <tr>
    <td>
      動作モード
    </td>
    
    <td>
      <details><summary>`operationMode="EMBEDDED"`</summary>操作モードは「EMBEDDED」と「REMOTE」の2種類から選択できます。 REMOTEに設定すると、リモートのスタンドアロンElasticsearchクラスタに接続します。 EMBEDDEDに設定すると、Liferayを内部のElasticsearchインスタンスで起動します。 EMBEDDEDの動作モードは、実運用環境ではサポートされておらず、「開発モード」の機能と考えられます。</details>
    </td>
    
    <td>
      Liferay 7.2. Liferay 7.3で非推奨となり、 <em x-id="4">本番モードが有効</em>に置き換えられました。
    </td>
  </tr>
  
  <tr>
    <td>
      クラスター名
    </td>
    
    <td>
      <details><summary>`clusterName="LiferayElasticsearchCluster"`</summary>クラスタ名はLiferay 7.2のTransport Clientでのみ必要です。 統合するクラスタを宣言するためのString値を設定します。 接続がRESTクライアントを通じて管理されるLiferay 7.3では、このプロパティは開発モードのときに埋め込みクラスタの名前を付けるためにのみ使用されます。</details>
    </td>
    
    <td>
      Liferay 7.2-<br />7.3では、開発モードに適用されます。
    </td>
  </tr>
  
  <tr>
    <td>
      トランスポート アドレス
    </td>
    
    <td>
      <details><summary>`transportAddresses=["localhost:9300"]`</summary>接続するリモートのElasticsearchノードのアドレスをString値で設定します。 この値は、Operation ModeがRemoteに設定されている場合に必要となります（詳細は、 <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html">こちらの</a> を参照してください）。 任意の数のノードを指定することができます。</details>
    </td>
    
    <td>
      Liferay 7.2
    </td>
  </tr>
  
  <tr>
    <td>
      クライアント・トランスポート・スニフ
    </td>
    
    <td>
      <details><summary>`clientTransportSniff=B "true"`</summary>このブール値をtrueに設定すると、クラスタスニフィングが有効になり、クラスタ内の利用可能なデータノードを動的に検出します（詳細は <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html">こちらの</a> を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2
    </td>
  </tr>
  
  <tr>
    <td>
      クライアントのトランスポートは、クラスター名を無視します。
    </td>
    
    <td>
      <details><summary>`cclientTransportIgnoreClusterName=B "false"`</summary>接続されているノードのクラスター名の検証を無視するには、このブール値をtrueに設定します（詳細については、 <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html">こちら</a> を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2
    </td>
  </tr>
  
  <tr>
    <td>
      クライアント・トランスポート・Ping タイムアウト
    </td>
    
    <td>
      <details><summary>`clientTransportPingTimeout=""`</summary>クライアントノードがノードからのPing応答を待つ時間（秒単位）を設定します。 設定されていない場合は、デフォルトのElasticsearch <code>client.transport.ping_timeout</code> が使用されます。</details>
    </td>
    
    <td>
      Liferay 7.2
    </td>
  </tr>
  
  <tr>
    <td>
      クライアント・トランスポート・ノード のサンプル間隔
    </td>
    
    <td>
      <details><summary>`clientTransportNodesSamplerInterval=""`</summary>このString値を設定することで、リストアップされているノードや接続されているノードをサンプリング／Pingする頻度をクライアントノードに指示します（詳細は、 <a href="https://www.elastic.co/guide/en/elasticsearch/client/java-api/7.x/transport-client.html">こちらの</a> を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2
    </td>
  </tr>
  
  <tr>
    <td>
    </td>
    
    <td>
      <a href="#other-settings" id="other-settings">他の設定</a>
    </td>
    
    <td>
    </td>
  </tr>
  
  <tr>
    <td>
      リモートクラスター接続ID
    </td>
    
    <td>
      <details><summary>`remoteClusterConnectionId=`</summary>リモートのElasticsearchクラスタへの接続のための接続IDを選択します。 利用可能な接続は、「Elasticsearch Connections System Settings」エントリで定義されます。 この値が設定されていない場合は、Elasticsearch 7エントリの接続設定がリモートクラスタの接続に使用されます。</details>
    </td>
    
    <td>
      Liferay 7.3で <a href="../../liferay-enterprise-search/cross-cluster-replication/cross-cluster-replication.md">LES Cross-Cluster Replication</a>を使用する場合。
    </td>
  </tr>
  
  <tr>
    <td>
    </td>
    
    <td>
      <a href="#development-mode-settings" id="development-mode-settings">開発モード設定（組み込みおよびサイドカー）</a>
    </td>
    
    <td>
    </td>
  </tr>
  
  <tr>
    <td>
      追加設定
    </td>
    
    <td>
      <details><summary>`additionalConfigurations=""`</summary>埋め込み型Elasticsearchのカスタム設定用の文字列値をYML形式で設定します。 参照してください。 <a href="./advanced-configuration-of-the-liferay-elasticsearch-connector.md">Advanced Configuration of Liferay Elasticsearch Connector</a>.</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      Bootstrap Mlock All
    </td>
    
    <td>
      <details><summary>`bootstrapMlockAll="false"`</summary>ブーリアン値で、 <code>true</code>に設定すると、プロセスのアドレス空間をRAMにロックし、Elasticsearchのメモリがスワップアウトされないようにします（詳細は、 <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.x/setup-configuration-memory.html#bootstrap-memory_lock"></a>を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      埋め込みHttpポート
    </td>
    
    <td>
      <details><summary>`embeddedHttpPort="9201"`</summary>この設定は、EMBEDDEDモードにのみ適用されます。 実行モードがEMBEDDEDモードに設定されている時に作成される、埋め込みのElasticsearchノードのHTTPポートを設定します。</details>
    </td>
    
    <td>
      Liferay 7.2. Liferay 7.3.xでは非推奨となり、 <em x-id="4">Sidecar HTTP Port</em>に置き換えられました。
    </td>
  </tr>
  
  <tr>
    <td>
      Httpが有効
    </td>
    
    <td>
      <details><summary>`httpEnabled=B "true"`</summary>これをチェックすると、HTTPレイヤーが有効になります。 チェックされていない場合、RESTリクエストに直接対応することを意図していないノードでは、HTTPレイヤーが無効になります。</details>
    </td>
    
    <td>
      Liferay 7.1.xで非推奨となりました。
    </td>
  </tr>
  
  <tr>
    <td>
      Http CORS 許可オリジン
    </td>
    
    <td>
      <details><summary>`httpCORSAllowOrigin="/https?:\\/localhost(:[0-9])?/"`</summary>HTTP CORSが有効なときに許可する文字列の起源を設定します（詳細は、 <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-http.html#_settings">こちらの</a> を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      Http CORS 設定
    </td>
    
    <td>
      <details><summary>`httpCORSConfigurations =`</summary>HTTP CORSのカスタム設定の文字列値をYML形式（<code></code>）で設定します（詳細は、<a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-http.html#_settings">ここ</a>を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      Http CORSが有効
    </td>
    
    <td>
      <details><summary>`httpCORSEnabled=B "true"`</summary>クロスオリジンのリソース共有を無効にするには、このブール値を false に設定します。 <code>false</code>に設定すると、他のオリジンのブラウザはElasticsearchへのリクエストを行うことができません。 Elasticsearch HeadなどのWebフロントエンドツールが接続できない場合があります（詳細は、 <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-http.html#_settings">こちらの</a> を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      ネットワーク ホスト
    </td>
    
    <td>
      <details><summary>`networkHost=""`</summary>ノードがこのホスト名またはIPアドレスにバインドし、クラスタ内の他のノードにこのホストを公開（アドバタイズ）するよう指示するには、このString値を設定します。 これは、バインドホストとパブリッシュホストを同時に設定するショートカットです（詳しくは、 <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-network.html#common-network-settings">こちらの</a> を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      ネットワークバインドホスト
    </td>
    
    <td>
      <details><summary>`networkBindHost=""`</summary>ノードがリクエストをリッスンするためにバインドすべきネットワークインターフェースの文字列値を設定します（詳細は <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-network.html#advanced-network-settings">や</a> を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      ネットワーク公開ホスト
    </td>
    
    <td>
      <details><summary>`networkPublishHost=""`</summary>ノードがクラスタ内の他のノードにアドバタイズする1つのインターフェイスの文字列値を設定し、それらのノードが接続できるようにします（詳細は、 <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-network.html#advanced-network-settings">こちらの</a> を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      トランスポート Tcpポート
    </td>
    
    <td>
      <details><summary>`transportTcpPort=""`</summary>ノード間の通信にバインドするポートの文字列値を設定します。  単一の値または範囲を指定します（詳しくは <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-transport.html# **tcp** transport">や</a> を参照してください）。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      Zenディスカバリー・ユニキャスト・ホストポート
    </td>
    
    <td>
      <details><summary>`discoveryZenPingUnicastHostsPort="9300-9400"`</summary> <code>discovery.zen.ping.unicast.hostsの値を構築する際に使用するポートの範囲を文字列で設定</code>. ある範囲のポートにある複数のElasticsearchノードが、同じコンピュータでゴスルーターとして動作することができます（詳細は、 <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.x/modules-discovery-hosts-providers.html">こちらの</a> を参照してください）。 7.3で非推奨、直接の置き換えなし.</details>
    </td>
    
    <td>
      Liferay 7.2
    </td>
  </tr>
  
  <tr>
    <td>
    </td>
    
    <td>
      <a href="#sidecar-settings" id="sidecar-settings">サイドカー設定</a>
    </td>
    
    <td>
    </td>
  </tr>
  
  <tr>
    <td>
      ノード名
    </td>
    
    <td>
      <details><summary>`nodeName=`</summary>埋め込まれたElasticsearchサーバーのノードの名前を指定します。 リモートのElasticsearchサーバーのノード名は、そのElasticsearch.ymlの <code></code>で設定されます。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      Sidecarデバッグ
    </td>
    
    <td>
      <details><summary>`sidecarDebug=B "false"`</summary>sidecarプロセスのデバッグモードを有効にするには、これをtrueに設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      Sidecarデバッグ設定
    </td>
    
    <td>
      <details><summary>`sidecarDebugSettings="-agentlib:jdwp=transport=dt_socket,address=8001,server=y,suspend=y,quiet=y"`</summary>sidecarプロセスのデバッグに使用するJVMオプションを設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      Sidecarのハートビート監視間隔
    </td>
    
    <td>
      <details><summary>`sidecarHeartbeatInterval="10000"`</summary>sidecarプロセスの健全性を検知するためのハートビート間隔をミリ秒単位で設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      Sidecarホーム
    </td>
    
    <td>
      <details><summary>`sidecarHome="elasticsearch7"`</summary>sidecarプロセスの開始に使用するsidecarベースフォルダのパスを設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      Sidecar HTTPポート
    </td>
    
    <td>
      <details><summary>`sidecarHttpPort="9200"`</summary>この設定はLiferay 7.3でサイドカーにElasticsearchを使用している場合のみ適用されます。 サイドカーのElasticsearchノードのHTTPポートレンジを設定します。 AUTOに設定すると、9201～9300番台のポートを自動的に検出します。 設定されていない場合は、Embedded HTTP portの値（デフォルトでは、<code>9201</code> ）が使用されます。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      Sidecar JVMオプション
    </td>
    
    <td>
      <details><summary>`sidecarJVMOptions="-Xms1g\|-Xmx1g\|-XX: AlwaysPreTouch"`</summary>sidecarプロセスが使用するJVMオプションを設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
      Sidecarのシャットダウンタイムアウト
    </td>
    
    <td>
      <details><summary>`sidecarShutdownTimeout="10000"`</summary>sidecarプロセスが強制的にシャットダウンされるまでの時間をミリ秒単位で設定します。</details>
    </td>
    
    <td>
      Liferay 7.3+
    </td>
  </tr>
  
  <tr>
    <td>
    </td>
    
    <td>
      <a href="#advanced-settings" id="advanced-settings">アドバンスド・セッティング</a>
    </td>
    
    <td>
    </td>
  </tr>
  
  <tr>
    <td>
      追加インデックス設定
    </td>
    
    <td>
      <details><summary>`additionalIndexConfigurations=""`</summary>Liferayインデックスのカスタム設定のためのString値を、JSONまたはYML形式で設定します（詳細はElasticsearch Create Index APIを参照してください）。  参照してください。 <a href="./advanced-configuration-of-the-liferay-elasticsearch-connector.md">Advanced Configuration of Liferay Elasticsearch Connector</a>.</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      追加タイプマッピング
    </td>
    
    <td>
      <details><summary>`additionalTypeMappings =「」`</summary>セットのカスタムマッピングのための文字列値 <code>LiferayDocumentType</code>JSON形式で、（詳細はElasticsearch入れマッピングAPIを参照）： <a href="./advanced-configuration-of-the-liferay-elasticsearch-connector.md">のLiferay Elasticsearchコネクタの詳細設定</a>を参照。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      タイプマッピングを上書き
    </td>
    
    <td>
      <details><summary>`overrideTypeMappings=""`</summary>ここでの設定は、Liferayのデフォルトのタイプマッピングをオーバーライドします。 これは高度な機能であり、厳密に必要な場合にのみ使用する必要があります。 この値を設定すると、LiferayソースコードでLiferayドキュメントタイプを定義するために使用されるデフォルトマッピング（例えば、 <code>liferay-type-mappings.json</code>）は完全に無視されますので、修正するセグメントだけでなく、マッピング定義全体をこのプロパティに含めてください。</details>
    </td>
    
    <td>
      Liferay 7.2+
    </td>
  </tr>
  
  <tr>
    <td>
      プロキシホスト
    </td>
    
    <td>
      <details><summary>`proxyHost=""`</summary>クライアント接続用のプロキシホストを設定します。</details>
    </td>
    
    <td>
      Liferay DXP 7.3 FP1 /SP1およびLiferay Portal GA7
    </td>
  </tr>
  
  <tr>
    <td>
      プロキシポート
    </td>
    
    <td>
      <details><summary>`proxyPort="0"`</summary>クライアント接続用のプロキシポートを設定します。</details>
    </td>
    
    <td>
      Liferay DXP 7.3 FP1 /SP1およびLiferay Portal GA7
    </td>
  </tr>
  
  <tr>
    <td>
      プロキシユーザー名
    </td>
    
    <td>
      <details><summary>`proxyUserName=""`</summary>プロキシ接続時のプロキシユーザー名を設定します。</details>
    </td>
    
    <td>
      Liferay DXP 7.3 FP1 /SP1およびLiferay Portal GA7
    </td>
  </tr>
  
  <tr>
    <td>
      プロキシパスワード
    </td>
    
    <td>
      <details><summary>`proxyPassword=""`</summary>プロキシに接続するためのパスワードを設定します。</details>
    </td>
    
    <td>
      Liferay DXP 7.3 FP1 /SP1およびLiferay Portal GA7
    </td>
  </tr>
</table>

<a name="関連トピック" />

## 関連トピック
- [7.3の検索の新機能](../../getting-started/whats-new-in-search-for-73.md)
- [Elasticsearchの保護](securing-elasticsearch.md)
- [Elasticsearchへの接続](connecting-to-elasticsearch.md)
- [Liferay DXP Elasticsearchコネクタ。テクニカルデータシート（KBリファレンス）](https://help.liferay.com/hc/en-us/articles/360046478452)
- [Liferay DXPのElasticsearchとの互換性について（KBリファレンス）](https://help.liferay.com/hc/en-us/articles/360051492032)
