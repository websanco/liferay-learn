# Creating Structures

Structures are a set of web elements organized in a certain way. In Liferay DXP, these elements are configured in the *Structures* page. 

To create a Structure:

1. Go to *Site Administrator* &rarr; *Content & Data* &rarr; *Web Content*.
2. Select the *Structures* tab.
3. Click *Add* (![Add Structure](../../../../../../images/icon-add.png)) to create a new structure.
4. Enter the title of your new structure and, optionally, choose the structure language.
5. Add and arrange the available fields under the structure.

    Each field shows the following three options:
    * **Settings:** (![Settings](../../../../../../images/icon-wrench.png)) Changes the
name and label and set other information about the field, like whether or not
it is required.
    * **Delete:** (![Delete](../../../../../../images/icon-trash.png)) Removes the field 
from the structure.
    * **Duplicate:** (![Duplicate](../../../../../../images/icon-wysiwyg-add.png)) Duplicates the 
field and all its settings and iterates the *Name* to avoid conflicts.

6. Optionally, add a *Description* for your structure.
<!-- 1. Describe the optional Parent Structure option -->
7. Click *Save*.

```note::
   When you crete or edit a structure, the *View* mode is selected by default. You can also manually customize a structure's XML by clicking in the *Source* tab.
```

You can copy an existing structure to create a new one. To copy a Structure:

1. Go to *Site Administrator* &rarr; *Content & Data* &rarr; *Web Content*.
2. Click the dotted menu (![Action Menu](../../../../../../images/icon-actions.png)) for the structure you want to copy.
3. Choose *Copy*.
4. Type a new *Name* for the structure and, optionally, a new *Description*.
5. If you want to copy the templates associated with the structure, check the *Copy Templates* box. 
5. Click *Copy*.

```note::
   Liferay DXP generates a unique ID for the copied structure. The new copy inherits all the attributes from the original structure, including the name. To avoid confusing the copy with the original, use a different name for the copy.
```

## Structure fields

**Boolean:** Adds a checkbox onto your structure, which stores either `true` (checked) or `false` (unchecked). You can use this field to set a display rule for the structure's content.

**Color:** Adds a color chooser.

**Date:** Adds a pre-formatted text field that displays a date picker. The date format depends on the current locale.

**Decimal:** Adds a text box to include decimal numbers.

 **Documents and Media:** Adds a way to include a document into the structure. You can include the document from your computer or from the Documents and Media library.

**Geolocation:** Adds a map that displays a configured location. Geolocation provides both the current location and directions to a another place.

**HTML:** An area that uses a WYSIWYG editor to enhance the content.

**Image:** Adds the browse image application into your structure. Using this field, you can select an image from the Documents and Media library or from your computer.

```note::
   If you upload an image from your computer, the image is only available for that particular article.
```

**Integer:** Adds a text box to include whole numbers.

**Link to Page:** Inserts a link to another page in the same site.

**Number:** Adds a text box to include any type of number number.

**Radio:** Presents the user with a list of options to choose from using radio buttons. This field allows one selection only.

**Select:** Presents a selection of options for the user to choose from using a combo box. This field allows multiple selections.

**Separator:** Adds a horizontal line between fields.

**Text:** Used for short texts descriptions, such as titles or headings.

**Text Box:** Used for long texts descriptions, such as the body of your content.

**Web Content:** Provides a way to select a web content element.