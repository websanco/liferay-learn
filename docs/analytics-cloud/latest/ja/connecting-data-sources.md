# データソースの接続

```{toctree}
:maxdepth: 3

connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.md
connecting-data-sources/syncing-contact-data-from-dxp.md
connecting-data-sources/scoping-sites-and-individuals-using-properties.md
connecting-data-sources/adding-a-salesforce-data-source.md
connecting-data-sources/adding-a-csv-data-source.md
```

Liferay Analytics Cloudには2種類のデータが必要です。 １つ目は、Liferay DXP Pagesやアセットとのユーザーインタラクションに関するウェブアナリティクスデータ。 2つ目は、ユーザー自身のプロフィールデータです。 Analytics Cloudはこれら2種類のデータを同期して、ユーザーがどのようにサイトを利用しているのか、およびそれらのユーザーが誰なのかを知ることができます。 まず、Analytics Cloudにデータソースを提供します。

どちらの種類のデータもLiferay DXPインスタンスから取得することができます。 Liferay DXPのユーザーモデルにすでに保存されているものに加えて、他のユーザープロファイルデータがある場合は、CSVファイルからインポートしたり、Salesforceシステムのコンタクトデータで追加することができます。

コンタクトデータはマージされ、単一のカスタマービューに集約されます。 Liferay DXPのデータソースでは、同期する組織やユーザーグループを選択することができます。 個々の連絡先は、メールアドレスで照合されます。 コンタクトデータモデルは、好きなフィールド名やタイプを使って定義することができますが、Analytics Cloudでは、値を提案することで簡単に軽微なモデルフィールドを定義することができます。

<a name="viewing-your-data-sources" />

## データソースの表示

データソースは、ナビゲーションパネルから見ることができます。

1. [設定]を選択します。

1. [データソース]をクリックします。

[データソース]ページが表示され、既存のすべてのデータソースが一覧表示されます。

![ [データソース]ページからデータソースを表示、編集、および追加します。](./connecting-data-sources/images/01.png)

チームメイトが既にデータソースを追加していない限り、リストは空です。 新しいデータソースを追加するには、以下のチュートリアルを参照してください。

- [Liferay DXPをAnalytics Cloudに接続する](./connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.md)
- [Salesforce データソースの追加](./connecting-data-sources/adding-a-salesforce-data-source.md)
- [CSVデータソースを追加する](./connecting-data-sources/adding-a-csv-data-source.md)

データソースを作成した後、データソースを変更する必要がある場合もあります。 詳細は [Managing Data Sources](./workspace-data/managing-data-sources.md) を参照してください。

<a name="next-steps" />

## 次のステップ

- [Liferay DXPをAnalytics Cloudに接続する](./connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.md)
- [連絡先データの同期](./connecting-data-sources/syncing-contact-data-from-dxp.md)
- [プロパティを使用したスコープサイトと個人](./connecting-data-sources/scoping-sites-and-individuals-using-properties.md)
- [Salesforce データソースの追加](./connecting-data-sources/adding-a-salesforce-data-source.md)
- [CSVデータソースを追加する](./connecting-data-sources/adding-a-csv-data-source.md)