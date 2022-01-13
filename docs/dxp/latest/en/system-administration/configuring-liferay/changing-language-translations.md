# Changing Language Translations

Use the Language Override tool to override existing language translations or add new language keys and translations. This tool is available in Liferay DXP U4 (Update 4) and above. Note that these override changes take place at the Instance level of [Configuration Scope](./understanding-configuration-scope.md). See [Overriding Global Language Keys](../../liferay-internals/extending-liferay/overriding-global-language-keys.md) for previous Liferay versions.

## Overriding a Language Translation

To override a language translation,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Language Override*.

2. Use the search bar to find the translation you wish to override. For example, maybe you want to override the `home` key found at the top of the Product Menu. Search for the term `home`.

    ![Search for the term `home` in the Language Override UI.](./changing-language-translations/images/01.png)

    Note, the search bar searches language keys and only the translations of the selected locale. Use the locale drop-down menu to switch between languages. For example, select `en-US` if you want to search within US English language translations. 

3. Locate and click on the language key. A new window will open. Input a new language translation for the locale you want to override. Note, the maximum length is 1,000 characters. Click *Save* at the bottom of the page. 

    ![Input a new language translation for the locale you want to override.](./changing-language-translations/images/02.png)

4. The `home` language key's English translation is now overriden.

    ![The `home` language key's English translation is now overriden.](./changing-language-translations/images/03.png)

## Modifying a Language Override

To modify an existing language override,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Language Override*.

1. Click *Filter and Order* and select *Override*. A list of overriden language keys is displayed.

1. Click on the language key you wish to modify. A new window will open.

1. Input your changes and click *Save* at the bottom of the page. Or click *Clear All Overrides* if you wish to remove all overrides and revert back to the original language translations.

## Adding a Language Key and Translation

To add a language key and translation,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Language Override*.

1. Click the *Add* button (![Add button](../../images/icon-add.png)) and a new window will open.

1. Give an input for the key and at least one language translation. Click *Save* when finished.