# サイトのドキュメントコンテンツの保護

デフォルトでは、サイトのドキュメントとメディアのファイルとフォルダはWebサーバーのディレクトリインデックスに表示されないため、ブラウザから表示することはできません。 Webサーバーのディレクトリインデックスを介してサイトのドキュメントを閲覧できるようにするには、サイトでこの設定を有効にします。

## Webサーバーディレクトリインデックスでドキュメントの表示を設定する

1.  プロダクトメニューを開き、*[設定]* → *[Settings]* （以前は*[サイト設定]*）に移動します。

2.  *[一般]* タブを選択し、*[ドキュメントとメディア]* 小見出しまで下にスクロールして展開します。

3.  *[ディレクトリのインデックスを有効にする]* の設定を*[YES]* と*[NO]* の間で切り替えて、機能を有効/無効にします。

    ![サイトの一般設定を使用して、サイトのドキュメントとメディアを保護できます。](./securing-site-documents-content/images/01.png)

    ```{important}
    Once enabled, only Administrators and Users with view permission can browse your Site's Documents and Media files in the web server directory index.
    ```

4.  下にスクロールして、*[保存]* をクリックして変更を適用します。

有効にすると、サイトのドキュメントとメディアのファイルは、 `http://localhost:8080/documents/site-name` (例： *Marketing*というサイトの場合は`http://localhost:8080/documents/marketing` ) で閲覧できます。
