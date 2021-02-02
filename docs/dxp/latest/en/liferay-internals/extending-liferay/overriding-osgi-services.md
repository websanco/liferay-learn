# Overriding OSGi Services

Liferay's OSGi container is a dynamic environment in which services can be added, removed, or overridden as needed. This framework registers Liferay components with the OSGi service registry, each with their own availability, ranking, and attributes. Together, these details determine whether components referring to a service bind to it or not.

When attempting to override an OSGi service, follow these steps:

1. Identify the service you want to override, and gather its essential service and reference details.

1. Use these details to create a custom service for overriding the existing service.

1. Reconfigure existing Liferay components to use your custom service (if needed).

The following tutorial uses [sample modules](https://learn.liferay.com/dxp/7.x/en/liferay-internals/extending-liferay/liferay-s1j6.zip) to demonstrate how to override an OSGi service. These modules include an api for defining a new OSGi service type, an initial implementation of that type, and a generic portlet that uses the initial implementation. Also included are a second implementation for overriding the initial one and a config file for reconfiguring the portlet to use the new implementation.

## Deploy Sample Modules for Overriding

Follow these steps to download, build, and deploy `s1j6-api`, `s1j6-able-impl`, and `s1j6-web`:

1. Start a new [Liferay Docker container](../../installation-and-upgrades/installing-liferay/using-liferay-docker-images/docker-container-basics.md).

   ```bash
   docker run -it -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
   ```

1. Download and unzip the example modules.

   ```bash
   curl https://learn.liferay.com/dxp/7.x/en/liferay-internals/extending-liferay/liferay-s1j6.zip -O
   ```

   ```bash
   unzip liferay-s1j6.zip -d liferay-s1j6
   ```

1. Run the following `gradlew` command from the `s1j6-api`, `s1j6-able-impl`, and `s1j6-web` subfolders to build and deploy a JAR file for each module to your new Docker container:

   ```bash
   ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   Each module's JAR is generated in its `build/libs` folder (e.g., `s1j6-api/build/libs/com.acme.s1j6.api-1.0.0.jar`).

   Log messages indicate when Liferay begins processing and successfully starts each module. These Logs also provide each service's bundle id.

   ```
   STARTED com.acme.s1j6.api_1.0.0 [1357]
   STARTED com.acme.s1j6.able.impl_1.0.0 [1358]
   STARTED com.acme.s1j6.web_1.0.0 [1359]
   ```

1. Confirm the modules have successfully deployed via the Gogo Shell.

   ```shell
   lb | grep -i "s1j6"
   ```

   If successful, the output reads as follows:

   ```
   1357|Active     |   15|Acme S1J6 API (1.0.0)|1.0.0
   1358|Active     |   15|Acme S1J6 Able Implementation (1.0.0)|1.0.0
   1359|Active     |   15|Acme S1J6 Web (1.0.0)|1.0.0
   true
   ```

   The provided `api` defines an OSGi service type that is then implemented by the `able.impl` module, which in turn is used by the provided `portlet`.

## Gathering OSGi Service and Reference Details

Once you've identified the service you want to override, use Gogo Shell commands to gather the following details:

* **Service Type**: Your custom OSGi service must implement the service type as the component you want to override.

* **Service's Class Name**: You can invoke the existing service with your custom service using its full class name.

* **Reference Configuration Details**: A service's reference configuration details determine its conditions for adopting another service and whether it needs reconfiguration to use new services or not. Collect the following details for service reconfiguration, if necessary:

  * **Component Name**: the full name of the component referencing the service you're overriding

  * **Reference Name**: the `Reference` value

  * **Reference Policy**: whether the reference is `static` or `dynamic`

  * **Reference Policy Option**: whether the reference is `greedy` or `reluctant`

  * **Cardinality**: the number of service instances to which the reference can and must bind

The following example runs the `scr:info` Gogo Shell command for the sample `portlet` to gather details for overriding `able.impl`.

```shell
scr:info com.acme.s1j6.web.internal.portlet.S1J6Portlet
```

```
Component Description: com.acme.s1j6.web.internal.portlet.S1J6Portlet
=====================================================================
Class:         com.acme.s1j6.web.internal.portlet.S1J6Portlet
...

Component Configuration Id: 8131
--------------------------------
...
References:   (total 1)
  - _s1J6: com.acme.s1j6.S1J6 SATISFIED 1..1 static
    target=(*) scope=bundle (1 binding):
    * Bound to [4516] from bundle 1358 (com.acme.s1j6.able.impl:1.0.0)
```

In the above output, `S1J6Portlet` has an `_s1J6` field that references and is bound to a component of the `S1J6` service type in the `com.acme.s1j6.able.impl` bundle. Searching this bundle's id in the Gogo Shell by running `bundle 1358` reveals the full service class name of the component used by the portlet: `com.acme.s1j6.able.internal.S1J6AbleImpl`.

Finally, the full reference configuration details for `S1J6Portlet` are as follows:

* **Component Name**: `com.acme.s1j6.web.internal.portlet.S1J6Portlet`

* **Reference Name**: `_s1J6`

* **Reference Policy**: `static` (default)

* **Reference Policy Option**: `reluctant` (default)

* **Cardinality**: mandatory and unary (i.e., `1..1`)

Together, these details indicate the portlet will require reconfiguration to use a new `impl` in place of `S1J6AbleImpl`.

After gathering the requisite service and reference details, you can use them to override an existing service.

## Creating an OSGi Service with the Gathered Details

Follow these steps to create an OSGi service for overriding an existing service:

1. Implement the same service type as the target OSGi service.

2. Add the `@Component` annotation and `service` attribute to make your class a Declarative Service (DS) component, which registers your class as an OSGi service available in the OSGi service registry.

3. Include the `service.ranking:Integer` component `property` to rank your service higher than existing services.

4. Override the service type's methods.

5. (Optional) Reference the existing service's `component.name` to invoke it.

When ready, deploy the custom service to your Liferay instance to register it with Liferay's OSGi runtime framework.

The sample `S1J6BakerImpl` module is provided to override `S1J6AbleImpl`.

```java
@Component(property = "service.ranking:Integer=100", service = S1J6.class)
public class S1J6BakerImpl implements S1J6 {

    @Override
    public String doSomething() {
        StringBuilder sb = new StringBuilder();

        sb.append(
            "S1J6BakerImpl, which delegates to " +
                _defaultService.doSomething());

        return sb.toString();
    }

    @Reference(
        target = "(component.name=com.acme.s1j6.able.internal.S1J6AbleImpl)",
        unbind = "-"
    )
    private S1J6 _defaultService;

} 
```

Here, `S1J6BakerImpl` implements the same service type as `S1J6AbleImpl` (i.e., `S1J6`) and includes the necessary `@Component` annotation, `service` attribute, and `service.ranking` property. It also references the existing service (i.e., `component.name=com.acme.s1j6.able.internal.S1J6AbleImpl`) and delegates to it as part of overriding the interface's method.

However, getting components to adopt your custom service immediately can require reconfiguring their references.

## Reconfiguring Other Components to Use Your New Service

Together, a service's [Reference Policy](https://docs.osgi.org/javadoc/r5/enterprise/org/osgi/service/component/annotations/ReferencePolicy.html) (i.e., `static` or `dynamic`), [Reference Policy Option](https://docs.osgi.org/javadoc/r5/enterprise/org/osgi/service/component/annotations/ReferencePolicyOption.html) (i.e., `reluctant` or `greedy`) and [Cardinality](https://docs.osgi.org/specification/osgi.cmpn/7.0.0/service.component.html#service.component-reference.cardinality) (i.e., unary or multiple, and optional or mandatory) determine a component's conditions for adopting new services. See the above links for detailed explanations of each category.

In cases where the reference's policy option is `greedy`, the reference does not need to be reconfigured to adopt your new service, provided its ranking is higher than the service you want to override.

However, if the policy is `static` and its policy option is `reluctant`, the referencing service requires reconfiguration.

Follow these steps:

1. Create a system configuration file named after the referencing component. Follow the name convention `[component].config`. <!--Link to Understanding System Configuration Files once ported-->

1. Add configuration information to the file using the following format:

   ```
   [reference].target=[filter]\=[target]
   ```

   Replace `[reference]` with the reference's name (e.g., _`s1J6`).

   Replace `[filter]` with the desired filter (e.g., `component.name`).

   Replace `[target]` with the target component you want your service to use (e.g., `com.acme.s1j6.baker.internal.S1J6BakerImpl`).

1. Optionally, append a `cardinality.minimum` entry to the reference name to determine the number of services the reference can use. Ensure it follows this format:

   ```
   [reference].cardinality.minimum=[int]
   ```

Once your configuration file is prepared, deploy it to your Liferay instance to ensure your new service is used in place of the old.

The root folder of the sample project includes a configuration file for reconfiguring the `portlet` service to use `baker.impl` in place of `able.impl`. The file's name is `com.acme.m1t1.web.internal.portlet.S1J6Portlet.config`, and it contains the following script:

```
_s1J6.target="(component.name\=com.acme.s1j6.baker.internal.S1J6BakerImpl)" 
```

## Deploy the Overriding Module and Config File

Follow these steps to deploy the second `impl` and system `config` file to override :

1. Open the `s1j6-baker-impl` folder in your console.

1. Run the following `gradlew` command to build and deploy a JAR file for the module to your most latest Docker container:

   ```bash
   ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

1. Deploy the system configuration file to ensure the deployed portlet uses the `baker.impl` component to override and delegate to `able.impl`.

   ```bash
   docker cp com.acme.s1j6.web.internal.portlet.S1J6Portlet.config $(docker ps -lq):/opt/liferay/osgi/configs
   ```

1. Confirm both `baker.impl` and the `config` file have successfully deployed to your instance via the Gogo Shell.

   ```shell
   scr:info com.acme.s1j6.web.internal.portlet.S1J6Portlet
   ```

   If successful, the output should indicate `S1J6Portlet` is now bound to a component in the `com.acme.s1j6.baker.impl` bundle.

   ```
   References:   (total 1)
   - _s1J6: com.acme.s1j6.S1J6 SATISFIED 1..1 static
      target=(*) scope=bundle (1 binding):
      * Bound to [4518] from bundle 1360 (com.acme.s1j6.baker.impl:1.0.0)
   ```

## Additional Information

* [Module Projects](../fundamentals/module-projects.md)
* [Using an OSGi Service](../fundamentals/using-an-osgi-service.md)
* [APIs as OSGi Services](../fundamentals/apis-as-osgi-services.md)
