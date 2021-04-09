# Liferay DXP Tomcatバンドルのインストール

Tomcatバンドルには、Liferay DXPが事前にデプロイされたApache Tomcatアプリケーションサーバーが含まれています。 これは、Liferay DXPをオンプレミスにインストールする最も簡単で最速の方法です。

``` note::
   `Tomcat <./installing-liferay-on-an-application-server/installing-liferay-dxp-on-tomcat.md>`_ 、WildFly、JBoss EAP, WebLogic、または `WebSphere <./installing-liferay-on-an-application-server/installing-liferay-on-websphere.md>`_ のいずれかのアプリケーションサーバーをすでに使用している場合は、使用しているアプリケーションサーバーの名前をクリックして、DXPをデプロイする手順を確認してください。
```

``` note::
   ツアーやデモンストレーションの目的でLiferay DXPインスタンスをすばやく起動するには、 `Starting with a DXP Docker Image <../../getting-started/starting-with-a-dxp-docker-image.md>`_ を参照してください。
```

## 前提条件

Liferay DXPには**Java JDK 8または11**が必要です。 JDKをインストールするには、[www.java.com](https://www.java.com/)を参照してください。

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
