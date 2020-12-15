# Introduction to the Recycle Bin

Assets that are deleted are placed in the Recycle Bin. Assets in the Recycle Bin can be restored by users. Assets in the Recycle Bin can expire after a configured period of time, resulting in permanent deletion. By default, the Recycle Bin is enabled instance-wide and can be disabled on a site by site basis.

With the Recycle Bin enabled, the _Move to the Recycle Bin_ action replaces _Delete_ for certain asset types. <!-- What asset types? -->

![The folder is moved to Recycle Bin and not deleted immediately.](./introduction-to-the-recycle-bin/images/01.png)

When viewing the Recycle Bin, users can restore or permanently delete an asset.

![There is an option to delete or restore a file.](./introduction-to-the-recycle-bin/images/02.png)

If the Recycle Bin is disabled on a site, assets that are deleted are permanently deleted and cannot be restored.

![Delete an asset.](./introduction-to-the-recycle-bin/images/03.png)

## Recycle Bin and Staging

If you have a [Staging](../../../site-building/publishing-tools/staging/staging-overview.md) environment enabled for your Site, you have a separate Recycle Bin for both the Staging environment and the Live environment. This prevents staged assets and live assets from mixing.

To move an asset to the Recycle Bin, first switch to the environment containing the asset. Click on Staging or Live.

![Check which environment you are working in. Click on Staging or Live to switch.](./introduction-to-the-recycle-bin/images/04.png)

[Using the Recycle Bin](using-the-recycle-bin.md) has the same functionality whether you are working in a Staging environment or a Live environment.

Note that the Recycle Bin is enabled for different asset types when the Staging environment is initially configured. See [Managing Data and Content Types in Staging](../../../site-building/publishing-tools/staging/managing-data-and-content-types-in-staging.md) to make any changes.

```warning::
   Check your Recycle Bin before enabling or disabling a Staging environment. You may end up losing assets that are already in the Recycle Bin. 
```

## What's Next

* [Using the Recycle Bin](./using-the-recycle-bin.md)
* [Configuring the Recycle Bin](./configuring-the-recycle-bin.md)
