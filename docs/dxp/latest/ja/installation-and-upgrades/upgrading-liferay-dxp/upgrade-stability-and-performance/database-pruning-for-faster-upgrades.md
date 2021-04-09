# より高速なアップグレードのためのデータベースのプルーニング

データが多いほど、データのアップグレードにかかる時間が長くなります。 不要なサイトデータはよく発生します。 不要なデータのデータベースを削除することで、アップグレードプロセスのパフォーマンスが向上します。

たとえば、サイトには、未使用バージョンのWebコンテンツの記事やドキュメントおよびメディアファイルが多数保存されている場合があります。 それらの改訂が完了していて、中間リビジョンが必要ない場合は、安全に削除できます。 これにより、スペースとアップグレード時間が節約されます。

データベースのプルーニングに関するトピックは次のとおりです。

  - 重複するWeb コンテンツストラクチャーのフィールド名を削除する
  - 未使用のオブジェクトを見つけて削除する
  - プルーニングされたデータベースのコピーを使用してテストする

## 重複するWeb コンテンツストラクチャーのフィールド名を削除する

Webコンテンツ管理を広範囲に使用している場合、固有のフィールド名のない構造が存在する可能性があります。 アップグレードする前に、重複するフィールド名を見つけて削除してください。 以前にLiferay Portal 6.2にアップグレードし、この作業をスキップした場合、次のエラーが発生します。

    19:29:35,298 ERROR [main][VerifyProcessTrackerOSGiCommands:221] com.liferay.portal.verify.VerifyException: com.liferay.dynamic.data.mapping.validator.DDMFormValidationException$MustNotDuplicateFieldName: The field name page cannot be defined more than once
    com.liferay.portal.verify.VerifyException: com.liferay.dynamic.data.mapping.validator.DDMFormValidationException$MustNotDuplicateFieldName: The field name page cannot be defined more than once

このエラーが発生した場合は、Liferay Portal 6.2の以前のバックアップにロールバックし、重複するフィールド名を見つけて削除してください。

## 未使用のオブジェクトを見つけて削除する

UIで未使用のオブジェクトを識別するか、またはデータベースで`SELECT`クエリを使用して識別します。 次に、UI、[スクリプトコンソール](../../../system-administration/using-the-script-engine/running-scripts-from-the-script-console.md)を介したAPI、または作成したポートレットのいずれかを使用してそれらを削除します。

``` warning::
   LiferayのUIまたはAPIはLiferay DXPのオブジェクト間の関係を考慮しているため、データの操作にはLiferayのUIまたはAPIのみを使用してください。 データベースでSQLを直接使用してレコードを削除しないでください。 SQLがオブジェクトの関係を見失い、オブジェクトが孤立し、パフォーマンスの問題が発生する可能性があります。
```

次に、未使用のオブジェクトを確認する一般的な場所をいくつか示します。

### 大規模なテーブルまたは設定済みのテーブルのオブジェクト

テーブルの行は、Liferay DXPのオブジェクトにマップされます。 多数のレコードがある大規模なテーブルには、多くの未使用のオブジェクトが含まれている場合があります。 テーブルサイズとテーブルごとのレコード数が大きいほど、アップグレードに時間がかかります。

このようなテーブルに関連付けられている未使用のオブジェクトを見つけて削除すると、アップグレード時間が短縮されます。 Liferayのバックアップからデータをインポートすると、貴重なテーブル情報が得られます。 データベースエンジンには、さまざまな形でこの情報が表示されます。 たとえば、データベースのインポートログは次のようになります。

    Processing object type SCHEMA\_EXPORT/TABLE/TABLE\_DATA
    
    imported "LIFERAY"."JOURNALARTICLE" 13.33 GB 126687 rows
    
    imported "LIFERAY"."RESOURCEPERMISSION" 160.9 MB 1907698 rows
    
    imported "LIFERAY"."PORTLETPREFERENCES" 78.13 MB 432285 rows
    
    imported "LIFERAY"."LAYOUT" 52.05 MB 124507 rows
    
    imported "LIFERAY"."ASSETENTRY" 29.11 MB 198809 rows
    
    imported "LIFERAY"."MBMESSAGE" 24.80 MB 126185 rows
    
    imported "LIFERAY"."PORTALPREFERENCES" 4.091 MB 62202 rows
    
    imported "LIFERAY"."USER\_" 17.32 MB 62214 rows
    
    ...

サンプルのデータベースインポートでは、いくつかの項目が際立っています。

  - `JOURNALARTICLE`テーブルは、データベースサイズの98％を占めています。
  - 多数の`RESOURCEPERMISSION`レコードがあります。
  - 多数の`PORTLETPREFERENCES`レコードがあります。

目立つテーブルに関連付けられている未使用のオブジェクトを検索し、LiferayのAPI（例：[スクリプトコンソール](../../../system-administration/using-the-script-engine/running-scripts-from-the-script-console.md)を使用）を使用して不要なオブジェクトを削除します。

### 確認すべき一般的なオブジェクトタイプ

一部の特定のオブジェクトタイプでは、未使用のオブジェクトがないか確認する必要があります。 これらを確認する理由は次のとおりです。

  - それらを削除すると、関連する未使用のオブジェクトが削除できるようになります
  - 保持する必要のないバージョンのオブジェクトである可能性があります

次のオブジェクトタイプを確認します。

  - **サイト**：不要なサイトを削除します。 サイトを削除すると、次のような関連オブジェクトが削除されます。

      - レイアウト
      - ポートレット設定
      - ファイルエントリ（ドキュメントライブラリオブジェクト）
      - アセットエントリ
      - タグ
      - ボキャブラリとカテゴリ
      - Expandoフィールドとその値
      - `ResourcePermission`オブジェクト
      - サイトに固有のその他すべてのオブジェクト

  - **インスタンス**：未使用のインスタンスはまれですが、それらは階層内の最上位のオブジェクトであるため、それらのオブジェクトを削除すると、アップグレードを大幅に最適化できます。 インスタンスを削除すると、それらに関連付けられている次のオブジェクトが削除されます。

      - サイト（およびすべての関連コンテンツ）
      - ユーザー
      - ロール
      - 組織
      - グローバル`ResourcePermission`オブジェクト

  - **中間Webコンテンツバージョン：** Liferay DXPは、変更（翻訳を含む）後に新しいWebコンテンツバージョンを生成します。 不要なバージョンは削除することを検討してください。 特に、削除されたバージョンにそれらのバージョンに固有のイメージファイルなどのオブジェクトが含まれている場合、削除することでかなりの領域を解放できます。 詳細については、[Example: Removing Intermediate Journal Article Versions](./example-removing-intermediate-journal-article-versions.md)を参照してください。

  - **ドキュメントのバージョン**：ジャーナル記事と同様に、中間ドキュメントバージョンが不要な場合は削除してください。 これにより、データベースとファイルシステムの両方で領域が節約されます。

  - **レイアウト：**レイアウトはサイトページであり、ポートレットのルック&フィール、権限、アセット、評価などの他のエンティティに関連しているため、アップグレードのパフォーマンスに影響します。 不要なレイアウトは削除してください。

  - **ロール**：不要なロールを削除します。 それらを削除すると、関連する`ResourceBlockPermission`オブジェクトと`ResourcePermission`オブジェクトも削除されます。

  - **ユーザー：**非アクティブで不要になったユーザーを削除します。

  - **ボキャブラリ**：未使用のボキャブラリを削除します。 ボキャブラリを削除すると、そのカテゴリも削除されることに注意してください。

  - **孤立データ**：何にも接続されていない未使用のオブジェクトがないか確認します。 次にいくつかの例を示します。

      - ファイルシステムデータのない`DLFileEntries`。
      - 存在しないロール、レイアウト、ユーザー、ポートレットインスタンスなどに関連付けられている`ResourcePermission`オブジェクト。
      - 存在しないポートレットまたはレイアウトに関連付けられている`PortletPreference`オブジェクト。 これは、多くの組み込みポートレットがある環境でよく見られます。 これらのポートレットインスタンスには異なるライフサイクルがあり、ポートレットがテンプレートから削除されても削除されません。

中間オブジェクトバージョンの削除例については、[Example: Removing Intermediate Journal Article Versions](./example-removing-intermediate-journal-article-versions.md)をご覧ください。

次に、プルーニングされたデータベースを使用してインスタンスをテストします。

## プルーニングされたデータベースのコピーを使用してテストする

削除したオブジェクトに関連する問題を見つけて解決します。 これは、オブジェクトが誤って削除された場合や、他のコンテンツに影響を与える場合に重要な手順です。 問題を解決できない場合は、いつでも本番データベースの新しいコピーのプルーニングを再開できます。

## 追加情報

  - [Example: Removing Intermediate Journal Article Versions](./example-removing-intermediate-journal-article-versions.md)
