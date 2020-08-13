# How Adaptive Media Works

Media providers must consider differences between devices (phones, laptops, tablets, etc.) when delivering content: not only their screen size but also their bandwidth and processing capabilities. Users can control image quality and dynamically adjusts uploaded media to best fit the screen being used.

Adaptive Media integrates with Documents and Media, Blogs, and Web Content. It generates a set of images for use on various screens. When the content is accessed, Adaptive Media checks the screen type and resolution and selects appropriate the appropriate image. Adaptive Media comes preinstalled in DXP.

The Adaptive Media app is installed in DXP by default. The following sections describe the Adaptive Media app's modules, and how to prepare Adaptive Media to handle animated GIFs.

```tip::
   Since the Adaptive Media app is installed by default, it's updated via Liferay DXP Fix Packs and Liferay Portal CE GA releases. Using [Liferay Marketplace](https://web.liferay.com/marketplace) to update the app causes an error.
```

To use Adaptive Media, you must first define the resolutions for the images delivered to users' devices. Adaptive Media then generates new images scaled to fit those resolutions, while maintaining the original aspect ratio. See [Adding Image Resolutions](./adding-image-resolutions.md) to learn more about creating new Image Resolutions.

As noted above, you can use the adapted images in various types of content; see [Using Adapted Images in Site Content](./using-adapted-images-in-site-content.md).

To manage the Adaptive Media Image Resolutions, see [Managing Image Resolutions](./managing-image-resolutions.md).

If you have upgraded to the latest DXP version and were using a legacy version that did not have the Adaptive Media app installed, you can migrate existing Documents and Media thumbnails to new Adaptive Media defined Image Resolutions, see [Migrating Documents and Media Thumbnails](./migrating-documents-and-media-thumbnails.md).

## Additional Information

* [Adaptive Media Configuration Reference](./adaptive-media-configuration-reference.md)
* [Adaptive Media Modules Reference](../../developer-guide/adaptive-media-modules-reference.md)
