# ウィジェットページをコンテントページに変換する

以前のバージョンでは、ウィジェットページはカスタムレイアウトやカスタマイズ可能な列などの排他的な機能を提供していました。 これらの排他的な機能の多くは、7.3の[コンテントページ](../../using-content-pages.md)で利用できますので、ほとんどの場合、コンテントページを作成することになるでしょう。

以前のバージョンからLiferay Portal7.3にアップグレードし、既存のウィジェットページを移行する場合は、コンテントページに変換できます。 ウィジェットページをすぐにコンテントページに変換することも、変換をプレビューして変更を加えてからページを変換することもできます。

変換するページが複数ある場合は、サイト管理から一括変換するか、利用可能なAPIと組み込みのスクリプトエディターを使用して、すべてのウィジェットページを一度に変換できます。 ここでは、すべてのアプローチについて説明します。

## ウィジェットページをプレビューしてコンテントページに変換する

ウィジェットページを変換する前に変換下書きをプレビューするには、次の手順に従います。

1. Liferay DXPメニューを開き、サイトメニューの下の ［**サイトビルダー**］ &rarr; ［**ページ**］ に移動してください。

1. ウィジェットページの隣にあるアクションメニュー（![Actions](../../../../images/icon-actions.png)）を開き、 ［**Preview and convert to Content Page**］ オプションを選択します。

1. 警告を確認し、変換下書きに必要な調整を行います。 この時点で、必要なフラグメントを下書きに追加することもできます。

1. ［**公開**］ をクリックすると下書きのプレビューが公開され、 ［**変換下書きを破棄**］ をクリックするとウィジェットページが元の状態に戻されます。 警告がある場合は、以下で説明するように、ベストエフォートの変換が完了します。

### ベストエフォートコンバージョン

ウィジェットページの一部の機能はコンテントページでサポートされていないため、そのままでは変換できません。 これらの場合、ユーザーには問題が警告され、ベストエフォートの変換が処理されます。 次のウィジェットページ機能はサポートされていません。

***入れ子になったアプリケーション：** 入れ子になったアプリケーションは、変換中にレイアウトの同じ列に配置されます。 ベストエフォート変換が完了した後、これらのアプリケーションを再編成する必要がある場合があります。

***カスタマイズ可能なセクション：** ページが [カスタマイズ可能](./enabling-user-personalization-of-widget-pages.md)な場合、ユーザーが行ったカスタマイズは変換中に失われます。

***カスタムページレイアウト：** レイアウトを変換できる場合、レイアウトの構造が保持され、ユーザーに警告が表示され、処理する前に変換下書きを確認する機会が与えられます。 レイアウトを変換できない場合、すべてのウィジェットは単一の行と列に配置されるため、ページの変換後に手動で再編成する必要があります。

```{note}
カスタムレイアウトテンプレートの変換が可能であることが確認されている場合、そのレイアウトテンプレートの変換警告を無効にすることで、そのレイアウトを使用するウィジェットページを変換するたびに警告が表示され続けることがなくなります。 Liferay DXPメニューを開き、*［コントロールパネル］* -> *［設定］* -> *［システム設定］*に移動します。 コンテンツとデータの下にある*ページ*を選択し、システムスコープの下の「レイアウトテンプレートID」のリストに確認済みレイアウトテンプレートIDを追加します。
```

```{note}
ウィジェットページではポートレットは [render-weight](https://learn.liferay.com/reference/latest/en/dxp/definitions/liferay-portlet-app_7_3_0.dtd.html#render-weight) に従ってレンダリングされますが、コンテントページではその限りではありません。 ポートレットは、コンテントページでページ上に表示される順番（つまり、左から右、上から下）にレンダリングされます。したがって、コンテントページでの配置によって、一部のポートレットが早くレンダリングされたり遅かったりすることに気づくかもしれません。
```

## ウィジェットページをコンテントページに直接変換する

次の手順に従って、プレビューなしでウィジェットページをコンテントページに変換します。

1. Liferay DXPメニューを開き、サイトメニューの下の ［**サイトビルダー**］ &rarr; ［**Pages**］ に移動してください。

1. ウィジェットページまたは複数のウィジェットページのチェックボックスをオンにし、管理ツールバーでアクションメニューを開いて、 ［**Convert to Content Page**］ オプションを選択します。

   ![コンテキストメニューから複数のウィジェットページを変換できます。](./converting-widget-pages-to-content-pages/images/01.png)

1. 表示されたプロンプトで ［**OK**］ をクリックして変換を完了します。

## APIを使用してすべてのウィジェットページをコンテントページに一括変換する

組み込みのスクリプトエディターを使用して、サイト上のすべてのウィジェットページをコンテントページに一括変換できます。 次の手順を実行します：

1. Liferay DXPのメニューを開き、 ［**コントロールパネル**］ &rarr; ［**設定**］ &rarr; ［**サーバ管理**］ &rarr; ［**スクリプト**］ と進みます。

1. スクリプトウィンドウにこのスクリプトを入力します。必ず、グループIDを自分のものに置き換えてください。 Groovyスクリプトは、 [`BulkLayoutConverter` インターフェース](https://docs.liferay.com/dxp/portal/7.3-latest/apps/layout-3.0.0/javadocs/com/liferay/layout/util/BulkLayoutConverter.html) を使用して、指定されたグループIDを持つすべてのウィジェットページをコンテントページに変換します。

    ```groovy
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

1. ［**実行**］ をクリックしてスクリプトを実行します。

1. 出力は以下のスニペットのようになり、変換可能なレイアウト（ページ）が残っていないことが示されます。

  ```bash
  Convertible layouts before conversion:[25, 26, 27]
  Converted layouts:[25, 26, 27]
  Convertible layouts after conversion: []
  ```