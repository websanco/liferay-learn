## Set Up Docker on Linux

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Install the latest Community Edition of Docker locally</li>
			<li>Pull and run a simple image from Docker Hub</li>
		</ul>
</div>
<br />

Let's set up Docker CE on Ubuntu. We'll be using the 18.04 LTS version for these exercises. The steps should also apply on 17.10, 16.04, and 14.04 as well. 

<div class="note">
    Note: You'll need a 64-bit version of Ubuntu to install Docker CE.
</div>

For more information and instructions for other distributions, you can see the official documentation at https://docs.docker.com/install/#supported-platforms.

#### Uninstall Any Older Versions of Docker on the System

We'll begin with a clean slate by removing older versions of Docker.

1. **Open** a new _Terminal_ window.
    * You can press `CTRL+ALT+T` to quickly open up a new session.
1. **Run** the following command to remove any existing versions of Docker already on your machine.
```bash
sudo apt-get remove docker docker-engine docker.io
```
1. **Enter** your password when prompted.

This will uninstall older versions of Docker. If no existing versions are present, you'll see a message in the _Terminal_ saying so.

#### Add the Docker Repository to Your System

We'll be installing Docker from a Docker repository. Before we can begin the installation, we'll connect to the repository.

1. **Run** the following command to update packages:
```bash
sudo apt-get update
```
    * Enter your password if prompted
1. **Run** the following commands to install packages that will allow `apt` to access a repository over HTTPS
```bash
sudo apt-get install apt-transport-https ca-certificates curl software-properties-common
```
    * Alternatively, you can use the `\` to avoid a long single line command
1. **Run** the following command to add the official Docker GPG key: 

```bash
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -
```
    * You can confirm you have the correct key by searching for the last 8 digits of the fingerprint, `0EBFCD88`:
```bash
sudo apt-key fingerprint 0EBFCD88
```
1. **Run** the following command to set up a **stable** repository: 
```bash
sudo add-apt-repository \
"deb [arch=amd64] https://download.docker.com/linux/ubuntu \
$(lsb_release -cs) stable"
```

#### Install Docker CE From the Repository

Now that our Docker repository is set up, we can install Docker CE.

1. **Run** the following command to update the `apt` index again:
```bash
sudo apt-get update
```

1. **Run** the following command install the latest version of Docker:

```bash
sudo apt-get install docker-ce
```
1. **Type** `y` when prompted for confirmation to install.
    * The installation may take a few moments.

#### Pull an Image to Verify the Installation

1. **Run** the following Docker command to test the installation:
```bash
sudo docker run hello-world
```
    * You can also run `docker --version` to see the version in your _Terminal_.

#### Install Docker Compose and Make the Command Executable

Before we wrap up, we'll also install Docker Compose. We'll be using this to orchestrate containers throughout the course.

1. **Run** the following command to download Docker Compose:
```bash
sudo curl -L "https://github.com/docker/compose/releases/download/1.22.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
```

1. **Type** in your user password if prompted.
    * The download will begin and you'll see download information in the terminal.
1. **Run** the following command to make Compose executable:
```bash
sudo chmod +x /usr/local/bin/docker-compose
```
1. **Run** `docker-compose --version` to verify the installation.
1. **Run** `sudo usermod -aG docker $USER`.
    - This command makes you a member of the "docker" usergroup. Without this, we'd need to execute all docker commands as root, e.g. with `sudo`.

Congratulations! You've successfully installed the latest version of Docker CE and Docker Compose.

---

#### Bonus Exercises: 

Try the following bonus exercise after completing the main exercises:
1. Download an Ubuntu image and run it in _Terminal_ mode: `$ docker run -it ubuntu bash`
	* Run `Ctrl + p`, then `Ctrl + q` to detach from the bash session without killing the container process. 
