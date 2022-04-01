## Add Localization Resources

<div class="ahead">

#### Exercise Goals

- Review portlet component properties
- Change the localization file encoding
- Provide localization resources
- Test the user interface

</div>

Take a look at the component properties of `GradebookPortlet.java`. See the resource bundle property:

```java
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.training",
		"com.liferay.portlet.instanceable=false",
		"javax.portlet.display-name=Gradebook",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK,
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user",
		"javax.portlet.init-param.add-process-action-success-action=false"
	},
	service = Portlet.class
)
public class GradebookPortlet extends MVCPortlet {
}
```

The value of the property translates to a file system path `src/main/resources/content/Language.properties`, which was done automatically by the module template. By setting this property, localization resources of `Language.properties` are available for Liferay JSP tag libraries automatically. To add support for another language, let's say for German, just add a new language properties file `src/main/resources/content/Language_de_DE.properties`.

Default encoding for the language text files is ISO-8859-1. While it usually works for English, localizations for other languages with non-standard ASCII special characters will break.

Let's change the file encoding to UTF-8.

#### Change the Localization File Encoding
1. **Right-click** on the `src/main/resources/content/Language.properties` file in the *gradebook-web*.
2. **Click** on *Properties*.
3. **Change** the *Text file encoding* to UTF-8 and close the dialog.

#### Provide Localization Resources
1. **Open** the `gradebook-web/src/main/resources/content/Language.properties` file.
2. **Implement** the file as follows:

```properties
#
# This is the default localization file containing the key to display messages mappings.
#

#
# Standard portlet display messages
#
javax.portlet.description.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook 
javax.portlet.display-name.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.keywords.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.short-title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
javax.portlet.title.com_liferay_training_gradebook_web_portlet_GradebookPortlet=Gradebook
#
# Application category
#
category.training=Liferay Training

#
# Asset name localization (Asset Publisher, search and permissions UI)
#
model.resource.com.liferay.training.gradebook.model.Assignment=Assignment
model.resource.com.liferay.training.gradebook.model=Gradebook

#
# Application Display Template localization
#
application-display-template-type=Gradebook template

#
# Permission localizations
#
action.ADD_ASSIGNMENT=Add Assignment
action.ADD_SUBMISSION=Add Submission
action.DELETE_SUBMISSION=Delete Submission
action.EDIT_SUBMISSION=Edit Submission
action.GRADE_SUBMISSION=Grade Submissions
action.VIEW_SUBMISSIONS=View Submissions

#
# Other messages
#
add-assignment=Add New Assignment
add-submission=Add New Submission
add-submission-for-x=Add New Submission for {0}
are-you-sure-to-delete=Are you sure to delete this?
assignment-added-successfully=Assignment added successfully
assignment-deleted-successfully=Assigment was deleted succesfully
assignment-duedate=Assignment Due Date
assignment-information=Assignment Information
assignment-updated-successfully=Assignment updated successfully
assignments=Assignments
assignments-help-text=Please click the plus sign to add a new assignments.
edit-assignment=Edit
edit-submission-for-x=Edit Submission for {0}
error.assignment-date-empty=Due date cannot be empty.
error.assignment-description-empty=Description cannot be empty.
error.assignment-service-error=Service request failed with message: {0}
error.assignment-title-empty=Please enter a valid title.
error.only-one-submission-allowed=Only one submission per student is allowed.
error.assignment-title-format=Please enter letters, words, numbers, or standard punctuation.
error.submission-is-too-late=Your submission is too late.
error.submission-service-error=There was a problem with your submission.
error.submission-text-null=Submission text cannot be empty.
error.submission-text-too-short=Submission text too short.
error.submission-text-too-long=Submission text too long.
grade=Grade
gradebook-example-adt=Gradebook Application Display Template example
gradebook-portlet-instance-configuration-name=Gradebook Portlet Configuration
gradebook-service-configuration-name=Gradebook Service Configuration
grade-submission=Grade
grading=Grading
no-assignments=No assignments yet
no-comment=No comments yet.
no-submissions=No submissions for this assignment yet
not-graded=Not graded yet.
student=Student
submission-comment=Submission Comment
submission-added-succesfully=Submission was created succesfully
submission-deleted-succesfully=Submission was deleted succesfully
submission-graded-succesfully=Submission was graded succesfully
submission-information=Submission Information
submission-not-graded=Not graded
submission-settings=Submission Settings
submission-text=Submission Text
submissions=Submissions
submit-date=Submitted
this-is-an-example-adt=This is an Example ADT for the Gradebook
viewing-submissions-not-allowed=You don't have permissions to view submissions.
your-submission=Your Submission
```

> Note: Some of the keys are used at later and optional exercise steps.

#### Test the User Interface
1. **Go to** localhost:8080 in your browser after redeploying the module.
* **Open** the Gradebook application.
* **Refresh** the page.
	- You should now be able to see the labels and messages correctly:

---