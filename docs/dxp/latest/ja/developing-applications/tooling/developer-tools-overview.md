# 開発者ツールの概要

Liferayの開発者ツールは、軽量のCLIユーティリティからEclipseベースの本格的なIDEまで、あらゆる範囲で動作します。 これは、開発を始めたばかりの場合でも、長年コードを作成してきた場合でも、すべての開発者に役立つものです。 Liferayの開発ツールはすべて、Linux、Mac、およびWindowsプラットフォームで動作します。


<!-- JR: the mental image I have from all these tools (initially) is a variety of jigsaw puzzle pieces that I don't (yet) see how they piece together. Perhaps by presenting them with more structure, it can help guide a reader to build a better mental model for how all these tools relate to (and don't duplicate) one another. In some ideal world we have a nice diagram that visualizes how these tools relate to one another. One off the cuff organizational model:

# Title

(intro paragraph)

## General Development Tools

### Blade CLI

Blade CLi is x. Use it to do a, b, and c quickly and efficiently.

### Liferay Workspace

## Frontend Tooling

### Theme Generator

### JS Generator

## IDE Plugins

### IntelliJ Plugin

### Dev Studio
-->

[**Blade CLI**](./blade-cli/installing-and-updating-blade-cli.md) を使用すると、コマンドラインインターフェイスからスタンドアロンプロジェクトとLiferayワークスペースの両方を簡単に作成およびビルドできます。

**Liferayワークスペース** は、プロジェクトとその構成を保持するファイルシステム内の環境です。 Liferay DXPにデプロイし、Docker構成を作成して保存し、この単一の環境からすべてのDevOpsを実行できます。

**Liferayテーマジェネレーター** は、フロントエンドの開発者がテーマとレイアウトテンプレートを作成して、Liferay DXPベースのサイトの外観を定義するのに役立ちます。

**Liferay JS Generator** は、Liferay DXPに展開可能なJavaScriptベースのウィジェットのプロジェクトを作成します。

**Liferay IntelliJプラグイン** は、IntelliJ IDEAでLiferayワークスペースとスタンドアロンプロジェクトを使用するためのサポートを提供します。

**Liferay Developer Studio** は、Liferay開発の全範囲をサポートするEclipseベースのIDEです。

内部的には、Liferayワークスペースは、プロジェクトに適用できる多数のGradleおよびMavenプラグインを利用しています。 開始するためのプロジェクトテンプレートとサンプルもあります。

開発者ドキュメントで提供されるサンプルプロジェクトは、Liferay DXPの機能とAPIを学ぶのを助けるために意図的に単純化された小さなスタンドアロンプロジェクトです。 独自のプロジェクトを作成する準備ができたら、経験豊富な開発者がブレードCLIをインストールしてLiferayワークスペースを作成し、IntelliJユーザーがIntelliJプラグインをインストールし、他の全員がLiferay Developer Studioをインストールする必要があります。

Liferayの開発ツールをLiferayプロジェクトでお楽しみください\！
