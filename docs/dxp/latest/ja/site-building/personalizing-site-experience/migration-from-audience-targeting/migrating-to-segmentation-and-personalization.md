# セグメンテーションとパーソナライゼーションへの移行

Liferay 7.2からは、セグメンテーションとパーソナライゼーションの機能がコア製品に統合されました。 オーディエンスターゲティングのユーザーは、オーディエンスターゲティングセグメントをコアのLiferayセグメンテーションに移行する必要があります。

この移行には3つのステップがあります。

1. Liferay DXP 7.2以降にアップグレードします。
1. カスタムルールを移行し、プロパティを表示します。
1. 動作ベースの機能を移行します。

まず、[アップグレードガイド](../../../installation-and-upgrades/upgrading-liferay/upgrade-basics.md)に従ってLiferay DXPの最新バージョンにアップグレードします。 オーディエンスターゲティング構成のほとんどは、アップグレード中にLiferayセグメンテーションに自動的に転送されます。

次に、次の情報を考慮して、オーディエンスターゲティングのカスタムルールを移行します。

- Liferay[セグメンテーション機能](../segmentation/creating-and-managing-user-segments.md)を考慮して、オーディエンスターゲティングのカスタムルールの必要性を再評価します。
  - 一部のカスタムルールには同等のものがある場合があります。 詳細は、 [ユーザーセグメントの移行](./migrating-user-segments.md) を参照してください。
- 同等のルールがないオーディエンスターゲティングルールの場合、[これらのルールを手動で移行する](./manually-migrating-from-audience-targeting.md)必要があります。
- ルールを完全に再実装する必要がある場合は、 [セグメンテーション開発の概要 (近日公開！)](../developer-guide/introduction-to-segmentation-development.md) の情報に従ってください。
- Liferay DXP 7.2以降の新しいパーソナライゼーション機能はさまざまなツールを使用するため、 [表示ウィジェットも移行する](./manually-migrating-from-audience-targeting.md#migrating-display-properties) 必要があります。

最後に、オーディエンスターゲティングで行動ベースの機能を移行する必要があります。 Liferay DXP 7.2以降では、動作ベースのルールはLiferay Analytics Cloudを使用して管理されます。 詳細は、 [Analytics Cloudのドキュメント](https://learn.liferay.com/analytics-cloud/latest/ja/people/segments/segments.html) を参照してください。

<a name="related-information" />

## 関連情報

- [ユーザーセグメントの移行](./migrating-user-segments.md)
- [オーディエンスターゲティングからの手動移行](./manually-migrating-from-audience-targeting.md)
- [ユーザーセグメントの作成と管理](../segmentation/creating-and-managing-user-segments.md)