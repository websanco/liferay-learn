package com.acme.r6j9.internal.kaleo.runtime.condition;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.LocalizationUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.kernel.workflow.WorkflowDefinitionManager;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.condition.ConditionEvaluator;
import com.liferay.portal.workflow.kaleo.service.KaleoDefinitionLocalService;

import java.io.InputStream;
import java.io.Serializable;

import java.util.List;
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
		List<Company> companies = _companyLocalService.getCompanies();

		for (Company company : companies) {
			ServiceContext serviceContext = new ServiceContext();

			serviceContext.setCompanyId(company.getCompanyId());

			int kaleoDefinitionsCount =
				_kaleoDefinitionLocalService.getKaleoDefinitionsCount(
					"R6J9 Conitional Approver", serviceContext);

			if (kaleoDefinitionsCount > 0) {
				System.out.println("Already have the definition");

				return;
			}

			Class<?> clazz = getClass();

			ClassLoader classLoader = clazz.getClassLoader();

			InputStream inputStream = classLoader.getResourceAsStream(
				"definitions/r6j9-workflow-definition.xml");

			if (inputStream == null) {
				if (_log.isWarnEnabled()) {
					_log.warn(
						"Unable to find file for R6J9 Conditional Approver " +
							"with file name r6j9-workflow-definition.xml");
				}

				return;
			}

			User defaultUser = _userLocalService.getDefaultUser(
				company.getCompanyId());

			String localizedTitle = LocalizationUtil.updateLocalization(
				StringPool.BLANK, "title", "R6J9 Conditional Approver",
				LocaleUtil.toLanguageId(LocaleUtil.getDefault()));

			_workflowDefinitionManager.deployWorkflowDefinition(
				serviceContext.getCompanyId(), defaultUser.getUserId(),
				localizedTitle, "R6J9 Conditional Approver",
				FileUtil.getBytes(inputStream));
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
		R6J9ConditionEvaluator.class);

	@Reference
	private CompanyLocalService _companyLocalService;

	@Reference
	private KaleoDefinitionLocalService _kaleoDefinitionLocalService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private WorkflowDefinitionManager _workflowDefinitionManager;

}