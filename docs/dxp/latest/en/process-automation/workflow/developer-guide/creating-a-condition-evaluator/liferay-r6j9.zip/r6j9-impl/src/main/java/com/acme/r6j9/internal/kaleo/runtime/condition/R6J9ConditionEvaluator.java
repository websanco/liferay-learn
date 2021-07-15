package com.acme.r6j9.internal.kaleo.runtime.condition;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.workflow.kaleo.model.KaleoCondition;
import com.liferay.portal.workflow.kaleo.runtime.ExecutionContext;
import com.liferay.portal.workflow.kaleo.runtime.condition.ConditionEvaluator;

@Component(
	immediate = true, property = "scripting.language=java",
	service = ConditionEvaluator.class
)
public class R6J9ConditionEvaluator implements ConditionEvaluator {

	@Override
	public String evaluate(
			KaleoCondition kaleoCondition, ExecutionContext executionContext)
		throws PortalException {

		// logic here
		return "approved";
	}

}