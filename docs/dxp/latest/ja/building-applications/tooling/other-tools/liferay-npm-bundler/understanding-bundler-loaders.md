# Liferay npmバンドラーローダーについて

liferay-npm-bundlerのメカニズムは、webpackに似ています。 webpackと同様に、liferay-npm-bundlerは、最終出力を生成する前にプロジェクトのソースファイルを変換するローダーを含む一連のルールを使用してファイルを処理します。

```{note}
webpackは単一のJSバンドルファイルを作成しますが、liferay-npm-bundlerはAMDローダーを対象としているため、webpackとliferay-npm-bundlerローダーは互換性がありません。
```

ローダーとは、メインモジュールにソースファイルを受け取り、ローダーの設定に基づいて新規または修正されたファイルを返す関数をエクスポートするnpmパッケージです。 例えば、 [babel-loader](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-babel-loader) はES6 + JavaScriptファイルを受信し、それらでBabelを実行し、生成されたソースマップとともに変換されたES5ファイルを返します。 このパターンを利用して、 [カスタムローダーを作成](../developer/creating-custom-loaders-for-the-bundler.md)することができます。 ローダー関数の例をいくつかご紹介します：

* JavaScriptファイルをBabelまたはTSC経由で渡す
* CSSファイルを、CSSをHTMLページに動的に挿入するJSモジュールに変換する
* SASSでCSSファイルを処理する
* [インターフェイス記述言語（IDL）](https://en.wikipedia.org/wiki/Interface_description_language) ファイルに基づいてコードを生成するツールを作成する

ローダーは、プロジェクトの`.npmbundlerrc`ファイルを介して構成されます。 ローダーの設定は、2つの主要なオプションを使用して指定されます。

* `ソース`：処理するソースファイルを含むフォルダ
* `ルール`：ローダー、オプション---該当する場合---、および処理するファイルを決定する正規表現。

設定の要件とオプションの詳細については、 [Understanding the `.npmbundlerrc`'s Structure](./npmbundlerrc-structure.md#package-processing-options) を参照してください。

ローダーはチェーン化できます。 ローダーは、 `use`プロパティにリストされている順序でファイルを処理します。 ファイルは、ルールによってファイルが処理されるまで、最初のローダーに渡されて処理され、次のローダーに送信されます。 SASSファイルをsass-loaderを使用してCSSに変換するなどの複雑なプロセスを実行してから、スタイルローダーを使用してそれをJavaScriptモジュールに変換できます。 ルールが適用されると、liferay-npm-bundlerは、バンドラープラグインのpre、post、およびbabelフェーズを続行します。
