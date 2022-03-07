# DXP Cloudコンソールによる変更の展開

このチュートリアルでは、Liferayサービスにポータル・プロパティを追加し、プル・リクエストでCIビルドをトリガーし、DXP Cloudコンソールを介して `dev` 環境に新しいビルドをデプロイするプロセスを説明します。

始めるためには、プロジェクトのCIサービスと統合されたコードホスティングサービスと、プロジェクトのリポジトリのローカルな作業コピーが必要です。

```{note}
   別のデプロイメントワークフローについては、[Deploying Changes via CLI Tool](./deploying-changes-via-the-cli-tool.md) を参照して、Liferay Cloud Platform CLIツールを使用して、プロジェクト環境にローカルの変更をデプロイする方法を学んでください。
```

* [Liferayサービスへのポータル・プロパティの追加](#adding-a-portal-property-to-the-liferay-service)
* [GitHubを使ったJenkinsビルドの実行](#triggering-a-jenkins-build-with-github)
* [DXP Cloudコンソールによる新規ビルドのデプロイメント](#deploying-your-new-build-via-the-dxp-cloud-console)
* [サンプルデプロイメントの検証](#verifying-your-sample-deployment)

<a name="adding-a-portal-property-to-the-liferay-service" />

## Liferayサービスへのポータル・プロパティの追加

デプロイメント ライフサイクルを開始するには、 `dev` 環境の Liferay サービスにポータル プロパティを追加し、変更をコミットします。

1. ローカルのマスターブランチが、プロジェクトのリポジトリの最新版であることを確認してください。

1. 最新の master ブランチをベースに、新しい作業用ブランチを作成します。

   ```bash
   git checkout -b example-console-deployment-branch
   ```

1. `<project>\liferay\configs\dev\portal-env.properties`に移動し、次のプロパティを追加します。

   ```properties
   web.server.display.node=true
   ```

   ```{note}
      バージョン3.x.xのサービスを使用している場合は、適切なフォルダパスは `lcp/liferay/deploy/dev` となります。 ディレクトリ構造の違いについては、 [バージョン4におけるDXP Cloudプロジェクトの変更](../reference/dxp-cloud-project-changes-in-version-4.md) **_** を、サービスのバージョンを確認する方法については、 [サービススタックのバージョンについて](../reference/understanding-service-stack-versions.md) **_** を参照してください。
   ```

1. 以下のコマンドで、 `portal-env.properties` ファイルに変更を追加し、コミットします。

   ```bash
   git add .
   ```

   ```bash
   git commit -m "Test - Adding Portal Property"
   ```

<a name="triggering-a-jenkins-build-with-a-pull-request" />

## プル・リクエストによるJenkinsビルドのトリガー

次に、プロジェクトの中央Gitリポジトリへのプルリクエストを使ってJenkinsのビルドを開始します。

1. 新しいブランチを、ローカルリポジトリのオリジンにプッシュします。

   ```bash
   git push -u origin example-console-deployment-branch
   ```

1. Gitリポジトリに移動し、プロジェクトの中央リポジトリへの新しいプルリクエストを使ってJenkinsのビルドを開始します。

   ![プロジェクトのセントラル・リポジトリへの新しいプルリクエストによって、Jenkinsのビルドが開始されます。](./deploying-changes-via-the-dxp-cloud-console/images/01.png)

   ビルドの状況を把握したり、詳細なログを見るには、プロジェクトのJenkinsページから行うことができます。 `https://ci-<project-name>-infra.lfr.cloud`

   ![プロジェクトのJenkinsページでビルドのステータスを追跡](./deploying-changes-via-the-dxp-cloud-console/images/02.png)

   ![プロジェクトのJenkinsページから詳細なログを見ることができます。](./deploying-changes-via-the-dxp-cloud-console/images/03.png)

<a name="deploying-your-new-build-via-the-dxp-cloud-console" />

## DXP Cloudコンソールによる新規ビルドのデプロイメント

新しいビルドの準備ができたら、DXP Cloudコンソールからプロジェクトの `dev` 環境にデプロイします。

1. DXP Cloudコンソールで、プロジェクトの **Builds** ページに移動します。 このページは、どのプロジェクト環境からでもアクセスできます。

1. デプロイしたいビルドの ［**Actions**］ ボタン（⋮）をクリックし、 ［**Deploy Build To...**］ を選択します。

   ![ビルドページからビルドの表示とデプロイを行うことができます。](./deploying-changes-via-the-dxp-cloud-console/images/04.png)

1. ドロップダウンメニューを使って、 `dev` の環境を選択します。

   ![開発環境を選択し、［Deploy Build］をクリックします。](./deploying-changes-via-the-dxp-cloud-console/images/05.png)

1. チェックボックスを使ってデプロイを確認し、 ［**Deploy Build**］ をクリックします。

   ![チェックボックスを使ってデプロイを確認し、［Deploy Build］をクリックします。](./deploying-changes-via-the-dxp-cloud-console/images/06.png)

   ```tip::
      また、デプロイしたいビルドの **Build ID** をクリックすると、 **Deploy Build to...** のページにアクセスできます。
   ```

   デプロイメントのステータスは、 **デプロイメント** ページや、  `dev` 環境の **ログ** および **アクティビティ** ページから見ることができます。

   ![デプロイメントページからデプロイメントのステータスを確認する](./deploying-changes-via-the-dxp-cloud-console/images/07.png)

<a name="verifying-your-sample-deployment" />

## サンプルデプロイメントの検証

ビルドが正常にデプロイされ、 `dev` 環境のLiferayサービスが **Ready** になったら、以下の手順で変更内容を確認します。

1. プロジェクトの `dev` の環境に移動します。

1. **Web Server** サービスのページにアクセスし、その URL をクリックして、 `dev` 環境の DXP インスタンスにアクセスします：`https://webserver-<project-name>-dev.lfr.cloud/`。

   ![開発環境のDXPインスタンスにアクセスするために、WebサーバのURLをクリックします。](./deploying-changes-via-the-dxp-cloud-console/images/08.png)

1. ホームページの下部にWebサーバーのノードが表示されていることを確認します。

   ![ホームページの下部にWebサーバーのノードが表示されていることを確認します。](./deploying-changes-via-the-dxp-cloud-console/images/09.png)

<a name="additional-information" />

## 追加情報

* [DXP Cloud開発ワークフローの概要](./overview-of-the-dxp-cloud-deployment-workflow.md)
* [CLIツールによる変更点のデプロイ](./deploying-changes-via-the-cli-tool.md)
* [自動デプロイメントの設定](./automatically-deploying-ci-service-builds.md)
* [Liferay DXPサービスの設定　](../using-the-liferay-dxp-service/configuring-the-liferay-dxp-service.md)
