# Personalizing the Search Experience

One of the premier use cases for Search Experiences and Search Blueprints in particular is to personalize Liferay's [search results](../../search-pages-and-widgets/search-results.md). With a personalized search search experience, each User sees results depending on the particular details of his or her context. There are currently Elements and attributes for reacting to the answers of these contextual questions:

* Where is the User?
* What Roles does this User have?
* What Sites is the User a member of?
* What content does the User own?
* Is the User a Guest?
* Is the User a new User?
* What User Segments is the User part of?

Without a Blueprint tailored for each User's context, the default results are scored based on a query that calculates relevance by how well the text of the searched keywords matches the [tokenized](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/analysis-tokenizers.html) values of indexed documents.

Personalizing search results increases the chance that the User will find what's needed for their success. This in turn increases the chance of your site's success, by keeping Users engaged with your content. Some examples include

* Prioritizing certain results if they are near in proximity to the User's IP Address
* Prioritizing certain content if the User is new
* Hiding certain content if the User isn't authenticated

## Personalization Elements

There are several Elements that are especially useful for personalization efforts:

* Boost Proximity
* Boost Contents for the Current Language
* Hide Contents in a Category for Guest Users
* Limit Search to My Contents
* Limit Search to My Sites
* Boost Contents on My Sites
* Boost Contents in a Category for New User Accounts
* Boost Contents in a Category for a User Segment

<!-- TODO: Move these descriptions to the Elements Reference guide when written, and link to them. Since we don't currently have the Elements Reference guide written, we must describe them here. -->

Within those Elements are some important context-aware parameters that drive the personalization functionality:

* User
* Session
* IPStack
* Open Weather Map

<!-- List and describe these and other available context-aware parameters, with any special instructions and considerations -->

from the LRDOCS-10134 ticket (Petteri's geolocaton details) 

IPStack:
<!--only lat and long are use in an ootb element: boost proximity -->

ipstack.city
ipstack.continent_code
ipstack.continent_name
ipstack.country_code
ipstack.country_name
ipstack.latitude
ipstack.longitude
ipstack.region_code
ipstack.region_name
ipstack.zip

OpenWeatherMap:
<!-- none of these are used in the ootb elements -->

openweathermap.temp
openweathermap.weather_description
openweathermap.weather_id
openweathermap.weather_main
openweathermap.wind_speed

Additional Notes:
 * Using the information Petteri provided in LRDOCS-10134 for this effort may be enough to close both tickets
 * Also see [slack|https://liferay.slack.com/archives/C0154CEGR3Q/p1642617864109400]{quote}

## Building a Blueprint to Personalize Search Results

One important usage of Search Experiences' personalization features is presenting result based on the search User's location. 

To demonstrate this use case, multiple configuration exercises must be completed:

- [Configure the service that geolocates the User's IP address, <https://ipstack.com>.](#configure-the-ipstack-service)
- [Geolocate a Liferay Asset.](#configure-a-geolocated-asset)
- [Create the Blueprint with the Element that communicates with the geolocation service.](#configure-a-geolocation-aware-blueprint)


### Configure the Ipstack Service

Before configuring the Blueprint, you must obtain an [ipstack key](https://ipstack.com/) and enable the Ipstack service in Liferay.

1. Go to <https://ipstack.com> and obtain an API key.
1. In Liferay, go to System Settings &rarr; Platform &rarr; Search Experiences &rarr; Ipstack.
1. Click _Enabled_.
1. Enter the API Key.
1. Click _Save_.

![Configure the Ipstack service in System Settings.](./personalizing-the-search-experience/images/01.png)

### Configure a Geolocated Asset

A search document must have a [geopoint field](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/geo-point.html) to work with the Ipstack service and the Boost Proximity Element. Liferay includes compatible Geolocation fields that you can add as a Custom Field to existing assets:

1. Go to Control Panel &rarr; Custom Fields.
1. Add a new Custom Field on the Blogs Entry:
   - Type: Geolocation
   - Field Name: location
1. Click _Save_.
1. Add two new blogs entries (open the Site Menu and go to Content & Data &rarr; Blogs):
   - First Blogs Entry 
     - Title: _Blog title_
     - Content: _Blog content_
     - Under Custom Fields, drag the geolocation pin somewhere approximately 100 km from your current location.
     - Publish the blog.
   - Second Blogs Entry
     - Title: _Second blog title_
     - Content: _Second blog content_
     - Under Custom Fields, drag the geolocation pin as close as possible to your current location.
     - Publish the blog.

```{tip}
Zoom in to the geolocation map in order to place the pin more precisely.
```

If you search for the word _blog_ on the search page, the Blogs Entry with the shorter title and content fields (the first one, titled _Blog title_) will appear first in the Search Results widget. Let's create a Blueprint that will boost the content that's closer to you, the search User.

### Configure a Geolocation-Aware Blueprint

Now that you have the Ipstack service configured and search documents with geopoint data, you're ready to configure a Blueprint that boosts certain results by their proximity to the search User:

1. Open the Blueprints application by clicking _Blueprints_ from Global Menu &rarr; Applications (Search Experiences).

1. Add a Blueprint by clicking the Add (![Add](../../../images/icon-add.png)) button.

   ![Start creating a Blueprint from the Add Blueprint modal window.](./creating-and-managing-search-blueprints/images/02.png)

1. In the New Search Blueprint window, give the Blueprint a name (required) and a description (optional).

1. Use the [Query Builder](#using-the-query-builder) to add the Boost Proximity Element.
   - Set the field as `expando__keyword__custom_fields__location_geolocation`.
   - Set the Decay to 0.99.
   - Set the Scale to 10 km.
   - Set the Boost value to 100.

![Configure the Boost Proximity Element.](./personalizing-the-search-experience/images/02.png)

1. Test the Blueprint as you build and configure it. Click _Preview_.

1. To model the search experience of a User in your location, set your public IPV4 address into the search context by clicking the gear icon (![Cog](../../../images/icon-cog3.png)): 
   - Key: `search.experiences.ip.address`
   - Value: `[My Public IPV4 Address]`

   Click _Done_.

1. Enter the keyword _blog_ and verify that the geolocated Blogs Entry that's closer to your location is returned before the more distant Blogs Entry.

   ```{note}
   The Gaussian function used to score documents by their proximity to the sending IP address might need to be adjusted. The Boost Proximity Element lets you adjust the decay, scale, and boost:

   - Decay defines the factor by which to reduce the boost value when the proximity of the asset to the User is equal to the scale.

   - Scale is the distance away from the User's IP adress location, above which the relevance of results should begin to deteriorate.

   - Boost is the numeric value to boost results that are within the defined scale.

   For example, if you specify a boost of 100 for search results geolocated to within 10 km of the User, and define a decay factor of 0.5, a result exactly 10 km away from the User will receive half of the maximum boost value, so it will be boosted by 50. At distances greater than 10 km, the Gaussian function takes over in determining the remaining scores.

   See [Elastic's Function Score Query](https://www.elastic.co/guide/en/elasticsearch/reference/current/query-dsl-function-score-query.html) documentation for more details.
   ```

1. Once you're finished with the Blueprint, Click _Save_.

Now you can [apply the Blueprint to a Liferay search page](./using-a-search-blueprint-on-a-search-page.md).
