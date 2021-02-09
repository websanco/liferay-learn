# コマンドラインツール

Liferay DXPのコマンドラインインターフェイス（CLI）は、DXP Cloudサービスの使用と管理に役立つツールです。 CLIは、アプリケーションの作成、管理、およびスケーリングに使用できます。 次のアクションを参照してください。

  - [CLIのインストール](#installing-the-cli)
  - [CLIをv2からv3にアップグレードする](#uninstalling-version-2x)
  - [CLIリモートの変更](#changing-the-cli-remote)
  - [サービスログの表示](#showing-the-service-logs)
  - [サービスインスタンスの数の変更](#changing-the-number-of-service-instances)
  - [プロジェクトまたはサービスの一覧表示](#listing-projects-or-services)
  - [サービスを再開する](#restarting-a-service)
  - [サービスのシェルへのアクセス](#accessing-a-services-shell)
  - [CLIのアンインストール](#uninstalling-the-cli)

## CLIのインストール

### \*nixシステム

ターミナルを開き、次のコマンドを実行します。

``` bash
curl https://cdn.liferay.cloud/lcp/stable/latest/install.sh -fsSL | bash
```

パーミッションエラーが発生した場合、同じコマンドを `sudo`で実行してみてください。

### Windowsシステム

[Windowsインストーラー](https://cdn.liferay.cloud/lcp/stable/latest/lcp-install.exe) の最新バージョンをダウンロードし、ウィザードの手順に従います。

## CLIをv2からv3にアップグレードする

コマンドラインインターフェイスツールの最新バージョンはバージョン3です。 技術的な制限により、CLIツールはコマンド `lcp update`を使用して更新できません。 レガシーバージョンは、バージョン3を使用する前に、最初に以下の手順を使用してアンインストールする必要があります。 これにより、同じバイナリ名との競合が防止されます。

> **重要：** 以下の手順は、CLIバージョンアンインストールに特異的である **2** とされている **ない** と同じ [アンCLI](#uninstalling-the-cli)。

### MacOSまたはLinux

1.  ターミナルを開いて `lcp uninstall`を実行します。
2.  コマンド `lcp`実行して、アンインストールが完了したことを確認します。

### Windows

Windows 7および8の場合は、 `コントロールパネル > プログラムの追加または削除`に移動します。 Windows 10では、 `コントロールパネル&` \> `プログラム` \> `プログラムをアンインストールします`に行きます。

1.  コントロールパネル\> プログラム\> プログラムをアンインストールしますに行きます。
2.  `lcp amd64-installer-0.3`を選択します。
3.  Click *アンインストール*とクリックしてから、確認します。
4.  `lcp` または `lcp.exe`コマンドを実行して、アンインストールが完了したことを確認してください。

バージョン3をインストールする前に、CLIバージョン2がアンインストールされていることを確認してください。

## CLIリモートの変更

CLIを介してDXP Cloudサービスにアクセスするには、CLIが適切なDXP Cloudをポイントしている必要があります。 DXP CloudのリモートURLは `liferay.cloud`です。 CLIのリモートを一覧表示するには、次のコマンドを実行します。

``` shell
lcp remote
```

デフォルトのリモートを変更するには、次の手順に従います。

1.  必要に応じて新しいリモートを追加します。

    ``` shell
    lcp remote set <remote-alias> <remote-url>
    ```

2.  次のコマンドを実行して、デフォルトのリモートを設定します。

    ``` shell
    lcp remote default <remote-alias>
    ```

または、次のコマンドでリモートインラインを指定します。

``` shell
lcp shell --project <project-id> --service <service-id> --remote <remote-alias>
```

## サービスログの表示

`lcp log` コマンドは、DXP Cloudプロジェクトのサービスログを表示します。 一般的な例を次に示します。

プロジェクト全体のログを確認します。

``` shell
lcp log --project <projectID>
```

プロジェクト内の特定のサービスのログを表示します。

``` shell
lcp log --project <projectID> --service <serviceID>
```

特定の期間のログを表示します。

``` shell
lcp log --project <projectID> --since <timestamp>
```

または、サービスの完全なURLを `lcp log`渡して、サービスのログを表示します。

``` shell
lcp log --url <serviceID>-<projectID>.lfr.cloud
```

## サービスインスタンスの数の変更

インスタンスの数を変更するには、 `lcp scale` コマンドを使用します。 一般的な例を次に示します。

``` shell
lcp scale --project <projectID> <instances>
```

プロジェクト内の特定のサービスのインスタンスをスケーリングします。

``` shell
lcp scale --project <projectID> --service <serviceID> <instances>
```

または、完全なURLを `lcp restart`渡してインスタンスをスケーリングします。

``` shell
lcp scale --url <serviceID>-<projectID>.lfr.cloud <instances>
```

## プロジェクトまたはサービスの一覧表示

`lcp list` コマンドを使用して、プロジェクトとサービスを一覧表示します。 一般的な例を次に示します。

所有または共同編集しているプロジェクト、サービス、インスタンスの全リストを確認します。

``` shell
lcp list
```

プロジェクトのサービスをリストします。

``` shell
lcp list --project <projectID>
```

プロジェクト内の特定のサービスを確認します。

``` shell
lcp list --project <projectID> --service <serviceID>
```

または、完全なURLを`lcp list`に渡してサービスを確認します。

``` shell
lcp list --url <serviceID>-<projectID>.lfr.cloud
```

## サービスを再開する

`lcp restart` コマンドを使用して、サービスを再起動します。 一般的な例を次に示します。

プロジェクトのサービスを再起動します。

``` shell
lcp restart --project <projectID>
```

プロジェクトの特定のサービスを再起動します。

``` shell
lcp restart --project <projectID> --service <serviceID>
```

または、完全なURLを `lcp restart`渡してサービスを再起動します。

``` shell
lcp restart --url <serviceID>-<projectID>.lfr.cloud
```

## サービスのシェルへのアクセス

サービスコンテナのシェルにアクセスするには、次のコマンドを実行します。

``` shell
莢莢
```

これにより、コンテナ内のすべてのサービスがリストされ、管理者はアクセスするサービスを選択できます。

または、サービスのプロジェクトIDとサービスIDを `lcp shell` コマンドに追加して、特定のサービスのコンテナのシェルにアクセスします。

``` shell
lcp shell --project <projectID> --service <serviceID>
```

## ドキュメントを開く

CLIを使用して、DXP Cloudのドキュメントをいつでも開くことができます。

``` shell
lcp docs
```

## CLIのアンインストール

MacおよびLinuxの場合、次のコマンドを実行します。

``` bash
curl https://cdn.liferay.cloud/lcp/stable/latest/uninstall.sh -fsSL | bash
```

Windows 7および8の場合は、 `コントロールパネル > プログラムの追加または削除`に移動します。 Windows 10では、 `コントロールパネル&` \> `プログラム` \> `プログラムをアンインストールします`に行きます。 プログラムのリストから「LCP CLI」を見つけて選択し、「アンインストール」をクリックします。 ウィザードの手順に従います。
