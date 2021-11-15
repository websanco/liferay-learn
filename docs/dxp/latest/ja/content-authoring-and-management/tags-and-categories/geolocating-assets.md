# アセットの地理位置情報

位置情報は、アセットがメタデータとして作成された地理座標をアセットに追加します。 この機能は、[Web コンテンツストラクチャー](../web-content/web-content-structures/understanding-web-content-structures.md)、動的データリストフィールド、またはドキュメントとメディアの[ドキュメントタイプ](../documents-and-media/uploading-and-managing/managing-metadata/defining-document-types.md)または[メタデータセット](../documents-and-media/uploading-and-managing/managing-metadata/using-metadata-sets.md)に含めることができるオプションのフィールドとして提供されます。

## Webコンテンツの地理位置情報

Webコンテンツで位置情報を使用するには、位置情報フィールドを含む[ストラクチャーとテンプレート](../web-content/web-content-structures/understanding-web-content-structures.md)を作成する必要があります。

![図1：位置情報フィールドをストラクチャーに追加して、Webコンテンツで位置情報を有効にします。](./geolocating-assets/images/01.png)

1.  上の画像のように、位置情報フィールドを使用してストラクチャーを作成します。

2.  新しいテンプレートを作成し、位置情報フィールドで作成したストラクチャーを選択します。

3.  *[スクリプト]* 見出しまで下にスクロールして、*[フィールド]* セクションを見つけます。 これが*コンテンツ*と*位置情報*のスニペットです。

4.  スニペットをクリックして、テンプレートに追加します。

    ![図2：コンテンツスニペットと位置情報スニペットを追加して、Webコンテンツテンプレートをすばやく作成します。](./geolocating-assets/images/02.png)

5.  完了したら、*[保存]* をクリックします。

Webコンテンツの場所を設定するには、ブラウザと現在地を共有したり、地図のアドレスバーに特定の住所を入力したり、インジケーターをドラッグして地図の任意の場所にドロップしたりすると、住所が自動的に更新され、新しい地点が反映されます。 Webコンテンツが保存されると、その場所がメタデータとしてWebコンテンツに追加されます。

![アドレスバーに現在地を入力したり、インジケーターを特定の場所に移動したり、現在地をブラウザで共有したりできます。](./geolocating-assets/images/15.png)

```{note}
ブラウザの設定によっては、現在地を共有するように設定する必要がある場合があります。
```

## データリストの地理位置情報

動的データリストで位置情報を使用するには、最初に位置情報フィールドを含むデータ定義を作成する必要があります。

### 新しいデータリスト定義の作成

1.  プロダクトメニューを開き、サイトに移動します。

2.  *[Content & Data]* → *[動的データリスト]* の順にクリックします。

    ![サイト管理のDDLに移動します。](./geolocating-assets/images/03.png)

3.  *オプション*（![Options menu](../../images/icon-options.png)）メニューをクリックし、*[データ定義の管理]* を選択します。

    ![[データ定義の管理]に移動して、新しい定義を作成します。](./geolocating-assets/images/04.png)

4.  *追加*（![add](../../images/icon-add.png)）ボタンをクリックして、新しいデータ定義を作成します。

5.  名前、オプションの説明、および親データ定義（ある場合）を入力します。

6.  下にスクロールして、 *[位置情報]* フィールドを、追加する他のフィールドとともにデータ定義に追加します。

    ![新しい定義を作成します。](./geolocating-assets/images/05.png)

7.  完了したら、*[保存]* をクリックします。

データ定義が作成されたので、この定義を使用して新しいデータリストを作成します。

### 新しいデータリストの作成

新しいデータ定義を使用するデータリストを作成するには：

1.  [動的データリスト]画面に戻り、*追加*ボタン（![add](../../images/icon-add.png)）をクリックして新規リストを作成します。

2.  名前とオプションで説明を入力します。

3.  *[Select]* ボタンをクリックして、新しく作成されたデータ定義を選択します。

4.  このフォームにワークフローを含めるかどうかを選択します。

    ![新しいデータリストを作成します。](./geolocating-assets/images/06.png)

5.  完了したら、*[保存]* をクリックします。

データリストが完成したので、 [データリスト表示ポートレット](../../process-automation/forms/dynamic-data-lists/getting-started-with-dynamic-data-lists.md)を使用してデータリストを表示できます。

## ドキュメントとメディアの地理位置情報

ドキュメントとメディアで位置情報を有効にするには、最初に位置情報メタデータを含む[ドキュメントタイプ](../documents-and-media/uploading-and-managing/managing-metadata/defining-document-types.md)を作成する必要があります。 または、[メタデータセット](../documents-and-media/uploading-and-managing/managing-metadata/using-metadata-sets.md)の一部として位置情報メタデータを追加することもできます。 そのメタデータセットを作成したら、それをドキュメントタイプの一部として含めることができます。

### メタデータセットへの位置情報の追加

以下の手順に従ってください。

1.  プロダクトメニューを開き、サイトに移動します。

2.  *[Content & Data]* → *[ドキュメントとメディア]* の順にクリックします。

3.  *[Metadata Sets]* タブをクリックします。

    ![DMの[Metadata Sets]タブに移動します](./geolocating-assets/images/07.png)

4.  *追加*（![add](../../images/icon-add.png)）ボタンをクリックし、名前、オプションの説明、および親メタデータセット（ある場合）を入力します。

5.  下にスクロールして、 *[位置情報]* フィールドを追加し、さらに必要なフィールドも追加します。

    ![[位置情報]フィールドを他のフィールドと一緒にドラッグアンドドロップして、メタデータセットを作成します。](./geolocating-assets/images/08.png)

6.  完了したら、*[保存]* をクリックします。

### ドキュメントタイプに位置情報フィールドを追加する

以下の手順に従ってください。

1.  プロダクトメニューを開き、サイトに移動します。

2.  *[Content & Data]* → *[ドキュメントとメディア]* の順にクリックします。

3.  *[ドキュメントタイプ]* タブをクリックします。

    ![DMの[ドキュメントタイプ]タブに移動します。](./geolocating-assets/images/09.png)

4.  *追加*ボタン（![add](../../images/icon-add.png)）をクリックして、名前とオプションの説明を入力します。

5.  [メインメタデータフィールド]見出しまで下にスクロールし、*[位置情報]* フィールドを追加し、ドキュメントタイプに必要な他のフィールドも追加します。

    ![DMの[ドキュメントタイプ]タブに移動します。](./geolocating-assets/images/10.png)

6.  メタデータセットを使用している場合は、[追加メタデータフィールド]見出しまで下にスクロールし、*[メタデータセットを選択]* ボタンをクリックします。

7.  位置情報メタデータを含むメタデータセットを選択します。

8.  *[保存]* をクリックします。

### ドキュメントへの位置情報の追加

以下の手順に従ってください。

1.  *[ドキュメントとメディア]* タブに移動します。

    ![DMの[ドキュメントとメディア]タブに移動します。](./geolocating-assets/images/11.png)

2.  *追加*ボタン（![add](../../images/icon-add.png)）をクリックして、新しく作成したドキュメントタイプを選択します。

    ![DMの[ドキュメントとメディア]タブに移動します。](./geolocating-assets/images/12.png)

3.  ドキュメントの情報を入力すると、現在地がブラウザから自動的に取得され、ドキュメントに追加されます。

    ![DMの[ドキュメントとメディア]タブに移動します。](./geolocating-assets/images/13.png)

4.  完了したら、*[Publish]* をクリックします。

アセットの位置情報が有効になったら、[アセットパブリッシャー](../../site-building/displaying-content/using-the-asset-publisher-widget/displaying-assets-intro.md)を使用して、地図表示テンプレートを使用してアセットの場所を地図に表示できます。 詳細は、[表示設定の構成](../../site-building/displaying-content/using-the-asset-publisher-widget/configuring-display-settings.md)セクションを確認してください。

![アセットパブリッシャーは、位置情報が設定されたアセットを地図に表示できます。](./geolocating-assets/images/14.png)

## 追加情報

  - [Configuring Asset Auto Tagging](./auto-tagging/configuring-asset-auto-tagging.md)
  - [コンテンツのタグ付けとタグの管理](./tagging-content-and-managing-tags.md)
  - [ドキュメントタイプの定義](../documents-and-media/uploading-and-managing/managing-metadata/defining-document-types.md)
  - [メタデータセットの使用](../documents-and-media/uploading-and-managing/managing-metadata/using-metadata-sets.md)
