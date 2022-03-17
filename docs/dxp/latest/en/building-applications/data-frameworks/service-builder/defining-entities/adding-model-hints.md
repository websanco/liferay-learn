# Adding Model Hints

Once you've used Service Builder to define model entities, you may want to refine how users enter that data. Model hints specify entity data restrictions and other formatting. For example, model hints can define a calendar field with selectable dates only in the future.

You define model hints in a file called `portlet-model-hints.xml`. The `portlet-model-hints.xml` file goes in the service module's `src/main/resources/META-INF` folder.

Model hints define two things:

1. How entities are presented to users

1. The size of database columns

As Liferay renders your form fields, it customizes the form's input fields based on your configuration.

```{note}
If you chose Spring as the dependency injector, Service Builder generates a number of XML configuration files in your service module's ``src/main/resources/META-INF`` folder. Service Builder uses most of these files to manage Spring and Hibernate configurations. Don't modify the Spring or Hibernate configuration files; changes to them are overwritten when Service Builder runs. You can however, safely edit the ``portlet-model-hints.xml`` file.
```

As an example, consider the [Blogs app service module's](https://github.com/liferay/liferay-portal/blob/master/modules/apps/blogs/blogs-service/src/main/resources/META-INF/portlet-model-hints.xml) model hints file:

```xml
<?xml version="1.0"?>

<model-hints>
	<model name="com.liferay.blogs.model.BlogsEntry">
		<field name="mvccVersion" type="long" />
		<field name="ctCollectionId" type="long" />
		<field name="uuid" type="String" />
		<field name="externalReferenceCode" type="String" />
		<field name="entryId" type="long" />
		<field name="groupId" type="long" />
		<field name="companyId" type="long" />
		<field name="userId" type="long" />
		<field name="userName" type="String" />
		<field name="createDate" type="Date" />
		<field name="modifiedDate" type="Date" />
		<field name="title" type="String">
			<hint name="max-length">255</hint>
			<sanitize content-type="text/plain" modes="ALL" />
			<validator name="required" />
		</field>
		<field name="subtitle" type="String">
			<hint-collection name="TEXTAREA" />
		</field>
		<field name="urlTitle" type="String">
			<hint name="max-length">255</hint>
		</field>
		<field name="description" type="String">
			<hint-collection name="TEXTAREA" />
			<hint name="display-width">350</hint>
		</field>
		<field name="content" type="String">
			<hint-collection name="CLOB" />
			<sanitize content-type="text/html" modes="ALL" />
		</field>
		<field name="displayDate" type="Date" />
		<field name="allowPingbacks" type="boolean" />
		<field name="allowTrackbacks" type="boolean" />
		<field name="trackbacks" type="String">
			<hint-collection name="CLOB" />
		</field>
		<field name="coverImageCaption" type="String">
			<hint-collection name="TEXTAREA" />
			<sanitize content-type="text/html" modes="ALL" />
		</field>
		<field name="coverImageFileEntryId" type="long" />
		<field name="coverImageURL" type="String">
			<hint-collection name="URL" />
		</field>
		<field name="smallImage" type="boolean" />
		<field name="smallImageFileEntryId" type="long" />
		<field name="smallImageId" type="long" />
		<field name="smallImageURL" type="String">
			<hint-collection name="URL" />
			<hint name="display-width">210</hint>
		</field>
		<field name="lastPublishDate" type="Date" />
		<field name="status" type="int" />
		<field name="statusByUserId" type="long" />
		<field name="statusByUserName" type="String" />
		<field name="statusDate" type="Date" />
	</model>
</model-hints>
```

The root-level element is `model-hints`. Model entities are represented by `model` sub-elements of the `model-hints` element. Each `model` element must have a `name` attribute specifying the fully-qualified class name. Models have `field` elements representing their entity's columns. Finally, `field` elements must have a name and a type. Each `field` element's name and type maps to the name and type specified for the entity's column in the service module's `service.xml` file. Service Builder generates all these elements for you, based on the `service.xml`.

To add hints to a field, add a `hint` child element. For example, you can add a `display-width hint` to specify the pixel width to use in displaying the field. The default pixel width is `350`. To show a `String` field with 50 pixels, you could nest a `hint` element named `display-width` and give it a value of `50`.

To see the effect of a hint on a field, [run Service Builder](../service-builder-basics/generating-model-persistence-and-service-code.md#generate-the-persistence-code) again and [redeploy your module](../../../../liferay-internals/fundamentals/module-projects.md#deploy-a-simple-module). Note that changing `display-width` doesn't limit the number of characters a user can enter into the `name` field; it only controls the field's width in the AlloyUI input form.

To configure the maximum size of a model field's database column (i.e., the maximum number of characters that can be saved for the field), use the `max-length` hint. The default `max-length` value is `75` characters. If you want the `name` field to persist up to 100 characters, add a `max-length` hint  to that field:

```xml
<field name="name" type="String">
    <hint name="display-width">50</hint>
    <hint name="max-length">100</hint>
</field>
```

Remember to run Service Builder and redeploy your project after updating the `portlet-model-hints.xml` file.

## Model Hint Types

So far, you've seen a few different hints. The following table describes the portlet model hints available for use.

**Model Hint Values and Descriptions**

|      Name       | Value Type | Description | Default |
|-----------------|:--------|:---------- |:-----|
| `auto-escape`       | boolean | sets whether text values should be escaped via `HtmlUtil.escape` | true |
| `autoSize`          | boolean | displays the field in a for scrollable text area | false |
| `day-nullable`      | boolean | allows the day to be null in a date field | false |
| `default-value`     | String  | sets the default value of the form field rendered using the aui taglib | (empty String) |
| `display-height`    | integer | sets the display height of the form field rendered using the aui taglib | 15 |
| `display-width`     | integer | sets the display width of the form field rendered using the aui taglib | 350 |
| `editor`            | boolean | sets whether to provide an editor for the input | false |
| `max-length`        | integer | sets the maximum column size for SQL file generation | 75 |
| `month-nullable`    | boolean | allows the month to be null in a date field | false |
| `secret`            | boolean | sets whether to hide the characters input by the user | false |
| `show-time`         | boolean | sets whether to show the time along with the date | true |
| `upper-case`        | boolean | converts all characters to upper case | false |
| `year-nullable`     | boolean | allows a date field's year to be null | false |
| `year-range-delta`  | integer | specifies the number of years to display from today's date in a date field rendered with the aui taglib | 5 |
| `year-range-future` | boolean | sets whether to include future dates | true |
| `year-range-past`   | boolean | sets whether to include past dates | true |

```{note}
The aui taglib is fully supported and not related to AlloyUI (the JavaScript library) that's deprecated.
```

```{note}
You can use a mix of Clay and aui tags in a form. Model hints, however, affect aui tags only.
```

Note that Liferay has its own model hints file [`portal-model-hints.xml`](https://github.com/liferay/liferay-portal/blob/master/portal-impl/src/META-INF/portal-model-hints.xml). It's in `portal-impl.jar`'s `META-INF` folder. This file contains many hint examples, so you can reference it when creating `portlet-model-hints.xml` files.

## Default Hints

You can use the `default-hints` element to define a list of hints to apply to every field of a model. For example, adding the following element inside a model element applies a `display-width` of 300 pixels to each field:

```xml
<default-hints>
    <hint name="display-width">300</hint>
</default-hints>
```

## Hint Collections

You can define `hint-collection` elements inside the `model-hints` root-level element to define a list of hints to apply together. A hint collection must have a name. For example, Liferay's `portal-model-hints.xml` defines the following hint collections:

```xml
<hint-collection name="CLOB">
    <hint name="max-length">2000000</hint>
</hint-collection>
<hint-collection name="EDITOR">
    <hint name="editor">true</hint>
    <hint name="max-length">2000000</hint>
</hint-collection>
<hint-collection name="EMAIL-ADDRESS">
    <hint name="max-length">254</hint>
</hint-collection>
<hint-collection name="HOSTNAME">
    <hint name="max-length">200</hint>
</hint-collection>
<hint-collection name="SEARCHABLE-DATE">
    <hint name="month-nullable">true</hint>
    <hint name="day-nullable">true</hint>
    <hint name="year-nullable">true</hint>
    <hint name="show-time">false</hint>
</hint-collection>
<hint-collection name="TEXTAREA">
    <hint name="display-height">105</hint>
    <hint name="display-width">500</hint>
    <hint name="max-length">4000</hint>
</hint-collection>
<hint-collection name="URL">
    <hint name="max-length">4000</hint>
</hint-collection>
```

You can apply a hint collection to a model field by referencing the hint collection's name. For example, if you define a `SEARCHABLE-DATE` collection like the one above in your `model-hints` element, you can apply it to your model's date field by using a `hint-collection` element that references the  collection by its name:

```xml
<field name="date" type="Date">
    <hint-collection name="SEARCHABLE-DATE" />
</field>
```

Suppose you want to use a couple of model hints in your project. Start by providing users with an editor for filling in their comment fields. To apply the same hint to multiple entities, define it as a hint collection. Then reference the hint collection in each entity.

To define a hint collection, add a `hint-collection` element inside the `model-hints` root element in your `portlet-model-hints.xml` file. For example:

```xml
<hint-collection name="COMMENT-TEXTAREA">
    <hint name="display-height">105</hint>
    <hint name="display-width">500</hint>
    <hint name="max-length">4000</hint>
</hint-collection>
```

To reference a hint collection for a specific field, add the `hint-collection` element inside the field's `field` element:

```xml
<field name="comment" type="String">
    <hint-collection name="COMMENT-TEXTAREA" />
</field>
```

After defining hint collections and adding hint collection references, rebuild your services, redeploy your project, and check that the hints defined in your hint collection have taken effect.

Nice work! Now you can not only influence how your model's input fields are displayed, but you can also can set its database table column sizes. You can organize hints, insert individual hints directly into your fields, apply a set of default hints to all of a model's fields, or define collections of hints to apply at either of those scopes.