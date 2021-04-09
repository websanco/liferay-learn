# Angularプロジェクトを移行してLiferay npm Bundler 2.xを使用する

次の手順に従って、Angularプロジェクトを移行してliferay-npm-bundler 2.xを使用します。 liferay-npm-bundler 1.xはいくつかの変換ステップを実行するためにBabelに依存していましたが、これらの変換はバージョン2.xで自動的に適用されるようになりました。 したがって、プロジェクトからBabelを削除する必要があります。

1.  `package.json`の依存関係`liferay-npm-bundler`をバージョン2.xにアップデートします。

    ``` json
    {
      "devDependencies"：{
...
        "liferay-npm-bundler"： "^ 2.0.0"、
...
      }、
...
    }
    ```

2.  liferay-npm-bundler-preset-*</code>依存関係を全て`package.json`から削除します。liferay-npm-bundler 2.xにはデフォルトでこれらが含まれるためです。

3.  `.npmbundlerrc` ファイルで構成したバンドラープリセットを削除します。 liferay-npm-bundler 2.xには、すべてのフレームワークを自動的に処理する1つのスマートプリセットが含まれています。

4.  `tsconfig.json` ファイルを開き、 `"module"： "amd"` コンパイラオプションを以下に示す構成に置き換えて、CommonJSモジュールを生成します。

    ``` json
    {
      "compilerOptions"：{
...
        "module": "commonjs",
        ...
      }
}
    ```

5.  `.babelrc` ファイルを削除して、Babel構成を削除します。

6.  `package.json` ビルドプロセスからBabelを削除して、以下の設定と一致させます。

    ``` json
    {
      "scripts": {
        "build": "tsc && liferay-npm-bundler"
      },
      ...
    }
    ```

7.  `package.json`から次のBabel依存関係を削除します` <em x-id="3">devDependencies</em>：
<pre><code class="json">    "babel-cli": "6.26.0",
    "babel-preset-liferay-amd": "1.2.2"
`</pre>

プロジェクトは、liferay-npm-bundler 2.xを使用するように移行されます。

## 関連情報

  - [AMD用のnpmモジュールのフォーマット](../how-the-bundler-formats-js-modules.md)
  - [liferay-npm-bundler 1.xと2.xの変更点](../changes-between-bundler-1.x-and-2.x.md)
