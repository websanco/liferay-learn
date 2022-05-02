# ブルーグリーンのデプロイ

ブルーグリーンとは、本番環境（**ブルー** 環境）を複製し、ソフトウェアとデータの変更によって複製を変更する（**グリーン** 環境）デプロイ手法です。 グリーン環境のテストに成功したら、ユーザーをブルー環境からグリーン環境にカットオーバーします。 ブルーグリーンは、システムのダウンタイムを排除します。

データの変更とデータスキーマの変更には、特別な注意が必要です。 既存のコードとの互換性を損なうカスタムのプラグイン/モジュールデータスキーマの変更は、いくつかのリリースにわたって導入する必要があり、データを古い列と新しい列に移行し、古い列が不要になるまで維持します。

データとスキーマの変更には、次の手順が必要です。

1. 新しい列を作成します。

1. データを新しい列にコピーします。

1. 古い列がクラスターノードで使用されなくなるまで、両方の列を維持します。

1. 次のリリースで古い列を削除します。

詳細は、以下のブルーグリーンのデプロイに関する記事を参照してください。

* [BlueGreenDeployment](http://martinfowler.com/bliki/BlueGreenDeployment.html)
* [Implementing Blue-Green Deployments with AWS](https://www.thoughtworks.com/insights/blog/implementing-blue-green-deployments-aws)

## 追加情報

* [Rolling Restarts](./rolling-restarts.md)
* [高可用性のクラスタリング](../../setting-up-liferay/clustering-for-high-availability.md)
* [DXP へのパッチ適用](../patching-dxp-7-3-and-earlier.md)