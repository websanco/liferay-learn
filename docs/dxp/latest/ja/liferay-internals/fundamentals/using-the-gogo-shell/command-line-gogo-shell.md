# コマンドラインGogo シェル

開発環境にいる場合は、コマンドラインからローカルでモジュールフレームワークを操作できます。

```{warning}
Gogoシェルは、開発環境のコマンドラインからのみ実行してください。 本番環境では、 [Using the Gogo Shell](../using-the-gogo-shell.md) で説明されているように、コントロールパネルでGogoシェルを実行してください。
```

## 前提条件

コマンドラインでGogoシェルを使用するには、サーバーが開発者モードで動作している必要があります。 サーバーの開発者モードを有効にするには、[デベロッパー・スタジオ](../../../building-applications/tooling/developer-studio.md)で有効にするか、または以下の[ポータルプロパティ](../../../installation-and-upgrades/reference/portal-properties.md)を設定します。

``` properties
include-and-override=portal-developer.properties
```

## コマンドの実行

[ブレードCLI](../../../building-applications/tooling/blade-cli/installing-and-updating-blade-cli.md)またはtelnetセッションを使用してGogoシェルコマンドを実行できます。

### ブレードCLIの使用

ブレードCLIを使って個々のコマンドを実行することができます。

``` bash
blade sh [Gogo shell command]
blade sh [another Gogo shell command]
...
```

### Telnetセッションの利用

`telnet`セッションでコマンドを実行することができます。

1.  セッションを開きます。

    ``` bash
    telnet localhost 11311
    ```

2.  Gogoのシェルコマンドを実行します。

3.  コマンドの実行が終わったら、 `disconnect`コマンドを実行してセッションを終了します。

<!-- end list -->

```{warning}
以下のコマンドは実行しないでください。 モジュールのフレームワークが停止してしまいます。

`close`

`exit`

`shutdown`
```

## まとめ

コマンドラインからGogoシェルを実行する方法がわかったので、使用可能な[Gogoシェルコマンド](./gogo-shell-commands.md)を調べます。 本番環境でGogoシェルを使用する必要がある場合は、[Using the Gogo Shell](../using-the-gogo-shell.md)を参照してください。
