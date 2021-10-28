# Default Setting Changes in 7.4

Most new versions of Liferay DXP include changes to the default settings. If you rely on the defaults from your old version, you should review the changes and decide to keep the defaults from your old version or accept the defaults of the new.

The 7.4 [`portal.properties`](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) file describes the properties and provides examples. Many properties are now replaced by [OSGi configuration files](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md), which are accessible through the UI in [System Settings](../../../system-administration/configuring-liferay/system-settings.md).

Here are the changes from 7.3 to 7.4:

| **Portal Property** | **7.3 Default** | **7.4 Default** |
| --- | --- | --- |
| `access.control.sanitize.security.exception` | Not applicable | `true` |
| `auth.login.prompt.enabled` | `true` | `false` |
| `auto.login.hooks` | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) |
| `#com.liferay.portal.servlet.filters.sso.cas.CASFilter` | `true` | Removed property |
| `com.liferay.portal.servlet.filters.sso.ntlm.NtlmFilter` | `true` | Removed property |
| `com.liferay.portal.servlet.filters.sso.ntlm.NtlmPostFilter` | `true` | Removed property |
| `company.security.update.password.required` | Not applicable | `true` |
| `data.limit.dl.storage.max.size` | Not applicable | `0` |
| `data.limit.model.max.count[com.liferay.asset.kernel.model.AssetCategory]` | Not applicable | `0` |
| `data.limit.model.max.count[com.liferay.asset.kernel.model.AssetTag]` | Not applicable | `0` |
| `data.limit.model.max.count[com.liferay.asset.kernel.model.AssetVocabulary]` | Not applicable | `0` |
| `data.limit.model.max.count[com.liferay.blogs.model.BlogEntry]` renamed | From `BlogEntry` | To `BlogsEntry` (plural) |
| `dl.email.file.entry.expired.body` | Not applicable | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) |
| `dl.email.file.entry.expired.enabled` | Not applicable | `true` |
| `dl.email.file.entry.expired.subject` | Not applicable | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) |
| `dl.email.file.entry.review.body` | Not applicable | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) |
| `dl.email.file.entry.review.enabled` | Not applicable | `true` |
| `dl.email.file.entry.review.subject` | Not applicable | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) |
| `dl.file.entry.preview.audio.mime.types` | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | Added value `audio/vnd.wave` |
| `dl.file.entry.preview.fork.process.jvm.options` | Empty value | `-Xmx1024m` |
| `dl.file.entry.preview.video.bit.rate[mp4]` | `250000` | `1200000` |
| `dl.file.entry.preview.video.bit.rate[ogv]` | `250000` | `1200000` |
| `dl.file.entry.preview.video.mime.types` | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | Added value `video/theora` |
| `dl.store.antivirus.impl` | Empty value | Removed property |
| `#dl.store.gcs.aes256.key` | Not applicable | Empty value |
| `editor.wysiwyg.portal-web.docroot.html.taglib.ui.discussion.jsp` | `alloyeditor` | Removed property |
| `enterprise.product.commerce.enabled` | Not applicable | `true` |
| `enterprise.product.enterprise.search.enabled` | Not applicable | `true` |
| `enterprise.product.notification.enabled` | Not applicable | `true` |
| `jsonws.web.service.parameter.type.whitelist.class.names` | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) |
| `jsp.engine.enablePooling` | Not applicable | `false` |
| `learn.resources.cdn.enabled` | Not applicable | `true` |
| `learn.resources.enabled` | Not applicable | `true` |
| `learn.resources.refresh.time` | Not applicable | `14400000` |
| `#lucene.boolean.query.clause.max.size` | `1024` | Removed property |
| `minifier.enabled` | `true` | `false` |
| `module.framework.properties.org.apache.cxf.osgi.http.transport.disable` | Not applicable | `true` |
| `module.framework.properties.org.osgi.framework.bootdelegation` | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | Removed value `com.sun.syndication` |
| `module.framework.web.generator.excluded.paths` | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) |
| `multi.value.map.com.liferay.portal.convert.ConvertPermissionAlgorithm.convertResourcePermission` | Not applicable | `0` |
| `multi.value.map.com.liferay.portal.convert.ConvertPermissionAlgorithm.convertResourcePermission` | `0` | Removed property |
| `multi.value.map.com.liferay.portal.convert.ConvertPermissionAlgorithm.convertRoles` | Not applicable | `0` |
| `multi.value.map.com.liferay.portal.convert.ConvertPermissionAlgorithm.convertRoles` | `0` | Removed property |
| `openoffice.conversion.source.extensions[text]` | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) |
| `portal.jaas.auth.type` | `userId` | Property commented out |
| `portal.jaas.enable` | `false` | Property commented out |
| `portal.jaas.plain.password` | `false` | Property commented out |
| `portal.jaas.strict.password` | `false` | Property commented out |
| `redirect.url.domains.allowed` |  | Removed property |
| `redirect.url.ips.allowed` | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | Removed property |
| `redirect.url.security.mode` | `ip` | Removed property |
| `resource.actions.strict.mode.enabled` | Not applicable | `true` |
| `search.container.page.max.delta` | Not applicable | `200` |
| `session.disabled` | `false` | Removed property |
| `session.phishing.protected.attributes` | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) |
| `session.timeout.auto.extend.offset` | `10` | `70` |
| `system.role.Publications.User.description` | Not applicable | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | |
| `template.engine.freemarker.transaction.read.only` | Not applicable | `true` |
| `template.engine.service.locator.restrict` | Not applicable | `true` |
| `transactional.cache.names` | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) | See [setting](https://github.com/liferay/liferay-portal/blob/7.4.x/portal-impl/src/portal.properties) |
| `upgrade.report.enabled` | Not applicable | `false` |
| `verify.process.concurrency.threshold` | `5` | Not applicable |
