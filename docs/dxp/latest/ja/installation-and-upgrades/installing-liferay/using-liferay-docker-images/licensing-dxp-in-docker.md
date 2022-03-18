# DXPをDockerでライセンスする

有効期限が切れるDXPトライアルライセンスを使用している場合、またはインストールする必要のある新しいライセンス（アクティベーションキー）がある場合は、コンテナの既存のライセンスを置き換えることができます。

ライセンス有効期限ログメッセージの例を次に示します。

```
ERROR [fileinstall-directory-watcher][LicenseManager:?] DXP Development license is expired
```

```{note}
各DXP Dockerイメージには、イメージが作成されてから1か月後に有効期限が切れる一時的なトライアルライセンス（`trial-dxp-license-[id-number].xml`）が含まれています。
```

ライセンスを置き換える方法は次のとおりです。

1. コンテナでBashシェルを開きます。

    ```bash
    docker exec -it [container] bash
    ```

1. 既存のライセンスファイルを削除します。

    ```bash
    rm /opt/liferay/data/license/*
    ```

1. OSGiモジュールフォルダからすべてのトライアルライセンスとアクティベーションキーファイルを削除します。

    ```bash
    rm /opt/liferay/osgi/modules/*license*.xml /opt/liferay/osgi/modules/*activation*.xml
    ```

1. Bashシェルを終了します。

    ```bash
    exit
    ```

1. 新しいライセンス/キーファイルをコンテナにコピーします。

    ```bash
    docker cp [license file] [container]:/opt/liferay/deploy
    ```

    または、[バインドマウント](./providing-files-to-the-container.md)をコンテナに関連付けている場合は、ライセンス/キーファイルを`/mnt/liferay/deploy`にマップされているフォルダにコピーできます。

    詳細は、[コンテナへのファイルの提供](./providing-files-to-the-container.md)を参照してください。

DXPはライセンスのインストールをログに記録します。 例:

```
INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for DXP Development
INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:?] Processing trial-dxp-license-123.xml
INFO  [fileinstall-directory-watcher][LicenseManager:?] DXP Development license validation passed
INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for DXP Development
```

DXPライセンスを更新しました。

## 追加情報

* [Activating Liferay DXP](../../setting-up-liferay/activating-liferay-dxp.md)
* [コンテナへのファイルの提供](./providing-files-to-the-container.md)
* [新しいDockerイメージへのアップグレード](./upgrading-to-a-new-docker-image.md)