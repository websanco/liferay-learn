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

package com.liferay.learn.dxp.importer.flexmark;

import com.liferay.headless.delivery.client.dto.v1_0.Document;
import com.liferay.headless.delivery.client.dto.v1_0.DocumentFolder;
import com.liferay.headless.delivery.client.pagination.Page;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentFolderResource;
import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;

import com.vladsch.flexmark.ast.Image;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.ast.Visitor;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import com.vladsch.flexmark.util.sequence.CharSubSequence;

import java.io.File;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FilenameUtils;

/**
 * Visitor to replace image sources with uploaded Doc Library entries
 * @author Rich Sezov
 */
public class ImageVisitor {

	public String _toString(BasedSequence sequence) {
		String string = "";

		for (int i = 0; i < sequence.length(); i++) {
			char c = sequence.charAt(i);
			string = string + c;
		}

		return string;
	}

	public void visit(Image image) {
		try {
			String markdownPath = FilenameUtils.getPath(
				_markdownFile.getPath());
			String url = _toString(image.getUrl());

			String fileName = markdownPath + url;

			File imageFile = new File(fileName);
			String dirName = FilenameUtils.getPath(
				fileName.substring(fileName.indexOf("/"), fileName.length()));
			HashMap<String, File> contents = new HashMap();

			contents.put("file", imageFile);
			Document imageEntry = new Document();

			imageEntry.setTitle(fileName);

			Long folderId = _getDocumentFolderId(dirName, 0L);

			Document document = _documentResource.postDocumentFolderDocument(
				folderId, imageEntry, contents);

			String imageSrc = document.getContentUrl();

			BasedSequence newUrl = _toBasedSequence(imageSrc);

			image.setUrl(newUrl);
		}
		catch (Exception e) {
			System.out.println(
				"Exception while trying to add images:" + e.getMessage());
		}

		//_images.add(file);
		_visitor.visitChildren(image);
	}

	public void visit(Node node) {
		_visitor.visit(node);
	}

	NodeVisitor _visitor = new NodeVisitor(
		new VisitHandler<Image>(
			Image.class,
			new Visitor<Image>() {

				@Override
				public void visit(Image image) {
					ImageVisitor.this.visit(image);
				}

			}));

	public void visit(
		Node node, File markdownFile, DocumentResource documentResource,
		long groupId) {

		_documentResource = documentResource;

		_GROUP_ID = groupId;

		_markdownFile = markdownFile;

		visit(node);
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

	private BasedSequence _toBasedSequence(String string) {
		BasedSequence bs = CharSubSequence.of(
			string.toCharArray(), 0, string.length());

		return bs;
	}

	private long _GROUP_ID;

	private Map<String, Long> _documentFolderIds = new HashMap<>();
	private DocumentFolderResource _documentFolderResource;
	private DocumentResource _documentResource;
	private List<String> _images;
	private File _markdownFile;

}