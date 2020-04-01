# 商品カテゴリを使用してカタログを整理する

商品はカテゴリに分類できます。 これらのグループ化により、複数の商品をグループ化し、一括して扱うことができます。 商品カテゴリを使用して、商品のセットに割引やその他のオファーを適用したり、購入者が商品を見つけやすくしたり、特定の商品を特定のアカウントまたはアカウントグループにプロモートしたりできます。

カテゴリを階層的に編成して、カテゴリの分類を形成することもできます。 商品をカテゴリに整理するには、最初にカテゴリを作成してから商品を割り当てる必要があります。 さらに、すべてのLiferay Commerce商品はグローバルレベルで処理されるため、すべてのボキャブラリとカテゴリはグローバルスコープレベルである必要があります。

グローバルスコープとサイト管理の詳細については、この記事で[データ範囲](https://help.liferay.com/hc/articles/360018168991-Data-Scopes)および[設定範囲](https://help.liferay.com/hc/articles/360017895452-Introduction-to-Setting-Up#configuration-scope)について参照してください。

## カテゴリ作成

上記で説明したように、分類の作成はグローバルスコープレベルで行う必要があります。

*[Control Panel]* → ![Navigation Compass](./organizing-your-catalog-with-product-categories/images/05.png) *[Go to Other Site]* → *[My Sites]* → *[Global]*に移動します。 *[Global]*サイトで、*[Categorization]* → *[Categories]*をクリックします。

![[Vocabularies]ページ](./organizing-your-catalog-with-product-categories/images/01.png)

すべてのカテゴリには、親コンテナであるボキャブラリが必要です。 カテゴリを作成する前に、*最初に*[ボキャブラリを作成](https://help.liferay.com/hc/en-us/articles/360018171951-Defining-Categories-for-Content)します。 ボキャブラリを作成するには、（+）ボタンをクリックします。

| フィールド                     | 説明                                           |
| ------------------------- | -------------------------------------------- |
| Name                      | ボキャブラリの名前                                    |
| Description               | ボキャブラリの説明                                    |
| Allow Multiple Categories | 1対1または1対多の関係を許可するトグル                         |
| Choose Asset Type         | *コマース商品*を含むLiferay DXPのすべてのアセットタイプのリスト       |
| Required                  | すべてのコマース商品にカテゴリを関連付ける必要があるかどうかを制御するために切り替えます |

![ボキャブラリの作成](./organizing-your-catalog-with-product-categories/images/02.png)

ボキャブラリを作成したら、関連するカテゴリを作成します。 ボキャブラリをクリックしてから、（+）ボタンをクリックします。

![カテゴリ作成](./organizing-your-catalog-with-product-categories/images/03.png)

*[Allow Multiple Categories]*がボキャブラリに対し有効になっている場合は、必要な数のカテゴリを作成できます。

## 商品カテゴリを管理

ボキャブラリとカテゴリを使用して分類を作成したら、カタログ内の商品にカテゴリを追加できます。

*[Control Panel]* → *[Commerce]* → *[Products]*に移動します。 商品をクリックしてから、*[Categorization]*タブをクリックします。 （Miniumを使用してサンプルデータを含むサイトを作成した場合は、*[Transmission Fluid]*をクリックします。 *[Transmission Fluid]*商品はすでにサンプルのボキャブラリとカテゴリにリンクされています。）

![[Categorization]タブ](./organizing-your-catalog-with-product-categories/images/04.png)

適切なボキャブラリのラベルの下にある*[Select]*ボタンをクリックし、表示されるポップアップで、該当する各カテゴリの横にあるチェックボックスをオンにします。

## 追加情報

**注1 **：Miniumなどのアクセラレータを使用してサンプルデータを使用してサイトを作成した場合、ボキャブラリとカテゴリはグローバルスコープに入力されます。 これは、すべてのサンプルカテゴリがすべてのサイトで利用できることを意味します。

**注2 **：カテゴリは階層的にネストします。 これにより、複数のカテゴリをグループ化して単一のユニットとして扱うことができますが、管理者がボキャブラリ全体を表示することが難しくなる場合もあります。 すべてのカテゴリを一度に表示することができます。 これを行うには、*[Control Panel]* → *[Configuration]* → *[System Settings]* → *[Assets]*に移動して、*[Asset Categories Web]*でドロップダウンメニューから*[Flattened Tree]*を選択します。

![アセットカテゴリWeb](./organizing-your-catalog-with-product-categories/images/06.png)

*[Save]*をクリックして変更を適用します。 いつでもデフォルトのビューに戻すことができます。

商品の整理を開始するには、[Creating a New Product Category](./creating-a-new-product-category.md)を参照してください。
