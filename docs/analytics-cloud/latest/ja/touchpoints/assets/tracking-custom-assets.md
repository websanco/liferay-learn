# カスタムアセットのトラッキング

Liferay Analytics Cloudは、フォーム、ブログ、ドキュメントとメディア、ウェブコンテンツなどの組み込みのLiferay DXPアセットを検出して分析することができます。 ただし、カスタム アプリ内のアセットを分析するには、アプリの HTML にタグを付ける必要があります。

## アセットイベント

Analytics Cloud JavaScript プラグインには、追跡できる以下のイベントが含まれています。

`AssetClicked`: ユーザーがアセット領域をクリックした。 また、クリックされたタグの情報も掲載しています。

`AssetDepthReached`: アセットエリアのスクロールイベント。 また、ユーザーが到達したコンテンツの深さに関する情報（例えば、ユーザーがスクロールしたブログ記事の下の方まで）も掲載されています。

`AssetViewed`: ユーザーがアセットを閲覧します。

`AssetDownloaded`: ユーザーがアセットをダウンロードするリンクをクリックしました。

`AssetSubmitted`: アセットエリアでのフォーム送信。 これは、HTMLフォーム要素の下にサブミットの入力タイプを配置する必要があります。

## 必要なメタデータ

カスタムエンティティのトラッキングを有効にするには、以下の情報が必要です。 この情報は、リストされた属性を使ってHTMLで指定する必要があります。

**アセット・タイプ（文字列）：** 追跡するアセット・タイプ。 このためのHTML属性はdata-analytics-asset-typeです。 この属性の値は、エンティティの正確な型ではないことに注意してください。 カスタムエンティティの場合、この値は常にカスタムです。

**アセットID（文字列）：** アセットの一意の識別子。 このためのHTML属性はdata-analytics-asset-idです。

**アセットのカテゴリ（文字列、オプション）：** アセットを含むカスタム アプリのカテゴリ。 このためのHTML属性はdata-analytics-asset-categoryです。 これを利用して、カスタムアプリを名前で識別することができます（例：［polls］）。 カテゴリ内では、すべてのアセットIDは一意でなければならないことに注意してください。

**アセットのタイトル（文字列、オプション）：** アセットのタイトル。 このためのHTML属性はdata-analytics-asset-titleです。

## アセットイベントのトラッキング

例えば、カスタムPollsアプリでポールを追跡したい場合、次のようなHTMLを使用することができます。

``` html
<div>
<h1> What's your favorite food? </h1>
<form action="/submit.php">
<div>
  <input type="radio" id="sushi" name="food" value="Sushi"
     checked>
  <label for="sushi">Sushi</label>
</div>
<div>
  <input type="radio" id="pizza" name="food" value="Pizza">
  <label for="pizza">Pizza</label>
</div>
<input type="submit" value="Submit" />
</form>
</div>
```

このポールのイベントを追跡するには、上記の属性を div に追加します。

``` html
<div data-analytics-asset-type="custom"
  data-analytics-asset-id="favorite-food-poll"
  data-analytics-asset-category="polls"
  data-analytics-asset-title="What is your favorite food Poll">

<h1> What's your favorite food? </h1>
...
</div>
```

``` note::
   You must add these attributes to each individual asset that you want Analytics Cloud to track. However, you can populate the attributes’ values via a script, therefore automating this process for each asset.
```

### ダウンロードの追跡

ダウンロードを追跡するには、アクションをトリガーする要素にこの属性をタグ付けする必要があります。

``` html
data-analytics-asset-action="download"
```

例えば、上記の世論調査は、世論調査の指示を含むPDFファイルのダウンロードリンクを備えています。

``` html
<div data-analytics-asset-type="custom"
  data-analytics-asset-id="favorite-food-poll"
  data-analytics-asset-category="polls"
  data-analytics-asset-title="What is your favorite food Poll">

<a href="/poll-instructions.pdf" data-analytics-asset-action="download">Download the Poll Instructions </a>

<h1> What's your favorite food? </h1>
...
</div>
```
