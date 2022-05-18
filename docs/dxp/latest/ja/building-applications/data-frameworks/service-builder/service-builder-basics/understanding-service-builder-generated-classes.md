# 生成されたクラスの理解と拡張

サービスビルダーは、[エンティティのテーブル](./generating-model-persistence-and-service-code.md)と、モデルクラス、永続性クラス、およびサービスクラスのテーブルの両方を生成します。 ここでは、`W9B7Entry`というエンティティに対して生成されたクラスを調べます。 次に、新しいメソッドを使用してローカルサービスを拡張し、それを呼び出します。

サンプルプロジェクトをダウンロードして解凍することから始めます。

```bash
curl https://learn.liferay.com/dxp/latest/ja/building-applications/data-frameworks/service-builder/service-builder-basics/liferay-w9b7.zip -O
```

```bash
unzip liferay-w9b7.zip
```

`w9b7-service/service.xml`ファイルを調べます：

```{literalinclude} ./understanding-service-builder-generated-classes/resources/liferay-w9b7.zip/w9b7-service/service.xml
:language: xml
```

`package-path="com.acme.w9b7"`の設定は、クラスが`-api`モジュールおよび`-service`モジュールの`com/acme/w9b7`パッケージパスに生成されることを指定します。 唯一のエンティティは`W9B7Entry`と呼ばれます。 ローカルサービス（DXP/Portalと同じJVMからアクセス可能なサービス）はありますが、リモートサービスはありません。

生成されたクラスを確認してください。

<a name="generated-classes-listing" />

## 生成されたクラスのリスト

各モジュールの`com/acme/w9b7`パッケージフォルダには、サービスビルダーで生成されたJavaクラスが含まれています。 ファイルストラクチャーに表示されるモジュールクラスは次のとおりです。

```
w9b7-api/src/main/java/com/acme/w9b7
├── exception
│   └── NoSuchW9B7EntryException.java
├── model
│   ├── W9B7Entry.java
│   ├── W9B7EntryModel.java
│   ├── W9B7EntrySoap.java
│   ├── W9B7EntryTable.java
│   └── W9B7EntryWrapper.java
└── service
    ├── persistence
    │   ├── W9B7EntryPersistence.java
    │   └── W9B7EntryUtil.java
    ├── W9B7EntryLocalService.java
    ├── W9B7EntryLocalServiceUtil.java
    └── W9B7EntryLocalServiceWrapper.java

w9b7-service/src/main/java/com/acme/w9b7
├── model
│   └── impl
│       ├── W9B7EntryBaseImpl.java
│       ├── W9B7EntryCacheModel.java
│       ├── W9B7EntryImpl.java // MODIFIABLE
│       └── W9B7EntryModelImpl.java
└── service
    ├── base
    │   └── W9B7EntryLocalServiceBaseImpl.java
    ├── impl
    │   └── W9B7EntryLocalServiceImpl.java // MODIFIABLE
    └── persistence
        └── impl
            ├── constants
            │   └── W9B7EntryPersistenceConstants.java
            ├── W9B7EntryModelArgumentsResolver.java
            └── W9B7EntryPersistenceImpl.java
```

`W9B7EntryImpl.java`クラスと`W9B7EntryLocalServiceImpl.java`クラスは、変更できる唯一のクラスです。 生成された他のクラスは変更しないでください。サービスビルダーは、サービスビルダーが実行されるたびにそのコンテンツを再生成します。

```{note}
エンティティに対してリモートサービスを有効にして（つまり、`remote-service="true"`）サービスビルダーを実行すると、サービスビルダーは、変更可能なリモートサービス実装クラス（たとえば、`W9B7EntryServiceImpl.java`）を含むリモートサービスクラスを生成します。 *Remote Services*（近日公開）を参照してください。
```

APIクラスから順に、すべてのクラスについて説明します。

<a name="api-classes" />

## APIクラス

APIクラスは、パブリックインターフェイス、ユーティリティー、および定数を定義します。

| APIクラス                         | 説明                                                                                                                                                                                                                                            |
|:------------------------------ |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `W9B7Entry`                    | `W9B7Entry`モデルインターフェイス。 `W9B7EntryModel`を拡張します。                                                                                                                                                                                               |
| `W9B7EntryModel`               | ベースモデルインターフェイス。 このインターフェイスとその`W9B7EntryModelImpl`実装は、サービスビルダーが生成するデフォルトのプロパティアクセサーのコンテナとしてのみ機能します。 すべてのヘルパーメソッドとすべてのアプリケーションロジックを`W9B7EntryImpl`に追加する必要があります。                                                                                 |
| `W9B7EntrySoap`                | `W9B7EntryModelImpl`に似たSOAPモデル。 `W9B7EntrySoap`はシリアル化可能です。 `W9B7Entry`は実装されていません。                                                                                                                                                             |
| `W9B7EntryTable`               | エンティティのテーブルを表します。                                                                                                                                                                                                                             |
| `W9B7EntryWrapper`             | `W9B7Entry`をラップするラッパー。 このクラスを拡張して、[エンティティをカスタマイズ](../../../liferay-internals/extending-liferay/creating-service-wrappers.md)することができます。                                                                                                        |
| `W9B7EntryPersistence`         | `create`、`remove`、`countAll`、`find`、`findAll`などのエンティティのCRUDメソッドを定義する永続性インターフェイス。                                                                                                                                                              |
| `W9B7EntryUtil`                | `W9B7EntryPersistenceImpl`をラップし、CRUD操作のためにデータベースへの直接アクセスを提供する永続性ユーティリティクラス。 このユーティリティは、サービスレイヤーでのみ使用する必要があります。ポートレットクラスでは、`W9B7Entry`クラスを[`@Reference`アノテーション](../../../liferay-internals/fundamentals/using-an-osgi-service.md)付きで挿入して使用します。 |
| `W9B7EntryLocalService`        | ローカルサービスインターフェイス。                                                                                                                                                                                                                             |
| `W9B7EntryLocalServiceUtil`    | `W9B7EntryLocalServiceImpl`をラップするローカルサービスユーティリティクラス。                                                                                                                                                                                          |
| `W9B7EntryLocalServiceWrapper` | `W9B7EntryLocalService`を実装するローカルサービスラッパー。 このクラスを拡張して、[エンティティのローカルサービスをカスタマイズ](../../../liferay-internals/extending-liferay/creating-service-wrappers.md)することができます。                                                                            |

<a name="implementation-classes" />

## 実装クラス

これらのクラスは、モデルレイヤー、永続性レイヤー、およびサービスレイヤーを実装します。

| 実装クラス                                        | 説明                                                                                                                                                                                            |
|:-------------------------------------------- |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| `W9B7EntryBaseImpl`                          | `W9B7EntryModelImpl`を拡張して、`W9B7_W9B7Entry` データベーステーブルの行を表します。各列は`W9B7EntryModel`プロパティにマップされます。                                                                                                |
| `W9B7EntryCacheModel`                        | キャッシュ内の`W9B7Entry`エンティティを表します。                                                                                                                                                                |
| `W9B7EntryImpl` (**MODIFIABLE**)             | モデルの実装。 このクラスを使用して、ヘルパーメソッドとアプリケーションロジックをモデルに追加できます。 ヘルパーメソッドまたはアプリケーションロジックを追加しない場合、自動生成されたフィールドゲッターとセッターのみが使用可能です。 このクラスでメソッドを追加または変更するたびに、サービスビルダーは、次回の実行時にその変更を`W9B7Entry`インターフェイスに伝播します。 |
| `W9B7EntryLocalServiceBaseImpl`              | ローカルサービスベースの実装。 これは抽象クラスです。 サービスビルダーは、さまざまなサービスクラスと永続性クラスのインスタンスをこのクラスに挿入します。                                                                                                                 |
| `W9B7EntryLocalServiceImpl` (**MODIFIABLE**) | ローカルサービスの実装。 これは、ローカルサービスで変更する必要がある唯一のクラスです。 ここにビジネスロジックを追加します。 サービスビルダーは、ここで追加または変更されたメソッドについて、次回の実行時にその変更を`W9B7EntryLocalService`インターフェイスに伝播します。                                            |
| `W9B7EntryModelArgumentsResolver`            | エンティティ値を取得するためのパラメーターを処理します。                                                                                                                                                                  |
| `W9B7EntryModelImpl`                         | ベースモデルの実装。                                                                                                                                                                                    |
| `W9B7EntryPersistenceImpl`                   | `W9B7EntryPersistence`を実装する永続性実装クラス。                                                                                                                                                          |

`*BaseImpl`抽象クラスは実装に富んでいます。 `W9B7EntryImpl`クラスと`W9B7EntryLocalServiceImpl`クラスはそれらを拡張し、機能を追加する方法を提供します。

<a name="adding-a-local-service-method" />

## ローカルサービスメソッドの追加

指定された名前と説明から`W9B7Entry`インスタンスを作成するための便利なメソッドを追加して、ローカルサービスを拡張します。

1. 次の便利なメソッドを`W9B7EntryLocalServiceImpl`クラスに追加します。

    ```java
    public W9B7Entry addW9B7Entry(String description, String name)
        throws PortalException {

        W9B7Entry w9b7Entry = w9b7EntryPersistence.create(
            counterLocalService.increment());

        w9b7Entry.setDescription(description);
        w9b7Entry.setName(name);

        return w9b7EntryPersistence.update(w9b7Entry);
    }
    ```

    基本クラスの`w9b7EntryPersistence`フィールドと`counterLocalService`フィールドを使用して、カウンターサービスによって生成されたIDを持つ`W9B7Entry`インスタンスを作成します。 説明と名前がエントリーに割り当てられ、`w9b7EntryPersistence.update`メソッド呼び出しを介してそのエントリーが永続化されます。

    ```{note}
    `W9B7EntryLocalServiceBaseImpl.java`などのサービスビルダーで生成された基本クラスは、アプリケーションで使用するための便利なフィールドとメソッドを提供します。
    ```

1. サービスビルダーを実行します。

    ```bash
    cd w9b7-service
    ```

    ```bash
    ../gradlew buildService
    ```


    出力:

    ```
    > Task :w9b7-service:buildService
    Building W9B7Entry
    Writing ../w9b7-api/src/main/java/com/acme/w9b7/model/W9B7EntryModel.java
    Writing ../w9b7-api/src/main/java/com/acme/w9b7/service/W9B7EntryLocalService.java
    Writing ../w9b7-api/src/main/java/com/acme/w9b7/service/W9B7EntryLocalServiceUtil.java
    Writing ../w9b7-api/src/main/java/com/acme/w9b7/service/W9B7EntryLocalServiceWrapper.java
    Writing src/main/resources/service.properties
    ```

   サービスビルダーは、新しいローカルサービスメソッドの実装をサポートするようにローカルサービスAPIを更新しました。

1. `w9b7-api`モジュールの`W9B7EntryLocalService`クラスで新しいメソッドシグネチャを確認します。

    ```java
    public W9B7Entry addW9B7Entry(String description, String name) throws PortalException;
    ```

新しいメソッドは、モジュールで公開できます。

<a name="testing-the-new-service-method" />

## 新しいサービスメソッドのテスト

```{include} /_snippets/run-liferay-portal.md
```

次に、モジュールをデプロイして、新しいサービスをテストします。

1. サンプルをビルドしてデプロイします。

    ```bash
    ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
    ```

    ```{note}
    このコマンドは、モジュールJARをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
    ```

1. Dockerコンテナコンソールでデプロイを確認します。

    ```bash
    STARTED com.acme.w9b7.api_1.0.0
    STARTED com.acme.w9b7.service_1.0.0
    ```

1. ［コントロールパネル］→［サーバー管理］→［スクリプト］でスクリプトコンソールに移動します。

1. 次のスクリプトを実行して、新しいメソッドを介してエントリーを追加します。

    ```groovy
    import com.acme.w9b7.service.W9B7EntryLocalServiceUtil;

    W9B7EntryLocalServiceUtil.addW9B7Entry("Remove clutter from your desk.", "Straighten Desk");

    w9b7Entries = W9B7EntryLocalServiceUtil.getW9B7Entries(-1, -1);

    for (w9b7Entry in w9b7Entries){
        out.println(w9b7Entry);
    }
    ```

    出力:

    ```
    {w9b7EntryId=1234, description=Remove clutter from your desk., name=Straighten Desk}
    ```

    エントリーはJSON形式で印刷されます。

　 これで、新しいサービスメソッドが正常に追加されました。

<a name="whats-next" />

## 次のステップ

サービスビルダーで生成されたクラスについてと、ローカルサービスメソッドを追加する方法を理解したので、ポートレットからサービスを呼び出す</a>方法を学習できます。