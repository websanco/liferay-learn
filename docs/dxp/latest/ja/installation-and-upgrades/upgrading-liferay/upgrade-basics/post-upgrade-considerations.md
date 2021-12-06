# アップグレード後の考慮事項

データベースをアップグレードした後、本番環境用にLiferayを再度構成し、Liferayインスタンスに影響を与える可能性のある機能の変更を確認する必要があります。

## 本番環境設定を再度有効にする

データベースのアップグレードが完了したら、本番環境設定を再度有効にします。

### サーチインデックス

ほとんどのアップグレードでは検索インデックスの再インデックス化が必要ですが、通常、同じLiferayバージョン内でサービスパックを適用したり、新しいGA（ローリングリリース）にアップグレードしたりする場合は必要ありません。 インデックスを再構築する方法は次のとおりです。

1.  *グローバルメニュー* （![Global Menu icon](./post-upgrade-considerations/images/01.png)）をクリックし、*[コントロールパネル]* タブを選択します。 コントロールパネルが表示されます。

2.  [設定]セクションで*[検索機能]* をクリックし、*[アクションをインデックスする]* タブを選択し、*[Reindex all search indexes]* に対し*[実行]* をクリックします。インデックスの再構築が実行され、完了すると成功メッセージが表示されます。

![コントロールパネルでサーチインデックスのインデックスを再作成できます。](./post-upgrade-considerations/images/02.png)

```{note}
古いバージョン（7.1以下）からアップグレードした場合は、検索インデックスを処理するために [Elasticsearchのインストール](../../../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md) を検討してください。
```

### データベース設定

データベースをアップグレードする前に、アップグレード用にデータベースを調整している場合があります（[アップグレードのためのデータベース調整](../upgrade-stability-and-performance/database-tuning-for-upgrades.md)を参照）。 アップグレードが完了したので、本番環境データベースの設定を復元します。

```{note}
データのアップグレード中にシャード化された環境から移行した場合、仮想インスタンスへの移行を完了するには、構成をさらに調整する必要があります。 詳細は、「 [Upgrade and Update Properties](../other-upgrade-scenarios/upgrading-a-sharded-environment.md#Upgrade-and-Update-Properties) 」のセクションを参照してください。
```

## 最新のマーケットプレイスアプリのインストール

以前のLiferayバージョンのマーケットプレイスアプリを使用していた場合は、*新しい*DXPバージョンと互換性のある各アプリのバージョンを使用する必要があります。 各[マーケットプレイスアプリ](../../../system-administration/installing-and-managing-apps/installing-apps/downloading-apps.md)の互換性のある最新バージョンをダウンロードしてインストールします。

``` bash
cp [new-app-version].lpkg /new-version/liferay-home/deploy
```

Liferay Homeがソース管理されている場合は、新しいアプリのデプロイをコミットします。

``` bash
commit -a -m "New version of xyz app"
```

## 機能変更の考慮

新しいLiferayバージョンでは、機能と動作が変わります。 以下の変更点を確認してください。

### Webコンテンツの表示権限を有効にする

7.1より前は、すべてのユーザーがデフォルトでWebコンテンツの記事を表示できました。 現在は、表示権限がデフォルトでオンになっています。 表示権限を開くための主なオプションは次のとおりです。

**オプション1：***[コントロールパネル]* → *[設定]* → *[システム設定]* → *[Webコンテンツ]* → *[仮想インスタンススコープ]* → *[Webコンテンツ]* に移動し、*[Article view permissions check enabled]* の選択を解除して、すべてのWebコンテンツの記事の表示権限を開きます。

![[システム設定]の[Webコンテンツ]メニューを下にスクロールして、Webコンテンツを表示するための権限チェックを無効にします。](./post-upgrade-considerations/images/03.png)

**オプション2：** Webコンテンツの記事が少ない場合は、ロールに基づきWebコンテンツの記事ごとに表示権限を編集します。

### Webコンテンツの画像を確認する

7.2にアップグレードすると、Webコンテンツの画像は[ファイル ストア](../../../system-administration/file-storage.md)（ドキュメントライブラリともいう）に移動し、以前のテーブル`JournalArticleImage`は削除されます。 画像を移行できない場合、Liferayは失敗を報告します。

    Unable to add the journal article image {filename} into the file repository

そのようなメッセージがない場合は、すべての画像がファイルストアにあるはずです。 Webコンテンツの記事をプレビューして、画像を確認できます。

### メンテナンスモードでの非推奨項目と機能の考慮

[メンテナンスモードでの非推奨項目と機能](../reference/maintenance-mode-and-deprecations-in-7-3.md)を確認し、Liferayインスタンスへの影響に対応するための計画を立ててください（まだ行ってない場合）。

### 廃止されたデータを削除する

廃止された機能からデータをクリーンアップします。 詳細は、[データクリーンアップ](../reference/data-cleanup.md)を参照してください。

## まとめ

アップグレード後の必要なタスクをすべて完了すると、Liferayサーバーは以前と同様に通常の操作を行う準備が整います。
