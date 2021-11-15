# コレクションのパーソナライズ

> 利用可能: Liferay Portal 7.3 GA6、Liferay DXP 7.3以降

デフォルトでは、すべてのユーザーがコレクションのすべてのコンテンツを表示でき、*Anyone* グループに属しています。 [セグメント](../segmentation/creating-and-managing-user-segments.md)を使用して、コレクションのアイテムの*個人用バリエーション*を作成できます。 個人用バリエーションを使用する場合、コレクションの一部であるアイテムは変更されませんが、コレクションページまたはコレクション表示フラグメントには、セグメントに対してフィルタリングされたアイテムが表示されます。

```{note}
To customize a Collection using Segments, you must define the Segments first. For more information, see [Creating and Managing User Segments](../segmentation/creating-and-managing-user-segments.md).
```

![コレクションは、デフォルトで誰でも利用できます](./personalizing-collections/images/02.png)

次の例を考えてみましょう。 あなたは、登録ユーザーに限定プロモーションを提供することで、オンラインのキッチン用品ストアでの売り上げを伸ばしたいと考えています。 登録ユーザー向けのプロモーションや商品に関するニュースを含むコレクションを作成します。 商品に関するニュースはすべての人に表示したいが、プロモーションは登録ユーザーのみに限定したいと考えています。 この例では、登録ユーザー用の新しいセグメントを作成し、そのセグメントを、プロモーションコンテンツをフィルタリングする新しい個人用バリエーションにリンクすることができます。

```{tip}
You can create multiple Personalized Variations for a Collection, and edit or delete them as needed. You can also edit the *Anyone* Personalized Variation, but you cannot delete it.
```

## 個人用バリエーションの作成

1.  *[サイト管理]* → *[サイトビルダー]* → *[コレクション]* に移動します。

2.  *[Collections]* タブから、カスタマイズしたいコレクションの横にある*オプション*メニュー（![Options](../../../images/icon-staging-bar-options.png)）をクリックし、*[編集する]* を選択します。

    ![カスタマイズするコレクションを編集する](./personalizing-collections/images/01.png)

3.  *[Personalized Variations]* で、*[New Personalized Variation]* をクリックするか、新規ボタン（![New](../../../images/icon-plus.png)）をクリックします。

4.  *[New Personalized Variation]* ダイアログで、このコレクションに関連付けるセグメントをクリックします。

5.  個人用バリエーションのプロパティを構成します。 詳細は、[コレクションの作成](../../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md)を参照してください。

    たとえば、手動コレクションの場合、個人用バリエーションに表示するアイテムを選択できます。 動的コレクションの場合は、フィルターを追加してコンテンツをカスタマイズできます。

    ![個人用バリエーションのプロパティを構成する](./personalizing-collections/images/04.png)

6.  動的コレクションをカスタマイズする場合は、*[保存]* をクリックします。

7.  このコレクションの一部であるアイテムをプレビューするには：

      - 手動コレクションでは、個人用バリエーションの名前をクリックして、アイテムを表示します。

      - 動的コレクションでは、個人用バリエーションの名前の横にある*オプション*メニュー（![Options](../../../images/icon-staging-bar-options.png)）をクリックし、*[項目の参照]* を選択します。

        ![カスタマイズするコレクションを編集する](./personalizing-collections/images/03.png)

## Liferay DXP 7.2

### コンテンツセットのパーソナライゼーション

次に、[セグメント](../segmentation/creating-and-managing-user-segments.md)を使用して、コンテンツセットのパーソナライズを示します。 この例では、*ホーム*ページに表示されるデフォルトのコンテンツセットを作成します。 次に、それを変更して、*American Engineers*セグメントのメンバー向けの技術記事を含む個人用バリエーションを作成します。

コンテンツセットに詳しくない場合は、[Creating Content Sets](../../../content-authoring-and-management/collections-and-collection-pages/creating-collections.md#creating-content-sets)の記事を読んでから開始してください。

#### デフォルトのコンテンツセットの作成と設定

まず、デフォルトのコンテンツセットを作成し、アセットパブリッシャーを使用してホームページに設定します。

1.  *[サイト管理]* → *[Content & Data]* → *[コンテンツセット]* に移動します。

2.  追加ボタン（![Add](../../../images/icon-add.png)）をクリックし、*[手動選択]* を選択します。

3.  *[ホームページコンテンツ]* という名前を付けます。

4.  新しいコンテンツセットの場合は、*[アセットエントリー]* の横にある*[選択]* をクリックし <0>[基本Webコンテンツ]* を選択します。</p>

    ![[選択]をクリックして、新しいアセットエントリーを追加します。](./personalizing-collections/images/20.png)</li>

5

*[Select Basic Web Content]* ページで、追加するコンテンツの横にあるチェックボックスをオンにして*[追加]* をクリックします。

6

*ホーム*ページに移動し、アセットパブリッシャーをページに追加します。

7

アセットパブリッシャーの*[設定]* を開きます。

8

*[データ選択方法]* の下で、*[コンテンツセット]* を選択します。

9

*[コンテンツセットの選択]* の下で、*[選択]* をクリックし、*[ホームページコンテンツ]* を選択して、*[保存]* をクリックします。</ol>

これで、構成したコンテンツセットがアセットパブリッシャーの*ホームページ*に表示されます。 次に、コンテンツセットを個人用に構成します。

#### コンテンツセットのパーソナライズ

次に、技術者向けのコンテンツセットを作成し、その表示を構成します。

1.  *[サイト管理]* からコンテンツセットに戻ります。

2.  *[New Personalized Variation]* をクリックし、*[American Engineers]* セグメントを選択します。

    ![新しい個人用バリエーションを作成します。](./personalizing-collections/images/21.png)

3.  *[アセットエントリー]* の横にある*[選択]* をクリックし、*[基本Webコンテンツ]* を選択します。

4.  エンジニアの読者に適した記事を選択し、*[追加]* をクリックします。

これで、*American Engineers*セグメントのメンバーが、この表示されているコンテンツセットを表示すると、デフォルトバージョンではなく、パーソナライズされたバージョンが表示されます。 次に、*シミュレータ*を使用してこれをテストします。

## 関連情報

  - [コンテンツページのパーソナライゼーション](./content-page-personalization.md)
  - [コレクションとコレクションページについて](../../../content-authoring-and-management/collections-and-collection-pages/about-collections-and-collection-pages.md)
  - [Creating User Segments](../segmentation/creating-and-managing-user-segments.md)
  - [Assigning Roles to User Segments](../../../users-and-permissions/roles-and-permissions/assigning-roles-to-user-segments.md)
