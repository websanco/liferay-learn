# 新しい商品タイプの追加

このチュートリアルでは、次の3つのインターフェースを実装することで、新商品のタイプを追加する方法を説明します： [CPType](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-api/src/main/java/com/liferay/commerce/product/type/CPType.java) 、 [ScreenNavigationCategory](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/frontend-taglib/frontend-taglib/src/main/java/com/liferay/frontend/taglib/servlet/taglib/ScreenNavigationCategory.java) 、および [ScreenNavigationEntry](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/frontend-taglib/frontend-taglib/src/main/java/com/liferay/frontend/taglib/servlet/taglib/ScreenNavigationEntry.java)

商品タイプを使用して、類似の特性を共有する商品をグループ化できます。 Liferay Commerceには標準で次の3つの商品タイプが組み込まれています： [Simple](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-type-simple/src/main/java/com/liferay/commerce/product/type/simple/internal/SimpleCPType.java) 、 [Grouped](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-type-grouped-web/src/main/java/com/liferay/commerce/product/type/grouped/web/internal/GroupedCPType.java) 、および [Virtual](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-type-virtual-web/src/main/java/com/liferay/commerce/product/type/virtual/web/internal/VirtualCPType.java)

![すぐに使える商品タイプ](./adding-a-new-product-type/images/01.png "すぐに使える商品タイプ")

<a name="overview" />

## 概要

1. [**サンプルをデプロイする**](#deploy-an-example)
1. [**例の説明**](#walk-through-the-example)
1. [**追加情報**](#additional-information)

<a name="deploy-an-example" />

## サンプルをデプロイする

このセクションでは、商品タイプをLiferay Commerceのインスタンスで実行する例を示します。

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [Acme Commerce Product Type](./liferay-c1n4.zip) をダウンロードして解凍します。

    ``` bash
    curl https://learn.liferay.com/commerce/latest/en/developer-guide/catalog/liferay-c1n4.zip -O
    ```

    ```bash
    unzip liferay-c1n4.zip
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
    STARTED com.acme.c1n4.web_1.0.0
    ```

1. サンプルの商品タイプが追加されたことを確認します。 ブラウザで`https://localhost:8080`を開きます。 アプリケーションメニュー（![Applications Menu](../../images/icon-applications-menu.png)）をクリックし、［**コマース**］→［**商品**］に移動します。 次に、（+）アイコンをクリックして、新しい商品を追加します。 新しい商品タイプ（「Example」）が、選択するタイプのリストに表示されます。

```{note}
   Liferay Commerce 2.1以前のバージョンでは、*コントロールパネル* → *Commerce* → *商品*に移動して商品ページを検索します。
```

![新商品タイプ](./adding-a-new-product-type/images/02.png "新商品タイプ")

これで、`CPType`を実装する新しい商品タイプのビルドとデプロイが完了しました。

次に、詳細をさらに詳しく見ていきましょう。

<a name="walk-through-the-example" />

## 例の説明

このセクションでは、デプロイした例について確認します。 商品タイプクラスとカスタム画面用の画面ナビゲーションエントリクラスの2つのクラスを作成します。 次の手順を実行します。

* [OSGi登録用の商品タイプのクラスに注釈を付ける](#annotate-the-product-type-class-for-osgi-registration)
* [`CPType`インターフェイスを確認する](#review-the-cptype-interface)
* [OSGi登録用の画面ナビゲーションエントリクラスに注釈を付ける](#annotate-the-screen-navigation-entry-class-for-osgi-registration)
* [`ScreenNavigationCategory`インターフェイスを確認する](#review-the-screennavigationcategory-interface)
* [`ScreenNavigationEntry`インターフェイスを確認する](#review-the-screennavigationentry-interface)
* [商品タイプを完成させる](#complete-the-product-type)

### OSGi登録用の商品タイプのクラスに注釈を付ける

商品タイプのクラスは、`CPType`インターフェイスを実装します。

```java
@Component(
    property = {
    "commerce.product.type.display.order:Integer=16",
    "commerce.product.type.name=c1n4"
    },
    service = CPType.class
)
public class C1N4CPType implements CPType {
```

> Liferay Commerceがこの商品タイプを既存の商品タイプと区別できるように、商品タイプ名は一意の値である必要があります。
> 
> `commerce.product.type.display.order`値は、商品タイプのリストでこの商品タイプがUIに表示される範囲を示します。 例えば、 [仮想商品タイプ](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-type-virtual-web/src/main/java/com/liferay/commerce/product/type/virtual/web/internal/VirtualCPType.java) の値は15です。 作成した商品タイプに値16を指定すると、仮想タイプの直後に表示されます。

### `CPType`インターフェイスを確認する

商品タイプクラスで`CPType`の以下のメソッドを実装します。

```java
public void deleteCPDefinition(long cpDefinitionId) throws PortalException;
```

> このメソッドで、商品タイプのカスタム削除ロジックを追加します。

```java
public String getLabel(Locale locale);
```

> これは、商品タイプを説明するテキストラベルを返します。 言語キーでラベルを取得する際のリファレンスについては、[C1N4CPType.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/catalog/adding-a-new-product-type/resources/liferay-c1n4.zip/c1n4-web/src/main/java/com/acme/c1n4/web/internal/commerce/product/type/C1N4CPType.java)の実装を参照してください。

```java
public String getName();
```

> これは、商品タイプの名前を返します。 この名前は、UIに表示される名前に対応する言語キーの場合があります。

### OSGi登録用の画面ナビゲーションエントリクラスに注釈を付ける

画面ナビゲーションエントリークラスでは`ScreenNavigationCategory`と`ScreenNavigationEntry`の両方のインターフェイスを実装しています。

```java
@Component(
    property = {
    "screen.navigation.category.order:Integer=11",
    "screen.navigation.entry.order:Integer=11"
    },
    service = {ScreenNavigationCategory.class, ScreenNavigationEntry.class}
)
public class C1N4ScreenNavigationEntry
    implements ScreenNavigationCategory, ScreenNavigationEntry<CPDefinition> {
```

> Liferay Commerceがこの画面を既存の画面とは別の画面として区別できるように、ナビゲーション画面クラスに個別のキーを提供することが重要です。 すでに使用されているキーを再利用すると、既存の関連付けられているナビゲーション画面が上書きされます。
> 
> `screen.navigation.category.order`および`screen.navigation.entry.order`値は、この画面が表示される商品タイプ画面の位置を決定します。 たとえば、[[Details]画面クラス](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/commerce/commerce-product-definitions-web/src/main/java/com/liferay/commerce/product/definitions/web/internal/frontend/taglib/servlet/taglib/CPDefinitionDetailsScreenNavigationCategory.java)の値は10に設定されています。値を11に設定すると、カスタム画面がリストでその後に表示されるようになります。

### `ScreenNavigationCategory`インターフェイスを確認する

画面ナビゲーションエントリクラスに次のメソッドを実装します。

```java
public String getCategoryKey();
```

> これは、画面ナビゲーションエントリに使用されるカテゴリの一意の識別子を返します。

```java
public String getLabel(Locale locale);
```

> これは、UIに表示される画面ナビゲーションエントリのテキストラベルを返します。 言語キーでラベルを取得する際のリファレンスについては、[C1N4ScreenNavigationEntry.java](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/catalog/adding-a-new-product-type/resources/liferay-c1n4.zip/c1n4-web/src/main/java/com/acme/c1n4/web/internal/frontend/taglib/servlet/taglib/C1N4ScreenNavigationEntry.java)の実装を参照してください。

```java
public String getScreenNavigationKey();
```

> これにより、Liferayで画面を表示する場所を示すキーが返されます。 商品の他の画面に正しく表示されるように、String値`"cp.definition.general"`を返します。

### `ScreenNavigationEntry`インターフェイスを確認する

次のメソッドを使用して、画面ナビゲーションエントリクラスの構築を続けます。

```java
String getCategoryKey();
```

> これは、画面で使用される画面ナビゲーションカテゴリの一意の識別子を返します。

```java
String getEntryKey();
```

> これは、画面ナビゲーションエントリの一意の識別子を返します。 `getCategoryKey`と同じ値を返します。

```java
String getScreenNavigationKey();
```

> これは、`ScreenNavigationCategory`インターフェイスの`getScreenNavigationKey`と同じメソッドです。 String値`"cp.definition.general"`を返すことでこの方法を実行しました。

```java
public void render(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
    throws IOException;
```

> ここで、商品タイプに合わせてカスタマイズされた画面をレンダリングするためのコードを追加します。

### 商品タイプを完成させる

商品タイプは、商品を削除するためのバックエンドロジック、ナビゲーションメニューで画面をレンダリングするロジック、およびカスタム画面自体で構成されます。 以下を行います。

* [モジュールに`ServletContext`を構成する。](#configure-the-servletcontext-for-the-module)
* [`ScreenNavigationEntry`の`render`メソッドを実行します。](#implement-the-screennavigationentrys-render-method)
* [`ScreenNavigationEntry`の`isVisible`メソッドを上書きします。](#override-the-screennavigationentrys-isvisible-method)
* [商品タイプ削除ロジックを `deleteCPDefinition`に追加する。](#add-the-product-type-deletion-logic-to-deletecpdefinition)
* [JSPを追加して、カスタム画面をレンダリングする。](#add-a-jsp-to-render-the-custom-screen)
* [言語キーを`Language.properties`に追加する。](#add-the-language-key-to-languageproperties)

#### モジュールに`ServletContext`を構成する

モジュール内でJSPを見つけられるように、バンドルのシンボル名を使用して`ScreenNavigationEntry`クラスで`ServletContext`を定義します。

```java
@Reference(target = "(osgi.web.symbolicname=com.acme.c1n4.web)")
private ServletContext _servletContext
```

> `osgi.web.symbolicname`に設定した値は、 [bnd.bndファイル](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/catalog/adding-a-new-product-type/resources/liferay-c1n4.zip/c1n4-web/bnd.bnd)の`Bundle-SymbolicName`の値と一致します。 これらの値は、JSPを見つけるために`ServletContext`と一致する必要があります。
> 
> `ServletContext`が正しく生成されるように、bnd.bndファイルで`Web-ContextPath`の一意の値を宣言します。 この例では、`Web-ContextPath`は`/c1n4-web`に設定されています。 これらの値のリファレンスについては、[bnd.bnd](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/catalog/adding-a-new-product-type/resources/liferay-c1n4.zip/c1n4-web/bnd.bnd)を参照してください。

#### `ScreenNavigationEntry`の`render`メソッドを実行します。

```java
@Override
public void render(
        HttpServletRequest httpServletRequest,
        HttpServletResponse httpServletResponse)
    throws IOException {

    _jspRenderer.renderJSP(
        _servletContext, httpServletRequest, httpServletResponse,
        "/c1n4.jsp");
}
```

> `JSPRenderer`を使用して、製品タイプのカスタム画面のJSPをレンダリングします（この例では [c1n4.jsp](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/catalog/adding-a-new-product-type/resources/liferay-c1n4.zip/c1n4-web/src/main/resources/META-INF/resources/c1n4.jsp)）。 作成したJSPを見つけるためのパラメーターとして`ServletContext`を提供します。

#### `ScreenNavigationEntry`の`isVisible`メソッドを上書きします。

```java
@Override
public boolean isVisible(User user, CPDefinition cpDefinition) {
    if (cpDefinition == null) {
        return false;
    }

    return Objects.equals(
        cpDefinition.getProductTypeName(), getCategoryKey());
}
```

> カスタム画面を表示するタイミングを決定するロジックをここに実装します。 この例では、 `CPDefinition` の商品タイプがサンプルの商品タイプと一致するかどうかのみを確認します。

#### 商品タイプ削除ロジックを `deleteCPDefinition`に追加する

今回の例では、 `deleteCPDefinition`にロジックを追加する必要はありません。

#### JSPを追加して、カスタム画面をレンダリングする

今回の例では、「Hello C1N4.」と表示するJSPを追加しています。

```jsp
<h1>Hello C1N4.</h1>
```

> フォームやMVCアクションコマンドなど、カスタム画面で必要な他の入力またはアクションをここに実装します。 JSPからアクセスできるMVCアクションコマンドを追加する方法については、 [MVC Action Command](https://help.liferay.com/hc/en-us/articles/360018165091-MVC-Action-Command) を参照してください。

#### 言語キーを`Language.properties`に追加する

モジュール内の[Language.properties](https://github.com/liferay/liferay-learn/blob/master/docs/commerce/latest/en/developer-guide/catalog/adding-a-new-product-type/resources/liferay-c1n4.zip/c1n4-web/src/main/resources/content/Language.properties)ファイルに言語キーとその値を追加します。

```properties
c1n4-commerce-product-type=C1N4 Commerce Product Type
c1n4-screen-navigation-entry=C1N4 Screen Navigation Entry
```

> 詳細は、 [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application) を参照してください。

<a name="conclusion" />

## まとめ

　 これで、 `CPType`インターフェイスを実装するための基本を理解し、Liferay Commerceに新しい商品タイプとその独自のカスタム画面を追加しました。

<a name="additional-information" />

## 追加情報

  - [アプリケーションのローカライズ](https://help.liferay.com/hc/en-us/articles/360018168251-Localizing-Your-Application)
