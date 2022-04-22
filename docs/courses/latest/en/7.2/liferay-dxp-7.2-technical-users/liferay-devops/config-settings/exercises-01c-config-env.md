## Configuring Environment Settings

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Update File System Store settings on the Tomcat instance</li>
			<li>Export the updated File System Store configuration</li>
			<li>Add the updated configuration to the Wildfly instance</li>
		</ul>
</div>


#### Update the File System Store in System Settings

*System Settings* are easily migrated from one environment to another. We'll first change the settings of the *Advanced File System Store*.

1. **Go to** _`Control Panel → Configuration → System Settings`_ in the *Menu* for the _liferay-tomcat-1_ service.
2. **Click** on  the _File Storage_ option under the _Platform_ category of the menu.
3. **Click** on _Advanced File System Store_.
4. **Replace** the current path with *content/documents*.
5. **Click** *Save*.

<img src="../images/chapter-2/file-system-store.png" style="max-width: 100%">

#### Export the Updated File Store Configuration

Let's export the changes we've made.

1. **Click** on the *Options* menu for the _Advanced File System Store_ settings.
2. **Click** on *Export*.
3. **Click** *Save* or *Save As* if prompted whether to open or save the file.
1. **Click** on the *Options* menu again.
2. **Click** on *Reset Default Values*.
2. **Click** _Save_.
5. **Find** the *com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config* file in your */Downloads* folder.

<img src="../images/chapter-2/export-all.png" style="max-width: 100%">

#### Copy the Configuration to the Wildfly Configs Directory and Verify Changes

1. **Copy** the *Advanced File System Store* configuration file to the Wildfly server.
	- Copy this file: com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config.
	- Paste it in this directory:
	_liferay/liferay-wildfly/volumes/liferay-wildfly/osgi/configs_.
1. **Go to** _localhost:9090_ in your browser (either open a new tab or new window, keeping the liferay-tomcat instance running).
	- Start the container if it is not already running:
```bash
docker-compose up -d liferay-wildfly
```
2. **Sign in** using your user name and password if you are not already logged in.
3. **Go to** _`Control Panel → Configuration → System Settings`_ in the *Menu*.
4. **Click** on _`File Storage → Advanced File System Store`_ under the _Platform_ section of the menu.

You should see the _content/documents_ path that we just copied over. 

<img src="../images/chapter-2/advanced-files.png" style="max-width: 70%">

Now that we have our configurations migrated to another Liferay instance, how do we roll back to our previous configuration? In this situation, it is actually really simple. All we do is remove the `.config` file we pasted in the configs folder. That's it. Once it's removed, give Liferay a few seconds to see that it has been removed and things are back to normal.

---

#### Bonus Exercises: 

Try the following bonus exercise after completing the main exercises:

Configurations changed via the UI do not affect `.config` files added to our _osgi/configs_ directory.

1. Try to revert to the default _Advanced File System Store_ settings via the UI after having deployed the `.config` changes to the Wildfly instance.
	- What happens to the file in the configs directory?
	- What will happen upon the Wildfly server's restart?
	
