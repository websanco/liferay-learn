# Liferay Commerce Enterpriseのアクティベーション

> **サブスクライバー**

Liferay Commerce EnterpriseはLiferay DXP上に構築されており、完全に機能するためにはアクティベーションが必要です。

## アクティベーションキーの展開

アクティベーションキーは、Commerce Enterpriseインストールの `{liferay.home}\ deploy` ディレクトリにコピーされる `XML` ファイルです。 アクティベーションキーを取得して展開するには、次の手順に従います。

1.  [購入または既存のサブスクリプションへの追加](https://www.liferay.com/contact-sales) Liferay Commerce Enterprise。
2.  [ヘルプセンター](https://liferay-support.zendesk.com/agent/) チケットを開き、Liferay Commerceアクティベーションキーをリクエストします。
3.  Liferayプロビジョニングチームは、アクティベーションキーのダウンロードを提供します。
4.  アクティベーションキーを [`${liferay.home}/ deploy`](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/liferay-home.html) フォルダーに展開します。
5.  同様のアクティベーション成功メッセージがコンソールに表示されることを確認します。

<!-- end list -->

    INFO  [fileinstall-C:/Users/Liferay/liferay-commerce-enterprise-2.0.6/osgi/modules][LicenseManager:?] Liferay Commerce license validation passed
    INFO  [fileinstall-C:/Users/Liferay/liferay-commerce-enterprise-2.0.6/osgi/modules][LicenseManager:?] License registered for Liferay Commerce

Liferay Commerce Enterpriseを使用する準備ができました。

## アクティベーションキーの更新

アクティベーションキーは、ユーザーのサブスクリプションの条件に基づいて設定された期間が経過すると期限切れになります。 Liferay Commerce Enterpriseを再度アクティブ化するには、新しいキーを `liferay.home/deploy` フォルダーに展開します。 サーバーに展開されている期限切れのキーは必ず削除してください。

期限切れのキーが削除されない場合、次回のサーバーの再起動時にコンソールにエラーメッセージが表示されます。

    エラー [main][LicenseManager :?] Liferay Commerceライセンスの有効期限が切れています

次の手順に従って、Liferay Commerce Enterpriseを再アクティブ化します。

1.  以下のフォルダに移動します。

      - `${liferay.home}/data/license`
      - `${liferay.home}/osgi/modules`

2.  上記のディレクトリから期限切れのCommerce Enterpriseアクティベーションキーを削除します。 有効期限が切れたキーを確認するには、既存の `xml` keys `activation-key-commerce-1.xml` を開き、既に渡された `<expiration-date>` 要素を探します。 例:

    ``` xml
    <expiration-date>2019年9月23日土曜日2:05:47 PM GMT</expiration-date>
    ```

    ``` warning::
       期限切れのキーを確認して削除する際は、有効な* DXP *固有のアクティベーションキーを含む有効なキーを削除しないように注意してください。
    ```

3.  新しいアクティベーションキーを [`${liferay.home}/ deploy`](https://learn.liferay.com/dxp/7.x/en/installation-and-upgrades/reference/liferay-home.html) フォルダーに展開します。

    ``` tip::
      アクティベーションキーを展開または削除するためにアプリケーションサーバーをシャットダウンする必要はありません。
    ```

## 追加情報

  - [Activating a Marketplace App Through a Proxy Server](https://help.liferay.com/hc/en-us/articles/360018427391)
