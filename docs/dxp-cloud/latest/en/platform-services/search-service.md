# Search Service (Elasticsearch)

The Elasticsearch service is the text search engine for your Liferay DXP application. It's a private service that only communicates with the other services in your application, not with the outside internet.

![Figure 1: The Elasticsearch service is one of several services available in DXP Cloud.](./search-service/images/01.png)

See the [Search service limitations](../reference/platform-limitations.md#search-service) section for more information.

## Configurations

Although DXP Cloud's services are fine-tuned to work well by default, you may need to configure Elasticsearch further. To do so, you can include any YML file inside the appropriate `configs/{ENV}/config/` folder. When you deploy your changes, the file is automatically injected into your service and overwrites the default configuration. Here's an example folder structure of such a file inside the correct folder:

    search
    ├── configs
    │   └── common
    │       └── config
    │           └── elasticsearch.yml
    └── LCP.json

```{important}
You must use the `elasticsearch.yml` configuration file to configure Elasticsearch. Configuring Elasticsearch through the UI will be overwritten on each deployment.
```

```{warning}
The search service's `LCP.json` file contains this value by default: `"podManagementPolicy": "Parallel"`. Removing this value from the `LCP.json` can cause the search service to fail to start when there are multiple clustered instances, because Elasticsearch nodes must connect to each other to start up successfully.
```

Your environment's [service stack version](../reference/understanding-service-stack-versions.md) affects which folder you must add your configuration file to if it is not yet upgraded to version `4.x.x`:

| **Service Stack Version** | **Configuration Folder** |
| --- | --- |
| **3.x.x** | `lcp/search/config/{ENV}/` |
| **4.x.x** | `search/configs/{ENV}/config/` |

## Scripts

You can use scripts for more extensive customizations. However, use caution when doing so. This is the most powerful way to customize the search service and can cause undesired side effects. Any `.sh` files found in a `scripts/configs/{ENV}/scripts/` folder are run prior to starting your service. For example, to include a script that removes all log files, you could place it in this directory structure:

    search
    ├── configs
    │   └── common
    │       └── scripts
    │           └── elasticsearch.yml
    └── LCP.json

Your environment's [service stack version](../reference/understanding-service-stack-versions.md) affects which folder you must add your scripts to if it is not yet upgraded to version `4.x.x`:

| **Service Stack Version** | **Scripts Folder** |
| --- | --- |
| **3.x.x** | `lcp/search/script/{ENV}/` |
| **4.x.x** | `search/configs/{ENV}/scripts/` |

## Deploying a License to the Search Service

To deploy a license to the search service, you must create the path `search/configs/{ENV}/license/` and put your license file there.

Your environment's [service stack version](../reference/understanding-service-stack-versions.md) affects which folder you must add your license files to if it is not yet upgraded to version `4.x.x`:

| **Service Stack Version** | **License Folder** |
| --- | --- |
| **3.x.x** | `lcp/search/license/{ENV}/` |
| **4.x.x** | `search/configs/{ENV}/license/` |

## Environment Variables Reference

All environment variables and other forms of configuration for Elastisearch are in the [official Elastisearch documentation](https://www.elastic.co/guide/index.html).
You can set such configurations and environment variables in the `configs/{ENV}/config/` directory and `LCP.json`, respectively. Examples include:

| Name | Value | Description |
| --- | --- | --- |
| `ES_JAVA_OPTS` | `-Xms4g -Xmx4g` | Java settings for the ES instance |

## Additional Information

* [Configuration via LCP JSON](../reference/configuration-via-lcp-json.md)
