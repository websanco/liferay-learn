# テーマレットを介したリソースのバンドルとテーマへのインストール

テーマレットは、複数のテーマで再利用できる小さなモジュール式のコードです。 テーマ自体とは異なり、テーマレットには変更を加えたファイルのみを含めることができます。 テーマレットを使用して、テーマの一部として通常定義するあらゆる種類のスタイルや機能を変更できます。

<a name="prerequisites" />

## 前提条件

テーマは、Liferayテーマジェネレーターを使用して作成します（[テーマの作成](../getting-started/setting-up-an-environment-and-creating-a-theme.md)など）。 まだインストールしていない場合は、次のコマンドを使用してインストールしてください。

```bash
npm install -g generator-liferay-theme@10.x.x
```

次のコマンドを使用して、YeomanとGulpの依存関係をインストールします。

```bash
npm install -g yo gulp
```

<a name="creating-a-themelet" />

## テーマレットの作成

Liferayテーマジェネレーターを使用してテーマレットを作成します。

1. テーマレットを作成するファイルの場所から次のコマンドを実行します。

    ```bash
    yo liferay-theme:themelet
    ```

1. プロンプトでテーマレットの名前を入力します。 デフォルトの「My Liferay Themelet」を使用するには、Enterキーを押します。

    ```
    ? What would you like to call your themelet? 例
    ```

1. プロンプトでテーマレットのIDを入力します。 テーマレットが生成されると、IDによってテーマレットが組み込まれているフォルダの名前が決まります。 Enterキーを押して、名前に基づいたデフォルトIDを使用することもできます。

1. 矢印キーを使用して、プロンプトでテーマレットを作成するLiferay DXPのバージョンを選択し、Enterキーを押します。

    ```
    ? Which version of Liferay is this themelet for? (Use arrow keys)
    ❯ 7.3 
      7.2 
      Any 
    ```

テーマレットは、選択したIDに基づいて生成され、フォルダ内に配置されます。 これで、デプロイするテーマに追加できます。

テーマレットをテーマ内で使用できるようにするには、テーマレットのフォルダに移動して、次のコマンドを実行する必要もあります。

```bash
npm link
```

このコマンドを実行すると、テーマレットがグローバルにインストールされ、テーマを拡張するときに選択できるようになります。

<a name="adding-the-themelet-to-a-theme" />

## テーマレットをテーマに追加する

テーマレットをグローバルにインストールしたら、それを任意のテーマに追加できます。

```{note}
テーマレットを追加するためのテーマがない場合は、[Creating a Theme](../getting-started/setting-up-an-environment-and-creating-a-theme.md)でテーマを作成する手順を参照してください。
```

`gulp extend`コマンドを使用して、テーマレットを任意のテーマに追加します。

1. テーマのルートフォルダに移動します。

    ```bash
    cd my-theme/
    ```

1. このフォルダから次のコマンドを実行します。

    ```bash
    gulp extend
    ```

1. プロンプトで、矢印キーを使用して`テーマレット`を使用して拡張することを選択し、Enterキーを押します。

    ```
    ? What kind of theme asset would you like to extend? 
      Base theme 
    ❯ Themelet 
    ```

1. プロンプトで、矢印キーを使用して「Search globally installed npm modules」を選択します。

    ```
    ? Where would you like to search for themelets? (Use arrow keys)
    ❯ Search globally installed npm modules (development purposes only)
      Search npm registry (published modules)
      Specify a package URL
    ```

1. 矢印キーを使用して、グローバルにインストールされたモジュールのリストからテーマレットを選択します。 リストに表示するには、テーマレット内で`npm link`を実行している必要があります。

テーマレットがテーマにインストールされ、次に`gulp deploy`コマンドを使用してこのテーマをビルドおよびデプロイするときに変更内容が適用されます。

<a name="additional-information" />

## 追加情報

* [Creating a Theme](../getting-started/setting-up-an-environment-and-creating-a-theme.md)
