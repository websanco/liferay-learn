# スクリプトエンジンの使用

```{toctree}
:maxdepth: 3

using-the-script-engine/invoking-liferay-services-from-scripts.md
using-the-script-engine/running-scripts-from-the-script-console.md
using-the-script-engine/script-examples.md
```

DXPは、[Groovy](http://groovy-lang.org/)スクリプトを実行してLiferay DXPインスタンスを維持するための堅牢なスクリプトエンジンを提供します。 スクリプトを実行して、データのクリーンアップ、ユーザーメンテナンス操作、Liferay APIの一括呼び出し、またはシステムレベルの操作を含むメンテナンスタスクを実行できます。

![このスクリプトコンソールはGroovyスクリプトを実行し、現在のactionRequestなどのコンテキスト変数を提供します。 このスクリプトコンソールは、Liferayサービスを呼び出すために設計されました。](./using-the-script-engine/images/01.png)

## スクリプトエンジンへの移動

スクリプトコンソールは、コントロールパネルから利用できます。 スクリプトエンジンを使用するには、次の手順に従います。

1.  管理者ユーザーとしてログインします。

2.  [製品メニュー](../getting-started/navigating-dxp.md) を開き、コントロールパネルに移動して、 *Configuration* → *Server Administration* → *Script*ます。

    ![このスクリプトコンソールは、[System Administration]メニュー内のタブです。](./using-the-script-engine/images/02.png)

## 次のステップ

  - [スクリプトからLiferayサービスを呼び出す](./using-the-script-engine/invoking-liferay-services-from-scripts.md)
  - [スクリプトコンソールからのスクリプトの実行](./using-the-script-engine/running-scripts-from-the-script-console.md)
  - [ワークフローでのスクリプトエンジンの使用](../process-automation/workflow/developer-guide/using-the-script-engine-in-workflow.md)
  - [スクリプトの例](./using-the-script-engine/script-examples.md)
