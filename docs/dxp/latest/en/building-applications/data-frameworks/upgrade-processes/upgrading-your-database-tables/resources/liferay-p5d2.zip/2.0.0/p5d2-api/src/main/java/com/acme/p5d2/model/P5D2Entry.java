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

package com.acme.p5d2.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the P5D2Entry service. Represents a row in the &quot;P5D2_P5D2Entry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see P5D2EntryModel
 * @generated
 */
@ImplementationClassName("com.acme.p5d2.model.impl.P5D2EntryImpl")
@ProviderType
public interface P5D2Entry extends P5D2EntryModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.acme.p5d2.model.impl.P5D2EntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<P5D2Entry, Long> P5D2_ENTRY_ID_ACCESSOR =
		new Accessor<P5D2Entry, Long>() {

			@Override
			public Long get(P5D2Entry p5d2Entry) {
				return p5d2Entry.getP5d2EntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<P5D2Entry> getTypeClass() {
				return P5D2Entry.class;
			}

		};

}