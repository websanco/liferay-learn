# 新しいアナリティクスサービスの追加

Liferay DXPには、サイトのトラフィックを分析するためのGoogle AnalyticsとPiwikのサポートが含まれています。 別のアナリティクスサービスが必要な場合は、サイトに追加できます。 次の手順に従って、新しいアナリティクスサービスを有効にします。

## 新しいアナリティクスサービスの追加

1.  [グローバルメニュー](../../getting-started/navigating-dxp.md) ( ![Global Menu icon](../../images/icon-applications-menu.png) ) を開き、 *[コントロールパネル]* → *[Instance Settings]* に移動します。

2.  *[プラットフォーム]* 見出しで*[アナリティクス]* を選択します。

3.  追加したいサービスの名前を*[アナリティクス]* フィールドに入力します。

    ![[Instance Settings]から追加のアナリティクスサービスを入力できます。](./adding-a-new-analytics-service/images/01.png)

## アナリティクスサービスの追跡コードを追加する

1.  名前を入力したら、サイトメニューを開き、アナリティクスを追加するサイトの*[設定]* → *[Settings]* → *[詳細設定]* → *[アナリティクス]* ページに移動します。

    ```{note}
    In Liferay DXP 7.1 and 7.2, instead navigate to *Configuration* → *Site Settings* → *Advanced* → *Analytics* in the Site Menu.
    ```

2.  アナリティクスプラットフォームによって提供されるJavaScript追跡コードをサービスの対応するフィールドにコピーします。

    ![新しいアナリティクスサービスは、サイトの詳細設定の下に表示されます。](./adding-a-new-analytics-service/images/02.png)

これで、選択したサイトのすべてのページに追跡スクリプトが含まれ、アナリティクスデータがアナリティクスプラットフォームに送信されます。
