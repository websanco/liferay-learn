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

package com.acme.j7z3.internal.upgrade.v1_0_1;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;

import java.sql.PreparedStatement;

/**
 * @author Brian Wing Shun Chan
 */
public class J7Z3EntryUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		processConcurrently(
			"select j7z3EntryId, name from J7Z3_J7Z3Entry",
			resultSet -> new Object[] {
				resultSet.getLong("j7z3EntryId"), resultSet.getString("name")
			},
			columns -> {
				long j7z3EntryId = (Long)columns[0];

				try (PreparedStatement preparedStatement =
						connection.prepareStatement(
							"update J7Z3_J7Z3Entry set name = ? where " +
								"j7z3EntryId = ?")) {

					preparedStatement.setString(1, "baker");
					preparedStatement.setLong(2, j7z3EntryId);

					preparedStatement.executeUpdate();
				}
			},
			null);
	}

}