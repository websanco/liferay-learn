# TCPを介したユニキャストの構成

ネットワーク構成またはクラスターノード間の地理的距離により、 [UDPマルチキャストクラスタリング](./configuring-cluster-link.md#using-multicast-over-udp)を使用できない場合は、TCPユニキャストを構成できます。 ファイアウォールがノードを分離している場合、またはノードが地理的に異なる場所にある場合は、これを使用する必要があります。

**内容：**

  - [ユニキャスト構成](#unicast-configurations)
  - [代替発見プロトコル](#alternative-discovery-protocols)
  - [異なる制御およびトランスポートチャネルポートの使用](#using-different-control-and-transport-channel-ports)

## ユニキャスト構成

ユニキャストを構成するには、次の手順を使用します。

1.  JNodesバインドアドレスパラメーターを各ノードのアプリサーバーのJVMに追加します。

    ``` bash
    -Djgroups.bind_addr=[place your IP address or host name here]
    ```

    ノードのIPアドレスまたはホスト名を使用します。

2.  ノードが互いを見つけるために使用する検出プロトコルを選択します。 プロトコルの選択肢は次のとおりです。

      - `TCPPing`
      - `JDBCPing`
      - `S3_Ping`
      - `Rackspace_Ping`

    どれを選択すればよいかわからない場合は、TCPPingを使用してください。 これらの残りのステップでは、TCPPingを使用します。 他の詳細については、 [代替発見プロトコル](#alternative-discovery-protocols) を参照してください。
    
     <!-- the craziness in the next step is probably an example of something that Brian Chan would want to see get improved in the product. We should bring this up w/ the core team or with Brian Chan himself to see his thoughts. jrhoun -->

3.  `$LIFERAY.HOME/osgi/marketplace/Liferay Foundation - Liferay Portal - Impl.lpkg/com.liferay.Portal.cluster.multiple-[version].jar/lib/jgroups-[version].Final.jar/tcp.xml`から`tcp.xml`ファイルをDXPにアクセスできる場所に展開します。 jar/lib/jgroups-[version].Final.jar/tcp.xml</code>を、DXPウェブアプリケーションの`WEB-INF/classes`フォルダ内の`jgroups</0>というフォルダなど、DXPでアクセス可能な場所に移動します。</p>

<p spaces-before="8">WEB-INF/classes/jgroups/tcp.xml</p></li>
<li><p spaces-before="0"> <code>tcp.xml` ファイルで、TCPバインドポートをノードの未使用ポートに設定します。 以下に例を示します。

    ``` xml
    <TCP bind_port="7800"/>
    ```

5.  `tcp.xml` ファイルで、ノードのIPアドレスとそのノードの未使用ポートを指定して、各ノードをTCPPingで検出できるようにします。 前のステップを基にして、以下は `<TCPPing>` 要素の例です。

    ``` xml
    <TCP bind_port="7800"/>
    <TCPPING async_discovery="true"
        initial_hosts="192.168.224.154[7800],192.168.224.155[7800]"
        port_range="0"/>
    ```

    **初期ホストについて：**

      - 最初のホスト値がすべてのノードに対応していることを確認してください。 TCP XMLファイルまたはJVM引数で `initial_hosts` が指定されていない場合、 `localhost` が初期ホストです。

      - TCP XMLファイルで初期ホストを指定する代わりに、次のようなJVM引数を使用してアプリサーバーにそれらを指定することもできます。
        
            -Djgroups.tcpping.initial_hosts=192.168.224.154[7800],192.168.224.155[7800]

6.  `tcp.xml` ファイルを各ノードの同じ場所にコピーして、TCPバインドポートが各ノードの未使用のポートに設定されていることを確認します。 たとえば、IPアドレス `192.168.224.155`のノードで、次のようにTCPPingを構成します。

    ``` xml
    <TCP bind_port="7800"/>
    <TCPPING async_discovery="true"
        initial_hosts="192.168.224.154[7800],192.168.224.155[7800]"
        port_range="0"/>
    ```

7.  変更 [クラスタリンクプロパティ](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/portal.properties.html#Cluster%20Link) 各ノードの内の [`portal-ext.properties` ファイル](../../reference/portal-properties.md) 各クラスタリンクチャネルのためのTCPのXMLファイルにクラスタリンクおよびポイントを有効にします。

    ``` properties
    cluster.link.enabled=true
    cluster.link.channel.properties.control=/jgroups/tcp.xml
    cluster.link.channel.properties.transport.0=/jgroups/tcp.xml
    ```

上記のJGroups構成は、通常、Unicast over TCPに必要なすべての構成です。 ただし、非常に特定の場合には、 *（および場合のみ）* のクラスタ・ノードが複数のネットワークにまたがって展開され、次いで `external_addr` TCPトランスポートパラメータは、ファイアウォールの外部（パブリックIP）アドレスに、各ホストに設定されなければなりません。 この種の構成は通常、ノードが地理的に離れている場合にのみ必要です。 これを設定することにより、別々のネットワークにデプロイされたクラスター化されたノード（たとえば、異なるファイアウォールによって分離されたノード）は互いに通信できます。 この構成は、システムのセキュリティ監査でフラグが立てられる場合があります。 詳細については、 [JGroupsドキュメント](http://www.jgroups.org/manual4/index.html#_transport_protocols) を参照してください。

```{note}
The `singleton_name` TCP attribute was deprecated in JGroups v4.0.0 and has therefore been removed since Liferay DXP 7.2 SP1 and Liferay Portal CE GA2 which use JGroups v 4.1.1-Final.
```

これで、TCPクラスタリングを介したユニキャストがセットアップされました\！

## 代替発見プロトコル

TCP Pingは、大部分のユースケースに適合するために使用できるデフォルトの検出プロトコルです。 ただし、以下で説明する他の検出プロトコルを使用することもできます。

### JDBC Ping

TCP Pingを使用してクラスターメンバーを検出する代わりに、すべてのノードがアクセスできる中央データベースを使用して、お互いを見つけやすくすることができます。 クラスターメンバーは自分のメンバーを書き込み、このデータベースから他のメンバーのアドレスを読み取ります。 この構成を有効にするには、 [ユニキャスト構成](#unicast-configurations) 手順で参照されている `TCPPING` タグを、対応する `JDBC_PING` タグに置き換えます。

``` xml
<JDBC_PING
    connection_url="[place the URL to your database here]"
    connection_username="[place your user name here]"
    connection_password="[place your password here]"
    connection_driver="[place your driver name here]"/>
```

JDBC接続値の例については、 [データベーステンプレート](../../reference/database-templates.md)を参照してください。 JDBC Pingの詳細については、 [JGroups Documentation](http://www.jgroups.org/manual4/index.html#DiscoveryProtocols)を参照してください。

### S3 ping

Amazon S3 Pingは、AmazonのEC2クラウドサービスで実行されているサーバーに使用できます。 各ノードは小さなファイルをS3バケットにアップロードし、他のすべてのノードはこのバケットからファイルを読み取って他のノードを検出します。 ノードが離れると、そのファイルは削除されます。

S3 Pingを構成するには、 [ユニキャスト構成](#unicast-configurations) ステップの `TCPPING` タグを、対応する `S3_PING` タグに置き換えます。

``` xml
<S3_PING
    secret_access_key="[SECRETKEY]"
    access_key="[ACCESSKEY]"
    location="ControlBucket"/>
```

上記のパラメーターの値としてAmazonキーを指定します。 S3 Pingの詳細については、 [JGroups Documentation](http://www.jgroups.org/manual4/index.html#_s3_ping)を参照してください。

### その他のping

JGroupsは、Rackspace Ping、BPing、File Pingなど、クラスターメンバーがお互いを発見するための他の手段を提供します。 これらの検出方法については、 [JGroups Documentation](http://www.jgroups.org/manual4/index.html#DiscoveryProtocols) を参照してください。

## 異なる制御およびトランスポートチャネルポートの使用

制御チャネルとトランスポートチャネルは、異なるポートを使用するように構成できます。 個別の制御チャネルポートとトランスポートチャネルポートを使用すると、制御トラフィックとトランスポートトラフィックを監視でき、情報を分離して問題を診断できます。

次の手順では、Unicast over TCPPingを使用してアプローチを示します。

1.  各ノードでアプリサーバーのJVMにパラメーターを追加します。

    ``` bash
    -Djgroups.bind_addr=[node_ip_address]
    ```

2.  `$LIFERAY.HOME/osgi/marketplace/Liferay Foundation - Liferay Portal - Impl.lpkg/com.liferay.Portal.cluster.multiple-[version].jar/lib/jgroups-[version].Final.jar/tcp.xml`から`tcp.xml`ファイルをDXPにアクセスできる場所に展開します。 jar/lib/jgroups-[version].Final.jar/tcp.xml</code>を、DXPウェブアプリケーションの`WEB-INF/classes`フォルダ内の`jgroups</0>というフォルダなど、DXPでアクセス可能な場所に移動します。</p></li>
<li><p spaces-before="0"> 同じ場所に <code>tcp.xml` コピーを作成し、両方のファイルの名前を変更して、1つを制御チャネル用に、もう1つをトランスポートチャネル用に指定します。 たとえば、次のファイル名を使用できます。

      - `tcp-control.xml`
      - `tcp-transport.xml`

4.  ノードの [`portal-ext.properties` ファイル](../../reference/portal-properties.md) の [Cluster Linkプロパティ](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/portal.properties.html#Cluster%20Link) を変更してCluster Linkを有効にし、各Cluster LinkチャネルのTCP XMLファイルをポイントします。

    ``` properties
    cluster.link.enabled=true
    cluster.link.channel.properties.control=/jgroups/tcp-control.xml
    cluster.link.channel.properties.transport.0=/jgroups/tcp-transport.xml
    ```

5.  各 `tcp-*。xml` ファイルのTCPおよび検出プロトコルタグ（たとえば、TCPPingを使用している場合は `TCPPing` タグ）を変更して、各ノードのIPアドレスとバインドポートを考慮します。

垂直方向にクラスタリングしている場合（つまり、同じ物理システムまたは仮想システムで複数のサーバーを実行している場合）、すべてのチャネルは、ディスカバリ通信に未使用の一意のバインドポートを使用する必要があります。 各 `tcp-*。xml` ファイルで、TCPタグの `bind_port` 属性を一意の未使用ポートに割り当てます。

たとえば、最初の2つのノードがこれらのバインドポートを割り当てる場合があります。

| ノード  | プロパティファイル           | ポート    |
|:---- |:------------------- |:------ |
| ノード1 | `tcp-control.xml`   | `7800` |
| ノード1 | `tcp-transport.xml` | `7801` |
| ノード2 | `tcp-control.xml`   | `7802` |
| ノード2 | `tcp-transport.xml` | `7803` |

次に、同じシステム（つまり、同じIPアドレス）で実行されているノードのバインドポートを使用するTCPおよびTCPPing要素の例を示します。

**ノード1 `tcp-control.xml`**

``` xml
<TCP bind_port="7800"/>
<TCPPING async_discovery="true"
    initial_hosts="192.168.224.154[7800],192.168.224.154[7802]"
    port_range="0"/>
```

**ノード1 `tcp-transport.xml`**

``` xml
<TCP bind_port="7801"/>
<TCPPING async_discovery="true"
    initial_hosts="192.168.224.154[7801],192.168.224.154[7803]"
    port_range="0"/>
```

**ノード2 `tcp-control.xml`**

``` xml
<TCP bind_port="7802"/>
<TCPPING async_discovery="true"
    initial_hosts="192.168.224.154[7800],192.168.224.154[7802]"
    port_range="0"/>
```

**ノード2 `tcp-transport.xml`**

``` xml
<TCP bind_port="7803"/>
<TCPPING async_discovery="true"
    initial_hosts="192.168.224.154[7801],192.168.224.154[7803]"
    port_range="0"/>
```
キャッシュできるエンティティを追加した場合、またはシステムのキャッシュ構成を調整したい場合は、モジュールを使用して行うことができます。 <!--TODO Link to caching articles. jhinkey -->

## 追加情報

  - [クラスタリンクの構成](./configuring-cluster-link.md)
  - [高可用性のクラスタリング](./clustering-for-high-availability.md)
