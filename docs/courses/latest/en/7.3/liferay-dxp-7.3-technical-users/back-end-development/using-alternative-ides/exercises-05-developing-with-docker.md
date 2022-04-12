## Developing with Docker

<div class="ahead">

#### Exercise Goals

- Configuring the docker image to use
- Creating the image
- Configuring the container settings
- Starting and Stopping the container
- Viewing the runtime logs
- Performing deployments

</div>


#### Configuring the Docker Image to Use

1. In the root of the *gradebook-workspace*, find and **Open** the file *gradle.properties*

2. **Locate** the property *liferay.workspace.docker.image.liferay*

    > NOTE: <br/><br/>
    > This property is normally disabled by default so the line itself may be commented out

3. **Uncomment** the line to make the property active

4. **Open** a new internet browser and navigate to *https://hub.docker.com/r/liferay/dxp/tags*

5. **Copy** the tag for the version you wish to work with. eg. docker pull liferay/dxp:7.2.10-dxp-6

6. Back in the gradle,properties file, **Set the value** of the new property to *liferay/dxp:7.2.10-dxp-6*

7. That's it! Now when you run the commands to create the container, it will use the version of the image
that you have configured. 


#### Creating the image

1. Now that we have the image configured and the basic set of configurations we want to us in place, we can
go ahead and create our container that we will use for development

2. **Expand** the *docker* node found in the Gradle pane within Developer Studio,

3. Double click the **createDockerContainer** task

4. After a few moments your new image should be created. You can validate this on the command line. **Open**
a new terminal / shell

5. **Run** the following command

`$>docker image ls`

6. The list of images will be output and you should see in that list an image named *gradebook-workspace-liferay*

7. Now that we have our image ready to go, let's add some configurations and start a new container.


#### Configuring the container settings

1. **Expand** the directory WORKSPAE_ROOT/configs/local

2. **Open** the *portal-ext.properties* file

3. **Replace** the content in the file with the following

    <pre>
    #
    # Developer Settings -- DO NOT USE IN PRODUCTION
    #
    include-and-override=portal-developer.properties
    
    #
    # Host and port used to the Gogo shell. This property is providing a 
    # host to image mapping so that users can access the Gogo shell in the 
    # container from the host machine
    #
    module.framework.properties.osgi.console=0.0.0.0:11311
    </pre>
    
4. **Save** the file

5. That's it! Time to start up our container.

> NOTE: <br/><br/>
> Here we've haven't really done extensive configurations. This is really just to illustrate how you should be
managing your configurations for your development docker image. Remember that one of the key advantages to 
using this approach is that the files that are part of the configs directory are normally under source control.
This means that the changes you make (and commit/push) will immediately be available to the rest of the team
which greatly reduces the risk of individual developer environments getting out of sync.


#### Starting and Stopping the container

1. We've created the image and provided some basic configurations, now it's time to put it in action. 

2. First let's make sure that our *createDockerContainer* Gradle task was successful

3. Open a terminal / shell and run the following command 

    `$>docker container ls -all`

4. In the results you should see the list of containers that are available. The output will vary from student 
to student based on whether or not you are an active Docker user, but what should be there in all cases
is a reference to a *gradebook-workspace-lifray-xxx* container

    <figure>
        <img src="../images/01-06__01-docker-container-list.png"/>
    </figure>

5. Liferay provides a Gradle task called *startDockerContainer* but, as part of the task execution, it has a 
dependency on the *createDockerContainer* image. This might seem odd, but when you think about the concept
of disposable infrastructure, it makes sense. Before we use it, we need to purge the exiting container. 

6. In the Gradle tasks pane, under *docker* double click on **removeDockerContainer**

7. Now double click on the **startDockerContainer** task

8. To **Stop** the container you will **Execute** the Gradle task marked as *stopDockerContainer*. 

    > NOTE: <br/><br/>
    > Even though you stop the container, the process will not remove it from the list of containers.
    This means that before you can start the container again you will first have to execute 
    the removeDockerContainer task

9. After a few moments the process will kick off, and though you can't see it, Liferay is starting up. To 
monitor the startup process we'll tail the logs from the container. Move to the next section for the details
on how to do this.


#### Viewing the runtime logs

1. First **Check** to make sure that your container is running

2. **Open** a new terminal / shell and execute the following command

`$>docker container ls -all`

3. **Locate** the container running your *gradebook-workspace-liferay* image and validate that the status
for the container is *Up X minutes ...*


    <figure>
        <img src="../images/01-06__02-docker-container-status.png"/>
    </figure>
    
4. Switch back to Developer Studio and in the Gradle tasks pane double click on the **logsDockerContainer** task

5. Wait a moment and you should now see the runtime log for you docker container running Liferay


#### Performing deployments

> NOTE: <br/><br/>
> For this section it is assumed that you have at least one module that you have developed, or started
developing that can be successfully built. If you do not, then we suggest that you skip this section for
now and come back to it later

1. The last piece we need to close the loop on development using docker containers is to handle deployments 
from our local development into the running container.

2. Inside Developer Studio, turn your attention to the Gradle tasks pane. **Expand** the node with the 
label *modules*

3. **Select** the module that you want to deploy and expand it's node

4. From the list of sub-nodes under the module name **Expand** the *docker* item

5. **Locate** the task named *dockerDeploy* and double click it

6. Wait a few moments and if you turn your attention back to the pane where you are tailing the docker log 
(from the last section) you should see the module successfully started


<div class="page"></div>

---

