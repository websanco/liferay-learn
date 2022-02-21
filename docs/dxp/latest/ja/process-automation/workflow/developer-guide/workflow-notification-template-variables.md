# ワークフロー通知テンプレート変数

> 対象：Liferay CE/DXP 7.3+

一部の変数は、ワークフローの通知コンテキストに自動挿入されます。 これらは、ワークフローの通知テンプレートで使用すると便利です。

通常、Freemarkerでは、変数を明示的に宣言する必要があります。

```markup
<#assign variableName = "Variable Name" />
```

インジェクトされた変数はすでに宣言されており、テンプレート内で直接使用することができます。

```markup
${variableName}
```

これらの変数を使用するには、ワークフローの通知コンテキストで利用可能な変数を知っておく必要があります。 利用できる変数は、ワークフロー定義の詳細やワークフロー内のアセットに応じて変化します。 ここで紹介する方法は、特定のコンテキストに合わせて変数のリストを取得する方法を示していますので、推測で行う必要はありません。

## ワークフロー通知テンプレート変数の確認

DEBUGレベルの [ロギング](./../../../system-administration/using-the-server-administration-panel/configuring-logging.md) を `TemplentNotificationMessageGenerator` クラスで有効にして、ワークフローの通知コンテキストで利用可能な変数を表示します。

1. コントロールパネル &rarr; 構成 &rarr; サーバーの管理 に移動します。

1. ［Log Levels］タブをクリックします。

1. この設定でLog Levelを追加します。

   - *ロガー名*： `com.liferay.portal.workflow.kaleo.runtime.internal.notification.TemplateNotificationMessageGenerator`
   - *ログレベル*：`DEBUG`

1. [アセット（Blogs Entryなど）に対して、ワークフロー定義](./../using-workflows/activating-workflow.md) （唯一の承認者定義など）をアクティブにすることができます。

1. テストエントリを送信すると、ログメッセージが表示され、ワークフローで使用可能な通知テンプレートの変数が表示されます。

例えば、唯一の承認者のワークフローでは、アセットの初回提出時にこれらの変数が表示されます。

```bash
2020-03-30 14:21:42.089 DEBUG [liferay/kaleo_graph_walker-2][TemplateNotificationMessageGenerator:94] saxReaderUtil (class com.sun.proxy.$Proxy447)
2020-03-30 14:21:42.097 DEBUG [liferay/kaleo_graph_walker-2][TemplateNotificationMessageGenerator:94] getterUtil (class com.liferay.portal.kernel.util.GetterUtil_IW)
2020-03-30 14:21:42.098 DEBUG [liferay/kaleo_graph_walker-2][TemplateNotificationMessageGenerator:94] portalPermission (class com.liferay.portal.service.permission.PortalPermissionImpl)
2020-03-30 14:21:42.098 DEBUG [liferay/kaleo_graph_walker-2][TemplateNotificationMessageGenerator:94] entryClassPK (class java.lang.String)
2020-03-30 14:21:42.098 DEBUG [liferay/kaleo_graph_walker-2][TemplateNotificationMessageGenerator:94] layoutPermission (class com.liferay.portal.service.permission.LayoutPermissionImpl)
2020-03-30 14:21:42.098 DEBUG [liferay/kaleo_graph_walker-2][TemplateNotificationMessageGenerator:94] expandoTableLocalService (class com.sun.proxy.$Proxy43)
2020-03-30 14:21:42.098 DEBUG [liferay/kaleo_graph_walker-2][TemplateNotificationMessageGenerator:94] localeUtil (class com.liferay.portal.kernel.util.LocaleUtil)
2020-03-30 14:21:42.099 DEBUG [liferay/kaleo_graph_walker-2][TemplateNotificationMessageGenerator:94] groupId (class java.lang.String)
2020-03-30 14:21:42.099 DEBUG [liferay/kaleo_graph_walker-2][TemplateNotificationMessageGenerator:94] portalUtil (class com.liferay.portal.util.PortalImpl)
...
```

上の出力のスニペットを見ると、 `saxReaderUtil`, `getterUtil`, `portalPermission`, `entryClassPK`, `layoutPermission`, `expandoTableLocalService`, `localeUtil`, `groupId`, `portalUtil` は、これらのメッセージがログに出力される原因となったコンテキストで利用可能です。

### ワークフロー通知テンプレート変数の使用

コンテクストに注入される通知変数は、主に2つのタイプに分類されます。

1. **値** 変数は、単一の値を提供します。 変数が単一の値を提供する場合、その値を通知に表示するために使用することができます。また、通知メッセージに表示される他の有用な情報を取得する操作のパラメータとして渡すこともできます。

   _例：_ 唯一の承認者の定義では、FreeMarkerのテンプレートでこの通知を提供しています。

   ```markup
   ${userName} sent you a ${entryType} for review in the workflow.
   ```

   `userName` と `entryType` が値を提供しているので、通知は次のように表示されます。

   _Joe Bloggsさんから、ワークフローで確認するためのブログのエントリが送られてきました。_


   また、値型変数の内容を確認することもできます。 唯一の承認者の定義には、この通知テンプレートも含まれています。

   ```markup
   Your submission was reviewed<#if taskComments?has_content> and the reviewer applied the following ${taskComments}</#if>.
   ```

   レビュアがタスクコメントを提供した場合は、そのコメントが表示されます。 そうでない場合は、通知の送付先は

   _あなたの提出物はレビューされました_

1. **オペレーション** 変数はLiferay DXPのJavaクラスを公開しているので、通知テンプレートでその操作にアクセスすることができます。 これらの変数については、クラスの [Javadoc](https://learn.liferay.com/reference/latest/en/dxp/javadocs/) を熟知するか、 [ソースコード](https://github.com/liferay/liferay-portal/tree/[$LIFERAY_LEARN_PORTAL_GIT_TAG$]) を見て、その動作を理解する必要があります。

   _例：_ このFreeMarkerは、デフォルトのロケールを使用して、特定のパターン（_月/日/年、時間：分_）で現在の日付を取得します。

   ```markup
   ${dateUtil.getCurrentDate("MM/dd/yyyy, HH:mm",  localeUtil.getDefault())}`
   ```

## ワークフロー通知テンプレートの変数について

これらは、Blogsエントリーの唯一の承認者の定義 を有効にして、新しいエントリーを公開用に送信したときに記録される変数です。

WebServerServletToken. getToken(long imageId)</0 からの画像トークン</td> </tr> 

</tbody> </table>
