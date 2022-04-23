# PortletMVC4Springプロジェクトの構造

PortletMVC4Springポートレットは、WARにパッケージ化されています。 Liferayは、JSP/JSPXおよびThymeleafテンプレートを使用するように構成されたプロジェクトを作成するためのMavenアーキタイプを提供します。 それらのコマンドを以下に示します。 PortletMVC4Springプロジェクトストラクチャーは、そのコマンドに従います。

## PortletMVC4Springプロジェクトを生成するためのMavenコマンド

JSPXおよび [Thymeleaf](https://www.thymeleaf.org) ビューテンプレートを使用するPortletMVC4Springポートレットプロジェクトを生成するためのMavenコマンドは次のとおりです。

### SP/JSPXフォームポートレット

```bash
mvn archetype:generate \
* DarchetypeGroupId=com.liferay.portletmvc4spring.archetype \
* DarchetypeArtifactId=com.liferay.portletmvc4spring.archetype.form.jsp.portlet \
* DarchetypeVersion=5.1.0 \ 
* DgroupId=com.mycompany \ 
* DartifactId=com.mycompany.my.form.jsp.portlet
```

### Thymeleafフォームポートレット

```bash
mvn archetype:generate \
* DarchetypeGroupId=com.liferay.portletmvc4spring.archetype \
* DarchetypeArtifactId=com.liferay.portletmvc4spring.archetype.form.thymeleaf.portlet \
* DarchetypeVersion=5.1.0 \
* DgroupId=com.mycompany \
* DartifactId=com.mycompany.my.form.thymeleaf.portlet
```

## プロジェクトストラクチャー

Mavenコマンドは、モデルクラスとコントローラークラス、ビューテンプレート、リソースバンドル、スタイルシートなどを含むプロジェクトを生成します。 [Springコンテキストと構成ファイル](./portletmvc4spring-configuration-files.md)は、PortletMVC4Spring開発の要点を設定します。 結果として生じるプロジェクトストラクチャーは次のとおりです。

* `[com.mycompany.my.form.jsp.portlet]`/ &rarr; 任意のプロジェクト名
    * `src/`
        * `メイン/`
            * `java/[my-package-path]/`
                * `controller/` &rarr; コントローラクラスのサブパッケージ（オプション）
                * `dto/` &rarr; モデル（データ転送オブジェクト）クラスのサブパッケージ（オプション）
                * `resources/` &rarr; クラスパスに含めるリソース
                    * `content/` &rarr; リソースバンドル
                    * `log4j.properties` &rarr; Log4Jロギング構成
                * `webapp/`
                    * `リソース/`
                        * `css/` &rarr; スタイルシート
                        * `images/` &rarr; 画像
                    * `WEB-INF/`
                        * `spring-context/` &rarr; コンテキスト
                            * `portlet/` &rarr; ポートレットのコンテキスト
                                * `portlet1-context.xml` &rarr; ポートレットのコンテキスト
                            * `portlet-application-context.xml` &rarr; アプリケーションコンテキスト
                        * `views/` &rarr; ビューテンプレート
                        * `liferay-display.xml` &rarr; ポートレット表示構成
                        * `liferay-plugin-package.properties` &rarr; パッケージ記述子
                        * `liferay-portlet.xml` &rarr; Liferay固有のポートレット構成
                        * `portlet.xml` &rarr; ポートレット構成
                        * `web.xml` &rarr; Webアプリケーション構成
        * `test/java/` &rarr; テストソースファイル
    * `build.gradle` &rarr; Gradleビルドファイル
    * `pom.xml` &rarr; Maven POM