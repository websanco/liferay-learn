# ページのフレンドリURLの設定

Liferay DXPを使用すると、サイトの各ページにフレンドリURLを設定できます。 これらのURLを使用すると、ページのアドレスを簡単に理解して思い出すことができると同時に、サイトをクロールする検索エンジンやその他のツールに重要な情報を提供できます。

サイトページを作成すると、ページの名前と一致するフレンドリURLが自動的に生成されますが、いつでも変更できます。 また、Liferay DXPは、リンク切れを防ぎ、変更を簡単に元に戻すことができるように、ページに以前に使用されたURLの履歴を保持します。

## ページのフレンドリURLの変更

ページのフレンドリURLを変更するには、*[プロダクトメニュー]* を開き、*[サイトビルダー]* → *[ページ]* に移動します。 次に、カスタマイズするページの*アクション*ボタン（![Actions button](../../../images/icon-staging-bar-options.png)）をクリックし、 *[設定]* を選択します。

![カスタマイズするページの[アクション]ボタンをクリックし、[設定]を選択します](./configuring-your-pages-friendly-url/images/01.png)

または、カスタマイズするページに移動して、*アプリケーションバー*の右上にある*[ページ設定]* ボタン（![Configure Page Button](../../../images/icon-cog.png)）をクリックして、個々のページ設定にアクセスすることもできます。

[一般]タブで、*[フレンドリURL]* フィールドを使用して、ページの新しいURLを入力します。 ローカライズされたURLを設定するには、*[言語フラグ]* をクリックして、目的の言語を選択します。 完了したら、* [保存]* をクリックします。

![[フレンドリURL]フィールドを使用して、ページのカスタムURLを設定します。](./configuring-your-pages-friendly-url/images/02.png)

このフィールドに入力された値は、その[サイトURL](./../../site-settings/managing-site-urls/configuring-your-sites-friendly-url.md)に追加され、一意である必要があります。

```{note}
カスタムURLを設定する場合、サイトのページ階層を維持する必要はありません。 つまり、子ページは、フレンドリURLの一部として親を含める必要がありません。
```

## ページのURL履歴の管理

Liferay DXPは、ページに使用されるすべてのフレンドリURLのリストを維持します。 つまり、フレンドリURLを変更しても、手動で削除しない限り、リンク切れを防ぐために古いURLがページに関連付けられたままになります。 古いURLを使用するページリクエストの場合、LiferayはアクティブなフレンドリURLを指すリダイレクトをブラウザ（または他のコンシューマ）に提供します。

ページに関連付けられているURLの完全なリストを表示および管理するには、*履歴*アイコン（![Friendly URL History icon](../../../images/icon-history.png)）をクリックします。 選択した言語のページのアクティブなURLと古いURLを表示するモーダルウィンドウが開きます。 *[言語フラグ]* ボタンをクリックして、他の言語の設定されたURLを表示することもできます。

![言語ごとにページのフレンドリーURLの履歴を表示します。](./configuring-your-pages-friendly-url/images/03.png)

ここから、古いURLにカーソルを合わせると、次のオプションが表示されます。

**URL を復元**：アクティブなURLを古いものに置き換える場合は、アクティブにするURLの*[URLを復元]* ボタン（![Restore icon](../../../images/icon-restore2.png)）をクリックします。

**記憶したURL情報を消去**：ページに関連付けられたフレンドリURLが不要になった場合は、*[記憶したURL情報を消去]* ボタン（![Delete icon](../../../images/icon-delete.png)）をクリックします。 ユーザーが消去されたURLを使用してページにアクセスしようとすると、リンクが壊れ、Liferayは「Not found」というメッセージを表示します。

![古いURLにカーソルを合わせると、[復元]オプションと[消去]オプションが表示されます。](./configuring-your-pages-friendly-url/images/04.png)

## 追加情報

  - [Configuring Your Site's Friendly URL](./../../site-settings/managing-site-urls/configuring-your-sites-friendly-url.md)
  - [Configuring Individual Pages](./configuring-individual-pages.md)

<!--Include Reference to SEO article when finished.-->
