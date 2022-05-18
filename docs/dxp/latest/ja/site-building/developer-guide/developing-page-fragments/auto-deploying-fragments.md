# フラグメントの自動デプロイ

> 利用可能：Liferay Portal 7.3GA1+およびLiferayDXP7.3+

[独自のツールを使用してページフラグメントを開発している](./using-the-fragments-toolkit.md#fragment-set-structure) 場合は、Liferay UI を介してインポートできるように、それらを ZIP ファイルにパッケージ化することでデプロイできます。 ただし、UI の使用に限定されません。 コマンドラインからフラグメント ZIP ファイルをデプロイすることもできます。 ここでは、フラグメントプロジェクトを操作し、Liferay の自動デプロイメカニズムを使用してコマンドラインからフラグメントを必要なサイトにインポートする方法を学びます。

```{note}
Liferay DXP 7.4以降の場合、フラグメントコレクションはLiferay UIではフラグメントセットと呼ばれます。
```

<a name="deploy-an-auto-deployable-fragment-set" />

## 自動デプロイ可能なフラグメントセットをデプロイする

```{include} /_snippets/run-liferay-dxp.md
```

次に、以下の手順を実行します。

1. [サンプルの自動デプロイ可能なフラグメントセット](https://learn.liferay.com/dxp/latest/ja/site-building/developer-guide/developing-page-fragments/liferay-a2f8.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/site-building/developer-guide/developing-page-fragments/liferay-a2f8.zip -O
    ```

    ```bash
    unzip liferay-a2f8.zip
    ```

1. フラグメントプロジェクトのセットとそのデプロイメント記述子をZIPファイルに圧縮します。

    ```bash
    cd liferay-a2f8
    ```

    ```bash
    zip -r  a2f8-fragments.zip a2f8-set/ liferay-deploy-fragments.json
    ```

1. 新しい `.zip` ファイルをLiferayのDockerコンテナ内の自動デプロイフォルダにコピーすることで、フラグメントセットを記述子で指定されたサイトにインポートします。

    ```bash
    docker cp a2f8-fragments.zip $(docker ps -lq):/opt/liferay/deploy
    ```

1. Docker コンソールで次のログメッセージを確認します。

    ```bash
    INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:263] Processing a2f8-fragments.zip
    ```

1. フラグメントセットが利用可能であることを確認します。 **サイトメニュー**(![Site Menu](../../../images/icon-product-menu.png)) を開き、 ［**デザイン**］ &rarr; ［**フラグメント**］ に移動します。 セットがリストに表示されます。

```{note}
インポートされたフラグメントに無効なルールがある場合、ドラフトとして自動的に保存されます。
```

![セットが利用可能です。](./auto-deploying-fragments/images/01.png)

<a name="fragment-project-structure" />

## フラグメントプロジェクトのストラクチャー

自動デプロイ可能なフラグメントプロジェクトの構造は次のとおりです。

```bash
[project ZIP]
├── [fragment-set]
│   ├── collection.json
│   └── [fragment]
│       └── fragment files ...
└── liferay-deploy-fragments.json
```

`liferay-deploy-fragments.json` ファイルは、フラグメントをデプロイする範囲を指定します。

* システム全体 (すべてのインスタンス)
* 仮想インスタンス (会社)
* サイト (グループ)

以下のサンプルの構成では、仮想インスタンス（liferay.com）内の「Guest」というサイト（グループ）にデプロイするよう指定されています。

```json
{
    "companyWebId": "liferay.com",
    "groupKey": "Guest"
}
```

このJSONファイルのキーはどちらもオプションです。 `liferay-deploy-fragments.json` ファイルで、空の JSON 要素を指定するか、次の構成を追加することで、フラグメントをシステム全体 (すべてのインスタンス) で使用できるようにすることができます。

```json
{
    "companyWebId": "*"
}
```

```{note}
フラグメントツールキット[npm run compress command](./using-the-fragments-toolkit.md) を使用すると、フラグメント ZIP ファイルとそのデプロイメント記述子の作成が容易になります。
```

<a name="modify-the-fragment-set-and-redeploy" />

## フラグメントセットを変更して再デプロイする

フラグメントセットを変更して再デプロイするには、次の手順に従います。

1. プロジェクトの `a2f8-jumbotron` フラグメントフォルダを `a2f8-set/` フォルダに移動して、サンプルのセットに新しいフラグメントを追加します。

1. 前述のように、フラグメントセットを ZIP ファイルに圧縮します。

    ```bash
    zip -r  a2f8-fragments.zip a2f8-set/ liferay-deploy-fragments.json
    ```

1. 上記で行ったように、ZIP ファイルを Docker コンテナにコピーして、変更されたフラグメントセットをインポートします。

   ```bash
   docker cp a2f8-fragments.zip $(docker ps -lq):/opt/liferay/deploy
   ```

1. 新しいフラグメントを確認します。 ［**サイトメニュー**］(![Site Menu](../../../images/icon-product-menu.png)) を開き、 ［**デザイン**］ &rarr; ［**フラグメント**］ に移動し、 ［**A2F8セット**］ をクリックします。 A2F8ジャンボトロンフラグメントが含まれているはずです。

    ![新しいフラグメントは、自動デプロイされたセットに含まれます。](./auto-deploying-fragments/images/02.png)

これで、ローカルでフラグメントセットを操作する方法、フラグメントセットにサイトを指定する方法、自動デプロイメントを使ってフラグメントセットをインポートする方法がわかりました。

<a name="additional-information" />

## 追加情報

* [Including Default Resources with Fragments](./including-default-resources-with-fragments.md)
* [フラグメントへの構成オプションの追加](./adding-configuration-options-to-fragments.md)
