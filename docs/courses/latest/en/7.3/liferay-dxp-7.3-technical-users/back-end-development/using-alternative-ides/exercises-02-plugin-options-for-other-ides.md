## Plugin Options for Intellij or Existing Eclipse Installations

<div class="ahead">

#### Exercise Goals

- Installing Liferay Plugins into existing Eclipse installation
- Installing Liferay Plugins into Intellij

</div>

#### Installing Liferay Plugins into existing Eclipse installations

> NOTE: <br/><br/>
> The plugin versions and the version of Eclipse that you are working matter (just as they do for other plugins)
Be sure to review the details of the plugins page before choosing the items you wish to install

1. Start by opening a browser and **Navigating** to https://liferay.dev/-/ide-installation-instructions

2. Review the version requirements near the top. For example, at the time of this writing, the messaging on the
site stated *Supports Eclipse 4.8.0 or greater and Liferay Portal 7.0 and greater.* 

3. **Launch** your eclipse application

4. Using the application menus choose **Help** > **Install New Software**

5. Click the **Add** button

6. In the dialog enter **Liferay IDE** for the name and then **https://releases.liferay.com/tools/ide/latest/stable/**
 for the URL and click the **Add** button
 
 > NOTE: <br/><br/>
 > The url we're using here can be found on the web page that we navigated to in Step #1.

    <figure>
        <img src="../images/01-03__01-lifeay-ide-update-site.png"/>
    </figure>

7. Chances are you want to install the entire set of plugins, but if you would like to be more selective, you can
expand the **Liferay IDE** node and select the items you want to install. **Select** the items you want and then 
click the **Next** button

    <figure>
        <img src="../images/01-03__02-lifeay-ide-install-options.png"/>
    </figure>

8. All requirements should be met and assuming they are, click the **Next** button to continue to the next step

    <figure>
        <img src="../images/01-03__03-lifeay-ide-install-requirements.png"/>
    </figure>
    
9. Accept the terms of license agreement and click **Finish**

    <figure>
        <img src="../images/01-03__04-lifeay-ide-terms-of-use.png"/>
    </figure>

10. Wait for the installation to complete. You can monitor the status in the bottom right hand corner.

     <figure>
         <img src="../images/01-03__05-lifeay-ide-install-progress.png"/>
     </figure>


11. Once the install is complete and Eclipse has been restarted, we'll validate that everything is working as
  expected by changing our perspective to use the *Liferay perspective*
 
12. In the application menu choose **Window** > **Perspective** > **Open Perspective** > **Other**

13. From the resulting modal select **Liferay Workspace** and click **Open**

     <figure>
         <img src="../images/01-03__06-lifeay-ide-install-perspectives.png"/>
     </figure>

14. Notice that the view updates so that the individual panels that make up the workbench are the same as 
those that we saw in the last section when we installed Developer Studio.

> NOTE: <br/><br/>
> The Liferay IDE plugins is the equivalent of installing the standalone Liferay IDE application. The Liferay
ID application is actually the community edition of the toolkit which means that if you go this route you 
will not have the additional tools that are part of the subscription based version with Developer Studio.



#### Installing Liferay Plugins into Intellij


1. Start by **Launching** your Intellij application

2. Go to the **Preferences** area of Intellij

3. Use the navigator on the left hand side of the modal window to select **Plugins**

    <figure>
         <img src="../images/01-03__07-intellij-plugins.png"/>
    </figure>

4. In the search field enter **Liferay** and hit enter

    <figure>
         <img src="../images/01-03__08-intellij-liferay-plugins.png"/>
    </figure>

5. Click the **Install** button in the upper right hand corner to install the plugins and then restart the application

6. To test it out we'll see if we can find the context menus that allow you to create modules. Inside your 
Liferay Workspace, right click on the **modules** folder, choose **New** > **Liferay Module**

    <figure>
         <img src="../images/01-03__09-intellij-context-menu.png"/>
    </figure>


7. A dialog should open with the options that you fill in to create the new module. That's it, you now have the 
Intellij plugins for Liferay install and are ready to start developing

    <figure>
         <img src="../images/01-03__08-intellij-liferay-wizard.png"/>
    </figure>


<div class="page"></div>

