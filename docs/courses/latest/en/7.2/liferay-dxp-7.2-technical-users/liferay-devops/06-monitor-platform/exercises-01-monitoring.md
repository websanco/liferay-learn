<h3 class="exercise">Exercises</h3>

## Monitoring your platform

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Configure your DXP instance for JConsole access</li>
		</ul>
</div>

#### Bonus Exercise: Configure your DXP instance for JConsole access

If you want to access vital data of your JVM, or the running application on top of it, JConsole can provide you with the basic data:

* Memory Consumption
* Thread Count
* JMX-Bean information, like database connection pool and cache utilization
* CPU consumption
* Garbage Collection insight

We're not providing this step by step. In order to prepare Tomcat for a connection from JConsole, we'll need to provide the proper access. As this is a Bonus Exercise, we'll leave it up to you and give you only some quick&dirty hints (e.g. no authentication, no encryption. Don't do this in production!)

Here's an example for how to edit Tomcat's setenv.sh (that we're including in the Docker image anyway):

	```shell
    CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=512m -XX:MetaspaceSize=512m -XX:NewSize=1536m -XX:SurvivorRatio=7 "
    CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote=true "
    CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.local.only=false "
    CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.authenticate=false "
    CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.ssl=false "
    CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.port=9990 "
    CATALINA_OPTS="$CATALINA_OPTS -Dcom.sun.management.jmxremote.rmi.port=9991 "
    CATALINA_OPTS="$CATALINA_OPTS -Djava.rmi.server.hostname=YOUR-IP "
	```

You may or may not need the server.hostname setting - i.e. substitute YOUR-IP. RMI will signal the client (JConsole) to which IP it should connect, that's what it's for.

Of course, you'll need to map the port from the container to your local machine (in docker-compose.yml)

Once you got this running, Inspect the JVM - e.g. memory consumption, cache-JMX beans, database connection pool etc.
