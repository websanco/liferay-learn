# エクスペリエンスの作成と管理

> 一部のエクスペリエンス機能は、Liferay 7.4以降、7.3フィックスパック1以降、および7.2フィックスパック11以降でのみ使用できます。

*エクスペリエンス*を使用して、コンテンツページをさまざまなユーザーグループに合わせてカスタマイズできます。  Liferay DXPエクスペリエンスについては、 [コンテンツページのパーソナライゼーション](./content-page-personalization.md) を参照してください。

## 新しいコンテンツページエクスペリエンスの作成

1. *［サイト管理］* &rarr; *［サイトビルダー］* &rarr; *［Pages］*に移動します。
1. アクション（![Actions](../../../images/icon-actions.png)）メニューをクリックして*［編集］*を選択するか、[新しいコンテントページを作成](../../creating-pages/using-content-pages/building-content-pages.md)します。
1. ページの上部にある*［エクスペリエンス］*で、*［デフォルト］*をクリックしてエクスペリエンスの選択ダイアログを開きます。

1. アクション（![Actions](../../../images/icon-actions.png)）メニューをクリックして*［編集］*を選択するか、[新しいコンテンツページを作成](../../creating-pages/adding-pages/adding-a-page-to-a-site.md)します。

1. ページの上部にある［エクスペリエンス］で、*［デフォルト］*をクリックしてエクスペリエンスの選択ダイアログを開きます。

    ![［エクスペリエンス］をクリックしてエクスペリエンスを選択するか、［新しいエクスペリエンス］をクリックして新しいエクスペリエンスを作成します。](./creating-and-managing-experiences/images/01.png)
1. *［新しいエクスペリエンス］*をクリックします。

1. エクスペリエンスの名前を入力し、ターゲットとするオーディエンスのセグメントを選択するか、[新しいセグメントを作成](../segmentation/creating-and-managing-user-segments.md)します（Liferay DXP 7.2フィックスパック1以降およびLiferay Portal 7.2 CE GA2以降で利用可能）。

    ```{note}
    新しいエクスペリエンスはデフォルトで*Anyone*セグメントに割り当てられ、すべてのWebサイト訪問者を対象としています。
    ```

1. 選択したセグメントに表示する情報とレイアウトを使用して、コンテンツページを編集します。

1. 上（![Up](../../../images/icon-angle-up.png)）および下（![Down](../../../images/icon-angle-down.png)）コントロールを使用して、リスト内のエクスペリエンスを移動させて優先順位を設定します（詳細については、 [Understanding How Experiences Work](./content-page-personalization.md#understanding-how-experiences-work) を参照してください）。
1. *［公開］*をクリックします。

ページのデフォルトバージョンは、選択したセグメントのメンバーを除くすべての人に表示されます。メンバーには、定義されたセグメントのサイトのバージョンが表示されます。

## コンテンツページエクスペリエンスの管理

コンテンツページを編集するとき、［エクスペリエンス］をクリックして、そのページのオプションを管理できます。

![エクスペリエンスの優先度を追加、編集、削除、または変更できます。](./creating-and-managing-experiences/images/04.png)

1. *［サイト管理］* &rarr; *［サイトビルダー］* &rarr; *［Pages］*に移動します。
2. アクション（![Actions](../../../images/icon-actions.png)）メニューをクリックし、*［編集］*を選択します。
3. 管理するエクスペリエンスをクリックします。 ここから以下のことを実行できます。

   - 上（![Up](../../../images/icon-angle-up.png)）および下（![Down](../../../images/icon-angle-down.png)）コントロールを使用して、エクスペリエンスの優先度を設定する。
   - エクスペリエンスの名前または選択したセグメントの編集（![Edit](../../../images/icon-edit.png)）。
   - エクスペリエンスの複製（![Duplicate](../../../images/icon-copy.png)）（Liferay DXP 7.4以降、7.3フィックスパック1以降、および7.2フィックスパック11以降で利用可能）。
   - エクスペリエンスの![削除](../../../images/icon-delete.png)削除（）。

    ```{important}
    エクスペリエンスセレクターでのエクスペリエンスの順序によって、エクスペリエンスの優先順位が決まります。 詳細については、 [コンテンツページのパーソナライゼーション](./content-page-personalization.md) の*Understanding How Experiences Work*を参照してください。
    ```

## 関連情報

- [コンテンツページのパーソナライゼーション](./content-page-personalization.md)
- [コレクションのパーソナライズ](./personalizing-collections.md)
- [ユーザーセグメントの作成と管理](../segmentation/creating-and-managing-user-segments.md)
