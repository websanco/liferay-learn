---
description: 6 - Real World Application
title: Implement Submissions
order: 2
---

<h2 class="exercise">Optional Exercise</h2>

## Implement Submissions

<div class="ahead">
<h4>Exercise Goals</h4>
	<ul>
		<li>Implement the service layer: </li>
		<ul>
			<li>Define the Submission entity in <code>service.xml</code></li>
			<li>Increase submission text column size by modifying the <code>portlet-model-hints.xml</code></li>
			<li>Implement SubmissionLocalServiceImpl</li>
			<li>Implement SubmissionServiceImpl</li>
		</ul>
		<li>Implement the controller layer</li>
		<ul>
			<li>Implement the MVC Commands</li>
		</ul>
		<li>Implement the user interface</li>
		<ul>
			<li>Implement the JSP files</li>
		</ul>
		<li>Test the application</li>
	</ul>
</div>

<br />

> Notice that we have already created some resources for this exercise in the previous exercises:
> * Permission definitions in the `default.xml` of *gradebook-service* module.
> * Localizations in the `Language.properties` of the *gradebook-web* module
> * MVC Command name constants in the `MVCCommandNames.java` class in the *gradebook-web* module.

#### Define the Submission entity in `service.xml`

1. **Open** the `service.xml` in the *gradebook-service* module.
1. **Implement** the submission entity definition right after the closing `<entity>` tag of Assignment:
	```xml
	<entity name="Submission" local-service="true" remote-service="true">
	
        <column name="submissionId" type="long" primary="true" />
        
        <!-- Group instance -->

		<column name="groupId" type="long" />

		<!-- Audit fields -->

		<column name="companyId" type="long" />
		<column name="userId" type="long" />
		<column name="userName" type="String" />
		<column name="createDate" type="Date" />
		<column name="modifiedDate" type="Date"/>
		
        <column name="assignmentId" type="long"/>
        <column name="studentId" type="long"/>
        <column name="submitDate" type="Date"/>
        <column name="submissionText" type="String" />
        <column name="comment" type="String"/>
        <column name="grade" type="int"/>
        
        <finder name="GroupId" return-type="Collection">
            <finder-column name="groupId"></finder-column>
        </finder>
        <finder name="G_A" return-type="Collection">
            <finder-column name="groupId"></finder-column>
            <finder-column name="assignmentId"></finder-column>
        </finder>
        <finder name="StudentId" return-type="Collection">
            <finder-column name="studentId"></finder-column>
        </finder>
        <finder name="StudentIdAssignmentId" return-type="Collection">
            <finder-column name="studentId"></finder-column>
            <finder-column name="assignmentId"></finder-column>
        </finder>
        
		<!-- References -->

		<reference package-path="com.liferay.portlet.asset" entity="AssetEntry" />
		<reference package-path="com.liferay.portlet.asset" entity="AssetTag" />    
	</entity>	
	```
	
	> The reference field between Assigment and Submission is `assignmentId`.
1. **Add** `SubmissionValidationException` definition in the `<exceptions>` tag:
	```xml
	<exceptions>
        <exception>AssignmentValidation</exception>
        <exception>SubmissionValidation</exception>
    </exceptions>
	```
1. **Rebuild** the service to generate the Submission services.

#### Increase Submission Text Column Size by Modifying the `portlet-model-hints.xml`

The default size for a text column is 75 characters. Increase the size to 1024 characters:

1. **Open** the file `src/main/resources/META-INF/portlet-model-hints.xml` in the *gradebook-service* module.
1. **Find** the definition of `submissionText` column and replace it with:
	```xml
	<field name="submissionText" type="String">
		<hint name="max-length">1024</hint>
	</field>
	```
1. **Rebuild** the service.	

#### Implement Message Stacking in the Submission Validation Exception

When validating entities, it's often desired to get all validation errors on the screen at once. Let's customize the submission validation exception and add a new constructor taking a list as a parameter:

1. **Open** the class `com.liferay.training.gradebook.exception.SubmissionValidationException` in the *gradebook-api* module.
1. **Implement** the new constructor, getter method and list variable to the class as follows:
	```java
	/**
	 * Custom constructor for validation
	 * @param errors
	 */
	public SubmissionValidationException(List<String> errors) {
		super(String.join(",", errors));
		_errors = errors;
	}
	
	public List<String> getErrors() {
		return _errors;
	}
	
	private List<String> _errors;
	```
1. **Organize** missing imports.	

#### Implement SubmissionLocalServiceImpl

Implement the CRUD logic for the Submission entities:

1. **Open** the class `com.liferay.training.gradebook.service.impl.SubmissionLocalServiceImpl` in the *gradebook-service* module.
1. **Replace** the contents of the class with the following:
	```java
	/**
	 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
	 *
	 * This library is free software; you can redistribute it and/or modify it under
	 * the terms of the GNU Lesser General Public License as published by the Free
	 * Software Foundation; either version 2.1 of the License, or (at your option)
	 * any later version.
	 *
	 * This library is distributed in the hope that it will be useful, but WITHOUT
	 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
	 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
	 * details.
	 */
	
	package com.liferay.training.gradebook.service.impl;
	
	import com.liferay.portal.aop.AopService;
	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.model.User;
	import com.liferay.portal.kernel.module.configuration.ConfigurationException;
	import com.liferay.portal.kernel.service.ServiceContext;
	import com.liferay.training.gradebook.exception.SubmissionValidationException;
	import com.liferay.training.gradebook.model.Assignment;
	import com.liferay.training.gradebook.model.Submission;
	import com.liferay.training.gradebook.service.base.SubmissionLocalServiceBaseImpl;
	
	import java.text.SimpleDateFormat;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;
	
	import org.osgi.service.component.annotations.Component;
	
	/**
	 * The implementation of the submission local service.
	 *
	 * <p>
	 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.training.gradebook.service.SubmissionLocalService</code> interface.
	 *
	 * <p>
	 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
	 * </p>
	 *
	 * @author Brian Wing Shun Chan
	 * @see SubmissionLocalServiceBaseImpl
	 */
	@Component(
		property = "model.class.name=com.liferay.training.gradebook.model.Submission",
		service = AopService.class
	)
	public class SubmissionLocalServiceImpl extends SubmissionLocalServiceBaseImpl {
	
		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never reference this class directly. Use <code>com.liferay.training.gradebook.service.SubmissionLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.liferay.training.gradebook.service.SubmissionLocalServiceUtil</code>.
		 */
		
		/**
		 * Adds a new submissions
		 * 
		 * @param assignmentId
		 * @param studentId
		 * @param submissionText
		 * @param serviceContext
		 * @return
		 * @throws PortalException
		 */
		@Override
		public Submission addSubmission(
			long assignmentId, long studentId, String submissionText,
			ServiceContext serviceContext)
			throws PortalException {
	
			Assignment assignment =
				assignmentPersistence.findByPrimaryKey(assignmentId);
	
			long userId = serviceContext.getUserId();
	
			// Verify that user exists (throws exception if not).
	
			User user = userLocalService.getUser(userId);
	
			// Verify that student exists (throws exception if not).
	
			User studentUser = userLocalService.getUser(studentId);
	
			// Validate submission
	
			validateSubmission(
				serviceContext.getCompanyId(), studentId, assignment, -1L,
				submissionText);
	
			// Create submission id.
	
			long submissionId =
				counterLocalService.increment(Submission.class.getName());
	
			// Create new submission.
	
			Submission submission =
				submissionLocalService.createSubmission(submissionId);
	
			submission.setSubmissionId(submissionId);
			submission.setAssignmentId(assignmentId);
			submission.setCompanyId(assignment.getCompanyId());
			submission.setGroupId(assignment.getGroupId());
			submission.setCreateDate(new Date());
			submission.setModifiedDate(new Date());
	
			submission.setUserId(userId);
			submission.setGrade(-1);
			submission.setStudentId(studentId);
			submission.setSubmissionText(submissionText);
			submission.setSubmitDate(new Date());
	
			return super.addSubmission(submission);
		}
	
		@Override
		public Submission updateSubmission(
			long submissionId, String submissionText, ServiceContext serviceContext)
			throws PortalException {
	
			Submission submission = getSubmission(submissionId);
	
			Assignment assignment =
					assignmentPersistence.findByPrimaryKey(submission.getAssignmentId());
	
			validateSubmission(
				serviceContext.getCompanyId(), submission.getStudentId(),
				assignment, submissionId, submissionText);
	
			submission.setSubmissionText(submissionText);
			submission.setSubmitDate(new Date());
	
			return super.updateSubmission(submission);
		}
	
		public List<Submission> getSubmissionsByAssignment(
			long groupId, long assignmentId) {
	
			return submissionPersistence.findByG_A(groupId, assignmentId);
		}
	
		public List<Submission> getSubmissionsByAssignment(
			long groupId, long assignmentId, int start, int end) {
	
			return submissionPersistence.findByG_A(
				groupId, assignmentId, start, end);
		}
	
		public int getSubmissionsCountByAssignment(
			long groupId, long assignmentId) {
	
			return submissionPersistence.countByG_A(groupId, assignmentId);
		}
	
		public Submission gradeSubmission(long submissionId, int grade)
			throws PortalException {
	
			Submission submission = getSubmission(submissionId);
	
			submission.setGrade(grade);
			submission.setModifiedDate(new Date());
	
			return super.updateSubmission(submission);
		}
	
		public Submission gradeAndCommentSubmission(
			long submissionId, int grade, String comment)
			throws PortalException {
	
			Submission submission = getSubmission(submissionId);
	
			submission.setGrade(grade);
			submission.setComment(comment);
	
			submission.setModifiedDate(new Date());
	
			return super.updateSubmission(submission);
		}
	
		private void validateSubmission(
			long companyId, long studentId, Assignment assignment,
			long submissionId, String submissionText)
			throws PortalException, SubmissionValidationException, ConfigurationException {
	
			List<String> errorMessages = new ArrayList<String>();
	
			// Validate the due date.
	
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			String dueDate = sdf.format(assignment.getDueDate());
			String today = sdf.format(new Date());
	
			if (dueDate.compareTo(today) < 0) {
				errorMessages.add("submissionTooLate");
			}
	
			// Validate submission count.
	
			if (submissionId < 0) {
	
				long submissionCount =
					submissionPersistence.countByStudentIdAssignmentId(
						studentId, assignment.getAssignmentId());
	
				if (submissionCount > 0) {
					errorMessages.add("onlyOneSubmissionAllowed");
				}
			}
			else {
	
				Submission submission =
					submissionPersistence.fetchByPrimaryKey(submissionId);
	
				if (submission.getStudentId() != studentId) {
					errorMessages.add("onlyOneSubmissionAllowed");
				}
			}
			
			// Validate text length.
	
			if (submissionText == null) {
	
				errorMessages.add("submissionTextNull");
			}
	
			// Throw an exception if necessary.
	
			if (errorMessages.size() > 0) {
				throw new SubmissionValidationException(errorMessages);
			}
		}	
	}	
	```
1. **Rebuild** the service.	

#### Implement SubmissionServiceImpl

Implement the facade methods in the remote service implementation class:

1. **Open** the class `com.liferay.training.gradebook.service.impl.SubmissionServiceImpl` in the *gradebook-service* module.
1. **Implement** as follows:
	```java
	/**
	 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
	 *
	 * This library is free software; you can redistribute it and/or modify it under
	 * the terms of the GNU Lesser General Public License as published by the Free
	 * Software Foundation; either version 2.1 of the License, or (at your option)
	 * any later version.
	 *
	 * This library is distributed in the hope that it will be useful, but WITHOUT
	 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
	 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
	 * details.
	 */
	
	package com.liferay.training.gradebook.service.impl;
	
	import com.liferay.portal.aop.AopService;
	import com.liferay.portal.kernel.exception.PortalException;
	import com.liferay.portal.kernel.security.auth.PrincipalException;
	import com.liferay.portal.kernel.security.permission.resource.ModelResourcePermission;
	import com.liferay.portal.kernel.service.ServiceContext;
	import com.liferay.training.gradebook.model.Assignment;
	import com.liferay.training.gradebook.model.Submission;
	import com.liferay.training.gradebook.service.base.SubmissionServiceBaseImpl;
	
	import java.util.List;
	
	import org.osgi.service.component.annotations.Component;
	import org.osgi.service.component.annotations.Reference;
	import org.osgi.service.component.annotations.ReferencePolicy;
	import org.osgi.service.component.annotations.ReferencePolicyOption;
	
	/**
	 * The implementation of the submission remote service.
	 *
	 * <p>
	 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.liferay.training.gradebook.service.SubmissionService</code> interface.
	 *
	 * <p>
	 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
	 * </p>
	 *
	 * @author Brian Wing Shun Chan
	 * @see SubmissionServiceBaseImpl
	 */
	@Component(
		property = {
			"json.web.service.context.name=gradebook",
			"json.web.service.context.path=Submission"
		},
		service = AopService.class
	)
	public class SubmissionServiceImpl extends SubmissionServiceBaseImpl {
	
		/*
		 * NOTE FOR DEVELOPERS:
		 *
		 * Never reference this class directly. Always use <code>com.liferay.training.gradebook.service.SubmissionServiceUtil</code> to access the submission remote service.
		 */
	
		public Submission addSubmission(
			long assignmentId, long studentId, String submissionText,
			ServiceContext serviceContext)
			throws PortalException {
	
			// Check permissions.
	
			_assignmentModelResourcePermission.check(
				getPermissionChecker(), assignmentId, "ADD_SUBMISSION");
	
			return submissionLocalService.addSubmission(
				assignmentId, studentId, submissionText, serviceContext);
		}
	
		public Submission deleteSubmission(long submissionId)
			throws PortalException {
	
			// Check permissions.
	
			Submission submission =
				submissionLocalService.getSubmission(submissionId);
	
			_assignmentModelResourcePermission.check(
				getPermissionChecker(), submission.getAssignmentId(),
				"DELETE_SUBMISSION");
	
			return submissionLocalService.deleteSubmission(submissionId);
		}
	
		public Submission gradeSubmission(long submissionId, int grade)
			throws PortalException {
	
			Submission submission =
				submissionLocalService.getSubmission(submissionId);
	
			_assignmentModelResourcePermission.check(
				getPermissionChecker(), submission.getAssignmentId(),
				"GRADE_SUBMISSION");
	
			return submissionLocalService.gradeSubmission(submissionId, grade);
		}
	
		public Submission gradeAndCommentSubmission(
			long submissionId, int grade, String comment)
			throws PortalException {
	
			Submission submission =
				submissionLocalService.getSubmission(submissionId);
	
			_assignmentModelResourcePermission.check(
				getPermissionChecker(), submission.getAssignmentId(),
				"GRADE_SUBMISSION");
	
			return submissionLocalService.gradeAndCommentSubmission(
				submissionId, grade, comment);
		}
	
		public List<Submission> getSubmissionsByAssignment(
			long groupId, long assignmentId, int start, int end)
			throws PrincipalException, PortalException {
	
			_assignmentModelResourcePermission.check(
				getPermissionChecker(), assignmentId, "VIEW_SUBMISSIONS");
	
			return submissionPersistence.findByG_A(
				groupId, assignmentId, start, end);
		}
	
		public int getSubmissionsCountByAssignment(
			long groupId, long assignmentId) {
	
			return submissionPersistence.countByG_A(groupId, assignmentId);
		}
	
		public Submission updateSubmission(
			long submissionId, String submissionText, ServiceContext serviceContext)
			throws PortalException {
	
			Submission submission =
				submissionLocalService.getSubmission(submissionId);
	
			_assignmentModelResourcePermission.check(
				getPermissionChecker(), submission.getAssignmentId(),
				"EDIT_SUBMISSION");
	
			return submissionLocalService.updateSubmission(
				submissionId, submissionText, serviceContext);
		}
		
		@Reference(
			policy = ReferencePolicy.DYNAMIC,
			policyOption = ReferencePolicyOption.GREEDY,
			target = "(model.class.name=com.liferay.training.gradebook.model.Assignment)"
		)
		private volatile ModelResourcePermission<Assignment>
			_assignmentModelResourcePermission;
	
	}
	```
1. **Rebuild** the service.

#### Implement the MVC Action Commands

1. **Add** and implement the three MVC Action Commands in the *gradebook-web* module for adding, editing, grading and deleting submissions:
	* `com.liferay.training.gradebook.web.portlet.action.EditSubmissionMVCActionCommand`
	* `com.liferay.training.gradebook.web.portlet.action.GradeSubmissionMVCActionCommand`
	* `com.liferay.training.gradebook.web.portlet.action.DeleteSubmissionMVCActionCommand`

**EditSubmissionMVCActionCommand**
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
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.exception.SubmissionValidationException;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.service.SubmissionService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC Command for adding and editing submissions.
 * 
 * @author liferay
 *
 */
@Component(
	immediate = true, 
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
		"mvc.command.name=" + MVCCommandNames.ADD_SUBMISSION,
		"mvc.command.name=" + MVCCommandNames.EDIT_SUBMISSION
	}, 
	service = MVCActionCommand.class
)
public class EditSubmissionMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		
		try {

			// Create service context.

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				Submission.class.getName(), actionRequest);

			String submissionText =
				ParamUtil.getString(actionRequest, "submissionText");

			// Do edit or add action.

			if (isEditAction(actionRequest)) {

				long submissionId =
					ParamUtil.getLong(actionRequest, "submissionId");

				// Call the service to update the submission.

				_submissionService.updateSubmission(
					submissionId, submissionText, serviceContext);

				// Set the success message

				SessionMessages.add(actionRequest, "submissionUpdated");

			}
			else {
				long assignmentId =
					ParamUtil.getLong(actionRequest, "assignmentId");

				long studentUserId = themeDisplay.getUserId();

				_submissionService.addSubmission(
					assignmentId, studentUserId, submissionText,
					serviceContext);
				
				SessionMessages.add(actionRequest, "submissionAdded");
			}

			sendRedirect(actionRequest, actionResponse);
		}
		catch (SubmissionValidationException e) {

			e.getErrors().forEach(key -> SessionErrors.add(actionRequest, key));

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", MVCCommandNames.EDIT_SUBMISSION);			
		}
		catch (PortalException e) {

			// Set error message.

			SessionErrors.add(actionRequest, "serviceErrorDetails", e);

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", MVCCommandNames.EDIT_SUBMISSION);			
		}
	}

	/**
	 * Returns <code>true</code> if we are editing.
	 * 
	 * @param actionRequest
	 * @return <code>true</code> if we are editing; <code>false</code> otherwise
	 */
	private boolean isEditAction(ActionRequest actionRequest) {

		return ParamUtil.getString(
			actionRequest, ActionRequest.ACTION_NAME,
			MVCCommandNames.ADD_SUBMISSION).indexOf(
				MVCCommandNames.EDIT_SUBMISSION) >= 0;
	}

	@Reference
	protected SubmissionService _submissionService;

	@Reference
	protected Portal _portal;
}
```

**GradeSubmissionMVCActionCommand**
```java

package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.training.gradebook.exception.SubmissionValidationException;
import com.liferay.training.gradebook.service.SubmissionService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC Command for grading submissions.
 * 
 * @author liferay
 *
 */
@Component(
	immediate = true, 
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
		"mvc.command.name=" + MVCCommandNames.GRADE_SUBMISSION
	}, 
	service = MVCActionCommand.class
)
public class GradeSubmissionMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		// Get parameters from request.
		
		long submissionId = ParamUtil.getLong(actionRequest, "submissionId");
		int grade = ParamUtil.getInteger(actionRequest, "grade");
		String comment = ParamUtil.getString(actionRequest, "comment");
		
		try {
			
			// Call the service to grade and comment.
			
			_submissionService.gradeAndCommentSubmission(
				submissionId, grade, comment);

			// Set success message.

			SessionMessages.add(actionRequest, "submissionGraded");
			
			sendRedirect(actionRequest, actionResponse);
		}
		catch (SubmissionValidationException e) {

			// Set errors.
			
			e.getErrors().forEach(
				key -> SessionErrors.add(actionRequest, key));

			actionResponse.setRenderParameter(
				"mvcRenderCommandName", MVCCommandNames.GRADE_SUBMISSION);			
		}
	}

	@Reference
	protected SubmissionService _submissionService;
}
```

**DeleteSubmissionMVCActionCommand**
```java

package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.training.gradebook.service.SubmissionService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC Action Command for deleting submissions.
 * 
 * @author liferay
 *
 */
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
		"mvc.command.name=" + MVCCommandNames.DELETE_SUBMISSION
	},
	service = MVCActionCommand.class
)
public class DeleteSubmissionMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(
		ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		// Get submission id for the request.

		long submissionId =
			ParamUtil.getLong(actionRequest, "submissionId");

		try {

			// Call the service to delete the submission.
			
			_submissionService.deleteSubmission(submissionId);
			
			// Set success message.

			SessionMessages.add(actionRequest, "submissionDeleted");

		} catch (PortalException e) {
			
			SessionErrors.add(actionRequest, "serviceErrorDetails", e);
		}
		
		sendRedirect(actionRequest, actionResponse);
	}

	@Reference
	protected SubmissionService _submissionService;
}
```

#### Implement the MVC Render Commands

1. **Add** and implement the following three MVC Render Commands in the *gradebook-web* module as follows:
	* `com.liferay.training.gradebook.web.portlet.action.EditSubmissionMVCRenderCommand`
	* `com.liferay.training.gradebook.web.portlet.action.GradeSubmissionMVCRenderCommand`
	* `com.liferay.training.gradebook.web.portlet.action.ViewSubmissionMVCRenderCommand`

**EditSubmissionMVCRenderCommand**
```java

package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.service.SubmissionLocalService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;
import com.liferay.training.gradebook.web.internal.security.permission.resource.AssignmentPermission;

import java.text.DateFormat;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC Command for showing the submission edit view.
 * 
 * @author liferay
 *
 */
@Component(
	immediate = true, 
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
		"mvc.command.name=" + MVCCommandNames.EDIT_SUBMISSION
	}, 
	service = MVCRenderCommand.class
)
public class EditSubmissionMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		ThemeDisplay themeDisplay =
					(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long submissionId =
						ParamUtil.getLong(renderRequest, "submissionId", 0);

		try {

			// Call the service to get the submission for editing.
			// Notice that "fetch" returns a null instead of exception, 
			// when not found.

			Submission submission =
				_submissionLocalService.fetchSubmission(submissionId);

			long assignmentId;

			if (submission == null) {
				assignmentId = ParamUtil.getLong(renderRequest, "assignmentId");
			}
			else {
				assignmentId = submission.getAssignmentId();
			}

			// Call the service to get the assigment.
			// Set the attributes to the request.
			
			Assignment assignment =
				_assignmentService.getAssignment(assignmentId);

			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				"EEEEE, MMMMM dd, yyyy", renderRequest.getLocale());

			renderRequest.setAttribute("assignment", assignment);
			renderRequest.setAttribute("submission", submission);
			renderRequest.setAttribute("submissionClass", Submission.class);
			renderRequest.setAttribute(
				"assignmentPermission", _assignmentPermission);
			renderRequest.setAttribute(
				"dueDate", dateFormat.format(assignment.getDueDate()));

			// Set back icon visible.
			
			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			String redirect = renderRequest.getParameter("redirect");

			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(redirect);

			return "/submission/edit_submission.jsp";
		}
		catch (PortalException e) {
			throw new PortletException(e);
		}
	}

	@Reference
	protected AssignmentPermission _assignmentPermission;

	@Reference
	private AssignmentService _assignmentService;

	@Reference
	private SubmissionLocalService _submissionLocalService;
}
```

**GradeSubmissionMVCRenderCommand**
```java

package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.service.SubmissionLocalService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;
import com.liferay.training.gradebook.web.internal.security.permission.resource.AssignmentPermission;

import java.text.DateFormat;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC Command for showing the submission grading view.
 * 
 * @author liferay
 */
@Component(
	immediate = true, 
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
		"mvc.command.name=" + MVCCommandNames.GRADE_SUBMISSION
	}, 
	service = MVCRenderCommand.class
)
public class GradeSubmissionMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long submissionId = ParamUtil.getLong(renderRequest, "submissionId", 0);

		try {

			// Call the service to get the submission for grading.
			// Notice that "fetch" returns a null instead of exception,
			// when not found.

			Submission submission =
				_submissionLocalService.fetchSubmission(submissionId);

			long assignmentId;

			if (submission == null) {
				assignmentId = ParamUtil.getLong(renderRequest, "assignmentId");
			}
			else {
				assignmentId = submission.getAssignmentId();
			}

			Assignment assignment =
				_assignmentService.getAssignment(assignmentId);

			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				"EEEEE, MMMMM dd, yyyy", renderRequest.getLocale());

			renderRequest.setAttribute("assignment", assignment);
			renderRequest.setAttribute("submission", submission);
			renderRequest.setAttribute("submissionClass", Submission.class);
			renderRequest.setAttribute(
				"assignmentPermission", _assignmentPermission);

			renderRequest.setAttribute(
				"createDate", dateFormat.format(submission.getCreateDate()));
			renderRequest.setAttribute(
				"student", _userLocalService.getUser(
					submission.getStudentId()).getFullName());

			renderRequest.setAttribute(
				"dueDate", dateFormat.format(assignment.getDueDate()));

			// Set back icon visible.

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			String redirect = renderRequest.getParameter("redirect");

			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(redirect);

			return "/submission/grade_submission.jsp";
		}
		catch (PortalException e) {
			throw new PortletException(e);
		}
	}

	@Reference
	protected AssignmentPermission _assignmentPermission;

	@Reference
	private AssignmentService _assignmentService;

	@Reference
	private SubmissionLocalService _submissionLocalService;

	@Reference
	private UserLocalService _userLocalService;
}

```

**ViewSubmissionMVCRenderCommand**
```java

package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.service.SubmissionLocalService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;
import com.liferay.training.gradebook.web.internal.security.permission.resource.AssignmentPermission;

import java.text.DateFormat;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * MVC Command for showing the single submission view.
 * 
 * @author liferay
 */
@Component(
	immediate = true, 
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
		"mvc.command.name=" + MVCCommandNames.VIEW_SUBMISSION
	}, 
	service = MVCRenderCommand.class
)
public class ViewSubmissionMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		long submissionId = ParamUtil.getLong(renderRequest, "submissionId", 0);

		try {

			// Call the service to get the submission for grading.
			// Notice that "fetch" returns a null instead of exception,
			// when not found.

			Submission submission =
				_submissionLocalService.fetchSubmission(submissionId);

			long assignmentId = submission.getAssignmentId();

			Assignment assignment =
				_assignmentService.getAssignment(assignmentId);

			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				"EEEEE, MMMMM dd, yyyy", renderRequest.getLocale());

			// Set attributes to the request.

			renderRequest.setAttribute("assignment", assignment);
			renderRequest.setAttribute("submission", submission);
			renderRequest.setAttribute("submissionClass", Submission.class);
			renderRequest.setAttribute(
				"assignmentPermission", _assignmentPermission);
			renderRequest.setAttribute(
				"createDate", dateFormat.format(submission.getCreateDate()));
			renderRequest.setAttribute(
				"student", _userLocalService.getUser(
					submission.getStudentId()).getFullName());
			renderRequest.setAttribute(
				"dueDate", dateFormat.format(assignment.getDueDate()));

			// Set back icon visible.

			ThemeDisplay themeDisplay =
							(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			String redirect = renderRequest.getParameter("redirect");

			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(redirect);

			return "/submission/view_submission.jsp";
		}
		catch (PortalException e) {
			throw new PortletException(e);
		}
	}

	@Reference
	protected AssignmentPermission _assignmentPermission;

	@Reference
	private AssignmentService _assignmentService;

	@Reference
	private SubmissionLocalService _submissionLocalService;

	@Reference
	private UserLocalService _userLocalService;

}
```

#### Modify the MVC Render Command for Showing a Single Assignment

Modify the MVC Render Command for showing a single assignment so that is includes the list of related submissions in the request attributes:

1. **Open** the class `com.liferay.training.gradebook.web.portlet.action.ViewSingleAssignmentMVCRenderCommand` in the *gradebook-web* module.
1. **Replace** the contents of the class with the following:

```java

package com.liferay.training.gradebook.web.portlet.action;

import com.liferay.portal.kernel.dao.search.SearchContainer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.PortletDisplay;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.model.Submission;
import com.liferay.training.gradebook.service.AssignmentService;
import com.liferay.training.gradebook.service.SubmissionService;
import com.liferay.training.gradebook.web.constants.GradebookPortletKeys;
import com.liferay.training.gradebook.web.constants.MVCCommandNames;
import com.liferay.training.gradebook.web.internal.security.permission.resource.AssignmentPermission;

import java.text.DateFormat;
import java.util.List;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
	* MVC Command for showing the assignment submissions list view.
	* 
	* @author liferay
	*/
@Component(
	immediate = true,
	property = {
		"javax.portlet.name=" + GradebookPortletKeys.Gradebook,
		"mvc.command.name=" + MVCCommandNames.VIEW_ASSIGNMENT
	},
	service = MVCRenderCommand.class
)
public class ViewSingleAssignmentMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(
		RenderRequest renderRequest, RenderResponse renderResponse)
		throws PortletException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long assignmentId = ParamUtil.getLong(renderRequest, "assignmentId", 0);

		try {

			// Call the service to get the assignment.

			Assignment assignment =
				_assignmentService.getAssignment(assignmentId);

			DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
				"EEEEE, MMMMM dd, yyyy", renderRequest.getLocale());

			// Set attributes to the request.

			renderRequest.setAttribute("assignment", assignment);
			renderRequest.setAttribute(
				"dueDate", dateFormat.format(assignment.getDueDate()));
			renderRequest.setAttribute(
				"createDate", dateFormat.format(assignment.getCreateDate()));

			// Add submissions list.

			addSubmissionListAttributes(
				renderRequest, renderResponse, assignmentId);
			
			// Set permission checker.
			
			renderRequest.setAttribute(
				"assignmentPermission", _assignmentPermission);		

			// Set back icon visible.

			PortletDisplay portletDisplay = themeDisplay.getPortletDisplay();

			String redirect = renderRequest.getParameter("redirect");

			portletDisplay.setShowBackIcon(true);
			portletDisplay.setURLBack(redirect);

			return "/assignment/view_assignment.jsp";

		}
		catch (PortalException e) {
			throw new PortletException(e);
		}
	}


	/**
		* Adds submission list related attributes to the request.
		* 
		* @param renderRequest
		* @throws PortalException
		*/
	private void addSubmissionListAttributes(
		RenderRequest renderRequest, RenderResponse renderResponse,
		long assignmentId) throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		// Resolve start and end for the search.

		int currentPage = ParamUtil.getInteger(
			renderRequest, SearchContainer.DEFAULT_CUR_PARAM,
			SearchContainer.DEFAULT_CUR);

		int delta = ParamUtil.getInteger(
			renderRequest, SearchContainer.DEFAULT_DELTA_PARAM,
			SearchContainer.DEFAULT_DELTA);

		int start = ((currentPage > 0) ? (currentPage - 1) : 0) * delta;
		int end = start + delta;

		// Call the service to get the list of submissions.
		// Notice that the search only targets to submissionText field.

		List<Submission> submissions = 
			_submissionService.getSubmissionsByAssignment(
				themeDisplay.getScopeGroupId(), assignmentId, start,
				end);

		long submissionsCount =
			_submissionService.getSubmissionsCountByAssignment(
				themeDisplay.getScopeGroupId(), assignmentId);

		// Set request attributes.

		renderRequest.setAttribute(
			"canAddSubmission", canAddSubmission(submissions, renderRequest));
		renderRequest.setAttribute("submissions", submissions);
		renderRequest.setAttribute("submissionsCount", submissionsCount);
	}	
	/**
		* Returns <code>true</code> if user is allowed to do submit.
		* 
		* @param submissions
		*            list of submissions
		* @param renderRequest
		* @return
		* @throws PortalException
		*/
	private boolean canAddSubmission(
		List<Submission> submissions, RenderRequest renderRequest)
		throws PortalException {

		ThemeDisplay themeDisplay =
			(ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long userId = themeDisplay.getUserId();

		boolean hasAlreadySubmitted = false;

		if (submissions != null) {
			for (Submission submission : submissions) {
				if (submission.getStudentId() == userId) {
					hasAlreadySubmitted = true;
					break;
				}
			}
		}

		if (!hasAlreadySubmitted) {
			return true;
		}

		return false;
	}	
	@Reference
	protected AssignmentPermission _assignmentPermission;

	@Reference
	private AssignmentService _assignmentService;

	@Reference
	private Portal _portal;

	@Reference
	private SubmissionService _submissionService;

	@Reference
	private UserLocalService _userLocalService;
}
```

#### Implement the JSP files

1. **Create** a folder `src/main/resources/META-INF/resources/submission` in the *gradebook-web* module.
1. **Implement** the following JSP files in the folder:
	
**edit_submission.jsp**	
```html
<%-- 
	Submission editing view.
--%>

<%@ include file="/init.jsp"%>

<%-- Error messages. --%>

<liferay-ui:error key="serviceErrorDetails">
	<liferay-ui:message key="error.assignment-service-error" arguments='<%= SessionErrors.get(liferayPortletRequest, "serviceErrorDetails") %>' />
</liferay-ui:error>
<liferay-ui:error key="submissionTooLate" message="error.submission-is-too-late" />
<liferay-ui:error key="onlyOneSubmissionAllowed" message="error.only-one-submission-allowed" />
<liferay-ui:error key="submissionTextNull" message="error.submission-text-null" />

<%-- Generate cancel URL. --%>

<portlet:renderURL portletMode="view" var="assignmentsURL" />

<portlet:renderURL var="cancelURL">
	<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.VIEW_ASSIGNMENT %>" />
	<portlet:param name="redirect" value="${assignmentsURL}" />
	<portlet:param name="assignmentId" value="${assignment.getAssignmentId()}" />
</portlet:renderURL>

<%-- Generate add / edit action URL. --%>

<c:choose>

	<c:when test="${not empty submission}">

		<portlet:actionURL var="submissionActionURL" name="<%=MVCCommandNames.EDIT_SUBMISSION %>">
			<portlet:param name="redirect" value="${param.redirect}" />
		</portlet:actionURL>
		
		<c:set var="editTitle" value="edit-submission-for-x"/>
		
	</c:when>

	<c:otherwise>

		<portlet:actionURL var="submissionActionURL" name="<%=MVCCommandNames.ADD_SUBMISSION %>">
			<portlet:param name="redirect" value="${param.redirect}" />
		</portlet:actionURL>

		<c:set var="editTitle" value="add-submission-for-x"/>
		
	</c:otherwise>
</c:choose>

<div class="container-fluid-1280">

	<h1><liferay-ui:message key="${editTitle}" arguments="${assignment.getTitle(locale)}"/></h1>
	
	<%--Show editing control based on user's permissions. --%>

	<c:if test="${assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'EDIT_SUBMISSION' )}">

		<aui:model-context bean="${submission}" model="${submissionClass}" />

		<aui:form action="${submissionActionURL}" name="fm"
			onSubmit='<%= "event.preventDefault(); " + renderResponse.getNamespace() + "saveSubmission();" %>'>

			<aui:input 
				name="assignmentId" 
				type="hidden" 
				value="${assignment.assignmentId}"				
			/>
			<aui:input name="submissionId" field="submissionId" type="hidden" />

			<aui:fieldset-group markupView="lexicon">
			
				<aui:fieldset>

					<aui:field-wrapper required="true">
						<label for="submissionText">
							<liferay-ui:message key="your-submission" />
						</label>
						<liferay-ui:input-editor 
							contents="${submission.submissionText}"
							editorName='alloyeditor' 
							name="submissionTextEditor"
							placeholder="submission-text" />
						<aui:input name="submissionText" type="hidden" />
					</aui:field-wrapper>
				</aui:fieldset>
			</aui:fieldset-group>
			
			<%--Buttons. --%>
			
			<aui:button-row>
				<aui:button cssClass="btn btn-primary" type="submit" />
				<aui:button cssClass="btn btn-secondary" onClick="<%=cancelURL %>" type="cancel" />
			</aui:button-row>
		</aui:form>

		<aui:script>
		
			/**
			* Set editor value to the hidden field which transports the value to the backend.
			*/
			function <portlet:namespace />saveSubmission() {
				
				var editorValue = window['<portlet:namespace />submissionTextEditor'].getHTML();
				
				window['<portlet:namespace />submissionText'].value = editorValue;
	
				submitForm(document.<portlet:namespace />fm);
			}

		</aui:script>
	</c:if>
</div>
```
	
**entry_actions.jsp**	
```html   
<%@ include file="/init.jsp"%>

<c:set var="submission" value="${SEARCH_CONTAINER_RESULT_ROW.object}" />

<liferay-ui:icon-menu markupView="lexicon">

	<%-- View action. Show for the owner for those having grading permissions. --%>

	<c:if test="${submission.studentId eq user.userId or assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'GRADE_SUBMISSION' )}">
		<portlet:renderURL var="viewSubmissionURL">
			<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.VIEW_SUBMISSION %>" />
			<portlet:param name="redirect" value="${currentURL}" />
			<portlet:param name="submissionId" value="${submission.submissionId}" />
		</portlet:renderURL>

		<liferay-ui:icon message="view" url="${viewSubmissionURL}" />
	</c:if>

	<%-- Grade action. --%>

	<c:if test="${assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'GRADE_SUBMISSION' )}">
		<portlet:renderURL var="gradeSubmissionURL">
			<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.GRADE_SUBMISSION %>" />
			<portlet:param name="redirect" value="${currentURL}" />
			<portlet:param name="submissionId" value="${submission.submissionId}" />
		</portlet:renderURL>

		<liferay-ui:icon message="grade" url="${gradeSubmissionURL}" />
	</c:if>

	<%-- Edit action. Don't allow editing graded submission. --%>

	<c:if 
		test="${submission.grade lt 0 
			and (assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'EDIT_SUBMISSION')
			or submission.studentId eq user.userId)}">

		<portlet:renderURL var="editSubmissionURL">
			<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.EDIT_SUBMISSION %>" />
			<portlet:param name="redirect" value="${currentURL}" />
			<portlet:param name="submissionId" value="${submission.submissionId}" />
		</portlet:renderURL>

		<liferay-ui:icon message="edit" url="${editSubmissionURL}" />
	</c:if>
	
	<%-- Delete action. --%>
	
	<c:if test="${assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'DELETE_SUBMISSION' )  or submission.studentId eq user.userId}">
	
		<portlet:actionURL var="deleteSubmissionURL" name="<%=MVCCommandNames.DELETE_SUBMISSION %>">
			<portlet:param name="redirect" value="${currentURL}" />
			<portlet:param name="submissionId" value="${submission.submissionId}" />
		</portlet:actionURL>

		<liferay-ui:icon-delete message="delete" url="${deleteSubmissionURL}" confirmation="are-you-sure-to-delete" />
	</c:if>

</liferay-ui:icon-menu>
```

**entry_search_columns.jspf**
```html   
<%-- Generate view submission URL. --%>

<portlet:renderURL var="viewSubmissionURL">
	<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.VIEW_SUBMISSION %>" />
	<portlet:param name="redirect" value="${currentURL}" />
	<portlet:param name="submissionId" value="${entry.submissionId}" />
</portlet:renderURL>

<%--Submission ID. --%>

<liferay-ui:search-container-column-text 
	href="${viewSubmissionURL}" 
	name="id" 
	value="${entry.submissionId}"
/>

<%-- Student. --%>

<liferay-ui:search-container-column-user 
	href="${viewSubmissionURL}" 
	name="student" 
	userId="${entry.studentId}" 
/>

<%-- Submit date. --%>

<liferay-ui:search-container-column-date 
	name="submit-date" 
	property="submitDate"
/>

<%-- Grade --%>

<liferay-ui:search-container-column-text name="grade">
	<c:choose>
		<c:when test="${entry.grade lt 0}">
			<liferay-ui:message key="submission-not-graded" />
		</c:when>
		<c:otherwise>
			<fmt:formatNumber value="${entry.grade}" type="number" />
		</c:otherwise>
	</c:choose>
</liferay-ui:search-container-column-text>

<%-- Actions menu. --%>

<liferay-ui:search-container-column-jsp 
	name="actions"
	path="/submission/entry_actions.jsp" 
/>
```

**grade_submission.jsp**
```html   
<%--
	Single submission view.
--%>

<%@ include file="/init.jsp"%>

<%--Grading action url. --%>

<portlet:actionURL name="<%=MVCCommandNames.GRADE_SUBMISSION %>" var="submissionGradingURL"> 
	<portlet:param name="redirect" value="${param.redirect}" />
</portlet:actionURL>

<div class="container-fluid-1280">

	<h1><liferay-ui:message key="submission-information" /></h1>

	<div class="submission-metadata">

		<dl>
			<dt><liferay-ui:message key="created" /></dt>
			<dd>${createDate}</dd>
	
			<dt><liferay-ui:message key="created-by" /></dt>
			<dd>${student}</dd>

			<dt><liferay-ui:message key="grade" /></dt>
			<dd>	
				<c:choose>
					<c:when test="${submission.grade lt 0}">
						<i><liferay-ui:message key="not-graded" /></i>
					</c:when>
				
					<c:otherwise>
						<fmt:formatNumber value="${submission.grade}" type="number" />
					</c:otherwise>
				</c:choose>
			</dd>	
			<dt><liferay-ui:message key="submission-comment" /></dt>
			<dd>
				<c:choose>
					<c:when test="${empty submission.comment}">
						<i><liferay-ui:message key="no-comment" /></i>
					</c:when>
					<c:otherwise>
						${submission.comment}
					</c:otherwise>
				</c:choose>
			</dd>	
		</dl>
	</div>

	<h2><liferay-ui:message key="submission-text" /></h2>
	
	<div class="submission-text">
		${submission.submissionText}
	</div>

	<h2><liferay-ui:message key="grading" /></h2>

		<aui:form action="${submissionGradingURL}" name="gradingForm"  onSubmit="event.preventDefault();">

			<aui:input name="assignmentId" value="${submission.assignmentId}" type="hidden" />
			<aui:input name="submissionId" value="${submission.submissionId}" type="hidden" />

			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>
					<aui:field-wrapper required="true">
						<label for="grade"><liferay-ui:message key="grade-submission" /></label>
						<aui:input name="grade" value="${submission.grade}" min="4" max="10" type="number" />
					</aui:field-wrapper>			
				</aui:fieldset>
				<aui:fieldset>
					<aui:field-wrapper required="true">
						<label for="comment"><liferay-ui:message key="submission-comment" /></label>	
						<liferay-ui:input-editor contents="${submission.comment}"
							editorName="alloyeditor"
							name="commentEditor" placeholder="comment"  />
							<aui:input name="comment" type="hidden" />
					</aui:field-wrapper>
				</aui:fieldset>
				
			</aui:fieldset-group>
				<aui:button cssClass="btn btn-primary" type="submit" />
				<aui:button cssClass="btn btn-secondary" onClick="${param.redirect}" type="cancel"  />
		</aui:form>	

	<aui:script>
	
		/**
		* Handle form submit. Set editor value to the hidden field 
		* which transports the value to the backend.
		*/
		AUI().ready(function() {
			
			$('#<portlet:namespace />gradingForm').on('submit', function() {
	
				var editorValue = window['<portlet:namespace />commentEditor'].getHTML();
									
				window['<portlet:namespace />comment'].value = editorValue;
	
				submitForm(document.<portlet:namespace />gradingForm);
			});
		});
	
	</aui:script>
</div>
```

**view_submission.jsp**
```html   
<%--
	Single submission view.
--%>

<%@ include file="/init.jsp"%>

<%--Success messages. --%>

<liferay-ui:success key="submissionGraded" message="submission-graded-succesfully" />

<div class="container-fluid-1280">

	<h1><liferay-ui:message key="submission-information" /></h1>

	<div class="submission-metadata">

		<dl>
			<dt><liferay-ui:message key="created" /></dt>
			<dd>${createDate}</dd>
	
			<dt><liferay-ui:message key="created-by" /></dt>
			<dd>${student}</dd>

			<dt><liferay-ui:message key="grade" /></dt>
			<dd>	
				<c:choose>
					<c:when test="${submission.grade lt 0}">
						<i><liferay-ui:message key="not-graded" /></i>
					</c:when>
				
					<c:otherwise>
						<fmt:formatNumber value="${submission.grade}" type="number" />
					</c:otherwise>
				</c:choose>
			</dd>	
			<dt><liferay-ui:message key="submission-comment" /></dt>
			<dd>
				<c:choose>
					<c:when test="${empty submission.comment}">
						<i><liferay-ui:message key="no-comment" /></i>
					</c:when>
					<c:otherwise>
						${submission.comment}
					</c:otherwise>
				</c:choose>
			</dd>	
		</dl>
	</div>

	<h2><liferay-ui:message key="submission-text" /></h2>
	
	<div class="submission-text">
		${submission.submissionText}
	</div>
	
	<aui:button-row>

		<%-- Show edit button if permissions allow and submissions is not graded. --%>

		<c:if
			test="${submission.grade lt 0 
				and (assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'DELETE_SUBMISSION' ) 
				or submission.studentId eq user.userId)}">

			<portlet:renderURL var="editSubmissionURL">
				<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.EDIT_SUBMISSION %>" />
				<portlet:param name="redirect" value="${currentURL}" />
				<portlet:param name="submissionId" value="${submission.submissionId}" />
			</portlet:renderURL>

			<aui:button cssClass="btn btn-primary" onClick="${editSubmissionURL}" value="edit" />

		</c:if>

		<%--Show grading button. --%>

		<c:if
			test="${assignmentPermission.contains(
				permissionChecker, assignment.assignmentId, 'GRADE_SUBMISSION' )}">

			<portlet:renderURL var="gradeSubmissionURL">
				<portlet:param name="mvcRenderCommandName" value="<%=MVCCommandNames.GRADE_SUBMISSION %>" />
				<portlet:param name="redirect" value="${currentURL}" />
				<portlet:param name="submissionId" value="${submission.submissionId}" />
			</portlet:renderURL>
	
			<aui:button cssClass="btn btn-primary" onClick="${gradeSubmissionURL}" value="grade" />
		</c:if>
	
		<aui:button cssClass="btn btn-secondary" onClick="${param.redirect}" type="cancel"  />
	</aui:button-row>
</div>	
```

#### Customize the JSP for Showing a Single Assigment	

Implement showing the list of submissions on single Assignment view:

1. **Open** the JSP file `src/main/resources/META-INF/resources/assignment/view_assignment.jsp`
1. **Replace** the contents with the following. See the highlighted lines for changes:

```html
<%@ include file="/init.jsp"%>

<liferay-ui:success key="assignmentUpdated" message="assignment-updated-successfully" />
<liferay-ui:success key="submissionAdded" message="submission-added-succesfully" />
<liferay-ui:success key="submissionDeleted" message="submission-deleted-succesfully" />
<liferay-ui:success key="submissionGraded" message="submission-graded-succesfully" />

<div class="container-fluid-1280">

	<h1>${assignment.getTitle(locale)}</h1>

	<h2><liferay-ui:message key="assignment-information" /></h2>
	
	<div class="assignment-metadata">

		<dl>
			<dt><liferay-ui:message key="created" /></dt>
			<dd>${createDate}</dd>
	
			<dt><liferay-ui:message key="created-by" /></dt>
			<dd>${assignment.userName}</dd>
	
			<dt><liferay-ui:message key="assignment-duedate" /></dt>
			<dd>${dueDate}</dd>
	
			<dt><liferay-ui:message key="description" /></dt>
			<dd>${assignment.getDescription(locale)}</dd>
		</dl>
	</div>
	
	<!-- Submissions -->

	<c:choose>
		<c:when test="${assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'VIEW_SUBMISSIONS' )}">
					
			<h2><liferay-ui:message key="submissions" /></h2>
			
			<%-- The search container. --%>
			
			<liferay-ui:search-container 
				emptyResultsMessage="submissions"
				id="submissionEntries"
				iteratorURL="${portletURL}" 
				total="${submissiontCount}">
							
				<liferay-ui:search-container-results results="${submissions}" />
		
				<liferay-ui:search-container-row
					className="com.liferay.training.gradebook.model.Submission"
					modelVar="entry">
					
					<%@ include file="/submission/entry_search_columns.jspf" %>
		
				</liferay-ui:search-container-row>
		
				<liferay-ui:search-iterator markupView="lexicon" />
		
			</liferay-ui:search-container>
			
			<c:if test="${assignmentPermission.contains(permissionChecker, assignment.assignmentId, 'ADD_SUBMISSION' )}">
				<portlet:renderURL var="addSubmissionURL">
					<portlet:param name="mvcRenderCommandName"
						value="<%=MVCCommandNames.EDIT_SUBMISSION %>" />
					<portlet:param name="redirect" value="${currentURL}" />
					<portlet:param name="assignmentId" value="${assignment.assignmentId}" />
				</portlet:renderURL>
				<br />
				<p><a href="${addSubmissionURL}"><liferay-ui:message key="add-submission" /></a></p>
			</c:if>
			
		</c:when>
		<c:otherwise>

			<clay:alert
				message='<%=LanguageUtil.get(request, "viewing-submissions-not-allowed") %>'
				title="Info" />
		</c:otherwise>
	</c:choose>
</div>
```

#### Test the Application

> Be sure that you have rebuilt the services. If you encounter `ClassNotFoundExceptions` while testing, remove all the Gradebook modules from the server and redeploy to refresh the resources.

1. **Sign in** to the portal with your web browser.
1. **Open** the Gradebook Application.
1. **Create** or open an Assignment.
1. **Try** creating a submission from the link on the bottom of the page.
1. **Try** editing, grading and deleting submissions.

<img src="../images/submissions.png" style="max-height: 100%"/>