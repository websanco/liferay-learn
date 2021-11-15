# フラグメントの自動デプロイ

> 利用可能: Liferay Portal CE 7.3 GA1以降および Liferay DXP 7.3以降

[独自のツールを使用してページフラグメントを開発している](./using-the-fragments-toolkit.md#collection-format-overview)場合は、Liferay UI を介してインポートできるように、それらを ZIP ファイルにパッケージ化することでデプロイできます。 ただし、UI の使用に限定されません。 コマンドラインからフラグメント ZIP ファイルをデプロイすることもできます。 ここでは、フラグメントプロジェクトを操作し、Liferay の自動デプロイメカニズムを使用してコマンドラインからフラグメントを必要なサイトにインポートする方法を学びます。

フラグメントコレクションのサンプルを自動デプロイすることから始めます。

## 自動デプロイ可能なフラグメントコレクションをデプロイする

フラグメントコレクションのサンプルを自動デプロイすることから始めます。

1.  以下のコマンドを実行して、Docker コンテナを起動します。

    ``` bash
    docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

2.  [サンプルの自動デプロイ可能なフラグメントコレクション](https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-page-fragments/liferay-a2f8.zip)をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-page-fragments/liferay-a2f8.zip -O
    ```

    ``` bash
    unzip liferay-a2f8.zip
    ```

3.  フラグメントプロジェクトのコレクションとそのデプロイメント記述子をZIPファイルに圧縮します。

    ``` bash
    cd liferay-a2f8
    ```

    ``` bash
    zip -r  a2f8-fragments.zip a2f8-collection/ liferay-deploy-fragments.json
    ```

4.  新しい `.zip` ファイルをLiferayのDockerコンテナ内の自動デプロイフォルダにコピーすることで、フラグメントコレクションを記述子で指定されたサイトにインポートします。

    ``` bash
    docker cp a2f8-fragments.zip $(docker ps -lq):/opt/liferay/deploy
    ```

5.  Docker コンソールで次のログメッセージを確認します。

    ``` bash
    INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:263] Processing a2f8-fragments.zip
    ```

6.  フラグメントコレクションが利用可能であることを確認します。 ブラウザで`https://localhost:8080`を開きます。 画面左側のサイトメニューから、*[デザイン]* → *[フラグメント]* に移動します。 *A2F8 コレクション*は、フラグメントコレクションリストにあります。

<!-- end list -->

```{note}
インポートされたフラグメントに無効なルールがある場合、ドラフトとして自動的に保存されます。
```

![コレクションが利用可能です。](./auto-deploying-fragments/images/01.png)

## デプロイメント記述子の概要

自動デプロイ可能なフラグメントプロジェクトの ZIP ファイルは次の構造になっています。

``` 
[project ZIP]
├── [fragment-collection]
│   ├── collection.json
│   └── [fragment]
│       └── fragment files ...
└── liferay-deploy-fragments.json

```

`liferay-deploy-fragments.json` ファイルは、フラグメントをデプロイする範囲を指定します。

  - システム全体 (すべてのインスタンス)
  - 仮想インスタンス (会社)
  - サイト (グループ)

以下のサンプルの構成では、仮想インスタンス（liferay.com）内の「Guest」というサイト（グループ）にデプロイするよう指定されています。

``` json
{
    "companyWebId": "liferay.com",
    "groupKey": "Guest"
}
```

このJSONファイルのキーはどちらもオプションです。 `liferay-deploy-fragments.json` ファイルで、空の JSON 要素を指定するか、次の構成を追加することで、フラグメントをシステム全体 (すべてのインスタンス) で使用できるようにすることができます。

``` json
{
    "companyWebId": "*"
}
```

```{note}
フラグメントツールキット [npm run compress command](./using-the-fragments-toolkit.md) を使用すると、フラグメント ZIP ファイルとそのデプロイメント記述子の作成が容易になります。
```

## フラグメントコレクションを変更して再デプロイする

フラグメントコレクションを変更して再デプロイするには、次の手順に従います。

1.  プロジェクトの `a2f8-jumbotron` フラグメントフォルダを `a2f8-collection/` フォルダに移動して、サンプルのコレクションに新しいフラグメントを追加します。

2.  前述のように、コレクションを ZIP ファイルに圧縮します。

    ``` bash
    zip -r  a2f8-fragments.zip a2f8-collection/ liferay-deploy-fragments.json
    ```

3.  上記で行ったように、ZIP ファイルを Docker コンテナにコピーして、変更されたコレクションをインポートします。

    ``` bash
    docker cp a2f8-fragments.zip $(docker ps -lq):/opt/liferay/deploy
    ```

4.  新しいフラグメントを確認します。 サイトメニューで、*[デザイン]* → *[フラグメント]* に移動し、*[A2F8 コレクション]* をクリックします。 A2F8 ジャンボトロンフラグメントがコレクションに含まれています。

    ![新しいフラグメントは、自動デプロイされたコレクションに含まれます。](./auto-deploying-fragments/images/02.png)

これで、ローカルでフラグメントコレクションを操作する方法、フラグメントコレクションにサイトを指定する方法、自動デプロイメントを使ってフラグメントコレクションをインポートする方法がわかりました。

## 追加情報

  - [Including Default Resources with Fragments](./including-default-resources-with-fragments.md)
  - [Adding Configuration Options to Fragments](./adding-configuration-options-to-fragments.md)
