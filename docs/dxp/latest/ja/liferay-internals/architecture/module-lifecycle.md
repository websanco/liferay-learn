# モジュールのライフサイクル

OSGiでは、すべてのコンポーネント、Javaクラス、リソース、および記述子がモジュール（OSGiバンドル）を介してデプロイされます。 `MANIFEST.MF`ファイルは、モジュールがエクスポートおよびインポートするパッケージなど、モジュールの物理的特性を記述します。 モジュールのコンポーネント記述ファイルは、その機能特性（つまり、そのコンポーネントが提供および消費するサービス）を指定します。 モジュールとそのコンポーネントにも、独自のライフサイクルと管理APIがあります。 Declarative Serviceとシェルツールを使用すると、モジュールとコンポーネントのデプロイをきめ細かく制御できます。

モジュールのコンテンツはそのアクティブ化に依存するため、アクティブ化の手順を検討してください。

1. *インストール*：モジュールJARを`[Liferay Home]/deploy`フォルダにコピーすると、モジュールがOSGiフレームワークにインストールされ、モジュールに `INSTALLED`とマークされます。

1. *解決*：すべてのモジュールの要件が満たされると（たとえば、インポートするすべてのパッケージが使用可能になると）、フレームワークはモジュールのエクスポートされたパッケージを公開し、モジュールに`RESOLVED`とマークを付けます。

1. *アクティブ化*：モジュールはデフォルトで*積極的に*アクティブ化されます。 つまり、フレームワークで開始され、解決時に`ACTIVE`とマークされます。 アクティブなモジュールのコンポーネントが有効になります。 以下のマニフェストヘッダーに示すように、モジュールが`lazy`アクティベーションポリシーを指定した場合、別のモジュールがそのクラスの1つを要求した後にのみアクティブ化されます。

   ```properties
   Bundle-ActivationPolicy: lazy
   ```

次の図は、モジュールのライフサイクルを示しています。

![この状態図は、モジュールのライフサイクルを示しています。](./module-lifecycle/images/01.png)

You can manage the life cycle with the [Apache Felix Gogo Shell](../fundamentals/using-the-gogo-shell.md). モジュールをインストール/アンインストールして、それらを開始/停止することができます。 モジュールを更新し、依存モジュールに更新を使用するように通知できます。 [Liferay Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)、[Blade CLI](../../developing-applications/tooling/blade-cli/installing-and-updating-blade-cli.md)、[Liferay Dev Studio](https://liferay.dev/-/ide)などのLiferayのツールは、OSGi Admin APIを使用する類似のシェルコマンドを提供します。

モジュールをアクティブ化すると、そのコンポーネントが有効になります。 ただし、使用できるのは*アクティブ化*されたコンポーネントのみです。 コンポーネントをアクティブ化するには、参照されているすべてのサービスが満たされている必要があります。 つまり、コンポーネントが参照するすべてのサービスが登録されている必要があります。 参照に一致する最高ランクのサービスがコンポーネントにバインドされます。 コンテナは、コンポーネントが参照するすべてのサービスを見つけてバインドすると、コンポーネントを登録します。 これで、アクティブ化の準備が整いました。

コンポーネントは、*遅延*（デフォルト）または*即時*アクティベーションポリシーを使用できます。 即時アクティブ化を指定するには、開発者は属性`immediate=true`を[`@Component`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Component.html)アノテーションに追加します。

```java
@Component(
    immediate = true,
    ...
)
```

即時アクティベーションが指定されていない限り、コンポーネントのアクティベーションは遅延されます。 つまり、コンポーネントが要求されると、コンポーネントのオブジェクトが作成され、そのクラスがロードされます。 このように、アクティベーションを遅らせることで、起動時間を短縮し、リソースを節約することができます。

Gogo シェルの[サービスコンポーネントランタイムコマンド](http://felix.apache.org/documentation/subprojects/apache-felix-service-component-runtime.html#shell-command)を使用すると、コンポーネントを管理できます。

* `scr:list [bundleID]`：モジュール（バンドル）のコンポーネントを一覧表示します。

* `scr:info [componentID|fullClassName]`：コンポーネントのステータスや提供するサービスなど、コンポーネントについて説明します。

* `scr:enable [componentID|fullClassName]`：コンポーネントを有効にします。

* `scr:disable [componentID|fullClassName]`：コンポーネントを無効にします。 サーバーが再起動されるまで、サーバー（またはクラスター内の現在のサーバーノード）では無効になっています。

サービス参照は_静的_であり、デフォルトでは_消極的_です。 つまり、注入されたサービスは、サービスが無効になるまで参照コンポーネントにバインドされたままになります。 または、参照用に*GREEDY*サービスポリシーを指定することもできます。 上位のマッチングサービスが登録されるたびに、フレームワークは下位のサービスを（サービスポリシーがgreedyの）コンポーネントからバインド解除し、その場所に新しいサービスを自動的にバインドします。 greedyポリシーを使用する[`@Reference`](https://docs.osgi.org/javadoc/osgi.cmpn/7.0.0/org/osgi/service/component/annotations/Reference.html)アノテーションは次のとおりです。

```java
@Reference(policyOption = ReferencePolicyOption.GREEDY)
```

Declarative Serviceアノテーションを使用して、コンポーネントのアクティブ化とサービスポリシーを指定します。 Gogo シェルコマンドを使用して、モジュールとコンポーネントを制御します。

## 追加情報

* [モジュールプロジェクト](../fundamentals/module-projects.md)
* [Gogo シェルコマンド](../fundamentals/using-the-gogo-shell/gogo-shell-commands.md)
* [Liferay Workspace](../../developing-applications/tooling/liferay-workspace/what-is-liferay-workspace.md)
