## Optional Exercise: Publish a REST Service

<div class="ahead">

#### Exercise Goals

- Create a Liferay Module project using the Rest template
- Declare dependencies
- Implement the AssignmentRestApplication class
- Final code review
- Deploy and test
- Create an OAuth 2.0 application
- Test

<div class="note">
Note: This exercise is optional. It is not written as step-by-step exercises so that you can explore and experiment more.
</div>

</div>
	
> In this exercise, we will be adding a REST API to the service layer by way of a JAX-RS Whiteboard. We will be using the standard JAX-RS Whiteboard annotations. You can find more information regarding JAX-RS Whiteboard in the official [OSGi documentation](https://osgi.org/specification/osgi.cmpn/7.0.0/service.jaxrs.html). We'll expose two REST methods: one to get all the Assignments and another one to look up a specific Assignment by its ID.

> a cURL command line client is needed for this exercise. Windows users can download a client for example [here](https://curl.haxx.se/windows/). 

<div class="page"></div>

#### Create a Liferay Module Project

Create a new module project in dev studio (or if you've been using other tools in the previous exercises, use those tools) Use the following information for the first step:
	* __Project Name__:  "indexer-post-processor"
	* __Build Type__: Gradle
	* __Liferay Version__: 7.3
	* __Project Template__: rest

Use the following information in the second step and then click finish:
	* __Component Class Name__: "AssignmentRest"
	* __Package Name__: "com.liferay.training.gradebook.rest"

#### Resolve Dependencies

We'll need to resolve dependencies for the portal kernel, servlet, portlet and Gradebook API by opening `build.gradle` for the gradebook-api module and adding the following dependencies:

```groovy
compileOnly group: "com.liferay.portal", name: "com.liferay.portal.kernel"
compileOnly group: "javax.portlet", name: "portlet-api"
compileOnly group: "javax.servlet", name: "javax.servlet-api"
compileOnly project(":modules:gradebook:gradebook-api")
```	

#### Implement the GradebookRestApplication Class

Well implement a simple rest application which you can use to fetch the list of all the Assignments or just a single one by its ID. OAuth will be disabled in this application.

Replace the `AssignmentRestApplication` class contents with the code below.

```java
package com.liferay.training.gradebook.rest.application;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.service.CompanyService;
import com.liferay.portal.kernel.service.GroupService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.training.gradebook.model.Assignment;
import com.liferay.training.gradebook.service.AssignmentService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

/**
	* Simple REST application.
	* 
	* @author liferay
	*/
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/gradebook-rest",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=Gradebook.Rest",
		"liferay.auth.verifier=true",	// default
		"liferay.oauth2=true"			// default
	},
	service = Application.class
)
public class AssignmentRestApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Path("/assignments")
	@Produces({
		MediaType.APPLICATION_JSON
	})
	public String getAssignments() {
		
		try {
			List<Assignment> assignments = new ArrayList<Assignment>();

			Company company =
				_companyService.getCompanyById(_portal.
					getDefaultCompanyId());

			List<Group> groups =
				_groupService.getGroups(company.getCompanyId(), 0, true);

			for (Group group : groups) {
				assignments.addAll(
					_assignmentService.getAssignmentsByGroupId(
						group.getGroupId()));
			}

			return JSONFactoryUtil.serialize(assignments);

		}
		catch (PortalException pe) {
			pe.printStackTrace();
			return "[{}]";
		}
	}

	@GET
	@Path("/assignment/{assignmentid}")
	@Produces({
		MediaType.APPLICATION_JSON
	})
	public String getAssignment(
		@PathParam("assignmentid") long assignmentId) {

		try {
			return JSONFactoryUtil.serialize(
				_assignmentService.getAssignment(assignmentId));
		}
		catch (Exception e) {
			e.printStackTrace();
			return "{}";
		}
	}

	@Reference
	private AssignmentService _assignmentService;

	@Reference
	private CompanyService _companyService;
	
	@Reference
	private GroupService _groupService;

	@Reference
	private Portal _portal;
}
```

#### Deploy and Test

Go to http://localhost:8080 and sign in as the administrative user. Then, go to http://localhost:8080/o/gradebook-rest/assignments.

You should get an access denied message:

```xml
<Forbidden>
	<message>
		Access denied to
		com.liferay.training.gradebook.rest.application.
		AssignmentRestApplication#getAssignments
	</message>
</Forbidden>
```
	
The JAX-RS REST application requires OAuth 2.0 authorization by default and we have to configure that to grant access to the service.

#### Create an OAuth 2.0 Application

Go to localhost:8080 again and open *OAuth2 Administration* in the Control Panel. Add an application and use the following information for the first step:
	* __Application Name__: "Gradebook REST"
	* __Client Profile__:  "Headless Server"

Leave the defaults for the other values and click *Save*. Copy the following values on the next dialog:
	* __Client ID__
	* __Client Secret__ (Click the *Edit* button to show the secret)

Open the _Scopes_ tab, then open *Gradebook.Rest* and check *read data on your behalf*. Click the save button.

#### Test Again

Open the command line shell. Use a cURL request to get an access token. Use the client ID and secret values from the previous step.

```bash
curl http://localhost:8080/o/oauth2/token -d
	'grant_type=client_credentials&client_id=[CLIENT_ID]&client_secret=[CLIENT_SECRET'
```
You'll get the access token as a response:
```bash
{"access_token":"262e6fffb6faffef928b251efa9f15644526023d63a7a9a3d6527b94ef12b2c",
	"token_type":"Bearer","expires_in":600,"scope":"Gradebook.Rest.everything.read"}
```

Next use the access token and call the Assignment REST service to list all the assignments.

```bash
curl -H 'Accept: application/json' -H "Authorization: Bearer [ACCESS_TOKEN]"
	http://localhost:8080/o/gradebook-rest/assignments
```

You'll get an Assignments list as a response.

```bash
{"javaClass":"java.util.ArrayList","list":[{"contextName":
	"com.liferay.training.gradebook.service_1.0.0","javaClass":
	"com.liferay.training.gradebook.model.impl.AssignmentImpl",
	"serializable":{"statusDate":null,"originalUuid":
	"ee4172af-a9d8-83cd-5f2a-cdd341813732","columnBitmask":0,
	"cachedModel":false,"groupId":20123,"dueDate":{"javaClass":
```

Find any `assignmentId` value in the response and get a single assignment from our REST service.

```bash
curl -H 'Accept: application/json' -H "Authorization: Bearer [ACCESS_TOKEN]"
	http://localhost:8080/o/gradebook-rest/assignment/[ASSIGNMENT_ID]
 ```

#### Takeaways 

In this exercise you've seen how easy it is to create a REST API for your Liferay Services. Because JAX-RS Whiteboard is a standard OSGi specification, you now have another convenient tool at your disposal to create APIs that others can use to easily integrate with your applications.

> See Developer Network article (https://dev.liferay.com/en/develop/tutorials/-/knowledge_base/7-2/jax-rs-and-jax-ws) for more information about using JAX RS and JAX WS in your projects.

