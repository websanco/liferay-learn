# 組織を使用して販売ネットワークを作成する

B2B販売ネットワークの組織構造は、組織機能を使用してLiferay Commerceにミラーリングできます。 組織はユーザーの階層的な配置であり、現実世界の組織階層をモデル化して、ユーザー、ロール、権限、アカウントの分散管理を簡素化できます。

``` note::
   ロールは、ユーザーに割り当てることができる権限のコレクションです。 権限は、メニュー、アカウント、ウィジェットの表示、他のユーザーへの役割の割り当て、新しい商品の追加など、ユーザーが実行できる特定の割り当て可能なアクションです。 詳細については、 `役割 <https://help.liferay.com/hc/en-us/articles/360018174371-Managing-Roles>`_ と `権限 <https://help.liferay.com/hc/en-us/articles/360018174391-Defining-Role-Permissions>`_ についての記事を参照してください 。
```

組織を組織の役割への権限の割り当てとともに使用すると、組織に割り当てられたユーザーは、その組織に定義された役割ベースの権限を自動的に継承します。


<!-- The following sentence is removed because I read it and I don't know what specifically it's telling someone - maybe: Organizations (and sub-organizations) can have accounts assigned to them - ensuring that members of an organization have access to the right accounts at the right time.

For example, a B2B company can give everyone in their sales network access to the right Account information through their organizational membership.

-->

組織はユーザー管理を簡単にします。 組織内のユーザーが再割り当てされるか、別の組織に転送されると、ユーザーの役割と権限は、新しい組織のメンバーシップに基づいて自動的に更新されます。 販売ネットワークの場合、これは顧客アカウントへの合理化された安全なアクセス管理を意味します。

## 現実世界の組織階層のミラーリング


<!-- I'm commenting out the following image because I don't think it clearly depicts how to use organizations to model a sales network. First - it would seem that the image states that "Minium" is the company - and then the company has a sub-org named "Italy". And then "Italy" has sub-orgs per region. I don't think real companies organize like that. The correct model (I think) would be - to make it extremely clear how to understand the image: "Minium Corporation" (Parent Org) > Minium Corporation - Italy Sales Department > [Regions]. Or alternatively: Minium Sales Group > Italy Sales Region > [Regions] - something in the naming to make the hierarchy clearer.
![Image 01](./using-organizations-to-create-a-sales-network/images/01.png) -->

現実の世界とまったく同じように販売ネットワークとアカウントを編成すると、販売チームのメンバーがLiferay Commerce内を簡単に移動できます。 組織の作成と構造化の詳細については、 [こちら](https://learn.liferay.com/dxp/7.x/en/users-and-permissions/organizations/creating-and-managing-organizations.html)を参照してください。

## 組織の役割と権限でアカウント管理とアクセスを定義する

関連付けられた権限を持つ役割をユーザーに割り当てると、ユーザーがシステム内で実行できることと実行できないことを指定できます。 Liferay Commerceには、すぐに使用できるいくつかの組織の役割が付属しています。

![すぐに使用できるいくつかの組織の役割があります。](./using-organizations-to-create-a-sales-network/images/02.png)

アクセス許可のコレクションを各役割に割り当てると、Liferay Commerce内でのその役割のアクセスが定義されます（その後、ユーザーに適用できます）。 権限の例には次のものが含まれます。サブ組織（地域など）、アカウントデータ、注文履歴の表示。注文を管理し、役割を割り当てます。 権限の詳細については、 [ロールの権限の定義](https://help.liferay.com/hc/en-us/articles/360018174391-Defining-Role-Permissions)参照してください。

![各役割には、その役割に定義された権限を持たせることができます。](./using-organizations-to-create-a-sales-network/images/03.png)

## 組織のメンバーシップで長期アクセスを管理する

役割と権限の長期的な管理は、Liferay Commerce内でユーザーの組織メンバーシップを更新することで簡単に実行できます（組織、役割、権限がセットアップされ、割り当てられると）。

![組織をユーザーに割り当てたり、割り当てを解除したりすることで、そのユーザーのアクセス許可を管理できます。](./using-organizations-to-create-a-sales-network/images/04.png)


<!--
(Double/Dual-Screenshot: Show User's permissions before the move and after the move)
-->

## 追加情報

  - [組織の作成と管理](https://learn.liferay.com/dxp/7.x/en/users-and-permissions/organizations/creating-and-managing-organizations.html)
  - [新しい組織タイプの追加](https://learn.liferay.com/dxp/7.x/en/users-and-permissions/organizations/adding-a-new-organization-type.html)
  - [役割の管理](https://help.liferay.com/hc/en-us/articles/360018174371-Managing-Roles)
  - [ロール権限の定義](https://help.liferay.com/hc/en-us/articles/360018174391-Defining-Role-Permissions)
  - [アカウントへの組織の追加](./adding-organizations-to-accounts.md)
