# フラグメントツールキットの使用

フラグメント ツールキットは、お気に入りのツールを使用してフラグメントをローカルで作成および管理するのに役立ちます。 ここでは、ツールキットを使用して、サンプルのフラグメントを含むフラグメント プロジェクトを生成し、デプロイして、独自のフラグメント コレクションを追加します。

## ツールキットの設定

ツールキットには次のソフトウェアが必要です。

  - [NPM](https://www.npmjs.com/)
  - [NodeJS](https://nodejs.org/)
  - [Yeoman](https://yeoman.io/)
  - [Yarn](https://classic.yarnpkg.com/)

NPM と Yarn は、依存するツールキットとモジュールをインストールするために使用するパッケージ マネージャーです。

Liferay の [setup\_tutorial.sh](https://github.com/liferay/liferay-learn/blob/master/docs/_template/js/setup_tutorial.sh) スクリプトは、Yeoman、Yarn、およびツールキットを設定するためのコマンドを提供します。 スクリプトは、サンプルの ZIP ファイルと、サンプルのすべての JavaScript プロジェクトの ZIP ファイルで使用できます。

1.  [Node.js LTS](https://nodejs.org/en/download/) を介して NPM と Node.js をインストールすることから始めます。

2.  サンプルの JavaScript プロジェクトの ZIP ファイルをダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-page-fragments/liferay-x2y6.zip -O
    ```

    ``` bash
    unzip liferay-x2y6.zip
    ```

3.  `setup_tutorial.sh` スクリプトを使用して、フラグメント ツールキットとその依存関係を設定します。

    ``` bash
    cd liferay-x2y6
    ```

    ``` bash
    ./setup_tutorial.sh
    ```

スクリプトによって報告された満たしていない要件をすべて解決し、環境の準備が整ったと報告されるまでスクリプトを再実行してください。

## フラグメント プロジェクトを作成する

フラグメントツールキットの `yo liferay-fragments` コマンドは、フラグメント プロジェクトを生成するためのインターフェイスを起動します。

```{warning}
フラグメントプロジェクトをネストしないでください。 新しいフラグメントプロジェクトは、既存のフラグメントプロジェクトとは別の場所に作成してください。
```

`liferay-x2y6` プロジェクト フォルダにいる場合は、それを終了します (例: `cd ..`)。

フラグメント プロジェクトを生成する方法は次のとおりです。

1.  `yo liferay-fragments` コマンドを実行して、プロジェクト ジェネレータを起動します。
   
        yo liferay-fragments

2.  プロジェクトに名前を付けます。 この例では、Enter をクリックしてデフォルトのプロジェクト名を受け入れます。
   
        ? Project name (Sample Liferay Fragments)

3.  サンプル コンテンツを追加するかどうかを示します。 この例では、 `Yes` と入力します。
   
        ? Add sample content? Yes
        Creating directory
           force .yo-rc.json
          create src/.gitkeep
          create .editorconfig
          create .gitignore
          create liferay-npm-bundler.config.js
          create package.json
          create README.md
        Adding sample content...
        Warning: some of these fragments are not compatible all
        portal versions, please check the generator documentation before using them:
        https://www.npmjs.com/package/generator-liferay-fragments#creating-new-fragments
        Running yarn...
        Done!
        You're ready to create fragments.
          create src/sample-collection/collection.json
          create src/sample-collection/sample-fragment/index.html
          create src/sample-collection/sample-fragment/main.js
          create src/sample-collection/sample-fragment/styles.css
          create src/sample-collection/sample-fragment/fragment.json
          create src/sample-collection/sample-fragment/configuration.json
          create src/sample-collection/sample-fragment-with-new-editables/index.html
          create src/sample-collection/sample-fragment-with-new-editables/main.js
          create src/sample-collection/sample-fragment-with-new-editables/styles.css
          create src/sample-collection/sample-fragment-with-new-editables/fragment.json
          create src/sample-collection/sample-fragment-with-new-editables/configuration.json
          create src/sample-collection/sample-fragment-with-react/index.html
          create src/sample-collection/sample-fragment-with-react/main.js
          create src/sample-collection/sample-fragment-with-react/styles.css
          create src/sample-collection/sample-fragment-with-react/fragment.json
          create src/sample-collection/sample-fragment-with-react/configuration.json

フラグメント プロジェクトが生成されました。

ジェネレータは、プロジェクト名から派生したフォルダに各プロジェクトを作成します。 この例では、プロジェクト名を `Sample Liferay Fragments` としたので、ジェネレータは `sample-liferay-fragments`というプロジェクト フォルダを作成します。

## サンプル コレクションのインポート

フラグメントを表示するには、サンプルのフラグメント コレクションを Liferay にインポートします。

1.  以下のコマンドを実行して、Docker コンテナを起動します。

    ``` bash
    docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

2.  `sample-liferay-fragments` プロジェクト フォルダで `yarn run import` コマンドを呼び出して、フラグメント コレクションを Liferay インスタンスにインポートします。 あるいは、[手動でフラグメントコレクションをインポート](../../displaying-content/using-fragments/managing-page-fragments.md)することもできます。

    ``` bash
    yarn run import
    yarn run v1.22.10
    warning package.json: No license field
    $ yo liferay-fragments:import
    ? Liferay host & port http://localhost:8080
    ? Username test@liferay.com
    ? Password [hidden]
    Checking connection...
    Connection successful

    ? Company ID liferay.com
    ? Group ID Liferay DXP
    Building project...
    Importing project...
    Project imported
        force .yo-rc.json
        force ../../.yo-rc-global.json
    Done in 21.43s.
    ```

3.  フラグメントコレクションが利用可能であることを確認します。 ブラウザで`https://localhost:8080`にアクセスし、画面左側のサイトメニューで、*[デザイン]* → *[フラグメント]* に移動します。 コレクションがコレクションリストに表示されます。

    ![コレクションが利用可能です。](./using-the-fragments-toolkit/images/01.png)

    ```{note}
    Liferay DXP 7.1および7.2では、代わりにプロダクトメニューの*サイト* → *サイトビルダー* → *ページ フラグメント*に移動して、*フラグメント*ページを表示します。
    ```

4.  フラグメントを選択して、フラグメント エディターで表示します。

    ![React を使用したフラグメントのサンプルです。](./using-the-fragments-toolkit/images/02.png)

フラグメントコレクションを正常にデプロイしました。

## コレクション形式の概要

フラグメント コレクションは次のプロジェクト構造を使用します。

  - `collection.json`: コレクションを説明するテキスト ファイル。

    ``` json
    {
        "description": "Optional description",
        "name": "Collection name"
    }
    ```

  - `language.properties` (オプション): コレクションに定義された言語キー。

  - `[fragment-name]/`: フラグメントのすべてのファイルを含むフォルダ。

      - `configuration.json`(オプション): フラグメントの構成を定義する JSON ファイル。 詳細は、[Adding Configuration Options to Fragments](./adding-configuration-options-to-fragments.md)を参照してください。

      - `fragment.json`: フラグメントを説明するテキスト ファイル。

        ``` json
        {
            "cssPath": "styles.css",
            "configurationPath": "configuration.json",
            "htmlPath": "index.html",
            "jsPath": "main.js",
            "name": "Fragment name",
            "type": "[component|react]"
        }
        ```

        CSS、構成、HTML、および JavaScript ファイルの名前で `*Path` プロパティを更新します。

      - `index.html`: フラグメントの HTML ソース。

      - `main.js`: フラグメントの JavaScript ソース。

      - `styles.css`: フラグメントの CSS ソース。

  - `resources/` (オプション): フラグメントが必要とする追加の画像またはファイルを含むフォルダ。 詳細は、[Including Default Resources in Fragments](./including-default-resources-with-fragments.md)を参照してください。

フラグメント ツールキットを使うと、フラグメント コレクションを簡単に作成できます。

## 新しいコレクションとフラグメントを追加する

`yarn run add-collection` コマンドを使用して既存のプロジェクトにフラグメント コレクションを作成し、コレクションの名前とオプションで説明を入力してプロンプトに答えます。

``` bash
yarn run add-collection

> yo liferay-fragments:collection

? Collection name (required) My Collection
? Collection description (optional) This is my new Fragment Collection.
   create src/my-collection/collection.json
```

結果の `collection.json` ファイル:

``` json
{
    "description": "This is my new Fragment Collection.",
    "name": "My Collection"
}
```

## 新規フラグメントを作成する

`add-fragment` コマンドを入力するごとにフラグメントが生成されます。

1.  `add-fragment` コマンドを実行します。

    ``` bash
    yarn run add-fragment
    ```

    CLI がプロセスを開始します:

    ``` bash
    > yo liferay-fragments:fragment
    ```

2.  フラグメントに名前を付けます。

    ``` bash
    ? Fragment name (required) My Jumbotron
    ```

3.  React または別の JavaScript フレームワークを使用するかどうかを選択します。 React には Liferay 7.3 以降が必要です。 このチュートリアルでは、React の使用を拒否してください。

    ``` bash
    ? Use React (or other JS framework)? No
    ```

4.  Liferay 7.3 以降の場合は、新しい編集可能な要素の構文を使用します。

    ``` bash
    ? Use new data-lfr editable syntax? Yes
    ```

    ```{note}
    フラグメントで React の使用を受け入れた場合、ツールキットは Liferay 7.3 以降を使用していると想定し、新しい編集可能な要素の構文を構成します。
    ```

    ```{note}
    Liferay の編集可能なデータ構文の詳細は、[Fragment-Specific Tags](../reference/fragments/fragment-specific-tags-reference.md) を参照してください。
    ```

5.  先ほど作成したコレクションを選択します (`My Collection`)。

    ``` bash
    ? Choose a collection (my-collection)
       create src/my-collection/my-jumbotron/index.html
       create src/my-collection/my-jumbotron/main.js
       create src/my-collection/my-jumbotron/styles.css
       create src/my-collection/my-jumbotron/fragment.json
       create src/my-collection/my-jumbotron/configuration.json
    ```

    ```{note}
    `fragment.json` はフラグメントの CSS、HTML、JavaScript へのパスを定義します。 これらのファイル名のいずれかを変更した場合は、`fragment.json` 内のパスを更新してください。
    ```

フラグメントの `index.html` は次のとおりです。

``` html
<div class="my-jumbotron">
    <h1 data-lfr-editable-id="title" data-lfr-editable-type="text">
        My Jumbotron
    </h1>
</div>
```

フラグメント HTML は、`data-lfr` の新しい編集可能な構文を使用します。

## フラグメントを編集する

フラグメントの任意の部分を変更します。

  - HTML (`index.html`)
  - CSS (`styles.css`)
  - JavaScript (`main.js`)
  - [構成オプション](./adding-configuration-options-to-fragments.md) (`configuration.json`)

たとえば、上記の HTML から構築し、[Clay](https://clayui.com/) の [Bootstrap](https://getbootstrap.com/) ベースのコンポーネントを使用して、次の `index.html` に示すように、注目を集める見出しと説明を持つフラグメントを作成できます。

``` html
<div class="component-my-jumbotron">
    <div class="jumbotron">
        <h1
            class="display-4"
            data-lfr-editable-id="01-title"
            data-lfr-editable-type="rich-text"
        >
            Editable Jumbotron Headline
        </h1>

        <p
            class="lead"
            data-lfr-editable-id="02-lead"
            data-lfr-editable-type="rich-text"
        >
            Edit this text to call extra attention to featured content or information.
        </p>

        <hr />

        <p
            data-lfr-editable-id="03-text"
            data-lfr-editable-type="rich-text"
        >
            Edit this text to provide more information.
        </p>

        <a
            class="btn btn-primary btn-lg"
            data-lfr-editable-id="04-label"
            data-lfr-editable-type="link"
            href="#"
        >
            Editable Link
        </a>
    </div>
</div>
```

```{note}
Liferay 7.2 以下を使用している場合は、 [Fragment-Specific Tags](../reference/fragments/fragment-specific-tags-reference.md) で説明されているように、`data-lfr-editable-[id|type]` 属性を削除し、`lfr-editable` 要素でコンテンツ要素をラップしてください。
```

最初の `div` 要素の `class="component-my-jumbotron"` 属性は、ページ上のこのフラグメントを一意に識別しようとします。

```{tip}
フラグメントの HTML ファイルで、メインのラッパー要素 (上記の例では `<div>`) を使用してフラグメントを一意に識別し、ページ上の他のコンポーネントと競合しないようにします。
```

次に、 `<div class="jumbotron"/>` 要素がコンテンツをラップし、 [Bootstrap](https://getbootstrap.com/) のジャンボトロン コンポーネントを適用します。 このコンポーネントは、コンテンツを際立たせます。 コンテンツ要素は次のとおりです。

  - `<h1 class="display-4" ...>Editable Jumbotron ...` は、フラグメントの見出しを作成します。 [Bootstrap](https://getbootstrap.com/) の `display-4` サイズ スタイルを使用します。 `data-lfr-editable-` 属性は、見出しテキストを[編集可能](../reference/fragments/fragment-specific-tags-reference.md)にします。 `data-lfr-editable-id="03-text"` 属性は要素を識別し、`data-lfr-editable-type="rich-text"` 属性はコンテンツ タイプを宣言します。
  - `<p class="lead" ...>Edit this text ...` は、Clay の [`lead`](https://clayui.com/docs/css/content/typography.html#css-lead) スタイル コンポーネントで識別されるリード本文テキストです。 `data-lfr-editable-` 属性は、段落を編集可能にします。
  - `<hr/>` 要素と次の `<p ...` 要素は、それぞれ水平方向の罫線と別の編集可能な段落を生成します。
  - `<a class="btn btn-primary btn-lg" ...` は変更可能なリンクを指定します。 `btn-primary` クラスはそれをメイン ボタンとしてスタイル設定し、`btn-lg` はそれを拡大します。 `href="#"` 属性は、リンクをクリックしたときにユーザーをページの上部に移動します。 `data-lfr-editable-type="link"` 属性は、リンクを[編集可能](../reference/fragments/fragment-specific-tags-reference.md)にします。

フラグメントにリソースを含めることもできます。 詳細は、[Including Default Resources in Fragments](./including-default-resources-with-fragments.md)を参照してください。

```{note}
フラグメントと設定オブジェクトは JavaScript の引数として渡され、それぞれ `fragmentElement` と `configuration` パラメーターとして利用できます。
```

## 新しいフラグメントをインポートする

元のサンプルフラグメントと同じように、新しいフラグメントをインポートできます。

1.  import コマンドを実行し、資格情報を提供します。

    ``` bash
    yarn run import
    ```

2.  新しいフラグメントコレクションが利用可能であることを確認します。 ブラウザで`https://localhost:8080`にアクセスし、画面左側のサイトメニューで、*[デザイン]* → *[フラグメント]* に移動します。 コレクションがコレクションリストに表示されます。

![コレクションが利用可能です。](./using-the-fragments-toolkit/images/03.png)

これで、フラグメント ツールキットを使用してフラグメントを作成および管理する方法がわかりました。 ツールキット コマンドの詳細は、[Fragments Toolkit Command Reference](../reference/fragments/fragments-toolkit-command-reference.md) を参照してください。

## 関連情報

  - [Creating a Contributed Fragment Collection](./creating-a-contributed-fragment-collection.md)
  - [フラグメントエディターの使用](./using-the-fragments-editor.md)
  - [Fragments Toolkit Command Reference](../reference/fragments/fragments-toolkit-command-reference.md)
