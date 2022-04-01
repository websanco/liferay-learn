## Configuring Document Settings

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Create a new document MIME type</li>
			<li>Export and deploy the configuration on a separate container</li>
		</ul>
</div>

#### Find the Documents and Media Settings in System Settings

1. **Open** the *Menu* for _liferay-tomcat-1_.
2. **Go to** _`Control Panel → Configuration → System Settings`_.
3. **Find** the *Content and Data* section near the top of the _System Settings_ menu.
4. **Click** on *Documents and Media*.

#### Create a New MIME Type for Markdown Files

1. **Click** on _Service_ on the left-hand side.
2. **Click** the "+" next to the top entry.
3. **Create** a new *MIME type* for `text/markdown`.
4. **Click** *Save* at the bottom of the page.

<img src="../images/text-mark.png" style="max-height: 40%">

#### Export the New MIME Type Configuration

Let's take a look at how to export our settings.

1. **Click** on the *Options* button next to *Service* at the top of the page.
2. **Choose** *Export* from the options that appear.
3. **Click** *Save* or *Save As* if prompted by your browser.
4. **Click** *Ok*.

<img src="../images/chapter-2/export-service.png" style="max-width: 100%">

#### Find the Exported Configuration and Add it to the Wildfly Volume

1. **Go to** your *Downloads* folder.
    - You should see your custom _Documents and Media_ configuration file: `com.liferay.document.library.configuration.DLConfiguration.config`
2. **Copy** the configuration file to the _configs_ folder in the mounted volume for the Wildfly server:
    - _liferay/liferay-wildfly/volumes/liferay-wildfly/osgi/configs_
    - You may see some configuration files already in the folder.

#### Start the Wildfly Container for Testing the Configuration

1. **Start** the Liferay Wildfly instance to test the settings:
	- Open a new _Terminal_.
	- Run the following command from your _wildfly_ folder:
```bash
docker-compose build liferay-wildfly #To create a container from the image
docker-compose up -d liferay-wildfly #To start the container
```
	- Restart the container if it is currently running:
```shell
docker-compose restart liferay-wildfly
```
2. **Go to** *localhost:9090* in your web browser.
3. **Sign in** to Liferay.
    - User Name: *test@liferay.com*
    - Password: *test*

#### Confirm the Imported Configuration is on the Wildfly Server

1. **Open** the *Menu*.
2. **Go to** _`Control Panel → Configuration → System Settings`_.
3. **Click** on *Documents and Media*.
4. **Click** on _Service_.
    - You should see that the MIME type configuration has been added to the Wildfly instance.

<img src="../images/wildfly-mark.png" style="max-width: 100%">

---

#### Bonus Exercises:

Try the following bonus exercise after completing the main exercises:

You can manually edit any `.config` file or even create one from scratch provided you use the correct naming scheme.

1. Manually edit the _com.liferay.document.library.configuration.DLConfiguration.config_ file we exported from the Tomcat instance.
	- Add new MIME Types following the structure generated when we exported the file.
```properties
multimediaFileMimeTypes=["audio","image","video"] 
codeFileMimeTypes=["text/asp","text/markdown","text/css","text/ecmascript","text/html","text/javascript","text/x-c","text/x-fortran","text/x-java-source","text/x-pascal","text/x-script.perl","text/x-script.perl-module","text/xml"] 
```
2. Add the file to the configs directory for the Wildfly instance and confirm the changes on the platform.
