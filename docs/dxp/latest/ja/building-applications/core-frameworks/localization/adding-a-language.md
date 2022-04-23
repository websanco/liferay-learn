# 言語の追加

Liferayは現在、約50の言語をサポートしています。 これらの言語の多くは [翻訳](https://translate.liferay.com/) が完了していますが、一部はまだ翻訳中です。 各言語には、その言語のキーを含む [言語プロパティファイル](https://github.com/liferay/liferay-portal/tree/master/modules/apps/portal-language/portal-language-lang/src/main/resources/content) があります。 しかし、世界には [6000](https://www.linguisticsociety.org/content/how-many-languages-are-there-world) 以上のの言語があります。 どうしても言語を追加したい場合は、以下の方法があります。

## 新しい言語の追加

デフォルトでは、 [`portal.properties`](https://github.com/liferay/liferay-portal/blob/41ac354cd0aa3f9d851a37a6a62d8167f81a2bce/portal-impl/src/portal.properties#L2930) ファイルがLiferayで利用可能なすべての言語を指定します。 指定されていない新しい言語を追加するには

1. あなたの言語の [ISO 639-1標準コード](http://www.loc.gov/standards/iso639-2/php/code_list.php) に注意してください（例：アムハラ語のISOコードは `am`となります）。

1. あなたの言語の [ISO 3166-1 alpha-2 two-letter country code](https://www.iso.org/iso-3166-country-codes.html) に注意してください。 (例えば、エチオピアのISOコードは、 `ET`となります）。

1. Liferayのインストール先にある `web.xml` ファイルを修正して置き換えます。`[LIFERAY_HOME]/tomcat<version>/webapps/ROOT/WEB-INF/`. `<web-resource-name`> セクションの下に、新しい言語を追加します。 例:

    ```xml
    <url-pattern>/am/c/portal/protected</url-pattern>
    <url-pattern>/am-ET/c/portal/protected</url-pattern>
    <url-pattern>/am_ET/c/portal/protected</url-pattern>
    ```

1. Liferayのインストール先にある `shielded-container-web.xml` ファイルを修正して置き換えます。`[LIFERAY_HOME]/tomcat<version>/webapps/ROOT/WEB-INF/`. `<servlet-mapping`> セクションの下に、新しい言語を追加します。 例:

    ```xml
    <servlet-mapping>
        <servlet-name>I18n Servlet</servlet-name>
        <url-pattern>/am/*</url-pattern>
    </servlet-mapping>
    <servlet-mapping>
        <servlet-name>I18n Servlet</servlet-name>
        <url-pattern>/am-ET/*</url-pattern>
    </servlet-mapping>
    <servlet-mapping>
        <servlet-name>I18n Servlet</servlet-name>
        <url-pattern>/am_ET/*</url-pattern>
    </servlet-mapping>
    ```

1. 使用する言語用に`language.properties` ファイルを作成し、ファイル名にISOコードを使用します。 例えば、アムハラ語の場合、 `Language_am.properties` となります。

    ```properties
    create-account=መለያ መፍጠር
    email-address=የ ኢሜል አድራሻ
    forgot-password=መክፈቻ ቁልፉን ረሳኽው
    home=መነሻ ገጽ
    password=ፕስወርድ
    powered-by-x=በ {0} የተጎላበተ
    remember-me=አስታወስከኝ
    search=የፍለጋ አሞሌ
    sign-in=ስግን እን
    ```

1. 作成した `language.properties` ファイルで言語モジュールを作成し、モジュールをLiferayのインストールにデプロイします。 このモジュールを作成するためのサンプルプロジェクトと具体的な手順については、 [Overriding Global Language Translations](../../../../liferay-internals/extending-liferay/overriding-global-language-translations.md) を参照してください。

1. インストールした[Liferay Home](../../../../installation-and-upgrades/reference/liferay-home.md)フォルダに`portal-ext.properties` ファイルを作成または変更します。 なお、このファイルは、 `portal.properties` ファイルで定義された有効なデフォルトロケールを上書きします。 そのため、 `portal-ext.properties` ファイルに使用する予定の言語をすべて列挙します。 `locales`と`locales.enabled`の両方を定義します。 たとえば、

    ```properties
    locales=am_ET,ar_SA,fa_IR,en_US,zh_CN,ja_JP
    locales.enabled=am_ET,ar_SA,fa_IR,en_US,zh_CN,ja_JP
    ```

1. モジュールがデプロイされたら、 ［**コントロールパネル**］ &rarr; ［**インスタンス設定**］ &rarr; ［**Localization**］ に移動します。 検証し、新しい言語を［current ］言語に移動させます。 ［**Save**］ をクリックします。

   ![Save your new language to the list of current languages.](./adding-a-language/images/01.png)

3. デフォルトの言語を新しい言語に変更し、 ［**Save**］ をクリックします。 新しい言語がLiferayインスタンスで使用されるようになりました。

   ![Select and use the new language for your Liferay instance.](./adding-a-language/images/02.png)
