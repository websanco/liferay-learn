# オブジェクトアクションの定義

> 対応可能：Liferay DXP/Portal 7.4以降

Liferay Objectsでは、特定の条件でトリガーされるアクションを定義することができます。 アクションがアクティブになると、LiferayはObjectのデータが追加、削除、または解除された時にリッスン、設定されたアクションを実行します。

```{important}
現在、アクションは、データを同期・処理するために外部ソースにペイロードを配信する*webhooks*のトリガーのみをサポートしています。
```

次の手順に従って、オブジェクトにアクションを定義します。

1. *［グローバルメニュー］ * （![Global Menu](../../../images/icon-applications-menu.png)）を開き、 *［コントロールパネル］ * タブをクリックして、 *［オブジェクト］ *に進みます。

1. 既存のカスタムオブジェクトを選択するか、 [新しいオブジェクトを作成](./creating-objects.md)します。

1. オブジェクトを表示した状態で、 *［Actions］* タブを選択し、 *［Add］* ボタン（![Add Button](../../../images/icon-add.png)）をクリックします。

1. *［name］*を入力します。

1. ［When］では、アクションのトリガーを決めます。

   | トリガー            | 説明                 |
   |:--------------- |:------------------ |
   | On After Add    | オブジェクトエントリが追加されたとき |
   | On After Delete | オブジェクトエントリが削除されたとき |
   | On After Update | オブジェクトエントリが更新されたとき |

1. ［Then］に、 *［Webhook］*を選択します。 これにより、実行されるアクションの種類が決まります。

1. Webhookの *URL* を入力します。

1. (オプション）Webhookの*secret*を入力します。

   ![Enter a name, trigger, action type, and URL.](./defining-object-actions/images/01.png)

1. *［Save］* をクリックします。

   ```{note}
   アクションを保存した後、そのトリガー（つまり"When"フィールド）やアクションタイプ（つまり"Then"フィールド）を変更することはできません。
   ```

作成されると、アクションはアクティブになり、構成に応じてトリガーされます。

![The action is activated after saving.](./defining-object-actions/images/02.png)

必要に応じて、いつでもアクションを無効にすることができます。 アクションを選択し、スイッチを*［Inactive］*に切り替え、*［保存］*をクリックします。

![Actions can be deactivated at any time.](./defining-object-actions/images/03.png)

## 追加情報

* [オブジェクトの作成](./creating-objects.md)
* [オブジェクトへのフィールドの追加](../creating-and-managing-objects/adding-fields-to-objects.md)
* [オブジェクトリレーションの定義](../creating-and-managing-objects/defining-object-relationships.md)
