# Dockerによるアップグレード

DXP Dockerイメージは、イメージを実行するコマンドに自動アップグレードパラメータが渡されると、すべてのDXPアップグレードプロセスを呼び出します。 アップグレードプロセスが完了すると、DXPはアップグレードされたデータベースで起動します。
Dockerデスクトップは[こちら](https://www.docker.com/products/docker-desktop)から入手できます 。 Liferay Portal Community Edition (CE) Docker イメージは [ここ](https://hub.docker.com/r/liferay/portal)です。 <!-- Add DXP link once the 7.3 image is ready - jhinkey -->

``` important::
   重要なインストールとサブスクライバーの場合は、データベースアップグレードツールを使用してください。 詳細については、Using the Liferay Upgrade Tool <./using-the-database-upgrade-tool.md>を参照してください。
```

``` warning::
   アップグレードする前に、**必ず**データベースとインストールをバックアップ<../../maintaining-a-liferay-dxp-installation/backing-up.md>してください。 バックアップコピーでアップグレードプロセスをテストすることをお勧めします。
```

## 最新のDockerイメージによるアップグレード

Dockerイメージを使用する手順は次のとおりです。

1.  現在のLiferay Homeのコンテンツを含む新しい[Liferay Home](../../reference/liferay-home.md)フォルダを設定します。 この新しいLiferay Homeは、後の手順でDockerイメージにバインドします。

    ``` bash
    cp -r /old-version/liferay-home/* /new-version/liferay-home/
    ```

    または、現在のLiferay Homeがソース管理されている場合は、新しいブランチを作成します。

    ``` bash
    git checkout -b new-version
    ```

2.  データベースベンダーが推奨するJDBCデータベースドライバーを使用していることを確認してください。 たとえば、MySQLを使用している場合は、[`portal-ext.properties`](../../reference/portal-properties.md)で`jdbc.default.driverClassName=com.mysql.cj.jdbc.Driver`を設定し、アプリケーションサーバーが使用するMySQL JDBCドライバーのJARを置き換えます。 詳細については、[Database Drivers](../configuration-and-infrastructure/migrating-configurations-and-properties.md#database-drivers)を参照してください。

3.  `com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`ファイルで`indexReadOnly="true"`を設定して、データベースのアップグレード中にサーチインデックス作成を無効にします。

    ``` bash
    cd /new-version/liferay-home
    mkdir -p files/osgi/configs
    echo "indexReadOnly=\"true\"" > files/osgi/configs/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config
    ```

4.  次のコマンドを使用して、新しいLiferay HomeにマウントされたDXP Dockerイメージを実行し、必要に応じて環境値を置き換えます。

    ``` bash
    docker run -it -p 8080:8080 \
     -v /new-version/liferay-home:/mnt/liferay \
     -e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true \
     liferay/portal:7.3.1-ga2
    ```

    `-v /new-version/liferay-home:/mnt/liferay`引数は、ホストの`/new-version/liferay-home`フォルダをコンテナの`/mnt/liferay`にバインドマウントします。

    パラメータ`-e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true`は、データベースのアップグレードプロセスの実行をトリガーします。

5.  コンソールまたはログで、データベースのアップグレードとDXPの起動が成功したことを確認します。 アップグレードプロセスメッセージに、各アップグレードプロセスの開始と完了が表示されます。 次のようなメッセージは、DXPの起動の完了を示します。

    ``` bash
    org.apache.catalina.startup.Catalina.start Server startup in [x] milliseconds
    ```

    アップグレードの失敗やエラーがある場合は、ログと[Gogo Shellコマンド](../upgrade-stability-and-performance/upgrading-modules-using-gogo-shell.md)を使用して、トラブルシューティングを行い、アップグレードを完了できます。

6.  `indexReadOnly="false"`を設定するか、`com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config`ファイルを削除して、サーチインデックス作成を再度有効にします。

    ``` bash
    rm files/osgi/configs/com.liferay.portal.search.configuration.IndexStatusManagerConfiguration.config
    ```

7.  アップグレードしたデータベースを検証します。

    ![これがLiferay DXPのランディング画面です。](./upgrading-via-docker/images/01.png)

Liferay DXPデータベースのアップグレードが完了しました。

``` note::
   If you're done upgrading the database, leave off the ``-e LIFERAY_UPGRADE_PERIOD_DATABASE_PERIOD_AUTO_PERIOD_RUN=true`` environment setting from your Docker command the next time you run DXP.
```

## まとめ

アップグレードされたDXPデータベースで十分な場合は、新しいバージョンのDXPを使用してください。 アップグレードの完了の他に作業が必要な場合は、以下の記事を参考にしてください。

  - [Upgrade Overview](./upgrade-overview.md)では、アップグレードに関するすべてのトピックについて説明しています。 まだ対処しなければならないトピックが見つかるかもしれません。

  - [Using the Database Upgrade Tool](./using-the-database-upgrade-tool.md)では、DXPサーバーのオフライン時にデータベースをアップグレードする方法を示しています。 アップグレードに時間がかかりすぎる場合は、[データベースの調整](../upgrade-stability-and-performance/database-tuning-for-upgrades.md)、[不要なデータの削除](../upgrade-stability-and-performance/database-pruning-for-faster-upgrades.md)、データベースアップグレードツールの使用をお勧めします。

  - [Custom Code Upgrade](https://help.liferay.com/hc/en-us/articles/360029316391-Introduction-to-Upgrading-Code-to-Liferay-DXP-7-2)では、開発したカスタムプラグインコードを新しいDXPバージョンに適合させる方法を示しています。

  - [Maintaining Clustered Installations](../../maintaining-a-liferay-dxp-installation/maintaining-clustered-installations/maintaining-clustered-installations.md)では、クラスター環境でDXPをアップグレードする方法について説明しています。
