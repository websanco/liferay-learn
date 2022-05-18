# ポートレットフィルタを使用したポートレットアクティビティの監査

ポートレットフィルターは、各 [ポートレットリクエスト処理フェーズ](../../../developing-applications/developing-a-java-web-application/reference/portlets.md#portlet-phases) の開始時にリクエストと応答をインターセプトするため、そこに機能を追加できます。 これにより、レンダリング、アクション、イベント、およびリソース提供の各フェーズでのポートレットアクティビティの監査に役立ちます。

次の手順に従って、ポートレットアクティビティを監査するためのポートレットフィルターを作成します。

1. フルネーム（`com_liferay_blogs_web_portlet_BlogsPortlet`など）でターゲットポートレットを識別します。

1. 監査するポートレットフェーズを決定し、 [`javax.portlet.filter`](http://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/filter/package-summary.html) パッケージから対応するポートレットフィルターインターフェイスを実装します。

   * アクションフェイズ - [`ActionFilter`](http://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/filter/ActionFilter.html)
   * イベントフェーズ - [`EventFilter`](http://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/filter/EventFilter.html)
   * レンダリングフェーズ - [`RenderFilter`](http://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/filter/RenderFilter.html)
   * リソース提供フェーズ - [`ResourceFilter`](http://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/filter/ResourceFilter.html)

   各ポートレットフェーズの詳細については、 [Portlets](../../../developing-applications/developing-a-java-web-application/reference/portlets.md#portlet-phases) を参照してください。

1. `@Component`アノテーションを使用して、OSGiフレームワーク内のコンポーネントをポートレットフィルターとして宣言し、それを`PortletFilter.class`サービスとして識別します。

   ```{note}
      ポートレットフィルターは [OSGi Declarative Service（DS）コンポーネント](https://enroute.osgi.org/FAQ/300-declarative-services.html) です。 フィルタは、portlet.xml`記述子または`@PortletLifecycleFilter`アノテーションを使用してポートレットに適用することもできます。 詳細については、ポートレット3.0仕様を参照してください。
   ```

1. `@Component`宣言に次のプロパティを入力します。

   * `"javax.portlet.name=[portlet_Name]"`：このプロパティは、フィルターのターゲットポートレットを設定します。
   * `"service.ranking:Integer=100"`：このプロパティは、フィルターのランキングを設定し、高い方の整数が最初に実行されます。 フィルターに最高ランクを割り当てて、フィルターチェーンの最初からフィルターが起動するようにします。

1. フィルターの`doFilter`メソッドをオーバーライドして、ポートレットフェーズの目的の側面を監査します。

次の例では、`RenderFilter`を使用して、ブログポートレットのレンダリングフェーズを監査します。

<a name="サンプルのポートレットフィルターをデプロイする" />

## サンプルのポートレットフィルターをデプロイする

```{include} /_snippets/run-liferay-portal.md
```

次の手順に従って、サンプルのポートレットフィルターをダウンロード、ビルドして、新しいDockerコンテナにデプロイします。

1. サンプルモジュールをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/liferay-internals/extending-liferay/portlet-filters/liferay-b4k8.zip -O
   ```

   ```bash
   unzip liferay-b4k8.zip -d liferay-b4k8
   ```

1. 次の`gradlew`コマンドを実行してJARファイルをビルドし、それを新しいDockerコンテナにデプロイします。

   ```bash
   ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   JARは、`build/libs`フォルダ（つまり、`b4k8-impl/build/libs/com.acme.b4k8.impl-1.0.0.jar`）に生成されます。

1. モジュールが正常にデプロイされ、コンテナコンソールを介して開始されたことを確認します。

   ```
   Processing com.acme.b4k8.impl-1.0.0.jar
   STARTED com.acme.b4k8.impl_1.0.0 [1656]
   ```

1. ブログウィジェットをサイトページに追加して、ポートレットフィルターが機能していることを確認します。

   ブログポートレットに対して描画リクエストが行われるたびに、コンテナコンソールに、レンダリング時間、平均レンダリング時間、およびレンダリングの総数の監査を含む警告メッセージが表示されます。

   ```
   WARN [http-nio-8080-exec-2][B4K8PortletFilter:54] Blogs portlet rendered in 3 ms with an average of 3 ms out of 1 renders.
   WARN [http-nio-8080-exec-10][B4K8PortletFilter:54] Blogs portlet rendered in 0 ms with an average of 1 ms out of 2 renders.
   ```

<a name="サンプルのレンダリングフィルターコード" />

## サンプルのレンダリングフィルターコード

提供されているサンプルのフィルターは、ブログポートレットを対象とし、`RenderFilter`インターフェイスを使用してそのレンダリングフェーズを監査します。

```java
@Component(
   property = {
      "javax.portlet.name=com_liferay_blogs_web_portlet_BlogsPortlet",
      "service.ranking:Integer=100"
   },
   service = PortletFilter.class
)
public class B4K8PortletFilter implements RenderFilter {

   @Override
   public void destroy() {
   }

   @Override
   public void doFilter(
         RenderRequest renderRequest, RenderResponse renderResponse,
         FilterChain filterChain)
      throws IOException, PortletException {

      long startTime = System.currentTimeMillis();

      filterChain.doFilter(renderRequest, renderResponse);

      long renderTime = (System.currentTimeMillis() - startTime) / 1000;

      _totalTime.add(renderTime);

      _count.increment();

      if (_log.isWarnEnabled()) {
         long count = _count.longValue();

         long averageRenderTime = _totalTime.longValue() / count;

         _log.warn(
            "Blogs portlet rendered in " + renderTime +
               " ms with an average of " + averageRenderTime +
                  " ms out of " + count + " renders.");
      }
   }

   @Override
   public void init(FilterConfig filterConfig) throws PortletException {
   }

   private static final Log _log = LogFactoryUtil.getLog(
      B4K8PortletFilter.class);

   private final LongAdder _count = new LongAdder();
   private final LongAdder _totalTime = new LongAdder();

}
```

このコードでは、フィルターは最初にOSGi DSコンポーネントとして宣言され、`PortletFilter.class`サービスとして識別されます。  この宣言の一部として、2つのプロパティも設定します。最初のプロパティは`BlogsPortlet`を対象とし、2番目のプロパティは重要度を`100`に設定します。

ポートレットフィルターは次に [`RenderFilter`](http://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/filter/RenderFilter.html) インターフェイスを実装し、 [`PortletFilter`](http://docs.liferay.com/portlet-api/3.0/javadocs/javax/portlet/filter/PortletFilter.html) インターフェイスを拡張します。 このインターフェイスには3つのメソッド（つまり、`init`、`destroy`、`doFilter`）が含まれており、ブログポートレットへの描画リクエストとその応答の両方でフィルタリングタスクを実行します。

* `init`：ポートレットフィルターが最初にLiferayにデプロイされ、ポートレットコンテナ内で初期化されたときに呼び出されます。

* `destroy`：サービスからポートレットフィルターを削除するために呼び出されます。

* `doFilter`：クライアントリクエストにより描画リクエスト/応答のペアがチェーンを通過するたびに、ポートレットコンテナによって呼び出されます。

   この例では、`doFilter`は次の方法でブログポートレットを監査します。

   1. レンダリングフェーズの開始時間をメモします。

      ```java
      long startTime = System.currentTimeMillis();
      ```

   1. `FilterChain`の`doFilter`メソッドを実行して、チェーン内のすべての`RenderFilter`を呼び出します。

      ```java
      filterChain.doFilter(renderRequest, renderResponse);
      ```

   1. ブログポートレットがレンダリングフェーズを完了するのにかかる時間を計算します。

      ```java
      long renderTime = (System.currentTimeMillis() - startTime) / 1000;
      ```

   1. 現在のレンダリング時間をすべてのレンダリングの合計時間に追加します。

      ```java
      _totalTime.add(renderTime);
      ```

   1. ポートレットレンダリングの総数を増やします。

      ```java
      _count.increment();
      ```

   1. `LongAdder`ユーティリティを使用してポートレットの平均レンダリング時間とレンダリングの総数を保存し、次にLogユーティリティを使用してこれらの値をポートレットの現在のレンダリング時間とともに表示します。

      ```java
      if (_log.isWarnEnabled()) {
         long count = _count.longValue();

         long averageRenderTime = _totalTime.longValue() / count;

         _log.warn(
            "Blogs portlet rendered in " + renderTime +
               " ms with an average of " + averageRenderTime +
                  " ms out of " + count + " renders.");
      }
      ```

   描画リクエストが行われるたびに、この`doFilter`が呼び出されます。

<a name="追加情報" />

## 追加情報

* [Portlets](../../../developing-applications/developing-a-java-web-application/reference/portlets.md)
<!--TASK: Add link to Using Portlet Filters article when finished -->
