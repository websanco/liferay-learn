# Using Taglibs in Templates

Liferay's taglibs are accessible when developing templates in FreeMarker. There is no need to instantiate these taglibs within your FreeMarker template; they're already provided for you automatically. You can access these taglibs by indicating the TLD's file name with underscores.

When you're using DXP's template editor, you can find variables on the left side of the template editor. Here's how to place one of the variables onto the template editor:

1. Navigate to your Site's (the default DXP site in this example) _Site Administration_.
1. Click _Content & Data_ &rarr; _Web Content_.
1. Click on the _Templates_ tab.
1. Position your cursor where you want the variable placed.
1. Click the variable name.

If the variable name doesn't give you sufficient information on its functionality, you can hover your pointer over it for a more detailed description.

![You can hover your pointer over a variable for a more detailed description.](./using-taglibs-in-templates/images/01.png)

The interactive template editor is available for the FreeMarker, Velocity, and XSL languages. Depending on the language you select, the variable content changes so you're always adding content in the language you've chosen. Autocomplete can be invoked by typing `*${*` which opens a menu of available variables. The editor inserts the variable into the template editor when it is chosen.

```note::
   The ``utilLocator``, ``objectUtil``, and ``staticUtil`` variables for FreeMarker are disabled by default. These variables are vulnerable to remote code execution and privilege escalation, and should only be enabled for trusted template developers. 
```

After you've saved your template, DXP provides a WebDAV URL and static URL. These values access the XML source of your structure. You can find these URLs by returning to your template after it's been saved and expanding the *Details* section. For more information on WebDAV and the uses of the WebDAV URL, reference the [WebDAV Access](../../documents-and-media/publishing-and-sharing/accessing-documents-with-webdav.md) section.

## Additional Information

* [Embedding Widgets in Templates](./embedding-widgets-in-templates.md)
