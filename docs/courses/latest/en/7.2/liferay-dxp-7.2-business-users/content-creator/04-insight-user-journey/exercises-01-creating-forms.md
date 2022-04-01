<h3 class="exercise">Exercises</h3>

## Creating Forms in Liferay

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Create a simple Form</li>
    <li>Create a reusable group of form fields</li>
</ul>
</div>

#### Navigate to the Global Forms Page
1. **Open** the _Menu_.
* **Click** on the _Site Selector_ in the _Site Administration_ panel.
* **Click** on the _My Sites_ tab.
* **Click** on the _Global_ site.
* **Go to** _`Content & Data  → Forms`_ in the _Site Administration_ panel.
* **Click** the _Add_ button near the top right of the _Forms_ page.

<img src="../images/new-form.png" style="max-height:30%;" />

#### Create the New Guest Feedback Form 
1. **Click** _Untitled Form_ to edit the form title.
* **Type** `Satisfaction Survey` in the _Title_ section.
* **Type** `The Guest Satisfaction survey for each Hotel location` under the _Title_.

<img src="../images/survey-titled.png" style="max-height:33%;" />

#### Add a Single Selection Field to Gather the Overall Opinion
1. **Click** the _Add_ button near the top right.
	* This will open up a sidebar of fields we can add to the form.
* **Drag** the _Single Selection_ field into the form.  
* **Type** `What is your overall opinion of Livingstone Hotels & Resorts?` under _Label_.  
* **Type** `Choose the option closest to what you think` under _Help Text_.  
* **Type** `Very poor` for the first option.  
* **Type** `Poor` for the second option.  
* **Type** `Neutral` for the third option.  
* **Type** `Good` for the fourth option.  
* **Type** `Very good` as the last option.

<img src="../images/field-1.png" style="max-height:33%;" />

#### Add a Multiple Selection Field to Gather Positive Associations
1. **Click** the back arrow on the sidebar to get back to the fields. 
* **Drag** the _Multiple Selection_ field into the form under the _Single Selection_ field.  
* **Type** `Which of the following do you associate with Livingstone Hotels & Resorts?` in the _Label_ field.  
* **Type** `Choose any number of options` under _Help Text_.  
* **Type** `Luxury` for the first option.  
* **Type** `Good value` for the second option.  
* **Type** `Exciting` as the third option.  
* **Type** `Comfortable` as the final option.
* **Click** the back arrow on the sidebar at the top right to get back to the fields.

<img src="../images/field-2.png" style="max-height:40%;" />

#### Add a Text Field to Gather What Improvements Can Be Done
1. **Drag** a _Text Field_ into the form under the _Multiple Selection_ field.  
* **Type** `Is there anything specific we could be doing better?` under _Label_.  
* **Choose** _Multiple Lines_ under _My text field has_.
* **Close** the sidebar.

<img src="../images/survey-complete.png" style="max-height:40%;" />

<br />

#### Add a Success Page
1. **Click** on the _Options_ menu for the survey. 
	* This will be the _Options_ menu in the form builder.
* **Choose** the _Add Success Page_ option.
* **Type** _`Success!`_ for the _Title_ field.
* **Type** _`Your feedback is highly valued. Thank you for filling out this form.`_ for the _Content_ field. 
* **Click** the _Publish Form_ button.

<br />

<img src="../images/feedback-form.png" style="max-height:100%;" />

<br />

#### Add the Form to the Luxury Hotel Landing Page Site Template
1. **Open** the _Menu_.
* **Go to** _`Sites → Site Templates`_ in the _Control Panel_.
* **Click** _Luxury Hotel Location Site_.
* **Click** _The Livingstone Difference_ in the _Navigation_ menu.
* **Click** the _Add_ icon in the top right corner to open the _Add Panel_.
* **Go to** _`Widgets → Collaboration`_.
* **Click** to add the _Form_ widget to the left column.
* **Click** _Select Form_.
* **Click** the _Scope_ tab.
* **Choose** _Global_ from the drop-down.
* **Click** _Save_.
* **Click** the _Setup_ tab.
* **Click** the _Satisfaction Form_.
* **Click** _Save_.
* **Close** the pop-up.

<img src="../images/form-displayed.png" style="max-height:50%;" />

#### Import the Employee Satisfaction Form
1. **Open** the _Menu_.
* **Click** on the _Site Selector_ in the _Site Administration_ panel.
* **Click** on the _My Sites_ tab.
* **Click** on the _Livingstone Loop_ site.
* **Go to** _`Content & Data → Forms`_ in the _Site Administration_ panel.   
* **Click** the _Options_ icon in the top right corner.  
* **Choose** _Export/Import_.  
* **Click** the _Import_ tab.
* **Click** on the _Select File_ button.
* **Choose** the `employee-feedback-form.lar` from your Course Module exercises folder.
* **Click** _`Continue → Import`_.
* **Close** the pop-up.

<img src="../images/imported-survey.png" style="max-height:50%;" />

#### Review the Imported Form
1. **Click** the _Employee Feedback Form_.  
* **Click** the field that says _How satisfied are you with the following_.

<img src="../images/grid-field.png" style="max-height:35%;" /> 

#### Update the Form's Grid Field
1. **Type** `Amount of Work Time vs. Break Time` into the last option field under _Rows_.  
* **Click** the _Save Form_ button at the bottom.  
* **Click** _Preview Form_ at the bottom.

<img src="../images/employee-satisfaction-preview.png" style="max-height:35%;" />

#### Create an End of Form Element Set
1. **Close** the tab displaying the Form preview.
* **Click** the _Back_ button at the top left to navigate back to the Forms application page.
* **Click** the _Element Sets_ tab.  
* **Click** the _Add_ button near the top right.  
* **Click** _Untitled Element Set_ to edit the title.
* **Type** `End of Form Set` as the _Title_.
* **Click** to edit the description under the title.
* **Type** `Use this set on the last page of a survey or feedback form` in the _Description_ section.

<img src="../images/element-set-titled.png" style="max-height:35%;" />

#### Add a Select From List Field to Gather Survey Satisfaction
1. **Click** the _Add_ button.  
* **Drag** the _Select from List_ field into the form.  
* **Type** `I think the length of this survey:` under _Label_.  
* **Type** `Was too long` for the first option.  
* **Type** `Was a little long` for the second option.  
* **Type** `Was just right` for the third option.  
* **Type** `Could have been longer` as the last option.

<img src="../images/element-set-field-1.png" style="max-height:28%;" />

#### Add a Text Field for Additional Comments
1. **Click** the _back_ button.
* **Drag** a _Text Field_ into the form.
* **Type** `Is there anything else you would like to let us know about?` under _Label_.
* **Choose** _Multiple Lines_ under _My text field has_.
* **Click** _Save_.

<img src="../images/element-set-complete.png" style="max-height:32%;" />

#### Add the Element Set to an Existing Form
1. **Click** the _Back_ button at the top left to navigate back to the Forms application page.
* **Click** on the _Forms_ tab.
* **Click** on the _Employee Feedback Form_.
* **Click** the _Options_ icon to the right of the page map at the top of the form.  
* **Choose** _Add New Page_.
* **Click** the _Element Sets_ tab.  
* **Drag** our _End of form set_ into the form.  
* **Click** _Save Form_.

<img src="../images/page-2.png" style="max-height:27%;" />

---

#### Bonus Exercises
1. Create a new form on the Livingstone Hotels & Resorts site for gathering feedback about the user experience. The form should be at least three pages long with no more than five fields on a page.
2. Create an Element Set for commonly asked user experience questions and add it to the new form.
