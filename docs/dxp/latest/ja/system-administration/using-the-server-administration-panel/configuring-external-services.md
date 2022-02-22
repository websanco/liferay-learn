# 外部サービスの構成

Liferayは、ファイルの変換やファイルのプレビューを生成するための外部サービスとの統合を提供します。 これらのうち3つのサービスは、サーバー管理の設定で有効にすることができます。 [ImageMagick](https://www.imagemagick.org/script/index.php) 、 [Ghostscript](https://www.ghostscript.com/) 、および [Xuggler](http://www.xuggle.com/xuggler/) です。

```{important}
Liferay 7.3.xでは、Xugglerの統合は非推奨となっています。 ユーザーはLiferayのFFmpegインテグレーションを代替として使用することをお勧めします。 詳しくは [FFmpeg for Audio and Video Previewsを有効にする](../../content-authoring-and-management/documents-and-media/devops/enabling-ffmpeg-for-audio-and-video-previews.md) をご覧ください。
```

![［外部サービス］タブからImageMagick、Ghostscript、およびXugglerを有効にします。](./configuring-external-services/images/01.png)

各外部サービスをLiferayインスタンスで有効にするには、サーバーにインストールする必要があります。 Liferay DXPでは古いバージョンが正しく動作しない可能性があるため、サービスをインストールするときは、オペレーティングシステムに最新の安定バージョンを使用してください。

これらのサービスをインストールしたら、コントロールパネルの［サーバー管理］ページでLiferayインスタンスがこれらのサービスを使用するように設定します。

```{note}
Liferay 7.1では、OpenOffice/LibreOfficeは、サーバー管理やポータルのプロパティではなく、OSGi Configuration Adminで設定されます。 詳しくは [OpenOffice/LibreOfficeの統合を可能にする](./../content-authoring-and-management/documents-and-media/devops/enabling-openoffice-libreoffice-integration.md) をご覧ください。
```

<a name="imagemagickとghostscriptを有効にする" />

## ImageMagickとGhostscriptを有効にする

デフォルトでは、ドキュメントやメディアは使用しています [PDFBox](https://pdfbox.apache.org/) プレビューを生成します。 [ImageMagickの](https://www.imagemagick.org/script/index.php) 及び [のGhostscript](https://www.ghostscript.com/) 高速かつ高品質のプレビューやコンバージョン提供し、複数の画像ファイルの種類をサポートします。 動作させるには、両方のサービスを一緒にインストールして有効にする必要があります。

```{note}
お使いのOSによっては、これらのサービスがすでにインストールされている場合があります。 Linuxをお使いの方は、すでに両方ともインストールされていると思います。 ただし、Windowsにはインストールされていない可能性が高く、macOSにはインストールされている可能性があります。
```

ImageMagickとGhostscriptの両方がサーバーにインストールされたら、以下の手順でLiferayインスタンスのこれらのサービスを有効にします。

1. **グローバルメニュー**（![Global Menu](../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ &rarr; **コンフィギュ** &rarr; ［**サーバの管理**］ へ行きます。

1. ［**外部サービス**］ タブをクリックします。

1. ImageMagickとGhostscriptの **有効** をチェックします。

1. ImageMagickおよびGhostscript実行可能ファイルへのパスが正しいことを確認します。

1. リソース制限を構成します。

1. 完了したら、 ［**保存**］ をクリックします。

<a name="xugglerを有効にする" />

## Xugglerを有効にする

デフォルトでは、［ドキュメントとメディア］では、オーディオファイルやビデオファイルのプレビューは生成されません。 LiferayのXuggler統合を使用して、これらのファイルを変換し、プレビューを生成することができます。 Xugglerがまだサーバーにインストールされていない場合は、［サーバー管理］の［外部サービス］タブからXugglerをインストールできます。

```{tip}
Xugglerは、Linuxではglibcバージョン2.6以降が必要です。
```

以下の手順で、LiferayインスタンスにXugglerをインストールして有効にします。

1. ［**グローバルメニュー**］（![Global Menu](../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ &rarr; ［**設定**］ &rarr; ［**サーバの管理**］ へ行きます。

1. ［**外部サービス**］ タブをクリックします。

1. Xugglerの*有効化をチェックします。</p>

   Xugglerがまだインストールされていない場合は、インストールするように促されます。 OSに適したJARを選択し、 ［**インストール**］ をクリックします。 その後、サーバーを再起動すると変更が反映されます。 インストールが完了したら、 ［**外部サービス**］ タブに戻り、Xugglerを有効にすることができます。

   ![Xugglerがインストールされていない場合は、インストールするように促されます。](./configuring-external-services/images/02.png)</li>

1

［**保存**］ をクリックします。</ol>

<a name="portal-extproperties-ファイルを使用して外部サービスを有効にする" />

## `portal-ext.properties` ファイルを使用して外部サービスを有効にする。

コントロールパネルに加えて、`portal-ext.properties` ファイルを使用してこれらの外部サービスを有効にできます。

### ImageMagickとGhostscriptを有効にする

`portal-ext.properties`を使用してImageMagickとGhostscriptを有効にするには、 `imagemagick.enabled` と `imagemagick.global.search.path` プロパティをファイルに追加してください。 検索パスがImageMagickとGhostscriptの実行ファイルのあるディレクトリを指していることを確認してください。 macOSまたはUnix環境でGhostscriptが使用するフォントのパスを構成する必要がある場合もあります。

```properties
imagemagick.enabled=true
imagemagick.global.search.path[apple]=/opt/local/bin:/opt/local/share/ghostscript/fonts:/opt/local/share/fonts/urw-fonts
imagemagick.global.search.path[unix]=/usr/local/bin:/usr/local/share/ghostscript/fonts:/usr/local/share/fonts/urw-fonts
imagemagick.global.search.path[windows]=C:\\Program Files\\ImageMagick
```

### Xugglerを有効にする

`portal-ext.properties` ファイルを使用してXugglerを有効にするには、そのファイルに以下のコードを追加します。

```properties
xuggler.enabled=true
```

アプリケーションサーバーを再起動して、Xugglerの有効化を完了します。

<a name="追加情報" />

## 追加情報

* [ドキュメントとメディアプレビューの設定](../../../content-authoring-and-management/documents-and-media/devops/configuring-documents-and-media-previews.md)
* [音声とビデオのプレビュー用にFFmpegを有効にする](../../../content-authoring-and-management/documents-and-media/devops/enabling-ffmpeg-for-audio-and-video-previews.md)
