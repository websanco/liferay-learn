# Configuring the Liferay DXP Service

There are several methods available to configure Liferay DXP: through the in [DXP System Settings](https://learn.liferay.com/dxp/7.x/en/system-administration/configuring-liferay/system-settings.html) and through the use of [config](https://learn.liferay.com/dxp/7.x/en/system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.html) and [property files](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html).  DXP property and configuration files for your Liferay DXP instance in DXP Cloud are deployed by being placed inside of one of the `configs/` folders in the Liferay DXP service directory in your repository.

```
liferay
├── configs
│   ├── common
│   ├── dev
│   ├── local
│   ├── prd
│   └── uat
└── LCP.json
```

With the exception of the `common/` directory, changes added to an environment-specific folder (e.g., `dev`, `uat`, `prod`) will _only_ be propagated when deploying to the corresponding environment. Changes added to a `common/` directory will _always_ be deployed, regardless of the target deployment environment. This applies to all subfolders within the `configs/` directory, for all services.

```note::
   If you are using version 3.x.x services, then these configuration files instead belong in the appropriate ``lcp/liferay/config/{ENV}/`` folder. See `Understanding Service Stack Versions <../reference/understanding-service-stack-versions.md>`__ for more information on checking the version.
```

## Portal Properties

[Portal properties](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html) are files of the form `portal-*.properties` that are used to configure your Liferay DXP environment.

For an on-premises Liferay DXP instance, these files belong inside of `$LIFERAY_HOME`. When using Liferay DXP Cloud, place these files into the appropriate `configs/{ENV}/` folder(s) for them to be copied into `$LIFERAY_HOME` for the Liferay DXP instance on deployment.

For example, the properties in a dev environment will result from the property files in the `configs/common` directory and the properties in the `configs/dev` directory. If any files have the same name, the file in the environment specific directory will overwrite the file from the `common` directory.

```note::
   If you are using version 3.x.x services, then these configuration files instead belong in the appropriate ``lcp/liferay/config/{ENV}/`` folder. See `Understanding Service Stack Versions <../reference/understanding-service-stack-versions.md>`__ for more information on checking the version.
```

Liferay will only read properties directly from the `portal-ext.properties` file, but it can be helpful to organize your properties into the following files:

* `portal-all.properties`: Contains the properties that change Liferay DXP across environments

* `portal-env.properties`: Contains the properties that only affect the current environment (for example, credentials and URL endpoints for external services that differ from environment to environment)

If you choose to use these other portal properties files, be sure to explicitly include them in your `portal-ext.properties` file using the `include-and-override` property. This is explained in greater detail [here](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html#portal-property-priority).

You could organize your properties with the following structure:

```
liferay
├── configs
│   ├── common
│   │   ├── portal-ext.properties
│   │   └── portal-all.properties
│   ├── dev
│   │   └── portal-env.properties
│   ├── local
│   │   └── portal-env.properties
│   ├── prd
│   │   └── portal-env.properties
│   └── uat
│       └── portal-env.properties
└── LCP.json
```

For this to work, the `portal-ext.properties` file must explicitly import `portal-all.properties` and `portal-env.properties`:
```
include-and-override=/opt/liferay/portal-all.properties
include-and-override=/opt/liferay/portal-env.properties
```

The `portal-ext.properties` and `portal-all.properties` files will be shared across all environments. You can add any shared properties there and add environment specific properties in each respective `portal-env.properties` file.

```note::
   Portal properties may also be defined as environment variables. See `the environment variables reference <./introduction-to-the-liferay-dxp-service.md#environment-variables-reference>`_ for more information.
```

## OSGi Configurations

OSGi configurations (`.cfg` or `.config` files) are used to configure OSGi components in Liferay DXP.

These configuration files belong in the `osgi/configs/` folder inside of `$LIFERAY_HOME`. When using Liferay DXP Cloud, place these files into the appropriate `configs/{ENV}/osgi/` folder(s) for them to be copied into `/osgi/configs` for the Liferay DXP instance on deployment.

```note::
   If you are using version 3.x.x services, then OSGi configuration files instead belong in the appropriate ``config/{ENV/`` folder within the Liferay service directory. See `Understanding Service Stack Versions <../reference/understanding-service-stack-versions.md>`__ for more information on checking the version.
```

## Additional Information

* [Introduction to the Liferay DXP Service](./introduction-to-the-liferay-dxp-service.md)
* [Enabling Clustering in DXP Cloud](./setting-up-clustering-in-dxp-cloud.md)
* [Portal Properties](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/portal-properties.html)
