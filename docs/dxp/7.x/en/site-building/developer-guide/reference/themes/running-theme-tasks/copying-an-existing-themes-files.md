# Copying an Existing Theme's Files

Unlike extending a base Theme, which overlays your `src` files on top of the base Theme on every build, the `kickstart` task is a one-time overlay.

```warning::
  The ``gulp kickstart`` task copies an existing Theme's files into your own, which can potentially overwrite files with the same name. Proceed with caution.
```

1. Navigate to your Theme's root folder and run the `kickstart` task.

    ```bash
    C:\Users\liferay\Desktop\projects\marketing-theme>npm run kickstart

    > marketing-theme@1.0.0 kickstart C:\Users\liferay\Desktop\projects\marketing-theme
    > gulp kickstart

    [16:10:05] Using gulpfile ~\Desktop\projects\liferay-learn-port\liferay-t6s3.zip\t6s3-impl\marketing-theme\gulpfile.js
    [16:10:05] Starting 'kickstart'...
    [16:10:05] Warning: the kickstart task will potentially overwrite files in your src directory
    ? Where would you like to search?
      1) Search globally installed npm modules
      2) Search npm registry (published modules)
      Answer:
    ```

1. Select where to search for the Theme to copy. You can copy files from globally installed Themes or Themes published on the npm registry.

    ```note::
      You can't kickstart the Classic Theme. If you want to use the Classic Theme as a starting point, use the `Classic sub-generator <../../../../../developing-applications/tooling/other-tools/theme-generator.md>`_ instead.
    ```

    ```note::
      To install a Theme globally, run the ``npm link`` command from the Theme's root folder.
    ```

    ```bash
    Answer: 1
    ? Where would you like to search? Search globally installed npm modules
    ...
    ? Select a theme
      1) tranquil-theme
      Answer:
    ```

1. The Theme's files are copied into your own Theme. Add your changes on top of these files.

Congrats! Now you have a head start to developing your Theme.
