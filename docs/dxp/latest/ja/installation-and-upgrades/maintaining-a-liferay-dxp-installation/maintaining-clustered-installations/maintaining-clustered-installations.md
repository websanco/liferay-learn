# クラスター化されたインストールの維持

[クラスター](../../setting-up-liferay/clustering-for-high-availability.md)で機能するようにLiferay DXPのインストールを設定すると、パフォーマンスとスケーラビリティが向上しますが、適切にサポートおよび維持するために追加の考慮事項も必要です。 これには、新規および更新されたプラグインとモジュールのデプロイ、[パッチとフィックスパックのインストール](../patching-liferay/patching-liferay.md)、構成の変更などが含まれます。 概説されているLiferay DXPクラスターのメンテナンス方法は、サーバーの稼働時間を最大化し、サーバーメンテナンスのリスクを最小限に抑えます。 Liferay DXPは、標準のクラスターメンテナンス技法の使用をサポートしています。

  - [ローリング再起動](./rolling-restarts.md)：ノードはシャットダウンされ、一度に1つずつ更新されます。
  - [ブルーグリーンデプロイ](./blue-green-deployments.md)：ブルーグリーンには、現在の環境（*ブルー*環境）の複製、複製の更新（*グリーン*環境）、および更新された環境（グリーン）へのユーザーのカットオーバーが含まれます。

これらの手法を以下で比較します。

## クラスター更新手法

| Update                                       | ローリング再起動 | ブルーグリーン |
| -------------------------------------------- | -------- | ------- |
| プラグイン/モジュールのインストール                           | ✔        | ✔       |
| プラグイン/モジュールの更新（下位互換性のあるデータ/スキーマの変更）          | ✔        | ✔       |
| プラグイン/モジュールの更新（下位互換性のないデータ/スキーマの変更）[1](#one) |          | ✔       |
| フィックスパックのインストールと削除（復元可能なフィックスパック）            | ✔        | ✔       |
| フィックスパックのインストール（元に戻せないフィックスパック）              |          | ✔       |
| クラスターコードの変更[2](#two)                         |          | ✔       |
| ポータルプロパティの変更                                 | ✔        | ✔       |
| 構成管理ファイルによるシステム設定の変更                         | ✔        | ✔       |
| アプリケーションサーバーの更新                              | ✔        | ✔       |
| JVM設定の変更                                     | ✔        | ✔       |
| 新しいJavaバージョン（マイナー）                           | ✔        | ✔       |
| 新しいJavaバージョン（メジャー）                           |          | ✔       |

\[<a name="one">1</a>\]下位互換性のないデータおよびデータスキーマの変更には、次のものがありますが、これらに限定されません。

  - 既存の列のデータを変更する
  - 列の削除
  - 列タイプの変更
  - 列で使用されるデータ形式の変更（XMLからJSONへの変更など）
  - Service Builderサービスモジュールのデータスキーマを、モジュールの必要なデータスキーマ範囲外のバージョンに更新する。 モジュールの`Liferay-Require-SchemaVersion`（`bnd.bnd`で指定）は、`Release_`テーブルのモジュールのスキーマバージョン値と一致する必要があります。 新しいスキーマバージョンでモジュールをインストールすると、`Release_`テーブルがそのスキーマバージョンで更新され、データアップグレードプロセスがトリガーされます。 そのようなモジュールを1つのノードにインストールすると、`Release_`テーブルのスキーマバージョンが他のノード上のモジュールの`Liferay-Require-SchemaVersion`と一致しなくなり、モジュールが他のノードにインストールされるまで、モジュールのService Builderサービスが利用できなくなります。 このような変更は元に戻せません。データベースをバックアップから復元する必要があります。 これらのスキーマバージョンの変更は、すべてのノードがシャットダウンされている間に適用する必要があります。

\[<a name="two">2</a>\]クラスター通信はそのまま維持する必要があります。 このため、ローリング再起動ではクラスターコードを更新しないでください。 カスタマーポータルは、元に戻せないこのような変更を含むDXPフィックスパックを識別します。 以下は、ローリング再起動で変更してはならないパッケージです。

  - `com.liferay.portal.kernel.cluster`
  - `com.liferay.portal.kernel.cluster.*`
  - `com.liferay.portal.kernel.exception.NoSuchClusterGroupException`
  - `com.liferay.portal.kernel.scheduler.multiple`
  - `com.liferay.portal.kernel.scheduler.multiple.*`
  - `com.liferay.portal.cache.multiple`
  - `com.liferay.portal.cache.multiple.*`
  - `com.liferay.portal.scheduler.multiple`
  - `com.liferay.portal.scheduler.multiple.*`

## 追加情報

  - [Rolling Restarts](./rolling-restarts.md)
  - [Blue-Green Deployments](./blue-green-deployments.md)
  - [サーバー管理](./../../../system-administration/using-the-server-administration-panel/using-the-server-administration-panel.md)
