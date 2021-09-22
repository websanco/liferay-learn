# Reporting Upgrade Details

When you upgrade your data, it's important to know the changes made and any issues that occurred. The [Database Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md)'s built-in reporting capability captures this information. Reports include these details:

* Upgrade execution date and time
* Initial, expected, and final DXP/Portal schema versions and build numbers.
* Database vendor and version
* Upgrade-related Portal property settings
* Document Library Store and `rootDir` configuration settings
* Document Library folder size (for [File System Store](../../../system-administration/file-storage/other-file-store-types/simple-file-system-store.md) or [Advanced File System Store](../../../system-administration/file-storage/configuring-file-storage.md))
* 20 longest-running upgrade processes
* Errors that occurred and each type's frequency
* Warnings that occurred and each type's frequency

The tool reports upgrade details to a file called `upgrade_report.info` in the `tools/portal-tools-db-upgrade-client/reports` folder. On subsequent upgrades the tool renames the `upgrade_report.info` file to include a timestamp (e.g., `upgrade_report.info.1631029824000`) and then writes the new upgrade details to a new `upgrade_report.info` file

```{note}
Upgrade reporting is available for Liferay DXP/Portal 7.4+.
```

You can enable upgrade reporting by setting the `upgrade.report.enabled=true` [Portal property](../../reference/portal-properties.md) in the `tools/portal-tools-db-upgrade-client/portal-upgrade-ext.properties` file. For example,

```bash
cd liferay-home
```

```bash
echo "upgrade.report.enabled=\"true\"" << tools/portal-tools-db-upgrade-client/portal-upgrade-ext.properties
```

When you [run the Database Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md), it logs details to the `tools/portal-tools-db-upgrade-client/reports/upgrade_report.info` file.

## Additional Information

[Upgrade Overview](../upgrade-basics/upgrade-overview.md)

[Running the Database Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md)

[Database Upgrade Tool Reference](../reference/database-upgrade-tool-reference.md#manual-configuration)

[Troubleshooting Upgrades](../reference/troubleshooting-upgrades.md)