# サイトのアセットごみ箱の設定

ごみ箱には、削除のフラグが付けられたコンテンツとアセットが、システムから完全に削除される前に保存されます。 この機能はデフォルトでグローバルに有効にでき、サイトごとに設定できます。 ごみ箱の詳細については、[Recycle Bin](../../../content-authoring-and-management/recycle-bin/recycle-bin-overview.md)のセクションを参照してください。

```{warning}
サイトのごみ箱を無効にすると、サイトで削除されたコンテンツとアセットは完全に削除されます。
```

## サイトのごみ箱の設定

特定のサイトのごみ箱を有効または無効にするには、次の手順に従います。

1. ごみ箱オプションにアクセスします。

    - Liferay DXP 7.4+の場合

      1. サイトのメニューから、*［Configuration］* &rarr; *［Site Settings］*に移動します。
      1. ［コンテンツとデータ］セクションで、*［Recycle Bin］*をクリックします。

         ![Liferay DXP 7.4以降では、［サイト設定］セクションからゴミ箱オプションを変更します。](./configuring-the-asset-recycle-bin-for-sites/images/02.png)

    - 以前のLiferay DXPバージョンの場合

      1. サイトメニューから、*［Configuration］* &rarr; *［Settings］*に移動します。
      1. ［Advanced］領域で、*［Recycle Bin］*セクションを展開します。

         ![以前のLiferay DXPバージョンでは、［Settings］セクションからゴミ箱オプションを変更します。](./configuring-the-asset-recycle-bin-for-sites/images/01.png)

1. *［ Enable Recycle Bin］*を切り替えて、サイトのごみ箱を有効または無効にします。
1. 必要に応じて、*［Trash Entries Max Age］*入力フィールドに値（分単位）を指定して、リサイクルされたアセットが完全に削除される前にごみ箱にとどまる時間を指定します。

1. *［Save］* をクリックします。

## 関連情報

- [ゴミ箱の概要](../../../content-authoring-and-management/recycle-bin/recycle-bin-overview.md)
- [サイトのごみ箱の設定](../../../content-authoring-and-management/recycle-bin/configuring-the-recycle-bin.md)
- [サイト設定UIリファレンス](../../site-settings/site-settings-ui-reference.md)
