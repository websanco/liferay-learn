# パブリケーションの有効化

パブリケーションは、あなたとあなたのチームがDXPインスタンスへの変更を開発、追跡、および公開するための便利で柔軟な方法を提供します。 有効にすると、パブリケーションはグローバルにアクティブ化され、どこからでもアクセスしてインスタンスでサポートされているエンティティを編集できます。

```{important}
Staging and Publications cannot be enabled at the same time. If Staging is enabled on any of your Sites, you must first disable Staging before enabling Publications. See [Disabling Local Live Staging](../staging/configuring-local-live-staging.html#disabling-local-live-staging) and [Disabling Remote Live Staging](../staging/configuring-remote-live-staging.html#disabling-remote-live-staging) for more information.
```

## パブリケーションを有効にする方法

1.  *グローバルメニュー*（![Global Menu](../../../images/icon-applications-menu.png)）に移動し、*[Publications]* の下にある *[設定]* をクリックします。

    ![グローバルメニューで、[Publications]の下の[設定]をクリックします。](./enabling-publications/images/01.png)

2.  トグルスイッチを*[Yes]* に設定して、DXPインスタンスのパブリケーションを有効にします。

3.  *[送信]* をクリックして、構成を保存します。

    または、*[保存して概要に移動]* をクリックすると、構成が保存され、[Publications]概要ページにリダイレクトされます。

    ![トグルを[Yes]に設定し、[送信]または[保存して概要に移動]をクリックします。](./enabling-publications/images/02.png)

有効にすると、DXPインスタンスの任意のページからドロップダウンの[Publications]バーメニューにアクセスできます。 このメニューを使用して、新規パブリケーションを作成したり、作業するパブリケーションを選択したり、編集モードと本番環境モードを切り替えたり、選択したパブリケーションの変更を確認して即時にまたは後で公開したりできます。 個々のパブリケーションの作成、編集、および削除については、[パブリケーションの作成と管理](./creating-and-managing-publications.md)を参照してください。

## パブリケーションを無効にする方法

有効にしたパブリケーションは、[Publications]の*[設定]* ページからいつでも無効にすることができます。 トグルスイッチを*[No]* に設定してから、*[送信]* をクリックします。

```{important}
Once disabled, all active publications are deactivated, scheduled publications are canceled, and you can no longer access your publication history. DXP, however, retains your instance's publication history, so you can access it again if you re-enable Publications.
```

## 追加情報

  - [Publications Overview](../publications.md)
  - [パブリケーションの作成と管理](./creating-and-managing-publications.md)
  - [変更の作成と公開](./making-and-publishing-changes.md)
