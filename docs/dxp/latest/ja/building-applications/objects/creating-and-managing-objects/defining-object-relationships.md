# オブジェクトリレーションの定義

関連は、エンティティをリンクするオブジェクト間の接続です。 公開済みまたは未公開のカスタムオブジェクトに関連を追加できます。 <!--TASK: Include system Objects once supported; "You can add relationships to any published or unpublished Object, including both system and custom Objects."-->

## 関連のタイプ

オブジェクトには、次の2種類の関連があります。

<!--TASK: Add One to One after it's been implemented-->

**One to Many（1対多**） ：現在のオブジェクトのエンティティの1つを別のオブジェクトの複数のエンティティに関連付けることができます。 選択すると、関連の子側（つまり、「多」側）のエンティティに新しいフィールドが追加され、「親」側（つまり、「1」側）にテーブルが追加されます。 子側では、新しいフィールドをオブジェクトのカスタムレイアウトの[フィールドタブ](./designing-object-layouts.md#adding-fields-tabs)に追加し、そのエンティティを関連の親側の単一エンティティに関連付けることができます。 親側では、新しいテーブルをオブジェクトのカスタムレイアウトの[関連タブ](./designing-object-layouts.md#adding-relationships-tabs)に追加して、現在の親エンティティに関連するすべてのエンティティを一覧表示できます。 <!--REFINE-->

**Many to Many（多対多**） ：現在のオブジェクトの複数のエンティティを別のオブジェクトの複数のエンティティに関連付けることができます。 選択すると、関連の両側に新しいデータベーステーブルが作成されます。 このテーブルは、いずれかのオブジェクトのカスタムレイアウトの[関連タブ](./designing-object-layouts.md#adding-relationships-tabs)に追加でき、関連するすべてのエンティティを一覧表示するために使用されます。 <!--REFINE-->

```{important}
オブジェクトエントリの関連を表示するには、カスタムレイアウトを作成する必要があります。 詳しくは、[Designing Object Layouts](./designing-object-layouts.md)を参照してください。 
```

## 新しい関連の追加

次の手順に従って、オブジェクトに関連を追加します。

1. **オブジェクト** ポートレットを開きます。

1. 目的のオブジェクトを選択します。

1. ［**関連**］ タブをクリックし、 **追加** ボタン（![Add Button](../../images/icon-add.png)）を選択します。

   ![［関連］タブの追加ボタンをクリックし、ラベルと名前を入力して、関連タイプと目的のオブジェクトを選択します。](./defining-object-relationships/images/01.png)

1. **ラベル** と **関連名** を入力します。

   **ラベル** ：オブジェクトUIで関連を識別し、関連の作成後にローカライズできます。

   **関連名** ：バックエンドで関連の名前を決定し、キャメルケースを使用します。 関連が公開されると、この値は変更できません。

1. 関連タイプ（［**One to Many**］ または ［**Many to Many**］）を選択します。 <!--TASK: Add One to One after it's been implemented-->   ```{note}
   同じオブジェクト内のエントリーを関連付ける場合は、［One to Many］タイプを使用する必要があります。 <!--タスク：実装後に1対1で追加-->
   ```

1. 現在のオブジェクトに関連付ける*オブジェクト*を選択します。

1. *［保存］* をクリックします。

   ![［保存］をクリックすると、保存された関連が［関連］タブに一覧表示されます。](./defining-object-relationships/images/02.png)

保存すると、新しい関連を関連するオブジェクトの[カスタムレイアウトに追加](./designing-object-layouts.md)できます。 次に、関連フィールドとテーブルを使用して、オブジェクトエントリーを相互に関連付けることができます。

```{important}
オブジェクトはドラフトですが、親側から関連を編集および削除できます。 ただし、オブジェクトが公開されると、既存の関連または新しく追加された関連を削除することはできません。 ユーザーは、関連のラベルと[削除タイプ](#configuring-deletion-type)のみを構成できます。
```

## 削除タイプの構成

関連が保存されたら、その削除タイプを構成できます。 この設定は、エントリーが別のエントリーに関連付けられている場合のエントリーの削除の処理方法を決定し、関連が作成された後にのみ使用できます。

![関連を作成した後、その削除タイプを構成できます。](./defining-object-relationships/images/03.png)

使用可能なオプションには、*［Prevent］*、*［Disassociate］*、および*［Cascade］*があります。

**Prevent** （デフォルト）：親側のエントリは、子エントリに関連している場合は削除できません。

**Disassociate**：親側のエントリは、関連する子エントリに影響を与えることなく自由に削除できます。

**Cascade**：親側のエントリは自由に削除できます。親エントリを削除すると、ユーザーが子オブジェクトに必要な権限を持っている場合、関連する子エントリもすべて削除されます。

## 追加情報

* [Creating Objects](./creating-objects.md)
* [Adding Fields to Objects](./adding-fields-to-objects.md)
* [Designing Object Layouts](./designing-object-layouts.md)
* [Managing Objects](./managing-objects.md)
