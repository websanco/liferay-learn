package com.acme.b4k8.model;

public class Person {

	public Person(String name, String email) {
		_name = name;
		_email = email;
	}

	public String getEmail() {
		return _email;
	}

	public String getName() {
		return _name;
	}

	private final String _email;
	private final String _name;

}