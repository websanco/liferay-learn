# Scoping Configurations

In Liferay DXP, an application's configuration can be set at different levels of scope: System, Instance, Site, or Portlet. For example, if you create an application to have instance scoped configuration, the application can be independently configured for each instance you have set up in your Liferay DXP installation. To learn more about scope, see [Understanding Configuration Scope](../../../system-administration/configuring-liferay/understanding-configuration-scope.md).

To see an example of an instance scoped configuration, see the example portlet from [Setting and Accessing Configurations](./setting-and-accessing-configurations.md)

Note, portlet scoped configuration takes a different approach. See [Portlet Level Configuration](./portlet-level-configuration.md) for more information.

## Specify Scope in the Configuration Interface

Scoping a configuration requires a few different steps. First, specify the scope in your configuration interface. Use the `@ExtendedObjectClassDefinition` annotation to set your scope. Use `Scope.SYSTEM` for system scope. Use `Scope.COMPANY` for instance scope. Use `Scope.GROUP` for site scope. Use `Scope.PORTLET_INSTANCE` for the portlet scope.

```literalinclude:: ./scoping-configurations/resources/liferay-n2f3.zip/n2f3-web/src/main/java/com/acme/n2f3/web/internal/configuration/N2F3WebConfiguration.java
   :language: java
   :lines: 7-9
```

Notice the sample tutorial configuration interface is set with a instance scoped configuration.

## Create a Configuration Bean Declaration

To enable retrieval of a scoped configuration, the application's configuration must be registered with a `ConfigurationBeanDeclaration`. This enables the system to keep track of configuration changes as they happen.

```literalinclude:: ./scoping-configurations/resources/liferay-n2f3.zip/n2f3-web/src/main/java/com/acme/n2f3/web/internal/settings/definition/N2F3WebConfigurationBeanDeclaration.java
   :language: java
   :lines: 9-18
```

This class has one method that returns the configuration interface class of your application.

## Use the Configuration Provider

The Configuration Provider is an API that provides an easy way to retrieve configuration. Use the specific method for your application needs:

* `getSystemConfiguration()`: Used to obtain system scoped configuration. The application's system level configuration is found in *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.

* `getCompanyConfiguration()`: Used to obtain instance scoped configuration. Use an instance of `Portal` to retrieve `companyId`. For example, `_portal.getCompanyId(renderRequest)`. The application's instance level configuration is found in *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings*.

* `getGroupConfiguration()`: Used to get site scoped configuration. Use an instance of `Portal` to retrieve `groupId`. For example, `_portal.getScopeGroupId(renderRequest)`. The application's site level configuration is found in *Product Menu* &rarr; *Configuration* &rarr; *Site Settings*. Note, this is available in Liferay 7.4.

For portlet scoped configuration see [Portlet Level Configuration](./portlet-level-configuration.md).