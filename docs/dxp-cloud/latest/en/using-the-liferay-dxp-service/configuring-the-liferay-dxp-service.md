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

```note::
   If you are using version 3.x.x services, then these configuration files instead belong in the appropriate ``lcp/liferay/config/{ENV}/`` folder. See `Understanding Service Stack Versions <../reference/understanding-service-stack-versions.md>`__ for more information on checking the version.
```

These are the types of portal properties files you may use in one of the `config` folders:

* `portal-all.properties`: Contains the properties that change Liferay DXP across environments

* `portal-env.properties`: Contains the properties that only affect the current environment (for example, credentials and URL endpoints for external services that differ from environment to environment)

* `portal-clu.properties`: Contains the pre-configured properties for clustering Liferay DXP on DXP Cloud; see [Setting Up Clustering in DXP Cloud](./setting-up-clustering-in-dxp-cloud.md) for more information

* `portal-ext.properties`: Contains the final changes to the Liferay DXP configuration; since most properties are configured in `portal-all.properties` and `portal-env.properties`, this file is often empty or missing altogether

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
