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
import com.liferay.headless.delivery.client.dto.v1_0.Document;
import com.liferay.headless.delivery.client.dto.v1_0.DocumentFolder;
import com.liferay.headless.delivery.client.dto.v1_0.StructuredContent;
import com.liferay.headless.delivery.client.dto.v1_0.StructuredContentFolder;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentFolderResource;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentFolderResource;
import com.liferay.headless.delivery.client.resource.v1_0.StructuredContentResource;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.util.HashMapBuilder;

import com.vladsch.flexmark.ast.Image;
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
import com.vladsch.flexmark.ext.yaml.front.matter.AbstractYamlFrontMatterVisitor;
import com.vladsch.flexmark.ext.yaml.front.matter.YamlFrontMatterExtension;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.ast.Visitor;
import com.vladsch.flexmark.util.data.MutableDataSet;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.CharSubSequence;

import java.io.File;

import java.nio.charset.StandardCharsets;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

/**
 * @author Brian Wing Shun Chan
 * @author Rich Sezov
 */
public class Main {

	public static void main(String[] arguments) throws Exception {
		Main main = new Main("test@liferay.com", "test");

		main.uploadToLiferay();
	}

	public Main(String login, String password) {
		_addFileNames("../docs");

		_initFlexmark();
		_initResourceBuilders(login, password);
	}

	public void uploadToLiferay() throws Exception {
		for (String fileName : _fileNames) {
			if (!fileName.contains("/en/") || !fileName.endsWith(".md")) {
				continue;
			}

			System.out.println(fileName);

			_structuredContentResource.
				postStructuredContentFolderStructuredContent(
					_getStructuredContentFolderId(fileName),
					_toStructuredContent(fileName));
		}
	}

	private void _addFileNames(String fileName) {
		File file = new File(fileName);

		if (file.isDirectory()) {
			for (String currentFileName : file.list()) {
				_addFileNames(fileName + "/" + currentFileName);
			}
		}

		_fileNames.add(fileName);
	}

	private Long _getDocumentFolderId(
			String dirName, Long parentDocumentFolderId)
		throws Exception {

		String key = parentDocumentFolderId + "#" + dirName;

		Long documentFolderId = _documentFolderIds.get(key);

		if (documentFolderId != null) {
			return documentFolderId;
		}

		DocumentFolder documentFolder = null;

		if (parentDocumentFolderId == 0) {
			Page<DocumentFolder> page =
				_documentFolderResource.getSiteDocumentFoldersPage(
					_GROUP_ID, null, null, null, "name eq '" + dirName + "'",
					null, null);

			documentFolder = page.fetchFirstItem();

			if (documentFolder == null) {
				documentFolder = _documentFolderResource.postSiteDocumentFolder(
					_GROUP_ID,
					new DocumentFolder() {
						{
							description = "";
							name = dirName;
						}
					});
			}
		}
		else {
			Page<DocumentFolder> page =
				_documentFolderResource.getDocumentFolderDocumentFoldersPage(
					parentDocumentFolderId, null, null, null,
					"name eq '" + dirName + "'", null, null);

			documentFolder = page.fetchFirstItem();

			if (documentFolder == null) {
				documentFolder =
					_documentFolderResource.postDocumentFolderDocumentFolder(
						parentDocumentFolderId,
						new DocumentFolder() {
							{
								description = "";
								name = dirName;
							}
						});
			}
		}

		documentFolderId = documentFolder.getId();

		_documentFolderIds.put(key, documentFolderId);

		return documentFolderId;
	}

	private Long _getStructuredContentFolderId(String fileName)
		throws Exception {

		Long structuredContentFolderId = 0L;

		String[] parts = fileName.split(
			Matcher.quoteReplacement(System.getProperty("file.separator")));

		for (String part : parts) {
			if (part.endsWith(".html") || part.endsWith(".md") ||
				part.endsWith(".rst") || part.equalsIgnoreCase("..") ||
				part.equalsIgnoreCase("docs") || part.equalsIgnoreCase("en") ||
				part.equalsIgnoreCase("ja") ||
				part.equalsIgnoreCase("latest")) {

				continue;
			}

			String dirName = part;

			structuredContentFolderId = _getStructuredContentFolderId(
				dirName, structuredContentFolderId);
		}

		return structuredContentFolderId;
	}

	private Long _getStructuredContentFolderId(
			String dirName, Long parentStructuredContentFolderId)
		throws Exception {

		String key = parentStructuredContentFolderId + "#" + dirName;

		Long structuredContentFolderId = _structuredContentFolderIds.get(key);

		if (structuredContentFolderId != null) {
			return structuredContentFolderId;
		}

		StructuredContentFolder structuredContentFolder = null;

		if (parentStructuredContentFolderId == 0) {
			Page<StructuredContentFolder> page =
				_structuredContentFolderResource.
					getSiteStructuredContentFoldersPage(
						_GROUP_ID, null, null, null,
						"name eq '" + dirName + "'", null, null);

			structuredContentFolder = page.fetchFirstItem();

			if (structuredContentFolder == null) {
				structuredContentFolder =
					_structuredContentFolderResource.
						postSiteStructuredContentFolder(
							_GROUP_ID,
							new StructuredContentFolder() {
								{
									description = "";
									name = dirName;
								}
							});
			}
		}
		else {
			Page<StructuredContentFolder> page =
				_structuredContentFolderResource.
					getStructuredContentFolderStructuredContentFoldersPage(
						parentStructuredContentFolderId, null, null,
						"name eq '" + dirName + "'", null, null);

			structuredContentFolder = page.fetchFirstItem();

			if (structuredContentFolder == null) {
				structuredContentFolder =
					_structuredContentFolderResource.
						postStructuredContentFolderStructuredContentFolder(
							parentStructuredContentFolderId,
							new StructuredContentFolder() {
								{
									description = "";
									name = dirName;
								}
							});
			}
		}

		structuredContentFolderId = structuredContentFolder.getId();

		_structuredContentFolderIds.put(key, structuredContentFolderId);

		return structuredContentFolderId;
	}

	private String _getTitle(String text) {
		int x = text.indexOf("#");

		int y = text.indexOf(StringPool.NEW_LINE, x);

		String title = text.substring(x + 1, y);

		return title.trim();
	}

	private void _initFlexmark() {
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

		_renderer = HtmlRenderer.builder(
			mutableDataSet
		).build();

		_parser = Parser.builder(
			mutableDataSet
		).build();
	}

	private void _initResourceBuilders(String login, String password) {
		DocumentFolderResource.Builder documentFolderResourceBuilder =
			DocumentFolderResource.builder();

		_documentFolderResource = documentFolderResourceBuilder.authentication(
			login, password
		).build();

		DocumentResource.Builder documentResourceBuilder =
			DocumentResource.builder();

		_documentResource = documentResourceBuilder.authentication(
			login, password
		).build();

		StructuredContentResource.Builder structuredContentResourceBuilder =
			StructuredContentResource.builder();

		_structuredContentResource =
			structuredContentResourceBuilder.authentication(
				login, password
			).build();

		StructuredContentFolderResource.Builder
			structuredContentFolderResourceBuilder =
				StructuredContentFolderResource.builder();

		_structuredContentFolderResource =
			structuredContentFolderResourceBuilder.authentication(
				login, password
			).build();
	}

	private BasedSequence _toBasedSequence(String string) {
		return CharSubSequence.of(string.toCharArray(), 0, string.length());
	}

	private String _toHTML(File file, String text) {
		com.vladsch.flexmark.util.ast.Document document = _parser.parse(text);

		AbstractYamlFrontMatterVisitor abstractYamlFrontMatterVisitor =
			new AbstractYamlFrontMatterVisitor();

		abstractYamlFrontMatterVisitor.visit(document);

		/*Map<String, List<String>> data =
			abstractYamlFrontMatterVisitor.getData();*/

		_markdownFile = file;

		try {
			_nodeVisitor.visit(document);
		}
		finally {
			_markdownFile = null;
		}

		return _renderer.render(document);
	}

	private String _toString(BasedSequence basedSequence) {

		// TODO Perhaps BasedSequence#toString already does this

		char[] array = new char[basedSequence.length()];

		for (int i = 0; i < basedSequence.length(); i++) {
			char c = basedSequence.charAt(i);

			array[i] = c;
		}

		return new String(array);
	}

	private StructuredContent _toStructuredContent(String fileName)
		throws Exception {

		StructuredContent structuredContent = new StructuredContent();

		File englishFile = new File(fileName);

		String englishText = FileUtils.readFileToString(
			englishFile, StandardCharsets.UTF_8);

		ContentFieldValue englishContentFieldValue = new ContentFieldValue() {
			{
				data = _toHTML(englishFile, englishText);
			}
		};

		String englishTitle = _getTitle(englishText);

		File japaneseFile = new File(fileName.replace("/en/", "/ja/"));

		if (japaneseFile.exists()) {
			String japaneseText = FileUtils.readFileToString(
				japaneseFile, StandardCharsets.UTF_8);

			structuredContent.setContentFields(
				new ContentField[] {
					new ContentField() {
						{
							contentFieldValue = englishContentFieldValue;
							contentFieldValue_i18n = HashMapBuilder.put(
								"en-US", englishContentFieldValue
							).put(
								"ja-JP",
								new ContentFieldValue() {
									{
										data = _toHTML(
											japaneseFile, japaneseText);
									}
								}
							).build();
							name = "content";
						}
					}
				});

			structuredContent.setTitle_i18n(
				HashMapBuilder.put(
					"en-US", englishTitle
				).put(
					"ja-JP", _getTitle(japaneseText)
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
		structuredContent.setTitle(englishTitle);

		return structuredContent;
	}

	private void _visit(Image image) {
		try {
			String fileName =
				FilenameUtils.getPath(_markdownFile.getPath()) +
					_toString(image.getUrl());

			File file = new File(fileName);

			Document document = _documentResource.postDocumentFolderDocument(
				_getDocumentFolderId(
					FilenameUtils.getPath(
						fileName.substring(
							fileName.indexOf("/"), fileName.length())),
					0L),
				new Document() {
					{
						title = fileName;
					}
				},
				new HashMap<>() {
					{
						put("file", file);
					}
				});

			image.setUrl(_toBasedSequence(document.getContentUrl()));
		}
		catch (Exception exception) {
			throw new RuntimeException(exception);
		}

		_nodeVisitor.visitChildren(image);
	}

	private static final long _CONTENT_STRUCTURE_ID = 40384;

	private static final long _GROUP_ID = 20122;

	private Map<String, Long> _documentFolderIds = new HashMap<>();
	private DocumentFolderResource _documentFolderResource;
	private DocumentResource _documentResource;
	private Set<String> _fileNames = new TreeSet<>();
	private File _markdownFile;

	private NodeVisitor _nodeVisitor = new NodeVisitor(
		new VisitHandler<Image>(
			Image.class,
			new Visitor<Image>() {

				@Override
				public void visit(Image image) {
					_visit(image);
				}

			}));

	private Parser _parser;
	private HtmlRenderer _renderer;
	private Map<String, Long> _structuredContentFolderIds = new HashMap<>();
	private StructuredContentFolderResource _structuredContentFolderResource;
	private StructuredContentResource _structuredContentResource;

}