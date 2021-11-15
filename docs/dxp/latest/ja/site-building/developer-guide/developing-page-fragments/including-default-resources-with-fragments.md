# フラグメントにデフォルトのリソースを含める

フラグメントコレクションに画像（例： `.gif`、`.jpg`、`.jpeg`、`.png`）を含めて、フラグメントで使用できるようにすることができます。 [ドキュメントとメディア](../../../content-authoring-and-management/documents-and-media/introduction-to-documents-and-media.md)などの他のアプリケーションではなく、フラグメントと一緒に画像を保持すると便利です。 ここでは、フラグメント コレクションに画像リソースを含める方法と、フラグメントで画像リソースを使用する方法を学びます。

## リソースとともにフラグメント コレクションをインポートする

まず、フラグメント コレクションのサンプルをインポートして、フラグメント リソースがどのように機能するかを確認します。

1.  以下のコマンドを実行して、Docker コンテナを起動します。

    ``` bash
    docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
    ```

2.  [サンプルのリソースフラグメントコレクション](https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-page-fragments/liferay-i6r3.zip)をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/dxp/7.x/en/site-building/developer-guide/developing-page-fragments/liferay-i6r3.zip -O
    ```

    ``` bash
    unzip liferay-i6r3.zip
    ```

3.  フラグメント ツールキットをセットアップします。

    ``` bash
    cd liferay-i6r3
    ```

    ``` bash
    ./setup-tutorial.sh
    ```


    スクリプトによって報告された満たしていない要件をすべて解決し、環境の準備が整ったと報告されるまでスクリプトを再実行してください。

4.  以下の `yarn run import` コマンドを使用して、フラグメントツールキットでフラグメントコレクションをDockerコンテナにインポートします。

    ``` bash
    yarn run import

    ...
    ? Liferay host & port http://localhost:8080
    ? Username test@liferay.com
    ? Password [hidden]

    Checking connection...
    Connection successful

    ? Company ID liferay.com
    ? Group ID Liferay

    Importing project...

    Generating zip file

    build/liferay-fragments.zip file created
    Import them to your liferay-portal to start using them
    ✔ Fragment I6R3 Card imported
    ```

5.  フラグメントコレクションが利用可能であることを確認します。 ブラウザで`https://localhost:8080`にアクセスし、画面左側のサイトメニューで、*[デザイン]* → *[フラグメント]* に移動します。 I6R3 コレクションは、他のコレクションとともに表示されます。

6.  *[I6R3 コレクション]* をクリックします。

7.  *[Resources]* タブをクリックします。 `books.png` 画像がリソース リストに表示されます。

    ![リソースはコレクションで使用できます。](./including-default-resources-with-fragments/images/01.png)

## リソース形式の概要

画像リソースは、コレクションの `リソース` フォルダに含まれています。 フラグメント コレクションのファイル構造は次のとおりです。

  - `collection.json`: `{"name":"Collection name","description":"Collection description"}` の形式でコレクションを説明するテキスト ファイル。
  - `[fragment-name]/`: フラグメントのすべてのファイルが含まれています。
  - `resources/`: コレクションのすべてのフラグメントで使用可能なファイルが含まれています。

<!-- end list -->

```{tip}
または、 [フラグメントエディタ―](./using-the-fragments-editor.md) の*Resources*タブから画像をアップロードすることもできます。
```

画像ファイルはフラグメントの HTML で構文 `[resources:image-name.extension]` を使用して参照されます。 サンプルのフラグメント HTML には、次の `img` 要素があります。

``` html
<img
    class="card-img-top"
    src="[resources:books.png]"
/>
```

```{note}
リソース名は大文字と小文字が区別され、参照内の名前と大文字と小文字が正確に一致する必要があります。
```

```{tip}
`img[src="[resources:image-name.extension]"]` という構文を使用して、CSS で画像リソースのスタイルを設定できます。
```

## 新しいリソースを含める

フラグメントにリソースを組み込んで参照する方法がわかったので、サンプルのフラグメント コレクションで新しいリソースを使用できます。

1.  <https://www.freeimages.com/> からの画像などを `liferay-i6r3.zip/src/i6r3-collection/resources/` フォルダにコピーします。

2.  上記と同様に、Docker コンテナにフラグメントコレクションを再度インポートします。

    ``` bash
    yarn run import
    ```

3.  フラグメントコレクションにリソースが含まれていることを確認します。 UI の *[Fragments]* ページで、 *[I6R3 コレクション]* を選択し、*[Resources]* タブをクリックします。 新しい画像がリソース リストに表示されます。

    ![新しい画像がコレクションのリソースリストに表示されます。](./including-default-resources-with-fragments/images/02.png)

4.  コレクションの *[Fragments]* タブを選択してから *[I6R3 カード]* フラグメントをクリックして、*[I6R3 カード]* フラグメントを開きます。 フラグメント ソースがフラグメント エディターに表示されます。

5.  新しい画像リソースを使用するようにフラグメントを更新します。 `img` 要素の `src="[resources:books.png]"` 属性から `book.png` を削除し、`src="[resources:` の後に新しい画像ファイルの名前を入力します。 画像ファイル名の属性値がカーソルの下に表示されます。 その属性値を選択します。

    ![フラグメントエディタ―には、一致するリソースが一覧表示されます。](./including-default-resources-with-fragments/images/03.png)

6.  フラグメントが新しい画像をレンダリングすることを確認します。

![フラグメントには新しい画像が含まれています。](./including-default-resources-with-fragments/images/04.png)

```{note}
フラグメント コレクション内のすべてのフラグメントは、コレクションのリソースにアクセスできます。
```

これで、画像リソースのフラグメント コレクションの使用方法がわかりました。

## 関連情報

  - [Adding Configuration Options to Fragments](./adding-configuration-options-to-fragments.md)
  - [Auto-deploying Fragments](./auto-deploying-fragments.md)
  - [フラグメントエディターの使用](./using-the-fragments-editor.md)
