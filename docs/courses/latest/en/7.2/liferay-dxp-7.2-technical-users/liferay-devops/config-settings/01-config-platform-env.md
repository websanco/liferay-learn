Configuring Liferay Settings
============================

## Liferay's Default Settings

Liferay aims to offer the user as much flexibility and control as needed. This applies even in configuring the platform. There are two primary ways to configure Liferay: via the Settings Menus in the user interface or with `properties` files.

#### System and Instance Settings Menus

The simplest way to configure Liferay is through the UI. Configuration options are found by clicking on the _Menu_ button, then on *Control Panel*. Once in the *Control Panel*, an administrator is able to configure many of Liferay's properties and settings. You'll find most of the configuration settings in the *System Settings* options and settings under the *Configuration* section. 

<div class="key-point">
	Key Point: <br/>
	System Settings allows administrators to configure the majority of Liferay's properties for various applications and for Liferay itself right on the platform.
</div>

<figure>
	<img src="../images/system-settings.png" style="max-height: 40%" /> 
	<figcaption style="font-size: x-small">Fig.1 The System Settings menu</figcaption>
</figure>

The settings configured in *System Settings* can be exported as zip files containing `.config` files. `.config` files can also be created by hand and placed in the `osgi/configs` with the other `.config` files. Any settings configured in _System Settings_ are applied across **all** instances, including on a cluster.

<figure>
	<img src="../images/export-settings.png" style="max-width: 100%" />
	<figcaption style="font-size: x-small">Fig.2 Exporting from the System Settings menu</figcaption>
</figure>

*Instance Settings*, unlike *System Settings*, are only applied to a specific instance. Through *Instance Settings*, we're able to provide details such as addresses or phone numbers that correspond with our specific instance. In *Instance Settings*, we can also configure authentication and authentication servers.

<figure>
	<img src="../images/instance-settings.png" style="max-width: 100%" />
	<figcaption style="font-size: x-small">Fig.3 Instance Settings in the Control Panel</figcaption>
</figure>

#### Properties Files

Another method of configuring Liferay settings is through `properties` files. Before doing any configuration, however, we must first understand how Liferay gets the values from properties files. The read order of our properties files is:

1.  `portal.properties`
2.  `portal-ext.properties`
3.  `portal-setup-wizard.properties`

If you've used Liferay for any length of time, you'll be familiar with the `portal.properties` file. This file contains many of Liferay's default configurations along with some documentation. When Liferay starts up, it looks first in the `portal.properties` file. The reference to the file is in:

- `/LIFERAY_HOME/tomcat-[version]/webapps/ROOT/WEB-INF/lib/portal-impl.jar`
     - You can also browse the file online at http://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html

To modify the default values taken from `portal.properties`, we create a file called `portal-ext.properties` in Liferay's home directory. Liferay will read `portal-ext.properties` after `portal.properties` and will override the properties in `portal.properties` with any values we specify in `portal-ext.properties`.

When modifying properties, it's always best to modify the `portal-ext.properties` file. You should avoid modifying the `portal.properties` file at all costs. In order for the server to read the properties in `portal-ext.properties`, either the server must be restarted or `portal-ext.properties` must be modified before the server is started.

The last properties file that Liferay reads is `portal-setup-wizard.properties`. This properties file is created when going through the Basic Setup Wizard and contains information about the administrative user created through the Wizard as well as the directory for Liferay Home.

The properties file that is read last will override the properties of the previously read files (i.e., `portal-ext.properties` will override `portal.properties` and `portal-setup-wizard.properties` will override `portal-ext.properties` and `portal.properties`.)

The recommended way to configure properties in Liferay is through the System and Instance Settings menus in the UI, as those changes are made directly to the database and do not require restarting the platform. Keep in mind that none of the changes made in the UI end up in properties files.

#### Default Data

When you unzip a Liferay Bundle and run it, you'll notice that there is a large amount of default data already on the platform. The default data is there primarily to provide examples and guidance for using features. In addition to the database, there's a default document repository, search indices, and a number of applications. For testing, QA, installation, or demoing, you may need to remove or reset some or all of this data.

The first time you start Liferay, a number of necessary tables and data are generated by a database script. Some applications also generate some of their own data and tables when deployed. These core applications all have their own default data:

- Dynamic Data Lists
- Documents and Media
- Web Content Templates
- Application Display Templates

In a Liferay bundle, there are a few places to look for data and application storage. These locations can be customized and may be different in a manual installation. Default locations can be found for the:

- Hypersonic database
- Document repository
- Core applications
- Feature applications suites
- Custom applications
- Marketplace applications
- Default configuration
- In many cases, the default storage can be moved or reset as needed.

When starting a Liferay instance for testing and demoing, Liferay is pre-configured to connect to a Hypersonic database (hsql). Hypersonic is an embedded Java database and doesn't require a separate server to run. It may be necessary to empty the Hypersonic database to reset an instance for live demos and quick testing. The Hypersonic database is stored in `[LIFERAY_HOME]/data/hypersonic`. This folder can be safely removed *unless you're running with hsql as backend, of course*. If necessary, Liferay can generate the hsql database again from scratch, and it will be empty then.

Liferay ships with an embedded instance of Elasticsearch. This instance's Elasticsearch data is stored in `[LIFERAY_HOME]/data/elasticsearch`. There is storage for both indexing (`indices/`) and Elasticsearch plugins (`plugins/`). Note that an embedded Elasticsearch is *not supported in production systems*, as it can't be tuned independently of Liferay. For a proper production system setup, you'll need to run a separate remote Elasticsearch server. The embedded installation is only fine for quick test- and development environments. For the proper remote setup, you'll configure the remote Elasticsearch server with the storage location (in Docker: The proper volume). Make note of it for your backup. While an index is easy to regenerate if you haven't backed it up, that generation might take significant time, depending on the amount of data that you need to index.

By default, Liferay uses a simple file system storage solution. All documents uploaded to Liferay are stored in a document repository located on a disk. Most production systems will offload this location to a network share, SAN or similar location. The default location is `[LIFERAY_HOME]/data/document_library`. We recommend that you configure a different storage engine than the default, as it is limited by the number of files the underlying filesystem can store in a single directory. Find the alternatives by searching portal.properties for `dl.store.impl` (https://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html)

Applications deployed to Liferay are either standard Java web applications or modules. Modules are installed in the *OSGi Container*, which is by default located in `[LIFERAY_HOME]/osgi`. This is further subdivided based on module type:

- **`core/`:** core modules necessary for proper function of the platform
- **`portal/`:** modules necessary for portal functionality provided by the platform
- **`marketplace/`:** applications and app suites installed by the Marketplace
- **`modules/`:** where Liferay features and your custom applications are installed
- **`configs/`:** the location for default configuration files (`.config`)

Modules in **`modules/`** and **`marketplace/`** can be removed as needed. Don't remove Liferay modules for necessary features. When in doubt, don't remove the module. You can test module functionality by stopping individual modules as necessary without removing them. 

#### Configuring Locales and Time Zones

Within the various properties associated with Liferay are the properties that control the locales and time zones. As soon as you fire up Liferay, there are already a number of languages Liferay is using and even more that are in reserve. When it comes to locales (as well as many other aspects of Liferay), there are three scopes we have to consider:

- Instance-Wide
- Site-Wide
- Individual User

The *Instance Settings*, the *Site Settings*, and the *User Settings* are the three areas where language can be configured. The base languages of Liferay are established by the *Instance Settings*, and the other two settings can use what is given. *User Settings* take precedence over *Site Settings*, and *Site Settings* take precedence over *Instance Settings*, but *Instance Settings* establish what languages can be used.

Time zones can be configured in *Instance Settings* and *User Settings*. A good rule of thumb to remember when configuring your time zones: Set all servers to the same time zone. Preferably, you would set all servers to UTC (Coordinated Universal Time), but it is more important for all of the time zones in all of your servers to be the same than for all of them to be UTC. 

## Configuring Documents and Media

Documents and Media is part of Liferay's Foundation. Documents and Media enables you to store and organize all kinds of files on the platform. 

<div class="key-point">
	Key Point: <br />
	Each site on Liferay has its own Documents and Media repository.
</div>

Documents and Media is also compatible with third-party applications like Sharepoint, Alfresco, and Amazon S3. There are a few ways to look at Documents and Media as well as its several pieces. Documents and Media represents the repository itself, which stores files for each site. You can also use "Documents and Media" to refer to the specific application users access to manage the repository.

#### Document Repository

When you add a document to Documents and Media, Liferay saves:

- The document's binary content itself
- Information about the document based on its contents *(automatically extracted metadata)*
- Additional information about the document added with the document *(metadata)*

Liferay keeps all of the document metadata in the database. The actual document data (binary) is saved in the document repository. The document repository file system is customizable.

There are various stores you can take advantage of:

* **Simple File System Store:** Keeps files in a directory on the server's file system and can be local or mounted. This is the default store.
* **Advanced File System Store:** Stores files on the server's file system using a nested directory structure for better performance. Recommended for managing many files in a more easily scalable manner. Can be local or mounted.
* **DBStore:** Saves files directly to the portal database as a blob. Files over 1GB in size should be stored on the Simple or Advanced File System Stores.
* **S3Store (Amazon Simple Storage):** Stores directly to a bucket on Amazon's Simple Storage System service in the cloud.

Stores can be configured via `portal-ext.properties` or directly on the platform via the UI.

There are many other features you can use with Documents and Media:

- Metadata Sets allow you to add additional information to anything being uploaded.
- Document Types let you standardize metadata based on types that you define.
- Liferay Sync enables desktop access and sync.
- You can link to Google Docs and manage inside Liferay.
- You can use all of these features in custom applications with the Documents and Media API.
- You can integrate with external applications to use features like document conversion, previews, and audio/video playback.

#### Document MIME Types

When using any document repository, there's going to be some concern for document MIME types or content types. The content type can be an image, video, text document, etc., or specifically the format .PNG, .JPG, .AVI, or .DOCX. There are several settings that you can configure based on the Document's MIME type. You can configure how images will be optimized for display on the internet. You can also manage which types will automatically generate previews. For security purposes, you can configure certain types of files to be run through filters or disallow upload.

<div class="key-point">
	Key Point: <br />
	Based on the content type, Liferay can perform a number of actions:
    <ul>
    	<li>You can automatically generate previews.</li>
    	<li>You can manage download behavior.</li>
    	<li>You can extract metadata.</li>
	</ul>
</div>

The primary configuration for content types is found in the Control Panel, under *Configuration -> System Settings*. From there, you can find *Documents and Media Service* under the *Collaboration* tab. There are a large number of settings for defining files related to MIME types for everything ranging from code to compressed files, to multimedia files and more. Configuration in *System Settings* can be overridden with `.config` files.

<figure>
	<img src="../images/docs-media-system-settings.png" style="max-width: 100%" />
	<figcaption style="font-size:x-small">Fig.4 MIME Type Settings</figcaption>
</figure>

You can make edits directly in *System Settings* and add or delete fields there, but the best practice for maintainability or portability of settings is to use `.config` files. At any time, you can click on the contextual menu in the header and choose to *Export All Settings*, but this will only export some core settings and settings that you have changed.

Additional settings can be found in `portal.properties` and overridden in custom properties files. You can review properties in the online reference at http://docs.liferay.com/portal/7.2-latest/propertiesdoc/portal.properties.html. You can also look directly at the `portal.properties` file found in `portal-service.jar` in your Liferay distribution. The additional portal properties configuration for MIME types are:

- **`mime.types.content.disposition.inline`** allows you to specify which file types should be opened in context, while the ones not listed will prompt for download
- **`mime.types.web.images`** is a list of content types that will be optimized for display on the internet
- **`dl.file.entry.raw.metadata.processor.excluded.mime.types`** will exclude all content types listed from automatic metadata extraction
- **`dl.file.entry.preview.audio.mime.types`**, **`dl.file.entry.preview.image.mime.types`**, and **`dl.file.entry.preview.video.mime.types`** will determine which file types trigger automatic audio, image, and video previews

#### Reading Document Metadata

Files have two parts: binary data, the contents of the file, and metadata, information about the file. In some cases, metadata might just be something simple, like the date a file was created. In other cases, like an audio or video file, you might have a whole host of information, ranging from the title to the release date and more. 

<br />
<br />

<div class="key-point">
	Key Point: <br />
	Metadata can be added to all documents added to Liferay.
</div>

If a file has, for instance, existing EXIF data or ID3 tags, Liferay will import the existing metadata to fill its metadata fields. If such information is not available from the file itself, you can add metadata manually. You can also create custom fields to help organize and label any files to meet your business needs.

One example of metadata is the EXIF data that virtually every digital camera writes every time you take a picture. EXIF is a standard for encoding metadata for audio and video files:

[]()             |[]() 
-----------------|-----------------------------
Dimensions       | 2848x4272
Manufacturer     | Canon
Model            | Canon EOS Digital Rebel XSi
Color Space      | RGB
Color Profile    | sRGB IEC61966-2.1
Focal Length     | 35
F Number         | 18
Exposure Program | 2
Exposure Time    | 1/500

Now let's take a look at what Liferay does with that information. After you upload an image, you can view the image and then click on the information icon in the top right of the screen. From there, you can select *Metadata* as a view option, and Liferay will display the automatically extracted metadata. So, if you regularly take pictures with your smartphone or a digital camera, you generate metadata all the time, without even knowing it.

#### Enabling Document Conversion

You can enhance your document management capabilities by connecting to *LibreOffice* or *OpenOffice*. The enhanced document features include:

- Full previews of supported document types
- Conversion to a variety of formats, including PDF and Microsoft Office formats

<figure>
	<img src="../images/sysadmin-document-conversion.png" style="max-width: 100%" />
	<figcaption style="font-size:x-small">Fig.5 Document Conversion</figcaption>
</figure>

To configure Liferay to use *LibreOffice* so that it can generate previews and enable conversions for OpenDocument/MS Office files, you can:

1. Go to the *Control Panel → Configuration → Server Administration*.
2. Click on the *External Services* tab.
3. Check the *Enabled* button under the *OpenOffice* integration heading.
4. Click *Save*.
5. Set the port for Open Office.
6. Go to *Site Administration → Content → Documents and Media* in the *Menu*.

Next, you need to start LibreOffice as a headless server to receive conversion requests.

An example configuration you could use with LibreOffice uses the command:
```bash
soffice --headless --accept="socket,host=127.0.0.1,port=8100;urp;" --nofirststartwizard
```
This starts up LibreOffice as a server (`headless`), accepting incoming connections from port `8100` on `localhost`.

In addition to generating document previews and allowing users to view documents in their browsers, *LibreOffice* integration allows users to convert documents to and from a variety of file types. For example, suppose you had an MS office file stored in the *Documents and Media library*: *LibreOffice* integration allows users to download ODT or PDF versions of these documents. This saves the author of the file the hassle of having to manually perform various conversions and having to upload multiple versions of the same file to Liferay; of course, this also saves space in your file repository.

## Configuring Application Settings

Liferay has several contexts in which you can configure an application. The first is the configuration provided by an application when it is added to a page.

<figure>
	<img src="../images/configuring-applications-portlet-config.png" style="max-width: 80%" />
	<figcaption style="font-size:x-small">Fig.6 Application settings on the page</figcaption>
</figure>

Some applications also provide a site-level configuration interface in the Control Panel.

<figure>
	<img src="../images/configuring-applications-site-config.png" style="max-width: 80%" />
	<figcaption style="font-size:x-small">Fig.7 Site-level application settings</figcaption>
</figure>

In addition, some applications can be configured through *System Settings*.

<figure>
	<img src="../images/configuring-applications-system-settings.png" style="max-width: 80%" />
	<figcaption style="font-size:x-small">Fig.8 Application settings in the System Settings menu</figcaption>
</figure>

Other applications are still in the Control Panel and `portal.properties`.

<div class="key-point">
	Key Point: <br />
	A System Administrator will not likely be concerned with day-to-day settings and configuration of applications, but they may still need to configure the global default behavior for applications.
</div>

Because of this, we'll be focusing less on the configuration of individual applications on a page and more on system-level configuration.

There are three application suites in Liferay:

- *Web Experience Management* contains the basic tools for building a website. _Web Experience Management_ includes applications like Web Content, Navigation, and Search.
- *Collaboration* contains tools for connecting users and encouraging community-driven content. _Collaboration_ includes applications like Blogs, Forums, and the Wiki.
- *Business Productivity* contains essential tools for businesses, professional websites, and intranets. _Business Productivity_ includes applications like Forms and Kaleo Workflow.

Base configuration for these applications can be found in _`Control Panel → Configuration → System Settings`_.

There are several scopes for the settings that you'll configure through *System Settings*:

- **System** settings contains details about how the app works behind the scenes, like how often a display portlet should check for new entries or what types of files it will accept for upload. These settings are not available to override for an individual application instance.
- **Default Configuration for All Sites** manages additional settings at a global level. Often these are configurations that were formerly found in `portal.properties`.
- **Default Configuration for Widgets** sets the global default for a widget.

For example, if you wanted to set a default article to appear any time a Web Content Display was added to a page, you would open the menu, go to _`Control Panel → Configuration → System Settings`_, and choose the *Web Content* settings. Click on the *Web Content Display* tab on the left, and then enter the *Article ID* in the correct spot.

You can save the settings to apply them to your current instance, but when you make a lot of changes, you'll also want to be able to preserve that configuration. 

#### Config Files

You can export and manage any configuration changes outside of Liferay using `.config` files. From the *System Settings* root, click on the context menu in the top right, and select *Export All Settings.* This will download a zip file with all of your configuration changes packaged in `.config` files. 

<div class="key-point">
	Key Point: <br />
	<code>.config</code> files can be deployed on any running Liferay DXP 7.2 instance so that you can easily back up and manage system and application settings across multiple instances.
</div>


The `.config` files that you download will be named based on the specific setting that they are configuring. If you change multiple settings for different applications at different scopes, you can expect to have many `.config` files in the `.zip` when you export. The name of each file will indicate the application and scope. For example, with the *Default Display Style* for Dynamic Data Lists, the `.config` file will look like this: `com.liferay.dynamic.data.lists.form.web.configuration.DDLFormWebConfiguration.config`

- Inside the file, you can see the details of whatever setting you changed for that application at that scope. For this file, we only have one line of configuration:
```conf
defaultDisplayView=list
```

From here, we could manually change this to a different value or we could change it through the UI and re-export. If we need to apply this configuration to another Liferay instance, we can simply deploy it by copying it into Liferay's `/osgi/modules` folder.

The App Manager, found in _`Control Panel → Applications → App Manager`_, is your final destination for application management. By final destination, we mean that this is where you go to deactivate and delete applications that are currently installed. Always be careful when doing this, as some applications may have dependencies on others, and removing one could render some features unusable. As a best practice, you should *Deactivate* anything that you plan on deleting and test it before you finally delete it.

#### Configuring for Environment-Specific Settings

Whatever your environment (Dev server, UAT, Production), Liferay makes it easy to carry your settings from one environment to another.

In some cases, restoring previous configurations is not as simple as removing an imported *.config* file. The folders that are created by Liferay are not deleted by Liferay when we remove the *.config* file. Although the folders can't be deleted on their own, removing a *.config* file does restore the previous default values for the storage of Liferay's Document Library. In your own cluster/instances of Liferay, be sure to test the *.config* files that you deploy and undeploy to ensure that the settings are applied and removed.

Default configurations can also be restored via _System Settings_. Typically, using *System Settings* to restore our default configurations should only happen when we've made all of our changes in *System Settings*. If we have made changes to our instance using a configuration file, it's best to stay consistent and use the configuration file to make any additional changes.

*Instance Settings*, the separate entry in the Control Panel and not to be confused with the instance-wide scope of *System Settings*, are settings specific to our current instance. *Instance Settings* configures how to authenticate into that specific instance, virtual host, and other instance-specific settings. Unfortunately, there isn't an easy way to migrate the settings set in *Instance Settings*.

Many other configurations, including some found in *Instance Settings* and *System Settings*, can be configured through a portal-ext.properties. But, as discussed earlier, this comes with the price of requiring a restart.

#### Liferay Marketplace

Liferay Marketplace is your hub for getting new Liferay applications and expanding the features of your site. You can find Liferay-developed applications, including some that are exclusive to Digital Experience Platform subscribers, as well as applications developed by our partners and community members. You can also put your own applications up on Marketplace to be shared with or sold to other Liferay users.

<figure>
	<img src="../images/marketplace-marketplace.png" style="max-width: 100%" />
	<figcaption style="font-size:x-small">Fig.9 Liferay Marketplace</figcaption>
</figure>

You can take advantage of hot deploy to install applications directly to your running Liferay instance. Once you've connected the correct account and purchased the applications you want, you can view and install those applications from the _`Control Panel → Apps → Purchased`_. Applications can also be manually downloaded and deployed.

#### Hot Deploy

Liferay supports the installation of two major types of applications:
- Modules
- WAR files

Both can be installed through one mechanism, the *hot deploy* folder. Two major types of deployments are used in various environments:
- Hot
- Cold

The standard practice is to use Liferay's *hot deploy* feature. 

<div class="key-point">
	Key Point: <br />
	Hot deploy is a common feature among different app platforms and works as follow:
	<ul>
		<li>A designated deployment folder is created.</li>
		<li>The app listens for changes in that folder.</li>
		<li>When a new file is dropped in the folder, the parent app picks it up and installs it.</li>
	</ul>
</div>

Liferay's default *hot deploy* folder is established as `[LIFERAY_HOME]/deploy`. The *hot deploy* folder can be used for both WAR files and modules. The *hot deploy* folder is set as a default in `portal.properties`. Like other properties, this can be overridden in `portal-ext.properties`. The frequency of polling can be set as well. To override these settings, set the following properties:

```preferences
auto.deploy.deploy.dir=/path/to/deploy
auto.deploy.interval=3000 #time in milliseconds
```

You can also completely disable the *hot deploy* mechanism by adding the following:

```preferences
auto.deploy.enabled=false
```

There are two types of packages you can deploy in Liferay 7 - JAR and WAR. An OSGi Module packaged as a JAR will be picked up by Liferay and deployed in the OSGi Container. A WAR file will be picked up, "wrapped" as a module, and then deployed. By default, packages are picked up using Liferay's hot deploy directory. If needed, you can override this default:

```properties
auto.deploy.[SERVER].dest.dir 
```

`[SERVER]` is where your Java server type goes.

#### The OSGi Container

When Liferay's *hot deploy* mechanism picks up a module, it installs it in the *OSGi Container*. The *OSGi Container* is built on top of OSGi technology. The default location for the *Container* is `[LIFERAY_HOME]/osgi`. 

<div class="key-point">
	Key Point: <br />
	All deployed modules end up in the default location of <code>osgi/modules</code>. 
</div>

You can modify these locations through the following properties:

```properties
module.framework.base.dir=/path/to/
module.framework.auto.deploy.dirs=[comma-delimited list of locations]
```

Like the *hot deploy* folder, you can also change the frequency of directory polling:
```properties
module.framework.auto.deploy.interval=5000 #time in milliseconds
```

If you change the default locations of the *OSGi Container* or the deployment folders, you'll need to set these properties accordingly.

When a module is dropped into the *hot deploy* folder, or any of the *OSGi Container* deploy folders, it gets installed in the *Container*. Installation happens through the *FileInstall* module from Apache. This installation is performed automatically, and the modules are started upon installation. Most of the settings for the *FileInstall* module can be configured.

Since *FileInstall* is itself an app in the *OSGi Container*, we can configure it through *System Settings* in the Control Panel. You'll find *FileInstall* options under _`Control Panel → Configuration → System Settings → Other → org.apache.felix.fileinstall`_.

The most important settings you may want to change are:
- **Poll interval:** how often the *FileInstall* module polls the directory for new modules to install
    - This is separate from the *hot deploy* directory polling.
- **Log level:** Set it to a higher number for more log output, lower for less.
- **Start new bundles?:** Set it to *false* if you don't want new modules to start immediately.
    - Modules that aren't started will either be started on demand or when Liferay restarts.
- **Filename filter:** Use this to filter for file names you predetermine.
    - This allows you to limit what modules will be installed based on a file name pattern.

<div class="summary"><h3>Knowledge Check</h3>

<ul>
	<li>The simplest way to configure Liferay is through the _______________________________________ menu in the _____.</li>
	<li>Liferay's settings can also be configured through ___________________________ files.</li>
	<li>The read order of ___________________________ files is:</li>
	<ol>
		<li>___________________________________________</li>
		<li>____________________________________________________________</li>
		<li>________________________________________________________________________________</li>
	</ol>
	<li>Liferay's _______________________________________________ provides the ability to store and organize files on the platform.</li>
	<li>Each _____________ on Liferay has its own _______________________________________________.</li>
	<li>Liferay can also be integrated with ____________________________________________________________.</li>
	<li>Liferay supports the installation of two major types of applications: </li>
	<ul>
		<li>_________________________</li>
		<li>_________________________</li>
	</ul>
	<li>When Liferay's _____________________________________________ mechanism picks up a module, it installs it in the ___________________________________________.</li>
	<li>Users can ______________________ System Settings from one environment to another by ____________________________________________ from one environment and _______________________________ them to another environment.</li>
</ul>
</div>
