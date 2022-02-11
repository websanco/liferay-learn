# ドキュメントとメディアストレージクォータの設定

`data.limit.dl.storage.max.size`ポータルプロパティを有効にすることで、ドキュメントとメディアでストレージクォータを定義できます。 このプロパティの値を定義すると、定義されたクォータを超えるアップロードの試行は失敗し、エラーメッセージが表示されます。 このクォータは特に、ドキュメントとメディア、およびそのAPIのユーザー（掲示板の添付ファイル、ブログ画像など）に適用されます。

![制限を超えるアップロードの試行は失敗し、エラーメッセージが表示されます。](./setting-a-global-storage-quota-for-documents-and-media/images/01.png)

```important::
   設定はグローバルですが、各インスタンスは現在消費されているクォータを追跡します。 100MBのクォータを定義し、10個のインスタンスがある場合、ドキュメントとメディアが消費できる最大グローバルストレージは約1TBです。
```

デフォルトでは、`data.limit.dl.storage.max.size`ポータルプロパティが無効になっています。 `portal-ext.properties`ファイルを使用して、この値をオーバーライドできます。

Liferayインスタンスのドキュメントとメディアのグローバルストレージクォータを設定するには、次の手順に従います。

1. `portal-ext.properties`ファイルを作成します。

1. `data.limit.dl.storage.max.size`プロパティを新しいプロパティファイルに追加し、その値を必要なストレージクォータに設定します。

   プロパティの値はバイト単位で設定する必要があります。 たとえば、次の値はストレージクォータを100MBに設定します。

   ```properties
   data.limit.dl.storage.max.size=104857600
   ```

   負の数を割り当てるか、`0`を割り当てると、プロパティが無効になります。

1. `portal-ext.properties`ファイルを[Liferay Home](../../../installation-and-upgrades/reference/liferay-home.md)フォルダまたは`［USER_HOME]`フォルダにデプロイします。 詳細は、[Portal Properties](../../../installation-and-upgrades/reference/portal-properties.md)を参照してください。

1. Liferayサーバーを再起動して、新しいプロパティファイルを適用します。

通常の状況では、システムはストレージクォータを動的に更新します。 ただし、場合によっては、データベースの破損、ランタイムエラー、またはその他の原因により、手動による更新が必要になることがあります。 手動更新を行うには、Gogo シェルから`documentLibrary:update` OSGiコマンドを実行します。

```warning::
   ドキュメントとメディアに保存されているドキュメントの量によっては、クォータの更新に時間とリソースがかかる場合があります。 本番環境でこのコマンドを実行するときは注意してください。
```

## 追加情報

* [Documents and Media Overview](../documents-and-media-overview.md)
* [ポータルプロパティ](../../../installation-and-upgrades/reference/portal-properties.md)
