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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;T2P5_Entry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see Entry
 * @generated
 */
public class EntryTable extends BaseTable<EntryTable> {

	public static final EntryTable INSTANCE = new EntryTable();

	public final Column<EntryTable, Long> entryId = createColumn(
		"entryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<EntryTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<EntryTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private EntryTable() {
		super("T2P5_Entry", EntryTable::new);
	}

}