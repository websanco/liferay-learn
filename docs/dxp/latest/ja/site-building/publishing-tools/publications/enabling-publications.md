# パブリケーションの有効化

> 対応可能：Liferay DXP/Portal 7.3以降

パブリケーションは、あなたとあなたのチームがDXPインスタンスへの変更を開発、追跡、および公開するための便利で柔軟な方法を提供します。 有効にすると、パブリケーションはグローバルにアクティブ化され、どこからでもアクセスしてサポートされているエンティティを編集できます。

```{important}
ステージングとパブリケーションを同時に有効にすることはできません。 いずれかのサイトでステージングが有効になっている場合は、パブリケーションを有効にする前に、まずステージングを無効にする必要があります。 詳細については、 [Disabling Local Live Staging](../staging/configuring-local-live-staging.md#disabling-local-live-staging) および [Disabling Remote Live Staging](../staging/configuring-remote-live-staging.md#disabling-remote-live-staging) を参照してください。
```

## パブリケーションを有効にする方法

パブリケーションを有効にするには、次の手順に従います。

1. *グローバルメニュー*（![Global Menu](../../../images/icon-applications-menu.png)）を開き、*［Applications］*タブをクリックして、*［Publications］*に移動します。

1. スイッチを切り替えて、パブリケーションを有効にします。

   ![スイッチを切り替えて、［保存］をクリックします。](./enabling-publications/images/01.png)

1. *［Save］*をクリックして、構成を保存します。 これにより、［Publications］アプリケーションページにリダイレクトされます。

有効にすると、DXPインスタンスのどこからでもドロップダウンの［Publications］バーメニューにアクセスできます。

![DXPインスタンスのどこからでもドロップダウンの［Publications］バーメニューにアクセスできます。](./enabling-publications/images/02.png)

このメニューを使用して、新しいパブリケーションを作成したり、作業するパブリケーションを選択したり、編集モードと本番環境モードを切り替えたり、現在のパブリケーションの変更を確認して公開したりできます。 個々のパブリケーションの作成、編集、および削除については、[パブリケーションの作成と管理](./creating-and-managing-publications.md)を参照してください。

## パブリケーションを無効にする方法

必要に応じて、［Publications］アプリケーションページからいつでもパブリケーションを無効にできます。

1. *グローバルメニュー*（![Global Menu](../../../images/icon-applications-menu.png)）を開き、*［Applications］*タブをクリックして、*［Publications］*に移動します。

1. アプリケーションバーの*アクション*ボタン（![Actions Button](../../../images/icon-actions.png)）をクリックし、*［Settings］*を選択します。

   ![パブリケーションアプリケーションを開き、アクションボタンをクリックして、［Settings］を選択します。](./enabling-publications/images/03.png)

1. スイッチを切り替えて、パブリケーションを無効にします。

1. *［保存］*をクリックします。

```{important}
無効にすると、アクティブなすべてのパブリケーションが無効になり、スケジュールされたパブリケーションがキャンセルされ、パブリケーション履歴にアクセスできなくなります。 ただし、DXPはインスタンスのパブリケーション履歴を保持するため、パブリケーションを再度有効にすれば再度アクセスできます。
```

## 7.3.xのパブリケーションの有効化

パブリケーションを有効にするには、次の手順に従います。

1. *グローバルメニュー*（![Global Menu](../../../images/icon-applications-menu.png)）を開き、［Applications］タブをクリックし、*［Publications］*の下にある *［Settings］*をクリックします。

   ![グローバルメニューで、［Publications］の下の［Settings］をクリックします。](./enabling-publications/images/04.png)

1. スイッチを*［Yes］*に切り替えます。

1. *［Submit］*をクリックして、構成を保存します。

   または、*［Save and Go to Overview］*をクリックすると、構成が保存され、［Publications］概要ページにリダイレクトされます。

   ![トグルを［Yes］に設定し、［送信］または［保存して概要に移動］をクリックします。](./enabling-publications/images/05.png)

## 7.3.xのパブリケーションの無効化

必要に応じて、パブリケーションの*［Settings］*ページからいつでもパブリケーションを無効にできます。

1. *グローバルメニュー*（![Global Menu](../../../images/icon-applications-menu.png)）を開き、［Applications］タブをクリックし、*［Publications］*の下にある *［Settings］*をクリックします。

1. スイッチを*［No］*に切り替えます。

1. *［送信］*をクリックします 。

```{important}
無効にすると、アクティブなすべてのパブリケーションが無効になり、スケジュールされたパブリケーションがキャンセルされ、パブリケーション履歴にアクセスできなくなります。 ただし、DXPはインスタンスのパブリケーション履歴を保持するため、パブリケーションを再度有効にすれば再度アクセスできます。
```

## 追加情報

* [パブリケーションの概要](../publications.md)
* [パブリケーションの作成と管理](./creating-and-managing-publications.md)
* [変更の作成と公開](./making-and-publishing-changes.md)
