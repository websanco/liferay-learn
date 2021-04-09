# Mapping Web Content Templates to Fragments

By default, Web Content structure fields holding a single value (Date, Decimal, Image, Number, Text, and TextBox) can be mapped to Fragments. Multi-value fields such as Select Field values, Radio Button values, or HTML must be rendered using a Web Content Template first; then the Template can be mapped to a Fragment.

The steps below show how to map a Web Content Template to a Fragment.

1. [Create a Web Content Template](../../../content-authoring-and-management/web-content/03-web-content-templates/README.md) that uses the same Structure as the Web Content Article that contains the field you want to map. For example, the Web Content Article below uses the Structure *Web Content with Audience*.

    ![Create a Template that uses the Same Structure as the Web Content Article.](./mapping-web-content-templates-to-fragments/images/01.png)

    The example *Web Content with Audience* Structure contains a Content field (TextBox) that can be mapped by default and an Audience field (Select field) that requires a separate Web Content Template to be mapped.

    ![Some Structure fields are mappable by default.](./mapping-web-content-templates-to-fragments/images/02.png)

1. Render the Structure field in the Template by clicking its name under the *Fields* heading on the left side of the script window to add the variable.

    ![Render the Structure field in the Template by clicking its name under the Fields heading.](./mapping-web-content-templates-to-fragments/images/03.png)

1. On the Fragment-supported page (Content Page, Display Page Template, etc.) [add the Fragment](./building-content-pages.md#adding-elements-to-a-content-page) and click the [mappable element](./building-content-pages.md#mapping-content) to bring up its context menu.
1. Click the *Map* button (![Map](../../../images/icon-map.png)) and click the add button (![Add](../../../images/icon-add-app.png)) in the mapping dialog to select the Web Content Article that contains the value you want to map.

    ![Select a piece of Web Content from the Mapping Menu.](./mapping-web-content-templates-to-fragments/images/04.png)

1. Once the Web Content is selected, select the Web Content Template you created from the list of mapping options in the selector. The Template appears with a `*` next to its name to indicate that it's a Template rather than a standard field.

    ![Templates are denoted with an asterisk next to their name.](./mapping-web-content-templates-to-fragments/images/05.png)

1. Click *Publish* to save the changes. The Web Content's Template is mapped to the Fragment!

    ![The Web Content Template is mapped to the Fragment.](./mapping-web-content-templates-to-fragments/images/06.png)

```note::
  If you want to make several structure field values available (that aren't included by default, as mentioned above) for mapping to individual Fragments, you must create a separate Web Content Template for each field that uses that Structure and renders just the one field. If you render all your fields in one Template, all their values will be mapped to the Fragment when you select the Template.
```
