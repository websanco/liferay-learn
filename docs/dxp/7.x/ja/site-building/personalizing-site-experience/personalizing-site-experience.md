# サイトエクスペリエンスのパーソナライズ

Liferay DXPは、パーソナライズされたエクスペリエンスを理解して提供するための強力なツールを備えています。 パーソナライズされたエクスペリエンスを提供するための最初のステップは、ユーザーを理解する、またはセグメント化することから始めることです。 ユーザーセグメントは、動的に割り当てられたユーザーコレクションです。 ユーザーをコレクションに手動で割り当てる代わりに、ユーザーは共有属性または動作に基づいてセグメントに割り当てられます。

## セグメンテーション

セグメントビルダーを使用すると、ユーザープロファイルの属性、動作、サイトロールなどで類似したユーザーをグループ化できます。

![さまざまなユーザープロパティ値をチェックして、ユーザーセグメントを作成します。](./personalizing-site-experience/images/01.png)

開始するには、[Creating and Managing a Segment](./segmentation/creating-and-managing-user-segments.md)を参照してください。

``` note::
   ユーザーセグメントは、ロールの割り当てにも使用できます。 詳細については、「 `ユーザーセグメントへの役割の割り当て <../../users-and-permissions/roles-and-permissions/assigning-roles-to-user-segments.md>`_ 」を参照してください。
```

### Liferay Analytics Cloudを使用したユーザーセグメントの動作の追跡

Liferay DXPインスタンスをLiferay Analytics Cloudと統合して、セグメントを強化します。 Analytics Cloudは、DXPをSalesforce（tm）およびその他のデータソースと統合して、ユーザーベースをさらに理解する方法を提供します。

Analytics Cloudとの統合により、機械学習を活用した詳細なデータ分析機能が提供され、DXPサイトのすべてのページに対するユーザーの行動とエンゲージメントを理解するのに役立ちます。 Analytics Cloudを使用すると、ユーザーの行動や、標準コンテンツと対象コンテンツの両方との相互作用を確認できます。 詳細については、[Getting Analytics for User Segments](./segmentation/getting-analytics-for-user-segments.md)を参照してください。

![Analytics Cloudを使用したサイトメトリクス。](./personalizing-site-experience/images/05.png)

## パーソナライゼーション

[ユーザーセグメントを作成](./segmentation/creating-and-managing-user-segments.md)したら、ユーザーセグメントに固有のサイトエクスペリエンスの調整を開始できます。 コンテンツページのパーソナライゼーションとコンテンツセットのパーソナライゼーションは、パーソナライズされたユーザーエクスペリエンスを提供するために使用できる2つの方法です。

### コンテンツページ

コンテンツページのパーソナライゼーションは、ページの閲覧者に基づいてページレイアウトとコンテンツを動的に変更します。 ページを表示しているユーザーのユーザーセグメント基準に基づいて、さまざまなテキスト、画像、ウィジェット、さらにはさまざまなレイアウトを提供する[コンテンツページ](../creating-pages/building-and-managing-content-pages/building-content-pages.md)の*エクスペリエンス*を作成できます。 詳細については、[Content Page Personalization](./experience-personalization/content-page-personalization.md)を参照してください。

![ユーザーのさまざまなセグメントに固有のエクスペリエンスを作成できます。](./personalizing-site-experience/images/02.png)

### コンテンツセット
コンテンツセット <!--コンテンツセットドキュメントプレースホルダへのリンク--> は、コンテンツを整理して表示します。 コンテンツセットのパーソナライゼーションでは、ユーザーセグメントに基づいてコンテンツセットを選択できます。 つまり、特定のコンテキストで表示されるコンテンツセットは、ユーザーセグメント基準によって決定されます。 たとえば、コンテンツセットを使用して、「特集」記事をページの上部に表示できます。 次に、デフォルトのコンテンツではなく、より専門的なコンテンツを受け取る必要があるユーザーを含むユーザーセグメントを作成できます。 これらのセグメントには、デフォルトのコンテンツではなく、ユーザーの興味に合わせてパーソナライズされたコンテンツが表示されます。 詳細については、

[Content Set Personalization](./experience-personalization/content-set-personalization.md)を参照してください。

![コンテンツセットをパーソナライズして、特定のユーザーセグメントのアセットを表示できます。](./personalizing-site-experience/images/03.png)

### ユーザーエクスペリエンスのプレビュー

ページの上部にある*シミュレーション*ボタン（![Simulation](../../images/icon-simulation.png)）をクリックし、*[Segments]* の選択からユーザーセグメントを選択して、そのユーザーセグメントのメンバーとしてページをプレビューすることにより、ユーザーがページで体験できるさまざまなエクスペリエンスをプレビューできます。

ユーザーセグメントのパースペクティブを表示すると、そのユーザーセグメントのコンテンツページまたはコンテンツセットのパーソナライゼーションがプレビューされます。

![プレビューパネルからさまざまなエクスペリエンスをプレビューできます。](./personalizing-site-experience/images/04.png)

## 次のステップ

  - [Creating and Managing a Segment](./segmentation/creating-and-managing-user-segments.md)
  - [コンテンツページのパーソナライゼーション](./experience-personalization/content-page-personalization.md)
