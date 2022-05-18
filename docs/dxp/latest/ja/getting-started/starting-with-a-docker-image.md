# Dockerイメージから始める

Liferayの最新バージョンは、Dockerイメージとして入手できます。 以下の手順に従って、Liferay DXPの最新リリースをダウンロードして起動し、ツアーを開始してください。

```{important}
Dockerをお持ちではありませんか？ まずは [Linux](https://docs.docker.com/install/linux/docker-ce/ubuntu/) | [Windows](https://docs.docker.com/docker-for-windows/install/) | [OSX](https://docs.docker.com/docker-for-mac/install/) に移動してください。
```

<a name="get-started-with-liferay-portal" />

## Liferayを使ってみる

## Liferay Portalを始める

Liferay Portalは、説得力のある柔軟なWebエクスペリエンスを構築するための、オープンソースでコミュニティがサポートするプラットフォームです。

1. Dockerイメージを取得します。

    ```bash
    docker pull [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
    ```

1. Dockerイメージを取得します。

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
    ```

    ```{tip}
    起動の完了を示す「org.apache.catalina.startup.Catalina.start Server startup in ［x］ milliseconds」が表示されるまで待ちます。
    ```

1. ブラウザで`https://localhost:8080`を開きます。

    ![Liferay ポータルの最初のランディングページ。](./starting-with-a-docker-image/images/01.png)

<a name="get-started-with-liferay-dxp" />

### Liferayを使ってみる

Liferay DXPは、世界中で使用されている商用サポート済みのエンタープライズ対応プラットフォームであり、Liferayポータルの基盤の上に構築されています。 Dockerイメージから始めるのは簡単で、トライアルライセンスが含まれています。

1. Dockerイメージを取得します。

    ```bash
    docker pull [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

1. Dockerイメージを取得します。

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

1. ブラウザで`https://localhost:8080`を開きます。

<a name="whats-next" />

<a name="whats-next" />

## 次のステップ

引き続きスタートガイドを進め、[管理者アカウント](./introduction-to-the-admin-account.md)を使用して初めてログインする方法を学習します。

状況をチェックする以上のことをしますか？ 詳細は、[Liferay-Tomcatバンドルのインストール](../installation-and-upgrades/installing-liferay/installing-a-liferay-tomcat-bundle.md)を参照してください。

[Dockerイメージの使用](../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md)に関する詳細をご覧ください。
