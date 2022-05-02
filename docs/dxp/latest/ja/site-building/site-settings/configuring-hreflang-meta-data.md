# Hreflangメタデータの構成

Hreflangタグは、サイトページの代替バージョンの対象となる言語と地理的地域を識別するために使用されるHTML属性です。 場合によっては、これらの属性は、検索エンジンがユーザーに正しいバージョンのページを提供し、SEOに貢献するのに役立ちます。 デフォルトでは、Liferay DXPはページのhreflangメタデータにサイトで利用可能なすべての言語を設定します。

Liferay 7.4+ DXP/Portal以降では、ページに翻訳されたコンテンツを含む言語のhreflangメタデータのみを生成するようにこの動作を構成できます。 これは、[インスタンスレベルとサイトレベルの両方で構成](../../system-administration/configuring-liferay/understanding-configuration-scope.md)できます。

hreflangsがこのように制限されている場合、Liferayの動作はページタイプによって異なる可能性があります。

***ウィジェットページ** ：ウィジェットページの名前の翻訳に使用される言語がhreflangタグに追加されます。

***コンテンツページ** / **表示ページテンプレート** ：ページフラグメントの翻訳に使用される言語がhreflangタグに追加されます。

## インスタンス設定

Liferayインスタンスのhreflang動作を構成するには、次の手順に従います。

1. **グローバルメニュー**（![Global Menu](../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ タブをクリックして、 ［**Instance Settings**］ &rarr; ［**Pages**］ &rarr; ［**SEO**］（仮想インスタンススコープ）に移動します。

1. ［**Enable Only Translated Hreflangs**］ をオンまたはオフにします。

   ![すべてのインスタンスページのhreflang動作を有効または無効にします。](./configuring-hreflang-meta-data/images/01.png)

1. ［**保存**］ をクリックします。

有効にすると、hreflangメタデータは、ページに翻訳されたコンテンツがある言語に対してのみ生成されます。 無効にすると、ページのhreflangタグにサイトで使用可能なすべての言語が設定されます。

## インスタンスサイトスコープ設定

サイトスコープを使用してインスタンス内のすべてのサイトのhreflang動作を構成するには、次の手順に従います。

1. **グローバルメニュー**（![Global Menu](../../images/icon-applications-menu.png)）を開き、 ［**コントロールパネル**］ タブをクリックして、 ［**Instance Settings**］ &rarr; ［**Pages**］ &rarr; ［**SEO**］（サイトスコープ）に移動します。

1. ［**Enable Only Translated Hreflangs**］ をオンまたはオフにします。

   ![すべてのサイトのhreflang動作を有効または無効にします。](./configuring-hreflang-meta-data/images/02.png)

1. ［**Save**］ をクリックします。

有効にすると、hreflangメタデータは、ページに翻訳されたコンテンツがある言語に対してのみ生成されます。 無効にすると、ページのhreflangタグにサイトで使用可能なすべての言語が設定されます。

```{note}
デフォルトでは、サイトスコープのインスタンス設定はすべてのインスタンスサイトに適用されます。 ただし、 [個別のサイト構成](#individual-site-settings) は、グローバルメニューの構成より優先されます。 デフォルトの動作を復元し、両方のメニュー間で変更を同期するには、個々のサイトの設定をデフォルトに復元する必要があります。 
```

## 個々のサイト設定

次の手順に従って、個々のサイトのhreflang設定を構成します。

1. 目的の **サイト** に移動します。

1. **サイトメニュー**（![Site Menu](../../images/icon-product-menu.png)）を開き、 ［**Configuration**］ を展開し、 ［**Site Setting**］ &rarr; ［**Pages**］ &rarr; ［**SEO**］ に移動します。

1. ［**Enable Only Translated Hreflangs**］ をオンまたはオフにします。

   この構成は、現在のサイトのページにのみ影響します。

   ![サイトのページのhreflang動作を有効または無効にします。](./configuring-hreflang-meta-data/images/03.png)

1. ［**Save**］ をクリックします。

有効にすると、hreflangメタデータは、ページに翻訳されたコンテンツがある言語に対してのみ生成されます。 この構成は、現在のサイトのすべてのページに影響し、インスタンス構成の設定より優先されます。

```{note}
デフォルトでは、個々のサイト設定は [サイトスコープインスタンス設定](#instance-site-scope-settings) から継承されます。 ただし、個々のサイト構成はこの動作より優先されます。 デフォルトの動作を復元し、両方のメニュー間で変更を同期するには、個々のサイトの設定をデフォルトに復元する必要があります。 
```

## 追加情報

* [サイトのローカライゼーション](./site-localization.md)
* [翻訳のエクスポートとインポート](../../content-authoring-and-management/web-content/translating-web-content/exporting-and-importing-translations.md)
