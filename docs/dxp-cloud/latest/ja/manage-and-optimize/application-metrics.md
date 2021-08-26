# アプリケーションメトリクス

DXP Cloudのビルトインモニタリングにより、管理者はすべてのサービスに関する次の情報を追跡できます。

  - CPU使用率
  - メモリ使用量
  - データの転送

DXP Cloud管理コンソールにログインしながら、アプリケーションメトリクスを表示するには：

1.  *[Monitoring]* タブをクリックします。
2.  過去30日間のデータが表示されます。 期間を変更するには、時間セレクターのドロップダウンメニューをクリックします。

![図1：DXP Cloudを使用してサービスを監視できます。](./application-metrics/images/01.png)

## 本番環境での高度なアプリケーションメトリクス

Dynatraceの高度なモニタリング機能を使用する前に、次の要件を満たす必要があります。

  - Dynatraceアカウントを作成している。
  - Dynatraceシークレットの `token` および `tenant` の値が生成されている。
  - Dynatrace環境変数が、DXPサービスの本番環境の `LCP.json` ファイルに追加されている。 例：

<!-- end list -->

``` json
{
    "environments": {
      "prd": {
        "env": {
          "LCP_PROJECT_MONITOR_DYNATRACE_TENANT": "tot02934",
          "LCP_PROJECT_MONITOR_DYNATRACE_TOKEN": "dDKSowkdID8dKDkCkepW"
        }
      }
    }
}
```

| 名前                                     | 説明                                                                               |
| -------------------------------------- | -------------------------------------------------------------------------------- |
| `LCP_PROJECT_MONITOR_DYNATRACE_TENANT` | 8文字の文字列。 これはDynatrace SaaSアカウントのURL（プレフィックス）の一部です。                               |
| `LCP_PROJECT_MONITOR_DYNATRACE_TOKEN`  | *Dynatraceアカウントで見つかる22文字の文字列* → *インストールを開始* → *PaaS監視をセットアップ* → *インストーラのダウンロード*. |

これらの必要な手順が完了後：

1.  <https://console.liferay.cloud/projects> に移動します。
2.  本番環境をクリックします。
3.  左側のメニューで *[Monitoring]* をクリックします。
4.  *[Advanced]* タブをクリックします。
5.  *[Go to Dynatrace Dashboard]* をクリックして、 [[Dynatrace]](https://www.dynatrace.com/) ダッシュボードにアクセスします。

![Dynatraceダッシュボード](./application-metrics/images/02.png)

Dynatrace資格情報でサインインして、ログの追跡を確認し、カスタムダッシュボードを作成します。

## 追加情報

  - [Advanced Monitoring: APM Tools - Dynatrace](https://help.liferay.com/hc/en-us/articles/360017896452-Advanced-Monitoring-APM-Tools-Dynatrace)
