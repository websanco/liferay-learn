# Dockerイメージから始める

Liferayの最新バージョンは、Dockerイメージとして入手できます。 以下の手順に従って、Liferay DXPの最新リリースをダウンロードして起動し、ツアーを開始してください。

``` important::
    Dockerをお持ちではありませんか？ まずは `Linux <https://docs.docker.com/install/linux/docker-ce/ubuntu/>`_ | `Windows <https://docs.docker.com/docker-for-windows/install/>`_ | `OSX <https://docs.docker.com/docker-for-mac/install/>`_ に移動してください。
```

## Liferayを使ってみる

Liferay Portal CEは、説得力のある柔軟なWebエクスペリエンスを構築するための、オープンソースでコミュニティがサポートするプラットフォームです。

1.  Dockerイメージを取得します。

    ``` bash
    docker pull liferay/portal:7.3.1-ga2
    ```

2.  Dockerイメージを取得します。

    ``` bash
    docker run -it -p 8080:8080 liferay/portal:7.3.1-ga2
    ```

    ``` tip::
       Wait until you see ``org.apache.catalina.startup.Catalina.start Server startup in [x] milliseconds`` to indicate startup completion.
    ```

3.  ブラウザで`https://localhost:8080`を開きます。

    ![Liferay ポータルの最初のランディングページ。](./starting-with-a-docker-image/images/01.png)

### Liferayを使ってみる

Liferay DXPは、世界中で使用されている商用サポート済みのエンタープライズ対応プラットフォームであり、Liferayポータルの基盤の上に構築されています。 Dockerイメージから始めるのは簡単で、トライアルライセンスが含まれています。

1.  Dockerイメージを取得します。

    ``` bash
    docker pull liferay/dxp:7.2.10-dxp-4
    ```

2.  Dockerイメージを取得します。

    ``` bash
    docker run -it -p 8080:8080 liferay/dxp:7.2.10-dxp-4
    ```

3.  ブラウザで`https://localhost:8080`を開きます。

## 次のステップ

引き続きスタートガイドを進め、[管理者アカウント](./introduction-to-the-admin-account.md)を使用して初めてログインする方法を学習します。

状況をチェックする以上のことをしますか？ 詳細については、[Installing a Liferay DXP Tomcat Bundle](../installation-and-upgrades/installing-liferay/installing-a-liferay-tomcat-bundle.md)を参照してください。

Liferay DXPで構築できるものについて詳しく知りたいですか？ [Build Solutions on DXP](../building-solutions-on-dxp/README.md)を参照してください。
