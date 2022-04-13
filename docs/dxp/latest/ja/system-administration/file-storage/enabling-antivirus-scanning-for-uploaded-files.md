# アップロードされたファイルのウイルス対策スキャンを有効にする

> 利用可能：DXP 7.3 SP 1、Portal 7.3 GA7、DXP 7.2 FP 9

```{note}
この機能は現在、Portal 7.2以前のバージョンでは利用できません。
```

<!-- TODO: LRDOCS-9341 - Antivirus can be enabled for older versions (7.2 and below) but requires use of portal properties and a local install of ClamAV server which we do not recommend. -->

Liferayにアップロードされたファイルを自動的にスキャンしてウイルスを検出できます。 ウイルス対策スキャナーを有効にすると、[ドキュメントとメディア](../../content-authoring-and-management/documents-and-media/documents-and-media-overview.md)、[掲示板](../../collaboration-and-social/message-boards/user-guide/getting-started-with-message-boards.md)などのLiferayアプリケーションへのアップロード時にファイルがチェックされます。 ウイルスが見つかった場合は、報告され、ユーザーが拒否できます。

![スキャナーは、ドキュメントとメディアやその他のLiferayアプリケーションへのアップロード時にウイルスに感染したファイルを検出します。](./enabling-antivirus-scanning-for-uploaded-files/images/01.png)

Liferayは、 [ClamAV Daemon](https://www.clamav.net/documents/scanning#clamd) (Clamd)と統合されています。 最高のパフォーマンスを得るためには、Clamdを別のサーバーで実行してください。

ここでは、ウイルス対策スキャンを有効にする方法を説明します。

1. 別のサーバーで、 [Clamdを設定・起動します](https://www.clamav.net/documents/scanning#clamd) 。

    ```{important}
    Clamdを起動する前にClamAVデータベースを読み込みます。
    ```

1. 次の[ポータルプロパティ](../../../installation-and-upgrades/reference/portal-properties.md)または[Docker環境変数](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/configuring-containers.md)を設定して、[ファイルストア](../../../system-administration/file-storage/configuring-file-storage.md)（ドキュメントライブラリ）のウイルス対策を有効にします。

    ポータルプロパティ：

    ```properties
    dl.store.antivirus.enabled=true
    ```

    Docker環境変数：

    ```properties
    -e LIFERAY **DL** PERIOD **STORE** PERIOD **ANTIVIRUS** PERIOD_ENABLED=true
    ```

1. Liferayサーバーを起動します。

1. ［**コントロールパネル**］ &rarr; ［**システム設定**］ に移動し、セキュリティカテゴリで ［**Antivirus**］ を選択します。

1. メニューで ［**Antivirus Clamd Scanner**］ を選択します。

    ![Antivirus Clamd Scannerの設定](./enabling-antivirus-scanning-for-uploaded-files/images/02.png)

1. Clamdサーバーのホスト名またはIPアドレス、ポート、および接続タイムアウト時間（ミリ秒）を入力します。

1. ［**保存**］ をクリックします。

これで、Liferayアプリケーションへのアップロード時にファイルがスキャンされます。 アップロードするファイルでウイルスが検出された場合、スキャナーによって感染したファイルが報告されるので、ファイルの保存を却下する必要があります。

![これがウイルス検出メッセージです。](./enabling-antivirus-scanning-for-uploaded-files/images/03.png)

```{important}
ウイルスに感染したファイルは絶対に保存しないでください。 現在の操作をキャンセルして、ファイルを拒否します。
```

<a name="additional-information" />

## 追加情報

* [ファイルストレージの構成](./configuring-file-storage.md)
* [Liferayの設定](../configuring-liferay.html)
* [システム設定](../configuring-liferay/system-settings.md)
