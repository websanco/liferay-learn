package com.acme.b4k8.web.internal.portlet.filter;

import com.acme.b4k8.constants.B4K8PortletKeys;
import com.acme.b4k8.model.Person;
import com.acme.b4k8.web.internal.constants.B4K8WebConstants;

import java.io.IOException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.filter.FilterChain;
import javax.portlet.filter.FilterConfig;
import javax.portlet.filter.PortletFilter;
import javax.portlet.filter.RenderFilter;

import org.osgi.service.component.annotations.Component;

@Component(
	property = {
		"javax.portlet.name=" + B4K8PortletKeys.B4K8,
		"service.ranking:Integer=1"
	},
	service = PortletFilter.class
)
public class B4K8AblePortletFilter implements RenderFilter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			RenderRequest request, RenderResponse response, FilterChain chain)
		throws IOException, PortletException {

		Optional.ofNullable(
			(List<Person>)request.getAttribute(B4K8WebConstants.MEMBERS)
		).ifPresent(
			personList -> request.setAttribute(
				B4K8WebConstants.MEMBERS, _obfuscateEmails(personList))
		);

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws PortletException {
	}

	private List<Person> _obfuscateEmails(List<Person> list) {
		return list.stream(
		).map(
			this::_obfuscatePersonEmail
		).collect(
			Collectors.toList()
		);
	}

	private Person _obfuscatePersonEmail(Person person) {
		String email = person.getEmail();

		return new Person(
			person.getName(),
			email.replaceFirst("(.+)(...)@(...)(.*)", "$1...@...$4"));
	}

}