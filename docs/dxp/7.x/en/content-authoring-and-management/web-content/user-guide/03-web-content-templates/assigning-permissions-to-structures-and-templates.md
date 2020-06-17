# Assigning Permissions to Web Content Structures and Templates

Structures and Web Content Templates provide direct access to Liferay's API. To avoid unauthorized or unintended access to Liferay DXP functionality, you should configure permissions for Structures and Web Content Templates.

As a best practice, define two different roles with access to Structures and Web Content Templates:

* **Content Developer:** Users under this role have permissions to create and edit Structures or Web Content Templates.
* **Content Creator:** Users under this role have permission to view Structures or Web Content Templates, so they can use them to create content.

For information about how to create Roles, see [Creating and Managing Roles](../../../../users-and-permissions/roles-and-permissions/creating-and-managing-roles.md).

Consider the following information when you assign permissions to Structures and Web Content Templates:

* Determine if your roles must have global permissions for all Structures and Web Content Templates across the Liferay DXP instance, or only for specific sites.
* The *View* permission allows users to only view Structures or Web Content Templates.
* The majority of users should not be able to edit Structures or Web Content Templates.

To assign permissions for Web Content Structures or Templates, follow these steps:

1. Go to *Site Administrator* &rarr; *Content & Data* &rarr; *Web Content*.
1. Select the *Structures* tab to set permissions for Structures, or the *Templates* tab to set permissions for Templates.
1. For the Web Content Template where you want to assign permissions, click the *Actions* button (![Actions](../../../../images/icon-actions.png)) and select *Permissions*.
1. Select the permissions you need for your roles.
1. Click *Save*.

![Permissions Dialog for Web Content Structures and Templates](./assigning-permissions-to-structures-and-templates/images/01.png).

See [Roles and Permissions](../../../../users-and-permissions/roles_and_permissions.rst) for more information on configuring Permissions.