# デフォルトのバンドラーローダー

デフォルトでは、liferay-npm-bundlerでいくつかの [ローダー](./understanding-bundler-loaders.md) を使用できます。

  - [`babel-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-babel-loader)： [Babel](https://babeljs.io/)ソースファイルを処理します。 これにより、バンドラーの前の余分なビルド手順が回避されます。
  - [`コピーローダー`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-copy-loader)：ソースファイル（静的アセット）を出力フォルダーにコピーします。
  - [`css-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-css-loader)：CSSファイルを、ロードされるとDOMに挿入されるJavaScriptモジュールに変換します。
  - [`json-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-json-loader)：JSONファイルのコンテンツをオブジェクトとしてエクスポートするJavaScriptモジュールを生成するため、 `require（）` 呼び出しでJSONファイルを含めることができます。
  - [`sass-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-sass-loader)：ソースファイルで `node-sass` または `sass` を実行するため、静的CSSファイルを生成できます。 `スタイルローダー`前にチェーンできます。
  - [`style-loader`](https://github.com/liferay/liferay-js-toolkit/tree/master/packages/liferay-npm-bundler-loader-style-loader)：CSSファイルをJavaScriptモジュールに変換します。読み込まれると、CSSコンテンツがDOMに挿入されます。 次に、 `require（）` 呼び出しでCSSファイルを含めることができます。
参照してください [のLiferay-JS-ツールキットローダーが披露](https://github.com/izaera/liferay-js-toolkit-showcase/tree/loaders) のLiferay-NPM-バンドラのローダーの例ユースケースのために。 デフォルトのローダーが要件を満たしていない場合は、[バンドラ用のカスタムローダーを作成](../../creating-custom-loaders-for-the-liferay-npm-bundler.md)<の手順に従ってください。 独自のローダーを作成します。
