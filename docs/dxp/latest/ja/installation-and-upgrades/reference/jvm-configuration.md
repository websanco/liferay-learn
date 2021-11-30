# JVM設定

Liferay DXPにはJava JDK 8 または11が必要であり、特定のJVMオプション設定が必要です。 JDK 11に固有の推奨設定と推奨されるベースラインメモリ設定もあります。 ここでは、これらすべての設定について学習し、Tomcatスクリプトの例で説明します。

```{note}
JDKを選択するには、[LiferayDXP互換性マトリックス](https://help.liferay.com/hc/en-us/articles/360049238151) を参照してください。
```

## 推奨されるJVM設定

| タイプ           | 設定                                        | 必須  | 説明                                                                                                                                                                                                                                   |
|:------------- |:----------------------------------------- |:--- |:------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| ファイルのエンコーディング | `-Dfile.encoding=UTF8`                    | はい  | DXPでは、国際化をサポートするためにUTF-8ファイルエンコーディングが必要です。                                                                                                                                                                                           |
| タイムゾーン        | `-Dfile.encoding=UTF8`                    | はい  | DXPは、すべての日付にGMTタイムゾーンを使用します。                                                                                                                                                                                                         |
| 4桁の年          | `-Djava.locale.providers=JRE,COMPAT,CLDR` | いいえ | JDK 11では、この設定で4桁の年を表示します。 JDK 9以降、Unicode Common Locale Data Repository（CLDR）がデフォルトのロケールプロバイダーです。 CLDRでは、4桁形式の年は提供されません（ [LPS-87191](https://issues.liferay.com/browse/LPS-87191)を参照）。 この設定は、JDK 8のデフォルトのロケールプロバイダーを使用することで問題を回避します。 |
| ヒープサイズ        | `-Xms2560m -Xmx2560m`                     | いいえ | 推奨される最大ヒープサイズは2GBです。 最小ヒープサイズを最大ヒープサイズ値に設定すると、ガベージコレクションが最小限に抑えられます。                                                                                                                                                                 |

## 既知の問題の回避策

### 不正アクセスの警告

JDK 11では、次のような*不正アクセス*警告がログに出力されることがあります。

``` message
WARNING: An illegal reflective access operation has occurred
WARNING: Illegal reflective access by com.liferay.petra.reflect.ReflectionUtil (file:/Users/malei/dev/project/bundles/master-bundles/tomcat-9.0.10/lib/ext/com.liferay.petra.reflect.jar) to field java.lang.reflect.Field.modifiers
WARNING: Please consider reporting this to the maintainers of com.liferay.petra.reflect.ReflectionUtil
WARNING: Use --illegal-access=warn to enable warnings of further illegal reflective access operations
WARNING: All illegal access operations will be denied in a future release
```

これらの警告は既知の問題（[LPS-87421](https://issues.liferay.com/browse/LPS-87421)）が原因で発生し、次のJVMオプションを追加することで解決できます。

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

### サーバー機能を判別できない

Linux/UNIXを使用していて、LCS5.0.0クライアントを使用してJDK11でLiferay DXPを起動している場合、次のエラーが発生する可能性があります。

``` message
ERROR [LCS Worker 2][BaseScheduledTask:92] java.lang.reflect.InaccessibleObjectException: Unable to make public long com.sun.management.internal.OperatingSystemImpl.getOpenFileDescriptorCount() accessible: module jdk.management does not
 "opens com.sun.management.internal" to unnamed module @1a3325e5
java.lang.reflect.InaccessibleObjectException: Unable to make public long com.sun.management.internal.OperatingSystemImpl.getOpenFileDescriptorCount() accessible: module jdk.management does not "opens com.sun.management.internal" to unnamed module @1a3325e5
at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:
at java.base/java.lang.reflect.AccessibleObject.checkCanSetAccessible(AccessibleObject.java:
at java.base/java.lang.reflect.Method.checkCanSetAccessible(Method.java:198)
at java.base/java.lang.reflect.Method.setAccessible(Method.java:192)
```

このエラーは既知の問題（[LPS-87506](https://issues.liferay.com/browse/LPS-87506)）が原因で発生し、次のJVMオプションを追加することで解決できます。

``` properties
--add-opens=jdk.management/com.sun.management.internal=ALL-UNNAMED
```

## Tomcatスクリプトの例

以下は、上記のJVMオプションの一部を示すTomcat `setenv.sh`スクリプトです。

``` properties
CATALINA_OPTS="$CATALINA_OPTS -Dfile.encoding=UTF-8 -Djava.locale.providers=JRE,COMPAT,CLDR -Djava.net.preferIPv4Stack=true -Duser.timezone=GMT -Xms2560m -Xmx2560m -XX:MaxNewSize=1536m -XX:MaxMetaspaceSize=768m -XX:MetaspaceSize=768m -XX:NewSize=1536m -XX:SurvivorRatio=7"
CATALINA_OPTS="$CATALINA_OPTS --add-opens=java.base/java.io=ALL-UNNAMED"
CATALINA_OPTS="$CATALINA_OPTS --add-opens=java.base/java.lang.reflect=ALL-UNNAMED"
```

Liferayは多くのアプリケーションサーバーをサポートしており、それらはすべて、選択したJVMオプションで構成することができます。