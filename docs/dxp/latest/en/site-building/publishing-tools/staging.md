# Staging Overview

```{toctree}
:maxdepth: 3

staging/understanding-the-publishing-process.md
staging/configuring-local-live-staging.md
staging/configuring-remote-live-staging.md
staging/managing-data-and-content-types-in-staging.md
staging/page-versioning.md
staging/publishing-single-assets-and-widgets.md
staging/configuring-automatic-publishing-of-displayed-content.md
staging/managing-staging-permissions.md
staging/using-staging-in-asset-libraries.md
staging/site-staging-ui-reference.md
```

Liferay's Staging application provides a working environment for making changes to a Site or Asset Library before publishing your changes to your live environment. With it, you can modify staged application data and Pages behind the scenes and then publish your changes when ready, whether individually or altogether, while the Live environment handles incoming user traffic.

```{important}
Liferay Commerce does not support Staging. During the publishing process, entities from Commerce are not staged. 
```

When setting up Staging, determine whether to host your Site or Asset Library locally (i.e., on the same server) or remotely (i.e., on separate servers connected by a network). Then customize which application data is staged. For Sites, you can also enable Page versioning for Widget Pages and Content Pages.

```{note}
Staging is supported for Asset Libraries since Liferay DXP 7.4 and 7.3.10 FP1.
```

## Configuration Options

There are two options for configuring Staging : *Local Live Staging* and *Remote Live Staging*.

**Local Live Staging**: You can host both your Staging and Live environments on the same Liferay server. When enabled, Liferay DXP creates a local clone of your Site or Asset Library that serves as the Staging environment, while your original Site or Asset Library becomes your Live environment.

**Remote Live Staging**: You can host your Staging and Live environments on separate Liferay servers. When enabled, the Site or Asset Library used to configure Staging becomes your Staging environment, while the configured remote server becomes your Live environment.

While both options use the same interface for managing and publishing staged Pages, applications, and content, they differ in their setup. Once you've chosen the configuration option that best suits your needs, see [Configuring Local Live Staging](./staging/configuring-local-live-staging.md) or [Configuring Remote Live Staging](./staging/configuring-remote-live-staging.md) for more information and setup instructions.

### Staged Data and Content Types

As part of Staging configuration, you can select which content groups and application data are staged. If staged, the selected data and content are managed by your Staging environment. Also, when an application or content group is selected, all contained entities (e.g., application folders) are staged as well. See [Managing Data and Content Types in Staging](./staging/managing-data-and-content-types-in-staging.md) for more information.

### Page Versioning

When configuring Staging for Sites, you can enable the *Page versioning* feature. With this feature, you and your team can simultaneously develop variations of a Site's Content Pages and Widget Pages. You can see the complete history of Page versions and revert Pages to an earlier version if needed. See [Page Versioning](./staging/page-versioning.md) to learn how to create and manage variations and [Managing Staging Permissions](./staging/managing-staging-permissions.md) to learn about managing permissions for each variation.

```{note}
Starting with Liferay DXP 7.4, 7.3 Fix Pack 1, and 7.2 Fix Pack 11, you can use page versioning in Widget Pages and Content Pages. In previous versions, you could only create variations for Widget Pages. To learn more about variations for each type of Page, see [Types of Variations](./staging/page-versioning.md#types-of-variations).
```

## Understanding the Publishing Process

From a low level perspective, staging is an equivalence relation where entities are mirrored to a different location. From a high level perspective, the staging process is executed in three sequential phases: Export, Validation, and Import.

Understanding this process can improve efficiency and help you plan ahead to achieve a seamless publishing experience. See [Understanding the Publishing Process](./staging/understanding-the-publishing-process.md) for more information.

```{raw} html
:file: ../../landingpage_template.html
```

```{raw} html
:file: staging/landing.html
```
