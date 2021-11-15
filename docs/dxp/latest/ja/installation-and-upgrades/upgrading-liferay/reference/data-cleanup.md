# データのクリーンアップ

廃止されたLiferayアプリまたは機能の使用を終えたら、そのデータを削除できます。 コントロールパネルまたは [設定ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) を使用して、データのクリーンアップを行うことができます。

廃止されたデータをクリーンアップする方法は次のとおりです。

1.  *[コントロールパネル]* で、*[システム設定]* → *[Upgrades]*（*[プラットフォーム]* カテゴリ内） → *[Data Cleanup]* に移動します。 [Data Cleanup]画面が表示されます。

    ![[Data Cleanup]では、廃止されたLiferayアプリケーションからデータを削除するためのインターフェイスが提供されます。](./data-cleanup/images/01.png)

2.  クリーンアップするモジュールを選択し、*[保存]* をクリックします。

    ```{note}
    [設定ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) にクリーンアップ設定を保存するには、*アクション*メニューをクリックして、*エクスポート*を選択します。
    ```

    データのクリーンアップが実行されます。

3.  [Data Cleanup]画面内のすべてのモジュールのチェックを外す、または`com.liferay.data.cleanup.internal.configuration.DataCleanupConfiguration.config` [設定ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)内でモジュールクリーンアップキー`false`を設定することにより、データのクリーンアップを無効にします。

サーバーから廃止されたデータが削除されました。

```{note}
`com.liferay.data.cleanup.internal.configuration.DataCleanupConfiguration.config` [設定ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) を使用して、 [Liferay Dockerコンテナ](../upgrade-basics/upgrading-via-docker.md) またはローカルマシンで）サーバーの起動時にモジュールデータをクリーンアップすることもできます。 クリーンアップ後、 `osgi`フォルダから設定ファイルを削除します。
```

## 追加情報

  - [構成ファイルの使用](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)
  - [Upgrading Via Docker](../upgrade-basics/upgrading-via-docker.md)
  - [コンテナへのファイルの提供](../../installing-liferay/using-liferay-docker-images/providing-files-to-the-container.md)
  - [Using the Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md)
