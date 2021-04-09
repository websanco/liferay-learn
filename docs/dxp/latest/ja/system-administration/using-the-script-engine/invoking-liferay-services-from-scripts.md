# スクリプトからLiferayサービスを呼び出す

多くのスクリプトシナリオでは、Liferayサービスを呼び出す必要があります。 [Liferay `*ServiceUtil`クラス](https://docs.liferay.com/dxp/portal/7.2-latest/javadocs/portal-kernel/)は、[スクリプトコンソール](./running-scripts-from-the-script-console.md)でLiferayサービスを呼び出す最も速くて便利な方法です。 Javaを使用するのと同じ方法でGroovyを使用してLiferayサービスを呼び出します。 Groovyの構文では、簡潔で洗練されたスクリプトを容易に作成できます。

ユーザーのリストを取得し、その名前をLiferayのログに出力する方法を、Javaコードの場合と`UserLocalServiceUtil`を使用するGroovyコードの場合を比較して説明します。

## Java

DXPにデプロイされたモジュールで次のJavaコードを実行するか、[スクリプトコンソール](./running-scripts-from-the-script-console.md)でコードを実行できます。

``` groovy
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import java.util.List;

...

int userCount = UserLocalServiceUtil.getUsersCount();
List<User> users = UserLocalServiceUtil.getUsers(0, userCount);

for (User user:users) {
    System.out.println("User Name: " + user.getFullName());
}

...
```

## Groovy

または、スクリプトコンソールでGroovyコードを使用することもできます。

``` groovy
import com.liferay.portal.kernel.service.UserLocalServiceUtil

userCount = UserLocalServiceUtil.getUsersCount()
users = UserLocalServiceUtil.getUsers(0, userCount)

for (user in users){
    System.out.println("User Name: " + user.getFullName())
}
```

スクリプトコンソールで使用できるため、`com.liferay.portal.kernel.model.User`と`java.util.List`をインポートする必要はありません。 また、Groovy構文はJava構文よりも単純です。

``` note::
   サービスに `` * ServiceUtil``クラスがない場合は、 サービスにアクセスするために、次を使用します。`Service Tracker <../../liferay-internals/dependency-injection/using-a-service-tracker.md`_
```

## 次のステップ

  - [スクリプトコンソールからのスクリプトの実行](./running-scripts-from-the-script-console.md)
  - [Using the Script Engine in Workflow](../../process-automation/workflow/developer-guide/using-the-script-engine-in-workflow.md)
  - [スクリプトの例](./script-examples.md)
