# Liferay Talend Components Overview

{bdg-warning}`Unsupported`

If you use Talend Open Studio for data integration jobs, Liferay's Talend Components make it easier to import/export data between Liferay/DXP Portal and other external systems (eg. MySQL, SAP, Salesforce). As of 7.4.x, these components are part of the [Liferay DXP/Portal codebase](https://github.com/liferay/liferay-portal/tree/master/modules/etl/talend/talend-definition/src/main/java/com/liferay/talend) and must be built and added into Talend Open Studio. Once added, you can use these components to design Talend [Jobs](https://help.talend.com/r/lqV2ftgSbYSEBU9Bwsd61w/eAVXNKXfys1ji9dujoRfrg) that connect to Liferay and perform ETL operations along with other Talend components.

Liferay's Talend Components can connect to both out-of-the-box Headless APIs and any custom APIs developed using REST Builder. All components require a defined Headless API endpoint. Each endpoint path must begin with `/headless-` (e.g.,`/headless-delivery-api`) and include a proper version sub-path (e.g.,`/headless-commerce-admin-catalog/v1.0`, `/headless-commerce-delivery-catalog/v1.0`).

## Liferay Talend Components

**Liferay Connection Component** ([`tLiferayConnection`](https://github.com/liferay/liferay-portal/blob/master/modules/etl/talend/talend-definition/src/main/java/com/liferay/talend/tliferayconnection/TLiferayConnectionDefinition.java)): Establishes a connection with a Liferay DXP/Portal instance that's used by the other components. Its Liferay Host URL value should be set to the root domain (e.g., `http://localhost:8080`, `https://yourserver.com`).

**Liferay Input Component** ([`tLiferayInput`](https://github.com/liferay/liferay-portal/blob/master/modules/etl/talend/talend-definition/src/main/java/com/liferay/talend/tliferayinput/TLiferayInputDefinition.java)): Submits GET requests to the configured Headless API endpoint. It then retrieves data, converts it to the schema described by the OpenAPI specification, and passes it forward in the defined Talend data flow.

**Liferay Output Component** ([`tLiferayOutput`](https://github.com/liferay/liferay-portal/blob/master/modules/etl/talend/talend-definition/src/main/java/com/liferay/talend/tliferayoutput/TLiferayOutputDefinition.java)): Receives data from a defined Talend input, converts it to the schema described by the OpenAPI specification, and submits POST/PUT requests to the configured Headless API endpoint.

**Liferay Batch File** ([`tLiferayBatchFile`](https://github.com/liferay/liferay-portal/blob/master/modules/etl/talend/talend-definition/src/main/java/com/liferay/talend/tliferaybatchfile/TLiferayBatchFileDefinition.java)): Receives data and formats it according to the schema described by the OpenAPI specification. It then aggregates this data into a JSON file.

**Liferay Batch Output File** ([`tLiferayBatchOutput`](https://github.com/liferay/liferay-portal/blob/master/modules/etl/talend/talend-definition/src/main/java/com/liferay/talend/tliferaybatchoutput/TLiferayBatchOutputDefinition.java)): Submits a JSON file to the defined headless-batch-engine endpoint. While it can receive this file from the `tLiferayBatchFile` component, it can also receive and submit batch files from different components.

With these components, you can create and run complex data flow management processes in Talend Open Studio. See [Installing Liferay Talend Components](./installing-liferay-talend-components.md) for detailed installation instructions.

## Additional Information

* [Installing Liferay Talend Components](./installing-liferay-talend-components.md)
* [Designing Talend Jobs Using Liferay Talend Components](./designing-talend-jobs-using-liferay-talend-components.md)
