# Implement Validation

Coming Soon!

<!--

#### Exercise Goals

- Customize the AssignmentValidationException to support message stacking
- Implement an Assignment validator interface
- Implement an Assignment validator service component
- Implement validation in the Assignment service
- Implement the feedback message dispatching on the controller layer
- Implement showing feedback on the user interface
- Test the server-side validation
- Implement client-side validation on the user interface
- Test the client-side validation

</div>

<br />

The Assignment validation process may encounter multiple issues. Content can be too long and have illegal characters at the same time, for example. It would be convenient to provide feedback to the user of all the issues encountered at once. To support message stacking, we have to customize the generated `AssignmentValidationException` class we defined in the `service.xml`. 

<div class="page"></div>

#### Customize the AssignmentValidationException to Support Message Stacking
1. **Open** the `com.liferay.training.gradebook.exception.AssignmentValidationException.java` in the *gradebook-api* module.
* **Implement** a new constructor and code to the class:

	```java
	/**
	 * Custom constructor taking a list as a parameter.
	 * 
	 * @param errors
	 */
	public AssignmentValidationException(List<String> errors) {
		super(String.join(",", errors));
		_errors = errors;
	}
	public List<String> getErrors() {
		return _errors;
	}
	private List<String> _errors;	
	```

* **Organize** missing imports. Be sure to include `import java.util.List;`.

#### Implement an Assignment Validator Interface
1. **Go to** the *gradebook-api* module.
* **Create** an interface `com.liferay.training.gradebook.validator.AssignmentValidator`.
* **Implement** code as follows:

```java
package com.liferay.training.gradebook.validator;

import com.liferay.training.gradebook.exception.AssignmentValidationException;

import java.util.Date;

public interface AssignmentValidator {

	/**
	 * Validates an Assignment
	 * 
	 * @param title
	 * @param description
	 * @param dueDate
	 * @throws AssignmentValidationException
	 */
	public void validate(
		String title, String description, Date dueDate)
		throws AssignmentValidationException;
}
```

#### Export the com.liferay.training.gradebook.validator Package
1. **Open** the `bnd.bnd` file of the *gradebook-api* project.
* **Export** the `com.liferay.training.gradebook.validator` package. Afterwards the file will look like this:

```properties
Bundle-Name: gradebook-api
Bundle-SymbolicName: com.liferay.training.gradebook.api
Bundle-Version: 1.0.0
Export-Package:\
	com.liferay.training.gradebook.exception,\
	com.liferay.training.gradebook.model,\
	com.liferay.training.gradebook.service,\
	com.liferay.training.gradebook.service.persistence,\
	com.liferay.training.gradebook.validator
-check: EXPORTS
-includeresource: META-INF/service.xml=../gradebook-service/service.xml
```

#### Implement an Assignment Validator Service Component
1. **Create** a class `com.liferay.training.gradebook.util.validator.AssignmentValidatorImpl`.
* **Implement** code as follows:

```java
package com.liferay.training.gradebook.util.validator;

import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.training.gradebook.exception.AssignmentValidationException;
import com.liferay.training.gradebook.validator.AssignmentValidator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.osgi.service.component.annotations.Component;

@Component(
	immediate = true, 
	service = AssignmentValidator.class
)
public class AssignmentValidatorImpl implements AssignmentValidator {

	/**
	 * Validates assignment values and throws
	 * {AssignmentValidationExceptionException} if the assignment values are not
	 * valid.
	 * 
	 * @param title
	 * @param description
	 * @param dueDate
	 * @throws AssignmentValidationExceptionException
	 */
	public void validate(
		String title, String description, Date dueDate)
		throws AssignmentValidationException {

		List<String> errors = new ArrayList<>();
		
		if (!isAssignmentValid(title, description, dueDate, errors)) {
			throw new AssignmentValidationException(errors);
		}
	}
	
	/**
	 * Returns <code>true</code> if the given fields are valid for an
	 * assignment.
	 * 
	 * @param title
	 * @param description
	 * @param dueDate
	 * @param errors
	 * @return boolean <code>true</code> if assignment is valid, otherwise
	 *         <code>false</code>
	 */

	private boolean isAssignmentValid(
		final String title, final String description,
		final Date dueDate, final List<String> errors) {

		boolean result = true;

		result &= isTitleValid(title, errors);
		result &= isDueDateValid(dueDate, errors);
		result &= isDescriptionValid(description, errors);

		return result;
	}

	/**
	 * Returns <code>true</code> if description is valid for an assignment. If
	 * not valid, an error message is added to the errors list.
	 * 
	 * @param description
	 * @param errors
	 * @return boolean <code>true</code> if description is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isDescriptionValid(
		final String description, final List<String> errors) {

		boolean result = true;

		// Verify the description has something

		if (description == "") {
			errors.add("assignmentDescriptionEmpty");
			result = false;
		}

		return result;
		
	}

	/**
	 * Returns <code>true</code> if due date is valid for an assignment. If not
	 * valid, an error message is added to the errors list.
	 * Note that this error can't ever happen in the user interface because
	 * we are always getting a default value on the controller layer (Action Commands)
	 * However, this service could be access through the WS Api, which is why we need it.
	 * 
	 * @param dueDate
	 * @param errors
	 * @return boolean <code>true</code> if due date is valid, otherwise
	 *         <code>false</code>
	 */
	private boolean isDueDateValid(
		final Date dueDate, final List<String> errors) {

		boolean result = true;

		if (Validator.isNull(dueDate)) {
			errors.add("assignmentDateEmpty");
			result = false;
		}

		return result;
	}

	/**
	 * Returns <code>true</code> if title is valid for an assignment. If not
	 * valid, an error message is added to the errors list.
	 * 
	 * @param title
	 * @param errors
	 * @return boolean <code>true</code> if the title is valid, otherwise
	 *         <code>false</code>
	 */

	private boolean isTitleValid(
		final String title, final List<String> errors) {

		boolean result = true;

		// Verify the Title has something

		if (title == "") {
			errors.add("assignmentTitleEmpty");
			result = false;
		}

		return result;
	}
}
```

<div class="page"></div>

#### Implement Validation in the Assignment Service
1. **Open** the class `com.liferay.training.gradebook.service.impl.AssignmentLocalServiceImpl`.
* **Add** a reference to the AssignmentValidator service to the end of the class:

	```java
	@Reference
	AssignmentValidator _assignmentValidator;
	```

* **Organize** missing imports.
* **Add** the validation call to the `addAssignment()` right after the method declaration:

	```java
	public Assignment addAssignment(
		long groupId, String title, String description,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {
		// Validate assignment parameters.
		_assignmentValidator.validate(title, description, dueDate);
		...
	```

* **Add** a validation call to `updateAssignment()` right after the method declaration:
	
	```java
	public Assignment updateAssignment(
		long assignmentId, String title, String description,
		Date dueDate, ServiceContext serviceContext)
		throws PortalException {
		// Validate assignment parameters.
		_assignmentValidator.validate(title, description, dueDate);
		...
	```

* **Organize** missing imports.
* **Rebuild** the service.

Validation is now implemented on the service layer. As we call the services on the controller layer through the MVC commands in the *gradebook-web* module, we have to pass the feedback messages from the service layer to the user interface there.

For transporting the messages to the user interface, we'll be using the [SessionMessages](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-kernel/src/com/liferay/portal/kernel/servlet/SessionMessages.java) object for success messages and the [SessionErrors](https://github.com/liferay/liferay-portal/blob/7.1.x/portal-kernel/src/com/liferay/portal/kernel/servlet/SessionErrors.java) object for the error messages.

You've probably noticed the default success message the platform sets when you add an Assignment. We'll silence that because we'll be using our custom message. 

#### Implement Feedback Messages Dispatching on the Controller Layer
1. **Open** the class `GradebookPortlet.java` in the *gradebook-web* module.
* **Add** the following component property:

	```properties
	"javax.portlet.init-param.add-process-action-success-action=false"
	```	
	
	* Modify the `doProcessAction()` methods of the three MVC Action Command classes in the *gradebook-web* module, calling the service to set the success and error messages for the user interface.

* **Update** the code of all of the following MVC Action Command classes as follows:
	
**AddAssignmentMVCActionCommand.java**
```java
package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.exception.AssignmentValidationException;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC Action Command for adding assignments.
 * 
 * @author liferay
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK,
		"mvc.command.name=" + MVCCommandNames.ADD_ASSIGNMENT
	},
	service = MVCActionCommand.class
)
public class AddAssignmentMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Assignment.class.getName(), actionRequest);

		// Get parameters from the request.

				String title = ParamUtil.getString(actionRequest, "title");
			
				String description = ParamUtil.getString(actionRequest, "description", null);

		        Date dueDate = ParamUtil.getDate(actionRequest, "dueDate", null);
        try {

			// Call the service to add a new assignment.
			
			_assignmentService.addAssignment(
				themeDisplay.getScopeGroupId(), title, description, dueDate, serviceContext);
			
			// Set the success message.

			SessionMessages.add(actionRequest, "assignmentAdded");

			sendRedirect(actionRequest, actionResponse);
		}
		catch (AssignmentValidationException ave) {
			
			// Get error messages from the service layer.

			ave.getErrors().forEach(key -> SessionErrors.add(actionRequest, key));
			
			ave.printStackTrace();

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT);			

		}
		catch (PortalException pe) {
			
			// Set error messages from the service layer.

		    SessionErrors.add(actionRequest, "serviceErrorDetails", pe);

			pe.printStackTrace();
			
			

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT);			
		}
	}

	@Reference
	protected AssignmentService _assignmentService;

}
```

**EditAssignmentMVCActionCommand.java**
```java
package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.training.gradebook.exception.AssignmentValidationException;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;

import java.util.Date;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC Action Command for editing assignments.
 * 
 * @author liferay
 *
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK,
		"mvc.command.name=" + MVCCommandNames.EDIT_ASSIGNMENT
	},
	service = MVCActionCommand.class
)
public class EditAssignmentMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ServiceContext serviceContext =
			ServiceContextFactory.getInstance(Assignment.class.getName(), actionRequest);

		// Get parameters from the request.

		long assignmentId = ParamUtil.getLong(actionRequest, "assignmentId");

		String title = ParamUtil.getString(actionRequest, "title");
	
		String description = ParamUtil.getString(actionRequest, "description", null);

		Date dueDate = ParamUtil.getDate(actionRequest, "dueDate", null);
		try {
			
			// Call the service to update the assignment
			
			 _assignmentService.updateAssignment(
				assignmentId, title, description, dueDate, serviceContext);
			 
			// Set the success message.

			SessionMessages.add(actionRequest, "assignmentUpdated");

			sendRedirect(actionRequest, actionResponse);
		}
		catch (AssignmentValidationException ave) {
			
			// Get error messages from the service layer.

			ave.getErrors().forEach(key -> SessionErrors.add(actionRequest, key));
			
			ave.printStackTrace();
			
			actionResponse.setRenderParameter(
				"mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT);			

		}
		catch (PortalException pe) {
			
			// Get error messages from the service layer.

			SessionErrors.add(actionRequest, "serviceErrorDetails", pe);
			
			pe.printStackTrace();

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", MVCCommandNames.EDIT_ASSIGNMENT);			
		}
	}

	@Reference
	protected AssignmentService _assignmentService;
}
```

<div class="page"></div>

**DeleteAssignmentMVCActionCommand.java**
```java
package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
	* MVC Action Command for deleting assignments.
	* 
	* @author liferay
	*/
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.GRADEBOOK,
		"mvc.command.name=/gradebook/assignment/delete"
	},
	service = MVCActionCommand.class
)
public class DeleteAssignmentMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		// Get assignment id from request.

		long assignmentId = ParamUtil.getLong(actionRequest, "assignmentId");

		try {

			// Call service to delete the assignment.

			_assignmentService.deleteAssignment(assignmentId);

			// Set success message.

			SessionMessages.add(actionRequest, "assignmentDeleted");
		}
		catch (PortalException pe) {

			// Set error messages from the service layer.

			SessionErrors.add(actionRequest, "serviceErrorDetails", pe);
		}

	}

	@Reference
	protected AssignmentService _assignmentService;
}
```

The last thing to do for displaying the error messages from the service layer is to implement the user interface. We'll be using the `<liferay-ui>` tag library for this purpose.

Add an import for the `SessionErrors` class for showing the error message details.

#### Import SessionErrors Class
1. **Add** an import in `src/main/resources/META-INF/resources/init.jsp`:

	```html
	<%@ page import="com.liferay.portal.kernel.servlet.SessionErrors"%>
	```

	- After we add, update, or delete an Assignment successfully, we are redirected to the main list view, implemented with the `view.jsp`.

2. **Add** `<liferay-ui>` tags to `src/main/resources/META-INF/resources/view.jsp` just after the `init.jsp` include:

	```html
	<%@ include file="init.jsp"%>
	<liferay-ui:error key="serviceErrorDetails">
		<liferay-ui:message arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' key="error.assignment-service-error" />
	</liferay-ui:error>
	<liferay-ui:success key="assignmentAdded" message="assignment-added-successfully" />
	<liferay-ui:success key="assignmentUpdated" message="assignment-updated-successfully" />
	<liferay-ui:success key="assignmentDeleted" message="assignment-deleted-successfully" />
	```

3. **Add** `<liferay-ui>` tags to `src/main/resources/META-INF/resources/assignment/edit_assigments.jsp` just after the `init.jsp` include:

```html
<%@ include file="../init.jsp"%>

<liferay-ui:error key="serviceErrorDetails">
	<liferay-ui:message key="error.assignment-service-error" arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' />
</liferay-ui:error>
<liferay-ui:error key="assignmentTitleEmpty" message="error.assignment-title-empty" />
<liferay-ui:error key="assignmentDescriptionEmpty" message="error.assignment-description-empty" />
```

Server-side validation is now implemented. Let's test it.

#### Test the Server-Side Validation
1. **Open** the Gradebook application in your web browser.
2. **Click** on the plus sign to add an Assignment.
3. **Leave** the *Title* field empty but enter something on the *Description* field.
4. **Submit** the form:

<br />

> If you get a `NoSuchMethodError` error in your log, remove and redeploy the modules from the server.

Detecting invalid input early on the user interface, client-side, improves the user experience and reduces server load. Remember, however, that user interface validation, typically JavaScript-based, is more about usability than security: if you disable page JavaScripts, your security is gone.

Take a look at the `edit_assignments.jsp`. Notice the already existing <aui:validator> tag for the *description* fields:

```html
<aui:validator name="required" />
```

We will add two validators for the *title* field. One is for setting the field mandatory, and the other one checks for valid characters.  

#### Implement Client-Side Validation
1. **Open** the `edit_assignments.jsp`.
2. **Add** the following validator tags inside the `<aui:input name="title">` tag:

```html
<aui:validator name="required" />

<aui:validator errorMessage="error.assignment-title-format" name="custom">
	function(val, fieldNode, ruleValue) {
		var wordExpression = 
			new RegExp("^[^\\[\\]\\^$<>]*$");
		
		return wordExpression.test(val);
	}
</aui:validator>
```

The complete `edit_assignments.jsp` file will look like this:

```html
<%@ include file="../init.jsp"%>
<liferay-ui:error key="serviceErrorDetails">
	<liferay-ui:message key="error.assignment-service-error" arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' />
</liferay-ui:error>
<liferay-ui:error key="assignmentTitleEmpty" message="error.assignment-title-empty" />
<liferay-ui:error key="assignmentDescriptionEmpty" message="error.assignment-description-empty" />

<%-- Generate add / edit action URL and set title. --%>

<c:choose>
	<c:when test="${not empty assignment}">
		<portlet:actionURL var="assignmentActionURL" name="<%=MVCCommandNames.EDIT_ASSIGNMENT %>">
			<portlet:param name="redirect" value="${param.redirect}" />
		</portlet:actionURL>

		<c:set var="editTitle" value="edit-assignment"/>
	</c:when>
	<c:otherwise>
		<portlet:actionURL var="assignmentActionURL" name="<%=MVCCommandNames.ADD_ASSIGNMENT %>">
			<portlet:param name="redirect" value="${param.redirect}" />
		</portlet:actionURL>

		<c:set var="editTitle" value="add-assignment"/>
	</c:otherwise>
</c:choose>
	
<div class="container-fluid-1280 edit-assignment">

	<h1><liferay-ui:message key="${editTitle}" /></h1>

	<aui:model-context bean="${assignment}" model="${assignmentClass}" />

	<aui:form action="${assignmentActionURL}" name="fm">

		<aui:input name="assignmentId" field="assignmentId" type="hidden" />
		
		<aui:fieldset-group markupView="lexicon">
		
			<aui:fieldset>
			
				<%-- Title field. --%>
				
				<aui:input name="title">
					<aui:validator name="required" />
					<%-- Custom AUI validator. --%>
					
					<aui:validator errorMessage="error.assignment-title-format" name="custom">
						function(val, fieldNode, ruleValue) {
							var wordExpression = 
								new RegExp("^[^\\[\\]\\^$<>]*$");
							
							return wordExpression.test(val);
						}
					</aui:validator>			
				</aui:input>

				<%-- Description field. --%>

				<aui:input name="description">
					<aui:validator name="required" />
				</aui:input>

				<%-- Due date field. --%>

				<aui:input name="dueDate">
					<aui:validator name="required" />
				</aui:input>
			</aui:fieldset>
		</aui:fieldset-group>
		
		<%--Buttons. --%>
		
		<aui:button-row>
			<aui:button cssClass="btn btn-primary" type="submit" />
			<aui:button cssClass="btn btn-secondary" onClick="${param.redirect}" type="cancel" />
		</aui:button-row>
	</aui:form>

</div>
```

#### Test the Client-Side Validation
1. **Open** the Gradebook application in your web browser.
* **Click** on the plus sign to add an Assignment.
* **Put** a dollar `$` sign in the title field and leave the *Description* field empty.
* **Submit** the form.

-->


