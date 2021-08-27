# Scoping Configurations

In Liferay DXP, you can set an application's configuration to different levels of scope: System, Instance, Site, or Portlet. For example, if you create an application to have instance scoped configuration, the application can be independently configured for each instance you have set up in your Liferay DXP installation. This is useful because it gives more flexibility and control to each user. For example administrators of a DXP instance can set a configuration for their own instance that is independent from other instances. 

To learn more about scope, see [Understanding Configuration Scope](../../../system-administration/configuring-liferay/understanding-configuration-scope.md).

To see an example of an instance scoped configuration, see the example portlet from [Setting and Accessing Configurations](./setting-and-accessing-configurations.md)

Note, portlet scoped configuration takes a different approach. See [Portlet Level Configuration](./portlet-level-configuration.md) for more information.

## Specify Scope in the Configuration Interface

To set the scope of a configuration, specify the scope in the configuration interface. Use the `@ExtendedObjectClassDefinition` annotation to set your scope. Use `Scope.SYSTEM` for system scope. Use `Scope.COMPANY` for instance scope. Use `Scope.GROUP` for site scope. 

```{literalinclude} ./scoping-configurations/resources/liferay-n2f3.zip/n2f3-web/src/main/java/com/acme/n2f3/web/internal/configuration/N2F3WebConfiguration.java
:language: java
:lines: 7-9
```

## Use the Configuration Provider

To retrieve the configuration, make use of `ConfigurationProvider`. The Configuration Provider API provides an easy way to access the configuration. Choose the specific method for your application needs:

* `getSystemConfiguration()`: Obtains system-scoped configuration. The application's system level configuration is found in *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.

* `getCompanyConfiguration()`: Obtains instance-scoped configuration. Use an instance of `Portal` to retrieve `companyId`. For example, `_portal.getCompanyId(renderRequest)`. The application's instance level configuration is found in *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings*.

* `getGroupConfiguration()`: Gets Site-scoped configuration. Use an instance of `Portal` to retrieve `groupId`. For example, `_portal.getScopeGroupId(renderRequest)`. The application's Site level configuration is found in *Product Menu* &rarr; *Configuration* &rarr; *Site Settings*. Note, this is available in Liferay 7.4.

For portlet scoped configuration see [Portlet Level Configuration](./portlet-level-configuration.md).
