# Web Server Service (Nginx)

The Nginx web server functions as a gateway from the open internet to your DXP 
Cloud services. It handles all traffic from your users and acts as a 
high-performance web server. 

![Figure 1: The web server is one of several services available in DXP Cloud.](./web-server-service/images/01.png)

See the [Web server service limitations](../platform-limitations.md#web-server-service) section for more information.

## Configurations

Although DXP Cloud's services are fine-tuned to work well by default, you may 
need to configure Nginx further. To do this, you can include any CONF file 
inside the `configs/{ENV}/conf.d/` folder. When you deploy your changes, the file is 
automatically injected into your service and overwrites the default 
configuration. Here's an example folder structure of such a file inside the 
appropriate directory: 

    webserver
    ├── configs
    │   └── common
    │       └── conf.d
    │           └── nginx.conf
    └── LCP.json

Files in `/webserver/configs/{ENV}/` will be copied as overrides into /etc/nginx/ in the webserver container in DXP Cloud. Files in `/webserver/configs/{ENV}/public/` will be copied as overrides into var/www/html/.

```note::
   If you are using version 3.x.x services, then Nginx configurations instead belong in the appropriate ``lcp/webserver/config/{ENV}/`` directory. See `Understanding Service Stack Versions <../reference/understanding-service-stack-versions.md>`__ for more information on checking the version.
```

## Environment Variables

These environment variables are available for the web server service:

| Name | Default value | Description |
| --- | --- | --- |
| `LCP_HAPROXY_RESOLVER_HOLD_TIME` | `10` | Configures the [`hold` configuration](https://cbonte.github.io/haproxy-dconv/2.0/configuration.html#5.3.2-hold) for the HAProxy load balancer. This configuration is for the `valid` status.|
| `LCP_HAPROXY_RESOLVER_RETRIES` | `3` | Configures the [`resolve_retries` configuration](https://cbonte.github.io/haproxy-dconv/2.0/configuration.html#5.3.2-resolve_retries) for the HAProxy load balancer (the number of retries the session will attempt to connect to the server before giving up).|
| `LCP_HAPROXY_RESOLVER_TIMEOUT_RESOLVE` | `1` | Configures the [`timeout` configuration](https://cbonte.github.io/haproxy-dconv/2.0/configuration.html#5.3.2-timeout) for the HAProxy load balancer (the number of seconds for an event timeout). This configuration is for the `resolve` event.|
| `LCP_HAPROXY_RESOLVER_TIMEOUT_RETRY` | `1` | Configures the [`timeout` configuration](https://cbonte.github.io/haproxy-dconv/2.0/configuration.html#5.3.2-timeout) for the HAProxy load balancer (the number of seconds for an event timeout). This configuration is for the `retry` event.|
| `LCP_HAPROXY_SERVER_TEMPLATE_BACKEND_NUM` | `10` | Overrides the maximum number of instances for any service. If you plan to use [auto-scaling](../manage-and-optimize/auto-scaling.md), then set this to the highest value needed. |
| `LCP_WEBSERVER_LOG_FORMAT` |   | Customizes the format for Nginx logging. See the [official Nginx documentation](https://docs.nginx.com/nginx/admin-guide/monitoring/logging/#setting-up-the-access-log). |

The [Ingress Load Balancer](../infrastructure-and-operations/networking/load-balancer.md) is also configured via the web server service. Environment variables can be added to this service to configure the load balancer and custom domains. See [the Load Balancer environment variables reference](../infrastructure-and-operations/networking/load-balancer.md#environment-variables-reference) for more information.

All environment variables and other forms of configuration for Nginx are in the [official Nginx documentation](https://docs.nginx.com/). You can set such configurations in the `configs/{ENV}/` directory, and environment variables in the service's `LCP.json` file.

## Scripts

You can use scripts for more extensive customizations. However, use caution when 
doing so. This is the most powerful way to customize the web server service and 
can cause undesired side effects. 

Any `.sh` files found in the `configs/{ENV}/scripts/` folder are run prior to starting your 
service. For example, to include a script that removes all log files, you could 
place it in this directory structure: 

    webserver
    ├── configs
    │   └── common
    │       └── scripts
    │           └── remove-log-files.sh
    └── LCP.json

```note::
   If you are using version 3.x.x services, then webserver scripts instead belong in the appropriate ``lcp/webserver/script/{ENV}/`` directory. See `Understanding Service Stack Versions <../reference/understanding-service-stack-versions.md>`__ for more information on checking the version.
```