# サイトエクスペリエンスのパーソナライズの概要

Liferay DXPには、パーソナライズされたエクスペリエンスを理解してサイトのユーザーに提供する強力なツールが付属しています。 パーソナライズされたエクスペリエンスを提供するための最初のステップは、ユーザーを理解する、またはセグメント化することから始めることです。

## セグメンテーション

セグメントビルダーを使用すると、ユーザープロファイルの属性、動作、サイトロールなどで類似したユーザーをグループ化できます。

![さまざまなユーザープロパティ値をチェックして、ユーザーセグメントを作成します。](./introduction-to-personalizing-site-experience/images/01.png)

開始するには、[Creating and Managing a Segment](./segmentation/creating-and-managing-user-segments.md)を参照してください。

``` note::
   ユーザーセグメントは、ロールの割り当てにも使用できます。 詳細については、 `Assigning Roles to User Segments <../../users-and-permissions/roles-and-permissions/advanced-roles-and-permissions/assigning-roles-to-user-segments.md>`_ を参照してください。
```

### Liferay Analytics Cloudを使用したユーザーセグメントの動作の追跡

Liferay DXPインスタンスをLiferay Analytics Cloudと統合して、セグメントを強化します。 Analytics Cloudを使用すると、DXPをSalesforce（tm）およびその他のデータソースと統合して、ユーザーベースをさらに理解する方法を提供できます。

Analytics Cloudとの統合により、機械学習を活用した詳細なデータ分析機能が提供され、DXPサイトのすべてのページに対するユーザーの行動とエンゲージメントを理解するのに役立ちます。 Analytics Cloudを使用すると、サイトのユーザーと訪問者が標準コンテンツとターゲットコンテンツの両方でどのように動作し、どのように対話しているかを確認できます。 詳細については、[Getting Analytics for User Segments](./segmentation/getting-analytics-for-user-segments.md)を参照してください。

![Analytics Cloudを使用したサイトメトリクス。](./introduction-to-personalizing-site-experience/images/05.png)

## パーソナライゼーション

[ユーザーセグメントを作成](./segmentation/creating-and-managing-user-segments.md)したら、ユーザーセグメントに固有のサイトエクスペリエンスの調整を開始できます。 コンテンツページのパーソナライゼーションとコンテンツセットのパーソナライゼーションは、パーソナライズされたユーザーエクスペリエンスを提供するために使用できる2つの方法です。

### コンテンツページ

コンテンツページのパーソナライゼーションは、ページの閲覧者に基づいてページレイアウトとコンテンツを動的に変更します。 ページを表示しているユーザーのユーザーセグメント基準に基づいて、さまざまなテキスト、画像、ウィジェット、さらにはさまざまなレイアウトを提供する[コンテンツページ](../creating-pages/building-and-managing-content-pages/building-content-pages.md)の*エクスペリエンス*を作成できます。 詳細については、[Content Page Personalization](./experience-personalization/content-page-personalization.md)を参照してください。

![ユーザーのさまざまなセグメントに固有のエクスペリエンスを作成できます。](./introduction-to-personalizing-site-experience/images/02.png)

### コンテンツセット
コンテンツセット <!--コンテンツセットドキュメントプレースホルダへのリンク--> は、コンテンツを整理して表示します。 コンテンツセットのパーソナライゼーションでは、ユーザーセグメントに基づいてコンテンツセットを選択できます。 つまり、特定のコンテキストで表示されるコンテンツセットは、ユーザーセグメント基準によって決定されます。 たとえば、コンテンツセットを使用して、「特集」記事をページの上部に表示できます。 次に、デフォルトのコンテンツではなく、より専門的なコンテンツを受け取る必要があるユーザーを含むユーザーセグメントを作成できます。 これらのセグメントには、デフォルトのコンテンツではなく、ユーザーの興味に合わせてパーソナライズされたコンテンツが表示されます。 詳細については、

[Content Set Personalization](./experience-personalization/content-set-personalization.md)を参照してください。

![コンテンツセットをパーソナライズして、特定のユーザーセグメントのアセットを表示できます。](./introduction-to-personalizing-site-experience/images/03.png)

### ユーザーエクスペリエンスのプレビュー

ページの上部にある*シミュレーション*ボタン（![Simulation](../../images/icon-simulation.png)）をクリックし、*[Segments]* の選択からユーザーセグメントを選択して、そのユーザーセグメントのメンバーとしてページをプレビューすることにより、ユーザーがページで体験できるさまざまなエクスペリエンスをプレビューできます。

ユーザーセグメントのパースペクティブを表示すると、そのユーザーセグメントのコンテンツページまたはコンテンツセットのパーソナライゼーションがプレビューされます。

![プレビューパネルからさまざまなエクスペリエンスをプレビューできます。](./introduction-to-personalizing-site-experience/images/04.png)

## 次のステップ

  - [Creating and Managing a Segment](./segmentation/creating-and-managing-user-segments.md)
  - [コンテンツページのパーソナライゼーション](./experience-personalization/content-page-personalization.md)
