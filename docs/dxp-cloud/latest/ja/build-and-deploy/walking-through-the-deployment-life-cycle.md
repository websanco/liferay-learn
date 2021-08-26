# デプロイメントライフサイクルの概要

この記事では、通常のデプロイワークフローと同様にサンプルモジュールをデプロイする手順について説明します。 このワークフローのさまざまなステップのより包括的な説明については、 [DXP Cloud開発ワークフローの概要](./overview-of-the-dxp-cloud-deployment-workflow.md) を参照してください。

1.  [前提条件](#prerequisites)
2.  [サンプルをリポジトリに追加する](#add-the-sample-to-the-repository)
3.  [サンプルを開発環境にデプロイする](#deploy-the-sample-to-the-development-environment)
4.  [サンプルの配置を確認する](#verify-the-sample-deployment)

## 前提条件

開始するには、次のものが必要です。

  - サンプルモジュール
  - 設定されたDXP Cloud Gitリポジトリ

### Gitリポジトリの設定

GitHubリポジトリを設定する必要があります。 DXP Cloudリポジトリの設定の詳細は、 [GitHubリポジトリの設定](../getting-started/configuring-your-github-repository.md) を参照してください。

### サンプルモジュール

このチュートリアルでは、JARにコンパイルされたサンプルモジュールが必要です。 任意のモジュールを使用して、このウォークスルーを続行できます。

このチュートリアルでは、既存のモジュールを開発または使用してデプロイします。 このチュートリアルでは、 [Liferay Blade Samples](https://github.com/liferay/liferay-blade-samples) リポジトリのサンプル「JSPポートレット」を使用します。

## サンプルをリポジトリに追加する

サンプルモジュールをGitリポジトリに追加することで、デプロイメントライフサイクルを開始します。 JARを `lcp/liferay`内の適切な `deploy` フォルダに追加します。 このチュートリアルでは、開発環境のフォルダを選択します（例： `dev`）：

``` bash
cp path-to-module/my-module my-repository-path/lcp/liferay/deploy/dev
```

``` note::
   このチュートリアルでは、あなたの開発環境はデフォルトでは ``dev``` という名前になっていると仮定しています。 もし開発環境の名前が違う場合は、``dev``を正しい名前に置き換えてください。
```

## サンプルを開発環境にデプロイする

DXP Cloudへのデプロイは通常、Gitの変更をプッシュし、GitHub WebhookがDXP CloudCIサービスでビルドをトリガーし、最後に成功したビルドをデプロイすることで設定されます。 ただし、必要に応じてCLIを使用して、GitHubへの変更のプッシュをスキップすることもできます。

``` tip::
   一般的にはGitHubにプッシュしてDXP Cloud Management Consoleを使用することがデプロイには推奨されています。
```

### Gitでサンプルをコミットする

サンプルモジュールをリポジトリに追加してコミットします。

``` bash
git commit -am "Add sample module"
```

次に、ブランチをGitHubリポジトリのオリジンにプッシュします。

``` bash
git push origin testing-branch
```

### サンプルでビルドをトリガーする

次に、プル要求を上流のリポジトリに送信します。 CIサービスは、デプロイメントに使用できるビルドを自動的に開始します。

まず、リポジトリに移動し、変更用の新しいプルリクエストを作成します。

![プルリクエストボタン](./walking-through-the-deployment-life-cycle/images/02.png)

プルリクエストをアップストリームリポジトリに送信すると、CIサービスは自動的にビルドを開始し、デプロイメントに使用できるようになります。

![CIでのPRトリガービルド](./walking-through-the-deployment-life-cycle/images/09.png)

``` tip::
   Jenkins `CI サービス <../platform-services/continuous-integration.md>`https://ci-<project>-<environment>.lfr.cloud`` にアクセスすることで、トリガーされたビルドを見ることができます。
```

### DXP Cloud管理コンソールからビルドを開発環境にデプロイする

DXP Cloud管理コンソールに移動し、目的の環境の[ `ビルド` ]タブに移動します。 ビルドの右側にある3つのドットをクリックして、オプションを表示します。

![ビルド](./walking-through-the-deployment-life-cycle/images/03.png)

ビルドを成功させるには、「ビルドのデプロイ先...」をクリックして、選択した環境にデプロイします。 通常、新しいビルドは最初に `dev` 環境にデプロイされます。 ただし、開発者は、権限がある限り、任意の環境に直接デプロイできます。

![展開する環境の選択](./walking-through-the-deployment-life-cycle/images/04.png)

### CLIを使用して展開する

開発者は、必要に応じて、DXP Cloud CLIツールを使用してサービスに直接デプロイできます。

まず、DXP Cloud CLIがインストールされていることを確認します。

``` bash
lcp version
```

``` bash
Liferay Cloud Platform CLI version 2.1.2 linux/amd64
Build commit: 59e244b342d7b119f8e77eb94c6486f8049ca2b3
Build time: Wed Jul 10 01:59:00 UTC 2019
```

そうでない場合は、インストールと使用法の詳細について、 [コマンドラインツール](../reference/command-line-tool.md) 記事を参照してください。

リポジトリのルートで `gradle.properties` を開き、次のように各サービスのDockerイメージバージョンのプロパティを見つけます。

``` properties
liferay.workspace.lcp.backup.image=liferaycloud/backup:3.2.1
liferay.workspace.lcp.database.image=liferaycloud/database:3.2.1
liferay.workspace.lcp.search.image=liferaycloud/elasticsearch:6.1.4-3.0.3
liferay.workspace.lcp.database.image=liferaycloud/database:3.workspace.lcp.liferay.image=liferaycloud/liferay-dxp:7.2.10-ga1-3.0.10
liferay.workspace.lcp.webserver.image=liferaycloud/nginx:1.14.2-3.1.1
liferay.workspace.lcp.jenkins.image=liferaycloud/jenkins:2.176.1-3.1.1.1
```

これらの各プロパティについて、値をコピーし、それを使用して、対応するサービスの `LCP.json` ファイルの `image` プロパティのプレースホルダー値を置き換えます。 これにより、CLIは `lcp` ディレクトリ内を検索するときに正しいDockerイメージを使用できます。

``` note::
   ``liferay.workspace.lcp.jenkins.image`` プロパティは ``ci`` サービスに対応しています。
```

たとえば、この行の `lcp/search/LCP.json` の新しい値として、 `liferay.workspace.lcp.search.image` プロパティの値を使用します。

``` properties
"image": "@liferay.workspace.lcp.search.image@",
```

モジュールの展開を開始するには、まずコマンドプロンプトで `lcp` ディレクトリに移動します。 これは、CLIが変更のためにこのディレクトリをトラバースできるようにするために必要です。

``` bash
cd lcp
```

``` bash
lcp deploy --project=<project-name> --environment=dev
```

![CLIによる展開](./walking-through-the-deployment-life-cycle/images/05.png)

コマンドの実行が完了すると、モジュールは選択した環境にコピーされます。 影響を受けるサービスを再起動してDockerイメージに新しいモジュールを適用するには、しばらく時間がかかります。

## サンプルの配置を確認する

Liferay DXPインスタンスに移動して、モジュールが `dev` 環境にデプロイされたことを確認します。

### ログイン認証情報

Webサーバーにアクセスするには、最初に顧客のユーザー名とパスワードが必要です。 これらの資格情報は、すべての環境で共有されます。

DXP Cloud管理コンソールから、 `インフラ` 環境→CIサービス→環境変数タブに移動します（または直接 `https://console.liferay.cloud/projects/<project-name>-infra/services/ci/environment-variablesに移動します`）。

この画面から、 `JENKINS_CUSTOMER_USER_NAME` および `JENKINS_CUSTOMER_PASSWORD` 変数をチェックして、Webサーバーのログイン資格情報を確認できます。

![ログイン認証情報](./walking-through-the-deployment-life-cycle/images/06.png)

### Webサーバーへのアクセス

`https://console.liferay.cloud/projects/<project-name>-dev/services` に移動して、サービスのステータスを確認します。

`webserver` サービスを使用する準備ができたら、そのサービスに移動し、ページの上部にある「webserver」という単語のリンクをクリックします。 これにより、Webサーバーから実行中のLiferay DXPインスタンスに移動します。

![ウェブサーバーサービスからのリンク](./walking-through-the-deployment-life-cycle/images/07.png)

``` tip::
   また、``https://webserver-<project-name>-<environment>.lfr.cloud/```に直接行っても同じ場所に行けます。
```

Gogoシェルを使用すると、モジュールがデプロイされたかどうかを簡単に確認できます。 コントロールパネル→設定→Gogo Shellに移動します。 ここから、次のコマンドを使用して、モジュールがデプロイされているかどうかを確認します。

`lb | grep "my.module.name"`

![モジュールの配備の確認](./walking-through-the-deployment-life-cycle/images/08.png)

デプロイが成功したことを確認したら、このチュートリアルを完了します。

## 追加情報

  - [コマンドラインツール](../reference/command-line-tool.md)
  - [GitHubリポジトリの設定](../getting-started/configuring-your-github-repository.md)
  - [DXP Cloud開発ワークフローの概要](./overview-of-the-dxp-cloud-deployment-workflow.md)
  - [モジュール開発の開始](https://help.liferay.com/hc/en-us/articles/360017884192-Starting-Module-Development)
  - [DXP Cloud環境について](../getting-started/understanding-dxp-cloud-environments.md)
