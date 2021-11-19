/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.acme.s5e6.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the S5E6Entry service. Represents a row in the &quot;S5E6_S5E6Entry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see S5E6EntryModel
 * @generated
 */
@ImplementationClassName("com.acme.s5e6.model.impl.S5E6EntryImpl")
@ProviderType
public interface S5E6Entry extends PersistedModel, S5E6EntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.acme.s5e6.model.impl.S5E6EntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<S5E6Entry, Long> S5E6_ENTRY_ID_ACCESSOR =
		new Accessor<S5E6Entry, Long>() {

			@Override
			public Long get(S5E6Entry s5e6Entry) {
				return s5e6Entry.getS5E6EntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<S5E6Entry> getTypeClass() {
				return S5E6Entry.class;
			}

		};

}