# インストールの概要

この記事では、前提条件、必要なソフトウェアに関する情報、インストールとデプロイ、およびLiferay Commerceの保守方法について詳しく説明しています。 Liferay Commerceは、Liferay Digital Experience Platform上に構築されています。 したがって、Liferay Commerceをインストールする前に、まずLiferay DXPをインストールする必要があります。

## 前提条件

インストールする前に、システム要件とLiferay DXPデプロイドキュメンテーションを参照してください。

### システム要件

[Liferay Commerce 2.0互換性マトリックス](https://web.liferay.com/documents/14/21598941/Liferay+Commerce+2.0+Compatibility+Matrix/0ed97477-f5a7-40a6-b5ab-f00d5e01b75f)を参照して、サポートされているテクノロジーのリストを確認してください。

### Liferay DXPデプロイチェックリスト

Liferay DXPのアーキテクチャとパフォーマンスチューニングのガイドラインについては、[Liferay Digital Experience Platform 7.1デプロイチェックリスト](https://www.liferay.com/documents/10182/3292406/Liferay+DXP+7.1+Deployment+Checklist/cacaac23-9e02-411a-dcc9-adf86f95c513)をご覧ください。

## Liferay Commerceのインストール

Liferayは、Liferay Commerceのインストール方法をいくつか提供しています。

### Dockerイメージを使用する

Liferay Commerceの最新バージョンは、Dockerイメージとして利用できます。 詳細については、[Using Liferay Commerce Docker Image](./using-the-liferay-commerce-docker-image.md)を参照してください。

### バンドルを使用する

Liferay Commerceの最新バージョンは、[[Liferay Commerce Community Download]](https://commerce.liferay.dev/download)ページからバンドルとしてダウンロードできます。 詳細については、[Using the Liferay Commerce Tomcat Bundle](./using-the-liferay-commerce-tomcat-bundle.md)を参照してください。

### 既存のLiferay DXPインストールを使用する

既存のLiferay DXPインストールを使用する場合、ユーザーは`LPKG`を使用してLiferay Commerceをデプロイできます。 詳細については、[Deploying Liferay Commerce to an Existing Installation](./deploying-liferay-commerce-to-an-existing-liferay-installation.md)を参照してください。

### Commerce Enterpriseのアクティベーション

Liferay Commerce Enterpriseを購入した加入者は、[ヘルプセンター](https://customer.liferay.com/downloads)から`LPKG`をダウンロードできます。 Liferay Commerce Enterpriseをアクティブ化する方法については、[Activating Liferay Commerce Enterprise](./activating-liferay-commerce-enterprise.md)を参照してください。

## Liferay Commerceの管理

### アップグレードと更新

最新の機能とバグ修正を含む定期的な更新とアップグレードがあります。

  - [Maintenance Versions](../../get-help/commerce-enterprise-support/liferay-commerce-fix-delivery-method.md)
  - [Upgrading from Liferay Commerce 1.1.x](./upgrading-from-liferay-commerce-1.1.x.md)

## 追加情報

  - [Liferay Digital Experience Platform Performance](https://www.liferay.com/documents/10182/3292406/Liferay+DXP+Performance+-+Benchmark+Study+of+Liferay+DXP+7.1/fe7d4cd2-2efc-b5cc-9680-825ec9bad5be)
  - [Deploying Liferay DXP Using Docker](https://www.liferay.com/documents/10182/1645493/Deploying%20Liferay%20DXP%20Using%20Docker)
  - [Activating a Marketplace App Through a Proxy Server](https://help.liferay.com/hc/en-us/articles/360018427391)
