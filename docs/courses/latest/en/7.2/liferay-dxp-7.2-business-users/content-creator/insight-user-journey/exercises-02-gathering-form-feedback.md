<h2 class="exercise">Exercises</h2>

## Preparing Forms with Validation and Gathering Feedback

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Add Text Validation for the Employee Feedback Form</li>
    <li>Require CAPTCHA for the Employee Feedback Form</li>
    <li>Fill out the Form and View Form Entries</li>
</ul>
</div>

#### Add Text Field Validation to the Employee Feedback Form
1. **Open** the _Menu_.
* **Go to** _`Content & Data → Forms`_  in the _Site Administration_ panel.
	* You should be in the Livingstone Loop site already.
* **Click** the _Employee Feedback Form_. 
* **Click** the _Add_ button.
* **Drag** a _Text Field_ to the bottom of the Form.
* **Type** `Email Address` as the _Label_.
* **Click** the _Properties_ tab for the field.
* **Click** the _Validation_ slider to enable validation.
* **Choose** _Is not email_ under the _If Input_ drop-down.
* **Type** `Answer must be in the form of name@address.com` under _Show Error Message_.

<img src="../images/field-valid.png" style="max-height:20%;" />

#### Enable CAPTCHA for the Form
1. **Click** the _Options_ icon at the top of the page.
* **Choose** _Settings_.
* **Click** the _Require CAPTCHA_ slider.
* **Click** _Done_.
* **Click** _Save Form_.

<img src="../images/require-captcha.png" style="max-height:50%;" />

#### Add the Employee Feedback Form to a Page
1. **Click** _Go to Site_ in the _Site Administration_ panel.
* **Click** the _Add_ icon to open the _Add_ panel.
* **Go to** _`Widgets → Collaboration`_.
* **Drag** a _Form_ Widget to the bottom-left of the page.
* **Click** _Select Form_.
* **Choose** the _Employee Feedback Form_.
* **Click** _Save_.
* **Close** the pop-up.

<img src="../images/form-on-page.png" style="max-height:30%;" />

#### Fill out the Employee Feedback Form
1. **Click** to fill in your answers for the _How satisfied are you with the following_ grid. 
* **Click** to type in the _Email Address_ field.
* **Type** `josiah.copeland`.
* **Click** _Next_.
	* The Error Message should have appeared below the _Email Address_ field.
* **Type** `josiah.copeland@livingstone.com` in the _Email Address_ field.
* **Click** _Next_.
* **Choose** an option for the _I think the length of this survey:_ field.
* **Type** the _CAPTCHA_ text verification.
* **Click** _Submit_.

<img src="../images/survey-completed.png" style="max-height:35%;" />

#### Review the Form Submission
1. **Open** the _Menu_.
* **Go to** _`Content & Data → Forms`_ in the _Site Administration_ panel.
* **Click** on the _Options_ icon for the _Employee Feedback Form_.
* **Choose** _View entries_.
* **Click** on the _Options_ icon for the Form Entry.
* **Choose** _View_.
    * You may need to scroll to the right to see the _Options_ icon.
	* Here you can see all the answers we just submitted as Josiah Copeland.

<img src="../images/form-entries.png" style="max-height:50%;" />

---

#### Bonus Exercises
1. Add a field for employees to fill in the number of years they have worked at Livingstone. Enable validation so that employees cannot put in a number greater than 25 (Livingstone has only been around for 25 years).
2. Fill out the Form as Omar Miles. View the Form Entries and look at Omar's response to the Employee Feedback Form.
