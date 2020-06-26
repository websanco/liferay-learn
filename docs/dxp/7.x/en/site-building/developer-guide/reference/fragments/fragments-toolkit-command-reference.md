# Fragments Toolkit Command Reference

The [Fragments Toolkit](../../developing-page-fragments/using-the-fragments-toolkit.md) can connect to your currently running Liferay DXP instance to import and export fragments. You can even have Fragments that you create with the toolkit imported into Portal automatically. These commands are available:

<!-- TODO: The description for `npm run preview` is probably too long, it's overrunning the column width into a new table cell when the site is built. Probably should consider adding an asterisk and adding the version specific info separately after the table. -->

| Command | Description |
| --- | --- |
| `npm run export` | Get collections and fragments from a running server |
| `npm run import` | Send the collections and fragments from your current project to a running server. If your Fragment's configuration JSON (if available) is invalid, the import fails and provides an error message. |
| `npm run import:watch` | Automatically import Collections and Fragments into Portal as they are created or modified |
| `npm run preview` | Preview how a Fragment will look when it's imported. This renders a Fragment on a specified Liferay server without importing it. When changes are made to the fragment while it's previewed, changes are auto reloaded to rapidly display updates. Note, this is available for Liferay DXP
  7.2 SP1+ and Liferay Portal 7.2 GA2+. You must install the [OAuth 2](https://web.liferay.com/marketplace/-/mp/application/109571986) plugin in your portal instance for this command to work properly. |
| `npm run compress` | Create a `.zip` file that can be manually imported into Portal |

```note::
  You can see all of the available tasks inside the ``scripts`` section in the Fragment Collection project's ``package.json``.
```

With these tools at your disposal, you can efficiently manage creating and editing Page Fragments from the command line in your local environment.
