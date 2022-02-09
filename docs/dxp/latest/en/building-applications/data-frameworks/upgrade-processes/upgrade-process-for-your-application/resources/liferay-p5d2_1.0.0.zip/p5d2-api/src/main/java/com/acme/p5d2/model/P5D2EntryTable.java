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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;P5D2_P5D2Entry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see P5D2Entry
 * @generated
 */
public class P5D2EntryTable extends BaseTable<P5D2EntryTable> {

	public static final P5D2EntryTable INSTANCE = new P5D2EntryTable();

	public final Column<P5D2EntryTable, Long> p5d2EntryId = createColumn(
		"p5d2EntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<P5D2EntryTable, String> able = createColumn(
		"able", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private P5D2EntryTable() {
		super("P5D2_P5D2Entry", P5D2EntryTable::new);
	}

}