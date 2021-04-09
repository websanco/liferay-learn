# バンドラー構成プリセットについて

liferay-npm-bundlerには、デフォルトの構成プリセット[`liferay-npm-bundler-preset-standard`](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-preset-standard)　が付属しています。 `.npmbundlerrc` ファイルのnpmパッケージ名から `liferay-npm-bundler` プレフィックスを省略できます。 このプリセットは、自分でオーバーライドしない限り、ビルドプロセス用のいくつかのプラグインを構成し、自動的に使用されます（` .npmbundlerrc `がない場合も）。 このプリセットを使用してliferay-npm-bundlerを実行すると、 `<a href="https://github.com/liferay/liferay-npm-build-tools/blob/master/packages/liferay-npm-bundler-preset-standard/config.json">設定ファイル</a> liferay-npm-bundler-preset-standard`が適用されます。

``` json
{
  "/": {
    "plugins": ["resolve-linked-dependencies"],
    ".babelrc": {
      "presets": ["liferay-standard"]
    },
    "post-plugins": ["namespace-packages", "inject-imports-dependencies"]
  },
  "*": {
    "copy-plugins": ["exclude-imports"],
    "plugins": ["replace-browser-modules"],
    ".babelrc": {
      "presets": ["liferay-standard"]
    },
    "post-plugins": [
      "namespace-packages",
      "inject-imports-dependencies",
      "inject-peer-dependencies"
    ]
  }
}
```

上記の構成は、すべてのnpmパッケージ（`*`）について、前処理フェーズ（`プラグイン`）が `replace-browser-modules` プラグインを実行する必要があることを示しています。 これを `post-plugins` に設定すると、代わりにポストフェーズで実行されます。

``` note::
  プロジェクトの `` .npmbundlerrc``ファイルに独自の設定を追加することで、設定のプリセット値を上書きできます。 たとえば、上記の設定プリセットの例を使用して、独自の「.babelrc」値を「.npmbundlerrc」ファイルに定義して、定義された「liferay-standard」babelrcプリセットを上書きできます。
```

## Liferay標準プリセット

[`liferay-standard` プリセット](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-preset-liferay-standard) は、次のプラグインをパッケージに適用します。

  - [exclude-imports](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-exclude-imports)： `imports` セクションで宣言されたパッケージをビルドから除外します。

  - [inject-imports-dependencies](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-inject-imports-dependencies)：依存関係の `package.json` ファイルの `インポート` セクションで宣言された依存関係を注入します。

  - [inject-peer-dependencies](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-inject-peer-dependencies)：宣言されたピア依存関係（プロジェクトの `node_modules` フォルダーで解決されるため）を、依存関係の `package.json` ファイルに挿入します。

  - [namespace-packages](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-namespace-packages): プロジェクトごとのパッケージを分離し、衝突を避けるためにルートプロジェクトのパッケージ名に基づいた名前空間パッケージ名です。 これは、 `package.json` ファイル内の各パッケージ名の外観に `<project-package-name>$` を付加します。

  - [replace-browser-modules](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-replace-browser-modules): `ブラウザー`/`unpkg`/`/<code>jsdelivr`セクションに一覧されているモジュールに対して、サーバー側のファイルを、<1>package.json</code>の他のモジュールに対して置き換えます。

  - [resolve-linked-dependencies](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/liferay-npm-bundler-plugin-resolve-linked-dependencies)： `package.json` ファイル（たとえば、ローカルファイルシステムまたはGitHubから取得したファイル）に表示されるリンクされた依存関係のバージョンを、プロジェクトの `node_modules` ディレクトリで解決される実際のバージョン番号で置き換えます。

## Liferay Babelプリセット

バンドラーは、 [babel-preset-liferay-standard](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-preset-liferay-standard) プリセットを使用してBabelも実行し、次のプラグインを呼び出します。

  - [babel-plugin-normalize-requires](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-plugin-normalize-requires): AMD `require()`呼び出しを正規化します。

  - [babel-plugin-transform-node-env-inline](https://github.com/babel/minify/tree/master/packages/babel-plugin-transform-node-env-inline)： `NODE_ENV` 環境変数をインライン化し、それがバイナリ式の一部である場合（例： `process.env.NODE_ENV === "development"`）、その後静的に評価して交換してください。

  - [babel-plugin-minify-dead-code-elimination](https://www.npmjs.com/package/babel-plugin-minify-dead-code-elimination)：可能な場合はインラインバインディング。 結果として式を評価しようとし、到達できないプルーニングを行います。

  - [babel-plugin-wrap-modules-amd](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-plugin-wrap-modules-amd)：AMD `define（）` モジュール内にモジュールをラップします。

  - [babel-plugin-name-amd-modules](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-plugin-name-amd-modules)：パッケージ名、バージョン、モジュールパスに基づいてAMDモジュールに名前を付けます。

  - [babel-plugin-namespace-modules](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-plugin-namespace-modules)：ルートプロジェクトのパッケージ名に基づいた名前空間モジュール。先頭に `<project-package-name>$`付加します。 AMD `define()`モジュール内のモジュールをラップし、パッケージがプロジェクトごとにローカライズされ、クラッシュしないようにモジュール名の外観(`define()`または`require()`コール)で表示します。

  - [babel-plugin-namespace-amd-define](https://github.com/liferay/liferay-npm-build-tools/tree/master/packages/babel-plugin-namespace-amd-define): AMDの`define()`呼び出しにプレフィックスを追加します(デフォルトでは`Liferay)。ローダ`）。

これで、 `.npmbundlerrc` 使用可能な構成プリセットと、それらがどのように機能するかがわかりました。
