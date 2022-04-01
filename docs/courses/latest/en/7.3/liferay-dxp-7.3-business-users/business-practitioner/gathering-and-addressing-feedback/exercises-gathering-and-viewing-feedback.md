## Gathering and Viewing Feedback

<div class="ahead">

#### Exercise Goals
* Add Text Validation for the Employee Feedback Form
* Require CAPTCHA for the Employee Feedback Form
* Fill out the Form and view its Form Entry

</div>

#### Add Text Field Validation to the Employee Feedback Form
1. **Open** the _Menu_.
2. **Go to** _`Content & Data > Forms`_  in the _Site Administration_ panel.
	* You should be in the Livingstone Loop Site already.
3. **Click** the _Employee Feedback Form_. 
4. **Click** the _Add_ button.
5. **Drag** a _Text_ field to the bottom of page 1 of the Form.
6. **Type** `Email Address` as the _Label_.
7. **Click** the _Advanced_ tab for the field.
8. **Click** the _Validation_ slider to enable validation.
9. **Choose** _Is not email_ under the _If Input_ drop-down.
10. **Type** `Answer must be in the form of name@address.com` under _Show Error Message_.

<br />

<img src="images/field_valid.png" style="max-width:60%;" />

#### Enable CAPTCHA for the Form
1. **Click** the _Options_ icon at the top of the page.
2. **Choose** _Settings_.
3. **Click** the _Require CAPTCHA_ slider.
4. **Click** _Done_.
5. **Click** _Save_.

<br />

<img src="images/require_captcha.png" style="max-width:60%;" />

<div class="page"></div>

#### Create the Home Page for the Livingstone Loop Site
1. **Go to** _`Site Builder > Pages`_
2. **Click** the _Add_ icon.
3. **Choose** _Private Page_.
4. **Choose** _Widget Page_.
5. **Type** `Home` for _Name_.
6. **Click** _Save_.
7. **Click**  _Home_ in the _Site Administration_ panel.
8. **Click** the _Add_ icon to open the _Add_ panel.
9. **Go to** _`Widgets > Social`_.
10. **Drag** an _Activities_ Widget to the top left of the page.
11. **Go to** _`Widgets > Tools`_.
12. **Drag** a _Language Selector_ Widget to the top right of the page.
13. **Go to** _`Widgets > News`_.
14. **Drag** a _Recent Content_ Widget to the bottom right of the page.

<br />

<img src="images/livingstone_loop_home.png" style="max-width:100%;" />

<div class="page"></div>

#### Add the Employee Feedback Form to a Page
1. **Click** the _Add_ icon to open the _Add_ panel.
2. **Go to** _`Widgets > Collaboration`_.
3. **Drag** a _Form_ Widget to the bottom left of the page.
4. **Click** _Select Form_.
5. **Choose** the _Employee Feedback Form_.
6. **Click** _Save_.
7. **Close** the pop-up.

<br />

<img src="images/form_on_page.png" style="max-width:100%;" />

<div class="page"></div>

#### Fill out the Employee Feedback Form
1. **Click** to fill in your answers for the _How satisfied are you with the following_ grid. 
2. **Click** to type in the _Email Address_ field.
3. **Type** `josiah.copeland`.
4. **Click** _Next_.
	* The Error Message should have appeared below the _Email Address_ field.
5. **Type** `josiah.copeland@livingstone.com` in the _Email Address_ field.
6. **Click** _Next_.
7. **Choose** an option for the _I think the length of this survey:_ field.
8. **Type** the _CAPTCHA_ text verification.
9. **Click** _Submit_.

<br />

<img src="images/survey_completed.png" style="max-width:60%;" />

#### Review the Form Submission
1. **Open** the _Menu_.
2. **Go to** _`Content & Data > Forms`_ in the _Site Administration_ panel.
3. **Click** on the _Options_ icon for the _Employee Feedback Form_.
4. **Choose** _View entries_.
5. **Click** on the _Options_ icon for the Form Entry.
6. **Choose** _View_.
    * You may need to scroll to the right to see the _Options_ icon.
	* Here you can see all the answers we just submitted as Josiah Copeland.

<br />

<img src="images/form_entries.png" style="max-width:100%;" />


<div class="page"></div>

---

#### Bonus Exercises
1. Add a field for employees to fill in the number of years they have worked at Livingstone. Enable validation so that employees cannot put in a number greater than 25 (Livingstone has only been around for 25 years).
2. Fill out the Form as Omar Miles. View the Form Entries and look at Omar's response to the Employee Feedback Form.

