# フォームストレージアダプターについて

> 利用可能：[LPS-97208](https://issues.liferay.com/browse/LPS-97208)の修正（Liferay DXP 7.2 SP3で計画）を含むLiferay DXP 7.3およびLiferay DXP 7.2バージョン。

ユーザーがフォームレコードを追加すると、フォームAPI はストレージアダプターAPIを通じてリクエストの処理をルーティングします。 これは、フォームエントリーに対して行われる他の*CRUD*操作（読み取り、更新、削除）についても同様です。 ストレージ サービスのデフォルトの実装は、`JSONDDMStorageAdapter`と呼ばれ、その名のとおり、 `DDMStorageAdapter`インターフェイスを実装し、フォームエントリーデータをJSON形式で保存します。

動的データマッピング（DDM）バックエンドは、フォームレコードの他のストレージ形式に*適応*することができます。 これにより、フォームデータをデフォルトのJSONではなく、XMLやYAML（または希望する形式）として簡単にシリアライズできます。 そして、フォームデータをLiferayデータベースを含む任意の場所に保存することを選択できます。

```{important}
新しく追加されたストレージアダプターは、新しいフォームでのみ使用することができます。 既存のすべてのフォームは、作成時に選択されたアダプター（デフォルトではJSON）を引き続き使用し、異なるストレージアダプターを選択することはできません。
```

## フォームレコードの保存

デフォルトのJSON実装では、保存リクエストに格納されているブール値`isInsert`に応じて、異なる応答をします。 trueの場合は、新しいフォームレコードを追加するロジックが呼び出され、falseの場合は、代わりに更新が行われます。 このロジックは、`insert` と `update`というメソッドに含まれています。 `DDMStorageAdapterの` の実装がこのパラダイムにも対応していることを確認してください。

## フォームレコードのシリアライズとデシリアライズ

フォームレコードのシリアライズとデシリアライズのデフォルトフォーマットはJSONです。 [サンプルプロジェクト](./writing-a-form-storage-adapter.md)では、このデフォルトフォーマットの使用方法を示しています。 `DDMContent`を異なるフォーマットで保存するには、追加のインターフェイスを実装する必要があります。

**シリアライズ：**`DDMcontent`を保存するときは、`DDMFormValues`オブジェクトをターゲットストレージ形式に変換する必要があります。

**デシリアライズ：**（ストレージアダプターの`get`メソッドで）`DDMContent`を読み取るときは、ストレージ形式から`DDMStorageAdapterGetResponse`の`Builder`コンストラクターが必要とする`DDMFormValues`オブジェクトに変換し直す必要があります。

このシリアライズのロジックに実装する`DDMFormValuesSerializer`インターフェイスと、デシリアライズのロジックに実装する`DDMFormValuesDeserializer`インターフェイスがあります。 `DDMFormValues`オブジェクトのJSONへの変換（およびその逆の変換）をサポートしているLiferayのデフォルトの実装は、それぞれ[こちら](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/io/DDMFormValuesJSONSerializer.java)と[こちら](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/io/DDMFormValuesJSONDeserializer.java)にあります。

完全な例については、[Writing a Form Storage Adapter](./writing-a-form-storage-adapter.md)を参照してください。
