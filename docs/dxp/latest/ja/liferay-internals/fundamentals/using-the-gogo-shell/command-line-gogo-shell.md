# コマンドラインGogo シェル

開発環境にいる場合は、コマンドラインからローカルでモジュールフレームワークを操作できます。

```{warning}
Gogoシェルは、開発環境のコマンドラインからのみ実行してください。 本番環境では、 [Gogo シェルの使用](../using-the-gogo-shell.md) で説明されているように、コントロールパネルでGogoシェルを実行してください。
```

<a name="前提条件" />

## 前提条件

コマンドラインでGogoシェルを使用するには、サーバーが開発者モードで動作している必要があります。 サーバーの開発者モードを有効にするには、[デベロッパー・スタジオ](../../../developing-applications/tooling/developer-studio.md)で有効にするか、または以下の [ポータルプロパティ](../../../installation-and-upgrades/reference/portal-properties.md) を設定します。

```properties
include-and-override=portal-developer.properties
```

<a name="コマンドの実行" />

## コマンドの実行

[Blade CLI](../../../developing-applications/tooling/blade-cli/installing-and-updating-blade-cli.md)またはtelnetセッションを使用してGogoシェルコマンドを実行できます。

### Blade CLIの使用

Blade CLIを使って個々のコマンドを実行することができます。

```bash
blade sh ［Gogo shell command］
blade sh ［another Gogo shell command］
...
```

### Telnetセッションの利用

`telnet`セッションでコマンドを実行することができます。

1. セッションを開きます。

    ```bash
    telnet localhost 11311
    ```

1. Gogoのシェルコマンドを実行します。

1. コマンドの実行が終わったら、 `disconnect`コマンドを実行してセッションを終了します。

```{warning}
以下のコマンドは実行しないでください。 モジュールのフレームワークが停止してしまいます。

`close`

`exit`

`shutdown`
```

<a name="まとめ" />

## まとめ

コマンドラインからGogoシェルを実行する方法がわかったので、使用可能な[Gogoシェルコマンド](./gogo-shell-commands.md)を調べます。 本番環境でGogoシェルを使用する必要がある場合は、 [Gogo シェルの使用](../using-the-gogo-shell.md) を参照してください。