## Setting Up Liferay

```{toctree}
:maxdepth: 3

setting-up-liferay/activating-liferay-dxp.md
../system-administration/configuring-liferay/virtual-instances/instance-configuration.md
setting-up-liferay/initial-instance-localization.md
setting-up-liferay/configuring-mail.md
../system-administration/configuring-liferay/virtual-instances/users.md
../system-administration/file_storage.rst
../system-administration/file-storage/enabling-antivirus-scanning-for-uploaded-files.md
../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md
securing-liferay/securing_liferay.rst
maintaining-a-liferay-dxp-installation/backing-up.md
../system-administration/installing_and_managing_apps.rst
setting-up-liferay/using-a-cdn.md
setting-up-liferay/clustering-for-high-availability.md
setting-up-liferay/tuning-liferay.md
setting-up-liferay/tuning-your-jvm.md
```

After [installing Liferay](./installing_liferay.html), configure it to meet your needs. Here are common setup tasks.

## Setup Tasks

* [Activate Liferay](#activate-liferay) (Subscription Required)
* [Configure Your Instance](#configure-your-instance)
* [Localize Your Instance](#localize-your-instance)
* [Configure Mail](#configure-mail)
* [Configure Users](#configure-users)
* [Configure File Storage](#configure-file-storage)
* [Install a Search Engine](#install-a-search-engine)
* [Secure Liferay](#securre-liferay)
* [Configure Backups](#configure-backups)

**Other Setup Tasks**

* [Install Apps From Marketplace](#install-apps-from-marketplace)
* [Configure Roles and Permissions](#configure-roles-and-permissions)
* [Add Custom Fields](#add-custom-fields)
* [Integrate with Existing Systems](#integrate-with-existing-systems)
* [Configure Media File Previews](#configure-media-file-previews)
* [Configure a CDN](#configure-a-cdn)
* [Configure High Availability](#configure-high-availability)
* [Tune Liferay](#tune-liferay)

Each task above links to a description below that includes references to applicable articles. The first tasks are essential for any production-ready Liferay instance. Examine the *Other Setup Tasks* that also apply to your system. As you complete tasks, make sure to revisit the checklist above.

## Activate Liferay

`Subscribers`

If you're using Liferay DXP, [apply your activation key](./setting-up-liferay/activating-liferay-dxp.md).

## Configure Your Instance

Configure your virtual instance's basic appearance, contact information, Terms of Use, and essential pages, including landing pages and logout pages. [Instance Configuration](../system-administration/configuring-liferay/virtual-instances/instance-configuration.md) explains how to do it all.

## Localize Your Instance

Configure your instance's locale and time zone. See [Initial Instance Localization](./setting-up-liferay/initial-instance-localization.md) for details.

## Configure Mail

[Set up a mail server](./setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md) to communicate with Users. Then configure your [email settings](../system-administration/configuring-liferay/virtual-instances/email-settings.md), including your email sender and message templates for email verifications, password resets, and password changes.

## Configure Users

After you [understand Users](../users-and-permissions/users/understanding-users.md), configure them for your instance.

* Enable/disable User fields
* Add any necessary [custom User fields](../users-and-permissions/users/adding-custom-fields-to-users.md)
* Specify whether to allow strangers to create accounts
* Define [User authentication](./securing-liferay/authentication-basics.md)
* Configure automatic association of Users to specific Sites, [Roles](../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md), and [User Groups](../users-and-permissions/user_groups.html)

Visit [Users](../system-administration/configuring-liferay/virtual-instances/users.md), [User Authentication](../system-administration/configuring-liferay/virtual-instances/user-authentication.md), and [Adding Custom Fields For Users](../users-and-permissions/users/adding-custom-fields-to-users.md) for details.

## Configure File Storage

Using [Documents and Media](../content-authoring-and-management/documents-and-media/sharing-documents-and-media.md), file attachments, and embedding images in content requires file storage. Visit [File Storage](../system-administration/file-storage/configuring-file-storage.md) to configure it.

After setting up file storage, consider enabling antivirus file scanning. See [Enabling Antivirus Scanning for Uploaded Files](../system-administration/file-storage/enabling-antivirus-scanning-for-uploaded-files.md) to learn how.

## Install a Search Engine

Empower Users to search your site. Search engines such as Liferay Enterprise Search return results fast. Visit [Installing a Search Engine](../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md).

## Secure Liferay

It's really important to secure Liferay too. Go to [Securing Liferay](./securing-liferay/securing-liferay.md) to learn more.

## Configure Backups

As you develop your virtual instance make sure to back it up. See [Backing Up](./maintaining-a-liferay-dxp-installation/backing-up.md) for guidance.

## Other Setup Tasks

The tasks described above are typically (but not always) completed first. In any case, you may need to perform the following applicable tasks soon too.

## Install Apps From Marketplace

Improve your sites with [themes](../getting-started/changing-your-sites-appearance.md), connectors, and all kinds applications available on [Liferay Marketplace](https://web.liferay.com/marketplace). [Installing and managing apps](../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md) has never been easier.

## Configure Roles and Permissions

Use [Roles](../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md) to categorize all of your Users. Define [Permissions](../users-and-permissions/roles-and-permissions/defining-role-permissions.md) for the activities Users perform in your Instances and Sites.

## Add Custom Fields

Entities in your Instances and applications can be customized with additional fields. This may include adding fields for Users. For details, see [Adding Custom Fields](../system-administration/configuring-liferay/adding-custom-fields.md) and [Adding Custom Fields For Users](../users-and-permissions/users/adding-custom-fields-to-users.md).

## Integrate with Existing Systems

Liferay can integrate with some other systems. For example, if you're using a directory such as LDAP, you can import Users from it. [Connecting to an LDAP Directory](../users-and-permissions/devops/connecting-to-a-user-directory/connecting-to-an-ldap-directory.md) demonstrates the process. Please search this site for integrations you may need.

## Configure Media File Previews

Set up external services for your site's images, videos, and audio files. See [Configuring External Services](../system-administration/using-the-server-administration-panel/configuring-external-services.md) to learn how.

## Configure a CDN

Deliver static content faster via a Content Delivery Network (CDN). Visit [Using a CDN](./setting-up-liferay/using-a-cdn.md) for more information.

## Configure High Availability

You'll want eliminate or minimize any site downtime. If a server fails or needs to be shut down for maintenance, it's important to have other servers to handle the requests. You can maximize site availability by configuring multiple servers for Liferay, the search engine, and other components. Learn how to cluster servers at [Clustering for High Availability](./setting-up-liferay/clustering-for-high-availability.md).

## Tune Liferay

Tune Liferay's JVM, connection pools, and more for optimal performance. See [Tuning Liferay](./setting-up-liferay/tuning-liferay.md) and [Tuning Your JVM](./setting-up-liferay/tuning-your-jvm.md) for details.

## What's Next

If you're using Liferay DXP and haven't [activated](./setting-up-liferay/activating-liferay-dxp.md) it, do that first. Then continue with [Instance Configuration](../system-administration/configuring-liferay/virtual-instances/instance-configuration.md) and the other [Setup Tasks`_ listed above.

```{important}
Before going to production, configure a process for backing up your installation and data. Please see [Backing Up](./maintaining-a-liferay-dxp-installation/backing-up.md) for details.
```