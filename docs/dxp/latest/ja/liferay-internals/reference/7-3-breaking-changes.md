# 7.3破壊的な変更

このドキュメントは、サードパーティのLiferay開発者またはユーザーとの既存の機能、API、または契約に違反する変更の時系列リストを示しています。 私たちはこれらの混乱を最小限にするために最善を尽くしていますが、時には避けられないこともあります。

このファイルに記載されている変更の種類の一部を次に示します。

* 削除または置換される機能
* APIの非互換性：パブリックJavaまたはJavaScript APIの変更
* テンプレートで利用可能なコンテキスト変数の変更
* Liferayテーマおよびポートレットで利用可能なCSSクラスの変更
* 設定の変更：`portal.properties`、`system.properties`などの設定ファイルの変更
* 実行要件：Javaバージョン、Java EEバージョン、ブラウザーバージョンなど
* 非推奨またはサポート終了：たとえば、次のバージョンで特定の機能またはAPIが停止されると警告している

## 破壊的変更の一覧

### Liferay FontAwesomeはデフォルトで含まれなくなりました
- **日付：** 2019-Aug-21
- **JIRAチケット：** [LPS-100021](https://issues.liferay.com/browse/LPS-100021)

#### 何が変わりましたか？

Font Awesome、Glyphicon、およびカスタムLiferayアイコンのアイコンフォントが含まれていたLiferay FontAwesomeは、デフォルトでは含まれなくなりました。

#### 誰が影響を受けますか？

これは、これらのアイコンフォント自体を含まないテーマが適用されているページまたはサイトに影響します。 これらのアイコンフォントを使用するページやサイトのコンテンツやコードは機能しなくなります。

#### コードを更新するにはどうすればよいですか？

アイコンフォントの使用方法に応じて、いくつかの方法があります。

##### liferay-ui:アイコン使用の場合

`<liferay-ui:icon iconCssClass="icon-user">` を `<liferay-ui:icon icon="user" markupView="lexicon" />`置き換え`</p>

<h5 spaces-before="0">JavaScriptで生成されたアイコンの場合</h5>

<p spaces-before="0">FontAwesomeアイコンhtmlを手動で生成する場合は、<code>Liferay.Util.getLexiconIconTpl('user')` APIを使用できます。 たとえば、前の呼び出しは、ユーザーのsvgアイコンのHTMLコードを返します。

##### JSP内の直接HTMLの場合

直接JSPでアイコンを使用して開発者がいずれかを使用することができる `のLiferay-UIを：アイコン` タグ以上説明したように `粘土：アイコン` つの代わりにSVGベースのアイコンを生成します。

##### 非制御コードの場合

アイコンフォントを使用するコンテンツにアクセスできない場合、またはコードやコンテンツを更新したくない場合は、フォントをテーマに含めることができます。

7.2のアップグレードプロセス中、テーマアップグレードアシスタントは、FontAwesomeをテーマの一部として維持するよう開発者に促します。 アイコンフォントが既に含まれているテーマは影響を受けず、7.3でも引き続き機能します。

#### なぜこの変更が行われたのですか？

この変更は、不要なファイルを提供しないことで帯域幅を節約し、サイトのパフォーマンスを向上させるために行われました。

---------------------------------------

### liferay.frontend.ProgressBarを削除
- **日付：** 2019-Aug-28
- **JIRAチケット：** [LPS-100122](https://issues.liferay.com/browse/LPS-100122)

#### 何が変わりましたか？

従来の動作の一時的なブリッジとして使用されていたレガシーのmetal+soy `liferay.frontend.ProgressBar`コンポーネントが削除されました。

#### 誰が影響を受けますか？

これは、`liferay.frontend.ProgressBar`に依存するすべてのコードに影響します。 これは通常、`{call liferay.frontend.ProgressBar /}`として`soy`を介して行われます。

#### コードを更新するにはどうすればよいですか？

`liferay.frontend.ProgressBar`コンポーネントを直接置き換えるものはありません。 依存するコンポーネントがある場合は、古い実装のコピーを同じ場所に配置して、モジュール内でローカルに使用できます。

#### なぜこの変更が行われたのですか？

`liferay.frontend.ProgressBar`コンポーネントは7.2で非推奨になり、使用されなくなりました。

---------------------------------------

### AssetCategoryのツリーパスが左右のカテゴリーIDに置き換わりました
- **日付：** 2019年08月10日
- **JIRAチケット：** [LPS-102671](https://issues.liferay.com/browse/LPS-102671)

#### 何が変わりましたか？

`AssetCategory`の左右のカテゴリーIDが削除され、単一のツリーパスに置き換えられました。

#### 誰が影響を受けますか？

これは、`AssetCategory`および関連するAPIで左右のカテゴリーIDを使用するすべてのユーザーに影響します。

左右のカテゴリーIDは、主に`AssetCategory`の内部階層ツリーに使用されていました。

削除された`AssetCategoryLocalService::rebuildTree(long groupId, boolean force)`を除いて、既存の`AssetCategory`サービスAPIは同じままです。

次のメソッドは`AssetCategoryUtil`から削除されました。

- `countAncestors`
- `countDescendants`
- `getAncestors`
- `getDescendants`

左右のカテゴリーIDに関連するメソッドは`AssetEntryQuery`から削除されました。

`G_P_N_V`で終わるFinderメソッドは、`P_N_V`で終わるメソッドに置き換えられました。

#### コードを更新するにはどうすればよいですか？

##### 左右のカテゴリーIDの場合

左右のカテゴリーIDを使用している場合は、次のオプションを検討してください。

- 新しいツリーパスを使用するようにコードを調整する
- サービスAPIを使用して同じ目標を達成できるかどうかを検討する

たとえば、`category.getLeftCategoryId()`および`category.getRightCategoryId()`を介してカテゴリーIDを使用する代わりに、`category.getTreePath()`介してツリーパスを取得できます。  次に、ツリーパスを使用します。

参考までに、このスニペット`AssetCategoryLocalService`は、カテゴリを追加するときにツリーパスを設定します。

```
if (parentCategory == null) {
    category.setTreePath("/" + categoryId + "/");
}
else {
    category.setTreePath(
        parentCategory.getTreePath() + categoryId + "/");
}
```

[7.3.0-ga1 - AssetCategoryLocalServiceImpl.java#L122-L128](https://github.com/liferay/liferay-portal/blob/7.3.0-ga1/portal-impl/src/com/liferay/portlet/asset/service/impl/AssetCategoryLocalServiceImpl.java#L122-L128)を参照してください。

##### AssetCategoryLocalService#rebuildTree(long, boolean)の場合

`AssetCategoryLocalService#rebuildTree(long, boolean)`の呼び出しは不要な場合があります。  このメソッドは主に、現在は置き換えられている内部階層ツリーの実装を維持するために使用されていました。

既存のコードを再評価して、`rebuildTree`メソッドの呼び出しがまだ必要かどうかを確認することを検討してください。

##### AssetCategoryUtilおよびAssetEntryQueryの場合

`AssetCategoryUtil`および`AssetEntryQuery`から削除されたメソッドを使用する場合は、次の提案を検討してください。

- 既存のコードを再評価する
- 既存のサービスAPIが同じ目標を達成できるかどうかを検討する

##### G_P_N_Vを含むFinderメソッドの場合

`G_P_N_V`で終わる`AssetCategory` Finderメソッドを使用する場合は、代わりに`P_N_V`で終わるメソッドを使用してください。

#### なぜこの変更が行われたのですか？

この変更は、AssetCategoryの階層ツリーの実装を改善するために行われました。

---------------------------------------

### liferay.frontend.Sliderを削除
- **日付：** 2019年10月10日
- **JIRAチケット：** [LPS-100124](https://issues.liferay.com/browse/LPS-100124)

#### 何が変わりましたか？

従来の動作の一時的なブリッジとして使用されていたレガシーのmetal+soy `liferay.frontend.Slider`コンポーネントが削除されました。

#### 誰が影響を受けますか？

これは、`liferay.frontend.Slider`に依存するすべてのコードに影響します。 これは通常、`{call liferay.frontend.Slider /}`として`soy`を介して行われます。

#### コードを更新するにはどうすればよいですか？

`liferay.frontend.Slider`コンポーネントを直接置き換えるものはありません。 依存するコンポーネントがある場合は、古い実装のコピーを同じ場所に配置して、モジュール内でローカルに使用できます。

#### なぜこの変更が行われたのですか？

`liferay.frontend.Slider`コンポーネントは7.2で非推奨になり、使用されなくなりました。

---------------------------------------

### com.liferay.asset.taglib.servlet.taglib.soy.AssetTagsSelectorTagを削除
- **日付：** 2019-Oct-15
- **JIRAチケット：** [LPS-100144](https://issues.liferay.com/browse/LPS-100144)

#### 何が変わりましたか？

Javaクラス`com.liferay.asset.taglib.servlet.taglib.soy.AssetTagsSelectorTag`が削除されました。

#### 誰が影響を受けますか？

これは、このクラスを直接インスタンス化または拡張するコードに影響します。

#### コードを更新するにはどうすればよいですか？

削除されたクラスを直接置き換えるものはありません。 依存するコードがある場合は、古い実装を独自のプロジェクトにコピーし、ローカルバージョンに依存するように依存関係を変更する必要があります。

#### なぜこの変更が行われたのですか？

`asset：asset-tags-selector` とそのコンポーネントはReactに移行され、古いタグとその大豆インフラストラクチャが不要になりました。

---------------------------------------

### 削除されたポータルプロパティuser.groups.copy.layouts.to.user.personal.site
- **日付：** 2019年12月26日
- **JIRAチケット：** [LPS-106339](https://issues.liferay.com/browse/LPS-106339)

#### 何が変わりましたか？

ポータルプロパティ `user.groups.copy.layouts.to.user.personal.site` とそれに関連付けられた動作が削除されました。

#### 誰が影響を受けますか？

これは、ユーザーグループページをユーザーの個人サイトにコピーするために、 `user.groups.copy.layouts.to.user.personal.site` プロパティを `true` に設定したすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

このプロパティを直接置き換えるものはありません。 動作に依存している場合は、 `UserGroupLocalServiceImpl＃copyUserGroupLayouts` 古い実装を独自のプロジェクトにコピーできます。

#### なぜこの変更が行われたのですか？

このプロパティに関連付けられた動作は、6.2以降廃止されました。

---------------------------------------

### EXTプラグインの自動デプロイのサポートを削除
- **日付：** 2019年12月31日
- **JIRAチケット：** [LPS-106008](https://issues.liferay.com/browse/LPS-106008)

#### 何が変わりましたか？

Auto Deployerを使用して（ `liferay-home/deploy folder`介して）EXTプラグインをデプロイするためのサポートは削除されました。 デプロイフォルダーにコピーされたEXTプラグインは認識されなくなりました。

#### 誰が影響を受けますか？

これは、Auto Deployerを介してEXTプラグインをデプロイするすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

削除された機能を直接置き換えるものはありません。 EXTプラグインがある場合は、手動でデプロイするか、 [`ant direct-deploy`](https://github.com/liferay/liferay-plugins-ee/blob/7.0.x/ext/build-common-ext.xml#L211)使用する必要があります。

#### なぜこの変更が行われたのですか？

この機能は7.1以降廃止されました。

---------------------------------------

### 置き換えられたOSGi構成プロパティーautoUpgrade
- **日付：** 2020-Jan-03
- **JIRAチケット：** [LPS-102842](https://issues.liferay.com/browse/LPS-102842)

#### 何が変わりましたか？

`com.liferay.portal.upgrade.internal.configuration.ReleaseManagerConfiguration.config`で定義されたOSGiプロパティ`autoUpgrade`は、ポータルプロパティ`upgrade.database.auto.run`に置き換えられました。

モジュールのアップグレードプロセスのみを制御していた古いプロパティとは異なり、新しいプロパティはコアアップグレードプロセスにも影響します。 デフォルト値は `false`なので、起動時またはモジュールのデプロイメント時にアップグレードプロセスは実行されません。 モジュールのアップグレードプロセスは、Gogo シェルコンソールを介して、またはサーバーがダウンしているときはデータベースアップグレードツールを介していつでも実行できます。

このプロパティは、`portal-developer.properties`で`true`に設定されています

#### 誰が影響を受けますか？

この変更は、サーバーの起動時またはモジュールの展開時にアップグレードを自動的に実行することを期待しているすべての環境に影響します。 `upgrade.database.auto.run`を`true`に設定することは、本番環境では推奨されません。 ただし、サーバーの起動時にアップグレードする必要がある場合は、最初にLiferayデータベースとファイルストア（ドキュメントライブラリ）をバックアップします。

`upgrade.database.auto.run`を`false`（デフォルト値）に設定したが、データベースのアップグレードが必要な場合、Liferayは必要なアップグレードに関する情報を出力し、起動を停止します。 データベースのアップグレードは通常、メジャー/マイナーのLiferayリリースで必要であり、初期のCE Portal GAリリースおよび特定のサービスパック（例外的な場合）で必要になる場合があります。フィックスパックではデータベースのアップグレードは必要ありません。 起動時に、Liferayは保留中のマイクロ変更に関する情報を出力します。 いつでもGogo シェルコンソールとリリースノートを使用して、このような変更を確認し、実行するかどうかを決定できます。

#### コードを更新するにはどうすればよいですか？

この変更はコードには影響しません。

#### なぜこの変更が行われたのですか？

この変更は、コアとモジュール間の自動アップグレード機能を統合するために行われました。 本番環境での起動時に新しいアップグレードプロセスが実行されないように、デフォルト値も変更されました。

---------------------------------------

### 削除されたキャッシュブートストラップ機能
- **日付：** 2020-Jan-8
- **JIRAチケット：** [LPS-96563](https://issues.liferay.com/browse/LPS-96563)

#### 何が変わりましたか？

キャッシュブートストラップ機能は削除されました。 これらのプロパティは、キャッシュブートストラップを有効化/構成するために使用できなくなりました。

- `ehcache.bootstrap.cache.loader.enabled`
- `ehcache.bootstrap.cache.loader.properties.default`
- `ehcache.bootstrap.cache.loader.properties.${specific.cache.name}`

#### 誰が影響を受けますか？

これは、上記のプロパティを使用するすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

削除された機能を直接置き換えるものはありません。 それに依存するコードがある場合は、自分で実装する必要があります。

#### なぜこの変更が行われたのですか？

この変更は、セキュリティの問題を回避するために行われました。

---------------------------------------

### liferay-frontend：cards-treeviewタグを削除
- **日付：** 2020-Jan-10
- **JIRAチケット：** [LPS-106899](https://issues.liferay.com/browse/LPS-106899)

#### 何が変わりましたか？

`liferay-frontend：cards-treeview` タグが削除されました。

#### 誰が影響を受けますか？

これは、JSPのタグ、またはSOY（閉鎖テンプレート）テンプレート内のそのコンポーネントの一部を使用するすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

削除された機能を直接置き換えるものはありません。 それに依存するコードがある場合は、自分で実装する必要があります。

#### なぜこの変更が行われたのですか？

この変更は、タグが主に内部で使用されたために行われました。

---------------------------------------

### liferay-frontend:contextual-sidebarタグを削除
- **日付：** 2020-Jan-10
- **JIRAチケット：** [LPS-100146](https://issues.liferay.com/browse/LPS-100146)

#### 何が変わりましたか？

`liferay-frontend：contextual-sidebar` タグが削除されました。

#### 誰が影響を受けますか？

これは、SOY（閉鎖テンプレート）テンプレート内のjspまたはそのコンポーネントの一部からのタグを使用するすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

削除された機能を直接置き換えるものはありません。 それに依存するコードがある場合は、自分で実装する必要があります。

#### なぜこの変更が行われたのですか？

この変更は、タグが主に内部で使用されたために行われました。

---------------------------------------

### Portal Vulcan APIのアクションの追加メソッドを削除
- **日付：** 2020-Jan-22
- **JIRAチケット：** [LPS-98387](https://issues.liferay.com/browse/LPS-98387)

#### 何が変わりましたか？

署名`String, Class, GroupedModel, String, UriInfo`および`String, Class, Long, String, String, Long, UriInfo`を持つ`addAction`メソッドが削除されました。

#### 誰が影響を受けますか？

これは、削除された`addAction`メソッドを使用しているユーザー、または`compileOnly group: "com.liferay", name: "com.liferay.portal.vulcan.api", version: "[1.0.0, 2.0.0)"`などの依存関係があるユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

署名`String, Class, GroupedModel, String, Object, UriInfo`または`String, Class, Long, String, String, Object, Long, UriInfo`で`addAction`メソッドを使用します。

#### なぜこの変更が行われたのですか？

これらのメソッドは、クリーンアップリファクタリングの一部として削除されました。

---------------------------------------

### 変更されたコントロールメニューとプロダクトメニューの配置
- **日付：** 2020年2月4日
- **JIRAチケット：** [LPS-107487](https://issues.liferay.com/browse/LPS-107487)

#### 何が変わりましたか？

コントロールとプロダクトメニューの配置と構造は、いくつかのアクセシビリティの問題と一般的な視覚的な不具合に対処するために変更されました。

これらの変更は、コントロールメニューとプロダクトメニューに適用されています。

- プロダクトメニューはコントロールメニューの外に移動されました
- コントロールメニューは、動作を制御するために `位置：スティッキー` を使用するようになりました。
- コントロールメニュー内のメニューのスタイルは、新しいスティッキー動作を考慮して更新されました

#### 誰が影響を受けますか？

これは、カスタマイズされた `portlet.ftl` テンプレートを使用したカスタムコントロールパネルテーマを使用している開発者、またはスティッキーバーとして動作し、 `* ControlMenuEntry` APIを使用して含まれているカスタムメニューを開発した開発者に影響を与える可能性があります。

#### コードを更新するにはどうすればよいですか？

##### コントロールパネルのテーマ

カスタムのコントロールパネルテーマを使用する開発者は、（存在する場合）呼び出しを `portlet.ftl`ポートレットセクションの上の `@ liferay.control_menu` マクロに移動する必要があります。

**前：**

```jsp
<section class="portlet" id="portlet_${htmlUtil.escapeAttribute(portletDisplay.getId())}">
    $ {portletDisplay.writeContent（writer）}
</section>

<#if portletDisplay.isStateMax（）>
    <@ liferay.control_menu />
</＃if>
```

**後：**

```jsp
<#if portletDisplay.isStateMax（）>
    <@ liferay.control_menu />
</＃if>

<section class="portlet" id="portlet_${htmlUtil.escapeAttribute(portletDisplay.getId())}">
    $ {portletDisplay.writeContent（writer）}
</section>
```

##### カスタムスティッキーバー

`* ControlMenuEntry` APIを使用してカスタムのスティッキーバーを組み込んだ開発者は、コントロールメニューに新しく組み込まれた拡張ポイントを使用して、コンポーネントを挿入できます。

メニューを挿入するコードを `DynamicInclude` コンポーネントに移動し、適切な位置に登録します。

- コントロールメニューの前に次を使用します。: `com.liferay.product.navigation.taglib#/page.jsp#pre`
- コントロールメニューの後： `com.liferay.product.navigation.taglib#/page.jsp#post`使用します。

#### なぜこの変更が行われたのですか？

この変更は、アクセシビリティを向上させ、上位に配置されたメニューの配置と制御に必要なロジックを簡素化するために行われました。 一般的な視覚的な不具合を回避する、より正確で期待されるマークアップを提供します。

---------------------------------------

### jQueryはデフォルトで含まれなくなりました
- **日付：** 2020年2月4日
- **JIRAチケット：** [LPS-95726](https://issues.liferay.com/browse/LPS-95726)

#### 何が変わりましたか？

以前は、デフォルトで `jQuery` がすべてのページに含まれ、グローバル `window。$` およびスコープ付き `AUI。$` 変数を介して使用可能になりました。 この変更後、 `jQuery` はデフォルトで含まれなくなり、それらの変数は `未定義`です。

#### 誰が影響を受けますか？

これは、カスタムスクリプトで `AUI.$` または `window.$` を使用した開発者に影響します。

#### コードを更新するにはどうすればよいですか？

コードで使用する独自のバージョンのJQueryを提供するサードパーティライブラリを追加するための戦略のいずれかを使用します。

さらに、一時的な対策として、*［システム設定］* &rarr; *［サードパーティー］* &rarr; *［jQuery］*の`［JQueryを有効にする］`プロパティを`［true］`に設定することで、以前の動作に戻すことができます。

#### なぜこの変更が行われたのですか？

この変更は、ほとんどの場合未使用で冗長なすべてのページに追加のライブラリコードをバンドルして提供することを回避するために行われました。

---------------------------------------

### サーバー側の並列レンダリングはサポートされなくなりました
- **日付：** 2020-Mar-16
- **JIRAチケット：** [LPS-110359](https://issues.liferay.com/browse/LPS-110359)

#### 何が変わりましたか？

プレフィックス `layout.parallel.render` が付いたプロパティは削除されました。つまり、AJAXレンダリングが有効な場合にのみ、並列レンダリングがサポートされます。

#### 誰が影響を受けますか？

これは、削除されたプロパティを使用するすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

プロパティファイルから、プレフィックス `layout.parallel.render` プロパティを削除します。

#### なぜこの変更が行われたのですか？

この機能は廃止されました。

---------------------------------------

### ContentField値のプロパティ名をcontentFieldValueに変更
- **日付：** 2020-Mar-18
- **JIRAチケット：** [LPS-106886](https://issues.liferay.com/browse/LPS-106886)

#### 何が変わりましたか？

Headless Delivery APIで、ContentFieldスキーマ内のプロパティ名の`value`が`contentFieldValue`に変更されました。

#### 誰が影響を受けますか？

これは、ContentField `value`プロパティ名に応じてRESTクライアントに影響します。

#### コードを更新するにはどうすればよいですか？

RESTクライアントでプロパティ名を`contentFieldValue`に変更します。

#### なぜこの変更が行われたのですか？

この変更により、`{schemaName}+Value`と呼ばれるHeadless APIのすべての値プロパティ名との整合性が復元されます。

---------------------------------------

### liferay-editor-image-uploaderプラグインを削除しました
- **日付：** 2020-Mar-27
- **JIRAチケット：** [LPS-110734](https://issues.liferay.com/browse/LPS-110734)

### 何が変わりましたか？

`liferay-editor-image-uploader` AUIプラグインが削除されました。 そのコードは、AlloyEditorとCKEditorで使用される`addimages` CKEditorプラグインにマージされました。

### 影響を受ける人

これは、プラグインを直接使用するカスタムソリューションに影響します。

### コードを更新するにはどうすればよいですか？

`liferay-editor-image-uploader`プラグインに直接置き換わるものはありません。 依存するコンポーネントがある場合は、古い実装のコピーを同じ場所に配置して、モジュール内でローカルに使用できます。

#### なぜこの変更が行われたのですか？

この変更により、CKEditorでの画像のドラッグアンドドロップ処理が可能になり、Alloy EditorとCKEditorの両方に共通の画像アップローダが提供されます。

---------------------------------------

### TinyMCEエディターはデフォルトでバンドルされなくなりました
- **日付：** 2020-Mar-27
- **JIRAチケット：** [LPS-110733](https://issues.liferay.com/browse/LPS-110733)

### 何が変わりましたか？

7.3以降では、CKEditorがデフォルトであり、WYSIWYGエディターのみがサポートされています。

### 影響を受ける人

これはTinyMCEを使用するすべてのユーザーに影響します。

### コードを更新するにはどうすればよいですか？

TinyMCEを使用するようにLiferayポータルを構成している場合は、これらの構成を削除できます。 それでもTinyMCEを使用したい場合は、次の手順を実行する必要があります。

- 構成を保持します。
- ブラウザで https://repository.liferay.com/nexus/index.html を開きます。
- `com.liferay.frontend.editor.tinymce.web`を検索します。
- `com.liferay.frontend.editor.tinymce.web` モジュールの.jarファイルをダウンロードします。
- ダウンロードした.jarファイルをliferay-portalインスタレーションにデプロイします。

#### なぜこの変更が行われたのですか？

この変更は、1つのエディターを中心にリッチテキストコンテンツを作成するためのすべてのUXを統合して、よりまとまりのある包括的なエクスペリエンスを提供するために行われました。

---------------------------------------

### Simple Editorはデフォルトでバンドルされなくなりました
- **日付：** 2020-Mar-27
- **JIRAチケット：** [LPS-110734](https://issues.liferay.com/browse/LPS-110734)

### 何が変わりましたか？

7.3以降では、CKEditorがデフォルトであり、WYSIWYGエディターのみがサポートされています。

### 影響を受ける人

これは、LiferayフロントエンドエディターのシンプルなWebモジュールを使用するすべてのユーザーに影響します。

### コードを更新するにはどうすればよいですか？

Simplerayを使用するようにLiferayポータルを構成している場合は、これらの構成を削除できます。 Simple Editorを引き続き使用する場合は、次の手順を実行する必要があります。

- 構成を保持します。
- ブラウザで https://repository.liferay.com/nexus/index.html を開きます。
- `com.liferay.frontend.editor.simple.web`検索します。
- `com.liferay.frontend.editor.simple.web` モジュールの.jarファイルをダウンロードします。
- ダウンロードした.jarファイルをliferay-portalインストールにデプロイします。

#### なぜこの変更が行われたのですか？

この変更は、1つのエディターを中心にリッチテキストコンテンツを作成するためのすべてのUXを統合して、よりまとまりのある包括的なエクスペリエンスを提供するために行われました。

---------------------------------------

### asset.vocabulary.defaultが言語キーを保持するようになりました
- **日付：** 2020-Apr-28
- **JIRAチケット：** [LPS-112334](https://issues.liferay.com/browse/LPS-112334)

### 何が変わりましたか？

`asset.vocabulary.default` が言語キーになり、固定値 `トピック`ではなくなりました。

### 影響を受ける人

これは、プロパティを上書きするすべてのユーザーに影響します。

### コードを更新するにはどうすればよいですか？

プロパティが上書きされない場合は、コードを変更する必要はありません。 プロパティが上書きされ、指定されたキーが見つからない場合、提供されたテキストはデフォルトの語彙の名前として使用されます。

#### なぜこの変更が行われたのですか？

この変更はユーザーがすべての言語でデフォルトの語彙の名前を変更する必要がないように行われました。

---------------------------------------

### Liferay.Pollerがデフォルトで初期化されなくなりました
- **日付：** 2020年5月19日
- **JIRAチケット：** [LPS-112942](https://issues.liferay.com/browse/LPS-112942)

#### 何が変わりましたか？

グローバルAUI `Liferay.Poller`ユーティリティは廃止され、デフォルトでは初期化されなくなりました。

#### 誰が影響を受けますか？

これは、`Liferay.Poller`に依存するすべてのコードに影響します。 これは通常、JSPで`Liferay.Poller.init()`を呼び出すことによって行われます。

#### コードを更新するにはどうすればよいですか？

`Liferay.Poller`ユーティリティに直接置き換わるものはありません。  `Liferay.Poller`を初期化する必要がある場合は、以下のコードを使用するようにJSPを更新してください。

```jsp
<%@ page import="com.liferay.petra.encryptor.Encryptor" %>

<%-- For access to `company` and `themeDisplay`. --%>
<liferay-theme:defineObjects>

<aui:script use="liferay-poller">
    <c:if test="<%= themeDisplay.isSignedIn() %>">
        Liferay.Poller.init({
            encryptedUserId:
                '<%= Encryptor.encrypt(company.getKeyObj(), String.valueOf(themeDisplay.getUserId())) %>',
        });
    </c:if>
</aui:script>
```

#### なぜこの変更が行われたのですか？

`Liferay.Poller`コンポーネントは、アーカイブされているチャットアプリケーションでのみ使用されていました。 デフォルトで初期化をスキップすると、一般的なケースでページの読み込みが合理化されます。

---------------------------------------

### ContentTransformerListenerはデフォルトで無効になっています
- **日付：** 2020年5月25日
- **JIRAチケット：** [LPS-114239](https://issues.liferay.com/browse/LPS-114239)

#### 何が変わりましたか？

`ContentTransformerListener` はデフォルトで無効になりました。

#### 誰が影響を受けますか？

これは、`ContentTransformerListener`によって提供されるレガシーなWebコンテンツ機能を使用しているLiferay Portalのインストールに影響します。例えば、別のWebコンテンツ内にWebコンテンツを埋め込む、レガシーなエディット・イン・プレース・インフラストラクチャ、トークンの置換(`@article_group_id@`, `@articleId;elementName@`)などです。

#### コードを更新するにはどうすればよいですか？

コードを更新する必要はありません。 それでも `ContentTransformerListener` を使用したい場合は、システム設定で *［Content & Data］* &rarr; *［Webコンテンツ］* &rarr; *［仮想インスタンススコープ］* &rarr; *［Webコンテンツ］*の*［Enable ContentTransformerListener］*プロパティを使用して有効にすることができます。

#### なぜこの変更が行われたのですか？

`ContentTransformerListener`は、記事要素の文字列処理にコストがかかるため、パフォーマンスを向上させるために無効化されました（記事フィールドに対して`HtmlUtil.stripComments`と`HtmlUtil.stripHtml`を呼び出しています）。

---------------------------------------

### Liferay.BrowserSelectors.runが呼び出されなくなりました
- **日付：** 2020年5月26日
- **JIRAチケット：** [LPS-112983](https://issues.liferay.com/browse/LPS-112983)

#### 何が変わりましたか？

`Liferay.BrowserSelectors.run()`関数はページで呼び出されなくなり、その結果、開始`<html>`タグから一部のCSSクラスが削除されます。 これらの多くは、代わりに `<body>` 要素に追加されています 。

#### 誰が影響を受けますか？

これは、 `<html>` 要素のこれらのCSSクラスに依存するすべてのコードに影響します。

- `AOL`
- `camino`
- `EdgeHTML` または `Edge`
- `Firefox`
- `flock`
- `gecko`
- `icab`
- `IE`、 `IE6`、 `IE7`、 `IE9`、又は `IE11`
- `js`
- `konqueror`
- `mac`
- `mozilla`
- `netscape`
- `opera`
- `presto`
- `safari`
- `secure`
- `touch`
- `trident`
- `webkit`
- `Win`

#### コードを更新するにはどうすればよいですか？

`Liferay.BrowserSelectors.run()`関数に直接置き換わるものはありませんが、CSSとJavaScriptを調整して、代わりに`<body>`要素の新しいクラスをターゲットにすることができます。  これらのクラスは、現在使用しているブラウザーを反映するために `<body>` 要素に追加されます。

- `Chrome`
- `Edge`
- `Firefox`
- `IE`
- `mobile`
- `その他`

または、`Liferay.BrowserSelectors.run()`を呼び出して、以下のコードで古いクラスを`<html>`要素に適用することもできます。

```
<aui:script use="liferay-browser-selectors">
    Liferay.BrowserSelectors.run();
</aui:script>
```

#### なぜこの変更が行われたのですか？

一部のクラスが古いブラウザを参照していたクラスは、Alloy UIに依存するレガシーJavaScriptを介して上位 `<html>` 要素に追加されていました。 古いブラウザー参照を削除するこの変更は、サーバー側で行われるようになり、ページの読み込み時間が改善されました。

---------------------------------------

### ブロッキングキャッシュのサポートを削除
- **日付：** 2020-Jun-17
- **JIRAチケット：** [LPS-115687](https://issues.liferay.com/browse/LPS-115687)

#### 何が変わりましたか？

ブロッキングキャッシュのサポートが削除されました。 これらのプロパティは、ブロッキングキャッシュを有効にするために使用できなくなりました。

- `ehcache.blocking.cache.allowed`
- `permissions.object.blocking.cache`
- `value.object.entity.blocking.cache`

#### 誰が影響を受けますか？

これは、上記のプロパティを使用するすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

削除された機能を直接置き換えるものはありません。 それに依存するコードがある場合は、自分で実装する必要があります。

#### なぜこの変更が行われたのですか？

この変更は、ブロッキングキャッシュを有効にしてはならないため、パフォーマンスを向上させるために行われました。

---------------------------------------

### 各エンティティモデルのキャッシュプロパティ設定のサポートを削除
- **日付：** 2020-Jun-24
- **JIRAチケット：** [LPS-116049](https://issues.liferay.com/browse/LPS-116049)

#### 何が変わりましたか？

エンティティのこれらのキャッシュプロパティを設定するためのサポートが削除されました：

- `value.object.entity.cache.enabled *`
- `value.object.finder.cache.enabled *`
- `value.object.column.bitmask.enabled *`

たとえば、次のプロパティはエンティティ`com.liferay.portal.kernel.model.User`に対するものです。

- `value.object.entity.cache.enabled.com.liferay.portal.kernel.model.User`
- `value.object.finder.cache.enabled.com.liferay.portal.kernel.model.User`
- `value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.User`

#### 誰が影響を受けますか？

これは、エンティティに対して上記のプロパティを使用するすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

削除された機能を直接置き換えるものはありません。 エンティティからこれらのプロパティを削除する必要があります。

#### なぜこの変更が行われたのですか？

これらのプロパティはエンティティには役に立たないため、この変更が行われました。

---------------------------------------

### ポータルプロパティの名前を"module.framework.properties.felix.fileinstall.\*"から"module.framework.properties.file.install.\*"に変更
- **日付：**2020年7月13日
- **JIRAチケット：** [LPS-115016](https://issues.liferay.com/browse/LPS-115016)

#### 何が変わりましたか？

"module.framework.properties.felix.fileinstall"で始まるポータルプロパティの名前が "module.framework.properties.file.install"で始まるように変更されました。

#### 誰が影響を受けますか？

これは、`module.framework.properties.felix.fileinstall.*`ポータルプロパティ設定をオーバーライドしているすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

`module.framework.properties.felix.fileinstall.*`で始まるプロパティの名前を`module.framework.properties.file.install.*`で始まる名前に変更します。

#### なぜこの変更が行われたのですか？

この変更は、Apache Felix ファイルインストールのインライン化を反映するために行われました。 Liferayは現在、この機能を管理および保守しています。

---------------------------------------

### Elasticsearchの動的データマッピングフィールドをネストされたドキュメントに変更
- **日付：**2020年7月27日
- **JIRAチケット：** [LPS-103224](https://issues.liferay.com/browse/LPS-103224)

#### 何が変わりましたか？

`ddm__keyword__`および`ddm__text__`で始まるElasticsearchの動的データマッピングフィールドは、新しいネストされたドキュメント`ddmFieldArray`に移動されました。

`ddmFieldArray`には、次のフィールドを持ついくつかのエントリーがあります。

- `ddmFieldName`：動的データマッピング・ストラクチャーフィールド名が含まれます。 この名前は、`DDMIndexer.encodeName`メソッドを使用して生成されます。
- `ddmFieldValue *`：インデックス付きデータが含まれます。 このフィールドの名前は、`DDMIndexer.getValueFieldName`を使用して生成され、フィールドのデータ型と言語によって異なります。
- `ddmValueFieldName`：インデックス付きデータが格納されているインデックスフィールド名が含まれます。

 Solr検索エンジンを使用している場合、この変更は適用されません。

#### 誰が影響を受けますか？

これは、`ddm__keyword__*`および`ddm__text__*`フィールドを使用してElasticsearchインデックスでクエリを実行するカスタム開発を行っているすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

Elasticsearchクエリで新しいネストされたドキュメント`ddmFieldArray`を使用する必要があります。

Liferayコードにはいくつかの例があります。 たとえば、[DDMIndexerImpl](https://github.com/liferay/liferay-portal/blob/7.3.x/modules/apps/dynamic-data-mapping/dynamic-data-mapping-service/src/main/java/com/liferay/dynamic/data/mapping/internal/util/DDMIndexerImpl.java)と[AssetHelperImpl](https://github.com/liferay/liferay-portal/blob/master/modules/apps/asset/asset-service/src/main/java/com/liferay/asset/internal/util/AssetHelperImpl.java)は`DDM_FIELD_ARRAY`定数を使用します。

システム設定からレガシー動作を復元し、`ddm__keyword__*`および`ddm__text__*`フィールドを引き続き使用することもできます。

1. *［システム設定］* &rarr; *［動的データマッピング］* &rarr; *［動的データマッピング Web インデクサー］*に移動します。
1. *［旧形式の動的データマッピングインデックスフィールドを有効にする］*を選択します。
1. 完全なインデックスの再構築を実行します。

#### なぜこの変更が行われたのですか？

この変更は、動的データマッピング・ストラクチャーの数が多すぎる場合に発生するElasticsearchエラーで*合計フィールド数の制限を超えない*ようにするために行われました。

---------------------------------------

### Lexiconアイコンパスの移動
- **日付：**2020年8月17日
- **JIRAチケット：** [LPS-115812](https://issues.liferay.com/browse/LPS-115812)

### 何が変わりましたか？

Lexiconアイコンのパスが`themeDisplay.getPathThemeImages() + "/lexicon/icons.svg`から`themeDisplay.getPathThemeImages() + "/clay/icons.svg`に変更されました

### 影響を受ける人

これは、Lexiconアイコンパスを直接使用するカスタムソリューションに影響します。 `lexicon`パスにアイコンを作成するためのGradleタスクは削除されます。

### コードを更新するにはどうすればよいですか？

`lexicon`の代わりに`clay`を参照するようにパスを更新します

#### なぜこの変更が行われたのですか？

この変更は、アイコンスプライトマップへの参照を統合するために行われました。

---------------------------------------

### DDM永続性クラスからclassNameId関連のメソッドを削除
- **日付：** 2020-Aug-18
- **JIRAチケット：** [LPS-108525](https://issues.liferay.com/browse/LPS-108525)

### 何が変わりましたか？

`countByClassNameId`、`findByClassNameId`、および`removeByClassNameId`メソッドは、次のクラスから削除されました。

- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLinkPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLinkUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructurePersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateLinkPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateLinkUtil`

### 影響を受ける人

これは、これらのメソッドのいずれかを使用するすべてのユーザーに影響します。

### コードを更新するにはどうすればよいですか？

他のファインダーとカウンターのメソッドを使用できます。

#### なぜこの変更が行われたのですか？

これらのメソッドは、[LPS-108525](https://issues.liferay.com/browse/LPS-108525)のソリューションの一部として削除されました。

---------------------------------------

### com.liferay.dynamic.data.mapping.util.BaseDDMDisplayメソッドを削除
- **日付：** 2020-Aug-18
- **JIRAチケット：** [LPS-103549](https://issues.liferay.com/browse/LPS-103549)

### 何が変わりましたか？

`isShowAddStructureButton`メソッドを削除しました。

### 影響を受ける人

これは、このメソッドを使用するすべてのユーザーに影響します。

### コードを更新するにはどうすればよいですか？

このメソッドの代わりに`isShowAddButton(Group scopeGroup)`メソッドを使用できます。

#### なぜこの変更が行われたのですか？

このメソッドは、クリーンアップリファクタリングの一部として削除されました。

---------------------------------------

### ポータルプロパティview.count.enabledおよびbuffered.increment.enabledを置き換え
- **日付：** 2020年01月10日
- **JIRAチケット：** [LPS-120626](https://issues.liferay.com/browse/LPS-120626) および [LPS-121145](https://issues.liferay.com/browse/LPS-121145)

#### 何が変わりましたか？

ビューカウントをグローバルに、またはエンティティ専用に有効にしたり無効にしたりする機能は、ポータルプロパティから削除され、システム設定として構成されるようになりました。 ビューカウントは、UIの*［システム設定］* &rarr; *［Infrastructure］* &rarr; *［View Count］*で構成するか、`com.liferay.view.count.configuration.ViewCountConfiguration.config`という名前の構成ファイルを使用して構成できます。

ポータルプロパティの変更は次のとおりです。

`buffered.increment.enabled`ポータルプロパティが削除されました。 ビューカウントのグローバルな有効化と無効化は、［View Count］ページの`enabled`プロパティを使用して実行されるようになりました。

特定のエンティティに対しビューカウント動作を無効にすることは、ポータルプロパティで行われなくなりました。たとえば、7.3では`view.count.enabled[SomeEntity]=false`、7.2では`buffered.increment.enabled[SomeEntity]=false`を設定していましたが、現在は［View Count］ページの`Disabled Class Name`値リストにエンティティクラス名を追加することで実行されます。

#### 誰が影響を受けますか？

これは、ポータルプロパティ`view.count.enabled=false`または`buffered.increment.enabled=false`を設定しているすべてのユーザーに影響します。

これは、ポータルプロパティ設定`view.count.enabled[SomeEntity]=false`（7.3の初期バージョン）または`buffered.increment.enabled[SomeEntity]=false`（7.2ポータル）を使用して、一部のエンティティ（`SomeEntity`など）のビューカウントを無効にしているすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

`view.count.enabled`または`buffered.increment.enabled`ポータルプロパティと、`view.count.enabled[SomeEntity]=false`または`buffered.increment.enabled[SomeEntity]=false`などのエンティティ固有のプロパティを削除します。

システム設定または構成ファイルを使用して、ビューカウントの動作を構成します。

*［システム設定］* &rarr; *［Infrastructure］* &rarr; *［View Count］*で、`enabled`を`false`に設定してグローバルにビューカウントを無効にするか、`enabled`を`true`に設定してグローバルにビューカウントを有効にし、エンティティクラス名を`Disabled Class Name`値リストに追加して特定のエンティティのビューカウントを無効にします。

構成ファイルを使用するには、システム設定でビューカウントを構成し、設定を保存して、`com.liferay.view.count.configuration.ViewCountConfiguration.config`ファイルにエクスポートします。  次に、ファイルを`[Liferay Home]/osgi/configs`フォルダに配置して、構成をデプロイします。

#### なぜこの変更が行われたのですか？

この変更は、ビューカウント動作の管理を容易にするために行われました。

---------------------------------------

### ポータルプロパティ"module.framework.properties.file.install.optionalImportRefreshScope"を削除
- **日付：** 2020年11月10日
- **JIRAチケット：** [LPS-122008](https://issues.liferay.com/browse/LPS-122008)

#### 何が変わりましたか？

ポータルプロパティ`module.framework.properties.file.install.optionalImportRefreshScope`は削除されました。  ファイルのインストールでは、更新が必要なオプションのパッケージを含むバンドルをスキャンするときに、管理対象バンドルのみがチェックされるようになりました。

#### 誰が影響を受けますか？

これは、ポータルプロパティ`module.framework.properties.file.install.optionalImportRefreshScope`を設定しているすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

`module.framework.properties.file.install.optionalImportRefreshScope`プロパティを削除します。 他の動作を使用するようにファイルのインストールを構成することはできません。

#### なぜこの変更が行われたのですか？

代替動作が望まれるケースはほとんどありません。 ファイルのインストールは、バンドルをLiferayにインストールする主な方法であるため、バンドル管理のデフォルトになりました。 古い機能とその分岐ロジックを削除すると、コードの保守性と可読性が向上します。

---------------------------------------