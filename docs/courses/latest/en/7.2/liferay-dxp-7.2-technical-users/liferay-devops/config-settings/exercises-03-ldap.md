<h3 class="exercise">Exercises</h3>

## Connecting to an LDAP Server

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Start the ApacheDS container</li>
			<li>Connect to the LDAP server</li>
			<li>Test and import LDAP users</li>
		</ul>
</div>

#### Make Sure the LDAP Server is Running

1. **Open** the _docker-compose.yml_ file for the Tomcat stack.
    * You can find the file at _liferay/liferay-tomcat_.
2. **Remove** the comment symbol (`#`) from the _apacheds_ dependency for the _liferay-tomcat-1_ service around line 51:
    ```dockerfile
    depends_on:
      - apacheds
    ```
3. **Save** the file.
4. **Open** a _Terminal_ window at _liferay/liferay-tomcat_.
5. **Build** the _apacheds_ service:
    ```shell
    docker-compose build apacheds
    ```
6. **Start** the _tomcat-1_ service:
    ```shell
    docker-compose up -d liferay-tomcat-1
    ```
7. **Type** `docker ps`.
8. **Press** _Enter_.
    - You should see both the tomcat-1 server and the apacheds containers running along with the mysql container, as all these services are required by the _liferay-tomcat-1_ service.

#### Start up Apache Directory Studio

If the ApacheDS LDAP server was configured to run on port 389 (the default), you would need to become _root_ before you could start the server. We've configured the LDAP server to run on port 10389 so this isn't necessary.

1. **Run** the `ApacheDirectoryStudio` installer for your operating system, located in the `liferay/liferay-tomcat/services/apacheds` folder.
    * Walk through the installer for your OS.
1. **Open** _Apache Directory Studio_.

<img src="../images/chapter-5/apache-directory-studio-empty.png" style="max-width:100%;">

#### Connect Apache Directory Studio with the LDAP Server

1. **Click** _`File → New...`_.
2. **Choose** _`LDAP Browser → LDAP Connection`_ in the _Select a wizard_ pop-up.
3. **Click** _Next_.
4. **Type** _Apache LDAP_ as the _Connection name_.
5. **Type** the following:
    - **127.0.0.1** for the _Hostname_
        * Linux users may need to use **localhost** instead.
    - **10389** for the _Port_
6. **Click** the _Check Network Parameter_ button.
    - A pop-up should appear that says: _The connection was established successfully_. If you do not see that pop-up, or it gives you an error, double-check the LDAP container and the fields you just filled out.
7. **Click** the _Next_ button.

<img src="../images/chapter-5/network-parameters.png" style="max-width:100%;">

<br>

#### Authenticate the Connection

1. **Type** in the following fields:
    - **uid=admin,ou=system** for the *Bind DN*
    - **secret** for the *password*
2. **Click** *Check Authentication*.
3. **Click** *Finish*.

<img src="../images/chapter-5/check-authentication.png" style="max-width:100%;">

#### Browse the LDAP Directory

Let's browse our LDAP directory and look at the entries we're going to import into Liferay.

1. **Expand** _dc=training,dc=liferay,dc=com_ under the _Root DSE_.
2. **Expand** _ou=people_.
    - Check to make sure there is a list of users from _cornelius.buckley_ to _william.bush_.

<img src="../images/chapter-5/ldap-users.png" style="max-width:100%;">

We will import the entries belonging to the *Users* and *Groups* organization units into Liferay. All the LDAP entries besides the *Users* and *Groups* were created by OpenAM.

<div class="note">
    Note: We're also using our OpenDJ LDAP server for OpenAM's (an SSO server) configuration data store and user data store.
</div>

#### Navigate to LDAP Configuration Settings in Liferay

1. **Go to** *localhost:8081* in your web browser if it isn't already up.
2. **Sign in** to Liferay as an administrator.
    - User: test@liferay.com
    - Password: test
3. **Go to** _`Control Panel → Configuration → Instance Settings`_ in the *Menu*.
4. **Click** on _LDAP_ under the _Security_ section.
5. **Click** on *Servers* on the left-hand side.
6. **Click** *Add*.

#### Configure LDAP Connection Settings

1. **Type** *Apache LDAP* for the _Server Name_.
2. **Click** the _Apache Directory Server_ radio button.
3. **Type** the following under the *Connection* heading:
    - **Base Provider URL:** *ldap://apacheds:10389*
    - **Base DN:** *dc=training,dc=liferay,dc=com*
    - **Principal:** *uid=admin,ou=system*
    - **Security Credential:** *secret*
4. **Click** on the *Test LDAP Connection* button to confirm that Liferay can connect to the LDAP server.
5. **Close** the pop-up.

<img src="../images/chapter-5/ldap-connected.png" style="max-width:100%;">

#### Test the New LDAP Users

1. **Go to** the _Users_ section.
2. **Check** that the following fields are filled out properly:
    - **Authentication Search Filter:** *(mail=@email_address@)*
    - **Import Search Filter:** *(objectClass=inetOrgPerson)*
    - **UUID=** *uuid*
    - **Screen Name=** *cn*
    - **Email Address=** *mail*
    - **Password=** *userPassword*
    - **First Name=** *givenName*
    - **Last Name=** *sn*
    - **Job Title=** *title*
    - **Group=** *groupMembership*
3. **Click** on *Test LDAP Users* to confirm the fields are entered correctly.
    - You should see the same list of users we saw earlier (albeit in a different order).
4. **Close** the pop-up.

*Group* is required for mapping LDAP groups to Liferay user groups. The other user mappings are required by Liferay.

<img src="../images/chapter-5/ldap-users-connected.png" style="max-width:100%;">

#### Test the New LDAP Groups

1. **Go to** the *Groups* section.
2. **Check** that the following fields are filled out properly:
    - **Import Search Filter:** *(objectClass=groupOfUniqueNames)*
    - **Group Name:** *cn*
    - **Description:** *description*
    - **User:** *uniqueMember*
    - The first group mapping is required by Liferay, the second one is optional, and the last one, User, is required for mapping LDAP groups to Liferay user groups.
3. **Click** on the *Test LDAP Groups* button to confirm the fields are entered correctly.
    - You should see the three groups listed in the image below. You can also double-check that the groups are the same as those listed under _ou=groups_ in Apache Directory Studio.
4. **Close** the pop-up.
5. **Click** on the *Save* button to add our LDAP server.

<img src="../images/chapter-5/ldap-groups.png" style="max-width:100%;">

You've just connected the LDAP server to the platform.

<!-- Could not get import to work by restarting or even rebuilding the tomcat-1 instance.

#### Test the LDAP Configuration

1. **Click** on the *Import* section on the left-hand side of the page.
2. **Check** the *Enable Import* box.
3. **Click** *Save* at the bottom of the page.
4. **Open** a private browsing window.
5. **Go to** *localhost:8081* in the private browsing window.
6. **Sign in** as a user:
    - User: *cbuckley@royalnavy.mod.uk*
    - Password: *password*

<img src="../images/chapter-5/sign-in.png" style="max-width:100%;">

Since we did not set up user import, users are never imported from LDAP en masse. Instead, LDAP users are imported upon log-in. We can configure user import so that users will be imported en masse from LDAP according to the provided interval.

#### Enable User Import

Now, let's import all of our LDAP users into Liferay.

1. **Go to** the first liferay instance in your web browser.
2. **Go to** _`Control Panel → Configuration → Instance Settings`_ in the *Menu* (if you are not already there).
3. **Click** on the *LDAP* tab under the *Authentication* section.
4. **Click** the boxes for *Enable Import* and *Enable Import on Startup*.
5. **Click** *Save*, leaving the rest of the defaults.
6. **Restart** Liferay to begin the import.
    - Open the _Terminal_ or PowerShell
    - Type: `docker restart liferay-tomcat-1`
    - Press _Enter_.

<img src="../images/chapter-5/tomcat-restart.png" style="max-width:100%;">

#### Verify the Import

Once Liferay has restarted and the import is complete, we can look at Liferay's user list to verify that the import was successful.

1. **Go to** *localhost:8081* in your browser.
2. **Sign in** as the _Test_ administrator.
3. **Go to** _`Control Panel → Users → Users and Organizations`_ in the *Menu*. You will see the LDAP users listed.

<img src="../images/chapter-5/.png" style="max-width:100%;">
-->
