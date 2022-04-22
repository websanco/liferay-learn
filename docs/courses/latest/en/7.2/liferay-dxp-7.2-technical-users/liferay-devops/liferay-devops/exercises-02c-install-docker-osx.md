## Set Up Docker on OSX

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Install the latest Community Edition of Docker locally</li>
			<li>Pull and run a simple image from Docker Hub</li>
		</ul>
</div>
<br />

<div class="note">
	Note: You'll need macOS 10.10.3 or newer and a minimum of 4GB of RAM for _Docker Desktop for Mac_ to launch.
</div>

#### Download the Docker Desktop Installer

We'll start by downloading the _Docker Desktop for Mac_ installer.

1. **Go to** https://hub.docker.com/editions/community/docker-ce-desktop-mac in your web browser.
	* You'll have to create a Docker Hub account and login in before you can download the installer.
1. **Click** the _Please Login to Download_ button.
1. **Click** the _Create Account_ link at the bottom right.
1. **Type** in the required information to create a new account.
1. **Click** _Sign Up_.
	* You'll be required to confirm the sign up e-mail address via e-mail confirmation. 
1. **Go to** Docker Store page for the Docker Desktop for Mac download if you aren't automatically redirected there after sign up.
1. **Click** the _Please Login to Download_ button again and enter your account information to sign in.
	* Now you should be able see the download option.
1. **Click** on the _Get Docker_ button.
	<img src="../images/chapter-1/osx-download-docker.png" />

The installer will begin downloading.

#### Extract and Run the Docker Installer

1. **Go to** your _Downloads_ folder to find the installer.
1. **Double-click** the `Docker.dmg` image to run the installer.
1. **Drag** the _Docker_ icon into the _Applications_ folder.
	* The transfer may take a few moments.
	<img src="../images/chapter-1/osx-drag_drop.png" />
1. **Find** the _Docker_ application in your _Applications_ folder.
1. **Double-click** the the _Docker_ application to run it.
1. **Click** _Open_ when prompted to run an application downloaded from the internet.
	<img src="../images/chapter-1/osx-docker-internet.png" />
1. **Click** _Next_ on the _Welcome_ dialog.
	<img src="../images/chapter-1/osx-welcome-next.png" />
1. **Click** _OK_ on the _Privileged Access_ dialog.
	<img src="../images/chapter-1/osx-docker-priv.png" />
1. **Type** in your User password to grant the required system permissions.
1. **Click** _OK_.
	<img src="../images/chapter-1/osx-docker-sys-pass.png" />

You'll see the Docker icon on your top menu bar. A dialog will open prompting for your Docker ID and Password.

#### Pull an Image to Verify the Installation

Let's test our installation and make sure everything looks good.


1. **Sign-in** using your DockerID and Password.
	* You can click on the Docker icon on the top menu to access Preferences, Documentation, User Settings and more.
	<img src="../images/chapter-1/osx-docker-top-dialog.png" style="max-width:25%;" />
1. **Open** a new _Terminal_ session.
1. **Run** the following command to check your Docker version:
```shell
docker --version
```
	* _Note: You should be running 18.X.X-ce_
1. **Run** the following command to set up a simple container:
```shell
docker run hello-world
```
	* You'll see that Docker downloads the latest _hello-world_ image from DockerHub.

#### Increase Resources Available to Docker 

Before we move on, let's update a few Docker settings. Depending on the stack you're running, Docker can become fairly resource-intensive. We suggest allocating a _minimum of 6GB of memory_ to Docker, with a recommended 8GB, if possible. Be sure your machine has adequate disk space, as containers can quickly eat up space.

1. **Open** Docker's _Preferences_ menu.
	* You can click on the whale icon in the top menu bar to see Docker options and menus.
1. **Click** on the _Advanced_ tab.
1. **Click** to set the memory to a minimum of 6GB.
	* On this menu you can also allocate the number of CPUs assigned to your containers as well as adjust settings for swap memory.
	<img src="../images/chapter-1/osx-advanced.png" style="max-width:100%;" />
1. **Click** the _Apply & Restart_ button. 

Your new settings will be applied and Docker will restart.

Congratulations! You've successfully installed the latest version of Docker and Docker Compose.

---

#### Bonus Exercises: 

Try the following bonus exercise after completing the main exercises:
1. Download an Ubuntu image and run it in _Terminal_ mode: `$ docker run -it ubuntu bash`
	* Run `Ctrl + p`, then `Ctrl + q` to detach from the bash session without killing the container process. 
