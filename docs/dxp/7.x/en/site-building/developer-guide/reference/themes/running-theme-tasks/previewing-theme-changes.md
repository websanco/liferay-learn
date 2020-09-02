# Previewing Theme Changes 

1. Navigate to your Theme's root folder and run the `watch` task. This sets up a proxy for your app server and opens it in a new window in the browser. This runs the [`deploy` task](./deploying-themes.md) initially.

    ```bash
    C:\Users\liferay\Desktop\projects\marketing-theme>npm run watch

    > marketing-theme@1.0.0 watch C:\Users\liferay\Desktop\projects\marketing-theme
    > gulp watch

    [16:56:45] Using gulpfile ~\Desktop\projects\marketing-theme\gulpfile.js
    [16:56:45] Starting 'watch'...
    [16:56:45] Starting 'deploy'...
    ...
    [16:56:54] Finished 'deploy' after 8.44 s
    [16:56:54] Starting 'watch:clean'...
    [16:56:54] Finished 'watch:clean' after 1.12 ms
    [16:56:54] Starting 'watch:setup'...
    [16:56:56] Finished 'watch:setup' after 1.64 s

    ---------------------------------------------------
    Watch mode is now active at: http://localhost:9080/
    Proxying: http://localhost:8080
    ---------------------------------------------------
    ```

1. Make a change to your Theme and save the file. The updated files are built, compiled, and copied directly to the proxy port (e.g. `9080`). CSS changes are deployed live, so no page reload is needed.
    
    ```bash
    [16:59:08] Starting 'build:clean'...
    [16:59:09] Finished 'build:clean' after 560 ms
    [16:59:09] Starting 'build:base'...
    [16:59:11] Finished 'build:base' after 2.39 s
    [16:59:11] Starting 'build:src'...
    [16:59:13] Finished 'build:src' after 1.73 s
    [16:59:13] Starting 'build:themelet-src'...
    [16:59:13] Finished 'build:themelet-src' after 1.34 ms
    [16:59:13] Starting 'build:themelet-css-inject'...
    [16:59:13] gulp-inject Nothing to inject into _custom.scss.
    [16:59:13] Finished 'build:themelet-css-inject' after 18 ms
    [16:59:13] Starting 'build:rename-css-dir'...
    [16:59:13] Finished 'build:rename-css-dir' after 14 ms
    [16:59:13] Starting 'build:compile-css'...
    [16:59:13] Starting 'build:compile-lib-sass'...
    [16:59:17] Finished 'build:compile-lib-sass' after 4 s
    [16:59:17] Finished 'build:compile-css' after 4.01 s
    [16:59:17] Starting 'build:move-compiled-css'...
    [16:59:17] Finished 'build:move-compiled-css' after 626 ms
    [16:59:17] Starting 'build:remove-old-css-dir'...
    [16:59:18] Finished 'build:remove-old-css-dir' after 266 ms
    [16:59:18] Starting 'watch:reload'...
    [16:59:18] Finished 'watch:reload' after 1.55 ms
    ```

    ```note::
      Live changes are only viewable on port `9080` (``http://localhost:9080``). Live changes **are not viewable** on your app server (e.g. ``http://localhost:8080``).
    ```

1. Once you're happy with the changes, [re-deploy](./deploying-themes.md) your Theme to apply the changes to your site on your app server.

Great! Now you know how to preview your Theme's changes live before deploying them to production.