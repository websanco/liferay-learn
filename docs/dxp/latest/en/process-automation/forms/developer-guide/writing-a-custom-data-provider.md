# Writing a Custom Data Provider

Liferay Forms fields can be populated using a [Data Provider](../data-providers/data-providers-overview.md). Out of the box, there's a REST Data Provider that provides a flexible way to consume data from most REST endpoints. See [Using the REST Data Provider to Populate Form Options](../data-providers/using-the-rest-data-provider-to-populate-form-options.md) to learn more.

If the out of the box data provider doesn't serve your purpose, use the `DDMDataProvider` extension point to create your own.

## Deploy an Example Custom Data Provider

The example Data Provider demonstrated here consumes data from the [GeoDataSource™ Location Search Web Service](https://www.geodatasource.com/web-service/location-search). The API key of a Liferay employee is hard-coded into the sample, so please do not overuse this sample. Never use it in production environments.

## Add Data Provider Settings

## Deploy and Test the Data Provider
