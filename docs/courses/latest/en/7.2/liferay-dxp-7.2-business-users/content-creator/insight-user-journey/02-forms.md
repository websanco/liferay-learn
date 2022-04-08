## Evaluating User Feedback with Forms

Once forms have been created and published and users have filled them out and submitted them, businesses need to see and use the information gathered. Data Analytics has become increasingly important, and statistical analysis is a great way to interpret the information gathered from Forms. 

It's also important to ensure the information your users fill out in your Forms is accurate. Answers given should always fit the appropriate requirements (e.g., an email address field should always include `@[somedomain].com`), and the Forms should only be filled out by actual users. In this section, we will learn how to optimize Forms to meet these requirements.

#### Gathering Feedback from Livingstone's Forms {#livingstone}

Josiah and Natalia have created and added Forms within the Livingstone platform. Natalia believes the user information she gathers from these Forms will help Livingstone have a clear picture of how to improve both the guest and employee experience, but if users don't fill out Natalia's forms correctly, gathering feedback in order to make any necessary business adjustments becomes significantly more challenging.

#### Form Entries {#entries}

Once users have filled out the Forms you have created for the sites within your platform, you will want to be able to access their data from those filled-out Forms.

<div class="key-point">
Key Point: <br />
<strong>Form Entries</strong> are Forms that have been filled out by users. There are three things you can do with them:
<ul>
	<li>View Form Entries</li>
	<li>Export Form Entries</li>
	<li>Delete Form Entries</li>
</ul>
</div>

<figure>
	<img src="../images/form-options.png" style="max-height:40%;" />
	<figcaption style="font-size: x-small">Fig.1 Selecting options for an individual Form</figcaption>
</figure>

<br />

To view Form Entries, go to the _Forms_ section of _Site Administration_ and click the _Options_ icon next to any given Form and select the _View Entries_ option. From here, you can select options for individual entries to either view or delete an individual entry.

In order to export all Form Entries, simply select that option for an individual Form from within the _Forms_ section of _Site Administration_. This can also be done by selecting _Export_ under the _Options_ icon at the top of the Form Entries page. Entries can be exported as CSV, JSON, XLS, or XML files for use with third-party applications.

#### Ensuring Forms are Filled Out Properly {#valid}

Certain fields in the Forms you create might need to only allow for particular values. You need to have a way to validate answers within these fields when you create your Form and before users start filling the form out. Likewise, your Forms should have a way to validate that an actual user filled out the Form and not a bot pretending to be that user. Liferay offers solutions to both of these issues through Field Validation and enabling CAPTCHA for Form Submissions.

<div class="key-point">
Key Point: <br />
<strong>Validation</strong> ensures that only certain values are entered in a field. Validation is available for both text and numeric fields in the Forms you create.
</div>

<figure>
	<img src="../images/text-validation.png" style="max-height:13%;" />
	<figcaption style="font-size: x-small">Fig.2 Text validation in action</figcaption>
</figure>

In order to enable Validation for a field in your Form, go to that field's _Properties_ tab and turn on the _Validation_ toggle. From here, you can set one of five conditions:

- If Input Contains
- If Input Does Not Contain
- If Input Is not URL
- If Input Is not Email
- If Input Does not Match

These conditions are then tied to the value you enter below it. Lastly, you can type out a unique error message that will display if the condition is not met.

Enabling Validation for Numeric fields works the same way. The only difference is the conditions. In this case, they are:

- Is greater than or equal to
- Is greater than
- Is not equal to
- Is less than or equal to
- Is less than

<div class="key-point">
Key Point: <br />
<strong>CAPTCHA</strong> prevents a bot from submitting Forms by requiring users to fill it out before the Form can be completed.
</div>

<figure>
	<img src="../images/require-captcha.png" style="max-height:38%;" />
	<figcaption style="font-size: x-small">Fig.3 Enabling CAPTCHA in Form Settings</figcaption>
</figure>

To enable CAPTCHA in your Form, simply go to the Form's settings. This can be done by editing your Form from within _Site Administration_ and clicking the _Options_ icon at the top of the Form. From there, it's simple: find the _Require CAPTCHA_ toggle and enable it. Once that's done, your Form has protection against bot submissions.

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>_____________________ are Forms that have been filled out by users.</li>
  <li>Validation is available for two types of fields:</li>
  <ul>
  	<li>_____________________</li>
  	<li>_____________________</li>
  </ul>
  <li>_____________________ prevents bots from submitting Forms.</li>
</ul>
</div>
