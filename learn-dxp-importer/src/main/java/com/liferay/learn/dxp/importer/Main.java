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
import com.liferay.headless.delivery.client.dto.v1_0.StructuredContentFolder;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.pagination.Pagination;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentFolderResource;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentResource;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HashMapBuilder;

import com.vladsch.flexmark.ext.anchorlink.AnchorLinkExtension;
import com.vladsch.flexmark.ext.aside.AsideExtension;
import com.vladsch.flexmark.ext.attributes.AttributesExtension;
import com.vladsch.flexmark.ext.definition.DefinitionExtension;
import com.vladsch.flexmark.ext.footnotes.FootnoteExtension;
import com.vladsch.flexmark.ext.gfm.strikethrough.StrikethroughExtension;
import com.vladsch.flexmark.ext.media.tags.MediaTagsExtension;
import com.vladsch.flexmark.ext.superscript.SuperscriptExtension;
import com.vladsch.flexmark.ext.tables.TablesExtension;
import com.vladsch.flexmark.ext.toc.TocExtension;
import com.vladsch.flexmark.ext.typographic.TypographicExtension;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.io.File;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;

import org.apache.commons.io.FileUtils;

/**
 * @author Brian Wing Shun Chan
 * @author Rich Sezov
 */
public class Main {

	public static void main(String[] arguments) throws Exception {
		Set<String> fileNames = new TreeSet<>();

		_addFileNames("../docs", fileNames);
		
		_initStructuredContentResources();

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

	private static StructuredContentFolder _addTopLevelFolder(String folderName) 
		throws Exception {

		return _structuredContentFolderResource.postSiteStructuredContentFolder(
			_GROUP_ID, new StructuredContentFolder() {
				{
					description = "";
					name = folderName;
				}
		});

	}

	private static long _getFolderId(String fileName) throws Exception {
		String[] fileFolders = fileName.split(Matcher.quoteReplacement(System.getProperty("file.separator")));

		List<String> folderList = _sanitizeFolderList(fileFolders);

		return _retrieveCreateFolderId(folderList, _getTopLevelFolders());

	}


	private static String _getTitle(String text) {
		int x = text.indexOf("#");

		int y = text.indexOf(StringPool.NEW_LINE, x);

		String title = text.substring(x + 1, y);

		return title.trim();
	}

	private static ArrayList<StructuredContentFolder> _getTopLevelFolders() throws Exception {

		Page<StructuredContentFolder> page = 
			_structuredContentFolderResource.getSiteStructuredContentFoldersPage(
			_GROUP_ID, null, null, null, null, Pagination.of(1,50), null);

		ArrayList<StructuredContentFolder> topLevelFolders = (ArrayList)page.getItems();

		return topLevelFolders;
	}

	private static void _initStructuredContentResources() {

		StructuredContentResource.Builder builder =
			StructuredContentResource.builder();

		_structuredContentResource =
			builder.authentication(
				"test@liferay.com", "test"
			).build();

		StructuredContentFolderResource.Builder folderBuilder = 
			StructuredContentFolderResource.builder();

		_structuredContentFolderResource = 
			folderBuilder.authentication(
				"test@liferay.com", "test"
			).build();
	}

	private static long _retrieveCreateFolderId(List<String> fileList, 
		ArrayList<StructuredContentFolder> liferayList) throws Exception {
		long folderId = 0;

		StructuredContentFolder folder;

		// If we have an empty liferayList coming in
		if (liferayList.size() == 0) {

			folder = _addTopLevelFolder(fileList.get(0));

			folderId = folder.getId();

			liferayList = _getTopLevelFolders();

		} 

		for (int i=0;i<fileList.size();i++) {
			String folderName = fileList.get(i);

			if (liferayList.size() > 0) {

				boolean matched = false; 

				for (StructuredContentFolder liferayFolder : liferayList) {

					 if (folderName.equalsIgnoreCase(liferayFolder.getName())) {

						folderId = liferayFolder.getId();

						matched = true;

					} 
					
				}

				if (matched == false) {

					if (folderId == 0) {
						folder = _addTopLevelFolder(folderName);

						folderId = folder.getId();

					} else {
					folder = 
						_structuredContentFolderResource.postStructuredContentFolderStructuredContentFolder(
						folderId, new StructuredContentFolder() { 
							{
								description = "";
								name = folderName;
							}
						});

					folderId = folder.getId();

					}
				}

			} else {

				folder = 
					_structuredContentFolderResource.postStructuredContentFolderStructuredContentFolder(
					folderId, new StructuredContentFolder() { 
						{
							description = "";
							name = folderName;
						}
					});

				folderId = folder.getId();
			}

			Page<StructuredContentFolder> page = 
				_structuredContentFolderResource.getStructuredContentFolderStructuredContentFoldersPage(
					folderId, null, null, null, Pagination.of(1, 50), null);

			liferayList = (ArrayList)page.getItems();

		}

		return folderId;
	}

	private static List _sanitizeFolderList(String[] folderList) {

		ArrayList<String> fileFolders = new ArrayList();
		
		for (int i=0;i<folderList.length;i++) {
			String fileFolder = folderList[i];

			if (!fileFolder.equalsIgnoreCase("..") && !fileFolder.equalsIgnoreCase("docs") && 
				!fileFolder.equalsIgnoreCase("latest") && !fileFolder.equalsIgnoreCase("en") && 
				!fileFolder.equalsIgnoreCase("ja") && !fileFolder.endsWith(".md") && 
				!fileFolder.endsWith(".html") && 
				!fileFolder.endsWith(".rst")) {

				fileFolders.add(fileFolder);

			}

		}

		return fileFolders;
	}

	private static String _toHTML(String text) {
		MutableDataSet mutableDataSet = new MutableDataSet().set(
			AsideExtension.ALLOW_LEADING_SPACE, true
		).set(
			AsideExtension.EXTEND_TO_BLANK_LINE, false
		).set(
			AsideExtension.IGNORE_BLANK_LINE, false
		).set(
			AsideExtension.INTERRUPTS_ITEM_PARAGRAPH, true
		).set(
			AsideExtension.INTERRUPTS_PARAGRAPH, true
		).set(
			AsideExtension.WITH_LEAD_SPACES_INTERRUPTS_ITEM_PARAGRAPH, true
		).set(
			HtmlRenderer.GENERATE_HEADER_ID, true
		).set(
			Parser.EXTENSIONS,
			Arrays.asList(
				AnchorLinkExtension.create(), AsideExtension.create(),
				AttributesExtension.create(), DefinitionExtension.create(),
				FootnoteExtension.create(), MediaTagsExtension.create(),
				StrikethroughExtension.create(), SuperscriptExtension.create(),
				TablesExtension.create(), TocExtension.create(),
				TypographicExtension.create(),
				YamlFrontMatterExtension.create())
		);

		HtmlRenderer htmlRenderer = HtmlRenderer.builder(
			mutableDataSet
		).build();

		Parser parser = Parser.builder(
			mutableDataSet
		).build();

		return htmlRenderer.render(parser.parse(text));
	}

	private static StructuredContent _toStructuredContent(String fileName)
		throws Exception {


		StructuredContent structuredContent = new StructuredContent();

		String englishText = FileUtils.readFileToString(
			new File(fileName), StandardCharsets.UTF_8);

		ContentFieldValue englishContentFieldValue = new ContentFieldValue() {
			{
				data = _toHTML(englishText);
			}
		};

		String englishTitle = _getTitle(englishText);

		ContentFieldValue japaneseContentFieldValue;

		String japaneseTitle = englishTitle;

		File japaneseFile = new File(fileName.replace("/en/", "/ja/"));

		if (japaneseFile.exists()) {
			String japaneseText = FileUtils.readFileToString(
				japaneseFile, StandardCharsets.UTF_8);

			japaneseContentFieldValue = new ContentFieldValue() {
				{
					data = _toHTML(japaneseText);
				}
			};
			japaneseTitle = _getTitle(japaneseText);
			structuredContent.setContentFields(
				new ContentField[] {
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
				});
			structuredContent.setTitle_i18n(
				HashMapBuilder.put(
					"en-US", englishTitle
				).put(
					"ja-JP", japaneseTitle
				).build());
		}
		else {
			structuredContent.setContentFields(
				new ContentField[] {
					new ContentField() {
						{
							contentFieldValue = englishContentFieldValue;
							name = "content";
						}
					}
				});
		}

		structuredContent.setContentStructureId(_CONTENT_STRUCTURE_ID);
		structuredContent.setTitle(_getTitle(englishText));

		return structuredContent;
	}

	private static void _uploadHTML(String fileName) throws Exception {
		long folderId = _getFolderId(fileName);

		_structuredContentResource.postStructuredContentFolderStructuredContent(
			folderId, _toStructuredContent(fileName));
	}

	private static final long _CONTENT_STRUCTURE_ID = 40090;

	private static final long _GROUP_ID = 20122;

	private static StructuredContentResource _structuredContentResource;

	private static StructuredContentFolderResource _structuredContentFolderResource;

}