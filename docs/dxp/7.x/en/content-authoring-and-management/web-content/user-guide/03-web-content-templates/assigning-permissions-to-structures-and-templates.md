# Assigning Permissions to Web Content Structures and Templates

Structures and Web Content Templates provide direct access to Liferay DXP's APIs. To avoid unauthorized or unintended access to Liferay DXP functionality, you should configure permissions for Structures and Web Content Templates. Follow these steps:

1. Open the Product Menu and go to the Site Menu &rarr; *Content & Data* &rarr; *Web Content*.
1. Select the *Structures* tab to set permissions for Structures, or select the *Templates* tab to set permissions for Web Content Templates.
1. Open the *Actions* Menu (![Actions](../../../../images/icon-actions.png)) for the Structure or Template and select *Permissions*.
1. Select the [permissions you need for your Roles](../../../../users-and-permissions/roles-and-permissions/defining-role-permissions.md).
1. Click *Save*.

![Permissions Dialog for Web Content Structures and Templates](./assigning-permissions-to-structures-and-templates/images/01.png).

## Suggested Roles and Permissions for Web Content Templates and Structures

As a best practice, define the two [Roles](../../../../users-and-permissions/roles-and-permissions/creating-and-managing-roles.md) below to grant access to Structures and Web Content Templates:

```note::
  You can grant these Roles to have global permissions for all Structures and Web Content Templates across the Liferay DXP instance, or you can grant them just for specific Sites.
```

* **Content Developer:** Users under this Role have permissions to create and edit Structures or Web Content Templates.
* **Content Creator:** Users under this Role have permission to view Structures or Web Content Templates, so they can use them to create content.

Consider these points when setting permissions for Structures and Web Content Templates:

* The *View* permission allows users to create Web Content that uses the Structure and Web Content Template. You should enable this if you want the Role to be able to create Web Content that uses the Structure or Web Content Template.
* Most users should not be able to edit Structures or Web Content Templates.
