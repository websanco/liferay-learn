# Upgrading a Theme

Themes developed for previous versions of Liferay DXP must be upgraded in order to be deployed to a new version. The `gulp upgrade` task updates Liferay version references and theme dependencies. This allows you to deploy your theme to newer versions of Liferay.

## Prerequisites

The Liferay Theme Generator is used to run the upgrade task. If you have not done so, then install it with this command:

```bash
npm install -g generator-liferay-theme@10.x.x
```

Install the Yeoman and Gulp dependencies with this command:

```bash
npm install -g yo gulp
```

## Running the Upgrade Task

Use the upgrade task to update the theme's version dependencies.

```{important}
The upgrade task may change or overwrite files in your theme. Back up your theme's files before running it. 
``` 

Run this command from inside your theme's folder:

```bash
gulp upgrade
```

A warning appears reminding you to back up your files if you have not yet done so. When ready, type "y" (or "yes") to continue at the prompt.

```
? We recommend creating a backup of your theme files before proceeding. Are you 
sure you wish to start the upgrade process?
```

**If you are upgrading to Liferay 7.4**, another prompt appears asking if you want to add the Bootstrap 3 to 4 compatibility layer. If your theme requires compatibility with Bootstrap 3, enter "y" (or "yes"), and then enter it again when a warning appears to confirm. If you do not need compatibility with Bootstrap 3, then type "n" (or "no").

```
? Would you like to add the Bootstrap 3 to 4 compatibility layer? Yes
? Bootstrap 3 to 4 compatibility layer is for DXP 7.4 GA1 and we cannot garauntee it working with future releases of Liferay DXP. Confirm?
```

Confirm that the upgrade task has completed with the messages in the console:

```
Finished 'upgrade' after 36 s
```

The version numbers in your theme are now updated, and you can now deploy it to the new version of Liferay DXP.

<!-- Add Next Steps section when information is available -->
