# Introduction to Objects

Liferay Objects provides low-code development capabilities built on and integrated with Liferay's core frameworks. With it, you can easily build and deliver applications without having to develop any code or manually deploy modules. Quickly develop and manage flexible solutions that adapt Liferay DXP to your business needs while maintaining a seamless user experience. Reduce dependencies and decrease the time spent maintaining custom solutions. This can simplify your upgrade path, so you can get access to Liferay's latest/newest features. <!--TASK: Improve paragraph flow.-->

When creating Objects, you can easily add custom data [fields](./creating-and-managing-objects/adding-fields-to-objects.md) and define complex [relationships](./creating-and-managing-objects/defining-object-relationships.md) between Object entries. You can then design custom [layouts](./creating-and-managing-objects/designing-object-layouts.md) to determine how fields and relationships are displayed to users when creating or editing entries. Before publishing, you can scope each Object's data to Company or Site and determine where it's displayed in the Liferay UI. <!--TASK: Add in Views once implemented-->

From the time of creation, all Objects are fully integrated with Liferay's core frameworks to provide a unified experience across the platform and leverage its full capabilities. This includes Liferay automatically generating Headless REST APIs for every Object, so you can interact with the Object through defined endpoints. These APIs can also be used with the [Job Scheduler](../../core-frameworks/dispatch-framework/using-dispatch.md) integration to configure Talend jobs for dynamically syncing Object data with external systems.

Every Object is integrated with Workflows, so you can guide Object entry creation through a defined review and approval process. Objects are also integrated with Forms, so you can design forms that map to Object fields and receive user input for creating Object entries. Once entries are created, you can leverage Liferay's Info framework to create dynamic user experiences, displaying Object entries with Page Fragments and Display Page Templates.

All Objects are integrated with Liferay's [Permissions framework](./understanding-object-integrations/permissions-framework-integration.md), so you can assign application and resource permissions to manage user access to Objects and their entries. Together with scoping, role-based access control can help you secure your data and ensure it can only be accessed by the appropriate users.

<!--TASK: Add the following text once more features are supported for system Objects, "The Objects application also provides a convenient way to extend and configure any system services registered with the Objects framework. This includes adding custom fields, defining relationships with other Objects, and designing layouts for Object entries." -->

See [Creating Objects](./creating-and-managing-objects/creating-objects.md) to get started building custom applications or [Understanding Object Integrations](./understanding-object-integrations.md) to learn more about how Objects leverages Liferay's core frameworks.

## Additional Information

* [Creating Objects](./creating-and-managing-objects/creating-objects.md)
* [Understanding Object Integrations](./understanding-object-integrations.md)
<!--TASK: * [Objects UI Reference](./objects-ui-reference.md) -->
