# GitLabリポジトリの設定

DXP Cloudオンボーディングメールを受信すると、 `dxpcloud` 組織でホストされているGitHubリポジトリがプロビジョニングされます。 このリポジトリは、チームの個別のプライベートDXP Cloud開発リポジトリのテンプレートとして使用する必要があり、通常は10営業日後に削除されます。 ユーザーは次のことを期待されています。

1.  最初にプロビジョニングされたリポジトリを独自のプライベートリポジトリに転送します。
2.  Webhookを使用して、そのリポジトリをDXP CloudのJenkins（CI）サービスと統合します。

プロビジョニングされたリポジトリはGitHubにありますが、Jenkinsサービスのバージョン3.2.0以降ではGitLabリポジトリに転送できます。

## Jenkinsサービスの準備

開始する前に、 `LCP.json` で `ci` サービスを確認し、次のJenkinsサービス以上を実行していることを確認してください。

    liferaycloud/jenkins:2.222.1-3.2.0

そうでない場合は、次の手順に従ってアップグレードします。

1.  最後に、新しいリポジトリを指すようにJenkinsサービスの環境変数を設定します。

2.  ルートフォルダーにある `Jenkinsfile` を削除します。

3.  以下の環境変数を追加します。 `LCP_CI_USE_DEFAULT_JENKINSFILE: true`.

4.  Jenkinsサービスをデプロイします。

<!-- end list -->

```{note}
Jenkinsfile をカスタマイズしている場合は、以下のガイドに従って [デフォルトの Jenkinsfile](../platform-services/continuous-integration.md#extending-the-default-jenkinsfile)_.
```

## GitLabリポジトリの作成

まず、新しいGitLabリポジトリを作成します。

1.  [GitLab](https://gitlab.com)に移動します。

2.  *新しいプロジェクト*をクリックします。

    ![[新しいプロジェクト]をクリックして、新しいGitLabリポジトリの作成を開始します。](./configuring-your-gitlab-repository/images/01.png)

3.  プロジェクトのスラッグ（URL内のリポジトリ名）を提供します。

    ![新しいリポジトリの詳細を入力します。](./configuring-your-gitlab-repository/images/02.png)

4.  表示レベルをプライベートに設定します（無料ユーザーは無制限のプライベートリポジトリを使用できます）。

5.  *READMEでリポジトリを初期化* がチェックされていないことを確認します。

6.  *プロジェクトを作成*をクリックします。

## GitHubからGitLabへの転送

以下の手順に従って、初期の GitHub リポジトリを自分の GitLab リポジトリに転送します。

1.  最初のGitHubリポジトリをローカルでクローンします。

    `git clone git@github.com:dxpcloud/example.git`

    ```{note}
    すでに他のプロバイダで作業するためにリポジトリをクローンしている場合は、このステップをスキップして、同じクローン内で作業することができます。
    ```

2.  新しいGitリモートを追加し、GitLabをポイントします。

    `git remote add gitlab git@gitlab.com:USERNAME/REPOSITORYNAME.git`

3.  クローンされたリポジトリを新しいリモートリポジトリにプッシュします。

    `git push gitlab master`

GitHub リポジトリの作成、クローン、プッシュについてのヘルプが必要な場合は、 [GitHub のドキュメント](https://docs.gitlab.com/ee/gitlab-basics/start-using-git.html)を参照してください。

## GitLabのアクセストークンの生成

次に、JenkinsのビルドをトリガーするためにWebhookで使用するアクセストークンを作成します。

1.  [パーソナル・アクセストークンのページ](https://gitlab.com/profile/personal_access_tokens)に移動します。

    ![後で再びアクセスできないGitLabの個人アクセストークンを作成します。](./configuring-your-gitlab-repository/images/03.png)

2.  このアクセストークンの名前と有効期限を指定します。

3.  次のアクセス許可を持つようにアクセストークンを設定します。

      - api
      - read\_repository
      - write\_repository

4.  [ *個人用アクセストークンの作成*]をクリックします。

5.  アクセストークンをコピーしてどこかに保存します（それ以外の場合は表示されません）。

## GitLabをJenkinsサービスに接続する

次の手順に従って、初期GitHubリポジトリを独自のGitLabリポジトリに転送します。

1.  DXP Cloudコンソールにログインし、 `インフラ` 環境でJenkinsサービスに移動します。

2.  「 *環境変数* 」タブに移動します。

3.  以下の環境変数を設定します。

| 名前                            | 値                     |
| ----------------------------- | --------------------- |
| `LCP_CI_SCM_PROVIDER`         | gitlab                |
| `LCP_CI_SCM_REPOSITORY_OWNER` | \[repo\_owner\]   |
| `LCP_CI_SCM_REPOSITORY_NAME`  | \ [リポジトリ\ _name \] |
| `LCP_CI_SCM_TOKEN`            | \[アクセストークン\]        |

これらの環境変数を更新すると、Jenkinsサービスが再起動します。 これで、新しいリポジトリ内のプッシュされたブランチとプルリクエストがトリガーされます。

## 追加のGitLab Webhook設定

次に、WebhookがJenkinsビルドをトリガーするために使用するアクセストークンを作成します。

1.  GitLabリポジトリに移動します。

2.  *設定* 、 *Webhook*を選択します。

3.  *Project Hooks*で、作成されたWebhookがリストされていることを確認します。

4.  CI Webhookの[ *Edit* ]ボタンをクリックします。

    ![リポジトリ用に自動的に作成されたWebhookを編集します。](./configuring-your-gitlab-repository/images/04.png)

5.  *タグプッシュイベント* と *コメント*のチェックを外します。

6.  *SSL検証を有効にする*にチェックします。

7.  *[変更を保存]* をクリックします。

## ビルドの確認

プッシュされたブランチとマージリクエスト（GitLabのプルリクエストに相当）は、DXP Cloudコンソールの[ *Builds* ]タブから表示またはデプロイできるビルドをトリガーする必要があります。 Jenkins サービスとの統合を設定したら、次のステップとして、統合が成功したかどうかを確認するためにビルドを検証します。

### プッシュされたブランチからのビルドの確認

新しいGitプッシュがJenkinsビルドをトリガーしていることを確認するには：

1.  リポジトリに変更を加え（ファイルの追加など）、ブランチにコミットします。

    ``` bash
    git commit -m "Add file to test builds"
    ```

2.  ブランチをGitLabにプッシュします。

    ``` bash
    git push gitlab branch-name
    ```

3.  DXP Cloudコンソールの *ビルド* ページに移動します。

4.  *ビルド* ページで、プッシュされたブランチのビルドが表示されることを確認します。

### マージリクエストからのビルドの確認

新しいマージ要求がJenkinsビルドをトリガーしていることを確認するには：

1.  任意のブランチから `develop` ブランチへのマージリクエストを作成します。

2.  マージ要求に対して新しいビルドが作成されていることを確認します。

3.  DXP Cloudコンソールの *ビルド* ページに移動します。

4.  ブランチのリンクをクリックして、適切なビルドでコミットします。

    ![[ビルド]ページで、ブランチのリンクを確認し、ビルドをコミットします。](./configuring-your-gitlab-repository/images/05.png)

5.  リンクが正しいGitLabページにリダイレクトすることを確認します。

## 追加情報

  - [BitBucketリポジトリの設定](./configuring-your-bitbucket-repository.md)
  - [GitHubリポジトリの設定](./configuring-your-github-repository.md)
