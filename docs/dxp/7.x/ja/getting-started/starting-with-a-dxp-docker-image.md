# DXP Dockerイメージから始める

Liferay DXPの最新バージョンは、Dockerイメージとして入手できます。 以下の手順に従って、Liferay DXPの最新リリースをダウンロードして起動し、ツアーを開始してください。

1.  Liferay DXPを入手します。

    ``` tip::
       Dockerをお持ちではありませんか？ まずは `Linux <https://docs.docker.com/install/linux/docker-ce/ubuntu/>`_ | `Windows <https://docs.docker.com/docker-for-windows/install/>`_ | `OSX <https://docs.docker.com/docker-for-mac/install/>`_ に移動してください。
    ```

    ``` bash
    docker pull liferay/portal:7.3.0-ga1
    ```

2.  Liferay DXPを起動します。

    ``` bash
    docker run -it -p 8080:8080 liferay/portal:7.3.0-ga1
    ```

    ``` tip::
       起動の完了を示す「org.apache.catalina.startup.Catalina.start Server startup in [x] milliseconds」が表示されるまで待ちます。
    ```

3.  ブラウザで`https://localhost:8080`を開きます。

    ![Liferay DXPの最初のランディングページ。](./starting-with-a-dxp-docker-image/images/01.png)

## 次のステップ

引き続きスタートガイドを進め、[管理者アカウント](./introduction-to-the-admin-account.md)を使用して初めてログインする方法を学習します。

状況をチェックする以上のことをしますか？ 詳細については、[Installing a Liferay DXP Tomcat Bundle](../installation-and-upgrades/installing-liferay/installing-a-liferay-dxp-tomcat-bundle.md)を参照してください。

Liferay DXPで構築できるものについて詳しく知りたいですか？ [Build Solutions on DXP](../building-solutions-on-dxp/README.md)を参照してください。
