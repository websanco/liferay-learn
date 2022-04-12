## Create and explore your first Liferay workspace

<div class="ahead">

#### Exercise Goals

- Create a new Liferay workspace using BLADE command line tools
- Create a new Maven based Liferay workspace using the BLADE command line tools
- Create a new Liferay workspace using Developer Studio
- Create a new Liferay workspace using Intellij
- Configuring your Liferay workspace
- Upgrading your Liferay workspace

</div>

> NOTE: <br/>
> This section is best served to those who prefer to work on the command line. If your workflow is to start the IDE and then do everything you can from in there, this section will provide useful information, but you are probably best to skip to the next section where we cover the same topic, but from inside Developer Studio

#### Create a new Liferay workspace using BLADE command line tools
1. **Open** a new terminal / shell.
2. **Navigate** to the location where you plan to store your projects (eg. projects/liferay).
    * The convention used for naming the directory that houses a workspace is to use [project name]-workspace. For the purposes of Liferay's training modules we demonstrate the features and capabilities of the product by building building a sample application piece by piece. The sample application starts as a personal financial tracking application which will morph over the course into a SaaS based product. The title of this application is _Grade Book_ and so we want to create a new workspace called _gradebook-workspace_.
* **Run** the following command to create a new workspace:

    `$>blade init gradebook-workspace`

    * A text based menu of options will appear giving you the choice of Liferay Version you wish to target.
* **Select** the option for _7.3_.
* **Navigate** into the new workspace and list the directory contents

    `$>cd gradebook-workspace && ls -all`

That's it! Your new Liferay Workspace for the Gradebook application is good to go.

> NOTE: <br />
> By default BLADE will create a workspace that uses Gradle as the build too. Many Liferay developers still 
prefer to work with Maven so let's look a how we can generate a new Liferay Workspace that uses Maven
instead of Gradle.

#### Create a new Maven-based Liferay workspace using the BLADE command line tools
1. ** Navigate** back to the root projects location

    `$>cd ~/projects/liferay`

* **Run** the following command to tell BLADE to create a workspace that uses Maven.
    * Here we also see that you can specify the version of Liferay you want to target as part of the command which will allows us to skip the prompt.

    `$>blade init -b maven -v 7.3 gradebook-maven-workspace` 
    
* **Navigate** into the new workspace that uses maven and list the directory contents
   
    `$>cd gradebook-maven-workspace && ls -all`
   
That's it! You now have a new Liferay workspace that builds with Maven in place of Gradle.

> NOTE: <br/>
> When installing Developer Studio, the installation process will automatically add a new Liferay Workspace
(called liferay-workspace) to the install location. This was the workspace that we used in the previous sections
as a reference for the IDE perspectives. At this point it is advised that you remove (delete) this directory
so that you avoid error messages from the Liferay Workspace Wizard that complain about a workspace already
existing. 

<!--
#### Create a new Liferay workspace using Developer Studio
1. **Launch** Liferay Developer Studio
2. **Choose** to create a **New Liferay Workspace**
* **Type** _gradebook-workspace_ for the name
* **Change** the versions to 7.3.

    > NOTE: <br />
    > Notice that there is an option here for being able to choose between creating a workspace based on the
    Gradle build tooling, or generated the workspace so that it uses Maven.

4. **Click** _Finish_ to complete the setup process.
5. Once complete the screen should reload and you should now be on the workbench for the Gradebook Liferay Workspace

    <figure>
        <img src="../images/01-04__03-new-workspace-piggybank-worksbench.png"/>
    </figure>
-->

#### Create a new Liferay workspace using IntelliJ

1. **Launch** your Intellij application

2. From the application menu choose **File** > **New** 

3. In the resulting dialog window, be sure to choose **Liferay** from the left hand navigation

4. In the main area of the window choose between Maven and Gradle. For this example we'll select
**Liferay Gradle Workspace**

    <figure>
        <img src="../images/01-04__04-intellij-new-workspace.png"/>
    </figure>

5. Complete the wizard dialog with the details for your new workspace and click **Finish** 

    <figure>
        <img src="../images/01-04__05-intellij-new-workspace-wizard.png"/>
    </figure>

6. If Intellij prompts you to create the directory (because it does not already exist) then agree to the action
and when prompted, open the workspace in a **New Window**

    <figure>
        <img src="../images/01-04__06-intellij-new-piggybank-workspace.png"/>
    </figure>

7. That's it! You now have a new Liferay Workspace that you can start developing with using Intellj as your IDE


#### Configuring your Liferay workspace

1. Start by opening the file in the root of the workspace called **gradle.properties**

2. This file contains many of the settings that can be used to tune the workspace to meet your needs. Each property
should be accompanied with comments that explain the purpose of the property and in some cases, where to 
find more information. There are a few key properties we should understand. **Search** the file for the property 
named *liferay.workspace.bundle.url*

3. This property contains a url that references the location for where the bundle can be found (more on this in 
the next learning module). The important piece to note here is that as the new versions of Liferay are released,
or, if you wish to target an older version of a release, you can use this property to ensure the correct bundle
is downloaded and installed

4. Next **Search** the file for the property *liferay.workspace.target.platform.version*

5. The target platform property is used to specify, well, the version if Liferay that you intend to be working 
with. As part of your module development you will often have to specify versions for the dependencies that you
are referencing. Leveraging this property (it's commented out by default) will allow you to omit having to specify
many of the dependency versions -- just like the *dependencyManagement* feature for the bill of materials (BOM)
that you find in Maven.

6. **Remove** the leading hashes from the line to activate the target platform property

7. In the comments for this property you will find URLs that will lead you to a page where you can look up 
the value that you will want to use. **Navigate** to *https://bit.ly/2GIyfZF*

8. Here you will find the list of versions that you can reference. You should select a version that aligns with
running version of Liferay to avoid conflicts between your development references and the runtime references.

    <figure>
        <img src="../images/01-04__07-nexus-repository.png"/>
    </figure>

9. Go back to the *gradle.properties* file and **Search** for the property *liferay.workspace.product*

10. This is a relatively new property that has been added to the Liferay Workspace. The goal of this property 
is a one-stop-shop that can be used in place of specifying the bundle, target platform and a couple other
properties (tomcat and docker references). 

11. **Update** the value for value for this property to XXX

12. **Update** the lines containing *liferay.workspace.target.platform.version* and *liferay.workspace.bundle.url*
by commenting out these lines as they are no longer required with the *liferay.workspace.product* property enabled


#### Upgrading your Liferay workspace

1. Keeping pace with changes to the Liferay workspace is important to gain access to new features as well as 
bug fixes etc. **Open** your internet browser and **Navigate** to 
https://repository-cdn.liferay.com/nexus/content/repositories/liferay-public-releases/com/liferay/com.liferay.gradle.plugins.workspace/

2. Here you will see the versions that have been released. **Select** a version from the list, eg. *2.5.8*

3. In the root of your Liferay Workspace, **Open** the file *settings.gradle*

4. Near the top of the file you should see a *dependencies* block that contains a line that contains a reference
to *com.liferay.gradle.plugins.workspace*. **Update** the version portion of this line to have a value of 
*2.5.8*

5. That's it! You may be waiting for something magical to happen, but there is nothing to see. The Gradle 
env within the workspace will *"refresh"* itself and you will now have access to whatever fixes or features 
are part of the Liferay Workspace v2.5.8

> NOTE: <br/><br/>
> It's a good practice to record the location of the workspace releases somewhere (notepad, bookmark, etc)
and to check back every few weeks to see if a new version has been released. This is particularly important
for cases where you are working with existing Liferay Workspaces that have been around for some time. Remember
that the upgrades/updates are not automatic for this tool so it's up to you to keep pace within existing 
Liferay Worksspaces

