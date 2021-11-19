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

package com.acme.w9b7.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the W9B7Entry service. Represents a row in the &quot;W9B7_W9B7Entry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see W9B7EntryModel
 * @generated
 */
@ImplementationClassName("com.acme.w9b7.model.impl.W9B7EntryImpl")
@ProviderType
public interface W9B7Entry extends PersistedModel, W9B7EntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.acme.w9b7.model.impl.W9B7EntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<W9B7Entry, Long> W9B7_ENTRY_ID_ACCESSOR =
		new Accessor<W9B7Entry, Long>() {

			@Override
			public Long get(W9B7Entry w9b7Entry) {
				return w9b7Entry.getW9b7EntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<W9B7Entry> getTypeClass() {
				return W9B7Entry.class;
			}

		};

}