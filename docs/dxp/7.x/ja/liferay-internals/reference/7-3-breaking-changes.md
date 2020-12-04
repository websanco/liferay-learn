# 7.3破壊的な変更

このドキュメントは、サードパーティのLiferay開発者またはユーザーとの既存の機能、API、または契約に違反する変更の時系列リストを示しています。 私たちはこれらの混乱を最小限にするために最善を尽くしていますが、時には避けられないこともあります。

このファイルに記載されている変更の種類の一部を次に示します。

  - 削除または置換される機能
  - APIの非互換性：パブリックJavaまたはJavaScript APIの変更
  - テンプレートで利用可能なコンテキスト変数の変更
  - Liferayテーマおよびポートレットで利用可能なCSSクラスの変更
  - 設定の変更：`portal.properties`、`system.properties`などの設定ファイルの変更
  - 実行要件：Javaバージョン、J2EEバージョン、ブラウザーバージョンなど
  - 非推奨またはサポート終了：たとえば、次のバージョンで特定の機能またはAPIが停止されると警告している
  - 推奨事項：たとえば、後方互換性のためにLiferay Portalに古いAPIが保持されているにもかかわらず、古いAPIに代わる、新しく導入されたAPIを使用することを推奨している

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

<p spaces-before="0">FontAwesomeアイコンhtmlを手動で生成するユーザーは、 <code>Liferayを使用できます。Util.getLexiconIconTpl（ 'user'）` API。 たとえば、前の呼び出しは、ユーザーのsvgアイコンのHTMLコードを返します。

##### JSP内の直接HTMLの場合

直接JSPでアイコンを使用して開発者がいずれかを使用することができる `のLiferay-UIを：アイコン` タグ以上説明したように `粘土：アイコン` つの代わりにSVGベースのアイコンを生成します。

##### 非制御コードの場合

アイコンフォントを使用するコンテンツにアクセスできない場合、またはコードやコンテンツを更新したくない場合は、フォントをテーマに含めることができます。

7.2のアップグレードプロセス中、テーマアップグレードアシスタントは、FontAwesomeをテーマの一部として維持するよう開発者に促します。 アイコンフォントが既に含まれているテーマは影響を受けず、7.3でも引き続き機能します。

#### なぜこの変更が行われたのですか？

この変更は、不要なファイルを提供しないことで帯域幅を節約し、サイトのパフォーマンスを向上させるために行われました。

-----

### liferay.frontendを削除しました。プログレスバー

  - **日付：** 2019-Aug-28
  - **JIRAチケット：** [LPS-100122](https://issues.liferay.com/browse/LPS-100122)

#### 何が変わりましたか？

従来のmetal + soy `liferay.frontend。レガシー動作の一時的なブリッジとして使用されていたProgressBar` コンポーネントは削除されました。

#### 誰が影響を受けますか？

これは `liferay.frontendに依存するすべてのコードに影響します。ProgressBar`;これは通常 `soy` via `{call liferay.frontend。ProgressBar /}`。

#### コードを更新するにはどうすればよいですか？

`liferay.frontendを直接置き換えるものはあり<code>ん。ProgressBar` コンポーネント。 依存するコンポーネントがある場合は、古い実装のコピーを同じ場所に配置して、モジュール内でローカルに使用できます。

#### なぜこの変更が行われたのですか？

`liferay.frontend.ProgressBar` コンポーネントは7.2で非推奨になり、使用されなくなりました。

-----

### liferay.frontendを削除しました。スライダー

  - **日付：** 2019年10月10日
  - **JIRAチケット：** [LPS-100124](https://issues.liferay.com/browse/LPS-100124)

#### 何が変わりましたか？

従来のmetal + soy `liferay.frontend。従来の動作の一時的なブリッジとして使用されていたSlider` コンポーネントが削除されました。

#### 誰が影響を受けますか？

これは `liferay.frontendに依存するすべてのコードに影響します。スライダー`;これは通常 `soy` via `{call liferay.frontend。スライダー/}`。

#### コードを更新するにはどうすればよいですか？

`liferay.frontendを直接置き換えるものはありません。スライダー` コンポーネント。 依存するコンポーネントがある場合は、古い実装のコピーを同じ場所に配置して、モジュール内でローカルに使用できます。

#### なぜこの変更が行われたのですか？

`liferay.frontend.Slider` コンポーネントは7.2で廃止され、現在は使用されていません。

-----

### com.liferay.asset.taglib.servlet.taglib.soyを削除しました。AssetTagsSelectorTag

  - **日付：** 2019-Oct-15
  - **JIRAチケット：** [LPS-100144](https://issues.liferay.com/browse/LPS-100144)

#### 何が変わりましたか？

Javaクラス `com.liferay.asset.taglib.servlet.taglib.soy。AssetTagsSelectorTag` が削除されました。

#### 誰が影響を受けますか？

これは、このクラスを直接インスタンス化または拡張するコードに影響します。

#### コードを更新するにはどうすればよいですか？

削除されたクラスを直接置き換えるものはありません。 依存するコードがある場合は、古い実装を独自のプロジェクトにコピーし、ローカルバージョンに依存するように依存関係を変更する必要があります。

#### なぜこの変更が行われたのですか？

`asset：asset-tags-selector` とそのコンポーネントはReactに移行され、古いタグとその大豆インフラストラクチャが不要になりました。

-----

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

-----

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

-----

### 置き換えられたOSGi構成プロパティーautoUpgrade

  - **日付：** 2020-Jan-03
  - **JIRAチケット：** [LPS-102842](https://issues.liferay.com/browse/LPS-102842)

#### 何が変わりましたか？

`com.liferay.portal.upgrade.internal.configurationで定義されているOSGiプロパティ <code>autoUpgrade`。ReleaseManagerConfiguration.config</code>で定義されている、OSGiプロパティポータルプロパティ  <0>autoUpgrade</0>は`upgrade.database.auto.run`に置き換えられました。

モジュールのアップグレードプロセスのみを制御していた古いプロパティとは異なり、新しいプロパティはコアアップグレードプロセスにも影響します。 デフォルト値は `false`なので、起動時またはモジュールのデプロイメント時にアップグレードプロセスは実行されません。 Gogoコンソールからいつでもモジュールのアップグレードプロセスを実行できます。

#### 誰が影響を受けますか？

これは、新しいプロセスがデプロイされたときにアップグレードを実行したくない開発環境に影響します。 このプロパティは、本番環境では `true` に設定できません。 これらの場合、アップグレードツールを使用して、スキーマバージョンのマイナーおよびメジャー変更を実行する必要があります。

#### コードを更新するにはどうすればよいですか？

この変更はコードには影響しません。

#### なぜこの変更が行われたのですか？

この変更は、コアとモジュール間の自動アップグレード機能を統合するために行われました。 本番環境での起動時に新しいアップグレードプロセスが実行されないように、デフォルト値も変更されました。

-----

### 削除されたキャッシュブートストラップ機能

  - **日付：** 2020-Jan-8
  - **JIRAチケット：** [LPS-96563](https://issues.liferay.com/browse/LPS-96563)

#### 何が変わりましたか？

キャッシュブートストラップ機能は削除されました。 これらのプロパティは、キャッシュブートストラップを有効化/構成するために使用できなくなりました。

`ehcache.bootstrap.cache.loader.enabled`、 `ehcache.bootstrap.cache.loader.properties.default`、 `ehcache.bootstrap.cache.loader.properties。${specific.cache.name}`。

#### 誰が影響を受けますか？

これは、上記のプロパティを使用するすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

削除された機能を直接置き換えるものはありません。 それに依存するコードがある場合は、自分で実装する必要があります。

#### なぜこの変更が行われたのですか？

この変更は、セキュリティの問題を回避するために行われました。

-----

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

-----

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

-----

### 変更されたコントロールメニューと製品メニューの配置

  - **日付：** 2020年2月4日
  - **JIRAチケット：** [LPS-107487](https://issues.liferay.com/browse/LPS-107487)

#### 何が変わりましたか？

コントロールと製品メニューの配置と構造は、いくつかのアクセシビリティの問題と一般的な視覚的な不具合に対処するために変更されました。

これらの変更は、コントロールメニューと製品メニューに適用されています。

  - 製品メニューはコントロールメニューの外に移動されました
  - コントロールメニューは、動作を制御するために `位置：スティッキー` を使用するようになりました。
  - コントロールメニュー内のメニューのスタイルは、新しいスティッキー動作を考慮して更新されました

#### 誰が影響を受けますか？

これは、カスタマイズされた `portlet.ftl` テンプレートを使用したカスタムコントロールパネルテーマを使用している開発者、またはスティッキーバーとして動作し、 `* ControlMenuEntry` APIを使用して含まれているカスタムメニューを開発した開発者に影響を与える可能性があります。

#### コードを更新するにはどうすればよいですか？

##### コントロールパネルのテーマ

カスタムのコントロールパネルテーマを使用する開発者は、（存在する場合）呼び出しを `portlet.ftl`ポートレットセクションの上の `@ liferay.control_menu` マクロに移動する必要があります。

**前：**

``` markup
<section class="portlet" id="portlet_${htmlUtil.escapeAttribute(portletDisplay.getId())}">
    $ {portletDisplay.writeContent（writer）}
</section>

<#if portletDisplay.isStateMax（）>
    <@ liferay.control_menu />
</＃if>
```

**後：**

``` markup
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

-----

### jQueryはデフォルトで含まれなくなりました

  - **日付：** 2020年2月4日
  - **JIRAチケット：** [LPS-95726](https://issues.liferay.com/browse/LPS-95726)

#### 何が変わりましたか？

以前は、デフォルトで `jQuery` がすべてのページに含まれ、グローバル `window。$` およびスコープ付き `AUI。$` 変数を介して使用可能になりました。 この変更後、 `jQuery` はデフォルトで含まれなくなり、それらの変数は `未定義`です。

#### 誰が影響を受けますか？

これは、カスタムスクリプトで `AUI.$` または `window.$` を使用した開発者に影響します。

#### コードを更新するにはどうすればよいですか？

コードで使用する独自のバージョンのJQueryを提供するサードパーティライブラリを追加するための戦略のいずれかを使用します。

さらに、一時的な対策として、*システム設定*→*サードパーティ*→*jQuery*の`jQueryを有効にする`プロパティを`true`に設定することで、以前の動作に戻すことができます。

#### なぜこの変更が行われたのですか？

この変更は、ほとんどの場合未使用で冗長なすべてのページに追加のライブラリコードをバンドルして提供することを回避するために行われました。

-----

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

-----

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
  - ブラウザで <https://repository.liferay.com/nexus/index.html> を開きます。
  - `com.liferay.frontend.editor.simple.web`検索します。
  - `com.liferay.frontend.editor.simple.web` モジュールの.jarファイルをダウンロードします。
  - ダウンロードした.jarファイルをliferay-portalインストールにデプロイします。

#### なぜこの変更が行われたのですか？

この変更は、1つのエディターを中心にリッチテキストコンテンツを作成するためのすべてのUXを統合して、よりまとまりのある包括的なエクスペリエンスを提供するために行われました。

-----

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
  - ブラウザで <https://repository.liferay.com/nexus/index.html> を開きます。
  - `com.liferay.frontend.editor.tinymce.web`を検索します。
  - `com.liferay.frontend.editor.tinymce.web` モジュールの.jarファイルをダウンロードします。
  - ダウンロードした.jarファイルをliferay-portalインスタレーションにデプロイします。

#### なぜこの変更が行われたのですか？

この変更は、1つのエディターを中心にリッチテキストコンテンツを作成するためのすべてのUXを統合して、よりまとまりのある包括的なエクスペリエンスを提供するために行われました。

-----

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

-----

### Liferay.ポーラーがデフォルトで初期化されなくなった

  - **日付：** 2020年5月19日
  - **JIRAチケット：** [LPS-112942](https://issues.liferay.com/browse/LPS-112942)

#### 何が変わりましたか？

グローバルAUI `Liferay。Poller` ユーティリティは廃止され、デフォルトでは初期化されなくなりました。

#### 誰が影響を受けますか？

これは、 `Liferayに依存するすべてのコードに影響します。ポーラー`;これは通常、 `Liferayの呼び出しを介して行われます。JSPのPoller.init（）`

#### コードを更新するにはどうすればよいですか？

`Liferayを直接置き換えるものはありません。ポーラー` ユーティリティ。 `Liferayを初期化する必要がある場合。ポーラー`、以下のコードを使用するようにJSPを更新します。

``` markup
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

`LiferayPoller` コンポーネントは、アーカイブされているチャットアプリケーションでのみ使用されていました。 デフォルトで初期化をスキップすると、一般的なケースでページの読み込みが合理化されます。

-----

### ContentTransformerListenerはデフォルトで無効になっています

  - **日付：** 2020年5月25日
  - **JIRAチケット：** [LPS-114239](https://issues.liferay.com/browse/LPS-114239)

#### 何が変わりましたか？

`ContentTransformerListener` はデフォルトで無効になりました。

#### 誰が影響を受けますか？

これは、`ContentTransformerListener`によって提供されるレガシーなWebコンテンツ機能を使用しているLiferay Portalのインストールに影響します。例えば、別のWebコンテンツ内にWebコンテンツを埋め込む、レガシーなエディット・イン・プレース・インフラストラクチャ、トークンの置換(`@article_group_id@`, `@articleId;elementName@`)などです。

#### コードを更新するにはどうすればよいですか？

コードを更新する必要はありません。 それでも `ContentTransformerListener` を使用したい場合は、システム設定で *Content & Data* → *Web Content* → *Virtual Instance Scope* → *Web Content* の *Enable ContentTransformerListener* プロパティを使用して有効にすることができます。

#### なぜこの変更が行われたのですか？

`ContentTransformerListener`は、記事要素の文字列処理にコストがかかるため、パフォーマンスを向上させるために無効化されました（記事フィールドに対して`HtmlUtil.stripComments`と`HtmlUtil.stripHtml`を呼び出しています）。

-----

### Liferay.BrowserSelectors.runが呼び出されなくなった

  - **日付：** 2020年5月26日
  - **JIRAチケット：** [LPS-112983](https://issues.liferay.com/browse/LPS-112983)

#### 何が変わりましたか？

`Liferay.BrowserSelectors.run（）` 関数がページで呼び出されなくなりました。その結果、一部のCSSクラスが開始 `<html>` タグから削除されます。 これらの多くは、代わりに `<body>` 要素に追加されています 。

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

`Liferayを直接置き換えるものはありません。BrowserSelectors.run（）` 関数ですが、CSSとJavaScriptを調整して、代わりに `<body>` 要素の新しいクラスをターゲットにすることができます。 これらのクラスは、現在使用しているブラウザーを反映するために `<body>` 要素に追加されます。

  - `Chrome`
  - `Edge`
  - `Firefox`
  - `IE`
  - `モバイル`
  - `その他`

または、 `Liferayを呼び出すこともできます。BrowserSelectors.run（）` は、以下のコードで古いクラスを `<html>` 要素に適用します。

    <aui:script use="liferay-browser-selectors">
        Liferay.BrowserSelectors.run();
    </aui:script>

#### なぜこの変更が行われたのですか？

一部のクラスが古いブラウザを参照していたクラスは、Alloy UIに依存するレガシーJavaScriptを介して上位 `<html>` 要素に追加されていました。 古いブラウザー参照を削除するこの変更は、サーバー側で行われるようになり、ページの読み込み時間が改善されました。

-----

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

-----

### 各エンティティモデルのキャッシュプロパティ設定のサポートを削除

  - **日付：** 2020-Jun-24
  - **JIRAチケット：** [LPS-116049](https://issues.liferay.com/browse/LPS-116049)

#### 何が変わりましたか？

エンティティのこれらのキャッシュプロパティを設定するためのサポートが削除されました：

  - `value.object.entity.cache.enabled *`
  - `value.object.finder.cache.enabled *`
  - `value.object.column.bitmask.enabled *`

たとえば、これらのプロパティはエンティティ `com.liferay.portal.kernel.modelに対するものです。ユーザー`：

  - `value.object.entity.cache.enabled.com.liferay.portal.kernel.model.ユーザー`
  - `value.object.finder.cache.enabled.com.liferay.portal.kernel.model.User`
  - `value.object.column.bitmask.enabled.com.liferay.portal.kernel.model.User`

#### 誰が影響を受けますか？

これは、エンティティに対して上記のプロパティを使用するすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

削除された機能を直接置き換えるものはありません。 エンティティからこれらのプロパティを削除する必要があります。

#### なぜこの変更が行われたのですか？

## これらのプロパティはエンティティには役に立たないため、この変更が行われました。
