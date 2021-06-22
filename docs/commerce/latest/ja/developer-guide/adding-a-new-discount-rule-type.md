# 新しい割引ルールタイプの追加

このチュートリアルでは、 [CommerceDiscountRuleType](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-discount-api/src/main/java/com/liferay/commerce/discount/rule/type/CommerceDiscountRuleType.java) と [CommerceDiscountRuleTypeJSPContributor](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-discount-api/src/main/java/com/liferay/commerce/discount/rule/type/CommerceDiscountRuleTypeJSPContributor.java) の2つのインターフェースを実装して、新しい割引ルールタイプを追加する方法を示します。

割引ルールタイプは、割引が注文に適用される時期を評価するための条件を定義します。 Liferay Commerceでは、次の3つの割引ルールタイプをデフォルトで提供しています：[AddedAllCommerceDiscountRuleTypeImpl](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-discount-rule-added-all/src/main/java/com/liferay/commerce/discount/rule/added/all/internal/AddedAllCommerceDiscountRuleTypeImpl.java)、[AddedAnyCommerceDiscountRuleTypeImpl](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-discount-rule-added-any/src/main/java/com/liferay/commerce/discount/rule/added/any/internal/AddedAnyCommerceDiscountRuleTypeImpl.java)、および [CartTotalCommerceDiscountRuleTypeImpl](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-discount-rule-cart-total/src/main/java/com/liferay/commerce/discount/rule/cart/total/internal/CartTotalCommerceDiscountRuleTypeImpl.java)

![すぐに使える割引ルールのタイプ](./adding-a-new-discount-rule-type/images/01.png "すぐに使える割引ルールのタイプ")

## 概要

1.  [**サンプルをデプロイする**](#deploy-an-example)
2.  [**例の説明**](#walk-through-the-example)
3.  [**追加情報**](#additional-information)

## サンプルをデプロイする

まず、Liferay Commerceのインスタンスに割引ルールタイプの例を展開する必要があります。 次の手順を実行します：

1.  Liferay Commerceを開始します。

    ``` bash
    docker run -it -p 8080:8080 liferay/commerce:2.0.5
    ```

2.  [Acme Commerce Discount Rule Type](./liferay-m6a8.zip)をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/2.x/en/developer-guide/liferay-m6a8.zip -O
    ```

    ``` bash
    unzip liferay-m6a8.zip
    ```

3.  サンプルをビルドしてデプロイします。

    ``` bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ``` note::
       このコマンドは、デプロイされたjarをDockerコンテナの``/opt/liferay/osgi/modules``にコピーするのと同じです。
    ```

4.  Liferay Dockerコンテナコンソールでデプロイを確認します。

    ``` bash
    STARTED com.acme.m6a8.web_1.0.0
    ```

5.  サンプルの割引ルールタイプが追加されたことを確認します。 ブラウザーで `https://localhost:8080` を開き、 *コントロールパネル* → *コマース* → *ディスカウント*へ行きます。 メニュー内の[ *Edit* ]をクリックして割引を適用し、画面上部の[ *Rules* ]に移動します。

    そこから（+）アイコンをクリックして、新しい割引ルールを追加します。 *[Type]* ドロップダウンの下に、新しい割引ルールタイプ（「Has a minimum number of products」）が表示されます。

![新しい割引ルールタイプ](./adding-a-new-discount-rule-type/images/02.png "新しい割引ルールタイプ")

これで、`CommerceDiscountRuleType`を実装する新しい割引ルールタイプのビルドとデプロイが完了しました。

次に、詳細をさらに詳しく見ていきましょう。

## 例の説明

次に、デプロイした例を確認します。 割引ルールタイプのクラスと、カスタムUI入力用のJSPコントリビューターの2つのクラスを作成します。 次の手順を実行します：

  - [OSGi登録用の割引ルールタイプのクラスに注釈を付ける](#annotate-the-discount-rule-type-class-for-osgi-registration)
  - [`CommerceDiscountRuleType` インターフェイスを確認する](#review-the-commercediscountruletype-interface)
  - [OSGi登録用のJSPコントリビューターのクラスに注釈を付ける](#annotate-the-jsp-contributor-class-for-osgi-registration)
  - [`CommerceDiscountRuleTypeJSPContributor` インターフェイスを確認する](#review-the-commercediscountruletypejspcontributor-interface)
  - [割引ルールタイプを完成する](#complete-the-discount-rule-type)

### OSGi登録用の割引ルールタイプのクラスに注釈を付ける

``` java
@Component(
    immediate = true,
    property = {
        "commerce.discount.rule.type.key=" + M6A8CommerceDiscountRuleType.KEY,
        "commerce.discount.rule.type.order:Integer=51"
    },
    service = CommerceDiscountRuleType.class
)
public class M6A8CommerceDiscountRuleType implements CommerceDiscountRuleType {

    public static final String KEY = "Example";
```

> Liferay Commerceが[割引ルールタイプレジストリ](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-discount-service/src/main/java/com/liferay/commerce/discount/internal/rule/type/CommerceDiscountRuleTypeRegistryImpl.java)で新しいタイプを他のタイプと区別できるように、割引ルールタイプに個別のキーを提供することが重要です。 すでに使用されているキーを宣言すると、既存の関連付けられているタイプが上書きされます。
> 
> `commerce.discount.rule.type.order` 値は、使用可能な割引ルールタイプのリストでこのタイプが表示される範囲を示します。 たとえば、 ["added all"割引ルールタイプ](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-discount-rule-added-all/src/main/java/com/liferay/commerce/discount/rule/added/all/internal/AddedAllCommerceDiscountRuleTypeImpl.java)の値は50です。 作成した割引ルールタイプに値51を指定すると、"added all"タイプの直後に表示されます。

### `CommerceDiscountRuleType` インターフェイスを確認する

次のメソッドを実装します。

``` java
public boolean evaluate(
        CommerceDiscountRule commerceDiscountRule,
        CommerceContext commerceContext)
    throws PortalException;
```

> このメソッドでは、割引ルールが適用されるタイミングを評価するためのビジネスロジックを実装します。

``` java
public String getKey();
```

> これにより、割引ルールタイプレジストリに割引ルールタイプの一意の識別子が提供されます。 このキーを使用して、レジストリから新しいタイプを取得できます。

``` java
public String getLabel(Locale locale);
```

> これは、割引ルールの適用方法を説明するテキストラベルを返します。 言語キーでラベルを取得する際のリファレンスについては、 [M6A8CommerceDiscountRuleType.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/src/main/java/com/acme/m6a8/web/internal/commerce/discount/rule/type/M6A8CommerceDiscountRuleTypeImpl.java)の実装を参照してください。

### OSGi登録用のJSPコントリビューターのクラスに注釈を付ける

``` java
@Component(
    immediate = true,
    property = "commerce.discount.rule.type.jsp.contributor.key=" + M6A8CommerceDiscountRuleTypeJSPContributor.KEY,
    service = CommerceDiscountRuleTypeJSPContributor.class
)
public class M6A8CommerceDiscountRuleTypeJSPContributor
    implements CommerceDiscountRuleTypeJSPContributor {

    public static final String KEY = "Example";
```

> Liferay Commerceが [割引ルールタイプのJSPコントリビューターレジストリ](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-discount-api/src/main/java/com/liferay/commerce/discount/rule/type/CommerceDiscountRuleTypeJSPContributorRegistry.java)で新しいコントリビューターと他のコントリビューターを区別できるように、JSPコントリビューターに個別のキーを提供することが重要です。 すでに使用されているキーを宣言すると、既存の関連付けられているタイプが上書きされます。

### `CommerceDiscountRuleTypeJSPContributor` インターフェイスを確認する

次のメソッドを実装します。

``` java
public void render(
        long l, long l1, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
    throws Exception;
```

> ここで、割引ルールタイプのカスタムUI入力をレンダリングするコードを配置します。

### 割引ルールタイプを完成する

割引ルールタイプは、割引ルールを注文に適用するタイミングを評価するためのバックエンドロジック、割引ルールタイプのUI入力をレンダリングするロジック、およびカスタムUI入力自体で構成されます。 次の手順を実行します：

  - [モジュールに`ServletContext`を構成する。](#configure-the-servletcontext-for-the-module)
  - [`CommerceDiscountRuleTypeJSPContributor`の`render` メソッドを実装する。](#implement-the-commercediscountruletypejspcontributors-render-method)
  - [評価ロジックを追加して`評価`する。](#add-the-evaluation-logic-to-evaluate)
  - [JSPを追加して、カスタムUI入力をレンダリングする。](#add-a-jsp-to-render-the-custom-ui-input)
  - [言語キーを `Language.properties`に追加します。](#add-the-language-keys-to-languageproperties)

#### モジュールに`ServletContext`を構成する

バンドルのシンボル名を使用してJSPコントリビュータークラスで` ServletContext`を定義し、JSPを見つけられるようにします。

``` java
@Reference(target = "(osgi.web.symbolicname=com.acme.m6a8.web)")
private ServletContext _servletContext;
```

> `osgi.web.symbolicname`に設定した値は、 [bnd.bndファイル](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/bnd.bnd)の`Bundle-SymbolicName`の値と一致します。 これらの値は、JSPを見つけるために`ServletContext`と一致する必要があります。
> 
> `ServletContext`が正しく生成されるように、bnd.bndファイルで`Web-ContextPath`の一意の値を宣言します。 この例では、`Web-ContextPath`を`/m6a8-web`に設定しています。 これらの値のリファレンスについては、[bnd.bnd](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/bnd.bnd)を参照してください。

#### `CommerceDiscountRuleTypeJSPContributor`の`render` メソッドを実装する

``` java
@Override
public void render(
        long l, long l1, HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
    throws Exception {

    _jspRenderer.renderJSP(
        _servletContext, httpServletRequest, httpServletResponse,
        "/view.jsp");
}
```

> `JSPRenderer`を使用して、割引ルールタイプのカスタムUI入力のJSPをレンダリングします（この例では [view.jsp](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/src/main/resources/META-INF/resources/view.jsp)）。 JSPを見つけるためのパラメーターとして`ServletContext`を提供します。

#### 評価ロジックを追加して`評価`する。

``` java
@Override
public boolean evaluate(
        CommerceDiscountRule commerceDiscountRule,
        CommerceContext commerceContext)
    throws PortalException {

    CommerceOrder commerceOrder = commerceContext.getCommerceOrder();

    if (commerceOrder == null) {
        return false;
    }

    String settingsProperty = commerceDiscountRule.getSettingsProperty(
        commerceDiscountRule.getType());

    int minimumProducts = Integer.valueOf(settingsProperty);

    List<CommerceOrderItem> commerceOrderItems =
        commerceOrder.getCommerceOrderItems();

    if (commerceOrderItems.size() >= minimumProducts) {
        return true;
    }

    return false;
}
```

> 割引ルールを適用するためにtrueでなければならない条件をここに実装します。 この例では、カスタムUI入力で定義された最小値（[CommerceDiscountRule](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-discount-service/src/main/java/com/liferay/commerce/discount/model/impl/CommerceDiscountRuleImpl.java)内に文字列として保存されます）を使用して、注文に少なくとも最小数のアイテムが含まれていることを確認します。
> 
> `CommerceOrder`オブジェクトは、評価される注文に関する情報を表します。 `CommerceOrder`から取得できる詳細については、[CommerceOrder.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrder.java)および[CommerceOrderModel.java](https://github.com/liferay/com-liferay-commerce/blob/2.0.5/commerce-api/src/main/java/com/liferay/commerce/model/CommerceOrderModel.java)を参照してください。

#### JSPを追加して、カスタムUI入力をレンダリングする

この例では、商品の最小数を数値で入力してJSPを追加します。

``` jsp
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<aui:input label="minimum-number-of-products" name="typeSettings" type="text">
    <aui:validator name="digits" />
    <aui:validator name="min">1</aui:validator>
</aui:input>
```

> 割引ルールを定義するときに表示するUI要素を実装します。 これらは、割引ルールタイプが選択されるとすぐに表示されます。 入力を定義すると、保存された値が割引ルールの設定プロパティに保存されます。
> 
> AUI入力の使用の詳細については、[Using AUI Taglibs](https://help.liferay.com/hc/en-us/articles/360020189212-Using-AUI-Taglibs)を参照してください。

#### 言語キーを `Language.properties`に追加します。

[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/adding-a-new-discount-rule-type/resources/liferay-m6a8.zip/m6a8-web/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

    has-a-minimum-number-of-products =商品の最小数があります
    minimum-number-of-products =商品の最小数

> 詳細は、[Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)を参照してください。

## まとめ

　 `CommerceDiscountRuleType`インターフェイスを実装するための基本事項を理解し、新しい割引ルールタイプをカスタムUI入力とともにLiferay Commerceに追加しました。

## 追加情報

  - [割引を作成する](../../promoting-products/creating-a-discount.md)
  - [Localizing Your Application](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
  - [Using AUI Taglibs](https://help.liferay.com/hc/en-us/articles/360020189212-Using-AUI-Taglibs)
