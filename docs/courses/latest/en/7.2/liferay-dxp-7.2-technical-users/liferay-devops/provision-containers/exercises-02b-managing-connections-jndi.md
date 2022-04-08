# Manage JNDI Connections

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Update the portal.properties file for the Wildfly server</li>
			<li>Update the standalone.xml file for the Wildfly server</li>
			<li>Test changes to the outgoing mail configuration</li>
		</ul>
</div>

<div class="note">
    Note: Completed solutions for the <code>portal-ext.properties</code> and <code>standalone.xml</code> files can be found in liferay/liferay-wildfly/solutions. 
</div>

#### Update portal-ext.properties

Let's configure the JNDI names for the mail and data servers for our Wildfly instance.

1. **Open** the `portal-ext.properties` file for the Wildfly server.
	* This file can be found at _liferay-wildfly/config_
1. **Find** the _JDBC_ section around line 21.
1. **Replace** the `# Add datasource JNDI name here` comment with the following:
    ```properties
    jdbc.default.jndi.name=java:jboss/jdbc/LiferayConnectionPool
    ```
    * This sets the JNDI name for the JDBC datasource to _LiferayConnectionPool_.
1. **Find** the _Mail_ section around line 31.
1. **Replace** the `# Add mail session name here` comment with the following:
    ```properties
    mail.session.jndi.name=java:jboss/mail/LiferayMailSession
    ```
    * This sets the mail session object's JNDI name to _LiferayMailSession_. We'll reference this JNDI name when connecting to our "mail server" in Liferay.
1. **Save** the file.

Now let's update our `standalone.xml` file to reflect these changes.

<br>

#### Update standalone.xml

If you want to shortcut and save some typing, remember that you can find the resulting file in `liferay/liferay-wildfly/solutions`. 
Optionally, check the diff between the original file and the solution before applying it blindly.

1. **Open** the `standalone.xml` file for the Wildfly server.
    * This file can also be found at _liferay-wildfly/config_
1. **Find** the _Add Datasource JNDI Name_ comment around line 164.
    * You may want to use your text editor's _Find_ feature, if available.
1. **Replace** the `<!-- Add Datasource JNDI Name, driver and user-name -->` comment with the following:
    ```xml
    <datasource jndi-name="java:jboss/jdbc/LiferayConnectionPool" pool-name="LiferayConnectionPool" enabled="true" use-java-context="true">
        <connection-url>jdbc:hsqldb:/opt/liferay/data/hypersonic/lportal;hsqldb.write_delay=false</connection-url>
        <driver>hsql</driver>
        <security>
            <user-name>sa</user-name>
            <password></password>
        </security>
    </datasource>
    ```
    * Here we link the _LiferayConnectionPool_ JNDI name to the default Hypersonic database for our Wildfly server.
1. **Find** the _HSQL Datasource Class_ comment around line 184.
1. **Replace** the `<!-- Add HSQL Datasource Class-->` comment with the following:
    ```xml
    <driver name="hsql" module="com.liferay.portal">
        <xa-datasource-class>org.hsqldb.jdbc.JDBCDriver</xa-datasource-class>
    </driver>
    ```
    * Here we implement our datasource as belonging to the XA (Extended Architecture) class. 
1. **Find** the _Add Mail Session JNDI Name_ comment around line 437.
1. **Replace** the `<!-- Add Mail Session JNDI Name Here -->` comment with the following:
    ```xml
    <mail-session jndi-name="java:jboss/mail/LiferayMailSession" name="fake-smtp">
        <smtp-server ssl="true" outbound-socket-binding-ref="fake-smtp" />
    </mail-session>
    ```
    * Here we link the _LiferayMailSession_ JNDI name to the FakeSMTP mail server we-'ll be using in our exercises.
1. **Find** the _Add FakeSMTP port_ comment around line 590.
1. **Replace** the `<!-- Add FakeSMTP port here -->` comment with the following:
    ```xml
    <outbound-socket-binding name="fake-smtp">
        <remote-destination host="fake-smtp" port="25"/>
    </outbound-socket-binding>
    ```
    * Here we configure the port for outbound messages from our FakeSMTP server.
1. **Save** the file.

Before we rebuild our Wildfly container image with our changes, let's quickly update the docker-compose file.

#### Update Wildfly Dependencies

1. **Open** the `docker-compose.yml` file for the Wildfly stack.
1. **Add** the `depends_on` lines between the `container_name` and `networks` references in the _liferay-wildfly_ service.
Your wildfly service should now look like this:
```dockerfile
liferay-wildfly:
    build: liferay-wildfly
    container_name: liferay-wildfly
    depends_on:
      - fake-smtp
    networks: 
      - network-liferay-wildfly
    ports: 
      - 9090:8080
      - 127.0.0.1:31311:11311
    volumes:
      - ./volumes/liferay-wildfly/osgi/configs:/opt/liferay/osgi/configs
      - volume-liferay-wildfly-data:/opt/liferay/data
```
    * This will make the _fake-smtp_ service a dependency for our _liferay-wildfly_ service. When we create a container from our updated image, our fake-smtp service will start up along with our _liferay-wildfly_ service.
1. **Save** the file.

#### Rebuild the liferay-wildfly Service

1. **Open** a Terminal window in your _liferay/liferay-wildfly_ directory.
1. **Stop** the _liferay-wildfly_ service if the container is already running.
    * Run `docker ps` to see running containers.
    * Run the `docker-compose down` command to stop running containers.
1. **Run** the following command to build the _fake-smtp_ service:
    ```shell
    docker-compose build fake-smtp
    ```
1. **Run** the following command to rebuild the _liferay-wildfly_ service:
    ```shell
    docker-compose build liferay-wildfly
    ```
    * Docker builds its images in _layers_ and caches each layer. Docker will reuse every image layer that has not been affected by our configuration changes which should lead to a much quicker build/rebuild
    time for our Wildfly image.
    * Now that our images have been built, let's create our Wildfly container.
1. **Run** the following command to start the _liferay-wildfly_ service:
    ```shell
    docker-compose up -d liferay-wildfly
    ```
    * Because we set _fake-smtp_ as a dependency for running the `up` command for _liferay-wildfly_ will also start _fake-smtp_.
1. **Run** the following command in a new Terminal window or tab to see the _fake-smtp_ logs:
    ```shell
    docker-compose logs -f fake-smtp
    ```
    * You can also run `docker-compose logs -f liferay-wildly` in a new Terminal window to see the logs for the Wildfly server.
    * Make sure you are in the _liferay-wildfly_ directory.

Let's test our new mail server setup.

#### Test Outgoing Mail

1. **Go to** _localhost:9090_ in your browser.
1. **Sign in** to Liferay.
    * Email: `test@liferay.com`
    * Password: `test`
1. **Click** to open the _Menu_.
1. **Go to** _Control Panel → Users → Users and Organizations_.
1. **Click** on the _Add User_ button.
1. **Create** a new user and set their email address to test1@liferay.com.
1. **Click** _Save_.

When the new user is created an e-mail is "sent" to the address for the new user. We can see confirmation in the _fake-smtp_ logs.

```logs
fake-smtp | 07 May 2019 17:19:10 INFO  org.subethamail.smtp.server.ServerThread - SMTP server /0.0.0.0:25 started 
fake-smtp | 07 May 2019 17:23:55 DEBUG org.subethamail.smtp.server.Session - SMTP connection from liferay-wildfly.network-liferay-wildfly/172.22.0.3, new connection count: 1 
fake-smtp | 07 May 2019 17:23:55 DEBUG org.subethamail.smtp.server.Session - Server: 220 91e1a740278b ESMTP SubEthaSMTP null
fake-smtp | 07 May 2019 17:23:55 DEBUG org.subethamail.smtp.server.Session - no more lines from client
```

This is now a fully-functioning installation of Liferay on a standalone app server.
