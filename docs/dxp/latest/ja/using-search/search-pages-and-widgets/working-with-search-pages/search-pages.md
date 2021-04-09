# 検索ページ

デフォルトの検索ページは [`localhost:8080/web/guest/search`](http://localhost:8080/web/guest/search)にあります。 その中にいくつかのコンテンツを含むLiferay DXPを実行している場合は、そこに移動して検索を実行することができます。 デフォルトでは、このページはデフォルトサイトの非表示ページです。 ユーザーは、 *検索バー* ウィジェットに検索用語を入力すると、この非表示ページにルーティングされます。 検索ページは、ユーザーが検索結果を閲覧する場所でもあります。

![デフォルトの検索ページは非表示になっており、直接URLまたは検索バーウィジェットを使用してアクセスできます。](./search-pages/images/05.png)

ユーザーは、新しい検索ページを作成し、さまざまな検索ウィジェットを追加し、利用可能なさまざまなウィジェット構成を調整して、サイトのユーザーに正確で調整された検索エクスペリエンスを提供することにより、検索エクスペリエンスをカスタマイズすることを選択できます。 [デフォルト](#default-search-pages)検索ページを使用して、独自のカスタム検索ページを作成するのに良い出発点にすることができます。

``` note::
   Newly created sites do not get a Search Page created by default. You'll need to create one. You can use an existing Search Page Template or create a new Search Page yourself.
```

![新しい空白サイトには、検索ページを作成して構成する必要があります。](./search-pages/images/01.png)

## デフォルトの検索ページ

検索エクスペリエンスには、次の2つのコンポーネントがあります (デフォルトのサイト、テーマ、および検索構成)。

1.  各ページのヘッダーに埋め込まれた検索バー。
2.  検索リクエストが送信され、結果が表示されるデフォルトの検索ページ。

裏側で、検索バーウィジェットは、わかりやすいURL `/ search`非表示の検索ページをポイントしています。

![宛先ページを検索バー用に構成する必要があります。](./search-pages/images/02.png)

検索クエリを入力すると、デフォルトの検索ページにリダイレクトされ、検索結果ウィジェットに結果が表示されます。

<!-- I think more can be said here to introduce users to how, on the default search page, multiple widgets are added to a page to provide a cohesive experience. So in this case that the default search page is comprised of a Search results widget (A), a modified facet widget, user facet widget, folder, tag, type widgets - this helps to lay the groundwork for people that a search page is a combination of widgets that provide a specific type of experience. -->

![デフォルトの検索ページは、完全な検索エクスペリエンスを提供します。](./search-pages/images/03.png)

デフォルトの検索ページは、検索ページテンプレートに基づいていますが、デフォルトではテンプレートからの変更を継承しません。 つまり、テンプレートの継承構成を変更せずに、検索ページを直接カスタマイズできます。

![デフォルトでは、検索ページはページテンプレートからの変更を継承しません。](./search-pages/images/04.png)

デフォルトのページを少しだけ変更する必要がある場合は、それを破棄せずに手動で作成してください。 ページ上のウィジェットの追加、構成、削除など、必要な構成変更を行うだけです。 一方、デフォルトの検索ページから完全に抜けたい場合は、ゼロから始めることがオプションです。

## 検索ページの作成

ページテンプレートを使用して検索ページを作成するか、単一のサイトに対して手動で構成された検索ページを使用できます。 検索ページテンプレートの詳細については、 [検索ページテンプレート](./using-a-search-page-template.md) 使用を参照してください。サイトごとに個別に検索ページを構成する方法については、「 [検索ページを手動で作成する](./manually-creating-a-search-page.md) 」を参照してください。

アプローチを選択し、ここを読んで検索ページを起動して実行した後、さまざまな検索ウィジェットに関する詳細な記事と設定ドキュメントを読み、設定オプションの完全なスイートを理解してください。
