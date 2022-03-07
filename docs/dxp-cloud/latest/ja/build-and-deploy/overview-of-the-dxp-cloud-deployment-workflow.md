# DXP Cloud デプロイメントワークフローの概要

DXP Cloudは、高効率なCI/CD戦略を実現するための強固なフレームワークを提供します。 GitとJenkinsの統合により、CIビルドを自動的にトリガーして、プロジェクト環境にデプロイすることができます。 また、CIサービスのビルドプロセスを完全に省略し、 [Command Line Interface](../reference/command-line-tool.md) (CLI)ツールを使用して、プロジェクト環境にローカルの変更を直接デプロイすることもできます。

デプロイメントには複数のパスがありますが、ワークフローは一般的に以下の3つのステージに沿って行われます：

* [開発と構成](#develop-and-configure)
* [ビルドとテスト](#build-and-test)
* [デプロイ](#deploy)

<a name="develop-and-configure" />

## 開発と構成

すべてのワークフローは、プロジェクトのGitリポジトリ（つまり、[GitHub](../getting-started/configuring-your-github-repository.md)、[Bitbucket](../getting-started/configuring-your-bitbucket-repository.md)、または[GitLab](../getting-started/configuring-your-gitlab-repository.md)）に変更を加えることから始まります。 このリポジトリは、Liferay DXPサービスインスタンスを含む、お客様のプロジェクトへのカスタム追加の基礎となります。 このGitリポジトリは、プロジェクトサービスの構成とカスタマイゼーションのための共有バージョンコントロール、プロジェクトのデプロイのための信頼できる唯一のソース、DXPモジュール、テーマ、拡張機能を構築するための共有ワークスペースを提供します。

サービスの [LCP.json file](../reference/configuration-via-lcp-json.md)を構成、 または`configs/` フォルダを介してサービスへの環境固有およびプロジェクト全体の変更を行います。 各サービスの構成オプションの詳細については、それぞれのドキュメンテーションを参照してください：

* [Liferayサービス](../using-the-liferay-dxp-service/configuring-the-liferay-dxp-service.md)
* [バックアップサービス](../platform-services/backup-service/backup-service-overview.md)
* [継続的インテグレーションサービス（Jenkins）](../platform-services/continuous-integration.md)
* [データベースサービス(MySQL)](../platform-services/database-service/database-service.md)
* [検索サービス（Elasticsearch）](../platform-services/search-service.md)
* [Webサーバーサービス（Nginx）](../platform-services/web-server-service.md)

<a name="build-and-test" />

## ビルドとテスト

プロジェクトのGitリポジトリの構成にもよりますが、プロジェクトの中央リポジトリにコミットをマージしたり、変更内容を記載した新しいプルリクエストを公開したりすることで、自動CIビルドをトリガーすることができます。 このプロセスは自動的に行われますが、`infra`環境のCIサービスを変更して、テストを含む追加のパイプラインステップを含めることができます。 詳しくは [継続的インテグレーション](../platform-services/continuous-integration.md) を参照してください。

すべてのプロジェクト環境のビルドの履歴にアクセスするには、DXP Cloudコンソールの **Builds** ページに移動します。 ここでは、CIサービスまたはCLIツールのいずれかによって開始されたすべてのビルドを、その一般的な情報やステータス（保留、通過、失敗など）とともに表示することができます。

![ビルドページからプロジェクトのビルドにアクセスできます。](./overview-of-the-dxp-cloud-deployment-workflow/images/01.png)

<a name="deploy" />

## デプロイ

Liferay DXP Cloudでは、サービスをデプロイする方法として、CLIツールを介したデプロイ（手動）、DXP Cloud管理コンソールを介したデプロイ（手動）、または特定のCIビルドを構成して自動的にデプロイする３つの方法があります。

<a name="option-1-deploying-through-the-command-line-interface" />

### オプション1：コマンドラインインターフェースを介したデプロイ

CLIツールを使用するのが、ローカル変更をサービスにデプロイする最も早い方法です。 これを使えば、ターミナルからデプロイすることができ、変更内容をリモートリポジトリにプッシュしたり、Jenkinsのビルドをトリガーしたりする必要がありません。 他のデプロイ方法とは異なり、CLIツールでは一度に1つのサービスのローカル変更をデプロイすることもできます。

これを行うには、ターミナルのCLIツールにログインし、デプロイしたいサービスのフォルダーに移動します（フォルダーにはサービスの`LCP.json`ファイルが含まれている必要があります）。 次に、`lcp deploy`を実行してデプロイを開始し、デプロイするプロジェクトと環境を選択します（例： `dev`、`uat`、`prd`）。 デプロイを成功させるには、選択した環境にデプロイする権限が必要です。 このデプロイのワークフローについては、 [Deploying Changes via CLI Tool](./deploying-changes-via-the-cli-tool.md)を参照してください。

```{important}
バックアップ、CI、データベース、検索機能、Webサーバの各サービスを直接デプロイすることはできますが、 `lcp deploy` コマンドを実行する前に、まずLiferayサービスのgradleビルドを作成する必要があります。 詳細については、 [Deploying to the Liferay Service](../using-the-liferay-dxp-service/deploying-to-the-liferay-service.md#cli-tool-deployment) を参照してください。
```

CLIツールでデプロイする場合、すべてのサービスを一度にデプロイする（プロジェクトのルートディレクトリからコマンドを実行する）か、単一のサービスのみをデプロイする（サービスの `LCP.json`ファイルを含むディレクトリからコマンドを実行する）ことができます。 `liferay` サービスではデプロイするために [追加のステップ](../using-the-liferay-dxp-service/deploying-to-the-liferay-service.md#cli-tool-deployment) を必要とするため、プロジェクトワークスペースからファイルをビルドおよび準備できます。

<a name="option-2-deploying-from-the-dxp-cloud-console" />

### オプション2：Liferay DXP Cloudコンソールからのデプロイ

Liferay DXP Cloudコンソールは、プロジェクトの変更をデプロイするための主要な方法です。 これを使用すると、成功したビルドを表示して選択し、選択した環境にデプロイできます。 これらには、CIサービスとCLIツールの両方で生成されたビルドが含まれており、DXP Cloudコンソールの **Builds** ページからアクセスできます。 このデプロイのワークフローについては、 [Deploying Changes via the DXP Cloud Console](./deploying-changes-via-the-cli-tool.md)を参照してください。

![DXP Cloudコンソールからビルドをデプロイします。](./overview-of-the-dxp-cloud-deployment-workflow/images/02.png)

<a name="option-3-automatically-deploying-builds-to-dev-environment" />

### オプション3：自動的にビルドを `dev`環境にデプロイ

必要に応じて、CIサービスを設定して、プロジェクトの `dev`環境にビルドを自動的にデプロイすることもできます。 CIサービスに環境変数を追加することで、指定したブランチで作成されたビルドの自動デプロイを開始します。 詳細については、 [Setting Up Automatic Deployment](./automatically-deploying-ci-service-builds) を参照してください。

<a name="additional-information" />

## 追加情報

* [デプロイタイプを理解する](./understanding-deployment-types.md)
* [デプロイメント戦略について](./understanding-deployment-strategies.md)
* [DXP Cloudコンソールによる変更の展開](./deploying-changes-via-the-dxp-cloud-console.md)
* [CLIツールによる変更点のデプロイ](./deploying-changes-via-the-cli-tool.md)
