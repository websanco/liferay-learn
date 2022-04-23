# オブジェクトの管理

オブジェクトポートレットは、システムオブジェクトとカスタムオブジェクトの両方を管理する強力な方法を提供します。 システムオブジェクトには、オブジェクトフレームワークと統合されたLiferayアプリケーションが含まれ、カスタムオブジェクトはオブジェクトポートレットを使用して作成されたアプリケーションです。 どちらのタイプのオブジェクトにも対応していますが、一部の管理オプションはカスタムオブジェクトでのみ利用可能です。

![オブジェクトポートレットを使用して、システムオブジェクトとカスタムオブジェクトの両方を表示および管理します。](./managing-objects/images/01.png)

```{note}
これらの管理操作を行うために必要なパーミッションについては、［Objects Application Permissions］(../objects-application-permissions.md)を参照してください。
```

## オブジェクトの編集と拡張

オブジェクトポートレットでは、 [フィールド](#fields)、 [関連](#relationships)、 [レイアウト](#layouts)を使用して、システムオブジェクトとカスタムオブジェクトの両方を編集、拡張することができます。

### 項目

![システムオブジェクトおよびカスタムオブジェクトからフィールドを追加、編集、および削除します。](./managing-objects/images/02.png)

システムやカスタムオブジェクトにフィールドを追加することができます。 公開後にシステムやカスタムオブジェクトに追加されたフィールドは、いつでも編集・削除することができます。 ただし、公開時にカスタムオブジェクトに含まれているフィールドは、最小限の編集しかできず、削除することもできません。また、ネイティブシステムオブジェクトのフィールドは、編集や削除が一切できません。 すべてのフィールドは、オブジェクトの［Fields］タブのフィールドで確認できます。 詳細については、[Adding Fields to Objects](./adding-fields-to-objects.md)を参照してください。

### 関連

![カスタムオブジェクトの関連を追加および構成します](./managing-objects/images/03.png)

カスタムオブジェクトの関連を追加・設定することができます。 作成後、ユーザーが編集できるのは関連のラベルと削除タイプのみです。 また、関連の削除はオブジェクトドラフトからのみ可能で、システムオブジェクトや公開されたオブジェクトからは削除できません。  関連のタイプに応じて、オブジェクトの関連は［Fields］または［関連］タブに表示されます。 詳細については、[Defining Object Relationships](./defining-object-relationships.md)を参照してください。 <!--TASK: Replace with following text once system Objects are supported, "You can add relationships to both system and custom Objects. After creation, users can only edit a relationship's Label and Deletion Type. Also, relationships can only be removed from an Object draft and cannot be removed from system and published Objects.  Depending on the relationship type, an Object's relationships are displayed in either the Fields or Relationships tab. See \[Defining Object Relationships\](./defining-object-relationships.md) for more information."--> ### レイアウト

![カスタムオブジェクトのレイアウトを追加、編集、および削除します。](./managing-objects/images/04.png)

カスタムオブジェクトのレイアウトは、いつでも追加、編集、削除することができます。 カスタムレイアウトをオブジェクトのデフォルトレイアウトとして設定するには、オブジェクトのすべての必須フィールドを含める必要があります。 詳細については、[Designing Object Layouts](./designing-object-layouts.md)を参照してください。 <!--TASK: Replace with following text once system Objects are supported, "You can add, edit, and remove layouts for both system and custom Objects at any time. Custom layouts must include all of an Object's required fields in order to be set as the Object's default layout. See \[Designing Object Layouts\](./designing-object-layouts.md) for more information."--> ## オブジェクトの有効化と無効化

デフォルトでは、カスタムオブジェクトは、最初に[公開](./creating-objects.md#publishing-object-drafts)された時に、 **有効化** されます。 これにより、アプリケーションが作成されるとすぐに使用できるようになります。 アクティブになっている間は、LiferayのUIやHeadless APIコールを介して、オブジェクトとそのエントリーにアクセスできます。 ただし、カスタムオブジェクトはいつでも無効にすることができます。

オブジェクトを無効にすると、そのオブジェクトは、パネルカテゴリー、統合されたフレームワーク、および関連するオブジェクトから削除されます。 非アクティブな状態のオブジェクトは、 [編集と拡張](#editing-and-extending-objects)のみ、オブジェクト ポートレットで行うことができます。 オブジェクトのエントリが無効化された時点でサイトや関連オブジェクトに表示されていた場合、それらのエントリは空白となり、関連のオブジェクトレイアウトにフィールドは表示されなくなります。

非アクティブなオブジェクトを有効化すると、そのオブジェクトとデータを使用できるようにすることができます。

```{important}
オブジェクトを無効にしても、そのデータには影響を与えず、オブジェクトが再び有効化されるまでユーザーがアクセスできなくなるだけです。
```

以下の手順で、カスタムオブジェクトを無効化または有効化します。

1. **オブジェクト** ポートレットから、目的の **オブジェクト** をクリックします。

   ```{note}
   システムオブジェクトを無効にすることはできません。
   ```

1. ［Details］タブで、トグルスイッチを ［**有効**］ または ［**Inactive**］ のいずれかに設定します。

    ![オブジェクトを有効または無効に切り替えます。 ](./managing-objects/images/05.png)

1. ［**保存**］ をクリックします。

## オブジェクトの削除

オブジェクトのドラフトを削除することができます。 ただし、一度公開されたオブジェクトは削除できません。 公開されたオブジェクトは、[有効化または無効化](#activating-and-deactivating-objects)のみ可能です。

オブジェクトドラフトを削除するにいは、 **アクション** ボタン（![Actions Button](../../../images/icon-actions.png))をクリックし、 ［**削除**］ を選択します。

![オブジェクトドラフトのアクションボタンをクリックし、［削除］を選択します。](./managing-objects/images/06.png)

## 追加情報

* [Objects Overview](../../objects.md)
* [Creating Objects](./creating-objects.md) <!--TASK: Add once article is finished * \[Objects UI Reference\](../objects-ui-reference.md) -->
