# Liferay Commerce Dockerイメージの使用

Liferay Commerceの最新バージョンは、Dockerイメージとして利用できます。 Liferay Commerceの最新のDockerイメージリリースをダウンロードして開始するには、次の手順に従ってください。

1.  Liferay Commerceを入手します

    ``` tip::
       Dockerをお持ちではありませんか？ まずは `Linux <https://docs.docker.com/install/linux/docker-ce/ubuntu/>`_ | `Windows <https://docs.docker.com/docker-for-windows/install/>`_ | `OSX <https://docs.docker.com/docker-for-mac/install/>`_ に移動してください。
    ```

    ``` bash
    docker pull liferay/commerce:2.0.6
    ```

2.  Liferay Commerceを開始します

    ``` bash
    docker run -it -p 8080:8080 liferay/commerce:2.0.6
    ```

    ``` important::
       「org.apache.catalina.startup.Catalina.startサーバーの起動が [x] ミリ秒以内」と表示されるまで待ちます
    ```

3.  ブラウザで`https://localhost:8080`を開きます。

## 次のステップ

状況をチェックする以上のことをしますか？ [Installation Overview](./installation-overview.md)で詳細をご覧ください。

数分でストアを立ち上げて運営したいですか？ [Accelerators](../starting-a-store/accelerators.md)に関する詳細情報
