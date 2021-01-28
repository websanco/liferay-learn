# アップグレードの概要

DXPインストールの複雑さと規模は、DXPをアップグレードするために必要となる計画と作業に直接相関しています。 カスタマイゼーションと大規模なデータセットがあると、アップグレードの時間が長くなる可能性があります。 Liferay DXPのアップグレードに関するトピックは、次のカテゴリに分類されます。

  - [準備と計画](#preparation-and-planning)
  - [カスタムプラグインコードの更新](#updating-custom-plugin-code)
  - [構成とインフラストラクチャの移行](#migrating-and-updating-configurations-and-infrastructure)
  - [アップグレードパフォーマンスの向上](#improving-upgrade-performance)
  - [データベースのアップグレードの実行](#executing-the-database-upgrade)

``` warning::
   アップグレードする前に、**必ず**データベースとインストールをバックアップ<../../maintaining-a-liferay-dxp-installation/backing-up.md>してください。 バックアップコピーでアップグレードプロセスをテストすることをお勧めします。
```

## 準備と計画

準備と計画は、小規模でカジュアルなインストールではあまり重要でない場合がありますが、大規模なエンタープライズレベルのインストールでは*必須*です。

### 利用可能なアップグレードパスの確認

次の表で現在のLiferay DXP/Portalのバージョンを調べて、インストールのアップグレードパスを確認してください。

| アップグレードパス                                                         | 説明                                              |
| ----------------------------------------------------------------- | ----------------------------------------------- |
| Liferay DXP/Portal 6.2+ → DXP 7.3                                 |                                                 |
| Liferay Portal 6.1.x → DXP/Portal 7.1 → DXP 7.3                   | Liferay Portal 6.1のサポート期間は終了しました                |
| Liferay Portal 5.xおよび6.0.x → Liferay Portal 6.2 → Liferay DXP 7.3 | Liferay Portal 5.0、5.1、5.2、および6.0のサポート期間は終了しました |

パスにLiferay Portal 6.2へのアップグレードが含まれている場合は、最初に[Liferay Portal 6.2のアップグレード手順](https://help.liferay.com/hc/en-us/articles/360017903232-Upgrading-Liferay)に従ってください。

### 非推奨項目およびデフォルト設定の変更点を確認する

Liferay DXPの新しいバージョンでは、機能と構成のデフォルトが変更されている場合があります。 最新の非推奨項目および機能と設定の変更点については、リファレンスセクションまたは次の記事を参照してください。

  - [Deprecations](../reference/deprecations-in-liferay-dxp-7-3.md)
  - [Features in Maintenance Mode](../reference/features-in-maintenance-mode.md)
  - [Changes to Default Settings](../reference/changes-to-default-settings.md)

### アップグレードパッチのリクエスト（サブスクリプション）

> サブスクリプション

Liferay DXPサブスクリプションをお持ちの場合は、最新のフィックスパックにアップデートするか、データベースのアップグレードの準備をするためのアップグレードパッチをリクエストしてください。 このプロセスを開始するには、[ヘルプセンター](https://help.liferay.com/hc/requests/new)でチケットを提出してください。

### マーケットプレイスアプリの更新

DXPデータベースをアップグレードする前に、マーケットプレイスアプリを現在使用しているDXP/Portalバージョンの最新バージョンに更新する必要があります。 アプリの更新をスキップすると問題が発生し、アプリが新しいDXPバージョンで有効にならない場合があります。

``` important::
   アップグレードする前に、現在のインストールでこれを行ってください。
```

DXPデータベースをアップグレードした後、新しいDXPインスタンスの最新のアプリバージョンをインストールします。

## カスタムプラグインコードの更新

開発したプラグイン（テーマ、アプリ、カスタマイゼーションなど）は、新しいDXPバージョンに適合させる必要があります。 これは、依存関係の更新と同じくらい簡単な場合もあれば、APIの変更に合わせたコードの更新が必要な場合もあります。 カスタムプラグインの更新をしないと、新しいDXPバージョンで無効になる場合があります。 [コードアップグレード](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2)はコードアップグレードプロセスを歩きます。

## 構成とインフラストラクチャの移行と更新 <!-- New DXP installations use your configurations at run time and during database upgrade.--> 構成と対応するインフラストラクチャを、以前のインストールから新しいインストールに移行して更新する必要があります。

``` important::
   6.2以前からアップグレードする場合は、ファイルストアの構成を更新します。 詳細については、Updating the File Store <../configuration-and-infrastructure/updating-the-file-store.md>を参照してください。
```

データベースのアップグレードが完了してから、他の設定を更新します。 詳細については、[Migrating Configurations and Properties](../configuration-and-infrastructure/migrating-configurations-and-properties.md)を参照してください。

## アップグレードパフォーマンスの向上

不要なデータをそのままにしておいたり、パフォーマンスチューニングをしていないと、大規模なデータセットのアップグレードに非常に長い時間がかかる場合があります。 データベースのアップグレードを迅速化する方法はいくつかあります。

### データを削除する

DXPサーバーに不要なインスタンス、サイト、ページ、またはバージョン管理されたコンテンツアイテム（Webコンテンツの記事、ドキュメント、メディアファイルなど）がある場合、それらを削除するとアップグレード時間が大幅に短縮されます。 不要なデータを削除する方法については、[Database Pruning for Faster Upgrades](../upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md)を参照してください。

### データベースのパフォーマンスを調整する

アップグレード操作（本番環境よりもデータ書き込みが多い）用にデータベースを調整すると、データベースのアップグレードパフォーマンスが向上します。 詳細については、[Database Tuning for Upgrades](../upgrade-stability-and-performance/database-tuning-for-upgrades.md)を参照してください。

### 検索エンジンを調整する

検索エンジンは通常、Liferay DXPの実行中に定期的にインデックスを作成します。 ただし、DXPの起動時にデータベースをアップグレードする際にこのインデックス作成がオンのままになっていると、アップグレードのパフォーマンスに悪影響を及ぼす可能性があります。 データベースのアップグレード中にDXPがオフラインであっても、インデックス作成がオンになっていると、検索サーバーを呼び出すサービスを呼び出すアップグレードプロセスでエラーが発生します。 インデックス作成は、アップグレードを実行する前に無効にし、アップグレードが完了したら再度有効にする必要があります。 データベースのアップグレード手順とアップグレード後の手順では、インデックス作成の設定について説明します。

## データベースのアップグレードの実行

DXPデータベースをアップグレードするには、2つの方法があります。

  - [Dockerによるアップグレード](./upgrading-via-docker.md)では、DXP Dockerイメージを起動するためのコマンドに自動アップグレードパラメータを渡します。 DXPはデータベースを更新し、アップグレードされたデータベースを使用して起動します。

  - [データベースアップグレードツールの使用](./using-the-database-upgrade-tool.md) 。 アップグレードツールは、DXPインスタンスから切り離された状態でDXPデータベースを更新するためのクライアントプログラムです。 これにより、アップグレードプロセスに重点が置かれ、アップグレード操作のための[データベースの調整](../upgrade-stability-and-performance/database-tuning-for-upgrades.md)、[不要なデータの削除](../upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md)が容易になり、データベースのアップグレードが迅速化されます。

## まとめ

上記のタスクを完了すると、アップグレードが完了します。 ただし、DXPを使用する前に、必要なランタイム設定を再確立し、アップグレード固有の調整を元に戻す必要があります。 さらに、新しいDXP実稼働インスタンスに推奨される、以前のLiferayバージョンでは利用できなかったアプリケーションがある場合があります。 詳細については、[Post-Upgrade Considerations](./post-upgrade-considerations.md)を参照してください。

DXPのアップグレードコンポーネントについて理解したので、DXPインスタンスをアップグレードできます。 データベースをアップグレードする前に、現在のインストールで準備作業を必ず行ってください。 カスタムプラグインコードのアップグレードとデータベースのアップグレードを並行して実行するのが一般的です。 データベースのアップグレードは最初に開始する一般的なタスクであるため、以下に2つの方法へのリンクを示します。

  - [Upgrading Via Docker](./upgrading-via-docker.md)
  - [Using the Database Upgrade Tool](./using-the-database-upgrade-tool.md)

さらに、アップグレードに関連する以下のアップグレードシナリオを参照してください。

  - [Upgrading a Sharded Environment](../other-upgrade-scenarios/upgrading-a-sharded-environment.md)
  - [Maintaining Clustered Installations](../../maintaining-a-liferay-dxp-installation/maintaining-clustered-installations/maintaining-clustered-installations.md)
