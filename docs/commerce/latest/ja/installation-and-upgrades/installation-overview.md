# インストールの概要

Liferay Commerceは、Liferay DXP上に構築されたデジタルコマースプラットフォームです。 この記事では、Liferay Commerceをインストール、展開、および保守するための前提条件と利用可能なオプションについて概説します。

## 前提条件

インストールをスムーズに行うために、事前に互換性マトリックスと関連するインストール資料を確認してください。

* サポートされているテクノロジーのリストについては、[Liferay DXP 7.3 および Commerce 3.0 互換性マトリクス](https://help.liferay.com/hc/en-us/articles/360049238151) をご覧ください。<!-- * See the \[Liferay DXP 7.3 Deployment Checklist\]() for information about Liferay DXP architecture and performance tuning guidelines. -->## Liferay Commerceから

Liferay Commerce 3.0は、Liferay Portal 7.3 CE GA6とLiferay DXP 7.3 GA1がバンドルされています。 コミュニティー版には、基本的なバージョンのCommerceがバンドルされており、エンタープライズ版には、フル機能のエンタープライズ Commerceがバンドルされています。 CE版、エンタープライズ版ともに、Dockerイメージやダウンロード可能なバンドルとして提供されています。

| インストール方法                                                                                                                             | 目的                                                                                   |
| ------------------------------------------------------------------------------------------------------------------------------------ | ------------------------------------------------------------------------------------ |
| [Dockerイメージ](https://learn.liferay.com/dxp/latest/en/getting-started/starting-with-a-docker-image.html#get-started-with-liferay)     | Dockerイメージから始めるのが最も早くLiferay Commerceを使い始められる方法です。                                   |
| [バンドル](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/installing-liferay/installing-a-liferay-tomcat-bundle.html) | Liferay Commerceには、アプリケーションサーバーがあらかじめバンドルされています。 Liferayインストールの実行を開始するための最も一般的な方法です。 |

Liferay Portalのバンドルとイメージには、コマース特有の追加のインストール手順は必要ありません。 コマースの基本機能はアクティベートされており、起動時にすぐに使用することができます。

一方、DXPのインストールでは、基本的なCommerceとエンタープライズCommerceの両方のモジュールがデフォルトで無効化されているため、使用するためには有効にする必要があります。 DXPとCommerceの両方のライセンスがデプロイされ、検証されると、すべてのCommerceモジュールが開始され、すぐに利用できるようになります。よって、サーバーを再起動させる必要はありません。 詳しくは[Activating Liferay Commerce Enterprise](./activating-liferay-commerce-enterprise.md)を参照してください。

## Liferay Commerceの管理

定期的なメンテナンスアップデートと商品アップグレードを利用して、Liferay Commerceのエクスペリエンスの品質を保証および強化できます。 詳しくは以下の記事を参照してください。

* [メンテナンスバージョン](./maintenance-versions.md)
* [Liferay Commerceのアップグレード](./upgrading-liferay-commerce.md)

## Liferay Commerce 2.1以前

Liferay Commerceの以前のリリースでは、追加のインストール手順が必要でしたが、Commerce 3.x以降ではその必要はありません。

### 前提条件

Liferay Commerce 2.1以下をインストールする前に、お使いのインストールバージョンに対応するシステム要件とLiferay DXP導入チェックリストを確認してください。

* サポートされるテクノロジーのリスト： [Liferay Commerce 2.0 互換性マトリクス](https://web.liferay.com/documents/14/21598941/Liferay+Commerce+2.0+Compatibility+Matrix/0ed97477-f5a7-40a6-b5ab-f00d5e01b75f)
* [Liferay DXP 7.1 デプロイチェックリスト](https://www.liferay.com/documents/10182/3292406/Liferay+DXP+7.1+Deployment+Checklist/cacaac23-9e02-411a-dcc9-adf86f95c513)
* [Liferay DXP 7.2 デプロイチェックリスト](https://www.liferay.com/documents/10182/3292406/Liferay+DXP+7.2+Deployment+Checklist.pdf/22dee290-6b06-0bdc-aa89-30bb88d1d42e?t=1566483298239)

### Dockerイメージを使ったインストール

Dockerを使用してLiferay Commerceをインストールします。 詳細は、[Using Liferay Commerce Docker Image](./installing-commerce-2.1-and-below/using-the-liferay-commerce-docker-image.md)を参照してください。

### バンドルを使ったインストール

[Liferay Community Downloads](https://commerce.liferay.dev/download) ページのバンドルを使用してLiferay Commerceをインストールします。 詳細は、[Using the Liferay Commerce Tomcat Bundle](./installing-commerce-2.1-and-below/using-the-liferay-commerce-tomcat-bundle.md)を参照してください。

### 既存のLiferay DXPインストールを利用したインストール

`LPKG` ファイルを既存のLiferay DXPインストールに展開して、Liferay Commerceをインストールします。 詳細は、[Deploying Liferay Commerce to an Existing Installation](./installing-commerce-2.1-and-below/deploying-liferay-commerce-to-an-existing-liferay-installation.md)を参照してください。

## 追加情報

* [Liferay Digital Experience Platform Performance](https://www.liferay.com/documents/10182/3292406/Liferay+DXP+Performance+-+Benchmark+Study+of+Liferay+DXP+7.1/fe7d4cd2-2efc-b5cc-9680-825ec9bad5be)
* [プロキシサーバーを介したMarketplaceアプリのアクティブ化](https://help.liferay.com/hc/en-us/articles/360018427391)
