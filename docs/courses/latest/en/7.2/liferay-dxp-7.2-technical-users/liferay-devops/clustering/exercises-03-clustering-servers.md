<h3 class="exercise">Exercises</h3>

## Enable Load Balancing for the Tomcat Stack

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Update HAProxy Configurations for Load Balancing</li>
		</ul>
</div>

#### Update HAProxy's Configurations

Let's configure the HAProxy service to enable load balancing.

1. **Open** the `haproxy.cfg` file located in the _liferay/liferay-tomcat/services/haproxy/config_ folder.
    * We looked at this configuration file before when we saw how HAProxy is enabled as a reverse proxy for the Tomcat service.
1. **Find** the _backend liferay_cluster_ section at line 22.
1. **Remove** the comment symbol (`#`) from the `balance` keyword:
    ```config
    balance roundrobin
    ```  
    * This line sets the algorithm that HAProxy will use when load balancing. When using the default _roundrobin_ algorithm, each back-end server is used in turns according to server weight. In other words, when serving content, the load balancer will select liferay-tomcat-1, then liferay-tomcat-2 in a back and forth fashion.
1. **Remove** the comment symbol from the `cookie` keyword:
    ```config
    cookie JSESSIONID prefix
    ```  
    * Here we are implementing persistent sticky sessions. This guarantees that a client will stay on the server it was initially assigned to through its entire session.
1. **Remove** the comment symbol from the first `server` keyword:
    ```config
    server liferay-tomcat-1 liferay-tomcat-1:8080 cookie liferay_1 check
    ``` 
    * The following two lines define the servers we will use when handling front-end requests. This first line assigns a name to the server (_liferay-tomcat-1_), assigns the server's address (tomcat-1 container at port `8080`), and sets two additional parameters for the server.
    * The `cookie` parameter sets a prefix (_liferay\_1_) for the cookie value that HAProxy will preprend to the JSESSIONID cookie value. This allows HAProxy to identify this server for a sticky session. For example, HAProxy would tell the client to add something like the following to the HTTP Header:
        ```html
        Set-Cookie: JSESSIONID=liferay-tomcat-1~i12KJF23JKJ1EKJ21213KJ
        ```
        * On subsequent requests, when HAProxy sees the liferay\_1 prefix, it will know to send the request to the liferay-tomcat-1 server.
    * The `check` parameter enables health checks for the server, making sure the server is able to handle requests. If a health check fails for a server, HAProxy will disable the failing node until it is healthy again. Managing failing nodes is a key component to maintaining a high availability infrastructure. 
1. **Remove** the comment symbol from the second `server` keyword:
    ```config
    server liferay-tomcat-2 liferay-tomcat-2:8080 cookie liferay_2 check
    ``` 
    * This line defines our second server just as we did above.
1. **Delete** the original server definition:
    ```config
    server liferay-tomcat-1 liferay-tomcat-1:8080
    ```
	* Your liferay_cluster configurations should now look something like this:
	```config
	backend liferay_cluster
	    balance roundrobin
	    cookie JSESSIONID prefix
	    server liferay-tomcat-1 liferay-tomcat-1:8080 cookie liferay_1 check
	    server liferay-tomcat-2 liferay-tomcat-2:8080 cookie liferay_2 check
	```
1. **Save** the file.
1. **Open** a new _Terminal_ or _Command Line_ window at `liferay/liferay-tomcat`.
1. **Rebuild** the updated _haproxy_ service:
	```shell
	docker-compose build haproxy
	```
	* This will rebuild the _haproxy_ image with the changes we've just made.

With that, our HAProxy configuration is complete. We'll have to configure our Tomcat servers before we can use this updated image. We'll set up the Tomcat servers to run in a cluster and test out the updated HAProxy image in the next exercise.
