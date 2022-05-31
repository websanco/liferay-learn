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

import com.liferay.headless.delivery.client.resource.v1_0.DocumentResource;
import com.liferay.learn.dxp.importer.flexmark.ImageVisitor;

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
import com.vladsch.flexmark.util.ast.Document;
import com.vladsch.flexmark.util.data.MutableDataSet;

import java.io.File;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rich Sezov
 */
public class DxpConverter {

	public static DxpConverter getInstance() {
		if (_converter == null) {
			_converter = new DxpConverter();
		}

		return _converter;
	}

	public String convert(
		String markdown, File markdownFile, DocumentResource documentResource,
		long groupId) {

		parse(markdown);

		ImageVisitor imageVisitor = new ImageVisitor();

		imageVisitor.visit(_document, markdownFile, documentResource, groupId);

		String html = _renderer.render(_document);

		return html;
	}

	public void parse(String markdown) {
		_document = _parser.parse(markdown);
		AbstractYamlFrontMatterVisitor yamlVisitor =
			new AbstractYamlFrontMatterVisitor();

		yamlVisitor.visit(_document);
		Map<String, List<String>> data = yamlVisitor.getData();

		// Below we'll get YAML front matter data in later iterations.
		// For example,
		// _tags = data.get("liferay-tags").get(0);

	}

	private DxpConverter() {
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

	private static DxpConverter _converter = null;

	private Document _document;
	private final Parser _parser;
	private final HtmlRenderer _renderer;

}