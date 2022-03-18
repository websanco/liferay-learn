# 以前のDXPバージョンにパッチを適用

```{toctree}
:maxdepth: 3

patching-dxp-7-3-and-earlier/understanding-patch-types-for-dxp-7-3-and-earlier.md
patching-dxp-7-3-and-earlier/installing-patches-for-dxp-7-3-and-earlier.md
patching-dxp-7-3-and-earlier/advanced-patching-for-dxp-7-2.md
```

```{note}
Liferay DXP 7.3 SP3+はアップデートを用いたローリングリリースモデルを採用しています。 アップデートを適用する場合は、[Updating Liferay](./updating-liferay.md)を参照してください。 すべてのホットフィックスはパッチです。 ホットフィックスを適用する場合は、このまま読み進めてください。
```

Liferayの修正プログラムがリリースされたら、DXPインスタンスを最新の状態にしておきましょう。 Liferayは修正を集約し、 _パッチ_と呼ばれるZIPファイルでエンタープライズサブスクライバーが利用できるようにします。

## パッチの種類

パッチにはいくつかの種類があり、それらは異なる目的を果たします。

* **フィックスパック：** 最新の問題を解決します。
* **セキュリティフィックスパック：** 最新のセキュリティ問題に直ちに対処します。

**すべてのDXPバージョンで**

* **ホットフィックス：**ビジネスクリティカルなDXP問題を迅速に修正するようにお客様から依頼されました。

**DXP 7.3 SP3以前のバージョンの場合**

* **フィックスパック：** 最新の問題を解決します。
* **セキュリティフィックスパック：** 最新のセキュリティ問題に直ちに対処します。 なお、Liferay DXP 7.3 SP3+はセキュリティーアップデートを使用しています。 詳細は、 [Updating Liferay](./updating-liferay.md) を参照してください。
* **サービスパック：** より多くのテストを必要とする大きな修正を組み込みます。 Service Packのリリースには、完全なDXP Service PackTomcatバンドルも含まれています。

[Understanding Patch Types](./patching-dxp-7-3-and-earlier/understanding-patch-types-for-dxp-7-3-and-earlier.md) 上記のパッチオプションについて説明します。

## Installing Patches

必要なパッチが見つかったら、パッチングツールを使って適用します。 [パッチのインストール](./patching-dxp-7-3-and-earlier/installing-patches-for-dxp-7-3-and-earlier.md) では、DXPに安全かつ包括的にパッチを適用するための基本的な手順を説明しています。

## パッチングツールの構成

[`patching-tool.sh auto-discovery` コマンド](./reference/configuring-the-patching-tool.md) は、Tomcatバンドルおよび一般的なアプリサーバー構成に対してツールを自動的に構成します。 パッチングツールを手動で設定して、DXPインストールのバリエーションを処理することもできます。

## その他のパッチ適用に関するトピック

パッチを適用すると、サポートリクエストでパッチ情報を提出したり、不要になったパッチをアンインストールする必要が出てきます。

* [パッチ情報の取得](./reference/getting-patch-information.md)
* [パッチのアンインストール](./reference/uninstalling-patches.md)

## DXP 7.2へのパッチ適用

次のトピックでは、DXP 7.2でパッチを管理するためのベストプラクティスを紹介します。

* [パッチを適用したインストールのスリム化](./patching-dxp-7-3-and-earlier/advanced-patching-for-dxp-7-2/slimming-down-patched-installations.md)
* [スリムバンドルの使用](./patching-dxp-7-3-and-earlier/advanced-patching-for-dxp-7-2/using-slim-bundles.md)
* [パッチとカスタムプラグイン間の衝突の処理](./patching-dxp-7-3-and-earlier/advanced-patching-for-dxp-7-2/custom-code-and-patch-compatibility.md)

パッチ適用の概要を理解したところで、使用可能な [パッチタイプ](./patching-dxp-7-3-and-earlier/understanding-patch-types-for-dxp-7-3-and-earlier.md) について学習します。 その後、 [パッチのインストール](./patching-dxp-7-3-and-earlier/installing-patches-for-dxp-7-3-and-earlier.md)に続いて、パッチを適用する準備が整います。
