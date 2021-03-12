# DXP Cloud Limitations

In addition to a collection of features designed to help address development challenges, DXP Cloud also has a number of limitations due to the technologies and underlying infrastructure used to support it. Many of these limitations may vary due to your specific level of subscription. Other limitations may change over time as changes are made to DXP Cloud's infrastructure.

## Overview

Take these general limitations into consideration when planning to use DXP Cloud:

* Limits apply on the available CPUs, memory, scaling, network configurations (domains, SSL certificates, and IP addresses), and VPN bandwidth for each service. For instance, each service is limited to a maximum 200 GB RAM, and custom domains are limited to 50 or 1500 depending on your web server's configuration.

* Concurrent operations (such as uploads), build size, concurrent builds, and backups also have limitations.

* Service downtimes will occur (especially for environments with only a single instance of Liferay and Search services) at times for planned maintenance.

* A private cluster subscription may be needed for more stringent security, compliance, or VPN requirements.

* Remote Staging is not supported on DXP Cloud.

See the further sections below for more details.

* [All Services](#all-services)
* [Liferay Service](#liferay-service)
* [Database Service](#database-service)
* [Search Service](#search-service)
* [Backup Service](#backup-service)
* [Web Server Service](#web-server-service)
* [Continuous Integration Service](#continuous-integration-service)
* [Custom Services](#custom-services)
* [Security](#security)

## All Services

These limitations apply to every service in a DXP Cloud environment:

* **Access to Old Logs**: A maximum of 10,000 lines of logs from the last 30 days are available for each service. Submit a Support request to access older logs (up to a year old).

* **Additional Instances per Service**: Your subscription plan determines the allowed [`scale` setting](../manage-and-optimize/auto-scaling.md) for your services. By default, all services will have only one additional instance (the Search service must use an odd number of additional instances). The `scale` setting will begin already configured to use the purchased number of instances for your subscription plan.

* **Downtime**: Services running with a single instance may experience restarts when the DXP Cloud infrastructure is updated for scheduled maintenance. Use high availability settings (two instances each of the Web server and Liferay services, and three instances of the Search service) for production-type environments to avoid disruptions. You can view the schedule for planned maintenance [here](https://help.liferay.com/hc/en-us/articles/360032562611-DXP-Cloud-Platform-Maintenance-and-Release-Schedule).

* **Memory per Service Instance**: Services can have up to a possible 200 GB of RAM, and this is determined by your subscription plan. The default plan has 16 GB per service.

## Liferay Service

These limitations apply to the [Liferay service](../using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.md) in each DXP Cloud environment:

* **Remote Staging**: Remote Staging is not available with DXP Cloud. Local Staging is still available and supported.

* **Autoscaling**: When enabled, autoscaling may only add new instances up to a maximum of 10.

### Dynatrace

[Dynatrace](../manage-and-optimize/application-metrics.md#advanced-application-metrics-production-only) is not included in the Standard setup for DXP Cloud environments, but it can be purchased separately to use with it. Dynatrace is included in the High Availability setup, but only for Production or UAT environments.

These limitations apply to Dynatrace:

* **Dynatrace Metrics Discrepancy**: Dynatrace metrics do not match the metrics shown in the DXP Cloud Console. This is because Dynatrace displays metrics for the JVM process, while the console metrics measure the entire container hosting the JVM.

* **Streaming Liferay Logs**: Liferay logs cannot be streamed to Dynatrace logs.

* **Session Replay**: The Dynatrace Session Replay feature is not available with DXP Cloud.

## Database Service

These limitations apply to the [Database service](../platform-services/database-service/database-service.md) in each DXP Cloud environment:

* **Database Metrics**: The metrics displayed in the DXP Cloud console reflect the data for the service container, not individual service metrics.

* **Database Size**: The maximum size for a database is normally 100 GB. This limit can be increased by requesting it from Support.

* **Downtime**: Database maintenance may cause downtime every few months. This downtime usually lasts about two minutes. This may not come with a notification in advance.

## Search Service

These limitations apply to the [Search service](../platform-services/search-service.md) in each DXP Cloud environment:

* **Configuration**: Elasticsearch must be configured through the DXP Cloud workspace, and **not** the Liferay UI. The configuration file in the project workspace is used on each deployment and overwrites the previous configuration.

* **Memory Settings**: The default (and maximum) JVM heap size for the Elasticsearch server is 4 GB. The maximum allocation is determined by your subscription plan.

* **OS Packages**: Installing additional OS packages for the Search service is not supported.

## Backup Service

These limitations apply to the [Backup service](../platform-services/backup-service/backup-service-overview.md) in each DXP Cloud environment:

* **Backup Consistency**: Consistency between data in the database and document library is not guaranteed if a backup is created while the Liferay instance is running.

* **Backup Size**: Before DXP Cloud version 4.2.0, backups used [ephemeral storage](#file-storage). The size of backups in these versions is limited to the remaining space on a shared ephemeral disk, which may vary.

* **Backup Uploads**: Only one backup may be uploaded per minute.

* **Concurrent Operations**: Concurrent backup creation, restores, or uploads or not supported. However, concurrent downloads are supported.

* **Resource Allocation**: The RAM and number of CPUs allocated to the Backup service are determined by your subscription plan. The default allocation is 2 CPUs and 1 GB of RAM for the service.

* **Upload/Download Speed**: The speed of backup uploads or downloads is limited by your internet connection speed and the size of the backup. It may take up to several hours to download a backup with a very slow connection.

## Web Server Service

These limitations apply to the [Web server service](../platform-service/web-server-service.md) in each DXP Cloud environment:

* **Plugins**: Installing additional plugins for the web server is not supported.

* **Resource Allocation**: The web server has 2 vCPUs and 512 MB of memory. This may result in slower response times for large uploads or downloads. This may change depending on your subscription plan.

## Continuous Integration Service

These limitations apply to the [CI service](../platform-services/continuous-integration.md) in each DXP Cloud environment:

* **Administrative access**: Admin-level access is not allowed on the Jenkins server. Instead, use the [Jenkins pipeline hooks](../platform-services/continuous-integration.md#extending-the-default-jenkinsfile) to extend the CI pipeline. Existing DevOps processes may need to be adjusted to conform to this pipeline.

* **Concurrent API Calls**: Projects cannot perform concurrent calls to DXP Cloud API. This includes tasks such as deploying a build to an environment through the [CLI tool](./command-line-tool.md).

* **Resource Allocation**: The RAM and number of CPUs allocated to the CI service are determined by your subscription plan. The default allocation is 4 CPUs and 8 GB of RAM for the service.

* **Server capacity**: Your subscription plan determines the size of the data volume for the CI server.  The default size is 10 GB.

### Builds

These limitations apply to any builds created within a project:

* **Build Size**: Individual builds are limited to 2 GB each. You must ensure that the total size of the project in your repository is less than this limit.

* **Concurrent Builds**: A maximum of two concurrent builds may run on Jenkins because two executor threads are used.

* **Maximum Builds per Day**: Builds are limited to 300 per day. You can request this limit to be increased if needed.

* **Private GitHub Servers**: Integration with private GitHub servers is not supported.

## Custom Services

These limitations apply to any [custom services](../platform-services/using-a-custom-service.md) in a DXP Cloud environment:

* **Host OS Access**: Privileged access to the host Operating System kernel is not allowed, unless you purchase a subscription with a private cluster.

## Security

These limitations apply to the security features available within DXP Cloud:

* **Antivirus**: The default Liferay feature for scanning viruses on file upload cannot be used. DXP Cloud's [Antivirus solution](./dxp-cloud-infrastructure.md#antivirus) is used instead. Uploaded content is scanned on a schedule, and thus risks may not be detected immediately when a file is uploaded.

* **Authentications per Minute**: A maximum of 8400 authentications are allowed per minute.

* **Firewall Rules**: You must purchase a subscription with a private cluster and go through a special support process to set custom firewall rules. Custom firewall rules cannot be used with a shared cluster subscription. Any custom firewall rules created for a private cluster apply to all environments in the project.

* **IP Address Filtering**: IP address filtering can only be applied on the web server service.

## File Storage

These limitations apply to file storage for multiple services:

* **Ephemeral Storage**: Ephemeral Storage is used for all files not stored in volumes. Ephemeral Storage is located on the host node's internal storage, and it is shared between all containers running on that node. If a container requests more space than the host node has available, then the container is moved to another node. The hosts disks have a capacity of 250 GB.

* **Sharing Data Between Services**: Services with the StatefulSet [Deployment Type](../build-and-deploy/understanding-deployment-types.md) cannot share share data with other services.

* **StatefulSet Storage Size**: You must make a Support ticket to add storage for services with the StatefulSet [Deployment Type](../build-and-deploy/understanding-deployment-types.md). The storage size of StatefulSet services cannot be reduced once it is increased.

## Network Configuration

These limitations apply to the network configuration of your services in a DXP Cloud environment:

* **Maximum Custom Domains**: There is a limit of 50 [custom domains](../infrastructure-and-operations/networking/custom-domains.md) if you have multiple services exposed outside of the environment (in addition to the default web server). However, the web server can use a limit of 1500 custom domains.

* **Maximum SSL Certificates**: A maximum of 14 custom SSL certificates are allowed. This may be less depending on the provider issuing the certificates.

* **Public IP Addresses**: By default, every environment has one public IP address, and services within the environment have internal IP addresses. However, you can configure a service's ports to be external, assigning a public IP address to the service.

* **Wildcard SSL Certificates**: Wildcard certificates are not supported for Liferay's auto-generated SSL certificates. However, you may configure your instance with custom Wildcard SSL certificates.

## VPN Servers

These limitations apply if you have connected a [VPN server](../infrastructure-and-operations/networking/vpn-integration-overview.md) to the services in your environment:

**Site-to-Site VPN**: Site-to-Site VPN servers can only be configured with Google Cloud VPN. It also requires a private cluster subscription.

**Bandwidth**: Each VPN tunnel is limited to a total bandwidth of 3 Gbps. This limit applies to the total of incoming and outgoing traffic.

**Extending On-Premises Networks:** Cloud Interconnect or Express Route dedicated connections from an on-premises network are not supported. This applies to connections made directly or via partner providers, and with shared or private clusters.