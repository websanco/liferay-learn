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

package com.acme.h6d2.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;H6D2_H6D2&quot; database table.
 *
 * @author Brian Wing Shun Chan
 * @see H6D2
 * @generated
 */
public class H6D2Table extends BaseTable<H6D2Table> {

	public static final H6D2Table INSTANCE = new H6D2Table();

	public final Column<H6D2Table, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<H6D2Table, Long> h6d2Id = createColumn(
		"h6d2Id", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<H6D2Table, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<H6D2Table, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<H6D2Table, Long> userId = createColumn(
		"userId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<H6D2Table, String> userName = createColumn(
		"userName", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<H6D2Table, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<H6D2Table, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<H6D2Table, String> todo = createColumn(
		"todo", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);

	private H6D2Table() {
		super("H6D2_H6D2", H6D2Table::new);
	}

}