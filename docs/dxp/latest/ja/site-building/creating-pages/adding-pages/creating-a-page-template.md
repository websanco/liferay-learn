# ページテンプレートの作成

> 対応可能：Liferay DXP/Portal 7.3以降

ページテンプレートを使用すると、事前定義されたレイアウトとコンテンツに基づいてページを効率的に作成できます。 すぐに使用できるいくつかの基本的なページテンプレートがあります。

![基本的なページテンプレートを使用できます。](./creating-a-page-template/images/01.png)

以下で説明するように、独自の [コンテントページ](../using-content-pages.md) テンプレートと [ウィジェットページ](../understanding-pages/understanding-pages.md#widget-pages) テンプレートを作成することもできます。

```{note}
Liferay DXP 7.4以降の場合、ページテンプレートコレクションは、Liferay UIではページテンプレートセットと呼ばれます。
```

<a name="creating-a-new-page-template" />

## 新規のページテンプレートの作成

以下の手順は、ウィジェットおよびコンテントページテンプレートを作成して使用する方法を示しています。

1. プロダクトメニューを開き、 ［**Site Builder**］ &rarr; ［**Pages**］ に移動します。

1. ［**Page Templates**］ タブを選択します。

1. ［**New**］ をクリックし、新しいセットを作成してページテンプレートを整理します。

    ![新規のページテンプレート設定の作成。](./creating-a-page-template/images/02.png)

1. **追加** アイコン（![Add Page Template](../../../images/icon-add.png)）をクリックし、 **ウィジェットページテンプレート** または **コンテントページテンプレート** を選択します。 セット内のページテンプレート横の **アクション** アイコン(![Actions](../../../images/icon-actions.png))をクリックして ［**構成**］ を選択すれば、いつでもページテンプレートの構成に戻ることができます。

1. 名前を入力します。

1. ［**保存**］ をクリックします。

1. 新しいページテンプレートに自動的に移動します。 [ウィジェットページ](../understanding-pages/understanding-pages.md#widget-pages) の指示に従って、ウィジェットページテンプレートを作成します。 同様に、コンテントページテンプレートを構成するには、[コンテンツページへの要素の追加](../using-content-pages/adding-elements-to-content-pages.md) を参照してください。

<a name="sharing-a-new-page-template" />

## 新規のページテンプレートの共有

デフォルトでは、ページテンプレートの作成者のみが使用できます。

新しく作成したページテンプレートへのアクセス権を他のユーザーに付与するには、次の手順に従います。

1. ページテンプレートのアクションメニューから ［**Permissions**］ を選択します。
1. ページテンプレートへのアクセスを許可するロールの **View** 権限にチェックを入れます。 ページを作成できるユーザーがページテンプレートを使用できるようにする場合は、 **ユーザー** ロールの **View** 権限にチェックを入れます。

    ![他のユーザーがアクセスできるようにページテンプレートの権限を構成します。](./creating-a-page-template/images/03.png)

1. ［**保存**］ をクリックします。

指定されたロールを持つユーザーは、ページテンプレートにアクセスできます。

<a name="additional-information" />

## 追加情報

<a name="propagating-changes-for-widget-pages" />

### ウィジェットページの変更の伝播

ウィジェットページテンプレートからページを作成すると、変更はデフォルトでページテンプレートから継承されるため、今後の変更は **自動的** にページテンプレートを使用するページに伝播されます。 サイト管理者は、 [個々のページ設定](../page-settings/configuring-individual-pages.md#general) を通じてこの動作を無効にすることができます。

<a name="propagating-changes-for-content-pages" />

### コンテントページの変更の伝播

コンテントページテンプレートを使用して作成した場合、コンテントページの **変更は自動的に伝播されません** 。 ただし、コンテントページで使用されるフラグメントへの変更は伝播できます。 詳細については、 [フラグメントの変更のプロパゲート](../page-fragments-and-widgets/using-fragments/propagating-fragment-changes.md) を参照してください。

<a name="sharing-page-templates-with-other-sites" />

### 他のサイトとのページテンプレートの共有

ページの場合と同じプロセスを使用して、ページテンプレートを他のサイトと共有できます。 方法については、[ページとコンテンツのインポートとエクスポート](../../building-sites/importing-exporting-pages-and-content.md)の手順に従ってください。

<a name="staging-and-page-templates" />

### ステージングとページテンプレート

```{important}
7.3 GA1以降、ステージング機能は [メンテナンスモード](../../../installation-and-upgrades/upgrading-liferay/reference/maintenance-mode-and-deprecations-in-7-3.md) になっています。
```

ステージングが有効になっている場合、ウィジェットページテンプレートへの変更は、自動的に **準備** ページに伝播されます。 これらの変更は、ページが本番環境に公開される前に承認される必要があります。 このため、準備ページへのウィジェットページテンプレートの変更の自動伝播をオフにすることはできず、 **変更の継承** セレクターは表示されません。

<a name="additional-information-1" />

## 追加情報

- [サイトにページを追加する](./adding-a-page-to-a-site.md)
- [ページテンプレートのエクスポートとインポート](./exporting-and-importing-page-templates.md)
- [フルページアプリケーションページタイプの使用](./using-the-full-page-application-page-type.md)
