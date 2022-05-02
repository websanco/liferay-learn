# データのクリーンアップ

廃止されたLiferayアプリまたは機能の使用を終えたら、そのデータを削除できます。 コントロールパネルまたは [設定ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) を使用して、データのクリーンアップを行うことができます。

廃止された機能からデータをクリーンアップする方法は次のとおりです。

1. [**コントロールパネル**] で、 [**システム設定**] → [**Upgrades**]（[**プラットフォーム**] カテゴリ内） → [**Data Cleanup**] に移動します。 [Data Cleanup]画面が表示されます。

    ![ [Data Cleanup]では、廃止されたLiferayアプリケーションからデータを削除するためのインターフェイスが提供されます。](./data-cleanup/images/01.png)

2.  クリーンアップするモジュールを選択し、 [**保存**] をクリックします。

    ```{note}
    [構成ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)にクリーンアップ設定を保存するには、 **アクション** メニューをクリックして、 **エクスポート** を選択します。
    ```

   データのクリーンアップが実行されます。

サーバーには古いデータの負担から解放され、すべてのモジュールでデータクリーンアップが自動的に無効になります。

```{note}
データクリーンアップを実行すると（構成ファイルを介してでも）、DXPはすべてのモジュールに対してデフォルトで自動的にデータクリーンアップを無効にします。 これにより、不要な重複するデータのクリーンアップが防止されます。

7.4より前のバージョンでは、データクリーンアップ設定は保持されます。 これらの古いバージョンでクリーンアップを無効にするには、［Data Cleanup］画面ですべてのモジュールのチェックを外して*保存*をクリックするか、 `com.liferay.data.cleanup.internal.configuration.DataCleanupConfiguration.config` [構成ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)でモジュールクリーンアップキー` false`を設定します。
```

<a name="additional-information" />

## 追加情報

  - [構成ファイルの使用](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)
  - [Dockerによるアップグレード](../upgrade-basics/upgrading-via-docker.md)
  - [コンテナへのファイルの提供](../../installing-liferay/using-liferay-docker-images/providing-files-to-the-container.md)
  - [Using the Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md)
