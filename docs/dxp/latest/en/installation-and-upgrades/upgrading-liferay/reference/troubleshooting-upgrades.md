# Troubleshooting Upgrades

Skipping an upgrade step or creating custom references to the Liferay database, can cause upgrade issues. The following questions and answers address some common situations.

## How should I handle an upgrade exception caused by a foreign key constraint? 

Liferay tables don't use foreign keys. If a new Liferay version adds a primary key to a Liferay table and there is a foreign key associated with the table, the foreign key breaks and the upgrade fails.

For example, Liferay 7.3 added a primary key called `ctCollectionId` to the `user_` table. A foreign key associated with the `user_` table causes the `user_` table upgrade to fail with messages like these:

```
INFO  [main][LoggingTimer:44] Completed com.liferay.portal.kernel.upgrade.UpgradeCTModel#doUpgrade#User_ in 750 ms
INFO  [main][UpgradeProcess:115] Failed upgrade process com.liferay.portal.kernel.upgrade.UpgradeCTModel in 4703 ms
INFO  [main][UpgradeProcess:115] Failed upgrade process com.liferay.portal.upgrade.PortalUpgradeProcess in 53125 ms
com.liferay.portal.kernel.upgrade.UpgradeException: com.liferay.portal.kernel.upgrade.UpgradeException: java.sql.SQLException: Error on rename of '.\liferaydxp\#sql-908_12f' to '.\liferaydxp\user_' (errno: 150 - Foreign key constraint is incorrectly formed)
```

The last `UpgradeException` message indicates that the upgrade can't rename a foreign key constraint to the `user_` table.

If you have custom tables that use foreign keys associated with Liferay tables, replace the foreign keys with [Model Listeners](../../../liferay-internals/extending-liferay/creating-a-model-listener.md) that update your custom tables based on Liferay model changes. For example, if your custom table currently has a foreign key referencing the `user_` table, create a Model Listener that updates your custom table when `User` instances are added or deleted.

Here's how to do the replacement:

1. Create a Model Listener for the model events related to your custom table data.

1. Validate the Model Listener in a test environment.

1. In your upgrade environment, replace your custom table with one that doesn't use any foreign keys.

1. Upgrade your database.

1. Deploy your Model Listener on the new Liferay installation.

1. Start up your Liferay server.

Your Model Listener updates your new custom table based on the model events it's listening for.

## How should I handle an upgrade process warning about renaming a table column used by a virtual column expression?

Some new Liferay versions rename table columns. Any [virtual columns](https://en.wikipedia.org/wiki/Virtual_column) associated with these columns inhibit database upgrade and must be removed before the upgrade. After the upgrade, you can add equivalent virtual columns.

For example, Liferay 7.0 renamed the `JournalArticle` table's `structureId` and `templateId` columns to `DDMStructureKey` and `DDMTemplateKey`, respectively. If you're upgrading from Liferay Portal 6.2 and you have associated virtual columns with the `JournalArticle` table, remove them before upgrading. In an Oracle database, for example, you can check for virtual columns with a query like this:

```sql
select column_name, data_default, hidden_column from user_tab_cols where table_name = 'JOURNALARTICLE';
```

After you've upgraded the table, you can add equivalent virtual columns to your database.
