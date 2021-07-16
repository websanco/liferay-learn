# JVM Configuration

Liferay DXP requires a Java JDK 8 or 11 and requires specific JVM option settings. There are also recommended settings specific to JDK 11 and recommended baseline memory settings. Here you'll learn about all of these settings and see them demonstrated in an example Tomcat script.

```note::
   See `the Liferay DXP compatibility matrix <https://help.liferay.com/hc/en-us/articles/360049238151>`_ to choose a JDK.
```

## Recommended JVM Settings

| Type | Setting | Required | Description |
| :---------- | :------ | :------- | :---------- |
| File Encoding | `-Dfile.encoding=UTF8` | Yes | DXP requires UTF-8 file encoding to support internationalization. |
| Timezone | `-Duser.timezone=GMT` | Yes | DXP uses the GMT timezone for all dates. |
| Four-digit Years | `-Djava.locale.providers=JRE,COMPAT,CLDR` | No | On JDK 11, this setting displays four-digit years. Since JDK 9, the Unicode Common Locale Data Repository (CLDR) is the default locales provider. CLDR does not provide years in a four-digit format (see [LPS-87191](https://issues.liferay.com/browse/LPS-87191)). This setting works around the issue by using JDK 8's default locales provider. |
| Heap Size | `-Xms2560m -Xmx2560m` | No | The recommended maximum heap size is 2GB. Setting the minimum heap size to the maximum heap size value minimizes garbage collections. |

## Known Issue: Illegal Access Warnings

On JDK 11, _Illegal Access_ warnings like these may print to your logs:

```message
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.liferay.petra.reflect.ReflectionUtil (file:/Users/malei/dev/project/bundles/master-bundles/tomcat-9.0.10/lib/ext/com.liferay.petra.reflect.jar) to field java.lang.reflect.Field.modifiers
WARNING: Please consider reporting this to the maintainers of com.liferay.petra.reflect.ReflectionUtil
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```

These warnings are caused by a known issue ([LPS-87421](https://issues.liferay.com/browse/LPS-87421)) and can be resolved by adding these JVM options:

```
--add-opens=java.base/java.awt.font=ALL-UNNAMED
--add-opens=java.base/java.io=ALL-UNNAMED
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.lang.reflect=ALL-UNNAMED
--add-opens=java.base/java.net=ALL-UNNAMED
--add-opens=java.base/java.nio=ALL-UNNAMED
--add-opens=java.base/java.text=ALL-UNNAMED
--add-opens=java.base/java.util=ALL-UNNAMED
--add-opens=java.base/sun.nio.ch=ALL-UNNAMED
--add-opens=java.rmi/sun.rmi.transport=ALL-UNNAMED
--add-opens=java.xml/com.sun.org.apache.xerces.internal.parsers=ALL-UNNAMED
```

## Example Tomcat Script

Here is a Tomcat `setenv.sh` script that demonstrates some of the JVM options mentioned above:

```properties
CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"
CATALINA_OPTS="$CATALINA_OPTS --add-opens=java.base/java.io=ALL-UNNAMED"
CATALINA_OPTS="$CATALINA_OPTS --add-opens=java.base/java.lang.reflect=ALL-UNNAMED"
```

Note that Liferay supports many application servers, and all of them can be configured with the JVM options of your choosing. 
