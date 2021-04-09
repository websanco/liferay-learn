# ユーザーセグメントの作成と管理

セグメントは、セグメントの評価に*[Job Title]* フィールドや組織メンバーシップなどのユーザーデータを使用します。 次の手順では、新しいユーザーセグメントを作成する方法について説明します。

1.  製品メニューを開き、サイトのメニューから*[People]* → *[Segments]* に移動します。

    ![[People ]メニューからユーザーセグメントを追加します。](./creating-and-managing-user-segments/images/01.png)

2.  *追加*ボタン（![Add](../../../images/icon-add.png)）をクリックします。

3.  上部のテキスト領域をクリックして、ユーザーセグメントの名前を入力します。

4.  必要な*[User]*、*[Organization]*、*[Session]* プロパティを[Conditions]ボックスにドラッグし、フィールドを構成してユーザーセグメントの条件を作成します。 エディターの使用の詳細と使用できるすべてのプロパティの説明については、[The Segments Editor UI Reference](./segments-editor-ui-reference.md)をご覧ください。 カスタムフィールドを作成することにより、デフォルトのリストにプロパティを追加できます <!-- link todo --> ユーザーまたは組織向け。

    ![インターフェイスを介して組織を直接選択することにより、タイプミスを防ぐことができます。](./creating-and-managing-user-segments/images/02.png)

    次の図の例では、役職が「Engineer」であるユーザーのユーザーセグメントを作成します。

    ![比較演算子を含むように設定すると、セグメントにソフトウェアエンジニアのようなエンジニアのバリエーションが含まれます。](./creating-and-managing-user-segments/images/03.png)

    編集すると、基準を満たすメンバーの数がページの上部に表示されます。 *[View Members]* をクリックしてリストを表示できます。 これは、セグメントを正しく定義しているかどうかを判断するのに役立ちます。

    ![セグメントメンバーのリストはいつでも表示できます。](./creating-and-managing-user-segments/images/04.png)

5.  *[保存]* をクリックしてユーザーセグメントを保存します。

6.  ユーザーセグメントを作成すると、*[Segments]* ページのユーザーセグメントのリストに表示されます。 ここから、アクションメニュー（![Actions](../../../images/icon-actions.png)）を使用して、ユーザーセグメントを管理（編集、削除、 [サイトロール](../../../users-and-permissions/roles-and-permissions/assigning-roles-to-user-segments.md) 割り当て、またはユーザーセグメントへのアクセス権を持つユーザーの変更）を行うことができます。 ユーザーセグメントの名前をクリックして編集することもできます。

    ![アクションメニューから権限を編集、削除、または管理できます。](./creating-and-managing-user-segments/images/05.png)

``` note::
  エクスペリエンスで使用されているユーザーセグメントは削除できません。
```

## 関連情報

  - [Assigning Roles to User Segments](../../../users-and-permissions/roles-and-permissions/assigning-roles-to-user-segments.md)
  - [Getting Analytics for User Segments](./getting-analytics-for-user-segments.md)
  - [コンテンツページのパーソナライゼーション](../experience-personalization/content-page-personalization.md)
