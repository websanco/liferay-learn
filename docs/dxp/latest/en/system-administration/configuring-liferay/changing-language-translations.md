# Changing Language Translations

Use the Language Override tool to override existing translations or add new language keys and translations. This tool is available in Liferay DXP U4 (Update 4) and above. Note that these overrides take place at the Instance level of [Configuration Scope](./understanding-configuration-scope.md). For previous Liferay versions, see [Overriding Global Language Translations](../../liferay-internals/extending-liferay/overriding-global-language-translations.md).

## Overriding a Translation

To override a translation,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Language Override*.

2. Use the search bar to find the translation you wish to override. For example, maybe you want to override `home` found at the top of the Product Menu. Search for the term `home`.

    ![Search for the term `home` in the Language Override UI.](./changing-language-translations/images/01.png)

    Note, the search bar searches language keys and only the translations of the selected locale. Use the locale drop-down menu to switch between languages. For example, select `en-US` if you want to search within US English translations. 

3. Locate and click on the translation. A new window will open. You can also click the *Actions* icon (![Actions icon](../../images/icon-actions.png)) to the right of the translation and click *Edit*.

4. Input a new translation for the locale you want to override. Click *Save* at the bottom of the page. 

    ![Input a new translation for the locale you want to override.](./changing-language-translations/images/02.png)

5. The translation for `home` is now overriden.

    ![The translation for `home` is now overriden..](./changing-language-translations/images/03.png)

## Modifying a Translation Override

To modify a translation override,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Language Override*.

2. Click *Filter and Order* and select *Override*. A list of overriden translations are displayed.

3. Click on the translation you wish to modify. A new window will open. You can also click the *Actions* icon (![Actions icon](../../images/icon-actions.png)) to the right of the translation and click *Edit*. 

    If overrides exists, the *Actions* icon (![Actions icon](../../images/icon-actions.png)) also presents an option to remove translation overrides. Click *Remove all translation overrides* and click *OK* in the pop-up window to remove all overrides. If an override exists in the locale you have selected, you will see an additional option to remove just that locale's translation (e.g. *Remove translation override for en_US* if US English is selected).

    ![The Actions icon presents the option to remove translation overrides.](./changing-language-translations/images/04.png)

4. Input your changes and click *Save* at the bottom of the page. If you wish to remove all overrides and revert back to the original translations, click *Clear All Overrides*.

## Adding a Language Key and Translation

To add a language key and translation,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Language Override*.

1. Click the *Add* button (![Add button](../../images/icon-add.png)) and a new window will open.

1. Give an input for the key and at least one language translation. Note, the maximum length is 1,000 characters for the key. Click *Save* when finished.