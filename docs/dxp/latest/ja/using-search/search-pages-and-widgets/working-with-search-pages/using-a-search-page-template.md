# 検索ページテンプレートの使用

デフォルトの検索ページは、グローバルスコープページテンプレートによってサポートされています。 手動で作成した検索ページは、同じテンプレートをベースラインとして使用できます。 すぐに使用できる検索ページテンプレートには、検索バー、検索結果、検索候補、検索オプション、およびいくつかの検索ファセットウィジェットが含まれています。

<!-- Please update image without Lunar Resort. ![The Search Page template gets you up and running.](./using-a-search-page-template/images/01.png) -->

テンプレートは、次の2つの方法で使用することができますに [検索ページを管理する](#using-a-page-template-to-manage-search-pages) テンプレートを使用して作成した、またはに [ベースラインの検索ページの作成](#using-a-page-template-to-create-a-baseline-search-page) 独立したテンプレートの設定されています。

## ページテンプレートを使用した検索ページの管理

検索の管理ページには、有効にすることによって行うことができますテンプレートを使用して作成した *継承の変更* 件の検索ページを構成するときに機能します。

テンプレートを使用して検索ページを作成し、テンプレートに加えられた変更を継承させるには、次のようにします。

1.  [サイトメニュー](../../../getting-started/navigating-dxp.md#site-menu)を使用して、目的のサイトに移動します。

2.  サイトメニューの*[Site Builder]* → *[Pages]* に移動します。

3.  [Pages]ページで、*追加*ボタン（![Add](../../../images/icon-add.png)）をクリックしてトップレベルのページを作成します。

4.  検索ページテンプレートを選択します。 デフォルトの検索ページテンプレートを使用するには、 *グローバルテンプレート* → *検索*クリックします。 新しい検索ページの名前を入力し、 *[保存]* をクリックします。

5.  変更の継承を有効にするには、ページの構成内の *変更の継承* オプションを切り替えます。

    ![この機能を有効にするには、[変更の継承]をクリックします。](./using-a-search-page-template/images/02.png)

    ``` tip::
       For new pages, you can enable this when the page is first created. For an existing page based on the search template, navigate to the Site menu → *Site Builder* → *Pages*, and then click *Configure* within the Actions menu.
    ```

    ``` warning::
       Some settings within the page configuration will become unavailable if the page is set to inherit changes from the page template.
    ```

ページテンプレートが更新されるたびに、検索ページが更新されます。

## ページテンプレートを使用してベースライン検索ページを作成する

また、ページテンプレートを検索ページを作成するための開始点として使用し、ベースページテンプレートに加えられる変更とは別にページを微調整することもできます。

ページテンプレートからの変更を継承しないテンプレートを使用して検索ページを作成するには、次の手順を実行します。

1.  [サイトメニュー](../../../getting-started/navigating-dxp.md#site-menu)を使用して、目的のサイトに移動します。

2.  サイトメニューの*[Site Builder]* → *[Pages]* に移動します。

3.  [Pages]ページで、*追加*ボタン（![Add](../../../images/icon-add.png)）をクリックしてトップレベルのページを作成します。

4.  検索ページテンプレートをクリックして、デフォルトの検索ページテンプレートを使用するには、 *グローバルテンプレート* → *検索*クリックします。 新しい検索ページの名前を入力し、 *[保存]* をクリックします。

5.  ページ設定で[ *変更の継承* ]オプションをオフのままにします。

これで、ページはテンプレートに基づいて作成され、ページテンプレートに加えられた変更を受信せずにさらに調整および構成できます。

## デフォルトの検索ページテンプレートの使用

デフォルトでは、すべてのウィジェットは *Barebone* Application Decoratorを使用します。ウィジェットにレンダリングするコンテンツがない限り、ウィジェットの本体は非表示になります。 マウスでウィジェットにカーソルを合わせると、ヘッダーとウィジェットオプションアイコンが表示されます。 このため、検索ページにアクセスすると、一部のウィジェットが完全にレンダリングされていないことがわかります。

<!-- A screenshot or two comparing/contrasting the apperaance of Barebone vs. Borderless would be helpful -->

対照的に、検索ウィジェットをページに手動で追加すると、 *ボーダーレス* デコレーター（デフォルト）が使用され、表示するコンテンツがない場合でもウィジェットの多くが表示されます。
