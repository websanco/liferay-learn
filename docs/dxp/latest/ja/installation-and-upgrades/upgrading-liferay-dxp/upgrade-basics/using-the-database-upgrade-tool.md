# データベースアップグレードツールの使用

Liferayデータベースアップグレードツールは、DXPデータベースをオフラインでアップグレードするためのクライアントプログラムです。 DXPから切り離されているデータベースを変更すると、次のことが可能になります。

  - [アップグレード操作のためにデータベースを調整する](../upgrade-stability-and-performance/database-tuning-for-upgrades.md)
  - [不要なデータを削除](../upgrade-stability-and-performance/database-tuning-for-upgrades.md)（不要なバージョンのWebコンテンツ、ドキュメントなど）して、アップグレードのパフォーマンスを向上させる
  - アップグレードの問題を解決する

上記のトピックは、大規模なエンタープライズレベルのDXP環境を安全かつ可能な限り迅速にアップグレードするために特に重要です。 データベースの調整と削除を考慮し、[Upgrade Overview](./upgrade-overview.md)で説明されている関連タスクを完了すると、アップグレードツールを使用してデータベースをアップグレードする準備が整います。

``` warning::
   アップグレードする前に、**必ず**データベースとインストールをバックアップ<../../maintaining-a-liferay-dxp-installation/backing-up.md>してください。 バックアップコピーでアップグレードプロセスをテストすることをお勧めします。
```

``` important::
   6.2以前からアップグレードする場合は、ファイルストアの構成を更新します。 詳細については、Updating the File Store <../configuration-and-infrastructure/updating-the-file-store.md>を参照してください。
```

## アップグレードツールのインストールと設定

Liferay DXP Tomcatバンドルには、次のフォルダにアップグレードツールが含まれています。

    [Liferay Home]/tools/portal-tools-db-upgrade-client

このツールは、次の表に示すように、個別にダウンロードできます。

| DXP版                   | ダウンロード手順                                                                                                                                                                  |
|:---------------------- |:------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Liferay DXP（サブスクリプション） | [*ダウンロード*ページ](https://customer.liferay.com/group/customer/downloads)に移動し、DXPバージョンと*製品/サービスパック*のファイルタイプを選択します。 表示されるリストで、*Liferay DXPアップグレードクライアント*用の*[Download]* をクリックします。 |
| LiferayポータルCE          | [*ダウンロード*ページ](https://www.liferay.com/downloads-community)に移動し、*Liferay Portal Tools for 7.x*の*[Download]* を選択します。                                                         |

ツールのコマンドラインインターフェイスを使用して実行するか、[プロパティファイルを使用](../reference/database-upgrade-tool-reference.md#manual-configuration)して、アップグレードツールを設定します。

## アップグレードツールの使用状況

`[Liferay Home]/tools/portal-tools-db-upgrade-client`フォルダにある`db_upgrade.sh`スクリプトは、アップグレードツールを呼び出します。 `--help`オプションは、ツールの使用状況を説明します。

``` bash
db_upgrade.sh --help
```

次のパラメータを使用してログファイルを指定できます。

    -l <logfile>

JVMオプションは、次の形式を使用して渡されます。

    -j "<JVM Options>"

### アップグレードツールのJVMオプションの推奨事項

DXPアプリケーションサーバーに使用するものと同じJVMオプションでアップグレードツールを設定します。 ファイルのエンコード（`UTF-8`）、タイムゾーン（`GMT`）、国、言語、メモリの設定（`-Xmx値`）はすべて、アプリケーションサーバーの設定と一致している必要があります。 データが10 GB以上のデータベースの場合、デフォルトの2 GBよりも多くのメモリを割り当てることをお勧めします。

JVMオプションとログファイルを指定するコマンドの例を次に示します。

``` bash
db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.timezone=GMT -Xmx2048m" -l "output.log"
```

## アップグレードツールの実行

ツールを使用してデータベースをアップグレードするには：

1.  現在のLiferay Homeのコンテンツを含む新しい[Liferay Home](../../reference/liferay-home.md)フォルダを設定します。

    ``` bash
    cp /old-version/liferay-home/ /new-version/liferay-home/
    ```

    現在のLiferay Homeがソース管理されている場合は、新しいブランチを作成します。

    ``` bash
    git checkout -b new-version
    ```

2.  データベースベンダーが推奨するJDBCデータベースドライバーを使用していることを確認してください。 たとえば、MySQLを使用している場合は、[`portal-ext.properties`](../../reference/portal-properties.md)で`jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver`を設定し、アプリケーションサーバーが使用するMySQL JDBCドライバーのJARを置き換えます。 詳細については、[Database Drivers](../configuration-and-infrastructure/migrating-configurations-and-properties.md#database-drivers)を参照してください。

3.  アップグレードツールを実行します。 次にコマンドの例を示します。

    ``` bash
    cd /new-version/liferay-home/tools/portal-tools-db-upgrade-client
    db_upgrade.sh -j "-Dfile.encoding=UTF-8 -Duser.timezone=GMT -Xmx2048m" -l "output.log"
    ```

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

4.  アップグレードが完了したら、ログでデータベースのアップグレードの失敗やエラーがないか確認します。 [Gogo Shellコマンド](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md)を使用して、それらをトラブルシューティングし、アップグレードを完了できます。

5.  アップグレード固有の調整を元に戻し、[Post-Upgrade Considerations](./post-upgrade-considerations.md)を確認して、DXPのテストの準備をします。

6.  サーバーを起動し、アップグレードしたデータベースでDXPを検証します。

    ![これがLiferay DXPのランディング画面です。](./using-the-database-upgrade-tool/images/01.png)

アップグレードツールを使用してDXPデータベースをアップグレードしました。

これが試用版のアップグレードで、アップグレード時間を短縮したい場合は、データベースをアップグレード用に調整し（まだ行っていない場合）、[不要なデータがないか確認](../upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md)してデータベースから削除してください。 必要に応じてこの記事を繰り返してください。

## 次のステップ

[Upgrade Overview](./upgrade-overview.md)を再確認して、アップグレードに必要な残りの作業について確認してください。
