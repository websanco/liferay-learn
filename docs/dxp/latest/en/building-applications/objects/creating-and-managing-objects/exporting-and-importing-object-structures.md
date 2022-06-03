# Exporting and Importing Object Structures

{bdg-secondary}`Available Liferay 7.4 U5+ and 7.4 GA9+`

With Liferay Objects, you can import and export Object structures as `.json` files. This provides flexibility during application development, enabling users to easily migrate Object definitions between Liferay environments.

```{important}
As of Liferay DXP 7.4 U5 and Portal 7.4 GA9, Objects does not support importing or exporting Relationships. All other Object configurations and structure elements are supported (e.g., scope, Fields, Layouts).
```

## Exporting Structures

Follow these steps to export an Object structure:

1. Open the *Global Menu* (![Global Menu](../../../images/icon-applications-menu.png)), go to the *Control Panel* tab, and click on *Objects*.

1. Click on the *Actions* button (![Action Button](../../../images/icon-actions.png)) for the desired Object and select *Export as JSON*.

   ![Click the Actions button for the desired Object and select Export as JSON.](./exporting-and-importing-object-structures/images/01.png)

1. Click *Save* in the dialog window to begin downloading the Object's definition as a JSON file.

This file includes the Object's Fields, Layouts, Actions, and general configuration details (e.g., label, scope, etc.). If the exported Object has Relationships, they are not included in the JSON file.

Once exported, you can import the Object definition into compatible Liferay environments.

## Importing Structures

Follow these steps to import an Object structure:

1. Open the *Global Menu* (![Global Menu](../../../images/icon-applications-menu.png)), click on the *Control Panel* tab, and go to *Objects*.

1. Click the *Actions* button (![Actions Button](../../../images/icon-actions.png)) in the Application Bar and select *Import Object*.

   ![Click the Actions button in the Application Bar and select Import Object.](./exporting-and-importing-object-structures/images/02.png)

1. Enter a *name* for the new Object and select the *JSON file* you want to import.

   ```{important}
   Object names must be unique and use PascalCase.
   ```

   ![Enter a name and select the desired JSON file.](./exporting-and-importing-object-structures/images/03.png)

1. Click *Import*.

The Object structure imports in the background and can take a few minutes.

## Additional Information

* [Creating Objects](./creating-objects.md)
* [Managing Objects](./managing-objects.md)
