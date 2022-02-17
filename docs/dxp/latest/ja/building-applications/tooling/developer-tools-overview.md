# 開発者ツールの概要

Liferayの開発者ツールは、標準的なビルドスクリプトから、軽量のCLIユーティリティやEclipseベースの本格的なIDEまで、あらゆる範囲で動作します。 これは、開発を始めたばかりの場合でも、長年コードを作成してきた場合でも、すべての開発者に役立つものです。 Liferayの開発ツールはすべて、Linux、Mac、およびWindowsプラットフォームで動作します。

<a name="lightweight-tools" />

## 軽量ツール

経験豊富な開発者の方や、Liferay開発を既存のツールセットに統合したい場合は、CLIおよびファイルシステムベースのツールが役立ちます。

[**Liferay Workspace**](./liferay-workspace/what-is-liferay-workspace.md)は、プロジェクトとその構成を保持するGradleベースの環境です。 Liferay DXPにデプロイし、Docker構成を作成して保存し、この単一の環境からすべてのDevOpsを実行できます。 あらゆるストライプの開発者が既存のツールやワークフローと統合できる柔軟性を備えています。

[**Blade CLI**](./blade-cli/installing-and-updating-blade-cli.md) を使用すると、コマンドラインインターフェイスからプロジェクトとLiferayワークスペースを簡単に作成およびビルドできます。 経験豊富な開発者は、この小さくてシンプルなCLIツールを使用して、Liferayプロジェクトを既存のワークフローに統合できます。

<a name="tools-for-front-end-developers" />

## フロントエンド開発者向けのツール

フロントエンド開発者の場合、2つのCLIベースのツールを使用して、Liferayのルックアンドフィールを変換するテーマプロジェクトとウィジェットプロジェクトを作成できます。

**Liferayテーマジェネレーター** は、フロントエンドの開発者がテーマとレイアウトテンプレートを作成して、Liferay DXPベースのサイトの外観を定義するのに役立ちます。

**Liferay JS Generator** は、Liferay DXPに展開可能なJavaScriptベースのウィジェットのプロジェクトを作成します。

<a name="ides-and-plugins" />

## IDEとプラグイン

JetBrainsのIntelliJIDEAまたはEclipseを使用する場合、Liferayにはそれらと統合するグラフィカルツールがあります。

**Liferay IntelliJプラグイン** は、IntelliJ IDEAでLiferay Workspaceとスタンドアロンプロジェクトを使用するためのサポートを提供します。

**Liferay Developer Studio** は、Liferay開発の全範囲をサポートするEclipseベースのIDEです。

内部的には、Liferay Workspaceは、プロジェクトに適用できる多数のGradleおよびMavenプラグインを利用しています。 開始するためのプロジェクトテンプレートとサンプルもあります。

開発者向けドキュメンテーションで提供されているサンプルプロジェクトのいずれかに従っている場合は、Liferay Workspaceをすでに使用しています。 これで、独自のプロジェクトを作成するための機能の全範囲を学ぶことができます。 そこから、Blade CLIを使用してターミナルからプロジェクトを管理できます。 IntelliJユーザーはIntelliJプラグインを使用できます。必要なものがすべて含まれるフル機能の環境が必要な場合は、Liferay Developer Studioをインストールしてください。

Liferayの開発ツールをLiferayプロジェクトで使用していただければ幸いです。
