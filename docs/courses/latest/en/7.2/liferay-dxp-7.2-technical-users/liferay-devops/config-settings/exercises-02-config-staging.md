<h2 class="exercise">Exercises</h2>

## Configuring the Platform for Staging 

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Configure the remote live server</li>
			<li>Configure the local staging server</li>
			<li>Verify the configuration and test staging</li>
		</ul>
</div>

In this exercise, we are going to be connecting a Staging server (liferay-staging) to our live cluster (liferay-tomcat-1 and its other services). This will enable Content Creators and Developers to make all the changes they need to on the Staged instance of Liferay, and then push to production whenever they're ready.

#### Configure the Tomcat Server for Remote Live Staging

Let's configure our nodes for Remote Live Staging, starting with our Tomcat instance. We need to make sure both servers trust each other.

1. **Open** the `portal-ext.properties` in your *liferay/tomcat/liferay-tomcat/config* folder with your favorite text editor.
2. **Edit** the line by uncommenting the `tunneling.servlet.shared.secret` around line 112.
	- This key is shared between each server.
3. **Edit** the line by uncommenting the `tunneling.servlet.shared.secret.hex` around line 118.
	- Setting this to true ensures that the secret is read as hexadecimal encoding.
4. **Edit** the line by uncommenting the `tunneling.servlet.hosts.allowed=` around line 128.
	- By leaving this blank after the equals (=) sign, we are allowing any IP to connect with this server as a host. In a production environment, you would want to set this to your host's IP address.
5. **Save** the file.
6. **Open** the `com.liferay.portal.security.auth.verifier.internal.tunnel.module.configuration.TunnelAuthVerifierConfiguration-default` file in your *liferay/devops/tomcat/liferay-tomcat/config* folder with your favorite text editor.
	- The configuration defined in this file allows for a connection to be made between this, our live server, and the staging server. This configuration only needs to be set for the **live server**.
	- Lines 1, 3, and 4 are the default configuration, while line 2 is set to the host server's IP address.


#### Configure the Staging Server for Local Staging

Let's configure our nodes for Remote Live Staging, starting with our Tomcat instance.

1. **Open** the `portal-ext.properties` in your *liferay/devops/tomcat/liferay-staging/config* folder with your favorite text editor.
1. **Edit** the line by uncommenting the `tunneling.servlet.shared.secret` around line 62.
	- This is the same key we added to the Tomcat Server. This key allows the two servers to recognize each other in order to establish a connection.
1. **Edit** the line by uncommenting the `tunneling.servlet.shared.secret.hex` around line 68.
	- Setting this to true ensures that the secret is read as hexadecimal encoding.
1. **Save** the file.

#### Rebuild and Start the Server Containers

1. **Open** a _Terminal_ window at the _liferay/devops/tomcat/_ folder.
2. **Run** the following command:
```bash
$ docker-compose down # This will stop any running containers
```
3. **Build** the liferay-tomcat-1 and liferay-staging containers.
```bash
$ docker-compose build liferay-tomcat-1
$ docker-compose build liferay-staging
```
4. **Start** both containers.
```bash
$ docker-compose up -d liferay-tomcat-1 
$ docker-compose up -d liferay-staging
```

#### Create a New Blank Site on the Remote Live Server

Once both containers have restarted, we need to do a couple more things.

1. **Sign in** to Liferay on the Tomcat server.
    - The Tomcat server is at localhost:8081.
2. **Go to** _`Control Panel → Sites → Sites`_ in the *Menu*.
3. **Click** the *Add* button near the top right.
4. **Choose** _Blank Site_.
5. **Type** *Live Site* to name the new site.
6. **Click** *Save*.
7. **Copy** the Site ID that is generated on the _General_ tab.

<img src="../images/blank_site.png" style="max-width: 100%">

#### Add the Remote Server IP Address to the Staging Server

With our new blank site created, let's finish up.

1. **Sign in** to Liferay on the Staging server.
    - The Tomcat server is at localhost:8085. Make sure to use a different browser from the one where you have your Tomcat instance running.
    - Click through the setup options as necessary.
2. **Go to** _`Site Administration → Publishing → Staging`_ in the *Menu*.
3. **Click** on *Remote Live*.
4. **Type** *liferay-tomcat-1* in the *Remote Host/IP* field.
    - This is the name of the container our remote server is running in. Since both servers are running on the same network on Docker, we need to refer to them by an internally recognized name (i.e., the name of their Container), and not the externally recognized IP address.

<img src="../images/chapter-2/remote-host-ip.png" style="max-width: 100%">

#### Activate Remote Staging for Both Servers

1. **Type**  *8080* into the *Remote Port* field.
    - This is the remote server's port. We use *8080* instead of *8081* because we are connecting between containers internally.
2. **Paste** the Site ID of the blank site we created on _liferay-tomcat-1_ into the *Remote Site ID* field.
3. **Click** _Save_ at the bottom of the page.
4. **Click** *Ok* when prompted to activate Remote Staging.

<img src="../images/remote-staging.png" style="max-width: 100%">

You now have Staging configured! You can configure Page Versioning and what content you want to have staged. Lastly, let's test it to see if it works.

#### Make Changes to the Site to Verify Staging Configuration

1. **Go to** _`Site Administration → Go to Site`_ in the *Menu*.
2. **Click** the *Options* icon at the top right of the *Hello World* widget.
3. **Choose** *Remove*.
4. **Click** the *Add* button to open up the *Add Panel*.
5. **Go to** _`Widgets → Highlighted`_.
6. **Click** to add an *Asset Publisher* to the page.
7. **Click** the *Publish to Live* button.
	- This may take a few moments; wait until the page has refreshed before moving on.
8. **Type** _Pushing Changes to Live Site_ as the _Name_ for our publication.
9. **Click** the _Publish to Remote Live_ button.
10. **Go to** the Liferay instance in your other browser window.
11. **Click** on the _Go to Other Site_ icon to change the site and choose the _Live Site_ we created earlier.
12. **Click** *Go to Site* in the _Site Administration_ panel.

<img src="../images/live-site.png" style="max-width: 100%">

Congratulations! You should see the Hello World widget replaced with an asset publisher. Staging is now properly configured, and the content team can safely add content to the staging server and publish to live when their content is ready to go.

You can run `docker-compose down` to shut down all of your running containers for this exercise section.

---

#### Bonus Exercises: 

Try the following bonus exercise after completing the main exercises:

1. Explore the remote live site after having pushed your content from the staging server to the live server.
	- Can you add new content directly to the live site?
	- Can you add widgets and applications to the pages?
	- Can you edit the content that is already on the page?
