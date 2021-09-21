# Upgrading Language Key Overrides

In Liferay DXP/Portal 7.4, Liferay module language keys were moved to the Liferay Portal Language module. Many keys were renamed. If you're overriding a renamed language key, you must update the key name in your module.

```{note}
If you are upgrading a Language Key Hook Plugin, please follow [Upgrading Portlet Language Key Hooks](https://help.liferay.com/hc/en-us/articles/360029005272-Upgrading-Portlet-Language-Key-Hooks) first.
```

Here's how to upgrade language key overrides:

1. Check if any of the language keys have been renamed. See [Renamed Language Keys](../reference/renamed-language-keys.md).
1. For each language key that has been renamed, use the new key name in your language properties files.

Your module is ready to deploy.

## Additional Information

* [Overriding Global Language Keys](../../../liferay-internals/extending-liferay/overriding-global-language-keys.md)
* [Renamed Language Keys](../reference/renamed-language-keys.md)
* [Upgrading Custom Development](../upgrading-custom-development.md)