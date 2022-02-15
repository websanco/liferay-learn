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

package com.acme.e4g5.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the E4G5Entry service. Represents a row in the &quot;E4G5_E4G5Entry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see E4G5EntryModel
 * @generated
 */
@ImplementationClassName("com.acme.e4g5.model.impl.E4G5EntryImpl")
@ProviderType
public interface E4G5Entry extends E4G5EntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.acme.e4g5.model.impl.E4G5EntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<E4G5Entry, Long> E4G5_ENTRY_ID_ACCESSOR =
		new Accessor<E4G5Entry, Long>() {

			@Override
			public Long get(E4G5Entry e4g5Entry) {
				return e4g5Entry.getE4g5EntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<E4G5Entry> getTypeClass() {
				return E4G5Entry.class;
			}

		};

}