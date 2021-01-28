# サイトを追加する

新しいサイトをLiferay Portalインスタンスに追加するには：

1.  [製品メニュー](../../getting-started/navigating-dxp.md)を開き、コントロールパネルに移動して、*[Sites]* → *[Sites]* の順に選択します。

    ![サイトのコントロールパネルの場所。](./adding-a-site/images/02.png)

2.  ページの右上にある追加アイコン（![Add Site](../../images/icon-add.png)）をクリックします。

3.  サイトテンプレートを選択します。 サイトテンプレートの作成の詳細については、[Site Templates](./building-sites-with-site-templates.md)を参照してください。

4.  サイトの名前を入力し、オプションで説明を入力します。

5.  *[Membership Type]* を設定します。 次のメンバーシップタイプが利用可能です。 <!-- What does it mean for a user to be a member of a site? What abilities/permissions does that typically confer? To answer that question it probably doesn't make sense here, but should link to another article, potentially, "Understanding Site Membership" -->
    
      - **Open：**ユーザーはいつでもサイトのメンバーになることができます。
      - **Restricted：**ユーザーはサイトメンバーシップを要求できますが、サイト管理者がユーザーがメンバーになるための要求を承認する必要があります。
      - **Private：**ユーザーはサイトに参加したり、サイトのメンバーシップを要求したりできません。 サイト管理者は手動でユーザーを選択し、サイトメンバーとして割り当てる必要があります。

6.  残りの設定を構成し、*[保存]* をクリックします。

    ![[General]セクションには、サイトの基本情報が示されます。](./adding-a-site/images/01.png)

``` tip::
   新しく作成された*空のサイト*を表示するには、まずそのサイトにページを作成する必要があります。 See `Adding a Page to a Site <../creating-pages/adding-pages/adding-a-page-to-a-site.md>`_ for more information.
```

製品メニューを開くと、新しいサイトが選択されています。 利用可能なすべてのサイト設定の概要については、[Site Settings](../06-site-settings/README.md)を参照してください。

## 追加情報

  - [Introduction to Site Building](../introduction-to-site-building.md)
  - [Building Sites with Site Templates](./building-sites-with-site-templates.md)
  - [Adding Members to Sites](./adding-members-to-sites.md) <!-- ### The Default Site

@Michael Williams - This information feels out of place here.

When you first start and configure your Liferay Portal instance, a default Site is included. This Site doesn't have its own name but rather takes the name of the instance. By default the instance name is *Liferay*, but this value can be changed through the configuration of the setup wizard or through the Instance Configuration in the Control Panel. See [Configuring the Virtual Instance](TODO) for more information. -->
