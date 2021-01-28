# サイトテンプレートを使用したサイトの構築

サイトテンプレートは、サイトの事前構成された構造を定義します。これには、ページ、テーマ、コンテンツ、レイアウト、ページテンプレート、アプリケーション、および各ページに定義されたアプリの構成が含まれます。 サイトテンプレートに加えられた変更は、特に指定がない限り、それを使用するサイトに自動的に反映されます。

すぐに使用できる3つのサイトテンプレートが用意されています。

  - **空白のサイト：**ページやコンテンツを含まないサイトを作成します。

  - **コミュニティサイト：**メッセージボード、検索、ポール、アクティビティ、およびWikiアプリケーションがサイトページに事前にデプロイされた、事前設定されたサイトを作成します。

  - **イントラネットサイト：**イントラネット用に事前設定されたサイトを作成します。 ホームページには、サイトのメンバーのアクティビティ、検索、言語セレクター、およびイントラネットで作成された最近のコンテンツのリストが表示されます。 また、ドキュメントとメディアのページと、パブリックフィードを介して取得した外部ニュースのページも提供されます。

## サイトテンプレートからサイトを作成する

サイトテンプレートを使用してサイトを作成するには、次の手順に従います。

1.  製品メニューを開き、 *[Control Panel]* → *[Sites]* → *[Sites]* に移動します。

    ![コントロールパネルの[Sites]オプションに移動。](./building-sites-with-site-templates/images/03.png)

2.  *追加*アイコン（![Add Site](../../images/icon-add.png)）をクリックし、メニューからサイトテンプレートを選択します。

3.  サイトの名前を入力します。

      - *[Create default pages as private (available only to members)]* をオンにすると、ゲストユーザーはサイトページを表示できなくなります。

4.  *[保存]* をクリックします。

5.  [サイト設定](../06-site-settings/README.md)を構成します。

6.  フォームの下の*[Pages]* パネルを開きます。

    ![サイト構成ページのドロップダウンを展開し、サイトテンプレートのオプションを表示。](building-sites-with-site-templates/images/04.png)

    ``` note::
       [*Enable propagation of changes from the Site template*]をオンにすると、サイトテンプレートが変更された場合にサイトが更新を受信できるようになります。 サイトテンプレートから作成されたサイトに直接変更が加えられた場合、そのサイトはサイトテンプレートから更新を受け取りません。 詳細については、`Merging Site Template Changes <./merging-site-template-changes.md>`を参照してください。
    ```

7.  *[保存]* をクリックしてサイトを作成します。

8.  製品メニューを開き、コントロールパネルの*[Sites]* → *[Sites]* に移動します。

9.  *[Actions]* をクリックし、新しいサイトの横にある*[Go to Public Pages]* または*[Go to Private Pages]* を選択して表示します。

    ``` tip::
       To view a newly created *blank site*, you must first create a page for it. See `Adding a Page to a Site <../creating-pages/adding-pages/adding-a-page-to-a-site.md>`_ for more information.
    ```

## 追加情報

  - [Introduction to Site Building](../introduction-to-site-building.md)
  - Creating a Site Template
  - [Adding Members to Sites](./adding-members-to-sites.md)
