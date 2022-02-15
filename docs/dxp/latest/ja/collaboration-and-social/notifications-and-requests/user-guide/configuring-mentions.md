# メンションの設定

メンションは、 **Mentions** アプリで有効にします。 デフォルトでは、 **Mentions** アプリがグローバルに有効になっています。 ただし、このアプリはグローバルに、またはサイトごとに有効または無効にできます。

<a name="グローバルスコープでのメンションの設定" />

## グローバルスコープでのメンションの設定

仮想インスタンスのグローバルメンション設定にアクセスするには：

1. **Global Menu**（![Global Menu](../../../images/icon-applications-menu.png)）をクリックし、 ［**コントロールパネル**］ をクリックします。
1. ［設定］で、 ［**インスタンス設定**］ をクリックします。
1. ［CONTENT AND DATA］で、 ［**Comunity Tools**］ をクリックします。

   ![コミュニティツール](./configuring-mentions/images/01.png)

1. ［ Virtual Instance Scope］セクションで、 ［**Mentions**］ をクリックします。
1. デフォルトでは、すべてのユーザーが他のサイトのメンバーや友達にメンションできます。 このオプションを設定するには、 ［**Define Mentions Capability for Users**］ を選択し、必要な設定を指定します。

    ![仮想インスタンスのすべてのサイトに対しメンション機能を有効または無効にできます。](./configuring-mentions/images/02.png)

1. ［**Save**］ をクリックします。

<a name="サイトスコープでのメンションの設定" />

## サイトスコープでのメンションの設定

サイト管理者は、サイトへの **メンション** を有効または無効にできます。

1. ［Mentions］オプションにアクセスします。

    - Liferay DXP 7.4+の場合

      1. サイトのメニューから、 ［**Configuration**］ &rarr; ［**Site Settings**］ に移動します。
      1. ［コンテンツとデータ］セクションで、 ［**Community Tools**］ をクリックしてから、 ［**Mentions**］ をクリックします。

            ![Liferay DXP 7.4以降では、［サイト設定］セクションからメンション設定を変更します。](./configuring-mentions/images/04.png)

   - 以前のLiferay DXPバージョンの場合

      1. サイトメニューから、 ［**Configuration**］ &rarr; ［**Settings**］ に移動します。
      1. ［Social］エリアで、 ［**Mentions**］ オプションを展開します。

            ![以前のLiferay DXPバージョンでは、［Settings］セクションからメンション設定を変更します。](./configuring-mentions/images/03.png)

1. ［**Allow Users to Mention Other Users**］ オプションで、スイッチを切り替えて優先する設定にします。

1. ［**Save**］ をクリックします。

<a name="関連情報" />

## 関連情報

- [サイト設定UIリファレンス](../../../site-building/site-settings/site-settings-ui-reference.md)
- [通知とリクエストの管理](./managing-notifications-and-requests.md)
- [ユーザーにメンションする](./mentioning-users.md)
