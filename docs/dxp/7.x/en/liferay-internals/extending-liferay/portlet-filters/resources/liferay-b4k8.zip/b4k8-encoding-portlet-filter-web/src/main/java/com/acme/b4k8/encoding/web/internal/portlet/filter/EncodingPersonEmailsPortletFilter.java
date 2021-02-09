package com.acme.b4k8.encoding.web.internal.portlet.filter;

import com.acme.b4k8.model.Person;
import com.acme.b4k8.web.internal.portlet.B4K8Portlet;

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
	immediate = true,
	property = {
		"javax.portlet.name=" + B4K8Portlet.B4K8_PORTLET_NAME,
		"service.ranking:Integer=1"
	},
	service = PortletFilter.class
)
public class EncodingPersonEmailsPortletFilter implements RenderFilter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(
			RenderRequest request, RenderResponse response, FilterChain chain)
		throws IOException, PortletException {

		Optional.ofNullable(
			(List<Person>)request.getAttribute(B4K8Portlet.MEMBERLIST_ATTRIBUTE)
		).ifPresent(
			personList -> request.setAttribute(
				B4K8Portlet.MEMBERLIST_ATTRIBUTE, _obfuscateEmails(personList))
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