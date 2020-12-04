# ファイルストアの更新

Liferay DXP 7.0では、ファイルストアのオプションと構成値が変更されました。 7.0以前を使用していて、これらの変更の影響を受ける場合は、DXPデータベースをアップグレードする*前*にファイルストアを更新する必要があります。

次の表は、Liferayのバージョンごとに必要なアップデートを示しています。

| 現在のLiferayのバージョン     | 必要なアップデート                                                                                          |
|:-------------------- |:-------------------------------------------------------------------------------------------------- |
| Liferay DXP 7.0      | 1\. CMIS StoreおよびJCR Storeから移行します。                                                                |
| Liferay Portal 6.2以前 | 1\. ストア実装クラス名を更新します。<br>2. CMIS StoreおよびJCR Storeから移行します。<br>3. システム設定でファイルストアを設定します。 |

次のセクションでは、ファイルストアを更新する方法について説明します。

## ストア実装クラス名の更新

ストア実装クラスのパッケージ名が、Liferay Portal 6.2の`com.liferay.portlet.documentlibrary.store.*`からDXP 7.0の`com.liferay.portal.store.*`に変更されました。次のいずれかの方法で、[`portal-ext.properties`](../../reference/portal-properties.md)の`dl.store.impl`プロパティを設定してください。

``` properties
dl.store.impl=com.liferay.portal.store.file.system.FileSystemStore
dl.store.impl=com.liferay.portal.store.db.DBStore
dl.store.impl=com.liferay.portal.store.file.system.AdvancedFileSystemStore
dl.store.impl=com.liferay.portal.store.s3.S3Store
```

## JCR Storeからの移行

JCR Storeは、DXP 7.0で非推奨になりました。[Document Repository Configuration](https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration)のドキュメントで、他のストアオプションについて説明しています。 データベースをアップグレードする前に、[サポートされているファイルストアに移行](https://help.liferay.com/hc/en-us/articles/360029131691-Server-Administration)してください。

## CMIS Storeからの移行

CMIS Storeは7.0.10フィックスパック14で非推奨になり、DXP 7.2で削除されました。[Document Repository Configuration](https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration)のドキュメントで、他のストアオプションについて説明しています。 データベースをアップグレードする前に、[サポートされているファイルストアに移行](https://help.liferay.com/hc/en-us/articles/360029131691-Server-Administration)してください。

## システム設定でファイルストアを構成する

DXP 7.0以降、ドキュメントストアタイプに固有の構成（例：Simple File Store、Advanced File Store、S3などに固有の構成）は、コントロールパネル（*[Configuration[* → *[System Settings]* → *[File Storage]*）から行うか、またはOSGi構成ファイル（`.config`ファイル）を使用して行います。 タイプに固有の構成は、`portal-ext.properties`を使用して行われなくなりました。 データベースをアップグレードした後、[[System Settings]](https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration)でファイルストアを構成してください。

ストア構成の詳細については、[Document Repository Configuration](https://help.liferay.com/hc/en-us/articles/360028810112-Document-Repository-Configuration)を参照してください。
