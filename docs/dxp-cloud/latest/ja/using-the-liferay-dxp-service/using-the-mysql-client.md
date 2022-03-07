# MySQLクライアントの使用

アプリケーションの問題のトラブルシューティングやカスタム開発を行うために、データベース内のデータを表示することが必要になる場合があります。 データベースにアクセスするには、組み込みのMySQLクライアントを使用して、データベースと直接やり取りする必要があります。 この機能は、DXP Cloudの最新バージョンで利用でき、いくつかの短い手順でアクセスできます。

```{note}
   管理者*と貢献者*の権限を持つDXP CloudユーザーのみがMySQLクライアントを使用することができます。
```

<a name="prerequisites" />

## 前提条件

Liferayサービスを通じてMySQLクライアントを使用する前に、サービスを少なくとも以下のサポートされているイメージバージョンにアップグレードする必要があります：

| **Service** | **サポートされる最小イメージバージョン** |
| ---------------- | ----------------------------------------------- |
| **データベース** | liferaycloud/database：3.2.8                     |
| **DXP (7.0を使用**) | liferaycloud/liferay-dxp：7.0.10-ga1-fp90-3.0.19 |
| **DXP（7.1を使用**） | liferaycloud/liferay-dxp-7.1.10-ga1-fp17-3.0.19 |
| **DXP（7.2を使用**） | liferaycloud/liferay-dxp-7.2.10-sp1-fp4-3.0.19  |

```{warning}
   データベースイメージを MySQL クライアントをサポートするバージョンにアップグレードすると、データベースの読み取り専用ユーザが初期化されます。 アップグレード前にこのユーザーのパスワードを設定しなかった場合、デフォルトのパスワードが使用され、後で変更することはできません。 詳細は [Changing the Read-Only Database Password](#changing the read-only-database-password) を参照してください。
```

<a name="accessing-the-mysql-client" />

## MySQLクライアントへのアクセス

1. DXP Cloudコンソールにログインします。

1. 適切な環境に移動し、 ［**サービス**］ をクリックします：

   ![サービスに移動して、ご使用の環境のすべてのサービスを表示します。](./using-the-mysql-client/images/01.png)

1. ［**liferay** サービス］をクリックします。

1. ［**Shell**］ タブをクリックします：

    ![［シェル］タブをクリックして、MySQLクライアントが使用可能なシェルにアクセスします。](./using-the-mysql-client/images/03.png)

1. この画面のシェルに `mysql` と入力します。 これにより、MySQLクライアントにログインし、読み取り専用のクエリを実行できます。 たとえば、 `show tables;`を実行すると、すべてのテーブルを表示できます。

    ![mysqlコマンドとshow tablesコマンドを実行します](./using-the-mysql-client/images/04.png)

利用可能なすべてのコマンドについては、公式の [MySQL Client documentation](https://dev.mysql.com/doc/refman/8.0/en/mysql-commands.html) を参照してください。

<a name="logging-in-with-read-and-write-privileges" />

### 読み取りおよび書き込み権限でのログイン

デフォルトのユーザーは、データベースに対して読み取りクエリのみを実行でき、データを操作することはできません。 これにより、偶発的な修正でサービスのデータが破損することを防ぐことができます。

しかし、データベース内のデータを操作できることが **重要** であれば、シェルにこのコマンドを入力することで（ `mysql`とだけ入力するのではなく）、データベースの認証情報を使ってログインすることができます。

```bash
mysql -u <user_name> -p <database_name>
```

データベース名、ユーザー名、パスワードは、 `portal.properties` ファイルで確認できます。

<a name="changing-the-read-only-database-password" />

### 読み取り専用データベースのパスワードの変更

サポートされているバージョンのデータベース サービスをまだデプロイしていない場合は、データベース サービスの `LCP_DATABASE_READONLY_USER_PASSWORD` 環境変数の `LCP.json`に独自のパスワードを設定することで、デフォルト ユーザーに独自のパスワードを設定することができます。

```{important}
   MySQL クライアントをサポートするバージョンを使用してデータベースサービスをデプロイしている場合、デフォルトユーザーはすでにデフォルトパスワードで初期化されています。 このパスワードは後で変更することはできないので、データベースサービスをデプロイする前に `LCP_DATABASE_READONLY_USER_PASSWORD` 環境変数を追加してください(初めてデプロイする場合や、上記よりも古いバージョンのイメージから更新する場合など)。 それ以外の場合は、デフォルトの生成されたパスワードを使用する必要があります。
```

次に、適切なDockerイメージバージョン（またはそれ以降）に更新し、[サービスを再デプロイ](../build-and-deploy/deploying-changes-via-the-dxp-cloud-console.md)し、MySQLクライアントを使用できるようにします。

`LCP_DATABASE_READONLY_USER_PASSWORD`でデフォルトユーザーのパスワードを設定した場合、同じ環境変数を `liferay` サービスに追加します。 それ以外の場合は、この変数を追加しないでください。 これにより、サービスはデフォルトのパスワードを使用します。

この変数は、 ［**環境変数**］ タブ内から追加できます。

![必要に応じて、［環境変数］タブをクリックしてパスワードを設定します。](./using-the-mysql-client/images/02.png)

<a name="related-information" />

## 関連情報

* [データベースサービス](../platform-services/database-service/database-service.md)
