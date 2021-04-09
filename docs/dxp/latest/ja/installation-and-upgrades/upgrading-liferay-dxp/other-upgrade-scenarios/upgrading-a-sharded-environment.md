# シャード化された環境のアップグレード

Liferay DXP 7.0以降、Liferayはデータベースベンダーがネイティブに提供する機能を優先して、独自の物理パーティショニング実装（シャーディングとも呼ばれる）を削除しました。 シャード化されたインストールをDXP 7.0以降にアップグレードするには、シャードと同じ数のシャード化されていないLiferay DXPインストール（サーバー）に移行する必要があります。 これらの手順では、以前にシャード化されたデータを使用するように新しいLiferay DXPサーバーを構成する方法を説明します。

``` note::
   Liferayは、論理パーティション機能（仮想インスタンス<https://help.liferay.com/hc/en-us/articles/360028818692-Setting-Up-a-Virtual-Instance>とも呼ばれます）を引き続きサポートしています。
```

## データのアップグレードの前に構成を追加する

他の構成に加えて、データをアップグレードするためにシャードを仮想インスタンスに移行するには、より多くのプロパティを設定する必要があります。 他の構成の詳細については、[Upgrade Tool Reference](../reference/database-upgrade-tool-reference.md)を参照してください。

シャーディングから移行するようにアップグレードを構成する方法は次のとおりです。

1.  すべてのシャードJDBC接続プロパティを`portal-ext.properties`から`portal-upgrade-database.properties`にコピーします。 たとえば、デフォルトのシャードと2つのデフォルト以外のシャードのJDBC接続は次のようになります。

    ``` properties
    jdbc.default.driverClassName=[the database driver class name]
    jdbc.default.url=[the URL to the default database shard]
    jdbc.default.username=[the user name]
    jdbc.default.password=[the password]

    jdbc.one.driverClassName=[the database driver class name]
    jdbc.one.url=[the URL to database shard one]
    jdbc.one.username=[the user name]
    jdbc.one.password=[the password]

    jdbc.two.driverClassName=[the database driver class name]
    jdbc.two.url=[the URL to database shard two]
    jdbc.two.username=[the user name]
    jdbc.two.password=[the password]
    ```

2.  各サーバーの`portal-upgrade-database.properties`でJDBCの*デフォルトの*接続プロパティを設定して、関連するシャードを指定します。

      - デフォルト以外の各シャードデータベースに元のJDBCプロパティを追加します。 たとえば、シャード`1`の元のプロパティが`jdbc.one`から始まっているとします。
    
    <!-- end list -->
    
    ``` properties
    jdbc.one.driverClassName=[the database driver class name]
    jdbc.one.url=[the URL to database shard one]
    jdbc.one.username=[the user name]
    jdbc.one.password=[the password]
    ```

      - プロパティの名前を`jdbc.default`で始まるように変更します。 例:
    
    <!-- end list -->
    
    ``` properties
    jdbc.default.driverClassName=[the database driver class name]
    jdbc.default.url=[the URL to database shard one]
    jdbc.default.username=[the user name]
    jdbc.default.password=[the password]
    ```

## プロパティのアップグレードと更新

データベースのアップグレードを実行するときは、デフォルトのシャードを最初にアップグレードしてから、デフォルト以外の各シャードをアップグレードします。 データベースアップグレードの実行の詳細については、[Using the Database Upgrade Tool](../upgrade-basics/using-the-database-upgrade-tool.md)を参照してください。

データベースのアップグレードが完了したら、アプリケーションサーバーの構成を次のように変更します。

1.  各サーバーの`portal-ext.properties`で、`portal-upgrade-database.properties`で指定したJDBCの*デフォルトの*プロパティを使用します（上記の*デフォルトの*プロパティを参照）。

2.  デフォルトのシャードデータベースの`jdbc.default`プロパティのみを残して、デフォルト以外のシャードJDBCのプロパティをデフォルトのシャードサーバーの`portal-ext.properties`ファイルから削除します。 例:

    古いJDBCプロパティ：

    ``` properties
    jdbc.default.driverClassName=[the database driver class name]
    jdbc.default.url=[the URL to the default database shard]
    jdbc.default.username=[the user name]
    jdbc.default.password=[the password]

    jdbc.one.driverClassName=[the database driver class name]
    jdbc.one.url=[the URL to database shard one]
    jdbc.one.username=[the user name]
    jdbc.one.password=[the password]

    jdbc.two.driverClassName=[the database driver class name]
    jdbc.two.url=[the URL to database shard two]
    jdbc.two.username=[the user name]
    jdbc.two.password=v[the password]
    ```

    新しいJDBCプロパティ：

    ``` properties
    jdbc.default.driverClassName=[the database driver class name]
    jdbc.default.url=[the URL to your database]
    jdbc.default.username=[the user name]
    jdbc.default.password=[the password]
    ```

これらのすべての手順を完了すると、DXPのアップグレードとともに、シャード化された環境から別のLiferay DXPサーバー上の仮想インスタンスに移行されます。

アップグレードを完了するためのガイダンスについては、[Upgrade Overview](../upgrade-basics/upgrade-overview.md)を参照してください。
