# Search Service (Elasticsearch)

The Elasticsearch service is the text search engine for your Liferay DXP 
application. It's a private service that only communicates with the other 
services in your application, not with the outside internet. 

![Figure 1: The Elasticsearch service is one of several services available in DXP Cloud.](./search-service/images/01.png)

## Configurations

Although DXP Cloud's services are fine-tuned to work well by default, you may 
need to configure Elasticsearch further. To do so, you can include any YML file 
inside the appropriate `configs/{ENV}/config/` folder. When you deploy your changes, the file is 
automatically injected into your service and overwrites the default 
configuration. Here's an example folder structure of such a file inside the 
correct folder: 

    search
    ├── configs
    │   └── common
    │       └── config
    │           └── elasticsearch.yml
    └── LCP.json

```note::
   If you are using version 3.x.x services, then these configuration files instead belong in the appropriate ``lcp/search/config/{ENV}/`` folder. See `Understanding Service Stack Versions <../reference/understanding-service-stack-versions.md>`__ for more information on checking the version.
```

## Scripts

You can use scripts for more extensive customizations. However, use caution when 
doing so. This is the most powerful way to customize the search service and can 
cause undesired side effects. 

Any `.sh` files found in a `scripts/{ENV}/scripts/` folder are run prior to starting your 
service. For example, to include a script that removes all log files, you could 
place it in this directory structure: 

    search
    ├── configs
    │   └── common
    │       └── scripts
    │           └── elasticsearch.yml
    └── LCP.json

```note::
   If you are using version 3.x.x services, then these scripts instead belong in the appropriate ``lcp/search/script/{ENV}/`` folder. See `Understanding Service Stack Versions <../reference/understanding-service-stack-versions.md>`__ for more information on checking the version.
```

## Deploying a License to the Search Service

To deploy a license to the search service, you must create the path 
`search/configs/{ENV}/license/` and put your license file there.

```note::
   If you are using version 3.x.x services, then instead put your license file in a ``lcp/search/license/common/` folder. See `Understanding Service Stack Versions <../reference/understanding-service-stack-versions.md>`__ for more information on checking the version.
```

## Environment Variables Reference

All environment variables and other forms of configuration for Elastisearch are in the [official Elastisearch documentation](https://www.elastic.co/guide/index.html).
You can set such configurations and environment variables in the `configs/{ENV}/config/` directory and `LCP.json`, respectively. Examples include:

| Name | Value | Description |
| --- | --- | --- |
| `ES_JAVA_OPTS` | `-Xms4g -Xmx4g` | Java settings for the ES instance |


## Additional Information

* [Configuration via LCP JSON](../reference/configuration-via-lcp-json.md)
