# セッションプロパティのボキャブラリ

*セッションプロパティのボキャブラリ*では、定義済みの値を使用して[セッションベースのセグメントプロパティ](../../site-building/personalizing-site-experience/segmentation/segments-editor-ui-reference.md#session-properties)を作成できます。 このオプションは、新しいセグメントの作成中にセグメントエディタでプロパティの値を手動で入力したくない場合に役立ちます。代わりに、ユーザーが事前定義された値のリストからそれらを選択できるようにします。 セッションプロパティのボキャブラリは、セグメントを定義するタスクを容易にし、手動入力エラーを排除します。

たとえば、マーケティングチームは、デバイスのブランド情報を使用して、Appleデバイス用の新しいアプリのプロモーションなど、さまざまなコンテンツをWebサイトの訪問者にターゲティングしたい場合があります。 マーケティングチームのプロセスを容易にするために、さまざまなデバイスのブランドを含むセッションプロパティのボキャブラリを作成し、このボキャブラリを[ユーザーセグメント](../../site-building/personalizing-site-experience/segmentation/creating-and-managing-user-segments.md)にリンクすることができます。 このように、マーケティングチームが「デバイスブランド」[セッションプロパティ](../../site-building/personalizing-site-experience/segmentation/segments-editor-ui-reference.md#session-properties)を使用してセグメントを作成する場合、異なるデバイスブランドオプションを入力する必要はありません。 可能なオプションのリストが事前定義されました。

![デバイスのブランドセッションのプロパティには、事前定義された値のリストが含まれています。](./session-property-vocabularies/images/07.png)

セッションプロパティのボキャブラリは、次の3つのステップで操作します。

1.  [セッションベースのセグメントのボキャブラリを設定します](#configure-the-vocabulary-for-the-session-based-segment)。
2.  [セッションプロパティのボキャブラリを定義します](#defining-the-session-property-vocabulary)。
3.  [セッションベースのセグメントを作成します](#creating-the-session-based-segment)。

## セッションベースセグメントのボキャブラリの設定

このボキャブラリで定義するカテゴリ（1）は、[セッションプロパティ](../../site-building/personalizing-site-experience/segmentation/segments-editor-ui-reference.md#session-properties)を使用してセグメントを作成するときに選択できるカテゴリ（2）です。

![ユーザーセグメントに関連付けるグローバルボキャブラリのカテゴリのリストは次のとおりです。](./session-property-vocabularies/images/01.png)

![セッションベースのセグメントで使用可能なオプションは、グローバルボキャブラリのカテゴリに対応しています。](./session-property-vocabularies/images/02.png)

新しいグローバルボキャブラリを作成することも、既存のボキャブラリを使用することもできます。 新しいボキャブラリを作成したり、既存のボキャブラリのカテゴリを更新したりするには、[コンテンツのカテゴリとボキャブラリの定義](./defining-categories-and-vocabularies-for-content.md)を参照してください。 [公開または内部の可視性](./organizing-content-with-categories-and-tags.md#vocabulary-visibility)を持つボキャブラリを使用できます。 セッションプロパティのボキャブラリは、グローバルボキャブラリでのみ機能します。

```{note}
グローバルサイトでは、グローバルボキャブラリのみを作成および編集できます。
```

## セッションプロパティのボキャブラリの定義

[セッションベースのセグメントのボキャブラリを設定](#configuring-the-vocabulary-for-the-session-based-segment)した後、そのボキャブラリを新しいセッションプロパティのボキャブラリで使用可能なセッションプロパティの1つにリンクする必要があります。

![セグメントのセッションプロパティと連携するようにボキャブラリを設定します。](./session-property-vocabularies/images/03.png)

1.  グローバルメニューをクリックし、*[コントロールパネル]* タブをクリックします。

2.  [設定]セクションで、*[システム設定]* をクリックします。

3.  [コンテンツとデータ]セクションで、*[セグメント]* をクリックします。

4.  *[セッションプロパティのボキャブラリ]* をクリックします。

5.  *[Add]* をクリックして、新しいセッションプロパティのボキャブラリを作成します。

6.  [Selection Property]ドロップダウンメニューでプロパティを選択します。

    ![グローバルボキャブラリに関連付けるプロパティを選択します。](./session-property-vocabularies/images/06.png)

    ```{note}
    別のセッションプロパティのボキャブラリに属するプロパティを選択することはできません。 これらのプロパティはグレー表示されており、使用できません。
    ```

7.  [ボキャブラリ名]ドロップダウンメニューで*[ボキャブラリ]* を選択します。

8.  *[保存]* をクリックします。

![セッションプロパティのボキャブラリを作成して保存します。](./session-property-vocabularies/images/05.gif)

## セッションベースのセグメントの作成

[ボキャブラリを設定](#configuring-the-vocabulary-for-the-session-based-segment)し、[セッションプロパティのボキャブラリを定義](#defining-the-session-property-vocabulary)した後、セグメントを作成できます。 セグメントの作成については、[ユーザーセグメントの作成と管理](../../site-building/personalizing-site-experience/segmentation/creating-and-managing-user-segments.md#creating-user-segments)を参照してください。

セグメントの条件を定義し、グローバルボキャブラリにリンクされたセッションプロパティの1つを使用すると、セッションプロパティの条件が事前定義され、ドロップダウンメニューから選択できるようになりました。 このドロップダウンメニューは、セッションプロパティの[ボキャブラリで定義した](#defining-the-session-property-vocabulary)ボキャブラリのカテゴリのリストに対応しています。

```{important}
セッションプロパティのボキャブラリ内のカテゴリのいずれか、またはユーザーセグメントにリンクされているボキャブラリが削除されても、セグメントは定義に基づいて動作します。 Liferayは、セグメントエディタでこの状況を警告します。
```

![セッションプロパティのボキャブラリ内のカテゴリの1つまたはそのボキャブラリが欠落している場合、警告メッセージが表示されます。](./session-property-vocabularies/images/08.png)

## 関連情報

  - [コンテンツのカテゴリとボキャブラリの定義](./defining-categories-and-vocabularies-for-content.md)
  - [ユーザーセグメントの作成と管理](../../site-building/personalizing-site-experience/segmentation/creating-and-managing-user-segments.md#creating-user-segments)
  - [Segments Editor UI Reference](../../site-building/personalizing-site-experience/segmentation/segments-editor-ui-reference.md)
