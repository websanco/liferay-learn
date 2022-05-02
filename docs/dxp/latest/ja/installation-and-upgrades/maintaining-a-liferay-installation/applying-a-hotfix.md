# ホットフィックスの適用

> サブスクライバー

ホットフィックスとは、お客様が確認したDXPの重大な問題に対処するために、アップデートの間（DXP 7.3 SP3以前のバージョンではフィックスパックの間）に配信される修正または修正のコレクションです。  [ヘルプセンターチケット](https://help.liferay.com/hc) からホットフィックスでリクエストできます。 ホットフィックスを受け取った後、パッチングツールを使ってホットフィックスをインストールすることができます。

```{warning}
ホットフィックスを適用する前に、**必ず**データベースとインストールを[バックアップ](./backing-up.md)してください。
```

```{note}
DXPをDockerコンテナで動作させている場合は、 [DXPをDockerでパッチする](./installing-liferay/using-liferay-docker-images/patching-dxp-in-docker.md)の手順に従ってホットフィックスを適用してください。
```

## ホットフィックスをリクエストする

ここでは、ホットフィックスのリクエスト方法について説明します。

1. [ヘルプセンター](https://help.liferay.com/hc) に移動します。
1. チケットを作成.

Liferayサポートチームはお客様と協力して、問題が製品にあるかどうかを判断し、製品の意図しない動作に対する修正を提供します。

## パッチツールの構成

パッチツールはホットフィックスを適用します。 設定方法は次の通りです。

1. DXPをアプリケーションサーバー上に `.war` としてデプロイした場合は、ホットフィックスを適用するために `.war` を一時的な場所に解凍してください。
1. [Liferay Home](../reference/liferay-home.md) で `patching-tool.sh` パッチングツールスクリプトを見つけるか、DXPインストールにパッチングツールがない場合はパッチングツールをインストールします。

    ***DXP Tomcat Bundle:** `[Liferay Home]/patching-tool/`
    ***DXPアプリのサーバーをインストール:** サーバーホストに [パッチングツール をインストール](./reference/installing-the-patching-tool.md)。

1. DXPのインストール用にパッチングツールを設定します。

    **DXP Tomcat バンドル:**[`auto-discovery` コマンド](./reference/configuring-the-patching-tool.md)を自動的に実行するようにツールを設定します。

    ```bash
    ./patching-tool.sh auto-discovery
    ```

    **DXPアプリサーバーのインストール:**[パッチングツールを手動で構成します。](./reference/configuring-the-patching-tool.md)、これにはパッチングツールの `war.path` プロパティ（例： `default.properties`）を解凍したDXP `..war` の場所に設定することも含まれます。

## ホットフィックスのインストール

1. [ヘルプセンター](https://help.liferay.com/hc) チケットから`patching-tool/patches` フォルダーにパッチをダウンロードします。---パッチを解凍しないでください。
1. アプリケーションサーバーをシャットダウンします。

    理由:

    * Unixスタイルのシステムでは、通常、実行中のファイルを置き換えることができますが、古いファイルはメモリに常駐します。
    * Windowsシステムでは、使用中のファイルはロックされており、パッチを適用できません。

1. `patching-tool` フォルダーからパッチングツールの `install` コマンドを実行して、パッチをインストールします。

    ```bash
    cd patching-tool
    ./patching-tool.sh install
    ```

1. `info` コマンドを実行し、現在インストールされているパッチの情報を確認して、インストールされているパッチを確認します。

    ```bash
    ./patching-tool.sh info
    ```

1. 全てのDXPキャッシュを消去します。

    `[Liferay Home]/osgi/state` フォルダーを削除します。

    ```bash
    cd [Liferay Home]
    rm -rf osgi/state
    ```

    `[Liferay Home]/work` フォルダーを空にします。

    ```bash
    rm -rf work/*
    ```

    アプリケーションサーバーのキャッシュを削除します。 キャッシュの場所については、アプリケーションサーバーのベンダーのドキュメントを参照してください。

    ```{note}
    モジュールの変更が内部のみである場合、変更はOSGiフレームワークからは見えず、モジュールはインストールされたままであり、モジュールの状態は保持されます。 次のDXP起動の前にOSGiバンドルの状態情報をクリアすると、そのようなモジュールが適切な状態で再インストールされます。
    ```

1. DXP をアプリケーションサーバーに再インストールする場合は、パッチを適用したDXPアプリケーションを [一時的な場所](#preparing-to-patch-dxp-on-an-application-server) から`.war`ファイルに圧縮して戻し、ファイルをアプリケーションサーバーにコピーします。 アプリケーションサーバーの[DXPのインストール手順](../installing-liferay/installing-liferay-on-an-application-server.md)を参照してください。
1. アプリケーションサーバーを再度起動します。

　 DXPのインストールにホットフィックスを適用しました。

## 追加情報

* [DockerでDXPにパッチを適用する](../installing-liferay/using-liferay-docker-images/patching-dxp-in-docker.md)
* [パッチ情報の取得](./reference/getting-patch-information.md)
* [パッチのアンインストール](./reference/uninstalling-patches.md)