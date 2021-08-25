Setting Up Liferay
==================

.. |checkbox1|   unicode:: U+2611 .. Checked Box

.. |checkbox2|   unicode:: U+2610 .. Unchecked Box

.. toctree::
   :maxdepth: 3

   setting-up-liferay/activating-liferay-dxp.md
   ../../system-administration/configuring-liferay/virtual-instances/instance-configuration.md
   setting-up-liferay/initial-instance-localization.md
   setting-up-liferay/configuring_mail.rst
   ../../system-administration/configuring-liferay/virtual-instances/users.md
   ../../system-administration/file_storage.rst
   ../../system-administration/file-storage/enabling-antivirus-scanning-for-uploaded-files.md
   ../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md
   securing-liferay/securing_liferay.rst
   maintaining-a-liferay-dxp-installation/backing-up.md
   ../../system-administration/installing_and_managing_apps.rst
   setting-up-liferay/using-a-cdn.md
   setting-up-liferay/clustering-for-high-availability.md
   setting-up-liferay/tuning-liferay.md
   setting-up-liferay/tuning-your-jvm.md

After `installing Liferay <./installing_liferay.html>`_, configure it to meet your needs. Here are common setup tasks.

Setup Tasks
~~~~~~~~~~~

.. csv-table::
   :header: "Initial Setup Tasks", |checkbox1|
   :widths: 4, 1
   :align: left

   `Activate Liferay <#activate_liferay>`_ (Subscription Required), |checkbox2|
   `Configure Your Instance`_, |checkbox2|
   `Localize Your Instance`_, |checkbox2|
   `Configure Mail`_, |checkbox2|
   `Configure Users`_, |checkbox2|
   `Configure File Storage`_, |checkbox2|
   `Install a Search Engine`_, |checkbox2|
   `Secure Liferay`_, |checkbox2|
   `Configure Backups`_, |checkbox2|
   **Other Setup Tasks**
   `Install Apps From Marketplace`_, |checkbox2|
   `Configure Roles and Permissions`_, |checkbox2|
   `Add Custom Fields`_, |checkbox2|
   `Integrate with Existing Systems`_, |checkbox2|
   `Configure Media File Previews`_, |checkbox2|
   `Configure a CDN`_, |checkbox2|
   `Configure High Availability`_, |checkbox2|
   `Tune Liferay`_, |checkbox2|

Each task above links to a description below that includes references to applicable articles. The first tasks are essential for any production-ready Liferay instance. Examine the *Other Setup Tasks* that also apply to your system. As you complete tasks, make sure to revisit the checklist above.

Activate Liferay
~~~~~~~~~~~~~~~~

`Subscribers`

If you're using Liferay DXP, `apply your activation key <./setting-up-liferay/activating-liferay-dxp.md>`_.

Configure Your Instance
~~~~~~~~~~~~~~~~~~~~~~~

Configure your virtual instance's basic appearance, contact information, Terms of Use, and essential pages, including landing pages and logout pages. `Instance Configuration <../system-administration/configuring-liferay/virtual-instances/instance-configuration.md>`_ explains how to do it all.

Localize Your Instance
~~~~~~~~~~~~~~~~~~~~~~

Configure your instance's locale and time zone. See `Initial Instance Localization <./setting-up-liferay/initial-instance-localization.md>`_ for details.

Configure Mail
~~~~~~~~~~~~~~~~

`Set up a mail server <./setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md>`_ to communicate with Users. Then configure your `email settings <../system-administration/configuring-liferay/virtual-instances/email-settings.md>`_, including your email sender and message templates for email verifications, password resets, and password changes.

Configure Users
~~~~~~~~~~~~~~~

After you `understand Users <../users-and-permissions/users/understanding-users.md>`_, configure them for your instance.

* Enable/disable User fields
* Add any necessary `custom User fields <../users-and-permissions/users/adding-custom-fields-to-users.md>`_
* Specify whether to allow strangers to create accounts
* Define `User authentication <./securing-liferay/authentication-basics.md>`_
* Configure automatic association of Users to specific Sites, `Roles <../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md>`_, and `User Groups <../users-and-permissions/user_groups.html>`_

Visit `Users <../system-administration/configuring-liferay/virtual-instances/users.md>`_, `User Authentication <../system-administration/configuring-liferay/virtual-instances/user-authentication.md>`_, and `Adding Custom Fields For Users <../users-and-permissions/users/adding-custom-fields-to-users.md>`_ for details.

Configure File Storage
~~~~~~~~~~~~~~~~~~~~~~

Using `Documents and Media <../content-authoring-and-management/documents-and-media/sharing-documents-and-media.md>`_, file attachments, and embedding images in content requires file storage. Visit `File Storage <../system-administration/file-storage/configuring-file-storage.md>`_ to configure it.

After setting up file storage, consider enabling antivirus file scanning. See `Enabling Antivirus Scanning for Uploaded Files <../system-administration/file-storage/enabling-antivirus-scanning-for-uploaded-files.md>`_ to learn how.

Install a Search Engine
~~~~~~~~~~~~~~~~~~~~~~~

Empower Users to search your site. Search engines such as Liferay Enterprise Search return results fast. Visit `Installing a Search Engine <../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md>`_.

Secure Liferay
~~~~~~~~~~~~~~

It's really important to secure Liferay too. Go to `Securing Liferay <./securing-liferay/securing-liferay.md>`_ to learn more.

Configure Backups
~~~~~~~~~~~~~~~~~

As you develop your virtual instance make sure to back it up. See `Backing Up <./maintaining-a-liferay-dxp-installation/backing-up.md>`_ for guidance.

Other Setup Tasks
~~~~~~~~~~~~~~~~~

The tasks described above are typically (but not always) completed first. In any case, you may need to perform the following applicable tasks soon too.

Install Apps From Marketplace
-----------------------------

Improve your sites with `themes <../getting-started/changing-your-sites-appearance.md>`_, connectors, and all kinds applications available on `Liferay Marketplace <https://web.liferay.com/marketplace>`_. `Installing and managing apps <../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md>`_ has never been easier.

Configure Roles and Permissions
-------------------------------

Use `Roles <../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md>`_ to categorize all of your Users. Define `Permissions <../users-and-permissions/roles-and-permissions/defining-role-permissions.md>`_ for the activities Users perform in your Instances and Sites.

Add Custom Fields
-----------------

Entities in your Instances and applications can be customized with additional fields. This may include adding fields for Users. For details, see `Adding Custom Fields <../system-administration/configuring-liferay/adding-custom-fields.md>`_ and `Adding Custom Fields For Users <../users-and-permissions/users/adding-custom-fields-to-users.md>`_.

Integrate with Existing Systems
-------------------------------

Liferay can integrate with some other systems. For example, if you're using a directory such as LDAP, you can import Users from it. `Connecting to an LDAP Directory <../users-and-permissions/devops/connecting-to-a-user-directory/connecting-to-an-ldap-directory.md>`_ demonstrates the process. Please search this site for integrations you may need.

Configure Media File Previews
-----------------------------

Set up external services for your site's images, videos, and audio files. See `Configuring External Services <../system-administration/using-the-server-administration-panel/configuring-external-services.md>`_ to learn how.

Configure a CDN
---------------

Deliver static content faster via a Content Delivery Network (CDN). Visit `Using a CDN <./setting-up-liferay/using-a-cdn.md>`_ for more information.

Configure High Availability
---------------------------

You'll want eliminate or minimize any site downtime. If a server fails or needs to be shut down for maintenance, it's important to have other servers to handle the requests. You can maximize site availability by configuring multiple servers for Liferay, the search engine, and other components. Learn how to cluster servers at `Clustering for High Availability <./setting-up-liferay/clustering-for-high-availability.md>`_.

Tune Liferay
------------

Tune Liferay's JVM, connection pools, and more for optimal performance. See `Tuning Liferay <./setting-up-liferay/tuning-liferay.md>`_ and `Tuning Your JVM <./setting-up-liferay/tuning-your-jvm.md>`_ for details.

What's Next
~~~~~~~~~~~

If you're using Liferay DXP and haven't `activated <./setting-up-liferay/activating-liferay-dxp.md>`_ it, do that first. Then continue with `Instance Configuration <../system-administration/configuring-liferay/virtual-instances/instance-configuration.md>`_ and the other `Setup Tasks`_ listed above.

.. important::
  Before going to production, configure a process for backing up your installation and data. Please see `Backing Up <./maintaining-a-liferay-dxp-installation/backing-up.md>`__ for details.
