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

package com.liferay.learn.dxp.importer;

import com.liferay.headless.delivery.client.dto.v1_0.ContentField;
import com.liferay.headless.delivery.client.dto.v1_0.ContentFieldValue;
import com.liferay.headless.delivery.client.dto.v1_0.StructuredContent;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentResource;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.io.File;

import java.nio.charset.StandardCharsets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

/**
 * @author Brian Wing Shun Chan
 * @author Rich Sezov
 */
public class Main {

	public static void main(String[] arguments) throws Exception {
		List<String> fileNames = new ArrayList<>();

		_addFileNames("../docs", fileNames);

		Collections.sort(fileNames);

		for (String fileName : fileNames) {
			System.out.println(fileName);

			if (fileName.endsWith(".md")) {
				String content = FileUtils.readFileToString(
					new File(fileName), StandardCharsets.UTF_8);

				_uploadHtml(_toHTML(content));
			}
		}
	}

	private static void _addFileNames(String fileName, List<String> fileNames) {
		File file = new File(fileName);

		if (file.isDirectory()) {
			for (String currentFileName : file.list()) {
				_addFileNames(fileName + "/" + currentFileName, fileNames);
			}
		}

		fileNames.add(fileName);
	}

	private static String _toHTML(String content) {
		MutableDataSet mutableDataSet = new MutableDataSet();

		HtmlRenderer htmlRenderer = HtmlRenderer.builder(
			mutableDataSet
		).build();

		Parser parser = Parser.builder(
			mutableDataSet
		).build();

		Node node = parser.parse(content);

		String html = htmlRenderer.render(node);

		System.out.println(html.length());

		return html;
	}

	private static void _uploadHtml(String html) {
		StructuredContentResource.Builder builder =
			StructuredContentResource.builder();

		StructuredContentResource structuredContentResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		try {
			StructuredContent structuredContent =
				structuredContentResource.postSiteStructuredContent(
					_siteId,
					new StructuredContent() {
						{
							contentFields = new ContentField[] {
								new ContentField() {
									{
										contentFieldValue =
											new ContentFieldValue() {
												{
													data = html;
												}
											};
										name = "content";
									}
								}
							};
							contentStructureId = _contentStructureId;
							title = "Article";
						}
					});
		}
		catch (Exception ex) {
			Logger.getLogger(
				Main.class.getName()
			).log(
				Level.SEVERE, null, ex
			);
		}
	}

	static long _siteId = 20123;
	static long _contentStructureId = 40301;

}