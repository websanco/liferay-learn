# ドキュメントとメディアプレビューの設定

デフォルトでは、Liferay DXPは [PDFBox](https://pdfbox.apache.org) を使用して、ドキュメントライブラリに追加されたファイルの[プレビュー](../uploading-and-managing/previewing-files.md)を生成します。 これは、PDFBoxがDXPで配布できる唯一の100％ Javaベースのツールであるためです。

ただし、PDFBoxは限られた数のファイル形式しかサポートしていないため、Liferayは追加のファイル形式をサポートするために外部サービスとの統合も提供します。 これらのサービスを使用すると、より多くのファイルの高品質のファイルプレビューをより迅速に生成できます。

```{important}
プレビューの生成に使用するには、まずLiferay DXPインスタンスを実行しているサーバーにサービスをインストールする必要があります。 
```

Liferay DXPは現在、以下のプログラムとの統合をサポートしています。

* [OpenOffice](http://www.openoffice.org) または [LibreOffice](http://www.libreoffice.org) ：これらのプログラムはサーバーモードで使用でき、サポートされているファイル形式（`.pdf`、`.docx`、`.odt`、`.ppt`、`.odp`など）のサムネイルおよびプレビューを生成することができます。 また、これらを使用してドキュメントを変換し、ブラウザで表示することもできます。 詳細は、[Enabling OpenOffice/LibreOffice Integration](./enabling-openoffice-libreoffice-integration.md)を参照してください。

* [ImageMagick](http://www.imagemagick.org) （ [Ghostscript](http://www.ghostscript.com) も必要です）：これらのプログラムは、画像ファイルの高速で高品質のプレビューと変換を提供します。 詳細は、 [Enabling ImageMagick and Ghostscript](../../../system-administration/using-the-server-administration-panel/configuring-external-services.md#enabling-imagemagick-and-ghostscript) を参照してください。

* [FFmpeg](http://ffmpeg.org/) ：このプログラムは、音声ファイルとビデオファイルのサポートを提供します。 ファイルプレビューの生成に加えて、ブラウザのネイティブHTML 5プレーヤーでマルチメディアを再生できます。 詳細は、 [音声とビデオのプレビュー用にFFmpegを有効にする](./enabling-ffmpeg-for-audio-and-video-previews.md) を参照してください。

   ```{note}
   Liferay 7.3.x以前のバージョンでは、音声とビデオのプレビューの生成に［Xuggler］(http://www.xuggle.com/xuggler)を使用しています。 詳細は、[Enabling Xuggler］(../../../system-administration/using-the-server-administration-panel/configuring-external-services.md#enabling-xuggler)を参照してください。
   ```

これらのツールをインストールして設定すると、ドキュメントとメディアはほとんどのファイル形式のアプリケーションプレビューを提供できます。

<a name="additional-information" />

## 追加情報

* [ファイルのプレビュー](../uploading-and-managing/previewing-files.md)
* [ドキュメントとメディアUIリファレンス](../documents-and-media-ui-reference.md)
* [Enabling OpenOffice/LibreOffice Integration](./enabling-openoffice-libreoffice-integration.md)
