# 外部ビデオショートカットの作成

> Liferay 7.4以降で利用可能

Liferayのドキュメントとメディアアプリケーションを使用すると、外部プラットフォームでホストされているビデオへの参照を保存できます。 これらの参照を使用して、Liferayアセットまたはサイトページに[ビデオを埋め込む](./embedding-videos-into-liferay-assets-and-pages.md)ことができます。 YouTube、Facebook、Vimeo、Twitchは初めからサポートされていますが、この機能を拡張してカスタムビデオソースをサポートすることもできます。<!--TASK: link to dev article once merged, "\[support custom video sources\](./creating-custom-video-shortcut-providers.md)"-->次の手順に従って、外部ビデオショートカットを作成します。

1. サイトまたはアセットライブラリでドキュメントとメディアアプリケーションに移動します。

   ```note::
      サイトで作成されたショートカットはそのサイトにスコープされますが、アセットライブラリで作成されたショートカットは複数のサイト間で共有できます。 詳細については、`Asset Libraries Overview <../../asset-libraries/asset-libraries-overview.md>`_を参照してください。
   ```

1. *追加*ボタン（![Add Button](../../../images/icon-add.png)）をクリックして、*［External Video Shortcut］*を選択します。

   ![新しい外部ビデオショートカットを追加します。](./creating-external-video-shortcuts/images/01.png)

1. サポートされているプラットフォーム（YouTube、Vimeo、Facebook、またはTwitch）からのビデオURLを入力します。

   YouTubeとVimeoの動画にはURLサムネイルが表示されます。 FacebookとTwitchのビデオでは表示されません。

   ![ビデオのURLを入力します。](./creating-external-video-shortcuts/images/02.png)

1. ショートカットのタイトルを入力します。

   このフィールドは、YouTubeとVimeoの場合は自動入力されます。 FacebookとTwitchは手動入力が必要です。

1. （オプション）ビデオのディスプレイページテンプレートを選択します。

   選択したテンプレートは、一意のフレンドリURLでビデオを表示し、表示ページのレイアウトを定義します。 詳細は、[表示ページテンプレートの作成と管理](../../../site-building/displaying-content/using-display-page-templates/creating-and-managing-display-page-templates.md)を参照してください。

   ![ビデオのディスプレイページテンプレートを選択します。](./creating-external-video-shortcuts/images/03.png)

1. (オプション）[タグとカテゴリー](../../tags-and-categories/organizing-content-with-categories-and-tags.md)を使って、ビデオのショートカットを分類します。

1. （オプション）ビデオショートカットの関連するアセットを選択します。

1. （オプション）ビデオショートカットの権限を構成します。

1. 完了したら、*［Publish］*をクリックします。

   他のドキュメントとメディアアセットと同様に、外部のビデオショートカットを表示および管理できます。

   ![ドキュメントとメディアで外部ビデオショートカットを表示および管理します。](./creating-external-video-shortcuts/images/04.png)

作成した外部ビデオショートカットは、ほとんどのLiferayアセットに埋め込むことができます。 See [Embedding Videos into Liferay Assets and Pages](./embedding-videos-into-liferay-assets-and-pages.md) for more information.

## 追加情報

* [Embedding Videos into Liferay Assets and Pages](./embedding-videos-into-liferay-assets-and-pages.md)
* [Creating Custom Video Shortcut Providers](../developer-guide/creating-video-shortcut-providers.md)
