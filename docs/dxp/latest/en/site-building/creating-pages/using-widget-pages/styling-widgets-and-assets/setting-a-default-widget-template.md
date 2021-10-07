# Setting a Default Widget Template

You can change the widget template for individual widgets through their own configuration, however you can also configure a default widget template to apply to all widgets of a particular type. To do this, go to *Control Panel* &rarr; *System Settings*. In System Settings you can find a configuration for every widget in Liferay DXP. Widgets that support widget templates have a *Display Style Group ID* and a *Display Style* option.

  ![The widget template configuration in System Settings lets you change the display style.](./setting-a-default-widget-template/images/01.png)

- **Display Style Group ID:** The Site ID where the widget template is located. For Global templates use 0 for the ID.
- **Display Style:** The widget template's key.

```{note}
To enter a Display Style, you first need the *Template Key* for the template you want to use. To get the Template Key, go to the *Application Display Template* list for a given Site and retrieve it from the widget template listing. Then enter the display style as `ddmTemplate_[template-key]`.
```

## Default Widget Template Example

For example, configure the Language Selector widget templates like this:

1.  Open the *Home* &rarr; *Design* &rarr; *Widget Templates*.

1.  Click on the *Add icon* (![Add](../../../../images/icon-add.png)) and select the option *Language Selector Template*.

1.  Open *Details* and find the *Template Key* - `LANGUAGE-ICON-FTL`

  ![System Settings shows where you can find the Template Key.](./setting-a-default-widget-template/images/02.png)

Now that you have the ID, you can change the template from System Settings.

1.  Go to *Control Panel* &rarr; *Configuration* &rarr; *System Settings*.

1.  Find *Localization* under the *Platform* heading and select *Language Selector* from the options on the left.

1.  In the *Display Style* field, enter `ddmTemplate_LANGUAGE-ICON-FTL`.

Now any Language Selector widgets are added to a page use the new defaults.

```{warning}
Setting a new default widget template does not affect any widgets that are already added to a page and have a configured widget template.
```
