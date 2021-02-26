# Importing Knowledge Base Articles

The Knowledge Base app can import articles in bulk. This lets you have a process where articles are prepared ahead of time before they’re published. Articles are imported into the Knowledge Base as [Markdown](http://commonmark.org/) files. Markdown is a text-only file format that is easy to read, yet supports all the things you’d need to do to format your articles.

Note: To import articles, your Role must be granted the *Knowledge Base* &rarr; *Resource Permissions: Import Articles* permission. 

The Knowledge Base supports a Markdown dialect known as [Multi-Markdown](http://fletcher.github.io/MultiMarkdown-4/). This dialect extends the original Markdown with features like table formatting, image captions, and footnotes.

For the Knowledge Base to import your Markdown articles, they must adhere to these requirements:

* All source files must use the `.markdown` or `.md` extensions.
* Articles must start with a top-level header (e.g., `# Some Heading ...`).
* Each header must have an associated, unique ID for the article’s friendly URL title and for anchor tags in the article’s sub headers. Here’s an example of a top-level header that correctly specifies an ID:

`# Some Heading [](id=some-heading)`

Here’s Markdown source text for a simple example article:

    # Modern Pentathlon [](id=modern-pentathlon)

    The modern pentathlon is a competition across five different sport disciplines.

    Each athlete must compete in fencing, shooting, swimming, horseback riding, and running.

In the first line above, notice the header’s ID assignment `id=modern-pentathlon`. On import, the ID value becomes the Knowledge Base article’s URL title.

Markdown is a standard with flavors: there’s [Github Flavored Markdown](https://help.github.com/articles/github-flavored-markdown), a proposed [common Markdown syntax](http://www.commonmark.org/), forums that support Markdown (reddit, StackExchange, and others), Markdown editors, and an [IETF draft](https://tools.ietf.org/html/rfc7763) for making it an official Internet media type (text/markdown). Markdown is favored because

* It’s readable. Even if you don’t know Markdown, you can read it without having to filter out the syntax.

* It gets out of a writer’s way. You don’t have to worry about mousing to various icons to change text into a heading or create bulleted lists. Just start typing. The syntax is very intuitive.

* There are tools to convert it to many other formats, though it was designed to convert to HTML. If your articles are in Markdown, you can publish them to the web, mobile formats (Kindle, ePub), and print.

* Since it’s only text, you can use existing tools to collaborate on that text. Using services like GitHub, people can contribute to your articles, and you can see all the changes that have been made to them.
