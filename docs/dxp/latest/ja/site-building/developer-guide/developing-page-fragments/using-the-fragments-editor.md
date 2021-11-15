# フラグメントエディターの使用

Liferay DXP には、コンテンツページフラグメントを作成するための組み込み[エディター](../reference/fragments/page-fragment-editor-interface-reference.md)が含まれています。 エディターにアクセスするには、*サイトメニュー*の *[デザイン]* → *[フラグメント]* に移動します。 ここから、既存のフラグメントとコレクションを表示および管理したり、新しいフラグメントとコレクションを作成したりできます。

  - [フラグメントコレクションの作成](#creating-a-fragment-collection)
  - [新しいフラグメントの作成](#creating-a-new-fragment)

## フラグメントコレクションの作成

新しいフラグメントを作成する前に、最初にそれらを配置する新しいコレクションを作成する必要があります。

1.  *サイトメニュー*を開き、 *[デザイン]* → *[フラグメント]* に移動します。

    ```{note}
    Liferay DXP 7.1 および 7.2 では、*プロダクトメニュー* を開き、*サイト* → *サイトビルダー* → *ページフラグメント* に移動します。
    ```

2.  *[Add]* ボタン（![追加ボタン](../../../images/icon-duplicate.png)）をクリックして、新しいコレクションの*名前*と*説明*を入力します。 コレクションの名前は、コンテンツページのサイドバーの[フラグメントとウィジェット]パネルに表示されます。

    ```{tip}
    機能ごと、またはチームや部門ごとにフラグメントをグループ化するコレクションを作成します。
    ```

3.  *[保存]* をクリックします。

![コレクションはフラグメントを整理するのに役立ちます。](./using-the-fragments-editor/images/01.png)

最初のコレクションを作成したら、新しいフラグメントの作成を開始できます。

## 新しいフラグメントの作成

1.  目的のコレクションに移動し、 *[Add]*（![追加ボタン](../../../images/icon-add.png)）をクリックして、新しいフラグメントを作成します。

    ```{note}
    Liferay DXP 7.3 より前では、フラグメントはセクションまたはコンポーネントのいずれかでした。 Liferay DXP 7.3以降では、ページフラグメントはすべてコンポーネントです。
    ```

2.  フラグメントの*名前*を入力し、*[保存]* をクリックします。

    ![新しいコンポーネントの名前を入力します。](./using-the-fragments-editor/images/02.png)

3.  [コード] タブで、CSS、HTML、および JavaScript フィールドを使用してフラグメントのリソースを追加します。 ここでは、さまざまなデバイスコンテキストでのフラグメントの外観のライブプレビューを表示することもできます。

    以下の例では、編集可能なテキストを含むカードコンポーネントを追加しています。

    ``` html
    <div class="marketing-card-fragment-01">
      <div class="card">
        <lfr-editable id="01-card-image" type="image">
          <img src="https://cdn.dribbble.com/users/1408464/screenshots/9323535/media/a5b9a76256562e878ecc6dc5cd0fadf0.png" class="card-img-top" alt="2020 - Try New Things">
        </lfr-editable>
        <div class="card-body">
          <lfr-editable id="02-card-title" type="rich-text">
            <h5 class="card-title">Editable Card title</h5>
          </lfr-editable>
          <lfr-editable id="03-card-text" type="rich-text">
            <p class="card-text">Here is some editable text.</p>
          </lfr-editable>
          <lfr-editable id="04-card-link" type="link">
            <a href="#" class="btn btn-primary">Editable link</a>
          </lfr-editable>
        </div>
      </div>
    </div>
    ```

    ``` css
    .marketing-card-fragment-01 .card img {
      max-width: 100%;
    }
    ```


    ![Add CSS, HTML, and Javascript resources to the Fragment and see a live preview.](./using-the-fragments-editor/images/03.png)

4.  [設定] タブで、`[JSON]` フィールドを使用して、[設定オプション](./adding-configuration-options-to-fragments.md)) をページ フラグメントに追加します。

    ![ページ フラグメントに構成オプションを追加します。](./using-the-fragments-editor/images/04.png)

5.  *[Publish]* をクリックして、[コンテンツページ](../../creating-pages/understanding-pages/understanding-pages.md#content-pages)で使用できるようにします。

    ![フラグメントはコンテンツ ページで使用できます。](./using-the-fragments-editor/images/05.png)

フラグメントの作成中、変更内容はフラグメントが公開されるまで自動的にドラフトとして保存されます。 コレクションに追加すると、フラグメントエディターでいつでもフラグメントをコピー、エクスポート、編集、および削除できます。 ページ フラグメントで使用可能なアクションの詳細は、[Managing Page Fragments](../../displaying-content/using-fragments/managing-page-fragments.md)を参照してください。

```{note}
Liferay DXP 7.2 SP1以降および Liferay Portal CE 7.2 GA2以降では、*グローバル* サイトでページ フラグメントを作成して、すべてのサイトで利用できるようにすることができます。 これらのバージョンの最初のリリースでこの機能を公開するには、`com.liferay.fragment.web.internal.configuration.FragmentGlobalPanelAppConfiguration.config` という名前の `.config` ファイルを作成し、`enabled=B"true"` プロパティを追加する必要があります。 それを Liferay DXP インスタンスの `osgi/configs` フォルダにコピーします。 グローバル ページ フラグメントは子サイトに継承され、グローバル サイトからのみ編集できます。 グローバル ページ フラグメントがグローバル サイトから参照するすべてのリソースは、ページ フラグメントを利用するサイトにコピーされます。
```

## 追加情報

  - [フラグメントツールキットの使用](./using-the-fragments-toolkit.md)
  - [Adding Configuration Options to Fragments](./adding-configuration-options-to-fragments.md)
