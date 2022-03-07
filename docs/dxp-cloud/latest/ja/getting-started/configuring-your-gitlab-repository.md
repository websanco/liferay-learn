# GitHubリポジトリの設定

DXP Cloudオンボーディングメールを受信すると、 `dxpcloud` 組織でホストされているGitHubリポジトリがプロビジョニングされます。 このリポジトリは、チームの個別のプライベートDXP Cloud開発リポジトリのテンプレートで、通常は10営業日後に削除されます。 ユーザーは以下のことを行う必要があります：

1. プロビジョニングされたリポジトリを独自のプライベートリポジトリに転送します。
1. プライベートリポジトリとDXP CloudのJenkins(CI)サービスをWebhookで連携させます。

プロビジョニングされたリポジトリはGitHubにありますが、Jenkinsサービスのバージョン3.2.0以降ではGitLabリポジトリに転送できます。

<a name="preparing-the-jenkins-service" />

## Jenkinsサービスの準備

すでにDXP Cloudのインスタンスで [version 4.x.x services](../reference/understanding-service-stack-versions.md) を使用している場合は、JenkinsサービスはすでにGitLabと互換性があります。 アップグレードの詳細については、 [DXP Cloudスタックのアップグレード](../reference/upgrading-your-dxp-cloud-stack.md) を参照してください。

version 3.x.xのサービスを使用している場合は、 `LCP.json` で、 `ci` サービスを確認し、以下のJenkinsサービス以上のものを実行していることを確認してください：

```
liferaycloud/jenkins:2.222.1-3.2.0
```

そうでない場合は、次の手順に従ってアップグレードします：

1. Jenkinsサービスを`liferaycloud/jenkins：2.222.1-3.2.0`バージョンに更新します。

1. ルートフォルダーにある `Jenkinsfile` を削除します。

1. 次の環境変数を追加します： `LCP_CI_USE_DEFAULT_JENKINSFILE: true`。

1. Jenkinsサービスをデプロイします。

```{note}
    Jenkinsfile をカスタマイズしている場合は、以下のガイドに従って [デフォルトの Jenkinsfile を延長します](../platform-services/continuous-integration.md#extending-the-default-jenkinsfile) _。
```

<a name="creating-a-gitlab-repository" />

## GitLabリポジトリの作成

まず、新しいGitLabリポジトリを作成します：

1. [GitLab](https://gitlab.com) に移動します。

1. ［**New project**］ をクリックします。

    ![［新しいプロジェクト］をクリックして、新しいGitLabリポジトリの作成を開始します。](./configuring-your-gitlab-repository/images/01.png)

1. プロジェクトのスラッグ（URL内のリポジトリ名）を提供します。

    ![新しいリポジトリの詳細を入力します。](./configuring-your-gitlab-repository/images/02.png)

1. 表示レベルをプライベートに設定します（無料ユーザーは無制限のプライベートリポジトリを使用できます）。

1. ［**Initialize repository with a README**］ がチェックされていないことを確認します。

1. ［**Create project**］ をクリックします。

<a name="transferring-from-github-to-gitlab" />

## GitHubからGitLabへの転送

以下の手順に従って、プロビジョニング済みの GitHub リポジトリを自分の GitLab リポジトリに転送します：

1. プロビジョニング済みのGitHubリポジトリをローカルでクローンします：

    `git clone git@github.com:dxpcloud/example.git`

    ```{note}
       すでに他のプロバイダで作業するためにリポジトリをクローンしている場合は、このステップをスキップして、同じクローン内で作業することができます。
    ```

1. 新しいGitリモートを追加し、GitLabをポイントします：

    `git remote add gitlab git@gitlab.com:USERNAME/REPOSITORYNAME.git`

1. クローンされたリポジトリを新しいリモートリポジトリにプッシュします：

    `git push gitlab master`

GitHub リポジトリの作成、クローン、プッシュについてのヘルプが必要な場合は、 [GitLab's documentation](https://docs.gitlab.com/ee/gitlab-basics/start-using-git.html) を参照してください。

<a name="generating-access-tokens-for-gitlab" />

## GitLabのアクセストークンの生成

次に、JenkinsのビルドをトリガーするためにWebhookで使用するアクセストークンを作成します。

1. [パーソナル・アクセストークンのページ](https://gitlab.com/profile/personal_access_tokens) に移動します。

    ![後で再びアクセスできないGitLabの個人アクセストークンを作成します。](./configuring-your-gitlab-repository/images/03.png)

1. このアクセストークンの名前と有効期限を指定します。

1. 次のアクセス許可を持つようにアクセストークンを設定します：

    * api
    * read_repository
    * write_repository

1. [**Create personal access token**]をクリックします。

1. アクセストークンをコピーしてどこかに保存します（アクセストークンが再度表示されないためです）。

<a name="connecting-gitlab-to-your-jenkins-service" />

## GitLabをJenkinsサービスに接続する

最後に、新しいリポジトリを指すようにJenkinsサービスの環境変数を設定します：

1. DXP Cloudコンソールにログインし、 `infra` 環境でJenkinsサービスに移動します。

1. ［**環境変数**］ タブに移動します。

1. 以下の環境変数を設定します：

| 名前                            | Value          |
| ----------------------------- | -------------- |
| `LCP_CI_SCM_PROVIDER`         | gitlab         |
| `LCP_CI_SCM_REPOSITORY_OWNER` | [repo_owner]   |
| `LCP_CI_SCM_REPOSITORY_NAME`  | [repo_name]    |
| `LCP_CI_SCM_TOKEN`            | [access_token] |

これらの環境変数を更新した後、Jenkinsサービスは再起動します。 これで、新しいリポジトリでプッシュされたブランチとプルリクエストがビルドをトリガーします。

<a name="connecting-to-a-private-gitlab-server" />

## プライベートGitLabサーバーへの接続

プライベートGitLabサーバーを使用するには、Jenkinsサービスで追加の環境変数を設定する必要があります：

| 名前                       | Value              |
| ------------------------ | ------------------ |
| `LCP_CI_SCM_SERVER_HOST` | [private host URL] |

`LCP_CI_SCM_SERVER_HOST` 変数に、プライベートGitLabサーバーのベースURLを設定します（例えば、 `http://private.gitlab.server.com/`）。 これにより、ビルドを生成してリポジトリのブランチにリンクするときにCIがコードベースを取得するために使用するサーバーURLが設定されます。 デフォルトでは、CIはGitLabのベースURLとして `https://gitlab.com/` を使用しています。

<a name="additional-gitlab-webhook-configurations" />

## 追加のGitLab Webhook設定

Liferay DXP CloudのJenkinsサービスは、選択したgit SCMプロバイダーのためのWebhookを作成しますが、デフォルトのものしか作成しません。 GitLabのデフォルトのWebhookをGitHubやBitBucketのWebhookの機能と一致させるためには追加の設定が必要です。

1. GitLabリポジトリに移動します。

1. **Settings** に移動し ［**Webhooks**］ を選択します。

1. **Project Hooks** で、作成されたWebhookがリストされていることを確認します。

1. CI webhookの[**Edit**]ボタンをクリックします。

    ![リポジトリ用に自動的に作成されたWebhookを編集します。](./configuring-your-gitlab-repository/images/04.png)

1. ［**Tags push events**］ と ［**Comments**］ をアンチェックします。

1. ［**Enable SSL verification**］ にチェックします。

1. ［**変更を保存**］ クリックします。

<a name="verifying-builds" />

## ビルドの確認

プッシュされたブランチとマージリクエスト（GitLabのプルリクエストに相当）は、DXP Cloudコンソールの[**Builds**]タブから表示またはデプロイできるビルドをトリガーする必要があります。 Jenkins サービスとの統合を設定したら、次のステップとして、インテグレーションが成功したかどうかを確認するためにビルドを検証します。

<a name="verifying-builds-from-pushed-branches" />

### プッシュされたブランチからのビルドの確認

新しいGitプッシュがJenkinsビルドをトリガーすることを確認します：

1. リポジトリに変更を加え（ファイルの追加など）、ブランチにコミットします：

    ```bash
    git commit -m "Add file to test builds"
    ```

1. ブランチをGitLabにプッシュします：

    ```bash
    git push gitlab branch-name
    ```

1. DXP Cloud コンソールの **Builds** ページに移動します。

1. **Builds** ページで、プッシュされたブランチのビルドが表示されることを確認します。

<a name="verifying-builds-from-merge-requests" />

### マージリクエストからのビルドの確認

新しいマージリクエストがJenkinsビルドをトリガーすることを確認します：

1. 任意のブランチから `develop` ブランチへのマージリクエストを作成します。

1. マージリクエストに対して新しいビルドが作成されていることを確認します。

1. DXP Cloud コンソールの **Builds** ページに移動します。

1. ブランチのリンクをクリックして、適切なビルドでコミットします。

    ![Buildsページで、ブランチのリンクを確認し、ビルドをコミットします。](./configuring-your-gitlab-repository/images/05.png)

1. リンクが正しいGitLabページにリダイレクトすることを確認します。

<a name="additional-information" />

## 追加情報

* [BitBucketリポジトリの設定](./configuring-your-bitbucket-repository.md)
* [GitHubリポジトリの設定](./configuring-your-github-repository.md)
