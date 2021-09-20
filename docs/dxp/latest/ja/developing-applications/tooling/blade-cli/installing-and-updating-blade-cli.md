# ブレードCLIのインストールと更新

ブレードCLIは、コマンドラインインターフェイスまたはグラフィカルインストーラーを使用してインストールできます。 プロキシの背後にいる場合は、インストールすると、プロキシを介して動作するように構成できます。 Blade CLIは、新しいバージョンがリリースされたときに通知し、CLIからいつでもアップグレードできます。

## Blade CLIのインストール

Blade CLIのインストール方法に関係なく、Java JDKの実装が最初にインストールされていることを確認してください。

### CLIからのインストール

LinuxおよびMacオペレーティングシステム用のブレードCLIのインストールは、1行のコマンドです。

``` bash
curl -L https://raw.githubusercontent.com/liferay/liferay-blade-cli/master/cli/installers/local | sh
```

<!-- is there a success message after running the above script to let someone know they 'did it right'? -->

インストールが完了したら、新しいコマンドラインを開いて `ブレード`と入力します` 。 エラーが見つからないコマンドがある場合は、<a href="./troubleshooting-blade-cli.md#the-blade-command-is-not-available-in-my-cli">パス</a>に<code>ブレード`コマンドを追加する必要があります。

### グラフィカルインストーラーからのインストール

インストーラーに慣れている場合は、最新の [Liferay Project SDKインストーラー](https://sourceforge.net/projects/lportal/files/Liferay%20IDE/) をダウンロードして実行してください。

1.  Javaランタイムを選択します。 これは自動検出されます。

    ![Liferayワークスペースインストーラーは、インストールされているJDKを自動検出します。](./installing-and-updating-blade-cli/images/01.png)

2.  紹介の後、 *Next*クリックします。

3.  インストール中に [Liferayワークスペース](../../tooling/liferay-workspace/what-is-liferay-workspace.md) 初期化できます。 Liferayワークスペースは、Liferayプロジェクトを管理するシステム上の環境（一連のフォルダー）です。 今すぐ初期化する場合は、その場所を設定します。

    ![Liferayワークスペースは、Liferayプロジェクトを管理するファイルシステム上の一連のフォルダです。](./installing-and-updating-blade-cli/images/02.png)

4.  Liferayワークスペースを初期化する場合は、次にLiferay DXPまたはCommunity Editionを選択する必要があります。

    ![インストーラーのデフォルトはCommunity Editionですが、DXPを選択できます。](./installing-and-updating-blade-cli/images/03.png)

    製品タイプを選択し、 *次へ*をクリックします。

5.  *次* をクリックして、ブレードCLIをインストールします。

Windows環境では、 `ブレード` コマンドがパスに自動的に追加されます（Windowsの場合、またはMacおよびLinuxで `bash` または `zsh` を使用している場合）。 詳細については、 [Blade CLI](./troubleshooting-blade-cli.md) トラブルシューティングを参照してください。

## プロキシの構成

プロキシサーバーの背後にいる場合は、CLIから構成できます。

``` bash
jpm command --jvmargs "-Dhttp(s).proxyHost=[your proxy host] -Dhttp(s).proxyPort=[your proxy port]" jpm
```

これで、BladeはインターネットからLiferayサンプルおよびテンプレートにアクセスするときにプロキシサーバーを使用できます。

## ブレードCLIの更新

Blade CLIを使用すると、更新があるかどうかが確認されます。 更新が利用可能な場合、コマンドが完了するとこのメッセージが表示されます。

``` bash
Update available 3.9.1 -> 3.9.2
Run `blade update` to install
```

ブレードを更新するには、次のコマンドを実行します。

``` bash
blade update
```

最先端の機能が必要な場合は、オプションで、より頻繁に更新されるスナップショットバージョンに切り替えることができます。

``` bash
blade update -s
```

スナップショットのバージョンは不安定になる可能性があるため、自己責任で使用してください。

## ブレードコマンド

以下は、使用可能なブレードコマンドの概要です。 `ブレードヘルプ [command]`入力すると、CLIでヘルプを利用できます。

| コマンド                  | 説明                                                                                                                                                                                                                                                                                                                                                            |
| --------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `convert`             | Plugins SDKプラグインプロジェクトをGradleワークスペースプロジェクトに変換します。                                                                                                                                                                                                                                                                                                             |
| `create`              | 利用可能なテンプレートから新しいLiferayプロジェクトを作成します。                                                                                                                                                                                                                                                                                                                          |
| `deploy`              | プロジェクトをビルドしてLiferayにデプロイします。                                                                                                                                                                                                                                                                                                                                  |
| `extension install`   | 拡張機能をブレードCLIにインストールします。                                                                                                                                                                                                                                                                                                                                       |
| `extension uninstall` | Blade CLIから拡張機能をアンインストールします。                                                                                                                                                                                                                                                                                                                                  |
| `gw`                  | 検出された場合、Gradleラッパーを使用してGradleコマンドを実行します（例： `blade gw tasks`）。                                                                                                                                                                                                                                                                                                 |
| `help`                | ブレードCLIのコマンドに関する情報を提供します。                                                                                                                                                                                                                                                                                                                                     |
| `init`                | 新しいLiferayワークスペースを初期化します。                                                                                                                                                                                                                                                                                                                                     |
| `samples`             | サンプルプロジェクトを生成します。                                                                                                                                                                                                                                                                                                                                             |
| `server init`         | Liferayワークスペースの `gradle.properties` ファイルで構成されたLiferayサーバーを初期化します。 `liferay.workspace.bundle.url` プロパティを設定して、サーバーを初期化するように構成します。                                                                                                                                                                                                                               |
| `server start`        | Liferayサーバーをバックグラウンドで起動します。 `-d` フラグを追加して、サーバーをデバッグモードで起動できます。 `-p` タグを追加してカスタムリモートデバッグポート（デフォルトはTomcatの場合は `8000` 、WildFlyの場合は `8787` ）を追加するか、ブール `-s` タグを追加して、デバッグモードをカスタマイズできます。デバッガが接続されるまで、起動したサーバーを一時停止します。                                                                                                                                             |
| `server stop`         | Liferayサーバーを停止します。                                                                                                                                                                                                                                                                                                                                            |
| `server run`          | フォアグラウンドでLiferayサーバーを起動します。 詳細については、 `server start` プロパティを参照してください。                                                                                                                                                                                                                                                                                           |
| `sh`                  | Liferay DXPに接続し、Gogoコマンドを実行して、出力を返します。 たとえば、 `blade sh lb` は、Gogoシェルを使用するすべてのバンドルをリストします。                                                                                                                                                                                                                                                                     |
| `update`              | Blade CLIを最新バージョンに更新します。                                                                                                                                                                                                                                                                                                                                      |
| `upgradeProps`        | 古い `portal-ext.properties` と新しくインストールされた7.xサーバーを分析して、OSGi構成ファイルに移動されたか、製品から削除されたプロパティを表示します。                                                                                                                                                                                                                                                                  |
| `watch`               | デプロイされたプロジェクトへの変更を監視し、変更が検出されると自動的に再デプロイします。 このコマンドは、変更が検出されるたびにプロジェクトを再ビルドしてインストールにコピーするのではなく、参照としてランタイムにインストールします。 つまり、Liferay DXPはプロジェクトのキャッシュコピーを作成しないため、プロジェクトのファイルに加えられた変更をすぐに確認できます。 `watch` タスクをキャンセルすると、モジュールは自動的にアンインストールされます。 `ブレードデプロイ-w` コマンドは、 `ブレードウォッチ`と同様に機能しますが、変更が検出されるたびにプロジェクトを手動で再コンパイルしてデプロイします。 これは遅くなりますが、再起動の間、デプロイされたプロジェクトを保持します。 |
| `version`             | ブレードCLIのバージョン情報を表示します。                                                                                                                                                                                                                                                                                                                                        |
