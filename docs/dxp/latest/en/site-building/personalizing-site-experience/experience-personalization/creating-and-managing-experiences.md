# Creating and Managing Experiences

> Some Experiences functionality is available in Liferay 7.4+, 7.3 Fix Pack 1+, and 7.2 Fix Pack 11+ only.

Using *Experiences* you can customize your Content Page to different groups of users. To learn about Liferay DXP Experiences and understand how Experiences work, see [Content Page Personalization](./content-page-personalization.md).

## Creating a New Content Page Experience

1. Go to *Site Administration* &rarr; *Site Builder* &rarr; *Pages*.
2. Click the Actions (![Actions](../../../images/icon-actions.png)) menu and select *Edit*, or [create a new Content Page](../../creating-pages/building-and-managing-content-pages/building-content-pages.md).
3. At the top of the page, for the *Experience* click on *Default* to open the Experience selection dialog.

    ![Click on the current Experience to create a new one or select a different existing experience.](./content-page-personalization/images/01.png)

4. Click on *New Experience*.
5. Enter a name for the Experience and select the Segment for the audience you want to target, or [create a New Segment](../segmentation/creating-and-managing-user-segments.md) (available in Liferay DXP 7.2 Fix Pack 1+ and Liferay Portal 7.2 CE GA2+.)

    ```note::
       New Experiences are assigned to the *Anyone* Segment by default, and target all website visitors.
    ```

1. On the *New Experience* dialog, under the *Languages* section, select [additional languages](./content-page-personalization.md#managing-experience-localization) you want to target with the Experience. For example, if your default Content Page language is en-US and you want to provide personalization for es-ES speakers, select this language as well.

    ![Select an existing Segment for the Experience and, opitonally, an additional language](./content-page-personalization/images/02.png)

6. Edit your Content Page with the information and layout you want to show to the selected Segment.
7. Using the Up (![Up](../../../images/icon-angle-up.png)) and Down (![Down](../../../images/icon-angle-down.png)) controls, move the experience in the list to set its preference (see [Understanding How Experiences Work](#understanding-how-experiences-work) for more information).
8. Click *Publish*.

The *Default* version of the page appears for everyone except for members of the selected Segment, who are presented with a version of the site for their defined Segment.

## Managing Content Page Experiences

When you edit a Content Page, you can click on the *Experience* selector to manage the options for that page.

![You can add, edit, delete, or change priority for Experiences.](./content-page-personalization/images/04.png)

1. Go to *Site Administration* &rarr; *Site Builder* &rarr; *Pages*.
2. Click the Actions (![Actions](../../../images/icon-actions.png)) menu and select *Edit*.
3. Click on the Experience you want to manage. From here you can:

   - Set the Experience Priority, using the Up (![Up](../../../images/icon-angle-up.png)) and Down (![Down](../../../images/icon-angle-down.png)) controls.
   - Edit (![Edit](../../../images/icon-edit.png)) the Experience's name, selected Segment or Language.
   - Duplicate (![Duplicate](../../../images/icon-copy.png)) the Experience (available in Liferay DXP 7.4+, 7.3 Fix Pack 1+, and 7.2 Fix Pack 11+.).
   - Delete (![Delete](../../../images/icon-delete.png)) the Experience.

    ```important::
        The order of your Experiences in the Experience selector determines the Experience precedence. See the *Understanding How Experiences Work* section in the `Content Page Personalization topic <./content-page-personalization.md>`__ for more information.
    ```

## Related Information

- [Content Page Personalization](./content-page-personalization.md)
- [Personalizing Collections](./personalizing-collections.md)
- [Creating and Managing User Segments](../segmentation/creating-and-managing-user-segments.md)
