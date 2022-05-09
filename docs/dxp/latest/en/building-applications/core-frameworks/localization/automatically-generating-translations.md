# Automatically Generating Translations

Liferay DXP supports 50 languages out-of-the-box. Each locale has its own [language properties file](https://github.com/liferay/liferay-portal/tree/master/modules/apps/portal-language/portal-language-lang/src/main/resources/content) containing keys for its language.

When you create an application with custom language keys, it's tedious and difficult to translate them for all these different languages. Fortunately, you can use Liferay's Language Builder tool to generate translations automatically. Language Builder generates translations using Microsoft's Translator API. 

```{note}
The accuracy and correctness of machine translation can vary from language to language. It is up to you on how to make use of these generated translations.
```

## See the Sample Project

```{include} /_snippets/run-liferay-dxp.md
```

Then, follow these steps:

1. Download and unzip [Automatically Generating Translations](./liferay-m7d6.zip).

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/building-applications/core-frameworks/localization/liferay-m7d6.zip -O
   ```

   ```bash
   unzip liferay-m7d6.zip
   ```

1. Open the `gradle.properties` file in the module root folder. Add your Microsoft Translator subscription key:

   ```properties
   microsoft.translator.subscription.key={your unique key}
   ```

   Note for Language Builder to work, you need to obtain your own [Microsoft Translator subscription key](https://www.microsoft.com/en-us/translator/business/translator-api/). 

1. From the module root, run Language Builder.

   ```bash
   ./gradlew buildLang
   ```

   In your terminal window, Language Builder generates the different language properties files. Note, if you see the error `Translation is disabled because credentials are not specified`, double-check that your subscription key is correct.

1. Navigate to the module's `src/main/resources/content` folder to view the translated properties files.

  ![The properties files are found in the src/main/resources/content folder.](./automatically-generating-translations/images/01.png)

## Adding Your Language Keys

The sample project has one language key in the `Language.properties` file. Add as many language keys as you need for your application, then run Language Builder.

To deploy the language keys to Liferay, see [Overriding Global Language Keys](../../../liferay-internals/extending-liferay/overriding-global-language-translations.md). The instructions are the same whether overriding existing keys or adding new keys. 

If you want to exclude some languages from being translated automatically, use the `excludedLanguageIds` property in your project's `build.gradle` file. For example, to include French, 

```properties
buildLang {
	excludedLanguageIds = ["fa"]
}
```

To learn more about the Language Builder tool and additional configuration, see [Lang Builder Gradle Plugin](https://github.com/liferay/liferay-portal/tree/master/modules/sdk/gradle-plugins-lang-builder).
