# WARのデプロイ（WABジェネレータ）

アプリケーションは、Java EEスタイルのWebアプリケーションARchive（WAR）アーティファクトまたはJava ARchive（JAR）OSGiバンドルアーティファクトとして作成できます。 Beanポートレット、PortletMVC4Springポートレット、およびJSFポートレットは、フレームワークがWARレイアウトを想定し、`WEB-INF/web.xml`記述子などのJava EEリソースを必要とするため、WARとしてパッケージ化する必要があります

Liferayは、これらのWARスタイルのプラグインをLiferayのOSGiランタイムによってOSGiモジュールのようにデプロイおよび処理する方法を提供します。 それらは*WAB*に変換できます。

Liferay DXPは、Java EEスタイルWARの展開のためのOSGi Web Application Bundle（WAB）標準をサポートしています。 WABは、WARレイアウトを持ち、`Bundle-SymbolicName` OSGiディレクティブを含む`META-INF/MANIFEST.MF`ファイルを含むアーカイブです。 WABはOSGiバンドルです。 プロジェクトソースにはWARレイアウトがありますが、アーティファクトファイル名は拡張子`.jar`または`.war`で終わる場合があります。

## WABジェネレータがWARを変換する方法

Liferayは、WABジェネレータによって自動生成されたWABの使用のみをサポートしています。 WABジェネレータは、デプロイメント中に従来のWARスタイルのプラグインをWABに変換します。 では、WABジェネレータは具体的にどのようにWARファイルをWABに変換するするのでしょうか。

WABジェネレータは、プラグインWARのJSP、記述子ファイル、およびクラス（`WEB-INF/classes`内および埋め込みJAR内）で参照されるパッケージを検出します。 記述子ファイルには、`web.xml`、`liferay-web.xml`、`portlet.xml`、`liferay-portlet.xml`、および`liferay-hook.xml`があります。 WAB ジェネレータは、検出されたパッケージがプラグインの`WEB-INF/classes` 内フォルダにあるかどうか、または`WEB-INF/lib`フォルダ内にある埋め込みJARにあるかどうかを検証します。 どちらの場所でも見つからないパッケージは、WABの`META-INF/MANIFEST.MF`ファイル内の`Import-Package` OSGiヘッダーに追加されます。

以下のタイプの場所でのみ参照されるパッケージをインポートするには、`Import-Package` OSGiヘッダーをプラグインの`WEB-INF/liferay-plugin-package.properties`ファイルに追加し、そのヘッダーの値のリストにパッケージを追加する必要があります。

  - 認識されない記述子ファイル
  - カスタムまたは認識されない記述子要素または属性
  - リフレクションコード
  - クラスローダーコード

## WARとWABのストラクチャー比較

WABフォルダストラクチャーとWARフォルダストラクチャーには違いがあります。 WARスタイルのポートレットの次のフォルダストラクチャーについて考えてみます。

**WAR**

  - `my-war-portlet`
      - `src`
          - `main`
              - `java`
              - `webapp`
                  - `WEB-INF`
                      - `classes`
                      - `lib`
                      - `resources`
                      - `views`
                      - `liferay-display.xml`
                      - `liferay-plugin-package.properties`
                      - `liferay-portlet.xml`
                      - `portlet.xml`
                      - `web.xml`

WARスタイルのポートレットがLiferayにデプロイされ、WABジェネレータによって処理されると、ポートレットのフォルダストラクチャーが変換されます。

**WAB**

  - `my-war-portlet-that-is-now-a-wab`
      - `META-INF`
          - `MANIFEST.MF`
      - `WEB-INF`
          - `classes`
          - `lib`
          - `resources`
          - `views`
          - `liferay-display.xml`
          - `liferay-plugin-package.properties`
          - `liferay-portlet.xml`
          - `portlet.xml`
          - `web.xml`

主な違いは、`META-INF/MANIFEST.MF`ファイルの追加です。 WABジェネレータは、OSGi対応のマニフェストファイルを自動的に生成します。 マニフェストファイルの内容に影響を与えるようにしたい場合は、プラグインの`liferay-plugin-package.properties`ファイルにBndディレクティブとOSGiヘッダを直接配置することができます。

```{note}
生成されたWABは、手動で追加された` bnd.bnd`ファイルまたはビルド時プラグイン（例：` bnd-maven-plugin`）を使用できません。
```

## WARのデプロイ

WARプラグインに基づいてWABをデプロイするには、WARプラグインを`[Liferay Home]`内のLiferayインスタンスの<0>deploy/</0>フォルダにコピーします。

## WABのコピーを保存する

必要に応じて、WABをローカルフォルダに保存します。 これにより、生成されたWABを確認することができます。 生成されたWABSを保存するには、以下の[portal properties](../../installation-and-upgrades/reference/portal-properties.md)を`[Liferay Home]/portal-ext.properties`ファイルに追加します。 その後、Liferayサーバーを再起動します。

``` properties
module.framework.web.generator.generated.wabs.store=true
module.framework.web.generator.generated.wabs.store.dir=${module.framework.base.dir}/wabs
```

これらのプロパティは、WABジェネレータに生成されたWABをインストールの`osgi/wabs/`フォルダに保存するように指示します。 生成されたWABは、上記のWABストラクチャーの例と同じ構造になっています。 [Module Framework Web Application Bundles](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Module%20Framework%20Web%20Application%20Bundles)のプロパティセクションで詳細を説明しています。

WARプラグインをWABとしてデプロイでき、WABのコピーを保存して調べる方法が分かりました。

## 追加情報

  - [JARs Excluded from WABs](./jars-excluded-from-wabs.md)
  - [Resolving Third Party Library Package Dependencies](../../liferay-internals/fundamentals/configuring-dependencies/resolving-third-party-library-package-dependencies.md)
