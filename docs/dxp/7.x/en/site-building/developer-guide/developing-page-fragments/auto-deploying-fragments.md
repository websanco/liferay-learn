# Auto-deploying Fragments

> Liferay DXP 7.3+

While you can [manually import Fragments](TODO:managing-fragments) into your server, you can also auto-deploy Fragment Collections if you prefer. This lets you deploy your Fragments without touching the UI. 

```note::
		This process can be automated if you're using the `Fragments Toolkit <./developing-page-fragments-with-the-fragments-toolkit.md>`_ by running `npm run compress` and answering yes to the "Add deployment descriptor" question. If you've created your Fragment Collection with your own tooling, you must complete these steps manually.
```

## Overview

1. [Deploy an Example](#deploy-an-example)
1. [Walk Through an Example](#walk-through-an-example)

## Deploy an Example

> Liferay DXP 7.3+

First you must deploy an example auto-deployable Fragment. Follow these steps:

1. Start the Docker container with a bind mount:

    ```bash
    docker run -d -it -p 8080:8080 -p 8000:8000 --name mylrdev -v C:\Users\liferay\Desktop\liferay-docker:/mnt/liferay liferay/portal:7.3.0-ga1
    ```

1. Download the [example auto-deployable Fragment Collection](https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/dev/01-developing-page-fragments/making-a-page-fragment-configurable/1584366888liferay38.zip):

    ```bash
    curl https://github.com/liferay/liferay-learn/tree/master/docs/dxp/7.x/en/site-building/dev/01-developing-page-fragments/making-a-page-fragment-configurable/1584366888liferay38.zip
    ```

1. Copy the Fragment Collection's ZIP to the `[host_folder]/deploy` folder for your Docker image's [bind mount](TODO), or create the `[host_folder]/deploy` folder if it doesn't exist. Alternatively, you can [import the Fragment manually](TODO:managing-fragments) instead.

    ```bash
    cp 1584366888liferay38.zip path/to/your/bind/mount
    ```
    
    ```note::
		    You must restart the Docker container if you're creating the `[host_folder]/deploy` folder for the first time in your bind mount.
    ```

1. Confirm the deployment to the Liferay Docker container console. The log message below should appear in the Docker console:

    ```bash
    INFO  [com.liferay.portal.kernel.deploy.auto.AutoDeployScanner][AutoDeployDir:263] Processing 1584366888liferay38.zip
    ```

1. Verify that the Fragment Collection is available. Open your browser to `https://localhost:8080`, and open the Product Menu and go to *Site Builder* &rarr; *Page Fragments* under the Site Menu. The Collection is listed with the other Collections.

    ![The Collection is available.](./auto-deploying-fragments/images/01.png)

Great! You successfully auto-deployed a Fragment. Next, you'll walk through the example and learn how it works.

## Walk Through the Example

* [Add the Deployment Descriptor](#add-the-deployment-descriptor)
* [Compress and Deploy the Collection to Auto-deploy Your Fragments](#compress-and-deploy-the-collection-to-auto-deploy-your-fragments)

### Add the Deployment Descriptor

1. Create a `liferay-deploy-fragments.json` file in the [Collection]():

		* `auto-deployable-fragment-collection/`
			* `src/auto-deployed-collection/auto-deployed-fragment/`
				* Fragment files...
			* `liferay-deploy-fragments.json`
			* `package.json`

1. Provide information about the scope of the Fragments: System-wide, a virtual instance (Company), or a Site (group). The example has the configuration below that specifies to auto-deploy the Fragments to a Site (group) called "Guest" within a virtual instance (liferay.com):

		```json
		{
			"companyWebId": "liferay.com",
			"groupKey": "Guest"
		}
		```

		Both keys in this JSON file are optional. You can make fragments available system-wide, for all the instances, by making the deployment descriptor an empty JSON object, or adding this configuration:

		```json
		{
			"companyWebId": "*"
		}
		```

### Compress and Deploy the Collection to Auto-Deploy Your Fragments

Compress the Fragment project into a ZIP and Copy the Collection `zip` file to the deploy folder of [Liferay-Home] to auto-deploy it to the scope specified in the `liferay-deploy-fragments.json` file.

		```bash
		zip auto-deployable-fragment-collection
		cp auto-deployable-fragment-collection.zip path/to/liferay/home/deploy/folder
		```