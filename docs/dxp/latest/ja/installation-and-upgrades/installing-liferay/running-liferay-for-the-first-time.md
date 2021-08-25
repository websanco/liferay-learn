# Liferayを初めて実行する

[Liferay DXPをインストール](./installing-a-liferay-tomcat-bundle.md#installing)して[データベースを構成](./configuring-a-database.md)すると、Liferay DXPを実行する準備が整います。

## Liferay DXPを起動する

1.  アプリケーションサーバーにバンドルされている起動スクリプトを実行します。 Tomcatバンドルの例：

    ``` bash
    ./liferay-dxp-version/tomcat-version/bin/startup.sh
    ```

    ``` note::
       By default, DXP writes log files to ``[Liferay Home]/logs``
    ```


    Webブラウザの`http://localhost:8080`にセットアップウィザードが表示されます。
    
    ![On completing startup, DXP launches a web browser that displays the Basic Configuration page.](./running-liferay-for-the-first-time/images/01.png)

2.  ポータルの*[Name]*、*[Default Language]* および*[Time Zone]* を設定します。

3.  *管理者ユーザー*の名、姓、メールアドレスを設定します。

4.  *[Database]* セクションで、*[Change]* をクリックしてデータベースフォームを表示します。

    ``` warning::
       本番環境グレードのLiferay DXPインスタンスではHSQLを使用しないでください。
    ```

    ![セットアップウィザードのデータベースフォームでは、DXP用に作成したデータベースを指定できます。](./running-liferay-for-the-first-time/images/02.png)

5.  データベースを指定します。

| フィールド           | 説明                                |
| --------------- | --------------------------------- |
| *Database Type* | 接続するデータベースの種類を選択します               |
| *JDBC URL*      | Liferay DXP用に作成したデータベースへのパスを更新します |
| *User Name*     | データベースのユーザ名                       |
| *Password*      | データベースのユーザーパスワード                  |

     6. *サンプルデータ*について：本番環境グレードのDXPインスタンスを作成している場合、またはデータが不要な場合は、サンプルデータフィールドを選択しないでください。 サンプルデータには、デモンストレーション用のユーザー、サイト、組織が含まれています。

     7. *[Finish Configuration]* をクリックします。

セットアップウィザードは、設定値を[Liferay Home](../reference/liferay-home.md)の`portal-setup-wizard.properties`ファイルに保存します。

Liferay DXP Enterpriseサブスクリプションをお持ちの場合、DXPはアクティベーションキーを要求します。 [Activating Liferay DXP](../setting-up-liferay/activating-liferay-dxp.md)を参照してください。

最後に、サーバーを再起動するようプロンプト表示されます。

## サーバーを再起動する

アプリケーションサーバーにバンドルされているシャットダウンスクリプトおよび起動スクリプトを使用してサーバーを再起動します。 Tomcatのサンプルコマンド：

### シャット ダウン

``` bash
./liferay-dxp-version/tomcat-version/bin/shutdown.sh
```

### 起動

``` bash
./liferay-dxp-version/tomcat-version/bin/startup.sh
```

DXPは、セットアップウィザードで指定したデータベースとポータルの設定値を使用して初期化します。 DXPのホームページが`http://localhost:8080`に表示されます。

![DXPを構成してサーバーを再起動すると、DXPのホームページが表示され、サインインできるようになります\!](./running-liferay-for-the-first-time/images/03.png)

オンプレミスのLiferay DXPインスタンスを起動しました。

## 次のステップ

[管理者ユーザーとしてサインイン](../../getting-started/introduction-to-the-admin-account.md)して、[DXPでソリューションの構築](../../building-solutions-on-dxp/README.md)を開始できます。 または、 [追加のLiferay DXPセットアップ](../setting-up-liferay.md) トピックを調べることもできます。

  - [アプリのインストールと管理](../../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md)
  - [試用期間中のプラグインへのアクセス](../../system-administration/installing-and-managing-apps/installing-apps/accessing-ee-plugins-during-a-trial-period.md)
  - [検索エンジンのインストール](../../using-search/installing-and-upgrading-a-search-engine/introduction-to-installing-a-search-engine.md)
  - [Liferay DXPのセキュリティ](../securing-liferay/introduction-to-securing-liferay.md)
  - [高可用性のクラスタリング](../setting-up-liferay/clustering-for-high-availability.md)
