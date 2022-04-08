<h3 class="exercise">Exercises</h3>

# Update an Organization's Password Policy

<div class="ahead">
	<h3>Exercise Goals:</h3>
		<ul>
			<li>Create the organization</li>
			<li>Update the Password Policy</li>
		</ul>
</div>

#### Create a New Organization

To demonstrate the power and flexibility of Liferay's password policies, we'll create a new, incredibly strict policy specifically for admin users. To do this, we'll create a new organization and assign it the Administrator role, which we'll assume is where all Administrators are assigned.

1. <span class="action">Go to</span> *localhost:8081* in your web browser. This should be the port where your liferay-tomcat-1 container is pointing.
    - You can start the container by running `docker-compose up -d liferay-tomcat-1`.
2. <span class="action">Sign in</span> to Liferay if you are not already logged in.
3. <span class="action">Go to</span> _`Control Panel → Users → Users and Organizations`_ in the *Menu*.
4. <span class="action">Click</span> the *Organizations* tab.
5. <span class="action">Click</span> the *Add* button to create a new organization.
6. <span class="action">Type</span> to name the organization *Administrators*.
7. <span class="action">Click</span> *Save*.

<img src="../images/chapter-4/administrators-org.png" style="max-width:100%;">

#### Update the Password Policy

1. <span class="action">Go to</span> _`Control Panel → Users → Password Policies`_ in the *Menu*.
2. <span class="action">Click</span> the *Add* button to add a new policy.
3. <span class="action">Type</span> _Administrators_ as the _Name_.
4. <span class="action">Click</span> to expand the _Password Changes_ section.
5. <span class="action">Click</span> the _Changeable_ slider so that it says _YES_.

<img src="../images/chapter-4/new-password-policy.png" style="max-width:100%;">

#### Define Password Syntax

1. <span class="action">Click</span> to expand the _Password Syntax Checking_ section.
2. <span class="action">Click</span> the _Enable Syntax Checking_ slider so that it says _YES_.
3. <span class="action">Type</span> the following:
    - _7_ in the Minimum Alpha Numeric field
    - _10_ in the Minimum Length field
    - _1_ in the Minimum Lower Case field
    - _1_ in the Minimum Numbers field
    - _1_ in the Minimum Symbols field
    - _1_ in the Minimum Upper Case field

<img src="../images/chapter-4/password-syntax.png" style="max-width:100%;">

#### Complete and Save the Password Policy

1. <span class="action">Click</span> to expand the _Password History_ section.
2. <span class="action">Click</span> the _Enable History_ slider so that it says _YES_.
3. <span class="action">Click</span> to expand the _Password Expiration_ section.
4. <span class="action">Click</span> the _Enable Expiration_ slider so that it says _YES_.
5. <span class="action">Click</span> to expand the _Lockout_ section.
6. <span class="action">Click</span> the _Enable Lockout_ slider so that it says _YES_.
7. <span class="action">Type</span> _5_ in the _Maximum Failure_ field.
8. <span class="action">Click</span> _Save_ to save the policy.

<img src="../images/chapter-4/password-configured.png" style="max-width:100%;">

#### Assign the Password Policy
1. <span class="action">Click</span> the *Administrator* password policy.
2. <span class="action">Click</span> the *Assignees* tab at the top for the new *Administrators* policy.
3. <span class="action">Click</span> the *Organizations* tab.
4. <span class="action">Click</span> the *Add* button.
5. <span class="action">Check</span> the box next to *Administrators*.
6. <span class="action">Click</span> *Add.*

Your password policy is now configured for Administrators!

<img src="../images/chapter-4/password-assignees.png" style="max-width:100%;">

#### Test the New Password Policy

Now let's add a new admin user to the organization and change the password to test the new policy.

1. <span class="action">Go to</span> _`Control Panel → Users → Users and Organizations`_ in the *Menu*.
1. <span class="action">Click</span> on the _Add_ button to add a new user.
1. <span class="action">Type</span> in information for a new user.
1. <span class="action">Click</span> *Save* to create the new user.
1. <span class="action">Click</span> on the _Organizations_ link in the _General_ tab of the user you just created.
1. <span class="action">Click</span> on _Select_ to choose an organization for the new user.
1. <span class="action">Click</span> _Choose_ next to the _Administrators_ organization.
1. <span class="action">Click</span> _Save_.

The user is now a member of the organization!

#### Change Your Password

1. <span class="action">Click</span> on the *Password* section under the _General_ tab for the new user.
1. <span class="action">Add</span> a password that doesn't meet the criteria set.
1. <span class="action">Click</span> *Save*.
    - What happens?
1. <span class="action">Replace</span> the password with something that should work.
1. <span class="action">Click</span> *Save*.

Your new policy is in effect. How would we change the policy for all users? What are some other useful scenarios for the password policy configuration?
