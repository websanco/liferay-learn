# フラグメントにデフォルトのリソースを含める

フラグメントセットに画像（例： `.gif`、`.jpg`、`.jpeg`、`.png`）を含めて、フラグメントで使用できるようにすることができます。 [ドキュメントとメディア](../../../content-authoring-and-management/documents-and-media/documents-and-media-overview.md)などの他のアプリケーションではなく、フラグメントと一緒に画像を保持すると便利です。 ここでは、フラグメント セットに画像リソースを含める方法と、フラグメントで画像リソースを使用する方法を学びます。

```{note}
Liferay DXP 7.4以降の場合、フラグメントコレクションはLiferay UIではフラグメントセットと呼ばれます。
```

<a name="import-a-fragment-set-with-resources" />

## リソースとともにフラグメント セットをインポートする

```{include} /_snippets/run-liferay-dxp.md
```

次に、フラグメント セットのサンプルをインポートして、フラグメント リソースがどのように機能するかを確認します。

1. [サンプルのリソースフラグメントセット](https://learn.liferay.com/dxp/latest/ja/site-building/developer-guide/developing-page-fragments/liferay-i6r3.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/site-building/developer-guide/developing-page-fragments/liferay-i6r3.zip -O
    ```

    ```bash
    unzip liferay-i6r3.zip
    ```

1. フラグメント ツールキットをセットアップします。

    ```bash
    cd liferay-i6r3
    ```

    ```bash
    ./setup_tutorial.sh
    ```


    スクリプトによって報告された満たしていない要件をすべて解決し、環境の準備が整ったと報告されるまでスクリプトを再実行してください。

1. 以下の `yarn run import` コマンドを使用して、フラグメントツールキットでフラグメントセットをDockerコンテナにインポートします。

    ```bash
    yarn run import

    ...
    ? Liferay host & port http://localhost:8080
    ? Username test@liferay.com
    ? Password [hidden]

    Checking connection...
    Connection successful

    ? Company ID liferay.com
    ? Group ID Liferay DXP

    Building project...
    Importing project...

    ✔ Fragment I6R3 Card imported

    Project Imported
    ```

1. フラグメントセットが利用可能であることを確認します。 ブラウザで`https://localhost:8080`にアクセスし、画面左側のサイトメニューで、 [**デザイン**] &rarr; [**フラグメント**] に移動します。 I6R3セットが他のフラグメントセットと一緒に表示されるはずです。

1. ［**I6R3 セット**］ をクリックします。

1. [**Resources**] タブをクリックします。 `books.png` 画像がリソース リストに表示されます。

    ![リソースはフラグメントセットで使用できます。](./including-default-resources-with-fragments/images/01.png)

<a name="fragment-set-structure-with-resources" />

## リソースを含むフラグメントセットストラクチャー

リソースを含むフラグメントセットは、次の構造を使用します。

* `collection.json`: `{"name":"Set Name","description":"Set description"}`の形式でセットを説明するテキスト ファイル。
* `[fragment-name]/`: フラグメントのすべてのファイルが含まれています。
* `resources/`: セットのすべてのフラグメントで使用可能なファイルが含まれています。

```{tip}
また、[フラグメントエディター](./using-the-fragments-editor.md) の *リソース* タブから画像をアップロードすることも可能です。
```

画像ファイルはフラグメントの HTML で構文 `[resources:image-name.extension]` を使用して参照されます。 サンプルのフラグメント HTML には、次の `img` 要素があります。

```html
<img
    class="card-img-top"
    src="[resources:books.png]"
/>
```

![画像リソースをフラグメントに埋め込みます。](./including-default-resources-with-fragments/images/02.png)

```{note}
リソース名は大文字と小文字が区別され、参照内の名前と大文字と小文字が正確に一致する必要があります。
```

```{tip}
`img[src="[resources:image-name.extension]"]` という構文を使用して、CSS で画像リソースのスタイルを設定できます。
```

<a name="include-a-new-resource" />

## 新しいリソースを含める

フラグメントにリソースを組み込んで参照する方法がわかったので、サンプルのフラグメント セットで新しいリソースを使用できます。

1. <https://www.freeimages.com/> から取得した画像などを `liferay-i6r3.zip/src/i6r3-set/resources/` フォルダにコピーします。

1. 上記と同様に、Docker コンテナにフラグメントセットを再度インポートします。

    ```bash
    yarn run import
    ```

1. フラグメントセットにリソースが含まれていることを確認します。 UI の ［**Fragments**］ ページで、 ［**I6R3 セット**］ を選択し、 ［**Resources**］ タブをクリックします。 新しい画像がリソース リストに表示されるはずです。

    ![新しい画像がセットのリソースリストに表示されることを確認します。](./including-default-resources-with-fragments/images/03.png)

1. セットの ［**Fragments**］ タブを選択してから ［**I6R3 カード**］ フラグメントをクリックして、 ［**I6R3 カード**］ フラグメントを開きます。 フラグメント ソースがフラグメント エディターに表示されます。

1. 新しい画像リソースを使用するようにフラグメントを更新します。 `img` 要素の `src="[resources:books.png]"` 属性から `book.png` を削除し、`src="[resources:` の後に新しい画像ファイルの名前を入力します。 画像ファイル名の属性値がカーソルの下に表示されます。 その属性値を選択します。

    ![フラグメントエディタ―には、一致するリソースが一覧表示されます。](./including-default-resources-with-fragments/images/04.png)

1. フラグメントが新しい画像をレンダリングすることを確認します。

    ![フラグメントは新しいリソース画像を使用する必要があります。](./including-default-resources-with-fragments/images/05.png)

```{note}
フラグメント セット内のすべてのフラグメントがセットのリソースにアクセスできます。
```

これで、画像リソースのフラグメント セットの使用方法がわかりました。

<a name="related-information" />

## 関連情報

* [フラグメントへの構成オプションの追加](./adding-configuration-options-to-fragments.md)
* [フラグメントの自動デプロイ](./auto-deploying-fragments.md)
* [フラグメントエディターの使用](./using-the-fragments-editor.md)
