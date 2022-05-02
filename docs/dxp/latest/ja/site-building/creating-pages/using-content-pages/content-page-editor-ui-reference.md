# コンテントページエディタUIリファレンス

コンテントページエディタを使用して、コンテントページに要素を[追加](./adding-elements-to-content-pages.md)および構成できます。 コンテントページエディタを開くには、 *アプリケーションバー*の右上にある*［編集］![Edit icon](../../../images/icon-edit.png)(*) をクリックしてください。 編集ツールバーとサイドバーが表示され、コンテントページを作成するためのツールやコンポーネントが表示されます。

- [編集ツールバー](#editing-toolbar)
- [編集サイドバー](#editing-sidebar)

![［編集する］をクリックして、コンテントページの編集ツールバーとサイドバーにアクセスします。](./content-page-editor-ui-reference/images/01.png)

## 編集ツールバー

コンテントページのツールバーには、独自のユーザーエクスペリエンスの作成、ローカライズされた翻訳の設定、編集モードの切り替えなどのツールが含まれています。

![［編集する］をクリックして、コンテントページのツールバーにアクセスします。](./content-page-editor-ui-reference/images/02.png)

### エクスペリエンス

［Experience］ドロップダウンメニューを使用して、現在のページのコピーを作成し、特定のオーディエンス向けにカスタマイズできます。 詳細は、 [コンテンツページのパーソナライゼーション](../../personalizing-site-experience/experience-personalization/content-page-personalization.md) を参照してください。

### 翻訳

*［言語フラグ］*をクリックして、翻訳ドロップダウンメニューにアクセスします。 ここでは、ページの有効言語のステータスを表示したり、言語を切り替えて翻訳を直接表示および編集したりできます。 詳細は、[サイト言語の設定](../../site-settings/site-localization.md)を参照してください。

[［エクスペリエンス］](../../personalizing-site-experience/experience-personalization/content-page-personalization.md) をコンテントページのパーソナライズに使用する場合、サイトで使用できる言語を使用してエクスペリエンスを設定することができます。 詳しくは、 [Managing Experience Localization](../../personalizing-site-experience/experience-personalization/content-page-personalization.md#managing-experience-localization) を参照してください。

### デバイスディスプレイ

デバイスアイコンをクリックすると、DXPのレスポンシブレイアウトエディターにアクセスし、デスクトップ、携帯、タブレットなどの異なるスクリーンサイズに合わせてコンテントページのレイアウトを表示、設定することが可能です。 これらのレイアウトコントロールには以下のものが含まれます。

- デスクトップ
- タブレット
- スマホ（横）
- スマホ（縦）

![画面レイアウトの異なるデザインオプションを選択します。](content-page-editor-ui-reference/images/17.png)

```{note}
   コンテントページサイドバーのフラグメントとウィジェットとページデザインオプションは、デスクトップレイアウトでのみ利用可能です。
```

タブレットやスマホのレイアウトでは、リサイズハンドルバーを使って、さらにスクリーンサイズを調整することができます。

![タブレットやスマホのレイアウトで、コンテンツプレビューのサイズを変更することができます。](content-page-editor-ui-reference/images/09.gif)

### 編集履歴

履歴(![Time](./../../../images/icon-time.png))をクリックすると、現在の編集セッション中にコンテントページに対して行った変更を表示します。 元に戻すボタンとやり直しボタンを使用して、これらの変更を元に戻したり復元したりできます。

![［元に戻す］、［やり直し］、［履歴］ボタンを使って、変更した内容を元に戻すことができます。](./content-page-editor-ui-reference/images/10.png)

### 編集モードセレクター

コンテントページのサイドバーで利用できる編集オプションは、選択した編集モードによって異なります。

| 編集モード   | Description                                   | 利用可能なオプション                                      |
|:------- |:--------------------------------------------- |:----------------------------------------------- |
| ページデザイン | ページとページの内容を更新する権限がある場合に、ページをデザインすることができます。    | <ul><li> [Selection](#selection) </li><li> [Contents](#contents) </li><li> [Comments](#comments) </li></ul>                       |
| コンテンツ編集 | ページの更新権限がある場合は、コンテンツの編集が可能ですが、ページの内容は編集できません。 | ページデザインモードの全オプションに加えて: <ul><li> [フラグメントとウィジェット](#fragments-and-widgets) </li><li> [ページデザインオプション](#page-design-options) </li></ul> |

![編集モードは、［ページデザイン］と［コンテンツ編集］のどちらかを選択します。](content-page-editor-ui-reference/images/18.png)

### プレビューと公開

変更後、 *プレビュー* (![Preview](../../../images/icon-preview.png)) をクリックすると、ページの公開ビューが表示されます。 変更をプレビューしながら、表示するエクスペリエンス、言語、およびデバイスレイアウトを選択できます。 完了したら、*［公開］*をクリックします。 デフォルトでは、公開すると変更がすぐに公開されます。 ただし、カスタム[ワークフロー](../../../process-automation/workflow/introduction-to-workflow.md)を有効にしてレビューと公開のプロセスを指示している場合は、［Publish］をクリックすると定義済みの手順が開始されます。

## 編集サイドバー

選択した編集モードに応じて、編集サイドバーで次のオプションにアクセスできます。

- ![フラグメントとウィジェット](../../../images/icon-cards2.png) - [フラグメントとウィジェット](#fragments-and-widgets)
- ![ブラウザ](../../../images/icon-browser.png) - [ブラウザ](#browser) (Liferay DXP 7.4)
- ![ページデザインオプション](../../../images/icon-format.png) - [ページデザインオプション](#page-design-options)
- ![コメント](../../../images/icon-comments-w.png) - [コメント](#comments)

DXP 7.4 以前では、ブラウザ (![Browser](../../../images/icon-browser.png)) ボタンの機能は、この 2 つのコントロールで提供されていました。

- ![選択](../../../images/icon-pages-tree.png) - [選択](#selection)
- ![コンテンツ](../../../images/icon-list-ul.png) - [コンテンツ](#contents)


![コンテンツページサイドバーは、Liferay DXP 7.4以降と以前のバージョンとの違いを制御します。](content-page-editor-ui-reference/images/03.png)

### フラグメントとウィジェット

フラグメントとウィジェットサイドバーパネル(![Fragments and Widgets icon](../../../images/icon-cards2.png))では、ページを作成するために、ページ要素とカスタムページ要素の両方にアクセスできます。

- **フラグメント**：フラグメントは、コンテントページの主要な構成要素で、拡張可能で再利用可能なドラッグアンドドロップ要素です。 例としては、レイアウト要素、ビデオ、テキストオーバーレイ付きのバナー画像などがあります。 DXPのすぐに使えるフラグメントの詳細については [フラグメントの使用](../page-fragments-and-widgets/using-fragments.md) を、独自のフラグメントを作成する方法については [フラグメントの開発](../../developer-guide/developing-page-fragments/developing-fragments-intro.md) を参照してください。

   ```{note}
      コンテントページエディタのさまざまなページフラグメントの説明については、 [デフォルトのフラグメントリファレンス](./../page-fragments-and-widgets/using-fragments/default-fragments-reference.md) を参照してください。
   ```

- **ウィジェット**：他のDXPコンテキストと同様に、ウィジェットはページに動的機能を追加する [アプリケーション](../../../building-applications.html) です。 例としては、ブログ、掲示板、カレンダーなどがあります。 詳細は、[Using Widgets on Content Pages](./using-widgets-on-a-content-page.md)を参照してください。

### ブラウザ

> 対応可能：Liferay DXP/Portal 7.4以降

*ブラウザ* サイドバーパネル (![Browser](../../../images/icon-browser.png)) では、ページ上の全てのWebコンテンツを表示、編集、管理することが可能です。 ページにマッピングされたコンテンツは、ドキュメントやメディア、Webコンテンツの記事など、さまざまな種類のアセットから作ることができます。 ウィジェットに表示されるコンテンツやコンテンツフィールドにマッピングされたコンテンツなどがあります。 詳細については、 [コンテントページのコンテンツの管理](./managing-content-in-content-pages.md) を参照してください。

   ![［ブラウザ］タブでは、ページ上のWebコンテンツを閲覧、編集、管理することができます。](./content-page-editor-ui-reference/images/02.gif)

- **ページ要素**：ページ要素は、すべてのページ要素の階層的なアウトラインを表示し、それらの要素を選択、設定、削除することが可能です。 デザインモード中にページ上の任意の要素をクリックすると、選択パネルが開き、階層内の選択した要素が強調表示されます。 要素を選択すると、その要素に一般、スタイル、およびリンクオプションがある場合はそれらにアクセスできます。 詳しくは、 [フラグメントの設定](../page-fragments-and-widgets/using-fragments/configuring-fragments.md) を参照してください。

   ```{note}
   ページのヘッダーとフッターは、ページ要素パネルの階層に表示されません。 これは、[マスターページテンプレート](../defining-headers-and-footers/master-page-templates.md)  でしか修正できないからです。
   ```

- **ページコンテンツ**：ページコンテンツから、ページに含まれるコンテンツと、それらを編集するオプションにアクセスすることができます。 このコンテンツは、必須情報、名称、サブタイプなど、タイプ別に分類されています。 ここから、さまざまなアクションを行うことができます。

  - コンテンツのタイプや名前でフィルタリングすることで、すぐに見つけて編集することができます。
  - [コレクション](../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md) の全項目を見て、新規項目を追加します。
  - 編集コントロールを使って、画像やインラインテキストを編集することができます。
  - サイドバーの編集ボタンからインラインテキストを編集します。

   ```{tip}
   コンテンツをタイプで絞り込むと、そのタイプに応じた絞り込み検索が行われます。 検索結果にカーソルを合わせると、該当するページ要素がハイライトされます。
   ```

### ページデザインオプション

*ルック&フィール* (![Look and Feel](../../../images/icon-format.png)) をクリックすると [マスターページテンプレート](../defining-headers-and-footers/managing-master-page-templates.md) または [スタイルブック](../../site-appearance/style-books/using-a-style-book-to-standardize-site-appearance.md)を変更することができます。

### コメント

ページフラグメントコメントを使用して、チームメンバーと協力しながらコンテンツページを作成できます。 一度作成したコメントは、コンテンツページサイドバーのコメントパネル（![Comments icon](../../../images/icon-comments-w.png)）で閲覧、編集、削除、返信することができます。 [メンション](../../../collaboration-and-social/notifications-and-requests/user-guide/mentioning-users.md)が有効になっている場合は、コメントの一部としてページ更新権限を持つユーザーにタグを付けることができます。

```{note}
   7.3以降のバージョンでは、コメントはデフォルトで無効になっています。 ページコメントを有効にするには、*コントロールパネル*→*設定*→*システム設定*→*ページ*→*コンテンツページエディタ*に移動します。 次に、*コメントを有効にする*ボックスにチェックを入れて、*アップデート*をクリックします。
```

### 選択

> 対応可能：Liferay DXP/Portal 7.3

選択サイドバーパネル（![Selection](../../../images/icon-pages-tree.png)）では、すべてのページ要素の階層的なアウトラインを表示したり、それらの要素を選択、設定、または削除したりできます。 同様に、デザインモードでページ上の任意の要素をクリックすると、選択パネルが開き、階層内の選択されたページ要素が強調表示されます。 要素を選択すると、その要素に一般、スタイル、およびリンクオプションがある場合はそれらにアクセスできます。 詳しくは、 [フラグメントの設定](../page-fragments-and-widgets/using-fragments/configuring-fragments.md) を参照してください。

![選択すると、ページとコンテンツの階層が表示されます。](./content-page-editor-ui-reference/images/08.png)

```{note}
   ページのヘッダーとフッターは、選択パネルの階層に表示されません。 これは、[マスターページテンプレート](../defining-headers and footers/master-page-templates.md) でしか修正できないからです。
```

### コンテンツ

> 対応可能：Liferay DXP/Portal 7.3

ここでは、ページ上のすべてのWebコンテンツを表示、編集、および管理できます。 ウィジェットに表示されるコンテンツやコンテンツフィールドにマッピングされたコンテンツがあります。 詳細については、 [コンテントページのコンテンツの管理](./managing-content-in-content-pages.md) を参照してください。

## 追加情報

- [コンテントページの使用](../using-content-pages.md)
- [コンテントページへの要素の追加](./adding-elements-to-content-pages.md)
- [フラグメントの使用](../page-fragments-and-widgets/using-fragments/configuring-fragments.md)
