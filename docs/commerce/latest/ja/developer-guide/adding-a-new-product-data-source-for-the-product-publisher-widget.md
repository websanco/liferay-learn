# [Product Publisher]ウィジェット用の新しい商品データソースの追加

このチュートリアルでは、`CPDataSource`インターフェイスを実装して、新しい商品データソースを追加する方法を示します。

商品データソースは、関連する商品を検索する独自の方法を提供します。 Liferay Commerceでは、[商品の関係別](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-product-service/src/main/java/com/liferay/commerce/product/internal/data/source/CPDataSourceDefinitionLinkTypeImpl.java)および[カテゴリ別に](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-product-service/src/main/java/com/liferay/commerce/product/internal/data/source/CPDataSourceAssetCategoriesImpl.java)検索するものなど、すぐに使用できるいくつかの商品データソースを提供しています。

![すぐに使える商品データソース](./adding-a-new-product-data-source-for-the-product-publisher-widget/images/01.png "すぐに使える商品データソース")

## 概要

1.  [**サンプルをデプロイする**](#deploy-an-example)
2.  [**例の説明**](#walk-through-the-example)
3.  [**追加情報**](#additional-information)

## サンプルをデプロイする

このセクションでは、商品データソースをLiferay Commerceのインスタンスで実行する例を示します。 次の手順を実行します：

1.  Liferay Commerceを開始します。

    ``` bash
    docker run -it -p 8080:8080 liferay/commerce:2.0.5
    ```

2.  [Acme Commerce Product Data Source](./liferay-m5x7.zip)をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/liferay-m5x7.zip -O
    ```

    ``` bash
    unzip liferay-m5x7.zip
    ```

3.  サンプルをビルドしてデプロイします。

    ``` bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ``` note::
       このコマンドは、デプロイされたjarをDockerコンテナの ``/opt/liferay/osgi/modules``にコピーするのと同じです。
    ```

4.  Liferay Dockerコンテナコンソールでデプロイを確認します。

    ``` bash
    STARTED com.acme.m5x7.impl_1.0.0
    ```

5.  サンプルの商品データソースが追加されたことを確認します。 ブラウザーで  `https://localhost:8080`  を開き、Product Publisherウィジェットのあるページに移動します。 Product Publisherの[ *Configuration* ]をクリックし、[ *Product Selection* ]セクションで[ *Data Source* ]を選択します。 新しい商品データソース（「Products Ending in the Same Word」）が、*[Data Source]* ドロップダウンの下に表示されます。

![新しい商品データソース](./adding-a-new-product-data-source-for-the-product-publisher-widget/images/02.png "新しい商品データソース")

これで、`CPDataSource`を実装する新しい商品データソースのビルドとデプロイが完了しました。

次に、詳細をさらに詳しく見ていきましょう。

## 例の説明

このセクションでは、デプロイした例について確認します。 最初に、OSGi登録用のクラスに注釈を付けます。 次に、 `CPDataSource`インターフェイスを確認します。 そして第三に、 `CPDataSource`の実装を完了します。

### OSGi登録用のクラスに注釈を付ける

``` java
@Component(
    immediate = true,
    property = "commerce.product.data.source.name=" + M5X7CPDataSource.NAME,
    service = CPDataSource.class
)
public class M5X7CPDataSource implements CPDataSource {

    public static final String NAME = "Example";
```

> Liferay Commerceが新しいデータと既存のデータソースを区別できるように、商品データソース名は一意の値である必要があります。

### `CPDataSource`インターフェイスを確認する

次のメソッドを実装します。

``` java
public String getLabel(Locale locale);
```

> このメソッドは、商品データソースが関連商品を検索する方法を説明するテキストラベルを返します。 言語キーでラベルを取得する際のリファレンスについては、 [M5X7CPDataSource.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-product-data-source-for-the-product-publisher-widget/resources/liferay-m5x7.zip/m5x7-impl/src/main/java/com/acme/m5x7/internal/commerce/product/data/source/M5X7CPDataSource.java)の実装を参照してください。

``` java
public String getName();
```

> これは、商品データソースの名前を返します。

``` java
public CPDataSourceResult getResult(
        HttpServletRequest httpServletRequest, int start, int end)
    throws Exception;
```

> ここで、関連商品の検索を実行するビジネスロジックを追加します。 `HttpServletRequest`には、結果を何らかの方法で関連付ける必要がある特定の商品への参照が含まれています。
> 
> このメソッドは、検索結果のリストを含む `CPDataSourceResult`を返します。 [CPDataSourceResult.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-product-api/src/main/java/com/liferay/commerce/product/data/source/CPDataSourceResult.java)の実装を参照してください。

### 商品データソースを完成させる

商品データソースは、関連商品の検索を実行するロジックで構成されています。 以下を行います。

  - [検索ロジックを `getResult`に追加する。](#add-the-search-logic-to-getresult)
  - [言語キーを`Language.properties`に追加する。](#add-the-language-key-to-languageproperties)

#### 検索ロジックを `getResult`に追加する。

``` java
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

    SearchContext searchContext = new SearchContext();

    Map<String, Serializable> attributes = new HashMap<>();

    attributes.put(Field.STATUS, WorkflowConstants.STATUS_APPROVED);
    attributes.put(
        "excludedCPDefinitionId", cpCatalogEntry.getCPDefinitionId());

    searchContext.setAttributes(attributes);

    searchContext.setCompanyId(_portal.getCompanyId(httpServletRequest));

    searchContext.setKeywords(
        StringPool.STAR + _getLastWordOfName(cpCatalogEntry));

    return _cpDefinitionHelper.search(
        _portal.getScopeGroupId(httpServletRequest), searchContext,
        new CPQuery(), start, end);
}
```

> [CPDefinitionHelper](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-product-service/src/main/java/com/liferay/commerce/product/internal/util/CPDefinitionHelperImpl.java)を使用して検索を実行します。 `CPDefinitionHelper`は、商品定義に固有のロジックと`BaseIndexer`の検索機能を組み合わせたものです。詳細は、[BaseIndexer.java](https://github.com/liferay/liferay-portal/blob/7.1.3-ga4/portal-kernel/src/com/liferay/portal/kernel/search/BaseIndexer.java)を参照してください。
> 
> `"excludedCPDefinitionId"`属性の値として商品定義のIDを`SearchContext`に追加します。 これにより、結果から元の商品が除外されます。 この例では、検索する商品名の最後の単語も指定しています。 [M5X7CPDataSource](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-product-data-source-for-the-product-publisher-widget/resources/liferay-m5x7.zip/m5x7-impl/src/main/java/com/acme/m5x7/internal/commerce/product/data/source/M5X7CPDataSource.java)にアクセスして、`_getLastWordOfName`の実装を参照してください。

#### 言語キーを`Language.properties`に追加する

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-product-data-source-for-the-product-publisher-widget/resources/liferay-m5x7.zip/m5x7-impl/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

    products-ending-in-the-same-word=Products Ending in the Same Word

> 詳細は、[Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)を参照してください。

## まとめ

　 これで、 `CPDataSource`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい商品データソースを追加しました。

## 追加情報

  - [Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
  - [Related Products, Up-Sells, and Cross-Sells](../../managing-a-catalog/creating-and-managing-products/products/related-products-up-sells-and-cross-sells.md)
