# Using Data Providers to Populate Form Options

_Select from List_ fields can hold many options. Those options can be supplied automatically any web service, including those provided by your installation. To learn more about how data providers work, see [Data Providers Overview](./data-providers-overview.md).

Here, you'll learn how to invoke a JSON web service, configure the data provider, and add the data provider to a form by populating a Select from List field with a list of countries.

## Prerequisite

[Create a form](../creating-forms.md) with one Select from List field called _Country_.

## Enabling Access to Data on the Local Network

By default, users cannot configure data providers to use URLs on the local network. This is a good default for security in a production environment, but makes testing more difficult.

To enable local network access from data providers:

1. Navigate to the _Control Panel_ &rarr; _Configuration_ &rarr; _System Settings_.
1. Click _Data Providers_ (under Content & Data).
1. Check the _Access Local Network_ checkbox.

    ![Grant access to the local network.](./using-a-data-provider-to-populate-form-options/images/01.png)

1. Click _Save_ when finished.

## Invoking the JSON Web Service for Countries

Invoke the `get-countries` JSON web service (there are two; use either one).

1. Navigate to [http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws).
1. Search for _get-countries_.

    ![Search for the get-countries web service.](./using-a-data-provider-to-populate-form-options/images/02.png)

1. Click _Invoke_.

The list of countries is now ready for use.

## Adding a Basic Data Provider

To add a _Countries of the World_ Data Provider,

1. Open the _Product Menu_ (![Product Menu](../../../../images/icon-product-menu.png)) and click the compass icon (![Compass](../../../../images/icon-compass.png)) on the _Site Administration_ menu.

1. Select the Site where the form is to be created.

1. Click _Content & Data_ &rarr; _Forms_.

1. Click the _Data Providers_ tab.

    ![Navigate to the data providers tab.](./using-a-data-provider-to-populate-form-options/images/03.png)

1. Click the Add button (![Add](../../../../images/icon-add.png)).

1. Fill out the form:

    * **Name**: Countries of the World
    * **URL**: `http://localhost:8080/api/jsonws/country/get-countries/`
    * **User Name**: `adminuser@liferay.com`
    * **Password**: `adminuserpass`
    * **Timeout**: 1000
    * **Outputs Label**: Country Name
    * **Outputs Path**: `$..nameCurrentValue`
    * **Outputs Type**: List

    ![Enter the values.](./using-a-data-provider-to-populate-form-options/images/04.png)

    Substitute your administrative user and password for the User Name and Password fields above. 

1. Click _Save_ when finished.

```note::
   The `$..` before `nameCurrentValue` is the ``JsonPath`` syntax to navigate the JSON data structure and specify the path to the output. Learn more about `JsonPath <https://github.com/json-path/JsonPath>`_ and `here <http://goessner.net/articles/JsonPath/>`_.
```

## Using Inputs as Filters

The above example uses one Output to populate a Select from List field. Often the response from the REST provider must be filtered before display in the Select from List field. For this, a Data Provider _Input_ field is required.

For example, to apply a region (for example, Americas, Europe, or Oceania) filter for the countries of the world, configure the provider like this:

* **Name**: `restcountries`
* **URL**: `https://restcountries.eu/rest/v2/region/{region}?fields=name`
* **Input Label**: Region
* **Parameter**: Region
* **Input Type**: Text
* **Outputs Label**: Countries of the World with Regions
* **Outputs Path**: `*$..name*`
* **Output Type**: List

To understand more about these values, see the [Data Providers Overview](../data-providers-overview.md).

## Granting Data Provider Permissions

You may have to grant additional permissions to the Data Provider. For example, if Guests are to fill out the form, they need the View permission so they can see the options provided by the data provider.

To configure the data provider's permissions,

1. Navigate to the _Site Administration_ &rarr; _Content & Data_ &rarr; _Forms_.

1. Click the *Data Providers* tab.

1. Click the Actions button (![Actions](../../../../images/icon-actions.png)), then _Permissions_ next to the data provider.

    ![Grant Guest View Permissions.](./using-a-data-provider-to-populate-form-options/images/05.png)

1. Grant the necessary permissions.

1. Click _Save_ when finished.

## Using a Data Provider in a Select from List Field

Once the Data Provider is configured, use it to populate a Select from List field:

1. Click _Content & Data_ &rarr; _Forms_.

1. Drag a _Select from List_ field onto the form.

1. Select _From Data Provider_ from the Create List selector.

1. Select _Countries of the World_ from the Choose a Data Provider selector.

1. Select _Country Name_ from the Choose an Output Parameter selector.

    ![Configure the Data Provider values on the Select from List field.](./using-a-data-provider-to-populate-form-options/images/06.png)

1. Click _Save Form_ when finished.

The Data Provider can now populate a select field.

![Form users select an option form the list populated by the Data Provider.](./using-a-data-provider-to-populate-form-options/images/07.png)

## Troubleshooting Data Provider Errors

To uncover errors arising from Data Provider failures, [configure the log level](../../../../system-administration/using-the-server-administration-panel/configuring-logging.md) for the `DDMDataProviderPortlet` service:

1. Navigate to the _Control Panel_ &rarr; _Configuration_ &rarr; _Server Administration_.

1. Click the _Log Levels_ tab.

1. Search for `com.liferay.dynamic.data.mapping.data.provider.web.internal.DDMDataProviderPortlet` in the Category field.

1. Select _WARN_ in the Level field.

1. Click _Save_ when finished.

The console now sends warning messages whenever there are errors in the Data Provider.

## Additional Information

* [Data Providers Overview](./data-providers-overview.md)
* [Using the Autofill Rule](./form-rules/using-the-autofill-rule.md)
