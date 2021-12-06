# データベースアップグレードツールの使用

Liferayデータベースアップグレードツールは、Liferay DXPデータベースおよびLiferay Portal CEデータベースをオフラインでアップグレードするためのクライアントプログラムです。

```{important}
アップグレードする前に、**必ず** データベースとインストールを [バックアップ](../../maintaining-a-liferay-installation/backing-up.md) してください。 バックアップコピーでアップグレードプロセスをテストすることをお勧めします。
```

```{important}
6.2以前からアップグレードする場合は、ファイルストアの構成を更新します。 詳細は、 [Updating the File Store](../reference/file-store-updates.md) を参照してください。
```

Liferayインスタンスから切り離された状態でデータベースを変更すると、[データベースをアップグレード操作用に調整](../upgrade-stability-and-performance/database-tuning-for-upgrades.md)し、[不要なデータ（Webコンテンツ、ドキュメントなどの不要なバージョンなど）を削除](../upgrade-stability-and-performance/database-tuning-for-upgrades.md)して、アップグレードのパフォーマンスを向上させ、アップグレードの問題を解決できます。  これらのアクティビティは、DXPをはじめとする大規模で重要なポータルCE環境を安全かつ迅速にアップグレードするために特に重要です。 データベースの調整と削除を考慮し、[Upgrade Basics](../upgrade-basics.md)で説明されている関連タスクを完了すると、新しいインストールをセットアップし、アップグレードツールを使用してデータベースをアップグレードする準備が整います。

[新しいLiferay Dockerイメージにアップグレード](../../installing-liferay/using-liferay-docker-images/upgrading-to-a-new-docker-image.md)していて、データベースアップグレードツールを使用する場合は、新しいLiferayバージョンの[Liferay Tomcat Bundle](../../installing-liferay/installing-a-liferay-tomcat-bundle.md)からそのツールを使用します。

## 新規インストール時の設定

1.  新しいLiferayのリリースをインストールします。 新しいLiferay Dockerイメージ用のデータベースをアップグレードする場合は、 [Liferay Tomcat Bundle](../../installing-liferay/installing-a-liferay-tomcat-bundle.md)をインストールします。

2.  新しいインストールの`[Liferay Home]/data`フォルダを[バックアップ](../../maintaining-a-liferay-installation/backing-up.md)の`[Liferay Home]/data`フォルダに置き換えます。

3.  [高度なファイルシステムストア](../../../system-administration/file-storage.md)または[簡易ファイルシステムストア](../../../system-administration/file-storage/other-file-store-types/simple-file-system-store.md)を使用していて、保存場所を変更している場合は、ファイルストアの設定を[`.config`ファイル](../../../system-administration/configuring-liferay/configuration-files-and-factories/using-configuration-files.md#creating-configuration-files)にエクスポートして、新しい`[Liferay Home]/osgi/configs/`フォルダにコピーします。

    ```{important}
    [高度なファイルシステムストア](../../../system-administration/file-storage.md) を使用している場合は、データベースをアップグレードする前に、新しいインストールで `.config` ファイルを使って設定する必要があります。

    以下に例を示します。`com.liferay.portal.store.file.system.configuration.AdvancedFileSystemStoreConfiguration.config` file with the required `rootDir` parameter:

    `rootDir="data/document_library"`
    ```

4.  DXPのアクティベーションキー（サブスクリプション）とOSGiの設定ファイルを、 [バックアップ](../../maintaining-a-liferay-installation/backing-up.md#liferay-home) から新しいインストールにコピーします。

5.  データベースベンダーが推奨するJDBCデータベースドライバーを使用していることを確認してください。 たとえば、MySQLを使用している場合は、[`portal-ext.properties`](../../reference/portal-properties.md)で`jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver`を設定し、アプリケーションサーバーが使用するMySQL JDBCドライバーのJARを置き換えます。 詳細は、[データベースドライバー](../configuration-and-infrastructure/migrating-configurations-and-properties.md#database-drivers)を参照してください。

    [新しいLiferay Dockerイメージにアップグレード](../../installing-liferay/using-liferay-docker-images/upgrading-to-a-new-docker-image.md)する場合は、Docker環境変数の代わりに[ポータルプロパティ](../../reference/portal-properties.md)を使用してデータベース接続を指定してください。 [ポータルプロパティのリファレンス](https://docs.liferay.com/dxp/portal/7.3-latest/propertiesdoc/portal.properties.html)には、対応するポータルプロパティが各Liferay環境変数とともに一覧表示されています。

6.  インストールがLiferay Tomcat Bundleの場合、`[Liferay Home]/tools/portal-tools-db-upgrade-client`にアップグレードツールが含まれています。 それ以外の場合は、ツールをダウンロードして、そのフォルダにインストールしてください。

| DXP版                   | ダウンロード手順                                                                                                                                                                                                                                    |
|:---------------------- |:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Liferay DXP（サブスクリプション） | [*ダウンロード*ページ](https://customer.liferay.com/group/customer/downloads)に移動し、DXPバージョンと*製品/サービスパック*のファイルタイプを選択します。 表示されるリストで、*Liferay DXPアップグレードクライアント*用の*[Download]* をクリックします。                                                                   |
| LiferayポータルCE          | [*[Downloads]* ページ](https://www.liferay.com/downloads-community)に移動します。 *[Liferay Portal]* ダウンロードメニューで、*[Other files]* を選択して、*[ダウンロード]* をクリックします。 最新のLiferay PortalのリリースアセットのGitHubページが表示されます。 `liferay-ce-portal-tools-[version].zip`をクリックします。 |

## アップグレードツールの実行

アップグレードツールの設定は、コマンドラインインターフェイスで行うか、[プロパティファイル](../reference/database-upgrade-tool-reference.md#manual-configuration)を使って行います。

`[Liferay Home]/tools/portal-tools-db-upgrade-client`フォルダにある`db_upgrade.sh`スクリプトは、アップグレードツールを呼び出します。 `--help`オプションは、ツールの使用状況を説明します。

``` bash
db_upgrade.sh --help
```

ここでは、アップグレードツールを使ってデータベースをアップグレードする手順を説明しています。

1.  アップグレードツールを起動します。 次にコマンドの例を示します。

    ``` bash
    cd liferay-home/tools/portal-tools-db-upgrade-client
    db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.timezone=GMT -Xmx4096m" -l "output.log"
    ```

    上記のコマンドは、アプリケーションサーバーで推奨されているのと同じJVMオプションでアップグレードツールを実行します。 ファイルのエンコード（`UTF-8`）、タイムゾーン（`GMT`）、国、言語、メモリの設定（`-Xmx値`）はすべて、アプリケーションサーバーの設定と一致している必要があります。 データが10 GB以上のデータベースの場合、デフォルトの4 GBよりも多くのメモリを割り当てることをお勧めします。 `-l "[file]"`引数は、アップグレードツールのログメッセージを指定したファイルに直接出力します。

    [アップグレードプロパティファイル](../reference/database-upgrade-tool-reference.md#manual-configuration)を作成していない場合、アップグレードツールによって構成値の入力が求められ、括弧内にデフォルト値が表示されます。 次に対話の例を示します。

     Please enter your application server (tomcat):
     tomcat
    
     Please enter your application server directory (../../tomcat-9.0.17):
    
     Please enter your extra library directories (../../tomcat-9.0.17/bin):
    
     Please enter your global library directory (../../tomcat-9.0.17/lib):
    
     Please enter your portal directory (../../tomcat-9.0.17/webapps/ROOT):
    
     [ db2 mariadb mysql oracle postgresql sqlserver sybase ]
     Please enter your database (mysql):
     mariadb
    
     Please enter your database host (localhost):
    
     (etc.)

    プロンプトに表示されるデフォルト値を使用する場合は、Enterキーを押します。

    構成が完了すると、アップグレードが開始されます。 ログファイルを監視できます。 ログメッセージでは、各アップグレードプロセスの開始と完了が報告されます。

2.  アップグレードが完了したら、ログでデータベースのアップグレードの失敗やエラーがないか確認します。 [Gogo Shellコマンド](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md)を使用して、それらの問題をトラブルシューティングし、アップグレードを完了できます。

データベースのアップグレードが完了し、問題が解決されました。

## アップグレードされたデータベースのテスト

データベースのアップグレードが完了したので、テストしてみましょう。

1.  新しいLiferay Dockerイメージにアップグレードしている場合は、イメージをアップグレードされたデータベースに指定し、Liferayをデータベースで検証します。 詳しくは、[Configuring Liferay Containers](../../installing-liferay/using-liferay-docker-images/configuring-containers.md)を参照してください。

2.  [アップグレード後の検討事項](./post-upgrade-considerations.md)を調べます。

3.  カスタム[Liferay Homeのファイル](../../maintaining-a-liferay-installation/backing-up.md#liferay-home)と[アプリケーションサーバーのファイル](../../maintaining-a-liferay-installation/backing-up.md#application-server)をバックアップから新規インストールにコピーしてマージします。 ファイルには次のものが含まれる場合がありますが、これらに限定されません。

      - `/license/*`：アクティベーションキー。 (サブスクリプション)
      - `/log/*`：ログファイル。
      - `/osgi/configs/*.config`：OSGi設定ファイル。
      - `portal-*.properties`：`portal-ext.properties`のようなポータルプロパティファイル。
      - アプリケーションサーバーファイル：変更されたスクリプトと設定ファイル。
      - `web.xml`：ポータルWebアプリケーション記述子。

4.  新しいインストール先で[ポータルプロパティを更新](../configuration-and-infrastructure/migrating-configurations-and-properties.md#migrating-portal-properties)します。

5.  サーバーを起動し、アップグレードしたデータベースでLiferayを検証します。

    ![これがLiferay DXPのランディング画面です。](./using-the-database-upgrade-tool/images/01.png)

データベースアップグレードツールを使用してLiferayデータベースをアップグレードしました。

これが試用版のアップグレードで、アップグレード時間を短縮したい場合は、データベースをアップグレード用に調整し（まだ行っていない場合）、データベースから[不要なデータを削除](../upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md)してください。 必要に応じてこの記事の手順を繰り返してください。

## 次のステップ

[Upgrade Basics](../upgrade-basics.md)を再確認して、アップグレードに必要な残りの作業について確認してください。
