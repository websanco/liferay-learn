# 7.4破壊的な変更

このドキュメントは、サードパーティのLiferay開発者またはユーザーとの既存の機能、API、または契約に違反する変更の時系列リストを示しています。 私たちはこれらの混乱を最小限にするために最善を尽くしていますが、時には避けられないこともあります。

このファイルに記載されている変更の種類の一部を次に示します。

* 削除または置換される機能
* APIの非互換性：パブリックJavaまたはJavaScript APIの変更
* テンプレートで利用可能なコンテキスト変数の変更
* Liferayテーマおよびポートレットで利用可能なCSSクラスの変更
* 設定の変更：`portal.properties`、`system.properties`などの設定ファイルの変更
* 実行要件：Javaバージョン、Java EEバージョン、ブラウザーバージョンなど
* 非推奨またはサポート終了：たとえば、次のバージョンで特定の機能またはAPIが停止されると警告している

<a name="liferay-uiflashタグを削除" />

## liferay-ui:flashタグを削除
- **日付：** 2020年13月10日
- **JIRAチケット：**[LPS-121732](https://issues.liferay.com/browse/LPS-121732)

### 何が変わりましたか？

タグ`liferay-ui:flash`は削除され、使用できなくなりました。

### 誰が影響を受けますか？

これは、`liferay-ui:flash`タグを使用しているすべての開発に影響します。

### コードを更新するにはどうすればよいですか？

それでもAdobe Flashコンテンツをページに埋め込む必要がある場合は、`SWFObject`などの標準メカニズムを使用して独自のコードを記述してください。

### なぜこの変更が行われたのですか？

この変更は、2020年12月31日に [AdobeがFlashのサポートを終了](https://www.adobe.com/products/flashplayer/end-of-life.html) し、今後のバージョンのブラウザでFlashのサポートが削除されることに対応したものです。

---------------------------------------

<a name="portalflashパスの削除" />

## /portal/flashパスの削除
- **日付：** 2020年13月10日
- **JIRAチケット：**[LPS-121733](https://issues.liferay.com/browse/LPS-121733)

### 何が変わりましたか？

以前は、動画URLをパラメーターとして`/portal/flash`パブリックパスに渡すことで、Adobe Flash動画を再生できました。 `/portal/flash path`パスは削除されました。

さらに、プロパティとアクセサーは`ThemeDisplay`から削除され、使用できなくなりました。

### 誰が影響を受けますか？

これは、`/c/portal/flash`パスを直接使用してAdobe Flashコンテンツを含むページを表示している場合に影響します。

### コードを更新するにはどうすればよいですか？

コードを直接更新することはできません。 ただし、古い動作をシミュレートするカスタムページを作成できます。 このページでは、URLから動画パラメータを解析し、Adobe Flash再生の一般的な手段を使用して動画をインスタンス化できます。

### なぜこの変更が行われたのですか？

この変更は、2020年12月31日に [AdobeがFlashのサポートを終了](https://www.adobe.com/products/flashplayer/end-of-life.html) し、今後のバージョンのブラウザでFlashのサポートが削除されることに対応したものです。

---------------------------------------

<a name="swfobject-auiモジュールの削除" />

## swfobject AUIモジュールの削除
- **日付：** 2020年13月10日
- **JIRAチケット：**[LPS-121736](https://issues.liferay.com/browse/LPS-121736)

### 何が変わりましたか？

AUIモジュールの`swfobject`は削除されました。 これは、Adobe Flashコンテンツを埋め込むために一般的に使用されるSWFObjectライブラリをロードする方法を提供していました。

### 誰が影響を受けますか？

これは、AUI `swfobject`モジュールを介してSWFObjectライブラリをグローバルに使用できるようにしている場合に影響します。

### コードを更新するにはどうすればよいですか？

それでもAdobe Flashコンテンツを埋め込む必要がある場合は、他の利用可能なメカニズムを使用して、SWFObjectライブラリをアプリケーションに直接挿入できます。

### なぜこの変更が行われたのですか？

この変更は、2020年12月31日に [AdobeがFlashのサポートを終了](https://www.adobe.com/products/flashplayer/end-of-life.html) し、今後のバージョンのブラウザでFlashのサポートが削除されることに対応したものです。

---------------------------------------

<a name="assetentries_assetcategoriesテーブルと対応するコードを削除" />

## AssetEntries_AssetCategoriesテーブルと対応するコードを削除
- **日付：** 2020年16月10日
- **JIRAチケット：**[LPS-89065](https://issues.liferay.com/browse/LPS-89065)

### 何が変わりましたか？

`AssetEntries_AssetCategories`マッピングテーブルとそれに対応するコードが削除されました。

### 誰が影響を受けますか？

これは、`AssetEntryLocalService`と`AssetCategoryLocalService`を使用してアセットエントリーとアセットカテゴリー間の関係を操作している場合に影響します。

### コードを更新するにはどうすればよいですか？

`AssetEntryAssetCategoryRelLocalService`の新しいメソッドを使用してください。 メソッドのシグネチャは、`AssetEntryAssetCategoryRelLocalService`の場合と同じであることに注意してください。

### なぜこの変更が行われたのですか？

この変更により、不要なテーブルと対応するコードが削除されます。

これは、7.2の旧バージョンと非互換の変更「 [Liferay AssetEntries_AssetCategoriesは使用されなくなりました](https://learn.liferay.com/dxp/latest/ja/liferay-internals/reference/7-2-breaking-changes.html#liferay-assetentries-assetcategories-is-no-longer-used) 」に対するフォローアップ手順です。この変更では、テーブルが`AssetEntryAssetCategoryRel`テーブルに置き換えられ、`AssetEntryLocalService`および`AssetCategoryLocalService`の対応するインターフェイスが`AssetEntryAssetCategoryRelLocalService`に移動されました。

---------------------------------------

<a name="antivirusscannerのサポートとclamdの統合をリファクタリング" />

## AntivirusScannerのサポートとClamdの統合をリファクタリング
- **日付：** 2020年10月21日
- **JIRAチケット：**[LPS-122280](https://issues.liferay.com/browse/LPS-122280)

### 何が変わりましたか？

ポータルのClamd統合実装は、Clamdリモートサービスを使用するOSGiサービスに置き換えられました。 AntivirusScanner OSGi統合は、AntivirusScanner実装選択ポータルプロパティおよびAntivirusScanner実装フック登録ポータルプロパティに置き換えられました。

### 誰が影響を受けますか？

これは、ポータルのClamd統合実装を使用している場合、またはフックを介して独自のAntivirusScanner実装を提供している場合に影響します。

### コードを更新するにはどうすればよいですか？

新しいClamd統合とAntivirusScannerサポートを有効にします。 [アップロードされたファイルのウイルス対策スキャンを有効にする](https://learn.liferay.com/dxp/latest/ja/system-administration/file-storage/enabling-antivirus-scanning-for-uploaded-files.html) を参照してください。

フックを介して独自のAntivirusScanner実装を提供している場合は、それをゼロより大きいサービスランキングのOSGiサービスに変換します。 ClamdリモートサービスAntivirusScanner実装サービスのランキングはゼロです。

### なぜこの変更が行われたのですか？

この変更により、コンテナ環境がより適切にサポートされ、APIが統合されてOSGi統合が実行されます。

---------------------------------------

<a name="エンティティ表示ページ登録追跡ロジックを変更" />

## エンティティ表示ページ登録追跡ロジックを変更
- **日付：** 2020年10月27日
- **JIRAチケット：**[LPS-122275](https://issues.liferay.com/browse/LPS-122275)

### 何が変わりましたか？

表示ページとのエンティティの関連付けの保持（追跡）が変更されました。 Liferay DXP/Portalバージョン7.1から7.3では、デフォルト表示ページとのエンティティの関連付けのみが保持されていました。表示ページの関連付けがないエンティティ、および特定のデフォルト以外の表示ページとのエンティティの関連付けは保持されていませんでした。 この変更により、動作が切り替わります。

新しい動作は次のとおりです。

- デフォルト表示ページにリンクされているエンティティは保持されません。
- 表示ページがないエンティティは保持されます。
- デフォルト以外の特定の表示ページを持つエンティティは保持されます。

### 誰が影響を受けますか？

これは、表示ページを作成できるカスタムエンティティがある場合に影響します。

### コードを更新するにはどうすればよいですか？

表示ページを持つカスタムエンティティがある場合は、`BaseUpgradeAssetDisplayPageEntries`アップグレードプロセスをアプリケーションに追加して、表示ページロジックを切り替えます。 プロセスは、テーブル、プライマリーキー列名、およびクラス名を受け取ります。

### なぜこの変更が行われたのですか？

この変更により、表示ページのロジックが全体的な表示ページの概念とより一致するようになります。

---------------------------------------

<a name="廃止予定および未使用のjspタグを削除" />

## 廃止予定および未使用のJSPタグを削除
- **日付：** 2020年11月24日
- **JIRAチケット：**[LPS-112476](https://issues.liferay.com/browse/LPS-112476)

### 何が変わりましたか？

廃止予定で未使用の一連のJSPタグが削除され、使用できなくなりました。 以下のタグが含まれています。

- `clay:table`
- `liferay-ui:alert`
- `liferay-ui:input-scheduler`
- `liferay-ui:organization-search-container-results`
- `liferay-ui:organization-search-form`
- `liferay-ui:ratings`
- `liferay-ui:search-speed`
- `liferay-ui:table-iterator`
- `liferay-ui:toggle-area`
- `liferay-ui:toggle`
- `liferay-ui:user-search-container-results`
- `liferay-ui:user-search-form`

### 誰が影響を受けますか？

これは、削除されたタグのいずれかを使用している場合に影響します。

### コードを更新するにはどうすればよいですか？

代替タグのある削除済みタグについては、7.3 [`liferay-ui.tld`](https://github.com/liferay/liferay-portal/blob/7.3.x/util-taglib/src/META-INF/liferay-ui.tld) を参照してください。 ただし、タグの多くには直接の置き換えはありません。 直接の置き換えがないタグを使用する必要がある場合は、古い実装をコピーして、プロジェクトから直接提供できます。

### なぜこの変更が行われたのですか？

これらのタグは、デフォルトのJSPコンポーネントの提供を明確にし、より小さくてもより高品質のコンポーネントセットの提供に注力するために削除されました。

---------------------------------------

<a name="container-fluid-1280-cssクラスを置き換え" />

## .container-fluid-1280 CSSクラスを置き換え
- **日付：** 2020年11月24日
- **JIRAチケット：**[LPS-123894](https://issues.liferay.com/browse/LPS-123894)

### 何が変わりましたか？

`.container-fluid-1280` CSSクラスが`.container-fluid.container-fluid-max-xl`に置き換えられました。 `.container-fluid-1280`のスタイルを持っていた互換性レイヤーも削除されました。

### 誰が影響を受けますか？

これは、コンテナ要素に`.container-fluid-1280` CSSクラスがある場合に影響します。

### コードを更新するにはどうすればよいですか？

`.container-fluid-1280`の代わりに、Clay `.container-fluid.container-fluid-max-xl`から更新されたCSSクラスを使用します。 または、ClayLayout [コンポーネント](https://clayui.com/docs/components/layout.html) と [TagLibs](https://clayui.com/docs/get-started/using-clay-in-jsps.html#clay-sidebar) を使用します。

### なぜこの変更が行われたのですか？

この変更により、廃止予定のレガシーコードが削除され、コードの一貫性とパフォーマンスが向上します。

---------------------------------------

<a name="cssおよびjavascriptリソースのランタイム縮小化をデフォルトで無効化" />

## CSSおよびJavaScriptリソースのランタイム縮小化をデフォルトで無効化
- **日付：** 2020年11月27日
- **JIRAチケット：**[LPS-123550](https://issues.liferay.com/browse/LPS-123550)

### 何が変わりましたか？

`minifier.enable`ポータルプロパティがデフォルトで`false`になりました。 ランタイム時にCSSおよびJSリソースの縮小化を実行する代わりに、ビルド時に事前に縮小されたリソースを用意しています。 ページスタイルやロジックにユーザーに見える変更はありません。

### 誰が影響を受けますか？

これは、実装がランタイムミニファイア（通常はGoogle Closure Compiler）に依存している場合に影響します。

### コードを更新するにはどうすればよいですか？

以前のタンタイム時の縮小動作を維持する場合は、`minifier.enable`ポータルプロパティを`true`に設定します。

### なぜこの変更が行われたのですか？

フロントエンドのリソースの縮小化をランタイム時からビルド時に移動すると、サーバーの負荷が軽減され、フロントエンドエコシステム内で利用可能な最新の縮小化テクノロジーの使用が容易になります。

---------------------------------------

<a name="soyportletクラスを削除" />

## SoyPortletクラスを削除
- **日付：** 2020年12月9日
- **JIRAチケット：**[LPS-122955](https://issues.liferay.com/browse/LPS-122955)

### 何が変わりましたか？

`SoyPortlet`クラスは削除されました。 このクラスは、ビューがClosure Templates（Soy）によってサポートされているポートレットを実装するために使用されていました。

### 誰が影響を受けますか？

これは、ポートレット開発のベースとして`SoyPortlet`を使用している場合に影響します。

### コードを更新するにはどうすればよいですか？

JSPを使用する`MVCPortlet`などの確立されたアーキテクチャ、または選択した特定のフロントエンドフレームワークを使用して、Soyポートレットを書き直すことを強くお勧めします。

### なぜこの変更が行われたのですか？

これは、フロントエンドの技術提供を簡素化し、市場で需要の高い実績のあるテクノロジーにより焦点を当てる方法として行われました。

利用可能なさまざまなフロントエンドオプションの詳細な調査と分析は、 [The State of Frontend Infrastructure](https://liferay.dev/blogs/-/blogs/the-state-of-frontend-infrastructure) で確認できます。ここでは、Soyから移行する理由についても説明しています。

> Liferayは、Soyが「聖杯」であると信じて数年かけて投資してきました。 クロージャテンプレートをコンパイルする機能により、JSPに匹敵するパフォーマンスが提供され、他のJavaScriptフレームワークの再利用可能なコンポーネントに対応できると信じてきました。 Soyはいくつかの目標を達成することができましたが、当社が望んでいたパフォーマンスを得ることはできませんでしたし、何よりもこの技術を使っているのは当社ぐらいだと感じていました。

---------------------------------------

<a name="サーバー側のclosure-templatessoyのサポートを削除" />

## サーバー側のClosure Templates（Soy）のサポートを削除
- **日付：** 2020年12月14日
- **JIRAチケット：**[LPS-122956](https://issues.liferay.com/browse/LPS-122956)

### 何が変わりましたか？

次のモジュールと、Soyがサーバー側をレンダリングできるようにエクスポートしたクラスは削除されました。
- `portal-template-soy-api`
- `portal-template-soy-impl`
- `portal-template-soy-context-contributor`

移行を簡素化するために、次のモジュールは廃止予定ですが、以前のSoyコンポーネントのクライアント側の初期化を行う目的でのみ使用できます。
- `portal-template-soy-renderer-api`
- `portal-template-soy-renderer-impl`

### 誰が影響を受けますか？

これは、`SoyContext`、`SoyHTMLData`などの削除されたクラスを使用していて、`lang.type`属性の値として`LANG_TYPE_SOY`を使用して`TemplateContextContributor`を宣言している場合に影響します。

これは、Soy `ComponentRenderer`を使用してSoyコンポーネントを初期化している場合に影響します。

### コードを更新するにはどうすればよいですか？

削除されたSoyのサポートに代わるものはありません。 最初のシナリオに当てはまる場合は、サポートされている別のテンプレート言語に切り替えて、テンプレートとコンポーネントを書き換えてください。

`ComponentRenderer`を使用している場合、唯一の違いは、コンポーネントがサーバー側でマークアップを生成しなくなることです。 これが重要な場合は、一時的な回避策が追加されています。 サーバー側でレンダリングするマークアップのバージョンを手動で生成し、`context`パラメーターの`__placeholder__`プロパティとしてマークアップを渡すことができます。 `ComponentRenderer`は廃止予定であり、最終的には削除されることを忘れないでください。そのため、別のテクノロジーを使用してコンポーネントを書き直すことをお勧めします。

### なぜこの変更が行われたのですか？

これは、フロントエンドの技術提供を簡素化し、市場で需要の高い実績のあるテクノロジーにより焦点を当てる方法として行われます。

利用可能なさまざまなフロントエンドオプションの詳細な調査と分析は、 [The State of Frontend Infrastructure](https://liferay.dev/blogs/-/blogs/the-state-of-frontend-infrastructure) で確認できます。ここでは、Soyから移行する理由についても説明しています。

> Liferayは、Soyが「聖杯」であると信じて数年かけて投資してきました。 クロージャテンプレートをコンパイルする機能により、JSPに匹敵するパフォーマンスが提供され、他のJavaScriptフレームワークの再利用可能なコンポーネントに対応できると信じてきました。 Soyはいくつかの目標を達成することができましたが、当社が望んでいたパフォーマンスを得ることはできませんでしたし、何よりもこの技術を使っているのは当社ぐらいだと感じていました。

---------------------------------------

<a name="comliferayportalkernelmodelportletpreferencesメソッドgetpreferencesおよびsetpreferencesを削除" />

## com.liferay.portal.kernel.model.PortletPreferencesメソッド、getPreferencesおよびsetPreferencesを削除
- **日付：** 2020年12月20日
- **JIRAチケット：**[LPS-122562](https://issues.liferay.com/browse/LPS-122562)

### 何が変わりましたか？

ポートレットのルック&フィールは、XMLとして保存および取得されなくなりました。 これらは、プリファレンス名とプリファレンス値用に別々の列を持つ`PortletPreferenceValue`というテーブルに格納されるようになりました。

### 誰が影響を受けますか？

これは、`com.liferay.portal.kernel.model.PortletPreferences`メソッド、`getPreferences`または`setPreferences`を介してポートレットのルック&フィールを直接取得または設定している場合に影響します。

### コードを更新するにはどうすればよいですか？

`PortletPreferencesLocalService`を介して`javax.portlet.PortletPreferences`インスタンスにアクセスします。 `javax.portlet.PortletPreferences` APIを使用してプリファレンスを取得および設定します。

### なぜこの変更が行われたのですか？

この変更により、アップグレードが簡素化され、ストレージ要件が軽減され、`like`演算子を使用せずに設定のクエリがサポートされます。

---------------------------------------

<a name="css互換性レイヤーを削除" />

## CSS互換性レイヤーを削除
- **日付：** 2021-Jan-02
- **JIRAチケット：**[LPS-123359](https://issues.liferay.com/browse/LPS-123359)

### 何が変わりましたか？

Boostrap 3マークアップのサポートは削除され、使用できなくなりました。

### 誰が影響を受けますか？

これは、Boostrap 3マークアップを使用している場合、またはBoostrap 4マークアップに正しく移行していない場合に影響します。

### コードを更新するにはどうすればよいですか？

Clayマークアップを使用している場合は、最新の [Clayコンポーネント](https://clayui.com/docs/components/index.html) バージョンに従って更新できます。 マークアップがBoostrap 3に基づいている場合は、 [Bootstrap移行ガイドライン](https://getbootstrap.com/docs/4.4/migration/) に従ってBoostrap 4マークアップを使用してマークアップを更新できます。

### なぜこの変更が行われたのですか？

構成可能なCSS互換性レイヤーによってLiferay 7.0から7.1への移行が簡素化されていましたが、レイヤーを削除すると、新しいスタイルとの競合が解決され、一般的なCSSの重みが向上します。

---------------------------------------

<a name="log4j-xml定義ファイルからspiidプロパティを削除" />

## Log4j XML定義ファイルからspi.idプロパティを削除
- **日付：** 2021-Jan-19
- **JIRAチケット：**[LPS-125998](https://issues.liferay.com/browse/LPS-125998)

### 何が変わりましたか？

Log4j XML定義ファイルの`spi.id`プロパティが削除されました。

### 誰が影響を受けますか？

これは、カスタムのLog4j XML定義ファイルで`@spi.id@`を使用している場合に影響します。

### コードを更新するにはどうすればよいですか？

Log4j XML定義ファイルから`@spi.id@`を削除します。

### なぜこの変更が行われたのですか？

SPIが [LPS-110758](https://issues.liferay.com/browse/LPS-110758) によって削除されました。

---------------------------------------

<a name="frontend-taglib-clayタグから廃止予定の属性を削除" />

## frontend-taglib-clayタグから廃止予定の属性を削除
- **日付：** 2021-Jan-26
- **JIRAチケット：**[LPS-125256](https://issues.liferay.com/browse/LPS-125256)

### 何が変わりましたか？

廃止予定の属性は`frontend-taglib-clay` TagLibから削除されました。

### 誰が影響を受けますか？

これは、`<clay:*>`タグで廃止予定の属性を使用している場合に影響します。

### なぜこの変更が行われたのですか？

`frontend-taglib-clay`モジュールは、削除された属性をサポートしていない [`Clay v3`](https://github.com/liferay/clay) のコンポーネントを使用するようになりました。

---------------------------------------

<a name="htmlタグのブール属性の処理を変更" />

## HTMLタグのブール属性の処理を変更
- **日付：** 2021-Feb-18
- **JIRAチケット：**[LPS-127832](https://issues.liferay.com/browse/LPS-127832)

### 何が変わりましたか？

ブール型のHTML属性は、`true`の値が渡された場合にのみレンダリングされます。  このような属性の値は、それらの正規名になります。

以前は、`disabled`属性の`false`などの値は、`disabled="false"`としてDOMにレンダリングされていました。現在、属性は省略されています。 同様に、`disabled`属性の`true`値は、以前は`disabled="true"`としてDOMにレンダリングされていました。 現在は、`disabled="disabled"`としてレンダリングされています。

### 誰が影響を受けますか？

これは、次のブール属性をタグライブラリに渡す場合に影響します。

- `"allowfullscreen"`
- `"allowpaymentrequest"`
- `"async"`
- `"autofocus"`
- `"autoplay"`
- `"checked"`
- `"controls"`
- `"default"`
- `"disabled"`
- `"formnovalidate"`
- `"hidden"`
- `"ismap"`
- `"itemscope"`
- `"loop"`
- `"multiple"`
- `"muted"`
- `"nomodule"`
- `"novalidate"`
- `"open"`
- `"playsinline"`
- `"readonly"`
- `"required"`
- `"reversed"`
- `"selected"`
- `"truespeed"`

### コードを更新するにはどうすればよいですか？

DOMに存在させたいブール属性に`true`値を渡すようにしてください。 `true`値（例：`[disabled="true"]`）をターゲットとするCSSセレクターを更新して、属性（例：`[disabled]`）またはその正規名（例：`[disabled="disabled"]`）の存在をターゲットにします。

### なぜこの変更が行われたのですか？

この変更は、 [HTML規格](https://html.spec.whatwg.org/#boolean-attribute) への準拠を強化するために行われました。この規格には、「要素にブール属性が存在する場合はtrueの値を表し、属性が存在しない場合はfalseの値を表す。 属性が存在する場合、その値は 空の文字列か、属性の正規名とASCIIの大文字/小文字を区別せずに一致する値のいずれかでなければならない」とあります。

---------------------------------------

<a name="comliferayportalkernelmodelportalpreferencesメソッドgetpreferencesおよびsetpreferencesを削除" />

## com.liferay.portal.kernel.model.PortalPreferencesメソッド、getPreferencesおよびsetPreferencesを削除
- **日付：** 2020-Mar-31
- **JIRAチケット：**[LPS-124338](https://issues.liferay.com/browse/LPS-124338)

### 何が変わりましたか？

`PortalPreferences`プリファレンスが`PortalPreferenceValue`テーブルに保存されるようになりました。

### 誰が影響を受けますか？

これは、`com.liferay.portal.kernel.model.PortalPreferences`メソッド`getPreferences`または`setPreferences`を介してポータル設定を直接取得または設定している場合に影響します。

### コードを更新するにはどうすればよいですか？

`PortalPreferencesLocalService`を介して`javax.portlet.PortalPreferences`インスタンスにアクセスします。 `javax.portlet.PortalPreferences` APIを使用してプリファレンスを取得および設定します。

### なぜこの変更が行われたのですか？

この変更により、アップグレードが簡素化され、ストレージ要件が軽減され、`like`演算子を使用せずに設定のクエリがサポートされます。

---------------------------------------

<a name="item-selector-taglibはcoverimage関連のイベントを起動しなくなりました" />

## item-selector-taglibはcoverImage関連のイベントを起動しなくなりました
- **日付：** 2021-Apr-15
- **JIRAチケット：**[LPS-130359](https://issues.liferay.com/browse/LPS-130359)

### 何が変わりましたか？

`ImageSelector` JavaScriptモジュールは、`Liferay.fire()` APIを使用して`coverImageDeleted`、`coverImageSelected`、および`coverImageUploaded`イベントを起動しなくなりました。 これらのイベントにより、`item-selector-taglib`モジュールと`blogs-web`モジュール間の通信が容易になりました。  `Liferay.State`は、`imageSelectorCoverImageAtom`を使用して通信を同期するようになりました。

### 誰が影響を受けますか？

これは、`Liferay.on()`または同様の関数を含む削除されたイベントをリッスンしている場合に影響します。

### コードを更新するにはどうすればよいですか？

実際には、これら2つのモジュール間の相互作用を観察するべきではありませんが、必要な場合は、`Liferay.State.subscribe()` APIを使用して`imageSelectorCoverImageAtom`にサブスクライブできます。

### なぜこの変更が行われたのですか？

`Liferay.fire()`と`Liferay.on()`は、共有チャネルでグローバルに表示されるイベントを公開します。 `Liferay.State` APIは、このように離れた場所で調整したいモジュールに適しています。これは、型安全な方法で行われます。

---------------------------------------

<a name="oauth-20トークンインストロスペクション機能識別子を変更" />

## OAuth 2.0トークンインストロスペクション機能識別子を変更
- **日付：** 2021年5月04日
- **JIRAチケット：**[LPS-131573](https://issues.liferay.com/browse/LPS-131573)

### 何が変わりましたか？

OAuth 2.0トークンイントロスペクション機能識別子が`token_introspection`から `token.introspection`に変更されました。

### 誰が影響を受けますか？

これは、トークンイントロスペクション機能識別子を使用している場合に影響します。 ユースケースの一部は以下のとおりです。

- トークンイントロスペクション機能識別子を有効にして、OAuth 2.0アプリケーションをプログラムで追加する。
- OAuth 2.0アプリケーションでトークンイントロスペクション機能識別子が有効になっているかどうかを確認する。

### コードを更新するにはどうすればよいですか？

トークンを`token_introspection`から`token.introspection`に変更します。

### なぜこの変更が行われたのですか？

この変更は、コード内のすべてのOAuth 2.0定数を調整および標準化するために行われました。 機能識別子の単語を区切るには、ドットを使用することをお勧めします。

---------------------------------------

<a name="journalarticleのコンテンツ項目を削除" />

## JournalArticleのコンテンツ項目を削除
- **日付：** 2021年5月21日
- **JIRAチケット：**[LPS-129058](https://issues.liferay.com/browse/LPS-129058)

### 何が変わりましたか？

`JournalArticle`コンテンツは、動的データマッピング（DDM）フィールドサービスによって保存されるようになりました。

### 誰が影響を受けますか？

これは、`JournalArticle`コンテンツフィールドを直接設定している場合に影響します。

### コードを更新するにはどうすればよいですか？

コンテンツ項目を直接設定する代わりに、`JournalArticleLocalService`の更新メソッドを使用します。

### なぜこの変更が行われたのですか？

この変更により、コンテンツをフェッチおよび解析せずに、データベース内のファイル、ページ、およびWebコンテンツのDDMフィールドを参照しやすくなります。

---------------------------------------

<a name="comliferayportalkernelutilstringbundlerをcomliferaypetrastringstringbundlerに置き換え" />

## com.liferay.portal.kernel.util.StringBundlerをcom.liferay.petra.string.StringBundlerに置き換え
- **日付：** 2021-Jun-25
- **JIRAチケット：**[LPS-133200](https://issues.liferay.com/browse/LPS-133200)

### 何が変わりましたか？

`com.liferay.petra.string.StringBundler`クラスは廃止予定になりました。  `com.liferay.portal.kernel.util.StringBundler`クラスに置き換えられました 。

`com.liferay.portal.kernel.util.StringBundler`の代わりに`com.liferay.petra.string.StringBundler`を返すようになったメソッドの一部を以下に示します。

- `com.liferay.frontend.taglib.dynamic.section.BaseJSPDynamicSection.java#modify`
- `com.liferay.frontend.taglib.dynamic.section.DynamicSection#modify`
- `com.liferay.portal.kernel.io.unsync.UnsyncStringWriter#getStringBundler`
- `com.liferay.portal.kernel.layoutconfiguration.util.RuntimePage#getProcessedTemplate`
- `com.liferay.portal.kernel.layoutconfiguration.util.RuntimePageUtil#getProcessedTemplate`
- `com.liferay.portal.kernel.servlet.BufferCacheServletResponse#getStringBundler`
- `com.liferay.portal.kernel.servlet.taglib.BodyContentWrapper.java#getStringBundler`
- `com.liferay.portal.kernel.theme.PortletDisplay#getContent`
- `com.liferay.portal.kernel.util.StringUtil#replaceToStringBundler`
- `com.liferay.portal.kernel.util.StringUtil#replaceWithStringBundler`
- `com.liferay.portal.layoutconfiguration.util.PortletRenderer#render`
- `com.liferay.portal.layoutconfiguration.util.PortletRenderer#renderAjax`
- `com.liferay.portal.layoutconfiguration.util.RuntimePageImpl#getProcessedTemplate`
- `com.liferay.taglib.BaseBodyTagSupport#getBodyContentAsStringBundler`
- `com.liferay.taglib.BodyContentWrapper#getStringBundler`
- `com.liferay.taglib.aui.NavBarTag#getResponsiveButtonsSB`

### 誰が影響を受けますか？

これは、これらのメソッドのいずれかを呼び出している場合に影響します。

### コードを更新するにはどうすればよいですか？

`com.liferay.portal.kernel.util.StringBundler`の代わりに`com.liferay.petra.string.StringBundler`をインポートします。

### なぜこの変更が行われたのですか？

`com.liferay.petra.string.StringBundler`クラスは廃止予定になりました。

---------------------------------------

<a name="userlocalservice関連のクラスがパブリックapiを変更しました" />

## UserLocalService関連のクラスがパブリックAPIを変更しました
- **日付：** 2021年7月7日
- **JIRAチケット：**[LPS-134096](https://issues.liferay.com/browse/LPS-134096)

### 何が変わりましたか？

通常`void`を返す多くのメソッドは、代わりに`boolean`値を返すようになりました。  このリストには次のものが含まれます。

- com.liferay.portal.kernel.service.UserLocalService#addDefaultGroups
- com.liferay.portal.kernel.service.UserLocalService#addDefaultRoles
- com.liferay.portal.kernel.service.UserLocalService#addDefaultUserGroups
- com.liferay.portal.kernel.service.UserLocalServiceUtil#addDefaultGroups
- com.liferay.portal.kernel.service.UserLocalServiceUtil#addDefaultRoles
- com.liferay.portal.kernel.service.UserLocalServiceUtil#addDefaultUserGroups
- com.liferay.portal.kernel.service.UserLocalServiceWrapper#addDefaultGroups
- com.liferay.portal.kernel.service.UserLocalServiceWrapper#addDefaultRoles
- com.liferay.portal.kernel.service.UserLocalServiceWrapper#addDefaultUserGroups
- com.liferay.portal.service.impl.UserLocalServiceImpl#addDefaultGroups
- com.liferay.portal.service.impl.UserLocalServiceImpl#addDefaultRoles
- com.liferay.portal.service.impl.UserLocalServiceImpl#addDefaultUserGroups

### 誰が影響を受けますか？

これらのメソッドのいずれかを呼び出しているすべてのユーザー

### コードを更新するにはどうすればよいですか？

すぐに対処する必要はありませんが、戻り値のタイプが変更されていることに注意することが重要です。

### なぜこの変更が行われたのですか？

この変更は、デフォルトのグループ、ロール、またはユーザーグループが特定のユーザーに追加されたかどうか、またはユーザーがすでにこれらの関連付けを持っているかどうかを確認するために行われました。

---------------------------------------

<a name="frontend-css-web-cssモジュールを削除" />

## frontend-css-web CSSモジュールを削除
- **日付：** 2021-Aug-02
- **JIRAチケット：**[LPS-127085](https://issues.liferay.com/browse/LPS-127085)

### 何が変わりましたか？

`frontend-css-web`モジュールが削除され、そのCSSファイルがアップグレードされました。

### 誰が影響を受けますか？

この変更は、次のモジュールに影響します。

- `modules/apps/asset/asset-taglib/`
- `modules/apps/asset/asset-tags-navigation-web/`
- `modules/apps/captcha/captcha-taglib/`
- `modules/apps/comment/comment-web/`
- `modules/apps/commerce/commerce-product-content-web/`
- `modules/apps/document-library/document-library-web/`
- `modules/apps/dynamic-data-lists/dynamic-data-lists-web/`
- `modules/apps/dynamic-data-mapping/dynamic-data-mapping-form-web/`
- `modules/apps/dynamic-data-mapping/dynamic-data-mapping-web/`
- `modules/apps/flags/flags-taglib/`
- `modules/apps/frontend-css/frontend-css-web/`
- `modules/apps/frontend-editor/frontend-editor-ckeditor-web/`
- `modules/apps/frontend-js/frontend-js-aui-web/`
- `modules/apps/frontend-js/frontend-js-components-web/`
- `modules/apps/frontend-taglib/frontend-taglib/`
- `modules/apps/frontend-theme/frontend-theme-styled/`
- `modules/apps/item-selector/item-selector-taglib/`
- `modules/apps/knowledge-base/knowledge-base-web/`
- `modules/apps/mobile-device-rules/mobile-device-rules-web/`
- `modules/apps/polls/polls-web/`
- `modules/apps/portal-settings/portal-settings-authentication-cas-web/`
- `modules/apps/product-navigation/product-navigation-control-menu-web/`
- `modules/apps/site-navigation/site-navigation-directory-web/`
- `modules/apps/social/social-bookmarks-taglib/`
- `modules/apps/staging/staging-taglib/`
- `modules/apps/wiki/wiki-web/`
- `modules/dxp/apps/portal-search-tuning/portal-search-tuning-rankings-web/`
- `portal-kernel/`
- `portal-web/`

### コードを更新するにはどうすればよいですか？

必要なコードの更新はありません。

### なぜこの変更が行われたのですか？

この変更により、廃止予定のレガシーコードがDXP/Portalから削除され、コードのパフォーマンスとコードの一貫性が向上します。

---------------------------------------

<a name="一部のsanitizedservletresponse静的メソッドhttpheaders-x-xss-protection定数およびhttpheadersecurexxssprotectionポータルプロパティを削除" />

## 一部のSanitizedServletResponse静的メソッド、HttpHeaders X **XSS** PROTECTION定数、およびhttp.header.secure.x.xss.protectionポータルプロパティを削除
- **日付：** 2021-Aug-05
- **JIRAチケット：**[LPS-134188](https://issues.liferay.com/browse/LPS-134188)

### 何が変わりましたか？

次のメソッド、定数、およびポータルプロパティは削除されました。

メソッド:

- `com.liferay.portal.kernel.servlet.SanitizedServletResponse#disableXSSAuditor`
- `com.liferay.portal.kernel.servlet.SanitizedServletResponse#disableXSSAuditor`
- `com.liferay.portal.kernel.servlet.SanitizedServletResponse#disableXSSAuditorOnNextRequest`
- `com.liferay.portal.kernel.servlet.SanitizedServletResponse#disableXSSAuditorOnNextRequest`

定数:

- `com.liferay.portal.kernel.servlet.HttpHeaders#X_XSS_PROTECTION`

ポータルプロパティ:

- `http.header.secure.x.xss.protection`

### 誰が影響を受けますか？

これは、これらのメソッドを呼び出す、定数を使用する、またはポータルプロパティを使用する場合に影響します。

### コードを更新するにはどうすればよいですか？

これらのメソッドを呼び出すか、定数を使用するコードを削除してください。 X-Xss-Protectionヘッダーは、最新のブラウザでは効果がなく、誤った安全性を感じさせる可能性があります。

ポータルプロパティ拡張ファイル（`portal-ext.properties`など）から`http.header.secure.x.xss.protection`プロパティを削除します。

### なぜこの変更が行われたのですか？

X-Xss-Protectionヘッダーは、最新のブラウザではサポートされなくなりました。 これらの静的メソッド、定数、およびポータルプロパティは、X-Xss-Protectionヘッダーに関連しています。

---------------------------------------

<a name="openidconnectservicehandlerインターフェイスをopenidconnectauthenticationhandlerに置き換え" />

## OpenIdConnectServiceHandlerインターフェイスをOpenIdConnectAuthenticationHandlerに置き換え
- **日付：** 2021-Aug-09
- **JIRAチケット：**[LPS-124898](https://issues.liferay.com/browse/LPS-124898)

### 何が変わりましたか？

`OpenIdConnectServiceHandler`インターフェイスは削除され、`OpenIdConnectAuthenticationHandler`インターフェイスに置き換えられました。

旧インターフェイス：

```
portal.security.sso.openid.connect.OpenIdConnectServiceHandler
```

新インターフェイス：

```
portal.security.sso.openid.connect.OpenIdConnectAuthenticationHandler
```

### 誰が影響を受けますか？

これは、`OpenIdConnectServiceHandler`インターフェイスを実装または使用している場合に影響します。

### コードを更新するにはどうすればよいですか？

コードが`OpenIdConnectServiceHandler`インターフェイスを呼び出す場合は、`OpenIdConnectAuthenticationHandler`インターフェイスを呼び出すようにコードを変更します。 これには、DXP/Portalユーザーにサインインするための`UnsafeConsumer`を提供する必要があります。

`OpenIdConnectServiceHandler`インターフェイスを実装している場合は、`OpenIdConnectAuthenticationHandler`インターフェイスを実装し、提供された更新トークンを使用してユーザーのOIDCアクセストークンを更新する方法を提供します。 このプロビジョニングを行わないと、初期アクセストークンの有効期限が切れたときにセッションが無効になります。

### なぜこの変更が行われたのですか？

この変更により、OIDC更新トークンの処理が改善されます。 変更は次の理由で行われました。

- アクセストークンの更新プロセスをHTTPリクエスト処理から切り離すため。 この切り離しができないと、更新トークンの使用を1回だけ許可するプロバイダーとのOIDCセッションを維持する際に問題が発生する可能性があります。 ポータルセッションの早期無効化が発生する可能性があります。

- 対応するアクセストークンと同時に期限切れになる更新トークンを提供するOIDCプロバイダーのポータルセッションの早期無効化を回避するため。

---------------------------------------

<a name="言語キーの名称変更" />

## 言語キーの名称変更

- **日付：** 2021-Sep-09
- **JIRAチケット：** LPS-135504

### 何が変わりましたか？

すべてのモジュール言語キーは、`liferay-[dxp|portal]/modules/apps/portal-language/portal-language-lang`にある`portal-language-lang`というモジュールに移動されました。 モジュールが同じ名前で異なる値の言語キーを使用する場合、異なる値に対応するために言語キーが追加されました。 影響を受けるモジュールの観点から、言語キーの名前が変更されました。

### 誰が影響を受けますか？

これは、名前が変更された言語キーを使用またはオーバーライドしている場合に影響します。 [名前が変更された言語キー](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/upgrading-liferay/reference/renamed-language-keys.html) は、古い言語キー名を新しい名前にマップします。

### コードを更新するにはどうすればよいですか？

[名前が変更された言語キー](https://learn.liferay.com/dxp/latest/en/installation-and-upgrades/upgrading-liferay/reference/renamed-language-keys.html) のマッピングに基づいて、名前が変更された言語キーのすべてのインスタンスの名前を新しい名前に変更します。

### なぜこの変更が行われたのですか？

一元化された言語キーは管理が簡単です。

---------------------------------------

<a name="cas-ssoモジュールをportal-security-sso-casプロジェクトに移動" />

## CAS SSOモジュールをportal-security-sso-casプロジェクトに移動
- **日付：** 2021-Sep-15
- **JIRAチケット：**[LPS-88905](https://issues.liferay.com/browse/LPS-88905)

### 何が変わりましたか？

CAS SSOモジュールは、`portal-security-sso`プロジェクトから`portal-security-sso-cas`という名前の新しいプロジェクトに移動されました。 新しいプロジェクトは廃止予定ですが、Liferay マーケットプレイスからダウンロードできます。

### 誰が影響を受けますか？

これは、認証システムとしてCAS SSOを使用しているすべてのユーザーに影響します。

### コードを更新するにはどうすればよいですか？

認証システムとしてCAS SSOを引き続き使用する場合は、Liferay マーケットプレイスから対応するアプリをダウンロードする必要があります。

### なぜこの変更が行われたのですか？

これは、SSOサポートを統合し、オープンスタンダードへの注目を高めるための継続的な取り組みの一環です。

---------------------------------------

<a name="clayselectタグの名前に印刷用の属性のネームスペースを指定" />

## clay:selectタグの名前に印刷用の属性のネームスペースを指定
- **日付：** 2021-Sep-15
- **JIRAチケット：**[LPS-139131](https://issues.liferay.com/browse/LPS-139131)

### 何が変わりましたか？

`clay:select`の属性`name`に、印刷時にデフォルトでポートレットネームスペース（使用可能な場合）が含まれるようになりました。

### 誰が影響を受けますか？

これは、`clay:select`とその`name`属性を使用してデータサーバー側を処理するすべてのユーザーに影響します。

### コードを更新するにはどうすればよいですか？

値の前に`liferayPortletResponse.getNamespace() + NAME_VALUE`を付けて、自分で属性のネームスペースを設定している場合は、そのプレフィックスを削除して、`name="NAME_VALUE"`を直接使用します。

フルコントロールが必要な場合（またはネームスペースのバージョンが不十分な場合）は、タグに`useNamespace="<%= Boolean.FALSE %>"`を渡すことで古い動作に戻すことができます。

### なぜこの変更が行われたのですか？

この変更は、現在の`aui:select`タグの動作により適合するように、また、今後`clay:select`タグへの移行を容易にし、現在の`clay:select`タグの使用を簡略化するために行われました。

---------------------------------------

<a name="コアレジストリapiおよびレジストリ実装モジュールを削除" />

## コアレジストリAPIおよびレジストリ実装モジュールを削除
- **日付：** 2021-Sep-28
- **JIRAチケット：**[LPS-138126](https://issues.liferay.com/browse/LPS-138126)

### 何が変わりましたか？

コアレジストリAPI（`registry-api`）およびレジストリ実装（`registry-impl`）モジュールが削除されました。

### 誰が影響を受けますか？

これは、レジストリAPIを使用しているすべてのユーザーに影響します。

### コードを更新するにはどうすればよいですか？

システムバンドルの`org.osgi.framework.BundleContext`を使用してネイティブOSGi APIにアクセスします。  `com.liferay.portal.kernel.module.util.SystemBundleUtil.getBundleContext()`メソッドを呼び出して、コンテキストを取得します。

### なぜこの変更が行われたのですか？

ネイティブOSGi APIにより、ブリッジAPIは不要になります。

---------------------------------------