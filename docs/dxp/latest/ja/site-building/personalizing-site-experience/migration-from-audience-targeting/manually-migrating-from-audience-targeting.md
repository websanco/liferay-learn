# オーディエンスターゲティングからの手動移行

オーディエンスターゲティングのユーザーセグメントとLiferay 7.2以降のセグメントは類似しているため、アップグレード中にほとんどの構成が自動的にLiferayセグメンテーションに転送されます（詳細については、 [ユーザーセグメントの移行](./migrating-user-segments.md) を参照してください）。 ただし、一部のオーディエンスターゲティングルールには、Liferay 7.2以降のセグメンテーションに直接相当するものがありません。 ここでは、各ルールタイプに対し推奨される解決策を確認できます。

## ユーザー属性ルール

性別や年齢などの一部のユーザー属性は、Liferay 7.2以降で直接相当するものがありません。 Facebookなどの外部ソースから取得したユーザー属性にも代替はありません。 これらを置き換えるには、 [カスタムユーザーフィールド](../../../users-and-permissions/users/adding-custom-fields-to-users.md)を作成し、それを使用して新しいセグメントを定義する必要があります。

## セッションルール

直接相当するものがないセッション属性の場合は、現在のURLのURLフィールドまたは以前にアクセスしたサイトのURLを基準として使用します。 より高度なセッション追跡が必要な場合は、Cookieを使用してください。

## 動作ルール

Liferay 7.2以降では、動作ベースのルールはAnalytics Cloudを使用して管理されます。 詳細は、 [Analytics Cloudのドキュメント](https://learn.liferay.com/analytics-cloud/latest/ja/people/segments/segments.html) を参照してください。

## カスタムルールの移行

Liferay 7.2以降に移行する前に、新しいLiferayセグメンテーション機能を考慮して、オーディエンスターゲティングのカスタムルールを評価してください。 まず、[Liferayセグメントのプロパティ](../segmentation/segments-editor-ui-reference.md)がオーディエンスターゲティングのカスタムルールをどのように置き換えることができるかを確認します。

ルールを完全に再実装する必要がある場合は、 [セグメンテーション開発の概要 (近日公開！)](../developer-guide/introduction-to-segmentation-development.md) の情報に従ってください。

## 表示プロパティの移行

オーディエンスターゲティングでは、ユーザーセグメントのコンテンツ表示またはアセットパブリッシャーのパーソナライズを使用して、パーソナライズされたコンテンツを表示できます。 オーディエンスターゲティングを使用してコンテンツをパーソナライズするために使用する方法によって、Liferayセグメンテーションでこのコンテンツをパーソナライズする方法が決まります。

| オーディエンスターゲティング方式    | Liferayセグメンテーション方式                                                                                                                                     |
|:------------------- |:------------------------------------------------------------------------------------------------------------------------------------------------------ |
| ユーザーセグメントのコンテンツ表示   | [手動コンテンツセットまたはコレクション](../../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md#creating-a-manual-collection) |
| アセットパブリッシャーのパーソナライズ | [動的コンテンツセット](../../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md#creating-a-dynamic-collection)         |

```{note}
Liferay 7.2のユーザーの場合、コレクションは [コンテンツ セット](../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md#liferay-dxp-7-2) と呼ばれます。 
```

コンテンツセットまたはコレクションのどちらを使用する場合でも、[パーソナライズされたバリエーション](../experience-personalization/personalizing-collections.md)を使用してコンテンツをパーソナライズできます。

さらに、[コンテントページのパーソナライズ](../../../site-building/personalizing-site-experience/experience-personalization/content-page-personalization.md)機能を使用すれば、以前はオーディエンスターゲティングのいずれかの方式で解決していたユースケースを実現できる可能性があります。

## 関連情報

- [Create a Custom User Field](../../../users-and-permissions/users/adding-custom-fields-to-users.md)
- [セグメントエディターUIリファレンス](../segmentation/segments-editor-ui-reference.md)
- [コレクションの作成](../../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md)
- [Analytics Cloudのセグメント](https://learn.liferay.com/analytics-cloud/latest/ja/people/segments/segments.html)
