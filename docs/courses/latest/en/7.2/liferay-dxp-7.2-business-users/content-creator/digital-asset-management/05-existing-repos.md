## Connecting with Existing Repositories

In the folders section, we discussed how you might have thousands, hundreds of thousands, or even millions of files that you want users to have access to from within your platform. It is going to be very difficult to manage all these files if they are sitting in a single unstructured repository, and even then, moving every single file might not be your preferred solution. Instead, it is often preferable to connect to existing external repositories from within your Liferay instance.

#### Connecting with Livingstone's External Repositories {#livingstone}

Livingstone has many files and documents that already exist in an external content management system. Josiah will have his hands full trying to manage all the external files and documents that need to be imported. Fortunately for Josiah and his team, Liferay allows for direct integration with existing document repositories so they do not all need to be imported. In fact, many of Livingstone's files exist in repositories that can be directly linked to their Liferay platform.

#### Integrating with Existing Repositories {#existing}

Administrators can connect any external repositories their business might be using to store and share files with Liferay DXP.

<div class="key-point">
Key Point: <br />
<strong>External repositories</strong> can be connected to sites in Liferay DXP.
</div>

Liferay can easily connect and manage content within a repository as long as that repository supports the CMIS standard.

In order to connect to a CMIS repository, a Site Administrator has to: 

1. Adjust the portal properties to configure the appropriate authentication settings
2. Synchronize the user accounts between the Liferay platform and the external repository
3. Add the repository to _Documents and Media_ in the _Documents and Media_ section of Site Administration on the platform

<br />

<figure>
	<img src="../images/add-new-repo.png" style="max-height:40%" />
	<figcaption style="font-size: x-small">Fig.1 Adding an external repository to a Liferay site's Documents and Media repository</figcaption>
</figure>

<br />

Once you have done the above, your external repository is effectively mounted inside the site’s default repository. Liferay DXP does this by creating a folder that acts as a proxy for your external repository. When users enter this special folder, they see the contents of the external repository.

<div class="note">
Note: For more information on connecting to a third-party CMIS repository, you can see the documentation available at <a href="https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/using-external-repositories">https://dev.liferay.com/discover/portal/-/knowledge_base/7-0/using-external-repositories</a>.
</div>

Liferay also provides widgets to allow for interoperability with Sharepoint (https://web.liferay.com/marketplace/-/mp/application/15188537). This widget is available in the Liferay Marketplace at https://web.liferay.com/marketplace.

Once the platform is connected to an external document repository, users are able to: 

- Read/write documents and folders to the repository
- Check-in, check-out, and undo check-out with documents
- Download documents to their local machine
- Move and reorganize documents within the connected repository
- See revision and update history
- Revert to a previous revision

<div class="summary">
<h3>Knowledge Check</h3>
<ul>
  <li>Liferay allows for direct integration with _________________ document repositories.</li>
  <li>In order to connect with a document repository, that repository must support the _________________ standard.</li>
  <li>When you connect to a document repository, Liferay DXP creates a _________________ that acts as a proxy for your external repository.</li>
</ul>
</div>
