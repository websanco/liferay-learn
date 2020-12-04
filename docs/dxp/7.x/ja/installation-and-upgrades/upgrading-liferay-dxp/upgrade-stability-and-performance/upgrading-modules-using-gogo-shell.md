# Gogoシェルを使用したモジュールのアップグレード

特定のモジュールのアップグレードの問題をトラブルシューティングするには、モジュールごとに（まとめてではなく）アップグレードをテストして実行することが必要になる場合があります。 Liferayには、個々のモジュールをアップグレードおよび検証するためのGogoシェルコマンドがあります。

たとえば、モジュールに新しい[データスキーママイクロバージョン](https://help.liferay.com/hc/en-us/articles/360030959231-Meaningful-Schema-Versioning)があるとします。 これは唯一の新しいマイクロバージョンであるため、モジュールデータを新しいスキーマにアップグレードすることはオプションです。 新しいモジュールバージョンをデプロイすると、アクティブになりますが、そのデータはアップグレードされません。 モジュールを新しいデータスキーマにアップグレードする場合は、Gogoシェルの`upgrade`コマンドを使用できます。

ただし、新しい[データスキーマのマイナー/メジャーバージョン](https://help.liferay.com/hc/en-us/articles/360030959231-Meaningful-Schema-Versioning)を持つモジュールを、そのデータをアップグレードせずにデプロイすると、モジュールは非アクティブになります。 新しいモジュールバージョンをアクティブ化するには、モジュールのデータを新しいデータスキーマにアップグレードする必要があります。

モジュールのアップグレードに関するトピックは次のとおりです。

  - [Gogoシェルコマンドの使用](#command-usage)
  - [アップグレード可能なモジュールのリスト](#listing-module-ready-for-upgrade)
  - [モジュールの依存関係のトラブルシューティング](#troubleshooting-module-dependencies)
  - [モジュールのアップグレードの実行](#executing-module-upgrades)
  - [アップグレードステータスの確認](#checking-upgrade-status)
  - [検証プロセスの実行](#executing-verify-processes)

## コマンドの使用

[Gogoシェルポートレット](https://help.liferay.com/hc/en-us/articles/360029070351-Using-the-Felix-Gogo-Shell)を使用して、モジュールのアップグレードおよび検証コマンドを実行します。

コマンドは次のとおりです。

| コマンド                            | 説明                                                        |
|:------------------------------- |:--------------------------------------------------------- |
| `exit`または`quit`                 | Gogoシェルを終了します                                             |
| `upgrade:help`                  | アップグレードコマンドを表示します                                         |
| `upgrade:check`                 | 過去に失敗したか、モジュールが最終バージョンに達していないため、実行が保留されているアップグレードを一覧表示します |
| `upgrade:execute [module_name]` | そのモジュールのアップグレードを実行します                                     |
| `upgrade:executeAll`            | 保留中のすべてのモジュールアップグレードプロセスを実行します                            |
| `upgrade:list`                  | 登録されているすべてのアップグレードを一覧表示します                                |
| `upgrade:list [module_name]`    | モジュールに必要なアップグレード手順を一覧表示します                                |
| \`upgrade:list                | grep Registered\`                                       |
| `verify:help`                   | 検証コマンドを表示します                                              |
| `verify:check [module_name]`    | モジュールの検証プロセスの最新の実行結果を一覧表示します                              |
| `verify:checkAll`               | すべての検証プロセスの最新の実行結果を一覧表示します                                |
| `verify:execute [module_name]`  | モジュールの検証機能を実行します                                          |
| `verify:executeAll`             | すべての検証機能を実行します                                            |
| `verify:list`                   | 登録されているすべての検証機能を一覧表示します                                   |

次に、各モジュールのアップグレードの可用性を調べます。

## アップグレード可能なモジュールのリスト

依存関係が満たされれば、モジュールはアップグレードの準備ができています。 準備ができているモジュールを一覧表示するGogoシェルコマンドと、モジュールの未解決の依存関係を一覧表示するコマンドがあります。

`upgrade:list` Gogoシェルコマンドは、アップグレードの依存関係が満たされているモジュールを一覧表示します。 これらのモジュールはアップグレードできます。

モジュールがアクティブだがリストにない場合は、その依存関係をアップグレードする必要があります。

## モジュールの依存関係のトラブルシューティング

未解決の依存関係を見つけるためのGogoシェルコマンドがあるので、それらを修正してモジュールをアップグレード可能な状態にできます。 Gogoシェルコマンド`scr:info [upgrade_step_class_qualified_name]`は、アップグレードステップクラスの満たされていない依存関係を示します。 次に`scr:info`コマンドの例を示します。

    scr:info com.liferay.journal.upgrade.JournalServiceUpgrade

モジュールのアップグレードステップ（クラス）は順番に解決する必要があります。 `upgrade:list [module_name]`を呼び出すと、モジュールのすべてのアップグレードステップが一覧表示されます。 たとえば、`upgrade:list com.liferay.bookmarks.service`（ブックマークサービスモジュールの場合）を実行すると、次のように表示されます。

    Registered upgrade processes for com.liferay.bookmarks.service 1.0.0
            {fromSchemaVersionString=0.0.0, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.portal.spring.extender.internal.context.ModuleApplicationContextExtender$ModuleApplicationContextExtension$1@6e9691da}
            {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0-step-3, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletId@5f41b7ee}
            {fromSchemaVersionString=1.0.0-step-1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletSettings@53929b1d}
            {fromSchemaVersionString=1.0.0-step-2, toSchemaVersionString=1.0.0-step-1, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradeLastPublishDate@3e05b7c8}
            {fromSchemaVersionString=1.0.0-step-3, toSchemaVersionString=1.0.0-step-2, upgradeStep=com.liferay.bookmarks.upgrade.v1_0_0.UpgradeClassNames@6964cb47}

アプリケーションのアップグレードステップクラス名は、通常、その意図を表します。 たとえば、サンプルの`com.liferay.bookmarks.upgrade.v1_0_0.UpgradePortletId`アップグレードステップクラスは、アプリのポートレットIDを更新します。 他の例のアップグレードステップクラスは、クラス名、`LastPublishDate`、および`PortletSettings`を更新します。 この例の`0.0.0`～`1.0.0`のステップでは、空のデータベースからモジュールをアップグレードします。

モジュールのアップグレードプロセスをより詳しく調べるために、リストされているアップグレードステップを頭の中で、またはテキストエディタで並べ替えることができます。 以下は、ブックマークサービスモジュールをLiferay Portal6.2（モジュールのデータベースが存在する）からスキーマバージョン`1.0.0`にアップグレードするためのアップグレードステップの順序です。

  - `0.0.1`～`1.0.0-step-3`
  - `0.0.1-step-3`～`1.0.0-step-2`
  - `0.0.1-step-2`～`1.0.0-step-1`
  - `0.0.1-step-1`～`1.0.0`

モジュール全体のアップグレードプロセスは、バージョン`0.0.1`から始まり、バージョン`1.0.0`で終わります。 最初のステップは、初期バージョン（`0.0.1`）から始まり、ターゲットバージョンの最も高いステップ（`step-3`）で終了します。 最後のステップは、ターゲットバージョンの最も低いステップ（`step-1`）から始まり、ターゲットバージョン（`1.0.0`）で終了します。

モジュールのアップグレードプロセスを理解することで、自信を持って実行できます。

## モジュールのアップグレードの実行

`upgrade:execute [module_name]`を実行すると、モジュールがアップグレードされます。 解決する必要があるアップグレードエラーが発生する場合があります。 コマンドを再度実行すると、最後に成功したステップからアップグレードが開始されます。

`upgrade:list [module_name]`を実行すると、アップグレードステータスを確認できます。 たとえば、`upgrade:list com.liferay.iframe.web`と入力すると、次のように出力されます。

    Registered upgrade processes for com.liferay.iframe.web 0.0.1
       {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

1行目には、モジュールの名前と現在のバージョンがリストされます。 サンプルモジュールの現在のバージョンは`0.0.1`です。 `toSchemaVersionString`値はターゲットバージョンです。

正常にアップグレードした後、モジュールで`upgrade:list [module_name]`を実行すると、モジュールの名前に続いてターゲットバージョンが表示されます。

たとえば、`com.liferay.iframe.web`をバージョン`1.0.0`に正常にアップグレードした場合、`upgrade:list com.liferay.iframe.web`を実行すると、モジュールのバージョンが`1.0.0`であることが示されます。

    Registered upgrade processes for com.liferay.iframe.web 1.0.0
       {fromSchemaVersionString=0.0.1, toSchemaVersionString=1.0.0, upgradeStep=com.liferay.iframe.web.upgrade.IFrameWebUpgrade$1@1537752d}

完了していないモジュールのアップグレードについては、そのステータスを確認して問題を解決できます。

## アップグレードステータスの確認

コマンド`upgrade:check`は、アップグレードが差し迫っているモジュールを一覧表示します。

たとえば、モジュール`com.liferay.dynamic.data.mapping.service`が`1.0.0-step-2`というラベルの付いたステップで失敗した場合、`upgrade:check`を実行すると次のように表示されます。

    Would upgrade com.liferay.dynamic.data.mapping.service from 1.0.0-step-2 to
    1.0.0 and its dependent modules

モジュールは、アップグレードを完了するために他のモジュールに依存することがよくあります。 `scr:info [upgrade_step_class_qualified_name]`を実行すると、アップグレードステップクラスの依存関係が表示されます。 モジュールが最初に依存するモジュールをアップグレードする必要があります。

モジュールを解決してアクティブ化するには、そのアップグレードを完了する必要があります。 [Apache Felix Dependency Manager](http://felix.apache.org/documentation/subprojects/apache-felix-dependency-manager/tutorials/leveraging-the-shell.html)のGogoシェルコマンド`dm wtf`は、未解決の依存関係を明らかにします。 モジュールで特定のデータスキーマバージョンが必要（たとえば、その`bnd.bnd`が`Liferay-Require-SchemaVersion: 1.0.2`を指定している）だが、モジュールがそのバージョンへのアップグレードを完了していない場合、`dm wtf`はスキーマバージョンが登録されていないことを示します。

    1 missing dependencies found.
    -------------------------------------
    The following service(s) are missing:
     * com.liferay.portal.kernel.model.Release (&(release.bundle.symbolic.name=com.liferay.journal.service)(release.schema.version=1.0.2)) is not found in the service registry

`dm wtf`コマンドは、ポートレット定義およびカスタムポートレットの`schemaVersion`フィールドのエラー検出にも役立ちます。

### Release\_テーブルの確認

各モジュールには1つの`Release_`テーブルレコードがあり、その`schemaVersion`フィールドの値は`1.0.0`以上である必要があります。 `1.0.0`は、バージョン6.2以前を対象とした従来のプラグインであったものを除き、Liferay DXPモジュールの初期バージョンです。

## 検証プロセスの実行

一部のモジュールには検証プロセスがあります。 これらは、アップグレードが正常に実行されたことを確認します。 Liferay DXPのアップグレード後に、コアのプロセスが自動的に実行されることを確認します。 [`verify.*`ポータルプロパティ](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html#Verify)を設定してサーバーを再起動することで、これらを実行することもできます。

使用可能な検証プロセスを確認するには、Gogoシェルコマンド`verify:list`を入力します。 検証プロセスを実行するには、`verify:execute [verify_qualified_name]`と入力します。

## 関連トピック

  - [Liferay Commands and Standard Commands Available in Gogo Shell](https://help.liferay.com/hc/en-us/articles/360029070351-Using-the-Felix-Gogo-Shell)
