# liferay-npm-bundlerの新しいモードを使用するためのプロジェクトの移行

[issue #303](https://github.com/liferay/liferay-js-toolkit/issues/303)以降、liferay-npm-bundlerには2つの操作モードが用意されています。 バンドラーを実行する前にファイルを前処理したり、Webpack（一連のルールを介してソースファイルを処理する）のように、バンドラーを使用してプロセス全体を処理したりできます。 次の手順に従ってプロジェクトを移行し、新しい構成モードを使用して、バンドラーがプロセス全体を処理できるようにします。

1. プロジェクトの `package.json` ファイルを開き、liferay-npm-bundlerのみを使用するように `build` スクリプトを更新します。

    古いバージョン：

    ```json
    {
      "scripts":{
        "build": "babel --source-maps -d build src && liferay-npm-bundler"
      }
    }
    ```

    新しいバージョン：

    ```json
    {
      "scripts":{
        "build": "liferay-npm-bundler"
      }
    }
    ```

1. プロジェクトの `.npmbundlerrc` ファイルでバンドラーが使用するルールを定義します（たとえば、ファイルをトランスパイルするためにbabelを実行する）。 以下の設定例では、 `babel-loader` を使用してJavaScriptファイルをトランスパイルするためのルールを定義しています。 デフォルトローダーの完全なリストについては、 [デフォルトローダーリファレンス](../default-bundler-loaders.md)を参照してください。 カスタムローダーを作成するには、[Creating Custom Loaders for the Bundler](../../developer/creating-custom-loaders-for-the-bundler.md) <!-- TODO: Fix link --> に記載の手順に従ってください。 liferay-npm-bundlerはbabelで `/src/` の `* .js` ファイルを処理し、デフォルトの `/build/` フォルダに結果を書き込みます。

    ```json
    {
      "sources": ["src"],
      "rules": [
        {
          "test": "\\.js$",
          "exclude": "node_modules",
          "use": [
            {
              "loader": "babel-loader",
              "options": {
                "presets": ["env"]
              }
            }
          ]
        }
      ]
    }
    ```

    ```{note}
    liferay-npm-bundlerの新しいモードはwebpackと非常によく似ていますが、** webpackとは互換性がありません**。 Webrayは単一のJavaScriptバンドルファイルを作成し、liferay-npm-bundlerはAMDローダーをターゲットにします。
    ```

## 関連情報

* [Default liferay-npm-bundler Loaders](../default-bundler-loaders.md)
* [Understanding liferay-npm-bundler's Loaders](../understanding-bundler-loaders.md)
