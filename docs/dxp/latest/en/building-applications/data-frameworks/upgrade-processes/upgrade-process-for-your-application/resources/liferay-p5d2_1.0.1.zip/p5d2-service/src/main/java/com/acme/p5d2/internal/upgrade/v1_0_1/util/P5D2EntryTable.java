package com.acme.p5d2.internal.upgrade.v1_0_1.util;

import java.sql.Types;

import java.util.HashMap;
import java.util.Map;

/**
 * @author	  Brian Wing Shun Chan
 * @generated
 */
public class P5D2EntryTable {

	public static final String TABLE_NAME = "P5D2_P5D2Entry";

	public static final Object[][] TABLE_COLUMNS = {
		{"p5d2EntryId", Types.BIGINT}, {"baker", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("p5d2EntryId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("baker", Types.VARCHAR);

}
	public static final String TABLE_SQL_CREATE =
"create table P5D2_P5D2Entry (p5d2EntryId LONG not null primary key,baker VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table P5D2_P5D2Entry";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
	};

}