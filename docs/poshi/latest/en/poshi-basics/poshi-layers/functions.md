# Functions

In Poshi, Functions handle extra WebDriver commands that an element might require to interact with a page object or element. Functions combine the basic methods defined in the BaseWebDriverImpl layer to create reusable functions for test cases, no Java experience required.

As an example, say a test includes clicking the Edit button on a page. On the surface, it seems like the only method needed is selenium.click(). To improve the reliability of the test it is advisable to include waiting for the page to load and asserting console log errors. The steps will then be as follows:

1. Wait for the page to load.

1. Wait for the button to be visible.

1. Click on the button.

1. Confirm that there are no errors on the console log.

Instead of scripting these 4 different steps, they are combined from the BaseWebDriverImpl layer to make one practical method called the _Click_ function.

```
Click.function
@default = "click"
@summary = "Click on '${locator1}'"
...
function click {
		WaitForSPARefresh();

		selenium.waitForVisible();

		selenium.mouseOver();

		selenium.click();

		selenium.assertJavaScriptErrors();

		selenium.assertLiferayErrors();
}
```

You would then simply call this `Click` function in a test with an xpath locator so the function knows what element or page object to click on. This locator is implicitly passed into the java layer as a string. Its usage is as follows:
```
Click(locator1 = "//button[contains(.,'Edit')]");
```

Functions are readily available for you in Poshi standalone. Available functions can be found in the [functions directory on Liferay Portal source](https://github.com/liferay/liferay-portal/tree/master/portal-web/test/functional/com/liferay/portalweb/functions).
