# APIs with REST Builder

```{toctree}
:maxdepth: 3

apis-with-rest-builder/producing-and-implementing-apis-with-rest-builder.md
apis-with-rest-builder/query-design.md
apis-with-rest-builder/reference.md
```

REST Builder is a code generation tool that makes it easy for you to take your local APIs and make them available on the web. It uses the [OpenAPI Specification](https://www.openapis.org/) to generate REST and [GraphQL](https://graphql.org/) APIs.

REST Builder uses the configuration you define in `rest-config.yaml` and `rest-openapi.yaml` files to generate most of the code necessary for your API to work all at once. You configure class names and where to put the code, and REST Builder generates all of the necessary files. Then you add your implementation logic. 

You run REST Builder using the Gradle task `buildREST` from your `impl` module in a Liferay workspace. Once configured, it generates all the scaffolding code, interfaces, and even resource classes ready for implementation. 

## Next Steps

- [Producing and Implementing APIs with REST Builder](./apis-with-rest-builder/producing-and-implementing-apis-with-rest-builder.md)
- [Query Design](./apis-with-rest-builder/query-design.md)
- [Reference](./apis-with-rest-builder/reference.md)