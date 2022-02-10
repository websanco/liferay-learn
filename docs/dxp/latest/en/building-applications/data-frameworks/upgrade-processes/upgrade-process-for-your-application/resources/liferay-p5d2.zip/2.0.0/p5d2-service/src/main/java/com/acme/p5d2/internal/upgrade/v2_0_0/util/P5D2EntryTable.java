package com.acme.p5d2.internal.upgrade.v2_0_0.util;

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
		{"p5d2EntryId", Types.BIGINT}, {"bar", Types.VARCHAR},
		{"able", Types.TIMESTAMP}, {"charlie", Types.VARCHAR}
	};

	public static final Map<String, Integer> TABLE_COLUMNS_MAP =
new HashMap<String, Integer>();

static {
TABLE_COLUMNS_MAP.put("p5d2EntryId", Types.BIGINT);

TABLE_COLUMNS_MAP.put("bar", Types.VARCHAR);

TABLE_COLUMNS_MAP.put("able", Types.TIMESTAMP);

TABLE_COLUMNS_MAP.put("charlie", Types.VARCHAR);

}
	public static final String TABLE_SQL_CREATE =
"create table P5D2_P5D2Entry (p5d2EntryId LONG not null primary key,bar VARCHAR(75) null,able DATE null,charlie VARCHAR(75) null)";

	public static final String TABLE_SQL_DROP = "drop table P5D2_P5D2Entry";

	public static final String[] TABLE_SQL_ADD_INDEXES = {
	};

}