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

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

/**
 * The table class for the &quot;E4G5_E4G5Entry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see E4G5Entry
 * @generated
 */
public class E4G5EntryTable extends BaseTable<E4G5EntryTable> {

	public static final E4G5EntryTable INSTANCE = new E4G5EntryTable();

	public final Column<E4G5EntryTable, Long> e4g5EntryId = createColumn(
		"e4g5EntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<E4G5EntryTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<E4G5EntryTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private E4G5EntryTable() {
		super("E4G5_E4G5Entry", E4G5EntryTable::new);
	}

}