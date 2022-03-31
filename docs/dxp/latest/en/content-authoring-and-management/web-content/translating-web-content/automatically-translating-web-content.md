# Automatically Translating Web Content

Liferay can integrate with the following service APIs to generate automatic translations of Web Content.

* [Google Cloud Translation](#enabling-for-google-cloud-translation)
* [Amazon Translate](#enabling-for-amazon-translate)
* [Microsoft Translator](#enabling-for-microsoft-translator)

To use this feature, you must first set up the translation service you are using and then enable it in Liferay's system settings. Once enabled, you can use the service to [generate translations](#using-automatic-translation) for Web Content.

```{important}
Only one service can be active at a given time. If you enable multiple services, the system picks the first one available.
```

## Enabling for Google Cloud Translation

Enabling Google Cloud Translation requires an active account and a Google Cloud project with Cloud Translation API enabled. This project must also have a service account with a JSON account key. See official [Google Cloud Translation](https://cloud.google.com/translate/docs/setup) documentation for more information.

When ready, follow these steps:

1. Open the *Global Menu*(![Global Menu](../../../images/icon-applications-menu.png)), click the *Control Panel* tab, and go to *System Settings* &rarr; *Translation* &rarr; *Translator Using Google Cloud*.

   ![Go to Translator Using Google Cloud.](./automatically-translating-web-content/images/01.png)

1. Check *Enabled*.

1. Enter a valid Google Cloud service account key in JSON format.

1. Click *Save*.

## Enabling for Amazon Translate

{bdg-secondary}`Available 7.4 U9+ and GA13+`

Enabling Amazon Translate requires an active Amazon AWS account. See official [Amazon Translate](https://docs.aws.amazon.com/translate/latest/dg/what-is.html) documentation for more information.

When ready, follow these steps:

1. Open the *Global Menu*(![Global Menu](../../../images/icon-applications-menu.png)), click the *Control Panel* tab, and go to *System Settings* &rarr; *Translation* &rarr; *Translator Using AWS*.

   ![Go to Translator Using AWS.](./automatically-translating-web-content/images/02.png)

1. Check *Enabled*.

1. Enter a valid *Access Key* and *Secret Key*.

1. Enter your region. The default value is `us-west-1`.

1. Click *Save*.

## Enabling for Microsoft Translator

{bdg-secondary}`Available 7.4 U6+ and GA10+`

Enabling Microsoft Translator requires an active Microsoft Azure account. See official [Microsoft Translator](https://docs.microsoft.com/en-us/azure/cognitive-services/translator/) documentation for more information.

When ready, follow these steps:

1. Open the *Global Menu*(![Global Menu](../../../images/icon-applications-menu.png)), click the *Control Panel* tab, and go to *System Settings* &rarr; *Translation* &rarr; *Translator Using Azure*.

   ![Go to Translator Using Azure.](./automatically-translating-web-content/images/03.png)

1. Check *Enabled*.

1. Enter a valid *Subscription Key* and *Resource Location* (region).

1. Click *Save*.

## Using Automatic Translation

Once a translation service is enabled, auto translate buttons are added to the translation interface for Web Content.

Follow these steps to automatically translate all or only individual Web Content fields:

1. Open the Web Content application in a Site or Asset Library.

1. Click *Actions* (![Actions Button](../../../images/icon-actions.png)) for the content you want to translate and select *Translate*.

   ![Open the Web Content translation interface.](./automatically-translating-web-content/images/04.png)

1. Click the top left *Auto Translate* button to translate all Web Content fields into the selected language.

   ![Click the top left Auto Translate button to translate all Web Content fields.](./automatically-translating-web-content/images/05.png)

   Alternatively, click a field's individual *Auto Translate* button (![Auto Translate Button](../../../images/icon-translate.png)) to only translate its text.

   ![Click a field's individual Auto Translate button to only translate its text.](./automatically-translating-web-content/images/06.png)

1. Click *Save as Draft* or *Publish* when finished.

## Additional Information

* [Manually Translating Web Content](./manually-translating-web-content.md)
* [Exporting and Importing Translations](./exporting-and-importing-translations.md)
