# Liferay DXPサービスの紹介

Liferay DXPサービスは、あらゆるプロジェクトの心臓部です。 アプリケーションのLiferay DXPインスタンスを実行し、Webサーバー、Elasticsearch、MySQLデータベースなどの他のサービスとやり取りします。

![図1：Liferay DXPサービスは、DXP Cloudで利用可能なサービスの1つです。](./introduction-to-the-liferay-dxp-service/images/01.png)

DXP CloudのLiferay DXPサービスは、Liferay DXPのオンプレミスインスタンスと同じように多くの方法で使用できます。 ただし、DXP Cloudでインスタンスを操作する場合、設定と開発のワークフローにもいくつかの違いがあります。

詳しくは、 [Liferay service limitations](../reference/platform-limitations.md#liferay-service) を参照してください。

* [バージョンの選択](#choosing-a-version)
* [デプロイメント（カスタマイズ、パッチ適用、ライセンス）](#deployment-customization-patching-and-licensing)
* [構成](#configuration)
* [ホットデプロイ](#hot-deploy)
* [クラスタリングを有効にする](#enabling-clustering)
* [スクリプトの実行](#running-scripts)

<a name="choosing-a-version" />

## バージョンの選択

使用しているLiferay DXPのメジャーバージョンは、Gitリポジトリの`liferay/`フォルダの`LCP.json`ファイル内で設定されています。 `LCP.json`ファイル内のDockerイメージ名を使用してメジャーバージョンを `image` 変数として設定します。

```
"image": "liferaycloud/liferay-dxp:7.2-4.0.1"
```

同じ `liferay/` フォルダ内の`gradle.properties`ファイルで、サービスパックとフィックスパックを定義します。 `liferay.workspace.docker.image.liferay` プロパティは、実際のデプロイに使用される、この特定のフィックスパックレベルを持つ別のDockerイメージ名を定義します。

```properties
liferay.workspace.docker.image.liferay=liferay/dxp:7.2.10-sp2-202005120922
```

```{note}
   DXP Cloudのスタックがまだ4.x.xにアップデートされていない場合、デフォルトでは、このバージョンは代わりに、リポジトリのルートにある radle.properties` ファイル内に配置されます。 この場合、`liferay.workspace.lcp.liferay.image` プロパティでバージョンを定義します（メジャーバージョンと別に定義する必要はありません）。 バージョンの確認については、 [サービススタックのバージョンについて](../reference/understanding-service-stack-versions.md) を参照してください。
```

DXP Cloudの [Services Changelog](https://help.liferay.com/hc/ja/sections/360006251311-Services-Changelog) をチェックして、新しいリリースごとのリファレンスを確認できます。 新しい各サービスアップデートには、インスタンスに使用できるDockerイメージが含まれています。 また、 [Docker HubのDXPタグ](https://hub.docker.com/r/liferay/dxp/tags?page=1) を直接確認して、使用するDockerイメージ名を探すこともできます。

リリースメモに記載されている新しいバージョンを使用して、Dockerイメージの値を更新します。 新しいDockerイメージは、インスタンスの起動時、または次回リポジトリからLiferayサービスをデプロイするときに使用されます。 新しいリリースのDockerイメージを使用して、他のサービスのプロパティをアップグレードすることもできます。

<a name="deployment-customization-patching-and-licensing" />

## デプロイメント（カスタマイズ、パッチ適用、ライセンス）

Liferay DXPへのカスタム追加の導入には、Gitリポジトリの適切な場所への新しいモジュール、ライセンス、またはホットフィックスの追加が含まれます。

`common/`ディレクトリを除き、特定の環境フォルダ（`dev`、`uat`、`prod`など）に追加された変更は、対応する環境にデプロイするときに **のみ** 伝播されます。 `common/`ディレクトリに追加された変更は、ターゲットのデプロイ環境に関係なく、 **常に** デプロイされます。 これは、すべてのサービスにおいて、 `configs/` ディレクトリ内のすべてのサブフォルダに適用されます。

Liferayサービスは、他のサービスに比べて多くのカスタマイゼーション（ホットフィックスの追加、モジュールのソースコードの構築など）を提供しているため、サービスをデプロイする際には、いくつかの考慮すべき点があります。 詳しくは [Liferayサービスへのデプロイ](./deploying-to-the-liferay-service.md) を参照してください。 また、一般的なデプロイメントワークフローについては、 [Overview of Deployment Workflow](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md) を参照してください。 DXP Cloudへのデプロイについてのチュートリアルは、 [Deploying Changes via DCP Cloud Console](../build-and-deploy/deploying-changes-via-the-dxp-cloud-console.md)を参照してください。

<a name="configuration" />

## 設定

`portal.properties` 変更など、Liferayサービスに設定を適用するには、Gitリポジトリに設定を追加し、変更をGitにプッシュする必要があります。 これらの設定ファイルの追加の詳細は、 [Liferay DXPサービスの設定](./configuring-the-liferay-dxp-service.md)参照してください。

環境変数は、Liferayサービスの設定にも使用され、場合によってはポータルのプロパティをオーバーライドすることもあります。 詳しくは、 [Liferayサービス環境変数](./liferay-service-environment-variables.md) を参照してください。

<a name="hot-deploy" />

## ホットデプロイ

ホットデプロイは、Liferay DXP UIを介して実行できます。 これを行うには、［コントロールパネル］→［アプリ］→［アプリマネージャー］に移動します。 次に、右上の点をクリックして、[アップロード]をクリックします。 この画面から、ローカルファイルシステムからファイルを選択してデプロイおよびインストールできます。

```{note}
   この方法でデプロイされたカスタマイズは、その後のDXPサービスのデプロイで失われてしまうため、DXP Cloudでホットデプロイを使用することは推奨されません。
```

<a name="enabling-clustering" />

## クラスタリングを有効にする

DXP CloudでのLiferay DXPのクラスタリングは、Liferay DXPでのクラスタリングに比べて非常に単純化されたプロセスです。 クラスタリングのサポートが利用可能で、DXP Cloudですぐに使用できます。 クラスタリングの動作とスケールのための追加の設定には、いくつかの追加の手順が必要です。 詳細は、 [DXP Cloud](./setting-up-clustering-in-dxp-cloud.md) でのクラスタリングのセットアップを参照してください。

<a name="running-scripts" />

## スクリプトの実行

`configs/{ENV}/scripts` フォルダーにある `.sh` ファイルは、サービスを開始する前に自動的に実行されます。 スクリプトは、より広範なカスタマイズに使用できます。 ただし、これを行う際は注意してください。 これはLiferay DXPをカスタマイズする最も強力な方法であり、望ましくない副作用を引き起こす可能性があります。

例えば、すべてのログファイルを削除するスクリプトを含めるには、プロジェクトのGitリポジトリ内の次のディレクトリ構造に配置します。

```
liferay
├── LCP.json
└── configs
    └── dev
        ├── deploy
        ├── osgi
        ├── patching
        └── scripts
            └── remove-log-files.sh
```

```{note}
   バージョン3.x.xのサービスを使用している場合、スクリプトはリポジトリの「lcp/liferay/script/」フォルダに置かれます。 バージョンの確認については、 [サービススタックのバージョンについて](../reference/understanding-service-stack-versions.md) を参照してください。
```

<a name="limitations" />

## 制限事項

Liferay DXPには、コンテンツを構築、管理、Webにデプロイするための強力な機能が多数搭載されています。 しかし、その中にはDXP Cloudでは利用できない機能もあります。

* [リモートステージング](https://learn.liferay.com/dxp/latest/ja/site-building/publishing-tools/staging/configuring-remote-live-staging.html) は、現在DXP Cloudではサポートされていません。 オンプレミスからDXP Cloudに移行する場合、ステージング機能を継続して使用するためには、ローカルステージングに変更する必要があります。

<a name="additional-information" />

## 追加情報

* [Liferayサービスへのデプロイ](./deploying-to-the-liferay-service.md)
* [DXP Cloudサービスへのログイン](../getting-started/logging-into-your-dxp-cloud-services.md)
* [Liferay DXPサービスの構成](./configuring-the-liferay-dxp-service.md)
* [DXP Cloudコンソールによる変更のデプロイ](../build-and-deploy/deploying-changes-via-the-dxp-cloud-console.md)
