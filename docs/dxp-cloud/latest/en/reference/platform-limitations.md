# Platform Limitations

DXP Cloud and its services have some notable limitations. Many of these limitations may vary due to your specific level of subscription. Other limitations may change over time as changes are made to DXP Cloud's infrastructure.

## Overview

Take these general limitations into consideration when planning to use DXP Cloud:

* Limits apply on the available vCPUs, memory, scaling, network configurations (domains, SSL certificates, and IP addresses), and VPN bandwidth for each service. For instance, each service is limited to a maximum 200 GB RAM. Custom domains are also limited to 50 or 1500, depending on your [web server's configuration](#network-configuration).

* Concurrent operations (such as concurrent uploads), build size, concurrent builds, and backups also have limitations.

* Service downtime may occur due to planned maintenance, most notably for environments using a single instance of the Liferay or Search services.

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
* [File Storage](#file-storage)
* [Network Configuration](#network-configuration)
* [VPN Servers](#vpn-servers)

## All Services

These limitations apply to every service in a DXP Cloud environment:

* **Access to Old Logs**: By default, logs are only available from the last 30 days are available for each service. [Submit a Support request](https://help.liferay.com/) to access older logs (up to a year old).

* **Additional Instances per Service**: Your subscription plan determines the allowed [`scale` setting](../manage-and-optimize/auto-scaling.md) for your services. By default, all services will have only one additional instance (the Search service must use an odd number of additional instances). The `scale` setting will begin already configured to use the purchased number of instances for your subscription plan.

* **Downtime**: Services running with a single instance may experience restarts when the DXP Cloud infrastructure is updated for scheduled maintenance. Use high availability settings (two instances each of the Web server and Liferay services, and three instances of the Search service) for production-type environments to avoid disruptions. You can view the schedule for planned maintenance [here](https://help.liferay.com/hc/en-us/articles/360032562611-DXP-Cloud-Platform-Maintenance-and-Release-Schedule).

* **Memory per Service Instance**: Services can have up to a possible 200 GB of RAM, and this is determined by your subscription plan. The default plan has 16 GB per service.

* **Virtual CPUs per Service Instance**: Services can have up to a possible 32 vCPUs, and this is determined by your subscription plan.

## Liferay Service

These limitations apply to the [Liferay service](../using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.md) in each DXP Cloud environment:

* **Remote Staging**: [Remote Staging](https://learn.liferay.com/dxp/latest/en/site-building/publishing-tools/staging/configuring-remote-live-staging.html) is not available with DXP Cloud. Local Staging is still available and supported.

* **Autoscaling**: When enabled, autoscaling may only add new instances up to a maximum of 10.

* **Session Replication**: Replicating sessions between multiple Liferay instances in DXP Cloud may impact your instances' performance, and is not supported. <!-- Instead, use sticky sessions, or avoid using session storage entirely in your custom applications. -->

### Dynatrace

[Dynatrace](../manage-and-optimize/application-metrics.md#advanced-application-metrics-production-only) is not included in the Standard setup for DXP Cloud environments, but it can be purchased separately to use with it. Dynatrace is included in the High Availability setup, but only for Production or UAT environments.

These limitations apply to Dynatrace:

* **Dynatrace Metrics Discrepancy**: Dynatrace metrics do not match the metrics shown in the DXP Cloud Console. This is because Dynatrace displays metrics for the JVM process, while the console metrics measure the entire container hosting the JVM.

* **Streaming Liferay Logs**: Liferay logs cannot be streamed to Dynatrace logs.

* **Session Replay**: The Dynatrace Session Replay feature is not available with DXP Cloud.

## Database Service

These limitations apply to the [Database service](../platform-services/database-service/database-service.md) in each DXP Cloud environment:

* **Database Metrics**: The metrics displayed in the DXP Cloud console reflect the data for the service container, not individual service metrics.

* **Database Size**: The maximum size for a database is normally 100 GB. [Submit a Support request](https://help.liferay.com/) to increase this limit.

* **Downtime**: Database maintenance may cause downtime every few months. This downtime usually lasts about two minutes. This may not come with a notification in advance.

* **Read/write splits**: Configuring a read/write split in your database service is not supported in DXP Cloud.

## Search Service

These limitations apply to the [Search service](../platform-services/search-service.md) in each DXP Cloud environment:

* **Configuration**: Elasticsearch must be configured through the DXP Cloud workspace, and **not** the Liferay UI. The configuration file in the project workspace is used on each deployment and overwrites the previous configuration.

* **Memory Settings**: The default (and maximum) JVM heap size for the Elasticsearch server is 4 GB. The maximum allocation is determined by your subscription plan.

* **OS Packages**: Installing additional OS packages for the Search service is not supported.

* **Pod Management Policy**: Elasticsearch nodes in a cluster must connect to each other in order to start successfully. For search services with multiple instances, the `podManagementPolicy` value in the service's `LCP.json` file must be set to `parallel` to avoid issues with the service starting up.

## Backup Service

These limitations apply to the [Backup service](../platform-services/backup-service/backup-service-overview.md) in each DXP Cloud environment:

* **Backup Consistency**: As with any process copying from a database with changing data, consistency between data in the database and document library cannot be guaranteed if a backup is created while updates are occurring. To ensure a completely consistent backup, coordinate with your database administrator to freeze updates while you perform a [manual backup](../platform-services/backup-service/backup-service-overview.md#creating-a-manual-backup).

* **Backup Size**: Before DXP Cloud version 4.2.0, backups used [ephemeral storage](#file-storage). The size of backups in these versions is limited to the remaining space on a shared ephemeral disk, which may vary.

* **Backup Uploads**: Only one backup may be uploaded per minute.

* **Concurrent Operations**: Concurrent backup creation, restores, or uploads or not supported. However, concurrent downloads are supported.

* **Resource Allocation**: The RAM and number of vCPUs allocated to the Backup service are determined by your subscription plan. The default allocation is 2 vCPUs and 1 GB of RAM for the service.

* **Upload/Download Speed**: The speed of backup uploads or downloads is limited by your internet connection speed and the size of the backup. It may take up to several hours to download a backup with a very slow connection.

## Web Server Service

These limitations apply to the [Web server service](../platform-services/web-server-service.md) in each DXP Cloud environment:

* **Plugins**: Installing additional plugins for the web server is not supported.

* **Resource Allocation**: The web server has 2 vCPUs and 512 MB of memory by default. This may result in slower response times for large uploads or downloads. Your subscription plan determines the specific resource allocation for the service.

## Continuous Integration Service

These limitations apply to the [CI service](../platform-services/continuous-integration.md) in each DXP Cloud environment:

* **Administrative access**: Admin-level access is not allowed on the Jenkins server. Instead, use the [Jenkins pipeline hooks](../platform-services/continuous-integration.md#extending-the-default-jenkinsfile) to extend the CI pipeline. Existing DevOps processes may need to be adjusted to conform to this pipeline.

* **Concurrent API Calls**: Projects cannot perform concurrent calls to DXP Cloud APIs. This includes tasks such as deploying a build to an environment through the [CLI tool](./command-line-tool.md).

* **Resource Allocation**: The RAM and number of vCPUs allocated to the CI service are determined by your subscription plan. The default allocation is 4 vCPUs and 8 GB of RAM for the service.

* **Server capacity**: Your subscription plan determines the size of the data volume for the CI server.  The default size is 100 GB.

### Builds

These limitations apply to any builds created within a project:

* **Build Size**: Individual builds are limited to 2 GB each. You must ensure that the total size of the project in your repository is less than this limit.

* **Concurrent Builds**: A maximum of two concurrent builds may run on Jenkins because two executor threads are used.

* **Maximum Builds per Day**: Builds are limited to 300 per day. [Submit a Support request](https://help.liferay.com/) to increase this limit.

* **Private GitHub Servers**: Integration with private GitHub servers is not supported.

## Custom Services

These limitations apply to any [custom services](../platform-services/using-a-custom-service.md) in a DXP Cloud environment:

* **Host OS Access**: Privileged access to the host Operating System kernel is limited to subscriptions that include a private cluster.

## Security

These limitations apply to the security features available within DXP Cloud:

* **Antivirus**: The default Liferay DXP feature for scanning viruses on file upload cannot be used. DXP Cloud's [Antivirus solution](./dxp-cloud-infrastructure.md#antivirus) is used instead. Uploaded content is scanned on a schedule, and thus risks may not be detected immediately when a file is uploaded.

* **Authentications per Minute**: A maximum of 8400 authentications are allowed per minute.

* **Firewall Rules**: You must purchase a subscription with a private cluster and coordinate with DXP Cloud Support to set custom firewall rules. Custom firewall rules cannot be used with a shared cluster subscription. Any custom firewall rules created for a private cluster apply to all environments in the project.

* **IP Address Filtering**: IP address filtering can only be applied on the web server service.

## File Storage

These limitations apply to file storage for multiple services:

* **Ephemeral Storage**: Ephemeral Storage is used for all files not stored in volumes. Ephemeral Storage is located on the host node's internal storage, and it is shared between all containers running on that node. If a container requests more space than the host node has available, then the container is moved to another node. The hosts disks have a capacity of 250 GB.

* **Sharing Data Between Services**: Services with the StatefulSet [Deployment Type](../build-and-deploy/understanding-deployment-types.md) cannot share data with other services.

* **StatefulSet Storage Size**: You must make a Support ticket to add storage for services with the StatefulSet [Deployment Type](../build-and-deploy/understanding-deployment-types.md). The storage size of StatefulSet services cannot be reduced once it is increased.

## Network Configuration

These limitations apply to the network configuration of your services in a DXP Cloud environment:

* **Maximum Custom Domains**: There is a limit of 50 [custom domains](../infrastructure-and-operations/networking/custom-domains.md) if you have multiple services exposed outside of the environment (in addition to the default web server). However, the web server can use a limit of 1500 custom domains if it is the only point of entry.

* **Maximum SSL Certificates**: A maximum of 14 custom SSL certificates are allowed. The provider issuing the certificates may also impose its own limitations to make this less.

* **Public IP Addresses**: By default, every environment has one public IP address, and services within the environment have internal IP addresses. However, you can configure a service's ports to be external, assigning a public IP address to the service.

* **Wildcard SSL Certificates**: Wildcard certificates are not supported for Liferay's auto-generated SSL certificates. However, you may configure your instance with custom Wildcard SSL certificates.

## VPN Servers

These limitations apply if you have connected a [VPN server](../infrastructure-and-operations/networking/vpn-integration-overview.md) to the services in your environment:

**Site-to-Site VPN**: Site-to-Site VPN servers can only be configured with Google Cloud VPN. It also requires a private cluster subscription.

**Bandwidth**: Each VPN tunnel is limited to a total bandwidth of 3 Gbps. This limit applies to the total of incoming and outgoing traffic.

**Extending On-Premises Networks:** Cloud Interconnect or Express Route dedicated connections from an on-premises network are not supported. This applies to connections made directly or via partner providers, and with shared or private clusters.