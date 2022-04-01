<h2 class="exercise">Exercises</h2>

## Set Up Docker on Windows 10 Professional

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Install the latest Community Edition of Docker locally</li>
			<li>Pull and run a simple image from Docker Hub</li>
		</ul>
</div>

<div class="note">
	Note: Docker Desktop requires Windows 10 Pro or Enterprise edition and will enable Microsoft Hyper-V. When Hyper-V is enabled on your system, <b>VirtualBox will no longer work</b>. If you have a VirtualBox workflow or if you are on Windows 10 Home or an earlier version of Windows, you will need to install <i>Docker Toolbox</i>. For installation instructions and the download link see the Docker Toolbox page at <a>https://docs.docker.com/toolbox/toolbox_install_windows/ </a>.
</div>

#### Create A Docker Account on Docker Hub

1. **Go to** https://hub.docker.com/editions/community/docker-ce-desktop-windows in your browser.
	* You'll have to create a Docker ID and login in before you can download the installer.
* **Click** the _Please Login to Download_ button.
	* Log in if you already have a Docker ID.
* **Click** the _Create Account_ link at the bottom right.
	<img src="../images/chapter-1/login-to-docker.png" style="max-width:100%;" />
* **Create** a new Docker account by filling in the required information. 
1. **Click** _Sign Up_.
	* You'll be required to confirm the sign up e-mail address via e-mail confirmation.
1. **Click** the _Confirm Your Email_ link in the confirmation e-mail you receive from Docker.
	- If the email is not in your inbox try refreshing the page. If it still doesn't show up, check your spam folder.
	- When you click the confirmation link in your e-mail, you'll be redirected to the Docker Desktop download page again.
1. **Click** the _Please Login to Download_ button again.

<img src="../images/chapter-1/login-to-download.png" style="max-height:20%;" />

#### Download and Install the CE Version of Docker

1. **Sign in** to Docker Hub with your _Docker ID_ and _Password_.
2. **Click** the _Get Docker_ button.
3. **Double-click** on `Docker for Windows Installer.exe` to run the installer once the download is complete.
	* Click _Yes_ if prompted by Windows to allow the installer to make changes.
4. **Click** through the installer to install Docker Desktop on your machine.
	- Make sure to leave _Use Windows containers instead of Linux containers_ unchecked
	<img src="../images/chapter-1/docker-config.png" style="max-height:20%;" />
5. **Click** the _Close_ button when Docker is finished installing.
	- You may be prompted to restart your computer. Make sure that you do not have anything important running on your computer _before_ clicking Ok.

After the system restarts a Docker pop-up will appear telling you that _Docker is now up and running!_

<img src="../images/chapter-1/docker-running.png" style="max-height:20%;" />

#### Confirm the Docker Installation and Pull Your First Image
1. **Open** a command-line terminal window.
2. **Type** `docker version`.
3. **Press** _Enter_.
	- You should see confirmation that Docker is installed on your machine.
4. **Run** `docker run hello-world` in the command line.

You should see the message _Hello from Docker_! This message shows that you can successfully pull and run images from Docker Hub.

#### Give Docker Access to the `C` Drive and Increase Resources

Before we move on, let's update a few Docker settings. Depending on the stack you're running, Docker can become fairly resource intensive. We suggest allocating a _minimum of 6GB of memory_ to Docker, with a recommended 8GB if possible. Also be sure your machine has adequate disk space as containers can quickly eat up space.

1. **Open** Docker's _Settings_ menu.
	* You can right click on the whale icon in the task bar to see Docker options and menus
1. **Click** on the _Shared Drives_ section.
1. **Check** the box to share the `C` drive.
	* We'll need to share the drive for our Wildfly container in later exercises.
	<img src="../images/chapter-1/win-share-drive.png" style="max-height:20%;" />
1. **Click** _Apply_.
1. **Click** on the _Advanced_ section.
1. **Click** to set the memory to a minimum of 6GB.
	* On this menu you can also allocate the number of CPUs assigned to your containers as well as adjust settings for swap memory, disk image location and max disk image size.
	<img src="../images/chapter-1/win-advanced-settings.png" style="max-height:20%;" />
1. **Click** the _Apply_ button. 

Your new settings will be applied and Docker will restart. Congratulations! You have successfully installed Docker on Windows. 

---

#### Bonus Exercises: 

Try the following bonus exercise after completing the main exercises:
1. Download an Ubuntu image and run it in terminal mode: `$ docker run -it ubuntu bash`
	* Run `Ctrl + p` then `Ctrl + q` to detach from the bash session without killing the container process. 
