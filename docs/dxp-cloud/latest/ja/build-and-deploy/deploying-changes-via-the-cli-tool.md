# CLIツールによる変更点のデプロイ

CLIツールを使用すると、Jenkinsのビルドをトリガーしたり、DXP Cloud Management Consoleを使用することなく、ローカルプロジェクトの変更を直接デプロイすることができます。 このチュートリアルでは、Liferayサービスにポータル・プロパティを追加し、CLIツールを使用して `dev` にデプロイするプロセスを説明します。

はじめに、まず [のCLIツール](../reference/command-line-tool.md) をインストールして使用できる状態にし、プロジェクトのGitリポジトリのローカルコピー（例, [GitHub](https://docs.github.com/en/github/creating-cloning-and-archiving-repositories/cloning-a-repository-from-github), [Bitbucket](https://confluence.atlassian.com/bitbucketserver/clone-a-repository-790632786.html), [GitLab](https://docs.gitlab.com/ee/university/training/topics/getting_started.html#instantiate-workflow-with-clone)など）が必要です。)

``` note::
   CLIツールは、プロジェクトへの変更を迅速にデプロイする方法を提供しますが、大部分のデプロイにはCIサービスとDXP Cloudコンソールを使用することがベストプラクティスです。 方法については、`Deploying Changes via DXP Cloud Console <./deploying-changes-via-the-dxp-cloud-console.md>`__ を参照してください。
```

プロジェクトでバージョン3.x.xのサービスを使用している場合、CLIツールで変更をデプロイする前に、まずそれらの`LCP.json`ファイルを[準備](#preparing-lcpjson-files-in-project-version-3)する必要があります。

そうでなければ、このステップをスキップして、デプロイメントプロセスを開始することができます。

  - [Liferayサービスへのポータル・プロパティの追加](#adding-a-portal-property-to-the-liferay-service)
  - [CLIツールで新しいビルドをデプロイする](#deploying-your-new-build-via-the-cli-tool)
  - [サンプルデプロイメントの検証](#verifying-your-sample-deployment)

## プロジェクトバージョン3でのLCP.jsonファイルの準備

リポジトリのルートにある `gradle.properties` を開き、以下のように各サービスのDockerイメージのバージョンを示すプロパティを見つけます。

``` properties
liferay.workspace.lcp.backup.image=liferaycloud/backup:3.2.1
liferay.workspace.lcp.database.image=liferaycloud/database:3.2.1
liferay.workspace.lcp.search.image=liferaycloud/elasticsearch:6.1.4-3.0.3
liferay.workspace.lcp.liferay.image=liferaycloud/liferay-dxp:7.2.10-ga1-3.0.10
liferay.workspace.lcp.webserver.image=liferaycloud/nginx:1.14.2-3.1.1
liferay.workspace.lcp.jenkins.image=liferaycloud/jenkins:2.176.1-3.1.1
```

これらのプロパティのそれぞれについて、値をコピーして、対応するサービスの `LCP.json` ファイル内の `image` プロパティのプレースホルダー値を置き換えるために使用します。 これにより、CLIが `lcp` ディレクトリ内を検索する際に、正しいDockerイメージを使用することができます。

たとえば、この行の `lcp/search/LCP.json` の新しい値として、 `liferay.workspace.lcp.search.image` プロパティの値を使用します。

``` properties
"image": "@liferay.workspace.lcp.search.image@",
```

``` note::
   liferay.workspace.lcp.jenkins.image``プロパティは、``ci``サービスに対応しています。
```

## Liferayサービスへのポータル・プロパティの追加

デプロイメント ライフサイクルを開始するには、 `dev` 環境の Liferay サービスにポータル プロパティを追加し、変更をコミットします。

1.  ローカルのマスターブランチが、プロジェクトのリポジトリの最新版であることを確認してください。

2.  最新の master ブランチをベースに、新しい作業用ブランチを作成します。

    ``` bash
    git checkout -b example-cli-deployment-branch
    ```

3.  `<project>`portal-env.properties`ファイルに以下のプロパティを追加してください。

    ``` properties
    web.server.display.node=true
    ```

    ``` note::
       バージョン3.x.xのサービスを使用している場合は、適切なフォルダパスは ``lcp/liferay/deploy/dev`` となります。 See `DXP Cloud Project Changes in Version 4 <../reference/dxp-cloud-project-changes-in-version-4.md>`__ for more information on the differences in the directory structure, and `Understanding Service Stack Versions <../reference/understanding-service-stack-versions.md>`__ for how to check the version of your services.
    ```

4.  以下のコマンドで変更内容を追加し、コミットします。

    ``` bash
    git add .
    ```

    ``` bash
    git commit -m "Test - Adding Portal Property"
    ```

## CLIツールで新しいビルドをデプロイする

変更をコミットしたら、以下の手順で Gradle を使って Liferay サービスのビルドを作成し、CLI ツールを使ってそれを `dev` 環境にデプロイします。

1.  端末を開き、Liferayのサービスフォルダに移動します。

    ``` bash
    cd <project-folder>/liferay
    ```

    ``` important::
    バージョン3.x.xのサービスを使用している場合は、CLIツールを実行する前に、リポジトリの ``lcp`` ディレクトリに移動して、ディレクトリを走査し、サービスの ``LCP.json`` ファイルを見つけられるようにする必要があります。
    ```

2.  `lcp login`を実行 まだログインしていない場合は、ブラウザで認証を行うよう促されます。

3.  以下のコマンドを順番に実行して、LiferayサービスのGradleビルドを作成します。

    ``` bash
    ./gradlew clean deploy createDockerfile
    ```

    ``` bash
    cp LCP.json build/docker
    ```

    ``` bash
    cd build/docker
    ```

    ``` important::
       lcp deploy``コマンドを実行する前に、まずLiferayサービスのGradleビルドを作成する必要があります。 No local build process is required for other services, so you can directly deploy backup, CI, database, search, and webserver services.
    ```

4.  以下のコマンドを実行して、ビルドを `dev` の環境にデプロイします。

    ``` bash
    lcp deploy --project=<project-name> --environment=dev
    ```

    ![デプロイメントの進捗状況はターミナルで確認できます。](./deploying-changes-via-the-cli-tool/images/01.png)

    このプロセスでは、新しいビルドがプロジェクトに追加され、 `dev` 環境にデプロイされます。 このとき、Liferay サービスは新しいポータル・プロパティで再起動する必要があります。

## サンプルデプロイメントの検証

ビルドが正常にデプロイされ、 `dev` 環境のLiferayサービスが *Ready*になったら、以下の手順で変更内容を確認します。

1.  プロジェクトの `dev` の環境に移動します。

2.  *Web Server* サービスのページにアクセスし、その URL をクリックして、 `dev` 環境の DXP インスタンスにアクセスします。 `https://webserver-<project-name>-dev.lfr.cloud/`

    ![WebサーバーのURLをクリックすると、開発環境のDXPインスタンスにアクセスできます。](./deploying-changes-via-the-cli-tool/images/02.png)

3.  ホームページの下部にWebサーバーのノードが表示されていることを確認します。

    ![ホームページの下部にウェブサーバノードが表示されていることを確認します。](./deploying-changes-via-the-cli-tool/images/03.png)

## 追加情報

  - [コマンドラインツール](../reference/command-line-tool.md)
  - [DXP Cloud開発ワークフローの概要](./overview-of-the-dxp-cloud-deployment-workflow.md)
  - [DXP Cloudコンソールによる変更の展開](./deploying-changes-via-the-dxp-cloud-console.md)
