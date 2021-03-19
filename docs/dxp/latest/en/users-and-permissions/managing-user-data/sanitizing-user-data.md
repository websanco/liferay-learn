# Sanitizing User Data

One of the technically challenging requirements of the General Data Protection Regulation (GDPR) is _the right to be forgotten_. The purpose here is not to go into the details of this requirement, but to show two pieces of functionality that can assist you in satisfying the requirement: Personal Data Erasure and Data Anonymization.

Being _forgotten_ by software is removing the ability for even administrators to glean information that could lead to knowing the identity of the User whose personal data was erased or anonymized.

Conceptually, forgetting a User means two things, at a minimum:

* Erasing the User's identifying information from the system. In Liferay DXP, this entails removing the User from database tables and search indexes.
* Erasing or anonymizing content the User has interacted with so it cannot be tracked to a real person.

``` tip::
   `Isn't User deactivation and deletion enough?`

   Deleting removes the User from the table of Users in the database. The User's information, however, is preserved in other locations. In a standard User deletion scenario, all of a User's personally created content is still assigned to the User by the system's identifiers (User ID and User Name) and still appears in the UI next to the content. This unintentional preservation of user-identifying data is inadequate for satisfying some of the GDPR requirements and is the primary reason why the data erasure functionality was added.
```

## The Personal Data Erasure Screen

User data deletion and anonymization both happen in the same place: the Personal Data Erasure screen:

1. Open the Applications Menu (![Applications Menu](../../images/icon-applications-menu.png)) and go to Control Panel &rarr; Users &rarr; Users and Organizations.

1. Click the Actions button for a User (![Actions](../../images/icon-actions.png)) and select *Delete Personal Data*. If you have not deactivated the User, you are asked to do so.

   The User's Personal Data Erasure screen appears.

You can browse all data the User has posted on the system. Click *Personal Site* to browse data from that Site.

![From here, you can browse all data the User posted on his or her personal Site.](./sanitizing-user-data/images/01.png)

Click *Regular Sites* to browse data posted in regular Liferay Sites.

![Choose Regular Sites to browse all data posted by the User on administratively-created Sites.](./sanitizing-user-data/images/02.png)

To review the User's data, click the item. For example, Pepper seems to have posted a blog entry on her personal Site. Clicking that entry reveals the title of that blog entry.

![Pepper's blog entry might need review.](./sanitizing-user-data/images/03.png)

To review any entry, click it. You're brought to the application's edit mode (in this case, Blogs), where you can make any changes to the content that are necessary. 

To manage (anonymize or delete) all the items for an application at once, 

1. Click the Actions button (![Actions](../../images/icon-actions.png)) for the application.

1. If you're sure all items for an application can be safely deleted, choose *Delete*. 

1. If you're sure simple anonymization is good enough for all of an application's items, choose *Anonymize*.

Use the interface to browse through the Sites, applications, and data. 

## Delete the User

Once all data is reviewed, deleted, edited, and/or anonymized as appropriate, delete the User. A dialog box pops up automatically when you're finished in the Personal Data Erasure screen. This step is simple: Click *OK*.

![To finish the data erasure process, delete the User.](./sanitizing-user-data/images/04.png)

Now the User's data is anonymized or deleted, and the User is also deleted.

## Manual Anonymization

Anonymizing just the User's identification fields and deleting the User is often not enough. For example, if a User named John Smith inputs personally identifiable information into a form, the text input fields are not anonymized by the UAD framework. 

![A user inputs identifiable information into an input text field.](./sanitizing-user-data/images/05.png)

After anonymization, notice the *Author* field has been sanitized but the user input text remains and must be manually removed.

![User input text is not anonymized.](./sanitizing-user-data/images/06.png)

User generated content like this must be manually identified and edited to remove personally identifying information.