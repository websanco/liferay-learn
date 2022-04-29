# Creating and Managing Elements

Elements are one of the fundamental building blocks of [Search Blueprints](understanding-search-blueprints.md) and are managed within the Blueprints application. Open the Global menu's Applications section and find the Search Experiences category to open Search Blueprints. Click the _Elements_ tab to add a new Element and manage existing Elements.

![Create and Manage Elements from the Blueprints application.](./creating-and-managing-elements/images/01.png)

```{warning}
The [Element schema](#understanding-the-schema) can change. If the schema changes between Liferay versions, importing the older Element's JSON may fail.
```

## Managing Elements

To create new Elements, click the Add (![Add](../../../images/icon-add.png)) button. See [Creating Elements](#creating-elements) for more details.

To delete existing custom Elements, select the Element(s) using the checkbox selector and click the Delete (![Delete](../../../images/icon-trash.png)) button.

```{note}
System (i.e., out of the box) Elements cannot be edited or deleted. Duplicate an existing Element if you would like to create a custom Element base don an existing Element.
```

To duplicate an existing Element, click the Actions (![Actions](../../../images/icon-actions.png)) button and select _Copy_. The duplicated Element can be deleted or edited just like any other custom Element.

To export an Element, click the Actions (![Actions](../../../images/icon-actions.png)) button for the Element and select _Export_.

To import an Element, click the Actions (![Actions](../../../images/icon-actions.png)) button in the top corner of the screen (next to the Global Menu icon). Click _Import_ and browse to the Element's JSON file.

## Creating Elements

There are a lot of [system Elements that ship with Search Blueprints](search-blueprints-elements-reference.md). If none of the system Elements can meet your need, there are a variety of ways to create your own Search Blueprints Elements:

* [Use the Custom JSON Element](#using-the-custom-json-elements) to create Elements from the Search Blueprints [Query Builder](creating-and-managing-search-blueprints.md#using-the-query-builder).
* [Use the Add Element source editor](#using-the-add-element-source-editor) to write Elements using a more robust Element editor, including handy preview functionality and a listing of the predefined template variables in the system. 
* [Duplicate a system Element](#managing-elements) to get a head start on developing your own custom Element.
* Use the [Paste Any Elasticsearch Query system Element](search-blueprints-elements-reference.md#paste-any-elasticsearch-query) if you're familiar with using the [Elasticsearch query DSL](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/query-dsl.html) and need to cook up an Element quickly based on an Elasticsearch query.

### Using the Custom JSON Element

Elements that are not meant to be reused in other Blueprints can be added in the [Query Builder](creating-and-managing-search-blueprints.md#using-the-query-builder) while working on a Blueprint. These are only available in the Blueprint of origin and are not visible in the Elements section of Search Blueprints. 

Add the Custom JSON Element to the builder and begin editing the boilerplate JSON:

```json
{
   "description_i18n": {
      "en_US": "Editable JSON Text Area"
   },
   "elementDefinition": {
      "category": "custom",
      "configuration": {},
      "icon": "custom-field"
   },
   "title_i18n": {
      "en_US": "Custom JSON Element"
   }
}
```

As you type in the JSON editor, auto-completion reveals the available properties. To further understand the available JSON properties, see [Understanding the Schema](#understanding-the-schema).

While any Element can be written in the Custom JSON Element, usually this approach is best reserved for simple Elements that add a query clause and perhaps a condition. Often these will not need a `uiConfiguration` section and will not make extensive use of the predefined template variables that can be used in an Element. For more complex cases, use the [Element source editor](#using-the-add-element-source-editor).

For example, the below Exclude Journal Articles Element is based on the Custom JSON Element. It adds a condition so that the Element's query is only applied if a parameter called `exclude.journal_articles` is `true`. The query adds a `must_not` term query clause to make sure that results do not match a Web Content article's `entryClassName` field.

```json
{
   "description_i18n": {
      "en_US": "Exclude journal articles from the search."
   },
   "elementDefinition": {
      "category": "hide",
      "configuration": {
         "queryConfiguration": {
            "queryEntries": [
               {
                  "clauses": [
                     {
                        "context": "query",
                        "occur": "filter",
                        "query": {
                           "bool": {
                              "should": [
                                 {
                                    "bool": {
                                       "must_not": [
                                          {
                                             "term": {
                                                "entryClassName": {
                                                "value": "com.liferay.journal.model.JournalArticle"}
                                             }
                                          }
                                       ]
                                    }
                                 },
                                 {
                                    "bool": {
                                       "must": [
                                          {
                                             "term": {
                                                "discussion": false
                                             }
                                          }
                                       ]
                                    }
                                 }
                              ]
                           }
                        }
                     }           
                  ],
                  "condition": {
                     "contains": {
                        "parameterName": "exclude.journal_articles",
                        "value": "true"
                     }
                  }
               }
            ]
         }
      },
      "icon": "hidden"
   },
   "title_i18n": {
      "en_US": "Exclude Journal Articles"
   }
}
```

This Element depends on a custom template variable that can be added using the Parameter Configuration of a Blueprint. See the [Search Blueprints Configuration Reference](./search-blueprints-configuration-reference.md#parameter-configuration) to learn about adding custom template variables to the Blueprint.

### Using the Add Element Source Editor

A more robust editing experience is available for building your Elements. From the Elements section of Search Blueprints, click the Add (![Add](../../../images/icon-add.png)) button. 

Name the Element and click _Create_. The Element Source editor is displayed, and the Predefined Variables are shown in the pane to the left of the editor. As you type in the JSON editor, auto-completion reveals the available properties. To further understand the available JSON properties, see [Understanding the Schema](#understanding-the-schema).


![Create Elements in the Element source editor.](./creating-and-managing-elements/images/02.png)

To preview what the Element's configuration window will look like in the Query Builder, click the _Preview_ link (next to the Cancel button).

![Preview the Element Configuration window.](./creating-and-managing-elements/images/03.png)

### Using Predefined Variables

To add a Predefined Variable to the Element in the Element Source editor, place the cursor where the variable will be, then click on the variable in the left hand sidebar to have it inserted. In the Custom JSON Element you must type the variable directly into the editor. The syntax for each variable is included in the table below.

| Variable | Type: Definition | Syntax |
| :--- | :--- | :--- |
| | <a href="#context" id="context">**CONTEXT**</a> | |
| Company ID | Number: Return the company ID of the current instance| `${context.company_id}` |
| Is Staging Group | Boolean: Return whether the current site is staged | `${context.is_staging_group}` |
| Language | Text: Return the 2-letter code for the current language (e.g., `en`) | `${context.language}` |
| Language ID | Text: Return the 4-letter language code (e.g., `en_US`) of the current language | `${context.language_id}` |
| Layout Name Localized | Text: Return the localized name of the page | `${context.layout-name-localized}` |
| Page Layout ID | Number: Return the ID of the page | `${context.plid}` |
| Publication ID | Number: Return the Publication ID | `${context.publication_id}` |
| Scope Group ID | Return the current Site's ID | `${context.scope_group_id}` |
| | <a href="#time" id="time">**TIME**</a> | |
| Current Date | Date: Return the current date | `${time.current_date}` |
| Current Day of Month | Number: Return the day of the month | `${time.current_day_of_month}` |
| Current Day of Week | Number: Return the day of the week (_1 = Monday_) | `${time.current_day_of_week}` |
| Current Day of Year | Number: Return the day of the year | `${time.current_day_of_year}` |
| Current Hour | Number: Return the current hour | `${time.current_hour}` |
| Current Year | Number: Return the current year | `${time.current_year}` |
| Time of Day | Time: Return the time | `${time.time_of_day}` |
| Time Zone Name Localized | Text: Return the time zone, localized for the locale | `${time.time_zone_name_localized}` |
| | <a href="#user" id="user">**USER**</a> | |
| Active Segment Entry IDs | Number: Return the User's segment IDs | `${user.active_segment_entry_ids}` |
| Age | Number: Return the User's age in number of years | `${user.age}` |
| Birthday | Date: Return he User's birth date | `${user.birthday}` |
| Create Date | Date: Return the date when the User Account was created in Liferay | `${user.create_date}` |
| Current Site Role IDs | Number: Return the IDs of the User's Site Roles | `${user.current_site_role_ids}` |
| Email Domain | Text: Return the domain of the User's email | `${user.email_domain}` |
| First Name | Text: Return the User's first name | `${user.first_name}` |
| Full Name | Text: Return the User's full name | `${user.full_name}` |
| Group IDs | Number: Return the IDs of Sites the User is a member of | `${user.group_ids}` |
| User ID | Number: Return the User's ID | `${user.id}` |
| Is Female | Boolean: Return true if the User is female | `${user.is_female}` |
| Is Gender X | Boolean: Return true if the user is gender X | `${user.is_gender_x}` |
| Is Male | Boolean: Return true if the user is Male | `${user.is_male}` |
| Is Omniadmin | Boolean: Return true if the user is the Omni Admin User (the default User of the default Instance) | `${user.is_omniadmin}` |
| Is Signed In | Boolean: Return true if the User is signed in | `${user.is_signed_in}` |
| Job Title | Text: Return the User's job title | `${user.job_title}` |
| Language ID | Text: Return the User's language code (e.g., `en_US`) | `${user.language_id}` |
| Last Name | Text: Return the User's last name | `${user.last_name}` |
| Regular Role IDs | Number: Return the Role IDs for the User's Regular Roles | `${user.regular_role_ids}` |
| User group IDs | Number: Return the IDs of the User Groups that the User is part of | `${user.user_group_ids}` |
| | <a href="#conditional-elements" id="conditional-elements">**CONDITIONAL ELEMENTS\***</a> | |
| IP Stack City | Text: Return the city where the searching IP address originates | `${ipstack.city}`
| IP Stack Continent Code | Text: Return the continent code (e.g., NA for North America) where the searching IP address originates | `${ipstack.continent_code}`
| IP Stack Continent Name | Text: Return the continent name | `${ipstack.continent_name}`
| IP Stack Country Code | Text: Return the country code (e.g., US for United States)  | `${ipstack.country_code}`
| IP Stack Country Name | Text: Return the country name | `${ipstack.country_name}`
| IP Stack Latitude | Number: Return the latitude in decimal degrees | `${ipstack.latitude}`
| IP Stack Longitude | Number: Return the longitude in decimal degrees | `${ipstack.longitude}`
| IP Stack Region Code | Text: Return the region code (e.g., CA for California) | `${ipstack.region_code}`
| IP Stack Region Name | Text: Return the region name | `${ipstack.region_name}`
| IP Stack Zip Code | Number: Return the zip code | `${ipstack.zip}`
| Open Weather Map Temperature | Number: Return the temperature | `${openweathermap.temp}`
| Open Weather Map Weather Description | Text: Return a description of the weather (e.g., "clear sky") | `${openweathermap.weather_description}`
| Open Weather Map Weather ID | Number: Return the [ID corresponding to the weather description](https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2) | `${openweathermap.weather_id}`
| Open Weather Map Main Label| Text: Return the [primary identifier for the weather (e.g., Rain; this is the Main field in OpenWeatherMap's API response](https://openweathermap.org/weather-conditions#Weather-Condition-Codes-2), according to the weather ID | `${openweathermap.weather_main}`
| Open Weather Map Wind Speed | Number: Return the Wind Speed in km/h | `${openweathermap.wind_speed}` |
| User Custom Fields **\*\*** | Return the value of a User Custom Field | `user.custom.field.[name]` |

**\*** [Activate Liferay Commerce](https://learn.liferay.com/commerce/latest/en/installation-and-upgrades/activating-liferay-commerce-enterprise.html) to access the Commerce-specific variables.

**\*** Access Ipstack and OpenWeatherMap variables by enabling these services. Visit System or Instance Settings &rarr; Platform &rarr; Search Experiences. Click the Enabled checkbox and Save the configuration for the service you're configuring. See [Personalizing the Search Experience](./personalizing-the-search-experience.md) for an example using the Ipstack service.

**\*\*** In addition to these out-of-the-box variables, [Custom Fields](../../../system-administration/configuring-liferay/adding-custom-fields.md) on the User entity can also be referenced as variables in the Element. For example, a User Custom Field with the name _Employee_ is referenced in an Element with `user.custom.field.employee`.

## Understanding the Element Schema

The schema for Search Blueprint Elements is defined in the [sxp-query-element.schema.json](https://github.com/liferay/liferay-portal/blob/master/modules/dxp/apps/search-experiences/search-experiences-web/src/main/resources/META-INF/resources/sxp_blueprint_admin/schemas/sxp-query-element.schema.json) file. Users with access to the REST API Explorer can browse the schema more conveniently. While logged in to Liferay visit

<http://localhost:8080/o/api?endpoint=http://localhost:8080/o/search-experiences-rest/v1.0/openapi.json>

Expand the _POST /v1.0/sxp-blueprints_ endpoint entry. Scroll down and click the _Schema_ link (next to the _Example Value_ link).

![Explore the Element schema from the API Explorer.](./creating-and-managing-elements/images/04.png)

```{tip}
Inspect the syntax in the system Elements to better understand how the Elements are constructed using the schema elements.
```

Each Element has these two mandatory top-level properties: `elementDefinition` and `title_i18n`. The `elementDefinition` must include the `category` and the `configuration` &rarr; `queryConfiguration` properties:

```json
{
	"elementDefinition": {
		"category": "custom",
		"configuration": {
			"queryConfiguration": {}
		}
	},
	"title_i18n": {
		"en_US": "Custom JSON Element"
	}
}
```

In the title field, set the title text for the Element in as many languages as needed.

### Creating the Element `elementDefinition`

The `elementDefinition` is where you'll do the bulk of the work. Its properties include `category`, `configuration`, `icon`, and `uiConfiguration`. 

- `category` provides a string that classifies the behavior of the Element. Specify `match`, `boost`, `conditional`, `filter`, `hide`, or `custom`.
- `configuration` provides the `queryConfiguration`, which holds the query clauses you're contributing, via the `queryEntries` property.
- `icon` sets a string that sets which available icon to use for the Element. Any image available in the [Lexicon Icon Library](https://learn.liferay.com/dxp/latest/en/building-applications/developing-a-java-web-application/using-mvc/tag-libraries/clay-tag-library/clay-icons.html) can be used (e.g., `thumbs-up`).
- `uiConfiguration` sets the configuration elements that you'll show in the UI and then pass into your custom Element with the configured values. 

For example, an Element that boosts a term query match on the `entryClassName` field can configure the query and the UI like this:

```json
"configuration": {
   "queryConfiguration": {
      "queryEntries": [
         {
            "clauses": [
               {
                  "query": {
                     "term": {
                        "entryClassName": {
                           "boost": "${configuration.boost}",
                           "value": "${configuration.entry_class_name}"
                        }
                     }
                  }
               }
            ]
         }
      ]
   }
},
"uiConfiguration": {
   "fieldSets": [
      {
         "fields": [
            {
               "defaultValue": 1,
               "label": "Boost",
               "name": "boost",
               "type": "number",
               "typeOptions": {
                  "min": 0
               }
            }
         ]
      }
   }
```

### Defining the Element `configuration`

The `configuration` property holds the nested properties `queryConfiguration` &rarr; `queryEntries`.

Diving into the `queryEntries` JSON, it can contain the properties `clauses`, `conditions`, `enabled`, `postFilterClauses`, and `rescores`. 

- The snippet above shows how `clauses` (an array of elements) is used to add a query clause the Element contributes to the Blueprint-driven search. In addition to `query`, you can add `additive`, `boost`, `content`, `disabled`, `field`, `name`, `occur`, `parent`, `type`, or `value` properties.
- A `condition` provides a boolean check. If true the provided clauses are included in the search query, if false they are left out.
- Set `enabled` to false (it's true by default) to disable the Element.
- Add `postFilterClauses` (as an array of `clause` elements). You can add `additive`, `boost`, `content`, `disabled`, `field`, `name`, `occur`, `parent`, `query`, `type`, or `value` properties.
- Add `rescores` to recalculate the relevance score for results of a query. Specify the `query`, `queryWeight`, `rescoreQueryWeight`, `scoreMode`, and `windowSize`. See the [Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/filter-search-results.html#rescore) for details.

The `uiConfiguration` property holds the nested properties `fieldSet` and `field`.

There are a number of configuration properties you can add for each field in the UI Configuration:

- `fieldMappings` <!--not sure what this does -->
- Enter `helpText` to display help text for the field in the Blueprints UI.
- Enter a human readable `label`.
- Enter a `name` that can be used to reference the field elsewhere in the Element, like when passing the field value into the query clause: `${configuration.name}`.
- `step`
- Set the `type` of the field. Choose from `date`, `fieldMapping`, `fieldMappingList` (a list of the available fields, with their locale and a field boost), `itemSelector`, `json`, `keywords`, `multiselect`, `number`, `select`, `slider`, and `text`
- Use the `typeOptions` property to configure the options available for each type of field. For example, set the `options` displayed for a select field.
   - `boost` sets a per-field numeric boost value.
   - `format` determines the [acceptable date format](https://www.elastic.co/guide/en/elasticsearch/reference/7.x/mapping-date-format.html) (e.g., `yyyyMMddHHmmss`).
   - `nullable` sets whether a null value can be passed.
   - `options` sets the options of a select field.
   - `required` sets whether the configuration must have a value.
   - `step` sets the numeric increment or decrement value for a number or slider field.
   - `unit` sets the unit of measurement for a number field.
   - `unitSuffix` sets the unit notation to display for a number field with a unit (for example, if `unit` is `km`, you could set the `unitSuffix` as `km` or `kilometers`).

## Additional Information

- [Creating and Managing Search Blueprints](./creating-and-managing-search-blueprints.md)
- [Search Blueprints Elements Reference](./search-blueprints-elements-reference.md)
- [Search Blueprints Configuration Reference](./search-blueprints-configuration-reference.md) 
