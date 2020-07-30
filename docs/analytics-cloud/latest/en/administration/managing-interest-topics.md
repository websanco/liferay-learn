# Web Server Service (Nginx)

The Nginx web server functions as a gateway from the open internet to your DXP 
Cloud services. It handles all traffic from your users and acts as a 
high-performance web server. 

![Figure 1: The web server is one of several services available in DXP Cloud.](./web-server-service/images/01.png)

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

This service has no environment variables specific to DXP Cloud. All environment 
variables and other forms of configuration for Nginx are in the 
[official Nginx documentation](https://docs.nginx.com/). 
You can set such configurations and environment variables in the `configs/{ENV}/` 
directory and `LCP.json`, respectively. 

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