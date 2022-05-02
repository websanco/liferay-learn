# グリッドフラグメントを使用したレスポンシブレイアウトの構築
<!--TASK: Reconsider article.-->
> 対応可能：Liferay DXP 7.3以降

Liferay DXPではデフォルトで[レスポンシブデザインが使用](./building-a-responsive-site.md)されますが、コンテンツレイアウトをさらに細かく制御したい場合があります。 コンテントページで[グリッドフラグメント](../../creating-pages/page-fragments-and-widgets/using-fragments/using-layout-elements.md)を使用することにより、デスクトップデバイスとモバイルデバイスのさまざまなスクリーンサイズに対してコンテンツレイアウトを正確に制御できます。 [コンテントページを編集する](../../creating-pages/using-content-pages/adding-elements-to-content-pages.md)場合、［デバイス表示］セクション（C）でグリッドフラグメント（A）を使用してコンテンツの外枠を描き、さまざまなターゲットデバイス用にカスタマイズされたレイアウトスタイル（B）を定義できます。 たとえば、1行のモジュール数をカスタマイズしたり、グリッドパッディングオプションをスマートフォンサイズの画面専用にカスタマイズすることが可能です。

![グリッドフラグメントを使用すると、さまざまなスクリーンサイズのレイアウトオプションをカスタマイズできます。](./building-responsive-layouts-with-the-grid-fragment/images/04.png)

次の例を考えてみましょう。 保険会社のWebサイトのサービスセクションに、潜在的な顧客を対象としたサービスの概要を提供します。 このセクションでは、3つのモジュールのグリッドフラグメント（B）内に一連のカードフラグメント（A）を使用して、3つの異なるサービスに関する情報を含めます。

![グリッドフラグメントを他のフラグメントと組み合わせて、コンテンツレイアウトをカスタマイズできます。](./building-responsive-layouts-with-the-grid-fragment/images/01.png)

コンピューターからこの「サービス」コンテンツページにアクセスすると、グリッドレイアウトに期待される結果が表示されます。 ただし、スマートフォンからページにアクセスすると、各カードフラグメントのテキストの量（C）とグリッドのパッディングサイズ（D）の関係で、適切な結果とはなりません。 グリッドフラグメントスタイルを使用すると、各モジュールに表示するカードの数をカスタマイズしたり、さまざまなスクリーンサイズで最高の表示エクスペリエンスが得られるようにスタイルを変更したりできます。

![デフォルトのグリッドレイアウトスタイルは、スマートフォンのスクリーンサイズに最適化されていません。](./building-responsive-layouts-with-the-grid-fragment/images/02.png)

## グリッドフラグメントのレイアウトの変更

1. [コンテントページを開いて編集](../../creating-pages/using-content-pages/adding-elements-to-content-pages.md)します（または新しい[コンテントページ](./../../creating-pages/adding-pages/adding-a-page-to-a-site.md)を作成します）。
1. コンテントページで[グリッドフラグメントを構成](../../creating-pages/page-fragments-and-widgets/using-fragments/using-layout-elements.md)します。
1. 編集サイドバーで、*［Selection］*をクリックし、カスタマイズするグリッドフラグメントをクリックします。
1. 編集ツールバーの［Device Display］セクションで、最適化するスクリーンサイズを選択します。

    ![［Device Display］セクションで、カスタマイズするスクリーンサイズを選択します。](./building-responsive-layouts-with-the-grid-fragment/images/06.png)

1. 編集サイドバーの*［Styles］*列で、スクリーンサイズに適したレイアウトオプションを選択します。  たとえば、*［スマホ（縦）］*レイアウトオプションでは、行ごとに1つのモジュールを設定した方がはるかに効果的です。
1. ターゲットのスクリーンサイズの画像*解像度*を構成する（Liferay DXP 7.4以降で使用可能）か、デフォルトの*［Auto］*オプションのままにして[アダプティブメディア](../../../content-authoring-and-management/documents-and-media/publishing-and-sharing/serving-device-and-screen-optimized-media/how-adaptive-media-works.md)を使用します。

    ![ターゲットスクリーンサイズの画像解像度を選択するか、［Auto］オプションのままにしてアダプティブメディアを使用します。](./building-responsive-layouts-with-the-grid-fragment/images/05.png)

1. *［公開］*をクリックします。
1. 必要に応じて、メインツールバーの*［シミュレーション］*（![Simulation](../../../images/icon-simulation.png)）ボタンをクリックして、ターゲットデバイスでの変更を確認します。

    ![コンテンツページエディタの［Device Display］および［Styles］オプションを使用して、グリッドフラグメントのレイアウトをカスタマイズします。](./building-responsive-layouts-with-the-grid-fragment/images/03.gif)

```{tip}
選択したグリッドフラグメント構成を保存して、他のコンテンツページで再利用できます。 詳細については、 [フラグメント構成の保存](../../creating-pages/page-fragments-and-widgets/using-fragments/saving-fragment-compositions.md) を参照してください。
```

## 追加情報

- [レスポンシブサイトの構築の概要](./building-a-responsive-site.md)
- [フラグメント構成の保存](../../creating-pages/page-fragments-and-widgets/using-fragments/saving-fragment-compositions.md)
- [レイアウト要素の使用](../../creating-pages/page-fragments-and-widgets/using-fragments/using-layout-elements.md)
