package com.acme.s7a3.internal.dispatch.executor;

import com.liferay.dispatch.executor.BaseDispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutor;
import com.liferay.dispatch.executor.DispatchTaskExecutorOutput;
import com.liferay.dispatch.model.DispatchTrigger;
import com.liferay.portal.kernel.exception.PortalException;

import java.io.IOException;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"dispatch.task.executor.name=" + S7A3DispatchTaskExecutor.S7A3,
		"dispatch.task.executor.type=" + S7A3DispatchTaskExecutor.S7A3
	},
	service = DispatchTaskExecutor.class
)
public class S7A3DispatchTaskExecutor extends BaseDispatchTaskExecutor {

	public static final String S7A3 = "s7a3";

	@Override
	public void doExecute(
			DispatchTrigger dispatchTrigger,
			DispatchTaskExecutorOutput dispatchTaskExecutorOutput)
		throws IOException, PortalException {

		System.out.println("Hello World!");
	}

	@Override
	public String getName() {
		return null;
	}

}