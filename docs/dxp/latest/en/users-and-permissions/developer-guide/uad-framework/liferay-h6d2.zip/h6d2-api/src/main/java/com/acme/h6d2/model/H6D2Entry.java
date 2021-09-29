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

package com.acme.h6d2.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the H6D2Entry service. Represents a row in the &quot;H6D2_H6D2Entry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see H6D2EntryModel
 * @generated
 */
@ImplementationClassName("com.acme.h6d2.model.impl.H6D2EntryImpl")
@ProviderType
public interface H6D2Entry extends H6D2EntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.acme.h6d2.model.impl.H6D2EntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<H6D2Entry, Long> H6D2_ENTRY_ID_ACCESSOR =
		new Accessor<H6D2Entry, Long>() {

			@Override
			public Long get(H6D2Entry h6d2Entry) {
				return h6d2Entry.getH6d2EntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<H6D2Entry> getTypeClass() {
				return H6D2Entry.class;
			}

		};

}