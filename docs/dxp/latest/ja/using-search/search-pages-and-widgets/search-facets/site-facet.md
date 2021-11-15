# サイトファセット

サイトファセットは、検索結果を特定のサイトに存在するものに絞り込みます。 検索されたキーワードに一致するコンテンツを含む各サイトは、ファセット用語として表示されます。

![サイトファセットの結果の例。](site-facet/images/01.png)

```{important}
For the Site Facet to display multiple sites, the Search Bar must be configured to search *Everything*. If not searching for Everything, only the current Site is searched, and the Site Facet has nothing to display. When this occurs, the Site Facet is hidden on the page.

Configuring the globally embedded page-top Search Bar to search for Everything not only configures the embedded Search Bar on all pages. It also ensures that the Search Page’s Search Bar searches Everything, because the page-top Search Bar's configuration overrides the Search Page's Search Bar configuration. The same does not apply to other Search Bar widgets in the Site. Each of these must be configured as desired.
```

グローバル検索バーが無効になっている場合は、すべてを検索するように検索ページの検索バーウィジェットを設定します。

検索範囲を設定するには、

1.  検索バーのオプションアイコン（![Options icon](../../../images/icon-app-options.png)）をクリックし、*[設定]* をクリックします。

    ![[設定]オプションをクリックします。](site-facet/images/02.png)

2.  [スコープ]オプションを*[Everything]* に設定します。

    ![ドロップダウンメニューで[Everything ]を選択します。](site-facet/images/03.png)

    完了したら、* [保存]* ボタンをクリックします。

## サイトファセットの設定

サイトファセットを設定するには、ファセットのオプションメニュー（![Click on the options icon of the search bar.](../../../images/icon-app-options.png)）を開き、*[設定]* をクリックします。

![[設定]オプションをクリックします。](site-facet/images/04.png)

**表示設定：***[Default]*、*[コンパクトレイアウト]*、および*[ラベルレイアウト]* から選択します。 デフォルトのレイアウトでは、各用語の横にチェックボックスが表示されますが、コンパクトレイアウトでは表示されません。 ラベルレイアウトでは、用語ごとにクリック可能な小さなラベルが表示されます。

[詳細設定]セクションには、追加のオプションが含まれています。

**サイトパラメータ名：** ファセットのURLのパラメーター名を変更します。 デフォルト値は*site*です。

**最大ターム数：**ファセットに一致する用語がいくつ見つかったかに関係なく、表示するファセット用語の最大数を設定します。

**頻度の閾値：**用語がファセット用語のリストに表示されるために必要な最小頻度を設定します。 たとえば、ファセットの頻度の閾値が3に設定されている場合、一致する結果が2つの用語は用語結果リストに表示されません。

**表示頻度：**用語頻度を表示するかどうかを選択します。

完了したら、* [保存]* ボタンをクリックします。
