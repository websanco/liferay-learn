# サイトコンテンツでの適応画像の使用

アダプティブメディアはバックグラウンドで動作し、Liferay DXPにアップロードする画像用にさまざまな解像度を作成します。 ブログ、Webコンテンツ、およびコンテンツページの作成者がこれらの画像を使用する場合、アダプティブメディアは対象となる画面サイズに最適な解像度を自動的に選択します。 アダプティブメディアは、ユーザーがコンテンツを表示したときに最新の適用された画像に置き換えられる`data-fileentryid`属性を使用して、コンテンツのHTML内の各適用された画像を識別します。 これにより、アダプティブメディアは、コンテンツがそれらの画像の前に存在していた場合でも、最新の適用された画像をコンテンツに配信できます。

```{note}
   画像に対するアダプティブメディアを無効にする<./managing-image-resolutions.md>`_と、元の画像がブログエントリー、Webコンテンツの記事、およびコンテンツページに表示されます。
```

<a name="including-adapted-images-in-content" />

## 適用された画像をコンテンツに含める

[コンテンツページに画像を追加する](../../../../site-building/creating-pages/building-and-managing-content-pages/configuring-elements-on-content-pages.md)場合、アダプティブメディアは、ユーザーが直接選択した画像とマッピングされた画像に対して機能します。 ブログまたはWebコンテンツに画像をアップロードする場合、アダプティブメディアは、 ［**ブログ画像**］ 、 ［**ドキュメントとメディア**］ 、および ［**アップロード**］ タブから追加された画像でのみ機能します。   さらに、適用された画像はブログエントリーのコンテンツにのみ適用でき、カバー画像には適用できません。 アダプティブメディアは、ドラッグアンドドロップでブログエントリーに追加された画像に対して機能します。画像はブログ画像リポジトリに自動的にアップロードされ、適応されてから、ブログエントリーのコンテンツに含まれます。 これは、HTMLを調べて、画像に`<img>`タグと`data-fileentryid`属性が含まれていることを確認することで確認できます。

Webコンテンツ記事の場合、アダプティブメディアは、ファイルセレクターの［ドキュメントとメディア］タブから追加された画像でのみ機能します。 ブログとは異なり、アダプティブメディアは、ドラッグアンドドロップでWebコンテンツ記事に追加された画像には適用された画像を配信しません。

ブログエントリーとメディアコンテンツ記事の両方で、アダプティブメディアはファイルセレクターの ［**URL**］ タブから追加された画像では機能しません。 これは、画像がURLから直接リンクされているため、アダプティブメディアがコピーする画像ファイルが提供されないためです。

ブログエントリーまたはWebコンテンツ記事のHTMLには、書き込み中に`<img>`タグと`data-fileentryid`属性が表示されます。 コンテンツが表示されると、HTMLは自動的に置き換えられ、次のようになります。

```html
    <picture>

        <source media="(max-width:850px)" srcset="/o/adaptive-media/image/44147/med/photo.jpeg">

        <source media="(max-width:1200px) and (min-width:850px)" srcset="/o/adaptive-media/image/44147/hd/photo.jpeg">

        <source media="(max-width:2000px) and (min-width:1200px)" srcset="/o/adaptive-media/image/44147/ultra-hd/photo.jpeg">

        <img src="/documents/20140/0/photo.jpeg/1992-9143-85d2-f72ec1ff77a0">

    </picture>
```

この例では、解像度がそれぞれ異なる3つの異なる画像を使用しています。 `source`タグは、これらの各画像を定義します。 また、元の画像（`img`）は、適用された画像が利用できない場合のフォールバックとして使用されることに注意してください。

<a name="using-adapted-images-in-structured-web-content" />

## 構造化されたWebコンテンツでの適用された画像の使用

[構造化されたWebコンテンツ](../../../web-content/web-content-structures/creating-structures.md)で適用された画像を使用するには、コンテンツ作成者はWebコンテンツの構造に画像フィールドを手動で含める必要があります。 その後、エディターの左側にある画像フィールドを選択することで、一致するテンプレートでその画像フィールドを参照することができます。 テンプレートに含まれている`Imagecrrf`という名前の画像フィールドのスニペットの例を次に示します。

```markup
    <#if Imagecrrf.getData()?? && Imagecrrf.getData() !="">
      <img data-fileentryid="${Imagecrrf.getAttribute("fileEntryId")}" alt="${Imagecrrf.getAttribute("alt")}" src="${Imagecrrf.getData()}" />
    </#if>
```

このスニペットには、アダプティブメディアが画像を適用された画像に確実に置き換えるための`data-fileentryid`属性が含まれています。 結果のWebコンテンツのHTMLをエディターのコードビューで調べると、次のようなタグが表示されます。

```html
    <img data-fileentryid="37308" src="/documents/20143/0/photo.jpeg/85140258-1c9d-89b8-4e45-d79d5e262318?t=1518425" />
```

`<img>`タグに`data-fileentryid`属性が含まれていることに注目してください アダプティブメディアは、ファイルエントリーIDを使用して、`<img>`要素を各解像度で使用可能な適用された画像を含む`<picture>`要素に自動的に置き換えます（上記の`<picture>`の例を参照）。

<a name="staging-adapted-images" />

## 適用された画像のステージング

アダプティブメディアは、DXPの [コンテンツステージング](/dxp/latest/en/content_authoring_and_management.html) および[エクスポート/インポート](../../../../site-building/building-sites/importing-exporting-pages-and-content.md)機能と完全に統合されています。 アダプティブメディアは、公開時にステージコンテンツに適用された画像を含め、それらの画像を新しい解像度に一致するように更新できます。

同様に、適用された画像を含むコンテンツがエクスポートされると、アダプティブメディアはそれらの画像をLARファイルにエクスポートします。 次に、そのLARファイルをインポートして、そのコンテンツとその適用された画像を復元または転送できます。

アダプティブメディアは、エクスポート/インポートまたはステージングコンテンツの公開中は適用された画像を再生成しません。 パフォーマンスを向上させるために、アダプティブメディアは代わりに既存の適用された画像を再利用します。

<a name="additional-information" />

## 追加情報

* [画像解像度の追加](./adding-image-resolutions.md)
