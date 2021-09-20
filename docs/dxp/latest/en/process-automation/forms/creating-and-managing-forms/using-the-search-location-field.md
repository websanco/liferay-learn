# Using the Search Location Field

## Adding and Configuring the Search Location Field

1. Enable the JavaScript Google Maps API and the Google Places API.

  - Add the Search Location field to a form. If a Google Places API key is not configured in the Site, the field displays a warning message.

      ![The Search Location field detects when an API key has not been configured.](./using-the-search-location-field/images/01.png)
  - Obtain a single API Key for using both the Google Places API and the Maps JavaScript API.
  - Click the link in the field warning message or navigate to Site Settings(Platform) &rarr; Google Places. 

      ![The API key must enable the Google Places API and the Maps JavaScript API.](./using-the-search-location-field/images/02.png)

  - Enter the Google Places API Key and click _Save_.

1. Now configure the field in the Form. Pay special attention to the field settings Field Label, Visible Fields, and Layout.

   - **Field Label** is for changing the field's label in the form. For example, instead of Search Location you might choose Location Lookup.
   - **Visible Fields** configures which subfields should be shown, and autofilled, in the form. The _Search Location_ text field is always displayed (though the label can be customized). Choose to include Address, City, State, Postal Code, and/or Country subfields.
   - **Layout** determines whether the subfields are displayed in a single column or in two columns.

<!-- Write a note on looking these subfields up programmatically? -->

## Entering Data into the Search Location Field

A User interacts with the Search Location field, and whatever fields have been configured as visible in the field settings are autofilled when a location is selected.

![Begin entering a location and the Google APIs autocomplete valid locations you can select from.](./using-the-search-location-field/images/03.png)

## Related Content

- [Site Settings]()
