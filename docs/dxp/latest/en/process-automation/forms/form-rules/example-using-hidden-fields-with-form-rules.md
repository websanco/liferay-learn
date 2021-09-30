# Example: Using Hidden Fields with Form Rules

You must first enable local network access in the Data Provider System Settings entry. This example requires two data providers with inputs and outputs and three form rules. The form has four fields:

| Form Element    | Element Label    | Description
| --------------- | ---------------  | ----------------------------------------------------------------------- |
| Data Provider 1 | Get Group Users  | Get the Site's/Group's Users and provide a list of their email addresses.
| Field 1         | Hidden Group ID  | A Numeric field that holds the Group ID of the current site.
| Field 2         | Who are you?     | Use a Select from List field that displays the returned email addresses.
| Form Rule 1     | _Not Applicable_  | If the group ID field is not empty, autofill the Select form List field with the email addresses of the Site's Users.
| Data Provider 2 | Get User by Email | Get the User by the selected email address and provide the first and last name.
| Field 3         | First Name        | Use a Text field for the User's first name.
| Field 4         | Last Name         | Use a Text field for the User's last name.
| Form Rule 2     | _Not Applicable_  | If the Select from List field is not empty, autofill the first and last name fields retrieved from the data provider.
| Form Rule 3     | _Not Applicable_  | If the Select from List field is empty, enable the first and last name fields so the User can manually enter data. If the condition isn't met and the first and last name fields are autofilled form the data provider, these fields re disabled on the form.

Each element of the example is described below.

- Use the _Who are you?_ field to choose your email address from a list of Users that belong to the current Site. This list is populated by a data provider calling the JSON web service _get-group-users_.
- Choosing an email address from the selector autofills the First Name and Last Name fields, and disables these fields so they cannot be edited.
- Leaving the ema

20123

hidden field: group id (this may be accessible to the form builder if they can access Site Settings &rarr; Site Configuration).
use the group ID to get the site's users with get-group-users
the greeting contains the full name ("Welcome Rex Nihilo!") can we use JsonPath to cut this down to the full name?
otherwise just use the email address to select the user account. 
autofill the First and Last Name fields based on the selected user account. Disable them if the account is selected?

data provider returns user id based on email address.
autofill the user id field, and hide it
the user id field is used as a data provider input
list or something
if user selects something , autofill
if the user selects 

Go to Control Panel > System Settings > Data Provider
Enable the setting Access Local Network
Go to Content & Data > Forms
Go to tab Data Providers
Create a new Data Provider with the following settings:
Name: get-countries
URL: http://localhost:8080/api/jsonws/country/get-countries
User Name: test@liferay.com
Password: test
Output 1 - Label: Countries
Output 1 - Path: $..name
Output 1 - Type: List
Output 2 - Label: Countries Length
Output 2 - Path: $.length()
Output 2 - Type: Number
Go to tab Forms
Add a new Form
Add a Text field
Add a Select From List field and name it Countries. On field Create List, choose From Autofill.
Add a Numeric field and name it Countries Length.
Add a Text field and name it Hidden Field.
Go to tab Rules
Add the following Rules:
If Field Text Is not empty Autofill Countries, Countries Length from Data Provider get-countries
If Field Countries Length Is greater than Value 0 Show Hidden Text
Publish the Form and visit its published URL
 Actual Result

Field Countries Length isn't populated with the number of existing Countries and the field Hidden Text isn't shown.

 Expected Result

Field Countries Length is populated with the number of existing Countries and the field Hidden Text is shown.

Options
from get-current-user, use his id to get the company-id. hide the companyId field, then 

From the top of my head, I'm thinking, you fill a text field with your email address, let's say, and Forms gets from a Data Provider your Rolescredit score and autofill a hidden field with this information. Then, the admin will have this useful data to approve your submission or not based on the hidden data.

get-user-by-email-address

if get-current-user works we can have the user fill in the boss's email address, get that user, store user's ID in a hidden field, then autofill another field based on the hidden field.

Perhaps you have a form that asks new Users for certain information depending on how many users there are in the portal.

Paragraph field: Submit this form to be entered into a raffle. Please fill out the information below

The fields here depend on the get-company-users-count result.

if `<10` we can ask for t-shirt size

if `>=10` we can ask for favorite lollipop flavor

1. Configure a Data Provider with one output: the number of Users registered in the portal.
   - 

1. Create a form with the following fields:

1. Add a text field labeled _Test_, and give it a predefined value of _Test_. In the Advanced tab of the form field configuration sidebar, enable _Hide Field_.

1. Configure the rule:

1. Click the _Rules_ tab.
1. Click the Add (![Add](../../../images/icon-add.png)) button.
1. Select _Test_ from the Condition dropdown menu.
1. Create the condition: _Is equal to_ &rarr; _Value_ &rarr; _Yes_.
1. Select _Enable_ under the Actions dropdown menu.
1. Select _List all medications_.
