# Understanding Web Content Structures

Structures are the building blocks for web content. They determine the elements you can use as you create new items for display.

Structures in Liferay DXP serve a double function:

* Improve manageability for the administrator.
* Make easy for users to add content.

To understand this double function, consider the example of an online news magazine. The different articles must contain the same type of information and format: a title, a subtitle, an author, and one or more pages of text and images that comprise the body of the article. With only basic content creation, each author may submit articles with different types of information and formats. For example, some authors may include a subtitle, while some others may not use subtitles. And different authors may chose different format options for the page elements, like fonts or colors. In this situation, the editor of this online news magazine must spend time reformatting the articles before they are published.

When you use structures, you enforce a format for your content, so writers know exactly what elements a complete article needs. In this situation, the editor of the magazine provides a form that can be formatted automatically using a [template](/docs/dxp/7.x/en/content-authoring-and-management/web-content/user-guide/03-web-content-templates/adding-templates.md) or a [display page](/docs/dxp/7.x/en/site-building/displaying-content/display-pages-for-web-content).

Using structures, the editor of the magazine does not longer need to spend time updating every article to a particular format, and writers can save time formatting their content.

In Liferay DXP, you create a structure by adding elements such as text fields, text boxes, check boxes, select boxes, or multi-selection lists. You can also add specialized application fields on the structure, such as an Image Uploader, a Document control, or a Media control. You drag-and-drop the elements into the structure, choosing the elements and order that fit your business need. Additionally, you can group elements into repeatable blocks. Display Page creators can then map these fields to [editable page fragments](/docs/dxp/7.x/en/site-building/creating-pages/building-and-managing-content-pages/content-page-elements#editable-elements) to use custom styles and formatting.