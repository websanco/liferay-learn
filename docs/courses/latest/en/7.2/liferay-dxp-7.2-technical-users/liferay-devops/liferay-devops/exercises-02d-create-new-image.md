<h2 class="exercise">Exercises</h2>

## Running a Simple Container

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Create a Dockerfile for an nginx web server</li>
			<li>Run a container from the image defined in the Dockerfile</li>
			<li>Pull an official Liferay image from DockerHub</li>
		</ul>
</div>

#### Create a New Dockerfile for an nginx Web Server

1. **Create** a text file called _Dockerfile_ in your favorite text editor.
	* You can keep this file at the top level of the Liferay folder we created as a prerequisite for the module.
	* Make sure not to add an extension to the file.
1. **Type** the following line in the file:
```Dockerfile
FROM nginx:alpine
```
	This is all we'll need to generate a container for our nginx web server. Docker will build all of the layers imported in the `FROM` call and give us a clean set up for an nginx server running on Alpine linux.
1. **Save** the file.

#### Build an Image from the Dockerfile

1. **Open** a new terminal window and navigate to the folder where you created the Dockerfile.
1. **Run** the following command in your terminal:
```shell
docker build -t firstimage .
```
	This will build an image from the Dockerfile we just created. The image will be tagged _firstimage_. The `docker build` command will look for the Dockerfile in the current directory.
	You should see output that looks something like this:
```bash
docker build -t firstimage .
Sending build context to Docker daemon  3.072kB
Step 1/1 : FROM nginx:alpine
alpine: Pulling from library/nginx
6c40cc604d8e: Pull complete
30c6e03cb91a: Pull complete
105f03a3abdf: Pull complete
a492669e5fd8: Pull complete
Digest: sha256:e0292d158b6b353fde34909243a4886977cb9d1abb8a8a5fef9e0ff7138dd3e2
Status: Downloaded newer image for nginx:alpine
 ---> b411e34b4606
 Successfully built b411e34b4606
 Successfully tagged firstimage:latest
```
1. **Run** the following command in your terminal to see the list of images you've created:
```bash
docker images
```
	You'll see the image we just created, the nginx image it's based on, and the hello-world image we ran in an earlier exercise.
	
```bash
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
firstimage          latest              b411e34b4606        6 days ago          16.1MB
nginx               alpine              b411e34b4606        6 days ago          16.1MB
hello-world         latest              fce289e99eb9        5 weeks ago         1.84kB
```

#### Run A Container From the Image
1. **Run** the following command to run a container based on this image:
```bash
docker run --name firstcontainer -d -p 8080:80 firstimage
```
	This command will create a container using the image we just created. You'll see we are giving the container a unique name (_firstcontainer_), we are running in detached mode (`-d`) to avoid seeing the live logs, we're publishing the port where we'll access the container on our host machine (`-p 8080:80`) and we're referencing _firstimage_ as the template. 
1. **Run** the following command in your terminal to see your running containers:
```bash
docker ps
```
	You'll see a list of your currently running containers:
```bash
CONTAINER ID        IMAGE               COMMAND                  CREATED             STATUS              PORTS                  NAMES
495b53c9fd98        firstimage          "nginx -g 'daemon of…"   3 minutes ago       Up 3 minutes        0.0.0.0:8080->80/tcp   firstcontainer
```
1. **Go to** _localhost:8080_ in a web browser.
	You should see that our web server is running. The default welcome message is displayed.
	<img src="../images/nginx_start.png" style="max-width:100%;" />

#### Stop the Running Container
1. **Go to** your terminal window.
1. **Run** the following command to stop the running container:
```bash
docker stop firstcontainer
```
	We are referencing the name we gave the container in order to stop it. You could also use the container ID to stop the process.

Congratulations! You've just created your first image from scratch.


#### Pull a Liferay Image from DockerHub

As we mentioned before, you can also access official Liferay Portal CE images from the Liferay DockerHub account. Let's take a look at creating a container from one of these images.

1. **Open** a new terminal window.
2. **Run** the following command:
```bash
docker run -it -p 8080:8080 liferay/portal:{tag}
```
	* _Replace_ the content in `{tag}` with the latest build available. In our example we can use the latest GA release, **7.2.0-ga1**. You can find a list of tags by going to [https://hub.docker.com/r/liferay/portal/tags](https://hub.docker.com/r/liferay/portal/tags)
	* We'll start the container in an _interactive_ mode by running the `-it` flag. This will allow us to use `CTRL+C` to kill the container.
	* When you run the command for the first time, you'll see a message that the image can't be found locally. Docker will pull the image from the online repository. It'll take a few moments.
3. **Go to** _localhost:8080_ in your web browser once the image has been downloaded and the container has started.
	* You'll see the start up logs in your terminal. Wait until you see a message like the following:
```bash
19-Jun-2019 15:07:58.788 INFO [main] org.apache.catalina.startup.Catalina.start Server startup in [88,141] milliseconds
```
You can sign into the platform using the following credentials:
	* User: test@liferay.com
	* Pass: test
4. **Press** `CTRL+C` back in your terminal window to kill the container after you've take a look around.
	* The portal will shutdown and you'll be able to access your terminal again.

As you can see it's very easy to pull and run images from DockerHub. In later exercises we'll use a custom Dockerfile to create an image for our needs. Keep in mind you can also use the default Liferay image as a base to build your own Dockerfiles, just as we did with the nginx image.

---

#### Bonus Exercise

Try this bonus exercise after you've completed the main exercises. You'll need docker's COPY instruction that we haven't yet discussed.
1. Try to add the custom `index.html` file provided in the exercises files to the nginx web server.
	* Hint: The file should be copied to `/usr/share/nginx/html` in the container.

If you need more information about the COPY instruction and have some time: Look it up. Otherwise dog-ear this page and come back to it later when we've used it in course.
