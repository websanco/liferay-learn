from pathlib import Path

import os
from sphinx.builders.html import StandaloneHTMLBuilder
from sphinx.util import logging

# Variables to set product name, version, and language using directory paths
language_path = Path(os.path.dirname(__file__))

version_path = language_path.parent

product_path = version_path.parent

#
# https://www.sphinx-doc.org/en/master/usage/configuration.html
#

author = "Liferay"
copybutton_image_svg = """
<svg xmlns="http://www.w3.org/2000/svg" fill="#fff" viewBox="0 0 512 512"><path fill="none" d="M412,128h-60V68c0-37.4-30.6-68-68-68H100C62.6,0,32,30.6,32,68v248c0,37.4,30.6,68,68,68h60v60c0,37.4,30.6,68,68,68h184c37.4,0,68-30.6,68-68V196C480,158.6,449.4,128,412,128z" class="lexicon-icon-body"/><path d="M412,128h-60V68c0-37.4-30.6-68-68-68H100C62.6,0,32,30.6,32,68v248c0,37.4,30.6,68,68,68h60v60c0,37.4,30.6,68,68,68h184c37.4,0,68-30.6,68-68V196C480,158.6,449.4,128,412,128z M160,196v124H96V64h192v64h-60C190.6,128,160,158.6,160,196z M416,448H224V192h192V448z" class="lexicon-icon-outline"/></svg>
"""
copyright = "2022, Liferay"
extensions = [
    "myst_parser",
    "notfound.extension",
    "sphinx_copybutton",
    "sphinx_design",
    "sphinx_sitemap",
    "sphinxext.opengraph",
]
html_baseurl = "https://learn.liferay.com/"
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
myst_enable_extensions = ["colon_fence"]
notfound_no_urls_prefix = True
notfound_template = "404.html"
ogp_image = "https://learn.liferay.com/_static/liferay-waffle.svg"
ogp_site_name = "Liferay Learn"
ogp_site_url = "https://learn.liferay.com/"
project = "Liferay Learn"
release = "1.0"
sitemap_url_scheme = os.path.basename(product_path) + "/latest/{lang}{link}"
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

def read_redirects(redirects, redirects_file_name, app, exception):
    redirects_file = os.path.join(app.srcdir, redirects_file_name)

    file = open(redirects_file)
    lines = file.readlines()
    file.close()

    for line in lines:
        redirect_from, redirect_to = line.split("=", 1)
        redirects[redirect_from.strip()] = redirect_to.strip()
    
    return redirects

def setup(app):
    app.add_builder(WithRootSiteHTMLBuilder, True)

    app.connect("build-finished", write_redirects)

def write_redirects(app, exception):
    # inspired by https://gitlab.com/documatt/sphinx-reredirects and
    #   https://github.com/sphinx-contrib/redirects

    if not isinstance(app.builder, StandaloneHTMLBuilder):
        log.error(
            "The builder type {} is unsupported. No redirects will be written.".format(
                type(app.builder)
            )
        )
        return
    
    redirects = {}

    redirects = read_redirects(redirects, "redirects_old.properties", app, exception)
    redirects = read_redirects(redirects, "redirects_new.properties", app, exception)
    redirects = read_redirects(redirects, "redirects_keep.properties", app, exception)

    template = "<html><head><meta content=\"0; url={}\" http-equiv=\"refresh\"></head></html>"

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