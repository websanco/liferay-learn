# Liferay UI Icon Lists

An icon list displays icons in a horizontal list, instead of in a pop-up navigation menu like an [icon menu](https://help.liferay.com/hc/en-us/articles/360029145151-Liferay-UI-Icon-Menus). You can see an example of an icon list menu in a message board thread. The thread's actions are visible at all times for administrators:

![Icon lists display an app's actions at all times.](./liferay-ui-icon-lists/images/01.png)

Create the list menu with the `liferay-ui:icon-list` tag and nest [icons](https://help.liferay.com/hc/en-us/articles/360028832232-Liferay-UI-Icons) for each list item, as shown below:

```markup
<div class="thread-actions">
    <liferay-ui:icon-list>

        <liferay-ui:icon
        iconCssClass="icon-lock"
        message="permissions"
        method="get"
        url="<%= permissionsURL %>"
        useDialog="<%= true %>"
        />

        <liferay-rss:rss
        delta="<%= rssDelta %>"
        displayStyle="<%= rssDisplayStyle %>"
        feedType="<%= rssFeedType %>"
        url="<%= MBRSSUtil.getRSSURL(plid, 0, message.getThreadId(), 0, themeDisplay) %>"
        />

        <liferay-ui:icon
        iconCssClass="icon-remove-sign"
        message="unsubscribe"
        url="<%= unsubscribeURL %>"
        />

        <liferay-ui:icon
        iconCssClass="icon-lock"
        message="lock"
        url="<%= lockThreadURL %>"
        />

        <liferay-ui:icon
        iconCssClass="icon-move"
        message="move"
        url="<%= editThreadURL %>"
        />

        <liferay-ui:icon-delete
        showIcon="<%= true %>"
        trash="<%= trashHelper.isTrashEnabled(themeDisplay.getScopeGroupId()) %>"
        url="<%= deleteURL %>"
        />
    </liferay-ui:icon-list>
</div>
```

See the [Icon List taglibdocs](https://help.liferay.com/hc/en-us/articles/360029145131-Liferay-UI-Icon-Lists) for the full list of available attributes.

## Related Topics

* [Clay Icons](../clay-tag-library/clay-icons.md)
* [Liferay UI Icon Menus](./liferay-ui-icon-menus.md)
* [Liferay UI Icons](./liferay-ui-icons.md)
