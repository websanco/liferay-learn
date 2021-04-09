# スリムバンドルの使用

Liferay DXPフィックスパックおよびサービスパックは、Tomcatアプリケーションサーバーにバンドルされて提供されています。 従来のバンドルと *スリムバンドル*として利用できます。 従来のバンドルには、 *パッチファイル*含まれています。パッチツールが特定のパッチをインストールするために必要なすべてのソースコードとバックアップファイル。 スリムバンドルにはパッチファイルが含まれていないため、従来のバンドルの約3分の1のサイズです。 スリムバンドルは、ディスク容量が限られている、またはコストがかかるデモまたはインストールを目的としています。 クラウドやコンテナ環境に最適です。

``` warning::
   Most Patching Tool commands (such as `install`) cannot be run on a Slim Bundle until the patching files for the Fix Pack or Service Pack are installed to the Slim Bundle.
```

スリムバンドルのそれぞれのフィックスパック/サービスパックのパッチファイルがない場合、スリムバンドルはこれらのパッチツールコマンドに限定されます。

  - [`auto-discovery`](../configuring-the-patching-tool.md)
  - [`info`](../getting-patch-information.md)
  - `setup`

スリムバンドルにはパッチを適用できますが、パッチファイルを復元する追加の手順が必要です。 各パッチの各パッチのパッチファイルは、 [ヘルプセンター](https://customer.liferay.com/downloads)入手できます。

## スリムバンドルのパッチ

スリムバンドルにパッチをインストールしようとすると、パッチツールはパッチファイルが必要であると報告します。 これがメッセージです。

    This installation does not include data for patching. Please copy the
    liferay-patching-files-[separation-name].zip file into the 'patches'
    directory and run patching-tool setup.

パッチファイルのセットアップ手順は次のとおりです。

1.  [ヘルプセンター](https://customer.liferay.com/downloads)からパッチファイルをダウンロードします。 Fix Pack または Service Pack に移動し、ドロップダウンから *パッチファイル* を選択し、*ダウンロード* をクリックして、パッチファイルをダウンロードします。

2.  パッチファイルをスリムバンドルの `patching-tool/patches` フォルダーに抽出します。

3.  コマンドラインを開き、 `patching-tool` フォルダーに移動します。

4.  パッチツールを使用して、パッチファイルを設定します。

    ``` bash
    ./patching-toolsh setup
    ```

    スリムバンドルにパッチファイルがあり、パッチを適用する準備ができています。

5.  [パッチのインストール](../installing-patches.md)手順に従って、スリムバンドルにパッチを適用します。

スリムバンドルを使用するメリットを学び、パッチを適用する方法を理解しました。

## 追加情報

  - [パッチのインストール](../installing-patches.md)
  - [パッチを適用したインストールのスリム化](./slimming-down-patched-installations.md)
