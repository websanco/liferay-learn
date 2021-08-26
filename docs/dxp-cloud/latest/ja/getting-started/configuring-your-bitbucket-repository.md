# Bitbucket リポジトリの設定

DXP Cloudオンボーディングメールを受信すると、 `dxpcloud` 組織でホストされているGitHubリポジトリがプロビジョニングされます。 このリポジトリは、チームの個別のプライベートDXP Cloud開発リポジトリのテンプレートとして使用する必要があり、通常は10営業日後に削除されます。 ユーザーは次のことを期待されています。

1.  最初にプロビジョニングされたリポジトリを独自のプライベートリポジトリに転送します。
2.  Webhookを使用して、そのリポジトリをDXP CloudのJenkins（CI）サービスと統合します。

プロビジョニングされたリポジトリはGitHubにありますが、Jenkinsサービスのバージョン3.2.0以降ではBitBucketリポジトリに転送できます。

## Jenkinsサービスの準備

開始する前に、 `LCP.json` で `ci` サービスを確認し、次のJenkinsサービス以上を実行していることを確認してください。

    liferaycloud/jenkins:2.222.1-3.2.0

そうでない場合は、次の手順に従ってアップグレードします。

1.  最後に、新しいリポジトリを指すようにJenkinsサービスの環境変数を設定します。

2.  ルートフォルダーにある `Jenkinsfile` 削除します。

3.  次の環境変数を追加します `LCP_CI_USE_DEFAULT_JENKINSFILE：true`。

4.  Jenkinsサービスをデプロイします。

<!-- end list -->

``` note::
    Jenkinsfile をカスタマイズしている場合は、以下のガイドに従って `デフォルトの Jenkinsfile <../platform-services/continuous-integration.md#extending-the-default-jenkinsfile>`__.
```

## Bitbucketリポジトリの作成

まず、新しいBitbucketリポジトリを作成します。

1.  [Bitbucket](https://bitbucket.org)に移動します。

2.  サイドバーの検索アイコンの下にある「＋」アイコンをクリックします。

    ![+アイコンをクリックして、新しいBitBucketリポジトリの作成を開始します。](./configuring-your-bitbucket-repository/images/01.png)

3.  *リポジトリ* をクリックして、新しいリポジトリの作成を開始します。

    ![新しいリポジトリの詳細を入力します。](./configuring-your-bitbucket-repository/images/02.png)

4.  リポジトリの名前を指定します。

5.  アクセスレベルがプライベートに設定されていることを確認します。

6.  *README を含めるか?* から *いいえ*に設定します。

7.  *リポジトリを作成*をクリックします。

## GitHubからBitbucketへの転送

以下の手順に従って、初期の GitHub リポジトリを自分の Bitbucket リポジトリに転送します。

1.  最初のGitHubリポジトリをローカルでクローンします。

    `git clone git@github.com:dxpcloud/example.git`

    ``` note::
       すでに他のプロバイダで作業するためにリポジトリをクローンしている場合は、このステップをスキップして、同じクローン内で作業することができます。
    ```

2.  新しいGitリモートを追加し、Bitbucketをポイントします。

    `git remote add bitbucket git@bitbucket.org:example/example.git`

3.  クローンされたリポジトリを新しいリモートリポジトリにプッシュします。

    `git push bitbucket master`

リポジトリの作成、複製、およびプッシュに関するヘルプが必要な場合は、 [Bitbucketのドキュメント](https://confluence.atlassian.com/bitbucket/create-a-git-repository-759857290.html)参照してください。

## BitBucketのアプリパスワードの生成

次に、WebhookがBitbucketで認証してJenkinsビルドをトリガーするために使用するアプリパスワードを作成します。 アプリパスワード生成のBitbucketユーザー **必見** リポジトリへの管理者レベルのアクセス権を持っています。

次の手順を実行して、アプリのパスワードを生成します。

1.  ユーザー設定ページの[ *Access Management* ]で、[ *App passwords*]をクリックします。

    ![ユーザー設定ページの[アプリパスワード]をクリックします。](./configuring-your-bitbucket-repository/images/03.png)

2.  [ *アプリパスワードの作成*]をクリックします。

    ![新しいアプリパスワードを生成します。 後で再びアクセスすることはできません。](./configuring-your-bitbucket-repository/images/04.png)

3.  アプリパスワードのラベルを提供します。

4.  アプリに次の権限を付与します。

      - - `プルリクエスト-読み取り、書き込み` （これにより、リポジトリにもフラグが付けられます-読み取り、書き込み）
      - `ウェブフック - 読み取り、書き込み`

5.  [作成]をクリックします。

6.  アプリのパスワードをコピーします（それ以外の場合は表示されません）。 これはBitBucketの個人用アクセストークンに相当します。

<!-- end list -->

``` important::
   アプリのパスワードを生成したユーザーは、環境変数 ``LCP_CI_SCM_USERNAME`` にそのユーザー名を使用しなければなりません。
```

## JenkinsサービスへのBitBucketの接続

最後に、新しいリポジトリを指すようにJenkinsサービスの環境変数を設定します。

1.  DXP Cloudコンソールにログインし、 `インフラ` 環境でJenkinsサービスに移動します。

2.  「 *環境変数* 」タブに移動します。

3.  次の環境変数を設定します。

| 名前                            | 値                      |
| ----------------------------- | ---------------------- |
| `LCP_CI_SCM_PROVIDER`         | bitbucket              |
| `LCP_CI_SCM_REPOSITORY_OWNER` | \[repo\_owner\]    |
| `LCP_CI_SCM_REPOSITORY_NAME`  | \[repo\_name\]     |
| `LCP_CI_SCM_TOKEN`            | \[app\_password\]  |
| `LCP_CI_SCM_USERNAME`         | \[auth\_username\] |

`LCP_CI_SCM_USERNAME` を、 [がアプリのパスワード](#generating-app-password-for-bitbucket)を生成したユーザーとして定義します。

これらの環境変数を更新した後、Jenkinsサービスが再起動します。 これで、新しいリポジトリでプッシュされたブランチとプルリクエストがトリガーされます。

## ビルドの確認

プッシュされたブランチとプルリクエストは、DXP Cloudコンソールの[ *Builds* ]タブから表示またはデプロイできるビルドをトリガーする必要があります。 Jenkins サービスとの統合を設定したら、次のステップとして、インテグレーションが成功したかどうかを確認するためにビルドを検証します。

### プッシュされたブランチからのビルドの確認

新しいGitプッシュがJenkinsビルドをトリガーしていることを確認するには：

1.  リポジトリに変更を加え（ファイルの追加など）、ブランチにコミットします。

    ``` bash
    git commit -m "Add file to test builds"
    ```

2.  ブランチをBitBucketにプッシュします。

    ``` bash
    git push bitbucket branch-name
    ```

3.  DXP Cloud コンソールの *ビルド* ページに移動します。

4.  *ビルド* ページで、プッシュされたブランチのビルドが表示されることを確認します。

### プルリクエストからのビルドの確認

アプリパスワードを生成するには、次の手順を実行します。

1.  任意のブランチから `develop` ブランチへのプルリクエストを作成します。

2.  プルリクエストに対して新しいビルドが作成されていることを確認します。

3.  DXP Cloudコンソールの *Builds* ページに移動します。

4.  ブランチのリンクをクリックして、適切なビルドでコミットします。

    ![[ビルド]ページで、ブランチのリンクを確認し、ビルドをコミットします。](./configuring-your-bitbucket-repository/images/05.png)

5.  リンクが正しいBitBucketページにリダイレクトすることを確認します。

## 追加情報

  - [GitHubリポジトリの設定](./configuring-your-github-repository.md)
  - [GitLabリポジトリの設定](./configuring-your-gitlab-repository.md)
