## Adding Users to the Portal

<div class="ahead">

#### Exercise Goals

* Add Users to your portal solution
* Make Users Site Members
* View a User's personal site

</div>

#### Create a User Account for Jason Murray
1. **Sign in** to Liferay if you're not already logged in.
    * Make sure you go to _localhost:8080_ in your browser once Liferay is started.
2. **Open** the _Applications Menu_.
3. **Go to** the _`Control Panel > Users > Users and Organizations`_.
4. **Click** the _Add_ icon at the top right to add a User.
5. **Type** `j.murray` for the _Screen Name_.
6. **Type** `jason.murray@livingstone.com` for the _Email Address_.
7. **Type** `Jason` for the _First Name_.
8. **Type** `Murray` for the _Last Name_.
9. **Click** _Save_ at the bottom.

#### Give Jason a Password
1. **Click** on _Password_ in the left menu.
2. **Type** a new password in the _New Password_ and _Enter Again_ fields.
    * For simplicity, you can just use _test_.
3. **Click** _Save_.

<br />
<br />
<br />

#### Give Jason Access to the Default Site
1. **Click** on _Memberships_ in the left menu.
2. **Click** _Select_ at the top right next to _Sites_.
3. **Click** _Livingstone Hotels & Resorts_.
4. **Click** _Save_.

#### Create a User Account for Hannah Jones
1. **Click** the _Applications Menu_.
* **Go to** the _`Control Panel > Users > Users and Organizations`_.
* **Click** the _Add_ button at the top right to add a User.
* **Type** `h.jones` for the _Screen Name_.
* **Type** `hannah.jones@livingstone.com` for the _Email Address_.
* **Type** `Hannah` for the _First Name_.
* **Type** `Jones` for the _Last Name_.
* **Click** _Save_ at the bottom.

#### Give Hannah a Password
1. **Click** on _Password_ in the left menu.
2. **Type** a new password in the _New Password_ and _Enter Again_ fields.
    * For simplicity, you can just use _test_.
3. **Click** _Save_.

#### Give Hannah Access to the Default Site
1. **Click** on _Memberships_ in the left menu.
2. **Click** _Select_ at the top right.
3. **Click** to choose the default Site.
    * If you followed the Module 1 set up, this should be _Livingstone Hotels & Resorts_.
4. **Click** _Save_.

<br />

#### Disable Email Address Verification
1. **Open** the _Applications Menu_.  
* **Go to** _`Control Panel > Instance Settings > User Authentication`_.
* **Click** the checkbox next to _Require strangers to verify their email address_ to uncheck the box.
* **Click** _Save_.

<div class="note">
Note: We only disable email address verification here since we are using a local instance that is not connected to an email server. It is recommended that you keep this default setting in your production instance.
</div>

#### Log in as Jason Murray
1. **Click** on the _Personal Menu_ at the top right.
* **Click** _Sign Out_ at the bottom.
* **Click** _Sign In_ at the top right.
* **Type** _jason.murray@livingstone.com_ for the _Email Address_.
* **Type** _liferay_ for the _Password_.
* **Click** _Sign In_.

#### View Jason Murray's Dashboard
1. **Click** on the _Personal Menu_.
* **Click** on _My Dashboard_.
* **Click** on the _Account Overview_ page.
	* Users won't see web content or announcements until posted by an Admin.

---

#### Bonus Exercises:
1. Create another User.
2. Give the user the Regular Administrator Role.
3. Add some contact information in the _Contact_ tab of the User Account.
4. Change your preferences in the _Preferences_ tab of the User Account.