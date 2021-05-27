# Writing a Custom Data Provider

Liferay Forms fields can be populated using a [Data Provider](../data-providers/data-providers-overview.md). Out of the box, there's a REST Data Provider that provides a flexible way to consume data from most REST endpoints. See [Using the REST Data Provider to Populate Form Options](../data-providers/using-the-rest-data-provider-to-populate-form-options.md) to learn more.

If the out of the box data provider doesn't serve your purpose, use the `DDMDataProvider` extension point to create your own.

```note::
   The example Data Provider demonstrated here consumes data from the `GeoDataSource™ Location Search Web Service <https://www.geodatasource.com/web-service/location-search>`__. The API key of a Liferay employee is hard-coded into this sample; please do not overuse the sample. Never use it in production environments.
```

## Deploy a Custom Data Provider

1. Start Liferay.

   ```bash
   docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
   ```

1. Download and unzip the Acme XML Data Provider.

   ```bash
   curl https://learn.liferay.com/dxp/7.x/en/process-automation/forms/developer-guide/liferay-b4d8.zip -O
   ```

   ```bash
   unzip liferay-b4d8.zip
   ```

1. From the module root, build and deploy.

   ```bash
   ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   ```tip::
      This command is the same as copying the deployed jars to /opt/liferay/osgi/modules on the Docker container.
   ```

1. Confirm the deployment of each module in the Liferay Docker container console.

   ```bash
   STARTED com.acme.n4g6.impl_1.0.0
   ```

## Test the Data Provider

To use the data provider in a form,

1. Add an instance of the Data Provider:

   1. In the Site Menu, go to Content and Data &rarr; Forms. 

   1. Open the Data Providers tab and click the Add button.

      ![The custom data provider is ready for use in Liferay Forms.](./writing-a-custom-data-provider/images/01.png)

   1. Configure it:
      - **Name:** Cites Near Diamond Bar, CA (USA)
      - **Description:** GeoDataSource Location Search--Fetch the cities within 20 km of Liferay headquarters.
      - **Outputs** 
         - **Label:** City
         - **Path:** city
         - **Type:** List

      ![Configure the custom data provider by specifying its output.](./writing-a-custom-data-provider/images/02.png)

   1. Click *Save*.

1. Add a form that uses the Cities Near Diamond Bar data provider:

   1. In the Site Menu, go to Content and Data &rarr; Forms. 

   1. In the Forms tab, click the Add button.

   1. Add a Select from List fields with these settings:

      1. **Label:** Choose a City Near Liferay

      1. **Create List:** From Data Provider

      1. **Choose a Data Provider:** Cities Near Diamond Bar, CA (USA)

      1. **Choose an Output Parameter:** City

   1. Publish the form and verify that the list is populated from the data provider:

   ![The Data Provider returns a list of cities within 20 km of Liferay.](./writing-a-custom-data-provider/images/02.png)
This is a nice example, but it hard codes the URL for the data provider. If you allow the URL to be configurable, you can use this same data provider for other cities, or any other URL that serves XML. 

## Add Data Provider Settings

To add Data Provider Settings,

```java
@DDMFormField(
	label = "%url", required = true,
	validationErrorMessage = "%please-enter-a-valid-url",
	validationExpression = "isURL(url)"
)
public String url();
```




## Deploy and Test the Data Provider
