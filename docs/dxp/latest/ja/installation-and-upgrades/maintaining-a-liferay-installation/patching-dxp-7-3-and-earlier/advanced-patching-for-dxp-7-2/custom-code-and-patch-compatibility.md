# カスタムコードとパッチの互換性


  - [カスタムパッチのパッチレベル情報の保存](#storing-patch-level-information-for-custom-patches)
  - [パッチレベルの違いの比較](#comparing-patch-level-differences)
  - [パッチとカスタムプラグイン間の衝突の処理](#handling-collisions-between-patches-and-custom-plugins)

<!-- end list -->

```{important}
これらのカスタマイズとパッチ適用の手順は、Liferay DXP 7.2にのみ適用されます。 DXP 7.3以降には適用されません。
```

## カスタムパッチのパッチレベル情報の保存

Patching Toolの `store` および `diff` コマンドは、Liferay DXPパッチとカスタムDXPパッチを含む、パッチ間の違いを処理します。 `store` コマンドは、パッチソースコードのパッチレベルファイルを書き込みます。 `diff` コマンドは、パッチ間の違いを出力します。

`./patching-tool.sh store` コマンドは、 `diff` コマンドで使用されるパッチレベル情報を管理します。 パッチには、パッチレベルを格納し、 `diff` コマンドの使用可能な情報を準備するためのソースコードが含まれている必要があります。 `store` コマンドオプションは次のとおりです。

  - `add`：パッチレベルを `パッチ` フォルダーに保存します。
  - `info`：保存されているパッチレベルを構成するパッチのリストを出力します。
  - `rm`：以前に保存されたパッチレベル情報を削除します。
  - `update`：パッチレベル情報を追加または更新します。

詳しい使用方法については、 `./patching-tool.sh help store`実行してください。

## パッチレベルの比較

`./patching-tool.sh diff` コマンドは、2つのパッチレベルの違いを出力します。 少なくとも1つの保存されたパッチレベルが使用可能である必要があります。 このコマンドは、出力をフィルタリングするためのオプションを受け入れます。

  - `衝突`：デプロイされたプラグインと衝突する変更されたファイルをリストします。
  - `ファイル`：変更されたファイルをリストします。
  - `修正された問題`：パッチが修正する（問題追跡システムからの）LPS/LPE問題をリストします。
  - `HTML`：フィルタリングオプション（のいずれかと一緒にこれを指定する`ソース`、 `ファイル`、又は `固定の問題`）とパッチレベルの後に、HTMLファイル（の違い書き込みに`<stored-name-1>-<stored-name-2>-[type]-diff.html`）は、 `diffs` フォルダーにあります。 追加は緑色で、削除は赤色で表示されます。
  - `source`：2つのパッチレベル間のソースコードの違いを示します。

詳しい使用方法については、 `./patching-tool.sh help diff`実行してください。

## パッチとカスタムプラグイン間の衝突の処理


``` bash
./patching-tool.sh list-collisions
```

これは、次のdiffコマンドのエイリアスです。

``` bash
./patching-tool.sh diff collisions files _base
```


特定のパッチを削除した場合、または何らかの衝突が発生した場合は、 `-force` 引数を使用して、パッチツールに現在使用可能なパッチを強制的にインストールさせます。

例：

``` bash
./patching-tool.sh profile_name install -force
```

カスタムコードとカスタムパッチを他のDXPパッチと統合する方法をマスターしました。

## 追加情報

  - [パッチのインストール](../installing-patches-for-dxp-7-3-and-earlier.md)
  - [パッチツールのインストール](../../reference/installing-the-patching-tool.md)
  - [パッチツールの構成](../../reference/configuring-the-patching-tool.md)
