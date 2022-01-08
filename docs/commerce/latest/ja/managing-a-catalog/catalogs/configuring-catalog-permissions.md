# カタログ権限設定の構成

カタログ権限とは、カタログやその関連商品を表示および変更できるユーザーを決定する機能です。 システム管理ユーザーにはデフォルトでこの権限が付与されており、この権限をシステムに追加された新しいロールに追加することができます。

```{note}
   個々の商品の表示権限は、その権限が使用できるチャネルを変更することで管理されます。 詳細は、`Configuring Product Visibility Using Channels <../../starting-a-store/channels/configuring-product-visibility-using-channels.md>`_を参照してください。
```

## 既存ロールへのカタログ管理権限の追加

カタログや商品を管理するために、新しいカスタムロールを作成するか、既存のロールを変更することから始めましょう。 この役割は、システム全体の機能に対する最小限のアクセス権を持つように設定することができます。 ユーザー権限の設定の定義については、[ロールの権限設定の定義](https://learn.liferay.com/dxp/latest/en/users-and-permissions/roles-and-permissions/defining-role-permissions.html)を参照してください。

既存ロールにカタログ管理権限を定義するには

1. ［_コントロールパネル_］ → ［_ユーザー_］ → ［_ロール_］に移動します。
1. （![Add icon](../../images/icon-add.png)）をクリックして新しい標準ロールを追加します。
1. 次のように入力します：

     * **役職**：カタログマネージャー
     * **説明**このロールはカタログを管理します。
     * **キー**：（役職の基づいて生成されます）

1. ［_保存_］ をクリックします。
1. ［_権限の定義_］をクリックします。
1. ［_コントロールパネル_］&rarr;［_Commerce_］をクリックしてドロップダウンメニューを拡大します。

    ![Commerceカタログ権限設定に移動します。](./configuring-catalog-permissions/images/03.png)

1. ［_カタログ_］をクリックします。
1. 最低でも、すべての _通常権限_ と _リソース権限_ を含む必要な権限を選択します。

    ![［カタログ権限設定］を選択します。](./configuring-catalog-permissions/images/04.png)

1. ［_保存_］ をクリックします。
1. ［_商品_］ドロップダウンメニューをクリックします。
1. 最低でも、すべての _通常権限_ と _リソース権限_ を含む必要な権限を選択します。
1. 完了したら、［_保存_］をクリックします。

新しいカタログマネージャーロールには、カタログおよび商品メニューを表示するための最低限の権限が与えられます。 これにより、このロールを持つユーザーは、［_コントロールパネル_］&rarr; ［_Commerce_］&rarr; ［_カタログ_］および［_商品_］メニューにアクセスできるようになります。

## カタログ権限設定の構成

以下の手順に従って、カタログ権限を設定します。

1. ［_コントロールパネル_］ → ［_コマース_］ → ［_カタログ_］に移動します。
1. （![3-dot icon](../../images/icon-actions.png)）をクリックし、次に ［_権限_］をクリックします。

    ![ユーザーは権限を編集できます。](./configuring-catalog-permissions/images/01.png)

1. 該当するロールに必要な権限の横のボックスをチェックします。

    ![必要な権限を選択します。](./configuring-catalog-permissions/images/02.png)

1. 完了したら、［_保存_］をクリックします。

これで、カタログの権限が設定されました。

## 追加情報

* [ロールと権限について](https://learn.liferay.com/dxp/latest/en/users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.html)
* [チャネルを使用した商品の可視性の構成](../../starting-a-store/channels/configuring-product-visibility-using-channels.md)
