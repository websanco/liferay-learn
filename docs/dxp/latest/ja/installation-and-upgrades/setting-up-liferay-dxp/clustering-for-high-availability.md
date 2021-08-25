# 高可用性のクラスタリング

```{toctree}
:maxdepth: 3

clustering-for-high-availability/example-creating-a-simple-dxp-cluster.md
clustering-for-high-availability/database-configuration-for-cluster-nodes.md
clustering-for-high-availability/configuring-cluster-link.md
clustering-for-high-availability/configuring-unicast-over-tcp.md
```

Liferay DXPは、最小から最大までのすべてのWebサイトに対応するように拡張できます。 アウトオブボックスで、単一のサーバー環境に最適な構成になっています。 トラフィックが多いと予想される場合、またはサイトの全体的なフォールトトレランスを向上させたい場合は、DXPのクラスタリングを検討してください。
![Liferay DXPは、必要に応じて大規模な設置に対応できるように設計されています。](./clustering-for-high-availability/images/01.png) <!-- I know this image is an oldie but I find the diagram presented in the "example creating a dxp cluster" article a lot clearer. -->

Liferay DXPは、複数のマシンのクラスター（水平クラスター）または単一のマシン上の複数のVMのクラスター（垂直クラスター）、または任意の組み合わせでうまく機能します。

## クラスタリング要件

これは、効果的に機能するDXPクラスターを作成するために必要なものです。

1.  Liferay DXP [は個別のアプリケーションサーバーノードに](../../installing_liferay.md) をインストール

2.  すべてのノードにアクセスできる [データベースまたはデータベースクラスター](./database-configuration-for-cluster-nodes.md)

3.  すべてのノードにアクセス可能な [ファイルストア](../../../system-administration/file-storage/configuring-file-storage.md)

4.  オプションでクラスター化されている [検索エンジン（DXPの外部で実行）](../../../using-search/installing-and-upgrading-a-search-engine/introduction-to-installing-a-search-engine.md)

5.  [すべてのノード間で通信とキャッシュレプリケーションを有効にするように構成されたクラスターリンク](./configuring-cluster-link.md)

DXPクラスターを構成したら、それにアプリケーションをデプロイし、ユーザーエクスペリエンスを引き続き改善できます。 詳細については、「 [クラスタ化インストールの維持](../../maintaining-a-liferay-dxp-installation/maintaining-clustered-installations/maintaining-clustered-installations.md) 」を参照してください。

## 次のステップ

例としてクラスタを作成することは、DXPクラスタリングを理解するための優れた最初のステップです。 詳細については、「 [例：単純なDXPクラスター](./example-creating-a-simple-dxp-cluster.md) 作成」から始めてください。 次に、上記の [クラスタリング要件](#clustering-requirements) 対処します。

``` note::
   This documentation describes DXP-specific cluster configuration without getting into specific implementations of third party software, such as Java EE application servers, HTTP servers, and load balancers. Please consult the documentation for those components to configure them. Before creating a DXP cluster, make sure your OS is not defining the hostname of your system to the local network at 127.0.0.1.
```
