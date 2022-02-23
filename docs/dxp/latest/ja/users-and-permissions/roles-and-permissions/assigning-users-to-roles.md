# ロールへのユーザーの割り当て

ロールの割り当てUIの場所は、 [ロールのスコープ](./understanding-roles-and-permissions.md#roles-and-scope) 対応してい

 。</p> 



<a name="通常のロール" />

## 通常のロール

グローバルスコープの通常のロールは、DXP [コントロールパネル](../../getting-started/navigating-dxp.md#control-panel) で構成できます。

| 許可範囲              | 利用可能な割り当て                                                                    |
| :--- | :--- |
| 仮想インスタンス全体（グローバル） | ユーザーグループ <br />組織 <br />サイト <br />セグメント <br />個別ユーザー |


［ロールの追加/編集］フォームの［割り当て先］タブで、ユーザーを通常のロールに割り当てることができます。

1. ［コントロールパネル ］&rarr; ［ユーザー］ &rarr;［ ロール］に移動し、［通常のロール］をクリックします。

1. ［ロールの追加/編集］フォームの［担当者］タブで、必要な割り当てタイプのタブをクリックします。 通常のロールは、ユーザー、サイト、組織、ユーザーグループ、またはユーザーセグメントに割り当てられます。

1. ［追加］ボタン ![Add](../../images/icon-add.png)クリックします。

1. 担当者を選択し、 ［**追加**］ をクリックします。
   
   グループを割り当てる場合、そのグループに割り当てられたすべてのユーザーはロールを継承します。

![ユーザーの組織全体に通常のロールを割り当てることができます。](./assigning-users-to-roles/images/05.png)



<a name="サイトのロール" />

## サイトのロール

ユーザーをサイトロールに割り当てるには、サイトの［People］サブメニューの［メンバーシップ］セクションを使用します。

| 許可範囲   | 利用可能な割り当て                                                       |
| :--- | :--- |
| 単一のサイト | ユーザーグループ <br />組織 <br />セグメント <br />個別サイトメンバー |


1. ［サイトメニュー］ &rarr; People &rarr; ［メンバーシップ］へ行きます。

1. ロールを割り当てるメンバーシップタイプのタブをクリックします。 サイトロールは、ユーザー（つまり、個々のサイトメンバー）、組織、またはユーザーグループに割り当てられます。

1. ［Actions］ボタンをクリック ![Add](../../images/icon-actions.png) &rarr; ロールの割り当て

1. 割り当てるロールを選択し、［**完了**］をクリックします。
   
   グループを割り当てる場合、そのグループに割り当てられたすべてのユーザーはロールを継承します。

![［サイトメニュー］のPeople &rarr; ［メンバーシップセクション］で、サイトロールを割り当てます。](./assigning-users-to-roles/images/01.png)



```{note}
   Liferay CE 7.3.1 GA2 および Liferay DXP 7.2 SP3+ の時点で、サイトロールを `User Segments <../../site-building/personalizing-site-experience/segmentation/creating-and-managing-user-segments.md>`_ に割り当てることができます。 詳細は、 `ユーザーセグメントにロールを割り当てる <./assigning-roles-to-user-segments.md>`_ を参照してください。
```




<a name="組織ロール" />

## 組織ロール

コントロールパネルの［ユーザーと組織］セクションの［組織］タブで、組織ロールにユーザーを割り当てることができます。

| 許可範囲  | 利用可能な割り当て |
| :--- | :--- |
| 単一の組織 | 個人ユーザー    |


1. ［コントロールパネル］ &rarr; ［ユーザー］ &rarr; ［ユーザーと組織］に移動し、 ［**組織**］ タブをクリックします。

1. 組織の［Actions］ボタン ![Add](../../images/icon-actions.png) をクリックし、［**Assign Organization Roles** を選択します。

1. 割り当てるロールを選択すると、現在の担当者が一覧表示されます。

1. 新しい担当者を追加するには、 ［**Available**］ タブをクリックして、ロールに割り当てることができる組織メンバーを表示します。

1. 担当者を選択し、［**Update Associations**］をクリックします。

![コントロールパネルの［組織］セクションで組織ロールを割り当てます。](./assigning-users-to-roles/images/02.png)



<a name="アセットライブラリのロール" />

## アセットライブラリのロール

アセットライブラリのロールの割り当ては、サイトの割り当てとほぼ同じです。アセットライブラリの［ユーザー］サブメニューの［メンバーシップ］セクションで、アセットライブラリのロールにユーザーを割り当てます。

| 許可範囲         | 利用可能な割り当て                                   |
| :--- | :--- |
| 単一のアセットライブラリ | 組織 <br />ユーザーグループ <br /> 個別ユーザー |


1. [［ アセットライブラリ］](../../content-authoring-and-management/asset-libraries/asset-libraries-overview.md)［ メニュー］&rarr; ［People］  &rarr; ［サイトメンバーシップ］へ行きます。

1. ロールを割り当てるメンバーシップタイプのタブをクリックします。 サイトと同様に、アセットライブラリはユーザー（つまり、個々のサイトメンバー）、組織、またはユーザーグループに割り当てられます。

1. ［Actions］ &rarr; ［ロールの割り当てボタン］をクリック ![Add](../../images/icon-actions.png)

1. 割り当てるロールを選択し、［**完了**］をクリックします。
   
   グループを割り当てる場合、そのグループに割り当てられたすべてのユーザーはロールを継承します。

![アセットライブラリメニューの［People］ &rarr; ［メンバーシップ］セクションで、アセットライブラリのロールを割り当てます。](./assigning-users-to-roles/images/03.png)

<!--
## Account Roles

You can assign Users to an Account Role in the Accounts Control Panel section.

| Permission Scope | Available Assignments |
| ---------------- | --------- |
| A single Account | Individual Account Members

1. Go to Control Panel &rarr; Accounts &rarr; Accounts, and click on the Account of interest.

1. Click the Roles tab for the Account.

1. Choose the Role to assign and you see the current assignees listed.

1. To add new assignees, click the **Available** tab to see the Account members that can be assigned to the Role.

1. Choose the assignees, then click **Update Associations** .

![Assign an Account Role within the Accounts section of the Control Panel.](./assigning-users-to-roles/images/04.png)
-->



<a name="デフォルトのユーザーの関連付け" />

## デフォルトのユーザーの関連付け

デフォルトでは、新しいユーザーはユーザーロールのみを受け取りますが、デフォルトのロールの関連付けを管理して、新しいロールを追加したり、ユーザーの割り当てを削除したりできます。

| 許可範囲              | 利用可能な割り当て  |
| :--- | :--- |
| 仮想インスタンス全体（グローバル） | すべての新規ユーザー |


1. ［コントロールパネル］ &rarr; ［設定］&rarr; ［インスタンスの設定］に移動します。

1. ［プラットフォーム］セクションの［ユーザー］カテゴリを選択します。

1. 左側のメニューから［**Default User Associations**］をクリックします。

1. 通常のロールの設定フィールドを使用して、すべての新しいユーザーが継承する必要がある通常のロールのコンマ区切りのリストを入力します。

詳細は、 [Default User Associations](../../system-administration/virtual-instances/users.md#default-user-associations) ドキュメントをご覧ください。

![インスタンス設定を使用して、すべての新しいユーザーにデフォルトの通常のロールを設定します。](./assigning-users-to-roles/images/06.png)

割り当ては重要ですが、ロールは、権限なしで占有するデータベース行の価値はありません。 次に、ロールの権限</a> 定義 についてお読みください。</p>
