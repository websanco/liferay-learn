# Sanitizing User Data

One of the technically challenging requirements of the General Data Protection Regulation (GDPR) is _the right to be forgotten_. The purpose of this article is not to go into the details of this requirement, but to show you two pieces of functionality that can assist you in satisfying the requirement:

- Personal Data Erasure 
- Data Anonymization

Being _forgotten_ by software is removing the ability for even administrative Users to glean information that could lead to knowing the identity of the User whose personal data was erased.

Conceptually, forgetting a User means two things, at a minimum:

* Erasing the User's identifying information from the system. In Liferay DXP, this  entails removing the User from database tables and search indexes.
* Erasing or anonymizing content the User has interacted with so it cannot be tracked to a real person.

_Isn't User deactivation and deletion enough?_
Deleting removes the User from the table of Users in the database. The User's information is preserved in other locations, however. In a standard User deletion scenario, all of a User's personally created content is still assigned to the User by the system's identifiers (User ID and User Name) and still appears in the UI next to the content. This unintentional preservation of user-identifying data is inadequate for satisfying some of the GDPR requirements and is the primary reason why the data erasure functionality was added.

## The Personal Data Erasure Screen

Whether deleting or anonymizing, to begin sanitizing a User's data,

1. Open the Applications Menu (![Applications Menu](../../images/icon-applications-menu.png)) and go to Control Panel &rarr; Users &rarr; Users and Organizations.

1. Click the Actions button for a User (![Actions](../../images/icon-actions.png)) and select *Delete Personal Data*.

   > If you have not deactivated the user, you will be asked to do so.

   The User's Personal Data Erasure screen appears.

You can browse all data the user has posted on the system. Click *Personal Site* to browse data from that site.

![From here, you can browse all data the user posted on his or her personal Site.](./sanitizing-user-data/images/users-data-erasure-personal.png)

Click *Regular Sites* to browse any data posted in regular Liferay sites.

![Choose Regular Sites to browse all data posted by the user on administratively-created Sites.](./sanitizing-user-data/images/users-data-erasure-regular.png)

To review the user's data, click the item. For example, Pepper seems to have posted a blog entry on her personal Site. Clicking that entry reveals the title of that blog entry.

![Pepper's blog entry might need review.](./sanitizing-user-data/images/users-data-erasure-blog.png)

To review any entry, click it. You're brought to the edit mode of the application (in this case, Blogs), where you can make any changes to the content that are necessary. 

To manage (anonymize or delete) all the items for an application at once: 

1. Click the Actions button (![Actions](./sanitizing-user-data/images/icon-actions.png)) for the application.

1. If you're sure all items for an application can be safely deleted, choose *Delete*. 

1. If you're sure simple anonymization is good enough for all of an application's items, choose *Anonymize*.

Use the interface to browse through the Sites, applications, and data. 

## Managing Anonymized Data: The Anonymous User

Deleting User data via the [Personal Data Erasure screen](#the-personal-data-erasure-screen) is the safest way to honor _right to be forgotten_ requests. When User data must be preserved, automatic anonymization of the data is in order. Users being anonymized must have their identifiers (for example, User ID and User Name) removed from content they've interacted with. However, portal content usually requires this information for its applications to work properly. Therefore, the User's identifiers must be replaced by something, or someone. Meet the new User, *Anonymous Anonymous*, identity swapper *extraordinaire*. This deactivated User is dedicated to be the User whose identifiers are assigned to anonymized content. This identity swap is an important step in the anonymization process, but additional [manual intervention](#manual-anonymization) may be necessary to achieve full anonymization.

![Anonymized content is presented with the User Anonymous Anonymous's identifying information.](./intro/images/users-anonymized-content.png)

If you'd rather start from scratch or assign an existing User to be the Anonymous User, get rid of *Anonymous Anonymous* and configure your own Anonymous User.

The anonymous user is programmatically created for each instance the first time an Administrator clicks *Delete Personal Data* from a User's Actions menu (![Actions](./intro/images/icon-actions.png)). If you haven't yet done that, no Anonymous User exists.

The easiest way to set up a new User as the Anonymous User is to edit an existing Anonymous User configuration, passing in the new Anonymous User's User ID. 

To edit an existing configuration:

1. Go to Control Panel &rarr; Configuration &rarr; System Settings &rarr; Users &rarr; Anonymous User.

1. Edit the existing configuration, providing a different User ID.

   Get the User ID from Control Panel &rarr; Users &rarr; Users and Organizations. Click on the User and find the User ID in the Information screen of the Edit User application.

1. Click *Update*.

To create a new Anonymous User:

1. [Create a User](/docs/7-2/user/-/knowledge_base/u/adding-editing-and-deleting-users#adding-users) use for data anonymization. Alternatively, you can use an existing User.

1. If there's already an Anonymous User configured for the instance, there are two ways to remove it: 
 
   Delete the User entirely. Deleting the User simultaneously deletes its configuration as the Anonymous User. Go to Control Panel &rarr; Users &rarr; Users and Organizations. If it's an active User, first deactivate, then delete the User. The default Anonymous Anonymous User is deactivated by default. Simply delete the User in this case. Click the Actions button (![Actions](./intro/images/icon-actions.png)) and select *Delete*.

   If you don't want to delete the User, just delete the User's configuration as the Anonymous User. Go to Control Panel &rarr; Configuration &rarr; System Settings &rarr; Users &rarr; Anonymous Users.

1. Add a new Anonymous User configuration. Click the *Add* button.

1. Fill out the two fields, Company ID and User ID. 

   Get the Company ID from Control Panel &rarr; Configuration &rarr; Virtual Instances. The Instance ID and Company ID are the same.

   Get the User ID from Control Panel &rarr; Users &rarr; Users and Organizations. Click on the User and find the User ID in the Information screen of the Edit User application.

There can only be one Anonymous User configured for each instance.

![Assign your own Anonymous User from Control Panel &rarr; Configuration &rarr; System Settings &rarr; Users &rarr; Anonymous User.](./intro/images/users-anonymous-config.png)

## Delete the User

Once all data is reviewed, deleted, edited, and/or anonymized as appropriate, delete the User. A dialog box pops up automatically when you're finished in the Personal Data Erasure screen. This step is simple: Click *OK*.

![To finish the data erasure process, delete the User.](./sanitizing-user-data/images/users-delete-user.png)

Now the User's data is anonymized or deleted, and the User is also deleted.


## Manual Anonymization

Anonymizing just the User's identification fields and deleting the User is often not enough. If a User named Ziltoid Omniscient complains about The Lunar Resort's coffee in a Message Boards Message and in it signs the post with _Supreme Leader of Ziltoidea 9_, anonymizing this post would remove the User's name (Ziltoid Omnisicent) and replace it with Anonymous Anonymous, but searching the Internet for _Ziltoidea_ quickly reveals that the post was written by [Ziltoid the Omniscient](https://en.wikipedia.org/wiki/Ziltoid_the_Omniscient). Because there can be user-entered personal data within the content of an application, you must manually edit such content to remove identifying details. 

![Even though this Message Boards Message (a comment on a blog post in this case) is anonymized, it should be edited to remove User Associated Data from the content of the message.](./intro/images/users-partial-anonymization.png)

