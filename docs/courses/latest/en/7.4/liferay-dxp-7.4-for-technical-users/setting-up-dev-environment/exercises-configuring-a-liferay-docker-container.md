# Installing a Bundle in your Workspace

Coming Soon!

<!--

#### Exercise Goals

- Choose a Liferay Docker Image
- Create the Liferay Docker Image

</div>

#### Add a Liferay Docker Image to Your Liferay Workspace
1. **View** the available Liferay Docker Images on Docker Hub.
	* For Liferay Portal visit: https://hub.docker.com/r/liferay/portal
	* For Liferay DXP visit: https://hub.docker.com/r/liferay/dxp 
* **Note** the full tag name for your container. For this project we will use `liferay/dxp:7.4.13-u8`.
* **Open** the `gradle.properties` file in your Liferay Workspace. 
* **Add** the following property:
```groovy
liferay.workspace.docker.image.liferay=liferay/dxp:7.4.13-u8
```
* **Save** your file.
* **Go to** your workspace's root folder in your CLI.
* **Run** the command `./gradlew createDockerContainer`.

Congratulations. A Docker image based on the name of your workspace with `-liferay` appended has been created. You can start/stop the container like any other Docker image.

---

-->