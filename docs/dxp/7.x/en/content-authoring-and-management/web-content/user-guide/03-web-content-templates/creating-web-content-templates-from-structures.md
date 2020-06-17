# Creating Web Content Templates from Structures

Using Web Content Templates, you can configure how to present the content of a [Structure](./03-editing-and-configuring-structures-content.md). Structures define the type of web content, Templates define how this web content is represented. A Template is always associated to a Structure, and changes in the Structure require changes in the associated Template.

```note::
   You create Web Content Templates using the FreeMarker Template Language (FTL).
```

To create a Web Content Template from a Structure:

1. Go to Go to *Site Administrator* &rarr; *Content & Data* &rarr; *Web Content*.
1. Select the *Structures* tab and verify that the structure you want to use for your template exists.
1. Select the *Templates* tab.
1. Click *Add* (![Add Template](../../../../images/icon-add.png)) to create a new template.
1. Enter the title of your new template and, optionally, choose the template language.
1. On the *Properties* panel, under the *Basic Information* section, verify that FreeMaker is the selected language (this is the default option.) 
1. Click *Select* under the *Structure* field.
1. In the *Structures* list, select your structure.
   The fields configured in the structure show up now in the *Fields* group.
1. Click on each one of the *Fields* you want to add to the template.
   
   The fields are included in editor area, like in the following example:

    ```markup
    ${title.getData()}
    ${abstract.getData()}
    <#if image.getData()?? && image.getData() != ""> <img alt="${image.getAttribute("alt")}" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" /> </#if>
    ${body.getData()}
    ```

1. Edit the fields in the editor area including the HTML elements for your template.
    
   This example adds headers (`<h1>`), paragraphs (`<p>`), and image positioning information (`align="center"`) to the fields: 

    ```markup
    <h1>${title.getData()}</h1>
    <p>${abstract.getData()}</p>
    <#if image.getData()?? && image.getData() != ""> <img alt="${image.getAttribute("alt")}" data-fileentryid="${image.getAttribute("fileEntryId")}" src="${image.getData()}" align="center" /> </#if>
    <p>${body.getData()}</p>
    ```

1. Click *Save*.