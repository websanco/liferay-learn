# アップグレードのトラブルシューティング

アップグレード手順をスキップしたり、Liferayデータベースへのカスタム参照を作成したりすると、アップグレードの問題が発生する可能性があります。 以下の質問と回答では、よくある状況を取り上げています。

## 外部キー制約によって引き起こされるアップグレード例外をどのように処理すればよいですか？

Liferayテーブルは外部キーを使用しません。 新しいLiferayバージョンでLiferayテーブルにプライマリーキーが追加され、テーブルに外部キーが関連付けられている場合、外部キーが壊れてアップグレードが失敗します。

たとえば、Liferay 7.3では、`ctCollectionId`というプライマリーキーが`user_`テーブルに追加されました。 ` user_`テーブルに関連付けられた外部キーにより、`user_`テーブルのアップグレードが失敗し、次のようなメッセージが表示されることがあります。

```
INFO  [main][LoggingTimer:44] Completed com.liferay.portal.kernel.upgrade.UpgradeCTModel#doUpgrade#User_ in 750 ms
INFO  [main][UpgradeProcess:115] Failed upgrade process com.liferay.portal.kernel.upgrade.UpgradeCTModel in 4703 ms
INFO  [main][UpgradeProcess:115] Failed upgrade process com.liferay.portal.upgrade.PortalUpgradeProcess in 53125 ms
com.liferay.portal.kernel.upgrade.UpgradeException: com.liferay.portal.kernel.upgrade.UpgradeException: java.sql.SQLException: Error on rename of '.\liferaydxp\#sql-908_12f' to '.\liferaydxp\user_' (errno: 150 - Foreign key constraint is incorrectly formed)
```

最後の`UpgradeException`メッセージは、アップグレードで外部キー制約の名前を`user_`テーブルに変更できないことを示しています。

Liferayテーブルに関連付けられた外部キーを使用するカスタムテーブルがある場合は、外部キーを、Liferayモデルの変更に基づいてカスタムテーブルを更新する[モデルリスナー](../../../liferay-internals/extending-liferay/creating-a-model-listener.md)に置き換えます。 たとえば、カスタムテーブルに現在`user_`テーブルを参照する外部キーがある場合は、`User`インスタンスが追加または削除されたときにカスタムテーブルを更新するモデルリスナーを作成します。

置き換える方法は次のとおりです。

1. カスタムテーブルデータに関連するモデルイベント用のモデルリスナーを作成します。

1. テスト環境でモデルリスナーを検証します。

1. アップグレード環境で、カスタムテーブルを外部キーを使用しないテーブルに置き換えます。

1. データベースをアップグレードします。

1. 新しいLiferayインストールにモデルリスナーをデプロイします。

1. Liferayサーバーを起動します。

モデルリスナーは、リッスンしているモデルイベントに基づいて新しいカスタムテーブルを更新します。

## 仮想列式で使用されるテーブル列の名前変更に関するアップグレードプロセスの警告はどのように処理すればよいですか？

一部の新しいLiferayバージョンでは、テーブル列の名前が変更されています。 これらの列に関連付けられている[仮想列](https://en.wikipedia.org/wiki/Virtual_column)はデータベースのアップグレードを妨げるため、アップグレードの前に削除する必要があります。 アップグレード後、同等の仮想列を追加できます。

たとえば、Liferay 7.0では、`JournalArticle`テーブルの`structureId`列と`templateId`列の名前をそれぞれ`DDMStructureKey`と`DDMTemplateKey`に変更しました。 Liferay Portal 6.2からアップグレードしていて、仮想列を`JournalArticle`テーブルに関連付けている場合は、アップグレードする前にそれらを削除してください。 たとえば、Oracleデータベースでは、次のようなクエリを使用して仮想列を確認できます。

```sql
select column_name, data_default, hidden_column from user_tab_cols where table_name = 'JOURNALARTICLE';
```

テーブルをアップグレードした後、同等の仮想列をデータベースに追加できます。
