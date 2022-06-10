# Activating Liferay Commerce Enterprise

Starting with Liferay DXP/Portal 7.3, Commerce ships with all Liferay bundles and Docker containers and does not require separate installation. However, to access and use the enterprise features of Commerce, the process differs depending on the DXP version you're running. For Liferay DXP 7.4 GA1+, all Commerce modules are enabled by default and ready for use. For DXP 7.3 FP3/SP2+, Commerce is activated using a [portal property](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/portal-properties.html), while earlier versions use a Commerce license file. This property doesn't affect Liferay Portal bundles/images since they don't include the enterprise features.

## Disabling Liferay Commerce Enterprise for Liferay DXP 7.4 GA1+

{bdg-primary}`Liferay DXP and Commerce Subscriptions Required`

For Liferay DXP 7.4 GA1+, all Commerce modules are enabled by default. Users can disable them by setting the `enterprise.product.commerce.enabled` portal property to `false`.

### Disabling Commerce for Liferay Bundles

If you're using a Liferay bundle, you can configure the `enterprise.product.commerce.enabled` property using a `portal-ext.properties` file. Simply add the following property to your Liferay server's `portal-ext.properties` file.

```properties
enterprise.product.commerce.enabled=false
```

After adding the property, restart the Liferay server to apply your changes.

If a `portal-ext.properties` file does not already exist, create one in the `[LIFERAY_HOME]` or `[USER_HOME]` folder.

```{note}
If you used the Setup Wizard for your bundle, portal properties are set using the `portal-setup-wizard.properties` file in the `[LIFERAY_HOME]` folder. Since this file takes priority over the `portal-ext.properties` file, ensure it doesn't have a conflicting value for the `enterprise.product.commerce.enabled` property. See [Portal Properties](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/portal-properties.html) to learn more.
```

### Disabling Commerce for Docker Containers

On a Docker container, you can configure the `enterprise.product.commerce.enabled` property with a `portal-ext.properties` file or with a Docker `env` variable.

In a `portal-ext.properties` file, add this property:

```properties
enterprise.product.commerce.enabled=false
```

Or use this Docker `env` variable:

```properties
LIFERAY_ENTERPRISE_PERIOD_PRODUCT_PERIOD_COMMERCE_PERIOD_ENABLED=false
```

See [Configuring Containers](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/using-liferay-docker-images/configuring-containers.html#portal-properties) for more information.

If successful, the Commerce modules no longer appear in the Global Menu (![Global Menu](../images/icon-applications-menu.png)).

## Activating Liferay Commerce Enterprise for Liferay DXP 7.3 FP3/SP3+

{bdg-primary}`Liferay DXP and Commerce Subscriptions Required`

For DXP 7.3 FP3/SP2+, activate Commerce by setting the `enterprise.product.commerce.enabled` portal property to `true`. 

### Activating Commerce for Liferay Bundles

You can configure the `enterprise.product.commerce.enabled` property using a `portal-ext.properties` file. Add this property to your Liferay server's `portal-ext.properties` file:

```properties
enterprise.product.commerce.enabled=true
```

After adding the property, restart the Liferay server to apply your changes.

If a `portal-ext.properties` file does not already exist, create one in the `[LIFERAY_HOME]` or `[USER_HOME]` folder.

### Activating Commerce for Docker Containers

If you're using a Docker container, the `enterprise.product.commerce.enabled` property can be configured using a `portal-ext.properties` file or overridden using a Docker `env` variable.

To use a `portal-ext.properties` file, simply add the following property to a new line:

```properties
enterprise.product.commerce.enabled=true
```

To override the portal property, use the following Docker `env` variable:

```properties
LIFERAY_ENTERPRISE_PERIOD_PRODUCT_PERIOD_COMMERCE_PERIOD_ENABLED=true
```

### Verifying Successful Activation

To verify the property has been added successfully, open Liferay DXP and click on the *Global Menu* (![Global Menu](../images/icon-applications-menu.png)). If successful, you can view and access Commerce modules in the *Commerce* tab. Alternatively, you can open the *Global Menu*, and go to *Control Panel* &rarr; *Server Administration* &rarr; *Properties* &rarr; *Portal Properties*. The new property should be listed along with other properties.

## Activating Liferay Commerce Enterprise for Liferay DXP 7.1-7.3 FP2

{bdg-primary}`Liferay DXP and Commerce Subscriptions Required`

For Liferay DXP 7.1-7.3 FP2, Commerce requires a valid license for use. This license is provided as XML (`.xml`) activation key and must be deployed to your Liferay server to enable Commerce modules.

Commerce licenses use many of the same parameters as DXP licenses, including `product-version`, `license-type`, and `expiration-date`. However, restrictions based on system resources (e.g., processor cores) or product version are not implemented in Commerce licenses.

```{important}
Both the DXP and Commerce activation keys must be of the same `license-type` (e.g., `production`, `developer`, or `enterprise`). A warning is thrown in the server startup log if the license types do not match. 
   
*Production* licenses also require a matching hostname, IP address, or mac address for validation.
```

* [Obtaining Commerce Activation Keys](#obtaining-commerce-activation-keys)
* [Deploying Commerce Activation Keys](#deploying-commerce-activation-keys)
* [Updating an Expired Commerce Enterprise License](#updating-an-expired-commerce-enterprise-license)

### Obtaining Commerce Activation Keys

If you've already [purchased](https://www.liferay.com/contact-sales) a Commerce subscription, you can obtain your activation key as a download in one of the following ways:

* Open a [Help Center](https://liferay-support.zendesk.com/agent/) ticket with the *Activation Key/Project Administration* component.

* Send a request by email to your regional Provisioning team (i.e., provisioning-[region]@liferay.com).

* Download a Commerce activation key from [here](https://customer.liferay.com/en_US/activation-key).

### Deploying Commerce Activation Keys

Once you've acquired your XML activation key, you can activate Commerce Enterprise by copying it to your DXP instance's `deploy` folder. This process is the same as activating Liferay DXP.

```{note}
As of Liferay 7.3 SP1, users are no longer required to re-index their instance after activating Commerce.
```

#### Deploying to DXP Bundles

Copy your XML file to the [`${liferay.home}/deploy`](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/reference/liferay-home.html) folder for your DXP instance. While processing the activation key, Liferay relocates this file to the `${liferay.home}/osgi/modules` folder and generates a license file (`.li`) in the `${liferay.home}/data/license` folder. See [Activating Liferay DXP](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/setting-up-liferay/activating-liferay-dxp.html) for more information.

#### Deploying to Docker Containers

Copy your XML file to the container's `/opt/liferay/deploy` folder. While processing the activation key, Liferay relocates this file to the `opt/liferay/osgi/modules` folder, and generates a license file (`.li`) in the `opt/liferay/data/license` folder. See [Providing Files to a Container](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/using-liferay-docker-images/providing-files-to-the-container.html?highlight=opt) for more information about deploying files to Docker containers.

#### Deploying to DXP Cloud

Copy your key to the [`liferay/configs/{ENV}/deploy/`](https://learn.liferay.com/dxp-cloud/latest/en/using-the-liferay-dxp-service/deploying-to-the-liferay-service.html#deploying-licenses) folder in your Project's central Git repository, and commit your changes. Then, trigger a Jenkins build for your Liferay service, and deploy it to the desired Project environment. See [Overview of the DXP Cloud Deployment Workflow](https://learn.liferay.com/dxp-cloud/latest/en/build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.html) for more information about deploying files to an environment's Liferay service.

```{important}
If you've purchased Commerce through a DXP Cloud contract, then the DXP Cloud team manages the activation key through the cloud infrastructure, and there is no need to manage the key yourself. However, if you are adding Commerce to an existing DXP Cloud project, you must manually deploy the licence yourself.
```

#### Verifying Deployment

Verify your key has successfully deployed via the console:

   ```
   INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:271] Processing activation-key-commercesubscriptiondevelopment-1-developeractivationkeys.xml
   ...
   INFO  [fileinstall-directory-watcher][LicenseManager:?] Commerce Subscription Development license validation passed
   INFO  [fileinstall-directory-watcher][LicenseManager:?] License registered for Commerce Subscription Development
   ```

### Updating an Expired Commerce Enterprise License

Commerce Enterprise licenses are only valid for a set period of time based on the terms of a user's subscription. When a license nears expiration (i.e., < 30 days for most licenses, < 7 days for a 30 day license), a warning message is displayed in Commerce applications for Administrators. A license has a 2 day grace period after its given expiration date before it expires.

If a license expires, the Commerce modules remain activated, though it is no longer usable in the UI and does not allow API calls until the license is updated. A notification is displayed on Commerce applications for all users, indicating the Commerce application is unavailable. Administrators are asked to update the license, while other users are asked to contact their administrator.

The console also displays an error message during server restart:

   ```
   ERROR [main][LicenseManager:?] Liferay Commerce license is expired
   ```

In order to reactivate Liferay Commerce Enterprise, first remove the expired files from the server, and then [deploy](#deploying-commerce-activation-keys) your new key.

   ```{tip}
   You can remove and add activation keys while the server is running.
   ```

#### Updating DXP Bundles

Remove the expired XML key from the `${liferay.home}/osgi/modules` folder and the expired license file from the `${liferay.home}/data/license` folder. Then, add your new activation key to the `${liferay.home}/deploy` folder.

#### Updating Docker Containers

Remove the expired XML key from the `opt/liferay/osgi/modules` folder and the expired license file from the `opt/liferay/data/licenses` folder. Then add your new activation key to the `/opt/liferay/deploy` folder in your container.

#### Updating the Liferay Service in DXP Cloud Projects

The DXP Cloud team manages both DXP and Commerce licenses, and there is no need for users to manually update them for themselves.

## Additional Information

* [Installation Overview](./installation-overview.md)
* [Upgrading Liferay Commerce](./upgrading-liferay-commerce.md)
* [Activating a Marketplace App Through a Proxy Server](https://help.liferay.com/hc/en-us/articles/360018427391)
