# Stage 8: Next Steps After Migration

Congratulations! If you have completed all of the previous stages in this section, then you have successfully migrated your Liferay DXP installation to DXP Cloud!

Now that you have Liferay DXP running on DXP Cloud, you are ready to begin taking advantage of its features, including [automated backups](../platform-services/backup-service/backup-service-overview.md), [built-in continuous integration](../platform-services/continuous-integration.md), automatic [service logging](../troubleshooting/reading-dxp-cloud-service-logs.md), and built-in [security features](../infrastructure-and-operations/information-security-and-dxp-cloud.md).

However, in order to take full advantage of DXP Cloud's capabilities, there are some additional steps you can perform while getting started:

* [Set up teams](#set-up-teams) to manage membership and permissions
* [Set up Single Sign-On](#set-up-sso) to streamline authentication
* [Add custom domains](#add-custom-domains) to meet your needs
* [Configure Your Services](#configure-your-services) to behave the way that works best for you
* [Optimize and tune application performance](#optimize-and-tune-application-performance)
* [Get started developing on DXP Cloud](#get-started-developing-on-dxp-cloud)

## Set Up Teams

As an Administrator in DXP Cloud, you have control over inviting other members and managing their access to various areas of your project as needed. [Invite your team members](../manage-and-optimize/environment-teams-and-roles.md#inviting-team-members) and then give them the appropriate [team roles](../manage-and-optimize/environment-teams-and-roles.md#understanding-team-roles) to ensure everyone has the level of access they need in your DXP Cloud project.

See [Environment Teams and Roles](../manage-and-optimize/environment-teams-and-roles.md) for more information.

## Set Up SSO

Single Sign-On allows you to use your preferred identity provider to authenticate Users to Liferay in your DXP Cloud instance. This can help your sign-on experience feel smoother and more integrated with the rest of your project.

DXP Cloud supports Signle Sign-On Identity Providers that are compliant with SAML 2.0. For more information, see [Using SSO with DXP Cloud](../infrastructure-and-operations/security/using-sso-with-dxp-cloud.md).

## Add Custom Domains

Ensure that your environment on DXP Cloud hosts your own custom domains as needed. DXP Cloud manages your custom domains, allowing you to define certified domains for each environment.

See [Custom Domains](../infrastructure-and-operations/networking/custom-domains.md) for more information.

## Set Up a Disaster Recovery Environment

By default, DXP Cloud mitigates downtime from service outages by providing [automatic disaster recovery](../troubleshooting/disaster-recovery-overview.md#automatic-disaster-recovery-strategy) within the same region. However, in the event of a disaster causing an outage in the area servicing your DXP Cloud environments, you should also consider using a fall-back environment (or Disaster Recovery environment) to minimize downtime for Users on your production instance.

[Configure a Diaster Recovery environment](../troubleshooting/configuring-cross-region-disaster-recovery.md) to maintain the most robust possible protection from unplanned downtime. Learn more about automatic and cross-region Disaster Recovery [here](../troubleshooting/disaster-recovery-overview.md).

## Configure Your Services

In addition to the `liferay` service, your main DXP Cloud environments all have a set of default services that you can configure individually. Each of these services has some configurations that you should configure early on:

* The [`backup` service](../platform-services/backup-service/backup-service-overview.md) automatically creates backups at regular intervals (or when you trigger them manually) which you can restore to your environments at any time. You may want to begin by setting your preferred [frequency and retention period](../platform-services/backup-service/backup-service-overview.md#scheduling-automated-backups-and-cleanups) for your backups.

* The [`search` service](../platform-services/search-service.md) provides an Elasticsearch implementation for your `liferay` service. You can deploy `.yml` configuration files in your `search` service's `configs/common/config/` folder to [configure the Elasticsearch behavior](../platform-services/search-service.md#configurations). See the [official Elasticsearch documentation](https://www.elastic.co/guide/en/elasticsearch/reference/current/settings.html) for more information.

* The [`webserver` service](../platform-services/web-server-service.md) provides a gateway between your DXP Cloud services and the rest of the internet. Depending on the performance and the types of requests your Liferay instances service, you may want to tweak the [timeout or number of retries](../platform-services/web-server-service.md#environment-variables) for requests to be handled as expected.

* The [`database` service](../platform-services/database-service/database-service.md) securely provides the database that you [uploaded earlier in migration](./uploading-and-restoring-the-data-backup.md). You may want to configure your preferred [database maintenance window](../platform-services/database-service/database-service.md#database-maintenance-window-variables) to mitigate the impact of downtime for maintenance.

## Optimize and Tune Application Performance

As you begin using Liferay in DXP Cloud, familiarize yourself with the available tools for monitoring and tuning application performance:

* **Alerts** can notify you in real-time when your services are running into problems with resource limitations, when they have scaled the number of instances up or down, and when they are experiencing other problems. [Configure your status alerts](../manage-and-optimize/real-time-alerts.md) to fit your needs so that you are aware of these events.

* **Auto-scaling** automatically adjusts the number of instances of your Liferay DXP service as needed to accommodate User traffic. [Configure auto-scaling](../manage-and-optimize/auto-scaling.md) to take advantage of this optimization.

* View the [**service popover**](../manage-and-optimize/application-metrics.md#service-popover) and [**Monitoring screen**](../manage-and-optimize/application-metrics.md#extended-application-metrics) to view details about your services and their resource usage. See [Application Metrics](../manage-and-optimize/application-metrics.md) for more information about monitoring your services' performance.

* For production environments, you can also use [Dynatrace integration](../manage-and-optimize/application-metrics.md#advanced-application-metrics-production-only) to use more advanced performance metrics. *Note that this requires your own [Dynatrace account](https://www.dynatrace.com/) to integrate with DXP Cloud.*

## Get Started Developing on DXP Cloud

Now that you have migrated to DXP Cloud and you have already deployed [your customizations](./migrating-dxp-configurations-and-customizations.md), you can continue your developments by deploying your changes to your Cloud environment(s). If you have not already, then getting familiar with the [command-line tool](../reference/command-line-tool.md) can help you perform development tasks.

In addition to deploying your services [via the DXP Cloud console](../build-and-deploy/deploying-changes-via-the-dxp-cloud-console.md), you can also configure [automatic deployments](../build-and-deploy/automatically-deploying-ci-service-builds.md) to speed up the process for your development environment. You can also [customize the Jenkins pipeline](../platform-services/continuous-integration.md#extending-the-default-jenkinsfile) that the CI service uses to suit your needs.

Learn more about the DXP Cloud deployment workflow [here](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md).
