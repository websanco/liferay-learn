# ワークフローの概要

Liferay DXPには、ユーザーがブログ、Webコンテンツ、Wikiなどのコンテンツを公開する前に確認および承認できるワークフローエンジンが付属しています。 ワークフローアプリケーションを使用すると、任意の数のビジネスプロセス/ワークフローを定義し、それらをデプロイして、ポータルインターフェイスを介して管理できます。 ワークフロープロセスは`XML`形式で記述されており、いくつかのサンプルのワークフロープロセスを参照できます。 最後に、ワークフロープロセスは、レビュー担当者および承認者としてのユーザー、グループ、およびロールと統合できます。 レビュープロセスを定義したら、公開前にレビュープロセスが必要なコンテンツのタイプを選択できます。

## ワークフローのアクティブ化

特定のアセットに対してワークフローがアクティブ化されると、*[Publish]* ボタンが*[Submit for Publication]* ボタンに置き換わり、即時公開ではなく、アセットのステータスが*[Pending]* に設定されます。 公開するにはワークフローを進める必要があります。

![[Publish]ボタンの代わりに、ワークフローが有効なリソースに対して[Submit for Publication]ボタンが表示されます。](./introduction-to-workflow/images/01.png)

既定のワークフロープロセスは、*Single Approver*と呼ばれます。このプロセスでは、公開前に提出物を確認および承認する人が1名必要です。

詳細については、 [Activating Workflow](./activating-workflow.md)および[sending assets through review](./reviewing-assets.md) をご覧ください。

## ワークフローの構築

ワークフローを使用すると、ユーザーは複数のレビュー担当者と承認者が設定されたより複雑なワークフローを構築できます。 効果的なワークフローを構築する方法を学習しましょう。

  - [Crafting Review Processes in XML](https://help.liferay.com/hc/articles/360029147791-Introduction-to-Crafting-XML-Workflow-Definitions)
  - [Visually Designing Review Processes](https://help.liferay.com/hc/articles/360028821892-Workflow-Designer)（サブスクライバー）
  - [Uploading Workflow Definitions](./managing-workflows.md#uploading-a-new-workflow-definitions)
  - [Managing Workflow Definitions](./managing-workflows.md)

独自のワークフローの構築を開始するには、[Building Workflows](./building-workflows.md)を参照してください。

### ワークフローメトリクスを使用したSLAの実装

> サブスクリプション

*メトリクス*関数を使用して、ワークフロープロセスのサービスレベルアグリーメント（SLA）のパフォーマンスを測定できます。 SLAは、ワークフロープロセスのイベントの期限を定義します。 期限は顧客との間で正式に合意したり、次のようなイベントを追跡して社内目標を達成するために非公式に作成することもできます。

  - 解決までの合計時間
  - 特定のワークフロータスクを完了する時間

SLAが設定されると、SLAタイマーをトリガーするワークフローの送信がワークフローメトリクスフレームワークによって自動的に報告され、*[On Time]* または *[Overdue]* のステータスが付けられます。

![SLAに基づいて生成されたワークフローレポートを参照してください。](./introduction-to-workflow/images/02.png)

SLAをワークフローに追加する方法については、[Using Workflow Metrics](./using-workflow-metrics.md)の記事を参照してください。

## 次のステップ

  - [Activating Workflow](./activating-workflow.md)
  - [Managing Workflows](./managing-workflows.md)
  - [Building Workflows](./building-workflows.md)
