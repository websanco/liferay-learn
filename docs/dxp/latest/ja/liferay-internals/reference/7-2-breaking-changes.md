# 7.2破壊的な変更

このドキュメントは、サードパーティのLiferay開発者またはユーザーとの既存の機能、API、または契約に違反する変更の時系列リストを示しています。 私たちはこれらの混乱を最小限にするために最善を尽くしていますが、時には避けられないこともあります。

このファイルに記載されている変更の種類の一部を次に示します。

* 削除または置換される機能
* APIの非互換性：パブリックJavaまたはJavaScript APIの変更
* テンプレートで利用可能なコンテキスト変数の変更
* Liferayテーマおよびポートレットで利用可能なCSSクラスの変更
* 設定の変更：`portal.properties`、`system.properties`などの設定ファイルの変更
* 実行要件：Javaバージョン、Java EEバージョン、ブラウザーバージョンなど
* 非推奨またはサポート終了：たとえば、次のバージョンで特定の機能またはAPIが停止されると警告している
* 推奨事項：たとえば、後方互換性のためにLiferay Portalに古いAPIが保持されているにもかかわらず、古いAPIに代わる、新しく導入されたAPIを使用することを推奨している

## 破壊的変更の一覧

### テーマでのJSPテンプレートのサポートの削除
- **日付：** 2018-Nov-14
- **JIRAチケット：** [LPS-87064](https://issues.liferay.com/browse/LPS-87064)

#### 何が変わりましたか？

テーマはJSPテンプレートを利用できなくなりました。 また、関連するロジックは、パブリックAPI `com.liferay.portal.kernel.util.ThemeHelper`および`com.liferay.taglib.util.ThemeUtil`から削除されました。

#### 誰が影響を受けますか？

これは、JSPテンプレートを使用するテーマを持っているか、削除されたメソッドを使用している人に影響します。

#### コードを更新するにはどうすればよいですか？

JSPテンプレートを使用するテーマがある場合は、FreeMarkerへの移行を検討してください。

#### なぜこの変更が行われたのですか？

JSPは実際のテンプレートエンジンではなく、めったに使用されません。 FreeMarkerは、今後推奨されるテンプレートエンジンです。

JSPテンプレートが削除されたことで、既存および新規のテンプレートエンジンにさらに焦点を合わせることができます。

---------------------------------------

### Lodashはデフォルトで含まれなくなりました
- **日付：** 2018-Nov-27
- **JIRAチケット：** [LPS-87677](https://issues.liferay.com/browse/LPS-87677)

#### 何が変わりましたか？

以前は、Lodashはデフォルトですべてのページに含まれ、グローバル `window._` およびスコープ `AUI._` 変数を介して利用可能になりました。 Lodashはデフォルトでは含まれなくなり、これらの変数は未定義になります。

#### 誰が影響を受けますか？

これは、カスタムスクリプトで `AUI._` または `window._` 変数を使用した開発者に影響します。

#### コードを更新するにはどうすればよいですか？

カスタム開発用に独自のLodashバージョンを提供して、サードパーティのライブラリを追加するための可能な戦略のいずれかを使用する必要があります。

一時的な対策として、Liferayポータルのコントロールパネルの*［コントロールパネル］* &rarr; *［設定］* &rarr; *［システム設定］* &rarr; *［サードパーティー］* &rarr; *［Lodash］*で*［Lodashを有効にする］*プロパティを`true`に設定することで、以前の動作に戻すことができます。

#### なぜこの変更が行われたのですか？

この変更は、ほとんどの場合未使用で冗長なすべてのページに追加のライブラリコードをバンドルして提供することを回避するために行われました。

---------------------------------------

### 2つのステージングポータルプロパティをOSGi構成に移動
- **日付：** 2018-Dec-12
- **JIRAチケット：** [LPS-88018](https://issues.liferay.com/browse/LPS-88018)

#### 何が変わりましたか？

2つのステージングプロパティが `portal.properties` から `export-import-service` モジュールの `ExportImportServiceConfiguration.java` という名前のOSGi構成に移動されました。

#### 誰が影響を受けますか？

これは、次のポータルプロパティを使用しているすべてのユーザーに影響します。

- `staging.delete.temp.lar.on.failure`
- `staging.delete.temp.lar.on.success`

#### コードを更新するにはどうすればよいですか？

`portal.properties` ファイルを上書きする代わりに、ポータルの構成管理者からプロパティを管理できます。 これにアクセスするには、Liferayポータルの*［コントロールパネル］* &rarr; *［設定］* &rarr; *［システム設定］* &rarr; *［Infrastructure］* &rarr; *［エクスポート / インポート］*に移動し、そこで設定を編集します。

アプリケーションに新しい構成を含める場合は、 [の手順に従って、アプリケーションを構成可能にします](https://dev.liferay.com/develop/tutorials/-/knowledge_base/7-1/making-applications-configurable)。

#### なぜこの変更が行われたのですか？

この変更は、ポータル構成の変更を容易にするためのモジュール化の取り組みの一環として行われました。

---------------------------------------

### ページの機能へのリンクアプリケーションURLを削除
- **日付：** 2018-Dec-14
- **JIRAチケット：** [LPS-85948](https://issues.liferay.com/browse/LPS-85948)

#### 何が変わりましたか？

ルックアンドフィールポートレットの *Link Portlet URLs to Page* オプションは、Liferay Portal 7.1で非推奨としてマークされ、ユーザーは構成プロパティを介してオプションを表示および非表示にできます。 Liferay Portal 7.2では、これは削除され、構成できなくなりました。

#### 誰が影響を受けますか？

これは、UIのオプションを使用した管理者と、ポートレットのオプションを利用した開発者に影響します。

#### コードを更新するにはどうすればよいですか？

プロパティへの事前設定された参照はポータルでは無視されるため、この機能を活用するすべてのポートレットを更新する必要があります。

#### なぜこの変更が行われたのですか？

限られた数のポートレットがこのプロパティを使用します。同じ結果を達成するためのより良い方法があります。

---------------------------------------

### TermsOfUseContentProviderをkernel.utilから移動
- **日付：** 2019-Jan-07
- **JIRAチケット：** [LPS-88869](https://issues.liferay.com/browse/LPS-88869)

#### 何が変わりましたか？

`TermsOfUseContentProvider` インターフェースのパッケージが変更されました：

`com.liferay.portal.kernel.util` &rarr; `com.liferay.portal.kernel.term.of.use`

`TermsOfUseContentProviderRegistryUtil` クラスの名前とパッケージが変更されました：

`TermsOfUseContentProviderRegistryUtil` &rarr; `TermsOfUseContentProviderUtil`

そして

`com.liferay.portal.kernel.util` &rarr; `com.liferay.portal.internal.terms.of.use`

`TermsOfUseContentProvider` を取得するロジックも変更されました。 登録されたサービスの順序に依存するランダムな最初のサービスを常に返すのではなく、 `TermsOfUseContentProvider` サービスが追跡され、 `com.liferay.portal.kernel.util.ServiceProxyFactory`で更新されます。 その結果、 `TermsOfUseContentProvider` はサービスランキングを尊重するようになりました。

#### 誰が影響を受けますか？

これは、`com.liferay.portal.kernel.util.TermsOfUseContentProviderRegistryUtil`を使用して`com.liferay.portal.kernel.util.TermsOfUseContentProvider`サービスを検索していたすべての人に影響します。

#### コードを更新するにはどうすればよいですか？

`com.liferay.portal.kernel.util.TermsOfUseContentProvider`を使用する場合は、インポートパッケージ名を更新してください。 `portal-web`で何か使用している場合は、`com.liferay.portal.kernel.util.TermsOfUseContentProviderRegistryUtil`を`com.liferay.portal.kernel.term.of.use.TermsOfUseContentProviderUtil`に更新してください。 モジュール内の`com.liferay.portal.kernel.util.TermsOfUseContentProviderRegistryUtil`の使用を削除し、代わりに`@Reference`アノテーションを使用して`com.liferay.portal.kernel.term.of.use.TermsOfUseContentProvider`サービスをフェッチします。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

---------------------------------------

### HibernateConfigurationConverterおよびConverterを削除
- **日付：** 2019-Jan-07
- **JIRAチケット：** [LPS-88870](https://issues.liferay.com/browse/LPS-88870)

#### 何が変わりましたか？

インターフェイス`com.liferay.portal.kernel.util.Converter`とその実装`com.liferay.portal.spring.hibernate.HibernateConfigurationConverter`が削除されました。

#### 誰が影響を受けますか？

これにより、 `HibernateConfigurationConverter`によって実装されたカスタマイズされた `portlet-hbm.xml` ファイルの生成のサポートが削除されます。 詳細は、 [LPS-5363](https://issues.liferay.com/browse/LPS-5363) を参照してください。

#### コードを更新するにはどうすればよいですか？

`HibernateConfigurationConverter`使用を削除する必要があります。 生成された `portlet-hbm.xml` が正確であることを確認してください。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

---------------------------------------

### JDK関数とサプライヤーを使用するように切り替え
- **日付：** 2019-Jan-08
- **JIRAチケット：** [LPS-88911](https://issues.liferay.com/browse/LPS-88911)

#### 何が変わりましたか？

パッケージ `com.liferay.portal.kernel.util` の `Function` および `Supplier` インターフェイスが削除されました。 それらの使用は、`java.util.function.Function`および`java.util.function.Supplier`に置き換えられました。

#### 誰が影響を受けますか？

これは、パッケージ `com.liferay.portal.kernel.util``Function` および `Supplier` インターフェースを実装したすべてのユーザーに影響します。

#### コードを更新するにはどうすればよいですか？

`com.liferay.portal.kernel.util.Function`と`com.liferay.portal.kernel.util.Supplier`の使用を、それぞれ`java.util.function.Function`と`java.util.function.Supplier`に置き換える必要があります。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

---------------------------------------

### com.liferay.portal.service.InvokableServiceインターフェイスを廃止
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

---------------------------------------

### ServiceLoaderConditionのサポートを終了
- **日付：** 2019-Jan-08
- **JIRAチケット：** [LPS-88913](https://issues.liferay.com/browse/LPS-88913)

#### 何が変わりましたか？

インターフェース `ServiceLoaderCondition` とその実装 `DefaultServiceLoaderCondition` （パッケージ `com.liferay.portal.kernel.util` は削除されました。

#### 誰が影響を受けますか？

これは、使用して誰に影響 `ServiceLoaderCondition` 及び `DefaultServiceLoaderCondition`。

#### コードを更新するにはどうすればよいですか？

`ServiceLoaderCondition`使用を削除する必要があります。 更新されたメソッドシグネチャに従って、`com.liferay.portal.kernel.util.ServiceLoader`の`load`メソッドの使用を更新します。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

---------------------------------------

### JDK述語を使用するように切り替え
- **日付：** 2019-Jan-14
- **JIRAチケット：** [LPS-89139](https://issues.liferay.com/browse/LPS-89139)

#### 何が変わりましたか？

インターフェイス`com.liferay.portal.kernel.util.PredicateFilter`が削除され、`java.util.function.Predicate`に置き換えられました。 その結果、次の実装が削除されました。

- `com.liferay.portal.kernel.util.AggregatePredicateFilter`
- `com.liferay.portal.kernel.util.PrefixPredicateFilter`
- `com.liferay.portal.kernel.portlet.JavaScriptPortletResourcePredicateFilter`
- `com.liferay.dynamic.data.mapping.form.values.query.internal.model.DDMFormFieldValuePredicateFilter`

`com.liferay.portal.kernel.util.ArrayUtil_IW` クラスが再生成されました。

#### 誰が影響を受けますか？

これは、使用誰影響 `PredicateFilter`、 `AggregatePredicateFilter`、 `PrefixPredicateFilter`、 `JavaScriptPortletResourcePredicateFilter`、及び `DDMFormFieldValuePredicateFilter`。

#### コードを更新するにはどうすればよいですか？

`com.liferay.portal.kernel.util.PredicateFilter`の使用を`java.util.function.Predicate`に置き換える必要があります。 さらに、用途の除去 `AggregatePredicateFilter`、 `PrefixPredicateFilter`、 `JavaScriptPortletResourcePredicateFilter`、及び `DDMFormFieldValuePredicateFilter`。

#### なぜこの変更が行われたのですか？

これは、カーネルプロバイダーインターフェイスをクリーンアップして、パッケージバージョンのロックダウンの可能性を減らすためのいくつかの手順の1つです。

---------------------------------------

### com.liferay.portal.kernel.utilパッケージから安全でない機能インターフェイスを削除しました
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

---------------------------------------

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

---------------------------------------

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

---------------------------------------

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

---------------------------------------

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



---------------------------------------



### dl.tabs.visibleポータルプロパティは非推奨になりました

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



---------------------------------------



### ユーザーメニューを製品メニューから移動

- **日付：** 2019年4月19日
- **JIRAチケット：** [LPS-87868](https://issues.liferay.com/browse/LPS-87868)



#### 何が変わりましたか？

ユーザーメニューはプロダクトメニューから削除され、ユーザーメニューエントリは、ユーザーのアバターによってトリガーされるドロップダウンメニューである新しいパーソナルメニューに移動されました。



#### 誰が影響を受けますか？

これは、プロダクトメニューのユーザーメニューセクションをカスタマイズしたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

カスタムユーザーメニューエントリを保持し、それらをパーソナルメニューで使用できるようにするには、 `PersonalMenuEntry` インターフェイスを実装する必要があります。 全てのパネルは、で登録アプリ `PanelCategoryKeys.USER`、 `PanelCategoryKeys.USER_MY_ACCOUNT`、及び `PanelCategoryKeys.USER_SIGN_OUT` パネルカテゴリキーはに変換されなければならない `PersonalMenuEntry`。



#### なぜこの変更が行われたのですか？

製品ナビゲーションはUXの観点から見直されており、プロダクトメニューからユーザーメニューを削除し、メニューを独自のメニューに分割することで、ユーザーエクスペリエンスが向上します。



---------------------------------------



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



---------------------------------------



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



---------------------------------------



### Liferay AssetEntries_AssetCategories は使用されなくなりました

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



```java
List<AssetEntry> entries =
AssetEntryLocalServiceUtil.getAssetCategoryAssetEntries(categoryId);

for (AssetEntry entry: entries) {
  ...
}
```


新しい方法：



```java
long ［］ assetEntryPKs =
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



---------------------------------------



### 自動タグ付けは手動で再構成する必要があります

- **日付：** 2019年02月10日
- **JIRAチケット：** [LPS-97123](https://issues.liferay.com/browse/LPS-97123)



#### 何が変わりましたか？

自動タグ設定の名前が変更され、再編成されました。 自動アップグレードプロセスはなくなったため、自動タグ付けを手動で再設定する必要があります。



#### 誰が影響を受けますか？

これは、SP1にアップグレードされ、自動タグ付けが構成および有効化されているDXP 7.2インストールに影響します。



#### コードを更新するにはどうすればよいですか？

システム設定で自動タグ付けを再設定する必要があります（詳細は、 [公式ドキュメント](https://help.liferay.com/hc/en-us/articles/360029041551-Configuring-Asset-Auto-Tagging) を参照してください）。 古い構成インターフェースを参照するコードは、新しいものを使用するように更新する必要があります。



#### なぜこの変更が行われたのですか？

この変更により、以前に分割された構成インターフェースが統合され、ユーザーエクスペリエンスが向上します。



---------------------------------------



### ブログ画像ポータルプロパティをシステム設定に移動

- **日付：** 2019年02月10日
- **JIRAチケット：** [LPS-95298](https://issues.liferay.com/browse/LPS-95298)



#### 何が変わりましたか？

ブログの画像構成が `portal.properties` からシステム設定に移動されました。 自動アップグレードプロセスがないため、カスタムのブログ画像のプロパティを手動で再設定する必要があります。



#### 誰が影響を受けますか？

これは、SP1にアップグレードされ、 `blogs.image.max.size` および `blogs.image.extensions` プロパティのカスタム値を持つDXP 7.2インストールに影響します。



#### コードを更新するにはどうすればよいですか？

カスタムブログ画像のプロパティ値を保持する場合は、*［設定］* &rarr; *［ブログ］* &rarr; *［ファイルアップロード］*の［システム設定］で再構成する必要があります。 新しいプロパティを使用するには、古いプロパティを参照するコードを更新する必要があります。



#### なぜこの変更が行われたのですか？

この変更は、再起動せずにブログの画像プロパティを設定できるように行われたものです。



---------------------------------------



### キャッシュブートストラップ機能を削除

- **日付：** 2020-Jan-08
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



---------------------------------------



### ContentTransformerListenerをデフォルトで無効化

- **日付：** 2020年5月25日
- **JIRAチケット：** [LPS-114239](https://issues.liferay.com/browse/LPS-114239)



#### 何が変わりましたか？

`ContentTransformerListener` はデフォルトで無効になりました。



#### 誰が影響を受けますか？

これは、`ContentTransformerListener`によって提供されるレガシーなWebコンテンツ機能を使用したLiferay Portalのインストールに影響します。例えば、別のWebコンテンツ内にWebコンテンツを埋め込む、レガシーなエディット・イン・プレース・インフラストラクチャ、トークンの置換(`@article_group_id@`、`@articleId;elementName@`)などです。



#### コードを更新するにはどうすればよいですか？

コードを更新する必要はありません。 それでも`ContentTransformerListener`を使用する場合は、システム設定で有効にできます。



#### なぜこの変更が行われたのですか？

`ContentTransformerListener`は、記事要素に対して多くの文字列プロセスを実行します（記事フィールドに対して`HtmlUtil.stripComments`および`HtmlUtil.stripHtml`を呼び出します）。 パフォーマンスを向上させるために無効にされました。



---------------------------------------



### DDMDataProviderのメソッドを置き換え

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-81563](https://issues.liferay.com/browse/LPS-81563)



#### 何が変わりましたか？

`com.liferay.dynamic.data.mapping.data.provider.DataProvider`の`getData`メソッドの`ddmDataProviderContext`パラメーター（`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderContext`型）が、`ddmDataProviderRequest`（`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest`型）に置き換えられました。



#### 誰が影響を受けますか？

これは、置き換えられたメソッドを使用するすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderContext`型のパラメーターを、`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest`型のパラメーターに置き換えます。



#### なぜこの変更が行われたのですか？

この変更は、[LPS-81563](https://issues.liferay.com/browse/LPS-81563)でのデータプロバイダーAPIリファクタリングの一部です。



---------------------------------------



### DDMDataProviderRequestのコンストラクターを削除

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-81563](https://issues.liferay.com/browse/LPS-81563)



#### 何が変わりましたか？

コンストラクターメソッドが`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest`から削除されました。



#### 誰が影響を受けますか？

これは、削除されたコンストラクターを使用するすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest.Builder`を使用して、コンストラクターではなく、必要なすべてのパラメーターを使用して新しい`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest`を作成します。



#### なぜこの変更が行われたのですか？

この変更は、[LPS-81563](https://issues.liferay.com/browse/LPS-81563)でのデータプロバイダーAPIリファクタリングの一部です。



---------------------------------------



### DDMDataProviderRequestのメソッドを削除

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-81563](https://issues.liferay.com/browse/LPS-81563)



#### 何が変わりましたか？

次のメソッドが`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest`から削除されました。

- `getDDMDataProviderContext`
- `setDDMDataProviderContext`
- `getHttpServletRequest`
- `getParameter`
- `queryString`



#### 誰が影響を受けますか？

これは、削除されたメソッドを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest`および`com.liferay.dynamic.data.mapping.data.provider.internal.DDMDataProviderInstanceSettingsImpl`を使用して、`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderContext`によって提供されるデータを取得します。

また、メソッド`withParameter`を使用して`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest.Builder`を介して`javax.servlet.http.HttpServletRequest`オブジェクトを追加し、メソッド`getParameterOptional`を使用してそれを取得します。

`getParameter`の代わりに既存のメソッド`getParameterOptional`を使用します。 `queryString`の使用を`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest.Builder`のメソッド`withParameter`に置き換えて、必要なすべてのパラメーターを追加します。 



#### なぜこの変更が行われたのですか？

この変更は、[LPS-81563](https://issues.liferay.com/browse/LPS-81563)でのデータプロバイダーAPIリファクタリングの一部です。



---------------------------------------



### DDMDataProviderRequestのメソッドを置き換え

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-81563](https://issues.liferay.com/browse/LPS-81563)



#### 何が変わりましたか？

`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest`の`getDDMDataProviderInstanceId`メソッドが`getDDMDataProviderId`に置き換えられました。



#### 誰が影響を受けますか？

これは、置き換えられたメソッドを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`getDDMDataProviderInstanceId`の使用を`getDDMDataProviderId`に置き換えます。



#### なぜこの変更が行われたのですか？

この変更は、[LPS-81563](https://issues.liferay.com/browse/LPS-81563)でのデータプロバイダーAPIリファクタリングの一部です。



---------------------------------------



### DDMDataProviderResponseのメソッドを削除

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-81563](https://issues.liferay.com/browse/LPS-81563)



#### 何が変わりましたか？

メソッド`error`、`of`、および`getDataMap`がクラス`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse`から削除されました。



#### 誰が影響を受けますか？

これは、削除されたメソッドを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

コードで次の更新されたメソッドを使用します。

- `error`メソッドを呼び出す代わりに、`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse.Builder`の`withStatus`メソッドを使用します。
- `of`メソッドを`Builder`の`withStatus`および`withOutput`メソッドに置き換えます。
- `Builder`の`withOutput`メソッドを使用して`getDataMap`呼び出しを出力追加に置き換え、メソッド`getOutputOptional`を介して取得します。

メソッド`withOutput`は、必要な回数だけ呼び出すことができます。 



#### なぜこの変更が行われたのですか？

この変更は、[LPS-81563](https://issues.liferay.com/browse/LPS-81563)でのデータプロバイダーAPIリファクタリングの一部です。



---------------------------------------



### DDMDataProviderResponseのメソッドを置き換え

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-81563](https://issues.liferay.com/browse/LPS-81563)



#### 何が変わりましたか？

`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse`の`get`メソッドが`getOutputOptional`に置き換えられました。



#### 誰が影響を受けますか？

これは、置き換えられたメソッドを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`get`の代わりに`getOutputOptional`を使用します。



#### なぜこの変更が行われたのですか？

この変更は、[LPS-81563](https://issues.liferay.com/browse/LPS-81563)でのデータプロバイダーAPIリファクタリングの一部です。



---------------------------------------



### DDMDataProviderResponseのEnumを置き換え

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-81563](https://issues.liferay.com/browse/LPS-81563)



#### 何が変わりましたか？

ローカルenum `com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse.Status`が、`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse`から`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponseStatus`に移動されました その結果、`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse`からの`getStatus`のデータ型が、`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse.Status`から`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponseStatus`に変更されました。 



#### 誰が影響を受けますか？

これは、置き換えられたenumを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponse.Status`の使用を`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponseStatus`に置き換えます。



#### なぜこの変更が行われたのですか？

この変更は、[LPS-81563](https://issues.liferay.com/browse/LPS-81563)でのデータプロバイダーAPIリファクタリングの一部です。



---------------------------------------



### DDMDataProviderResponseOutputを削除

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-81563](https://issues.liferay.com/browse/LPS-81563)



#### 何が変わりましたか？

`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponseOutput`が削除されました。



#### 誰が影響を受けますか？

これは、削除されたクラスを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

削除されたクラスを直接置き換えるものはありません。 それに依存するコードがある場合は、自分で実装する必要があります。



#### なぜこの変更が行われたのですか？

クラス`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderResponseOutput`は、[LPS-81563](https://issues.liferay.com/browse/LPS-81563)のデータプロバイダーコードに改善が実装された後に使用されなくなったため、廃止予定の警告なしに削除されました。



---------------------------------------



### DDMDataProviderTrackerのメソッドを削除

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-81563](https://issues.liferay.com/browse/LPS-81563)



#### 何が変わりましたか？

`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderTracker`の`getDDMDataProviderContextContributors`メソッドが削除されました。



#### 誰が影響を受けますか？

これは、削除されたメソッドを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`getDDMDataProviderContextContributors`の代わりに`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest`および`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderInstanceSettings`を使用して、必要なデータを取得します。



#### なぜこの変更が行われたのですか？

`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderContext`クラスによって提供されるすべてのデータは、クラス`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderRequest`および`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderInstanceSettings`にあります。 したがって、クラス`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderContext`および`com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderContextContributor`は不要になりました。  `com.liferay.dynamic.data.mapping.data.provider.DDMDataProviderContextContributor`がLiferay Portal 7.2で削除されたことに注意してください。 



---------------------------------------



### DDMFormInstanceRecordLocalServiceのメソッドを置き換え

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-81564](https://issues.liferay.com/browse/LPS-81564)



#### 何が変わりましたか？

この変更は、`com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalService`、`com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceUtil`、および`com.liferay.dynamic.data.mapping.service.DDMFormInstanceRecordLocalServiceWrapper`に対して行われました。

`getDDMFormValues`メソッドには`ddmStorageId`（`long`型）と呼ばれる単一のパラメーターがありましたが、現在は2つのパラメーター`storageId`（`ddmStorageId`の置換）と`ddmForm`（`com.liferay.dynamic.data.mapping.model.DDMForm`型）があります。 



#### 誰が影響を受けますか？

これは、置き換えられたメソッドを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

メソッドに`com.liferay.dynamic.data.mapping.model.DDMForm`型の新しいパラメーターを渡します。



#### なぜこの変更が行われたのですか？

この変更は、[LPS-81564](https://issues.liferay.com/browse/LPS-81564)でのストレージアダプタAPIリファクタリングの一部です。



---------------------------------------



### DDMStructureServiceのメソッドを削除

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-91760](https://issues.liferay.com/browse/LPS-91760)



#### 何が変わりましたか？

以下にリストされているメソッドが、`com.liferay.dynamic.data.mapping.service.DDMStructureService`、`com.liferay.dynamic.data.mapping.service.DDMStructureServiceUtil`、および`com.liferay.dynamic.data.mapping.service.DDMStructureServiceWrapper`クラスから削除されました。

- `addStructure`（パラメーターは`long userId`、`long groupId`、`long classNameId`、`Map<Locale, String> nameMap`、`Map<Locale, String> descriptionMap`、`com.liferay.dynamic.data.mapping.model.DDMForm ddmForm`、`com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout`、`String storageType`、`com.liferay.portal.kernel.service.ServiceContext serviceContext`）

- `addStructure`（パラメーターは`long userId`、`long groupId`、`long classNameId`、`Map<Locale, String> nameMap`、`Map<Locale, String> descriptionMap`、`String xsd`、`com.liferay.portal.kernel.service.ServiceContext serviceContext`）

- `addStructure`（パラメーターは`long userId`、`long groupId`、`String parentStructureKey`、`long classNameId`、`String structureKey`、`Map<Locale, String> nameMap`、`Map<Locale, String> descriptionMap`、`String xsd`、`String storageType`、`int type`、`com.liferay.portal.kernel.service.ServiceContext serviceContext`)

- `addStructure`（パラメーターは`long groupId`、`long parentStructureId`、`long classNameId`、`String structureKey`、`Map<Locale, String> nameMap`、`Map<Locale, String> descriptionMap`、`String xsd`、`String storageType`、`int type`、`com.liferay.portal.kernel.service.ServiceContext serviceContext`）

- `addStructure`（パラメーターは`long userId`、`long groupId`、`String parentStructureKey`、`long classNameId`、`String structureKey`、`Map<Locale, String> nameMap`、`Map<Locale, String> descriptionMap`、`com.liferay.dynamic.data.mapping.model.DDMForm ddmForm`、`com.liferay.dynamic.data.mapping.model.DDMFormLayout ddmFormLayout`、`String storageType`、`int type`、`com.liferay.portal.kernel.service.ServiceContext serviceContext`）

- `updateStructure`（パラメーターは`long groupId`、`long parentStructureId`、`long classNameId`、`String structureKey`、`Map<Locale, String> nameMap`、`Map<Locale, String> descriptionMap`、`String definition`、`com.liferay.portal.kernel.service.ServiceContext serviceContext`）

- `updateStructure`（パラメーターは`long structureId`、`long parentStructureId`、`Map<Locale, String> nameMap`、`Map<Locale, String> descriptionMap`、`String definition`、`com.liferay.portal.kernel.service.ServiceContext serviceContext`）



#### 誰が影響を受けますか？

これは、削除されたメソッドを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

削除されたメソッドを、残りの`addStructure`メソッドと`updateStructure`メソッドのいずれかに置き換えます。



#### なぜこの変更が行われたのですか？

これらのメソッドは、Liferay Portal 7.0で廃止されました。



---------------------------------------



### 動的データマッピング永続性クラスのメソッドを削除

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-91760](https://issues.liferay.com/browse/LPS-91760)



#### 何が変わりましたか？

メソッド`fetchByPrimaryKeys`および`getBadColumnNames`が次のクラスから削除されました。

- `com.liferay.dynamic.data.mapping.service.persistence.DDMContentPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstancePersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstancePersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceRecordPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceVersionPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStorageLinkPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLayoutPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructurePersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMTemplatePersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceLinkPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceRecordVersionPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLinkPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateLinkPersistence`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateVersionPersistence`



#### 誰が影響を受けますか？

これは、削除されたメソッドを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

削除されたメソッドを、基本クラス`com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl`で提供されている対応するメソッドに置き換えます。



#### なぜこの変更が行われたのですか？

基本クラス`com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl`で提供されるデフォルトの実装があるため、`fetchByPrimaryKeys`のカスタム実装は必要ありません。

`getBadColumnNames`は、インターフェイス（`com.liferay.dynamic.data.mapping.service.persistence.DDMContentPersistence`など）から自動的に削除され、クラスがService Builderによって生成されたときに実装クラス（`com.liferay.dynamic.data.mapping.service.persistence.impl.DDMContentPersistenceImpl`など）に保持されました。  



---------------------------------------



### 動的データマッピングユーティリティクラスのメソッドを削除

- **日付：**2020年7月14日
- **JIRAチケット：** [LPS-91760](https://issues.liferay.com/browse/LPS-91760)



#### 何が変わりましたか？

メソッド`getBadColumnNames`が次のクラスから削除されました。

- `com.liferay.dynamic.data.mapping.service.persistence.DDMContentUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMDataProviderInstanceUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceRecordUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMFormInstanceVersionUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStorageLinkUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureLayoutUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMStructureVersionUtil`
- `com.liferay.dynamic.data.mapping.service.persistence.DDMTemplateUtil`



#### 誰が影響を受けますか？

これは、削除されたメソッドを使用していたすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

削除されたメソッドを、基本クラス`com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl`で提供されている対応するメソッドに置き換えます。



#### なぜこの変更が行われたのですか？

`getBadColumnNames`は、永続性ユーティリティ（`com.liferay.dynamic.data.mapping.service.persistence.DDMContentUtil`など）から自動的に削除され、クラスがService Builderによって生成されたときに永続性の実装（`com.liferay.dynamic.data.mapping.service.persistence.impl.DDMContentPersistenceImpl`など）に保持されました。  



---------------------------------------



### module.framework.properties.felix.fileinstall.\*ポータルプロパティの名前をmodule.framework.properties.file.install.\*に変更

- **日付：**2020年8月8日
- **JIRAチケット：** [LPS-115016](https://issues.liferay.com/browse/LPS-115016)



#### 何が変わりましたか？

`module.framework.properties.felix.fileinstall.`で始まるポータルプロパティの名前が `module.framework.properties.file.install.`で始まるように変更されました。



#### 誰が影響を受けますか？

これは、`module.framework.properties.felix.fileinstall.`で始まるポータルプロパティを持っているすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`module.framework.properties.felix.fileinstall.`で始まるポータルプロパティの名前を`module.framework.properties.file.install.`で始まる名前に変更します。



#### なぜこの変更が行われたのですか？

この変更は、Apache Felix Fileinstallのインライン化を反映しています。 Liferayがこの機能を管理および維持するようになったため、プロパティの名前が適切に変更されました。



---------------------------------------



### buffered.increment.enabledポータルプロパティの置き換え

- **日付：**2020年10月21日
- **JIRAチケット：** [LPS-122159](https://issues.liferay.com/browse/LPS-122159)



#### 何が変わりましたか？

`buffered.increment.enabled`ポータルプロパティを使用したビューカウント管理の有効化と無効化は、`view.count.enabled`ポータルプロパティを使用するように置き換えられました。

ビューカウントのグローバルな有効化と無効化が、`view.count.enabled`ポータルプロパティを使用して実行されるようになりました。

たとえば、特定のエンティティのビューカウントを無効にするには、`view.count.enabled[SomeEntity]=false`を設定することで可能になりました。 



#### 誰が影響を受けますか？

これは、`buffered.increment.enabled=false`ポータルプロパティ設定を持っているすべてのユーザーに影響します。

これは、`buffered.increment.enabled[SomeEntity]=false`ポータルプロパティ設定を使用して、一部のエンティティ（`SomeEntity`など）のビューカウントを無効にしたすべてのユーザーに影響します。 



#### コードを更新するにはどうすればよいですか？

`buffered.increment.enabled=false`を`view.count.enabled=false`に置き換えます。

`buffered.increment.enabled[SomeEntity]=false`を`view.count.enabled[SomeEntity]=false`に置き換えます。ここで、`SomeEntity`は、ビューカウントを無効にするエンティティです。



#### なぜこの変更が行われたのですか？

これにより、ビューカウント動作の管理が容易になります。



---------------------------------------



### module.framework.properties.file.install.optionalImportRefreshScopeポータルプロパティの削除

- **日付：**2021年2月3日
- **JIRAチケット：** [LPS-122008](https://issues.liferay.com/browse/LPS-122008)



#### 何が変わりましたか？

`module.framework.properties.file.install.optionalImportRefreshScope`ポータルプロパティが削除されました。 ファイルのインストールでは、更新が必要なオプションのパッケージを含むバンドルをスキャンするときに、常に管理対象バンドルのみがチェックされるようになりました。



#### 誰が影響を受けますか？

これは、`module.framework.properties.file.install.optionalImportRefreshScope`ポータルプロパティを指定しているすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`module.framework.properties.file.install.optionalImportRefreshScope`プロパティを削除します。 他の動作を使用するようにファイルのインストールを構成することはできません。



#### なぜこの変更が行われたのですか？

代替動作が望まれるケースはほとんどありませんでした。 ファイルのインストールはバンドルをLiferayにインストールする主な方法であるため、すべてのバンドルはデフォルトでLiferayによって管理されます。 この機能をサポートする分岐ロジックを削除すると、コードの保守性と可読性が向上します。



---------------------------------------



### .cfgファイルに.cfg形式を使用

- **日付：**2021年2月21日
- **JIRAチケット：** [LPS-128031](https://issues.liferay.com/browse/LPS-128031)



#### 何が変わりましたか？

この変更の前は、`.cfg`ファイルは`.cfg`または`.config`形式を使用できました。 現在、`.cfg`構成ファイルは[ここ](https://sling.apache.org/documentation/bundles/configuration-installer-factory.html#property-files-cfg)で定義されている`.cfg`形式を使用する必要があります。 



#### 誰が影響を受けますか？

これは、`.config`形式を使用している`.cfg`ファイルを持っているすべてのユーザーに影響します。



#### コードを更新するにはどうすればよいですか？

`.cfg`ファイルの名前を`.config`に変更し、`.config`形式を使用していることを確認するか、`.cfg`ファイルを`.cfg`形式を使用するように変更します（上記のリンクを参照）。



#### なぜこの変更が行われたのですか？

Apacheのファイルインストール実装により、`.cfg`ファイルで`.config`形式を使用できるようになりました。 独自のファイルインストール実装を使用するように切り替えたとき、保守性を容易にするために、より厳密なフォーマット処理を行うことにしました。



---------------------------------------