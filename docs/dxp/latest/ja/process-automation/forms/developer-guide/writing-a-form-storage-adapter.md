# フォームストレージアダプターの書き込み

> 利用可能： [LPS-97208](https://issues.liferay.com/browse/LPS-97208) の修正（Liferay DXP 7.2 SP3で計画）を含むLiferay DXP 7.3およびLiferay DXP 7.2バージョン。

デフォルトでは、フォームはLiferay DXPのデータベースにJSONとして保存されます。 この例では、フォームレコードの永続化イベントにカスタムロジックを挿入するために、新しいストレージアダプターを実装する方法を紹介します。

![DDMストレージアダプターを使用して、フォームアプリケーションにストレージタイプを追加します。](./writing-a-form-storage-adapter/images/01.png)

最初に、 [デフォルトのストレージアダプター](https://github.com/liferay/liferay-portal/blob/［$LIFERAY **LEARN** PORTAL **GIT** TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/storage/JSONDDMStorageAdapter.java) がフォームレコードをLiferay DXPのデータベースにJSONコンテンツとして保存する方法を確認します。 そして、各フォームレコードをファイルシステムに保存するロジックを追加します。

<a name="examine-a-running-ddm-storage-adapter" />

## 実行中のDDMストレージアダプターを調べる

ストレージアダプターの動作を確認するために、サンプルをデプロイし、サンプルアダプターを使用していくつかのフォームデータを追加します。

### サンプルをデプロイする

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. [DDMストレージアダプターのプロジェクト](./writing-a-form-storage-adapter/resources/liferay-r2f1.zip) をダウンロードして解凍します。

    ```bash
    curl https://learn.liferay.com/dxp/latest/ja/process-automation/forms/developer-guide/liferay-r2f1.zip -O
    ```

    ```bash
    unzip liferay-r2f1.zip
    ```

1. モジュールのルートから、ビルドおよびデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```tip::
       このコマンドは、デプロイされたjarをDockerコンテナの/opt/liferay/osgi/modulesにコピーするのと同じです。
    ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.r2f1.impl_1.0.0 [1009]
    ```

### デプロイされたストレージアダプターを使用する

1. ブラウザで<http://localhost:8080>を開きます。

1. **サイトメニュー** &rarr; **コンテンツ & データ** &rarr; **フォーム** のフォームアプリケーションに移動します。

1. **追加** ボタン（![Add](./../../../images/icon-add.png)）をクリックして、フォームビルダーを開きます。

1. ［フォームビルダー］ビューで、 **オプション** ボタン（![Options](./../../../images/icon-options.png)）をクリックし、 ［**Settings**］ ウィンドウを開きます。

1. ［**ストレージの種類を選択する**］ で、 ［**R2F1 Dynamic Data Mapping Storage Adapter**］ タイプを選択し、 ［**Done**］ をクリックします。

1. フォームに[テキストフィールド](../creating-and-managing-forms/creating-forms.md)を追加し、フォームを公開して、何度か送信します。

1. フォームデータが保持されていることを確認するには、フォームのレコードに移動します。

［**サイトメニュー**］ → ［**コンテンツ**］ → ［**フォーム**］ から、フォームの **アクション** ボタン(![Actions](./../../../images/icon-actions.png))をクリックして、 ［**エントリの参照**］ をクリックします。

   ![フォームエントリーが追加されたことを確認します。](./writing-a-form-storage-adapter/images/02.png)

1. さらに、各CRUDメソッドにロギングが提供され、サンプルのメソッドが呼び出されていることが示されています。

   ```bash
   WARN  [http-nio-8080-exec-5][R2F1DDMStorageAdapter:82] Acme storage adapter's save method was invoked
   ```

<a name="understand-the-extension-point" />

## 拡張ポイントを理解する

この例には、`R2F1DDMStorageAdapter`という1つのクラスのみが含まれています。これは、フォームエントリーを格納するためのロジックを提供する`DDMStorageAdapter`を実装しているサービスです。  今のところ、デプロイされた例は、デフォルトのJSON実装`JSONDDMStorageAdapter`をラップしているだけです。 後で、すでにここにあるコードにファイルシステムのストレージを追加します。

### アダプタークラスをOSGiコンテナに登録する

`DDMFileSystemStorageAdapter`は、`DDMStorageAdapter`インターフェイスを実装していますが、OSGiサービスとして登録する必要があります。

```java
@Component(
    property = "ddm.storage.adapter.type=r2f1-ddm-storage-adapter",
    service = DDMStorageAdapter.class
)
public class R2F1DDMStorageAdapter implements DDMStorageAdapter {
```

```{note}
   ``r2f1-ddm-storage-adapter``キーは、``src/main/resources/content/Language.properties``ファイルと、``bnd.bnd``ファイル内の``Provide-Capability``ヘッダーによって、`R2F1 Dynamic Data Mapping Storage Adapter`という値にローカライズされます。
```

`service`コンポーネントプロパティは、実装を`DDMStorageAdapter`サービスとして登録します。

プロパティ`ddm.storage.adapter.type`は識別子を提供し、サービスが一意の`DDMStorageAdapter`の実装として登録されるようにします。 他のサービスでも次のように参照できるようになりました。

```java
@Reference(target = "(ddm.storage.adapter.type=r2f1-ddm-storage-adapter)")
private DDMStorageAdapter jsonWrapperDDMStorageAdapter;
```

### DDMStorageAdapterインターフェイスを理解する

このインターフェイスでは、フォームレコードのCRUD操作を処理するために、`delete`、`get`、`save`の3つのメソッドが必要です（更新ロジックも処理します）。

```java
public DDMStorageAdapterDeleteResponse delete(
        DDMStorageAdapterDeleteRequest ddmStorageAdapterDeleteRequest)
    throws StorageException;
```

```java
public DDMStorageAdapterGetResponse get(
        DDMStorageAdapterGetRequest ddmStorageAdapterGetRequest)
    throws StorageException;
```

```java
public DDMStorageAdapterSaveResponse save(
        DDMStorageAdapterSaveRequest ddmStorageAdapterSaveRequest)
    throws StorageException;
```

各メソッドは、 **DDMStorageAdapter[ [保存](https://github.com/liferay/liferay-portal/blob/[$LIFERAY** LEARN_PORTAL_GIT_TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/storage/DDMStorageAdapterSaveResponse.java) / [取得](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/storage/DDMStorageAdapterGetResponse.java) / [削除](https://github.com/liferay/liferay-portal/blob/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/apps/dynamic-data-mapping/dynamic-data-mapping-api/src/main/java/com/liferay/dynamic/data/mapping/storage/DDMStorageAdapterDeleteSaveResponse.java) ]レスポンス_ オブジェクトを返さなければなりません。静的なインナー `Builder` クラスの `newBuilder` メソッドを使用して構築されます。

すべてのメソッドに`DDMStorageAdapter［Save/Delete/Get］Request`が渡されます。 リクエストオブジェクトには、有用なコンテキスト情報を返すgetterメソッドが含まれています。

<a name="implement-file-system-storage" />

## ファイルシステムストレージを実装する

この例では、必要なメソッドをすでに上書きしています。 機能性を考慮したプライベートのユーティリティーメソッドを作成し、上書きされたメソッドからそれらを呼び出します。

### サービスの依存関係を宣言する

このコードは、OSGiコンテナにデプロイされた2つのサービスに依存しています。 `org.osgi.service.component.annotations.Reference`によって提供されるDeclarative Services `@Reference`アノテーションを使用して、クラスの最後にこれらの宣言を追加します。

```java
@Reference
private DDMContentLocalService _ddmContentLocalService;

@Reference
private DDMFormValuesSerializerTracker _ddmFormValuesSerializerTracker;
```

`com.liferay.dynamic.data.mapping.service.DDMContentLocalService`および`com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerTracker`をインポートします。

### ロガーを作成する

クラスのロガーを作成し、`_log`変数に設定します。

```java
private static final Log _log = LogFactoryUtil.getLog(
    R2F1DDMStorageAdapter.class);
```

これは、CRUDメソッドの1つが呼び出されるたびに、いくつかのログメッセージを追加するために使用されます。

### ファイル削除を実装する

1. プライベート変数 `_PATHNAME` を設定することで、ファイルの保存先をコントロールすることができます。 ここでのパスは、Dockerコンテナ内のLiferayのインストール場所を指しています。

   ```java
   private static final String _PATHNAME = "/opt/liferay/form-records";
   ```

1. `_deleteFile`ユーティリティメソッドを作成します（`java.io.File`クラスをインポート）。

   ```java
   private void _deleteFile(long fileId) {
       File file = new File(_PATHNAME + "/" + fileId);

       file.delete();

       if (_log.isWarnEnabled()) {
        _log.warn("Deleted file with the ID " + fileId);
       }
   }
   ```

1. 上書きされた `delete` メソッドを探します。 `return` ステートメントの直前に以下を追加します。

   ```java
    long fileId = ddmStorageAdapterDeleteRequest.getPrimaryKey();

    _deleteFile(fileId);
   ```

これで、このコードは、データベース内のコピーを削除する前に、まずファイルシステムからファイルを削除します。

### ファイル取得を実装する

`get`メソッドと同じ手順で、プライベートのユーティリティメソッドを作成し、それを呼び出します。

1. `_getFile`ユーティリティメソッドを追加します。

   ```java
    private void _getFile(long fileId) throws IOException {
        try {
            if (_log.isWarnEnabled()) {
                _log.warn(
                    "Reading the file with the ID " + fileId + ": " +
                        FileUtil.read(_PATHNAME + "/" + fileId));
            }
        }
        catch (IOException e) {
            throw new IOException(e);
        }
    }
    ```

   `com.liferay.portal.kernel.util.FileUtil`および`java.io.IOException`をインポートします。

1. 上書きされた`get`メソッド（`try`ブロック内）で、`return`ステートメントの直前に以下を挿入し、`storageId`（`ddmStorageAdapterGetRequest.getPrimaryKey()`で取得）を`fileId`として設定し、取得したコンテンツをLiferayのログに出力する`_getFile`ユーティリティメソッドを呼び出します。

   ```java
   long fileId = ddmStorageAdapterGetRequest.getPrimaryKey();

   _getFile(fileId);
   ```

### ファイル作成ロジックを実装する

保存リクエストには、新しいレコードが追加される場合と既存のレコードが更新される場合の2つのタイプがあります。 保存するたびに、現在の`ddmFormValues`コンテンツを使用して、`update`メソッドで既存のファイルを上書きします。

1. `_saveFile`ユーティリティメソッドを作成します。

   ```java
   private void _saveFile(long fileId, DDMFormValues formValues)
       throws IOException {

       try {
           String serializedDDMFormValues = _serialize(formValues);

           File abstractFile = new File(String.valueOf(fileId));

           FileUtil.write(
               _PATHNAME, abstractFile.getName(), serializedDDMFormValues);

           if (_log.isWarnEnabled()) {
               _log.warn("Saved a file with the ID" + fileId);
           }
       }
       catch (IOException e) {
           throw new IOException(e);
       }
   }
    ```

   `com.liferay.dynamic.data.mapping.storage.DDMFormValues`および`java.io.File`をインポートします。

1. `_serialize`ユーティリティメソッドを作成し、`DDMFormValues`オブジェクトをJSONに変換します。

    ```java
    private String _serialize(DDMFormValues ddmFormValues) {
        DDMFormValuesSerializer ddmFormValuesSerializer =
            _ddmFormValuesSerializerTracker.getDDMFormValuesSerializer("json");

        DDMFormValuesSerializerSerializeRequest.Builder builder =
            DDMFormValuesSerializerSerializeRequest.Builder.newBuilder(
                ddmFormValues);

        DDMFormValuesSerializerSerializeResponse
            ddmFormValuesSerializerSerializeResponse =
                ddmFormValuesSerializer.serialize(builder.build());

        return ddmFormValuesSerializerSerializeResponse.getContent();
    }
    ```

    `com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializer`、`com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeRequest`、および`com.liferay.dynamic.data.mapping.io.DDMFormValuesSerializerSerializeResponse`をインポートします。

1. このロジックと`_saveFile`への呼び出しを、既存の`return`ステートメントを置き換えて`save`メソッドに追加します。

   ```java
    DDMStorageAdapterSaveResponse jsonStorageAdapterSaveResponse =
        _jsonStorageAdapter.save(ddmStorageAdapterSaveRequest);

    long fileId = jsonStorageAdapterSaveResponse.getPrimaryKey();

    _saveFile(fileId, ddmStorageAdapterSaveRequest.getDDMFormValues());

    return jsonStorageAdapterSaveResponse;
   ```

   `_jsonStorageAdapter.save`の呼び出しが最初に行われ、新しいフォームエントリー用にプライマリーキーが作成されます。 このプライマリーキーは、`fielId`を作成するために`Response`オブジェクトから取得されます。

<a name="deploy-and-test-the-storage-adapter" />

## ストレージアダプターをデプロイしてテストする

先ほどと同じ`deploy`コマンドを使用してストレージアダプターをデプロイします。 モジュールルートから、以下を実行します。

```bash
./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
```

以下のように動作を確認します。

1. **サイトメニュー** &rarr; **コンテンツ** &rarr; **フォーム** にあるフォームアプリケーションにアクセスします。

1. **追加** ボタン![Add](./../../../images/icon-add.png)をクリックして、フォームビルダーを開きます。

1. ［フォームビルダー］ビューで、 **オプション** ボタン（![Options](./../../../images/icon-options.png)）をクリックし、 ［**Settings**］ ウィンドウを開きます。

1. リストを選択フィールド ［**ストレージの種類を選択する**］ から、 ［**R2F1 Dynamic Data Mapping Storage Adapter**］ タイプを選択し、 ［**完了**］ をクリックします。

1. フォームに[テキストフィールド](../creating-and-managing-forms/creating-forms.md)を追加し、フォームを公開して、何度か送信します。

1. フォームレコードがコンテナのファイルシステムに書き込まれたことを確認するには、ログを確認します。 以下のようなメッセージが表示されます。

   ```bash
   WARN  [http-nio-8080-exec-5][R2F1DDMStorageAdapter:82] Acme storage adapter's save method was invoked
   WARN  [http-nio-8080-exec-5][R2F1DDMStorageAdapter:134] Saved a file with the ID42088
   WARN  [http-nio-8080-exec-5][R2F1DDMStorageAdapter:61] Acme storage adapter's get method was invoked
   WARN  [http-nio-8080-exec-5][R2F1DDMStorageAdapter:112] Reading the file with the ID 42088: {"availableLanguageIds":["en **US"],"defaultLanguageId":"en** US","fieldValues":[{"instanceId":"EJ5UglA1","name":"Field51665758","value":{"en_US":"Stretched limousine"}}]}
   ```

<a name="conclusion" />

## まとめ

`DDMStorageAdapter`を実装することで、フォームレコードを任意のストレージ形式で保存することができます。
