<h3 class="exercise">Exercises</h3>

# Install a Fixpack or Hotfix

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Learn how to use Patching Tool</li>
		</ul>
</div>

#### Patching your installation

Patching is quite easy: Liferay Support provides Fixpacks and Hotfixes for you. For this training, we're providing a Hotfix, and the availability of this hotfix will be immediately visible when you open your browser on the default homepage:

<img src="../images/patched_ga1_72.jpg" style="max-width:100%;">

This Hotfix (while this chapter is written) is built for Liferay DXP 7.2 GA1 - Hotfixes are built for *exactly* the documented version. If this training is updated to utilize a later version, e.g. a later Servicepack, it might be updated. You'll see this in the hotfix metadata, and during the installation process.

Let's first look at the installation of a hotfix outside of Docker:

**Unzip** the Liferay Tomcat Bundle to a temporary folder. On the top level of this folder you'll see a `patching-tool` folder. This folder has a subdirectory `patches`, where you'll need to copy all fixpacks and hotfixes that you intend to use. Patching tool will pick them up and tell you what it intends to do with them: See the full options by executing `./patching-tool.sh help` (you might need to make the shellscript executable). Patching Tool can update your full installation (it will find Tomcat in the parent directory automatically), your Source Code, or any Liferay DXP installation in different locations, if you configure it properly. We're using the default configuration:

**Copy** the provided liferay-hotfix-11-7210.zip into `patching-tool/patches/`

    ```shell
    $ ./patching-tool.sh info
    Loading product and patch information...
    Product information:
    * installation type: binary
    * build number: 7210
    * service pack version:
        - available SP version: Not available
        - installable SP version: Not available
    * patching-tool version: 2.0.12
    * time: 2019-07-19 09:17Z
    * host: training-olaf (64 cores)
    * plugins: no plugins detected

    Currently installed patches: -
    Available patches: hotfix-11-7210

    Detailed patch list: 
    [ I] hotfix-11-7210 :: Currently not installed; Will be installed. :: DXP-TRAINING :: Built for THESPACEPRO
    $ 
    ```
    
This command gave you some information about the system you're installing on, and the patches that are already available and installed (hint: 1 available, 0 installed). You can now install the hotfix

    ```shell
    $ ./patching-tool.sh install
    One patch is ready to be installed. Applying hotfix-11...
    Cleaning up: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
    Installing patches: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]
    Processing bundles: [1%..10%..20%..30%..40%..50%..60%..70%..80%..90%..100%]

    No module-mapping.txt found in Patching Tool root folder.
    For further details, see https://help.liferay.com/hc/en-us/articles/360024518932-Patching-Tool-2-0-11
    The installation was successful. One patch is installed on the system.
    $
    ```

Subsequent `./patching-tool info` runs will tell you what's installed. To install more patches, just copy them into the patches directory and run the command again. Patching Tool will know, which patches are contained in other patches and only install the required ones.

The OSGi runtime caches quite a lot of deployed modules in the folder `osgi/state`, which you might need to wipe for the patches to become active.

#### Updating your Docker Image

For this training, we didn't create any automated build environment (e.g. Continuous Integration server). Thus, we'll need to simulate it by working manually. You may choose how to bring the updates back into the Docker Image. One option is to just zip up the patched directory from the previous chapter, and build the Docker Image from that updated file. Another one is to embed the patches during the building of the Docker Image.

We're patching the unchanged downloaded bundle, so no problems are assumed. Of course, if you patch a customized Liferay version, e.g. where you install ext-plugins, you will have to do your due-diligence and install&test more in-depth than we document here. This will be a requirement imposed on your team that develops the customizations: They'll have to make sure that their modifications (especially overrides to Liferay core modules) still apply to the updated versions.

For now, just delete the osgi/state folder in your unzipped directory and zip the directory. Replace the zip file name in your Dockerfile. Rebuild and restart. Observe that the "Hello World" Widget will indicate that you're running the patched installation
