# DXP Cloud デプロイメントワークフローの概要

この記事では、開発者がDXP Cloudプロジェクト用に開発してデプロイするための経路を概説します。 DXP Cloudを使用した開発プロセスは、次の3つの段階に従います。

  - [開発と設定](#develop-and-configure)
  - [ビルドとテスト](#build-and-test)
  - [デプロイ](#deploy)

## 開発と設定

環境へのデプロイには複数のパスがありますが、すべてのパスは、[DXP Cloud プロジェクトで設定した](../getting-started/configuring-your-github-repository.md) GitHub リポジトリ に変更を加えることから始まります。 このリポジトリは、Liferay DXPサービスインスタンス自体を含む、DXP Cloudプロジェクトへのカスタム追加の基礎として使用されます。

リポジトリでは以下のように提供されています。

  - Liferay DXPモジュール、テーマ、および拡張機能を構築するためのワークスペース。
  - DXP Cloudサービスの設定とカスタマイズのための共有バージョン管理。
  - DXP Cloudプロジェクトデプロイメントの信頼できる単一の情報源。

`common` フォルダを除いて、所定のサービスの環境フォルダ（例： `DEV`、 `UAT`、 `PRD`）に追加された変化は、対応する環境にデプロイするときにのみ伝播されます。 `common` 追加された変更は、ターゲットのデプロイメント環境に関係なく常にデプロイされます。 詳細は、 [デプロイメント](../using-the-liferay-dxp-service/introduction-to-the-liferay-dxp-service.md#deployment-customization-patching-and-licensing) を参照してください。

### コードの追加

新規追加コードのソースは、リポジトリーのルートにあるフォルダーに追加する必要があります。

  - 新モジュール用の `モジュール` フォルダー
  - カスタムテーマ用の `テーマ` フォルダー
  - デプロイされたWARの `wars` フォルダー

ビルドがデプロイされると、これらの場所のコード変更は自動的にコンパイルされ、Liferay DXPサービスに追加されます。

### コンパイルされた追加ファイル

コンパイル済みファイル（ビルド済みのJARまたはLPKGなど）をサービスの `デプロイ` フォルダに追加できます。 ビルドが環境にデプロイされると、これらのファイルは `$LIFERAY_HOME` 内の対応するフォルダにコピーされます（ファイルタイプによって異なります）。 例えば、 JARファイルを `lcp/liferay/deploy/common/` に追加すると、ビルドがデプロイされた環境に対して `$LIFERAY_HOME/osgi/modules/` にコピーされます。

## ビルドとテスト

CIサービスが自動的に実行され、次のいずれかのイベントのために構築します：コミットは、DXP Cloudリポジトリにマージされ、変更をプル要求をリポジトリに送信され、または `lcs deploy` 展開するためのコマンドラインインタフェース（CLI）を使用して呼び出されますDXP Cloud環境に。 `infra` 環境の `CI` サービスは、テストを含む追加のパイプラインステップを含めるように変更できます。 詳細は、 [継続的インテグレーション](../platform-services/continuous-integration.md) を参照してください。

[ `ビルド` ]タブに移動して、開始されたすべてのビルドを表示します。 保留中、成功、または失敗したビルドがすべて表示されます。 ビルドがCIに合格した場合、クラウドコンソールは、合格したビルドを該当する環境にデプロイするためのオプションをUIで提供します。

![ビルドの確認](./overview-of-the-dxp-cloud-deployment-workflow/images/02.png)

## デプロイ

DXP Cloudのサービスにデプロイするには、2つの主な方法があります。 CLIを介してデプロイするか、DXP Cloud管理コンソールの[ `ビルド` ]タブから成功したビルドをデプロイします。

### オプション1：コマンドラインインターフェースを介したデプロイ

ローカルでリポジトリからサービスをデプロイする最も簡単な方法は、CLIを使用することです。 CLIのセットアップの詳細は、「 [コマンドラインインターフェースの使用](../reference/command-line-tool.md) 」を参照してください。

CLIを介してログインした後、 `lcp deploy` を使用して、ローカルリポジトリに存在する追加を展開します。 CLIは、（例えば、に展開する環境のいずれかを選択するよう求められます `DEV`、 `UAT`、または `PRD`）。 展開を成功させるには、選択した環境に展開する権限が必要です。

### オプション2：DXP Cloudで `ビルド` からデプロイする

変更をデプロイする別の方法は、DXP Cloud管理コンソールからCIで完成したビルドを使用することです。

リポジトリへのコミットされた変更は、プルリクエストが送信またはマージされるたびに、CIの新しいビルドを自動的にトリガーします。 これにより、レビュープロセスの任意の時点で変更をテスト環境に展開できます。 チュートリアルの例については、 [継続的インテグレーション](./walking-through-the-deployment-life-cycle.md) を参照してください。

![製品へのデプロイ](./overview-of-the-dxp-cloud-deployment-workflow/images/01.png)

## 追加情報

  - [GitHubリポジトリの設定](../getting-started/configuring-your-github-repository.md)
  - [DXP Cloud環境について](../getting-started/understanding-dxp-cloud-environments.md)
  - [コマンドラインインターフェイスの使用](../reference/command-line-tool.md)
  - [デプロイメントライフサイクルの概要](../build-and-deploy/walking-through-the-deployment-life-cycle.md)
