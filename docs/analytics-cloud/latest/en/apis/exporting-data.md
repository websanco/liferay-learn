# Exporting Data

As mentioned before, every APIs call only returns a subset of the data, the scope is controlled by the page query parameter. Fortunately there's a way to export the entire data set to a JSON file. An initial request is made to prepare the data file and after the export is done the same endpoint can be fetched to stream the data results.

The user can request data export for the four mentioned resource types (account, individual, segment, and page).

## Requesting a Data Export

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/export/{type}
```

As mentioned, the type defines the data type. Possible values are: **account**, **individual**, **page**, and **segment**.  So, if you are interested in the segments data export, you would send the following request:

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/export/segment
```

The following response is returned:

```json
{"message":"The data export file is being created. Please come back later."}
```

The same data export file is returned for a period of 30 minutes from the date the first request was made. After that, a new data export file is generated upon new requests. Once the export job is completed you can download the file with the following command:

```
curl -H "Authorization: Bearer {token}" -L https://analytics.liferay.com/api/reports/export/segment > segment-data.json
```
