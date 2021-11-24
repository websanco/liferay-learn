# Headless Framework Integration

When you publish Objects, you also publish headless REST APIs with them. Liferay automatically generates both REST and [GraphQL](https://graphql.org) APIs for each Object you create. The APIs generated for an Object depend on its scope (i.e., company or Site).

All Objects use the `c/[pluralobjectlabel]` naming pattern (e.g., `c/timeoffrequests`). Each Object entry schema includes all of the Object's fields and relationships.

You can view and test an Object's APIs via the Liferay API Explorer at `[server]:[port]/o/api` (e.g., `localhost:8080/o/api`). REST APIs are listed under *REST Applications*. Click on *GraphQL* to access Liferay's [Graph*i*QL](https://github.com/graphql/graphiql) browser.

## Object REST APIs

With the REST APIs, you can perform both single and batch CRUD operations for Object entries.

![Each Object appears under REST Applications.](./headless-framework-integration/images/01.png)

All endpoints and Java methods use the Object's name. In the following examples, replace `ObjectName`/`objectName` with the name of your Object.

See [Consuming REST Services](../../../headless-delivery/consuming-apis/consuming-rest-services.md) for more information.

### Company Scoped Objects

The following REST APIs are available for all company scoped Objects.

<!-- Please convert the information in the graphic below to text so that screen readers can process it. -Rich -->

![These APIs are available for all company scoped Objects.](./headless-framework-integration/images/02.png)

### Site Scoped Objects

The following REST APIs are available for all Site scoped Objects.

<!-- Please convert the information in the graphic below to text so that screen readers can process it. -Rich -->

![These APIs are available for all Site scoped Objects.](./headless-framework-integration/images/03.png)

```{note}
For the API's `scopesKey` parameter, use the proper identifier for the desired data scope (e.g., Site ID, user role).
```

## Object GraphQL APIs

With the GraphQL APIs, you can both query and mutate Object data. Use Liferay's Graph*i*QL IDE  to search Object schemas, draft queries, run requests, and more.

![Use Liferay's GraphiQL explorer to search Object schemas and run your requests.](./headless-framework-integration/images/04.png)

See [Consuming GraphQL APIs](../../../headless-delivery/consuming-apis/consuming-graphql-apis.md) for more information.

## Additional Information

* [Consuming REST Services](../../../headless-delivery/consuming-apis/consuming-rest-services.md)
* [Consuming GraphQL APIs](../../../headless-delivery/consuming-apis/consuming-graphql-apis.md)
