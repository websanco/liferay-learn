# 構成とプロパティの移行

現在のDXPインストールのOSGi構成（7.0以降）とプロパティ（[ポータルプロパティ](../../reference/portal-properties.md)や[システムプロパティ](../../reference/system-properties.md)など）によって、ニーズに合わせてDXPインスタンスがセットアップされます。 これらの設定を新しいDXPインスタンスで使用するには、それらを新しいLiferay Homeに移行して更新する必要があります。

## 概要

  - [Liferay Homeの移行](#migrating-liferay-home)
  - [データベースアップグレードの設定の更新](#updating-settings-used-by-the-database-upgrade)
  - [ポータルプロパティの移行](#migrating-portal-properties)

## Liferay Homeの移行

ポータルプロパティ（`portal-ext.properties`など）やOSGi構成（`.config`ファイル）など、現在のLiferay Homeのコンテンツを含む新しい[Liferay Home](../../reference/liferay-home.md)フォルダを設定します。

``` bash
cp /old-version/liferay-home/ /new-version/liferay-home/
```

あるいは、Liferay Homeがソース管理にある場合は、新しいDXPインスタンスが使用する新しいブランチを作成します。

``` bash
git checkout -b new-version
```

## データベースアップグレードの設定の更新

DXPおよび一部のマーケットプレイスアプリのアップグレードプロセスでは、ポータルプロパティとOSGi構成を使用します。 カスタムコードのアップグレードプロセスでも、プロパティの更新と構成の更新が必要になる場合があります。 これらの設定と更新は、データベースのアップグレード*前*に行う必要があります。 その他の更新は、データベースのアップグレード後に行うことができます。

DXPアップグレードプロセスに必要な設定の更新は次のとおりです。

  - [データベースドライバー](#database-driver)
  - ドキュメントライブラリストアの実装名（[Updating the File Store](./updating-the-file-store.md#updating-the-store-implementation-class-name)を参照）

``` important::
   マーケットプレイスアプリとカスタムコードで、必要な設定の更新を確認してください。
```

### データベースドライバー

推奨されるデータベースドライバーについては、データベースベンダーのドキュメントを確認してください。 新しいドライバーが推奨されている場合は、既存のドライバーのJARファイルを置き換え、`portal-ext.properties`ファイルの`jdbc.default.driverClassName`プロパティを新しいドライバークラス名で更新します。

MySQLの例：

``` properties
jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver
```

その他のドライバーの例については、[Database Templates](../../reference/database-templates.md)を参照してください。

## ポータルプロパティの移行

ここで説明するプロパティは、データベースのアップグレード後に更新できます。 プロパティの移行には、次の内容が含まれます。

  - [ブレードCLI](https://help.liferay.com/hc/en-us/articles/360029147071-Blade-CLI)を使用してプロパティの変更を報告する
  - プロパティをOSGi構成に変換する
  - プロパティの移行に関する特別な考慮事項

### ブレードCLIを使用して互換性のないプロパティを報告する

[ブレードCLI](https://help.liferay.com/hc/en-us/articles/360029147071-Blade-CLI)ツールの`upgradeProps`コマンドは、ポータルプロパティファイル間の変更を報告します。 このツールは、次のタイプの変更を報告します。

  - 更新されていない場合に例外を発生させるプロパティ。
  - モジュールの`portal.properties`ファイルに移動されたプロパティ。
  - OSGi構成に移動されたプロパティ。
  - 新しいDXPバージョンにはないプロパティ。

多くの場合、`upgradeProps`コマンドは、必要な更新を説明するか、プロパティの変更に関する詳細情報を参照します。

`blade upgradeProps`コマンドの形式：

``` bash
blade upgradeProps -p {old_liferay_home_path}/portal-ext.properties -d {new_liferay_home_path}
```

次に、`blade upgradeProps`コマンドを実行した場合の出力例を示します。

    Checking the location for old properties in the new version
    -----------------------------------------------------------
    Following portal properties present an exception:
            asset.categories.navigation.display.templates.config has been removed.  Overwrite the method in the ADT handler. Se
    e LPS-67466
            asset.publisher.display.templates.config has been removed.  Overwrite the method in the ADT handler. See LPS-67466
            ...
    
    Some properties have been moved to a module portlet.properties:
            asset.publisher.search.with.index can match with the following portlet properties:
                    search.with.database from osgi/marketplace/Liferay CE Web Experience - Liferay CE Asset - Impl.lpkg/com.lif
    eray.asset.browser.web-4.0.4.jar/portlet.properties
            dynamic.data.lists.error.template[ftl] can match with the following portlet properties:
                    dynamic.data.lists.error.template[ftl] from osgi/marketplace/Liferay CE Forms and Workflow - Liferay CE Dyn
    amic Data Lists - Impl.lpkg/com.liferay.dynamic.data.lists.web-5.0.4.jar/portlet.properties
            ...
    
    Properties moved to OSGI configuration:
            asset.publisher.check.interval can match with the following OSGI properties:
                    checkInterval from com.liferay.asset.publisher.web.internal.configuration.AssetPublisherWebConfiguration
            asset.publisher.display.style.default can match with the following OSGI properties:
                    defaultDisplayStyle from com.liferay.asset.publisher.web.internal.configuration.AssetPublisherPortletInstanceConfiguration
            ...
    
    We have not found a new property for the following old properties (check if you still need them or check the documentation to find a replacement):
            admin.email.password.sent.body
            admin.email.password.sent.subject
            ...

### プロパティをOSGi構成に変換する

モジュール化された機能のプロパティが変更され、[OSGi構成ファイル](https://help.liferay.com/hc/en-us/articles/360029131591-System-Settings#exporting-and-importing-configurations)（OSGi構成管理）にデプロイされるようになりました。

たとえば、6.2では、Simple File Storeがこのポータルプロパティを使用して、ストアのルートディレクトリを指定していました。

``` properties
dl.store.file.system.root.dir=${liferay.home}/data/document_library
```

これで、ストアは`com.liferay.portal.store.file.system.configuration.FileSystemStoreConfiguration.config`と呼ばれる`.config`ファイルで構成され、次のような設定でルートディレクトリを指定します。

``` properties
rootDir="{document_library_path}"
```

`.config`ファイルを`[Liferay Home]/osgi/configs`というフォルダに配置します。

``` tip::
   コントロールパネルの[*System Settings*]画面（[*Configuration*]の下）は、OSGi構成管理の値を管理します。 これらの画面は``.config``ファイルを作成する最も正確な方法です。 構成する機能の構成画面を見つけて[*Save*]をクリックし、オプションボタンを使用して画面の構成<https://help.liferay.com/hc/en-us/articles/360029131591-System-Settings#exporting-and-importing-configurations>を``.config``ファイルにエクスポートします。
```

### プロパティの移行に関する特別な考慮事項

特定の環境、Liferayのバージョン、および機能に関連するプロパティを移行するためのリソースがあります。 便宜上、ここで呼び出しています。

  - ファイルストア設定の更新については、[Updating the File Store](./updating-the-file-store.md)で説明しています。

  - Liferay Portal 6.1以前を使用している場合は、[Liferay Portal 6.2で導入された新しいデフォルトにプロパティを適合](https://help.liferay.com/hc/en-us/articles/360017903232-Upgrading-Liferay#review-the-liferay-62-properties-defaults)させてください。

  - シャード化された環境がある場合は、[シャード化されていない環境を生成するようにアップグレードを構成](../other-upgrade-scenarios/upgrading-a-sharded-environment.md)します。

  - [こちら](../reference/changes-to-default-settings.md)で7.0以降のデフォルト設定の変更を確認してください。

  - Liferayの画像スプライトフレームワークは7.2で非推奨になり、デフォルトでは無効になっています。 フレームワークには、画像スプライト用のスキャンプラグインが必要です。 フレームワークを使用しない場合、画像スプライトをスキャンするためにフレームワークを使用する必要はありません。 フレームワークを使用する場合は、デフォルトの`sprite.enabled`ポータルプロパティ（7.2以降）の値を、[`portal-ext.properties`](../../reference/portal-properties.md)ファイルの次の設定でオーバーライドすることにより、フレームワークを有効にします。

    ``` properties
    sprite.enabled=true
    ```

``` note::
   好きなフレームワークを使用して画像スプライトを作成し、プラグインにデプロイできます。
```

## 次のステップ

Liferayの設定を新しいDXPインスタンスで使用する準備ができました。 次に、[ファイルストアを更新](./updating-the-file-store.md)します。
