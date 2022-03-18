# 構成の分類

構成インターフェースを登録すると、アプリケーションのUIが*［システム設定］* &rarr; *［プラットフォーム］* &rarr; *［サードパーティー］*で生成されます。 アプリケーションの設定UIが表示される場所に別のセクションとカテゴリーが必要な場合は、これを簡単に変更できます。

## 既存のカテゴリーの指定

利用可能なシステム設定のセクションは次のとおりです。 カテゴリーは次のセクションの下にネストされています。

* コマース
* プラットフォーム
* セキュリティ
* コンテンツとデータ
* その他

構成インターフェイスに`@ExtendedObjectClassDefinition`アノテーションを配置して、UIのカテゴリーを指定します。 たとえば、ブログの下にUIを配置する場合は、`@Meta.OCD`アノテーションの上に次の行を追加します。

```java
@ExtendedObjectClassDefinition(category = "blogs")
```

`@ExtendedObjectClassDefinition`クラスをインポートします。

```java
import com.liferay.portal.configuration.metatype.annotations.ExtendedObjectClassDefinition
```

アプリケーションを再デプロイすると、構成UIが*［Content and Data］* &rarr; *［Blogs］*の下に配置されます。

## 新しいセクションとカテゴリーの作成

独自のセクションとカテゴリーが必要な場合は、`ConfigurationCategory`インターフェイスを実装して作成できます。

[Setting and Accessing Configurations](./setting-and-accessing-configurations)のプロジェクト例に従った場合、このコードをアプリケーションの`configuration`フォルダに追加できます。 これは、構成インターフェイスのJavaファイルを保持しているフォルダと同じです。 以下のコードは、foobarセクションとfoobarカテゴリーを作成します。

```java
package com.acme.n2f3.web.internal.configuration;

import com.liferay.configuration.admin.category.ConfigurationCategory;

import org.osgi.service.component.annotations.Component;

@Component(service = ConfigurationCategory.class)
public class N2F3WebConfigurationCategory implements ConfigurationCategory {

    @Override
    public String getCategoryIcon() {
        return _CATEGORY_ICON;
    }

    @Override
    public String getCategoryKey() {
        return _CATEGORY_KEY;
    }

    @Override
    public String getCategorySection() {
        return _CATEGORY_SECTION;
    }

    private static final String _CATEGORY_ICON = "foobar";

    private static final String _CATEGORY_KEY = "foobar";

    private static final String _CATEGORY_SECTION = "foobar";

}
```

サンプルプロジェクトの構成インターフェイスの`@ExtendedObjectClassDefinition`アノテーションを`category = "n2f3"`から`category = "foobar"`に変更します。

カスタムセクションとカテゴリーの言語キーも、ローカライズファイルで指定する必要があります。 上記の例では、ポートレットの`src/main/resources/content/`フォルダにある`Language.properties`ファイルに次のキーを追加します。

```properties
category.foobar=Foobar
category-section.foobar=Foobar
```

サンプルプロジェクトを再デプロイして、変更を確認します。

![構成UIがカスタムカテゴリーに配置されました](./categorizing-a-configuration/images/01.png)

これで、アプリケーションの構成UIがカスタムセクションとカスタムカテゴリーに配置されました。
