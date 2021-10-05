# ナレッジベース記事のインポート

ナレッジベースアプリは、記事をまとめてインポートできます。 これにより、記事を公開する前に記事を事前に準備できるようになります。 記事は[Markdown](http://commonmark.org/)ファイルとしてナレッジベースに取り込まれます。 Markdownは、読みやすいテキストのみのファイル形式ですが、記事をフォーマットするために必要なすべての機能をサポートしています。

注：記事をインポートするには、ロールに*ナレッジベース* → *[リソース権限: 記事をインポートする]* へのアクセス許可が付与されている必要があります。

ナレッジベースでは、[Multi-Markdown](http://fletcher.github.io/MultiMarkdown-4/)として知られるMarkdown言語をサポートしています。 この言語は、オリジナルのMarkdownに、テーブルの書式設定、画像キャプション、脚注などの機能を追加したものです。

ナレッジベースがMarkdown記事をインポートするには、次の要件に準拠する必要があります。

  - すべてのソースファイルは、`.markdown`または`.md`拡張子を使用する必要があります。
  - 記事は、トップレベルのヘッダー（例：`# Some Heading ...`）で始まる必要があります。
  - 各ヘッダーには、記事のフレンドリURLタイトルと記事のサブヘッダーのアンカータグに関連付けられた一意のIDが必要です。 IDを正しく指定したトップレベルヘッダーの例を次に示します。

`# Some Heading [](id=some-heading)`

以下は、簡単なサンプル記事のMarkdownソーステキストです。

    # Modern Pentathlon [](id=modern-pentathlon)
    
    The modern pentathlon is a competition across five different sport disciplines.
    
    Each athlete must compete in fencing, shooting, swimming, horseback riding, and running.

上の最初の行で、ヘッダーのID割り当て`id=modern-pentathlon`に注目してください。 インポート時に、ID値はナレッジベース記事のURLタイトルになります。

Markdownはフレーバーを備えた規格です。[Github Flavored Markdown](https://help.github.com/articles/github-flavored-markdown)、提案されている[一般的なMarkdown構文](http://www.commonmark.org/)、Markdownをサポートするフォーラム（reddit、StackExchangeなど）、Markdownエディター、および公式のインターネットメディアタイプ（テキスト/マークダウン）にするための[IETFドラフト](https://tools.ietf.org/html/rfc7763)があります。 ）。 Markdownが好まれる理由は以下のとおりです。

  - 読みやすい。 Markdownを知らなくても、構文を除外せずに読むことができます。

  - 書き手の邪魔にならない。 テキストを見出しに変更したり、箇条書きを作成したりするために、さまざまなアイコンにマウスを合わせる必要はありません。 入力を開始するだけです。 構文は非常に直感的です。

  - HTMLに変換するように設計されていますが、他の多くの形式に変換するためのツールがあります。 記事がMarkdownで書かれていれば、それらをWeb、モバイル形式（Kindle、ePub）に公開し、印刷することができます。

  - テキストのみであるため、既存のツールを使用してそのテキストで共同作業を行うことができます。 GitHubなどのサービスを使用すると、ユーザーがあなたの記事に寄稿でき、記事に加えられたすべての変更を確認できます。
