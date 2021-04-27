# Troubleshooting Upgrades

If you skip an upgrade step or have customized your interaction with Liferay's database, you can have issues. The following questions and answers address these issue types.

## How should I handle a foreign key constraint upgrade exception? 

In Liferay 7.3, some Liferay tables (for example, the `user_` table) have an additional primary key called `ctCollectionId`. If you have a custom table that has a foreign key mapped to one of these Liferay tables, the new primary key breaks the foreign key. For example, upgrading a custom table that has a foreign key to the `user_` table fails with messages like these:

```
INFO  [main][LoggingTimer:44] Completed com.liferay.portal.kernel.upgrade.UpgradeCTModel#doUpgrade#User_ in 750 ms
INFO  [main][UpgradeProcess:115] Failed upgrade process com.liferay.portal.kernel.upgrade.UpgradeCTModel in 4703 ms
INFO  [main][UpgradeProcess:115] Failed upgrade process com.liferay.portal.upgrade.PortalUpgradeProcess in 53125 ms
com.liferay.portal.kernel.upgrade.UpgradeException: com.liferay.portal.kernel.upgrade.UpgradeException: java.sql.SQLException: Error on rename of '.\liferaydxp\#sql-908_12f' to '.\liferaydxp\user_' (errno: 150 - Foreign key constraint is incorrectly formed)
```

The last `UpgradeException` message indicates that the upgrade can't rename a foreign key constraint to the `user_` table.

Liferay tables don't use foreign keys. You shouldn't reference Liferay tables with foreign keys. If your custom tables have such foreign keys, replace them with [Model Listeners](https://help.liferay.com/hc/en-us/articles/360029122631-Model-Listeners) that update your custom tables based on Liferay model changes. For example, if your custom table currently has a foreign key to the `user_` table, create a Model Listener that updates your custom table when `User` instances are added or deleted.

Here's how to do the replacement:

1. Create a Model Listener for the model events related to your custom table data.

1. Validate the Model Listener in a test environment.

1. In your upgrade environment, replace your custom table with one that doesn't use any foreign keys.

1. Upgrade your database.

1. Deploy your Model Listener on the new Liferay installation.

1. Start up your Liferay server.

Your Model Listener updates your new custom table based on the model events it's listening for.

## How should I handle an UpgradeException involving virtual columns?

