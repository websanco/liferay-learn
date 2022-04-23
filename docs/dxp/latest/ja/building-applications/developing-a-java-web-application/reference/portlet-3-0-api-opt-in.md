# Portlet 3.0 API オプトイン

ポートレットは、バージョン3.0を指定して、Portlet 3.0 APIに「オプトイン」する必要があります。 3.0 Portlet APIのバージョンは、次の方法で指定できます。

## 標準のポートレットの`@PortletApplication`アノテーション

標準ポートレットは、 [`@PortletApplication`](https://learn.liferay.com/reference/latest/en/portlet-api/javax/portlet/annotations/PortletApplication.html) アノテーションを指定するだけで済みます。

```java
@PortletApplication(version="3.0") // 3.0 is the default for this annotation attribute
@PortletConfiguration(portletName="myPortlet")
public class MyPortlet {
    ...
}
```

## Liferay MVCポートレットの`@Component`アノテーション

`MVCPortlet`などの宣言型サービスポートレットは、`@Component`アノテーションでバージョン3.0を指定できます。

```java
@Component(properties="javax.portlet.version=3.0", service=javax.portlet.Portlet.class)
public class MyDeclarativeServicesPortlet {
    ...
}
```

### `portlet.xml`記述子

すべてのポートレットは、`portlet.xml`記述子でバージョン3.0を指定できます。

```xml
<?xml version="1.0"?>

<portlet-app xmlns="http://xmlns.jcp.org/xml/ns/portlet"
             xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/portlet http://xmlns.jcp.org/xml/ns/portlet/portlet-app_3_0.xsd"
             version="3.0">
    ...
</portlet-app>
```