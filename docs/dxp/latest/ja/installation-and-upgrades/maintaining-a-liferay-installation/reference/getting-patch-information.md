# パッチ情報の取得

> サブスクライバー

パッチ情報を一目で確認できるので、インストールのメンテナンスやLiferayサポートからのサポートを受けるのに役立ちます。 パッチツールの`info`コマンドは、次の情報を一覧表示します。

* サービスパックのバージョン
* パッチツールのバージョン
* インストールされているパッチ
* 利用可能なパッチ（ `patching-tool/patches/` フォルダー内）

彼らはあなたがインストールしたものとインストールに利用できるものを明確にします。

次に実行例を示します。

```
patching-tool>./patching-tool.sh info
Loading product and patch information...
Product information:
  * build number: 7310
  * service pack version:
    - available SP version: 1
    - installable SP version: 1
  * patching-tool version: 3.0.5
  * time: 2020-09-01 20:26Z
  * host: 91WRQ72 (8 cores)
  * plugins: no plugins detected

Currently installed patches: dxp-1-7310

Available patches: dxp-2-7310, dxp-3-7310

Detailed patch list:
  [*-] dxp-2-7310 :: Installed; Won't be installed: dxp-3 contains the fixes included in this one :: Built for LIFERAY
  [ I] dxp-3-7310 :: Currently not installed; Will be installed. :: Built for LIFERAY
```

これで、1つのコマンドを使用してインストールの詳細を取得できます。
