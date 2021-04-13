# Default Setting Changes in 7.3

Most new versions of Liferay DXP include changes to the default settings. If you rely on the defaults from your old version, you should review the changes and decide to keep the defaults from your old version or accept the defaults of the new.

The latest [portal properties reference](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html) describes the properties and provides examples. Many properties are now replaced by [OSGi configuration files](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md), which are accessible through the UI in [System Settings](../../../system-administration/configuring-liferay/system-settings.md).

Here are the changes from 7.2 to 7.3:

| **Portal Property** | **7.2 Default** | **7.3 Default** |
| --- | --- | --- |
| admin.email.from.address | test@liferay.com | test@domain.invalid |
| admin.email.user.added.reset.password.body | *NA* | com/liferay/portlet/admin/dependencies/email_user_added_reset_password_body.tmpl |
| admin.email.password.changed.subject | *NA* | com/liferay/portlet/admin/dependencies/email_password_changed_subject.tmpl |
| admin.email.password.changed.body | *NA* | com/liferay/portlet/admin/dependencies/email_password_changed_body.tmpl |
| admin.email.user.added.body | Removed in a 7.2 follow-up release | Removed |
| admin.email.password.sent.subject | com/liferay/portlet/admin/dependencies/email_password_sent_subject.tmpl | Removed |
| admin.email.password.sent.body | com/liferay/portlet/admin/dependencies/email_password_sent_body.tmpl | Removed |
| admin.obfuscated.properties | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Admin%20Portlet) | dropped captcha.engine.recaptcha.key.private |
| auto.deploy.listeners | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Auto%20Deploy) | Removed com.liferay.portal.deploy.auto.ExtAutoDeployListener |
| auto.deploy.glassfish.* | See [here](vhttps://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Auto%20Deploy) | Removed |
| auto.deploy.jetty.* | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Auto%20Deploy) | Removed |
| auto.deploy.jonas.* | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Auto%20Deploy) | Removed |
| auto.deploy.resin.* | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Auto%20Deploy) | Removed |
| axis.servlet.enabled | *NA* | false |
| axis.servlet.mapping | *NA* | /api/axis/* |
| blogs.entry.page.delta.values | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Blogs%20Service) | See the values in [Blogs Service](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Blogs%20Service). |
| blogs.image.extensions | .gif,.jpeg,.jpg,.png | Removed |
| blogs.image.max.size | 5242880 | Removed |
| buffered.increment.enabled | true | Removed. See the [breaking change](../../../liferay-internals/reference/7-3-breaking-changes.html). |
| cache.clear.on.context.initialization | true | Removed |
| com.liferay.portal.deploy.auto.ExtAutoDeployListener | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Auto%20Deploy) | Removed |
| cluster.link.channel.properties.control | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Cluster%20Link) | jgroups/udp_control.xml |
| cluster.link.channel.properties.transport.0 | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Cluster%20Link) | jgroups/udp_transport.xml |
| company.security.send.password | false | Removed |
| dl.file.entry.preview.fork.process.jvm.options | *NA* | *blank*. See [Document Library Service](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Document%20Library%20Service). |
| editor.wysiwyg.portal-web.docroot.html.portlet.message_boards.edit_message.bb_code.jsp | alloyeditor_bbcode | Removed |
| editor.wysiwyg.portal-web.docroot.html.portlet.message_boards.edit_message.html.jsp | alloyeditor | Removed |
| ehcache.blocking.cache.allowed | false | Removed. See the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#remove-support-for-blocking-cache). |
| ehcache.bootstrap.cache.loader.enabled | false | Removed. See the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#remove-support-for-blocking-cache). |
| ehcache.bootstrap.cache.loader.properties.default | *blank* | Removed. See the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#remove-support-for-blocking-cache). |
| image.io.use.disk.cache | true | false |
| include-and-override | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Properties%20Override) | Removed portal-companyID.properties overrides |
| index.on.upgrade | false | Removed |
| hibernate.validator.apply_to_ddl | *NA* | false. See [Hibernate](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Hibernate). |
| json.deserialization.whitelist.class.names | See this [section](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#JSON) | Added [values](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#JSON). |
| json.service.serialize.throwable | *NA* | New. See [JSON](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#JSON). |
| jsonws.web.service.paths.excludes | *blank* | /user/update-password |
| jsonws.web.service.parameter.type.whitelist.class.names | *NA* | New. See [JSON Web Service](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#JSON%20Web%20Service). |
| jsp.servlet.init.param.enablePooling | *NA* | false |
| layout.parallel.render.* properties | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Layouts) | Removed. See the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#server-side-parallel-rendering-is-no-longer-supported). |
| Layout settings for panel layouts, embedded layouts, and URL layouts | See the [properties](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Layouts). | Removed |
| mail.send.blacklist | *NA* | New. See [Mail](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Mail). |
| mail.session.mail.smtp.starttls.enable | *NA* | true. See [Mail](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Mail). |
| module.framework.configuration.bundle.symbolic.names | *NA* | New. See [here](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Module%20Framework) |
| module.framework.properties.dependency.manager.sync.timeout | *NA* | 60 | 
| module.framework.properties.dependency.manager.thread.pool.enabled | *NA* | true |
| module.framework.properties.eclipse.stateSaveDelayInterval | *NA* | 60000 |
| module.framework.properties.felix.fileinstall.* | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework) | Renamed to module.framework.properties.file.install.* |
| module.framework.properties.org.osgi.framework.bootdelegation | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework) | Added com.sun.imageio.plugins.*; removed javax.validation and javax.validation.* |
| module.framework.properties.osgi.bundlefile.limit | *NA* | 100000 |
| module.framework.properties.spifly.spi.consumer.header.enabled | *NA* | false |
| module.framework.services.ignored.interfaces | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework) | Added com.liferay.trash.kernel.service.* |
| module.framework.web.generator.default.servlet.packages | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework%20Web%20Application%20Bundles) | Removed org.eclipse.jetty.websocket.server and org.glassfish.tyrus.servlet |
| module.framework.web.generator.excluded.paths | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework%20Web%20Application%20Bundles) | Added WEB-INF/lib/jackson-databind.jar and WEB-INF/lib/portletmvc4spring-framework.jar; dropped WEB-INF/lib/spring-webmvc-portlet.jar |
| module.framework.web.servlet.annotation.scanning.blacklist | *NA* | See [Module Framework Web Application Bundles](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Module%20Framework%20Web%20Application%20Bundles) |
| module.framework.web.servlet.annotation.scanning.whitelist | *NA* | com/liferay/faces/ |
| module.framework.web.start.level | *NA* | 15. See [Module Framework](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Module%20Framework).
| permissions.object.blocking.cache | false | Removed. See this [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#remove-support-for-blocking-cache). |
| portal.resiliency.* | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Portal%20Resiliency) | Removed |
| retry.data.source.max.retries | 100 | 0 |
| session.phishing.protected.attributes | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Session) | added SETUP_WIZARD_PASSWORD_UPDATED |
| session.shared.attributes | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Session) | dropped org.apache.struts.action.LOCALE |
| `setup.database.jar.url[com.mysql.cj.jdbc.Driver]` | http* | https* |
| spring.configs | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Spring) | dropped META-INF/fabric-spring.xml and META-INF/asset-spring.xml |
| spring.remoting.servlet.* | Removed in a 7.2 follow-up release | Removed |
| system.role.Analytics.Administrator.description | *NA* | See [Groups and Roles](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Groups%20and%20Roles) |
| upgrade.database.auto.run | *NA* | false. See the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#replaced-osgi-configuration-property-autoupgrade). |
| user.groups.copy.layouts.to.user.personal.site | false | Removed the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#removed-portal-property-user-groups-copy-layouts-to-user-personal-site). |
| value.object.entity.blocking.cache | true | Removed. See the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#remove-support-for-blocking-cache). |
| value.object.entity.cache.enabled.com.liferay.portal.kernel.model.Layout | true | Removed. See the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#remove-support-for-setting-cache-properties-for-each-entity-model). |
| value.object.entity.cache.enabled.com.liferay.portal.kernel.model.User | true | Removed. See the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#remove-support-for-setting-cache-properties-for-each-entity-model). |
| value.object.finder.cache.enabled.* | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Value%20Object) | Removed. See the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html#remove-support-for-setting-cache-properties-for-each-entity-model). |
| verify.processes | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Verify) | Removed |
| verify.frequency | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Verify) | Removed |
| verify.database.transactions.disabled | See [here](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Verify) | Removed |
| view.count.enabled | *NA* | true. See the [breaking change](https://learn.liferay.com/dxp/latest/en/liferay-internals/reference/7-3-breaking-changes.html). |
| work.dir.override.enabled | *NA* | false. See [Work Directory](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html#Work%20Directory). |

## Additional Information

* [Default Setting Changes in 7.2](default-setting-changes-in-7-2.md)
* [7.3 Breaking Changes](../../../liferay-internals/reference/7-3-breaking-changes.md)
