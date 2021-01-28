# コンテンツページの構築

[コンテンツページを追加](../adding-pages/adding-a-page-to-a-site.md)したら、さまざまな[コンテンツページ要素](./content-pages-overview.md)を追加して構成することにより、ページの作成を開始できます。

## コンテンツページへの要素の追加

1.  コンテンツページのSite Builderビューに入ります。 製品メニューを開き、サイトのメニューから*[Site Builder]* → *[Pages]* に移動します。

    ![[Site Builder]メニューで新しいコンテンツページを編集することから始めます。](building-content-pages/images/01.png)

    ![コンテンツページは、最初は空白です。 ページを作成するには、フラグメントを追加する必要があります。](./building-content-pages/images/02.png)

    ``` note::
       新しいコンテンツページは空で始まり、*下書き*ステータスになります。 ページは公開されるまで表示されません。
    ```

2.  コンテンツページのサイドバーを開き、ページフラグメント要素の追加を開始します。

    ![ページにフラグメントを追加して、必要なモックアップを作成します。](./building-content-pages/images/03.png)

3.  ページフラグメントの編集可能な部分を設定して、コンテンツページの外観をカスタマイズします。 要素は、次のいくつかの方法で構成できます。

      - [背景色の設定](#setting-a-background-color)
      - [背景画像の使用](#using-a-background-image)
      - [テキストの編集](#editing-text)
      - [ハイパーリンクの編集](#editing-a-hyperlink)
      - [画像を編集する](#editing-an-image)
      - [フラグメントのコピー](#copying-a-fragment)
      - [フラグメント構成の保存](#saving-a-fragment-composition)
      - [セクションの幅とパディングの構成](#configuring-section-width-and-padding)
      - [行の列の構成](#configuring-a-row-s-columns)
      - [一般設定](#general-configuration)
      - [コンテンツのマッピング](#mapping-content)

    ![フラグメントを変更して、必要なコンテンツを表示します。](./building-content-pages/images/04.png)

    ``` tip::
       コンテンツページで進行中の作業は自動的に保存されます。
    ```

4.  右上の*[Publish]* ボタンをクリックして、更新をライブページで利用できるようにします。

## コンテンツページで要素を設定する

コンテンツページに追加できる多くの要素は、構成およびカスタマイズできます。 要素をクリックすると、選択した要素で使用可能な構成にアクセスできる管理ツールバーが提供されます。

### 背景色の設定

レイアウトの背景色を設定できます。

1.  レイアウトをクリックして選択します。
2.  （![Background Color](../../../images/icon-color.png)）アイコンをクリックして色を選択します。

![レイアウトの背景色を設定できます。](./building-content-pages/images/05.png)

``` note::
   使用可能なカラーパレットは、フラグメント開発者が設定できます。 詳細については、Fragment Configuration Typesを参照してください。
```

### 背景画像の使用

レイアウトの背景画像を設定できます：

1.  レイアウトをクリックして選択します。

2.  *[Layout Background Image]* を選択し、表示する画像を選択します。

    ![レイアウトには、背景色、背景画像、間隔のオプションがあります。](./building-content-pages/images/06.png)

    ``` note::
       レイアウトの背景画像のマッピングは、Liferay DXP 7.2 SP1+およびLiferay Portal 7.2 GA2+で使用できます。
    ```

### テキストの編集

1.  編集するテキストをクリックします。
2.  プレーンテキストの場合はテキストを置き換えるか、インラインテキストエディターを使用してリッチテキストスタイル、印刷上の強調、配置、およびリストの書式を更新します。

![編集可能なテキストを変更できます。](./building-content-pages/images/07.png)

### ハイパーリンクの編集

1.  編集するリンク、ボタン、または画像をクリックします。

2.  （![Edit](../../../images/icon-edit.png)）をクリックしてリンクテキストを編集するか、（![Link](../../../images/icon-link.png)）リンクプロパティを編集するか、（![Map](../../../images/icon-map.png)）をクリックしてリンクマッピングを編集します（前述）。

    リンクプロパティのポップアップから、以下のリンクオプションを定義できます。

    *Manual：*手動リンクを定義するか、既存のコンテンツフィールドにマッピングします

      - * URL：*リンクのURLを設定します
      - *Target：*リンクの動作を設定します

    *[From Content]フィールド：*

      - *Content：*コンテンツタイプを設定します
      - *Field：*選択したコンテンツに対して表示するフィールドを設定します

    使用可能なコンテンツフィールドの一部のリストを以下に示します。

      - Categories
      - Tags
      - Display Page URL
      - 説明
      - Publish Date
      - Summary
      - Title
      - Last Editor Name
      - Author Name
      - Basic Web Content

![編集可能なリンクを変更できます。](./building-content-pages/images/08.png)

### 画像を編集する

1.  置き換える画像をクリックします。

2.  （![Image Properties](../../../images/icon-edit.png)）アイコンをクリックします。

    ![画像を編集すると、URLを入力したり、ドキュメントとメディアから画像を選択したり、画像のリンクを設定したりできます。](./building-content-pages/images/09.png)

3.  クリックして *選択* Docsやメディアから画像をアップロードするか、画像のURLを定義します。 [ *クリア* ]をクリックして画像をリセットします。 画像の説明を指定することもできます。

あなたもすることができます [レイアウトの背景画像を指定する](#using-a-background-image) と [あなたのイメージのためのリンクを提供](#editing-a-hyperlink)。

### フラグメントのコピー

ページ（コンポーネント、セクション、行など）にフラグメントを複製して時間を節約できます。

1.  コピーするフラグメントにカーソルを合わせます。
2.  フラグメントをクリックして選択します。
3.  表示されるコンテキストメニューの[Duplicate Fragment]ボタンをクリックします。

![ページ上でフラグメントを複製できます。](./building-content-pages/images/10.png)

``` note::
  Duplicated Sections and Rows appear directly below the Section or Row that is duplicated. The Fragments, mappings, and customizations are duplicated as well.
```

``` warning::
  Layouts (Sections or Rows) containing instanceable Widgets cannot be duplicated. In this case, a message will appear, indicating the Widget preventing the duplication.
```

### フラグメント構成の保存

``` note::
  利用可能：Liferayポータル7.3 GA3以降
```

カスタマイズされたフラグメント構成（セクションまたは行レイアウトフラグメント）を新しいフラグメントとして保存できるため、それらを他のフラグメントベースのページで再利用できます。

1.  保存する行またはセクション構成をクリックします。
2.  表示されるコンテキストメニューで（![Save icon](../../../images/icon-save.png)）をクリックします。

![[行]または[セクション]をクリックして、[フラグメントの構成を保存]ボタンを表示します。](./building-content-pages/images/11.png)

1.  表示される[フラグメントとして保存]ダイアログで、名前、オプションの説明、サムネイル、編集済みのフラグメントエントリ（リンク、画像、テキストなど）などのインラインコンテンツを含めるためのオプションを有効/無効にし、[コンテンツをマッピング](#mapping-content)し、フラグメントを保存するコレクションを選択します。

![表示されるダイアログにフラグメント構成の情報を入力します。](./building-content-pages/images/12.png)

``` note::
   サイト固有のフラグメントコレクションが存在しない場合、保存されたフラグメント構成は、保存されたフラグメントと呼ばれる新しいフラグメントコレクションに自動的に保存されます。
```

保存されたFragmentコンポジションは、Fragmentsサイドバーですぐに使用でき、Page Fragments管理アプリケーションを通じて使用できます。

![保存したFragmentコンポジションはすぐに使用できます。](./building-content-pages/images/13.png)

他のフラグメントと同様に、コンポジションはサイト間でエクスポート/インポートできます。

``` note::
  `Liferayのフラグメントツールキットの最新バージョン <../developer-guide/developing-fragments/developing-page-fragments/developing-page-fragments-with-the-fragments-toolkit.md>` _サポートの作成、エクスポート/インポート、およびフラグメント構成のプレビュー。
```

### セクションの幅とパディングの構成

1.  セクションをクリックして選択します。
2.  （![Cog icon](../../../images/icon-control-menu-gear.png)）をクリックして、セクションの構成メニューを開きます。
3.  セクションの *コンテナ* 設定を更新して幅のスタイル（*固定幅* または *流体*）を調整し、セクションコンテナのパディングを調整するために *パディングトップ* および *パディングボトム* 設定を更新します。

![セクションの設定メニューから基本的なスタイルを設定できます。](./building-content-pages/images/14.png)

レイアウトセクションの背景画像と色を設定することもできます。 詳細については、「 [背景画像の使用](#using-a-background-image) 」を参照してください。

### 行の列の構成

1.  行をクリックして選択します。
2.  （![Cog icon](../../../images/icon-control-menu-gear.png)）をクリックして、行の構成メニューを開きます。
3.  *列数* 設定（1から6）を更新して行の列数を指定し、 *列の余白* 設定を無効/有効にして列間のパディングを削除/追加します。

![セクションの設定メニューから基本的なスタイルを設定できます。](./building-content-pages/images/15.png)

列の幅を調整して、よりカスタムなレイアウトを作成できます。 次の手順を実行します：

1.  行をクリックして選択します。 各列の間にサイズを変更できることを示す青い点が表示されます。
2.  青い点の1つをクリックし、左または右にドラッグして、列のサイズを調整します。

![レイアウトの列の間隔を調整して、カスタムレイアウトを作成できます。](./building-content-pages/images/16.png)

### 一般設定

これは、すぐに使用できる一部のフラグメントで表示され、コンテキスト固有のフラグメント構成を示します。 これらの設定のリファレンスについては、Basic Component Configuration Referenceを参照してください。

### コンテンツのマッピング

これらの要素をコンテンツにマップすることもできます。 要素に*コンテンツ*（Webコンテンツの記事、ドキュメント、またはブログ）を設定し、表示する適切な*フィールド*（タイトル、作成者名、タグなど）を選択できます。 これは、要素の*マップ*ボタン（![Map](../../../images/icon-map.png)）を選択して設定できます。

``` note::
   多くのマッピングの改善が Liferay DXP 7.2 SP1+およびLiferay Portal 7.2 GA2+でリリースされました。 たとえば、編集可能な要素を既存のコンテンツのテキスト/URLフィールドにマッピングしたり、フラグメントの背景画像を既存のコンテンツの画像フィールドにマッピングしたりできます。 カスタムフィールドをマップすることもできます。
```

コンテンツページを作成するときに、ユーザーセグメントに基づいてユーザー向けにさまざまな**エクスペリエンス**を作成できます。 既存のユーザーセグメントのコンテンツページに、独自のエクスペリエンスを作成できます。 詳細については、[Content Page Personalization guide](../../personalizing-site-experience/README.md)を参照してください。

## 関連情報

  - [ページフラグメントの開発](../../developer-guide/developing-page-fragments/developing-fragments-intro.md)
  - [マスターページの使用](../defining-headers-and-footers/master-page-templates.md)
  - [Changing Content Pages Look and Feel](./content-pages-overview.md#look-and-feel)
