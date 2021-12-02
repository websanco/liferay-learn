# アップグレードの基本

```{toctree}
:maxdepth: 2

upgrade-basics/upgrading-via-docker.md
upgrade-basics/using-the-database-upgrade-tool.md
upgrade-basics/post-upgrade-considerations.md
```

Liferayのツールや説明書は、DXPやポータルCE環境を安全かつ迅速にアップグレードするのに役立ちます。 小さなデータセットを持つ非クラスター環境などは、[Dockerイメージ](./upgrade-basics/upgrading-via-docker.md)を使ってアップグレードすることができます。 複雑で、より大きなデータセットを持つ、または多くのカスタマイズがあるDXP環境およびポータルCE環境は、[データベースアップグレードツール](./upgrade-basics/using-the-database-upgrade-tool.md)を使用してアップグレードします。

```{warning}
アップグレードする前に、**必ず**データベースとインストールを [バックアップ](../maintaining-a-liferay-installation/backing-up.md) してください。 バックアップコピーでアップグレードプロセスをテストすることをお勧めします。
```

```{note}
[Docker経由でのアップグレード](./upgrade-basics/upgrading-via-docker.md) は、小規模でカジュアルなポータルCE環境でデータベースをアップグレードする最も簡単な方法です。
```

アップグレードを始める前に、必要な項目を検討しましょう。 アップグレードに関するトピックは、次のカテゴリに分類されます。

  - [準備と計画](#preparation-and-planning)
  - [カスタムプラグインコードの更新](#updating-custom-plugin-code)
  - [構成とインフラストラクチャの移行](#migrating-and-updating-configurations-and-infrastructure)
  - [アップグレードパフォーマンスの向上](#improving-upgrade-performance)
  - [データベースのアップグレードの実行](#executing-the-database-upgrade)

## 準備と計画

準備と計画は、小規模でカジュアルなインストールではあまり重要でない場合がありますが、大規模なエンタープライズレベルのインストールでは*必須*です。

### 利用可能なアップグレードパスの確認

次の表で現在のLiferay DXP/ポータルのバージョンを調べて、インストールのアップグレードパスを確認してください。

| アップグレードパス                                                         | 説明                                              |
| ----------------------------------------------------------------- | ----------------------------------------------- |
| Liferay DXP/Portal 6.2+ → DXP 7.3                                 |                                                 |
| Liferay Portal 6.1.x → DXP/Portal 7.1 → DXP 7.3                   | Liferay Portal 6.1のサポート期間は終了しました                |
| Liferay Portal 5.xおよび6.0.x → Liferay Portal 6.2 → Liferay DXP 7.3 | Liferay Portal 5.0、5.1、5.2、および6.0のサポート期間は終了しました |

パスにLiferay Portal 6.2へのアップグレードが含まれている場合は、最初に[Liferay Portal 6.2のアップグレード手順](https://help.liferay.com/hc/en-us/articles/360017903232-Upgrading-Liferay)に従ってください。

### 非推奨項目およびデフォルト設定の変更点を確認する

Liferayの新しいバージョンでは、機能と構成のデフォルトが変更されている場合があります。 最新の非推奨項目および機能と設定の変更点については、リファレンスセクションまたは次の記事を参照してください。

  - [Maintenance Mode and Deprecations](./reference/maintenance-mode-and-deprecations-in-7-3.md)
  - [Default Setting Changes](./reference/default-setting-changes-in-7-3.md)

### アップグレードパッチのリクエスト（サブスクリプション）

> サブスクリプション

Liferay DXPサブスクリプションをお持ちの場合は、最新のフィックスパックにアップデートするか、データベースのアップグレードの準備をするためのアップグレードパッチをリクエストしてください。 このプロセスを開始するには、[ヘルプセンター](https://help.liferay.com/hc/requests/new)でチケットを提出してください。

### マーケットプレイスアプリの更新

Liferayデータベースをアップグレードする前に、マーケットプレイスアプリを現在使用しているLiferayバージョンの最新バージョンに更新する必要があります。 アプリの更新をスキップすると問題が発生し、アプリが新しいLiferayバージョンで有効にならない場合があります。

```{important}
アップグレードする前に、現在のインストールでこれを行ってください。
```

データベースをアップグレードした後、新しいLiferayインスタンスの最新のアプリバージョンをインストールします。

## カスタム開発のアップグレード

開発したプラグイン（テーマ、アプリ、カスタマイゼーションなど）は、新しいLiferayバージョンに適合させる必要があります。 これは、依存関係の更新と同じくらい簡単な場合もあれば、APIの変更に合わせたコードの更新が必要な場合もあります。 カスタムプラグインの更新をしないと、新しいLiferayバージョンで無効になる場合があります。 [Upgrading Custom Development](./upgrading-custom-development.md)では、コードのアップグレードのプロセスを説明しています。

## 構成とインフラストラクチャの移行と更新

構成と対応するインフラストラクチャを、以前のインストールから新しいインストールに移行して更新する必要があります。

```{important}
6.2以前からアップグレードする場合は、ファイルストアの構成を更新します。 詳細は、 [Updating the File Store](./reference/file-store-updates.md) を参照してください。
```

データベースのアップグレードが完了してから、他の設定を更新します。 詳細は、[構成とプロパティの移行](./migrating-configurations-and-properties.md)を参照してください。

## アップグレードパフォーマンスの向上

不要なデータをそのままにしておいたり、パフォーマンスチューニングをしていないと、大規模なデータセットのアップグレードに非常に長い時間がかかる場合があります。 データベースのアップグレードを迅速化する方法はいくつかあります。

### データを削除する

Liferayサーバーに不要なインスタンス、サイト、ページ、またはバージョン管理されたコンテンツアイテム（Webコンテンツの記事、ドキュメント、メディアファイルなど）がある場合、それらを削除するとアップグレード時間が大幅に短縮されます。 不要なデータを削除する方法については、[より高速なアップグレードのためのデータベースのプルーニング](./upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md)を参照してください。

### データベースのパフォーマンスを調整する

アップグレード操作（本番環境よりもデータ書き込みが多い）用にデータベースを調整すると、データベースのアップグレードパフォーマンスが向上します。 詳細は、[アップグレードのためのデータベース調整](./upgrade-stability-and-performance/database-tuning-for-upgrades.md)を参照してください。

## データベースのアップグレードの実行

Liferayデータベースをアップグレードするには、2つの方法があります。

  - [Dockerによるアップグレード](./upgrade-basics/upgrading-via-docker.md)では、Dockerイメージを起動するためのコマンドに自動アップグレードパラメータを渡します。 イメージはデータベースを更新し、アップグレードされたデータベースを使用して起動します。

  - [データベースアップグレードツールの使用](./upgrade-basics/using-the-database-upgrade-tool.md) 。 アップグレードツールは、Liferayインスタンスから切り離された状態でLiferayデータベースを更新するためのクライアントプログラムです。 これにより、アップグレードプロセスに重点が置かれ、アップグレード操作のための[データベースの調整](./upgrade-stability-and-performance/database-tuning-for-upgrades.md)、[不要なデータの削除](./upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md)が容易になり、データベースのアップグレードが迅速化されます。

## まとめ

上記のタスクを完了すると、アップグレードが完了します。 ただし、Liferayを使用する前に、必要なランタイム設定を再確立し、アップグレード固有の調整を元に戻す必要があります。 さらに、新しいLiferay本番環境インスタンスに推奨される、以前のLiferayバージョンでは利用できなかったアプリケーションがある場合があります。 詳細は、[アップグレード後の考慮事項](./upgrade-basics/post-upgrade-considerations.md)を参照してください。

アップグレードコンポーネントについて理解したので、Liferayインスタンスをアップグレードできます。 データベースをアップグレードする前に、現在のインストールで準備作業を必ず行ってください。 カスタムプラグインコードのアップグレードとデータベースのアップグレードを並行して実行するのが一般的です。 データベースのアップグレードは最初に開始する一般的なタスクであるため、以下に2つの方法へのリンクを示します。

  - [Upgrading Via Docker](./upgrade-basics/upgrading-via-docker.md)
  - [データベースアップグレードツールの使用](./upgrade-basics/using-the-database-upgrade-tool.md)

さらに、アップグレードに関連する以下のアップグレードシナリオを参照してください。

  - [Upgrading a Sharded Environment](./other-upgrade-scenarios/upgrading-a-sharded-environment.md)
  - [Maintaining Clustered Installations](../maintaining-a-liferay-installation/maintaining-clustered-installations.md)
