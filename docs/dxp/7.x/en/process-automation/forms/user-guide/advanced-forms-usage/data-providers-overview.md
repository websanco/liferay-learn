# Data Providers Overview

Data Providers are [REST web services](https://en.wikipedia.org/wiki/Representational_state_transfer) which can be used to import data. You can use data providers to auto-populate fields instead of having to enter each data point manually, especially with large data sets. One common example (but not limited to) is using a data provider to import a list of the countries of their world and their administrative regions. This tutorial demonstrates how to use a data provider with DXP _Forms_.

## Registered JSON Web Services

Some data sources are from third party sources, such as the [restcountries.eu](https://restcountries.eu) data provider. Liferay DXP also has its own registered web services to serve as your data provider's service: see [http://localhost:8080/api/jsonws](http://localhost:8080/api/jsonws) for the list (assuming you're running a local server). If populating a list of countries, you'll find two `get-countries` JSON web services: either one will work. Click _Invoke_ to generate the results.

The _Result_ tab shows a list of countries using JSON syntax, like this record for Afghanistan:

    [
      {
        "a2": "AF",
        "a3": "AFG",
        "countryId": "20",
        "idd": "093",
        "mvccVersion": "0",
        "name": "afghanistan",
        "nameCurrentValue": "Afghanistan",
        "number": "4"
      },
        ...

Take note of the web service's field you want Users to select. For `get-countries` it's most likely `nameCurrentValue`, because it contains the full, properly capitalized name of the country.

On the _URL Example_ tab, find the URL to use when creating the data provider. It's the same as the one generated for accessing the `get-countries` JSON web service. Users can find the URL for any registered JSON web service using this same procedure.

![The URL Example tab displays the corresponding the JSON web service.](./data-providers-overview/images/02.png)

## Data Provider Configuration Reference

Configure data providers from the Forms application at _Site Administration_ &rarr; _Content & Data_ &rarr; _Forms_. Click the _Data Provider_ tab then the (![Add icon](../../../../images/icon-add.png)) icon to begin. There are several fields to fill out when configuring a data provider.

![Here are the different fields and their explanations.](./data-providers-overview/images/03.png)

### URL

Enter the URL of an internal or external REST service endpoint. Consider the REST service at <https://restcountries.eu/> which contains a REST API endpoint to find countries by `region`:

`https://restcountries.eu/rest/v2/region/{region}`

Data Provider URLs can take two parameter types: path parameters and query parameters.

Path parameters are part of the URL calling the REST web service and are added using the pattern `https://service-url.com/service/{path_parameter_name}`:

For example, the `restcountries.eu` service's `region` endpoint's path parameter is `{region}`. Path parameters are mandatory parts of the URL, so make sure you specify an Input (see below) with a _Parameter_ field value matching the path parameter from the URL.

Query parameters are complementary parts of the URL that filter the output of the service call, following the pattern
`?query_parameter=query_parameter_value`:

    https://restcountries.eu/rest/v2/all?fields=capital

Unlike path parameters, query parameters are optional.

### User Name and Password

Enter the credentials used to authenticate to the REST Web Service, if necessary.

### Cache data on the first request

If the data is cached, a second load of the select list field is much faster, since a second call to the REST service provider is unnecessary.

### Timeout

Enter the time (in ms) to allow the REST service call to process before aborting the request, if a response is not returned.

### Inputs

Configure path or query parameters from the REST service to filter the REST service's response. Specify the Label, Parameter, and Type (Text or Number), and choose whether the input is required to use the Data Provider.

### Outputs

The Outputs are the Parameters to display in Select from List or Text fields with autocomplete enabled. You can add multiple Outputs. Outputs can be filtered by inputs (see above) but can also be displayed without configuring input filtering. Specify the Label, Path, and Type (Text, Number, or List).

You can add multiple Inputs. To provide a way for users to specify the input value, use an
[_Autofill_ Form Rule](../form-rules/using-the-autofill-rule.md). A User enters input into one field, and their input is sent to the REST service. The REST service's response data is filtered by the input parameter.

The Output Path field is specified in [JsonPath syntax](https://github.com/json-path/JsonPath/blob/master/README.md), so it must always start with a `$`. The type of data returned by the Path must match the type you choose in the Type field. Using the `restcountries.eu` service, specify the `name` field as an Output by entering enter `$..name` in the Path field. If you have a more complex JsonPath expression to construct (for example, you need the names of all countries with a population over 100 million---`$..[?(@.population>100000000)].name` with the `restcountries.eu` service), consider using an online JsonPath evaluator, like [this one](http://jsonpath.herokuapp.com/) or [this one](https://jsonpath.com/).

```tip::
   To display one value to the user, but persist another in the database, enter both into the Paths field, separated by a semicolon: ``$..name;$..numericCode``
```

If using the `restcountries.eu` data provider, the name of the country is displayed to the User, while the numeric country code is stored in the database.

![Set up Data Providers to display data retrieved from a REST service.](./data-providers-overview/images/01.png)

## What's Next

* [Using Data Providers to Populate Form Options](./using-data-providers-to-populate-form-options.md)
* [Using the Autofill Rule](./form-rules/using-the-autofill-rule.md)
