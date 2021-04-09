# System Settings

コントロールパネル→構成→システム設定から、 [システムスコープ](./understanding-configuration-scope.md) 構成を作成できます。

システム設定アプリケーションでは、プラットフォーム、セキュリティ、コンテンツとデータ、その他の4つのカテゴリの下に構成セクションが表示されます。 各セクションには、1つ以上の構成エントリが含まれています。

関心のあるセクション（コンテンツやデータなど）を探して必要な構成に移動し、そのカテゴリ（ワークフローなど）を参照します。

![システム設定のエントリは、セクション（コンテンツやデータなど）とカテゴリ（アセットなど）で構成されています。](./system-settings/images/02.png)

参照してもシステム設定エントリが見つからない場合は、検索バーを使用して検索します。

![システム設定エントリを検索できます。](./system-settings/images/03.png)

``` note::
   Some system-scoped configurations can be made in properties files and in the Server Administration application in the Control Panel.
```

Liferay DXPには多くのアプリケーションが含まれています。 アプリケーションとそのサービスには、いくつかの構成可能である [スコープ](./understanding-configuration-scope.md)アプリケーションの開発者によって定義されるように、。 システム設定で行われた構成は、システムスコープであるか、別のスコープで上書きできるシステム全体のデフォルト構成を設定します。

構成オプションの機能がわからない場合は、その機能のドキュメントを確認してください。

## システム構成の編集

変更する構成を見つけたら、

1.  エントリの構成フォームを開きます。

2.  変更を加えて、[ *保存*]をクリックします。 構成の変更が保存され、システム全体に適用されます。

    ``` important::
       Content generated using templates (e.g., FreeMarker templates and Application Display Templates) is cached. Cached content might not reflect configuration changes until the cache is invalidated (cleared). The Server Administration -> Resources tab provides cache clearing options.
    ```

## システム設定エントリのリセット

システム設定をデフォルト値にリセットするには、アクションボタン（![Actions](../../images/icon-actions.png)）をクリックし、次に[ *デフォルト値のリセット*]をクリックします。

![構成への変更を保存した後、デフォルト値のリセットおよびエクスポートのアクションが使用可能になります。](./system-settings/images/04.png)

## 構成のエクスポートと展開

システム設定は移植可能です。 同じ構成を別のインストールに適用するために、構成をエクスポートできます。単一の構成エントリまたは変更したすべてのエントリ。 エクスポートされたファイルは、同じバージョンのLiferay DXPインストールに展開できます。

単一のエントリの構成をエクスポートするには、アクションボタン（![Actions](../../images/icon-actions.png)）をクリックし、次に *エクスポート*クリックします。 システムにダウンロードした構成を含む `.config` ファイル。

システム設定で行ったすべての構成変更をエクスポートするには、[システム設定]オプションボタン（![Options](../../images/icon-options.png)）をクリックし、[ *すべての設定をエクスポート*]をクリックします。 編集したすべてのエントリの `.config` ファイルは、ZIPファイルでダウンロードします。

これらの構成を宛先システムでアクティブにするには、 `.config` ファイルを解凍して [`[Liferay_Home]/ osgi/configs` フォルダーに配置します](../../installation-and-upgrades/reference/liferay-home.md)。

詳細については、「 [構成ファイルの使用](./using-configuration-files.md) 」を参照してください。
