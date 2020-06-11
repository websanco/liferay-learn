# Fundamentals

The fundamentals of developing on Liferay DXP and customizing it are perhaps best learned in the context of projects. It's in projects that you configure access to  DXP's API, extend and override  DXP features, and package your software for deployment. Projects are developed as OSGi JARs, but are all installed to Liferay's OSGi framework as OSGi bundles (modules). These modules can depend on external Java packages, share Java packages, and be manipulated at run time via Apache Gogo Shell. The fundamentals are explained in the context of projects so that you understand them in a practical sense and can apply them right away. Here are the fundamental topics:

* **Module Projects** explains what an OSGi module is, its structure, and how it is used by Liferay's OSGi framework.

* **Configuring Dependencies** demonstrates how to identify and configure Liferay artifacts and third-party artifacts to use their Java packages in your projects.

* **Importing and Exporting Packages** shows how to import the packages your projects need and export packages your projects provide. Liferay's tooling detects package use and specifies package imports automatically.

* **Semantic Versioning** shows how Liferay uses a standard for ascribing meaning to major, minor, and micro versions of modules and Java packages.

* **Gogo Shell** enables you to examine components, debug issues, and manage deployments.

Start with understanding how module projects are used in development.