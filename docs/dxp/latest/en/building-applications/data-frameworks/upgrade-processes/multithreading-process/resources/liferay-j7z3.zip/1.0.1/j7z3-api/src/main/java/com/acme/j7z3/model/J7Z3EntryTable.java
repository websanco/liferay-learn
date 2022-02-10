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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;J7Z3_J7Z3Entry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see J7Z3Entry
 * @generated
 */
public class J7Z3EntryTable extends BaseTable<J7Z3EntryTable> {

	public static final J7Z3EntryTable INSTANCE = new J7Z3EntryTable();

	public final Column<J7Z3EntryTable, Long> j7z3EntryId = createColumn(
		"j7z3EntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<J7Z3EntryTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private J7Z3EntryTable() {
		super("J7Z3_J7Z3Entry", J7Z3EntryTable::new);
	}

}