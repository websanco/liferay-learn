# Permissions Framework Integration

When an Object is created, it is automatically integrated with Liferay's Permissions framework. This means you can assign [application](#application-permissions) and [resource](#resource-permissions) permissions to manage user access to individual Objects and their entries. With role-based access control, you can ensure only appropriate users can view and use your application data.

When defining role permissions, active Objects are listed according to their scope and panel category key.

## Application Permissions

Application permissions grant the ability to perform general application-related operations and do not include [resource-related permissions](#object-resource-permissions).

Custom Objects include the following standard application permissions according to their defined scope.

| Permission | Description |
| --- | --- |
| Access in Control Panel (Company Scope Only) | Ability to access the Object in the Global Menu |
| Access in Site and Asset Library Administration (Site Scope Only) | Ability to access the Object in the Site Menu or Asset Library |
| Configuration | Ability to view and set the Object's configuration options |
| Permissions | Ability to view and modify the Object's permissions |
| Preferences | Ability to view and set the Object's preferences |
| View | Ability to view the Object's application page |

```{note}
For Objects scoped to *Site*, you can also determine whether an application or resource permission is granted for all Sites or only specific Sites.
```

## Resource Permissions

Resource permissions grant specific abilities related to application resources. Some of these permissions grant the ability to perform [operations on database entities](#actions-on-database-entities) (i.e., model resources). Others grant the ability to perform [resource-related operations](#resource-related-actions) in an application context (e.g., the ability to create a new resource entity).

Each Object also has the following resource permissions.

### Resource-Related Actions

| Permission | Description |
| --- | --- |
| Add Object Entry | Ability to create an Object entry |
| Permissions | Ability to view and manage permissions related to Object entries |

### Actions on Database Entities

| Permission | Description |
| --- | --- |
| Delete | Ability to delete an Object entry |
| Permissions | Ability to view and modify permissions for individual Object entries |
| Update | Ability to update an Object entry |
| View | Ability to view an Object entry |

## Additional Information

* [Understanding Object Integrations](../understanding-object-integrations.md)
* [Introduction to Objects](../introduction-to-objects.md)
* [Creating Objects](../creating-and-managing-objects/creating-objects.md)
* [Managing Objects](../creating-and-managing-objects/managing-objects.md)
