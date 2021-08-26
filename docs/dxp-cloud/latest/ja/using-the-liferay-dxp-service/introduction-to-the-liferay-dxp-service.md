# Liferay DXPサービスの概要

Liferay DXPサービスは、あらゆるプロジェクトの心臓部です。 アプリケーションのLiferay DXPインスタンスを実行し、Webサーバー、Elasticsearch、MySQLデータベースなどの他のサービスとやり取りします。

![図1：Liferay DXPサービスは、DXP Cloudで利用可能なサービスの1つです。](./introduction-to-the-liferay-dxp-service/images/01.png)

DXP CloudのLiferay DXPサービスは、Liferay DXPのオンプレミスインスタンスと同じように多くの方法で使用できます。 ただし、DXP Cloudでインスタンスを操作する場合、設定と開発のワークフローにもいくつかの違いがあります。

  - [バージョンの選択](#choosing-a-version)
  - [デプロイメント（カスタマイズ、パッチ適用、ライセンス）](#deployment-customization-patching-and-licensing)
  - [設定](#configuration)
  - [ホットデプロイ](#hot-deploy)
  - [クラスタリングを有効にする](#enabling-clustering)
  - [環境変数](#environment-variables-reference)
  - [スクリプトの実行](#running-scripts)

## バージョンの選択

使用しているLiferay DXPのバージョンは、Gitリポジトリのルートにある `gradle.properties` ファイル内で設定されています。

``` properties
liferay.workspace.lcp.liferay.image=liferaycloud/liferay-dxp:7.2.10-ga1-3.0.10
```

このバージョンには、Liferay DXPインスタンスのベースとなる特定のサービスパックとフィックスパックが含まれています。

DXP Cloudの [Services Changelog](https://help.liferay.com/hc/en-us/sections/360006251311-Services-Changelog) をチェックして、新しいリリースごとのリファレンスを確認できます。 新しい各サービスアップデートには、インスタンスに使用できるDockerイメージが含まれています。

リリースノートの新しいバージョンを使用して、 `liferay.workspace.lcp.liferay.image` プロパティ値を更新します。 新しいDockerイメージは、インスタンスの起動時、または次回リポジトリからLiferayサービスをデプロイするときに使用されます。 新しいリリースのDockerイメージを使用して、他のサービスのプロパティをアップグレードすることもできます。

``` note::
   リポジトリ内の ``LCP.json`` ファイルが Liferay のバージョンの Docker イメージを直接参照している場合は、新しい Docker イメージにアップグレードする際に、これらのファイルも更新する必要があります。
```

## 展開（カスタマイズ、パッチ適用、ライセンス）

Liferay DXPへのカスタム追加の導入には、Gitリポジトリの適切な場所への新しいモジュール、ライセンス、またはホットフィックスの追加が含まれます。

`common/`ディレクトリを除き、特定のサービスの環境フォルダ（`dev`、`uat`、`prod`など）に追加された変更は、対応する環境にデプロイするときに*のみ*伝播されます。 `common/`ディレクトリに追加された変更は、ターゲットのデプロイ環境に関係なく、*常に*デプロイされます。 これは`lcp/liferay/`内にある、`config`、`deploy`、`hotfix`、`license`、 `script` ディレクトリに適用されます。

参照してください [展開ワークフローの概要](../build-and-deploy/overview-of-the-dxp-cloud-deployment-workflow.md) どのように展開ワークフローの詳細は。 DXP Cloudへのデプロイのチュートリアルについては、 [デプロイのライフサイクルを歩く](../build-and-deploy/walking-through-the-deployment-life-cycle.md)を参照してください。

### テーマ、ポートレット、およびOSGiモジュール

テーマ、ポートレット、OSGiモジュールをインストールするには、Liferay DXPサービスディレクトリの `/deploy` にあるフォルダのいずれかにWARまたはJARファイルをインクルードしてください。

たとえば、カスタムJARファイルを開発環境に（ `/dev` フォルダーを使用して）展開するには、Liferay DXPサービスディレクトリは次のようになります。

    lcp
      └── liferay
        ├── deploy
        │   └── dev
        │       └── com.liferay.apio.samples.portlet-1.0.0.jar
        └── LCP.json

### ソースコード

新しい追加のソースコードをCIビルドに含めることもできます。 ビルドが開始されると、ソースコードが自動的にコンパイルされます。 次に、裏ではビルドは結果を正しい `deploy` フォルダーにコピーします。

CIビルドは、これらのフォルダー内のソースコードをコンパイルします。

  - 新モジュール用の `モジュール` フォルダー
  - カスタムテーマ用の `テーマ` フォルダー
  - デプロイされたWARの `wars` フォルダー

<!-- end list -->

``` note::
   ソースコードはCI内のビルドからデプロイされた場合にのみ、デプロイメントに含まれます。
```

### ホットフィックス

ホットフィックスを適用するには、ホットフィックスのZIPファイルをLiferay DXPサービスディレクトリ内の `hotfix /` フォルダーの1つに追加します。 この変更を展開すると、ホットフィックスがLiferay DXPインスタンスに適用されます。

たとえば、次のような構造を持つホットフィックスを開発環境に展開できます。

    lcp
      └── liferay
        ├── hotfix
        │   └── dev
        │       └── liferay-hotfix-2-7110.zip
        └── LCP.json

サーバーが起動するたびにホットフィックスを再適用する必要があることに注意してください。 このため、 `gradle.properties` ファイルでLiferay DXP Dockerイメージの最新のフィックスパックまたはサービスパックに更新することは、このフォルダーに多数のホットフィックスを長期間追加するよりも優れています。 このファイルの `liferay.workspace.lcp.liferay.image` プロパティを置き換えることにより、Dockerバージョンを更新できます。 `gradle.properties` ファイルは、リポジトリーのルートにあります。

### ライセンス

自分のライセンスを追加するには、Liferay DXPサービスディレクトリ内の `license /` フォルダーの1つにライセンスを追加します。

たとえば、Liferay DXPサービスディレクトリに次のような構造でライセンスを開発環境に追加できます。

    lcp
      └── liferay
        ├── license
        │   └── dev
        │       └── license.xml
        │       └── license.aatf
        └── LCP.json

バックグラウンドで、XMLライセンスは `$LIFERAY_HOME/ deploy`にコピーされ、AATFライセンスは `$LIFERAY_HOME/ data`コピーされます。

## 設定

`portal.properties` 変更など、Liferayサービスに設定を適用するには、Gitリポジトリに設定を追加し、変更をGitにプッシュする必要があります。 これらの設定ファイルの追加の詳細は、 [Liferay DXPサービスの設定](./configuring-the-liferay-dxp-service.md)参照してください。

## ホットデプロイ

ホットデプロイは、Liferay DXP UIを介して実行できます。 これを行うには、コントロールパネル→アプリ→アプリマネージャーに移動します。 次に、右上の点をクリックして、[アップロード]をクリックします。 この画面から、ローカルファイルシステムからファイルを選択して展開およびインストールできます。

``` note::
   この方法でデプロイされたカスタマイズは、その後のDXPサービスのデプロイで失われてしまうため、DXP Cloudでホットデプロイを使用することは推奨されません。
```

## クラスタリングを有効にする

DXP CloudでのLiferay DXPのクラスタリングは、Liferay DXPでのクラスタリングに比べて非常に単純化されたプロセスです。 クラスタリングのサポートが利用可能で、DXP Cloudですぐに使用できます。 クラスタリングの動作とスケールのための追加の設定には、いくつかの追加の手順が必要です。 詳細は、 [DXP Cloud](./setting-up-clustering-in-dxp-cloud.md) でのクラスタリングのセットアップを参照してください。

## スクリプトの実行

`スクリプト` フォルダーにある `.sh` ファイルは、サービスを開始する前に自動的に実行されます。 スクリプトは、より広範なカスタマイズに使用できます。 ただし、その際は注意してください。 これはLiferay DXPをカスタマイズする最も強力な方法であり、望ましくない副作用を引き起こす可能性があります。

たとえば、すべてのログファイルを削除するスクリプトを含めるには、プロジェクトのGitリポジトリ内の次のディレクトリ構造に配置します。

    lcp
    └──liferay
        ├── script
        │   └── dev
        │       └── remove-log-files.sh
        └── LCP.json

## 環境変数リファレンス

| 名前                                     | デフォルト値 | 説明                                                                                                 |
| -------------------------------------- | ------ | -------------------------------------------------------------------------------------------------- |
| `LCP_PROJECT_LIFERAY_CLUSTER_ENABLED`  | `true` | ノード間のクラスタリングと通信を有効にするかどうか。                                                                         |
| `LCP_PROJECT_MONITOR_DYNATRACE_TENANT` |        | 8文字の文字列。 Dynatrace SaaSアカウントのURL（プレフィックス）の一部です。                                                    |
| `LCP_PROJECT_MONITOR_DYNATRACE_TOKEN`  |        | Dynatraceアカウントの *22文字の文字列Dynatrace* 導入</em> → *インストールの開始* → *PaaSモニタリングのセットアップ* → *インストーラーのダウンロード* |
| `LIFERAY_JAVA_OPTS`                    |        | デフォルトの推奨オプションを上書きするために、 `CATALINA_OPTS` に追加されるJVMオプション。                                            |

## 追加情報

  - [DXP Cloudサービスへのログイン](../getting-started/logging-into-your-dxp-cloud-services.md)
  - [Liferay DXPサービスの設定](./configuring-the-liferay-dxp-service.md)
  - [デプロイメントライフサイクルの概要](../build-and-deploy/walking-through-the-deployment-life-cycle.md)
