## Installing the BLADE command line tools

<div class="ahead">

#### Exercise Goals

- Download the BLADE installation file
- Run the BLADE installation executable 
- Validate our installation

</div>

#### Download the BLADE installation file

> NOTE: <br />
> This section will cover how to install the BLADE tools from the command line only. While this is a useful exercise, it is not 
the only way that the tools can be installed. You will see in the sections that follow alternative options where BLADE is 
installed as part of the Liferay IDE installation. This is also why the URL below takes you to the IDE install files as well.

1. **Login** to Liferay's Help Center:  https://help.liferay.com/hc/en-us
    * Once you are logged in you should see the option to *Browse by Product*

    > NOTE: <br/>
    If you do not see this option after login, then it could mean that the account you have with Liferay has not been
    associated with a customer profile. You'll need to contact the member of your organization who has access to open
    Liferay support tickets and ask them to open a ticket to have your user added to the customer account.
    
    <figure>
        <img src="../images/01-02__01-login-browse-by-product.png"/>
    </figure>
 

* **Choose** DXP 7.3.
* **Choose** the _Downloads_ tab
* **Click** _Latest Release_.
* **Select** _Developer Tools_ for the Product and _Developer Studio_ for the File Type.
* **Find** the Liferay Worksapce Installer you wish to install.
    * Typically you will opt for the latest version since the newer versions are backwards compatible with older workspaces.
* **Select** your target OS and then when ready, click the _Download_ button.

<br />

<img src="images/download_liferay_studio.png" />

#### Run the BLADE Install Executable
1. **Open** a finder or windows explorer window and navigate to the location of the download.
2. **Double click** the download to start the installation wizard.
    > NOTE: <br />
    For OSX users, you may need to go into your security settings and authorize the file to be run as the Developer will likely
    come up as unrecognized.
3. **Select** the JVM you wish to use.
    * You should see the _Setup -- Liferay Workspace_ dialog
* **Click** _Next_.
* **Click** _Next_ to complete the installation process.
* **Click** _Finish_ to close the dialog.

#### Validate our installation
1. **Open** a new terminal/shell window.
2. **Run** the following command when prompted:

    `$>blade version`

    * If the command executes successfully, then you should get a response that looks something like this. The version that comes back will depend on the version you have installed.

    `$> blade version`
    <br />
    ` blade version 3.9.2.202004101340`

* **Run** a blade command with no arguments:

    `$>blade`

    * If the command executes successfully, then you should get a response that contains the usage for the blade tools

    `$>blade`
    <br/>
    `Usage: <main class> [command] [command options]`
    <br/>
    `Commands:`
    <br/>
    `...`

That's it! You have successfully installed the BLADE command line tools. As useful as the command line tools are, there is a lot of to be said from using BLADE in conjunction with an IDE to accelerate your development. Next we'll look at how you can download and install Liferay Developer Studio to take advantage of the wizards, templates and automation tools when developing for Liferay.  

