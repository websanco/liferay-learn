package com.acme.r9u2;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface Greeter {

	public void greet(String name);

}