# Deploying Themes

1. Navigate to your Theme's root folder and run the `deploy` task. The `deploy` task initially runs the [`build`](./building-themes.md) task: 

    ```bash
    C:\Users\liferay\Desktop\projects\marketing-theme>npm run deploy

    > marketing-theme@1.0.0 deploy C:\Users\liferay\Desktop\projects\marketing-theme
    > gulp deploy

    [16:40:44] Using gulpfile ~\Desktop\projects\liferay-learn-port\liferay-t6s3.zip\t6s3-impl\marketing-theme\gulpfile.js
    [16:40:44] Starting 'deploy'...
    [16:40:44] Starting 'build'...
    ...
    [16:40:52] Finished 'build' after 8.15 s
    [16:40:52] Starting 'deploy:war'...
    [16:40:52] Starting 'plugin:deploy'...
    [16:40:52] Deploying to C:\Users\liferay\opt\Liferay\bundles\liferay-ce-portal-tomcat-7.3.2-ga3\liferay-ce-portal-7.3.2-ga3\deploy
    [16:40:52] Finished 'plugin:deploy' after 47 ms
    [16:40:52] Finished 'deploy:war' after 49 ms
    [16:40:52] Finished 'deploy' after 8.2 s
    ```

    ```note::
      If you're running the `Felix Gogo shell <../../../../../../liferay-internals/fundamentals/using-felix-gogo-shell.md>`_ , you can also deploy your Theme using the `gulp deploy:gogo` command.
    ```

1. Your server's log displays that the OSGi bundle is started.

    ```bash
    2020-07-09 20:48:51.851 INFO  [Refresh Thread: Equinox Container: c6476131-cff1-46ee-980b-1ece70fd8fb4][ThemeHotDeployListener:108] 1 theme for marketing-theme is available for use
    2020-07-09 20:48:51.900 INFO  [Refresh Thread: Equinox Container: c6476131-cff1-46ee-980b-1ece70fd8fb4][BundleStartStopLogger:46] STARTED marketing-theme_1.0.0 [1157]
    ```

Great! Now you know how to deploy your Theme.