# グローバルサービスオプションの構成

サービスのグローバルオプションは、そのすべてのエンティティに適用されます。 オプションは次のとおりです。

* [依存性インジェクター](#dependency-injector)
* [パッケージパス](#package-path)
* [マルチバージョン同時実行制御](#multiversion-concurrency-control-mvcc)
* [ネームスペースオプション](#namespace-options)
* [作成者](#author)

## 依存性インジェクター

デフォルトの依存性注入は、OSGi宣言型サービスです。 これにより、Service Builderは他のモジュールと同じように一貫して機能します。 DXP/Portal 7.2より前は、Service BuilderはSpring依存性注入を使用していました。 唯一の違いは、サービスを利用する際にどのように注入するかということです。 詳細については、Core Frameworks</a>の **Dependency Injection** を参照してください。 両方のインジェクター設定を以下に示します。

宣言型サービス依存性インジェクター：

```xml
<service-builder dependency-injector="ds">
```

Spring依存性インジェクター：

```xml
<service-builder dependency-injector="spring">
```

```{important}
[サービスビルダーテンプレート](../../../tooling/blade-cli/generating-projects-with-blade-cli.md#creating-a-project) を使用してプロジェクトを作成する場合、宣言型サービスの依存性インジェクターとその依存性は、デフォルトでプロジェクト用に構成されています。 代わりにSpring依存性インジェクターを使用するには、[Blade CLI](../../../tooling/blade-cli/generating-projects-with-blade-cli.md)のサービスビルダーテンプレートと`--dependency-injector spring`オプションを使用してプロジェクトを作成します。 
```

```{note}
Liferay DXP/Portal 7.2より前は、Springが唯一の依存性インジェクターでした。 サービスはSpring Beanでした。 LiferayのSpring Beanフレームワークは、相互に参照するSpring Beanに対応しています。たとえば、Spring Bean AにはSpring Bean Bフィールドがあり、その逆も同様です。 Springが依存性インジェクターである場合、サービスビルダーが生成する基本サービスには、すべての `service.xml`エンティティのローカルサービスフィールドと永続性フィールドが含まれます。 これにより、循環参照が発生します。 OSGi宣言型サービスは循環参照に対応していないため、DSが依存性インジェクターである場合、サービスビルダーは基本クラスにこれらのフィールドを作成しません。 詳細については、[Understanding Service Builder Generated Classes](../service-builder-basics/understanding-service-builder-generated-classes.md)を参照してください。
```

## パッケージパス

パッケージパスは、サービスクラスと永続性クラスが生成されるパッケージを指定します。 たとえば、ゲストブックのパッケージパスは次のとおりです。

```xml
<service-builder dependency-injector="ds"
        package-path="com.acme.guestbook">
```

上記のパッケージパスは、`*-api`モジュールのサービスクラスが`com.acme.guestbook`パッケージで生成されることを保証します。 永続性クラスは、`*-service`モジュール内の同じ名前のパッケージで生成されます。 生成されたクラスの詳細については、[Understanding Service Builder Generated Classes](../service-builder-basics/understanding-service-builder-generated-classes.md)を参照してください。

## マルチバージョン同時実行制御（MVCC）

`service-builder`要素の`mvcc-enabled`属性は、デフォルトでは`false`です。 `mvcc-enabled="true"`を設定すると、すべてのエンティティに対して [マルチバージョン同時実行制御](https://en.wikipedia.org/wiki/Multiversion_concurrency_control) （MVCC）が有効になります。 システムでは、同時更新が一般的です。 MVCCがないと、知らないうちに無効な状態からデータを読み取ったり上書きしたりする人がいる可能性があります。 MVCCでは、各変更は特定の基本バージョン番号に基づいて行われます。 Hibernate永続レイヤーは更新を受信すると、`update` SQLステートメントを生成します。このステートメントでは、`where`句を使用して現在のデータバージョンが期待するバージョンであることを確認します。

現在のデータバージョンが

***予想されるバージョンと一致する** 場合、データ操作は最新のデータに基づいており、受け入れられます。

***期待されるバージョンと一致しない場合、操作しているデータが古くなっています。  DXP/Portalはデータ操作を拒否し、例外をスローします。例外をキャッチすると、ユーザーが例外を処理するのに役立ちます（操作の再試行を提案するなど）。</p></li> </ul>

**重要：** `<service-builder/>`要素で`mvcc-enabled="true"`を設定して、すべてのサービスでMVCCを有効にしてください。 サービスエンティティの更新（例：`fooService.update(object)`）を呼び出すときは、必ずトランザクションで呼び出してください。 ユーザーが処理できるように、UIで拒否されたトランザクションを公開してください。

```xml
<service-builder dependency-injector="ds"
         package-path="com.acme.guestbook"
         mvcc-enabled="true">
```

## ネームスペースオプション

サービスビルダーは、サービスのネームスペースを使用してデータベーステーブルに名前を付けます。 たとえば、 **GB** はゲストブックアプリケーションサービスのネームスペースとして機能できます。

```xml
<service-builder dependency-injector="ds"
         package-path="com.acme.guestbook"
         mvcc-enabled="true">
    <namespace>GB</namespace>
    <author>Liferay</author>
```

サービスビルダーは、`src/main/resources/sql`フォルダに生成される次のSQLスクリプトのネームスペースを使用します。

* `indexes.sql`
* `sequences.sql`
* `tables.sql`

```{note}
生成されたSQLスクリプトのフォルダ場所は設定可能です。 たとえば、Gradleを使用している場合は、以下の例で `databaseNameMaxLength`設定が適用されるのと同じ方法で、プロジェクトのGradle `build.gradle`ファイルで` sqlDir`設定を定義できます。 
```

サービスビルダーは、SQLスクリプトを使用して、`service.xml`が定義するすべてのエンティティのデータベーステーブルを作成します。 データベーステーブル名には、作成時にネームスペースが付加されます。 この例のネームスペースの値は`GB`であるため、エンティティ用に作成されたデータベーステーブル名はプレフィックスとして`GB__`で始まります。 各サービスビルダープロジェクトのネームスペースは一意である必要があります。 個別のプラグインは個別のネームスペースを使用する必要があり、Liferayエンティティ（`Users`や`Groups`など）ですでに使用されているネームスペースを使用しないでください。 Liferayのデータベースのテーブル名をチェックして、すでに使用されているネームスペースを確認してください。

**警告：** ネームスペースの値の割り当てには注意が必要です。 一部のデータベースには、データベーステーブルと列名の長さに強い制限があります。 サービスビルダーの [Gradleプラグイン](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]/modules/sdk/gradle-plugins-service-builder) のパラメーター`databaseNameMaxLength`は、テーブル名と列名に使用できる最大長を設定します。 以下に、ビルドファイルで`databaseNameMaxLength`を設定する例をいくつか示します。

**Gradle `build.gradle`**

```groovy
buildService {
    ...
    databaseNameMaxLength = 64
    ...
}
```

## 作成者

グローバル情報の最後の部分として、`service.xml`ファイルにサービスの **作成者** としての名前を入力します。 サービスビルダーは、生成するすべてのJavaクラスとインターフフェイスに指定された名前の`@author`アノテーションを追加します。  `service.xml` ファイルを保存します。 次に、サービスのエンティティを追加します。

```xml
<service-builder dependency-injector="ds"
         package-path="com.acme.guestbook"
         mvcc-enabled="true">
    <namespace>GB</namespace>
    <author>Liferay</author>
```
