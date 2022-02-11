# Liferayアセットとページへのビデオの埋め込み

With Liferay, you can use URLs and [video shortcuts](./creating-external-video-shortcuts.md) to embed videos into Web Content, Knowledge Base articles, Forms, Blog entries, and Site Pages.

## アセットへのビデオの埋め込み

1. 目的のアセットに移動し、*［ビデオ］*ボタン（![Video Button](../../../images/icon-video.png)）をクリックします。

   Webコンテンツ、ナレッジ・ベース、およびフォームの場合、このボタンはツールバーにあります。

   ![ツールバーの［ビデオ］ボタンをクリックします。](./embedding-videos-into-liferay-assets-and-pages/images/01.png)

   ブログの場合は、コンテンツ項目の*追加*ボタン（![Add Button](../../../images/icon-plus.png)）をクリックして、［ビデオ］ボタンにアクセスします。

   ![追加ボタンをクリックして、［ビデオ］ボタンにアクセスします。](./embedding-videos-into-liferay-assets-and-pages/images/02.png)

   ドキュメントとメディアでビデオURLを入力したり、ビデオファイルと外部ビデオショートカットを選択したりするためのウィンドウが開きます。

1. （［(Video URL］タブ）URLを使用してビデオを埋め込むには、*［(Video URL］*タブをクリックし、YouTube、Vimeo、Facebook、またはTwitchのリンクを入力して、*［Add］*をクリックします。

   外部ビデオショートカットとは異なり、この方法で埋め込まれたビデオはドキュメントとメディアに保存されず、後で使用することはできません。

1. （［ドキュメントとメディア］タブ）外部ビデオショートカットを使用してビデオを埋め込むには、*［ドキュメントとメディア］*タブをクリックして、目的のビデオファイルまたは外部ビデオショートカットを選択します。

1. 完了したら、*［公開］*をクリックします。

## ページへのビデオの埋め込み

Liferayは、特にビデオをページに埋め込むための2つのすぐに使えるフラグメントを提供しています。

**Video URL**：ビデオURLフラグメントを使用して、内部ビデオファイルまたは外部のYouTubeビデオを表示します。 URLがフラグメントに追加されると、次の設定を構成できます。

   * **Autoplay：** ページが読み込まれたときにビデオを自動的に再生するかどうかを決定します。

   * **ループ：** 終了後にビデオを再スタートするかどうかを決定します。

   * **Mute：** ビデオをデフォルトでミュートするかどうかを決定します。

   * **Hide Video Controls：**ユーザーがビデオコントロールにアクセスできるかどうかを決定します。

**External Video**：無期限のビデオショートカットを使用して、YouTube、Vimeo、Facebook、およびTwitchのビデオをページに埋め込みます。 ビデオを追加すると、ビデオURLを入力したり、ドキュメントとメディアでビデオと外部ビデオショートカットを選択したりするためのウィンドウが開きます。

## 追加情報

* [Creating External Video Shortcuts](./creating-external-video-shortcuts.md)<!-- * \[Creating Custom Video Shortcut Providers\](./creating-custom-video-shortcut-providers.md) -->
