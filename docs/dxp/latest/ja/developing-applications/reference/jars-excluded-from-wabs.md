# WABから除外されたJAR

[Liferayで生成されたWebアプリケーションバンドル（WAB）](./deploying-wars-wab-generator.md)からは、[Liferayがすでにエクスポートしているパッケージ](../../liferay-internals/reference/exported-third-party-packages.md)を含むサードパーティーのJARが削除されます。 追加のJARから同じサードパーティーパッケージをデプロイすると、デバッグが難しい奇妙なエラーが発生する可能性があります。 Liferayの[`module.framework.web.generator.excluded.paths`](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Module%20Framework%20Web%20Application%20Bundles) [ポータルプロパティ](../../installation-and-upgrades/reference/portal-properties.md)は、除外されたJARを指定しています。 デフォルトの`module.framework.web.generator.excluded.paths`プロパティからの抜粋を以下に示します。

``` properties
module.framework.web.generator.excluded.paths=\
    WEB-INF/lib/ant.jar,\
    WEB-INF/lib/asm-debug-all.jar,\
    WEB-INF/lib/aspectj-rt.jar,\
    WEB-INF/lib/bnd.jar,\
    WEB-INF/lib/ccpp.jar,\
    WEB-INF/lib/commons-beanutils.jar,\
    WEB-INF/lib/commons-chain.jar,\
    ...
```

```{note}
WABが[liferay-plugin-package.properties](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/liferay-plugin-package_7_3_0.properties.html) ファイル内の`portal-dependency-jars`プロパティにJARを一覧表示していても、LiferayはこれらのJARをWABから除外します。
```

## 異なるバージョンのサードパーティーパッケージを含める

WARで[エクスポートされたサードパーティーパッケージ](../../liferay-internals/reference/exported-third-party-packages.md)の異なるバージョンが必要な場合は、除外されたJARとは異なる名前のJARにそれらを含めることができます。

たとえば、Spring Frameworkパッケージの異なるバージョンを含める方法は次のとおりです。

1.  [Liferayが提供しているパッケージのバージョン](../../liferay-internals/reference/exported-third-party-packages.md)を判断します。 たとえば、Liferayは`com.liferay.portal.bootstrap`モジュールを介してSpring Frameworkバージョン4.1.9パッケージをエクスポートします。
   
        Export-Package:\
            ...
            org.springframework.*;version='4.1.9',\
            ...

2.  必要なパッケージバージョンを提供する[アーティファクトを見つけます](../../liferay-internals/fundamentals/configuring-dependencies/finding-artifacts.md)。

3.  Liferayが除外する対応するアーティファクトがあるかどうか、 [`module.framework.web.generator.excluded.paths`](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Module%20Framework)ポータルプロパティを確認します。 たとえば、Liferayは次のSpring Framework JARを除外します。

    ``` properties
    module.framework.web.generator.excluded.paths=\
        ...
        WEB-INF/lib/spring-aop.jar,\
        WEB-INF/lib/spring-aspects.jar,\
        WEB-INF/lib/spring-beans.jar,\
        WEB-INF/lib/spring-context.jar,\
        WEB-INF/lib/spring-context-support.jar,\
        WEB-INF/lib/spring-core.jar,\
        WEB-INF/lib/spring-expression.jar,\
        WEB-INF/lib/spring-jdbc.jar,\
        WEB-INF/lib/spring-jms.jar,\
        WEB-INF/lib/spring-orm.jar,\
        WEB-INF/lib/spring-oxm.jar,\
        WEB-INF/lib/spring-tx.jar,\
        WEB-INF/lib/spring-web.jar,\
        WEB-INF/lib/spring-webmvc.jar,\
        WEB-INF/lib/spring-webmvc-portlet.jar,\
        ...
    ```

4.  必要なアーティファクトの名前が除外されたアーティファクトと同じである場合（前の手順を参照）、必要なアーティファクトの名前を変更します。 たとえば、Spring Frameworkバージョン3.0.7のSpring AOP JARを、プロジェクトの`WEB-INF/lib`フォルダに含め、名前を`spring-aop.jar`（除外されたJAR）以外に変更することで使用できます。

    ```{tip}
    JAR名にバージョンを追加すると（つまり`spring-aop-3.0.7.RELEASE.jar`）、除外されたJARと区別され、WAB（バンドルされたWAB）から削除されなくなります。
    ```

これで、WABに必要なJARのバージョンが含まれました。

## 追加情報

  - [Exported Third Party Packages](../../liferay-internals/reference/exported-third-party-packages.md)
  - [Configuring Dependencies](../../liferay-internals/fundamentals/configuring-dependencies.md)
  - [Deploying WARs (WAB Generator)](./deploying-wars-wab-generator.md)
