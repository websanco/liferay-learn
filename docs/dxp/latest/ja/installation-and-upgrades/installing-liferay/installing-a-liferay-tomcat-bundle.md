# Liferay-Tomcatバンドルのインストール

Tomcatバンドルには、Liferay DXPが事前にデプロイされたApache Tomcatアプリケーションサーバーが含まれています。 これは、Liferay DXPをオンプレミスにインストールする最も簡単で最速の方法です。

```note::
   次のアプリケーションサーバーのいずれかをすでに使用している場合は、使用しているアプリケーションサーバーの名前をクリックして、DXPをデプロイする手順を確認してください。 `Tomcat <./installing-liferay-on-an-application-server/installing-on-tomcat.md>`、`WildFly <./installing-liferay-on-an-application-server/installing-on-wildfly.md>`_、`JBoss EAP <./installing-liferay-on-an-application-server/installing-on-jboss-eap.md>`_、`WebLogic <./installing-liferay-on-an-application-server/installing-on-weblogic.md>`_、または`WebSphere <./installing-liferay-on-an-application-server/installing-on-websphere.md>`_.。
```

```note::
   ツアーやデモンストレーションの目的でLiferay DXPインスタンスをすばやく起動するには、`Starting With a Docker Image <../../getting-started/starting-with-a-docker-image.md>`_を参照してください。
```

## 前提条件

Liferay DXPにはJava JDK 8または11が必要です。 JDKの選択には [互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360049238151) を参照してください。 推奨される設定については、[JVM Configuration](../reference/jvm-configuration.md)を参照してください。

## DXPをダウンロードする

1. [ヘルプセンター](https://help.liferay.com/hc)（サブスクリプション）または[コミュニティダウンロード](https://www.liferay.com/downloads-community)にアクセスします。

2. 必要なLiferay DXPバージョンに移動します。

3. Tomcatバンドルをダウンロードします。

| ファイル                   | 説明                                 |
|:---------------------- |:---------------------------------- |
| Tomcatバンドル（`.tar.gz` ） | 任意のOSにインストールされるgzip圧縮されたバンドルアーカイブ  |
| Tomcatバンドル（`.7z`）      | 任意のOSにインストールされる7-Zip圧縮されたバンドルアーカイブ |

## DXPをインストールする

バンドルをDXPホスト上の場所に展開します。 この場所は、[Liferay Home](../reference/liferay-home.md)と呼ばれます。

　 Liferay DXPをインストールしました。 次にデータベースを設定します。

## 次のステップ

* [Configuring a Database](./configuring-a-database.md)
