# パッチ情報の取得

パッチレベルの情報をすぐに入手できるので、インストールを維持し、Liferayサポートから支援を受けることができます。 これらの2つのパッチツールコマンドは、非常に貴重な情報を提供します。

  - [`info`](#using-the-info-command)：DXPインストールの製品情報、パッチ、パッチツールのバージョンなどを一覧表示します。
  - [`support-info`](#including-support-info-in-support-tickets)：問題の再現に重要なシステム情報をリストします。

## `info` コマンドの使用

パッチツールの `info` コマンドは、次の詳細を含むパッチおよびDXPインストール情報を報告します。

  - サービスパックのバージョン
  - パッチツールのバージョン
  - 検出されたプラグイン
  - インストールされているパッチ
  - 利用可能なパッチ（ `patching-tool/patches/` フォルダー内）

彼らはあなたがインストールしたものとインストールに利用できるものを明確にします。

次に実行例を示します。

    patching-tool>./patching-tool.sh info
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
    
    Currently installed patches: dxp-2-7210
    
    Available patches: dxp-2-7210, dxp-3-7210
    
    Detailed patch list:
      [*-] dxp-2-7210 :: Installed; Won't be installed: dxp-3 contains the fixes included in this one :: Built for LIFERAY
      [ I] dxp-3-7210 :: Currently not installed; Will be installed. :: Built for LIFERAY

パッチツールの `support-info` コマンドは、Liferayサポートが環境をすばやく理解するのに役立つ詳細を収集します。

## サポートチケットに `support-info` を含める

問題の再現には、環境の情報（ハードウェアアーキテクチャなど）とパッチレベルをLiferayサポートに提供することが重要です。 次のコマンドを実行して、サポート情報（パッチレベルを含む）をファイルに書き込みます。

``` bash
./patching-tool.sh support-info
```

サポート情報は、 `patching-tool` フォルダーのファイル `patching-tool-support-info-actual-timestamp.txt` に書き込まれます。 このファイルを [ヘルプセンター](https://help.liferay.com/hc) チケットにアップロードしてください。
