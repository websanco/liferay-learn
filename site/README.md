# Generating the Site

The Liferay Learn documentation site is built using [Sphinx](http://www.sphinx-doc.org/en/master/). The templates and styles are in the `site/homepage/` directory and propagated to the `site/docs/` directory via the [build script](#build-resources). The final project is compiled into static html pages using the `build_site.sh` script.

## Getting Started

1. [Install node](https://nodejs.org/en/).
2. Install all dependencies with `npm install` in the `site/homepage/` directory.
3. If you would like to take advantage of live update, install autobuild with `pip install sphinx-autobuild`.

## Minify and Build Resources

To minify and build `main.min.css` and propagate assets (templates, javascript, and images), run `npm run build` in the `site/homepage/` directory.

To leverage live update for theme changes, run `sphinx-autobuild ${path/to/site/homepage/directory} build/output/homepage`. The output will be served on `http://127.0.0.1:8000/`.

## Project Structure

There is a conscious effort to organize the project into `docs/` and `site/` directories at the root to create a separation between documentation material and its website source.

### Docs

The `docs` directory contains all of the written documentation related to a product in markdown. All the images referenced throughout the documentation are also stored there, under its respective section name.

Previously, the use of `.rst` files was required to be created in order to define the a table of contents for navigating the site for every section. The [`myst-parser`](https://myst-parser.readthedocs.io/en/latest/index.html) is currently Sphinx's default markdown parser and no longer requires Table of Contents directives (`toc` directives) to be defined only in `.rst`. Site navigation can now be included in markdown natively, alongside other traditional markdown content. These table of contents files (writtten in `.rst` or `.md`) are compiled to be the site's side navigation with the rendering logic stored in `site/docs/templates/page.html`.

The product and section landing pages are achieved via [Vue](https://vuejs.org/) templates. The template logic is defined in `landingpage_template.html` and the data supplied are in respective `landing.html` files. Both the template and the data are required on the specific `rst` file that renders the landing page.

### Site

Most of the site generation related logic and assets (e.g. templates, css) are stored under the `site` directory following the Sphinx convention.

Inside the `site/` folder there are two subfolders: `docs/` and `homepage/`. The `docs` folder corresponds to the `docs` directory at the root and contains site templates and assets for generating each of the documentation pages. The `homepage` directory similiarly has the single responsibility of generating the homepage for the site.

Add entries to `redirects.json` inside `site/docs/` to create automatic redirects in case of moved documents, or to avoid broken links from other causes. Each entry defines a single redirect: the key (left side) defining where the redirect should start, and the value (right side) defining where the redirect should land (i.e. the key will redirect to the value).

## Building the Site

> **MacOS--Replace the `find` and `sed` tools:**

This script requires the `gnu` versions of `find` and `sed`. You can install these and replace the default versions:

1. Use `brew` to install the tools:

    brew install findutils

    brew install gnu-sed

2. Prepend the PATH with the path to the new tools, so they will be used in place of the native `find` and `sed`:

    export PATH="/usr/local/opt/findutils/libexec/gnubin:\$PATH"

    export PATH="/usr/local/opt/gnu-sed/libexec/gnubin:\$PATH"

Sphinx projects are typically built with a `Makefile`. This project is built with a shell script instead. Once executed it generates a `build/output` directory inside `site/`. The final static html pages are there.

The Build script has two flavors: build for production or build for development. Usage and behavior is as follows:

Build for production:

> **Warning:** Your docs folder is automatically subject to a
>
> `git clean -dfx`
>
> If you use this option. In addition, the virtual environment will be deleted and all the applications re-installed (including Sphinx). Only run this if you're building the site for publication or if you know you need these options.

```bash
./build_site.sh prod
```

Build for development:

```bash
./build_site.sh
```

This builds all products and versions for local testing. Your git index is safe from cleaning and the virtual environment won't automatically be removed for you. It's advisable to start from scratch with each new branch you check out, manually deleting the `liferay-learn/site/build` folder and the `liferay-learn/site/venv` folder.

## Formatting

This project is using [@liferay/npm-scripts](https://github.com/liferay/liferay-frontend-projects/tree/master/projects/npm-tools) to format all its `.scss` and `.js` files. It enforces Liferay specific code styles via ESLint, Prettier, and stylelint.

To automatically format the source, go to the root directory and run `npm run fix`. Alternatively, run `npm run check` to check if there are any formatting errors.

To only format source code with Prettier, run `npm run format` and `npm run format:check`.
