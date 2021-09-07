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
 * The table class for the &quot;T2P5_T2P5Entry&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see T2P5Entry
 * @generated
 */
public class T2P5EntryTable extends BaseTable<T2P5EntryTable> {

	public static final T2P5EntryTable INSTANCE = new T2P5EntryTable();

	public final Column<T2P5EntryTable, Long> t2p5EntryId = createColumn(
		"t2p5EntryId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<T2P5EntryTable, String> description = createColumn(
		"description", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<T2P5EntryTable, String> name = createColumn(
		"name", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private T2P5EntryTable() {
		super("T2P5_T2P5Entry", T2P5EntryTable::new);
	}

}