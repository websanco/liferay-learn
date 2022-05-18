# アクションエグゼキュータの作成

ワークフローノードは、[Groovyスクリプト](./using-the-script-engine-in-workflow.md)を介してカスタムロジックを実行する`<action>`要素を含むことができます。

```xml
<action>
    <name>approve</name>
    <script>
        <![CDATA[
            import com.liferay.portal.kernel.workflow.WorkflowConstants;
            import com.liferay.portal.kernel.workflow.WorkflowStatusManagerUtil;

            WorkflowStatusManagerUtil.updateStatus(WorkflowConstants.getLabelStatus("approved"), workflowContext);
        ]]>
    </script>
    <script-language>groovy</script-language>
    <execution-type>onEntry</execution-type>
</action>
```

Groovyアクションロジックをワークフロー定義の `<script>` 要素に直接記述する代わりに、 `ActionExecutor` インターフェースを実装することで、Javaロジックを実行することができます。

1. Javaの実装を書きます。
2. ワークフロー定義XMLファイルからJavaクラスを呼び出します。

まず、 `ActionExecutor`をデプロイし、動作を確認します。

<a name="deploy-an-action-executor" />

## アクション・エクゼキュータの配置

```{include} /_snippets/run-liferay-dxp.md
```

次に、次の手順を実行します。

1. Acme E5C9 Implementationプロジェクトをダウンロードして解凍します。

   ```bash
   curl https://learn.liferay.com/dxp/latest/ja/process-automation/workflow/developer-guide/liferay-e5c9.zip -O
   ```

   ```bash
   unzip liferay-e5c9.zip
   ```

1. モジュールのルートから、ビルドおよびデプロイします。

   ```bash
   ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   ```tip::
      このコマンドは、デプロイされたjarをDockerコンテナの ``/opt/liferay/osgi/modules``にコピーするのと同じです。
   ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

   ```bash
   STARTED com.acme.e5c9.impl_1.0.0
   ```

```{note}
   利便性のために、 ``ActionExecutor`` の ``activate`` メソッドは、 E5C9 唯一の承認者 のワークフローの定義をオートロードしました。 このコードは、ワークフロープロセスビルダーに移動し、ワークフロー定義をアップロードするのと同じことを実現します。 `アップロード新しいワークフロー定義を参照してください <../designing-and-managing-workflows/managing-workflows.md#uploading-a-new-workflow-definition>` __。
```

<a name="test-the-action-executor" />

## アクションエグゼキュータをテストする

Acme E5C9 Action Executorを使用するには、ワークフロー定義をブログエントリーで使用するように設定し、管理者ユーザーで新しいブログエントリーを追加します。

1. グローバルメニューの［アプリケーション］タブで、［ワークフロー］ &rarr; ［プロセスビルダー］を開きます。

1. 設定タブで、［E5C9 唯一の承認者］の定義を［ブログのエントリのアセットタイプ］に割り当てる。

1. ［**保存**］ をクリックします。

1. デフォルトの管理者であるUser Test Testを使って、サイトメニューの &rarr; コンテンツ& データ&rarr; ブログを開きます。

1. **追加** ボタン（![Add](../../../images/icon-add.png)）をクリックします。

1. タイトルとコンテンツの欄に何かを入力し、 **公開申請** をクリックします。

1. メインのBlogsビューに戻り、エントリーが表示され、ステータスが **返答待ち** と表示されていることを確認します。

   ワークフローフレームワークでは、ステータスを［保留］に設定しています。 これ以降、ステータスの更新はアクションエクゼキュータのロジックを使って行われます。

1. [ワークフロー内のブログエントリーを承認する](../using-workflows/reviewing-assets.md#approving-or-rejecting-a-task) .

   ![この承認または拒否は、E5C9アクションエグゼキューターによって行われます。](./creating-an-action-executor/images/01.png)

唯一の承認者ワークフローをブログエントリに割り当てると、［E5C9 唯一の承認者］と同じように動作することがわかります。

<a name="understanding-the-e5c9-action-executor" />

## E5C9アクションエグゼキュータを理解する

Acme E5C9実装プロジェクトでは、唯一の承認者定義のワークフロースクリプトのステータス設定ロジックを、`E5C9ActionExecutor` <p という1つのJavaクラスに抽出します。</p>

<p spaces-before="0">このプロジェクトでは、アクションエクゼキュータに加えて、E5C9 唯一の承認者と呼ばれるワークフロー定義が含まれ、オートロードされます。このワークフロー定義は、デフォルトの唯一の承認者と同じロジックを持ちますが、ワークフロー定義に直接Groovyスクリプトを使用する代わりに、アクションエクゼキュータクラスのロジックを使用します。</p>

<pre><code class="{literalinclude} ./creating-an-action-executor/resources/liferay-e5c9.zip/e5c9-impl/src/main/java/com/acme/e5c9/internal/workflow/kaleo/runtime/scripting/internal/action/E5C9ActionExecutor.java">   :dedent: 4
   :language: java
   :lines: 49-50
`</pre>

### ActionExecutorの実装

アクション・エグゼキュータ・クラスは、 `com.acme.e5c9.internal.workflow.kaleo.runtime.scripting.internal.action.ActionExecutor` インターフェースを実装し、単一の `execute` メソッドをオーバーライドします。 コンポーネントのプロパティで、アクションのスクリプト言語を `java` と設定します。

```{literalinclude} ./creating-an-action-executor/resources/liferay-e5c9.zip/e5c9-impl/src/main/java/com/acme/e5c9/internal/workflow/kaleo/runtime/scripting/internal/action/E5C9ActionExecutor.java
   :dedent: 0
   :language: java
   :lines: 31-35
```

`execute` メソッドは何も返しません。 その代わり、メソッド内で任意にロジックが実行され、XMLの定義に従ってワークフローの処理が継続されます。 アクションの実行中に、ワークフローのステータスが更新されることがよくあります。

`execute` メソッドは、 `KaleoAction` と `ExecutionContext`の2つのパラメータを受け取ります。 ワークフローエンジンは、ワークフロープロセス内のアクションエクゼキュータを呼び出す役割を担っているため、お客様のコードではこれらのオブジェクトのインスタンス化や構築を行う必要はありません。 しかし、そこから有益な情報を得ることができます。 例えば、E5C9アクション・エクゼキュータは、 `workflowContext` （ `Map`タイプ）を `ExecutionContext`から取得します。

```{literalinclude} ./creating-an-action-executor/resources/liferay-e5c9.zip/e5c9-impl/src/main/java/com/acme/e5c9/internal/workflow/kaleo/runtime/scripting/internal/action/E5C9ActionExecutor.java
   :dedent: 3
   :language: java
   :lines: 43-44
```

`workflowContext` は、直近に実行されたトランジションを取得するために使用され、条件付きロジックがワークフロー内のアセットに設定するステータスを決定できるようになります。

```{literalinclude} ./creating-an-action-executor/resources/liferay-e5c9.zip/e5c9-impl/src/main/java/com/acme/e5c9/internal/workflow/kaleo/runtime/scripting/internal/action/E5C9ActionExecutor.java
   :dedent: 3
   :language: java
   :lines: 46-59
```

### ワークフロー定義内でのActionExecutorの呼び出し

Acme E5C9実装プロジェクトで自動ロードされるE5C9 唯一の承認者のワークフロー定義は、Liferayに同梱されている唯一の承認者の定義とほぼ同じである。 E5C9の唯一の承認者定義では、すべてのロジックがアクション・エクゼキュータ・クラスにアウトソースされているため、大幅に簡素化されていますが、その違いはステート・ノードとタスク・ノードのスクリプト要素にあります。 定義のアクション（rejectとapprove）には、どちらも同じスクリプトタグが付いています。

```{literalinclude} ./creating-an-action-executor/resources/liferay-e5c9.zip/e5c9-impl/src/main/resources/com/acme/e5c9/internal/workflow/kaleo/runtime/scripting/internal/action/dependencies/e5c9-workflow-definition.xml
   :dedent: 4
   :language: xml
   :lines: 62-65
```

scriptタグは引き続き必要ですが、ワークフローフレームワークにロジックを保持するアクションエクゼキュータを指し示します。

ワークフロー定義の中で呼び出すアクションエクゼキュータは1つだけとは限りません。 例えば、 `E5C9ActionExecutor` は、ワークフローのステータスを設定する前に、遷移を判断するロジックを持っています。 しかし、ロジックがより複雑な場合は、ワークフローの各アクションを個別の `ActionExecutor` の実装でバックアップし、これらの実装を他のワークフロー定義で再利用することができます。
