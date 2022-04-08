<h2 class="exercise">Exercises</h2>

## Organizing Assets with Categories and Tags

<div class="ahead">
<h4>Exercise Goals</h4>
<ul>
    <li>Create a Vocabulary and categorize the assets</li>
    <li>Add tags to your assets</li>
    <li>Configure asset auto-tagging</li>
</ul>
</div>

#### Create a Vocabulary for Livingstone Loop Assets
1. **Open** the _Menu_.
* **Click** the _Site Selector_.
* **Click** the _My Sites_ tab.
* **Choose** the _Livingstone Loop_ site.  
* **Go to** _`Categorization → Categories`_ in the _Site Administration_ panel.
* **Click** the _Add_ button.
* **Type** `Employee Documents` as the _Name_.
* **Type** `The Vocabulary for organizing all employee documents` as the _Description_.
* **Click** _Save_.

<br />

<img src="../images/employee-documents-vocab.png" style="max-height:100%">

<br />

#### Add the Employee Records Category
1. **Click** on the _Employee Documents_ Vocabulary.
* **Click** the _Add_ button near the top right.
* **Type** in _`Employee Records`_ for the _Name_.
* **Click** _Save_.

<img src="../images/new-category.png" style="max-height:22%">

#### Add More Categories
1. **Click** the _Add_ button near the top right.
* **Type** in _`Onboarding`_ for the _Name_.
* **Click** _Save_.
* **Click** the _Add_ button near the top right.
* **Type** in _`Legal`_ for the _Name_.
* **Click** _Save_.

<img src="../images/more-categories.png" style="max-height:18%">

#### Add Sub-Categories for Onboarding
1. **Click** the _Onboarding_ category.
* **Click** the _Add_ button.
* **Type** in `Employee Handbooks`.
* **Click** _Save_.
* **Click** the _Add_ button near the top right.
* **Type** in _`Job Training`_ for the _Name_.
* **Click** _Save_.

<img src="../images/sub-categories.png" style="max-height:20%">

#### Categorize the Employee Record for Omar Miles
1. **Open** the _Menu_.
* **Go to** _`Content & Data → Documents and Media`_ in the _Site Administration_ panel.
* **Click** the _Options_ menu next to _Omar Miles Employee Record_.
* **Choose** _Edit_.
* **Open** the _Categorization_ section toward the bottom.
* **Click** _Select_ next to the _Employee Documents_ field.
* **Click** the _Employee Records_ category to highlight it.
* **Click** _Add_ at the bottom right.
* **Type** `Employee, Employee Record, HR, Livingstone Life` in the _Tags_ field.
	- The commas should separate each word or phrase into its own tag. Alternatively, you can press _Enter_ after each tag is typed.
* **Click** _Publish_.

<img src="../images/omar-categorized.png" style="max-height:15%">

#### Upload the Initial Draft of the Employee Handbook
1. **Click** the _Add_ button.
* **Choose** _File Upload_.
* **Click** _Browse/Choose File_.
* **Choose** the _`handbook.pdf`_ file from your course module exercise folder.

<img src="../images/employee-handbook.png" style="max-height:23%">

#### Categorize the Employee Handbook
1. **Click** to expand the _Categorization_ section if necessary.
* **Click** _Select_ next to the _Employee Documents_ field.
* **Click** the _Onboarding_ category to highlight it.
* **Click** the add button to expand the sub-categories beneath the _Onboarding_ category.
* **Click** on the _Employee Handbooks_ category to highlight it.
* **Click** _Add_.
* **Type** `Employee, Employee Handbook, HR, Livingstone` in the _Tags_ field.
* **Click** _Publish_.

<img src="../images/handbook-categorized.png" style="max-height:15%">

#### Configure Image Auto-Tagging to use TensorFlow
1. **Open** the _Menu_.
* **Go to** _`Control Panel → Configuration → System Settings`_.
* **Click** _Assets_ found under the _Content and Data_ section.
* **Click** _TensorFlow Image Auto Tagging_ under _Virtual Instance Scope_ near the bottom left side of the page.
* **Click** the checkbox to _Enable TensorFlow Image Auto Tagging_.
* **Type** `0.5` as the _Confidence Threshold_.
	- TensorFlow assigns a confidence level between 0 and 1 for each tag, where 1 is the highest confidence and 0 is the lowest. This field sets the minimum confidence level that TensorFlow needs to apply a tag.
* **Click** _Save_.

<img src="../images/image-auto-tagging-enabled.png" style="max-height:100%">

#### Configure Text Auto-Tagging to use OpenNLP
1. **Click** the _System Settings_ link at the top of the page.
* **Click** _Assets_ under the _Content and Data_ section.
* **Click** _OpenNLP Text Auto Tagging_ under _Virtual Instance Scope_.
* **Type** `0.5` as the _Confidence Threshold_.
	- OpenNLP assigns a confidence level between 0 and 1 for each tag, where 1 is the highest confidence and 0 is the lowest. This field sets the minimum confidence level that TensorFlow needs to apply a tag.
* **Click** the checkbox to _Enable OpenNLP Text Auto Tagging_.
* **Click** _Save_.

<img src="../images/text-auto-tagging-enabled.png" style="max-height:30%">

<br /><br />

---

#### Bonus Exercises
1. Create a Vocabulary for the _Livingstone Hotels & Resorts_ site. Be sure to add categories for Hotels, Resorts, and Vacation Packages.
2. Disable asset auto-tagging on the _Livingstone Life_ site by going to _`Configuration → Settings`_ in the _Site Administration_ panel.
