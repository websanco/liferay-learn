# 初期設定の概要

Liferay DXP Cloudの使用を開始するには、すべてのアカウント、環境、サービスが正常にプロビジョニングされ、インスタンスにデプロイされていることを確認する必要があります。 以下の手順で始めます：

  - [セットアップメールの確認](#check-setup-emails)
  - [DXP Cloudコンソールへのアクセス](#access-the-dxp-cloud-console)
  - [プロビジョニングされたプロジェクトのソースコードへのアクセス](#access-the-provisioned-project-source-code)
  - [お客様のCIとDXPサービスにログインする](#log-in-to-your-ci-and-dxp-services)
  - [ベリファイ・セットアップ](#verifying-setup)

## セットアップメールの確認

Liferay DXP Cloudのサブスクリプションに関連付けられたEメールアカウントでセットアップメールを確認してください。 購入したDXP Cloud環境ごとに、DXP Cloudのオンボーディングメールと招待メールが届くはずです。

オンボーディングメールには、非プロダクション環境でJenkinsとLiferay DXPにアクセスするために必要な認証情報と、DXP Cloudを使い始めるための重要なステップが記載されています。

各環境招待状は、購入された1つのDXP Cloud環境へのアクセスを許可するものです。 すべての環境の招待を受けるようにしてください。

## DXP Cloudコンソールへのアクセス

戻ってきたユーザーは、既存のアカウントを使ってDXP Cloudコンソールに [ログインすることができます](https://console.liferay.cloud/login) 。 新規ユーザーは、DXP Cloudのサブスクリプションに関連付けられたメールアカウントを使用して [アカウントを作成する必要があります](https://console.liferay.cloud/signup?undefined) 。

ログインすると、ユーザーはLiferay DXP Cloud Console [のホームページ](https://console.liferay.cloud/projects)に移動します。

![図1：DXP Cloud Consoleのホーム画面。](./initial-setup-overview/images/01.png)

ここから、環境やデプロイメントにアクセスして管理したり、他のチームメンバーを招待したりすることができます。

## プロビジョニングされたプロジェクトのソースコードへのアクセス

新しいDXP Cloudのプロジェクトは、 `dxpcloud` 組織にホストされた一時的なGitHubリポジトリにプロビジョニングされます。 このリポジトリには、DXP Cloudの開発プロジェクトのテンプレートが含まれています。

新しいプロジェクトのリポジトリにアクセスするには、DXP Cloudのオンボーディングメールの指示に従い、後続のGitHub招待状を承認します。

招待状を受け取ったら、プロビジョニングされたプロジェクトのリポジトリを探します。

1.  GitHubへのログイン

2.  `dxpcloud` を *Your teams* の検索ボックスで検索します。

![図2：「Your teams」の検索ボックスでdxpcloudを検索します。](./initial-setup-overview/images/02.png)

プロビジョニングされたリポジトリを見つけたら、次のことをしなければなりません。

1.  プロビジョニングされたリポジトリのコンテンツを、プライベートなGitリポジトリに転送する。

2.  プライベートリポジトリとDXP CloudのJenkins(CI)サービスをWebhookで連携させる。

詳しい手順は、 [Configuring Your GitHub Repository](./configuring-your-github-repository.md) をご覧ください。

``` note::
   他のホスティングサービスを利用していますか？ Bitbucket <./configuring-your-bitbucket-repository.md>`_ または `GitLab <./configuring-your-gitlab-repository.md>`_ のリポジトリを設定する方法を参照してください。
```

## お客様のCIとDXPサービスにログインする

Jenkins(CI)とLiferay DXPにログインして、それぞれのWebインターフェースに直接アクセスします。

どちらのログイン資格も最初のオンボーディング メールで提供されていますが、 *環境変数* の下に、 *infra* 環境の *ci* サービス ページにも記載されています。

詳しい手順については、 [DXP Cloudサービスへのログイン](./logging-into-your-dxp-cloud-services.md) をご覧ください。

## セットアップの確認

Liferay DXP Cloudでの展開を開始する前に、正しい環境とサービスが正常にプロビジョニングされ、展開されていることを確認してください。

### 環境の検証

購入したすべての環境がDXP Cloud・コンソールの [ホームページ](https://console.liferay.cloud/projects) の *プロジェクト*に表示されていることを確認します。

![図3：DXP Cloud Consoleでプロビジョニングされた環境を見る。](./initial-setup-overview/images/03.png)

すべての環境招待を受け入れた後に環境が見つからない場合は、Liferayサポートチームにお問い合わせください。

### 環境の場所を確認する

各環境のロケーションが、チームからリクエストされたロケーションと一致していることを確認します。

すべての環境の場所は、DXP Cloudコンソールの [ホームページ](https://console.liferay.cloud/projects)に記載されています。

また、環境の *概要* ページや *設定* ページに移動することで、環境の位置を確認することができます。

![図4：[Overview]ページで環境の位置を確認する。](./initial-setup-overview/images/04.png)

環境の場所があなたのチームが要求した場所と一致しない場合は、 [Liferayサポートチームに](https://help.liferay.com/hc/en-us/articles/360030208451-DXP-Cloud-Support-Overview)連絡してください。

### DXP Cloudスタックサービスの確認

DXP Cloudのスタックサービスが正しく展開されていることを確認します。

1.  DXP Cloud Consoleから *dev* 環境にアクセスします。

2.  *サービス* ページに移動します。

![図5：[Services]ページでDXP Cloud Stackサービスのステータスを表示します。](./initial-setup-overview/images/05.png)

適切にデプロイされると、5つのデフォルトクラウドスタックサービスのステータスがすべて「Ready」になります。

## 追加情報

  - [GitHubリポジトリの設定](./configuring-your-github-repository.md)
  - [DXP Cloud環境について](./understanding-dxp-cloud-environments.md)
  - [DXP Cloudサービスへのログイン](./logging-into-your-dxp-cloud-services.md)
