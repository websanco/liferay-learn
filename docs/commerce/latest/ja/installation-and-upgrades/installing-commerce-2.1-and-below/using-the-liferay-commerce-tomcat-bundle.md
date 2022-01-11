# Liferay Commerce Tomcatバンドルの使用

Liferay Commerceバンドルは、 [Liferay Commerceのコミュニティダウンロード](https://commerce.liferay.dev/download) ページからダウンロードできます。 バンドルには、Apache Tomcatに事前デプロイされたLiferay CommerceおよびLiferay Portalの最新バージョンが含まれています。

バンドルを入手する前に、 [Liferay Commerce互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360049238151) を読んで、サポートされているテクノロジーのリストを確認してください。

Liferay Commerceバンドルを使用するには、次の手順を実行します。

1. Liferay Commerce Tomcatバンドルを入手します。

    * （サブスクライバー） [エンタープライズダウンロード](https://customer.liferay.com/downloads?p **p** id=com **liferay** osb **customer** downloads **display** web **DownloadsDisplayPortlet&** com **liferay** osb **customer** downloads **display** web **DownloadsDisplayPortlet** productAssetCategoryId=118190997& **com** liferay **osb** customer **downloads** display **web** DownloadsDisplayPortlet_fileTypeAssetCategoryId=118191001)
    * [コミュニティダウンロードページ](https://www.liferay.com/downloads-community)

1. バンドルをターゲットの場所に解凍します。

1. Tomcatバンドルを開始します
    * `$CATALINA_HOME/bin`に移動します。
    * `./catalina.sh run`を実行します。 以下のための **Windowsの** ユーザ、実行： `カタリナラン`

    ```tip::
       「org.apache.catalina.startup.Catalina.startサーバーの起動が ［x］ ミリ秒以内」と表示されるまで待ちます
    ```

1. ブラウザで`https://localhost:8080`を開きます。

![ウェルカムイメージ](./using-the-liferay-commerce-tomcat-bundle/images/01.png)

## 追加情報

* [インストールの概要](../installation-overview.md)
* [LiferayDXPのインストール](https://learn.liferay.com/dxp/latest/ja/installation-and-upgrades/installing-liferay/installing-a-liferay-tomcat-bundle.html)
* [Liferay Commerce3.0互換性マトリクス](https://help.liferay.com/hc/en-us/articles/360049238151)
