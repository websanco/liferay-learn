# サイト設定UIリファレンス

サイトの設定を表示するには、サイトメニュー（![Site Menu](../../images/icon-product-menu.png)）を開き、*[設定]* → *[Settings]* に移動します。ここに、次のタブが表示されます。

  - [一般](#general)
  - [ソーシャル](#social)
  - [言語](#language)
  - [詳細設定](#advanced)

![サイト設定は4つのタブで構成されています。](./site-settings-ui-reference/images/01.png)

```{note}
Many of these settings can be localized to provide translations based on a user's locale. See [Introduction to Localization](https://help.liferay.com/hc/en-us/articles/360028746672-Introduction-to-Localization) and [Modifying Localizable Site Fields](./site-localization.md#modifying-localizable-site-fields) or more information.
```

## 一般

一般設定は、サイトのメンバーシップタイプなどのコア設定から、ドキュメントとメディアのインデックス作成オプションなどの詳細まで多岐にわたります。 タブのコンテンツは、詳細、ページ、カテゴリ設定、サイトURL、ドキュメントとメディア、Open Graph、アセットの自動タグ付け、共有、およびカスタムフィールドのサブセクションで構成されています。

### 詳細

**サイトID**: サイトの作成時に自動的に生成される一意の番号。 このIDは永続的であり、変更できません。

**Name**：*[Name]* フィールドを使用してサイトのタイトルを設定します。 このタイトルは、ブラウザのタイトルバーと各サイトページのヘッダに表示されます。 *[言語フラグ]* ボタンを使用してサイトの名前をローカライズすることもできます。

**説明**：*[説明]* フィールドを使用して、サイトの目的を説明します。 *[言語フラグ]* ボタンを使用してサイトの説明をローカライズすることもできます。

**有効**：サイトが*[有効]* か*[Inactive]* かを判別します。 非アクティブの間、ユーザーはサイトにアクセスできませんが、必要に応じて再アクティブ化できます。

**メンバーシップタイプ**：*メンバーシップタイプ*によって、サイトのメンバーシップの制限度が決まります。 詳細については、[Changing Site Membership Types](./site-users/changing-site-membership-type.md)を参照してください。

**手動メンバーシップ管理を許可する**：メンバーをサイトに手動で追加したりサイトから削除することを許可するかどうかを決定します。 サイトのメンバーシップがメンバーシップポリシーによって自動的に処理される場合は、この設定を無効にすることができます。 詳細については、[Managing Membership Policies for Sites](./site-users/changing-site-membership-type.md)を参照してください。

**親サイト**：親サイトを選択して、サイトをサブサイトとして指定します。 詳細については、[Site Hierarchies](../building-sites/site-hierarchies.md)を参照してください。

**Limit Membership to Parent Site Members**：サブサイトのメンバーシップをその親サイトのメンバーに制限するかどうかを決定します。 このオプションは、サブサイトにのみ表示されます。

### ページ

サイトの公開ページと非公開ページが存在する場合はそれを表示し、選択したサイトテンプレートからのプロパゲーションの変更を有効または無効にします。 ページが存在しない場合は、テンプレートを使用してページを作成するためのサイトテンプレートセレクターが表示されます。

![[Pages]セクションでは、サイトの公開ページと非公開ページを表示できます。](./site-settings-ui-reference/images/03.png)

### カスタムフィールド

ページに定義したカスタムフィールドを表示および設定します。 これらのフィールドを使用して、作成者、作成日、位置情報などのページメタデータを設定できます。 このセクションは、サイトのカスタムフィールドを設定した場合にのみ表示されます。 詳細については、 [Custom Fields](../../system-administration/configuring-liferay/adding-custom-fields.md)を参照してください。

### カテゴリ設定

カテゴリとタグを使用してサイトのコンテンツを分類し、ユーザーがより簡単に見つけられるようにします。 タグとカテゴリの使用の詳細については、[カテゴリとタグを使用したコンテンツの整理](../../content-authoring-and-management/tags-and-categories/organizing-content-with-categories-and-tags.md)を参照してください。

### サイトURL

**フレンドリURL**: サイトの公開ページと非公開ページの両方にカスタムURLを設定します。 詳細については、[Configuring Your Site's Friendly URL](./managing-site-urls/configuring-your-sites-friendly-url.md)を参照してください。

**Public and Private Virtual Host**：サイトの公開ページおよび非公開ページにマップする公開および非公開のバーチャルホストを設定します。 詳細については、 [Configuring Virtual Hosts Site URLs](./managing-site-urls/configuring-virtual-hosts-site-urls.md)を参照してください。

![[サイトURL]セクションでは、サイトのフレンドリURLと、公開ページと非公開ページのバーチャルホストを設定できます。](./site-settings-ui-reference/images/05.png)

### ドキュメントとメディア

サイトのディレクトリのインデックスを有効にするかどうかを決定します。 有効にすると、表示権限を持つユーザーがサイトのドキュメントライブラリファイルとフォルダを閲覧できます。

### サイトテンプレート

*サイトテンプレート*を使用してサイトを作成した場合、テンプレートを継承したページをユーザーが変更できるかどうかとともに、サイトがここに表示されます。 サイトテンプレートと独自のテンプレートを作成する方法の詳細については、[Building Sites from Templates](../building-sites/building-sites-with-site-templates.md)を参照してください。

![ページの選択されたテンプレートを表示します。](./site-settings-ui-reference/images/07.png)

### Open Graph

**Open Graphを有効にする**：サイトのページの`<head>`に[Open Graph](https://ogp.me/) `<meta>`タグを埋め込むかどうかを決定します。 これらのタグは、ページのメタデータを定義し、Facebook、Slack、TwitterなどのOpen Graphプロトコルをサポートするアプリケーションで共有する際に、サイトのコンテンツを魅力的に表現します。

**Image**：[Image]フィールドを使用して、ページの次のOpen Graph `<meta>`プロパティを定義します。

``` html
   <meta property="og:image" content="http://example.com/ogp.jpg" />
   <meta property="og:image:secure_url" content="https://secure.example.com/ogp.jpg" />
   <meta property="og:image:type" content="image/jpeg" />
   <meta property="og:image:width" content="400" />
   <meta property="og:image:height" content="300" />
```

**画像の別の説明**：[画像の別の説明]フィールドを使用して、ページの`og:image:alt`プロパティを定義します。 *[言語フラグ]* ボタンを使用して画像の別の説明をローカライズすることもできます。

詳しくは、[Configuring Open Graph](./configuring-open-graph.md)を参照してください。

![Open Graphを有効または無効にしたり、画像のメタタグを定義したり、画像プレビューを表示したりできます。](./site-settings-ui-reference/images/08.png)

### 共有

サイトユーザー間でドキュメント共有を有効にするかどうかを決定します。 有効にすると、ユーザー同士でアイテムを共有することができます。 詳細については、[Sharing Documents with Other Users](../../content-authoring-and-management/documents-and-media/publishing-and-sharing/managing-document-access/sharing-documents-with-other-users.md)を参照してください。

### アセットの自動タグ付け

サイトのアセットの自動タグ付けを有効にするかどうかを決定します。 有効にすると、アセットはインスタンスレベルで設定されたプロバイダーによって自動タグ付けされます。 詳細については、[Asset Auto Tagging](../../content-authoring-and-management/tags-and-categories/auto_tagging.rst)を参照してください。

## ソーシャル

ここでは、サイト上のユーザー間のソーシャルインタラクションを管理できます。

### 評価

ここでは、次のサイトアプリケーションに使用される評価のタイプを選択できます：コメント、ナレッジベース、ブログ、Wiki、掲示板、Webコンテンツ、およびドキュメントとメディア。 詳細については、 [Configuring Content Ratings Types](./site-content-configurations/configuring-content-rating-type.md)を参照してください。

![サイトアプリケーションに使用される評価のタイプを選択できます。](./site-settings-ui-reference/images/11.png)

### 自分について書かれた記事/コメント

ユーザーがサイトアプリケーションで他のユーザーにメンションすることを許可するかどうかを決定します。 詳細については、[Mentioning Users](../../collaboration-and-social/notifications-and-requests/user-guide/configuring-mentions.md)を参照してください。

## 言語

ここでは、インストールのデフォルトの言語オプションを使用するか、サイトに独自の言語オプションを定義するかを選択できます。

![サイト設定の[Languages]タブから言語オプションを更新できます。](./site-settings-ui-reference/images/13.png)

## 詳細設定

ここでは、アナリティクスサービス、コンテンツ共有などの追加設定を構成できます。

### デフォルトのユーザーの関連付け

新しいサイトメンバーにデフォルトで割り当てられるロールとチームを選択します。 詳細については、[Configuring Role and Team Defaults for Site Members](./site-users/configuring-role-and-team-defaults-for-site-members.md)を参照してください。

![新しいサイトメンバーにデフォルトで割り当てられるロールとチームを選択できます。](./site-settings-ui-reference/images/14.png)

### アナリティクス

提供されたフィールドを使用して、サイトのGoogle アナリティクスIDを設定し、追加のGoogleアナリティクスオプションを設定します。 スクリプトの開始タグと終了タグを含む完全なスクリプトコードを入力して、サイトのPiwikアナリティクス追跡コードを設定することもできます。

別のアナリティクスサービスが必要な場合は、追加できます。 異なるアナリティクスサービスのための追加フィールドを追加する方法については、[Adding a New Analytics Service](./adding-a-new-analytics-service.md)を参照してください。

![[アナリティクス]セクションでは、サイトのアナリティクスサービスを設定できます。](./site-settings-ui-reference/images/15.png)

### 地図

サイトにジオローカライズされたアセットを表示するときに使用するマップのAPIプロバイダーを選択します。 ジオローカライズされたアセットは、ドキュメント、Webコンテンツの記事、DDLレコードなどに表示できます。 詳細については、[Geolocating Assets](./site-content-configurations/configuring-geolocation-for-assets.md)を参照してください。

![サイトの位置情報に使用するマップのAPIプロバイダーを選択できます。](./site-settings-ui-reference/images/16.png)

### ごみ箱

サイトのごみ箱を有効にするかどうかを決定します。 アセットが自動的に削除されるまでごみ箱に残っている分数を指定できます。 デフォルトでは、リサイクルされたアイテムの最大経過時間は43200分（つまり30日）です。 詳細については、[Configuring the Asset Recycle Bin for Sites](./site-content-configurations/configuring-the-asset-recycle-bin-for-sites.md)を参照してください。

![サイトのごみ箱を設定できます。](./site-settings-ui-reference/images/17.png)

### コンテンツ共有

サブサイトがこのサイトのコンテンツ（構造、テンプレート、カテゴリ、ウィジェットテンプレートなど）を表示できるかどうかを決定します。 このオプションを無効にすると、すべてのサブサイトからのコンテンツ共有がすぐに取り消されます。 詳細については、[Managing Content Sharing Across Sites](./site-content-configurations/managing-content-sharing-globally.md)を参照してください。

![サブサイトがこのサイトのコンテンツを表示できるかどうかを決定できます。](./site-settings-ui-reference/images/18.png)

## 追加情報

  - [Page Configuration UI Reference](../creating-pages/page-settings/configuring-individual-pages.md)
  - [Configuring Page Sets](../creating-pages/page-settings/configuring-page-sets.md)
