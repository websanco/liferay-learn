# スクリプトコンソールからのスクリプトの実行

スクリプトコンソールは、Groovyスクリプトを実行し、その出力を印刷するための単一のビューを提供します。 ウィジェットとユーザーの操作を容易にする定義済みの変数があります。 次のトピックでは、スクリプトコンソールの使用を開始します。

  - [スクリプトコンソールでサンプルスクリプトを実行する](#running-a-sample-script-in-the-script-console)
  - [スクリプトコンソールで使用可能な定義済み変数](#predefined-variables)
  - [スクリプトコンソールでスクリプトを実行するためのヒント](#tips)

``` important::
   スクリプトコンソールは、システムの操作とメンテナンス用です。エンドユーザー用ではありません。 スクリプトコンソールへのアクセスをポータル管理者に制限してください。
```

まず、スクリプトコンソールのサンプルスクリプトを実行します。

## スクリプトコンソールでサンプルスクリプトを実行する

スクリプトコンソールでサンプルスクリプトを実行する方法は次のとおりです。

1.  管理者としてログインします。

2.  製品メニューで、*コントロールパネル*に移動し、*[Configuration]* → *[Server Administration]* を選択します。

3.  *[Script]* をクリックします。 これがスクリプトコンソールです。 デフォルトのサンプルスクリプトは、ユーザー数をコンソール出力に出力します。

    ``` groovy
    // ### Groovy Sample ###

    number = com.liferay.portal.kernel.service.UserLocalServiceUtil.getUsersCount();

    out.println(number);
    ```

4.  *[Execute]* をクリックし、スクリプトコンソールの*出力*でユーザー数を確認します。

    ![スクリプトコンソールのサンプルのGroovyスクリプトは、ユーザー数をスクリプトコンソールの出力に出力します。](./running-scripts-from-the-script-console/images/01.png)

Groovyのサンプルは、Liferayサービスユーティリティ[`UserLocalServiceUtil`](https://docs.liferay.com/dxp/portal/7.2-latest/javadocs/portal-kernel/com/liferay/portal/kernel/service/UserLocalServiceUtil.html)を呼び出して、ユーザー数を取得します。 次に、`out`（組み込みの`PrintWriter`）を使用して、スクリプトコンソールに数を書き込みます。

``` note::
   *out.println*ではなく*System.out.println*を使用すると、出力はスクリプトコンソールではなくLiferayのログファイルに出力されます。
```

## 定義済みのスクリプトコンソール変数

スクリプトコンソールのスクリプトで使用できる定義済みの変数は次のとおりです。

| 変数               | クラス                                   |
|:---------------- |:------------------------------------- |
| `out`            | `java.io.PrintWriter`                 |
| `actionRequest`  | `javax.portlet.ActionRequest`         |
| `actionResponse` | `javax.portlet.ActionReponse`         |
| `portletConfig`  | `javax.portlet.PortletConfig`         |
| `portletContext` | `javax.portlet.PortletContext`        |
| `preferences`    | `javax.portlet.PortletPreferences`    |
| `userInfo`       | `java.util.Map<String, String>` |

### 変数の使用

次のスクリプトは、`actionRequest`変数を使用してポータルインスタンスの`Company`を取得する方法を示しています。

``` groovy
import com.liferay.portal.kernel.util.*

company = PortalUtil.getCompany(actionRequest)
out.println("Current Company:${company.getName()}\n")

out.println("User Info:")
userInfo.each {
        k,v -> out.println("${k}:${v}")
}
```

![以下は、事前定義されているout、actionRequest、およびuserInfo変数を使用して、会社とユーザーに関する情報を出力するGroovyスクリプトを呼び出す例です。](./running-scripts-from-the-script-console/images/02.png)

## ヒント

スクリプトコンソールを使用するときは、次の点に注意してください。

  - 元に戻すことはできません。
  - プレビューはありません。
  - アクセス許可のチェックは、ローカルサービスには適用されません。
  - スクリプトは同期的に実行されます。 時間がかかる可能性のあるスクリプトの実行は避けてください。

スクリプトコンソールを慎重に使用し、スクリプトを本番環境で実行する前に非運用システムでテストしてください。

スクリプトエンジンは、Kaleoワークフローなど、スクリプトコンソールの外部で使用できます。 ワークフローでスクリプトエンジンを使用する方法は次回学びます。

## 追加情報

  - [Invoking Liferay services](./invoking-liferay-services-from-scripts.md)
  - [Using the Script Engine in Workflow](../../process-automation/workflow/developer-guide/using-the-script-engine-in-workflow.md)
  - [スクリプトの例](./script-examples.md)
