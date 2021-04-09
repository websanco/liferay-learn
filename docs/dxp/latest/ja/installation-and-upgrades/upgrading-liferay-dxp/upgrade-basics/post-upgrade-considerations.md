# アップグレード後の考慮事項

DXPデータベースをアップグレードした後、本番環境用にDXPを再度構成し、DXPインスタンスに影響を与える可能性のある機能の変更を確認する必要があります。

## 本番環境設定を再度有効にする

DXPデータベースのアップグレードが完了したら、本番環境設定を再度有効にする必要があります。

### サーチインデックス

`indexReadOnly="true"`プロパティを構成してインデックス作成を無効にし、アップグレードプロセスのパフォーマンスの問題を防止した場合は、`com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`ファイルを削除するか、`indexReadOnly="false"`を設定して、インデックス作成を再度有効にします。

``` bash
rm osgi/configs/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config
```

コントロールパネルで*[Search]* → *[Index Actions]* に移動し、*[Reindex All Search Indexes]* の*[Execute]* をクリックして、サーチインデックスのインデックスを再作成します。

![コントロールパネルでサーチインデックスのインデックスを再作成できます。](./post-upgrade-considerations/images/01.png)

``` note::
   古いバージョン（7.1以下）からアップグレードした場合は、 Elasticsearch <https://help.liferay.com/hc/en-us/articles/360029031631-Elasticsearch>`_ をインストールしてサーチインデックスを処理することを検討してください。
```

### データベース設定

Liferayデータベースをアップグレードする前に、アップグレード用にデータベースを調整している場合があります（[Database Tuning for Upgrades](../upgrade-stability-and-performance/database-tuning-for-upgrades.md)を参照）。 アップグレードが完了したので、以前に使用していた本番環境用のデータベースのチューニングを復元します。

``` note::
   データのアップグレード中にシャード化された環境から移行した場合、仮想インスタンスへの移行を完了するには、構成をさらに調整する必要があります。 詳細については、「Upgrade and Update Properties <../other-upgrade-scenarios/upgrading-a-sharded-environment.md#Upgrade-and-Update-Properties>」のセクションを参照してください。
```

## 最新のマーケットプレイスアプリのインストール

以前のLiferayバージョンのマーケットプレイスアプリを使用していた場合は、*新しい*DXPバージョンと互換性のある各アプリのバージョンを使用する必要があります。 各[マーケットプレイスアプリ](../../../system-administration/installing-and-managing-apps/installing-apps/downloading-apps.md)の互換性のある最新バージョンをダウンロードしてインストールします。

``` bash
cp new-app-version.lpkg /new-version/liferay-home/deploy
```

Liferay Homeがソース管理されている場合は、新しいアプリのデプロイをコミットします。

``` bash
commit -a -m "New version of xyz app"
```

## 機能変更の考慮

新しいDXPバージョンでは、機能と動作が変わります。 次の変更がDXPインスタンスにどのように影響するかを確認してください。

### Webコンテンツの表示権限を有効にする

DXP 7.1より前は、すべてのユーザーがデフォルトでWebコンテンツの記事を表示できました。 現在は、表示権限がデフォルトでチェックされるようになりました。 表示権限を開くための主なオプションは次のとおりです。

**オプション1：***[Control Panel]* → *[Configuration]* → *[System Settings]* → *[Web Content]* → *[Virtual Instance Scope]* → *[Web Content]* に移動し、*[Article view permissions check enabled]* の選択を解除して、すべてのWebコンテンツの記事の表示権限を開きます。

![[System Settings]の[Web Content]メニューを下にスクロールして、Webコンテンツを表示するための権限チェックを無効にします。](./post-upgrade-considerations/images/02.png)

**オプション2：** Webコンテンツの記事が多くない場合は、ロールに基づきWebコンテンツの記事ごとに表示権限を編集します。

### Webコンテンツの画像を確認する

DXP 7.2にアップグレードすると、Webコンテンツの画像がドキュメントライブラリに移動し、元のテーブルである`JournalArticleImage`が削除されます。 このプロセスがスムーズに行われるようにするには、Webコンテンツの記事をチェックして、それらの画像がまだ正しく表示されることを確認してください。

### メンテナンスモードでの非推奨項目と機能の考慮

[メンテナンスモードでの非推奨項目](../reference/deprecations-in-liferay-dxp-7-3.md)と[機能](../reference/features-in-maintenance-mode.md)を確認し、DXPインスタンスへの影響に対応するための計画を立ててください（まだ行ってない場合）。

## まとめ

アップグレード後の必要なタスクをすべて完了すると、Liferay DXPのインストールは以前と同様に通常の操作を行う準備が整います。
