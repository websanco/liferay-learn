# Liferay-Tomcatバンドルのインストール

Tomcatバンドルには、Liferay DXPが事前にデプロイされたApache Tomcatアプリケーションサーバーが含まれています。 これは、Liferay DXPをオンプレミスにインストールする最も簡単で最速の方法です。

``` note::
   If you're using one of the following application servers already, click the name of the one you're using to see instructions for deploying DXP to it: `Tomcat <./installing-liferay-on-an-application-server/installing-on-tomcat.md>`_, `WildFly <./installing-liferay-on-an-application-server/installing-on-wildfly.md>`_, `JBoss EAP <./installing-liferay-on-an-application-server/installing-on-jboss-eap.md>`_, `WebLogic <./installing-liferay-on-an-application-server/installing-on-weblogic.md>`_, or `WebSphere <./installing-liferay-on-an-application-server/installing-on-websphere.md>`_.
```

``` note::
   To start a Liferay DXP instance fast for touring or demonstration purposes, see `Starting With a Docker Image <../../getting-started/starting-with-a-docker-image.md>`_.
```

## 前提条件

Liferay DXPにはJava JDK 8または11が必要です。 参照してください [互換性マトリックス](https://www.liferay.com/documents/10182/246659966/Liferay+DXP+7.2+Compatibility+Matrix.pdf/ed234765-db47-c4ad-7c82-2acb4c73b0f9) JDK分布を選択します。

## DXPをダウンロードする

1.  [ヘルプセンター](https://help.liferay.com/hc)（サブスクリプション）または[コミュニティダウンロード](https://www.liferay.com/downloads-community)にアクセスします。

2.  必要なLiferay DXPバージョンに移動します。

3.  Tomcatバンドルをダウンロードします。

| ファイル                   | 説明                                 |
|:---------------------- |:---------------------------------- |
| Tomcatバンドル（`.tar.gz` ） | 任意のOSにインストールされるgzip圧縮されたバンドルアーカイブ  |
| Tomcatバンドル（`.7z`）      | 任意のOSにインストールされる7-Zip圧縮されたバンドルアーカイブ |

## DXPをインストールする

バンドルをDXPホスト上の場所に展開します。 この場所は、[Liferay Home](../reference/liferay-home.md)と呼ばれます。

Liferay DXPをインストールしました。 次にデータベースを設定します。

## 次のステップ

  - [Configuring a Database](./configuring-a-database.md)
