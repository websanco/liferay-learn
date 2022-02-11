# コンテンツダッシュボードインターフェイス

> 対応可能：Liferay 7.3以降

コンテンツダッシュボードインターフェイスには、次の領域があります。

- [コンテンツ監査ツール](#content-audit-tool)（1）
- [コンテンツ一覧](#contents-list)（2）
- [コンテンツ情報サイドバー](#content-info-sidebar)（3）

![コンテンツダッシュボードのユーザーインターフェイスには、3つの情報領域が含まれています。](./content-dashboard-interface/images/05.png)

[コンテンツダッシュボードを開く](#accessing-the-content-dashboard)と、ページの上部に[コンテンツ監査ツール](#content-audit-tool)（1）が表示され、下部に[コンテンツ一覧](#contents-list)（2）が表示されます。 コンテンツ一覧から、[コンテンツ情報サイドバー](#content-info-sidebar)（3）と[コンテンツパフォーマンスパネル](../content-performance-tool/about-the-content-performance-tool.md)にアクセスできます。 コンテンツ情報サイドバーとコンテンツパフォーマンスパネルは折りたたみ可能です。 コンテンツ一覧の各アイテムの追加の詳細が表示されます。

コンテンツ情報サイドバーにアクセスするには、コンテンツの*アクションメニュー*（![Action Menu](../../images/icon-actions.png)）をクリックし、*［Info］*オプションを選択します。 または、記事の上にカーソルを置き、*［Info］*（![Info](../../images/icon-information.png)）をクリックします。 *統計情報を表示*（![Metrics](../../images/icon-analytics.png)）オプションの詳細は、[About the Content Performance Panel](../content-performance-tool/about-the-content-performance-tool.md)を参照してください。

## コンテンツダッシュボードへのアクセス

1. グローバルメニューをクリックし、*［コンテンツ］*領域で、*［コンテンツダッシュボード］*をクリックします。
1. グローバルメニューが無効になっている場合は、プロダクトメニューを開き、*サイト*メニューの下にある*［コンテンツ］* &rarr; *［コンテンツダッシュボード］*に移動します。

    ![グローバルメニューからコンテンツダッシュボードを開きます.](./content-dashboard-interface/images/03.png)

## コンテンツ監査ツール

このツールは、［コンテンツダッシュボード］ページの上部にあるグラフィカル領域です。 このグラフは、選択したグローバルボキャブラリのカテゴリの組み合わせに対するアセットの総数を表しています。 この表は以下を考慮に入れています：

- 全てのサイトとアセットライブラリのWebコンテンツ記事のみ。
- アクセスできるコンテンツ。
- [コンテンツ一覧](#contents-list)の既存の[フィルター](#filtering-and-sorting-content-in-the-contents-list)。
- 二つのグローバルボキャブラリの最大値（*x*および*y*軸に対する）。

![コンテンツダッシュボードのコンテンツ監査ツールの概要](./content-dashboard-interface/images/10.png)

```{tip}
コンテンツ監査ツールに期待される表が表示されない場合は、グローバルボキャブラリの［カテゴリを確認］（../tags-and-categories/defining-categories-and-vocabularies-for-content.md）し、［コンテンツが分類されている］ことを確認（../tags-and-categories/organizing-content-with-categories-and-tags.md）してください。 
```

次のビデオは、コンテンツ監査ツールの概要を示しています。

<video width="100%" height="100%" controls>
    <source src="./content-dashboard-interface/images/14.mp4" type="video/mp4">
</video>

コンテンツ監査ツールのインターフェイスには、次のものがあります。

- 表の凡例（1）
- 最初のグローバルボキャブラリの*X*軸カテゴリ（2）
- 2番目のグローバルボキャブラリの*Y*軸カテゴリ（3）
- 棒グラフ（4）

![コンテンツパフォーマンスユーザーインターフェイスの概要](./content-dashboard-interface/images/04.png)

### コンテンツ監査ツールでボキャブラリとカテゴリをカスタマイズする

デフォルトでは、棒グラフには、選択したグローバルボキャブラリの各カテゴリのアセットの総数が表示されますが、この情報はさまざまな方法で絞り込むことができます。

- [コンテンツ一覧](#contents-list)で[フィルター](#filtering-and-sorting-content-in-the-contents-list)を使用する。
- 表の凡例のカテゴリー名をオンまたはオフにして、表のカテゴリーを絞り込む。
- バーの1つをクリックして、特定のカテゴリーの結果を拡大する。
- ボキャブラリを変更する。

ボキャブラリを変更するには：

1. ［コンテンツ監査］領域の*［表の設定］*アイコン（![gear icon](../../images/icon-settings.png)）をクリックします。
1. デュアルリストボックスで、*［設定可能一覧］*または*［使用中］*一覧の［ボキャブラリ］をクリックし、選択ボタン（![Angle Left](../../images/icon-angle-left.png) ![Angle right](../../images/icon-angle-right.png)）をクリックして、表示するボキャブラリを追加または削除します。
1. *［Up］*（![Up](../../images/icon-angle-up.png)）と*［下へ］*（![Down](../../images/icon-angle-down.png)）ボタンをクリックして、表軸のボキャブラリの順番を決めます。
1. *［保存］*をクリックします。
1. 選択したボキャブラリに割り当てられた軸を切り替える必要がある場合は、［コンテンツ監査］領域の*［軸を反転］*アイコン（![Flip Axes](../../images/icon-change.png)）をクリックします。

    ![コンテンツ監査ツールでボキャブラリを選択します](./content-dashboard-interface/images/12.png)

```{note}
グローバルサイトレベルで定義された最大2つのボキャブラリを選択できます。
```

灰色のバーは、選択したボキャブラリの1つにカテゴリーがないコンテンツを示します。 このコンテンツの表ラベルは独特です。 たとえば、オーディエンスボキャブラリを選択すると、［オーディエンス］に分類されていないコンテンツには、*［No Audience Specified］*ラベルが表示されます。

## コンテンツ一覧

デフォルトでは、この領域には、全てのサイトとアセットライブラリにわたってアクセスできるすべてのコンテンツが表示されます。 ここから、組み込みまたはカスタムのフィルターを使用してコンテンツを並べ替えたり絞り込んだりできます。 コンテンツ監査ツールのグラフは、設定したフィルターに基づいて動的に変化します。

コンテンツ一覧には、次の要素があります。

- 絞り込みおよびソートのセレクター（1）
- 絞り込みエディター（2）
- 検索フィールド（3）
- コンテンツ一覧（4）

![コンテンツ一覧には、コンテンツの検索に役立ついくつかの要素が含まれています。](./content-dashboard-interface/images/06.png)

コンテンツの一覧には、次のカラムがあります。

| 列                  | 説明                                                                                                              |
| :--- | :--- |
| タイトル               | 記事のタイトル（*）。                                                                                                     |
| 作成者                | 作成者の名前。                                                                                                         |
| サブタイプ              | 記事の[サブカテゴリ](../tags-and-categories/defining-categories-and-vocabularies-for-content.md#creating-subcategories)。 |
| サイトまたはアセットライブラリ    | Liferay DXPインスタンス内の記事の場所。                                                                                       |
| ステータス              | 記事のワークフローステータス。 ステータスが異なる複数のバージョンの記事には、すべてのステータスが表示されます。                                                        |
| *First Vocabulary* | これは、コンテンツ監査ツールの最初のボキャブラリです。                                                                                     |
| *第二ボキャブラリ*         | コンテンツ監査ツールのオプションの2番目のボキャブラリ。                                                                                    |
| 編集日時               | 最後に記事が更新された日付または時刻。                                                                                             |

（*）*ページ*（![Page](../../images/icon-page.png)）アイコンは、[ディスプレイページテンプレート](../../site-building/displaying-content/using-display-page-templates/about-display-page-templates-and-display-pages.md)に基づく記事を示します。

```{note}
［First Vocabulary］カラムと［第二ボキャブラリ］カラムは、選択したボキャブラリによって異なります。
```

コンテンツ一覧の各エントリについて、*アクション*メニュー（![Actions Menu](../../images/icon-actions.png)）を使用するか、記事にカーソルを合わせて対応するオプションをクリックすると、次のオプションにアクセスできます。

- *View*（![View](../../images/icon-preview.png)）：ディスプレイページテンプレートに基づいて閲覧モードでコンテンツを開きます。
- *編集する*（![Edit](../../images/icon-edit.png)）: コンテンツを編集モードで開きます。
- *Info*（![Information](../../images/icon-information.png)）: [コンテンツ情報](#content-info-sidebar)サイドバーを開きます。
- *統計情報を表示*（![View Metrics](../../images/icon-analytics.png)）: [コンテンツパフォーマンスサイドバー](../content-performance-tool/about-the-content-performance-tool.md)を開きます。

    ![アクションメニューまたは各コンテンツ行のアイコンから、コンテンツ情報サイドバーとコンテンツパフォーマンスパネルにアクセスします。](./content-dashboard-interface/images/11.png)

```{note}
ディスプレイページテンプレートに基づくコンテンツには、［View］、［Info］、および［統計情報を表示］オプションを使用します。 コンテンツの編集権限がある場合は、［Edit］オプションを使用します。
```

### コンテンツ一覧内のコンテンツの絞り込みとソート

1. *［フィルターと並び替え］*ドロップダウンメニューをクリックします。
1. 事前定義されたフィルターの1つを選択するか、またはカスタムフィルター（3つのドットで示される）を選択して、独自の絞り込み基準を作成します。
1. 絞り込みたいコンテンツを選択します。

絞り込みエディター領域には、フィルターと結果の総数が表示されます。 1つ以上のフィルターを削除するには、フィルター名の横にある*［閉じる］*（![Close](../../images/icon-times.png)）ボタンをクリックするか、*［クリア］*をクリックしてすべてのフィルターを削除し、コンテンツ一覧をリセットします。 定義済みのフィルターおよびカスタムフィルターに加えて、検索フィールドのキーワードを使用して、コンテンツビューでアセットを検索および絞り込みできます。 さまざまなフィルターの組み合わせがどのように機能するかを理解するには、[コンテンツリストでの絞り込みの仕組みを理解する](#understanding-how-filtering-works-in-the-contents-list)を参照してください。

![絞り込みエディターを使用して、特定のフィルターまたはすべてのフィルターをフィルター領域から削除します.](./content-dashboard-interface/images/01.png)

[［コンテンツ監査ツール］](#content-audit-tool)領域のバーの1つをクリックしてコンテンツを絞り込み、特定のカテゴリーの結果を拡大することもできます。

![コンテンツ監査ツールのバーの1つをクリックして、バーのカテゴリーで絞り込みます。](./content-dashboard-interface/images/08.png)

コンテンツをソートするには：

1. *［フィルターと並び替え］*ドロップダウンメニューをクリックします。
1. *［ソート順］*セクションで、項目の順序を選択します。

```{note}
［コンテンツダッシュボード］ページを閉じると、デフォルトのフィルターと並べ替えオプションが再度設定されます。
```

### コンテンツリストでの絞り込みの仕組みを理解する

- 複数の基準でカテゴリーフィルターまたはタグフィルターを使用すると、結果はすべての基準を満たすコンテンツに相当します（例Aを参照）。
- 同じフィルター基準に対して複数のオプションを選択すると、コンテンツリストには少なくとも1つのオプションに一致するアセットが表示されます（例Bを参照）。
- 異なるフィルターからの基準は、`and` 演算子を使用して結合されます（例Cを参照）。

| 例 | 次の項目で絞り込む  | フィルターの例                           | コンテンツリストの結果                                     |
| - | ---------- | --------------------------------- | ----------------------------------------------- |
| A | Categories | カテゴリー：*Entrepreneur* および *Family* | *Entrepreneur* `および` Familyカテゴリーの両方に属しているアセットのみ |
| B | 作成者        | 作成者：*Peter* および *Linda*           | *Peter* `または` _Linda_ `または`両者が作成したアセット。         |
| C | カテゴリー、作成者  | カテゴリーは_Entrepreneur_、作成者は_Linda_  | *Entrepreneur*カテゴリーにあり、`かつ`Lindaが作成したアセットのみ。    |

## コンテンツ情報サイドバー

このサイドバーでは、アセットの所有者、コンテンツで利用できる言語、アセットのタグとカテゴリ、レビュー日時など、コンテンツ一覧で選択したアセットのさまざまなメタデータにアクセスできます。

![コンテンツ一覧のアセットから情報サイドバーにアクセスします.](./content-dashboard-interface/images/07.png)

情報サイドバーを開くには：

1. アセットの横にあるアクションメニュー（![Actions Menu](../../images/icon-actions.png)）をクリックし、*［Info］*を選択します。
1. または、アセットの上にカーソルを置き、*［Info］*（![Information icon](../../images/icon-information.png)）アイコンをクリックします。

    ![情報サイドバーには、コンテンツのさまざまなメタデータが提供されます.](./content-dashboard-interface/images/09.png)

## 関連情報

- [About the Content Dashboard](./about-the-content-dashboard.md)
- [About the Content Performance Panel](../content-performance-tool/about-the-content-performance-tool.md)
- [コンテンツのカテゴリとボキャブラリの定義](../tags-and-categories/defining-categories-and-vocabularies-for-content.md)
