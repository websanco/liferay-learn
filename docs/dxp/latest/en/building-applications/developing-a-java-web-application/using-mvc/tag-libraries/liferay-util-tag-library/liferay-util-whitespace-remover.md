# Using Liferay Util Whitespace Remover

The whitespace remover tag removes line breaks and tabs from code blocks included between the opening and closing of the tag. Below is an example configuration for the `<liferay-util:whitespace-remover>` tag:

with remover:

```markup
<liferay-util:whitespace-remover>
	<p>Here is some text with        tabs.</p>
</liferay-util:whitespace-remover>
```

result:

```html
Here is some text withtabs.
```
Now you know how to use the `<liferay-util:whitespace-remover>` tag to ensure that your code formatting is consistent. 

## Related Topics

* [Using the Liferay Util Param Tag](./liferay-util-param.md)
* [Using the Liferay Util Buffer Tag](./liferay-util-buffer.md)
* [Using the AUI Taglib](https://help.liferay.com/hc/en-us/articles/360028832812-Using-AUI-Taglibs)