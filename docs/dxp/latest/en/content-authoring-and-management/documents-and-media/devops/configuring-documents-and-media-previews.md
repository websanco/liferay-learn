# Configuring Documents and Media Previews

By default, Liferay DXP uses [PDFBox](https://pdfbox.apache.org) to generate [previews](../uploading-and-managing/previewing-files.md) for files added to the Document Library. This is because PDFBox is the only 100% Java-based tool that can be distributed with DXP.

However, since PDFBox only supports a limited number of file types, Liferay also provides integration with external services to support additional file types. These services can be used to more quickly generate high quality file previews for more of your files.

```{important}
To be used for generating previews, the service must first be installed on the server running the Liferay DXP instance. 
```

Liferay DXP currently supports integration with the following programs.

* [OpenOffice](http://www.openoffice.org) or [LibreOffice](http://www.libreoffice.org): These programs can be used in server mode to generate thumbnails and previews for supported file types (`.pdf`, `.docx`, `.odt`, `.ppt`, `.odp`, etc.). You can also use them to convert documents and view them in your browser. See [Enabling OpenOffice/LibreOffice Integration](./enabling-openoffice-libreoffice-integration.md) to learn more.

* [ImageMagick](http://www.imagemagick.org) (also requires [Ghostscript](http://www.ghostscript.com)): These programs provide fast, high-quality previews and conversions for image files. See [Enabling ImageMagick and Ghostscript](../../../system-administration/using-the-server-administration-panel/configuring-external-services.md#enabling-imagemagick-and-ghostscript) for more information.

* [FFmpeg](http://ffmpeg.org/): This program provides support for audio and video files. In addition to generating file previews, you can and play multimedia in your browser's native HTML 5 player. See [Enabling FFmpeg for Audio and Video Previews](./enabling-ffmpeg-for-audio-and-video-previews.md) for more information.

   ```{note}
   Liferay 7.3.x and earlier versions use [Xuggler](http://www.xuggle.com/xuggler) for generating audio and video previews. See [Enabling Xuggler](../../../system-administration/using-the-server-administration-panel/configuring-external-services.md#enabling-xuggler) for more information.
   ```

With these tools installed and configured, Documents and Media can provide in application previews of most file types.

## Additional Information

* [Previewing Files](../uploading-and-managing/previewing-files.md)
* [Documents and Media UI Reference](../documents-and-media-ui-reference.md)
* [Enabling OpenOffice/LibreOffice Integration](./enabling-openoffice-libreoffice-integration.md)
