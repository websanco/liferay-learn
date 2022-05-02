# サイトテンプレートによるサイト構築

サイトテンプレートは、サイトの事前構成された構造を定義します。これには、ページ、テーマ、コンテンツ、レイアウト、ページテンプレート、アプリケーション、および各ページに定義されたアプリの構成が含まれます。 サイトテンプレートに加えられた変更は、特に指定がない限り、それを使用するサイトに自動的に反映されます。

```{note}
サイトテンプレートは、サイトの変更を伝えるものではありますが、サイトのデータを伝える手段としては使用しないでください。 サイトのデータを伝播させるには、代わりに[データをエクスポートして別のサイトにインポートする](./importing-exporting-pages-and-content.md)が必要です。
```

すぐに使用できる3つのサイトテンプレートが用意されています。

- **ブランクサイト：** ページやコンテンツを持たないサイトを作成します。

- **コミュニティーサイト：** 掲示板、検索、アンケート、最近のコンテンツ、Wiki、ナビゲーションなどのアプリケーションがサイトページに事前デプロイされた、設定済みのサイトを作成します。

- **社内ポータルサイト：** 社内ポータル用に事前設定されたサイトを作成します。 ホームページには、サイトメンバーのアクティビティ、言語選択機能、社内ポータルで作成された最近のコンテンツの一覧などが表示されます。 また、ドキュメントとメディアのページと、パブリックフィードを介して取得した外部ニュースのページも提供されます。

<a name="creating-a-site-from-a-site-template" />

## サイトテンプレートからサイトを作成する

サイトテンプレートを使用してサイトを作成するには、次の手順に従います。

1. [グローバルメニュー](../../getting-started/navigating-dxp.md) （ ![Global Menu icon](../../images/icon-applications-menu.png)）を開き、 ［**Control Panel**］ &rarr; ［**Sites**］ へ行きます。

    ![コントロールパネルの［サイト］オプションに移動。](./building-sites-with-site-templates/images/01.png)

1. **追加** アイコン (![Add Site](../../images/icon-add.png)) をクリックし、メニューから［サイトテンプレート］を選択します。

1. サイトの名前を入力します。

    ```{note}
    [*Enable propagation of changes from the Site template*]をオンにすると、サイトテンプレートが変更された場合にサイトが更新を受信できるようになります。 サイトテンプレートから作成されたサイトに直接変更が加えられた場合、そのサイトはサイトテンプレートから更新を受け取りません。 詳細については、[Merging Site Template Changes](./merging-site-template-changes.md)を参照してください。
    ```

1. ［**Save**］ をクリックします。

1. [サイトの設定](../site-settings/site-settings-ui-reference.md)を構成します。

    - Liferay DXP 7.4+の場合

      1. サイトメニューから、 ［**Configuration**］ &rarr; ［**Site Settings**］ に移動します。
      1. ［コンテンツとデータ］セクションで、 ［**Pages**］ をクリックします。
      1. サイトスコープで ［**Pages**］ をクリックします。

            ![Liferay DXP 7.4では、ページセクションからページの設定を変更します。](./building-sites-with-site-templates/images/03.png)

    - 以前のLiferay DXPバージョンの場合

      1. サイトメニューから、 ［**Configuration**］ &rarr; ［**Settings**］ に移動します。
      1. 一般エリアで、 ［**ページ**］ のセクションを展開します。

            ![Liferay DXPの旧バージョンでは、PagesセクションからPagesの設定を変更します。](./building-sites-with-site-templates/images/02.png)

    ```{note}
    *Enable propagation of changes from Site Template*は、サイトテンプレートが変更された場合に、サイトが更新情報を受け取ることを可能にします。 サイトテンプレートから作成されたサイトに直接変更が加えられた場合、そのサイトはサイトテンプレートから更新を受け取りません。 詳しくは[サイトテンプレートの変更をマージする](./merging-site-template-changes.md)をご覧ください。
    ```

1. ［**保存**］ をクリックしてサイトを作成します。

1. グローバルメニュー（ ![Global Menu icon](../../images/icon-applications-menu.png) ）を開き、 ［**コントロールパネル**］ のタブから ［**サイト**］ を開きます。

1. 新しいサイトの横にある **アクション** をクリックし、 ![アクションアイコン](../../images/icon-actions.png)［**公開ページへ移動**］ または ［**非公開ページへ移動**］ を選択して表示します。

    ```{tip}
    新しく作成した*ブランクサイト*を見るには、まずそのページを作成する必要があります。 詳しくは「サイトにページを追加する」(../creating-pages/adding-pages/adding-a-page-to-a-site.md)をご覧ください。
    ```

<a name="related-information" />

## 関連情報

- [サイト構築の概要](../introduction-to-site-building.md)
- [サイトテンプレートの作成](./building-sites-with-site-templates.md)
- [サイトにメンバーを追加する](./site-membership/adding-members-to-sites.md)
