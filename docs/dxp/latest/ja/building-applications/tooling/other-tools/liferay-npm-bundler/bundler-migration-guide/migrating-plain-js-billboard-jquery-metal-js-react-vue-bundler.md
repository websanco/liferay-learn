# プレーンJavaScript、Billboard JS、JQuery、Metal JS、React、またはVue JSプロジェクトを移行してLiferay npm Bundler 2.xを使用する

以下に示すフレームワークプロジェクトを移行してliferay-npm-bundler 2.xを使用するには、次の手順に従います。

* プレーンJavaScriptプロジェクト
* Billboard.jsプロジェクト
* jQueryプロジェクト
* Metal.jsプロジェクト
* Reactプロジェクト
* Vue.jsプロジェクト

ソースファイルをトランスパイルするにはBabelが必要ですが、トランスフォーマーに使用されるBabelプリセットを、バンドル1.xが課したプロジェクトから削除する必要があります。 liferay-npm-bundler 2.xは、デフォルトでこれらの変換を処理します。

1. `package.json`の依存関係`liferay-npm-bundler`をバージョン2.xにアップデートします。

    ```json
    {
      "devDependencies": {
        ...
        "liferay-npm-bundler": "^2.0.0",
        ...
      },
      ...
    }
    ```

1. すべての`liferay-npm-bundler-preset-*`依存関係を`package.json`から削除します。liferay-npm-bundler 2.xにはデフォルトでこれらが含まれているためです。
1. `.npmbundlerrc` ファイルで構成したバンドラープリセットを削除します。 liferay-npm-bundler 2.xには、すべてのフレームワークを自動的に処理する1つのスマートプリセットが含まれています。
1. プロジェクトの `.babelrc` ファイルから *liferay-project* プリセットを削除します。 残すべきは、以下に示す `es2015` プリセットだけです。

    ```json
    {
      "presets": ["es2015"]
    }
    ```

    プロジェクトでReactを使用している場合は、`react` プリセットも残っていることを確認してください。

    ```json    
    {
      "presets": ["es2015", "react"]
    }
    ```

1. `package.json`から `babel-preset-liferay-project` 依存関係を削除します。

プロジェクトは、liferay-npm-bundler 2.xを使用するように移行されます。

## 関連情報

* [Formatting Your npm Modules for AMD](../how-the-bundler-formats-js-modules.md)<!-- * \[Using the NPMResolver API in Your Portlets\](/docs/7-2/frameworks/-/knowledge_base/f/using-the-npmresolver-api-in-your-portlets) TODO: Update link-->* [What Changed between liferay-npm-bundler 1.x and 2.x](../changes-between-bundler-1.x-and-2.x.md)
