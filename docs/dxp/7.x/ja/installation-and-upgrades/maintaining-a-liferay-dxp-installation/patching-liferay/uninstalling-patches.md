# パッチのアンインストール

パッチをインストールした後、パッチの準備ができていないと判断し、インストールを以前のパッチレベルに復元することを決定する場合があります。 ここでは、次の方法を学びます。

  - パッチをアンインストールする
  - すべてのパッチを一度に元に戻します（アンインストールします）。

## パッチのアンインストール

パッチをアンインストールして、インストールを希望のパッチレベルに復元できます。

1.  `パッチ` フォルダーから不要なパッチを削除します。

2.  `./patching-tool.sh install` コマンドを実行して、残りの `patch` フォルダー内の残りのパッチが定義するパッチレベルにインストールを復元します。

これで、DXPインストールには、パッチで指定されているパッチレベルが適用されます。

## すべてのパッチを元に戻す

パッチのすべてまたはほとんどを削除する場合は、パッチツールの `revert` コマンドを使用する方が簡単な場合があります。 `revert` コマンドは、DXPインストールからすべてのパッチを削除し、元のインストールバージョン（GA1など）に戻します。

1.  すべてのパッチを削除します。

    ``` bash
    ./patching-tool.sh revert
    ```

    すべてのパッチが `パッチ` フォルダーから削除されます。

2.  [適用可能なパッチ](./installing-patches.md)をインストールすることで、インストールを希望のパッチレベルまで引き上げてください。

<!-- end list -->

``` tip::
   It's helpful to store your patches in a convenient location in case you want to restore your installation to a particular patch level. You can always download fix packs again from the `Customer Portal <https://customer.liferay.com/downloads>`_.
```

これで、パッチをアンインストールして元に戻すことにより、DXPインストールを復元する方法がわかりました。

## 追加情報

  - [パッチのインストール](./installing-patches.md)
  - [パッチタイプについて](./understanding-patch-types.md)
  - [パッチを適用したインストールのスリム化](./advanced-patching/slimming-down-patched-installations.md)
