# 提供されたフラグメントセットの作成

提供されたフラグメントセットは、ページフラグメントを含むデプロイ可能なモジュールです。 提供されたセットのフラグメントは、通常のフラグメントと同じように使用できますが、データベースには含まれておらず、UI から直接変更することはできません。 Liferay 7.3以降を実行している場合は、圧縮された ZIP セットで作成された[自動デプロイ済みフラグメント](./auto-deploying-fragments.md)を使用することをお勧めします。 これらは [独自のツール](./using-the-fragments-toolkit.md#fragment-set-structure) または[Liferayフラグメントツールキット](./using-the-fragments-toolkit.md)で作成でき、UI から変更して画像リソースを含めることができます。

次の例は、Liferay 7.3以降で動作します。

```{note}
提供されたフラグメントセットを通じて追加されたすべてのフラグメントは、すべてのサイトでグローバルに使用できます。
```

提供されたフラグメントセットを追加するには、それ自体が [`FragmentCollectionContributor`インターフェイス](https://docs.liferay.com/dxp/apps/fragment/latest/javadocs/com/liferay/fragment/contributor/FragmentCollectionContributor.html) を実装している [`BaseFragmentCollectionContributor`クラス](https://docs.liferay.com/dxp/apps/fragment/latest/javadocs/com/liferay/fragment/contributor/BaseFragmentCollectionContributor.html) を展開します。

ここでは、フラグメントセットを提供する方法を学びます。

- [提供されたフラグメントセットのデプロイ](#deploy-a-contributed-fragment-set)
- [提供されたフラグメントセットのロジックとメタデータ](#contributed-fragment-set-logic-and-metadata)
- [フラグメントリソースの追加](#add-fragment-resources)
- [関連情報](#related-information)

```{note}
Liferay DXP 7.4以降の場合、フラグメントコレクションはLiferay UIではフラグメントセットと呼ばれます。
```

## 提供されたフラグメントセットのデプロイ

まず、サンプルをデプロイして、提供されたフラグメントセットがどのように見えるかを確認します。

1. 以下のコマンドを実行して、Docker コンテナを起動します。

    ```bash
    docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_PORTAL_DOCKER_IMAGE$]
    ```

1. [サンプル](https://learn.liferay.com/dxp/latest/ja/site-building/developer-guide/developing-page-fragments/liferay-l3m9.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/site-building/developer-guide/developing-page-fragments/liferay-l3m9.zip -O
    ```

    ```bash
    unzip liferay-l3m9.zip
    ```

1. モジュールルートから、提供されたセットの JAR をビルドしてデプロイします。

    ```bash
    cd liferay-l3m9
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    Windows でテストする場合は、最初に `./gradlew build` でモジュールをビルドし、デプロイメントが失敗した場合は JAR を手動で `docker cp docker-container-name:/opt/liferay/osgi/modules` に直接コピーする必要があります。
    ```

1. Liferay Dockerコンテナコンソールへのデプロイを確認します。 以下のログ メッセージが Docker コンソールに表示されます。

    ```bash
    INFO  [fileinstall-directory-watcher][BundleStartStopLogger:46] STARTED com.acme.l3m9.impl_1.0.0 [1824]
    ```

1. 提供されたフラグメントセットが利用可能であることを確認します。 *サイトメニュー* (![Site Menu](../../../images/icon-product-menu.png)) を開き、 *［デザイン］* &rarr; *［フラグメント］*に移動します。 セットがデフォルトセットリストに表示されます。

    ![提供されたフラグメントセットがデフォルトセットリストに表示されていることを確認します。](./creating-a-contributed-fragment-set/images/01.png)

提供されたフラグメントセットを正常にデプロイしました。

ご覧のとおり、提供されたフラグメントセットはデフォルトのフラグメントセットとともに表示され、フラグメントは UI から変更できません。 セットを変更する唯一の方法は、元のモジュールを更新するか、 [フラグメントを別のセットにコピー](../../creating-pages/page-fragments-and-widgets/using-fragments/managing-fragments.md#managing-individual-page-fragments) してフラグメントのコピーを変更することです。

## 提供されたフラグメントセットのロジックとメタデータ

フラグメントセットのコントリビューターは、`*FragmentCollectionContributor` クラスの 2 つのメソッドをオーバーライドして、セットに関する情報を提供します。

`getFragmentCollectionKey()` メソッドは、これらのフラグメントが提供されるフラグメントセットのキー/名前を返します。

```java
@Override
public String getFragmentCollectionKey() {
    return "l3m9";
}
```

`getServletContext()` メソッドは、提供されたフラグメントセットモジュールのサーブレットコンテキストを返します。

```java
@Override
public ServletContext getServletContext() {
    return _servletContext;
}
```

`ServletContext` はバンドルのシンボル名を指しているので、フラグメントリソースを見つけることができます。

```java
@Reference(
  target = "(osgi.web.symbolicname=com.liferay.learn.fragments)"
)
private ServletContext _servletContext;
```

`bnd.bnd` ファイルには、セットに対して定義する必要があるいくつかのプロパティが含まれています。

* `osgi.web.symbolicname` は、 `bnd.bnd` ファイルの `Bundle-SymbolicName` と一致します。
* `Web-ContextPath` ヘッダはセットを含むモジュールフォルダを示すので、`ServletContext` が正しく生成されます。
* `-dsannotations-options` は、コンポーネントクラスのクラス階層にある宣言型サービスのアノテーションを有効にします。

これらの値の参照については、サンプルプロジェクトの [`bnd.bnd`](https://learn.liferay.com/dxp/latest/ja/site-building/developer-guide/developing-page-fragments/liferay-l3m9.zip) を参照してください。

次に、プロジェクトを変更して、提供されたセットに別のフラグメントを含めます。

## フラグメントリソースの追加

以下の手順で、提供されたフラグメントセットに新しいパッケージ化されたフラグメントを追加します。

1. サンプルの `l3m9-impl/l3m9-jumbotron` フォルダを `l3m9-impl/src/main/resources/com/acme/l3m9/internal/fragment/contributor/l3m9/dependencies` フォルダに移動します。

    ```bash
    cp -r l3m9-impl/l3m9-jumbotron l3m9-impl/src/main/resources/com/acme/l3m9/internal/fragment/contributor/l3m9/dependencies/
    ```

    フラグメントの作成の詳細は、[Developing Page Fragments with the Fragments Toolkit](./using-the-fragments-toolkit.md)を参照してください。

    ```{note}
    パッケージ化されたフラグメントは `dependencies` フォルダに置かれ、クラスのパッケージ名とリソースのパッケージ名が一致しなければなりません（例： `[class.package.path].dependencies`）。
    ```

    ```{note}
    提供されたフラグメントセットは、[含まれているリソース](./including-default-resources-with-fragments.md)をサポートしていません。
    ```

1. 更新された提供されたセットの JAR をビルドします。

    ```bash
    cd liferay-l3m9
    ```

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    Windows でデプロイに失敗した場合は、[コンテナに Liferay をバインドマウント](../../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/providing-files-to-the-container.md) してモジュール JAR を `.\gradlew jar` でビルドし、JAR を適切なバインドマウントフォルダにコピーする必要がある場合があります。
    ```

1. 更新されたフラグメントが提供されたセットに含まれていることを確認します。 ブラウザで`https://localhost:8080`にアクセスし、画面左側のサイトメニューで、*［デザイン］* &rarr; *［フラグメント］*に移動します。 L3M9 ジャンボトロンフラグメントが L3M9 セットに表示されます。

    ![カスタムのジャンボトロンフラグメントが提供されたセットに含まれています。](./creating-a-contributed-fragment-set/images/02.png)

　 これで、提供されたフラグメントセットを作成する方法を理解し、提供された新しいフラグメントセットを Liferay に追加しました。

## 関連情報

* [フラグメントツールキットの使用](./using-the-fragments-toolkit.md)
* [フラグメントエディターの使用](./using-the-fragments-editor.md)