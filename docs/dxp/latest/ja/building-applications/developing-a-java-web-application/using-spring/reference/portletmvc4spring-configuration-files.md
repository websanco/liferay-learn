# PortletMVC4Spring構成ファイル

PortletMVC4Springアプリケーションの`WEB-INF`フォルダには、次の記述子、Springコンテキスト、およびプロパティファイルがあります。

* `web.xml` &rarr; Webアプリケーション記述子
* `portlet.xml` &rarr; ポートレットアプリケーション記述子
* `liferay-portlet.xml` &rarr;  Liferay固有のポートレット記述子
* `liferay-display.xml` &rarr; Liferay固有の表示記述子
* `spring-context/portlet-application-context.xml` &rarr; ポートレットアプリケーションコンテキスト
* `spring-context/portlet/[portlet]-context.xml` &rarr; ポートレットコンテキスト
* `liferay-plugin-package.properties` &rarr; パッケージ記述子

各ファイルの例が提供され、ポートレット固有のコンテンツがハイライト表示されています。

## web.xml

サーブレットコンテナは`web.xml`を処理します。 このファイルは、ポートレットをレンダリングするサーブレットと、ポートレットアプリケーションのコンテキスト、サーブレット、フィルタ、リスナーなどを指定します。 `web.xml`の例を次に示します。

```xml
<?xml version="1.0"?>

<web-app version="3.1" xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd">
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/spring-context/portlet-application-context.xml</param-value>
    </context-param>
    <servlet>
        <servlet-name>ViewRendererServlet</servlet-name>
        <servlet-class>com.liferay.portletmvc4spring.ViewRendererServlet</servlet-class>
        <load-on-startup>1</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>ViewRendererServlet</servlet-name>
        <url-pattern>/WEB-INF/servlet/view</url-pattern>
    </servlet-mapping>
    <filter>
        <filter-name>delegatingFilterProxy</filter-name>
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
    </filter>
    <filter-mapping>
        <filter-name>delegatingFilterProxy</filter-name>
        <url-pattern>/WEB-INF/servlet/view</url-pattern>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>INCLUDE</dispatcher>
    </filter-mapping>
    <listener>
        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
    </listener>
</web-app>
```

`<context-param/>`要素は、ポートレットアプリケーションコンテキスト（後述）へのパスを提供します。

```xml
<context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>/WEB-INF/spring-context/portlet-application-context.xml</param-value>
</context-param>
```

`<servlet/>`要素と`<servlet-mapping/>`要素は、サーブレットとそのビューの内部位置を設定します。

```xml
<servlet>
    <servlet-name>ViewRendererServlet</servlet-name>
    <servlet-class>com.liferay.portletmvc4spring.ViewRendererServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>ViewRendererServlet</servlet-name>
    <url-pattern>/WEB-INF/servlet/view</url-pattern>
</servlet-mapping>
```

[`ViewRendererServlet`](https://liferay.github.io/portletmvc4spring/apidocs/com/liferay/portletmvc4spring/ViewRendererServlet.html)は、  ポートレットリクエストをサーブレットリクエストに変換し、Spring Web MVCインフラストラクチャと、JSP、Thymeleaf、Velocityなどのインフラストラクチャのレンダラーを使用してビューをレンダリングできるようにします。

フィルタとフィルタマッピングは転送するように設定されており、必要に応じてサーブレットビューが含まれます。

```xml
<filter>
    <filter-name>delegatingFilterProxy</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
    <filter-name>delegatingFilterProxy</filter-name>
    <url-pattern>/WEB-INF/servlet/view</url-pattern>
    <dispatcher>FORWARD</dispatcher>
    <dispatcher>INCLUDE</dispatcher>
</filter-mapping>
```

リスナーは、アプリケーションのコンテキストを処理するように構成されています。

```xml 
<listener>
    <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
```

Liferayのプロジェクトアーキタイプは、このすべての定型コードを生成します。

## portlet.xml

`portlet.xml`ファイルは、ポートレットコンテナにポートレットアプリケーションを記述します。 以下に例を示します。

```xml
<?xml version="1.0"?>

<portlet-app xmlns="http://xmlns.jcp.org/xml/ns/portlet" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/portlet http://xmlns.jcp.org/xml/ns/portlet/portlet-app_3_0.xsd" version="3.0">
    <portlet>
        <portlet-name>portlet1</portlet-name>
        <display-name>com.mycompany.my.form.jsp.portlet</display-name>
        <portlet-class>com.liferay.portletmvc4spring.DispatcherPortlet</portlet-class>
        <init-param>
            <name>contextConfigLocation</name>
            <value>/WEB-INF/spring-context/portlet/portlet1-context.xml</value>
        </init-param>
        <expiration-cache>0</expiration-cache>
        <supports>
            <mime-type>text/html</mime-type>
            <portlet-mode>view</portlet-mode>
        </supports>
        <resource-bundle>content.portlet1</resource-bundle>
        <portlet-info>
            <title>com.mycompany.my.form.jsp.portlet</title>
            <short-title>com.mycompany.my.form.jsp.portlet</short-title>
            <keywords>com.mycompany.my.form.jsp.portlet</keywords>
        </portlet-info>
        <security-role-ref>
            <role-name>administrator</role-name>
        </security-role-ref>
        <security-role-ref>
            <role-name>guest</role-name>
        </security-role-ref>
        <security-role-ref>
            <role-name>power-user</role-name>
        </security-role-ref>
        <security-role-ref>
            <role-name>user</role-name>
        </security-role-ref>
    </portlet>
    <filter>
        <filter-name>SpringSecurityPortletFilter</filter-name>
        <filter-class>com.liferay.portletmvc4spring.security.SpringSecurityPortletFilter</filter-class>
        <lifecycle>ACTION_PHASE</lifecycle>
        <lifecycle>RENDER_PHASE</lifecycle>
        <lifecycle>RESOURCE_PHASE</lifecycle>
    </filter>
    <filter-mapping>
        <filter-name>SpringSecurityPortletFilter</filter-name>
        <portlet-name>portlet1</portlet-name>
    </filter-mapping>
</portlet-app>
```

このアプリケーションには、`portlet1`という名前のポートレットが1つあります。

```xml
<portlet-name>portlet1</portlet-name>
<display-name>com.mycompany.my.form.jsp.portlet</display-name>
<portlet-class>com.liferay.portletmvc4spring.DispatcherPortlet</portlet-class>
```

`<portlet-name/>`は内部であり、`<display-name/>`はユーザーに表示されます。 `<portlet-class/>`は、ポートレットのJavaクラスを指定します。

**重要：**すべてのPortletMVC4Springポートレットは、`<portlet-class>com.liferay.portletmvc4spring.DispatcherPortlet</portlet-class>`を指定する必要があります。

`<supports/>` 要素は、ポートレットテンプレートが使用するMIME 種別を宣言する必要があります。

`<resource-bundle/>`は、ポートレットのローカライズされたJavaメッセージプロパティへのパスを設定します。 たとえば、次の要素は`content/portlet1.properties`のプロパティを参照します。

```xml
<resource-bundle>content.portlet1</resource-bundle>
```

`<portlet-info/>` 要素は、ポートレットのタイトルと予約済みキーワードを一覧表示します。

`<security-role-ref/>` 要素は、ポートレットを構成するデフォルトのユーザーロールを宣言します。

最後に、[`SpringSecurityPortletFilter`](https://liferay.github.io/portletmvc4spring/apidocs/index.html)という名前の `<filter/>`は、クロスサイトリクエストフォージェリ（CSRF）を回避します。

```xml
<filter>
    <filter-name>SpringSecurityPortletFilter</filter-name>
    <filter-class>com.liferay.portletmvc4spring.security.SpringSecurityPortletFilter</filter-class>
    <lifecycle>ACTION_PHASE</lifecycle>
    <lifecycle>RENDER_PHASE</lifecycle>
    <lifecycle>RESOURCE_PHASE</lifecycle>
</filter>
<filter-mapping>
    <filter-name>SpringSecurityPortletFilter</filter-name>
    <portlet-name>portlet1</portlet-name>
</filter-mapping>
```

[`portlet XSD`](https://docs.liferay.com/portlet-api/3.0/portlet-app_3_0.xsd)は`portlet.xml`を定義します。 次に、Liferay固有のポートレット記述子について説明します。

## liferay-portlet.xml

`liferay-portlet.xml` ファイルは、より多くの開発者機能を提供するLiferay固有の設定を適用します。 以下に例を示します。

```xml 
<?xml version="1.0"?>
<!DOCTYPE liferay-portlet-app PUBLIC "-//Liferay//DTD Portlet Application 7.4.0//EN" "http://www.liferay.com/dtd/liferay-portlet-app_7_4_0.dtd">

<liferay-portlet-app>
    <portlet>
        <portlet-name>portlet1</portlet-name>
        <icon>/resources/images/icon.png</icon>
        <requires-namespaced-parameters>false</requires-namespaced-parameters>
    </portlet>
    <role-mapper>
        <role-name>administrator</role-name>
        <role-link>Administrator</role-link>
    </role-mapper>
    <role-mapper>
        <role-name>guest</role-name>
        <role-link>Guest</role-link>
    </role-mapper>
    <role-mapper>
        <role-name>power-user</role-name>
        <role-link>Power User</role-link>
    </role-mapper>
    <role-mapper>
        <role-name>user</role-name>
        <role-link>User</role-link>
    </role-mapper>
</liferay-portlet-app>
```

この`<portlet/>`要素は、アイコンをポートレットに関連付け、名前空間化されたパラメーターが不要であることを示します。

`<role-mapper/>`要素は、ポートレットをデフォルトのLiferay DXPユーザーロールに関連付けます。

[`liferay-portlet-app-[version].dtd`ファイル](https://learn.liferay.com/reference/latest/en/dxp/definitions/index.html)は、`liferay-portlet.xml`ファイルを定義します。

## liferay-display.xml

`liferay-display.xml`は、表示特性をポートレットに適用します。 たとえば、次の記述子は、ポートレットをLiferay DXPの［ウィジェットの追加］メニューのウィジェットカテゴリに関連付けます。

```xml
<?xml version="1.0"?>
<!DOCTYPE display PUBLIC "-//Liferay//DTD Display 7.4.0//EN" "http://www.liferay.com/dtd/liferay-display_7_4_0.dtd">

<display>
<category name="category.sample">
    <portlet id="portlet1" />
</category>
</display>
```

詳細については、[`liferay-display-[version].dtd`ファイル](https://learn.liferay.com/reference/latest/en/dxp/definitions/index.html)を参照してください。

次に、アプリケーションコンテキストを見てみましょう。

## ポートレットアプリケーションコンテキスト

このコンテキストは、アプリケーションのすべてのポートレットに適用されます。 ここで、ビューリゾルバー、リソースバンドル、セキュリティBean、プロキシなどを指定します。 以下に例を示します。

```xml 
<?xml version="1.0"?>

<beans
    xmlns="http://www.springframework.org/schema/beans"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <context:annotation-config />
    <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver" id="viewResolver">
        <property name="contentType" value="text/html;charset=UTF-8" />
        <property name="prefix" value="/WEB-INF/views/" />
        <property name="suffix" value=".jspx" />
        <property name="viewClass" value="com.liferay.portletmvc4spring.PortletJstlView" />
    </bean>
    <bean id="messageSource" class="org.springframework.context.support.ResourceBundleMessageSource">
        <property name="basenames">
            <list>
                <value>content.portlet1</value>
            </list>
        </property>
        <property name="defaultEncoding" value="UTF-8" />
    </bean>
    <bean id="springSecurityPortletConfigurer" class="com.liferay.portletmvc4spring.security.SpringSecurityPortletConfigurer" />
    <bean id="delegatingFilterProxy" class="org.springframework.web.filter.DelegatingFilterProxy">
        <property name="targetBeanName" value="springSecurityFilterChain" />
    </bean>
</beans>
```

上記のビューリゾルバーBeanは、JSPXビューテンプレートを処理します。 たとえば、Thymeleafビューテンプレートを解決するには、次のBeanを指定できます。

```xml 
<bean class="org.thymeleaf.templateresolver.ServletContextTemplateResolver" id="templateResolver">
    <property name="prefix" value="/WEB-INF/views/"/>
    <property name="suffix" value=".html"/>
    <property name="templateMode" value="HTML"/>
</bean>
<bean class="org.thymeleaf.spring5.SpringTemplateEngine" id="templateEngine">
    <property name="templateResolver" ref="templateResolver"></property>
    <property name="enableSpringELCompiler" value="true" />
</bean>
<bean class="org.thymeleaf.spring5.view.ThymeleafViewResolver" id="viewResolver">
    <property name="templateEngine" ref="templateEngine"/>
    <property name="order" value="1"/>
</bean>
```

コンテキストの`springSecurityPortletConfigurer` Beanは、Spring Securityの使用を容易にします。

```xml
<bean id="springSecurityPortletConfigurer" 
    class="com.liferay.portletmvc4spring.security.SpringSecurityPortletConfigurer" />
```

アプリケーションの各ポートレットのコンテキストを指定することもできます。

## ポートレットコンテキスト

ポートレットに固有のBeanは、ポートレットのコンテキストに入ります。 アノテーションはPortletMVC4Springポートレットを開発する最も簡単な方法であるため、ポートレットコンテキストでMVCアノテーションスキャンを指定する必要があります。

```xml 
<?xml version="1.0"?>

<beans
    xmlns="http://www.springframework.org/schema/beans"
    xmlns:context="http://www.springframework.org/schema/context"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:mvc="http://www.springframework.org/schema/mvc"
    xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
        http://www.springframework.org/schema/mvc http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <context:component-scan base-package="portlet1**"/>
    <mvc:annotation-driven/>
</beans>
```

ポートレットコンテキストの命名規則は、`[portlet-name]-context.xml`です。 ポートレットを独自のコンテキストに関連付けるには、アプリケーションの`portlet.xml`ファイルを編集し、`<portlet/>`要素をポートレットのコンテキストにマップする`<init-param/>`要素を追加します。

```xml 
<init-param>
    <name>contextConfigLocation</name>
    <value>/WEB-INF/spring-context/portlet/portlet1-context.xml</value>
</init-param>
```

最後にアプリケーションパッケージについて説明します。

## liferay-plugin-package.properties

このファイルは、アプリケーションの名前、バージョン、Javaパッケージのインポート/エクスポート、およびOSGiメタデータを指定します。 パッケージプロパティファイルの例を次に示します。

```{properties} 
author=N/A
change-log=
licenses=N/A
liferay-versions=7.2.0+
long-description=
module-group-id=com.mycompany
module-incremental-version=1
name=com.mycompany.my.form.jsp.portlet
page-url=
short-description=my portlet short description
tags=myTag
Bundle-Version: 1.0.0
Import-Package: com.liferay.portal.webserver,com.liferay.portal.kernel.servlet.filters.invoker
```

次のOSGiメタデータヘッダーを使用して、[必要なJavaパッケージをインポート](../../../..//liferay-internals/fundamentals/importing-packages.md)します。

```{properties}
Import-Package: com.liferay.portal.webserver,\
com.liferay.portal.kernel.servlet.filters.invoker
```

ポートレットアプリケーションのWARファイルをデプロイすると、[WABジェネレータ](../../../reference/deploying-wars-wab-generator.md)は指定されたOSGiメタデータを、Liferayのランタイムフレームワークにデプロイされた結果として生じるWebアプリケーションバンドル（WAB）に追加します。

[`liferay-plugin-package-[version].dtd`ファイル](https://learn.liferay.com/reference/latest/en/dxp/definitions/index.html)は、`liferay-plugin-package.properties`ファイルを記述します。

## 追加情報

* [PortletMVC4Spring Annotations](./portletmvc4spring-annotations.md)
* [Migrating to PortletMVC4Spring \(Help Center\)](https://help.liferay.com/hc/en-us/articles/360030614052-Migrating-to-PortletMVC4Spring)