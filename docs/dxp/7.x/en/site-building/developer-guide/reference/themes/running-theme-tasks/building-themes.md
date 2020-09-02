# Building Your Theme's Files

1. Navigate to your Theme's root folder and run the `build` task.

    ```bash
    C:\Users\liferay\Desktop\projects\marketing-theme>npm run build
    [11:36:25] Using gulpfile ~\Desktop\projects\marketing-theme\gulpfile.js
    [11:36:25] Starting 'build'...
    [11:36:25] Starting 'build:clean'...
    ...
    [11:36:34] Finished 'build' after 8.58 s
    ```

1. The `build` task generates the base Theme files (in the `build` folder), compiles Sass into CSS, and compresses all Theme files into a `.war` file (in the `dist` folder) that you can deploy to your server. Copy any of these files and folders to your Theme's `src` folder to modify them.
1. [Deploy](./deploying-themes.md) the `war` file to your app server to make it available.

Great! Now you know how to build your Theme.