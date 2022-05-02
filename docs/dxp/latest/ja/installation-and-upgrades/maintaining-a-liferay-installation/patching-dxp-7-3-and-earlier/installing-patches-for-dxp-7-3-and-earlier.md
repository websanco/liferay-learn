# パッチのインストール

> サブスクライバー

[パッチングツール](../reference/installing-the-patching-tool.md)は、Liferay DXP 7.3 SP3以前のバージョンのフィックスパック（セキュリティフィックスパック、サービスパックを含む）を適用します。 また、任意のDXPバージョンのホットフィックスも適用されます。

```{note}
Liferay DXP 7.3 SP3+のアップデート(またはセキュリティアップデート)を適用する場合は、 [Liferayのアップデート](../updating-liferay.md) を参照してください。
```

DXPバンドルとDXPアプリケーションサーバーのインストールのパッチ適用手順は似ています。 DXPバンドルには事前設定されたパッチツールが含まれているため、パッチをすぐに適用できます。 ただし、DXPアプリケーションサーバーのインストールでは、パッチを適用する前にパッチツールをインストールして構成する必要があります。

```{warning}
パッチを当てる前に、**必ず**データベースとインストールを[バックアップ](../backing-up.md)するようにしてください。
```

```{note}
DXPをDockerコンテナで動作させている場合は、[Patching DXP in Docker](./../installing-liferay/using-liferay-docker-images/patching-dxp-in-docker.md)の指示に従ってください。
```

DXPバンドルにパッチを適用する場合は、以下の基本的なパッチ適用手順に進んでください。 アプリケーションサーバーでDXPにパッチを適用する場合は、パッチを適用する手順を実行する **前** に、 [追加の準備を行ってください](#preparing-to-patch-dxp-on-an-application-server) 。

## パッチ適用手順

1. パッチを `patching-tool/patches` フォルダーにダウンロードします---パッチを解凍しないでください。

    * フィックスパックとサービスパックは、ヘルプセンターの [ダウンロード](https://customer.liferay.com/downloads) ページにあります。
    * ホットフィックスは [ヘルプセンター](https://help.liferay.com/hc) チケットにあります。

1. アプリケーションサーバーをシャットダウンします。

    理由:

    * Unixスタイルのシステムでは、通常、実行中のファイルを置き換えることができますが、古いファイルはメモリに常駐します。
    * Windowsシステムでは、使用中のファイルはロックされており、パッチを適用できません。

1. `patching-tool` フォルダーからパッチングツールの `install` コマンドを実行して、パッチをインストールします。

    ```bash
    cd patching-tool
    ./patching-tool.sh install
    ```

    出力は次のようになります。

    ```
    There's no configuration available. Running auto-discovery in the parent folder.
    Directory is not set in command line, using ../
    Auto discovery looks for portal segments. The selected directory is "/home/russell/liferay-bundles/cross-cluster-replication_7.2_LRDOCS-8715/liferay-dxp-7.2.10.3-sp3".
    Configuration has been written into the default.properties:
    patching.mode=binary
    war.path=../tomcat-9.0.33/webapps/ROOT/
    global.lib.path=../tomcat-9.0.33/lib/ext/
    liferay.home=../
    One patch is ready to be installed. Applying dxp-10...
    Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
    Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
    Syncing...
    The patches contain database index modification. Run the patching tool with the index-info parameter for more information.
    The installation was successful. One patch is installed on the system.
    ```

1. `info` コマンドを実行し、現在インストールされているパッチの情報を確認して、インストールされているパッチを確認します。

    ```bash
    ./patching-tool.sh info
    ```

    出力には、現在インストールされているパッチが一覧表示されます。

    ```
    Loading product and patch information...
    Product information:
      * installation type: binary
      * build number: 7210
      * service pack version:
        - available SP version: 3
        - installable SP version: 3
      * patching-tool version: 2.0.15
      * time: 2021-01-21 18:02Z
      * host: russell-pc (8 cores)
      * plugins: no plugins detected
    Currently installed patches: dxp-10-7210
    Available patches: dxp-8-7210, dxp-10-7210
    Detailed patch list: 
      [ -] dxp-8-7210 :: Currently not installed; Won't be installed: dxp-10 contains the fixes included in this one :: Built for LIFERAY
      [*I] dxp-10-7210 :: Installed; Will be installed. :: Built for LIFERAY
    ```

1. 全てのDXPキャッシュを消去します。

    `［Liferay Home］/osgi/state` フォルダーを削除します。

    ```bash
    cd [Liferay Home]
    rm -rf osgi/state
    ```

    `［Liferay Home］/work` フォルダーを空にします。

    ```bash
    rm -rf work/*
    ```

    アプリケーションサーバーのキャッシュを削除します。 キャッシュの場所については、アプリケーションサーバーのベンダーのドキュメントを参照してください。

    ```{note}
    モジュールの変更が内部のみである場合、変更はOSGiフレームワークからは見えず、モジュールはインストールされたままであり、モジュールの状態は保持されます。 次のDXP起動の前にOSGiバンドルの状態情報をクリアすると、そのようなモジュールが適切な状態で再インストールされます。
    ```

1. パッチのリリースノートにマイクロまたはマイナーなスキーマ/データの変更が記載されている場合は、 [データベースアップグレードツール](../../upgrading-liferay/upgrade-basics/using-the-database-upgrade-tool.md) を使用して、マイナーな変更（必須）と必要なマイクロな変更を適用します。

    ```{important}
    Liferay DXP 7.2 GA1またはフィックスパック1からDXP 7.2 SP1 / フィックスパック2（またはそれ以降）に更新する場合は、データベースアップグレードツールを使用してデータとデータベースを更新する必要があります。
    ```

1. DXPの `web.xml` ファイルをカスタマイズした場合は、カスタマイズ内容をフィックスパックに含まれる新しい `web.xml` ファイルにマージします。 フィックスパックは常に既存の `web.xml` ファイルを上書きします。

1. パッチにインデックスの更新がある場合は、起動時にインデックスを更新するようにDXPを構成します。

    `info` コマンドを使用して、インデックスの更新を確認します。

    ```bash
    cd patching-tool
    ./patching-tool.sh info
    ```

    インデックスの更新がある場合は、[`portal-ext.properties` ファイル](../../reference/portal-properties.md)の [`database.indexes.update.on.startup`](https://learn.liferay.com/reference/latest/en/dxp/propertiesdoc/portal.properties.html#Database) Portal Propertyを `true` に設定します。

    ```properties
    database.indexes.update.on.startup=true
    ```

    `LIFERAY_` OR `IX_` で始まるインデックスのみが更新されます。 カスタムインデックスがこの命名規則を使用していないことを確認してください。

1. DXP 7.3をアプリケーションサーバーにインストールし直す場合は、パッチを適用したDXPアプリケーションを [一時的な場所](#preparing-to-patch-dxp-on-an-application-server) から`.war`ファイルに圧縮して戻し、ファイルをアプリケーションサーバーにコピーします。 アプリケーションサーバーの[DXPのインストール手順](../../installing-liferay/installing-liferay-on-an-application-server.md)を参照してください。

1. アプリケーションサーバーを再度起動します。

　 DXPインスタンスにパッチが適用され、実行されています。

```{note}
パッチがインストールされない場合、または発生したエラーを解決できない場合は、 [Help Center ticket](https://help.liferay.com/hc/) を開き、`./patching-tool.sh info > output.txt` を実行し、`output.txt`ファイルをチケットに添付して、パッチツールの完全な`info`出力を提供してください。
````

## アプリケーションサーバーのDXPにパッチを適用する準備

DXPをアプリケーションサーバーにインストールした場合は、次の準備をしてください。 

1. DXP 7.3にパッチを適用する際に、DXPが`.war`ファイルとしてデプロイされている場合は、`.war`をパッチ適用のための一時的な場所に解凍してください。

1. まだインストールしていない場合は、[Install the Patching Tool](../reference/installing-the-patching-tool.md)をご覧ください。

1. `auto-discovery`コマンドを実行して、DXPのインストール用の[パッチツールを構成します](../reference/configuring-the-patching-tool.md)。

    ```bash
    cd patching-tool
    ./patching-tool.sh auto-discovery
    ```

1. DXP 7.3にパッチを適用する場合は、パッチツールの`war.path`プロパティ（たとえば、`default.properties`）を解凍したDXP `.war`の場所に設定します。

1. 前のセクションの [パッチ適用手順](#basic-patching-steps) に進みます。

DXPバンドルとDXPアプリケーションサーバーのインストールにパッチを適用する方法をマスターしました。

<a name="additional-information" />

## 追加情報

* [パッチツールのインストール](../reference/installing-the-patching-tool.md)
* [パッチツールの構成](../reference/configuring-the-patching-tool.md)
* [パッチのアンインストール](../reference/uninstalling-patches.md)
