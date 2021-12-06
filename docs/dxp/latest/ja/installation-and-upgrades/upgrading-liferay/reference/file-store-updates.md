# ファイルストアの更新

Liferay DXP7.0でファイルストアオプションと設定値が変更されました。 7.0以前のバージョンを使用していて、これらの変更の影響を受ける場合は、DXPデータベースをアップグレードする</em>前*に、ファイルストアをアップデートする必要があります。</p>

次の表は、Liferayのバージョンごとに必要なアップデートを示しています。

| 現在のLiferayのバージョン     | 必要なアップデート                                                                                          |
|:-------------------- |:-------------------------------------------------------------------------------------------------- |
| Liferay DXP 7.0      | 1\. CMIS StoreおよびJCR Storeから移行します。                                                                |
| Liferay Portal 6.2以前 | 1\. ストア実装クラス名を更新します。<br>2. CMIS StoreおよびJCR Storeから移行します。<br>3. システム設定でファイルストアを設定します。 |

次のセクションでは、ファイルストアを更新する方法について説明します。

## ストア実装クラス名の更新

ストア実装クラスのパッケージ名が、Liferay Portal 6.2の`com.liferay.portlet.documentlibrary.store.*`からDXP 7.0の`com.liferay.portal.store.*`に変更されました。 次のいずれかの方法で、[`portal-ext.properties`](../../reference/portal-properties.md) の `dl.store.impl`プロパティを設定してください。

``` properties
dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
dl.store.impl=com.liferay.portal.store.db.DBStore
dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
dl.store.impl=com.liferay.portal.store.s3.S3Store
```

## JCR Storeからの移行

JCR StoreはDXP 7.0で非推奨になりました。 他のストアオプションについては、[ファイルストレージ](../../../system-administration/file-storage.md)のドキュメンテーションで説明しています。 データベースをアップグレードする前に、[サポートされているファイルストアに移行](../../../system-administration/file-storage/file-store-migration.md)してください。

## CMIS Storeからの移行

CMISストアは7.0.10フィックスパック14で非推奨になり、DXP 7.2で削除されました。 他のストアオプションについては、[ファイルストレージ](../../../system-administration/file-storage.md)のドキュメンテーションで説明しています。 データベースをアップグレードする前に、[サポートされているファイルストアに移行](../../../system-administration/file-storage/file-store-migration.md)してください。

## システム設定でファイルストアを構成する

DXP 7.0以降、ドキュメントストアタイプに固有の構成（例：Simple File Store、Advanced File Store、S3などに固有の構成）は、コントロールパネル（*[Configuration[* → *[System Settings]* → *[File Storage]*）から行うか、またはOSGi構成ファイル（`.config`ファイル）を使用して行います。 タイプに固有の構成は、`portal-ext.properties`を使用して行われなくなりました。 データベースをアップグレードした後、[[System Settings]](../../../system-administration/file-storage.md)でファイルストアを構成してください。

ストア設定の詳細は、[ファイルストレージの構成](../../../system-administration/file-storage.md)を参照してください。
