# JVM Configuration

Liferay DXP requires a Java JDK 8 or 11 and requires specific JVM option settings. There are also recommended settings specific to JDK 11 and recommended baseline memory settings. Here you'll learn about all of these settings and see them demonstrated in an example Tomcat script.

**Outline:**

* [Recommended Settings](#recommended-jvm-settings)
* [Known Issue Workarounds](#known-issue-workarounds)
* [Example Tomcat Script](#example-tomcat-script)

```note::
   See `the Liferay DXP compatibility matrix <https://www.liferay.com/documents/10182/246659966/Liferay+DXP+7.2+Compatibility+Matrix.pdf/ed234765-db47-c4ad-7c82-2acb4c73b0f9)>`_ to choose a JDK.
```

## Recommended JVM Settings

| Type | Setting | Required | Description |
| :---------- | :------ | :------- | :---------- |
| File Encoding | `-Dfile.encoding=UTF8` | Yes | DXP expects files to be encoded using UTF-8. |
| Timezone | `-Dfile.encoding=UTF8` | Yes | DXP uses the GTM timezone for all dates. |
| Four-digit Years | `-Djava.locale.providers=JRE,COMPAT,CLDR` | No | On JDK 11, this setting is required to display four-digit years. Since JDK 9, the Unicode Common Locale Data Repository (CLDR) is the default locales provider. CLDR does not provide years in a four-digit format (see [LPS-87191](https://issues.liferay.com/browse/LPS-87191)). This setting works around the issue by using JDK 8's default locales provider. |
| Heap Size | `-Xms2560m -Xmx2560m` | No | The recommended maximum heap size is 2GB. Setting the minimum heap size to the maximum heap size value minimizes garbage collections. |

## Known Issue Workarounds

### Illegal Access Warnings

If you're using JDK 11, _Illegal Access_ warnings like these may print to your logs:

```message
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.liferay.petra.reflect.ReflectionUtil (file:/Users/malei/dev/project/bundles/master-bundles/tomcat-9.0.10/lib/ext/com.liferay.petra.reflect.jar) to field java.lang.reflect.Field.modifiers
WARNING: Please consider reporting this to the maintainers of com.liferay.petra.reflect.ReflectionUtil
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```

These warnings are caused by a known issue ([LPS-87421](https://issues.liferay.com/browse/LPS-87421) and can be resolved by adding JVM options like these:

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

### Unable to Determine Server Capabilities

If you're on Linux/UNIX and starting Liferay DXP on JDK 11 using an LCS 5.0.0 client, the following error can occur:

```
ERROR [LCS Worker 2][BaseScheduledTask:92] java.lang.reflect.InaccessibleObjectException: Unable to make public long com.sun.management.internal.OperatingSystemImpl.getOpenFileDescriptorCount() accessible: module jdk.management does not
 "opens com.sun.management.internal" to unnamed module @1a3325e5
java.lang.reflect.InaccessibleObjectException: Unable to make public long com.sun.management.internal.OperatingSystemImpl.getOpenFileDescriptorCount() accessible: module jdk.management does not "opens com.sun.management.internal" to unnamed module @1a3325e5
at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:
at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:
at java.base/java.lang.reflect.Method.checkCanSetAccessible(Method.java:198)
at java.base/java.lang.reflect.Method.setAccessible(Method.java:192)
```

This error is caused by a known issue ([LPS-87506](https://issues.liferay.com/browse/LPS-87506)) and can be resolved by adding this JVM option:

```properties
--add-opens=jdk.management/com.sun.management.internal=ALL-UNNAMED
```

## Example Tomcat Script

Here is a Tomcat `setenv.sh` script that demonstrates some of the JVM options mentioned above:

```properties
CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"
CATALINA_OPTS="$CATALINA_OPTS --add-opens=java.base/java.io=ALL-UNNAMED"
CATALINA_OPTS="$CATALINA_OPTS --add-opens=java.base/java.lang.reflect=ALL-UNNAMED"
```
