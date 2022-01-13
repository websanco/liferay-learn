# Changing Language Keys

Use the Language Override tool to override existing language keys or add new language keys. Note that these override changes take place at the Instance level of [Configuration Scope](./understanding-configuration-scope.md). This tool is available in Liferay DXP U4 (Update 4) and above. See [Overriding Global Language Keys](../../liferay-internals/extending-liferay/overriding-global-language-keys.md) for previous Liferay versions.

## Overriding a Language Key

To override a language key,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Language Override*.

2. Use the search bar to find the key you wish to change. For example, maybe you want to change the `home` key found at the top of the Product Menu. Search for the term `home`.

    ![Search for the term `home` in the Language Override UI.](./changing-language-keys/images/01.png)

    Note, the search bar only searches within the selected locale. Use the locale drop-down menu to change languages. For example, select `en-US` to search for a language key within US English language keys. 

3. Locate and click on the language key. A new window will open. Input a new language key for the locale you want to override. Note, the maximum length is 1,000 characters. Click *Save* at the bottom of the page. 

    ![Input a new language key for the locale you want to override.](./changing-language-keys/images/02.png)

4. The `home` language key is now overriden.

    ![The `home` language key is now overriden.](./changing-language-keys/images/03.png)

## Modifying a Language Override

To modify an existing language override,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Language Override*.

1. Click *Filter and Order* and select *Override*. A list of overriden language keys is displayed.

1. Click on the language key you wish to modify. A new window will open.

1. Input your changes and click *Save* at the bottom of the page. Or click *Clear All Overrides* if you wish to remove all overrides and revert back to the original language keys.

## Adding a Language Key

To add a language key,

1. Open the Global Menu (![Global Menu](../../images/icon-applications-menu.png)). Navigate to *Control Panel* &rarr; *Configuration* &rarr; *Language Override*.

1. Click the *Add* button (![Add button](../../images/icon-add.png)) and a new window will open.

1. Give an input for the key and at least one language translation. Click *Save* when finished.