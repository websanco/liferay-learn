# 構成とプロパティの移行

現在のDXPインストールのOSGi構成（7.0以降）とプロパティ（[ポータルプロパティ](../../reference/portal-properties.md)や[システムプロパティ](../../reference/system-properties.md)など）によって、ニーズに合わせてDXPインスタンスがセットアップされます。 これらの設定を新しいDXPインスタンスで使用するには、それらを新しいLiferay Homeに移行して更新する必要があります。

<a name="overview" />

## 概要

* [Liferay Homeの移行](#migrating-liferay-home)
* [データベースアップグレードの設定の更新](#updating-settings-used-by-the-database-upgrade)
* [ポータルプロパティの移行](#migrating-portal-properties)

<a name="migrating-liferay-home-and-application-server-files" />

## Liferayホームおよびアプリケーションサーバーファイルの移行

1.  [バックアップ](../maintaining-a-liferay-installation/backing-up.md)からインストールに追加および編集した [Liferayホームファイル](../maintaining-a-liferay-installation/backing-up.md#liferay-home) および [アプリケーションサーバーファイル](../maintaining-a-liferay-installation/backing-up.md#application-server) をマージします。 ファイルには次のものが含まれる場合がありますが、これらに限定されません。

    * `/license/*`：アクティベーションキー。 (サブスクリプション)
    * `/log/*`：ログファイル。
    * `/osgi/configs/*.config`：OSGi設定ファイル。
    * `portal-*.properties`：`portal-ext.properties`のようなポータルプロパティファイル。
    * アプリケーションサーバーファイル：変更されたスクリプトと設定ファイル。
    * `web.xml`：ポータルWebアプリケーション記述子。

1. 新しいインストールの`［Liferay Home］/data`フォルダを、バックアップの`［Liferay Home］/data`フォルダと置き換えます。

3.  [ファイル ストア (ドキュメント ライブラリ)](../../system-administration/file-storage.md)を、[バックアップ](../maintaining-a-liferay-installation/backing-up.md)から新しいインストールにコピーするか、または [`.config`ファイル](../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md#creating-configuration-files) を介して使用するように新しいインストールを設定してセットアップします。

## データベースアップグレードの設定の更新

DXPおよび一部のマーケットプレイスアプリのアップグレードプロセスでは、ポータルプロパティとOSGi構成を使用します。 カスタムコードのアップグレードプロセスでも、プロパティの更新と構成の更新が必要になる場合があります。 これらの設定と更新は、データベースのアップグレード **前** に行う必要があります。 その他の更新は、データベースのアップグレード後に行うことができます。

DXPアップグレードプロセスに必要な設定の更新は次のとおりです。

  - [データベースドライバー](#database-drivers)
  - ドキュメントライブラリストアの実装名（ [Updating the File Store](./reference/file-store-updates.md#updating-the-store-implementation-class-name) を参照）

<!-- end list -->

```{important}
   マーケットプレイスアプリとカスタムコードで、必要な設定の更新を確認してください。
```

### データベースドライバー

推奨されるデータベースドライバーについては、データベースベンダーのドキュメントを確認してください。 新しいドライバーが推奨されている場合は、既存のドライバーのJARファイルを置き換え、`portal-ext.properties`ファイルの`jdbc.default.driverClassName`プロパティを新しいドライバークラス名で更新します。

MySQLの例：

```properties
jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
```

その他のドライバーの例については、 [データベーステンプレート](../reference/database-templates.md) を参照してください。

## ポータルプロパティの移行

```{important}
   ``locales`` [ポータルプロパティ](../../../installation-and-upgrades/reference/portal-properties.md) をオーバーライドした場合は、アップグレードする前に新しいインストールでそれをオーバーライドしてください。 これにより、すべてのロケールのデータが確実にアップグレードされます。
```

ここで説明するプロパティは、データベースのアップグレード後に更新できます。 プロパティの移行には、次のアクションが含まれます。

* `liferay.home`プロパティを更新する（変更している場合）
* [Blade CLI](../../../developing-applications/tooling/blade-cli/installing-and-updating-blade-cli.md)を使用してプロパティの変更を報告する
* プロパティをOSGi構成に変換する
* プロパティの移行に関する特別な考慮事項

### Blade CLIを使用して互換性のないプロパティを報告する

[Blade CLI](../../../developing-applications/tooling/blade-cli/installing-and-updating-blade-cli.md)ツールの`upgradeProps`コマンドは、ポータルプロパティファイル間の変更を報告します。 このツールは、次のタイプの変更を報告します。

* 更新されていない場合に例外を発生させるプロパティ。
* モジュールの`portal.properties`ファイルに移動されたプロパティ。
* OSGi構成に移動されたプロパティ。
* 新しいDXPバージョンにはないプロパティ。

多くの場合、`upgradeProps`コマンドは、必要な更新を説明するか、プロパティの変更に関する詳細情報を参照します。

`blade upgradeProps`コマンドの形式：

```bash
blade upgradeProps -p {old_liferay_home_path}/portal-ext.properties -d {new_liferay_home_path}
```

次に、`blade upgradeProps`コマンドを実行した場合の出力例を示します。

```
...
index.search.query.suggestion.dictionary
    MODULARIZE AS OSGI - This property matches with the following OSGI config, select the most appropriate:
        - searchQueryResultWindowLimit from com.liferay.portal.search.configuration.DefaultSearchResultPermissionFilterConfiguration

index.search.spell.checker.dictionary
    KEEP - This property is still present in the new portal.properties.

sites.friendly.url.page.not.found
    KEEP - This property is still present in the new portal.properties.

web.server.protocol
    KEEP - This property is still present in the new portal.properties.
...
```

### プロパティをOSGi構成に変換する

モジュール化された機能のプロパティが変更され、[OSGi構成ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md)（OSGi構成管理）にデプロイされるようになりました。

たとえば、6.2では、Simple File Storeがこのポータルプロパティを使用して、ストアのルートディレクトリを指定していました。

```properties
dl.store.file.system.root.dir=${liferay.home}/data/document_library
```

これで、ストアは`com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.config`と呼ばれる`.config`ファイルで構成され、次のような設定でルートディレクトリを指定します。

```properties
rootDir="{document_library_path}"
```

`.config`ファイルを`［Liferay Home］/osgi/configs`というフォルダに配置します。

```tip::
   コントロールパネルの［*System Settings*］画面（［*Configuration*］の下）は、OSGi構成管理の値を管理します。 これらの画面は ``.config`` ファイルを作成する最も正確な方法です。 構成する機能の構成画面を見つけて*保存*をクリックし、オプションボタンを [使用して画面の構成](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md) を ``.config`` ファイルにエクスポートします。
```

### プロパティの移行に関する特別な考慮事項

特定の環境、Liferayのバージョン、および機能に関連するプロパティを移行するためのリソースがあります。 便宜上、ここで呼び出しています。

1. ファイルストア設定の更新については、[Updating the File Store](./updating-the-file-store.md)で説明しています。

2.  Liferay Portal 6.1以前を使用している場合は、 [Liferay Portal 6.2で導入された新しいデフォルトにプロパティを適合](https://help.liferay.com/hc/ja/articles/360017903232-Upgrading-Liferay#review-the-liferay-62-properties-defaults) させてください。

1. シャード化された環境がある場合は、[シャード化されていない環境を生成するようにアップグレードを構成](../other-upgrade-scenarios/upgrading-a-sharded-environment.md)します。

1. [7.3](../reference/default-setting-changes-in-7-3.md)および[7.2](../reference/default-setting-changes-in-7-2.md)でのデフォルトのポータルプロパティの変更を調べます。

1. Liferayの画像スプライトフレームワークは7.2で非推奨になり、デフォルトでは無効になっています。 フレームワークには、画像スプライト用のスキャンプラグインが必要です。 フレームワークを使用しない場合、画像スプライトをスキャンするためにフレームワークを使用する必要はありません。 フレームワークを使用する場合は、デフォルトの`sprite.enabled`ポータルプロパティ（7.2以降）の値を、[`portal-ext.properties`](../../reference/portal-properties.md)ファイルの次の設定でオーバーライドすることにより、フレームワークを有効にします。

    ```properties
    sprite.enabled=true
    ```

   ```{note}
      好きなフレームワークを使用して画像スプライトを作成し、プラグインにデプロイできます。
   ```

6.  7.3以降、キャッシュはEhcache XMLファイルを使用してのみ構成されます。 ポータルプロパティでキャッシュを有効にしたり設定したりすることができなくなりました。 ポータルプロパティを使用してキャッシュを構成した場合は、モジュール内のEhcache XMLファイルを使用してキャッシュを構成してください。 詳細は、 [キャッシュ構成](https://help.liferay.com/hc/ja/articles/360035581451-Introduction-to-Cache-Configuration) を参照してください。

## 次のステップ

Liferayの設定を新しいDXPインスタンスで使用する準備ができました。 次に、[ファイルストアを更新](./updating-the-file-store.md)します。
