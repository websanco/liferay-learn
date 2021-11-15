# カタログ権限設定の構成

カタログ権限とは、カタログやその関連商品を表示および変更できるユーザーを決定する機能です。 システム管理ユーザーにはデフォルトでこの権限が付与されており、この権限をシステムに追加された新しいロールに追加することができます。

```{note}
個々の製品の表示権限は、その権限が使用できるチャネルを変更することで管理されます。 詳細は、[Configuring Product Visibility Using Channels](../../starting-a-store/channels/configuring-product-visibility-using-channels.md)を参照してください。
```

## 既存ロールへのカタログ管理権限の追加

まず、 [新しいカスタムアカウントロール](../../account-management/creating-a-custom-account-role.md) を作成するか、既存ロールを変更してカタログと製品を管理することから始めます。 この役割は、システム全体の機能に対する最小限のアクセス権を持つように設定することができます。 ユーザー権限の設定の定義については、[ロールの権限設定の定義](https://learn.liferay.com/dxp/7.x/en/users-and-permissions/roles-and-permissions/defining-role-permissions.html)を参照してください。

既存ロールにカタログ管理権限を定義するには

1.  *コントロールパネル* → *ユーザー* → *ロール*に移動します。

2.  （![Add icon](../../images/icon-add.png)）をクリックして新しい標準ロールを追加します。

3.  次のように入力します：

      - **役職**：カタログマネージャー
      - **説明**このロールはカタログを管理します。
      - **キー**：（役職の基づいて生成されます）

4.  *[保存]* をクリックします。

5.  *[権限の定義]* をクリックします。

6.  *コントロールパネル* → *Commerce* をクリックして、ドロップダウンメニューを展開します。

    ![Commerceカタログ権限設定に移動します。](./configuring-catalog-permissions/images/03.png)

7.  *[カタログ]* をクリックします。

8.  最低でも、すべての *通常権限*と*リソース権限*を含む必要な権限を選択します。

    ![[カタログ権限設定]を選択します。](./configuring-catalog-permissions/images/04.png)

9.  *[保存]* をクリックします。

10. *[商品]* ドロップダウンメニューをクリックします。

11. 最低でも、すべての *通常権限*と*リソース権限*を含む必要な権限を選択します。

12. 完了後、*[保存]* をクリックします。

新しいカタログマネージャーロールには、カタログおよび商品メニューを表示するための最低限の権限が与えられます。 このロールを持つユーザーは、 *[コントロールパネル]* → *[Commerce]* → *[カタログ]* および *[商品]* メニューにアクセスできます。

## カタログ権限設定の構成

以下の手順に従って、カタログ権限を設定します。

1.  *コントロールパネル* → *コマース* → *カタログ*に移動します。

2.  （![3-dot icon](../../images/icon-actions.png)）をクリックし、次に *[権限]* をクリックします。

    ![ユーザーは権限を編集できます。](./configuring-catalog-permissions/images/01.png)

3.  該当するロールに必要な権限の横のボックスをチェックします。

    ![必要な権限を選択します。](./configuring-catalog-permissions/images/02.png)

4.  完了後、*[保存]* をクリックします。

これで、カタログの権限が設定されました。

## 追加情報

  - [ロールと権限について](https://learn.liferay.com/dxp/7.x/en/users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.html)
  - [チャネルを使用した商品の可視性の構成](../../starting-a-store/channels/configuring-product-visibility-using-channels.md)
