# 破壊的変更

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

## 重大な変更リスト

### テーマでのJSPテンプレートのサポートの削除

  - **日付：** 2018-Nov-14
  - **JIRAチケット：** [LPS-87064](https://issues.liferay.com/browse/LPS-87064)

#### 何が変わりましたか？

テーマはJSPテンプレートを利用できなくなりました。 また、関連するロジックがパブリックAPI `com.liferay.portal.kernel.utilから削除されました。ThemeHelper` および `com.liferay.taglib.util。ThemeUtil`。

#### 誰が影響を受けますか？

これは、JSPテンプレートを使用するテーマを持っているか、削除されたメソッドを使用している人に影響します。

#### コードを更新するにはどうすればよいですか？

JSPテンプレートを使用するテーマがある場合は、FreeMarkerへの移行を検討してください。

#### なぜこの変更が行われたのですか？

JSPは実際のテンプレートエンジンではなく、めったに使用されません。 FreeMarkerは、今後推奨されるテンプレートエンジンです。

JSPテンプレートが削除されたことで、既存および新規のテンプレートエンジンにさらに焦点を合わせることができます。

-----

### Lodashはデフォルトで含まれなくなりました

  - **日付：** 2018-Nov-27
  - **JIRAチケット：** [LPS-87677](https://issues.liferay.com/browse/LPS-87677)

#### 何が変わりましたか？

以前は、Lodashはデフォルトですべてのページに含まれ、グローバル `window._` およびスコープ `AUI._` 変数を介して利用可能になりました。 Lodashはデフォルトでは含まれなくなり、これらの変数は未定義になります。

#### 誰が影響を受けますか？

これは、カスタムスクリプトで `AUI._` または `window._` 変数を使用した開発者に影響します。

#### コードを更新するにはどうすればよいですか？

カスタム開発用に独自のLodashバージョンを提供して、サードパーティのライブラリを追加するための可能な戦略のいずれかを使用する必要があります。

一時的な手段として、Liferayポータルの *コントロールパネルの* → *構成* → *システム設定* → *サードパーティ* → *Lodash* から `true`*Enable Lodash* プロパティを設定することにより、以前の動作に戻すことができます。

#### なぜこの変更が行われたのですか？

この変更は、ほとんどの場合未使用で冗長なすべてのページに追加のライブラリコードをバンドルして提供することを回避するために行われました。

-----

### 2つのステージングプロパティをOSGi構成に移動

  - **日付：** 2018-Dec-12
  - **JIRAチケット：** [LPS-88018](https://issues.liferay.com/browse/LPS-88018)

#### 何が変わりましたか？

2つのステージングプロパティが `portal.properties` から `export-import-service` モジュールの `ExportImportServiceConfiguration.java` という名前のOSGi構成に移動されました。

#### 誰が影響を受けますか？

これは、次のポータルプロパティを使用しているすべてのユーザーに影響します。

  - `staging.delete.temp.lar.on.failure`
  - `staging.delete.temp.lar.on.success`

#### コードを更新するにはどうすればよいですか？

`portal.properties` ファイルを上書きする代わりに、ポータルの構成管理者からプロパティを管理できます。 これにアクセスするには、Liferayポータルの *コントロールパネル* → *構成* → *システム設定* → *インフラストラクチャ* → *エクスポート/インポート* に移動し、そこで設定を編集します。

アプリケーションに新しい構成を含める場合は、 [の手順に従って、アプリケーションを構成可能にします](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/making-applications-configurable)。

#### なぜこの変更が行われたのですか？

この変更は、ポータル構成の変更を容易にするためのモジュール化の取り組みの一環として行われました。

-----

### ページの機能へのリンクアプリケーションURLの削除

  - **日付：** 2018-Dec-14
  - **JIRAチケット：** [LPS-85948](https://issues.liferay.com/browse/LPS-85948)

#### 何が変わりましたか？

ルックアンドフィールポートレットの *Link Portlet URLs to Page* オプションは、Liferay Portal 7.1で非推奨としてマークされ、ユーザーは構成プロパティを介してオプションを表示および非表示にできます。 Liferay Portal 7.2では、これは削除され、構成できなくなりました。

#### 誰が影響を受けますか?

これは、UIのオプションを使用した管理者と、ポートレットのオプションを利用した開発者に影響します。

#### コードを更新するにはどうすればよいですか？

プロパティへの事前設定された参照はポータルでは無視されるため、この機能を活用するすべてのポートレットを更新する必要があります。

#### なぜこの変更が行われたのですか？

限られた数のポートレットがこのプロパティを使用します。同じ結果を達成するためのより良い方法があります。

-----

### TermsOfUseContentProviderをkernel.utilから移動

  - **日付：** 2019-Jan-07
  - **JIRAチケット：** [LPS-88869](https://issues.liferay.com/browse/LPS-88869)

#### 何が変わりましたか？

`TermsOfUseContentProvider` インターフェースのパッケージが変更されました：

`com.liferay.portal.kernel.util` → `com.liferay.portal.kernel.term.of.use`

`TermsOfUseContentProviderRegistryUtil` クラスの名前とパッケージが変更されました：

`TermsOfUseContentProviderRegistryUtil` → `TermsOfUseContentProviderUtil`

および `com.liferay.portal.kernel.util` → `com.liferay.portal.internal.terms.of.use`

`TermsOfUseContentProvider` を取得するロジックも変更されました。 登録されたサービスの順序に依存するランダムな最初のサービスを常に返すのではなく、 `TermsOfUseContentProvider` サービスが追跡され、 `com.liferay.portal.kernel.utilで更新されます。ServiceProxyFactory`. その結果、 `TermsOfUseContentProvider` はサービスランキングを尊重するようになりました。

#### 誰が影響を受けますか？

これは、 `com.liferay.portal.kernel.util.TermsOfUseContentProviderRegistryUtil`を使用して`com.liferay.portal.kernel.util.TermsOfUseContentProvider`サービスを検索した場合に影響します。

#### コードを更新するにはどうすればよいですか？

`com.liferay.portal.kernel.util　の場合TermsOfUseContentProvider` が使用されています。インポートパッケージ名を更新してください。 `portal-web`に使用がある場合は、 `com.liferay.portal.kernel.utiTermsOfUseContentProviderRegistryUtil` から `com.liferay.portal.kernel.term.of.use.TermsOfUseContentProviderUtil`.を更新します。 `com.liferay.portal.kernel.utilの使用を削除します。TermsOfUseContentProviderRegistryUtil` をモジュールで使用し、 `@Reference` アノテーションを使用して `com.liferay.portal.kernel.term.of.useTermsOfUseContentProvider` サービスを代わりにフェッチします。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

-----

### HibernateConfigurationConverterおよびConverterを削除

  - **日付：** 2019-Jan-07
  - **JIRAチケット：** [LPS-88870](https://issues.liferay.com/browse/LPS-88870)

#### 何が変わりましたか？

インターフェース `com.liferay.portal.kernel.util。コンバーター` とその実装 `com.liferay.portal.spring.hibernate。HibernateConfigurationConverter` は削除されました。

#### 誰が影響を受けますか？

これにより、 `HibernateConfigurationConverter`によって実装されたカスタマイズされた `portlet-hbm.xml` ファイルの生成のサポートが削除されます。 詳細については、 [LPS-5363](https://issues.liferay.com/browse/LPS-5363) を参照してください。

#### コードを更新するにはどうすればよいですか？

`HibernateConfigurationConverter`使用を削除する必要があります。 生成された `portlet-hbm.xml` が正確であることを確認してください。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

-----

### JDK関数とサプライヤーを使用するように切り替え

  - **日付：** 2019-Jan-08
  - **JIRAチケット：** [LPS-88911](https://issues.liferay.com/browse/LPS-88911)

#### 何が変わりましたか？

パッケージ `com.liferay.portal.kernel.util` の `Function` および `Supplier` インターフェイスが削除されました。 それらの使用法は`java.util.functionで置き換えられました。関数` および `java.util.function。サプライヤー`に置き換えられました。

#### 誰が影響を受けますか？

これは、パッケージ `com.liferay.portal.kernel.util``Function` および `Supplier` インターフェースを実装したすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

` com.liferay.portal.kernel.util関数` および `com.liferay.portal.kernel.util.の使用を置き換える必要があります。<code>java.util.functionを持つサプライヤー`。関数</code> および `java.util.function。それぞれサプライヤー`。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

-----

### 非推奨のcom.liferay.portal.service。InvokableServiceインターフェース

  - **日付：** 2019-Jan-08
  - **JIRAチケット：** [LPS-88912](https://issues.liferay.com/browse/LPS-88912)

#### 何が変わりましたか？

パッケージ `com.liferay.portal.kernel.service` の `InvokableService` および `InvokableLocalService` インターフェースが削除されました。

#### 誰が影響を受けますか？

これは、パッケージ `com.liferay.portal.kernel.service`で `InvokableService` および `InvokableLocalService` を使用したすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

`InvokableService` および `InvokableLocalService`使用を削除する必要があります。 削除後にコンパイルエラーが発生した場合に備えて、最新バージョンのService Builderを使用してサービスの実装を生成してください。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

-----

### ServiceLoaderConditionのサポートを終了

  - **日付：** 2019-Jan-08
  - **JIRAチケット：** [LPS-88913](https://issues.liferay.com/browse/LPS-88913)

#### 何が変わりましたか？

インターフェース `ServiceLoaderCondition` とその実装 `DefaultServiceLoaderCondition` （パッケージ `com.liferay.portal.kernel.util` は削除されました。

#### 誰が影響を受けますか？

これは、使用して誰に影響 `ServiceLoaderCondition` 及び `DefaultServiceLoaderCondition`。

#### コードを更新するにはどうすればよいですか？

`ServiceLoaderCondition`使用を削除する必要があります。 `com.liferay.portal.kernel.utilの <code>load` メソッドの使用法を更新します。更新されたメソッドシグネチャに応じたServiceLoader</code>。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

-----

### JDK述語を使用するように切り替え

  - **日付：** 2019-Jan-14
  - **JIRAチケット：** [LPS-89139](https://issues.liferay.com/browse/LPS-89139)

#### 何が変わりましたか？

インターフェース `com.liferay.portal.kernel.util.PredicateFilter` は削除され、 `java.util.function変数`に置き換えられます。 その結果、次の実装が削除されました。

  - `com.liferay.portal.kernel.util。AggregatePredicateFilter`
  - `com.liferay.portal.kernel.util。PrefixPredicateFilter`
  - `com.liferay.portal.kernel.portlet.JavaScriptPortletResourcePredicateFilter`
  - `com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormFieldValuePredicateFilter`

`com.liferay.portal.kernel.util.ArrayUtil_IW` クラスが再生成されました。

#### 誰が影響を受けますか？

これは、使用誰影響 `PredicateFilter`、 `AggregatePredicateFilter`、 `PrefixPredicateFilter`、 `JavaScriptPortletResourcePredicateFilter`、及び `DDMFormFieldValuePredicateFilter`。

#### コードを更新するにはどうすればよいですか？

`使用を置き換える必要があり<code> com.liferay.portal.kernel.util。PredicateFilter` と `java.util.function。述語`。 さらに、用途の除去 `AggregatePredicateFilter`、 `PrefixPredicateFilter`、 `JavaScriptPortletResourcePredicateFilter`、及び `DDMFormFieldValuePredicateFilter`。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

-----

### パッケージcom.liferay.portal.kernel.utilの安全でない機能インターフェイスを削除

  - **日付：** 2019-Jan-15
  - **JIRAチケット：** [LPS-89223](https://issues.liferay.com/browse/LPS-89223)

#### 何が変わりましたか？

`com.liferay.portal.osgi.util.test.OSGiServiceUtil` クラスが削除されました。 また、次のインターフェースが `com.liferay.portal.kernel.util` パッケージから削除されました`</p>

<ul>
<li><code>UnsafeConsumer`</li>
  - `UnsafeFunction`
  - `UnsafeRunnable`</ul>

#### 誰が影響を受けますか？

これは、上記のクラス/インターフェースを使用するすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

`com.liferay.portal.osgi.util.test.OSGiServiceUtil` クラスは、Liferay Portal 7.1以降廃止されました。 このクラスの使用法がまだ存在する場合は、直接の置き換えで置き換えます： `com.liferay.osgi.util.service.OSGiServiceUtil`。 用途交換 `UnsafeConsumer`、 `UnsafeFunction` 及び `UnsafeRunnable` パッケージの対応するインターフェイスと `com.liferay.petra.function`。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

-----

### ポータル配布での非推奨のNTLM

  - **日付：** 2019-Jan-21
  - **JIRAチケット：** [LPS-88300](https://issues.liferay.com/browse/LPS-88300)

#### 何が変わりましたか？

NTLMモジュールは、 `portal-security-sso` プロジェクトから `portal-security-sso-ntlm`という名前の新しいプロジェクトに移動されました。 この新しいプロジェクトは廃止され、Liferay Marketplaceからダウンロードできます。

#### 誰が影響を受けますか？

これは、NTLMを認証システムとして使用しているすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

NTLMを認証システムとして引き続き使用する場合は、Liferay Marketplaceから対応するモジュールをダウンロードする必要があります。 または、Kerberosに移行することもできます（推奨）。これは、変更の必要がなく、Liferay Portal 7.0以降と互換性があります。

#### なぜこの変更が行われたのですか？

この変更は、古い専用ソリューション（NTLM）の使用を回避するために行われました。 現在、Kerberosが推奨されています。これは、標準プロトコルであり、NTLMと比較してより安全な認証方法です。

-----

### ポータル配布での非推奨のOpenID

  - **日付：** 2019-Jan-21
  - **JIRAチケット：** [LPS-88906](https://issues.liferay.com/browse/LPS-88906)

#### 何が変わりましたか？

OpenIDモジュールは、 `portal-security-sso-openid`という名前の新しいプロジェクトに移動されました。 この新しいプロジェクトは廃止され、Liferay Marketplaceからダウンロードできます。

#### 誰が影響を受けますか？

これは、OpenIDを認証システムとして使用するすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

OpenIDを認証システムとして引き続き使用する場合は、Liferay Marketplaceから対応するモジュールをダウンロードする必要があります。 または、Liferayポータル配布で入手できるOpenID Connectに移行する必要があります。

#### なぜこの変更が行われたのですか？

この変更は、非推奨のソリューション（OpenID）の使用を回避するために行われました。 OpenID Connectが推奨されます。これは、OAuthの上で実行されるため、より安全な認証方法です。

-----

### ポータル配布での非推奨のGoogle SSO

  - **日付：** 2019-Jan-21
  - **JIRAチケット：** [LPS-88905](https://issues.liferay.com/browse/LPS-88905)

#### 何が変わりましたか？

Google SSOモジュールは、 `portal-security-sso` プロジェクトから `portal-security-sso-google`という名前の新しいプロジェクトに移動されました。 この新しいプロジェクトは廃止され、Liferay Marketplaceからダウンロードできます。

#### 誰が影響を受けますか？

これは、認証システムとしてGoogle SSOを使用しているすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

認証システムとしてGoogle SSOを引き続き使用する場合は、Liferay Marketplaceから対応するモジュールをダウンロードする必要があります。 または、OpenID Connectを使用することもできます。

#### なぜこの変更が行われたのですか？

この変更は、認証（Google SSO）に古いソリューションを使用しないようにするために行われました。 OpenID Connectは、認証にGoogle実装を使用するための推奨仕様です。

-----

### 更新されたAlloyEditor v2.0には、Reactの新しいメジャーバージョンが含まれています

  - **日付：** 2019-Feb-04
  - **JIRAチケット：** [LPS-90079](https://issues.liferay.com/browse/LPS-90079)

#### 何が変わりましたか？

AlloyEditorがバージョン2.0.0にアップグレードされました。これには、React v15からv16へのメジャーアップグレードが含まれています。

React.createClass</code> の `は、React v15.5.0</a> （2017年4月）で <a href="https://reactjs.org/blog/2017/04/07/react-v15.5.0.html">廃止され、 <a href="https://reactjs.org/blog/2017/09/26/react-v16.0.html">がReact v16.0.0</a> （2017年9月）で削除されました。 AlloyEditorにバンドルされているすべてのボタンが更新され、 <code>React.createClass`ではなくES6クラス構文を使用するようになりました。

#### 誰が影響を受けますか？

これは、 `React.createClass`を使用して独自のボタンを作成したすべてのユーザーに影響します。 `createClass` 関数は使用できなくなり、実行時にアクセスしようとするとエラーが発生します。

#### コードを更新するにはどうすればよいですか？

次の2つの方法のいずれかでコードを更新する必要があります。

  - ポートカスタムボタン `React.createClass` APIはES6使用する `クラス` で説明したように、APIを [ドキュメント反応させ、](https://reactjs.org/docs/react-component.html)。 たとえば、以前の `createClass`ベースの実装</a>からから [ES6クラスベースのボタン](https://github.com/liferay/alloy-editor/blob/b082c312179ae6626cb2ddcc04ad3ebc5b355e1b/src/components/buttons/button-ol.jsx) に移動したときに加えられた変更を確認してください。</p></li> 
    
      - 互換性アダプタを提供します。 [create-react-classパッケージ](https://www.npmjs.com/package/create-react-class) （ここでは [について説明](https://reactjs.org/docs/react-without-es6.html)）をページに挿入して、 `createClass` APIを復元できます。</ul> 



#### なぜこの変更が行われたのですか？

この変更は、Reactの新しいメジャーバージョンを使用するために行われました。これにより、パフォーマンスと互換性が向上し、廃止されたAPIが削除されてバンドルサイズが削減されます。



-----



### 非推奨のdl.tabs.visibleプロパティ

  - **日付：** 2019-Apr-10
  - **JIRAチケット：** [LPS-93948](https://issues.liferay.com/browse/LPS-93948)



#### 何が変わりましたか？

`dl.tabs.visible` プロパティを使用すると、ユーザーはウィジェットページに配置されたときに、ドキュメントとメディアウィジェットのナビゲーションタブの表示を切り替えることができます。 この構成オプションは削除されたため、ナビゲーションページがウィジェットページに表示されることはありません。



#### 誰が影響を受けますか？

これは、 `dl.tabs.visible` プロパティを `true`に設定したすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

コードを変更する必要はありません。



#### なぜこの変更が行われたのですか？

Documents & MediaはUXの観点から見直され、ウィジェットページのナビゲーションタブの削除はUIクリーンアッププロセスの一部でした。



-----



### ユーザーメニューを製品メニューから移動する

  - **日付：** 2019年4月19日
  - **JIRAチケット：** [LPS-87868](https://issues.liferay.com/browse/LPS-87868)



#### 何が変わりましたか？

ユーザーメニューは製品メニューから削除され、ユーザーメニューエントリは、ユーザーのアバターによってトリガーされるドロップダウンメニューである新しいパーソナルメニューに移動されました。



#### 誰が影響を受けますか？

これは、製品メニューのユーザーメニューセクションをカスタマイズしたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

カスタムユーザーメニューエントリを保持し、それらをパーソナルメニューで使用できるようにするには、 `PersonalMenuEntry` インターフェイスを実装する必要があります。 全てのパネルは、で登録アプリ `PanelCategoryKeys.USER`、 `PanelCategoryKeys.USER_MY_ACCOUNT`、及び `PanelCategoryKeys.USER_SIGN_OUT` パネルカテゴリキーはに変換されなければならない `PersonalMenuEntry`。



#### なぜこの変更が行われたのですか？

製品ナビゲーションはUXの観点から見直されており、製品メニューからユーザーメニューを削除し、メニューを独自のメニューに分割することで、ユーザーエクスペリエンスが向上します。



-----



### 国のリストから香港とマカオを削除

  - **日付：** 2019-Apr-26
  - **JIRAチケット：** [LPS-82203](https://issues.liferay.com/browse/LPS-82203)



#### 何が変わりましたか？

香港とマカオは国のリストから削除され、中国の地域としてそれぞれXianggang（地域コード：CN-91）およびAomen（地域コード：CN-92）としてリストされています。



#### 誰が影響を受けますか？

これは、住所で香港またはマカオを使用したすべての人に影響します。



#### コードを更新するにはどうすればよいですか？

コードを変更する必要はありません。 ただし、コードに香港とマカオの `countryId` をハードコーディングしている場合は、中国の `countryId`更新する必要があります。 香港とマカオへの参照は、対応する `regionId`で行う必要があります。



#### なぜこの変更が行われたのですか？

1997年に香港が、1999年にマカオが引き渡された後、香港とマカオは現在、中国の特別行政区です。



-----



### JGroupsが3.6.16から4.1.1にアップグレードされました

  - **日付：** 2019-Aug-15
  - **JIRAチケット：** [LPS-97897](https://issues.liferay.com/browse/LPS-97897)



#### 何が変わりましたか？

JGroupsがバージョン3.6.16から4.1.1にアップグレードされました。



#### 誰が影響を受けますか？

これは、クラスターリンクを使用しているすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`portal.properties` の `cluster.link.channel.properties。*` プロパティは、接続文字列を値として受け入れなくなりました。現在は、構成XMLファイルへのファイルパスが必要です。 3.6.16の一部のプロトコルプロパティは削除され、4.1.1では解析されなくなりました。それに応じてプロトコルのプロパティを更新する必要があります。



#### なぜこの変更が行われたのですか？

このアップグレードは、セキュリティの問題を修正するために行われました。



-----



### Liferay `AssetEntries_AssetCategories` は使用されなくなりました

  - **日付：** 2019-Sep-11
  - **JIRAのチケット：** [LPS-99973](https://issues.liferay.com/browse/LPS-99973)、 [LPS-76488](https://issues.liferay.com/browse/LPS-76488)



#### 何が変わりましたか？

以前は、Liferayは、 `AssetEntryLocalService` と `AssetCategoryLocalService``AssetEntry` と `AssetCategory` 間の関係にマッピングテーブルと対応するインターフェイスを使用していました。 このマッピングテーブルと対応するインターフェイスは、テーブル `AssetEntryAssetCategoryRel` とサービス `AssetEntryAssetCategoryRelLocalService`置き換えられました。



#### 誰が影響を受けますか？

これは、 `AssetEntryLocalService` および `AssetCategoryLocalService`を通じて、 `AssetEntries_AssetCategories` 関係の古いインターフェースの呼び出しに依存するコンテンツまたはコードに影響します。



#### コードを更新するにはどうすればよいですか？

`AssetEntryAssetCategoryRelLocalService` 新しいメソッドを使用して、以前と同じデータを取得します。 メソッドのシグネチャは変更されていません。彼らはちょうど別のサービスに移転されました。

**例**

古い方法：



``` java
リスト<AssetEntry> エントリ=
AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries（categoryId）;

（AssetEntryエントリー：エントリー）{
...
}
```


新しい方法：



``` java
long [] assetEntryPKs =
_assetEntryAssetCategoryRelLocalService.getAssetEntryPrimaryKeys（assetCategoryId）; （long assetEntryPK：assetEntryPKs）に対して

{
  AssetEntry = _assetEntryLocalService.getEntry（assetEntryPK）;
...
}

...

@Reference
プライベートAssetEntryAssetCategoryRelLocalService _assetEntryAssetCategoryRelLocalService;

@参照
プライベートAssetEntryLocalService _assetEntryLocalService;
```




#### なぜこの変更が行われたのですか？

この変更は、 [LPS-76488](https://issues.liferay.com/browse/LPS-76488)に起因する変更が原因で行われました。これにより、開発者は特定のカテゴリのアセットのリストの順序を制御できます。



-----



### 自動タグ付けは手動で再構成する必要があります

  - **日付：2019年10月2日**
  - **JIRAチケット：** [LPS-97123](https://issues.liferay.com/browse/LPS-97123)



#### 何が変わりましたか？

自動タグ設定の名前が変更され、再編成されました。 自動アップグレードプロセスはなくなったため、自動タグ付けを手動で再設定する必要があります。



#### 誰が影響を受けますか？

これは、SP1にアップグレードされ、自動タグ付けが構成および有効化されているDXP 7.2インストールに影響します。



#### コードを更新するにはどうすればよいですか？

システム設定で自動タグ付けを再設定する必要があります（詳細については、 [公式ドキュメント](https://help.liferay.com/hc/en-us/articles/360029041551-Configuring-Asset-Auto-Tagging) を参照してください）。 古い構成インターフェースを参照するコードは、新しいものを使用するように更新する必要があります。



#### なぜこの変更が行われたのですか？

この変更により、以前に分割された構成インターフェースが統合され、ユーザーエクスペリエンスが向上します。



-----



### ブログの画像プロパティがシステム設定に移動されました

  - **日付：2019年10月2日**
  - **JIRAチケット：** [LPS-95298](https://issues.liferay.com/browse/LPS-95298)



#### 何が変わりましたか？

ブログの画像構成が `portal.properties` からシステム設定に移動されました。 自動アップグレードプロセスがないため、カスタムのブログ画像のプロパティを手動で再設定する必要があります。



#### 誰が影響を受けますか？

これは、SP1にアップグレードされ、 `blogs.image.max.size` および `blogs.image.extensions` プロパティのカスタム値を持つDXP 7.2インストールに影響します。



#### コードを更新するにはどうすればよいですか？

カスタムのブログ画像のプロパティ値を保持したい場合は、システム設定の *設定* → *ブログ* → *ファイルアップロード*再設定する必要があります。 新しいプロパティを使用するには、古いプロパティを参照するコードを更新する必要があります。



#### なぜこの変更が行われたのですか？

この変更は、再起動せずにブログの画像プロパティを設定できるように行われたものです。



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
