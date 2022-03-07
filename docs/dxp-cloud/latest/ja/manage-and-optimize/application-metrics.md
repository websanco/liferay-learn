# アプリケーションメトリクス

Liferay DXP Cloudに組み込まれたモニタリング機能により、各環境サービスが使用するリソースを追跡することができます。 これらのアプリケーションメトリクスには、メモリとCPUの使用量、およびネットワークデータ転送量が含まれます。 メトリクスは、デフォルトのDXP Cloudのスタックサービス（Webサーバ、Liferay、検索、データベース、バックアップ）で利用できます。

<a name="service-popover" />

## サービスポップオーバー

サービスの現在のリソース使用状況は、DXP Cloud環境の **概要** や **サービス** のページや、個々のサービスのページなど、いくつかのページから素早く確認することができます。 サービスのアイコンにカーソルを合わせると、そのサービスのリソース使用状況のポップオーバーが表示されます。

![サービスのアイコンにカーソルを合わせます。](./application-metrics/images/01.png)

<a name="extended-application-metrics" />

## 拡張アプリケーションメトリクス

ユーザーは、 ［**モニタリング**］ ページから拡張サービスメトリクスを表示できます：

1. 環境メニューの ［**モニタリング**］ をクリックします。
1. ドロップダウンメニューを使って、モニターしたいサービスと時間帯を選択します。

![DXP Cloudを使用してサービスを監視できます。](./application-metrics/images/02.png)

ユーザーは、 ［**Services**］ ページから拡張サービスメトリクスを表示できます：

1. 環境メニューの ［**サービス**］ をクリックします。

1. モニターしたい ［**Service**］ をクリックします。

1. ［**メトリクス**］ タブをクリックします。

![サービスのページからメトリクスを表示します。](./application-metrics/images/03.png)

<a name="determining-resources-allocated-to-services" />

## サービスに割り当てられるリソースの決定

サービスの `LCP.json` ファイルの設定により、そのサービスに割り当てられたメモリとCPUの合計が決定され、アプリケーションのメトリクスには、これらのリソースの使用状況が時系列で表示されます。

以下は `liferay` サービスの `LCP.json` ファイルにおけるCPUとMemoryの割り当ての例です：

```
"id": "liferay",

"memory": 8192,
"cpu": 8
```

ユーザーはDXP Cloudのコンソールから割り当てられたリソースを確認できます。

![DXP Cloudのコンソールから、お客様の環境サービスに割り当てられたリソースを確認できます。](./application-metrics/images/04.png)

<a name="advanced-application-metrics-production-only" />

## 高度なアプリケーションメトリクス（本番環境のみ）

Liferay DXP Cloudでは、 [Dynatrace](https://www.dynatrace.com/) の高度なパフォーマンスモニタリングを本番環境に統合することができます。

詳しくは、 [Dynatrace limitations](../reference/platform-limitations.md#dynatrace) を参照してください。

<a name="integrating-dynatrace-with-production-environments" />

### Dynatraceと本番環境の統合

以下の手順でDynatraceを統合します：

1. Dynatraceのアカウントを作成します。

1. `トークン` および `テナント` 値を生成します。

1. Liferayサービスに、Dynatrace`token`値を[Secret](../infrastructure-and-operations/security/managing-secure-environment-variables-with-secrets.md)として追加します。

1. Liferayサービスの本番環境の`LCP.json`ファイルにDynatrace`テナント` Dynatrace環境変数を追加します。 たとえば、

```json
{
    "environments": {
      "prd": {
        "env": {
          "LCP_PROJECT_MONITOR_DYNATRACE_TENANT": "tot02934"
        }
      }
    }
}
```

| 名前 | 説明 |
| -- | -- |
|    |    |


`LCP_PROJECT_MONITOR_DYNATRACE_TENANT` | Dynatrace SaaSアカウントのURL（敬称）の一部である文字列です。 | `LCP_PROJECT_MONITOR_DYNATRACE_TOKEN` | Dynatraceアカウントにある文字列です。 トークンを取得するには、 ［**Manage**］ &rarr; ［**Deploy Dynatrace**］ &rarr; ［**Set up PaaS Integration**］ にナビゲートします。次に環境IDを入力し、 ［**Generate new token**］ をクリックします。 |

これらの値の詳細については、 [official Dynatrace documentation](https://www.dynatrace.com/support/help/dynatrace-api/basics/dynatrace-api-authentication/) を参照してください。

<a name="accessing-dynatrace" />

### Dynatraceへのアクセス

DXP CloudのコンソールからDynatraceの高度なパフォーマンスモニタリングにアクセスできるようになりました。

1. 本番環境に移動します。

1. 環境メニューの ［**モニタリング**］ をクリックします。

1. ［**Advanced**］ タブをクリックします。

1. [**Go to Dynatrace Dashboard**] ボタンをクリックして、 [Dynatrace] ダッシュボードにアクセスします。

    ![DXP CloudコンソールからDynatraceダッシュボードにアクセスします。](./application-metrics/images/05.png)

Dynatraceの認証情報でログインすると、ログの軌跡を確認したり、カスタムダッシュボードを作成したりできます。

<a name="additional-information" />

## 追加情報

* [Liferay DXPサービスの概要](../using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.md)
* [リアルタイムアラート](./real-time-alerts.md)
* [割り当て](./quotas.md)
