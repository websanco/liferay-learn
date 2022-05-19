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
package com.liferay.learn.dxp.importer.util;

import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.CharSubSequence;

/**
 *
 * @author Rich Sezov
 */
public class ImporterUtil {

	public static String BStoString(BasedSequence sequence) {
		String string = "";

		for (int i=0; i < sequence.length(); i++) {
			char c = sequence.charAt(i);
			string = string + c;
		}
		
		return string;

	}
	
	public static BasedSequence StringtoBS (String string) {
		
		BasedSequence bs = CharSubSequence.of(string.toCharArray(), 0, string.length());

		return bs;
		
	}
	
}
