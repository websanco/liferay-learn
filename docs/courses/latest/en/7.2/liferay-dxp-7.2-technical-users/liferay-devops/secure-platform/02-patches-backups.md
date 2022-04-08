## Managing Fixes and Backups

Keeping your platform up-to-date and patched is important for:
- Security
- Performance

Liferay regularly provides updates to subscribers to ensure that security fixes are installed and performance improvements made available. There are multiple types of fixes and generally multiple channels of delivery.

<div class="key-point">
Key Point:<br />
Liferay generally provides:
<ul>
	<li> <b>Hotfix</b> packages, which install improvements and bug fixes in your Liferay installation.
	<li> <b>Security fixes</b>, which are important fixes that are vital to your system integrity. These should always be installed.
	<li> <b>Fix packs</b>, which bundle a collection of hotfixes and security fixes so you can bring an instance up-to-date quickly.
</ul>
</div>

These improvements and more can be delivered through two main channels.

The two basic mechanisms Liferay uses for updating the product and installing patches are Liferay Connected Services and the Patching Tool. The preferred method is always to use *Liferay Connected Services* whenever possible. When the environment or policy doesn't allow for *Liferay Connected Services* to be a viable solution, the patching tool works well.

Liferay Connected Services is a new online platform that offers a set of tools and services such as: Fix pack management, where you can easily manage and install fix packs across several servers and clusters.

<img src="../images/hardening-lcs-fixpacks.png" style="max-height:30%;" />

Metrics: Various metrics in a simple and clean format: page visits, portlet load times, memory metrics

<br />

<img src="../images/hardening-lcs-metrics.png" style="max-height:30%;" />

<br />

Online Dashboard and Alerts: Access all the information about your different environments from a single place. This is a dedicated alerts section where you will be notified when there are new fix packs available, when servers are down, or when there are configuration issues.

<br />

<img src="../images/hardening-lcs-online-dashboards.png" style="max-height:30%;" />

<br />

*Liferay Connected Services* is a service available to all subscribers that allows you to remotely manage, update, and get info on your running instances. Whenever new fixes are available, you'll see them in *Liferay Connected Services* and in email communication.

The Liferay Support Team regularly creates and releases fixes to handle various platform issues. If you're unable to use *Liferay Connected Services*, then you can download these fixes manually and install them. These are shipped in zipped packages usually referred to as fix packs. Fix packs typically contain multiple fixes in one package. Customers can download fix packs from Liferay's website or receive them directly from support. Fix packs are usually installed with the **Patching Tool**, which is included in every Liferay bundle.

<div class="key-point">
Key Point:<br />
The Patching Tool provides many benefits:
<ul>
<li> Dedicated and continuously updated tool for patching
<li> Auto-detection of working environment
<li> Easy to install or revert fix packs
<li> Shows occasional patch (fix pack) collisions
<li> Provides two levels of patching:
	<ul>
    	<li> In case of binary patching, the deployed portal application is patched (default mode).
    	<li> In case of source patching, it is possible to debug the modified source files.
	</ul>
</ul>
</div>

*Make sure to learn about the Patching Tool and install the latest fix packs when working on a project.*
For more on patching, you can check out https://help.liferay.com.

### Restoring From a Backup {#restore}

<div class="key-point">
Key Point:<br />
When backing up Liferay, we have three main styles:
<ul>
	<li> <b>Hot backups</b> are essentially live-running backups of the Liferay instance with an up-to-date database.
	<li> <b>Warm backups</b> are backups where the servers are mostly configured and the database needs to be imported.
	<li> <b>Cold backups</b> are backups where the data may be current, but all the servers need to be set up, configured, and the data imported.
</ul>
</div>

We'll go ahead and look hands-on at the middle-of-the-road approach to restoration from a **warm backup**.

Once your site is running, you should set up proper backup procedures in case of a catastrophe. Liferay is not much different from any other application running in your application server, and fits in well with most, if not all, sound backup procedures.

<br />

<div class="key-point">
Key Point: <br />
There are some specific components of Liferay that need to be backed up on a regular basis:
<ul>
	<li>Source code</li>
	<li>Database</li>
	<li>Default Data</li>
	<li>Configuration Files</li>
	<li>Apps & Modules</li>
	<li>Search Indexes</li>
	<li>File Repository</li>
</ul>
</div>

**Source Code**: If you have extended Liferay or developed custom
plugins, these should be stored in a source control repository, e.g.:
- Git
- Subversion
- CVS
- Mercurial

Of course, this source code repository should also be backed up on a regular basis. We assume that you keep track of the *exact version* of your code that is running on the server - e.g. through automatically building your production images with a CI server. Our example instance doesn't have any custom code.

**Search indexes** must be backed up individually and should be backed up at the same time as the corresponding data. Alternatively, you can reindex after restore: If indexing is quick enough (typically when not a lot of data is there to be indexed) this is a good option to simplify the backup and restore operations.

<div class="note">
Note: Embedded Elasticsearch is not supported and your indexing server should run in a separate VM. There are options to use Elasticsearch and SOLR as external indexing servers.
</div>

The **File Repository** must be saved regularly when using:
- Shared file system (e.g., SAN or NAS)
- DBStore (handled during database backup)

Liferay's **database** stores most of the information that Liferay needs to run, as well as all of the user accounts and data for the platform. The database is the most important component that needs to be backed up. If your database vendor supports it, you can back it up live. Otherwise, you can do a dump of the database to a text file and then back up the exported file. Backup can be done using the tools provided by your database vendor (mysqldump, pg_dump utility, Oracle data pump, exp/imp utility).

All three of the above should be backed up at the same time. This avoids stale data in the backups of the search index, database, and document library.

**Apps and modules**: In case you install extra applications from third party vendors or Liferay's marketplace: Remember that they'll have to be restored as well. The same principles as with your custom code apply: Hopefully your CI server will make sure they're included in your production image.

It's important to test your backup system. You should only be confident that a backup really is a backup if you've demonstrated that you're able to use it to restore a fully-functional system on a new computer. Too often, backups are dutifully taken, only to discover too late that vital parts are missing. This discovery is typically made in disaster situations; you should make sure that such discoveries only happen during testing.

Let's walk through an example of how you could restore from a backup.

#### Restore the Database

1. Open a _Terminal_ (Mac or Linux users)/_Powershell_ (Windows users) window.
2. Go to your database backup directory.
3. Run the command to import the database dump, for example:
```bash
$ mysql recovery < liferay_warm_09142016.sql
```

Wait as the import finishes. It's always important to back up all Liferay configuration files (the `portal-ext.properties` file from Liferay Home and other folders and all configuration files from `/osgi/configs`).

<div class="note">
Note: If you are implementing containerization (such as we have been using in the course exercises with Docker), it is not necessary to back-up the configuration files in this way. Instead make sure that you are able to build the same image again and run it in a different environment.
</div>

#### Restore the Liferay Configuration

1. Go to the configuration backup folder.
2. Copy the configuration `.cfg`/`.config` files.
3. Copy the properties over, for example:
```bash
$ sudo cp portal-ext.properties /opt/recovery/
```

Liferay stores configuration files, search indexes, and cache information in a folder called `/data` in Liferay Home by default. If you're using the *File System Store* or the *Advanced File System Store*, the Documents and Media repository is also stored here by default. It's always important to back up your `/data` folder. The files that make up Liferay's OSGi runtime are stored in a folder called `/osgi` in Liferay Home. This folder contains JAR files for all of the apps and modules that you've deployed to Liferay and other required JAR files, configuration files, and log files. It's also important to back up your `/osgi` folder. We don't have any custom modules or apps to restore.

#### Restore the Documents

1. Go to the backup document store.
2. Copy the old document store over, for example:
```bash
$ sudo rsync -r document_libary /opt/recovery/data/
```

**If possible, back up your entire application server and Liferay Home folder**. This allows you to revert to a working instance quickly in the event of an issue. This was already handled for us. You should be able to start up your recovered system using the Tomcat startup script:
```bash
$ sudo /opt/recovery/tomcat/bin/catalina.sh run
```
Or you can use the `startup.bat` file if you're on Windows.

This process will depend on the infrastructure you're using. See this documentation for further details: https://dev.liferay.com/discover/deployment/-/knowledge_base/7-1/backing-up-a-liferay-installation.

<div class="summary"><h3>Summary</h3>
<ul>
	<li>Liferay generally provides:</li>
	<ul>
		<li>__________________________________________________: packages that install improvements and bug fixes in your Liferay installation</li>
		<li>__________________________________________________________: important fixes that are vital to your system integrity and should always be installed</li>
		<li>__________________________________________________: collections of hotfixes and security fixes used to bring an instance up-to-date quickly</li>
	</ul>
	<li>The two basic mechanisms Liferay uses for updating the product and installing patches are __________________________________________________ and the ______________________________________________________</li>
</ul>
</div>
