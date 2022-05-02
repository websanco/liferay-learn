# アセットパブリッシャー購読の設定

アセットパブリッシャーは、メール購読とRSSフィード購読の2種類のサブスクリプションをサポートしています。

<a name="email-subscriptions" />

## メール購読

ユーザーは、アセットパブリッシャーを購読して、新しいアセットが公開されたときに電子メール通知を受け取ることができます。 最初にこの通知を有効にする必要があります。 次の手順を実行します：

1. アセットパブリッシャーの上にカーソルを置き、ウィジェットのメニューでオプションアイコン（![Options](../../../images/icon-app-options.png)）をクリックし、 ［**Configuration**］ を選択します。
1. ［**Enable Email Subscription**］ セレクターを［Yes］に切り替えます。
1. フォームに入力し、 ［**Save**］ をクリックして変更を適用します。

    ![メール購読は、新しいアセットが公開されたときにユーザーに通知します。](./configuring-asset-publisher-subscriptions/images/01.png)

1. 有効になっていない場合は、［表示設定］タブで **変更通知を受け取る（購読する**） 機能を有効にします。 ユーザーは、 ［**Subscribe**］ ボタンをクリックして、新しく公開されたアセットの電子メール通知を受け取ることができます。

![メールの購読を有効にすると、変更通知を受け取る（購読する）リンクがアセットパブリッシャーに追加されます。](./configuring-asset-publisher-subscriptions/images/02.png)

<a name="configuring-the-asset-check-interval" />

### アセットチェック間隔の設定

Liferay Portalは定期的に新しいアセットをチェックし、購読しているユーザーに新しいアセットを通知するメールを送信します。 デフォルトでは、アセットは 24 時間ごとにチェックされます。 システム設定からチェック間隔を変更できます。 次の手順を実行します：

1. プロダクトメニューを開き、 ［**コントロールパネル**］ &rarr; ［**Configuration**］ &rarr; ［**System Settings**］ に移動します。
1. [**コンテンツとデータ**] 見出しの下にある ［**Assets**］ を選択します。
1. ［**System Scope**］ &rarr; ［**Asset Publisher**］ に移動します。
1. ［**Check Interval**］ 設定を、新しいアセットをチェックして購読しているユーザーに通知する間隔 (時間単位) に変更し、 ［**保存**］ をクリックして変更内容を適用します。

![チェック間隔設定は、アセットの更新をチェックする頻度を指定します。](./configuring-asset-publisher-subscriptions/images/03.png)

<a name="rss-feed-subscriptions" />

## RSSフィード購読

```{note}
RSSフィードはLiferay Portal 7.2以降では廃止予定であり、デフォルトでは無効になっています。 RSSフィードを活用するには、この機能を有効にする必要があります。
```

アセットパブリッシャーのRSSフィード購読を有効にするには、次の手順を実行します。

1. プロダクトメニューを開き、 ［**Control Panel**］ &rarr; ［**Configuration**］ &rarr; ［**System Settings**］ に移動します。
1. ［**コンテンツとデータ**］ 見出しの下にある ［**Webコンテンツ**］ を選択します。
1. ［**システムスコープ**］ &rarr; ［**管理**］ タブで、 ［**Show Feeds**］ ボックスをオンにします。 廃止予定のアプリの詳細は、[こちらの記事](../../../installation-and-upgrades/upgrading-liferay/reference/maintenance-mode-and-deprecations-in-7-4.md)を参照してください。

    ![システム設定でRSSフィードを有効にします。](./configuring-asset-publisher-subscriptions/images/04.png)

1. アセットパブリッシャーウィジェットに戻り、ウィジェットの上にカーソルを置き、ウィジェットのメニューのオプションアイコン（![Options](../../../images/icon-app-options.png)）をクリックして、 ［**設定**］ を選択します。
1. ［**Enable RSS Subscription**］ セレクターを［Yes］に切り替えます。
1. フォームに入力し、 ［**保存**］ をクリックして変更を適用します。

    ![RSS購読は、RSSフィードを購読者のRSSリーダーに送信します。](./configuring-asset-publisher-subscriptions/images/05.png)

1. 有効になっていない場合は、［表示設定］タブで **変更通知を受け取る（購読する**） 機能を有効にします。 ユーザーは **RSS** リンクをクリックしてRSSフィードを購読できます。

![RSS購読を有効にすると、アセットパブリッシャーにRSSリンクが追加されます。](./configuring-asset-publisher-subscriptions/images/06.png)

<a name="related-information" />

## 関連情報

- [メールの構成](../../../installation-and-upgrades/setting-up-liferay/configuring-mail.md)
- [アセットパブリッシャーウィジェットを使用したアセットの表示](./displaying-assets-using-the-asset-publisher-widget.md)
- [アセットパブリッシャー表示設定の構成](./configuring-asset-publisher-display-settings.md)
