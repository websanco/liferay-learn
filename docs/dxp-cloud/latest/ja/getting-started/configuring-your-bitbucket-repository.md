# Bitbucket リポジトリの設定

DXP Cloudオンボーディングメールを受信すると、 `dxpcloud` 組織でホストされているGitHubリポジトリがプロビジョニングされます。 このリポジトリは、チームの個別のプライベートDXP Cloud開発リポジトリのテンプレートで、通常は10営業日後に削除されます。 ユーザーは以下のことを行う必要があります：

1. プロビジョニングされたリポジトリを独自のプライベートリポジトリに転送します。
1. プライベートリポジトリとDXP CloudのJenkins(CI)サービスをWebhookで連携させます。

プロビジョニングされたリポジトリはGitHubにありますが、Jenkinsサービスのバージョン3.2.0以降ではBitBucketリポジトリに転送できます。

<a name="preparing-the-jenkins-service" />

## Jenkinsサービスの準備

すでにDXPクラウドのインスタンスで [version 4.x.x services](../reference/understanding-service-stack-versions.md) を使用している場合は、JenkinsサービスはすでにBitbucketと互換性があります。 アップグレードの詳細については、 [DXP Cloudスタックのアップグレード](../reference/upgrading-your-dxp-cloud-stack.md) を参照してください。

version 3.x.xのサービスを使用している場合は、 `LCP.json` で、 `ci` サービスを確認し、以下のJenkinsサービス以上のものを実行していることを確認してください：

```
liferaycloud/jenkins:2.222.1-3.2.0
```

そうでない場合は、次の手順に従ってアップグレードします：

1. Jenkinsサービスを `liferaycloud/jenkins：2.222.1-3.2.0` バージョンに更新します。

1. ルートフォルダーにある `Jenkinsfile` を削除します。

1. 次の環境変数を追加します： `LCP_CI_USE_DEFAULT_JENKINSFILE: true`。

1. Jenkinsサービスをデプロイします。

```{note}
    Jenkinsfile をカスタマイズしている場合は、以下のガイドに従って [デフォルトの Jenkinsfile](../platform-services/continuous-integration.md#extending-the-default-jenkinsfile) _を延長します。
```

<a name="creating-a-bitbucket-repository" />

## Bitbucketリポジトリの作成

まず、新しいBitbucketリポジトリを作成します。

1. [［Bitbucket］](https://bitbucket.org) に移動します。

1. サイドバーの検索アイコンの下にある「＋」アイコンをクリックします。

    ![+アイコンをクリックして、新しいBitBucketリポジトリの作成を開始します。](./configuring-your-bitbucket-repository/images/01.png)

1. ［**リポジトリ**］ をクリックして、新しいリポジトリの作成を開始します。

    ![新しいリポジトリの詳細を入力します。](./configuring-your-bitbucket-repository/images/02.png)

1. リポジトリの名前を指定します。

1. アクセスレベルがプライベートに設定されていることを確認します。

1. ［**Include a README?**］ の設定を ［**No**］ にします。

1. ［**Create repository**］ をクリックします。

<a name="transferring-from-github-to-bitbucket" />

## GitHubからBitbucketへの転送

以下の手順に従って、プロビジョニング済みの GitHub リポジトリを自分の Bitbucket リポジトリに転送します：

1. プロビジョニング済みのGitHubリポジトリをローカルでクローンします：

    `git clone git@github.com:dxpcloud/example.git`

    ```{note}
       すでに他のプロバイダで作業するためにリポジトリをクローンしている場合は、このステップをスキップして、同じクローン内で作業することができます。
    ```

1. 新しいGitリモートを追加し、Bitbucketをポイントします：

    `git remote add bitbucket git@bitbucket.org:example/example.git`

1. クローンされたリポジトリを新しいリモートリポジトリにプッシュします：

    `git push bitbucket master`

リポジトリの作成、複製、およびプッシュに関するヘルプが必要な場合は、 [Bitbucket's documentation](https://confluence.atlassian.com/bitbucket/create-a-git-repository-759857290.html) を参照してください。

<a name="generating-app-password-for-bitbucket" />

## BitBucketのアプリパスワードの生成

次に、WebhookがBitbucketで認証してJenkinsビルドをトリガーするために使用するアプリパスワードを作成します。 アプリのパスワードを生成するBitbucketユーザーは、リポジトリへの管理者レベルのアクセス権を持っている **必要** があります。

次の手順を実行して、アプリのパスワードを生成します：

1. ユーザー設定ページから、 ［**Access Management**］ にある ［**App passwords**］ をクリックします。

    ![ユーザー設定ページの［アプリパスワード］をクリックします。](./configuring-your-bitbucket-repository/images/03.png)

1. ［**Create app password**］ をクリックします。

    ![新しいアプリパスワードを生成します。後で再びアクセスすることはできません。](./configuring-your-bitbucket-repository/images/04.png)

1. アプリパスワードのラベルを提供します。

1. アプリに次の権限を付与します：

    * `Pull request - read, write` （これにより、リポジトリにもフラグが付けられます - 読み取り、書き込み）
    * `Webhooks -  read, write`

1. ［**作成**］ をクリックします。

1. アプリのパスワードをコピーします（再度表示されません）。 これはBitBucketの個人用アクセストークンに相当します。

```{important}
   アプリのパスワードを生成したユーザーは、環境変数 ［LCP_CI_SCM_USERNAME］にそのユーザー名を使用しなければなりません。
```

<a name="checking-branch-types-and-prefixes" />

## ブランチタイプと敬称の確認

DXP Cloudがブランチに正しくリンクできるようにするためには、リポジトリで使用されているブランチ敬称の完全なリストをDXP Cloudに提供する必要があります。 リポジトリで使用されるブランチタイプには、それぞれ独自の敬称があり、リポジトリの設定で定義されています。

[the Bitbucket website](https://bitbucket.org) で、メニューの左側の ［**Repository settings**］ &rarr; ［**Branching model**］ をクリックします。 これにより、 **Branching model** のページが表示され、各ブランチの敬称が記載されています。 これらの各敬称をメモして、 `LCP_CI_SCM_BITBUCKET_BRANCH_PREFIXES` CI環境変数に追加してください。

![Bitbucket リポジトリのデフォルトのブランチタイプ（および敬称）は、bugfix/、feature/、hotfix/、release/ です。](./configuring-your-bitbucket-repository/images/05.png)

<a name="connecting-bitbucket-to-your-jenkins-service" />

## JenkinsサービスへのBitBucketの接続

最後に、新しいリポジトリを指すようにJenkinsサービスの環境変数を設定します：

1. DXP Cloudコンソールにログインし、 `infra` 環境でJenkinsサービスに移動します。

1. ［**環境変数**］ タブに移動します。

1. 以下の環境変数を設定します：

| 名前                                     | Value              |
| -------------------------------------- | ------------------ |
| `LCP_CI_SCM_PROVIDER`                  | bitbucket          |
| `LCP_CI_SCM_REPOSITORY_OWNER`          | [repo owner]       |
| `LCP_CI_SCM_REPOSITORY_NAME`           | [repo name]        |
| `LCP_CI_SCM_TOKEN`                     | [app password]     |
| `LCP_CI_SCM_USERNAME`                  | [auth username]    |
| `LCP_CI_SCM_BITBUCKET_BRANCH_PREFIXES` | [list of prefixes] |

`LCP_CI_SCM_USERNAME` を、 [アプリのパスワードを生成した](#generating-app-password-for-bitbucket) ユーザーとして定義します。 `LCP_CI_SCM_BITBUCKET_BRANCH_PREFIXES` を、 [リポジトリのブランチで使用される敬称](#checking-branch-types-and-prefixes) の全てのリストとして、スペースで区切って定義します。

これらの環境変数を更新した後、Jenkinsサービスが再起動します。 これで、新しいリポジトリでプッシュされたブランチとプルリクエストがトリガーされます。

<a name="connecting-to-a-private-bitbucket-server" />

## プライベートBitbucketサーバーへの接続

プライベートなBitbucketサーバーを使用するには、Jenkinsサービスで追加の環境変数を設定する必要があります：

| 名前                       | Value              |
| ------------------------ | ------------------ |
| `LCP_CI_SCM_SERVER_HOST` | [private host URL] |

`LCP_CI_SCM_SERVER_HOST` 変数に、プライベートBitbucketサーバーのベースURLを設定します（例えば、 `http://private.bitbucket.org/`）。 これにより、ビルドを生成してリポジトリのブランチにリンクするときにCIがコードベースを取得するために使用するサーバーURLが設定されます。 デフォルトでは、CIはBitbucketのベースURLとして `https://bitbucket.org/` を使用しています。

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

1. ブランチをBitBucketにプッシュします：

    ```bash
    git push bitbucket branch-name
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

    ![Buildページで、ブランチのリンクを確認し、ビルドをコミットします。](./configuring-your-bitbucket-repository/images/06.png)

1. リンクが正しいBitBucketページにリダイレクトすることを確認します。

<a name="additional-information" />

## 追加情報

* [GitHubリポジトリの設定](./configuring-your-github-repository.md)
* [GitHubリポジトリの設定](./configuring-your-gitlab-repository.md)
