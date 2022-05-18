# ［商品パブリッシャー］ウィジェット用の新しい商品データソースの追加

このチュートリアルでは、`CPDataSource`インターフェイスを実装して、新しい商品データソースを追加する方法を示します。

商品データソースは、関連する商品を検索する独自の方法を提供します。 Liferay Commerceでは、 [商品の関係別](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-service/src/main/java/com/liferay/commerce/product/internal/data/source/CPDataSourceDefinitionLinkTypeImpl.java) および [カテゴリ別に](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-service/src/main/java/com/liferay/commerce/product/internal/data/source/CPDataSourceAssetCategoriesImpl.java) 検索するものなど、標準でいくつかの商品データソースを提供しています。

![すぐに使える商品データソース](./adding-a-new-product-data-source-for-the-product-publisher-widget/images/01.png "すぐに使える商品データソース")

<a name="overview" />

## 概要

1. [**サンプルをデプロイする**](#deploy-an-example)
1. [**例の説明**](#walk-through-the-example)
1. [**追加情報**](#additional-information)

<a name="deploy-an-example" />

## サンプルをデプロイする

このセクションでは、商品データソースをLiferay Commerceのインスタンスで実行する例を示します。

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. [Acme Commerce Product Data Source](./liferay-m5x7.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/content/liferay-m5x7.zip -O
    ```

    ```bash
    unzip liferay-m5x7.zip
    ```

1. サンプルをビルドしてデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
       このコマンドは、デプロイされたjarをDockerコンテナの ``/opt/liferay/osgi/modules``にコピーするのと同じです。
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.m5x7.impl_1.0.0
    ```

1. サンプルの商品データソースが追加されたことを確認します。 ブラウザーで  `https://localhost:8080`  を開き、商品パブリッシャーウィジェットのあるページに移動します。 商品パブリッシャーの［**設定**］をクリックし、［**製品の選択**］セクションで［**データソース**］を選択します。 新しい商品データソース（「Products Ending in the Same Word」）が、［**データソース**］ ドロップダウンの下に表示されます。

![新しい商品データソース](./adding-a-new-product-data-source-for-the-product-publisher-widget/images/02.png "新しい商品データソース")

これで、`CPDataSource`を実装する新しい商品データソースのビルドとデプロイが完了しました。

次に、詳細をさらに詳しく見ていきましょう。

<a name="walk-through-the-example" />

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、 `CPDataSource`インターフェイスを確認します。 最後に、`CPDataSource`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

```java
@Component(
    property = "commerce.product.data.source.name=m5x7",
    service = CPDataSource.class
)
public class M5X7CPDataSource implements CPDataSource {
```

> Liferay Commerceが新しいデータと既存のデータソースを区別できるように、商品データソース名は一意の値である必要があります。

### `CPDataSource`インターフェースを確認する

次のメソッドを実装します。

```java
public String getLabel(Locale locale);
```

> このメソッドは、商品データソースが関連商品を検索する方法を説明するテキストラベルを返します。 言語キーでラベルを取得する際のリファレンスについては、 [M5X7CPDataSource.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/content/adding-a-new-product-data-source-for-the-product-publisher-widget/resources/liferay-m5x7.zip/m5x7-impl/src/main/java/com/acme/m5x7/internal/commerce/product/data/source/M5X7CPDataSource.java) の実装を参照してください。

```java
public String getName();
```

> これは、商品データソースの名前を返します。

```java
public CPDataSourceResult getResult(
        HttpServletRequest httpServletRequest, int start, int end)
    throws Exception;
```

> ここで、関連商品の検索を実行するビジネスロジックを追加します。 `HttpServletRequest`には、結果を何らかの方法で関連付ける必要がある特定の商品への参照が含まれています。
> 
> この方法では、検索結果の一覧を含む`CPDataSourceResult`を返します。実装については [CPDataSourceResult.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-api/src/main/java/com/liferay/commerce/product/data/source/CPDataSourceResult.java) を参照してください。

### 商品データソースを完成させる

商品データソースは、関連商品の検索を実行するロジックで構成されています。 以下を行います。

* [`getResult`に検索ロジックを追加する。](#add-the-search-logic-to-getresult)
* [言語キーを`Language.properties`に追加する。](#add-the-language-key-to-languageproperties)

#### `getResult`に検索ロジックを追加する。

```java
@Override
public CPDataSourceResult getResult(
        HttpServletRequest httpServletRequest, int start, int end)
    throws Exception {

    CPCatalogEntry cpCatalogEntry =
        (CPCatalogEntry)httpServletRequest.getAttribute(
            CPWebKeys.CP_CATALOG_ENTRY);

    if (cpCatalogEntry == null) {
        return new CPDataSourceResult(new ArrayList<>(), 0);
    }

    return _cpDefinitionHelper.search(
        _portal.getScopeGroupId(httpServletRequest),
        new SearchContext() {
            {
                setAttributes(
                    HashMapBuilder.<String, Serializable>put(
                        Field.STATUS, WorkflowConstants.STATUS_APPROVED
                    ).put(
                        "excludedCPDefinitionId",
                        cpCatalogEntry.getCPDefinitionId()
                    ).build());
                setCompanyId(_portal.getCompanyId(httpServletRequest));
                setKeywords(
                    StringPool.STAR + _getLastWordOfName(cpCatalogEntry));
            }
        },
        new CPQuery(), start, end);
}
```

> [CPDefinitionHelper](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-service/src/main/java/com/liferay/commerce/product/internal/util/CPDefinitionHelperImpl.java) を使用して、検索を実行します。 `CPDefinitionHelper`は、商品定義に固有のロジックと`BaseIndexer`の検索機能を組み合わせたものです。詳細は、 [BaseIndexer.java](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/portal-kernel/src/com/liferay/portal/kernel/search/BaseIndexer.java) を参照してください。
> 
> `"excludedCPDefinitionId"`属性の値として商品定義のIDを`SearchContext`に追加します。 これにより、結果から元の商品が除外されます。 この例では、検索する商品名の最後の単語も指定しています。 詳細は、この例では、`M5X7CPDataSource.java` ファイルの`_getLastWordOfName`メソッドの検索ロジックの実行を参照してください。

#### 言語キーを`Language.properties`に追加する

モジュール内の [Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/content/adding-a-new-product-data-source-for-the-product-publisher-widget/resources/liferay-m5x7.zip/m5x7-impl/src/main/resources/content/Language.properties) ファイルに言語キーとその値を追加します。

```properties
products-ending-in-the-same-word=Products Ending in the Same Word
```

> 詳細は、 [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application) を参照してください。

<a name="conclusion" />

## まとめ

　 これで、 `CPDataSource`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい商品データソースを追加しました。

<a name="additional-information" />

## 追加情報

* [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
* [関連商品、アップセル、クロスセル](../../product-management/creating-and-managing-products/products/related-products-up-sells-and-cross-sells.md)
