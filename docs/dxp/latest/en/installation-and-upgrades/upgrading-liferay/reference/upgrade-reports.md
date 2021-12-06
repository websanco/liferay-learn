# Upgrade Reports

When you upgrade data, it's important to know the changes made and any issues that occurred. The [Database Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md) captures this information in reports that include these details:

* Execution date and time
* Initial, expected, and final DXP/Portal schema versions and build numbers
* Database vendor and version
* Upgrade-related Portal property settings
* Document Library Store and `rootDir` configuration settings
* Document Library size (for [File System Store](../../../system-administration/file-storage/other-file-store-types/simple-file-system-store.md) and [Advanced File System Store](../../../system-administration/file-storage.md))
* 20 longest-running upgrade processes
* Errors and each type's frequency
* Warnings and each type's frequency

The tool reports this information to a file called `upgrade_report.info` in the `tools/portal-tools-db-upgrade-client/reports` folder. On subsequent upgrades, the tool appends a time stamp to the file name (e.g., `upgrade_report.info.1631029824000`) and then reports new upgrade details to a new `upgrade_report.info` file.

```{note}
Upgrade reporting is available for Liferay DXP/Portal 7.4+.
```

Upgrade reporting is enabled by setting the `upgrade.report.enabled=true` [Portal property](../../reference/portal-properties.md) in the `tools/portal-tools-db-upgrade-client/portal-upgrade-ext.properties` file. For example,

```bash
cd liferay-home
```

```bash
echo "upgrade.report.enabled=\"true\"" << tools/portal-tools-db-upgrade-client/portal-upgrade-ext.properties
```

The [Database Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md) generates an upgrade report the next time you run it.

## Additional Information

* [Upgrade Basics](../upgrade-basics.md)

* [Running the Database Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md)

* [Database Upgrade Tool Reference](../reference/database-upgrade-tool-reference.md#manual-configuration)

* [Troubleshooting Upgrades](../reference/troubleshooting-upgrades.md)
