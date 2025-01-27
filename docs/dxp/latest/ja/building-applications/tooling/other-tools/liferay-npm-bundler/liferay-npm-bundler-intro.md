# Liferay npmバンドラー

liferay-npm-bundlerは、Liferayポータルをプラットフォームとして対象とし、（通常のWebアプリケーションではなく）ウィジェットからnpmパッケージを使用していることを前提とするバンドラー（ [Webpack](https://webpack.github.io/) または [Browserify](http://browserify.org/) ）です。

ウィジェット内でnpmパッケージを実行するためのワークフローは、標準のバンドラーとは少し異なります。 JavaScriptを単一のファイルにバンドルする代わりに、完全なWebページが組み立てられたときに、ブラウザーですべてのパッケージを **リンク** する必要があります。 ウィジェットは、それぞれが独自のコピーをロードするのではなく、モジュールの共通バージョンを共有できます。 liferay-npm-bundlerがこれを処理します。

```{note}
また、 [project's Wiki] (https://github.com/liferay/liferay-npm-build-tools/wiki)にもliferay-npm-bundlerの情報があります。
```

<a name="how-the-liferay-npm-bundler-works-internally" />

## Liferay npmバンドラーが内部でどのように機能するか

liferay-npm-bundlerはウィジェットプロジェクトを取得し、そのファイル（npmパッケージを含む）をビルドフォルダーに出力するため、標準のウィジェットビルド（Gradle）でOSGiバンドルを生成できます。 ビルドフォルダーの構造の詳細については、 [OSGiバンドルとnpmパッケージ構造](./the-structure-of-osgi-bundles-containing-npm-packages.md) を参照してください。

liferay-npm-bundlerは、以下のプロセスを使用してOSGiバンドルを作成します。

1. プロジェクトの `package.json` ファイルを出力ディレクトリにコピーします。
1. プロジェクトの依存関係ツリーを走査して、その依存関係を判別します。
1. プロジェクトの場合、

    a. ルールを介して、 `.npmbundlerrc` 構成で指定されたソースファイルを実行します。

    b. 構成されたプラグインを使用してプロジェクトのパッケージを前処理します。

    c. プロジェクト内の `.js` ファイルごとにプラグインを構成して [Babel](https://babeljs.io/) を実行します。

    d. 構成されたプラグインを使用してプロジェクトパッケージを後処理します。

1. npmパッケージの依存関係ごとに、

    a. npmパッケージを出力フォルダーにコピーし、バンドル名の前に付けます。 バンドルは、標準のnode_modulesツリー形式ではなく、プレーン **バンドル名$package** @ **バージョン** 形式でパッケージを保存することに注意してください。 何がコピーされるかを判別するために、バンドラーはプラグインを呼び出してパッケージファイルリストをフィルタリングします。

    b. パッケージファイルに対してルールを実行します。

    c. 設定済みのプラグインを使用してnpmパッケージを前処理します。

    d. npmパッケージ内の `.js` ファイルごとにプラグインを構成して [Babel](https://babeljs.io/) を実行します。

    e. 構成されたプラグインを使用してnpmパッケージを後処理します。

前処理ステップと後処理ステップの間の唯一の違いは、それらがいつ実行されるかだけです（それぞれ、Babelが実行される前か後か）。 このワークフローの実行中、liferay-npm-bundlerは構成されたすべてのプラグインを呼び出し、npmパッケージで変換を実行できるようにします（たとえば、 `package.json` ファイルの変更またはファイルの削除や移動）。

```{note}
プレ、ポスト、バベルの各フェーズは、古い動作モード用に設計され、（[Migrating Your Project to Use the New Mode](./bundler-migration-guide/migrating-to-the-new-mode.mdを参照してください）新しいモードのルールに徐々に置き換えられます。
```

このリファレンスでは、liferay-npm-bundlerの構成、デフォルトのプリセット、形式などについて説明しています。
