# Step 4: Migrating DXP Configurations and Customizations

Now that your database and document library are applied to your DXP Cloud environment, the next step is to migrate your on-premises Liferay installation's configurations and custom code. This involves organizing property files, OSGi configurations, and custom modules, themes, and WAR files to the appropriate folders before deployment.

## Organize Configuration Files

First, organize the Liferay configuration files from your on-premises installation you want to migrate. This includes any [portal property files](../using-the-liferay-dxp-service/configuring-the-liferay-dxp-service.md#portal-properties) you are using.

```{tip}
Liferay-specific code belongs in the `liferay` folder, which is organized like a [Liferay DXP workspace](https://learn.liferay.com/dxp/latest/en/developing-applications/tooling/liferay_workspace.html).
```

In the DXP Cloud project repository you [cloned previously](./matching-dxp-versions.md#clone-the-dxp-cloud-repository), navigate to the `liferay/configs/{ENV}/` folders (which correspond to your DXP Cloud environments),
and put all of your Liferay [portal property](../using-the-liferay-dxp-service/configuring-the-liferay-dxp-service.md#portal-properties) files (e.g., `portal-ext.properties`) into each appropriate environment folder.

Put [OSGi configuration](../using-the-liferay-dxp-service/configuring-the-liferay-dxp-service.md#osgi-configurations) files (e.g., `.cfg` or `.config` files) into a subfolder in the appropriate environment folders called `osgi/`.

For example, for a `dev` environment, put portal property files into `liferay/configs/dev`, and OSGi configuration files into `liferay/configs/dev/osgi/`.

```{tip}
Any files put into the `liferay/configs/common/` folder applies to all environments when deployed.
```

When the changes are deployed, any portal property files put into the `liferay/configs/` environment folders are automatically copied to the `$LIFERAY_HOME` folder within the  `liferay` service's container in the corresponding environment(s). OSGi properties are automatically copied to the `$LIFERAY_HOME/osgi/configs/` folder.

## Organize OSGi Modules

If you have custom OSGi modules used for your on-premises Liferay installation, then put all [OSGi module source code](https://learn.liferay.com/dxp/latest/en/liferay-internals/extending-liferay/overriding-osgi-services.html) into the appropriate environment's `liferay/modules/{ENV}/` folder (with the same workspace folder structure).

When the changes are deployed, custom module code is automatically compiled and then deployed to the `liferay` service container's `$LIFERAY_HOME/deploy/` folder.

## Organize Custom Themes

Next, put the source code for all of your [custom themes](https://learn.liferay.com/dxp/latest/en/site-building/site-appearance/themes/theme-development/getting-started/setting-up-an-environment-and-creating-a-theme.html) into the appropriate environment's `liferay/themes/{ENV}/` folder.

When the changes are deployed, custom themes are automatically build and deployed to the `liferay` service container's `$LIFERAY_HOME/deploy/` folder.

As an example, download [this sample](https://drive.google.com/file/d/1scS29urm2C5zRGGDIHdoVmXjim6QsQKe/view?usp=sharing) of a Liferay DXP 7.3 theme.

## Organize WAR Files

Next, put all of your Liferay installation's [WAR files](https://learn.liferay.com/dxp/latest/en/building-applications/reference/deploying-wars-wab-generator.html) into the appropriate environment's `liferay/wars/{ENV}/` folder.

When the changes are deployed, WAR files are automatically deployed to the `$LIFERAY_HOME/deploy/` folder within the `liferay` service's container in the appropriate environment(s).

## Create and Deploy a Build

Next, you must create and deploy a build with these changes to apply them to your DXP Cloud environments.

### Create a Jenkins Build with the Change

Run Git commands to submit your changes using any terminal with Git installed.

1. Add the changed files to Git:

    ```bash
    git add .
    ```

1. Make a commit with your changes and a message:

    ```bash
    git commit -m "DXP Cloud Migration Step 4"
    ```

1. Push the changes to GitHub:

    ```bash
    git push origin master
    ```

Since your project is linked to the GitHub repository, pushing the changes automatically creates a build. Wait for the build to complete before proceeding.

### Deploy the Build to Your Chosen Environment

Finally, use the [DXP Cloud Console](https://console.liferay.cloud/) to deploy the completed build to your chosen environment.

1. In the DXP Cloud Console, go to the Builds page (using the link at the top of the page).

1. Find the build you created previously in the list, and from the Actions menu, click *Deploy build to*.

    ![Use the build's Actions menu to deploy it.](./creating-data-backup-files/images/01.png)

1. Select the environment to deploy the build to (e.g., `acme-dev`).

1. Read the information below and select the confirmation boxes to confirm the results of the deployment.

    ![Check the checkboxes and deploy the build when ready.](./creating-data-backup-files/images/02.png)

1. Click *Deploy Build*.

The build is deployed to your chosen environment, and your Liferay configurations and customizations are applied to the chosen environment once the `liferay` service restarts.

## Next Steps

Now you have finished migrating your Liferay configurations and customizations. Next, you will migrate your [web server configurations](./migrating-web-server-configurations.md).
