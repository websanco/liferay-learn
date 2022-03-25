# データソースの管理

ワークスペースに[データソースを接続](../connecting-data-sources.md)した後、設定ページからデータソースを表示し、変更を加えることができます。 ［**設定**］ &rarr; ［**Workspace Data**］ &rarr; ［**Data Sources**］ へ移動します。

![設定で接続されているデータソースのリストを見ることができます。](./managing-data-sources/images/01.png)

Liferay DXP インスタンスをまだ接続していない場合は、 [connecting Liferay DXP to Analytics Cloud](../connecting-data-sources/connecting-liferay-dxp-to-analytics-cloud.md)をご覧ください。

また、SalesforceのデータやCSVデータなど、他のデータソースを追加することもできます。 [adding a Salesforce data source](../connecting-data-sources/adding-a-salesforce-data-source.md) または [adding a CSV data source](../connecting-data-sources/adding-a-csv-data-source.md)を参照してください。

Liferay DXPのデータソースを表示したり変更したりするには、接続されているデータソースの名前をクリックします。

![データソース名をクリックすると、新しいページが表示されます。](./managing-data-sources/images/02.png)

右側の[Current Status]パネルには、データソースのステータスが表示されています。  接続されていて同期しているデータソースの場合は **Active** 、接続されているがサイトと連絡先が構成されていないデータソースの場合は **Connected** 、接続されていないデータソースの場合は、 **Disconnected** となっています。

**Name：** データソースの名前。 名前を変更するには、編集アイコン（![Edit](../images/icon-edit.png)）をクリックして、新しい名前を入力します。 ［チェックマーク］をクリックすると保存されます。

**DXP Instance ID:** DXPインスタンスのユニークなIDを表示します。

ワークスペースからDXPインスタンスを切断する場合は、 ［**Disconnect**］ ボタンをクリックします。 確認を求められます。 なお、データソースを切断すると、アナリティクスデータのすべての同期が停止します。

**Synced Sites：** DXPサイトのAnalytics Cloudとの同期状態を表示します。 設定方法は、 [syncing sites to a property](../connecting-data-sources/scoping-sites-and-individuals-using-properties.md#syncing-sites-to-a-property)を参照してください。

**Synced Contacts：** DXP連絡先のAnalytics Cloudとの同期状態を表示します。 設定方法は、 [syncing sites to a property](../connecting-data-sources/scoping-sites-and-individuals-using-properties.md#syncing-contacts-to-a-property)を参照してください。

なお、この管理ページは、SalesforceデータソースとCSVデータソースでは異なって見えます。

## データソースの削除

データソースを削除するには、ページの右上にある ［**Delete Data Source**］ ボタンをクリックします。 確認を求められます。 なお、データソースを削除すると、接続が解除され、同期されたデータがすべて削除されます。
