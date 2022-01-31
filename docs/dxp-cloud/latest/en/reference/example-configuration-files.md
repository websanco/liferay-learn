# Example Configuration Files

Different services in DXP Cloud (such as the [search](../platform-services/search-service.md) and [web server](../platform-services/web-server-service.md) services) use configuration files to perform what you might be used to handling differently in an on-premises environment. Here are some example resources you can use as a starting point to see what these files look like when you are getting started with DXP Cloud.

## Web Server Service Configuration (nginx.conf)

The web server service uses an [Nginx](link) server to manage web traffic. Here is an example `nginx.conf` file:

```{literalinclude} ./example-configuration-files/resources/nginx.conf
:lines: 1-81
```

The `nginx.conf` file belongs in the `webserver/configs/{ENV}/conf.d/` directory in your project repository.

## Search Service Configuration (elasticsearch.yml)

The search service uses an [Elasticsearch](https://www.elastic.co/guide/index.html) server to handle search queries in your Liferay instance. Here is an example `elasticsearch.yml` file:

```{literalinclude} ./example-configuration-files/resources/elasticsearch.yml
:lines: 1-27
```

The `elasticsearch.yml` file belongs in the `search/configs/{ENV}/config/` directory in your project repository.
