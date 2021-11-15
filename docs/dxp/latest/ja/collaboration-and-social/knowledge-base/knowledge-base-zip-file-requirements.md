# ナレッジベースのZIPファイルの要件

ナレッジベースインポーターは記事階層をサポートしているため、MarkdownファイルはZIPファイルのディレクトリ構造のどこにでも指定できます。 ファイルは、任意の数のフォルダにネストできます。 添付ファイルでサポートされているファイルは画像ファイルのみです。

```{note}
インポートされた記事は、ワークフロー設定に依存しません。 これは、インポートされた記事が自動的に承認されることを意味します。
```

ロールに割り当てられた記事をインポートする権限を持つユーザーのみが記事をインポートできます。 この権限は、[コントロールパネル] → [ユーザー] → [ロール]から手動で割り当てることができます。

ZIPファイルの記事はファイル順に（アルファベット順に）インポートされます。 記事の優先度を指定するには、ファイル名に数字のプリフィックスを追加します。 例えば、`01-file.markdown`と`02-file.markdown`という名前の記事の優先順位は、 `1.0`と`2.0`になります。

同じソースフォルダ内の他のすべての記事の親となる記事を指定するには、そのファイル名の最後に`-intro.markdown`を付けます。 これにより、親子の階層が形成されます。 親記事に`00`というプリフィックスをつけることで、フォルダのファイル順の一番上に配置することができます。 インポーターは、イントロファイルのフォルダの数値プリフィックスを記事の優先順位として使用します。

ここでは、 `00`というプリフィックスの基本的な論理を説明します。

  - 非イントロファイルのファイルプリフィックスが`00`の場合、結果として得られる記事の優先度は`1.0`なります。
  - トップレベルのイントロファイルのファイルプリフィックスが`00`の場合、記事の優先順位は、最初に見つかった`1.0`以上のフォルダの数字プリフィックスに設定されます。

この規則により、階層内の最上位（子以外）記事の優先順位を指定できます。

インポート時には、チェックボックス*[Apply numerical prefixes of article files as priorities]* を選択したままにしておきます。 ファイルにプリフィックスがない場合、その記事の優先度は、次に設定可能な優先度（現在の最高の優先度に1を加えたもの）になります。

以下は、これまでに説明した機能を示すZIPファイル構造の例です。

ZIPファイル構造の例：

  - `01-winter-olympics/`
      - `00-winter-events-intro.markdown`
      - `01-speed-skating.markdown`
      - `02-bobsleigh.markdown`
  - `02-summer-olympics/`
      - `00-summer-events-intro.markdown`
      - `01-swimming.markdown`
      - `02-gymnastics.markdown`
      - `03-track-and-field/`
          - `00-track-and-field-intro.markdown`
          - `01-marathon.markdown`
  - `images/`
      - `some-image.png`
      - `another-image.jpeg`

上記のZIPファイルでは、隣接するMarkdownファイル`01-speed-skating.markdown`および`02-bobsleigh.markdown`の親として`00-winter-events-intro.markdown`が指定されています。 同様に、`00-track-and-field-intro.markdown`は`01-marathon.markdown`の親です。 `00-track-and-field-intro.markdown`は、`01-swimming.markdown`と`02-gymnastics.markdown`のピアでもあり、`00-summer-events-intro.markdown`の子でもあります。

ZIP例の結果得られる関係と優先順位は次のとおりです。

  - `01-winter-olympics/00-winter-events-intro.markdown`
      - 記事：冬のイベント
      - 関係：夏のイベントのピア
      - 優先度：1.0
  - `01-winter-olympics/01-speed-skating.markdown`
      - 記事：スピードスケート
      - 関係：冬のイベントの子
      - 優先度：1.0
  - `01-winter-olympics/02-bobsleigh.markdown`
      - 記事：ボブスレー
      - 関係：冬のイベントの子
      - 優先度：2.0
  - `02-summer-events/00-summer-events-intro.markdown`
      - 記事：夏のイベント
      - 関係：夏のイベントのピア
      - 優先度：2.0
  - `02-summer-events/01-swimming.markdown`
      - 記事：水泳
      - 関係：夏のイベントの子
      - 優先度：1.0
  - `02-summer-events/02-gymnastics.markdown`
      - 記事：体操
      - 関係：夏のイベントの子
      - 優先度：2.0
  - `02-summer-events/03-summer-olympics/00-track-and-field-intro.markdown`
      - 記事：陸上競技
      - 関係：夏のイベントの子
      - 優先度：3.0
  - `02-summer-events/03-summer-olympics/01-marathon.markdown`
      - 記事：マラソン
      - 関係：夏のイベントの孫
      - 関係：陸上競技の子
      - 優先度：1.0

ZIPファイルは次の要件を満たしている必要があります。

  - 各ZIPファイルの末尾には、`.zip`が必要です。
  - 各ZIPファイルには、オプションでフォルダに編成された、少なくとも1つのMarkdownソースファイルが含まれている必要があります。
  - 参照するすべての画像ファイルは、ZIPファイルのルートにある`images`という名前のフォルダに含まれている必要があります。
  - 画像ファイルはサポートされている形式である必要があり、適切なファイル拡張子を使用する必要があります。 サポートされている拡張子は、`.bmp`、`.gif`、`.jpeg`、`.jpg`、および`.png`です。 これらは、アプリのシステム設定を介して指定されます。 詳細は、[ナレッジベースシステム設定](knowledge-base-system-settings.md)を参照してください。

記事のZIPファイルを入手したら、それをインポートします。 ZIPファイルをインポートするには、

1.  メニューアイコン（![Menu icon](../../images/icon-menu.png)）をクリックし、[コンテンツとデータ] → [ナレッジベース] → [記事]に移動します。

2.  追加アイコン（![Add icon](../../images/icon-add.png)）をクリックし、*[インポート]* をクリックします。

    ![この新しいページにZIPファイルをアップロードします。](./knowledge-base-zip-file-requirements/images/01.png)

3.  *[Choose File]* をクリックし、インポートするZIPファイルを探します。 次に、* [保存]* をクリックします。

ファイルがアップロードされ、インポーターが各ソースファイルのMarkdownテキストをHTMLに変換し、出来上がった記事にHTMLを適用します。 記事で参照され、ZIPファイルに含まれている画像ファイルはすべて、記事の添付ファイルとしてインポートされます。

ソースファイルと画像に加えて、ソースファイルのリポジトリの場所を指定するインポーターのベースソースURLのシステム設定を構成できます。 各記事の*[GitHub上で編集]* ボタン（有効な場合）を押すと、ソースの場所に移動します。 インポーターは、各ファイルのパスの前にベースソースURLを付けます。 これにより、記事のリポジトリソースの場所へのURLが作成されます。`[base URL]/[article file path]`のようになります。 ベースソースURLの例を次に示します。

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials

このベースURLと記事のソースファイル`folder-1/some-article.markdown`から構築されるソースURLは次のようになります。

    https://github.com/liferay/liferay-docs/blob/master/develop/tutorials/folder-1/some-article.markdown

ベースソースURLは、ZIPファイルのルートフォルダにある`.METADATA`というファイルで指定します。 インポーターは、`.METADATA`ファイルを標準のJavaプロパティファイルとして扱い、ベースソースURLを使用して、ZIPファイルから生成されるすべての記事のソースURLを作成します。

ソースURL機能を使用するには、管理者が[Knowledge Base System Settings](knowledge-base-system-settings.md)を介してその機能を有効にする必要があります。
