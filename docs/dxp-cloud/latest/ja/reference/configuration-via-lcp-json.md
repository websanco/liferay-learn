# LCP.jsonを介した設定

DXP Cloud環境の各サービスには、サービスの設定に使用できる `LCP.json` ファイルがあります。 サービスID、メモリ、CPU数、環境変数、ボリュームなどのプロパティを設定できます。

次の表に、 `LCP.json`に追加できるプロパティの一覧と説明を示します。

| フィールド           | タイプ    | デフォルト値                        | 説明                                                                                                                           |
| --------------- | ------ | ----------------------------- | ---------------------------------------------------------------------------------------------------------------------------- |
| `id`            | String | ランダム                          | サービスID                                                                                                                       |
| `image`         | 文字列    | `""`                          | Docker Hubからのサービスイメージ                                                                                                        |
| `env`           | オブジェクト | 未定義                           | 環境変数                                                                                                                         |
| `ロードバランサー`      | オブジェクト | `{}`                          | 公開されたポートとドメインの宣言                                                                                                             |
| `ｃｕｐｕ`          | 数値     | `1`                           | CPUの数                                                                                                                        |
| `スケール`          | 数値     | `1`                           | インスタンスの開始番号                                                                                                                  |
| `メモリ`           | 数値     | `512`                         | メモリ量（MB）                                                                                                                     |
| `ボリューム`         | オブジェクト | 未定義                           | データを維持するパス                                                                                                                   |
| `準備調査`          | オブジェクト | `{"timeoutSeconds": 5}`       | サービス準備チェック                                                                                                                   |
| `livenessProbe` | オブジェクト | `{"timeoutSeconds": 5}`       | サービスの活性チェック                                                                                                                  |
| `dependencies`  | 配列     | `[]`                          | 依存関係のデプロイ順序                                                                                                                  |
| `種類`            | String | `デプロイ`                        | デプロイタイプ（例：DeploymentまたはStatefulSet）                                                                                          |
| `ストラテジー`        | String | `ローリングアップデート`                 | デプロイ戦略（RollingUpdateやRecreateなど）                                                                                             |
| `ポート`           | 配列     | `[]`                          | ポートとプロトコルの宣言                                                                                                                 |
| `環境`            | オブジェクト | `{}`                          | 環境固有の設定                                                                                                                      |
| `deploy`        | ブール値   | `true`                        | 指定された環境にサービスをデプロイするかどうか。 このプロパティは、 `environments`プロパティ内でのみ使用してください。 ルートレベルではありません。 サンプル `LCP.json` ファイルを参照してください。            |
| `オートスケール`       | オブジェクト | `{ "cpu": 80, "memory": 80 }` | オートスケーリングでのCPUおよびメモリの目標平均使用率。 これがオートスケーリングでどのように機能するかについての詳細は、 [オートスケーリング](../manage-and-optimize/auto-scaling.md)を参照してください。 |

## 使用法

すべてのプロパティを使用する `LCP.json` ファイルの例を次に示します。

``` json
{
  "id": "myservice",
  "image": "liferaycloud/example",
  "env": {
    "DB_USER": "root",
    "DB_PASSWORD": "pass123"
  },
  "loadBalancer": {
    "cdn": true,
    "targetPort": 3000,
    "customDomains": ["example.com"],
    "ssl": {
      "key": "...",
      "crt": "..."
    }
  },
  "cpu": 2,
  "scale": 2,
  "memory": 2048,
  "volumes": {
    "storage": "/opt/storage"
  },
  "livenessProbe": {
    "timeoutSeconds": 5,
    "httpGet": {
      "path": "/status",
      "port": 3000
    },
    "initialDelaySeconds": 40,
    "periodSeconds": 5,
    "successThreshold": 5
  },
  "readinessProbe": {
    "timeoutSeconds": 5,
    "exec": {
      "command": ["cat", "/tmp/healthy"]
    },
    "initialDelaySeconds": 40,
    "periodSeconds": 5
  },
  "dependencies": ["service1", "service2"],
  "kind": "StatefulSet",
  "strategy": "RollingUpdate",
  "ports": [
    {
      "port": 3400,
      "targetPort": 7000,
      "protocol": "TCP"
    },
    {
      "port": 9000,
      "targetPort": 8000,
      "protocol": "TCP",
      "external": true
    }
  ],
  "environments": {
    "prd": {
      "memory": 4096,
      "cpu": 6
    },
    "dev": {
      "deploy": false
    }
  },
  "autoscale": {
    "cpu": 90,
    "memory": 90
  }
}
```
