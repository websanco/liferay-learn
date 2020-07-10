# Listing Your Theme's Extensions

1. Navigate to your Theme's root folder.
1. Run the `status` task to print your Theme's current extensions to the command line.

    ```bash
    C:\Users\liferay\Desktop\projects\marketing-theme>npm run status

    > marketing-theme@1.0.0 status C:\Users\liferay\Desktop\projects\marketing-theme
    > gulp status

    [16:53:07] Using gulpfile ~\Desktop\projects\marketing-theme\gulpfile.js
    [16:53:07] Starting 'status'...
    Base theme: styled

    [16:53:07] Finished 'status' after 4.29 ms
    ```

Your Theme's current extensions are also found under the `baseTheme` and `themeletDependencies` headings in your Theme's `package.json`.

```bash
"liferayTheme": {
  "baseTheme": "styled",
  "fontAwesome": false,
  "screenshot": "",
  "templateLanguage": "ftl",
  "version": "7.3"
},
```

Great! Now you know how to check your Theme's extensions.