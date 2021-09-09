# Navigation Menu API Basics

Liferay's REST API's provide services for the navigation menu functionality of Liferay. You can create and edit navigation menus with the API. Start by seeing an example of adding a new navigation menu.

## Adding a Navigation Menu

1. Start Liferay DXP. If you don't already have a docker container, use

   ```bash
   docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
   ```

1. Download and unzip [Categories and Vocabulary API Basics](./liferay-p7s4.zip).

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/site-building/site-navigation/developer-guide/liferay-p7s4.zip -O
   ```

   ```bash
   unzip liferay-p7s4.zip
   ```

2. [Find your site's ID](../../../headless-delivery/consuming-apis/consuming-rest-services.md#identify-the-site-containing-the-data). You'll use this in different service calls below.

3. Use the cURL script to add a new navigation menu to your Site. On the command line, navigate to the `curl` folder. Execute the `NavigationMenu_POST_ToSite.sh` script with your site ID as a parameter.

    ```bash
    ./NavigationMenu_POST_ToSite.sh 1234
    ```

    The JSON response shows a new navigation menu has been added:

    ```bash
    "creator" : {
    "additionalName" : "",
    "contentType" : "UserAccount",
    "familyName" : "Test",
    "givenName" : "Test",
    "id" : 20129,
    "name" : "Test Test"
    },
    "dateCreated" : "2021-09-09T21:41:31Z",
    "dateModified" : "2021-09-09T21:41:31Z",
    "id" : 40131,
    "name" : "Foo",
    "navigationMenuItems" : [ ],
    "siteId" : 20125

    ```

4. Go to the Navigation Menus application by navigating to *Administration Menu* &rarr; *Site Builder* &rarr; *Navigation Menus*. See that a new navigation menu has been added.

    ![See that a new vocabulary has been added.](./navigation-menu-api-basics/images/01.png)

5. The REST service can also be called with a Java class. Navigate out of the `curl` folder and into the `java` folder. Compile the source files with the following command:

    ```bash
    javac -classpath .:* *.java
    ```

6. Run the `NavigationMenu_POST_ToSite` class with the following command. Replace the `siteId` value with your site's ID:

    ```bash
    java -classpath .:* -DsiteId=1234 NavigationMenu_POST_ToSite
    ```

## Examine the cURL Command