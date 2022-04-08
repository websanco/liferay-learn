---
description: 06 - Real World Application
title: Testing
order: 11
---

## Testing

In this section, we'll discuss some general principles, practices, and guidelines of software testing. The practical part of the section will focus on integration testing.

#### Why Test?

Humans make mistakes and create bugs in the software. But that's not the only reason for software testing. Here are some additional reasons for testing:

* Finding bugs and defects in the software (functional testing)
* Verifying interaction between software components (integration testing)
* Checking if both functional and non-functional requirements are met (system testing)
* Checking if user acceptance criteria is met (user acceptance testing)
* Managing the development lifecycle (regression testing)

Testing provides concrete metrics about the state of your software. When done properly, it inevitably improves software quality and, ultimately, customer satisfaction.

####  What You Should Test

Things that generally should be tested include:

* The "business logic"
* CRUD functionalities
* Code or functionalities that are intensively used everywhere in the application 
* Code or functionalities developed by multiple developers
* Code or functionality that changes often

> Consider quantity vs. quality: having fewer well-targeted tests of qood quality is usually better than the opposite.

#### Functional vs Non-Functional Testing

Software testing is often divided into two main categories:

1. Functional testing
1. Non-functional testing
 
The purpose of __functional testing__ is to verify *what* the system does against known requirements. We give tests some input and expect certain kinds of output. Examples of functional testing are unit and integration testing. 

__Non-Functional Testing__ is used to verify the way the system works. Non-functional testing checks that all of the components are interacting as designed, the system as a whole works as defined within agreed performance and capacity levels, and the system is ready for production. Examples of this type of testing are, for example, performance and security testing.

Both functional and non-functional testing categories are necessary. Although functional testing often takes the major role in testing, performance and load tests can expose defects that otherwise would pass the process and go to production. Fixing issues in production is often more difficult, and more expensive.

#### Stages of Testing

Properly done, testing happens in multiple stages. The smallest software components like methods are tested first, then their interaction, then the complete system and, last, the user perception.

<img src="../images/testing-stages.png" style="max-height:100%"/>

The diagram below shows some tooling examples for the different stages:

<img src="../images/tooling-examples.png" style="max-height:100%"/>

#### General Recommendations for Testing

Make a proper __testing plan__ and try to test on all the stages of the development cycle. 

A good plan takes care of __regression__. Especially in the beginning of the application lifecycle, there are typically lots of changes in the code, making it prone to regression.

It's practically not possible to cover everything and every possible use case. __Focus on the critical parts__ first, and then extend the coverage, but not at the expense of quality. Rely on automation tools.

Test __borderline cases__. Boundary values when it comes to input fields on the user interface or calculations are a popular living area of bugs. These are also the areas where certain kinds of security breaches happen.

Keep tests __simple__. Creating tests can be time-consuming. Try to keep tests simple so that they provide exactly the information they should to remain maintainable over code changes and lifecycles. If your tests seem to be too complex, it might be a sign of too-complex code in the application itself.

<br />

#### Testing Types

Let's do a brief overview of some of the most common functional testing types. 

#### Unit Testing

Unit testing is the first stage of testing. It's meant for the smallest units of code, usually done against methods. Unit tests should be atomic and have only minimal dependencies on other units of code. 

A good unit test should:

* Test an isolated component only, with no integrations
* Be able to fail. If a test cannot fail, it's useless. 
* Have only one unambiguous reason to fail (often hard to reach)
* Not affect the data other test cases rely on
* Not rely on other tests
* Test just one thing and test it well
* Not have any conditional logic
* Be reliable
* Be repeatable
* Be platform-independent
* Have self-documenting method names

Liferay uses the JUnit framework for unit testing. Below is a simple example of a JUnit test:

```java
public class SampleTest {

	@Before
    public void setUp() {
		
		_car = new Car();
	}
    
	@Test
	public void testStartCar() {
		
		_car.start();
		
		assertEquals(true, car.isEngineRunning());
	}
	
	protected Car _car;
}
```

It's often not possible to isolate code under a test from its dependent components. __Mocking__ is an approach to "fake" those dependent components and help to keep the tested code isolated.

Mocking often increases test code complexity and decreases maintainability. In such cases, you should consider using integration testing instead.

Below is an example of a JUnit test using the PowerMock Mockito extension:

```java
@PrepareForTest(
	{
		CarService.class
	}
)
@RunWith(PowerMockRunner.class)

	public class SampleTest {

		@Before
		public void setUp() {
			
			when(carService.getOilLevel(Car.class)).thenReturn(4);
		}
        
		@Test
		public void testStartCar() {
			
			Car car = new Car();
			
			assertTrue(carService.getOilLevel(car) > 0);
		}
		
	@Mock
	CarService carService;
}
```

#### Integration Testing

In integration testing, a component is tested as a whole with all its integrations to other modules and services. Integration testing tries to expose defects in interfaces and interaction between integrated components.

Liferay uses the [Arquillian Framework](http://arquillian.org) and a tailored [Arquillian extension](https://github.com/liferay/liferay-portal/tree/7.2.x/modules/test/arquillian-extension-junit-bridge) for integration testing. Below is an example of what an Arquillian test could look like:

```java
@RunWith(Arquillian.class)
public class BlogsEntryLocalServiceImplTest {

	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule =
		new LiferayIntegrationTestRule();

	@Test
	public void testAddDiscussion() throws Exception {
		ServiceContext serviceContext =
			ServiceContextTestUtil.getServiceContext();

		BlogsEntry blogsEntry = BlogsEntryLocalServiceUtil.addEntry(
			TestPropsValues.getUserId(), StringUtil.randomString(),
			StringUtil.randomString(), new Date(), serviceContext);

		_blogsEntries.add(blogsEntry);

		long initialCommentsCount = CommentManagerUtil.getCommentsCount(
			BlogsEntry.class.getName(), blogsEntry.getEntryId());

		CommentManagerUtil.addComment(
			TestPropsValues.getUserId(), TestPropsValues.getGroupId(),
			BlogsEntry.class.getName(), blogsEntry.getEntryId(),
			StringUtil.randomString(),
			new IdentityServiceContextFunction(serviceContext));

		Assert.assertEquals(
			initialCommentsCount + 1,
			CommentManagerUtil.getCommentsCount(
				BlogsEntry.class.getName(), blogsEntry.getEntryId()));
	}
}
```

#### End-to-End Testing

End-to-end testing, sometimes also called user interface or browser testing, strives to emulate and test the (human) interaction with an application. Probably the most common testing framework in this category is [Selenium](https://www.seleniumhq.org). 

Selenium's core component WebDriver emulates browsers and is also behind [Arquillian's Graphene extension](http://arquillian.org/arquillian-graphene). Below is an example using Arquillian Graphene for Liferay portlet user interface testing. The `testInstallPortlet()` gets the current webpage's source and checks if the portlet is found. The other test, `testAdd()`, tests the simple calculator portlet by setting form values:

```java
@RunAsClient
@RunWith(Arquillian.class)
public class BasicPortletFunctionalTest {

	@Deployment
	public static JavaArchive create() throws Exception {
		...
	}

	@Test
	public void testAdd()
		throws InterruptedException, IOException, PortalException {

		_browser.get(_portlerURL.toExternalForm());
		_firstParameter.clear();
		_firstParameter.sendKeys("2");
		_secondParameter.clear();
		_secondParameter.sendKeys("3");

		_add.click();
		Thread.sleep(5000);
		Assert.assertEquals("5", _result.getText());
	}

	@Test
	public void testInstallPortlet() throws IOException, PortalException {
		_browser.get(_portlerURL.toExternalForm());

		final String bodyText = _browser.getPageSource();

		Assert.assertTrue("The portlet is not well deployed", bodyText.contains("Sample Portlet is working!"));
	}

	@FindBy(css = "button[type=submit]")
	private WebElement _add;

	@Drone
	private WebDriver _browser;

	@FindBy(css = "input[id$='firstParameter']")
	private WebElement _firstParameter;

	@PortalURL("arquillian_sample_portlet")
	private URL _portlerURL;

	@FindBy(css = "span[class='result']")
	private WebElement _result;

	@FindBy(css = "input[id$='secondParameter']")
	private WebElement _secondParameter;

}
```

#### Introducing Arquillian

Arquillian is an integration and functional testing platform for Java. Running tests against a dedicated container, it provides a way to test the Liferay modules comprehensively in a real environment and with real dependencies.

Arquillian can:

* Start and stop the test container
* Create a deployable test archive
* Deploy and un-deploy the test archive
* Enrich the test case with dependency injections
* Execute tests inside the container
* Capture the results

Below is a diagram of an Arquillian test:

<img src="../images/arquillian-flow.png" style="max-height:40%"/>

#### Arquillian Components

The Arquillian framework has four main components:

* Test runners
* Containers
* Test enrichers
* Run modes

The Arquillian __Test Runner__ extends the JUnit Runner class and takes care of running the Arquillian-decorated test class. The Runner is set with the @RunWith annotation:

```java
@RunWith(Arquillian.class)
public class AssignmentLocalServiceTest {
    ...
}
```

The __test container__ is the server runtime where tests are run. The test container should not be referenced directly in the tests. Container types supported by Arquillian are:

* Remote 
* Managed
* Embedded

The embedded container is not supported by the Liferay Arquillian extension.

__Test Enrichers__ are injection resources or service injections into Arquillian test classes:

* @Resource - Java EE resource injections
* @EJB - EJB session bean reference injections
* CDI-supported injections

If you inject Liferay service references in your test classes, just use @Inject instead of @Reference:

```java
@Inject
private AssignmentLocalService _assignmentLocalService
```

> Note that using the `@Inject` annotation requires using the portal integration test framework's [LiferayIntegrationTestRule](https://github.com/liferay/liferay-portal/blob/7.2.x/portal-test-integration/src/com/liferay/portal/test/rule/LiferayIntegrationTestRule.java).

__Run Mode__ defines the run context for the test:

* Container mode (default):
  * Deploy the test to the test container.
  * Execute tests in the test container.
* Client mode:
  * Deploy required components to the test container.
  * Execute tests as a client, outside the container.
  * Suitable for testing web services or user interfaces
  * Is set with the `@RunAsClient` annotation

An example of the Arquillian test class in client-run mode: 

```java
@RunAsClient
@RunWith (Arquillian.class)
public class GradebookPortletTest {
	...
}
```

#### Liferay Arquillian Extension JUnit Bridge

The Arquillian Extension is an extension for Liferay OSGi in-container deployments that takes care of managing the test container, deploying the test bundle, and running the tests.


#### Steps to Implement Liferay Arquillian Integration Tests

Typical steps for setting up Liferay Arquillian tests:

1. Set up the Liferay Workspace Tomcat bundle.
1. Create a Liferay testing module.
1. Declare testing dependencies, using the `testIntegrationCompile` and `testIntegrationRuntime` scopes.
1. Create the test cases.
1. Run tests using the Gradle `testIntegration` task.

<div class="summary-chapter">
<h3>Knowledge Check</h3>
<ul>
	<li>Testing provides ______________________________ about the state of your software.</li>
	<li>Software testing is often divided into two main categories:</li>
		<ul>
			<li>____________________________________________________</li>
			<li>____________________________________________________</li>
		</ul>
	<li>Testing is done in multiple stages:</li>
		<ul>
			<li>____________________________________________________</li>
			<li>____________________________________________________</li>
			<li>____________________________________________________</li>
			<li>____________________________________________________</li>
		</ul>
</ul>
</div>