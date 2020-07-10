# Updating Your Theme's App Server

1. Navigate to your Theme's root folder and run the `init` task.

    ```bash
    C:\Users\liferay\Desktop\projects\marketing-theme>npm run init
    [12:21:26] Using gulpfile ~\Desktop\projects\marketing-theme\gulpfile.js
    [12:29:29] Starting 'init'...
    [12:29:29] Starting 'plugin:init'...
    ```

1. Enter the updated path to your app server and site.

    ```bash
    ? Select your deployment strategy Local App Server
    ? Enter the path to your app server directory: C:\Users\liferay\opt\Liferay\bundles\liferay-ce-portal-tomcat-7.
    3.2-ga3\liferay-ce-portal-7.3.2-ga3\tomcat-9.0.33
    ? Enter the url to your production or development site: http://localhost:8080
    [12:30:51] Finished 'plugin:init' after 55 s
    [12:30:51] Finished 'init' after 55 s
    ```

1. Your Theme's `liferay-theme.json` file contains the updated server configuration information:

    ```json
    {
      "LiferayTheme": {
        "appServerPath": "C:/Users/liferay/opt/Liferay/bundles/liferay-ce-portal-tomcat-7.3.2-ga3/liferay-ce-portal-7.3.2-ga3/tomcat-9.0.33",
        "deployPath": "C:/Users/liferay/opt/Liferay/bundles/liferay-ce-portal-tomcat-7.3.2-ga3/liferay-ce-portal-7.3.2-ga3/deploy",
        "deployed": false,
        "deploymentStrategy": "LocalAppServer",
        "url": "http://localhost:8080"
      }
    }
    ```

Awesome! Now you can [deploy your Theme](./deploying-themes.md) to the proper server.