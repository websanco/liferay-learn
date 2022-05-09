# Using Poshi Resources

Once the Poshi resources jar file is loaded onto your Poshi project, you are now able to use Liferay's commonly used functions, paths, and macros. To distinguish between files from the Poshi resources jar file and those from the local file system, use the following name spaces.

* Use `Default` with functions from the [Liferay Portal functions github directory](https://github.com/liferay/liferay-portal/tree/master/modules/test/poshi/poshi-runner-resources/src/main/resources/default/testFunctional/functions) as in the Click function below:

    ```
    Default.Click(locator1 = "//body")
    ```
* Use `Portal` in the following cases:

  * When using paths from the [Liferay Portal paths github directory](https://github.com/liferay/liferay-portal/tree/master/portal-web/test/functional/com/liferay/portalweb/paths) as in the `TextInput#SUBJECT` path below:

    ```
    AssertElementPresent(locator1 = "Portal.TextInput#SUBJECT");
    ```
  * When using macros from the [Liferay Portal macros github directory](https://github.com/liferay/liferay-portal/tree/master/portal-web/test/functional/com/liferay/portalweb/macros) as in the `login()` command below:

    ```
    Portal.Liferay.login()
    ```

Poshi files from the local file system do not require a namespace prepended and are automatically assigned the LocalFile namespace.
