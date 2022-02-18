# Liferay Commerce修正プログラム配信方法

> 変更通知を受け取り（購読）が必要

修正プログラムの配信は、Liferay Enterpriseサブスクリプションに含まれる重要なサービスの1つです。 Liferay Commerceでは、プロジェクトのフェーズに合わせて修正プログラムの配信方法を設計しているため、最新のアップデートに継続的にアクセスできます。 Liferay Commerceの修正プログラムの提供方法をさらに理解するには、以下の表とそれに続く定義を参照してください。

Liferay Commerceは、Liferay Digital Experience Platform（DXP）上に構築されています。 そのため、Liferay Commerceのアップデートには、Liferay Commerceが指定するコアプラットフォームのアップデートが必要になる場合があります。

<a name="development-phase-maintenance-updates" />

## 開発フェーズ：メンテナンスアップデート

Liferay Commerceのメンテナンスアップデートは定期的にリリースされます。 これらのアップデートには、慎重に選択され、既知の問題を解決して商品の品質と安定性を維持する修正プログラムのコレクションが含まれています。 メンテナンスアップデートでは広範なQAテストが実施され、緊急アップデートよりもリスクが大幅に軽減されます。 これらは最新のコードベースを表します。Liferayは現在のコードベースの既知の問題の解決に重点を置いているため、プロジェクトの開発段階では最新のメンテナンスアップデートを常に入手することをお勧めします。

Liferay Digital Experience Platformの一部であるコアモジュールおよびスイートの場合、メンテナンスアップデートは、Liferay Connected Servicesおよびカスタマーポータルを通じて単一の [DXP 7.1](https://customer.liferay.com/group/customer/downloads? **com** liferay **osb** customer **downloads** display **web** DownloadsDisplayPortlet **formDate=1542377673692&p** p **id=com** liferay **osb** customer **downloads** display **web** DownloadsDisplayPortlet&p **p** lifecycle=0&p **p** state=normal&p **p** mode=view& **com** liferay **osb** customer **downloads** display **web** DownloadsDisplayPortlet **product=dxp** 71& **com** liferay **osb** customer **downloads** display **web** DownloadsDisplayPortlet_fileType=fixPacks) フィックスパックとしてリリースされます。

| 推定リリースターゲット      | レギュラー    | 必要に応じて   |
| :--- | :--- | :--- |
| Liferay DXP *    | &#10003; | &#10003; |
| Liferay Commerce | &#10003; | &#10003; |

<a name="production-phase-emergency-updates" />

## 生産段階：緊急アップデート

緊急アップデートは、本番環境にデプロイされたLiferayコンポーネントに対して個別に提供されます。 本番環境のデプロイスケジュールで本番環境に加えることができる変更の量や頻度が制限されている場合は、Liferayサポートチームに緊急アップデートをリクエストすることをお勧めします。 これらのアップデートは、将来のメンテナンスアップデートに含まれるお客様固有の一時的なソリューションです。 Liferay Digital Experience Platformの場合、緊急アップデートは、コアモジュールおよびスイートにパッチを適用できる短期的なソリューションであるホットフィックスとしても知られています。 緊急アップデートをインストールした場合は、新しい開発フェーズに入ったらすぐに、Liferay DXPシステムを最新のメンテナンスアップデートで最新の状態にすることを強くお勧めします。

| 推定リリースターゲット      | レギュラー    |
| :--- | :--- |
| Liferay DXP *    | &#10003; |
| Liferay Commerce | &#10003; |

\*コアモジュールとスイート（Static、Foundation、Web Experience、Collaboration、Forms and Workflow）
