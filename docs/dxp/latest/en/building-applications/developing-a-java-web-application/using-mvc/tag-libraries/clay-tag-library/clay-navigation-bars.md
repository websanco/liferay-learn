# Clay Navigation Bars

Similar to dropdown menus, navigation bars display a list of navigation items. The key difference is navigation bars are displayed in a horizontal bar with all navigation items visible at all times. The navigation bar also indicates the  active navigation item with an underline. Navigation bars come in two styles: white background with dark-grey text (default) and dark-grey background with white text (inverted).

Default navigation bar:

```markup
<clay:navigation-bar 
    navigationItems="<%= navigationBarsDisplayContext.getNavigationItems() %>" 
/>
```

![You can include navigation bars in your apps.](./clay-navigation-bars/images/01.png)

Inverted navigation bar (set `inverted` attribute to `true`):

```markup
<clay:navigation-bar 
    inverted="<%= true %>" 
    navigationItems="<%= navigationBarsDisplayContext.getNavigationItems() %>" 
/>
```

![Navigation bars can be inverted if you prefer.](./clay-navigation-bars/images/02.png)

## Related Topics

* [Clay Dropdown Menus and Action Menus](./clay-dropdown-and-action-menus.md)
* [Clay Form Elements](./clay-form-elements.md)
* [Clay Progress Bars](./clay-progress-bars.md)