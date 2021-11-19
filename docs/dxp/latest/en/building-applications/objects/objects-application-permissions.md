# Objects Application Permissions

Like other Liferay applications, the Objects portlet is integrated with the permissions framework. This means you can assign [application](#application-permissions) and [resource](#resource-permissions) permissions to user roles to determine which users have access to the Objects portlet and its data.

![Assign Objects permissions when defining role permissions.](./objects-application-permissions/images/01.png)

See [Defining Role Permissions](../../users-and-permissions/roles-and-permissions/defining-role-permissions.md) for more information about assigning permissions to user roles.

## Application Permissions

Application permissions grant permission to perform general application-related operations and do not include [resource-related permissions](#resource-permissions).

The Objects portlet has the following application permissions:

| Permission | Description |
| --- | --- |
| Access in Control Panel | Access Objects in the Global Menu |
| Configuration | View and modify configuration options for the Objects portlet |
| Permissions | View and modify Objects permissions |
| Preferences | View and modify preferences for the Objects portlet |
| View | View the Objects portlet |

## Resource Permissions

Resource permissions grant specific permissions on application resources. Some of these permissions enable performing [operations on database entities](#object-definition-actions) (i.e., model resources). Others grant permission to perform [resource-related operations](#object-related-actions) in an application context (e.g., create a new resource entity).

The Object's portlet has the following resource permissions.

### Object Related Actions

| Permission | Description |
| --- | --- |
| Add Object Definition | Create an Object draft |
| Extend System Object Definition | Add fields, relationships, and layouts to system Objects |
| Permissions | View and manage permissions related to Objects |
| Publish Object Definition | Publish an Object draft |

### Object Definition Actions

| Permission | Description |
| --- | --- |
| Delete | Delete an Object draft |
| Permissions | View and modify permissions for an Object |
| Update | Update an Object |
| View | View an Object |

## Additional Information

* [Objects Overview](../objects.md)
* [Creating Objects](./creating-and-managing-objects/creating-objects.md)
* [Managing Objects](./creating-and-managing-objects/managing-objects.md)
