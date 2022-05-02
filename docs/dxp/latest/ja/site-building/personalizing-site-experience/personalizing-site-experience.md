# サイトエクスペリエンスのパーソナライズ

Liferay DXPは、パーソナライズされたエクスペリエンスを理解して提供するための強力なツールを備えています。 パーソナライズされた体験を提供するための最初のステップは、ユーザーの収集、つまりセグメンテーションから始まります。 [*ユーザーセグメント*](./segmentation/creating-and-managing-user-segments.md) には、 [*コレクション*](../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md)が動的に割り当てられます。 ユーザーをコレクションに手動で割り当てる代わりに、ユーザーは共有属性または動作に基づいてセグメントに割り当てられます（詳細は、 [コレクションのパーソナライズ](./experience-personalization/personalizing-collections.md) を参照してください）。

## セグメンテーション

セグメントビルダーを使用すると、ユーザープロファイルの属性、動作、サイトロールなどで類似したユーザーをグループ化できます。

![さまざまなユーザープロパティ値をチェックして、ユーザーセグメントを作成します。](./personalizing-site-experience/images/01.png)

開始するには、[セグメントの作成と管理](./segmentation/creating-and-managing-user-segments.md)を参照してください。

```{note}
ユーザーセグメントは、ロールの割り当てにも使用できます。 詳細は、 [ユーザーセグメントへのロールの割り当て](../../users-and-permissions/roles-and-permissions/assigning-roles-to-user-segments.md) を参照してください。
```

### Liferay Analytics Cloudを使用したユーザーセグメントの動作の追跡

Liferay DXPインスタンスをLiferay Analytics Cloudと統合して、セグメントを強化します。 Analytics Cloudは、DXPをSalesforce（tm）およびその他のデータソースと統合して、ユーザーベースをさらに理解する方法を提供します。

Analytics Cloudとの統合により、機械学習を活用した詳細なデータ分析機能が提供され、DXPサイトのすべてのページに対するユーザーの行動とエンゲージメントを理解するのに役立ちます。 Analytics Cloudを使用すると、ユーザーの行動や、標準コンテンツと対象コンテンツの両方との相互作用を確認できます。 詳細は、 [ユーザーセグメントの分析を取得する](./segmentation/getting-analytics-for-user-segments.md) を参照してください。

![Analytics Cloudを使用したサイトメトリクス。](./personalizing-site-experience/images/05.png)

## パーソナライゼーション

[ユーザーセグメントを作成](./segmentation/creating-and-managing-user-segments.md)したら、ユーザーセグメントに固有のサイトエクスペリエンスの調整を開始できます。 コンテンツページのパーソナライゼーションとコンテンツセットのパーソナライゼーションは、パーソナライズされたユーザーエクスペリエンスを提供するために使用できる2つの方法です。

### コンテンツページ

コンテンツページのパーソナライゼーションを行うと、ページの閲覧者に基づいてページレイアウトとコンテンツが動的に変化します。 ページを表示しているユーザーのユーザーセグメント基準に基づいて、さまざまなテキスト、画像、ウィジェット、さらにはさまざまなレイアウトを提供する[コンテントページ](../creating-pages/using-content-pages.md)の*エクスペリエンス*を作成できます。 詳細は、 [コンテンツページのパーソナライゼーション](./experience-personalization/content-page-personalization.md) を参照してください。

![ユーザーのさまざまなセグメントに固有のエクスペリエンスを作成できます。](./personalizing-site-experience/images/02.png)

### コンテンツセット

[コレクション](../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md) コンテンツを整理して表示します。 [コレクションのパーソナライゼーション](./experience-personalization/personalizing-collections.md) ユーザーセグメントに基づいたコレクションの選択を提供します。 つまり、特定のコンテキストで表示されるコンテンツセットは、 [ユーザーセグメント](./segmentation/creating-and-managing-user-segments.md) の基準によって決定されます。 例えば、コレクションを使って「特集」の記事をページの上部に表示することができます。 次に、デフォルトのコンテンツではなく、より専門的なコンテンツを受け取る必要があるユーザーを含むユーザーセグメントを作成できます。 これらのセグメントには、デフォルトのコンテンツではなく、ユーザーの興味に合わせてパーソナライズされたコンテンツが表示されます。 詳しくは、 [コレクションのパーソナライズ](./experience-personalization/personalizing-collections.md) をご覧ください。

![コンテンツセットをパーソナライズして、特定のユーザーセグメントのアセットを表示できます。](./personalizing-site-experience/images/03.png)

```{note}
コレクションは、Liferay DXP 7.2ではコンテンツセットと呼ばれています。
```

### ユーザーエクスペリエンスのプレビュー

ページの上部にある*シミュレーション*ボタン（![Simulation](../../images/icon-simulation.png)）をクリックし、*［Segments］*の選択からユーザーセグメントを選択して、そのユーザーセグメントのメンバーとしてページをプレビューすることにより、ユーザーがページで体験できるさまざまなエクスペリエンスをプレビューできます。

ユーザーセグメントのパースペクティブを表示すると、そのユーザーセグメントのコンテンツページまたはコンテンツセットのパーソナライゼーションがプレビューされます。

![プレビューパネルからさまざまなエクスペリエンスをプレビューできます。](./personalizing-site-experience/images/04.png)

## 関連情報

- [セグメントの作成と管理](./segmentation/creating-and-managing-user-segments.md)
- [コンテンツページのパーソナライゼーション](./experience-personalization/content-page-personalization.md)
- [エクスペリエンスの創造と管理](./experience-personalization/content-page-personalization.md)
