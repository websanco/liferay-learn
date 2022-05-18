# 新しいディスパッチタスクエグゼキュータの作成

各ディスパッチタスクは、`DispatchTaskExecutor`インターフェイスを実装することによって作成され、任意のロジックを実行できます。 Liferay DXPは、すぐに使える複数のエグゼキュータを提供しますが、独自のエグゼキュータを作成することもできます。 作成してデプロイしたら、Liferayインスタンスにディスパッチタスクを追加できます。

次の手順に従って、`DispatchTaskExecutor`インターフェイスの独自の実装を作成します。

1. **OSGIコンポーネント** ：`@Component`アノテーションを使用して、OSGiフレームワーク内でモジュールをコンポーネントとして宣言します。

1. **サービス** ：モジュールを`@Component`アノテーション内の`DispatchTaskExecutor.class`サービスとして識別します。

1. **OSGiプロパティ** ：次のプロパティを`@Component`アノテーションに追加します。

   * `dispatch.task.executor.name`：ディスパッチUIでエグゼキュータの名前に使用される文字列を定義します。

      ```{note}
      ディスパッチタスクでローカライズされた名前を使用する場合は、 `dispatch.task.executor.name`プロパティの言語キー値をモジュールの`resources/content/Language.properties`ファイルに追加します。
      ```

   * `dispatch.task.executor.type`：適切なディスパッチタスクエグゼキュータおよびディスパッチトリガーに一致する一意の`type`値を定義します。

      ```{note}
      正しいエグゼキュータが一致するように、値は一意である必要があります。 値が一意でない場合、ログには起動時にエラーが表示され、同じプロパティ値を持つエグゼキュータが示されます。
      ```

1. [**`DispatchTaskExecutor`**](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dispatch/dispatch-api/src/main/java/com/liferay/dispatch/executor/DispatchTaskExecutor.java) ：`DispatchTaskExecutor`インターフェイスを実装するか、その実装を拡張します（`BaseDispatchTaskExecutor`など）。

      ```{important}
      ディスパッチフレームワークはタスクの同時実行を制御するためにこれらのログに依存しているため、 `DispatchTaskExecutor`インターフェイスの実装はディスパッチタスクのステータスログを処理する必要があります。

      便宜を図るため、Liferayは、ディスパッチタスクのステータスを`IN PROGRESS`、`SUCCESSFUL`、または`FAILED`としてログに記録する`BaseDispatchTaskExecutor`抽象 [クラス](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dispatch/dispatch-api/src/main/java/com/liferay/dispatch/executor/BaseDispatchTaskExecutor.java) を提供します。 
      ```

1. **メソッド** ：`DispatchTaskExecutor`インターフェイスを直接実装している場合は、`execute()`メソッドをオーバーライドしてカスタムロジックを実装します。 代わりに、`BaseDispatchTaskExecutor`抽象クラスを拡張する場合は、その`doExecute()`メソッドをオーバーライドします。

   ```{note}
   `getName()`メソッドは廃止予定となり、`dispatch.task.executor.name`プロパティに置き換えられました。
   ```

   ```{tip}
   `dispatchTrigger.getDispatchTaskSettings()`メソッドを使用して、ディスパッチタスクの設定エディターで設定されたプロパティをフェッチできます。
   ```

次のサンプルモジュールは、カスタムのディスパッチタスクエグゼキュータを作成してLiferayインスタンスにデプロイする方法を示しています。

<a name="deploying-the-sample-dispatch-task-executor" />

## サンプルのディスパッチタスクエグゼキュータのデプロイ

```{include} /_snippets/run-liferay-portal.md
```

次の手順に従って、サンプルのディスパッチタスクエグゼキュータをダウンロード、ビルドして、新しいDockerコンテナにデプロイします。

1. サンプルモジュールをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/docs/dxp/latest/en/building-applications/core-frameworks/dispatch-framework/liferay-s7a3.zip -O
   ```

   ```bash
   unzip liferay-s7a3.zip -d liferay-s7a3
   ```

1. `gradlew`コマンドを実行してJARファイルをビルドし、それを新しいDockerコンテナにデプロイします。

   ```bash
   ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   JARは、`build/libs`フォルダ（つまり、`s7a3-impl/build/libs/com.acme.s7a3.impl-1.0.0`）に生成されます。

1. モジュールが正常にデプロイされ、コンテナコンソールを介して開始されたことを確認します。

   ```log
   Processing com.acme.s7a3.impl-1.0.0.jar
   STARTED com.acme.s7a3.impl-1.0.0 [1656]
   ```

1. モジュールを使用してLiferayインスタンスに [新しいディスパッチタスクを追加](./using-dispatch.md#adding-a-new-dispatch-task) することにより、モジュールが機能していることを確認します。

   ![新しいテンプレートを使用して、新しいディスパッチタスクを追加します。](./creating-a-new-dispatch-task-executor/images/01.png)

   タスクを作成したら、 ［**今すぐ実行**］ をクリックします。

   ![新しいディスパッチタスクの［今すぐ実行］をクリックします。](./creating-a-new-dispatch-task-executor/images/02.png)

   成功すると、実行時に次のメッセージがコンソールに出力されます。

   ```log
   INFO [liferay/dispatch/executor-2][S7A3DispatchTaskExecutor:30] Invoking #doExecute(DispatchTrigger, DispatchTaskExecutorOutput)
   ```

   ディスパッチタスクをクリックして ［**Logs**］ に移動し、以前のすべての実行のリストを表示することもできます。

   ![ディスパッチタスクのログを表示および管理します。](./creating-a-new-dispatch-task-executor/images/03.png)

<a name="code-for-the-sample-dispatch-task-executor" />

## サンプルのディスパッチタスクエグゼキュータのコード

```{literalinclude} creating-a-new-dispatch-task-executor/resources/liferay-s7a3.zip/s7a3-impl/src/main/java/com/acme/s7a3/internal/dispatch/executor/S7A3DispatchTaskExecutor.java
   :language: java
   :lines: 15-44
```

モジュールはOSGi `@Component`として宣言され、`dispatch.task.executor.name`と`dispatch.task.executor.type`の2つのプロパティを定義します。 その後、モジュールを`DispatchTaskExecutor.class`サービスとして識別します。

`@Component`アノテーションに続いて、モジュールは`BaseDispatchTaskExecutor`抽象クラスを拡張し、`doExecute`メソッドをオーバーライドします。 このメソッドは、`LogFactoryUtil`を使用して、コンソールのログに通知メッセージを表示します。

<a name="additional-information" />

## 追加情報

* [ディスパッチフレームワークを理解する](./understanding-the-dispatch-framework.md)
* [ディスパッチの使用](./using-dispatch.md)
* [ディスパッチUIリファレンス](./dispatch-ui-reference.md)
