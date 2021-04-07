# Liferay Service Environment Variables

The Liferay service has a [range of environment variables](#environoment-variables-reference) that are used to configure the service, its connection to other services, and the DXP installation itself. You can use DXP Cloud-specific environment variables, or you can define variables that override DXP [portal properties](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html).

## Overriding Portal Properties

You can use environment variables in the Liferay service to override configurations normally defined in portal properties files.

Check the [portal properties documentation](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html) to find the environment variable name ("Env") for each corresponding portal property. For example, you can override the portal property `company.default.time.zone` with the environment variable, `LIFERAY_COMPANY_PERIOD_DEFAULT_PERIOD_TIME_PERIOD_ZONE`.

See [Defining Environment Variables](../reference/defining-environment-variables.md) for more information on adding them to the Liferay service.

### Converting Portal Property Names to Environment Variables

If the [portal properties documentation](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html) does not have a direct translation of a portal property, then you can also convert the property to an overriding environment variable yourself.

Environment variables overriding portal properties:

* Must not start with a digit.

* Must have the prefix `LIFERAY_` added in front of them.

* Must only consist of uppercase letters, digits, and the underscore (`_`) character. Any character that does not fit this constraint must be converted to its corresponding [`CharPool`](https://docs.liferay.com/dxp/portal/7.3-latest/javadocs/modules/core/petra/com.liferay.petra.string/) or [Unicode](https://unicode-table.com/en/) endpoint (converted to decimal).

To meet these requirements, you must convert any portal properties to this format. This allows DXP Cloud to properly recognize the full name and match it to its corresponding portal property.

Use these steps to convert a portal property name to an environment variable name:

1. Convert any characters contained in the name that are not a letter, digit or underscore (including periods) to a corresponding [`CharPool`](https://docs.liferay.com/dxp/portal/7.3-latest/javadocs/modules/core/petra/com.liferay.petra.string/) or Unicode endpoint, and surround them in underscores.

    For example, convert the period character (`.`) to `_PERIOD_`, or `_46_` (if using Unicode).

1. Add the prefix `LIFERAY_` to the start of the variable name.

1. Convert any letters to upper case.

For example, taking the portal property name `setup.wizard.enabled`, then using `CharPool` endpoints, you can convert it to the environment variable named: `LIFERAY_SETUP_PERIOD_WIZARD_PERIOD_ENABLED`.

## Environment Variables Reference

The following environment variables may be set through the environment variables UI or the Liferay service's `LCP.json` file:

Name                                  | Default Value | Description  |
------------------------------------- | ------------- | ------------ |
`LCP_DATABASE_PORT` | `3306` | Sets the database port configuration used by the readOnly user. Overrides the `DATABASE_SERVICE_PORT` infra environment variable if it is defined. |
`LCP_LIFERAY_JDBC_CONNECTION_URL` |  | The URL used to make the database connection. This may be used to directly set the database name and host. The value should start with `jdbc:mysql://`. |
`LCP_LIFERAY_JDBC_DRIVER` |  | Allows for specifying the MySQL driver the Liferay service uses. In DXP versions 7.0 and 7.1, this is also used for the cluster configuration. |
`LCP_PROJECT_LIFERAY_CLUSTER_ENABLED` | `true` | Whether to enable clustering and communication between nodes. |
`LCP_PROJECT_MONITOR_DYNATRACE_TENANT` |  | A string of characters that is part of the URL (prefix) of your Dynatrace SaaS account. Use this together with the `LCP_PROJECT_MONITOR_DYNATRACE_TOKEN` secret. |
`LIFERAY_JAVA_OPTS` | | JVM options that will be appended to `CATALINA_OPTS` to override the default recommended options. |

These variables must instead be [defined as Secrets](../infrastructure-and-operations/security/managing-secure-environment-variables-with-secrets.md) for the Liferay service:

Name                                  | Default Value | Description  |
------------------------------------- | ------------- | ------------ |
`LCP_PROJECT_MONITOR_DYNATRACE_TOKEN` |  | A string of characters that you can find in your Dynatrace account at *Deploy Dynatrace* &rarr; *Start installation* &rarr; *Set up PaaS monitoring* &rarr; *Installer Download*. |
`LCP_SECRET_DATABASE_NAME` |   | The database name used for database connections (jdbc, jdbc ping, and read-only user connections). |
`LCP_SECRET_DATABASE_PASSWORD` |  |  The database password used only for the jdbc (and jdbc ping) configurations. |
`LCP_SECRET_DATABASE_READONLY_USER` |  | The read-only user's username. |
`LCP_SECRET_DATABASE_READONLY_USER_PASSWORD` |  | The read-only user's password. |
`LCP_SECRET_DATABASE_USER` |  | The primary database user's user name. Used for the jdbc and jdbc ping connections. |