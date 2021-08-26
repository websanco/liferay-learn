# 継続的インテグレーション

DXP Cloudは [Jenkins](https://jenkins.io/) を使用して、継続的なインテグレーションインフラストラクチャサービスを強化しています。 プルリクエストを送信するか、事前設定されたGitHubブランチの1つにコミットをプッシュすると、自動で設定可能なビルドがトリガーされます。

デフォルトでは、この自動ビルドはコードをコンパイルし、テストを実行するように設定できます。 DXP Cloudはサービスをビルドし、そのステータスを環境の[ビルド]ページに表示します。 テストが失敗した場合は、Jenkinsダッシュボードと `https://ci-companyname-infra.lfr.cloud`ログを確認できます。

> **注：** 継続的インテグレーションは、CLIではなく、GitHub、GitLab、またはBitbucketからデプロイする場合にのみ機能します。

## デフォルトのJenkinsfileの使用

以前は、カスタマイズするために `Jenkinsfile` 全体を顧客に提供していました。 このアプローチの問題は、バグ修正、セキュリティ修正、および改善を1行ずつ手動で適用する必要があることです。

バージョン `liferaycloud/jenkins：2.222.1-3.2.0`以降、デフォルトのJenkinsfileの概念を導入しています。 以前にJenkinsfileに格納されていたすべてのロジックをカプセル化し、それをJenkinsプラグインに移動しました。

つまり、ユーザーのアクションを必要とせずに、すべてのバグ修正、セキュリティ修正、および改善を適用できます。

それとは別に、パイプラインのすべてのステップをカスタマイズするための強力な拡張ポイントのセットを提供しています。

### デフォルトのJenkinsfileを有効にする

1.  `liferaycloud/jenkins:2.222.1-3.2.0`にアップデート
2.  ルートフォルダにある `Jenkinsfile` を削除します
3.  次の環境変数 `LCP_CI_USE_DEFAULT_JENKINSFILE: true`を追加します
4.  Jenkinsサービスをデプロイする

## デフォルトのJenkinsfileの拡張

デフォルトのJenkinsファイルを拡張するには、次のファイルを `lcp/ci` フォルダーに追加します。

  - `Jenkinsfile-before-all`
  - `Jenkinsfile-before-cloud-build`
  - `Jenkinsfile-before-cloud-deploy`
  - `Jenkinsfile-after-all`
  - `Jenkinsfile-post-always`

CIビルドプロセスのステップの基本的な概要は次のとおりです。

1.  存在する場合は、`lcp/ci/Jenkinsfile-before-all`をロードします。
2.  Liferayワークスペースをビルドします。
3.  存在する場合は、`lcp/ci/Jenkinsfile-before-cloud-build`をロードします。
4.  コンソールに表示されるDXP Cloudビルドを作成します。
5.  存在する場合は、 `lcp/ci/Jenkinsfile-before-cloud-deploy`ロードします。
6.  必要に応じて、現在のブランチがデプロイブランチとして指定されているかどうかに応じて、ビルドをクラウド内の環境にデプロイします。 これは、 `LCP_CI_DEPLOY_BRANCH` 環境変数を介して設定されます。 `LCP_CI_DEPLOY_TARGET` 環境変数は、デプロイ先の環境を指定します。
7.  存在する場合は、 `lcp/ci/Jenkinsfile-after-all`ロードします。 これは、すべてのステップが完了すると実行されます。
8.  存在する場合は、 `lcp/ci/Jenkinsfile-post-always`ロードします。 これは、ビルドが失敗したときと成功したときの両方で実行されます。

デフォルトのパイプラインでこれらがどのように使用されるかを確認するには、Jenkinsサービスの起動ログを監視するだけです。 完全なデフォルトのJenkinsfileが起動ログに出力されます。

## 異なる拡張ポイント間でのコードの再利用

これらの拡張ポイント間でコードを共有する方法が必要になるでしょう。 基本的な方法の1つは、groovyスクリプトをロードすることです。 たとえば、次の内容で `lcp/ci/util.groovy` という名前のgroovyファイルを作成できます。

    def sendSlackMessage(message) {
        println(message)
    }
    
    return this

次に、以下を `lcp/ci/Jenkinsfile-before-cloud-build`挿入できます。

    def util = load("ci/util.groovy")
    
    util.sendSlackMessage("About to create DXP Cloud build...")

## 環境変数リファレンス

次の環境変数は、デフォルトのJenkinsファイルでのみ使用されます。 これらが何をするのかは、 [パイプラインオプションに関するJenkinsのドキュメント](https://jenkins.io/doc/book/pipeline/syntax/#options)を参照してください。

| 名前                                    | デフォルト値  | 説明                                                 |
| ------------------------------------- | ------- | -------------------------------------------------- |
| `LCP_CI_USE_DEFAULT_JENKINSFILE`      | `false` | デフォルトのJenkinsfileを有効または無効にするオプション                  |
| `LCP_CI_BUILD_TIMEOUT_MINUTES`        | `30`    | パイプライン実行のタイムアウト期間を設定します。 その後、Jenkinsがパイプラインを中止します。 |
| `LCP_CI_PRESERVE_STASHES_BUILD_COUNT` | `20`    | ステージの再起動で使用するために、完了したビルドのスタッシュを保持します               |
| `LCP_CI_BUILD_NUM_TO_KEEP`            | `10`    | 保存されるビルドの数                                         |
| `LCP_CI_BUILD_DAYS_TO_KEEP`           | `14`    | ビルドが保存される日数                                        |
| `LCP_CI_ARTIFACT_NUM_TO_KEEP`         | `1`     | 保存されるアーティファクトの数                                    |
| `LCP_CI_ARTIFACT_DAYS_TO_KEEP`        | `-1`    | 保存されるアーティファクトの日数                                   |

## 追加情報

  - [DXP Cloudサービスへのログイン](../getting-started/logging-into-your-dxp-cloud-services.md)
  - [GitLabリポジトリの設定](../getting-started/configuring-your-gitlab-repository.md)
  - [Bitbucketリポジトリの設定](../getting-started/configuring-your-bitbucket-repository.md)
