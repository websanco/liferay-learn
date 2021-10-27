# Objects Admin Permission

Like other Liferay applications, the Objects portlet is integrated with the permissions framework. This means you can assign [application](#application-permissions) and [resource](#resource-permissions) permissions to user roles to determine which users have access to the Objects portlet and its data.

![Assign Objects permissions when defining role permissions.](./objects-application-permissions/images/01.png)

See [Defining Role Permissions](../../../users-and-permissions/roles-and-permissions/defining-role-permissions.md) for more information about assigning permissions to user roles.

## Application Permissions

Application permissions grant the ability to perform general application-related operations and do not include [resource-related permissions](#object-resource-permissions).

The Objects portlet has the following application permissions.

| Permission | Description |
| --- | --- |
| Access in Control Panel | Ability to access Objects in the Global Menu |
| Configuration | Ability to view and modify configuration options for the Objects portlet |
| Permissions | Ability to view and modify Objects permissions |
| Preferences | Ability to view and modify preferences for the Objects portlet |
| View | Ability to view the Objects portlet |

## Resource Permissions

Resource permissions grant specific abilities related to application resources. Some of these permissions grant the ability to perform [operations on database entities](#object-definition-actions) (i.e., model resources). Others grant the ability to perform [resource-related operations](#object-related-actions) in an application context (e.g., the ability to create a new resource entity).

The Object's portlet has the following resource permissions.

### Object Related Actions

| Permission | Description |
| --- | --- |
| Add Object Definition | Ability to create an Object draft |
| Extend System Object Definition | Ability to add fields, relationships, and layouts to system Objects |
| Permissions | Ability to view and manage permissions related to Objects |
| Publish Object Definition | Ability to publish an Object draft |

### Object Definition Actions

| Permission | Description |
| --- | --- |
| Delete | Ability to delete an Object draft |
| Permissions | Ability to view and modify permissions for an Object |
| Update | Ability to update an Object |
| View | Ability to view an Object |

## Additional Information

* [Introduction to Objects](./introduction-to-objects.md)
* [Creating Objects](./creating-and-managing-objects/creating-objects.md)
* [Managing Objects](./creating-and-managing-objects/managing-objects.md)
