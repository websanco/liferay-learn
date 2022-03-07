# GitHubリポジトリの設定

DXP Cloudオンボーディングメールを受信すると、 `dxpcloud` 組織でホストされているGitHubリポジトリがプロビジョニングされます。 このリポジトリは、チームの個別のプライベートDXP Cloud開発リポジトリのテンプレートで、通常は10営業日後に削除されます。 ユーザーは以下のことを行う必要があります：

1. プロビジョニングされたリポジトリを独自のプライベートGitHubリポジトリに転送します。

1. プライベートリポジトリとDXP CloudのJenkins(CI)サービスをWebhookで連携させます。

```{note}
   組織のアカウントを使用している場合、リポジトリを組織に転送するには管理者権限が必要です。
```

<a name="transferring-the-repository" />

## リポジトリの転送

次の手順に従って、プロビジョニング済みのリポジトリを独自のGitHubリポジトリに転送します：

1. 新しいプライベートGitHubリポジトリを作成します。

1. ローカルで、プロビジョニング済みの `dxpcloud` リポジトリのクローンを作成します。

1. 手順2で複製したリポジトリを手順1で作成したリモートリポジトリにプッシュします。

GitHub リポジトリの作成、クローン、プッシュについてのヘルプが必要な場合は、 [GitHub's documentation](https://help.github.com) を参照してください。

<a name="integrating-with-the-jenkins-service" />

## Jenkinsサービスとの統合

次に、新しいリポジトリをDXP CloudのJenkinsサービスと統合する必要があります。 JenkinsサービスにプッシュするGitHubでWebhookを設定します：

1. GitHubでは、リポジトリの **Settings** ページに移動し、 ［**Webhooks**］ を選択します。

1. ［**Webhookの追加**］ クリックします。 **Webhookの追加** フォームが開きます。

1. **Payload URL** フィールドに、DXP Cloud `infra` 環境のJenkinsサービスのドメインを追加します。 たとえば、 `acme` というプロジェクトの `インフラ` 環境の `ci` サービスのURLは `https://ci-acme-infra.lfr.cloud/github-webhook/`です。 Jenkins GitHubプラグインと統合するには、相対パス `github-webhook` が必要です。

1. **Content type** セレクターメニューで、 **application/json** を選択します。

1. ［**Secret**］ フィールドを空白のままにし、 ［**Enable SSL verification**］ が選択されていることを確認します。

    ![図1：ペイロードURLとコンテンツタイプを指定し、SSL検証を有効にします。](./configuring-your-github-repository/images/01.png)

1. ［**Which events would you like to trigger this webhook?**］ の下にある、 ［**Let me select individual events.**］ を選択してください。 イベントのリストが表示されます。

1. イベントのリストから ［**Pushes**］ と ［**Pull Requests**］ を選択します。

    ![図2：このWebhookの個々のイベントを選択する必要があります。](./configuring-your-github-repository/images/02.png)

    ![図3：プッシュとプルリクエストを選択します。](./configuring-your-github-repository/images/03.png)

1. ［**Active**］ が選択されていることを確認し、 ［**Add webhook**］ をクリックします。

    ![図4：WebhookをActiveに設定し、作成を完了します。](./configuring-your-github-repository/images/04.png)

<a name="setting-environment-variables" />

### 環境変数の設定

新しいリポジトリを指すようにJenkinsサービスの環境変数を設定します：

1. DXP Cloudコンソールにログインし、 `infra` 環境でJenkinsサービスに移動します。

1. ［**環境変数**］ タブに移動します。

1. 以下の環境変数を設定します：

| 名前                            | Value          |
| ----------------------------- | -------------- |
| `LCP_CI_SCM_PROVIDER`         | github         |
| `LCP_CI_SCM_REPOSITORY_OWNER` | [repo_owner]   |
| `LCP_CI_SCM_REPOSITORY_NAME`  | [repo_name]    |
| `LCP_CI_SCM_TOKEN`            | [access_token] |

`LCP_CI_SCM_TOKEN` の値には、GitHub組織用に作成した個人用アクセストークンを使用します。 このトークンの作成とアクセスの手順については、 [GitHub's documentation](https://help.github.com/articles/creating-a-personal-access-token-for-the-command-line) を参照してください。

```{note}
   SAMLシングルサインオン認証で組織のアカウントを使用している場合は、アクセストークンを認証するために追加の手順を行う必要があります。 詳細は、 [GitHub's official documentation](https://docs.github.com/en/free-pro-team@latest/github/authenticating-to-github/authorizing-a-personal-access-token-for-use-with-saml-single-sign-on) を参照してください。
```

これらの環境変数を更新した後、Jenkinsサービスが再起動します。 これで、新しいリポジトリでプッシュされたブランチとプルリクエストがビルドをトリガーします。

```{note}
   ［2.222.1-3.2.0］以前のJenkinsのバージョンでは、代わりに環境変数 ［GITHUB_REPOSITORY］と［GITHUB_TOKEN］を使用します。 環境変数 ［LCP_CI_SCM_*］を使用する場合、Jenkins［2.222.1-3.2.0］以上のものを実行していることを確認してください。
```

<a name="personal-access-token-usage" />

### パーソナルアクセストークンの使用

リポジトリと統合するために`LCP_CI_SCM_TOKEN`の 値によってレファレンスされているパーソナルアクセストークンが必要です。

```{warning}
   パーソナルアクセストークンが個人のユーザーアカウントに属していて、そのユーザーが組織から削除されている場合、すべてのビルドが完了しません。 代わりに、組織専用のアカウントを使用してください。 詳細は、 [GitHub's official documentation](https://docs.github.com/en/actions/learn-github-actions/security-hardening-for-github-actions#considering-cross-repository-access) を参照してください。
```

デフォルトでは、GitHub組織の [Personal Access Token](https://docs.github.com/en/github/authenticating-to-github/creating-a-personal-access-token) に、 `admin:repo_hook` のパーミッションがなければ、CIサービスがデフォルトのウェブフックを使ってうまく統合できません。

しかし、[CI service](../platform-services/continuous-integration.md)の`LCP_CLI_SCM_MANAGE_HOOKS` [environment variable](../reference/defining-environment-variables.md)を`false`に設定することをお勧めします。 これにより、ウェブフックの自動管理が無効になり（リポジトリとの統合設定が終了すると不要になります）、DXP Cloudが使用するパーソナルアクセストークンから（管理者レベルの） `admin:repo_hook` の権限を削除することができます。

![ウェブフックの自動管理を無効にすると、個人用アクセストークンからadmin:repo_hookの権限を削除することができます。](./configuring-your-github-repository/images/05.png)

これらの権限をウェブフックから削除することで、リポジトリに与えられるアクセスを最小限に抑え、セキュリティを向上させることができます。

<a name="verifying-builds" />

## ビルドの確認

プッシュされたブランチとプルリクエストは、DXP Cloudコンソールの ［**Builds**］ タブから表示またはデプロイできるビルドをトリガーする必要があります。 Jenkins サービスとの統合を設定したら、次のステップとして、インテグレーションが成功したかどうかを確認するためにビルドを検証します。

<a name="verifying-builds-from-pushed-branches" />

### プッシュされたブランチからのビルドの確認

新しいGitプッシュがJenkinsビルドをトリガーすることを確認します：

1. リポジトリに変更を加え（ファイルの追加など）、ブランチにコミットします：

    ```bash
    git commit -m "Add file to test builds"
    ```

1. ブランチをGitHubにプッシュします：

    ```bash
    git push origin branch-name
    ```

1. DXP Cloud コンソールの **Builds** ページに移動します。

1. **Builds** ページで、プッシュされたブランチのビルドが表示されることを確認します。

<a name="verifying-builds-from-pull-requests" />

### プルリクエストからのビルドの確認

新しいプルリクエストがJenkinsビルドをトリガーすることを確認します：

1. 任意のブランチから `develop` ブランチへのプルリクエストを作成します。

1. プルリクエストに対して新しいビルドが作成されていることを確認します。

1. DXP Cloud コンソールの **Builds** ページに移動します。

1. ブランチのリンクをクリックして、適切なビルドでコミットします。

1. リンクが正しいGitHubページにリダイレクトされることを確認します。

<a name="additional-information" />

## 追加情報

* [BitBucketリポジトリの設定](./configuring-your-bitbucket-repository.md)
* [GitHubリポジトリの設定](./configuring-your-gitlab-repository.md)