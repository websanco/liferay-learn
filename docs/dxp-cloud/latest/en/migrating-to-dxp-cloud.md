# Migrating to DXP Cloud

```{toctree}
:maxdepth: 1

migrating-to-dxp-cloud/matching-dxp-versions.md
migrating-to-dxp-cloud/creating-data-backup-files.md
migrating-to-dxp-cloud/uploading-and-restoring-the-data-backup.md
migrating-to-dxp-cloud/migrating-dxp-configurations-and-customizations.md
migrating-to-dxp-cloud/migrating-web-server-configurations.md
migrating-to-dxp-cloud/migrating-search-configurations.md
migrating-to-dxp-cloud/connecting-the-vpn.md
migrating-to-dxp-cloud/next-steps-after-migration.md
```

Liferay DXP Cloud is a secure and reliable enterprise platform built for high availablity, scalability, and performance. 

Migrating to DXP Cloud from an on-premises Liferay DXP environment entails moving your entire instance and environment into an environment running on DXP Cloud, including all of your data (in documents or the database), configurations, and customizations (such as OSGi modules or plugins). This also involves working and deploying changes with a Git repository that directly integrates with your DXP Cloud environment(s).

Here are the main stages of migration to DXP Cloud:

- [Stage 1: Matching DXP Versions](./migrating-to-dxp-cloud/matching-dxp-versions.md)
- [Stage 2: Creating Data Backup Files](./migrating-to-dxp-cloud/creating-data-backup-files.md)
- [Stage 3: Uploading and Restoring the Data Backup](./migrating-to-dxp-cloud/uploading-and-restoring-the-data-backup.md)
- [Stage 4: Migrating DXP Configurations and Customizations](./migrating-to-dxp-cloud/migrating-dxp-configurations-and-customizations.md)
- [Stage 5: Migrating Web Server Configurations](./migrating-to-dxp-cloud/migrating-web-server-configurations.md)
- [Stage 6: Migrating Search Configurations](./migrating-to-dxp-cloud/migrating-search-configurations.md)
- [Stage 7: Connecting the VPN](./migrating-to-dxp-cloud/connecting-the-vpn.md)
- [Stage 8: Next Steps after Migration](./migrating-to-dxp-cloud/next-steps-after-migration.md)

## Why Should I Migrate to DXP Cloud?

Migrating to DXP Cloud allows you to take advantage of its [high availability and scalability](../getting-started/introduction-to-dxp-cloud.md#high-availability-scalability-and-performance) with quick-to-deploy features like [clustering](../using-the-liferay-dxp-service/setting-up-clustering-in-dxp-cloud.md), [load balancing](infrastructure-and-operations/networking/load-balancer.md), and [auto-scaling](../manage-and-optimize/auto-scaling.md) out-of-the-box. Built-in [integration with Git and Jenkins](../getting-started/introduction-to-dxp-cloud.md#accelerated-development-with-built-in-ci-cd) also streamlines your development process. This makes your production instance easier to deploy and develop with, more flexible for your needs, and more reliable for your Users.

## What Do I Need to Prepare in Advance?

You can take several measures to prepare for the migration in advance, including planning and setting up your project repository and required tools ahead of time.

### Plan Ahead

The first important task to perform to prepare for the migration is to plan ahead and reserve the time for it. Work with your database administrator to arrange a time to make the transition once the migration steps are complete, as well as a window to freeze your data for the [second stage of migration](./creating-data-backup-files.md#freeze-the-data) (creating backup files for your database and document library).

You may also want to review the steps of the migration ahead of time so that you have an idea of what to expect and how long the migration may take for you to complete.

### Install and Learn the Tools

You should also ensure that you have the necessary tools on your local system for the migration steps:

* [Git](https://git-scm.com/): You must have Git installed so that you can use it to commit and push your changes to DXP Cloud throughout the migration.
* Repository hosting service account: You must have an account with one of these websites to push changes and submit them for DXP Cloud builds. You can use an account with [GitHub](https://github.com/), [Bitbucket](https://bitbucket.org/), or [GitLab](https://about.gitlab.com/).
* [The Liferay Patching Tool](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/maintaining-a-liferay-installation/reference/installing-the-patching-tool.html): You need the Patching Tool to confirm your patching and hotfix information for the first stage of the migration.
* File compression software: If you are using Windows as your local system's OS, then you also need file compression software (such as [7-Zip](https://www.7-zip.org/)) to pack/unpack compressed files.

Once you have these tools installed, you may want to take some time to familiarize yourself with them.

For instance, if you are new to Git, then you may want to see the [official Git materials](https://git-scm.com/doc) (including reference documentation, a command cheat sheet, etc.) or practice using it independently before applying it to your migration. Git is a powerful version control tool with capabilities beyond what is used in this migration guide.

Migrating to DXP Cloud also involves moving your own custom code, modules, and themes into a Liferay Workspace specifically for your project. If you are new to Liferay Workspace, then you may also want to learn how to use it effectively [here](https://learn.liferay.com/dxp/latest/en/building-applications/tooling/liferay-workspace/what-is-liferay-workspace.html).

### Prepare the Environment

Prepare your environment for the migration in advance to ensure the migration process starts smoothly.

If you have not already done so, then complete the tasks in the [Initial Setup Overview](../getting-started/initial-setup-overview.md) to ensure that your DXP Cloud environment is up and running as expected. In case you run into problems during the migration, you may also want to enable [Support Access](../troubleshooting-support-access.md); enabling Support Access gives Liferay DXP Cloud Support staff more capacity to help if necessary by giving them access to your project's console, logs, and more.

Additionally, set up your repository (using [GitHub](../getting-started/configuring-your-github-repository.md), [Bitbucket](../getting-started/configuring-your-bitbucket-repository.md), or [GitLab](../getting-started/configuring-your-gitlab-repository.md)) to confirm that you can make deployments. The migration requires deploying multiple changes throughout the process, so you must be able to deploy them to proceed with the migration.

## How Do I Begin the Migration?

Make sure you have access to your local Liferay DXP instance and [your repository](#prepare-the-environment) for the migration. Then, when you are ready to start, begin with [Stage 1: Matching DXP Versions](./matching-dxp-versions.md).
