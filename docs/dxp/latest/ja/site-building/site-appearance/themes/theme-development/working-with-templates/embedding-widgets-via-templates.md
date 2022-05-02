# テンプレートを介したウィジェットの埋め込み

テーマにウィジェットを埋め込んで、選択したテンプレートファイルを使用してすべてのページでウィジェットを使用できるようにすることができます。 ウィジェットを埋め込むときは、ウィジェットのポートレット定義を参照して、 <!-- ポートレット定義の記事へのリンクがあれば追加してください --> コンポーネントの値の一部を提供する必要があります。

準備ができたら、テーマ（`src/templates/`ディレクトリ内）でFreeMarkerテンプレート（`.ftl`）ファイルを選択してウィジェットを埋め込みます。 たとえば、`portal_normal.ftl`を選択して、追加するほとんどのページにウィジェットを埋め込みます。

選択したウィジェットのポートレット名とインスタンスID（ウィジェットがインスタンス化されている場合）を使用して、ウィジェットを埋め込むテンプレートファイルに`liferay_portlet["runtime"]`マクロを追加します。

```
<@liferay_portlet["runtime"]
    instanceID="INSTANCE_ID"
    portletName="PORTLET_NAME"
/>
```

ポートレット定義でウィジェットの`javax.portlet.name`の値を使用して、ポートレット名を指定します。 `com.liferay.portlet.instanceable`プロパティが`false`に設定されていない限り、インスタンスIDも指定する必要があります。

たとえば、次のマクロは、テンプレートファイルの選択した場所にナビゲーションメニューを埋め込みます。

```
<@liferay_portlet["runtime"]
    portletName="com_liferay_product_navigation_applications_menu_web_internal_portlet_ProductNavigationApplicationsMenuPortlet"
/>
```

埋め込みウィジェットをテンプレートに追加したら、`gulp deploy`を実行して、変更を加えたテーマを作成します。 デプロイされたテーマには、選択した場所にウィジェットが埋め込まれました。

<!-- When available, add more information referencing article as to embedding widgets by function (developer tutorial) -->
