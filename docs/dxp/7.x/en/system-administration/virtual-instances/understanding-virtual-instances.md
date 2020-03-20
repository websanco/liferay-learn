# Understanding Virtual Instances

Once @product@ is installed, the configuration begins. Recall that configuration happens at different scopes. Here we're covering configuration at the virtual instance scope. There's an important difference between the system scope and the instance scope. The system scope is the highest level scope you can make configurations at. All virtual instances are impacted by configuration done at this scope. The instance scope applies only to one particular virtual instance.

Virtual instances have unique domain names but share a server and database. Each virtual instance can have independent data and configurations. 

The articles in this section cover these topics:

- Adding a virtual instance
- Configuring a virtual instance

Get started by learning how to add a virtual instance. 

## Virtual Instances

Here's a quick scenario: you already have a server hosting a @product@ installation and a database. It has many [Users](/docs/7-2/user/-/knowledge_base/u/users-and-organizations), [Sites](/docs/7-2/user/-/knowledge_base/u/building-a-site), and specific [instance settings](/docs/7-2/user/-/knowledge_base/u/instance-configuration-instance-settings#general).  If you require a second similar installation, then adding a *Virtual Instance* might be right for you. 

You can run more than one Virtual Instance on a single server with a shared database, but separate data and configurations. Users are directed to the correct Virtual Instance via its unique domain name. And because Virtual Instances share an application server and OSGi container, they also share these customizations: 

-  Custom code deployed by developers and administrators.
-  [System-scoped configurations](/docs/7-2/user/-/knowledge_base/u/system-settings) (e.g., `.config` files, changes made in *Control Panel* &rarr; *Configuration* &rarr; *System Settings*). 
-  Application server configuration.

Administrators can manage Virtual Instances in *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.

![Figure 1: Add and manage virtual instances of Liferay in the Control Panel's *Configuration* &rarr; *Virtual Instances* section.](../../../images/virtual-instances.png)

## Configuring Virtual Instances

To access instance settings, open the Control Panel and navigate to *Configuration* &rarr; *Instance Settings*. The Instance Settings are organized into three sections: PLATFORM, SECURITY, and CONTENT AND DATA. Here we focus on the instance settings available under the PLATFORM section. The CONTENT AND DATA settings are specific to each application, and the SECURITY instance settings are covered in their respective articles, listed below for reference:

- [LDAP](/docs/7-2/deploy/-/knowledge_base/d/configuring-ldap)
- [OAuth 2](/docs/7-2/deploy/-/knowledge_base/d/oauth2-scopes#creating-the-authorization-page)
- SSO: 
  - [CAS Server](/docs/7-2/deploy/-/knowledge_base/d/cas-central-authentication-service-single-sign-on-authentication)
  - [Token based SSO](/docs/7-2/deploy/-/knowledge_base/d/token-based-single-sign-on-authentication)
  <!-- [Facebook Connect]() Add back once article is available-->
  - [Open ID Connect](/docs/7-2/deploy/-/knowledge_base/d/authenticating-with-openid-connect#enabling-openid-connect-authentication)
  - [OpenSSO](/docs/7-2/deploy/-/knowledge_base/d/opensso-single-sign-on-authentication)

  The PLATFORM instance settings are covered in the articles shown below:

  - [Email](/docs/7-2/user/-/knowledge_base/u/email-instance-settings)
  - [Instance Configuration](/docs/7-2/user/-/knowledge_base/u/instance-configuration-instance-settings)
  - [User Authentication](/docs/7-2/user/-/knowledge_base/u/user-authentication-instance-settings)
  - [Users](/docs/7-2/user/-/knowledge_base/u/users-instance-settings)
  - [More Platform Settings](/docs/7-2/user/-/knowledge_base/u/more-platform-section-instance-settings)
