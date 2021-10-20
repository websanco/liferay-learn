# Open Graphの設定

[Open Graph](https://ogp.me/)のメタタグはすべてのページに埋め込まれているため、Facebook、Twitter、Slackなどの対応するアプリケーションでコンテンツを共有できます。 この動作は、サイトの一般設定から無効にできます。

<!-- It'd be great to have a screenshot here of the practical application of this feature. -->

1.  サイトメニューを開き、*[設定]* → *[Settings]* （以前は *[サイト設定]*）に移動します。

2.  *[Open Graph]* 小見出しまで下にスクロールして、展開します。

3.  *[Open Graphを有効にする]* 設定をオン/オフにしてこの機能を有効/無効にしたり、オプションでOpen Graphタグのデフォルトとして機能するOpen Graph画像を設定します。

![サイトの設定の[全般]タブで、サイト全体のOpen Graph設定を構成します。](./configuring-open-graph/images/01.png)

ここから、Open Graphを有効/無効にしたり、サイトのページに使用されるデフォルトの`og:image`プロパティを定義したりできます。

## Open Graphの有効化と無効化

デフォルトでは、Open Graph `<meta>`タグは、すべてのページに埋め込まれています。 Open Graphに対応するアプリケーションで共有したときに、コンテンツがどのように表示されるかをカスタマイズできます。 この動作を無効にするには、単に*[Open Graphを有効にする]* をオフにします。

![サイトのページに埋め込まれたOpen Graphタグを有効または無効にします。](./configuring-open-graph/images/02.png)

## Open Graph画像の設定

*[Image]* および*[画像の別の説明]* フィールドを使用して、サイトの`og:image`プロパティを定義することができます。

![サイトで使用されるデフォルトのog:imageおよびog:image:altプロパティを定義します。](./configuring-open-graph/images/03.png)

画像を選択すると、DXPは選択した画像の表示方法を決定する多くの構造化プロパティを自動的に追加します。 [画像] フィールドは、次の `<meta>` タグを定義します。

``` html
<meta property="og:image" content="http://example.com/ogp.jpg" />
<meta property="og:image:secure_url" content="https://secure.example.com/ogp.jpg" />
<meta property="og:image:type" content="image/jpeg" />
<meta property="og:image:width" content="400" />
<meta property="og:image:height" content="300" />
```

[画像の別の説明]フィールドにテキストを入力すると、選択した画像のスクリーンリーダーで読み取られるデフォルトのテキストが決まります。 Open Graph画像のローカライズされた別の説明を作成するには、*[言語フラグ]* ボタンをクリックして、設定する言語を選択します。 ここで入力された値によって、デフォルトの`og:image:alt`タグが定義されます。

``` html
<meta property="og:image:alt" content="This is an example." />
```

ここでは、サポートされているコンテキストでOpen Graph画像がどのように表示されるかをプレビューすることもできます。 ただし、ページのURLが共有されている場所によって、画像の比率が変わる場合があります。

![デフォルトのOpen Graph設定をプレビューします。](./configuring-open-graph/images/05.png)

終了したら、*[保存]* をクリックして変更を適用します。

## 追加情報

  - [Search Engine Optimization](./../optimizing_sites.html#search-engine-optimization)
  - [Site Settings UI Reference](./site-settings-ui-reference.md)
  - [Configuring Individual Pages](./../creating-pages/page-settings/configuring-individual-pages.md)
