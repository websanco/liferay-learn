# GitHub リポジトリの設定

DXP Cloudオンボーディングメールを受信すると、 `dxpcloud` 組織でホストされているGitHubリポジトリがプロビジョニングされます。 このリポジトリは、チームの個別のプライベートDXP Cloud開発リポジトリのテンプレートとして使用する必要があり、通常は10営業日後に削除されます。 ユーザーは次のことを期待されています。

1.  最初にプロビジョニングされたリポジトリを独自のプライベートGitHubリポジトリに転送します。
2.  Webhookを使用して、そのリポジトリをDXP CloudのJenkins（CI）サービスと統合します。

## リポジトリの転送

次の手順に従って、初期リポジトリを独自のGitHubリポジトリに転送します。

1.  新しいプライベートGitHubリポジトリを作成します。
2.  ローカルで、最初の `dxpcloud` リポジトリのクローンを作成します。
3.  手順2で複製したリポジトリを手順1で作成したリモートリポジトリにプッシュします。

GitHub リポジトリの作成、クローン、プッシュについてのヘルプが必要な場合は、 [GitHub のドキュメント](https://help.github.com)を参照してください。

## Jenkinsサービスとの統合

次に、新しいリポジトリをDXP CloudのJenkinsサービスと統合する必要があります。 これを行うには、JenkinsサービスにプッシュするGitHubでWebhookを設定する必要があります。

1.  GitHubで、リポジトリの *設定* ページに移動し、 *Webhook*を選択します。

2.  *ウェブフックの追加*をクリックします。  *ウェブフックの追加* フォームが開きます。

3.  *Payload URL* フィールドに、DXP Cloud `infra` 環境のJenkinsサービスのドメインを追加します。 たとえば、 `acme` というプロジェクトの `インフラ` 環境の `ci` サービスのURLは `https://ci-acme-infra.lfr.cloud/github-webhook/`です。 Jenkins GitHubプラグインと統合するには、相対パス `github-webhook` が必要です。

4.  *コンテンツの種類* セレクターメニューで、 *application/json*を選択します。

5.  [ *シークレット* ]フィールドを空白のままにし、[ *SSL検証を有効にする* ]が選択されていることを確認します。

    ![図1：ペイロードURLとコンテンツタイプを指定し、SSL検証を有効にします。](./configuring-your-github-repository/images/01.png)

6.  *このウェブフックをトリガーにしたいイベントはどれですか？*の下にある、*個々のイベントを選択させてください*を選択してください。 イベントのリストが表示されます。

7.  イベントのリストから「 *プッシュ* 」および「 *プルリクエスト* 」を選択します。

    ![図2：このWebhookの個々のイベントを選択する必要があります。](./configuring-your-github-repository/images/02.png)

    ![図3：プッシュとプルリクエストを選択します。](./configuring-your-github-repository/images/03.png)

8.  *Active* が選択されていることを確認してから、 *Add webhook*をクリックします。

    ![図4：WebhookをActiveに設定し、作成を完了します。](./configuring-your-github-repository/images/04.png)

### 環境変数の設定

最後に、新しいリポジトリを指すようにJenkinsサービスの環境変数を設定します。

1.  DXP Cloudコンソールにログインし、 `インフラ` 環境でJenkinsサービスに移動します。

2.  「 *環境変数* 」タブに移動します。

3.  以下の環境変数を設定します。

| 名前                            | 値                     |
| ----------------------------- | --------------------- |
| `LCP_CI_SCM_PROVIDER`         | ギスハブ                  |
| `LCP_CI_SCM_REPOSITORY_OWNER` | \[repo\_owner\]   |
| `LCP_CI_SCM_REPOSITORY_NAME`  | \[repo\_name\]    |
| `LCP_CI_SCM_TOKEN`            | \[access\_token\] |

`LCP_CI_SCM_TOKEN` 値には、GitHub組織用に作成した個人用アクセストークンを使用します。 このトークンの作成とアクセスの手順については、 [GitHubのドキュメント](https://help.github.com/articles/creating-a-personal-access-token-for-the-command-line)を参照してください。

これらの環境変数を更新すると、Jenkinsサービスが再起動します。 これで、新しいリポジトリ内のプッシュされたブランチとプルリクエストがトリガーされます。

## ビルドの確認

プッシュされたブランチとプルリクエストは、DXP Cloudコンソールの[ *Builds* ]タブから表示またはデプロイできるビルドをトリガーする必要があります。 Jenkinsサービスとの統合をセットアップした後、これらのビルドを検証して統合が成功したことを確認するための良い次のステップ。

### プッシュされたブランチからのビルドの確認

新しいGitプッシュがJenkinsビルドをトリガーしていることを確認するには：

1.  リポジトリに変更を加え（ファイルの追加など）、ブランチにコミットします。

    ``` bash
    git commit -m "Add file to test builds"
    ```

2.  ブランチをGitHubにプッシュします。

    ``` bash
    git push origin branch-name
    ```

3.  DXP Cloudコンソールの *Builds* ページに移動します。

4.  *ビルド* ページで、プッシュされたブランチのビルドが表示されることを確認します。

### プルリクエストからのビルドの確認

新しいプルリクエストがJenkinsビルドをトリガーしていることを確認するには：

1.  任意のブランチから `develop` ブランチへのプルリクエストを作成します。

2.  プルリクエストに対して新しいビルドが作成されていることを確認します。

3.  DXP Cloudコンソールの *ビルド* ページに移動します。

4.  ブランチのリンクをクリックして、適切なビルドでコミットします。

5.  リンクが正しいGitHubページにリダイレクトされることを確認します。

## 追加情報

  - [BitBucketリポジトリの設定](./configuring-your-bitbucket-repository.md)
  - [GitLabリポジトリの設定](./configuring-your-gitlab-repository.md)
