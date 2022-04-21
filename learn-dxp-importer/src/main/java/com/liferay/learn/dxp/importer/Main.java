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
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HashMapBuilder;

import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.io.File;

import java.nio.charset.StandardCharsets;

import java.util.Set;
import java.util.TreeSet;

import org.apache.commons.io.FileUtils;

/**
 * @author Brian Wing Shun Chan
 * @author Rich Sezov
 */
public class Main {

	public static void main(String[] arguments) throws Exception {
		Set<String> fileNames = new TreeSet<>();

		_addFileNames("../docs", fileNames);

		for (String fileName : fileNames) {
			if (fileName.contains("/en/") && fileName.endsWith(".md")) {
				System.out.println(fileName);

				_uploadHTML(fileName);
			}
		}
	}

	private static void _addFileNames(String fileName, Set<String> fileNames) {
		File file = new File(fileName);

		if (file.isDirectory()) {
			for (String currentFileName : file.list()) {
				_addFileNames(fileName + "/" + currentFileName, fileNames);
			}
		}

		fileNames.add(fileName);
	}

	private static String _getTitle(String content) {
		int x = content.indexOf("#");

		int y = content.indexOf(StringPool.NEW_LINE, x);

		String title = content.substring(x + 1, y);

		return title.trim();
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

	private static void _uploadHTML(String fileName) throws Exception {

		// English

		// TODO The external reference code must be unique and derived from the
		// file name

		String externalReferenceCode = "";

		String englishContent = FileUtils.readFileToString(
			new File(fileName), StandardCharsets.UTF_8);

		String englishTitle = _getTitle(englishContent);

		// Japanese

		File japaneseFile = new File(fileName.replace("/en/", "/ja/"));

		StructuredContentResource.Builder builder =
			StructuredContentResource.builder();

		StructuredContentResource structuredContentResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		StructuredContent structuredContent = new StructuredContent();

		String[] languages = {"en-US", "ja-JP"};

		structuredContent.setAvailableLanguages(languages);

		ContentField[] contentFields;

		ContentFieldValue englishContentFieldValue = new ContentFieldValue() {
			{
				data = _toHTML(englishContent);
			}
		};

		if (japaneseFile.exists()) {
			String japaneseContent = FileUtils.readFileToString(
				japaneseFile, StandardCharsets.UTF_8);

			String japaneseTitle = _getTitle(japaneseContent);

			ContentFieldValue japaneseContentFieldValue =
				new ContentFieldValue() {
					{
						data = _toHTML(japaneseContent);
					}
				};
			contentFields = new ContentField[] {
				new ContentField() {
					{
						contentFieldValue = englishContentFieldValue;

						contentFieldValue_i18n = HashMapBuilder.put(
							"en-US", englishContentFieldValue
						).put(
							"ja-JP", japaneseContentFieldValue
						).build();

						name = "content";
					}
				}
			};

			structuredContent.setTitle_i18n(
				HashMapBuilder.put(
					"en-US", englishTitle
				).put(
					"ja-JP", japaneseTitle
				).build());
		}
		else {
			contentFields = new ContentField[] {
				new ContentField() {
					{
						contentFieldValue = englishContentFieldValue;

						contentFieldValue_i18n = HashMapBuilder.put(
							"en-US", englishContentFieldValue
						).put(
							"ja-JP", englishContentFieldValue
						).build();

						name = "content";
					}
				}
			};

			structuredContent.setTitle_i18n(
				HashMapBuilder.put(
					"en-US", englishTitle
				).put(
					"ja-JP", englishTitle
				).build());
		}

		structuredContent.setContentFields(contentFields);

		structuredContent.setContentStructureId(_CONTENT_STRUCTURE_ID);

		structuredContent.setTitle(_getTitle(englishContent));

		structuredContentResource.postSiteStructuredContent(
			_GROUP_ID, structuredContent);
	}

	private static final long _CONTENT_STRUCTURE_ID = 40288;

	private static final long _GROUP_ID = 20123;

}