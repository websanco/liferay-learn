# Understanding Virtual Instances

Virtual instances are semi-discrete instances of Liferay DXP: the have unique domain names, but share a server and database. Each virtual instance can have independent data and configurations.

<!-- Table: Uses of Virtual Instances -->

Configuration of a Liferay DXP system happens at different [scopes](../system-seetings/system-settings-and-configuration-scope.md). Configuration at the virtual instance scope is the next most broad scope after the system scope. 

```important::
   The system scope is the highest level scope you can make configurations at. All virtual instances are impacted by configuration done at this scope. The instance scope applies only to one particular virtual instance.

   If your installation contains only one virtual instance, there's no practical difference between a system scoped configuration and a virtual instance scoped configuration. Both types of configuration will apply throughout your system.
```

Here's a quick scenario: you already have a server hosting a Liferay DXP installation and a database. It has many [Users](./../../users-and-permissions/users/understanding-users.md), [Sites](/docs/7-2/user/-/knowledge_base/u/building-a-site), and specific [instance settings](./../virtual-instances/configuring-a-virtual-instance-instance-configuration.md). If you require a second similar installation, then adding a *Virtual Instance* might be right for you. 

Because of the unique domain name, Users are directed to the correct Virtual Instance. Because Virtual Instances share an application server and OSGi container, they also share these customizations: 

-  All custom code you've deployed
-  [System-scoped configurations](./../system-settings/system-settings.md) (e.g., `.config` files and changes made in *Control Panel* &rarr; *Configuration* &rarr; *System Settings*). 
-  Application server configuration.

Add and manage Virtual Instances in *Control Panel* &rarr; *Configuration* &rarr; *Virtual Instances*.

![Add and manage virtual instances of Liferay in the Control Panel's Virtual Instances section.](./understanding-virtual-instances/images/01.png)

Configure an instance in *Control Panel* &rarr; *Configuration* &rarr; *Instance Settings*. The Instance Settings are organized into three sections: PLATFORM, SECURITY, and CONTENT AND DATA. Here we focus on the instance settings categories under the PLATFORM section. The CONTENT AND DATA settings are specific to an application, and the [SECURITY instance settings](./../../installation-and-upgrades/securing-liferay/introduction-to-securing-liferay.md) are covered in separate documentation.

![Instance Settings has several PLATFORM categories.](./understanding-virtual-instances/images/02.png)

[Search](./../../using-search/) and [Analytics Cloud](https://help.liferay.com/hc/en-us/categories/360000872551) are also covered separately.

