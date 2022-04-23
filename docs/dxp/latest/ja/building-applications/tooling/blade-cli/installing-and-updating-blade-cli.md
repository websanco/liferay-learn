# Blade CLIのインストールと更新

Blade CLIは、コマンドラインインターフェイスまたはグラフィカルインストーラーを使用してインストールできます。 プロキシの背後にいる場合は、インストールすると、プロキシを介して動作するように構成できます。 Blade CLIは、新しいバージョンがリリースされたときに通知し、CLIからいつでもアップグレードできます。

<a name="installing-blade-cli" />

## CLIのインストール

Blade CLIのインストール方法に関係なく、Java JDKの実装が最初にインストールされていることを確認してください。

<a name="installing-from-the-cli" />

### CLIからインストールする

LinuxおよびMacオペレーティングシステム用のBlade CLIのインストールは、1行のコマンドです。

```bash
curl -L https://raw.githubusercontent.com/liferay/liferay-blade-cli/master/cli/installers/local | sh
```

インストールが完了すると、`Bladeが正常にインストールされました`というメッセージが表示されます。 新しいコマンドラインを開き、`blade`と入力します。 エラーが見つからないコマンドがある場合は、 [パス](./troubleshooting-blade-cli.md#the-blade-command-is-not-available-in-my-cli) に`blade`コマンドを追加</0>する必要があります。

<a name="installing-from-the-graphical-installer" />

### グラフィカルインストーラーからインストールする

インストーラーに慣れている場合は、最新の [Liferay Project SDKインストーラー](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/) をダウンロードして実行してください。

1. Javaランタイムを選択します。 これは自動検出されます。

   ![Liferay Workspaceインストーラーは、インストールされているJDKを自動検出します。](./installing-and-updating-blade-cli/images/01.png)

1. イントロダクションの後、 ［**Next**］ をクリックします。

1. インストール中に [Liferay Workspace](../liferay-workspace/what-is-liferay-workspace.md) 初期化できます。 Liferay Workspaceは、Liferayプロジェクトを管理するシステム上の環境（一連のフォルダー）です。 今すぐ初期化する場合は、その場所を設定します。

   ![Liferay Workspaceは、Liferayプロジェクトを管理するファイルシステム上の一連のフォルダです。](./installing-and-updating-blade-cli/images/02.png)

1. Liferay Workspaceを初期化する場合は、次にLiferay DXPまたはCommunity Editionを選択する必要があります。

   ![インストーラーのデフォルトはCommunity Editionですが、DXPを選択できます。](./installing-and-updating-blade-cli/images/03.png)

   製品タイプを選択し、 ［**Next**］ をクリックします。

1. ［**Next**］ をクリックして、Blade CLIをインストールします。

Windows環境では、`blade` コマンドがパスに自動的に追加されます（Windowsの場合、またはMacおよびLinuxで `bash` または `zsh` を使用している場合）。 詳細は、 [Blade CLI](./troubleshooting-blade-cli.md) トラブルシューティングを参照してください。

<a name="configuring-a-proxy" />

## プロキシの構成

プロキシサーバーの背後にいる場合は、CLIから構成できます。

```bash
jpm command --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" jpm
```

これで、BladeはインターネットからLiferayサンプルおよびテンプレートにアクセスするときにプロキシサーバーを使用できます。

<a name="updating-blade-cli" />

## Blade CLIの更新

Blade CLIを使用すると、更新があるかどうかが確認されます。 更新が利用可能な場合、コマンドが完了すると次のメッセージが表示されます。

```bash
Update available 3.9.1 -> 3.9.2
Run `blade update` to install
```

Bladeを更新するには、次のコマンドを実行します。

```bash
blade update
```

最先端の機能が必要な場合は、オプションで、より頻繁に更新されるスナップショットバージョンに切り替えることができます。

```bash
blade update -s
```

スナップショットのバージョンは不安定になる可能性があるため、自己責任で使用してください。

<a name="blade-commands" />

## Bladeコマンド

以下は、使用可能なBladeコマンドの概要です。 `blade help [command]`と入力すると、CLIでヘルプを利用できます。

| コマンド                  | 説明                                                                                                                                                                                                                                                                                                                                                                 |
| --------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| `変換`                  | Plugins SDKプラグインプロジェクトをGradleワークスペースプロジェクトに変換します。                                                                                                                                                                                                                                                                                                                  |
| `作成`                  | 利用可能なテンプレートから新しいLiferayプロジェクトを作成します。                                                                                                                                                                                                                                                                                                                               |
| `デプロイする`              | プロジェクトをビルドしてLiferayにデプロイします。                                                                                                                                                                                                                                                                                                                                       |
| `extension install`   | 拡張機能をBlade CLIにインストールします。                                                                                                                                                                                                                                                                                                                                          |
| `extension uninstall` | Blade CLIから拡張機能をアンインストールします。                                                                                                                                                                                                                                                                                                                                       |
| `gw`                  | 検出された場合、Gradleラッパーを使用してGradleコマンドを実行します（例： `blade gw tasks`）。                                                                                                                                                                                                                                                                                                      |
| `help`                | Blade CLIのコマンドに関する情報を提供します。                                                                                                                                                                                                                                                                                                                                        |
| `init`                | 新しいLiferay Workspaceを初期化します。                                                                                                                                                                                                                                                                                                                                       |
| `samples`             | サンプルプロジェクトを生成します。                                                                                                                                                                                                                                                                                                                                                  |
| `server init`         | Liferay Workspaceの `gradle.properties` ファイルで構成されたLiferayサーバーを初期化します。 `liferay.workspace.bundle.url` プロパティを設定して、サーバーを初期化するように構成します。                                                                                                                                                                                                                                 |
| `server start`        | Liferayサーバーをバックグラウンドで起動します。 `-d` フラグを追加して、サーバーをデバッグモードで起動できます。 デバッグモードをカスタマイズするには、`-p`タグを追加してカスタムリモートデバッグポートを設定したり（デフォルトはTomcatの場合は`8000`、WildFlyの場合は`8787`）、ブール値の`-s`タグを追加してデバッガーが接続されるまで起動したサーバーを一時停止するかどうかを設定したりします。                                                                                                                                            |
| `server stop`         | Liferayサーバーを停止します。                                                                                                                                                                                                                                                                                                                                                 |
| `server run`          | フォアグラウンドでLiferayサーバーを起動します。 詳細は、 `server start` プロパティを参照してください。                                                                                                                                                                                                                                                                                                    |
| `sh`                  | Liferay DXPに接続し、Gogoコマンドを実行して、出力を返します。 たとえば、 `blade sh lb` は、Gogoシェルを使用するすべてのバンドルをリストします。                                                                                                                                                                                                                                                                          |
| `更新`                  | Blade CLIを最新バージョンに更新します。                                                                                                                                                                                                                                                                                                                                           |
| `upgradeProps`        | 古い `portal-ext.properties` と新しくインストールされた7.xサーバーを分析して、OSGi構成ファイルに移動されたか、製品から削除されたプロパティを表示します。                                                                                                                                                                                                                                                                       |
| `watch`               | デプロイされたプロジェクトへの変更を監視し、変更が検出されると自動的に再デプロイします。 このコマンドは、変更が検出されるたびにプロジェクトを再ビルドしてインストールにコピーするのではなく、参照としてランタイムにインストールします。 つまり、Liferay DXPはプロジェクトのキャッシュコピーを作成しないため、プロジェクトのファイルに加えられた変更をすぐに確認できます。 `watch` タスクをキャンセルすると、モジュールは自動的にアンインストールされます。 `blade deploy -w`コマンドは`blade watch`と同様に機能しますが、変更が検出されるたびにプロジェクトを手動で再コンパイルしてデプロイします。 これは遅くなりますが、再起動の間、デプロイされたプロジェクトを保持します。 |
| `バージョン`               | Blade CLIのバージョン情報を表示します。                                                                                                                                                                                                                                                                                                                                           |
