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

package com.acme.p5d2.internal.upgrade.v2_0_0;

import com.acme.p5d2.internal.upgrade.v2_0_0.util.P5D2EntryTable;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Brian Wing Shun Chan
 */
public class P5D2EntryUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		alter(
			P5D2EntryTable.class,
			new AlterColumnName("foo", "bar VARCHAR(75) null"),
			new AlterColumnType("able","DATE"),
			new AlterTableAddColumn("charlie", "VARCHAR(75) null"),
			new AlterTableDropColumn("baker"));
	}

}