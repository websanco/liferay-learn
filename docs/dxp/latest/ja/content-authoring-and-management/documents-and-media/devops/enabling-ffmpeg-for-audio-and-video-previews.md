# 音声とビデオのプレビュー用にFFmpegを有効にする

ドキュメントとメディアは、音声ファイルおよびビデオファイルのプレビューを生成するための [FFmpeg](http://ffmpeg.org/) マルチメディアフレームワークとの統合を提供します。 この統合を使用するには、最初にサーバーにFFmpegをインストールする必要があります。 クラスター環境で作業している場合は、FFmpegを各ノードにインストールする必要があります。

```{note}
以前、LiferayはXugglerを使用してビデオとオーディオのプレビューを生成していました。 しかし、Xugglerライブラリは現在メンテナンスされていません。 Liferay 7.4以降、プレビューの生成にはFFmpegを使用することをお勧めします。
```

インストールしたら、次の手順に従って、FFmpegを使用するようにドキュメントとメディアを設定します。

1. **グローバルメニュー**（![Global Menu](../../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ &rarr; ［**システム設定**］ &rarr; ［**ドキュメントとメディア**］ に移動します。

1. ［**FFMPEG Audio Converter**］ に移動し、 ［**Enable**］ をオンにして、 ［**アップデート**］ をクリックします。

1. ［**FFMPEG Video Converter**］ に移動し、 ［**Enable**］ をオンにして、 ［**アップデート**］ をクリックします。

   ![［FFMPEG Audio Converter］と［FFMPEG Video Converter］の両方を有効にします。](./enabling-ffmpeg-for-audio-and-video-previews/images/01.png)

   FFmpegがシステムに正しくインストールされていれば、［アップデート］をクリックした後に成功メッセージが表示されます。 それ以外の場合、エラーメッセージが表示されます。

   ![FFmpegがインストールされていない場合、エラーメッセージが表示されます。](./enabling-ffmpeg-for-audio-and-video-previews/images/02.png)

設定が完了すると、アップロードまたはアップデート時に音声ファイルとビデオファイルの音声とビデオのプレビューが自動的に生成されます。

<a name="additional-information" />

## 追加情報

* [ファイルのプレビュー](../uploading-and-managing/previewing-files.md)
* [ドキュメントとメディアプレビューの設定](./configuring-documents-and-media-previews.md)
