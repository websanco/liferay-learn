# ウィジェットページをコンテンツページに変換する

以前のバージョンでは、ウィジェットページはカスタムレイアウトやカスタマイズ可能な列などの排他的な機能を提供していました。 これらの専用機能の多くは7.3のコンテンツページで使用できるため、ほとんどの場合、コンテンツページを作成する必要があります。

以前のバージョンからLiferayポータル7.3にアップグレードし、既存のウィジェットページを移行する場合は、コンテンツページに変換できます。 ウィジェットページをすぐにコンテンツページに変換することも、変換をプレビューして変更を加えてからページを変換することもできます。

変換するページが複数ある場合は、サイト管理から一括変換するか、利用可能なAPIと組み込みのスクリプトエディターを使用して、すべてのウィジェットページを一度に変換できます。 ここでは、すべてのアプローチについて説明します。

## ウィジェットページをプレビューしてコンテンツページに変換する

ウィジェットページを変換する前に変換ドラフトをプレビューするには、次の手順に従います。

1.  製品メニューを開き、サイトのメニューから*[Site Builder]* → *[Pages]* に移動します。

2.  ウィジェットページの横にある[アクション]メニュー（![Actions](../../../images/icon-actions.png)）を開き、[ *プレビューしてコンテンツページに変換* ]オプションを選択します。

3.  警告を確認し、変換ドラフトに必要な調整を行います。 この時点で、必要なフラグメントをドラフトに追加することもできます。

4.  プレビューの下書きを公開するには *公開* をクリックし、ウィジェットページを元の状態にリセットするには *変換ドラフトを破棄* をクリックします。 警告がある場合は、以下で説明するように、ベストエフォートの変換が完了します。

### ベストエフォートコンバージョン

ウィジェットページの一部の機能はコンテンツページでサポートされていないため、そのままでは変換できません。 これらの場合、ユーザーには問題が警告され、ベストエフォートの変換が処理されます。 次のウィジェットページ機能はサポートされていません。

  - **ネストされたアプリケーション：** ネストされたアプリケーションは、変換中にレイアウトの同じ列に配置されます。 ベストエフォート変換が完了した後、これらのアプリケーションを再編成する必要がある場合があります。

  - **カスタマイズ可能なセクション：** ページが [カスタマイズ可能](./enabling-user-personalization-of-widget-pages.md)場合、ユーザーが行ったカスタマイズは変換中に失われます。

  - **カスタムページレイアウト：** レイアウトを変換できる場合、レイアウトの構造が保持され、ユーザーに警告が表示され、続行する前に変換ドラフトを確認する機会が与えられます。 レイアウトを変換できない場合、すべてのウィジェットは単一の行と列に配置されるため、ページの変換後に手動で再編成する必要があります。

<!-- end list -->

``` note::
  If you've already confirmed that a custom layout template can be converted, You can disable the layout template conversion warning for the layout template so you don't keep seeing it each time you convert a Widget Page that uses the layout. Open the Product Menu and go to *Control Panel* -> *Configuration* -> *System Settings*. Select *Pages* under Content and Data and add the layout template ID to the list of "Verified Layout Template IDs" under the System Scope.
```

``` note::
  While portlets are rendered according to `render-weight <https://docs.liferay.com/ce/portal/7.3-latest/definitions/liferay-portlet-app_7_3_0.dtd.html#render-weight>`_ on Widget Pages, that is not true for Content Pages. Portlets are rendered in the order they appear on the page on Content Pages (i.e. left to right, top to bottom), so you may notice some portlets are rendered sooner or later, depending on their placement on the Content Page.
```

## ウィジェットページをコンテンツページに直接変換する

次の手順に従って、プレビューなしでウィジェットページをコンテンツページに変換します。

1.  製品メニューを開き、サイトのメニューから*[Site Builder]* → *[Pages]* に移動します。

2.  ウィジェットページまたは複数のウィジェットページのチェックボックスをオンにし、管理ツールバーでアクションメニューを開いて、[ *コンテンツページ* 変換]オプションを選択します。

    ![コンテキストメニューから複数のウィジェットページを変換できます](./converting-widget-pages-to-content-pages/images/01.png)

3.  表示されるプロンプトで[ *OK* ]をクリックして、変換を完了します。

## APIを使用してすべてのウィジェットページをコンテンツページに一括変換する

組み込みのスクリプトエディターを使用して、サイト上のすべてのウィジェットページをコンテンツページに一括変換できます。 次の手順を実行します：

1.  製品メニューを開き、 *コントロール パネル* → *構成* → *サーバー管理* → *スクリプト* に移動します。

2.  スクリプトウィンドウにこのスクリプトを入力します。必ず、グループIDを自分のものに置き換えてください。 Groovyスクリプトは、 [`BulkLayoutConverter` インターフェース](https://docs.liferay.com/portal/7.3-latest/apps/layout-3.0.0/javadocs/com/liferay/layout/util/BulkLayoutConverter.html) を使用して、指定されたグループIDを持つすべてのウィジェットページをコンテンツページに変換します。

    ``` groovy
    import com.liferay.layout.util.BulkLayoutConverter
    import com.liferay.portal.kernel.util.ArrayUtil
    import com.liferay.registry.Registry
    import com.liferay.registry.RegistryUtil
    import org.osgi.framework.ServiceReference
    import org.osgi.framework.BundleContext

    Registry registry = RegistryUtil.getRegistry()

    BundleContext bundleContext = registry._bundleContext

    ServiceReference serviceReference = bundleContext.getServiceReference(BulkLayoutConverter.class.getName())

    BulkLayoutConverter bulkLayoutConverter = bundleContext.getService(serviceReference);

    long groupId = 20118L // Use your groupId

    long[] plids = bulkLayoutConverter.getConvertibleLayoutPlids(groupId)

    out.println("Convertible layouts before conversion:" + ArrayUtil.toStringArray(plids))

    long[] convertedLayoutPlids = bulkLayoutConverter.convertLayouts(groupId)

    out.println("Converted layouts:" + ArrayUtil.toStringArray(convertedLayoutPlids))

    plids = bulkLayoutConverter.getConvertibleLayoutPlids(groupId)

    out.println("Convertible layouts after conversion: " + ArrayUtil.toStringArray(plids))
    ```

3.  [ *実行* ]をクリックしてスクリプトを実行します。

4.  出力は以下のスニペットのようになり、変換可能なレイアウト（ページ）が残っていないことが示されます。

<!-- end list -->

``` bash
Convertible layouts before conversion:[25, 26, 27]
Converted layouts:[25, 26, 27]
Convertible layouts after conversion: []
```
