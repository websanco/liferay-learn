# Liferay Workspaceの概要

Liferay Workspaceは、開発ライフサイクル全体を処理するLiferay独自の方法を表すフォルダとGradleスクリプトのセットです。

- [プロジェクトの作成](#creating-projects)
- [プロジェクトの構築](#building-projects)
- [プロジェクトのデプロイ](#deploying-projects)
- [プロジェクトのテスト](#testing-projects)

## プロジェクトの作成

Liferay Workspaceは、Gradleスクリプトとプラグインを使用して実装されます。 そのため、IDEまたは開発ツールに統合できます。 また、LiferayのCLIユーティリティと連携して動作します。 標準ツールまたは[Blade CLI](../blade-cli/generating-projects-with-blade-cli.md)を使用してプロジェクトを作成できます。

## プロジェクトの構築

Liferay Workspaceには、LiferayのGradleプラグインがシームレスに組み込まれており、Liferayプロジェクトの構築をすぐに開始できます。 Gradleラッパーにはワークスペースが付属しているため、マシンにグローバルにインストールしなくてもGradleを使用できます。

ワークスペースはLiferayアプリケーションをビルドするように事前構成されているため、ビルド構成を作成するためにリポジトリーと依存関係を探す時間を節約できます。

## プロジェクトのデプロイ

GradleまたはBlade CLIを使用してLiferay Workspaceからプロジェクトを簡単にデプロイできます。つまり、これらのCLIツールをサポートするIDEからも簡単にデプロイできます。 また、ワークスペースには、コードをデバッグするためのLiferayランタイムを自動ダウンロードするメカニズムもあるため、デプロイとテストは簡単です。

## プロジェクトのテスト

Liferay Workspaceは、Dockerを使用して、開発、ユーザー受け入れテスト、および本番環境を構成し、コードをテストする環境をシミュレートするのに役立ちます。 環境はフォルダで区切られ、各フォルダは独自のデータベース、`portal-ext.properties`ファイル、Elasticsearch構成などを提供できます。 これらの構成は、Liferay DXPまたはCEのワークスペース制御のインストールをオーバーレイします。

[Blade CLI](../blade-cli/installing-and-updating-blade-cli.md)の有無にかかわらずワークスペースを使用できます。 引き続き読み進めることで、最初のLiferay Workspaceを[作成](./creating-a-liferay-workspace.md)する方法について学習できます。 
