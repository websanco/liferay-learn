# Adding Members to Sites

Users and Sites are central concepts in Liferay DXP. Sites are where all your content and pages are stored, and Users access and create that content. While User management is covered in depth in [User Management](TODO), there are some configuration options specific to Site management and assigning Users to sites that are relevant here:

* [Managing Site Membership](#managing-site-membership)
* Configuring Users to Automatically Join a Site
* [Creating Teams]((./creating-teams-for-sites.md)) or groups of Site Members for various site functions

The steps below show how to add Users manually to Sites and how to provide options for self management.

## Managing Site Membership

Administrators can manage Site members from that Site's *Site Membership* page.

1. Open *Site Administration* and select the Site that you want to manage members for.
1. Click on *People* &rarr; *Memberships*

From here you can manage Site memberships, Organizations, and User Group associations. You can learn more about those in [Users and Organizations](TODO). On this page, you can see a list of all of the current Users of the Site and you can add or remove user memberships from the Site.

![The current members of the Site are displayed on the Site Memberships page.](./adding-members-to-sites/images/01.png)

### Adding Members to a Site

Follow these steps to make an existing User a member of the Site:

1. Click the *New* (![Add User](../../../../images/icon-add.png)) button in the top right of the screen.
1. Use *Filter and Order* or the *Search* function to locate the User you want to add to the Site.
1. Select the User(s) you want to add and click *Done*.

On the *Assign Users to This Site* screen, all users eligible to be added to the Site appear. Deactivated users do not appear. Site members also appear, but with a greyed-out checkbox.

![You can view the list of Users that are members of the Site. Note that the current members are visible but cannot be added or removed here.](./adding-members-to-sites/images/02.png)

### Removing User Membership from a Site

There are two ways to remove a User from a Site. To remove an individual member, follow these steps:

1. Click the *Actions* (![Actions](../../../../images/icon-actions.png)) icon for the User that you want to remove.
2. Select *Remove Membership*.
3. In the pop-up that appears, confirm the removal.

![You can remove individual members from a Site.](./adding-members-to-sites/images/03.png)

To bulk remove Users from a Site, follow these steps:

1. Click the checkbox for each User that you want to remove.
1. In the menu at the top of the page, click the `X` icon to remove the Users from the Site.
1. In the pop-up that appears, confirm the removal.

Removed Users lose access to the Site's Private Pages and membership in any Site Roles or Teams they had.

### Assigning Site Roles

Roles grant permissions in Liferay Portal. Roles can be assigned for the entire instance or just for one specific Site or Organization. Site Roles assign permissions for a specific Site. Learn more about roles in [Roles and Permissions](../../../users-and-permissions/roles-and-permissions/README.rst).

Follow these steps to assign Site Roles to Users:

1. Select a User or Users and click *Assign Site Roles* (either through the Actions menu or the menu at the top). This take you to the *Assign Site Roles* screen.
1. Select the Roles that you want to assign to the selected User(s).
1. Click *Done*.

    ![You can assign Site Roles to Users.](./adding-members-to-sites/images/04.png)

## Joining Sites with the My Sites Application

The My Sites Directory application lists the Sites a User belongs to. This application is added to User [Dashboard pages](../managing-personal-sites.md) by default. You can view the available open and restricted Sites by adding the My Sites application to a page and accessing the *Available Sites* tab. You can request access to any of the Sites you're not a member of by selecting the Site's *Options* button (![Options](../../../images/icon-actions.png)) and clicking *Join*.

![The My Sites Application displays the Sites you're a member of and the ones you can join.](./adding-members-to-sites/images/05.png)
