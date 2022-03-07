# 連絡先データの同期

[LiferayDXPインスタンスをAnalyticsCloud](connecting-liferay-dxp-to-analytics-cloud.md) に接続した後、連絡先データの同期を開始できます。 Liferay DXPの最新リリースでは、コンタクトデータからAnalytics Cloudに同期するフィールドを細かくコントロールすることができます。

```{note}
   同期するコンタクトフィールドを選択する機能は、以下のリリース以降で利用できます。
     * Liferay 7.3 Fix Pack 1
     * Liferay 7.2 Fix Pack 9
     * Liferay 7.1 Fix Pack 20
     * Liferay 7.0 Fix Pack 97
```

<a name="initial-sync-of-contact-data" />

## コンタクトデータの初期同期

1. Liferay DXPインスタンスで、 ［**コントロールパネル**］ → ［**インスタンス設定**］ → ［**Analytics Cloud**］ に移動します。

1. ［**連絡先を選択**］ をクリックします。

1. ［**連絡先の同期**］ をクリックします。

1. スイッチを使って、 **Sync All** を有効にします。 また、連絡先の一部だけを同期させたい場合は、［ユーザーグループ別に同期］または［組織別に同期］をクリックしてください。

      ![すべての連絡先を同期したり、グループや組織を同期することができます。](./syncing-contact-data-from-dxp/images/01.png)

      ［**保存して次へ**］ ボタンをクリックします。

1. Liferay DXPでは、コンタクトデータを2つの別々のテーブル（ContactとUser）に保存します。 コンタクトタブとユーザータブを切り替えて、同期するフィールドを選択します。 Analytics Cloudでは、いくつかのフィールドを同期する必要があり、それらはグレーで表示されます。

      ![連絡先に同期するフィールドを選択します。](./syncing-contact-data-from-dxp/images/02.png)

      なお、ユーザー用に作成されたカスタムフィールドは、すべて同期に利用できます。 ユーザーにカスタムフィールドを追加する についての詳細は [こちら](https://learn.liferay.com/dxp/latest/ja/users-and-permissions/users/adding-custom-fields-to-users.html) 。

      選択後、ページ下部にある **保存** ボタンをクリックします。

<a name="modifying-sync-of-contact-data-fields" />

## コンタクトデータフィールドの同期を変更する

1. Liferay DXPインスタンスの ［**コントロールパネル**］ &rarr; ［**インスタンス設定**］ &rarr; ［**Analytics Cloud**］ &rarr; ［**Synced Contact Data**］ に移動します。

      ![コントロールパネルの［同期されたコンタクトデータ］セクションに移動します。](./syncing-contact-data-from-dxp/images/03.png)

1. **同期データフィールド** をクリックします。

1. 修正して、 **保存** ボタンをクリックします。
