# 条件エバリュエーターの作成

ワークフローの [条件ノード](../designing-and-managing-workflows/workflow-designer/using-condition-nodes.md) は、Groovyスクリプトを使用して、ワークフローアイテムが通過する適切なトランジションを決定します。 Groovyのロジックをワークフロー定義の `<script>` 要素に直接記述する代わりに、 `ConditionEvaluator` インターフェースを実装することで、Javaのロジックを展開することができます。

1. Javaの実装を書きます。
2. ワークフロー定義XMLファイルからJavaクラスを呼び出します。

まず、 `ConditionEvaluator`をデプロイし、動作を確認します。

<a name="deploy-a-condition-evaluator" />

## コンディション評価装置の導入

```{include} /_snippets/run-liferay-portal.md
```

次に、以下の手順を実行します。

1. Acme R6J9 Implementationプロジェクトをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/process-automation/workflow/developer-guide/liferay-r6j9.zip -O
   ```

   ```bash
   unzip liferay-r6j9.zip
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
   STARTED com.acme.r6j9.impl_1.0.0
   ```

```{note}
便宜上、`ConditionEvaluator`の`activate`メソッドは、R6J9 Conditional Approverのワークフロー定義をオートロードしました。 このコードは、ワークフロープロセスビルダーに移動し、ワークフロー定義をアップロードするのと同じことを実現します。 [新しいワークフロー定義のアップロード](../designing-and-managing-workflows/managing-workflows.md#uploading-a-new-workflow-definition) を参照してください。
```

<a name="test-the-condition-evaluator" />

## 条件エバリュエーターをテストする

Acme R6J9 Condition Evaluatorを使用するには、ブログエントリーで使用するように設定し、管理者のユーザーで新しいブログエントリーを追加します。

1. グローバルメニューの［アプリケーション］タブで、［ワークフロー］ &rarr; ［プロセスビルダー］を開きます。

2. 設定タブで、ブログのエントリアセットタイプにR6J9  Conditional Approver の定義を割り当てる。

3. ［**保存**］ をクリックします。

4. デフォルトの管理者であるUser Test Testを使って、サイトメニューの &rarr; コンテンツ& データ&rarr; ブログを開きます。

5. 追加ボタン（![Add](../../../images/icon-add.png)）をクリックします。

6. タイトルとコンテンツの欄に何かを入力し、 **公開申請** をクリックします。

7. メインのBlogsビューに戻り、エントリーが表示され、ステータスが **承認済み** と表示されていることを確認します。

   ```{tip}
   最初にステータスが［返答待ち］と表示された場合は、ページを更新してください。
   ```

   ![管理者であるユーザーがブログのエントリを送信したため、ワークフローによって自動承認されました。](./creating-a-condition-evaluator/images/01.png)

唯一の承認者の定義や、管理者ではない別のユーザーでテストすると、ブログのエントリが **返答待ち** というステータスで追加されていることがわかります。

<a name="understanding-the-r6j9-condition-evaluator" />

## R6J9Condition Evaluatorについて

Acme R6J9 Implementationプロジェクトには、ワークフローのユーザーがAdministrator Roleを持っている場合に、 `approve` の遷移をトリガーするための条件評価器が含まれています。 1つのクラスを含んでいます： `R6J9ConditionEvaluator`。

このプロジェクトでは、条件付き評価者に加えて、条件評価者を使用するR6J9 Conditional Approverというワークフロー定義が含まれ、自動でロードされます。

条件評価者は、ワークフローに送られるアセットのステータスを変更しません。 これは、単にトランジションを実行するための便利な方法を提供するものです。 ワークフローステータスの変更が必要な場合は、 `WorkflowStatusManagerUtil` クラスの `UpdateStatusを` 方法を呼び出します。 定義の `承認済み` 状態ノードは、ステータスを承認済みに設定します。

```{literalinclude} ./creating-a-condition-evaluator/resources/liferay-r6j9.zip/r6j9-impl/src/main/resources/com/acme/r6j9/internal/kaleo/runtime/condition/dependencies/r6j9-workflow-definition.xml
   :dedent: 1
   :language: xml
   :lines: 174-175, 186-201
```

### `ConditionEvaluator`を実装します。

条件付き評価者クラスは、 `com.liferay.portal.workflow.kaleo.runtime.condition.ConditionEvaluator` インターフェースを実装し、単一の `evaluate` メソッドをオーバーライドします。 `scripting.language=java` コンポーネントプロパティを設定します。

```{literalinclude} ./creating-a-condition-evaluator/resources/liferay-r6j9.zip/r6j9-impl/src/main/java/com/acme/r6j9/internal/kaleo/runtime/condition/R6J9ConditionEvaluator.java
   :dedent: 0
   :language: java
   :lines: 29-32
```

`evaluate` メソッドは、ワークフロー定義で呼び出されたときに、ワークフローがトランジションを介して適切なノードに進むことができるように、有効なトランジション名を返す必要があります。

`evaluate` メソッドは、 `KaleoCondition` と `ExecutionContext`の2つのパラメータを受け取ります。 ワークフローエンジンは、ワークフロープロセスの中で条件判定器を呼び出す役割を担っているため、お客様のコードではこれらのオブジェクトをインスタンス化したり構築したりする必要はありません。 しかし、そこから有益な情報を得ることができます。 例えば、以下のようになります。R6J9 Condition Evaluatorは、 `workflowContext` (type `Map`) と `serviceContext` (type `ServiceContext`) を<br /> から取得します。index='4' /> serviceContext </code> (タイプ ` ServiceContext `) と、 `ExecutionContext`から構成されています。

```{literalinclude} ./creating-a-condition-evaluator/resources/liferay-r6j9.zip/r6j9-impl/src/main/java/com/acme/r6j9/internal/kaleo/runtime/condition/R6J9ConditionEvaluator.java
   :dedent: 2
   :language: java
   :lines: 39-41
```

`workflowContext` はワークフローユーザーのIDを、 `serviceContext` はバーチャルインスタンスのID（ `companyId`）を取得するために使用されます。 どちらも、ユーザーが管理者権限 **管理者** を持っているかどうかを確認するために必要です。ユーザーが管理者権限を持っている場合、ワークフローによって資産が自動承認されます（**承認** というトランジションに沿って送信されます）。 それ以外の場合は、 **review** トランジションを実行します。

```{literalinclude} ./creating-a-condition-evaluator/resources/liferay-r6j9.zip/r6j9-impl/src/main/java/com/acme/r6j9/internal/kaleo/runtime/condition/R6J9ConditionEvaluator.java
   :dedent: 2
   :language: java
   :lines: 46-53
```

### ワークフロー定義での `ConditionEvaluator` の呼び出しについて

Acme R6J9実装プロジェクトで自動ロードされたR6J9 Conditional Approverワークフロー定義は、Liferay DXP/Portalに同梱されている唯一の承認者唯一の承認者の定義とほぼ同じです。 最初の違いは、 `が作成された` の状態ノードにあります。 実行唯一の承認者の定義では，レビューの遷移を常に実行するが，R6J9 Conditional Approverでは， `チェック・ロール` の遷移を実行する． 主な違いは、同じ名前の **条件ノード** 追加です。 conditionノードは、 `R6J9ConditionEvaluator` に依存して条件ロジックを実行します。

```{literalinclude} ./creating-a-condition-evaluator/resources/liferay-r6j9.zip/r6j9-impl/src/main/resources/com/acme/r6j9/internal/kaleo/runtime/condition/dependencies/r6j9-workflow-definition.xml
   :dedent: 1
   :language: xml
   :lines: 11-27
```

承認されたトランジションとレビューされたトランジションが、条件ノードの有効なターゲットとして追加されることに注意してください。 `ConditionEvaluator` で使用されているトランジションが、それが呼び出された条件ノードから有効なトランジションとして含まれていることを確認してください。
