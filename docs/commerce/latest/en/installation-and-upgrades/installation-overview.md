# Installation Overview

Liferay Commerce is a digital commerce platform built on Liferay DXP. This article outlines prerequisites and available options for installing, deploying, and maintaining Liferay Commerce.

## Prerequisites

For an optimal installation experience, please review the Compatibility Matrix and related installation material prior to getting started.

* [Liferay DXP 7.3 and Commerce 3.0 Compatibility Matrix](https://help.liferay.com/hc/en-us/articles/360049238151) for a list of supported technologies.
<!-- * See the [Liferay DXP 7.3 Deployment Checklist]() for information about Liferay DXP architecture and performance tuning guidelines. -->

## Starting with Liferay Commerce

Liferay Commerce 3.0 comes bundled with Liferay Portal 7.3 CE GA6 and Liferay DXP 7.3 GA1. The Community Edition comes bundled with a basic version of Commerce, while the Enterprise Edition comes with a full featured Enterprise Commerce. Both CE and Enterprise versions are available as Docker images and downloadable bundles:

| Installation Method | Purpose |
| --- | --- |
| [Docker image](https://learn.liferay.com/dxp/latest/en/getting-started/starting-with-a-docker-image.html#get-started-with-liferay) | Getting started with a Docker image is the fastest way to begin touring Liferay Commerce |
| [Bundle](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/installing-a-liferay-tomcat-bundle.html) | Liferay Commerce pre-bundled with an application server. The most common way to get started running a Liferay installation. |

Liferay Portal bundles and images require no additional, Commerce-specific installation steps. Basic Commerce features come activated and can be used immediately on startup.

By contrast, in DXP installations, both basic and enterprise Commerce modules are deactivated by default and require activation for use. Once both DXP and Commerce licenses are deployed and validated, all Commerce modules are started and become immediately available; no server restart is required. See [Activating Liferay Commerce Enterprise](./activating-liferay-commerce-enterprise.md) to learn more.

## Maintaining Liferay Commerce

Periodic maintenance updates and product upgrades are available to ensure and enhance the quality of your experience with Liferay Commerce. See the following articles to learn more:

* [Maintenance Versions](./maintenance-versions.md)
* [Upgrading Liferay Commerce](./upgrading-liferay-commerce.md)

## Liferay Commerce 2.1 and Below

Previous releases of Liferay Commerce required additional installation steps that are no longer necessary on Commerce 3.x and above.

### Prerequisites

Before installing Liferay Commerce 2.1 and below, review the system requirements and Liferay DXP deployment checklist that correspond to your installation version.

* [Liferay Commerce 2.0 Compatibility Matrix](https://web.liferay.com/documents/14/21598941/Liferay+Commerce+2.0+Compatibility+Matrix/0ed97477-f5a7-40a6-b5ab-f00d5e01b75f) for a list of supported technologies.
* [Liferay DXP 7.1 Deployment Checklist](https://www.liferay.com/documents/10182/3292406/Liferay+DXP+7.1+Deployment+Checklist/cacaac23-9e02-411a-dcc9-adf86f95c513)
* [Liferay DXP 7.2 Deployment Checklist](https://www.liferay.com/documents/10182/3292406/Liferay+DXP+7.2+Deployment+Checklist.pdf/22dee290-6b06-0bdc-aa89-30bb88d1d42e?t=1566483298239)

### Installing Using a Docker Image

Install Liferay Commerce using Docker. See [Using Liferay Commerce Docker Image](./installing-commerce-2.1-and-below/using-the-liferay-commerce-docker-image.md) for more information.

### Installing Using a Bundle

Install Liferay Commerce using a bundle from the [Liferay Community Downloads](https://commerce.liferay.dev/download) page. See [Using the Liferay Commerce Tomcat Bundle](./installing-commerce-2.1-and-below/using-the-liferay-commerce-tomcat-bundle.md) for more information.

### Installing Using an Existing Liferay DXP Installation

Install Liferay Commerce by deploying the `LPKG` file to your preexisting Liferay DXP installation. See [Deploying Liferay Commerce to an Existing Installation](./installing-commerce-2.1-and-below/deploying-liferay-commerce-to-an-existing-liferay-installation.md) for more information.

## Additional Information

* [Liferay Digital Experience Platform Performance](https://www.liferay.com/documents/10182/3292406/Liferay+DXP+Performance+-+Benchmark+Study+of+Liferay+DXP+7.1/fe7d4cd2-2efc-b5cc-9680-825ec9bad5be)
* [Activating a Marketplace App Through a Proxy Server](https://help.liferay.com/hc/en-us/articles/360018427391)
