# 7.3 Default Setting Changes

Most new versions of Liferay DXP include changes to the default settings. If you rely on the defaults from your old version, you should review the changes and decide to keep the defaults from your old version or accept the defaults of the new.

Here are the changes from 7.2 to 7.3:

| **Portal Property** | **7.2 Default** | **7.3 Default** |
| --- | --- | --- |
| image.io.use.disk.cache | true | false |
| setup.database.jar.url[com.mysql.cj.jdbc.Driver] | http* | https* |
|  cluster.link.channel.properties.control | see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | jgroups/udp_control.xml |
| cluster.link.channel.properties.transport.0= | see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | jgroups/udp_transport.xml |
| jsonws.web.service.paths.excludes | *blank* | /user/update-password |
| retry.data.source.max.retries | 100 | 0 |
| admin.obfuscated.properties | see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | see [value](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html) |
| spring.configs | see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | dropped META-INF/fabric-spring.xml and META-INF/asset-spring.xml |
| session.shared.attributes | see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | dropped org.apache.struts.action.LOCALE |
| session.phishing.protected.attributes | see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | added SETUP_WIZARD_PASSWORD_UPDATED |
| json.deserialization.whitelist.class.names | see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | added com.liferay.portal.kernel.util.GroupSubscriptionCheckSubscriptionSender and com.liferay.portal.kernel.util.SubscriptionSender |
| module.framework.services.ignored.interfaces | see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | added com.liferay.trash.kernel.service.* |
| module.framework.properties.org.osgi.framework.bootdelegation | see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | dropped javax.validation and javax.validation.* |
| module.framework.web.generator.excluded.paths | see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | added WEB-INF/lib/jackson-databind.jar and WEB-INF/lib/portletmvc4spring-framework.jar; dropped WEB-INF/lib/spring-webmvc-portlet.jar |
| admin.obfuscated.properties |  see [value](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html) | dropped captcha.engine.recaptcha.key.private |

The latest [portal properties reference](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html) provides property details and examples. Many properties are now replaced by OSGi configurations, which are accessible through the UI in [System Settings](../../../system-administration/configuring-liferay/system-settings.md).

## Additional Information

* [Default Setting Changes in 7.2](default-setting-changes-in-7-2.md)
* [7.3 Breaking Changes](../../../liferay-internals/reference/7-3-breaking-changes.md)