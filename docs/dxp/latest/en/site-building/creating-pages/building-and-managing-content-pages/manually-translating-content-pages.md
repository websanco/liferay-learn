# Manually Translating Content Pages

Liferay integrates translation functionality with your Content Page, so you can create engaging, localized experiences for your global users. Users with the required permissions can manually translate Site content to any available language. You can also integrate the translation process with custom workflows to streamline the review and publication process for new translations.

   ```{important}
   To trasnlate contetent pages users must have an update permeission for contet pages, or translations permissions for one or more languages.
   ``` 

## Translating Content Pages

1. Open the *Site* menu, and go to *Site Builder* &rarr; *Pages*.

1. Click the *Actions* menu ( ![Actions Button ](../../../images/icon-actions.png) ) for the content you want to translate and select *Translate*.

 ![Selecting translate redirects you to the content translation interface.](./manually-translating-content-pages/images/01.png)

   This redirects you to the content translation interface, where you can view the content's original text alongside your translation. The left column displays the language you're translating from, and the right column provides editable fields you can use for your translation.

   ```{warning}
   When translating a Content Page, you’ll be able to translate inline fragments (e.g. HTML, Header, etc.) but not Widgets or fragments that contain content mappings. Translation of non fragment content is limited to the title of the page.
   ```

1. Use the language flags in the top left corner to determine the language you're translating from as well as the language into which you want to translate.

   Users with content Update permissions can translate the original text into any language.

   Users with translate permissions can only translate the origin text into languages for which they have permission.

 ![Selecting translate redirects you to the content translation interface.](./manually-translating-content-pages/images/02.png)

1. Enter your translation into the *Title*, *Description*, and *Content* fields.

1. Click *Publish* to create a new version of the web content or initiate a workflow, if it's enabled.

   Alternatively, click *Save as Draft* to save and publish your translation at a later time.

   ```{note}
   If desired, you can implement a custom workflow with manual translation so that all published translations go through a defined review and approval process.
   ```

## Additional Information

- [Content Pages Overview](./content-pages-overview.md)
- [Adding Elements to Content Pages](./adding-elements-to-content-pages.md)
- [Content Page Editor User Interface Reference](./content-page-editor-user-interface-reference.md)

