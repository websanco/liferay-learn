# パブリケーションの作成と管理

パブリケーションツールを使用すると、あなたとあなたのチームは、同時に複数のパブリケーションを作成および管理したり、複数のサイトにわたる変更を1つのパブリケーションにグループ化したりできます。 各パブリケーションはインスタンスに範囲指定されており、複数のサイトにわたるページ、Webコンテンツ、およびドキュメントへの変更を含めることができます。

それらはインスタンスに範囲指定されているため、同じパブリケーションが表示され、インスタンス内のどこからでもアクセスできます。 ただし、これらのパブリケーション内で行われた変更は、変更が行われたコンテキストに応じて範囲が設定されます。 たとえば、サイトに範囲指定されている変更は、変更が行われたサイトにのみ適用されますが、インスタンスに範囲指定されている変更は、公開時にすべてのサイトに適用されます。

パブリケーションを有効にすると、*[Publications]* バーのドロップダウンメニューと*[Publications]* 概要ページからパブリケーションを作成、編集、公開、または削除できます。

このページにアクセスするには、*グローバルメニュー*（![Global Menu](../../../images/icon-applications-menu.png)）を開き、[Applications]タブの*[Publications]* に移動します。

```{tip}
Quickly access the Global Menu anywhere in your DXP instance using the following shortcut:

**Mac OS**: Cmd+Shift+M

**Windows/Linux**: Ctrl+Shift+M
```

![グローバルメニューの[Applications]タブにある[Publications]概要ページにアクセスします。](./creating-and-managing-publications/images/01.png)

  - [新しいパブリケーションの作成](#creating-a-new-publication)
  - [進行中のパブリケーションの管理](#managing-ongoing-publications)
  - [スケジュール設定されたパブリケーションの管理](#managing-scheduled-publications)
  - [パブリケーションの履歴の管理](#managing-history-of-publications)

## 新しいパブリケーションの作成

次の手順に従って、インスタンスに新しいパブリケーションを作成します。

1.  ドロップダウンの[Publications]バーメニューで*[新規公開を作成]* をクリックします。 *[新規公開を作成]* ページにリダイレクトされます。

    ![ドロップダウンの[Publications]バーメニューで[新規公開を作成]をクリックします。](./creating-and-managing-publications/images/02.png)

    または、[Publications]概要ページの*[進行中]* タブで新規公開を作成することもできます。 *追加*ボタン（![Add button](../../../images/icon-add.png)）をクリックします。

2.  新しいパブリケーションの*名前*を入力します。

    ```{tip}
    Unique publication names aren't required, though using unique names can be helpful for organization.
    ```

3.  必要に応じて、新しいパブリケーションの*説明*を入力します。

4.  *[Create]* をクリックします。

![パブリケーションの名前と説明を入力し、[Create]をクリックします。](./creating-and-managing-publications/images/03.png)

この方法で作成されたパブリケーションは、変更を加えることなく、現在の本番環境をベースラインとして使用します。 パブリケーションで行われたすべての変更は、作成時の本番環境からの逸脱として追跡されます。

新しいパブリケーションを作成した後、変更の追加を開始できます。 編集と公開のプロセスの詳細は、[変更の作成と公開](./making-and-publishing-changes.md)を参照してください。

## 進行中のパブリケーションの管理

*[進行中]* タブには、DXPインスタンスのすべてのアクティブな未公開のパブリケーションが一覧表示されます。 デフォルトでは、これらのパブリケーションは*編集日時*でソートされていますが、*名前*でソートすることもできます。 各エントリには、パブリケーションの名前、説明、最終変更時刻、作成時刻、および所有者が含まれます。

![[進行中]タブには、DXPインスタンスのすべてのアクティブな未公開のパブリケーションとそれらの情報が一覧表示されます。](./creating-and-managing-publications/images/04.png)

パブリケーションの*アクション*ボタン（ ![Actions button](../../../images/icon-actions.png) ）をクリックすると、パブリケーションを選択して、以下にリストされているタスクのいずれかを実行できます。

**公開に着手：**パブリケーションの機能を有効にします。 作業するパブリケーションを選択すると、ドロップダウンの[Publications]バーメニューを使用して、本番環境と選択したパブリケーションを簡単に切り替えることができます。

**編集：** パブリケーションの作成後はいつでも、パブリケーションの名前または説明を変更できます。

**変更の確認：** 選択したパブリケーションに含まれるすべての変更を表示します。 デフォルトでは、DXPはパブリケーションの追跡された変更の簡単な要約を表示します。 *[すべての項目を表示]* のトグルスイッチを使用して、変更に関連するすべてのプロセスを表示します。 リストとコンテキストの表示形式を切り替えることもできます。 詳細は、[パブリケーションの変更の確認](./making-and-publishing-changes.md#reviewing-publication-changes)を参照してください。

**公開：** パブリケーションの変更をすぐに公開します。 選択すると、DXPは競合を検出し、必要に応じて通知します。 競合がない場合は、*[公開]* をクリックして変更を公開します。 このプロセスの詳細は、[変更の作成と公開](./making-and-publishing-changes.md)および[競合の解決](./resolving-conflicts.md)を参照してください。

**スケジュール：** パブリケーションの変更が本番環境に適用される将来の日時を選択します。 選択すると、DXPは競合を検出し、必要に応じて通知します。 競合がない場合は、*[次へ]* をクリックし、UTCを使用して*日時*を設定して変更を公開します。 完了したら、* [スケジュール]* をクリックします。 このプロセスの詳細は、[変更の作成と公開](./making-and-publishing-changes.md)および[競合の解決](./resolving-conflicts.md)を参照してください。

**権限設定：** インスタンスのユーザーロールにパブリケーション固有の権限を割り当てます。 このように設定された権限は、個々のパブリケーションに範囲指定されます。 デフォルトでは、ほとんどのユーザーはパブリケーションを作成したりパブリケーションにアクセスしたりできませんが、ユーザーロールを手動で設定して、より広いアクセス権を付与することができます。

**削除：** インスタンスからパブリケーションを削除します。 選択すると、選択項目を確認するように求められます。

```{important}
Deleting a publication permanently removes all of its changes from your database and cannot be undone. Before deleting a publication, ensure you've saved any data you want to preserve.
```

## スケジュール設定されたパブリケーションの管理

*[スケジュール]* タブには、インスタンスでスケジュールされているすべてのパブリケーションが一覧表示されます。 デフォルトでは、これらのパブリケーションは名前でソートされていますが、編集日時または各パブリケーションの公開がスケジュールされている日時でソートすることもできます。

![[スケジュール]タブには、インスタンスでスケジュールされているすべてのパブリケーションが一覧表示されます。](./creating-and-managing-publications/images/05.png)

スケジュール設定されたパブリケーションの*アクション*ボタン（![Actions button](../../../images/icon-actions.png)）をクリックすると、そのパブリケーションの*スケジュール解除*、*再スケジュール*、および*変更の確認*ができます。 パブリケーションのスケジュールが解除されると、再び編集可能になり、[進行中]タブに一覧表示されます。

## パブリケーションの履歴の管理

*[履歴]* タブには、インスタンスで以前に公開されたすべてのパブリケーションが一覧表示されます。 デフォルトでは、これらのパブリケーションは*公開日*でソートされていますが、*名前*でソートすることもできます。

![[履歴]タブには、インスタンスで以前に公開されたすべてのパブリケーションが一覧表示されます。](./creating-and-managing-publications/images/06.png)

*[元に戻す]* をクリックすると、以前に公開された変更をインスタンスに元に戻すパブリケーションを簡単に作成できます。 [元に戻す]パブリケーションの一部として、DXPインスタンスに追加の変更を加えることもできます。 このプロセスの詳細は、[変更を元に戻す](./reverting-changes.md)を参照してください。

```{tip}
Its recommended Users enable Publications early on in your development process to achieve a more complete audit of your instance's changes.
```

## 追加情報

  - [Publications Overview](../publications.md)
  - [Enabling Publications](./enabling-publications.md)
  - [変更の作成と公開](./making-and-publishing-changes.md)
