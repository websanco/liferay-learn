# スクリプトの例

DXPでスクリプトを使用するのに役立ついくつかの例を次に示します。 ほとんどの例は、Liferayブログの投稿「[5 Tips to Improve Usage of the Liferay Script Console](https://liferay.dev/blogs/-/blogs/5-tips-to-improve-usage-of-the-liferay-script-console)」に基づいています。

## 例1：新しい利用規約をユーザーに提示する

この例では、データベースからユーザー情報を取得し、ユーザーを変更して、変更をデータベースに保存します。 あなたの会社が[利用規約](https://help.liferay.com/hc/en-us/articles/360031899692-Instance-Configuration-Instance-Settings#terms-of-use)を更新し、ユーザーにそれらを提示したいとします。 ユーザーが利用規約に同意すると、そのユーザーに関連付けられているユーザーレコードに`agreedToTermsOfUse`と呼ばれるブール型フィールドが設定されます。 フィールド値が`true`である限り、ユーザーには利用規約が表示されません。 すべてのユーザーに対してフィールドを`false`に設定した場合、ユーザーはログインする前に利用規約に再度同意する必要があります。

1.  スクリプトコンソールに次のスクリプトを入力して実行します。

    ``` groovy
    import com.liferay.portal.kernel.service.UserLocalServiceUtil

    userCount = UserLocalServiceUtil.getUsersCount()
    users = UserLocalServiceUtil.getUsers(0, userCount)

    for (user in users) { println("User Name: " + user.getFullName() + " -- " +
    user.getAgreedToTermsOfUse()) }
    ```

    このスクリプトは、各ユーザーの`agreedToTermsOfUse`フィールドの値を出力します。

2.  スクリプトを次のように置き換えます。

    ``` groovy
    import com.liferay.portal.kernel.service.UserLocalServiceUtil

    userCount = UserLocalServiceUtil.getUsersCount()
    users = UserLocalServiceUtil.getUsers(0, userCount)

    long currentUserId = Long.parseLong(userInfo.get("liferay.user.id"))

    for (user in users){

        if(!user.isDefaultUser() && (user.getUserId() != currentUserId)) {

                user.setAgreedToTermsOfUse(false)
                UserLocalServiceUtil.updateUser(user)

        }

    }
    ```

    このスクリプトは、各ユーザーの`agreedToTermsOfUse`フィールドを`false`に設定します。 デフォルトのユーザーと、現在ログインしてスクリプトを実行しているデフォルトの管理ユーザーはスキップされます。

3.  *[Execute]* をクリックします。

4.  最初のスクリプトを再度実行して、スクリプトがレコードを更新したことを確認します。

すべてのユーザー（デフォルトのユーザーとあなたのユーザーを除く）が更新されます。 すべてのユーザーが同意するための新しい利用規約を有効にしました。

## 例2：スクリプト出力へのHTMLマークアップの埋め込み

スクリプトコンソールは、出力をHTMLコンテンツとしてレンダリングします。 したがって、スクリプトの出力にHTMLマークアップを埋め込んで、ルックアンドフィールを変更できます。 以下に例を示します。

``` groovy
import com.liferay.portal.kernel.service.*

number = com.liferay.portal.kernel.service.UserLocalServiceUtil.getUsersCount();
out.println(
        """
                <div style="background-color:black; text-align: center">
                        <h1 style="color: #37A9CC; font-size:xx-large">${number}</h1>
                </div>
        """);
```

![このスクリプトは、HTMLを使用して出力のスタイルを設定します。](./script-examples/images/01.png)

## 例3：スクリプトコンソールに例外を表示する

スクリプトコンソールの標準のエラーメッセージは次のとおりです。

    Your request failed to complete.

メッセージにはエラーの説明も詳細も含まれていません。 エラー情報を取得するには、ログを調べる必要があります。 ただし、try/catchブロックを使用して例外をキャプチャし、コンソールに例外情報を出力することができます。

``` groovy
try {
        nullVar = null
        out.println(nullVar.length())
} catch(e) {
        out.println("""<div class="portlet-msg-error">${e}</div>""")
        e.printStackTrace(out)
}
```

![以下は、例外をキャッチしてスクリプトコンソールに例外情報を出力するGroovyスクリプトの例です。](./script-examples/images/02.png)

## 例4：ファイルへの出力のロギング

スクリプトの出力は、スクリプトが完了したときにのみスクリプトコンソールに表示されます。 スクリプトの進行状況を確認したい場合は、メッセージをファイルに記録できます。

``` groovy
import com.liferay.portal.kernel.service.*
import com.liferay.portal.kernel.dao.orm.*

// Output management

final def SCRIPT_ID = "MYSCRIPT"
outputFile = new File("""${System.getProperty("liferay.home")}/scripting/out-${SCRIPT_ID}.txt""")
outputFile.getParentFile().mkdirs()

def trace(message) {
        out.println(message)
        outputFile << "${message}\n"
}

// Main code

users = UserLocalServiceUtil.getUsers(QueryUtil.ALL_POS, QueryUtil.ALL_POS)
users.each { u ->
        trace(u.getFullName())
}
```

このスクリプトは、`scripting`と呼ばれる[Liferay Home](../../installation-and-upgrades/reference/liferay-home.md)にサブフォルダを作成し、スクリプト出力をこのフォルダ内のファイルに保存します。

ファイルシステムに直接アクセスできない場合は、次のコードを使用して、ファイルの内容をスクリプトコンソールに出力できます。

``` groovy
final def SCRIPT_ID = "MYSCRIPT"
outputFile = new File("""${System.getProperty("liferay.home")}/scripting/out-${SCRIPT_ID}.txt""")
out.println(outputFile.text)
```

　 スクリプトコンソールでさまざまなスクリプトを実行しました。

## 追加情報

  - [スクリプトコンソールからのスクリプトの実行](./running-scripts-from-the-script-console.md)
  - [Using the Script Engine in Workflow](../../process-automation/workflow/developer-guide/using-the-script-engine-in-workflow.md)
  - [Using the Script Engine](./using-the-script-engine.md)
