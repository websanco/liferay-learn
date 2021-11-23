# Objects

```{toctree}
:maxdepth: 3

objects/creating-and-managing-objects.md
objects/building-solutions-with-objects.md
objects/using-forms-with-objects.md
objects/displaying-object-entries.md
objects/enabling-workflows-for-objects.md
objects/understanding-object-integrations.md
objects/using-picklists.md
objects/objects-application-permissions.md
```

With Liferay Objects, you can build and deliver applications without having to write code or deploy modules, yet these applications are built on and integrated with Liferay's core frameworks. Quickly develop and manage flexible solutions that adapt Liferay DXP to your business needs while maintaining a seamless user experience.

When creating Objects, you can add data [fields](./objects/creating-and-managing-objects/adding-fields-to-objects.md) and define complex [relationships](./objects/creating-and-managing-objects/defining-object-relationships.md) between Object entries. You can then design [layouts](./objects/creating-and-managing-objects/designing-object-layouts.md) to determine how fields and relationships are displayed when creating or editing entries. Before publishing, you can scope each Object's data to Company or Site and determine where it's displayed in the Liferay UI. <!--TASK: Add in Views once implemented-->

From the time of creation, all Objects are fully integrated with Liferay's [core frameworks](./core-frameworks.md) to provide a unified experience across the platform and leverage all of Liferay's capabilities. This includes automatically generating [Headless REST APIs](./objects/understanding-object-integrations/headless-framework-integration.md) for every Object, so you can interact with your Objects through defined endpoints. These APIs can also be used with the [Job Scheduler](./core-frameworks/dispatch-framework/using-dispatch.md) integration to configure Talend jobs to sync Object data dynamically with external systems.

Every Object is integrated with [Workflows](./objects/enabling-workflows-for-objects.md), so you can guide Object entry creation through a defined review and approval process. Objects are also integrated with [Forms](objects/using-forms-with-objects.md), so you can design forms that map to Object fields and receive user input for creating Object entries. Once entries are created, you can leverage Liferay's Info framework to create dynamic user experiences, displaying Object entries with Page Fragments and Display Page Templates.

All Objects are integrated with Liferay's [Permissions framework](./objects/understanding-object-integrations/permissions-framework-integration.md), so you can assign application and resource permissions to manage access to Objects and their entries. Together with scoping, role-based access control can help you secure your data and ensure it can only be accessed by the appropriate users.

<!--TASK: Add the following text once more features are supported for system Objects, "The Objects application also provides a convenient way to extend and configure any system services registered with the Objects framework. This includes adding custom fields, defining relationships with other Objects, and designing layouts for Object entries." -->

See [Creating Objects](./objects/creating-and-managing-objects/creating-objects.md) to get started building custom applications or [Understanding Object Integrations](./objects/understanding-object-integrations.md) to learn more about how Objects leverages Liferay's core frameworks.

<!--TASK: * [Objects UI Reference](./objects-ui-reference.md) -->

```{raw} html
:file: ../landingpage_template.html
```

```{raw} html
:file: objects/landing.html
```
