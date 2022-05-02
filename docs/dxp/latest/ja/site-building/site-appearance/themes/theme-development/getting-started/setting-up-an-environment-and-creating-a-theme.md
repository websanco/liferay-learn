# 環境の設定とテーマの作成

テーマの作成は、テーマ開発の最初のステップです。 独自のテーマを作成したら、それをLiferay DXPインスタンスにデプロイして、さまざまなサイトのカスタマイズ</a>に使用できます。

## Liferayテーマジェネレーターを使用する

テーマは、Liferayテーマジェネレーターを使用して作成します。 Liferay DXP 7.3は、テーマジェネレーターのバージョン10.xxを使用しています。

```{note}
古いバージョンのLiferayテーマジェネレーターを使用して、古いバージョンのLiferay DXPのテーマを生成できます。 DXP 7.0または7.1のテーマを作成するには、テーマジェネレーターのバージョン8.xxをインストールしてください。
```

### インストール

```{note}
Liferayテーマジェネレータを使用してテーマを開発するには、ノードとNPMの両方が必要です。 [ノード互換性マトリクス](../../../../../building-applications/tooling/reference/node-version-information.md)をチェックして、ご使用のLiferayバージョンに対して正しいバージョンがインストールされていることを確認してください。 
```

まだインストールしていない場合は、次のコマンドを使用してLiferayテーマジェネレーターをインストールします。

```bash
npm install -g generator-liferay-theme@10.x.x
```

次のコマンドを使用して、YeomanとGulpの依存関係をインストールします。

```bash
npm install -g yo gulp
```

### Liferayテーマジェネレーターを実行する

次の手順でLiferayテーマジェネレーターを実行します。

1. Yeomanを使用してLiferayテーマジェネレーターを実行します。

    ```bash
    yo liferay-theme
    ```

    ```{important}
    このコマンドにベーステーマの名前を追加して、新しいテーマのベースにすることができます。 たとえば、 `yo liferay-theme:classic`を実行すると、DXPの標準テーマに基づいて新しいテーマが作成されます。
    ``` <!-- Add link to an explanation of choosing (and changing) the base theme when available.-->1. プロンプトでテーマの名前を入力します。 デフォルトの「My Liferay Theme」を使用するには、Enterキーを押します。

    ```
    ? What would you like to call your theme? (My Liferay Theme)
    ```

1. プロンプトでテーマのIDを入力します。 テーマが生成されると、IDによってテーマが組み込まれているフォルダの名前が決まります。 Enterキーを押して、名前に基づいたデフォルトIDを使用することもできます。

    ```
    ? What id would you like to give to your theme? (my-liferay-theme)
    ```

1. 矢印キーを使用して、プロンプトでテーマを作成するLiferay DXPのバージョンを選択します。 7.2または7.3のテーマは、Liferayテーマジェネレーターのバージョン10.xxを使用して生成できます。

    ```
    ? Which version of Liferay is this theme for? (Use arrow keys)
    ❯ 7.3 
      7.2
    ```

1. プロンプトで、テーマに使用できるフォントとしてFont Awesomeを追加するかどうかを答えます。

1. テーマが生成されたら、矢印キーを使用してプロセスを完了し、テーマに適切なデプロイタイプを選択します。 デプロイには、ローカルのアプリケーションサーバー、Dockerコンテナ、またはその他のURLを選択できます。

    ```
    ? Select your deployment strategy (Use arrow keys)
    ❯ Local App Server 
      Docker Container 
      Other
    ```

1. 使用しているデプロイメントのタイプに応じて、プロンプトでアプリサーバーの場所を指定します。

    アプリサーバーのディレクトリ、Dockerコンテナ名、またはホストURLを指定して、アプリサーバーを見つけることができます。

テーマが生成され、選択したIDにちなんで名付けられたフォルダ内に配置されます。 これで、テーマのベースフォルダから`gulp deploy`を実行することで、テーマをビルドしてDXPインスタンスにデプロイできます。

## ブレードを使用したテーマの作成

近日公開！ <!-- Link to Theme Templates documentation when available-->

## 追加情報

* [サイトの外観を変更する](../../../../../getting-started/changing-your-sites-appearance.md)
