# タッチ ポイント

```{toctree}
:maxdepth: 3

touchpoints/sites-dashboard.md
touchpoints/pages.md
touchpoints/assets.md
touchpoints/events.md
```

タッチポイントは、ユーザーや顧客がブランドと相互作用するさまざまな方法を反映しています。 Analytics Cloudは、ページビューなどの従来のページアナリティクスと、包括的なサイト、パス、アセットに特化したアナリティクスデータを組み合わせた幅広いアナリティクスデータを提示します。

Analytics Cloudは、サイト全体の包括的なレポートを提供する [サイトワイド レポート ダッシュボード](./touchpoints/sites-dashboard.md) を提示します。 これは、あなたのサイトのユニークな訪問者の数、どのページにアクセスしているか、彼らの興味は何か、などを把握するのに役立ちます。 [個別ページ](./touchpoints/pages/pages.md) のアナリティクスも簡単に見ることができます。 視聴率、訪問者数、バウンス率などが含まれます。 [パスアナリティクス](./touchpoints/pages/paths.md) は、訪問者がどのようにしてページにたどり着くかを示します。 これには、彼らがあなたのページよりも先にどのページを訪問したか、彼らの位置情報やデバイスの種類などの情報が含まれます。 多くのLiferay DXP [アセット](./touchpoints/assets/assets.md) のパフォーマンスを追跡して、ページレベルよりも深く掘り下げて、サイトのパフォーマンスを改善する方法を判断することもできます。 [イベントの分析](./touchpoints/events/events-analysis.md) では、個人がどのようにサイトを訪れ、どのように利用しているかを分析することができます。

![タッチポイント］メニューでは、［サイト］［ページ］［アセット］に関する情報にアクセスすることができます。](./touchpoints/images/01.png)

タッチポイントは、 [サイトダッシュボード](./touchpoints/sites-dashboard.md) からアクセスします。 タッチポイントでは、ページやアセットの追加分析も確認できます。

<a name="viewing-page-data" />

## ページデータの閲覧

サマリーデータのあるページの一覧を表示するには

![ページタブでは、ページや複数の列のデータが一定期間にわたって表示されます。](./touchpoints/images/02.png)

1. タッチポイントセクションの［Sites］をクリックします。

1. ［Pages］ タブをクリックします。

これらのメトリクスのいずれかに基づいて、ページを昇順または降順に並べ替えます。

* 総来場者数
* 総閲覧数
* ページの平均時間
* 平均バウンス率
* 入口
* 離脱率

ページリストの順序付けのためのメトリクスは、期間メニュー（表の右上）で選択された期間に基づいて計算されます。 以下の期間が使用できます：

* 過去 24 時間
* 過去 7 日
* 直近30日（デフォルト
* 過去 90 日
* その他のプリセット期間（昨日、過去28日間、過去180日間、去年）
* 期間を指定

```{note}
ページデータは正規URLに基づいています。 [canonicalization](https://moz.com/learn/seo/canonicalization) や [DXPでカスタムcanonical URLを設定する方法](./../../dxp/latest/en/site-building/creating-pages/page-settings/configuring-individual-pages.md#seo) については、こちらをご覧ください。
```

単一ページの詳細なメトリックを表示するには、テーブル内のそのページをクリックします。 詳しくは、 [ページ解析](./touchpoints/pages/pages.md) を参照してください。

![訪問者の行動パネルでは、一定期間の詳細な統計が表示されます。](./touchpoints/images/03.png)

<a name="finding-asset-data" />

## アセットデータの検索

アセットの分析データを見るには

![アセットデータは、アセットタイプのタブで整理されています。](./touchpoints/images/04.png)

1. ［タッチポイント］セクションで［Assets］をクリックします。

1. アセットタイプを選択します。ブログ、ドキュメントとメディア、フォーム、ウェブコンテンツ、またはカスタム

このセクションでは、［プロジェクト］と［プロジェクト］の間の関係について説明します。 アセットとアセットではメトリクスが異なります。 詳細は各アセットのドキュメントをご覧ください。 さらに、期間メニュー（表の右上）から次の値のいずれかを選択することで、選択した期間にわたるメトリクスを計算することができます。

* 過去 24 時間
* 昨日
* 過去 7 日
* 過去 28 日
* 直近30日（デフォルト
* 過去 90 日

リスト内のアセットをクリックすると、より詳細な詳細が表示されます。

![アセット固有のメトリクスデータは、経時的に見る傾向を識別します。](./touchpoints/images/05.png)

アセットの種類ごとに別々のリストがあることを覚えておいてください。 各アセットタイプのメトリクスの詳細は、その後の記事で解説しています。

<a name="next-steps" />

## 次のステップ

- [サイトダッシュボード](./touchpoints/sites-dashboard.md)

<a name="pages" />

### ページ

- [ページ](./touchpoints/pages/pages.md)
- [パス](./touchpoints/pages/paths.md)

<a name="assets" />

### アセット

- [アセット](./touchpoints/assets/assets.md)
- [Webコンテンツ](./touchpoints/assets/web-content.md)
- [ブログ](./touchpoints/assets/blogs.md)
- [フォーム](./touchpoints/assets/forms.md)
- [ドキュメントとメディア](./touchpoints/assets/documents-and-media.md)
- [カスタムアセットのトラッキング](./touchpoints/assets/tracking-custom-assets.md)

<a name="events" />

### 予定

- [イベントの分析](./touchpoints/events/events-analysis.md)