# 表示ページテンプレートの作成と管理

> 対応可能：Liferay DXP/Portal 7.3以降

表示ページテンプレートは、コンテンツのレイアウトとフォーマットを指定し、一意のフレンドリURLで表示します。 表示ページテンプレートと表示ページについて詳しく知りたい方は、 [Displaying Content with Display Page Templates](./about-display-page-templates-and-display-pages.md)を参照してください。

```{note}
表示ページテンプレートは、ウェブコンテンツ、ドキュメント、ブログエントリー、カテゴリにのみ対応しています（Liferay DXP 7.4 以降）。
```

## 表示ページテンプレートの作成

1. プロダクトメニューを開き、 [**デザイン**] &rarr; [**ページテンプレート**] をクリックします。

1. [**表示ページテンプレート**] タブをクリックします。

   ![[ページテンプレート] に移動し、 [表示ページテンプレート] タブをクリックします。](./creating-and-managing-display-page-templates/images/02.png)

1. **追加**（![Add](./../../../images/icon-add.png)）をクリックします。

1. テンプレートの ［**名前**］ を入力し、 該当する場合は ［**コンテンツの種類**］ と ［**サブタイプ**］ を選択します。 次に、 [**保存**] をクリックします。

   ![新しいテンプレートのコンテンツ タイプとサブタイプを選択します。](./creating-and-managing-display-page-templates/images/03.png)

1. [コンテントページ](../../creating-pages/using-content-pages/content-page-editor-ui-reference.md)と同じインターフェイスとページ要素を使用して、表示ページテンプレートエディタに要素を追加します。

   ![フラグメントとウィジェットを使って、テンプレートを構築します。](./creating-and-managing-display-page-templates/images/04.png)

1. ページ要素を追加したら、編集可能なフィールドをダブルクリックして、それらをコンテンツストラクチャーフィールド (タイトル、説明など) に [マップ](../../creating-pages/page-fragments-and-widgets/using-fragments/configuring-fragments/fragment-sub-elements-reference.md#mapping-settings) できます。

   ```{tip}
   マップされたフィールドは紫の輪郭で示されます。
   ```

   ![ページ要素をストラクチャーフィールドにマップします。](./creating-and-managing-display-page-templates/images/05.png)

   表示ページの SEO および Open Graph 設定を構成するときにこれらのマップされたストラクチャーフィールドを使用する方法については、 [SEOとOpen Graphの設定](./configuring-seo-and-open-graph.md) を参照してください。

1. 公開する前に、 [プレビュー機能](#preview-the-display-page-template-content-mappings) を使って既存のコンテンツとの編集可能フィールドのマッピングをプレビューできます（Liferay DXP 7.4 で利用可能です）。

1. 完了したら、 ［**公開**］ をクリックして作業内容を保存します。

これで、表示ページテンプレートを使用して[コンテンツを公開できます。](./publishing-content-with-display-pages.md)

### 表示ページテンプレートコンテンツマッピングのプレビュー

> 対応可能：Liferay DXP/Portal 7.4以降

1. プロダクトメニューから、 ［**Preview With**］ のドロップダウンメニューをクリックし、 ［**Select Other Item**］ を選択します。

   ![表示ページテンプレートのプレビューに使用するコンテンツを選択します。](./creating-and-managing-display-page-templates/images/07.png)

1. 選択ダイアログで、表示ページテンプレートのプレビューに使用するコンテンツをクリックします。
1. または、表示ページテンプレートエディターから、 ［**プレビュー**］(![Preview](../../../images/icon-preview.png)) ボタンをクリックし、プレビューしたいコンテンツを選択します。

   ![ [プレビュー]ボタンをクリックして、表示ページテンプレートのマッピングをプレビューします。](./creating-and-managing-display-page-templates/images/08.png)

## 表示ページテンプレートの使用量を見る

> 対応可能：Liferay DXP/Portal 7.4以降

**Actions** メニュー（![Actions](../../../images/icon-actions.png)）で、異なる表示ページテンプレートを管理することが可能です。 ここから、 ［**利用数を表示**］ オプションで、特定の表示ページテンプレートを使用しているコンテンツのリストが表示されます。

```{note}
* 利用数を表示*オプションは、デフォルトの表示ページテンプレートに割り当てられたコンテンツの使用量を提供するものではありません。
```

![［アクション］メニューを使った表示ページテンプレートの管理](./creating-and-managing-display-page-templates/images/06.png)

一部のコンテンツで使用されている表示ページ用テンプレートを削除する前に、コンテンツから表示ページ用テンプレートの割り当てを解除する方法が2つあります。

- デフォルトに割り当てる：コンテンツは現在の表示ページテンプレートから割り当てを解除し、コンテンツタイプとサブタイプのデフォルトの表示ページテンプレートに割り当てます（該当する場合）。
- 割り当て解除：コンテンツはどの表示ページテンプレートにも割り当てられていません。

表示ページテンプレートの使用量を表示し、コンテンツの割り当てを解除するには、

1. プロダクトメニューを開き、 [**デザイン**] &rarr; [**ページテンプレート**] に移動します。
1. ［**表示ページテンプレート**］ タブをクリックします。
1. 表示ページテンプレートの ［**Actions**］ メニュー（![Actions](../../../images/icon-actions.png)）をクリックし、 ［**利用数を表示**］ を選択してください。
1. 表示ページテンプレートを使用するコンテンツのリストから、1つ以上の要素を選択します。
1. 右上の ［**Actions**］ メニュー (![Actions](../../../images/icon-actions.png)) をクリックし、 ［**Assign to Default**］ または ［**Unassigned**］ を選択します。
1. [**OK**] をクリックします。

コンテンツを新しい表示ページテンプレートに割り当てた場合は、コンテンツが期待どおりに表示されることを確認してください。 コンテンツのプレビューと公開については、 [Publishing Content with Display Pages](./publishing-content-with-display-pages.md)を参照してください。

## 追加情報

- [Publishing Content with Display Pages](./publishing-content-with-display-pages.md)
- [SEOとOpen Graphの設定](./configuring-seo-and-open-graph.md)
- [コンテントページの使用](./../../creating-pages/using-content-pages.md)
- [コンテンツページへの要素の追加](../../creating-pages/using-content-pages/adding-elements-to-content-pages.md)
