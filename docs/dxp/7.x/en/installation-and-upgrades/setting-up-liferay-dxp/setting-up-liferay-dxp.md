# Setting Up Liferay

After installing Liferay, you can configure it to meet your needs. Here are common setup tasks. 

## Activate Liferay DXP (subscribers)

> Subscribers

If you're using Liferay DXP, [apply your activation key](./activating-liferay-dxp.md).

## Localize Your Instance 

Configure your Liferay instance's your locale and time zone, using the [Setup Wizard](../installing-liferay/running-liferay-for-the-first-time.md) or the Control Panel. See [Initial Instance Localization](./initial-instance-localization.md) for details.

## Configure Mail

Set up a mail server to communicate with users. See [Configuring Mail](./configuring-mail.md) to learn how.

## Configure File Storage 

Using [Documents and Media](../../content-authoring-and-management/documents-and-media/introduction-to-documents-and-media.md), file attachments, and embedding images in content requires file storage. Visit [File Storage](../../system-administration/file_storage.md) to configure it.

## Install a Search Engine 

Empower users to search your site. Search engines such as Liferay Enterprise Search return results fast. Visit [Installing a Search Engine](../../using-search/installing-and-upgrading-a-search-engine/introduction-to-installing-a-search-engine.md).

## Install Apps From Marketplace 

Improve your sites with themes, connectors, and all kinds applications available on [Liferay Marketplace](https://web.liferay.com/marketplace). [Installing and managing apps](../../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md) has never been easier.

## Configure High Availability 

You'll want eliminate or minimize any site downtime. If a server fails or needs to be shut down for maintenance, it's important to have other servers to handle the requests. You can maximize site availability by configuring multiple servers for Liferay, the search engine, and other components. Learn how to cluster servers at [Clustering for High Availability](./clustering-for-high-availability/clustering-for-high-availability.md).

## Other Setup Options

Here are some additional features to consider setting up:

* [User roles and permissions](../../users-and-permissions/users/understanding-users.md).
* [External Services](../../system-administration/using-the-server-administration-panel/configuring-external-services.md) to generate previews for images, audio files, and video files.
* CDN

You may also need to import data from systems such as [LDAP](../../users-and-permissions/devops/connecting-to-a-user-directory/connecting-to-an-ldap-directory.md). Search this site for other systems you'd like to integrate.

```note::
   See `Maintaining Clustered Installations <docs/dxp/7.x/en/installation-and-upgrades/maintaining-a-liferay-dxp-installation/maintaining-clustered-installations/maintaining-clustered-installations.md>`_ for guidance. 
```

## What's Next 

If you're using Liferay DXP and haven't [activated](./activating-liferay-dxp.md) it, do that first. Then continue with [Localizing Liferay](./initial-instance-localization.md) and the other setup tasks.

After setting up Liferay, you can start developing your site and getting it ready for production.

```important::
   Before going to production, make sure to set up a process for backing up your installation and data. Please see `Backing Up <../maintaining-a-liferay-dxp-installation/backing-up.md>`_ for details.
```