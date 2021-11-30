# アップグレードの安定性とパフォーマンス

```{toctree}
:maxdepth: 2

upgrade-stability-and-performance/database-tuning-for-upgrades.md
upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md
upgrade-stability-and-performance/example-removing-intermediate-journal-article-versions.md
upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md
```

本番リリース用のDXPインスタンスをアップグレードする場合、データのアップグレードは正しく迅速に行う必要があります。 確実に成功させる方法をいくつか次に示します。

  - データベースとインストールのコピーを使用するテスト環境を作成する
  - 不要なデータベースデータを削除してパフォーマンスを向上させる
  - アップグレード操作のためにデータベースを調整する
  - データベースをアップグレードし、アップグレードの問題をトラブルシューティングする
  - アップグレードしたデータベースをDXPでテストする

## テスト環境の作成

本番環境とは異なるが、同じデータ、構成、アプリを使用する環境でアップグレードをテストすることが重要です。 インストール先（Liferay Home）、データベース、マーケットプレイスアプリのコピーを使用できます。 これらは、[DXPのバックアップ](../maintaining-a-liferay-installation/backing-up.md)からすぐに利用できます。

## 不要なデータの削除

DXPサイトを積極的に更新する場合、不要になったデータがある可能性があります。 これには、使用を停止したサイト、ロール、組織や、不要になったコンテンツの改訂が含まれます。 不要なエンティティとそれに関連するデータを削除すると、アップグレードの処理時間を節約できます。 詳細は、[より高速なアップグレードのためのデータベースのプルーニング](./upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md)を参照してください。 また、[例：ジャーナル記事の中間バージョンの削除](./upgrade-stability-and-performance/example-removing-intermediate-journal-article-versions.md)も確認してください。

## アップグレードのためのデータベースの調整

DXPアップグレードプロセスでは、本番環境で通常行われるよりも多くのデータがデータベースに書き込まれます。 データベースを書き込み操作用に最適化できます。 詳細は、[アップグレードのためのデータベース調整](./upgrade-stability-and-performance/database-tuning-for-upgrades.md)を参照してください。

## アップグレードと問題のトラブルシューティング

テストデータベースを整理および調整した後、[データベースアップグレードツール](./upgrade-basics/using-the-database-upgrade-tool.md)を使用してアップグレードします。 未解決の依存関係などの問題が発生した場合は、ログを調べ、Gogoシェルコマンドを使用して問題のトラブルシューティングを行うことでアップグレードを完了できます。 方法については、[Upgrading Modules Using Gogo Shell](./upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md)を参照してください。

## アップグレードされたデータベースのテスト

最後に、時間をかけて、アップグレードしたデータベースでDXPインスタンスをテストします。

DXPインスタンスを正常にアップグレードしたら、それをオーケストレーションして本番環境にリリースできます。

## 追加情報

  - [Updating Clustered Installations](../maintaining-a-liferay-installation/maintaining-clustered-installations.md)
