# コレクションの作成

```{note}
   この情報はLiferay DXP 7.3以降に適用されます。 以前のLiferay DXPのバージョンについては、 [Liferay DXP 7.2](#liferay-dxp-7-2) を参照してください。
```

**手動** または **動的** コレクションを作成できます。 コレクションの種類については、 [コレクションとコレクションページについて](./about-collections-and-collection-pages.md) を参照してください。

<a name="creating-a-manual-collection" />

## 手動コレクションの作成

1. ［**サイト管理**］ &rarr; ［**サイトビルダー**］ &rarr; ［**コレクション**］ に移動します。
1. **新規** アイコン（![Add](../../images/icon-add.png)）をクリックし、 ［**手動コレクション**］ を選択します。
1. 手動コレクションの **タイトル** を追加し、 ［**保存**］ をクリックします。
1. ［**項目のタイプ**］ ドロップダウンメニューで、コレクションに含める項目のタイプを選択します。
    - 項目のタイプを1つだけ含める場合

       1. ［**単一項目のタイプ**］ の下でタイプを選択します。
       1. オプションで、 ［**項目のタイプ**］ にサブタイプが含まれている場合は、 ［**Item Subtype**］ ドロップダウンメニューでサブタイプを選択します。
       1. ［**保存**］ をクリックします。

       ![手動コレクションのコンテンツタイプとサブタイプを設定します](./creating-collections/images/01.png)

    - 項目のタイプを複数含める場合

       1. ［**複数項目のタイプ**］ の下の ［**Select More Than One**］ を選択します。
       1. デュアルリストボックスで、コレクションに含める項目のタイプを追加または削除します。

       ![手動コレクションのさまざまな項目のタイプを選択します](./creating-collections/images/02.png)

1. ［**Save**］ をクリックします。
1. ［**Collection Items**］ の隣にある ［**Select**］ をクリックし、項目のタイプを1つ選択します。

   ![手動コレクションに含める項目を選択します](./creating-collections/images/03.png)

1. 選択ダイアログで、含める項目にチェックを入れます。
1. ［**追加**］ をクリックします。
1. 手動コレクションに複数の項目のタイプが含まれている場合は、他のタイプについて手順6〜8を繰り返します。

なお、ドキュメントとメディアフォルダおよびWebコンテンツフォルダをコレクションに追加できます。

```{note}
   ドキュメントとメディア、およびWebコンテンツフォルダーはコレクションに追加できますが、フォルダがコレクションページまたはアセットパブリッシャーウィジェットで公開されている場合、コンテンツは表示されません。 これらのアセットがコレクションに追加された場合でも、個別に表示することしかできません。 これは既知の制限であり、将来のリリースで修正される予定です。
```

<a name="creating-a-dynamic-collection" />

## 動的コレクションの作成

1. ［**サイト管理**］ &rarr; ［**サイトビルダー**］ &rarr; ［**コレクション**］ に移動します。
1. **新規** アイコン（![Add](../../images/icon-add.png)）をクリックし、 ［**動的コレクション**］ を選択します。
1. 手動コレクションの **タイトル** を追加し、 ［**保存**］ をクリックします。
1. ［**項目のタイプ**］ ドロップダウンメニューで、コレクションに含める項目のタイプを選択します。
    - 項目のタイプを1つだけ含める場合,

       1. ［**単一項目のタイプ**］ の下でタイプを選択します。
       1. オプションで、 ［**Item Type**］ にサブタイプが含まれている場合は、 ［**Item Subtype**］ ドロップダウンメニューでサブタイプを選択します。
       1. **Item Subtype** を特定のフィールドで絞り込むには、 ［**Filter by Field**］ スイッチを有効にし、 ［**Select**］ をクリックしてフィールドを選択し、 ［**Apply**］ をクリックします。
       1. ［**保存**］ をクリックします。

       ![コレクション内の項目のサブタイプは、項目フィールドのいずれかで絞り込みできます](./creating-collections/images/09.gif)

    - 項目のタイプを複数含める場合、

       1. ［**複数項目のタイプ**］ の下で ［**タイプを選択**］ を選択します。
       1. デュアルリストボックスで、コレクションに含める項目のタイプを追加または削除します。

       ![手動コレクションのさまざまな項目のタイプを選択します](./creating-collections/images/02.png)

1. ［**スコープ**］ 、 ［**絞り込み**］ 、 ［**コンテンツ推薦**］ 、または ［**順序付け**］ に基づいて、動的コレクションの基準を設定します。

   **範囲:** 動的コレクション内の項目のソースを定義します。 デフォルトでは、ソースは現在のサイトです。

   **絞り込み:** 動的コレクション内の項目のルールを設定します。 たとえば、「プロモーション」タグが付いた項目のみを含めることができます。

   ![1つ以上のルールを追加して、動的コレクションを絞り込みます。](./creating-collections/images/10.png)

    ```{tip}
        ［Add］ボタンをクリックして、絞り込みに複数のルールを追加できます。 動的コレクションの最終的なコンテンツは、すべてのルールを追加した結果です。
    ```

   **コンテンツ推薦:** このオプションを有効にすると、ユーザーの行動に基づいてコンテンツが表示されます。 詳細は、[コンテンツリコメンデーションの設定](../../site-building/personalizing-site-experience/experience-personalization/configuring-content-recommendations.md)を参照してください。

   ```{note}
    コンテンツ推薦オプションは、DXPインスタンスをAnalytics Cloudに接続し、コンテンツを同期する際に使用できます。
    ```

   **順序付け:** コレクション内の項目は、ここで定義した順序基準を使用して表示されます。

1. ［**保存**］ をクリックします。

<a name="creating-a-collection-from-an-asset-publisher" />

## アセットパブリッシャーからコレクションを作成する

[アセットパブリッシャーウィジェット](../../site-building/displaying-content/using-the-asset-publisher-widget/displaying-assets-using-the-asset-publisher-widget.md)から新しいコレクションを作成できます。 これは、他のページでコレクションとして使用するアセットパブリッシャーのカスタマイゼーションがある場合に役立ちます。

![アセットパブリッシャーウィジェットをコレクションに変換できます。](./creating-collections/images/06.png)

1. ［**サイト管理**］ &rarr; ［**サイトビルダー**］ &rarr; ［**Pages**］ に移動します。
1. アセットパブリッシャーに含めるページの隣にある **アクション** ボタン（![Add](../../images/icon-actions.png)）をクリックし、 ［**Edit**］ を選択します。

   ![アセットパブリッシャーを含むページを編集します。](./creating-collections/images/11.png)

1. ［**Asset Publisher**］ にカーソルを合わせ、ウィジェットのメニューの **オプション** アイコン（![Options](../../images/icon-app-options.png)）をクリックして、 ［**Configuration**］ を選択します。

   ![アセットパブリッシャーウィジェットを設定します。](./creating-collections/images/07.png)

1. ［**Asset Publisher - Configuration**］ ダイアログで、 ［**Setup**］ タブをクリックしてから、 ［**Asset Selection**］ サブタブをクリックします。
1. 下にスクロールして、 ［**Create a Collection from this Configuration**］ をクリックします。

   ![［Create a collection from this configuration］をクリックして、アセットパブリッシャーウィジェットを新しいコレクションに変換します。](./creating-collections/images/08.png)

1. コレクションの **タイトル** を入力し、 ［**Save**］ をクリックします。
1. ［**アセットパブリッシャー - 設定**］ ダイアログを閉じます。
1. ［**サイト管理**］ &rarr; ［**サイトビルダー**］ &rarr; ［**コレクション**］ に新しいコレクションがあります。

<a name="liferay-dxp-72" />

## Liferay DXP 7.2

### コンテンツセットの作成

コンテンツセットは、サイト管理のコンテンツセットインターフェースから作成できます。 コンテンツセットは、手動選択または動的選択のいずれかを使用できます。 コンテンツセットはいくつでも作成でき、アセットパブリッシャーまたはカスタムアプリケーションを介して表示できます。 コンテンツセットには、指定した基準に基づいてさまざまなユーザーにさまざまなエクスペリエンスを提供する [個人用バリエーション](../../site-building/personalizing-site-experience/experience-personalization/personalizing-collections.md#content-set-personalization) を持たせることもできます。 基準管理はアセットパブリッシャーと共有されます。 各オプションの詳細は、[Selecting Assets in the Asset Publisher](../../site-building/displaying-content/using-the-asset-publisher-widget/selecting-assets-in-the-asset-publisher-widget.md)を参照してください。

#### 手動コンテンツセットの作成

手動コンテンツセットの作成方法を説明するために、架空の宇宙プログラムWebサイトのフロントページに表示される多数の画像を含むコンテンツセットを作成します。 この演習の準備として、コンテンツセットに使用する適切な画像を **ドキュメントとメディア** にアップロードします。

1. ［**サイト管理**］ &rarr; ［**コンテンツ & データ**］ に移動し、 ［**Content Sets**］ を選択します。

    ![コンテンツセットは、サイト管理の［Content & Data］セクションにあります。](./creating-collections/images/20.png)

2. ![Add](../../images/icon-add.png)をクリックし、 ［**手動選択**］ を選択します。
3. コンテンツセットに **スペースプログラムの画像** という名前を付けます。

次の画面で、コンテンツセットに含めるアセットを選択できます。

1. ［**Select**］ &rarr; ［**Basic Document**］ の順にクリックします。

    ![コンテンツセットに追加するアセットのタイプを選択できます。](./creating-collections/images/21.png)

2. ここで、追加したい画像のボックスにそれぞれチェックを入れて、 ［**Add**］ をクリックします。

これで、このコンテンツセットは、作成したサイトのどこにでも表示できるようになりました。 項目をセットに追加したりセットから削除することができ、どこに表示されていても自動的に更新されます。

#### 動的コンテンツセットの作成

動的コンテンツセットの作成方法を説明するために、「Trending」としてタグ付けされた多数のさまざまなアセットを含むコンテンツセットを作成します。 これを機能させるには、適切なタグが付いた既存のアセットがいくつか必要になります。

1. [**コンテンツセット**] ページから、![Add](../../images/icon-add.png) &rarr; [**動的選択**] の順にクリックします。
2. 名前に **Trending** と入力し、 ［**保存**］ をクリックします。

動的コンテンツセットでは、セット内の項目に対し、 [**ソース**] 、 [**範囲**] 、 [**絞り込み**] 、 [**順序付け**] を選択することができます。

1. [**ソース**] を [**Any**] のままにし、 [**範囲**] を [**現在のサイト**] のままにします。
2. ［**Filter**］ を開き、 ［**Contains Any of the following Tags**］ に設定されていることを確認してから、 ［**Tags**］ ボックスに「trending」と入力します。

    ![コンテンツセットは、アセットパブリッシャーと同じ絞り込みシステムを使用します。](./creating-collections/images/22.png)

3. ［**順序付け**］ を開き、 ［**ソート順**］ を ［**公開日付**］ に、 ［**第２ソート順**］ を ［**Title**］ に設定します。
4. ［**保存**］ をクリックします。

これにより、現在 **Trending** としてタグ付けされている項目を含むコンテンツセットが作成され、今後 **Trending** とタグ付けされた項目はコンテンツセットに自動的に追加されます。

コンテンツセットを作成したので、 [それらをページに表示する](../../site-building/displaying-content/additional-content-display-options/displaying-collections.md#displaying-content-sets) ことができます。

### アセットパブリッシャー設定をコンテンツセットに変換する

このセクションの前の2つのガイドでは、 [コンテンツセットの作成](./creating-collections.md#creating-content-sets) と [コンテンツセットの表示](../../site-building/displaying-content/additional-content-display-options/displaying-collections.md#displaying-content-sets) について説明しました。 次に、既存のアセットパブリッシャー設定をコンテンツセットに変換してみます。

このケースでは、あるページにアセットパブリッシャーを設置し、 **Trending** とタグ付けされた画像をタイトル別にアルファベットの逆順に表示するように設定しています。 これは、 **コンテンツセット** クリエイターで再現してもそれほど難しくないかもしれませんが、アセット パブリッシャーから直接コンテンツセットの定義を作成する方がより簡単です。

1. アセットパブリッシャーの![Options](../../images/icon-app-options.png) &rarr; ［**設定**］ に移動します。
2. ［**Create a content set from this configuration**］ をクリックします。

    ![アセットパブリッシャー設定から直接コンテンツセットを生成できます。](./creating-collections/images/23.png)

3. タイトルを入力し、 ［**保存**］ をクリックします。

そうすれば、新しいコンテンツセットができあがり、サイト上のあらゆる場所でアセットパブリッシャーで使用することができます。

![コンテンツセットは、既存のセットと一緒に追加されます。](./creating-collections/images/24.png)

アセットパブリッシャーの設定をコンテンツセットに変換できました。

<a name="related-information" />

## 関連情報

- [コレクションとコレクションページについて](./about-collections-and-collection-pages.md)
- [コレクションの表示](../../site-building/displaying-content/additional-content-display-options/displaying-collections.md)
- [コレクションのパーソナライズ](../../site-building/personalizing-site-experience/experience-personalization/personalizing-collections.md)
