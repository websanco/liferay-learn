# Development Requirements

Before beginning deployment in Liferay DXP Cloud, developers planning to create modules for DXP Cloud should install the required tools. There are various development tools and frameworks that developers can use to create their customizations, but deployment to DXP Cloud begins with the GitHub project created during the provisioning process.

In order to set up a local environment for development with DXP Cloud, the following are required:

* [JDK 1.8 or JDK 11](http://www.oracle.com/technetwork/java/javase/downloads/index.html): required for running Liferay DXP locally

    ```important::
       JDK 9 and JDK 10 are unsupported. Please see the latest `Compatibility Matrix <https://web.liferay.com/documents/14/21598941/Liferay+DXP+7.2+Compatibility+Matrix/b6e0f064-db31-49b4-8317-a29d1d76abf7?>`_ for more information.
    ```

* [Gradle 4+](http://www.gradle.org/downloads): used to run any of various build commands for Liferay DXP and DXP Cloud

* [Git](https://git-scm.com/): necessary for adding changes to deploy to DXP Cloud

* Repository hosting service account: required to push code changes to a remote repository and submit changes for DXP Cloud builds. You can use an account for any of these available options:

  * [GitHub](https://github.com/)
  * [Bitbucket](https://bitbucket.org/)
  * [GitLab](https://gitlab.com/)

```note::
    Liferay DXP Cloud has similar prerequisites to `Liferay Workspace <https://help.liferay.com/hc/en-us/articles/360029147471-Liferay-Workspace>`_. Developers who are familiar with creating a Liferay Workspace using `Liferay Dev Studio <https://customer.liferay.com/downloads?p_p_id=com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_productAssetCategoryId=118191007&_com_liferay_osb_customer_downloads_display_web_DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191038>`_, `Maven <https://help.liferay.com/hc/en-us/articles/360017885592-Maven-Workspace>`_, or the `Liferay IntelliJ Plugin <https://plugins.jetbrains.com/plugin/10739-liferay-intellij-plugin>`_ will have most of the necessary tools already set up.
```

## Additional Information

* [Configuring Your Github Repository](../getting-started/configuring-your-github-repository.md)
* [Configuring Your Bitbucket Repository](./configuring-your-bitbucket-repository.md)
* [Configuring Your GitLab Repository](./configuring-your-gitlab-repository.md)
* [Overview of the DXP Cloud Deployment Workflow](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md)
* [Logging Into Your Liferay DXP Instance](../getting-started/logging-into-your-dxp-cloud-services.md)
