# Forms Field Types Reference

The Liferay Forms application contains lots of field types and elements to add to your forms. To discover the available elements,

1. Open the _Product Menu_ (![Product Menu](../../../images/icon-product-menu.png)) and click the compass icon (![Compass](../../../images/icon-compass.png)) on the Site Administration menu. Select the Site where the form will be created.
1. Click _Content & Data_  &rarr; _Forms_.
1. Click the _Add_ button (![Add](../../../images/icon-add.png)). The form builder view appears.
1. Click the _Add_ button (![Add](../../../images/icon-add.png)) one more time to display the _Add Elements_ sidebar (if it is not already opened).

![There are many useful form elements.](./forms-field-types-reference/images/01.png)

## Field Configuration Overview

The field configuration editor sidebar appears whenever you add a new field or click an existing one in the form builder. The available configuration options differ by field type.

All Form Fields have at least two configuration types: basic and advanced. Many forms can be [created](../creating-and-managing-forms/creating-forms.md) with just the basic configurations, which include things like its label, [placeholder text, help text](../creating-and-managing-forms/providing-help-text-and-predefined-values.md), whether the field is required, and what the required message says. There are additional field-specific configurations as well.

![The Select from List field has several basic configurations.](./forms-field-types-reference/images/15.png)

Advanced configurations include [predefined values](../creating-and-managing-forms/providing-help-text-and-predefined-values.md), search configurations, whether the field is repeatable, the system/programmatic field reference, field confirmation, and whether the field is hidden. There are additional-field-specific configurations as well.

![The Text field has several advanced configurations.](./forms-field-types-reference/images/14.png)

### Hiding Fields

Sometimes it's useful to pass information from an external source (like a [REST Data Provider](../data-providers/using-the-rest-data-provider-to-populate-form-options.md)) into a form field that's only to be used for triggering a [form rule](../form-rules/form-rules-overview.md). In this case, use the Hide Field configuration (new in Liferay 7.4).

### Field Confirmation

Field confirmation is supported in Liferay Forms as of version 7.4. Rather than a field in the Form Builder that you add directly to the form body, the confirmation field only appears in the form when you configure a single-line Text or Numeric field to _Require Confirmation_ (using the field's Advanced configuration section). This field ensures that fields with important data are entered correctly (for example, email address fields).

![Confirmation fields help ensure the user enters the correct information.](./forms-field-types-reference/images/21.png)

New configuration options appear when you enable the confirmation field:

**Direction:** Choose Vertical (the default) or Horizontal to determine whether the confirmation field appears below or alongside the original field.

**Label:** Choose the prefix for the original field label. The default value is Confirm, so a field named Email Address has a confirmation field label of _Confirm Email Address_.

**Error Message:** Depending on the field you're confirming, you can provide a more specific error message.

![Confirmation fields are configurable.](./forms-field-types-reference/images/22.png)

See [Validating Text and Numeric Field Entries](validating-text-and-numeric-field-entries.md) for information on ensuring the user enters information in a valid format.

### Field Name, Field ID, and Field Reference

In the first release of Liferay 7.3, the editable configuration Field Name was removed, and a read-only Field ID was included in each field's Advanced tab. However, an editable replacement, Field Reference, is now available in Liferay 7.3+. Use the Field Name value in 7.2, and the Field Reference in 7.3+, when referencing the field programmatically.

![The Field Reference configuration gives form builders the ability to set a human readable system name for the field.](./forms-field-types-reference/images/20.png)

## Form Fields and Elements

### Paragraph

The paragraph field displays static text on the form, as entered by the form creator. Users do not enter data into paragraph fields. This is useful for longer instructions.

![Paragraph fields can be used to provide longer blocks of formatted text.](./forms-field-types-reference/images/18.png)

Paragraph fields can be much more than plain text. Create rich content using the embedded content editor.

![Create rich content in paragraph fields using the content editor.](./forms-field-types-reference/images/19.png)

### Text Field

Users enter free-form text into these fields. For example, a Full Name field is a text field. By default, a text field keeps all input on a single line of text. To accommodate longer responses, choose the multi-line setting when configuring the text field [as in this example](./creating-forms.md). You can put limits on the text users can enter (e.g., numbers from 1-10, email addresses, etc.) by using the text field's [validation options](./validating-text-and-numeric-field-entries.md).

![Multi-line text fields allow multiple lines of user input.](./forms-field-types-reference/images/03.png)

### Select from List

Users select one option (or more, if configured to allow it) from a list of choices. Choices are entered manually or automatically populated by a [data provider](../data-providers/data-providers-overview.md). For example, a Country of Residence field can be selected from list field populated by a Countries of the World data provider.

![Select form List fields present users with a dropdown list of options to select from.](./forms-field-types-reference/images/04.png)

### Single Selection

Using a radio button, users select one option from a list of options displayed on the form.

![Single Selection fields show users multiple options, allowing them to select just one.](./forms-field-types-reference/images/05.png)

### Boolean

Users enable or disable a checkbox or switch, logging a `true` or `false` response to the displayed text.

![Boolean fields show users a single option, allowing them to select true or false in response.](./forms-field-types-reference/images/17.png)

### Date

Users select a date using a date picker.

![A date picker offers  handy way to select a date.](./forms-field-types-reference/images/06.png)

As of Liferay 7.4, the Advanced configuration of the Date Field includes Validation. You can put limits on the dates users can enter (e.g., numbers from 1-10, email addresses, etc.) by using the [validation options in the field's Advanced configuration](./validating-text-and-numeric-field-entries.md).

![Enter validation rules for date fields.](./forms-field-types-reference/images/26.png)

### Date and Time

Added in Liferay 7.4, Users fill in both the date and a time of day in one field.

![The Date and Time field combines a valid date with the time of day.](./forms-field-types-reference/images/28.png)

The Date and Time field accepts input following the format `DD/MM/YYYY HH:MM [AM/PM]`. For example, `01/06/1981 12:15 PM` is January 6, 1981 at fifteen minutes past midday. A date and time picker can be used, or the User can enter the date directly into the field.

### Multiple Selection

Users select one or more options from check boxes (or toggles, if configured).

![Multiple selection fields let users choose more than one of the displayed options.](./forms-field-types-reference/images/07.png)

### Grid

Using radio buttons, users select from options laid out in rows and columns. One selection can be made per row. This is useful when the same response metric is needed for multiple questions.

![Grid fields let users respond to multiple inquiries from the same list of responses options.](./forms-field-types-reference/images/08.png)

### Numeric

Users enter numeric data (integers or decimals) into numeric fields. Non-numeric input is not accepted.

![Numeric fields restrict user input to numbers (integers or decimals).](./forms-field-types-reference/images/09.png)

There are some interesting advanced field settings for numeric fields:

- [**Require Confirmation**](#field-confirmation) ensures the user enters data correctly.
- **[Validation](./validating-text-and-numeric-field-entries.md)** constrains the user input to your pre-configured requirements.
      ![Validate numeric input.](./forms-field-types-reference/images/25.png)
- An **[Input Mask](./validating-text-and-numeric-field-entries.md#masking-numeric-fields)** allows you to display the correct format as the user enters numeric data. 

### Upload


Upload fields let users upload a file from the local file system. Logged in Users can choose from files they have previously uploaded or upload a new one.

```{note}
In 7.2 logged in Users can select an existing file from the Documents and Media library or choose to upload a file from their local file systems.
```

![Upload fields let users upload files or select from existing Docs and Media entries.](./forms-field-types-reference/images/10.png)

Beginning with Liferay 7.3 (DXP FP1+ and CE GA7+), you can allow unauthenticated Guest Users to upload files. Enable Guest uploads within the Form Field's configuration:

![You can choose whether Guests are allowed to upload files.](./forms-field-types-reference/images/16.png)

Uploaded documents are stored in a hidden folder in the [Documents and Media repository]( ./../../../system-administration/file-storage.md). To display them in the Documents and Media application, set the following [portal property](../../../installation-and-upgrades/reference/portal-properties.md ) in a `portal-ext.properties` file:

```properties
dl.show.hidden.mount.folders=true
```

Configure the Guest upload behavior in the Control Panel: System Settings &rarr; Forms (Content and Data) &rarr; Forms, or by creating a [configuration file](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) named `com.liferay.dynamic.data.mapping.form.web.internal.configuration.DDMFormWebConfiguration.config`.

```{tip}
To configure the Forms application at the [instance scope](../../../system-administration/configuring-liferay/understanding-configuration-scope.md#scope-definitions), use the Forms entry in Instance Settings.
```

**Guest Upload File Extensions (`guestUploadFileExtensions`):** Enter the comma-separated list of accepted file extensions. By default the following list is supported: `doc`, `docx`, `jpeg`, `jpg`, `pdf`, `png`, `ppt`, `pptx`, `tiff`, `txt`, `xls`, and `xlsx`.

**Guest Upload Maximum file Size (`guestUploadMaximumFileSize`):** Set the maximum file size Guest User can upload via the Upload field. This is configured for authenticated Users in the Documents and Media application's configuration, and system-wide limits are defined in the Upload Servlet Request System Settings entry, in the field named Overall Maximum Upload Request Size. By default the limit is 25 MB.

**Maximum Repetitions for Upload Fields (`maximumRepetitionsForUploadFields`):** If the field is configured as repeatable, set maximum number of times the field can be added to the form by the User (applies equally to Guest Users and logged in Users). By default the limit is 5.

**Maximum Submissions for Guest Upload Fields (`maximumSubmissionsForGuestUploadFields`):** Set the maximum number of times a Guest User can submit a form that includes a Guest-enabled Upload field. The Guest User's IP address is used to keep track of submissions. By default the limit is 5.

### Image

Users can select an image file (`.gif`, `.jpeg`, `.jpg`, or `.png`) from Web Content Images, the Documents and Media library, or upload an image file from their local file systems.

![Image fields let users upload images or select from existing images in Docs and Media or Web Content Images.](./forms-field-types-reference/images/11.png)

```{important}
Guest Users cannot access or upload images. Avoid using this field unless only authenticated Users can access the form.
```

After adding the image, the user can fill out an additional field for the image, _Description_. When the image field is optional, the usage is straightforward: neither the image or its description is required. However, like all forms fields, the image field can be marked required. When the image field is required, the form builder can choose whether the description is required or optional:

![The description field of a required image field can be required or optional.](./forms-field-types-reference/images/27.png)

### Color

Users can enter the hex code of a color by selecting it from the color picker or entering the code directly.

![Color fields let users enter a color's hex code by selecting the color form a color picker.](./forms-field-types-reference/images/13.png)

### Rich Text

Users can enter Rich Text content (including formatted text, embedded images, attachments, etc.) into a form field.

![Rich Text fields let users write formatted text with embedded images in a form.](./forms-field-types-reference/images/12.png)

### Search Location

Use the Search Location field(s) in Liferay 7.4+ to collect valid addresses conveniently from form users.

![The Search Location field requires a Google Places API key.](./forms-field-types-reference/images/23.png)

See [Using the Search Location Field](using-the-search-location-field.md) for more information.

## Additional Information

- [Creating Forms](./creating-forms.md)
