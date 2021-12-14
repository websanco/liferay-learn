# Using Liferay Util Get URL

The get URL tag scrapes the URL provided by the `url` attribute. If a value is provided for the `var` attribute, the content from the screen scrape is scoped to that variable. Otherwise, the scraped content is displayed where the taglib is used.

A basic configuration for the `<liferay-util:get-url>` tag is shown below:

```jsp
<liferay-util:get-url url="https://www.liferay.com/" />
```

Here is an example that uses the `var` attribute:

```jsp
<liferay-util:get-url url="https://www.liferay.com/" var="Liferay" />

<div>
				<h2>We borrowed <a href="https://www.liferay.com/">Liferay</a>. Here it is.</h2>

				<div class="Liferay">
								<%= Liferay %>
				</div>
</div>
```

![You can use the Liferay Util Get URL tag to scrape URLs.](./liferay-util-get-url/images/01.png)

Now you know how to use the `<liferay-util:get-url>` tag to scrape URLs. 

## Related Topics

* [Using the Liferay Util Param Tag](./liferay-util-param.md)
* [Using the Liferay Util Include Tag](https://help.liferay.com/hc/en-us/articles/360029145351-Using-Liferay-Util-Include)
* [Using the AUI Taglib](https://help.liferay.com/hc/en-us/articles/360028832812-Using-AUI-Taglibs)