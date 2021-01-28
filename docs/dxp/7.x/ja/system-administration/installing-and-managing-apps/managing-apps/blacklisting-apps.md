# アプリのブラックリスト登録

バンドルブラックリストは、複数のアプリ、OSGiバンドル（モジュール）、WARプラグインを一度にアンインストールまたは再インストールする便利な方法です。 [アプリケーションマネージャー](./using-the-app-manager.md)または[Gogoシェル](https://help.liferay.com/hc/articles/360029070351-Using-the-Felix-Gogo-Shell)を使用して、アプリ、モジュール、プラグインのインストールを個別に管理する手間を省きます。

``` important::
   The blacklist is an `OSGi configuration <../../system-settings/using-configuration-files.md#creating-configuration-files>`_ that DXP uses to uninstall apps. これを使用すると、リストされているアプリは削除されるまでインストールされなくなります。
```

リスト [は、コントロールパネル](#blacklisting-via-the-control-panel) で設定するか、 [コントロールパネルから](../../system-settings/using-configuration-files.md#creating-configuration-files) エクスポートすることでOSGi構成</a> （`.config`）ファイルを使用して
設定できます。</p> 



## ブラックリスト登録

DXPは、ブラックリストにあるインストール済みのアプリ、モジュール、プラグインをすべて削除します。 ブラックリストに登録されている間はインストールできません。 ログには、各アンインストールが記録されます。



``` note::
   LPKGをブラックリストに登録すると、その内部モジュールがすべてアンインストールされます。
```




### コントロールパネルを介したブラックリスト登録

次の手順に従って、アプリ、モジュール、プラグインをブラックリストに登録します。

1.  コントロールパネルで、*[Configuration]* → *[System Settings]* → *[Module Container]* に移動します。 [Bundle Blacklist]画面が表示されます。

2.  [Bundle Blacklist]画面で、アンインストールする[モジュール](https://help.liferay.com/hc/articles/360035467532-OSGi-and-Modularity#modules)JAR、LPKGファイル、またはWARのバンドルシンボリック名（[下の表](#blacklist-bundle-symbolic-names)を参照）を追加します。 完了したら、*[保存]* ボタンをクリックします。 DXPはブラックリストに登録されたモジュールをすぐにアンインストールします。
   
   ![このブラックリストは、com.liferay.docs.greeting.apiモジュール、Liferay マーケットプレイスアプリのLPKG、およびクラシックテーマのプラグインWARをアンインストールします。](./blacklisting-apps/images/02.png)



### 構成ファイルによるブラックリスト登録

ブラックリストは、コントロールパネルからOSGi構成（`.config`）ファイルにエクスポートすることもできます。 ファイルを変更してDXPにデプロイすると、次のような追加の効果があります。

  - DXPサーバーの起動時の変更を保持します
  - ローカルクラスターノードから他のすべてのノードに変更を伝播します。

次の手順に従って、構成ファイルを使用してブラックリストに登録します。

1.  現在使用中のブラックリストをエクスポートするには、そのアクションボタン（![アクション](./blacklisting-apps/images/03.png)）をクリックしてから、*[Export]* をクリックします。 次に、ブラックリスト構成ファイルがダウンロードされます（`com.liferay.portal.bundle.blacklist.internal。BundleBlacklistConfiguration.config`）。 次に、サンプルのブラックリストをエクスポートしたときのファイルの内容を示します。 
   
   

    ``` properties
    blacklistBundleSymbolicNames=["com.liferay.docs.greeting.api","Liferay\ Marketplace","classic-theme"]
    ```


2.  アンインストールして以降のDXPサーバーの起動時にインストールしないようにする、リストにまだないアプリ、モジュール、またはプラグインのバンドルシンボリック名を追加します。 
   
   

    ``` warning::
       構成値に余分なスペースを含めることはできません。 余分なスペースがあると、リストが短絡したり、構成エントリが無効になったりする可能性があります。
    ```


3.  構成ファイルをデプロイするには、フォルダ`[Liferay Home]/osgi/configs`にコピーします。 [Liferay Home](../../../installation-and-upgrades/reference/liferay-home.md)フォルダは通常、アプリケーションサーバーの親フォルダです。



### ブラックリストバンドルのシンボリック名

| タイプ           | バンドルのシンボリック名                                                                                          |
| ------------- | ----------------------------------------------------------------------------------------------------- |
| LPKG          | 拡張子`.lpkg`なしのLPKGファイル名                                                                                |
| バンドル/モジュールJAR | `bnd.bnd`または`MANIFEST.MF`ファイルの`Bundle-SymbolicName`                                                   |
| WAR           | `liferay-plugin-package.properties`ファイル内のサーブレットコンテキスト名、またはサーブレットコンテキスト名プロパティがない場合はWARファイル名（`.war`を削除） |




## ブラックリスト登録されているアイテムの再インストール

ブラックリストに登録されたアイテムを再インストールするには、次の手順に従います。

1.  構成ファイル`com.liferay.portal.bundle.blacklist.internalBundleBlacklistConfiguration.config`を開きます。

2.  LPKG、モジュールJAR、またはWARのシンボリック名を`blacklistBundleSymbolicNames`リストから削除し、ファイルを保存します。

ブラックリスト登録されている*すべての*アイテムを再インストールするには、次のいずれかのオプションを実行します。

  - 構成ファイルを削除します。
  - [アプリケーションマネージャー](./using-the-app-manager.md)または[Felix Gogo Shell](https://help.liferay.com/hc/articles/360029070351-Using-the-Felix-Gogo-Shell)を使用して、モジュール`com.liferay.portal.bundle.blacklist`をアンインストールします。

<!-- end list -->

``` tip::
   ブラックリストに登録されているアイテムを一時的に再インストールするには、[*System Settings*]のバンドルブラックリストモジュールからそのシンボリック名を削除し、[*Update*]ボタンをクリックします。 If you're using a blacklist config file (in the ``[Liferay Home]/osgi/configs`` folder) and want the item to install on subsequent server startup, make sure to remove the item's symbolic name from the file.
```


ログには、各アイテムのインストールが記録されます。

簡単なリストを使用して、複数のアプリ、モジュール、プラグインのインストールを管理できるようになりました。



## 追加情報

  - [Managing Apps](./using-the-app-manager.md)
  - [Using the Felix Gogo shell](https://help.liferay.com/hc/articles/360029070351-Using-the-Felix-Gogo-Shell)
  - [Blacklisting OSGi Components](./blacklisting-osgi-components.md)
  - [Configuring Portlets, Themes, and Layout Templates](./configuring-portlets-themes-and-layout-templates.md)
