# クラスタリンクの構成

Cluster Linkを有効にすると、分散キャッシュがアクティブになります。 キャッシュは、同時に実行されている複数のLiferay DXPノードに分散されます。 Cluster Linkは [Ehcache](http://www.ehcache.org) レプリケーションを使用し
 。 Ehcacheグローバル設定は [`portal.properties` ファイル](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/portal.properties.html#Ehcache)ます。</p> 

デフォルトでは、Liferayはノード間でキャッシュされたエンティティをコピーしません。 たとえば、エンティティが削除または変更された場合、クラスターリンクは他のノードに *削除* メッセージを送信して、ローカルキャッシュ内のこのエンティティを無効にします。 別のノードでそのエンティティを要求すると、キャッシュ *ミス*ます。次に、エンティティがデータベースから取得され、ローカルキャッシュに入れられます。 1つのノードのローカルキャッシュに追加されたエンティティは、他のノードのローカルキャッシュにはコピーされません。 エンティティがキャッシュされていないノードで新しいエンティティを取得しようとすると、キャッシュ *ミス* になります。 ミスにより、ノードがトリガーされてデータベースからエンティティが取得され、ローカルキャッシュに格納されます。

![Liferay DXPのキャッシュアルゴリズムは非常に効率的です。](./configuring-cluster-link/images/01.png)

DXPのクラスタリングは、ネットワークとクラスタノードの場所に応じて、さまざまな方法で構成できます。 この記事では、クラスターリンクに関する次のトピックについて説明します。

**内容：**

  - [クラスタリンクの有効化](#enabling-cluster-link)
  - [クラスタリンクの構成](#configuring-cluster-link)
  - [まとめ](#conclusion)



## クラスタリンクの有効化

Cluster Linkを有効にするには、次の [ポータルプロパティ](../../reference/portal-properties.md) を `portal-ext.properties` ファイルに追加します。



``` properties
cluster.link.enabled=true
```


[Cluster Linkポータルプロパティ](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/portal.properties.html#Cluster%20Link) は、ニーズに合わせてオーバーライドできるデフォルト構成を提供します。

デフォルトの多くは、実際のアドレスの代わりに `localhost`使用します。 ただし、一部の構成では、 `localhost` は、ホストの実際のアドレスではなく、内部ループバックネットワーク（`127.0.0.1` または `:: 1`）にバインドされています。 それでもこの設定が必要な場合は、DXPにこのプロパティを使用して実際のアドレスを自動検出させることができます。



``` properties
cluster.link.autodetect.address=www.google.com:80
```


サーバーから接続可能な他のホストに接続するように設定します。 デフォルトではGoogleを指しますが、サーバーがファイアウォールの背後にある場合は機能しない可能性があります。 各ホストの実際のアドレスを使用する場合、自動検出アドレスを設定する必要はありません。

Cluster Linkは [JGroups](http://www.jgroups.org) 依存し、ノードが通信するためのAPIを提供します。 以下のことが可能です：

  - クラスター内のすべてのノードにメッセージを送信する
  - 特定のノードにメッセージを送信する
  - メソッドを呼び出し、すべて、一部、または特定のノードから値を取得する
  - メンバーシップを検出し、ノードが参加または離脱したときに通知する

Cluster Linkには、ノード間の1対多タイプの通信を提供する拡張アルゴリズムが含まれています。 これはJGroupsのUDPマルチキャストでデフォルトで実装されていますが、ユニキャストとTCPも利用できます。



## クラスタリンクの構成

Cluster Linkを有効にすると、DXPのデフォルトのクラスタリング構成が有効になります。 この設定では、*UDP上のIPマルチキャスト*を定義します。 独自のノードでマルチキャストを使用できない場合（たとえば、地理的に離れている場合やファイアウォールによって分離されている場合）、代わりにユニキャスト実装を構成できます。 詳細については、 [TCPを介したユニキャストの設定](./configuring-unicast-over-tcp.md) を参照してください。



### UDPを介したマルチキャストの使用

DXPは、JGroups</a> チャネルの2つのグループを使用して、UDPを介したマルチキャストを実装します。コントロールグループとトランスポートグループです。 [チャネルのプロパティ](https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html#Cluster%20Link)をカスタマイズする場合は、次のポータルプロパティを `portal-ext.properties`追加することにより、カスタマイズできます。</p> 



``` properties
cluster.link.channel.name.control=[your control channel name]
cluster.link.channel.properties.control=[your control channel properties]
```


チャネルのプロパティについては、 [JGroupsのドキュメント](http://www.jgroups.org/manual4/index.html#protlist) を参照してください。 デフォルトの構成では、そこで説明されている設定を持つ多くのプロパティが設定されます。

マルチキャストは、ネットワーク上のすべてのデバイスにブロードキャストします。 同じネットワーク上のクラスター環境は、デフォルトで互いに通信します。 それらの間で送信されるメッセージと情報（たとえば、スケジュールされたタスク）は、意図しない結果につながる可能性があります。 ネットワーク上で論理的または物理的にそれらを分離するか、各クラスターの `portal-ext.properties` を構成して、 [マルチキャストグループアドレスとポート値の異なるセットを使用するようにして、そのようなクラスター環境を分離します](https://docs.liferay.com/portal/7.3-latest/propertiesdoc/portal.properties.html#Multicast)。

JGroupsはデフォルトで `localhost` を使用してバインドアドレスを自動的に設定します。 ただし、一部の構成では、 `localhost` は、ホストの実際のアドレスではなく、内部ループバックネットワーク（`127.0.0.1` または `:: 1`）にバインドされています。 DXPの `cluster.link.autodetect.address` ポータルプロパティが接続可能なサーバーを指している限り、DXPはそのサーバーを使用してホストの実際のアドレスを自動的に検出します。 デフォルトの設定は次のとおりです。



``` properties
cluster.link.autodetect.address=www.google.com:80
```


サーバーがファイアウォールの内側にある場合、Googleへの連絡が機能しない場合があります。

バインドアドレスのホストアドレスを自動的に検出する代わりに、 `portal-ext.properties` ファイルでバインドアドレスを手動で設定できます。

1.  `cluster.link.autodetect.address` プロパティを空の値に設定して、アドレスの自動検出を無効にします。 
   
   

    ``` properties
    cluster.link.autodetect.address=
    ```


2.  次のプロパティをホストのIPアドレスに設定します。 
   
   

    ``` properties
    cluster.link.bind.addr["cluster-link-control"]=[place your IP address or host name here]
    cluster.link.bind.addr["cluster-link-udp"]=[place your IP address or host name here]
    ```


ネットワーク構成により、TCPを介したマルチキャストを使用できない場合があります。これらの状況については、「 [TCPを介したユニキャストの構成](./configuring-unicast-over-tcp.md) を参照してください。 これらのメソッドはすべてJGroupsによって提供されることに注意してください。



## まとめ

クラスターを構成したら、開始できます。 ログファイルメッセージにクラスターの名前が表示されます（例： `cluster=liferay-channel-control`）：



``` bash
-------------------------------------------------------------------
GMS: address=oz-52865, cluster=liferay-channel-control, physical address=192.168.1.10:50643
-------------------------------------------------------------------
```


クラスタはCluster Linkを使用しています。



## 次のステップ

負荷をかけた状態でDXPクラスタをテストし、システムの最適化を調査するのが最善です。 サイトで最も使用されているエンティティを検討し、キャッシュ設定を適切に調整します。 キャッシュの構成については、 [キャッシュ構成](https://help.liferay.com/hc/en-us/articles/360035581451-Introduction-to-Cache-Configuration)を参照してください。



## 追加情報

  - [TCPを介したユニキャストの構成](./configuring-unicast-over-tcp.md)
  - [高可用性のクラスタリング](./clustering-for-high-availability.md)
