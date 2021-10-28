# Adding a Page to a Site

The steps below describe how to add a new Page to your Site and configure the general options. To learn how to configure the remaining Page settings, see [Configuring Individual Pages](../page-settings/configuring-individual-pages.md), or see [Configuring Page Sets](../page-settings/configuring-page-sets.md) to configure options for a [Page Set](../understanding-pages/understanding-pages.md#page-sets).

## Adding a New Page

You can add a new Page from the Pages administration screen or from the Page Tree (Liferay DXP +7.3 only).

### Adding a New Page from Pages Administration

1. Click the *Product Menu* (![Product Menu](../../../images/icon-menu.png)) and go to *Site Builder* &rarr; *Pages*.

1. Click the *Add* button (![Add](../../../images/icon-add.png)), and then click *Add Page*.

    ![The Pages screen lets you edit your Site pages as a whole.](./adding-a-page-to-a-site/images/02.png)

    ```{note}
    Starting with 7.3, the default Home Page is a Content Page instead of a Widget Page.
    ```

1. Under Collections, select a Basic or Global template for your page. To start with an empty [Content Page](../understanding-pages/understanding-pages.md#page-types), click the *Blank* [Master Page Template](../defining-headers-and-footers/master-page-templates.md). Alternatively, click one of the Page templates under the Other heading.

   ![Select a Basic or Global Template for your Page.](./adding-a-page-to-a-site/images/03.png)

1. In the Add Page dialog, enter a Name and click *Add*.

1. For a new Widget Page, configure the remaining settings and then click *Save*.

```{tip}
By default, new pages are included in the Navigation Menu for the Site. To configure this Navigation Menu, see [Managing Site Navigation](../../site-navigation/managing-site-navigation.md).
```

### Adding a New Page from the Page Tree Menu

> Available: Liferay DXP 7.3+.

1. Click the *Product Menu* (![Product Menu](../../../images/icon-menu.png)) and then, click *Page Tree* (![Page Tree](../../../images/icon-page-tree.png)) on the left side of the screen.

1. Click the *Add* (![Add](../../../images/icon-add-app.png)) button to add a new Page.

   ![Adding a new Page using the Page Tree menu.](adding-a-page-to-a-site/images/04.png)

1. Follow the standard steps for creating a page, as outlined [above](#adding-a-new-page).

```{tip}
Pages in draft status show an asterisk (*) next to its name in the Page Tree.
```

## Adding a Child Page

You can add child Pages from the Page administration or the Page Tree (Liferay DXP 7.3+ only).

### Adding a Child Page from Pages Administration

1. Click the *Product Menu* (![Product Menu](../../../images/icon-menu.png)) and go to *Site Builder* &rarr; *Pages*.

1. Click the *Add* button (![Add](../../../images/icon-duplicate.png)) next to an existing Page.

   ![Click the Add buton next to an exiting Page to create a new child Page.](./adding-a-page-to-a-site/images/05.png)

1. Follow the standard steps for creating a Page, as outlined [above](#adding-a-new-page).

```{tip}
Using the Actions Menu (![Actions Menu](../../../images/icon-actions.png)) next to the Page, you can preview a Content Page draft (Liferay DXP 7.2+) or approve a draft (Liferay DXP 7.2 only). These options are available for Pages in draft status and Users with page editing permission.
```

### Adding a Child Page from the Pages Tree

> Available: Liferay DXP 7.3+.

1. Click the *Product Menu* (![Product Menu](../../../images/icon-menu.png)) and then, click *Page Tree* (![Page Tree](../../../images/icon-page-tree.png)) on the left side of the screen.

1. Click the the Actions Menu (![Actions icon](../../../images/icon-actions.png)) next to an existing Page and select *Add Child Page*.

    ![Adding a Child Page using the Page Tree menu.](adding-a-page-to-a-site/images/01.png)

1. Follow the standard steps for creating a Page, as outlined [above](#adding-a-new-page).

## Related Information

- [Understanding Pages](../understanding-pages/understanding-pages.md)
- [Content Page Overview](../building-and-managing-content-pages/content-pages-overview.md)
- [Managing Site Navigation](../../site-navigation/managing-site-navigation.md)
