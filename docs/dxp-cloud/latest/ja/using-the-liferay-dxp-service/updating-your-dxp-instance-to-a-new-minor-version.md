# DXPインスタンスを新しいマイナーバージョンにアップデートする

Liferay DXPのインストールを定期的に更新することは、DXP Cloud環境を維持するための重要な要素です。 [Liferay DXP Docker Hubページの](https://hub.docker.com/r/liferay/dxp/tags) にある利用可能なタグを使用して、サービスの更新とデプロイを行います。

```{note}
   新しいメジャーバージョン（Liferay DXP 7.3など）へのアップグレードは、小さいバージョンのアップデートとは異なる手順が必要です。 詳しくは [Liferay DXPインスタンスのアップグレード](./upgrading-your-liferay-dxp-instance.md) を参照してください。
```

```{note}
   もし、 [hotfix](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/maintaining-a-liferay-dxp-installation/patching-liferay/understanding-patch-types.html#hotfixes) をインストールしたいのであれば、代わりに [these steps](./deploying-to-the-liferay-service.md#deploying-hotfixes) を実行してください。
```

<a name="enabling-module-upgrades-for-dxp-73" />

## DXP 7.3+のモジュールアップグレードの有効化

まず、DXPの7.3バージョンを更新しているのであれば、環境変数を設定して、必要に応じてモジュールがアップグレードできるようにします。

1. DXPクラウドのコンソールで、目的の環境のLiferayサービスをクリックします。

1. ［**環境変数**］ タブをクリックします。

1. `LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN` 変数の値を `true`にしてリストに追加します。

1. [変更を保存]ボタンをクリックします。

これにより、モジュールはDXPの新しいマイナーバージョンに必要なアップグレードを行うことができます。 DXPのバージョンを新しいフィックスパックやサービスパックにアップデートする際に、常にモジュールのアップグレードを許可するつもりであれば、アップデート後もこの環境変数を維持することができます。

<a name="updating-and-deploying-a-new-version-of-dxp" />

## DXPの新しいバージョンへのアップデートと導入

Liferay DXPのマイナーバージョンのアップデートには、プロジェクトのリポジトリの変更も必要です。

```{important}
   もし、[クラスターサービス](./setting-up-clustering-in-dxp-cloud.md) を使用していて、Liferay データベースのスキーマを変更するバージョン ( [サービスパック](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/maintaining-a-liferay-dxp-installation/patching-liferay/understanding-patch-types.html#service-packs) など) にアップデートする場合は、 [以下のステップ](#updating-to-a-new-service-pack-with-clustering-enabled) に従ってください。
```

以下の手順で、プロジェクトリポジトリの変更点を更新およびデプロイします。

1. [Docker Hub](https://hub.docker.com/r/liferay/dxp/tags) で、アップデートするLiferayのバージョンのタグを見つけます。

1. リポジトリで、 `liferay.workspace.docker.image.liferay` のプロパティの値を、 [`liferay/gradle.properties`](./introduction-to-the-liferay-dxp-service.md#choosing-a-version) の新しいバージョンのタグに変更します：

    ```properties
    liferay.workspace.docker.image.liferay=liferay/dxp:7.3.10-ga1
    ```

1. この環境変数を、リポジトリの `env` ブロックの `liferay/LCP.json` ファイルに追加します。

    ```json
    {
        "LIFERAY **UPGRADE** PERIOD **DATABASE** PERIOD **AUTO** PERIOD_RUN": "true"
    }
    ```

1. 目的の環境の`liferay`サービスに[変更をデプロイします](./deploying-to-the-liferay-service.md)。

1. DXPのバージョン7.3+の場合、今後新しいフィックスパックやサービスパックへのアップグレード時にモジュールのアップグレードを許可しないつもりであれば、Liferayサービスの「環境変数」ページで以前に追加した `LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN` 環境変数を削除します。 
 
   変更をデプロイすると、 `liferay` サービスが再起動し、アップデートを完了するために必要なアップグレード手順を開始します。
   
   

<a name="updating-to-a-new-service-pack-with-clustering-enabled" />

## クラスタリングが有効になっている新しいサービスパックへの更新

データベーススキーマを変更するLiferay DXPのバージョンにアップデートする場合、クラスター化された `liferay` サービスは、すべてのノードが正しくアップデートされるように、アップデート手順中に一時的な変更が必要になります。

次の手順を実行します：

1. リポジトリの`liferay/LCP.json`ファイルの`スケール`プロパティを`1`に設定します： 
   
   

   ```json
   {
       "scale": 1,
   }
   ```


1. `liferay` サービスに[変更内容をデプロイします](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md)。

1. [Docker Hub](https://hub.docker.com/r/liferay/dxp/tags) で、アップデートするLiferayのバージョンのタグを見つけます。

1. `liferay/gradle.properties`の`liferay.workspace.docker.image.liferay`のプロパティの値を、新しいバージョンのタグに変更します： 
   
   

    ```properties
    liferay.workspace.docker.image.liferay=liferay/dxp:7.3.10-ga1
    ```


1. `liferay/LCP.json`で[deployment strategy](../build-and-deploy/understanding-deployment-strategies.md) を`Recreate`に設定します。 
   
   

    ```json
    {
        "strategy": {
            "type": "Recreate"
        }
    }
    ```


1. `liferay`サービスに[これらの変更内容をデプロイします](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md)。
   
   お客様の `liferay` サービス上のLiferay DXPインストールは、起動時に新しいバージョンに更新されます。 ただし、一時的に行われたサービスの変更を元に戻す必要があります。

1. `scale` プロパティを、 `liferay/LCP.json`で希望するノード数に戻します： 
   
   

    ```json
    {
        "scale": 3,
    }
    ```


1. `liferay/LCP.json` のデプロイメントストラテジーを元の値に戻します（もしくはバージョン更新のためだけに追加されたプロパティを削除します）。

1. もう一度[変更内容をデプロイします](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md)。

1. DXPのバージョン7.3+の場合、今後新しいフィックスパックやサービスパックへのアップグレード時にモジュールのアップグレードを許可しないつもりであれば、Liferayサービスの「環境変数」ページで以前に追加した `LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN` 環境変数を削除します。
   
   更新された `liferay` サービスは、最終的なデプロイメントの後、希望する数のノードで再起動します。
   
   

<a name="additional-information" />

## 追加情報

* [Liferayサービスへのデプロイ](./deploying-to-the-liferay-service.md)
* [Hotfixのインストール](./deploying-to-the-liferay-service.md#deploying-hotfixes)
* [DXP Cloudでのクラスタリングのセットアップ](./setting-up-clustering-in-dxp-cloud.md)
* [Liferay DXPインスタンスのアップグレード](./upgrading-your-liferay-dxp-instance.md)
