# 選択リストの使用

選択リストは、Liferay Portal全体で理解される値の標準リストを作成するためのアプリケーションです。 選択リストを作成するプロセスには、初期リストの作成とリストへのアイテムの追加が含まれます。 これらの値を定義すると、サポートされているアプリケーションで使用できるようになります。

<a name="creating-a-picklist" />

## 選択リストの作成

以下の手順に従って、選択リストを作成します。

1. ［**グローバルメニュー**］（![Global Menu](../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ タブに移動して、 ［**選択リスト**］ をクリックします。

1. ［**追加**］ ボタン（![Add Button](../../images/icon-add.png)）をクリックします。

   ![追加ボタンをクリックして、新しい選択リストを作成します。](./using-picklists/images/01.png)

1. 選択リストに ［**Name**］ を入力し、 ［**Save**］ をクリックします。 この値は、Liferay UIでリストを識別し、作成後にローカライズできます。

1. 新しい［選択リスト］ をクリックして編集します。

1. ［Items］の下にある、 ［**追加**］ ボタン（![Add Button](../../images/icon-add.png)）をクリックします。

   ![追加ボタンをクリックして、新しいアイテムを選択リストに追加します。](./using-picklists/images/02.png)

1. モーダルウィンドウで、アイテムの ［**Name**］ と ［**Key**］ を入力します。

   **Name** ：アイテムの表示名を決定するもので、作成後にローカライズできます。

   **Key** ：バックエンドのアプリケーションが理解できる標準値を決定するもので、キャメルケースを使用します。

   ```{note}
   作成したアイテムのキーは変更できませんが、名前の編集や削除はいつでもできます。
   ```

   ![名前とキーを入力し、［保存］をクリックします。](./using-picklists/images/03.png)

1. ［**Save**］ をクリックします。 これにより、新しいアイテムで選択リストがすぐに更新されます。

1. 上記の手順を繰り返して、リストにアイテムを追加します。

   ![選択リストに複数のアイテムを追加します。](./using-picklists/images/04.png)

<a name="adding-picklist-fields-to-objects" />

## オブジェクトへの選択リストフィールドの追加

リストが作成されると、オブジェクトフィールドを作成する際に選択することができます。 **選択リスト** フィールドタイプを選択し、必要なリストを選択します。 詳細については、 [オブジェクトへのフィールドの追加](./creating-and-managing-objects/adding-fields-to-objects.md) を参照してください。

![ユーザーは、新しいオブジェクトフィールドを作成するときに選択リストを選択できます。](./using-picklists/images/05.png)

ユーザーがオブジェクトのレイアウトでこのフィールドにアクセスすると、選択リストのアイテムを一覧表示するドロップダウンメニューとして表示されます。

![選択リストは、オブジェクトのレイアウトにドロップダウンメニューとして表示されます。](./using-picklists/images/06.png)

```{important}
リストはオブジェクトフィールドで使用されている場合は削除できませんが、リストのアイテムはいつでも編集および削除できます。

選択リストのアイテムを更新または削除すると、アイテム値を使用してすべてのオブジェクトエントリーが自動的に更新されます。
```

<a name="picklist-fields-in-api-calls" />

## API呼び出しの選択リストフィールド

選択リストフィールドを含むオブジェクトに対してPOST、PATCH、PUTの各APIコールを行う場合は、以下のフォーマットを使用する必要があります。

<a name="for-liferay-dxp-74-u1portal-74-ga5" />

### Liferay DXP 7.4 U1/Portal 7.4 GA5の場合

```json
"samplePicklist": {"key": "string"}
```

<a name="for-liferay-dxp-74-ga1portal-74-ga4" />

### Liferay DXP 7.4 GA1/Portal 7.4 GA4の場合

```json
"samplePicklist": "string"
```

<a name="additional-information" />

## 追加情報

* [オブジェクトへのフィールドの追加](./creating-and-managing-objects/adding-fields-to-objects.md)
* [オブジェクトレイアウトの設計](./creating-and-managing-objects/designing-object-layouts.md)
