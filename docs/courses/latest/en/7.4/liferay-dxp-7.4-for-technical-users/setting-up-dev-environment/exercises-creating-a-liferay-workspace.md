# Creating a Liferay Workspace

Coming Soon!

<!--

#### Exercise Goals

- Install Blade CLI
- Create a Liferay Workspace

</div>

> NOTE: <br />
> While there are several methods available for the creation of Java Web Applications for Liferay, one of the most convenient is to use a Liferay Workspace. Liferay Workspace is a set of folders and Gradle scripts that represents the Liferay-opinionated way of handling a full development life cycle. Since it is implemented using Gradle scripts and plugins, Liferay Workspace integrates into any IDE or development tool. A Liferay Workspace can be created manually or using Liferay's Blade CLI, which is the method that will be outlined here. For steps on the manual creation of a Liferay Workspace, visit: https://learn.liferay.com/dxp/latest/en/building-applications/tooling/liferay-workspace/creating-a-liferay-workspace.html

<div class="page"></div>

#### Install Blade CLI
1. **Open** a new terminal window.
* **Run** the command `curl -L https://raw.githubusercontent.com/liferay/liferay-blade-cli/master/cli/installers/local | sh`.
    > NOTE: <br/>
    The command line install for Blade CLI is only available for Linux and Mac operating systems. If you are on Windows or prefer to use a Graphical Installer, follow the instructions found here: https://learn.liferay.com/dxp/latest/en/building-applications/tooling/blade-cli/installing-and-updating-blade-cli.html

* **Open** a new terminal window when the installation is complete.
* **Run** the command `blade`. 
    * If you get a command not found error, be sure to add the _blade_ command to your path.


#### Create a Liferay Workspace using Blade CLI
1. **Go to** the desired folder location for your Liferay Workspace in your CLI.
* **Run** the command `blade init -l` to view all the available versions of Liferay that you can target.
* **Run** the command `blade init -v 7.4 gradebook-workspace`.
     
Ok -- that's it! You now have successfully created a Liferay Workspace and are ready to start building your Liferay applications. Since a Gradle wrapper is installed in your workspace, from here you have the option to generate projects using either Blade or standard Gradle commands inside the IDE of your choice. For those of you who would rather use an existing Eclipse installation, or prefer to work with
Intellij over Eclipse, the good news is that you can install the plugins that allow you to access Liferay
functionality and shortcuts separately. If that's your workflow, then check out the next section.
    
<br />

---

#### Bonus Exercises:
1. Try creating your first widget module. Using the method of your choosing (i.e. Blade, Gradle commands, or IDE plugins) create an MVC portlet with the following characteristics:
    * For the **Project Name**, use *sample-module-web* 
    * For the **Component Class Name** use *SampleModulePortlet*
    * For the **Package Name** enter *com.sample.web*
    * Leave the properties section empty and click **Finish**
        * For instructions on using Blade to generate projects go here: https://learn.liferay.com/dxp/latest/en/building-applications/tooling/blade-cli/generating-projects-with-blade-cli.html 
2. **Navigate** down through the tree of files and try to understand how this structure might align with what you are already familiar with in WAR based portlet development.
    
-->