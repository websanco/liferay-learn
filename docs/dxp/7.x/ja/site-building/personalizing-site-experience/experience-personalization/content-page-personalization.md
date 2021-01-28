# コンテンツページのカスタマイズと管理

これらの手順では、ユーザーセグメントに基づいてコンテンツページのユーザーエクスペリエンスをパーソナライズおよび管理する方法を示しています。

## 新しいコンテンツページエクスペリエンスの作成

1.  既存のコンテンツページに移動するか、[新しいコンテンツページを作成](../../creating-pages/building-and-managing-content-pages/building-content-pages.md)します。

2.  ページ上部の*[Experience]* で*[Default]* をクリックして、エクスペリエンス選択ダイアログを開きます。

    ![現在のエクスペリエンスをクリックして、新しいエクスペリエンスを作成するか、別の既存のエクスペリエンスを選択します。](./content-page-personalization/images/01.png)

3.  *[New Experience]* をクリックします。

4.  名前を付けて、エクスペリエンスを提供したい対象者のユーザーセグメントを選択するか、またはエクスペリエンスの対象者がまだユーザーセグメントで表されていない場合は、新しいユーザーセグメントを作成します。

    ![新しいエクスペリエンスの作成中に新しいセグメントを追加できます。](./content-page-personalization/images/02.png)

5.  ページレイアウトとコンテンツを変更して、選択したユーザーセグメントに表示する情報を表示します。

6.  *[Publish]* をクリックします。

ページの*[Default]* バージョンが、選択したユーザーセグメントのメンバーを除くすべてのユーザーに表示されます。メンバーには、定義されたユーザーセグメントのサイトのバージョンが表示されます。 以下の図の例は、架空の*プレミアムカードの見込み客*のユーザーセグメントに対し新しいエクスペリエンスを作成しています。

![カードの見込み客の最終結果は次のようになります。](./content-page-personalization/images/03.png)

``` note::
  新しいエクスペリエンスを作成すると、作成時に*Default*エクスペリエンスがコピーされます。 *Default*エクスペリエンスにさらに変更を加えても、そのページのエクスペリエンスには影響しません。
```

## コンテンツページのカスタマイズの管理

コンテンツページを編集するとき、*[Experience]* をクリックして、そのページのオプションを管理できます。

![エクスペリエンスの優先度を追加、編集、削除、または変更できます。](./content-page-personalization/images/04.png)

1.  *[Site Administration]* → *[Site Builder]* → *[Pages]* に移動します。
2.  任意のコンテンツページの*アクション*ボタン ![Actions](../../../images/icon-actions.png) → *[Edit]* をクリックします。
3.  エクスペリエンスを管理するには、*[Default]* エクスペリエンスをクリックします。

次の3つのオプションがあります。

![Edit](../../../images/icon-edit.png) エクスペリエンスの名前または選択したユーザーセグメントを変更します。

![削除](../../../images/icon-delete.png) エクスペリエンスを削除します。

![優先度](../../../images/icon-priority.png) エクスペリエンスの優先度を変更します。 ユーザーが複数のエクスペリエンスの基準を満たしている場合、最も高い順序のエクスペリエンスが表示されます。

``` note::
  新しいエクスペリエンスインターフェイスからの新しいセグメントの作成は、Liferay DXP 7.2フィックスパック1+およびLiferay Portal 7.2 CE GA2+で利用できます。
```

## 関連情報

  - [Content Set Personalization](./content-set-personalization.md)
  - [Getting Analytics for User Segments](../segmentation/getting-analytics-for-user-segments.md)
  - [Creating User Segments](../segmentation/creating-and-managing-user-segments.md)
