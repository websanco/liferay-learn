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
import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;
import com.liferay.learn.dxp.importer.util.ImporterUtil;
import com.vladsch.flexmark.ast.Image;
import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.util.ast.NodeVisitor;
import com.vladsch.flexmark.util.ast.VisitHandler;
import com.vladsch.flexmark.util.ast.Visitor;
import com.vladsch.flexmark.util.sequence.BasedSequence;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FilenameUtils;

/**
 * Visitor to replace image sources with uploaded Doc Library entries
 * @author Rich Sezov
 */
public class ImageVisitor {

	public void visit (Node node) {
		_visitor.visit(node);
	}

	public void visit (Node node, File markdownFile, 
		DocumentResource documentResource, long groupId) {
		_documentResource = documentResource;

		_GROUP_ID = groupId;

		_markdownFile = markdownFile;

		visit (node);

	}
	
	public void visit (Image image) {

		try {
			String markdownPath = FilenameUtils.getPath(_markdownFile.getCanonicalPath());
			String url = ImporterUtil.BStoString(image.getUrl());
			String fileName = "/" + markdownPath + url;
			File imageFile = new File(fileName);
			HashMap<String, File> contents = new HashMap();
			contents.put(url, imageFile);
			
			Document document = _documentResource.postSiteDocument(
				_GROUP_ID, new Document(), contents);
			
			String imageSrc = document.getContentUrl();
			BasedSequence newUrl = ImporterUtil.StringtoBS(imageSrc);

			image.setUrl(newUrl);
			
		} catch (Exception e) {
			System.out.println("Exception while trying to add images:" +e.getMessage());
		}

		//_images.add(file);
		_visitor.visitChildren(image);
	}
	NodeVisitor _visitor = new NodeVisitor (new VisitHandler<Image>(Image.class, new Visitor<Image>() {
		
		@Override
		public void visit (Image image) {
			ImageVisitor.this.visit(image);
		}
	}));

	private List<String> _images;
	private long _GROUP_ID;
	private DocumentResource _documentResource;
	private File _markdownFile;

}
