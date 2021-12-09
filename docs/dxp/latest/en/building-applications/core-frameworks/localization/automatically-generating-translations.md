# Automatically Generating Translations

Liferay DXP supports about 50 languages out-of-the-box. Each locale has its own [language properties file](https://github.com/liferay/liferay-portal/tree/master/modules/apps/portal-language/portal-language-lang/src/main/resources/content) containing keys for its language.

If you have a custom language keys in your application, it would be tedious and difficult to create keys in all these different languages. Fortunately, you can use Liferay's Language Builder to automatically generate translations. Language Builder leverages Microsoft's Translator API to give you a jump start on creating translations.

```{note}
The accuracy and correctness of machine translation can vary from language to language. It is up to you to make use of the generated translations in your application.
```

## See the Sample Project

1. Start Liferay DXP. If you don't already have a docker container, use

   ```bash
   docker run -it -m 8g -p 8080:8080 [$LIFERAY_LEARN_DXP_DOCKER_IMAGE$]
   ```

   If you're running a different Liferay Portal version or Liferay DXP, adjust the above command accordingly.

1. Download and unzip [Completely Custom Configuration](./liferay-m7d6.zip).

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/building-applications/core-frameworks/localization/liferay-m7d6.zip -O
   ```

   ```bash
   unzip liferay-m7d6.zip
   ```

1. From the module root, run Language Builder.

   ```bash
   ./gradlew buildLang
   ```