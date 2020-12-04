# パッチのインストール

DXPバンドルとDXPアプリケーションサーバーのインストールのパッチ適用手順は似ています。 DXPバンドルには事前設定されたパッチツールが含まれているため、パッチをすぐに適用できます。 ただし、DXPアプリケーションサーバーのインストールでは、パッチを適用する前にPatching Toolをインストールして構成する必要があります。

``` warning::
   **Always** `back up <../backing-up.md>`_ your database and installation before patching.
```

DXPバンドルにパッチを適用する場合は、以下の基本的なパッチ適用手順に進んでください。 あなたは、アプリケーション・サーバー上でDXPにパッチを適用している場合、 [アプリケーションサーバ上でDXPにパッチを適用するための準備](#preparing-to-patch-dxp-on-an-application-server) *前* パッチ適用手順以下。

## パッチ適用手順

1.  パッチを `patching-tool/patches` フォルダーにダウンロードします---パッチを解凍しないでください。

      - フィックスパックとサービスパックは、ヘルプセンターの [ダウンロード](https://customer.liferay.com/downloads) ページにあります。
      - ホットフィックスは [ヘルプセンター](https://help.liferay.com/hc) チケットにあります

2.  アプリケーションサーバーをシャットダウンします。

    理由：

      - Windowsシステムでは、使用中のファイルはロックされており、パッチを適用できません。
      - Unixスタイルのシステムでは、通常、実行中のファイルを置き換えることができますが、古いファイルはメモリに常駐します。

3.  `patching-tool` フォルダーからパッチツールの `install` コマンドを実行して、パッチをインストールします。

    ``` bash
    cd patching-tool
    ./patching-tool.sh install
    ```

    出力は次のようになります。

     One patch is ready to be installed. Applying dxp...
     Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
     Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%...100%]
     The installation was successful. One patch is installed on the system.

4.  `info` コマンドを実行し、現在インストールされているパッチの情報を確認して、パッチのインストールを確認します。

    ``` bash
    ./patching-tool.sh info
    ```

    出力には、現在インストールされているパッチが一覧表示されます。

     Loading product and patch information...
     Product information:
    
       * installation type: binary
       * build number: 7210
       * service pack version:
         - available SP version: 1
         - installable SP version: 1
       * patching-tool version: 2.0.13
       * time: 2019-12-06 20:26Z
       * host: 91WRQ72 (8 cores)
       * plugins: no plugins detected
    
     Currently installed patches:
     ...

5.  Service Packをインストールし、そのリリースノートに [マイクロまたはマイナースキーマ/データの変更](https://help.liferay.com/hc/en-us/articles/360030959231-Meaningful-Schema-Versioning)が記載されている場合は、 [データベースアップグレードツール](../../upgrading-liferay-dxp/upgrade-basics/using-the-database-upgrade-tool.md) を使用して、マイナー変更（必須）および必要なマイクロ変更を適用します。

    ``` important::
       If you're updating from Liferay DXP 7.2 GA1 or Fix Pack 1 to DXP 7.2 SP1 / Fix Pack 2 (or above), you must update the data and database using the Database Upgrade Tool.
    ```

6.  DXPキャッシュをクリーンアップします。

    `[Liferay Home]/osgi/state` フォルダーを削除します。

    ``` bash
    cd [Liferay Home]
    rm -rf osgi/state
    ```

    `[Liferay Home]/work` フォルダーを空にします。

    ``` bash
    rm -rf work/*
    ```

    アプリケーションサーバーのキャッシュを削除します。 キャッシュの場所については、アプリケーションベンダーのドキュメントを参照してください。

    ``` warning::
       **Do not delete these two files:** ``patching-backup-deps.zip`` and ``patching-backup.zip``. The Patching Tool creates them in the DXP application's `WEB-INF` folder. The Patching Tool examines them to determine previous Fix Pack files to revert before installing a new Fix Pack.
    ```

    ``` note::
       If a module's changes are only internal, the changes are invisible to the OSGi framework, the module stays installed, and the module's state persists. Clearing the OSGi bundle state information before the next DXP startup ensures that such modules reinstall with the appropriate state.
    ```

7.  DXPの `web.xml` ファイルをカスタマイズした場合は、カスタマイズ内容をフィックスパックに含まれる新しい `web.xml` ファイルにマージします。 フィックスパックは常に既存の `web.xml` ファイルを上書きします。

8.  パッチにインデックスの更新がある場合は、起動時にインデックスを更新するようにDXPを構成します。

    `info` コマンドを使用して、インデックスの更新を確認します。

    ``` bash
    cd patching-tool
    ./patching-tool.sh info
    ```

    インデックスの更新がある場合は、 [`portal-ext.properties` ファイル](../../reference/portal-properties.md)[`database.indexes.update.on.startup`](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Database) Portal Propertyを `true` します。

    ``` properties
    database.indexes.update.on.startup=true
    ```

    `LIFERAY_` OR `IX_` で始まるインデックスのみが更新されます。 カスタムインデックスがこの命名規則を使用していないことを確認してください。

9.  アプリケーションサーバーを再起動します。

DXPインスタンスにパッチが適用され、実行されています。

``` note::
   If the patch doesn't install or if you're unable to resolve errors that occur, please open a `Help Center ticket <https://help.liferay.com/hc/>`_ and provide the full Patching Tool ``info`` output by running ``./patching-tool.sh info > output.txt`` and attaching the ``output.txt`` file to the ticket.
```

## アプリケーションサーバー上のDXPにパッチを適用する準備

DXPをアプリケーションサーバーにインストールした場合、DXPにパッチを適用する前に、まずパッチツールをインストールして構成する必要があります。

1.  [まだインストールしていない場合は、パッチツール](./installing-the-patching-tool.md)をインストールします。

2.  [ `auto-discovery` コマンドを実行して、DXPインストール用にパッチツール](./configuring-the-patching-tool.md) を構成します。

    ``` bash
    cd patching-tool
    ./patching-tool.sh auto-discovery
    ```

3.  前のセクションの [パッチ適用手順](#basic-patching-steps) に進みます。

DXPバンドルとDXPアプリケーションサーバーのインストールにパッチを適用する方法をマスターしました。

## 追加情報

  - [パッチツールのインストール](./installing-the-patching-tool.md)
  - [パッチツールの構成](./configuring-the-patching-tool.md)
  - [パッチのアンインストール](./uninstalling-patches.md)
  - [パッチを適用したインストールのスリム化](./advanced-patching/slimming-down-patched-installations.md)
  - [スリムバンドルの使用](./advanced-patching/using-slim-bundles.md)
