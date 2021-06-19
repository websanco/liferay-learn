# セグメントでコンテンツをパーソナライズする

Liferay DXPのパーソナライゼーション機能と併用することで、Analytics Cloudのセグメントのフルパワーが発揮されます。 パーソナライゼーションでは、特定のセグメントでLiferay DXPコンテンツをターゲットにすることができます。 例えば、金融業界のユーザー向けのセグメントがある場合、パーソナライゼーションを利用して投資に関するコンテンツを表示することができます。

Analytics Cloudと同様に、Liferay DXPにはセグメントを含めることができることに注意してください。 しかし、Liferay DXPのセグメントは、Analytics Cloudのセグメントと比較して威力が劣ります。 Analytics Cloudセグメントは、クラウドに存在する比較的大きな計算能力とリソースのため、より強力なものとなっています。

Liferay DXPとAnalytics CloudのSegmentsの比較です。

| Liferay DXPセグメント             | アナリティクスクラウドのセグメント                               |
| ---------------------------- | ----------------------------------------------- |
| DXPのユーザーデータのみに基づく            | 複数のソース（DXP、Salesforce、CSVファイルなど）からのユーザーデータに基づいて |
| セッション属性とクッキーから作成             | ユーザーの興味や過去の行動から作成                               |
| ユーザーIDは1つのDXPサイトからしか取得できません。 | ユーザーIDは複数のDXPサイトから解決されます。                       |
| 匿名ユーザーを含めることはできません           | 匿名ユーザーを含む                                       |
| 限定的で短期的なパーソナライゼーションに最適       | 広範囲で長期的なパーソナライゼーションに適しています。                     |

Liferay DXPでのAnalytics Cloudセグメントの使用については、 [ユーザーセグメントでのAnalytics Cloudの使用](https://learn.liferay.com/dxp/7.x/en/site-building/personalizing-site-experience/segmentation/getting-analytics-for-user-segments.html)を参照してください。 Liferay DXP のパーソナライズについては、 [エクスペリエンスのパーソナライズ](https://learn.liferay.com/dxp/7.x/en/site-building/personalizing_site_experience.html#)を参照してください。
