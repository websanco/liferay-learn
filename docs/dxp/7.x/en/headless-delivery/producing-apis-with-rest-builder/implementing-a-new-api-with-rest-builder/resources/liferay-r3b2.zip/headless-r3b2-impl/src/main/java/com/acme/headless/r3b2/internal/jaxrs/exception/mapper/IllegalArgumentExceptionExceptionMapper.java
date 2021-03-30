package com.acme.headless.r3b2.internal.jaxrs.exception.mapper;

import com.liferay.portal.vulcan.jaxrs.exception.mapper.BaseExceptionMapper;
import com.liferay.portal.vulcan.jaxrs.exception.mapper.Problem;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.osgi.service.component.annotations.Component;

/**
 * @author Jonah the son of Amittai
 */
@Component(
	property = {
		"osgi.jaxrs.application.select=(osgi.jaxrs.name=Acme.Headless.R3B2)",
		"osgi.jaxrs.extension=true",
		"osgi.jaxrs.name=Acme.Headless.R3B2.IllegalArgumentExceptionExceptionMapper"
	},
	service = ExceptionMapper.class
)
public class IllegalArgumentExceptionExceptionMapper
	extends BaseExceptionMapper<IllegalArgumentException> {

	@Override
	protected Problem getProblem(
		IllegalArgumentException illegalArgumentException) {

		return new Problem(
			Response.Status.CONFLICT, illegalArgumentException.getMessage());
	}

}