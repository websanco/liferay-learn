# Packaging Fragments

Coming Soon!

<!--

#### Exercise Goals

* Import the Fragments created in the last exercise into the platform
* Export a Collection

</div>

#### Start the Liferay-Tomcat Bundle
1. **Go to** the Tomcat server's `bin` directory:
	* Windows: _C:\liferay\bundles\liferay-dxp-[version]\tomcat-[version]\bin_ in the file manager
	* Mac/Linux: _[userhome]/liferay/bundles/liferay-dxp-[version]/tomcat-[version]/bin_ using the _Terminal_
* **Start** the Tomcat Server:
	* Windows: **Double-click** on the `startup.bat`.  
	* Mac/Linux: **Run** `./catalina.sh run` in your _Terminal_.
* **Go to** `localhost:8080` in your browser if you're not already there.
* **Sign in** to the _Livingstone Hotels & Resorts_ platform. 

<div class="page"></div>

#### Import the Fragment onto the Liferay Platform
1. **Open** _Terminal/Command Prompt_ if it isn't opened already.
* **Go to** the _livingstone-fragments_ project folder in the _Command Line/Terminal_.
	* Windows: _C:\liferay\livingstone-fragments_
	* Unix Systems: _[userhome]/liferay/livingstone-fragments_
* **Run** _npm run import_.
* **Press** _Enter_ to accept the default _`localhost:8080`_ _host & port_.
* **Type** in the administrator Username for the Platform.
  * If you see your admin email as the address, simply hit enter to accept.
* **Type** your password.
* **Choose** the default _Company ID_.
* **Choose** the default Site _Group ID_.
  * If you installed the platform in module one, it should be _Livingstone Hotels & Resorts_.
* **Go to** _`localhost:8080`_ in your browser.
	* Your Liferay instance must be running in this port. If it is not, start it up and wait until it is running before continuing this exercise.
* **Go to** _`Site Administration → Design → Fragments`_ to verify that it imported.

<br />

<div class="note">
  Note: There is a chance that the import command will not work. If you are running into this issue, you can simply add a new Collection and Fragment in <code>Menu → Site Builder → Page Fragments</code> and copy the html code to the new fragment.
</div>

#### Export a Collection
1. **Click** the _options_ icon next to the Livingstone Front Page title.
* **Choose** _Export_ in the drop-down.
* **Click** _Ok_ in the pop-up.

<br />   

---

#### Bonus Exercises
1. Create a new Collection in the Fragments Editor. Export the Collection and import it into a new instance.

-->