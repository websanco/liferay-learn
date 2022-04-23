# Blade CLIのトラブルシューティング

Blade CLIが期待どおりに動作しない場合は、ここで回答を見つけることができます。

<a name="the-blade-command-is-not-available-in-my-cli" />

## ブレードコマンドがCLIで使用できない

インストールスクリプトを使用した場合は、`blade`実行可能ファイルへのパスをシステムの `$PATH` 変数に追加する必要があります。

**macOS**

```bash
echo 'export PATH="$PATH:$HOME/Library/PackageManager/bin"' >> ~/.bash_profile
```

**Linux**

```bash
echo 'export PATH="$PATH:$HOME/jpm/bin"' >> ~/.bash_profile
```

`bash`以外のシェルを使用している場合は、手動で `blade` 実行可能ファイルへのパスを追加する必要があります。

<a name="i-cant-update-blade" />

## ブレードを更新できない

新しいインストーラーをダウンロードし、それを既存のバージョンの上にインストールして、Blade CLIを更新しようとした可能性があります。 この操作はサポートされておらず、マシンに2つの個別のインストールがある可能性があります。 グローバルフォルダ内にレガシーBladeファイルが存在する場合は、削除する必要があります。

管理アカウントを使用して、 `［グローバルフォルダー］/jpm4j` フォルダーに移動し、次の2つのファイルを削除します。

- `/bin/blade`
- `/commands/blade`

これで、ユーザーのホームフォルダの新しいインストールが認識され、CLIで使用できるようになりました。
