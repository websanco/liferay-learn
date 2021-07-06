from pathlib import Path

import json
import os
from sphinx.builders.html import StandaloneHTMLBuilder
from sphinx.util import logging

import recommonmark
from recommonmark.transform import AutoStructify

# Variables to set product name, version, and language using directory paths
language_path = Path(os.path.dirname(__file__))

version_path = language_path.parent

product_path = version_path.parent

#
# https://www.sphinx-doc.org/en/master/usage/configuration.html
#

author = "Liferay"
copybutton_image_path = "img/paste.svg"
copyright = "2020, Liferay"
extensions = [
    "notfound.extension",
    "recommonmark",
    "sphinx_copybutton",
    "sphinxext.opengraph",
    "sphinx_markdown_tables",
]
html_context = {
    "available_languages": os.listdir(".."),
    "product_name": os.path.basename(product_path),
    "product_version": os.path.basename(version_path),
}
html_css_files = ["main.min.css"]
html_favicon = "_static/img/favicon.ico"
html_logo = "_static/img/liferay-waffle.svg"
html_short_title = "Documentation"
html_show_copyright = False
html_show_sphinx = False
html_static_path = ["_static"]
html_theme = "basic"
html_title = "Liferay Learn"
language = os.path.basename(language_path)
locale_dirs = ["_locale"]
log = logging.getLogger(__name__)
master_doc = "contents"
notfound_no_urls_prefix = True
notfound_template = "404.html"
ogp_site_url = "https://learn.liferay.com/"
ogp_image = "https://learn.liferay.com/_static/liferay-waffle.svg"
ogp_site_name = "Liferay Learn"
project = "Liferay Learn"
release = "1.0"
source_suffix = [".md", ".rst"]
templates_path = ["_template"]
version = "1.0"


class WithRootSiteHTMLBuilder(StandaloneHTMLBuilder):
    def get_doc_context(self, docname, body, metatags):
        doc_context = super().get_doc_context(docname, body, metatags)

        if docname != "README":
            # Set the README.md on the root level as the "fake" parent
            # ie: docs/commerce/README.md
            site_parent = self.get_relative_uri(docname, "README")

            # Set the document title as site title
            # ie: Commerce or DXP Cloud, etc
            site_title = self.render_partial(self.env.titles["README"])["title"]

            # Add the "fake" parent to the parents object, so that "DXP", "Commerce", etc.
            # is visible in the breadcrumbs
            doc_context["parents"].insert(0, {"link": site_parent, "title": site_title})

        return doc_context


def setup(app):
    app.add_builder(WithRootSiteHTMLBuilder, True)

    app.add_config_value(
        "recommonmark_config",
        {
            "enable_auto_toc_tree": False,
            "enable_math": False,
            "enable_inline_math": False,
        },
        True,
    )

    app.add_transform(AutoStructify)

    app.connect("build-finished", write_redirects)


def write_redirects(app, exception):
    # inspired by https://gitlab.com/documatt/sphinx-reredirects and
    #   https://github.com/sphinx-contrib/redirects
    redirects_file = os.path.join(app.srcdir, "redirects.json")
    redirects = {}
    template = "<html><head><meta content=\"0; url={}\" http-equiv=\"refresh\"></head></html>"

    if os.path.isfile(redirects_file):
        with open(redirects_file) as file:
            redirects = json.loads(file.read())

    if not redirects:
        log.info(
            "No redirect info found at {}. No redirects will be written.".format(redirects_file)
        )
        return

    if not isinstance(app.builder, StandaloneHTMLBuilder):
        log.error(
            "The builder type {} is unsupported. No redirects will be written.".format(
                type(app.builder)
            )
        )
        return

    product = os.path.basename(product_path)
    version = os.path.basename(version_path)

    for redirect_from, redirect_to in redirects.items():
        cur_product, cur_version, cur_language, dummy_relpath = redirect_from.split("/", 3)

        if (cur_product, cur_version, cur_language) != (product, version, language):
            continue

        target_relpath = os.path.relpath(redirect_to, os.path.dirname(redirect_from))

        log.info("Redirecting '{}' to '{}'".format(redirect_from, target_relpath))

        dummy_abspath = os.path.join(app.builder.outdir, dummy_relpath)

        dummy_parent_dir = os.path.dirname(dummy_abspath)
        if not os.path.exists(dummy_parent_dir):
            os.makedirs(dummy_parent_dir)

        with open(dummy_abspath, 'w') as f:
            f.write(template.format(target_relpath))

