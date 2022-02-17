# Liferayのセットアップ

```{toctree}
:maxdepth: 3

setting-up-liferay/activating-liferay-dxp.md
../system-administration/configuring-liferay/virtual-instances/instance-configuration.md
setting-up-liferay/initial-instance-localization.md
setting-up-liferay/configuring-mail.md
../system-administration/configuring-liferay/virtual-instances/users.md
../system-administration/file_storage.rst
../system-administration/file-storage/enabling-antivirus-scanning-for-uploaded-files.md
../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md
securing_liferay.rst
maintaining-a-liferay-dxp-installation/backing-up.md
../system-administration/installing_and_managing_apps.rst
setting-up-liferay/using-a-cdn.md
setting-up-liferay/clustering-for-high-availability.md
setting-up-liferay/tuning-liferay.md
setting-up-liferay/tuning-your-jvm.md
```

[Liferayをインストール](./installing_liferay.html)した後、ニーズに合わせて設定します。 ここでは、一般的なセットアップ作業を説明します。

## セットアップタスク

* [Liferayの有効化](#activate-liferay)（変更通知を受け取り（購読）が必要）
* [インスタンスの設定](#configure-your-instance)
* [インスタンスのローカライズ](#localize-your-instance)
* [メールの設定](#configure-mail)
* [ユーザーの設定](#configure-users)
* [ファイルストレージの設定](#configure-file-storage)
* [検索エンジンのインストール](#install-a-search-engine)
* [Liferayの保護](#securre-liferay)
* [バックアップの設定](#configure-backups)

**その他のセットアップ作業**

* [マーケットプレイスからアプリをインストールする](#install-apps-from-marketplace)
* [ロールと権限の設定](#configure-roles-and-permissions)
* [カスタムフィールドの追加](#add-custom-fields)
* [既存のシステムとの統合](#integrate-with-existing-systems)
* [メディアファイルのプレビュー設定](#configure-media-file-previews)
* [CDNの設定](#configure-a-cdn)
* [高可用性の設定](#configure-high-availability)
* [Liferayの調整](#tune-liferay)

上記の各タスクは、以下の説明にリンクしており、該当する記事への参照も含まれています。 最初のタスクは、本番用のLiferayインスタンスには不可欠です。 お使いのシステムにも適用される*その他のセットアップタスク*を調べてください。 タスクを完了するときは、必ず上記のチェックリストを見直すようにしてください。

## Liferayの起動

`サブスクライバー`

Liferay DXPを使用している場合は、[アクティベーションキー](./setting-up-liferay/activating-liferay-dxp.md)を適用します。

## インスタンスの設定

バーチャルインスタンスの基本的な外観、連絡先情報、利用規約、ランディングページやログアウトページなどの必須ページを設定します。 [Instance Configuration](../system-administration/configuring-liferay/virtual-instances/instance-configuration.md) explains how to do it all.

## インスタンスのローカライズ

インスタンスのロケールとタイムゾーンを設定します。 詳細については、[Initial Instance Localization](./setting-up-liferay/initial-instance-localization.md)を参照してください。

## メールの設定

[Set up a mail server](./setting-up-liferay/configuring-mail/connecting-to-a-mail-server.md) to communicate with Users. Then configure your [email settings](../system-administration/configuring-liferay/virtual-instances/email-settings.md), including your email sender and message templates for email verifications, password resets, and password changes.

## ユーザーの設定

[ユーザーを把握](../users-and-permissions/users/understanding-users.md)したら、インスタンスに合わせて設定します。

* ユーザーフィールドの有効化／無効化
* 必要な[カスタムユーザーフィールド](../users-and-permissions/users/adding-custom-fields-to-users.md)の追加
* ユーザー以外の人にもアカウント作成を許可するかどうかの指定
* [ユーザー認証](./securing-liferay/authentication-basics.md)の定義
* ユーザーを特定のサイト、[ロール](../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)、および[ユーザーグループ](../users-and-permissions/user-groups.md)に自動的に関連付けるための設定。

詳細は、[Users](../system-administration/configuring-liferay/virtual-instances/users.md)、[User Authentication](../system-administration/configuring-liferay/virtual-instances/user-authentication.md)、および[Adding Custom Fields For Users](../users-and-permissions/users/adding-custom-fields-to-users.md)を参照してください。

## ファイルストレージの設定

[ドキュメントとメディア](../content-authoring-and-management/documents-and-media/documents-and-media-overview.md)、添付ファイルおよびコンテンツへの画像の埋め込みを使用するには、ファイルストレージが必要です。  設定するには、[ファイルストレージ](../system-administration/file-storage/configuring-file-storage.md)にアクセスします。

ファイルストレージを設定した後、ウイルス対策のファイルスキャンを有効にすることを検討してください。 方法については、[Enabling Antivirus Scanning for Uploaded Files](../system-administration/file-storage/enabling-antivirus-scanning-for-uploaded-files.md)を参照してください。

## 検索エンジンのインストール

ユーザーがサイト内を検索できるようにします。 Liferay Enterprise Searchのような検索エンジンは、結果を素早く返してくれます。 [Installing a Search Engine](../using-search/installing-and-upgrading-a-search-engine/installing-a-search-engine.md)をご覧ください。

## Liferayの保護

Liferayを保護することはとても重要です。 詳しくは、[Securing Liferay](./securing-liferay/securing-liferay.md)をご覧ください。

## バックアップの設定

仮想インスタンスを開発する際には、必ずバックアップを取ってください。 ガイダンスについては、[Backing Up](./maintaining-a-liferay-dxp-installation/backing-up.md)を参照してください。

## その他のセットアップ作業

上記のようなタスクは、一般的に（必ずしもそうではありませんが）最初に完了します。 状況に応じて、次のタスクを適宜実行する必要があります。

## マーケットプレイスからアプリをインストールする

[Liferayマーケットプレイス](https://web.liferay.com/marketplace)で利用可能な[テーマ](../getting-started/changing-your-sites-appearance.md)、コネクタ、およびあらゆる種類のアプリケーションを使用してサイトを改善します。 [アプリのインストールと管理](../system-administration/installing-and-managing-apps/getting-started/installing-and-managing-apps.md)がこれまでになく簡単になりました。

## ロールと権限の設定

[ロール](../users-and-permissions/roles-and-permissions/understanding-roles-and-permissions.md)を使用して、すべてのユーザーを分類します。 インスタンスとサイトでユーザーが実行するアクティビティの[権限](../users-and-permissions/roles-and-permissions/defining-role-permissions.md)を定義します。

## カスタムフィールドの追加

インスタンスやアプリケーションのエンティティは、追加フィールドでカスタマイズできます。 これには、ユーザーのためのフィールドの追加も含まれます。 詳細については、[Adding Custom Fields](../system-administration/configuring-liferay/adding-custom-fields.md)および[Adding Custom Fields For Users](../users-and-permissions/users/adding-custom-fields-to-users.md)を参照してください。

## 既存のシステムとの統合

Liferayはいくつかの他のシステムと統合することができます。 例えば、LDAPなどのディレクトリを使用している場合、そこからユーザーをインポートすることができます。 プロセスについては、[Connecting to an LDAP Directory](../users-and-permissions/connecting-to-a-user-directory/connecting-to-an-ldap-directory.md)で説明しています。 必要な場合は、このサイトで統合について検索してください。

## メディアファイルのプレビュー設定

サイト内の画像、動画、音声ファイルの外部サービスを設定します。 方法については、[Configuring External Services](../system-administration/using-the-server-administration-panel/configuring-external-services.md)を参照してください。

## CDNの設定

コンテンツ配信ネットワーク（CDN）を介して、静的コンテンツをより高速に配信します。 詳細は、[Using a CDN](./setting-up-liferay/using-a-cdn.md)を参照してください。

## 高可用性の設定

サイトのダウンタイムをなくす、あるいは最小限に抑える必要があります。 サーバーが故障したり、メンテナンスのために停止する必要がある場合、リクエストを処理するために他のサーバーを用意することが重要です。 Liferay、検索エンジン、その他のコンポーネントに複数のサーバーを構成することで、サイトの可用性を最大限に高めることができます。 サーバーをクラスター化する方法については、[Clustering for High Availability](./setting-up-liferay/clustering-for-high-availability.md)をご覧ください。

## Liferayの調整

LiferayのJVM、接続プールなどを最適なパフォーマンスに調整します。 詳細については、[Tuning Liferay](./setting-up-liferay/tuning-liferay.md)および[Tuning Your JVM](./setting-up-liferay/tuning-your-jvm.md)を参照してください。

## 次のステップ

Liferay DXPを使用していて、まだ[アクティブ化](./setting-up-liferay/activating-liferay-dxp.md)していない場合は、最初にアクティブ化してください。 次に、[インスタンス設定](../system-administration/configuring-liferay/virtual-instances/instance-configuration.md)と上記の他の[セットアップタスク](#setup-tasks)に進みます。

```{important}
本番環境に入る前に、インストールとデータをバックアップするためのプロセスを設定します。 詳しくは、[Backing Up](./maintaining-a-liferay-dxp-installation/backing-up.md)を参照してください。
```
