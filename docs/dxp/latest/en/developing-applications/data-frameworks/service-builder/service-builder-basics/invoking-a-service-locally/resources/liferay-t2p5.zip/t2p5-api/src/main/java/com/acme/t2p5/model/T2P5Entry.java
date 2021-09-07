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

package com.acme.t2p5.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the T2P5Entry service. Represents a row in the &quot;T2P5_T2P5Entry&quot; database table, with each column mapped to a property of this class.
 *
 * @author Brian Wing Shun Chan
 * @see T2P5EntryModel
 * @generated
 */
@ImplementationClassName("com.acme.t2p5.model.impl.T2P5EntryImpl")
@ProviderType
public interface T2P5Entry extends PersistedModel, T2P5EntryModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.acme.t2p5.model.impl.T2P5EntryImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<T2P5Entry, Long> T2P5_ENTRY_ID_ACCESSOR =
		new Accessor<T2P5Entry, Long>() {

			@Override
			public Long get(T2P5Entry t2p5Entry) {
				return t2p5Entry.getT2p5EntryId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<T2P5Entry> getTypeClass() {
				return T2P5Entry.class;
			}

		};

}