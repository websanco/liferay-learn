# フラグメントの開発

ページ フラグメントは、[コンテンツ ページ](../../creating-pages/understanding-pages/understanding-pages.md#content-pages)の構成要素です。 これらは、Web ページの 3 つのコンポーネント (CSS、HTML、JavaScript) で構成されています。 ページを作成するには、複数のフラグメントを組み合わせ、それらを組み合わせてページのデザインとその機能を作成します。 フラグメントは、より大きなページ要素 (カードや段落要素など) を構成することも、単独でスタンドアロン (バナーなど) にすることもできます。

![フラグメントをパズルのピースのように組み合わせて、コンテンツ ページを構築します。](./developing-fragments-intro/images/01.png)

## コレクション

コレクションはフラグメントを編成して、関連するフラグメントのグループを管理および共有できるようにします。 ユーザーはページに追加するフラグメントを選択するときにコレクションをナビゲートするため、ページに結合しやすいフラグメントをコレクションに作成します。 管理ページに一例が表示されており、すぐに使用できるすべてのフラグメント (およびそのコード) が表示されています。 ページ フラグメントのコードは、管理インターフェイスから更新できます。 ページ フラグメントのコードを静的 (ロックされており、インターフェイスから編集できない) にしたい場合は、[提供されたフラグメントコレクション](./creating-a-contributed-fragment-collection.md)を作成できます。

## ツーリング

ブラウザベースのツールはシステム上で直接フラグメントを作成するのに役立ち、CLI ベースのツールは独自のツールセットと統合して生産性を維持します。

  - [組み込みのフラグメント エディター](./using-the-fragments-editor.md): 組み込みのエディターを使用して、Liferay Portal 内のページ フラグメントを作成します。
  - [フラグメント ツールキット](./using-the-fragments-toolkit.md): フラグメント CLI を使用して、デスクトップからページ フラグメントおよびフラグメント コレクションを生成、インポート、およびエクスポートします。

## 機能一覧

フラグメントは、以下のリソースを使用して拡張でき、プレーンな HTML、CSS、JavaScript よりもはるかに強力なものになります。

  - [編集可能な要素](../reference/fragments/fragment-specific-tags-reference.md): テキスト、画像、リンク、および「リッチ」テキスト要素を編集可能にして、ユーザーがコンテンツをカスタムのテキストと画像に置き換えることができるようにします。 これは、フラグメントを再利用可能にするフラグメントの主要な機能です。 ウィジェットを埋め込むこともできます。
  - [ドロップ ゾーン](./defining-fragment-drop-zones.md) `<lfr-drop-zone></lfr-drop-zone>` ラベルを使用してフラグメントにドロップ ゾーンを追加します。 追加したら、フラグメントとウィジェットを定義した領域にドラッグ アンド ドロップできます。
  - [設定オプション](./adding-configuration-options-to-fragments.md) <!-- TODO: Fix link --> : フラグメントのフォントの色を変更するなど、アプリケーションの設定メニューに設定オプションを追加します。
  - [FreeMarker](https://freemarker.apache.org/): FreeMarker は、変数、条件文などで HTML を拡張するテンプレート言語です。 HTML から、FreeMarker に[代替 (角括弧) 構文](https://freemarker.apache.org/docs/dgui_misc_alternativesyntax.html)を使用できます。

<!-- end list -->

```{important}
FreeMarkerでテンプレートを作成または編集する権限は信頼できるユーザーにのみ付与してください。 Webコンテンツテンプレートのセキュリティについては、 [Assigning Permissions to Web Content Structures and Templates](../../../content-authoring-and-management/web-content/web-content-structures/assigning-permissions-to-structures-and-templates.md#security-considerations-for-web-content-templates) を参照してください。
```

## ベストプラクティス

フラグメント コードを書き込む際は、次のベスト プラクティスをお勧めします。

  - コードをセマンティックで再利用性の高いものにしてください。
  - フラグメントの外部にあるページの他の要素に干渉しないように、フラグメントに適切なネームスペースを設定してください。
  - 自動生成されたフラグメント クラスを、追加するすべての CSS セレクターの基礎として使用して、他のフラグメントに影響を与えないようにしてください。
  - JavaScript は簡単に再利用できないため、慎重に使用してください。 代わりに、外部の JavaScript ライブラリを参照してください。
  - [フラグメント設定テキスト値をエスケープしてください](./escaping-fragment-configuration-text-values-reference.md) <!-- TODO: Fix link -->

## 追加情報

  - [Adding Configuration Options to Fragments](./adding-configuration-options-to-fragments.md)
  - [フラグメントエディターの使用](./using-the-fragments-editor.md)
  - [フラグメントツールキットの使用](./using-the-fragments-toolkit.md)
