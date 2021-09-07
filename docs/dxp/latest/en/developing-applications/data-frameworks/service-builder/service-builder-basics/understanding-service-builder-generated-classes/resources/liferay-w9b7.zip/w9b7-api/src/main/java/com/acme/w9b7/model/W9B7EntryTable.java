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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;W9B7_W9B7Entry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see W9B7Entry
 * @generated
 */
public class W9B7EntryTable extends BaseTable<W9B7EntryTable> {

	public static final W9B7EntryTable INSTANCE = new W9B7EntryTable();

	public final Column<W9B7EntryTable, Long> w9b7EntryId = createColumn(
		"w9b7EntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<W9B7EntryTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<W9B7EntryTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private W9B7EntryTable() {
		super("W9B7_W9B7Entry", W9B7EntryTable::new);
	}

}