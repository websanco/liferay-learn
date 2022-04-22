## Gathering User Feedback with Forms

Surveys and feedback forms are powerful tools that can be leveraged by any business. Integrating these assets into your platform is essential for gathering and organizing data from your users. Being able to create and customize forms with various input fields is a powerful out-of-the-box feature of Liferay DXP.

#### Collecting Feedback Online at Livingstone {#livingstone}

Josiah Copeland and his team of administrators along with Natalia and the Marketing team want to ensure that the Livingstone Hotels & Resorts platform is meeting the needs of their employees as well as the expectations of their customers.

Josiah wants to gather feedback by providing surveys on both Livingstone Loop for their employees and the public-facing Hotel location sites for their customers. Natalia needs to create forms with specific fields relevant to each aspect of the guest and employee experiences.

#### Forms in Liferay DXP {#forms}

One of the Assets available in the _Content & Data_ section of the _Site Administration_ panel is _Forms_. Content Creators can use Forms to create surveys that users can fill out on different sites within the platform.

<div class="key-point">
Key Point: <br />
<strong>Forms</strong> allow content teams to digitize their process for gathering user feedback within the platform itself.
</div>

<figure>
  <img src="../images/forms-and-elements.png" style="max-height:30%;" />
  <figcaption style="font-size: x-small">Fig.1 The Forms section of Site Administration</figcaption>
</figure>

New forms can be created by going to `Content & Data → Forms` in the _Site Administration_ panel. Here, the Content and Marketing teams can create new _Forms_ and _Element Sets_.

#### Forms vs. Dynamic Data Lists {#ddl}

When you need a Form, what you are really looking for is data. There are two applications for building forms to collect the data you need in Liferay DXP:

* Liferay Forms - The primary form-building application is for the simplest one or two question survey about meal preference to the most complex, multi-page, homeowner's insurance application containing rules and lists populated by a REST data provider.
* Dynamic Data Lists (DDL) - Provides a User Interface tool for building reusable forms and list-based applications intended for display on pages using templates

<div class="key-point">
Key Point: <br />
<strong>Dynamic Data Lists</strong> should only be used:
<ul>
  <li>If you need a way for users to enter data and you need to display the data in the User Interface</li>
  <li>If you need to style your lists and forms with Templates</li>
  <li>If you need Color, Geolocation, Web Content, or Link to Page fields</li>
</ul>
</div>

<figure>
  <img src="../images/ddl.png" style="max-height:40%;" />
  <figcaption style="font-size: x-small">Fig.2 Adding a Dynamic Data List from the Site Administration panel</figcaption>
</figure>

<br />

If you do not need any of the features listed above, it is recommended that you use Forms instead of DDLs to collect data in the platform.

<div class="note">
Note: A third form-building tool is available to Enterprise customers called Kaleo Forms. Kaleo Forms integrates form-building with workflow to create form-based business processes, like a Conference Room Checkout Form or a Support Ticket Process so support tickets go through the proper channels on their way to resolution. Read more about Kaleo Forms in the workflow section. Kaleo Forms and Workflow are covered in the Manage Business Workflow course module.
</div>

#### Creating Forms {#create}

To create a Form or manage an existing Form, you need to use the _Forms_ section of the _Site Administration_ panel. The first thing you'll see in this section is a list of existing forms, if there are any. To add a new Form, click the _Add_ button on the _Forms_ page.

<div class="key-point">
Key Point: <br />
<strong>Fields</strong> are used to build Forms from within the Forms editor.
</div>

<figure>
  <img src="../images/form-editor.png" style="max-height:40%;" />
  <figcaption style="font-size: x-small">Fig.3 Adding fields to a new Form in the Forms editor</figcaption>
</figure>

<br />

You can add any number of fields to new Forms you create. The currently available fields include:

- Paragraph: a text field with a title  
- Text Field: a simple text field  
- Select from List: choose one or more options from a list  
- Single Selection: choose only one item with a radio button  
- Date: a datepicker to select a date  
- Multiple Selection: choose multiple options using a checkbox  
- Grid: select options from a matrix  
- Numeric: type in a number  
- Upload: upload files into the form

The _Paragraph_ field displays static text within your form, while the rest of the fields allow for user input. When a user submits a form, the information in the fields is collected.

To add fields, just click and drag them from the drop-down that appears on the right after clicking the _Add_ button. An _Options_ menu will then take the place of the _Add_ menu, allowing you to label the field, provide help text, and add any other information based on the specific form you want to create.

<figure>
	<img src="../images/field-options.png" style="max-height:38%;" />
	<figcaption style="font-size: x-small">Fig.4 Labeling a field and adding field options</figcaption>
</figure>

<br />

The Forms editor also allows you to add multiple pages to your forms, creating a more user-friendly experience.

<figure>
	<img src="../images/forms-pages.png" style="max-height:25%;" />
	<figcaption style="font-size: x-small">Fig.5 Adding a second page to a Form</figcaption>
</figure>

#### Adding Element Sets {#sets}

It is beneficial for Content Creators to have a way to reuse multiple elements and their configurations in multiple Forms. This is where _Element Sets_ come in.

<div class="key-point">
Key Point: <br />
<strong>Element Sets</strong> allow you to group and configure fields that can be re-used across many different Forms.
</div>

<figure>
  <img src="../images/element-sets-example.png" style="max-height:35%;" />
  <figcaption style="font-size: x-small">Fig.6 Where you can add Element Sets from Site Administration</figcaption>
</figure>

<br />

For example, a Content Creator could create an Element Set for User Information that includes a _Text_ field for the user's name, a _Date_ field for the date of submission, and a _Select List_ field for overall experience between 1 and 5. This can be the basic set of fields that will be used throughout all feedback forms. Doing this will remove the need for the team to re-add and configure the same fields in different forms, making new form generation faster and more efficient in the future.

Element Sets can be created from _`Content & Data → Forms → Element Sets`_ in the _Site Administration_ panel. From there, creating an Element Set is just like creating a Form. The only difference is that an Element Set is not publishable; it must be added to Forms after they have been created and saved. Once an Element Set is saved, it is immediately available for use within a Form.

#### Form Rules and Advanced Features {#rules}

When a Form is created, you can open it back up to add _Rules_. The _Rules_ tab lets you define conditions and actions for the fields you add to the form. The _Select from List_ field, for instance, allows you to define conditions such as "Is equal to," "Is not equal to," "Contains," "Does not contain," and "Is empty" and define actions such as:

* Show/Hide - Set the visibility of a form field based on a predefined condition.
* Enable/Disable - Use a predefined condition to enable or disable a field.
* Require - Use a predefined condition to enable or disable a field.
* Jump to Page - Skip over some form pages directly to a relevant page based on user input.
* Autofill with Data Provider - Use a data provider to populate fields when a condition is met in another field.
* Calculate - Populate a field with a calculated value using data entered in other fields.

<div class="key-point">
Key Point: <br />
<strong>Form Rules</strong> are tied to fields added to your Form within the Forms editor.
</div>

<figure>
  <img src="../images/forms-rules.png" style="max-height:40%;" />
  <figcaption style="font-size: x-small">Fig.7 Creating Rules for your Form</figcaption>
</figure>

<br />

Web designers can use advanced Form features, like Rules, to customize their Forms even further. Other advanced features include the following:

* Data Provider Integration
* Versioning
* User Authentication
* Requiring CAPTCHA
* Email Notifications
* A _Standalone Form_ option that allows a form to exist as a single, independent URL that can only be linked to (i.e., it cannot be navigated to from any Site Menu).

<br />

#### Success Pages {#success}

Add a success page under the _Options_ menu in the Forms editor. The page is added to the end of your form and gives you the option to add a Title and Content paragraph that users will see once they have submitted the form.

<div class="key-point">
Key Point: <br />
A <strong>Success Page</strong> is a static page that can be added to the end of your Form to indicate the user has completed the Form.
</div>

<br />

<figure>
	<img src="../images/success-page.png" style="max-height:25%;" />
	<figcaption style="font-size: x-small">Fig.8 The default Success Page</figcaption>
</figure>

<br />

A Success Page is simple. It has a title in bold text and a description beneath the title. A common alternative to using a Success Page is to redirect users to a different page in your site. What should you put in a Success Page? Whatever you want. If you can’t think of anything important or creative to say, use the default message: "Your information was successfully received. Thank you for filling out the form."

#### Displaying Forms to Users {#display}

The Form widget can be used to display your Forms to users on a page in a site. The Forms widget allows you to configure whether or not users can share Forms on different sites, as well as choose if they want to display Forms from the current site or the Global Site. 

<div class="key-point">
Key Point: <br />
The <strong>Form Widget</strong> allows you to add Forms to pages.
</div>

<figure>
  <img src="../images/display-form.png" style="max-height:40%;" />
  <figcaption style="font-size: x-small">Fig.9 Using the Form Widget to display a Form in a page</figcaption>
</figure>

<br />

Once users have given feedback, entries can be viewed in the `Content & Data → Forms` section of the _Site Administration_ panel of whichever site where the Form exists. Click the _Options_ button next to the Form and select _View Entries_.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Content Teams can build forms by clicking and dragging field elements from a list of options in the _______________________ editor.</li>
  <li>The Forms widget can be used to add a Form to a _______________________.</li>
  <li>Form entries can be viewed by going through the _______________________ menu of any Form.</li>
  <li>_______________________ enable further Form customization.</li>
  <li>A _______________________ Page can be added to the end of a Form.</li>
</ul>
</div>
