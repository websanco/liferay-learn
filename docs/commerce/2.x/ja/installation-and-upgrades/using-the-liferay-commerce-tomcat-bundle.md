# Liferay Commerce Tomcatバンドルの使用

Liferay Commerceバンドルは、[[Liferay Commerce Community Downloads]](https://commerce.liferay.dev/download)ページからダウンロードできます。 バンドルには、Apache Tomcatに事前デプロイされたLiferay CommerceおよびLiferay Portal CEの最新バージョンが含まれています。

バンドルを入手する前に、[Liferay Commerce互換性マトリックス](https://web.liferay.com/documents/14/21598941/Liferay+Commerce+2.0+Compatibility+Matrix/0ed97477-f5a7-40a6-b5ab-f00d5e01b75f)を読んで、サポートされているテクノロジーのリストを確認してください。

Liferay Commerceバンドルを使用するには、次の手順を実行します。

1.  Liferay Commerce Tomcatバンドルを入手します。

      - [ダウンロードページ](https://commerce.liferay.dev/download)

2.  バンドルをターゲットの場所に解凍します。

3.  Tomcatバンドルを開始します

      - `$CATALINA_HOME/bin`に移動します。
      - `./catalina.sh run`を実行します。 > Windowsユーザーの場合は、`catalina run`を実行します。

    > `org.apache.catalina.startup.Catalina.start Server startup in [x] milliseconds`と表示されるまで待ちます。

4.  ブラウザで`https://localhost:8080`を開きます。

![ウェルカムイメージ](./using-the-liferay-commerce-tomcat-bundle/images/01.png)

## 追加情報

  - [Installation Overview](./installation-overview.md)
  - [Installing Liferay DXP](https://help.liferay.com/hc/en-us/articles/360028711012-Installing-Liferay-DXP)
  - [Liferay Commerce 2.0 Compatibility Matrix](https://web.liferay.com/documents/14/21598941/Liferay+Commerce+2.0+Compatibility+Matrix/0ed97477-f5a7-40a6-b5ab-f00d5e01b75f)
