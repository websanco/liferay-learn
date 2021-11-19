# エクスポートされたサードパーティパッケージ

Liferayでは、100以上のサードパーティのJavaパッケージを実行時に提供しています。 `com.liferay.portal.bootstrap`モジュールは、個々のパッケージを明示的に指定したり、グロブを使ってパッケージのグループを指定することで、パッケージをエクスポートします。 例えば、[7.3.4-ga5](https://github.com/liferay/liferay-portal/blob/7.3.4-ga5/modules/core/portal-bootstrap/system.packages.extra.bnd)の`Export-Package` 宣言からの抜粋を以下に示します。

``` groovy
Export-Package:\
    ...
    \
    org.aspectj.*,\
    \
    org.dom4j.*;version='2.1.3',\
    \
    org.hibernate.*;version='3.6.10',\
    \
    org.jaxen.*;version='1.1.6',\
    \
    org.jdom.*;version='1.1.3',\
    \
    org.json.*;version='20180813',\
    \
    org.objectweb.asm;version='7.0',\
    org.objectweb.asm.commons;version='7.0',\
    org.objectweb.asm.signature;version='7.0',\
    org.objectweb.asm.tree;version='7.0',\
    org.objectweb.asm.tree.analysis;version='7.0',\
    org.objectweb.asm.util;version='7.0',\
    \
    org.slf4j;version='1.7.2',\
    org.slf4j.helpers;version='1.7.2',\
    org.slf4j.spi;version='1.7.2',\
    \
    org.springframework.*;version='4.1.9',\
    \
    ...
```

複数のパッケージは、 `org.aspectj.*`の`*`のようにワイルドカード文字を使って指定します。 `org.objectweb.asm*`パッケージや`org.slf4j*`パッケージのようなパッケージのグループは、`\`文字だけの行で区切られます。

異なるJARから同じパッケージをエクスポートすると、「パッケージの分割」の問題が発生します。 これにより、定義ができない問題が発生する可能性があります。 そのため、Liferayがエクスポートするパッケージと同じパッケージを持つJARのデプロイは控えてください。

## 実行時にエクスポートされたパッケージをLiferayに依存する

プロジェクトにコンパイル時にパッケージが必要であるが、実行時にエクスポートされたパッケージをLiferayに依存していることを確認する方法は次のとおりです。

1.  プロジェクトが必要とするパッケージが、`com.liferay.portal.bootstrap`モジュールのエクスポートマニフェストにリストされているかどうかを確認します。 確認方法は2つあります。

    **Bndソースファイル：**Liferayソースコードのコピーがある場合は、`modules/core/portal-bootstrap/system.packages.extra.bnd`ファイルの`Export-Package`宣言を調べます。 エクスポートされたパッケージは、上記のように分かりやすい形式で表示されています。 Liferayは、この`.bnd`ファイルに基づいて`com.liferay.portal.bootstrap`モジュールの`META-INF/system.packages.extra.mf`ファイルを生成します。

    **JARマニフェスト：** `[Liferay Home]/osgi/core/com.liferay.portal.bootstrap.jar`の`META-INF/system.packages.extra.mf`ファイルは、エクスポートされたパッケージを宣言します。 JARはLiferayのインストールに含まれているので便利ですが、マニフェストファイルの `Export-Package`宣言の形式はあまり分かりやすいものではありません。

2.  プロジェクトが提供されたサードパーティーのパッケージのいずれかを使用している場合は、`providedCompile` Gradleスコープを使用して、コンパイル時のアーティファクトを依存関係として追加します。 `providedCompile`スコープ内のアーティファクトは、コンパイル時に利用可能ですが、生成されたJARからは除外されます。

    例えば、Spring Beanのパッケージを使用しているプロジェクトの場合、`providedCompile`スコープに以下のアーティファクトの依存関係を指定します。

    ``` groovy
    dependencies {
        providedCompile group: "org.springframework", name: "spring-bean", version: "4.1.9"
        ...
    }
    ```

これで、Liferayのエクスポートされたサードパーティのパッケージを安全に利用できるようになりました。

## 追加情報

  - [Configuring Dependencies](../fundamentals/configuring-dependencies.md)
  - [JARs Excluded from WABs](../../building-applications/reference/jars-excluded-from-wabs.md)
  - [Resolving Third Party Library Package Dependencies](../fundamentals/configuring-dependencies/resolving-third-party-library-package-dependencies.md)
