# サイト設定UIリファレンス

> Liferay DXP 7.4では、サイト設定アプリケーションが大幅に変更されています。 以前のバージョンの詳細は、 [Liferay DXP7.2および7.3のサイト設定UIリファレンス ](#site-setting-ui-reference-in-liferay-dxp-7-2-and-7-3) を参照してください。

Liferay DXP 7.4以降、サイト設定アプリケーションのレイアウトは、インスタンス設定やシステム設定などの他の設定エリアと一致しています。

## サイト設定アプリケーションへのアクセス

1. サイト管理rarr; ［**Configuration**］ &rarr; ［**Site Settings**］ へ行きます。
1. 設定項目をクリックすると、その設定が表示されます。

以下のような設定エリアがあります。

- [コマース](#commerce)
- [プラットフォーム](#platform)
- [コンテンツとデータ](#content-and-data)
- [その他](#other)

![サイト設定は4つのエリアに分かれています。](./site-settings-ui-reference/images/20.png)

## コマース

デフォルトでは、Commerceエリアには以下の設定が含まれています。

- カタログ
- 順番
- 支払
- 出荷先

ここでは、サイトスコープでのCommerce設定を行うことができます。 コマースの設定の詳細は、 [のLiferay Commerce設定の概要](https://learn.liferay.com/commerce/latest/ja/store-administration/liferay-commerce-configuration-overview.html) を参照してください。

![コマースのデフォルトサイト設定](./site-settings-ui-reference/images/21.png)

## プラットフォーム

デフォルトでは、プラットフォームエリアには以下の設定が含まれています。

- [分析](#analytics)
- [ローカライズ](#localization)
- [サイト設定](#site-configuration)
- [サードパーティー](#third-party)
- [サードパーティアプリケーション](#third-party-applications)
- [ユーザー](#users)

### 分析

これらの設定を使用して、お客様のサイトのGoogle Analyticsオプションを設定します。 また、Piwik Analyticsのトラッキングコードを設定することもできます。

```{note}
Piwikの設定では、スクリプトの開始タグと終了タグを含む完全なスクリプトコードを入力します。
```

別のアナリティクスサービスが必要な場合は、追加できます。 異なるアナリティクスサービスのための追加フィールドを追加する方法については、 [新しいアナリティクスサービスの追加](./adding-a-new-analytics-service.md) を参照してください。

### ローカライズ

ここでは、インストールのデフォルト言語オプションを使用するか、サイト用に独自の言語を定義するかを選択できます。 サイトの言語オプションの設定についての詳細は、 [サイトのローカライズ](../site-settings/site-localization.md)をご覧ください。

![サイトの言語オプションを設定するには、「ローカライズ」設定を使用します。](./site-settings-ui-reference/images/22.png)

### サイト設定

デフォルトでは、［サイト設定］には［詳細］と［サイトURL］のセクションがあります。

#### 詳細

| 設定               | 説明                                                                                                                                      |
|:---------------- |:--------------------------------------------------------------------------------------------------------------------------------------- |
| サイトID            | サイトの作成時に自動的に生成される固有の番号。 このIDは永続的であり、変更できません。                                                                                            |
| 説明               | このフィールドは、サイトの目的を説明するために使用します。                                                                                                           |
| 親サイト             | 親サイトを選択して、子サイトとして指定する。 詳細は、[サイトの階層](../building-sites/site-hierarchies.md)を参照してください。                                                    |
| メンバーシップタイプ       | サイトのメンバーシップをどの程度制限するかを決定します。 詳細は、[サイトのメンバーシップタイプを変更する](../site-settings/site-users/changing-site-membership-type.md)を参照してください。          |
| 手動メンバーシップ管理を許可する | メンバーを手動でサイトに追加・削除することを許可するかどうかを決定します。 詳細は、[サイトのメンバーシップタイプを変更する](../site-settings/site-users/changing-site-membership-type.md)を参照してください。 |

#### サイトURL

| 設定           | 説明                                                                                                                                                                                |
|:------------ |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| フレンドリURL     | サイトの公開・非公開に関わらず、カスタムURLを設定することができます。 詳細は、[サイトのフレンドリーURLの設定](../site-settings/managing-site-urls/configuring-your-sites-friendly-url.md)を参照してください。                                 |
| 公開ページと非公開ページ | パブリックおよびプライベートのバーチャルホストを設定して、サイトのパブリックページおよびプライベートページにマッピングすることができます。 詳細は、 [仮想ホストサイトURLの設定](../site-settings/managing-site-urls/configuring-virtual-hosts-site-urls.md)を参照してください。 |

### サードパーティー

Liferay CommerceのPunchOut2Go Site設定を行います。 詳細は、 [Liferay Commerce Connector to PunchOut2Goのリファレンスのガイド](https://learn.liferay.com/commerce/latest/en/add-ons-and-connectors/liferay-commerce-connector-to-punchout2go-reference-guide.html) を参照してください。

### サードパーティアプリケーション

デフォルトでは、サードパーティアプリケーションには、マップとGoogle Placesのセクションが含まれています。

| セクション       | 説明                                                                                                                                                                                                                    |
|:----------- |:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| アセットの自動タグ付け | 有効にすると、インスタンススコープで構成されたプロバイダによってアセットが自動タグ付けされます。 詳細は、[アセットの自動タグ付けの設定](../../content-authoring-and-management/tags-and-categories/auto-tagging/configuring-asset-auto-tagging.md)を参照してください。                            |
| カテゴリの設定     | このセクションでは、お客様のサイトを分類します。 この設定は、本サイトで利用可能なカテゴリーやタグには影響しません。 カテゴリとタグの使用の詳細は、[カテゴリとタグを使用したコンテンツの整理](../../content-authoring-and-management/tags-and-categories/organizing-content-with-categories-and-tags.md)を参照してください。 |

### ユーザー

デフォルトでは、ユーザーセクションには「デフォルトユーザーの関連付け」の設定が含まれており、ここで新しいサイトメンバーのデフォルトのロールとチームを設定することができます。 詳細は、[サイトメンバーの役割とチームのデフォルトの設定](../site-settings/site-users/configuring-role-and-team-defaults-for-site-members.md)を参照してください。

## コンテンツとデータ

デフォルトでは、［コンテンツとデータ］エリアには以下の設定が含まれています。

- [アセット](#assets)
- [チャット機能](#click-to-chat)
- [コミュニティーツール](#community-tools)
- [デジタル署名](#digital-signature)
- [ドキュメントとメディア](#documents-and-media)
- [掲示板](#message-boards)
- [ページ](#pages)
- [ゴミ箱](#recycle-bin)
- [報告](#reports)
- [共有](#sharing)

### アセット

| セクション                | 説明                                                                                                                                                                                                                                     |
|:-------------------- |:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 地図                   | サイト内でジオロカライズされたアセットを表示する際に使用するマップAPIプロバイダーを選択します。 ドキュメント、ウェブコンテンツの記事、DDLレコードなどに対して、ジオローカライズされたアセットを表示することができます。 詳しくは、 [Geo-locating Assets](../../content-authoring-and-management/tags-and-categories/geolocating-assets.md) をご覧ください。 |
| Google Places API キー | Google Places API Keyを設定します。                                                                                                                                                                                                           |

### チャット機能

> 対応可能：Liferay 7.4 GA2以降

Liferay DXPは多くのライブサポートチャットプラットフォームと統合できます。 サイトスコープでの設定は、インスタンススコープでのClick to Chat設定に依存します。 詳細は、[自動ライブチャットシステムの有効化](../personalizing-site-experience/enabling-automated-live-chat-systems/enabling-automated-live-chat-systems.md)を参照してください。

この設定の構成タイプは、インスタンススコープの［サイト設定のストラテジー］テキストで確認できます。

![インスタンススコープの［サイト設定ストラテジー］で設定の種類を確認します。](./site-settings-ui-reference/images/23.png)

```{note}
インスタンススコープでの設定によっては、サイトスコープでの設定を更新できない場合があります。 インスタンスの設定を確認するには、［サイトメニュー］ → ［コントロールパネル］ →［インスタンスの設定］→［コンテンツとデータ］→［チャット］をクリックしてください。
```

### コミュニティーツール

| セクション             | 説明                                                                                                                                                                            |
|:----------------- |:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| 自分について書かれた記事/コメント | サイト内のアプリケーションで、ユーザーが他のユーザーに言及することを許可するかどうかを決定します。 詳しくは、 [メンションを設定する](../../collaboration-and-social/notifications-and-requests/user-guide/configuring-mentions.md) を参照してください。 |
| 評価                | ここでは、さまざまなサイトアプリケーションに使用されるレーティングのタイプを選択できます。 詳しくは、 [コンテンツの評価タイプを設定する](../site-settings/site-content-configurations/configuring-content-rating-type.md) を参照してください。            |

### デジタル署名

Liferay DXPとDocuSignを統合し、電子署名を管理することができます。 サイトスコープでの構成は、インスタンススコープでのデジタル署名の構成に依存します。 インスタンスの構成は、［Site Settings Strategy］のテキストで確認できます。

![インスタンススコープの［サイト設定のストラテジー］で設定の種類を確認します。](./site-settings-ui-reference/images/24.png)

```{note}
インスタンススコープでのデジタル署名の構成によっては、サイトスコープでの構成を更新できない場合があります。 インスタンスの設定を確認するには、［サイトメニュー］ →［コントロールパネル］ →［インスタンスの設定］ →［コンテンツとデータ］→［デジタル署名］と進みます。 
```

### ドキュメントとメディア

有効にすると、表示権限を持つユーザーがサイトのドキュメントライブラリファイルとフォルダを閲覧できます。 詳細は、[サイトのドキュメント内容の保護](../site-settings/site-content-configurations/securing-site-documents-content.md)を参照してください。

### 掲示板

このオプションを使用すると、あらかじめ定義されたメッセージ数の後に、メッセージボード上のユーザーメッセージの自動モデレーションを有効にすることができます。

![メッセージボードの自動モデレーションを設定します。](./site-settings-ui-reference/images/25.png)

### ページ

| セクション                                         | 説明                                                                                                                                                                                                                                                          |
|:--------------------------------------------- |:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Google PageSpeed Insights (Liferay DXP 7.4＋ ) | Liferay DXPでGoogle PageSpeed Insightの統合を有効にします。 このオプションを使用すると、ページのアクセシビリティの問題を監査し、修正のための提案を確認することができます。 詳しくは、 [ページのSEOとアクセシビリティを分析する](../../content-authoring-and-management/content-performance-tool/analyzing-seo-and-accessibility-on-pages.md) をご覧ください。 |
| Open Graph                                    | これらのタグは、ページのメタデータを定義し、Facebook、Slack、TwitterなどのOpen Graphプロトコルをサポートするアプリケーションで共有する際に、サイトのコンテンツを魅力的に表現します。 Open Graphを有効にすると、`<meta>`タグがサイトのページの`head`に埋め込まれます。 詳細は、[Open Graphの設定](./configuring-open-graph.md)を参照してください。                             |
| ページ                                           | サイトの公開と非公開のページを表示します。 このセクションでは、サイトテンプレートが存在する場合、そのテンプレートからの変更の伝搬を設定することもできます。 詳細は、[サイトテンプレートを使用したサイトの構築](../../../en/site-building/building-sites/building-sites-with-site-templates.md)を参照してください。                                                         |

### ゴミ箱

サイトのゴミ箱の設定 アセットが自動削除されるまでの、ごみ箱に残っている時間を指定できます。 デフォルトでは、リサイクルアイテムの最大経過年数は43200分（30日）です。 詳細は、
 サイトのアセットゴミ箱を設定する0>を参照してください。</p> 



### 報告

レポートグループサービス機能の設定



### 共有

| セクション   | 説明                                                                                                                                                                                                                                               |
|:------- |:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| コンテンツ共有 | このオプションを有効にすると、子サイトでこのサイトのコンテンツ（構造、テンプレート、カテゴリー、ウィジェットテンプレートなど）を表示できるようになります。 このオプションを無効にすると、すべての子サイトのコンテンツ共有が直ちに解除されます。 詳細は、[サイト間でのコンテンツ共用の管理](../site-settings/site-content-configurations/managing-content-sharing-across-sites.md)を参照してください。 |
| 共有      | 有効にすると、ユーザー同士でアイテムを共有することができます。 詳細は、[共有の有効化と設定](../../content-authoring-and-management/documents-and-media/publishing-and-sharing/managing-document-access/enabling-and-configuring-sharing.md)を参照してください。                                        |




## その他

デフォルトでは、Otherエリアには以下の設定が含まれています。

| セクション             | 説明                                                                                                                                                                       |
|:----------------- |:------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| その他               | お客様がサイトに定義したカスタムサイト設定があれば、それも含みます。 詳細は、[設定へのアクセス](../../building-applications/core-frameworks/configuration-framework/setting-and-accessing-configurations.md)を参照してください。 |
| 税金                | Liferay Commerceの「Remote Commerce Tax Configuration」の設定を行います。 詳細は、 [Liferay Commerce](https://learn.liferay.com/commerce/latest/ja/index.html) のドキュメントを参照してください。         |
| category.tax-rate | Liferay Commerce Avalara統合の設定を行います。 詳細は、 [Liferay Commerce](https://learn.liferay.com/commerce/latest/ja/index.html) のドキュメントを参照してください。                                   |




## Liferay DXP 7.2および7.3のサイト設定UIリファレンス

サイトの設定を表示するには、サイトメニュー（![Site Menu](../../images/icon-product-menu.png)）を開き、 ［**Configuration**］ → ［**Settings**］ に移動します。ここに、次のタブが表示されます。

* [一般](#general)
* [ソーシャル](#social)
* [言語設定](#language)
* [詳細設定](#advanced)

![サイト設定は4つのタブで構成されています。](./site-settings-ui-reference/images/01.png)



```{note}
これらの設定の多くは、ユーザーのロケールに応じた翻訳を提供するためにローカライズすることができます。 詳しくは、 [Introduction to Localization](https://help.liferay.com/hc/ja/articles/360028746672-Introduction-to-Localization) および [Modifying Localizable Site Fields](./site-localization.md#modifying-localizable-site-fields) をご覧ください。
```




### 一般

一般設定は、サイトのメンバーシップタイプなどのコア設定から、ドキュメントとメディアのインデックス作成オプションなどの詳細まで多岐にわたります。 タブのコンテンツは、詳細、ページ、カテゴリ設定、サイトURL、ドキュメントとメディア、Open Graph、アセットの自動タグ付け、共有、およびカスタムフィールドのサブセクションで構成されています。



#### 詳細

**サイトID** : サイトの作成時に自動的に生成される一意の番号。 このIDは永続的であり、変更できません。

**Name** ： ［**Name**］ フィールドを使用してサイトのタイトルを設定します。 このタイトルは、ブラウザのタイトルバーと各サイトページのヘッダに表示されます。 ［**言語フラグ**］ ボタンを使用してサイトの名前をローカライズすることもできます。

**説明** ： ［**説明**］ フィールドを使用して、サイトの目的を説明します。 ［**言語フラグ**］ ボタンを使用してサイトの説明をローカライズすることもできます。

**有効** ：サイトが ［**有効**］ か ［**Inactive**］ かを判別します。 非アクティブの間、ユーザーはサイトにアクセスできませんが、必要に応じて再アクティブ化できます。

**メンバーシップタイプ** ： **メンバーシップタイプ** によって、サイトのメンバーシップの制限度が決まります。 詳細は、[サイトのメンバーシップタイプを変更する](./site-users/changing-site-membership-type.md)を参照してください。

**手動メンバーシップ管理を許可する** ：メンバーをサイトに手動で追加したりサイトから削除することを許可するかどうかを決定します。 サイトのメンバーシップがメンバーシップポリシーによって自動的に処理される場合は、この設定を無効にすることができます。 詳細は、[サイトのメンバーシップポリシーの管理](./site-users/changing-site-membership-type.md)を参照してください。

**親サイト** ：親サイトを選択して、サイトをサブサイトとして指定します。 詳細は、[サイトの階層](../building-sites/site-hierarchies.md)を参照してください。

**Limit Membership to Parent Site Members** ：サブサイトのメンバーシップをその親サイトのメンバーに制限するかどうかを決定します。 このオプションは、サブサイトにのみ表示されます。



#### ページ

サイトの公開ページと非公開ページが存在する場合はそれを表示し、選択したサイトテンプレートからのプロパゲーションの変更を有効または無効にします。 ページが存在しない場合は、テンプレートを使用してページを作成するためのサイトテンプレートセレクターが表示されます。

![［ページ］セクションでは、サイトの公開ページと非公開ページを表示できます。](./site-settings-ui-reference/images/03.png)



### カスタムフィールド

ページに定義したカスタムフィールドを表示および設定します。 これらのフィールドを使用して、作成者、作成日、位置情報などのページメタデータを設定できます。 このセクションは、サイトのカスタムフィールドを設定した場合にのみ表示されます。 詳細は、 [Custom Fields](../../system-administration/configuring-liferay/adding-custom-fields.md)を参照してください。



#### カテゴリの設定

カテゴリとタグを使用してサイトのコンテンツを分類し、ユーザーがより簡単に見つけられるようにします。 タグとカテゴリの使用の詳細は、[カテゴリとタグを使用したコンテンツの整理](../../content-authoring-and-management/tags-and-categories/organizing-content-with-categories-and-tags.md)を参照してください。



#### サイトURL

**フレンドリURL** : サイトの公開ページと非公開ページの両方にカスタムURLを設定します。 詳細は、[サイトのフレンドリーURLの設定](./managing-site-urls/configuring-your-sites-friendly-url.md)を参照してください。

**公開および非公開仮想ホスト** ：サイトの公開ページおよび非公開ページにマップする公開および非公開のバーチャルホストを設定します。 詳細は、 [仮想ホストサイトURLの設定](./managing-site-urls/configuring-virtual-hosts-site-urls.md)を参照してください。

![［サイトURL］セクションでは、サイトのフレンドリURLと、公開ページと非公開ページのバーチャルホストを設定できます。](./site-settings-ui-reference/images/05.png)



#### ドキュメントとメディア

サイトのディレクトリのインデックスを有効にするかどうかを決定します。 有効にすると、表示権限を持つユーザーがサイトのドキュメントライブラリファイルとフォルダを閲覧できます。



#### サイトテンプレート

**サイトテンプレート** を使用してサイトを作成した場合、テンプレートを継承したページをユーザーが変更できるかどうかとともに、サイトがここに表示されます。 サイトテンプレートと独自のテンプレートを作成する方法の詳細は、[テンプレートからサイトを構築する](../building-sites/building-sites-with-site-templates.md)を参照してください。

![ページの選択されたテンプレートを表示します。](./site-settings-ui-reference/images/07.png)



#### Open Graph

**Open Graphを有効にする** ：サイトのページの`<head>`に [Open Graph](https://ogp.me/) `<meta>`タグを埋め込むかどうかを決定します。 これらのタグは、ページのメタデータを定義し、Facebook、Slack、TwitterなどのOpen Graphプロトコルをサポートするアプリケーションで共有する際に、サイトのコンテンツを魅力的に表現します。

**Image** ：［Image］フィールドを使用して、ページの次のOpen Graph `<meta>`プロパティを定義します。 



   ```html
      <meta property="og:image" content="http://example.com/ogp.jpg" />
      <meta property="og:image:secure_url" content="https://secure.example.com/ogp.jpg" />
      <meta property="og:image:type" content="image/jpeg" />
      <meta property="og:image:width" content="400" />
      <meta property="og:image:height" content="300" />
   ```


**画像の別の説明** ：［画像の別の説明］フィールドを使用して、ページの`og:image:alt`プロパティを定義します。 ［**言語フラグ**］ ボタンを使用して画像の別の説明をローカライズすることもできます。

詳しくは、 [Open Graphの設定](./configuring-open-graph.md) を参照してください。

![Open Graphを有効または無効にしたり、画像のメタタグを定義したり、画像プレビューを表示したりできます。](./site-settings-ui-reference/images/08.png)



#### 共有

サイトユーザー間でドキュメント共有を有効にするかどうかを決定します。 有効にすると、ユーザー同士でアイテムを共有することができます。 詳細は、 [他のユーザーとのドキュメントの共有](../../content-authoring-and-management/documents-and-media/publishing-and-sharing/managing-document-access/sharing-documents-with-other-users.md) を参照してください。 



#### アセットの自動タグ付け

サイトのアセットの自動タグ付けを有効にするかどうかを決定します。 有効にすると、インスタンススコープで設定されたプロバイダによってアセットが自動タグ付けされます。 詳細は、[アセットの自動タグ付けの設定](../../content-authoring-and-management/tags-and-categories/auto-tagging/configuring-asset-auto-tagging.md)を参照してください。 



### ソーシャル

ここでは、サイト上のユーザー間のソーシャルインタラクションを管理できます。



#### 評価

ここでは、次のサイトアプリケーションに使用される評価のタイプを選択できます：コメント、ナレッジベース、ブログ、Wiki、掲示板、Webコンテンツ、およびドキュメントとメディア。 詳細は、 [Configuring Content Ratings Types](./site-content-configurations/configuring-content-rating-type.md)を参照してください。

![サイトアプリケーションに使用される評価のタイプを選択できます。](./site-settings-ui-reference/images/11.png)



#### 自分について書かれた記事/コメント

ユーザーがサイトアプリケーションで他のユーザーにメンションすることを許可するかどうかを決定します。 詳細は、[Mentioning Users](../../collaboration-and-social/notifications-and-requests/user-guide/configuring-mentions.md)を参照してください。



### 言語設定

ここでは、インストールのデフォルトの言語オプションを使用するか、サイトに独自の言語オプションを定義するかを選択できます。

![サイト設定の［言語］タブから言語オプションを更新できます。](./site-settings-ui-reference/images/13.png)



### 詳細設定

ここでは、アナリティクスサービス、コンテンツ共有などの追加設定を構成できます。



#### デフォルトの関連付け

新しいサイトメンバーにデフォルトで割り当てられるロールとチームを選択します。 詳細は、 [サイトメンバーのロールとチームのデフォルトの設定](./site-users/configuring-role-and-team-defaults-for-site-members.md) を参照してください。

![新しいサイトメンバーにデフォルトで割り当てられるロールとチームを選択できます。](./site-settings-ui-reference/images/14.png)



#### 分析

提供されたフィールドを使用して、サイトのGoogle アナリティクスIDを設定し、追加のGoogleアナリティクスオプションを設定します。 スクリプトの開始タグと終了タグを含む完全なスクリプトコードを入力して、サイトのPiwikアナリティクス追跡コードを設定することもできます。

別のアナリティクスサービスが必要な場合は、追加できます。 異なるアナリティクスサービスのための追加フィールドを追加する方法については、 [新しいアナリティクスサービスの追加](./adding-a-new-analytics-service.md) を参照してください。

![［アナリティクス］セクションでは、サイトのアナリティクスサービスを設定できます。](./site-settings-ui-reference/images/15.png)



#### 地図

サイトにジオローカライズされたアセットを表示するときに使用するマップのAPIプロバイダーを選択します。 ジオローカライズされたアセットは、ドキュメント、Webコンテンツの記事、DDLレコードなどに表示できます。 詳細は、[Geolocating Assets](./site-content-configurations/configuring-geolocation-for-assets.md)を参照してください。

![サイトの位置情報に使用するマップのAPIプロバイダーを選択できます。](./site-settings-ui-reference/images/16.png)



#### ゴミ箱

サイトのゴミ箱を有効にするかどうかを決定します。 アセットが自動的に削除されるまでゴミ箱に残っている分数を指定できます。 デフォルトでは、リサイクルされたアイテムの最大経過時間は43200分（つまり30日）です。 詳細は、[サイトのアセットゴミ箱を設定する ](./site-content-configurations/configuring-the-asset-recycle-bin-for-sites.md)を参照してください。

![サイトのごみ箱を設定できます。](./site-settings-ui-reference/images/17.png)



#### コンテンツ共有

サブサイトがこのサイトのコンテンツ（構造、テンプレート、カテゴリ、ウィジェットテンプレートなど）を表示できるかどうかを決定します。 このオプションを無効にすると、すべてのサブサイトからのコンテンツ共有がすぐに取り消されます。 詳細は、[サイト間でのコンテンツ共用の管理](./site-content-configurations/managing-content-sharing-across-sites.md)を参照してください。 

![サブサイトがこのサイトのコンテンツを表示できるかどうかを決定できます。](./site-settings-ui-reference/images/18.png)



## 追加情報

- [ページ設定UIリファレンス](../creating-pages/page-settings/configuring-individual-pages.md)
- [ページセットの設定](../creating-pages/page-settings/configuring-page-sets.md)
