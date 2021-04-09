package com.acme.k8s2;

import aQute.bnd.annotation.ProviderType;

@ProviderType
public interface Greeter {

	public void greet(String name);

}