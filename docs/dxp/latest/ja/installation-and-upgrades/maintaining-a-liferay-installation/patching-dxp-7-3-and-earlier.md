# DXP 7.3 ライフレイのパッチ

```{toctree}
:maxdepth: 3

patching-dxp-7-3-and-earlier/understanding-patch-types-for-dxp-7-3-and-earlier.md
patching-dxp-7-3-and-earlier/installing-patches-for-dxp-7-3-and-earlier.md
patching-dxp-7-3-and-earlier/updating-liferay-portal-ce.md
patching-dxp-7-3-and-earlier/advanced-patching-for-dxp-7-2.md
```

DXPインスタンスを開発して保守するときは、最新の修正でインスタンスを更新する必要があります。 Liferayは修正を集約し、 *パッチ*と呼ばれるZIPファイルでエンタープライズサブスクライバーが利用できるようにします。

## パッチの種類

パッチにはいくつかの種類があり、それらは異なる目的を果たします。

  - **フィックスパック：** 最新の問題を解決します。
  - **セキュリティフィックスパック：** 最新のセキュリティ問題に直ちに対処します。
  - **ホットフィックス：** ビジネスクリティカルなDXP問題を迅速に修正するために顧客から要求されました。
  - **サービスパック：** より多くのテストを必要とする大きな修正を組み込みます。 Service Packリリースは、Service Packで構築され、TomcatにバンドルされているDXPも提供します。

[パッチタイプ](./patching-dxp-7-3-and-earlier/understanding-patch-types-for-dxp-7-3-and-earlier.md) は、すべてのパッチオプションを説明します。

## パッチのインストール

必要なパッチを入手したら、Liferayのパッチツールを使用してパッチを適用できます。 [パッチのインストール](./patching-dxp-7-3-and-earlier/installing-patches-for-dxp-7-3-and-earlier.md) では、DXPに安全かつ包括的にパッチを適用するための基本的な手順を説明しています。

## パッチツールの構成

[`patching-tool.sh auto-discovery` コマンド](./reference/configuring-the-patching-tool.md) は、Tomcatバンドルおよび一般的なアプリサーバー構成に対してツールを自動的に構成します。 パッチツールを手動で設定して、DXPインストールのバリエーションを処理することもできます。

## 高度なパッチ

定期的にパッチを適用したり、新しいDXP環境を追加したり、DXPソースコードを操作したりするとき、パッチを管理するための最良の方法を知りたいと思うでしょう。 パッチ適用に関する高度なトピックには次のものがあります。

  - [パッチ情報の取得](./reference/getting-patch-information.md)
  - [パッチのアンインストール](./reference/uninstalling-patches.md)
  - [パッチを適用したインストールのスリム化](./patching-dxp-7-3-and-earlier/advanced-patching-for-dxp-7-2/slimming-down-patched-installations.md)
  - [スリムバンドルの使用](./patching-dxp-7-3-and-earlier/advanced-patching-for-dxp-7-2/using-slim-bundles.md)
  - [パッチとカスタムプラグイン間の衝突の処理](./patching-dxp-7-3-and-earlier/advanced-patching-for-dxp-7-2/custom-code-and-patch-compatibility.md)

パッチ適用の概要を理解したところで、使用可能な [パッチタイプ](./patching-dxp-7-3-and-earlier/understanding-patch-types-for-dxp-7-3-and-earlier.md) について学習します。 その後、 [パッチのインストール](./patching-dxp-7-3-and-earlier/installing-patches-for-dxp-7-3-and-earlier.md)続いて、パッチを適用する準備が整います。