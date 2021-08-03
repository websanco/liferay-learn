package com.acme.r6j9.internal.kaleo.runtime.condition;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.condition.ConditionEvaluator;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;

import java.io.Serializable;

import java.util.Map;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(
	property = "scripting.language=java", service = ConditionEvaluator.class
)
public class R6J9ConditionEvaluator implements ConditionEvaluator {

	@Override
	public String evaluate(
			KaleoCondition kaleoCondition, ExecutionContext executionContext)
		throws PortalException {

		Map<String, Serializable> workflowContext =
			executionContext.getWorkflowContext();
		ServiceContext serviceContext = executionContext.getServiceContext();

		long userId = GetterUtil.getLong(
			workflowContext.get(WorkflowConstants.CONTEXT_USER_ID));

		if (_userLocalService.hasRoleUser(
				serviceContext.getCompanyId(), RoleConstants.ADMINISTRATOR,
				userId, false)) {

			return "approve";
		}

		return "review";
	}

	@Activate
	protected void activate() throws Exception {
		User user = _userLocalService.getDefaultUser(
			_portal.getDefaultCompanyId());

		_workflowDefinitionManager.deployWorkflowDefinition(
			_portal.getDefaultCompanyId(), user.getUserId(),
			LocalizationUtil.updateLocalization(
				StringPool.BLANK, "title", "R6J9 Conditional Approver",
				LocaleUtil.toLanguageId(LocaleUtil.getDefault())),
			"R6J9 Conditional Approver",
			FileUtil.getBytes(
				getClass(), "dependencies/r6j9-workflow-definition.xml"));
	}

	@Reference
	private KaleoDefinitionLocalService _kaleoDefinitionLocalService;

	@Reference
	private Portal _portal;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private WorkflowDefinitionManager _workflowDefinitionManager;

}