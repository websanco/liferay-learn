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

package com.acme.j7z3.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the J7Z3Entry service. Represents a row in the &quot;J7Z3_J7Z3Entry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see J7Z3EntryModel
 * @generated
 */
@ImplementationClassName("com.acme.j7z3.model.impl.J7Z3EntryImpl")
@ProviderType
public interface J7Z3Entry extends J7Z3EntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.acme.j7z3.model.impl.J7Z3EntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<J7Z3Entry, Long> J7Z3_ENTRY_ID_ACCESSOR =
		new Accessor<J7Z3Entry, Long>() {

			@Override
			public Long get(J7Z3Entry j7z3Entry) {
				return j7z3Entry.getJ7z3EntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<J7Z3Entry> getTypeClass() {
				return J7Z3Entry.class;
			}

		};

}