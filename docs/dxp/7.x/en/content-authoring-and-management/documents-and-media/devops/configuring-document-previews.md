# Configuring Document Previews

File previews help users browse and find media efficiently. To view a preview of a file, click the file's name in the Document Library. If the file is an image, the image appears. If an app is installed that can render a preview of the file type, a representative image of the file appears (e.g., the opening frame of a video file or a presentation's first slide). If there are no such preview apps for the file, a generic image based on the file type appears.

![Figure 1: File previews let you view and manage a file.](./configuring-document-previews/images/01.png)

## File Preview Apps

Whenever possible, DXP generates previews of documents added to the Document Library. Out of the box, Java-based APIs generate previews. The only tool available that is 100% Java and has a compatible license to be distributed with DXP is [PDFBox](https://pdfbox.apache.org).
A separate thread generates a preview for PDFs when uploaded. This process may last only a few seconds for a small file. The larger the file, the longer it takes.

While PDFBox provides a default implementation of image generation for document previews and thumbnails, you must install and configure additional tools to harness the full power of document previews. These tools include:

* [OpenOffice](http://www.openoffice.org) or [LibreOffice](http://www.libreoffice.org):
    Using one of these in server mode lets you generate thumbnails and previews for supported file types (`.pdf`, `.docx`, `.odt`, `.ppt`, `.odp`, etc.), view documents in your browser, and convert documents.

* [ImageMagick](http://www.imagemagick.org) (also requires [Ghostscript](http://www.ghostscript.com)): Enables faster and higher-quality previews and conversions.

* [Xuggler](http://www.xuggle.com/xuggler): Enables audio and video previews, lets you play audio and video files in your browser, and extracts thumbnails from video files.

After installing these tools, you can configure them in the Control Panel's Server Administration screen.

## Enabling ImageMagick

Follow the steps below to configure ImageMagick:

1. Navigate to the _Global Menu_ &rarr; _Control Panel_.
1. Click _Server Administration_.

    ![Figure 2: Navigate to the Server Administration to install the preview tools.](./configuring-document-previews/images/02.png).

1. Click _External Services_.
1. Enter the GhostScript and ImageMagick `variables` in the _Path_ field.

    ![Figure 3: Enable Ghostscript and ImageMagick.](./configuring-document-previews/images/03.png).

1. Check the _Enabled_ box.
1. Click _Save_ when finished.

## Enabling Xuggler

Follow the steps below to use Xuggler to managing video previews.

1. Navigate to the _Global Menu_ &rarr; _Control Panel_.
1. Click _Server Administration_.
1. Click _External Services_.
1. Scroll down to the bottom.
1. Select the desired `JAR` file based on your operating system.

    ![Figure 4: Install Xuggler.](./configuring-document-previews/images/04.png).

1. Click _Install_.
1. Shut down your application server.
1. Start the application server.
1. Navigate back to the _Server Administration_ &rarr; _External Services_.
1. Verify that the success message displays.

    ![Figure 5: Enable Xuggler once it has been installed.](./configuring-document-previews/images/05.png).

1. Check the _Enabled_ box.
1. Click _Save_ when finished.

With these tools installed and configured, a customized viewer displays Documents and Media content, depending on the content type. For example, you can view a document with a customized viewer that lets you navigate through the document's pages. You can also view and play multimedia documents (audio or video). If the browser supports HTML5, the viewer uses the browser's native player. Otherwise it falls back to a Flash player.

## Additional Information

* [Previewing Files](../uploading-and-managing/previewing-files.md)
* [Documents and Media UI Reference](../documents-and-media-ui-reference.md)
