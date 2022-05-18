# OSGiサービスのオーバーライドをする

LiferayのOSGiコンテナは、必要に応じてサービスを追加、削除、またはオーバーライドできる動的な環境です。 このフレームワークは、LiferayコンポーネントをOSGiサービスレジストリに登録します。各コンポーネントには、独自の可用性、ランキング、および属性があります。 これらの詳細が合わさって、コンポーネントが参照するサービスにどのようにバインドするかが決まります。

OSGiサービスをオーバーライドするには、次の手順に従います。

1. オーバーライドするサービスと、それを参照するコンポーネントを特定します。

1. 次のサービスの詳細を収集します。

   ***サービスタイプ** ：オーバーライドするサービスによって実装されるインターフェイス

   ***サービスのクラス名** ：既存のサービスのフルネーム

1. 該当する場合は、サービスを参照するコンポーネントについて次の詳細を収集します。

   ***コンポーネント名** ：オーバーライドするサービスを参照するコンポーネントのフルネーム

   ***参照名** ：ターゲットサービスを参照するフィールド名

   ***参照ポリシー** ：参照が`static`か`dynamic`か

   ***参照ポリシーオプション** ：`policy-option`が`greedy`か`reluctant`か

   ***カーディナリティ** ：参照がバインドできるまたはバインドする必要があるサービスインスタンスの数。

   サービスの [参照ポリシー](https://docs.osgi.org/javadoc/r5/enterprise/org/osgi/service/component/annotations/ReferencePolicy.html) 、 [参照ポリシーオプション](https://docs.osgi.org/javadoc/r5/enterprise/org/osgi/service/component/annotations/ReferencePolicyOption.html) 、および [カーディナリティ](https://docs.osgi.org/javadoc/r5/enterprise/org/osgi/service/component/annotations/ReferenceCardinality.html) が一緒になって、新しいサービスを採用するためのコンポーネントの条件を決定します。

1. オーバーライドするサービスによって実装されたものと同じインターフェイスを使用する新しいサービスを作成します。

1. オーバーライドしているサービスよりも高いランクを自分のサービスに指定します。

1. （オプション）必要に応じて、自分のサービスでオーバーライドするサービスを参照して呼び出します。

[サンプルモジュール](./liferay-s1j6.zip) は、OSGiサービスをオーバーライドする方法を示しています。 これらのモジュールには、新しいOSGiサービスタイプを定義するためのAPI、そのタイプの初期実装、および初期実装を参照する汎用ポートレットが含まれています。 また、初期実装をオーバーライドする方法を示すためのAPIの代替実装も含まれています。

<a name="オーバーライドするサンプルモジュールをデプロイする" />

## オーバーライドするサンプルモジュールをデプロイする

```{include} /_snippets/run-liferay-portal.md
```

次に、次の手順を実行します。

1. サンプルモジュールをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/liferay-internals/extending-liferay/liferay-s1j6.zip -O
   ```

   ```bash
   unzip liferay-s1j6.zip -d liferay-s1j6
   ```

1. `s1j6-api`、`s1j6-able-impl`、および`s1j6-web`サブフォルダから次の`gradlew`コマンドを実行して、各モジュールのJARファイルをビルドして新しいDockerコンテナにデプロイします。

   ```bash
   ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   各モジュールのJARは、その`build/libs`フォルダ（例：`s1j6-api/build/libs/com.acme.s1j6.api-1.0.0.jar`）に生成されます。

   ログメッセージは、Liferayが処理を開始し、各モジュールを正常に開始したことを示します。 これらのログには、各サービスのバンドルIDも提供されています。

   ```
   STARTED com.acme.s1j6.api_1.0.0 [1356]
   STARTED com.acme.s1j6.able.impl_1.0.0 [1357]
   STARTED com.acme.s1j6.web_1.0.0 [1358]
   ```

1. モジュールがGogoシェルを介して正常にデプロイされたことを確認します。

   ```shell
   lb | grep -i "s1j6"
   ```

   成功した場合、出力は次のようになります。

   ```
   1356|Active     |   15|Acme S1J6 API (1.0.0)|1.0.0
   1357|Active     |   15|Acme S1J6 Able Implementation (1.0.0)|1.0.0
   1358|Active     |   15|Acme S1J6 Web (1.0.0)|1.0.0
   true
   ```

提供された`api`は、`able.impl`モジュールによって実装されるOSGiサービスタイプを定義します。このタイプは、提供された`portlet`によって使用されます。 すべてがデプロイされたので、オーバーライドがどのように機能するかを試すことができます。

<a name="osgiサービスとリファレンスの詳細を収集する" />

## OSGiサービスとリファレンスの詳細を収集する

オーバーライドするサービスを特定したら、`scr:info` Gogo シェルコマンドを使用して、その重要なサービスとリファレンスの詳細を収集します。 この例では、`able.impl`サービスをオーバーライドします。

サービスの詳細を収集するには、次のコマンドを実行します。

```shell
scr:info com.acme.s1j6.able.internal.S1J6AbleImpl
```

```
Component Description: com.acme.s1j6.able.internal.S1J6AbleImpl
===============================================================
Class:         com.acme.s1j6.able.internal.S1J6AbleImpl
Bundle:        1357 (com.acme.s1j6.able.impl:1.0.0)
[...]

Component Configuration Id: 8337
--------------------------------
State:        ACTIVE
Service:      17776 [com.acme.s1j6.S1J6]
    Used by bundle 1358 (com.acme.s1j6.web:1.0.0)
Config Props: (2 entries)
  component.id = 8337
  component.name = com.acme.s1j6.able.internal.S1J6AbleImpl
References:   (total 0)
```

この簡略化された出力には、`S1J6AbleImpl`の次のサービスの詳細がリストされています。

***サービスタイプ** ：`S1J6AbleImpl`は、`S1J6`インターフェイス`com.acme.s1j6.S1J6`を実装します。

***サービスのクラス名** ：サービスのフルネームは`com.acme.s1j6.able.internal.S1J6AbleImpl`です。

また、サービスが`com.acme.s1j6.web:1.0.0`バンドル内のコンポーネントによって使用されていることも示されます。 コンポーネントの参照構成の詳細を表示するには、コンポーネントのフルネームを指定して`scr:info`コマンドを実行します。

```shell
scr:info com.acme.s1j6.web.internal.portlet.S1J6Portlet
```

```
Component Description: com.acme.s1j6.web.internal.portlet.S1J6Portlet
=====================================================================
Class:         com.acme.s1j6.web.internal.portlet.S1J6Portlet
Bundle:        1358 (com.acme.s1j6.web:1.0.0)
[...]

Component Configuration Id: 8338
--------------------------------
[...]
References:   (total 1)
  - _s1j6: com.acme.s1j6.S1J6 SATISFIED 1..1 static
    target=(*) scope=bundle (1 binding):
    * Bound to [17776] from bundle 1357 (com.acme.s1j6.able.impl:1.0.0)
```

この簡略化された出力には、次の参照構成の詳細がリストされています。

**参照名** ：`S1J6AbleImpl`サービスを参照するフィールドの名前は`_s1j6`です。

**参照ポリシー** ：コンポーネントの参照ポリシーは`static`です（デフォルト）。

**参照ポリシーオプション** ：`policy-option`が明示的に定義されていないため、デフォルトの構成`reluctant`を使用します。

**カーディナリティ** ：そのカーディナリティは必須であり、単項です（つまり、`1..1`）。

一部の参照構成は、新しいサービスまたは上位のサービスに自動的にバインドされますが、サーバーの再起動が必要なものもあります。 `S1J6Portlet`の参照構成は静的で、消極的で、必須であり、単項であるため、サーバーを新しい上位のサービスにバインドする前に、サーバーを再起動する必要があります。 新しいサービスまたは上位のサービスが利用可能になったときに、さまざまな参照構成がコンポーネントの動作にどのように影響するかについて詳しくは、 [OSGiのドキュメンテーション](http://docs.osgi.org/specification/osgi.cmpn/7.0.0/service.component.html#service.component-policy.option.action) を参照してください。

<a name="収集した詳細でosgiサービスを作成する" />

## 収集した詳細でOSGiサービスを作成する

必要なサービスと参照の詳細を収集したら、それらを使用して、ターゲットサービスをオーバーライドして呼び出すためのカスタムサービスを作成できます。

1. `@Component`アノテーションを使用して、サービスをOSGiフレームワーク内のコンポーネントとして宣言します。

1. ターゲットのOSGiサービスと同じインターフェイスを実装し、`@Component`アノテーション内でその`service`タイプを識別します。

1. インターフェイスのメソッドをオーバーライドします。

1. `@Component`アノテーション内に`service.ranking:Integer`プロパティを含めます。 そのランキングが既存のサービスよりも高いことを確認してください。

1. （オプション）既存のサービスの`component.name`を参照して呼び出します。

サンプルの`S1J6BakerImpl`モジュールは、`S1J6AbleImpl`をオーバーライドするために提供されています。

```{literalinclude} overriding-osgi-services/resources/liferay-s1j6.zip/s1j6-baker-impl/src/main/java/com/acme/s1j6/baker/internal/S1J6BakerImpl.java
   :language: java
   :lines: 8-22
```

ここで、`S1J6BakerImpl`は、`S1J6AbleImpl`と同じサービスタイプ（つまり、`com.acme.s1j6.S1J6`）を実装し、必要な`@Component`アノテーション、`service`属性、および`service.ranking`プロパティを含みます。 また、既存のサービス（つまり、`component.name=com.acme.s1j6.able.internal.S1J6AbleImpl`）を参照し、インターフェイスのメソッドをオーバーライドする一環としてサービスに委任します。 サンプルモジュールには、`S1J6AbleImpl`をオーバーライドしてから`S1J6BakerImpl`をオーバーライドするための2つの他のS1J6実装も含まれています。

合計すると、含まれている実装のランキングは次のとおりです。

* `S1J6AbleImpl`：ランキングなし（デフォルトは0）
* `S1J6BakerImpl`：100
* `S1J6CharlieImpl`：101
* `S1J6DogImpl`：101

デプロイされると、最高ランクのサービスが優先され、Liferayサーバーの再起動後に`S1J6Portlet`にバインドされます。 複数のサービスのランキングが同じ場合は、最初に登録されたサービスが優先されます。 ランクの低いサービスは無視されます。

<a name="オーバーライドモジュールと構成ファイルをデプロイする" />

## オーバーライドモジュールと構成ファイルをデプロイする

次の手順に従って、`S1J6BakerImpl`、`S1J6CharlieImpl`、および`S1J6DogImpl`をデプロイします。

1. コンソールで`s1j6-baker-impl`フォルダを開きます。

1. 次の`gradlew`コマンドを実行して、モジュールのJARファイルをビルドしてDockerコンテナにデプロイします。

   ```bash
   ../gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   ログメッセージには、Liferayが処理を開始し、バンドルIDとともにモジュールを正常に開始したことが示されます。

   ```
   STARTED com.acme.s1j6.baker_1.0.0 [1359]
   ```

1. `S1J6Portlet`の参照構成は静的で、消極的で、必須であり、単項であるため、Liferayサーバーを再起動して、`S1J6Portlet`が`S1J6BakerImpl`にバインドされるようにします。

1. `S1J6BakerImpl`が正常にデプロイされ、Gogoシェルを介してインスタンスにバインドされたことを確認します。

   ```shell
   scr:info com.acme.s1j6.web.internal.portlet.S1J6Portlet
   ```

   成功した場合、`S1J6Portlet`は`S1J6AbleImpl`よりランクが高い`S1J6BakerImpl`にバインドされます。

   ```
   References:   (total 1)
     - _s1j6: com.acme.s1j6.S1J6 SATISFIED 1..1 static
       target=(*) scope=bundle (1 binding):
       * Bound to [3248] from bundle 1359 (com.acme.s1j6.baker.impl:1.0.0)
   ```

1. `S1J6CharlieImpl`と`S1J6DogImpl`を同時にDockerコンテナにデプロイします。

   ```
   STARTED com.acme.s1j6.charlie_1.0.0 [1360]
   STARTED com.acme.s1j6.dog_1.0.0 [1361]
   ```

1. Liferayサーバーを再起動します。

1. `S1J6CharlieImpl`と`S1J6DogImpl`の両方がGogoシェルを介してインスタンスに正常にデプロイされたことを確認します。

   ```shell
   lb -s | grep -i "s1j6"
   ```

   ```
   1356|Active     |   15|com.acme.s1j6.api (1.0.0)|1.0.0
   1357|Active     |   15|com.acme.s1j6.able.impl (1.0.0)|1.0.0
   1358|Active     |   15|com.acme.s1j6.web (1.0.0)|1.0.0
   1359|Active     |   15|com.acme.s1j6.baker.impl (1.0.0)|1.0.0
   1360|Active     |   15|com.acme.s1j6.charlie.impl (1.0.0)|1.0.0
   1361|Active     |   15|com.acme.s1j6.dog.impl (1.0.0)|1.0.0
   ```

1. どのサービスが`S1J6Portlet`にバインドされているかを確認します。

   ```shell
   scr:info com.acme.s1j6.web.internal.portlet.S1J6Portlet
   ```

   ```
   References:   (total 1)
     - _s1j6: com.acme.s1j6.S1J6 SATISFIED 1..1 static
       target=(*) scope=bundle (1 binding):
       * Bound to [3249] from bundle 1360 (com.acme.s1j6.charlie.impl:1.0.0)
   ```

   `S1J6CharlieImpl`と`S1J6DogImpl`はどちらも同じランキングであるため、最初に登録されたサービスが優先されて`S1J6Portlet`にバインドされます。

<a name="追加情報" />

## 追加情報

* [モジュールプロジェクト](../fundamentals/module-projects.md)
* [OSGiサービスの使用](../fundamentals/using-an-osgi-service.md)
* [OSGiサービスとしてのAPI](../fundamentals/apis-as-osgi-services.md)
* [モジュールのライフサイクル](../architecture/module-lifecycle.md)