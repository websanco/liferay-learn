# ディスパッチフレームワークを理解する

Liferay Dispatchは、Liferayのスケジューラーエンジンの上に構築された柔軟なフレームワークです。 これを使用して、Liferayインスタンス全体でカスタムロジックを含むタスクの追加、実行、およびスケジューリングができます。

ディスパッチフレームワークは、6つの重要な部分で構成されています。

* [ベースの`DispatchTaskExecutor`](#base-dispatchtaskexecutor)
* [`DispatchTrigger`](#dispatchtrigger)
* [`DispatchMessageListener`](#dispatchmessagelistener)
* [`DispatchTaskExecutorRegistry`](#dispatchtaskexecutorregistry)
* [`DispatchLog`](#dispatchlog)
* [`DispatchConfigurator`](#dispatchconfigurator)

## ベースの`DispatchTaskExecutor`

ベースの[`DispatchTaskExecutor`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dispatch/dispatch-api/src/main/java/com/liferay/dispatch/executor/DispatchTaskExecutor.java)インターフェイスを実装すると、Liferayインスタンスでディスパッチタスクのテンプレートが作成されます。 `DispatchTaskExecutor`の各実装は、OSGiコンポーネントとして登録され、ディスパッチタスクによって実行されるロジックが含まれています。 すべてのディスパッチタスクは、`DispatchTaskExecutor`インターフェイスを実装し、`dispatch.task.executor.name`および`dispatch.task.executor.type` OSGiコンポーネントプロパティを持つJavaクラスのインスタンスです。 詳しくは、[Creating a New Dispatch Task Executor](./creating-a-new-dispatch-task-executor.md)をご覧ください。

## `DispatchTrigger`

[`DispatchTrigger`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dispatch/dispatch-api/src/main/java/com/liferay/dispatch/model/DispatchTrigger.java)インターフェイスは、`DispatchTriggerModel`と`PersistedModel`を拡張します。 このエンティティは、Liferay（Quartz）トリガーのドラフトとして機能します。 これは、`DispatchTaskExecutor`とLiferayスケジューラーエンジン間の接続です。

## `DispatchMessageListener`

[`DispatchMessageListener`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dispatch/dispatch-service/src/main/java/com/liferay/dispatch/internal/messaging/DispatchMessageListener.java)クラスは、すべてのディスパッチタスクエグゼキュータの実行を開始します。 これは、`DispatchTaskExecutor`サービスクラスのスケジュールされたインスタンスごとに新しいLiferayトリガーが作成されることを意味します。 このトリガーは同じ宛先（`liferay/dispatch/executor`）で作成され、Liferayトリガーを`DispatchTaskExecutor`に接続するペイロード（`dispatchTriggerId`）を持っています。 次に、Liferayスケジューラーエンジンは、適切なタイミングでメッセージ（`dispatchTriggerId`）を使用して`DispatchMessageListener`をトリガーします。 `dispatchTriggerId`を使用すると、`DispatchMessageListener`は、`DispatchTaskExecutorRegistry`を使用して`DispatchTaskExecutor`の適切なインスタンスを見つけて実行します。

## `DispatchTaskExecutorRegistry`

[`DispatchTaskExecutorRegistry`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dispatch/dispatch-api/src/main/java/com/liferay/dispatch/executor/DispatchTaskExecutorRegistry.java)インターフェイスを実装すると、ポータル内の`DispatchTaskExecutor`のすべての実装への参照が保持され、各`dispatch.task.executor.type` OSGiのプロパティ値が一意であることが検証されます。

## `DispatchLog`

[`DispatchLog`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dispatch/dispatch-api/src/main/java/com/liferay/dispatch/model/DispatchLog.java)インターフェイスは、`DispatchLogModel`と`PersistedModel`を拡張します。 このエンティティは、ディスパッチタスクの実行ログを保持します。

## `DispatchConfigurator`

[`DispatchConfigurator`](https://github.com/liferay/liferay-portal/blob/master/modules/apps/dispatch/dispatch-service/src/main/java/com/liferay/dispatch/internal/messaging/DispatchConfigurator.java)クラスは、`DispatchMessageListener`の宛先、`executorService`キューサイズとスレッドプール、`RejectedExecutionHandler`などのディスパッチフレームワークのプロパティを定義します。

## 追加情報

* [Using Dispatch](./using-dispatch.md)
* [Dispatch UI Reference](./dispatch-ui-reference.md)
* [Creating a New Dispatch Executor](./creating-a-new-dispatch-task-executor.md)
