# サイトのドキュメントコンテンツの保護

デフォルトでは、サイトのドキュメントとメディアのファイルとフォルダはWebサーバーのディレクトリインデックスに表示されないため、ブラウザから表示することはできません。 Webサーバーのディレクトリインデックスを介してサイトのドキュメントを閲覧できるようにするには、サイトでこの設定を有効にします。

有効にすると、サイトのドキュメントとメディアのファイルは、 `http://localhost:8080/documents/site-name` (例： *Marketing*というサイトの場合は`http://localhost:8080/documents/marketing` ) で閲覧できます。

## Webサーバーディレクトリインデックスでドキュメントの表示を設定する

1. ［Enable Directory Indexing］オプションにアクセスします。

    - Liferay DXP 7.4+の場合

      1. サイトのメニューから、*［Configuration］* &rarr; *［Site Settings］*に移動します。
      1. ［コンテンツとデータ］セクションで、*［Documents and Media］*をクリックします。

       ![Liferay DXP 7.4以降では、［サイト設定］セクションから［ディレクトリのインデックスを有効にする］オプションを変更します。](./securing-site-documents-content/images/02.png)

   - 以前のLiferay DXPバージョンの場合

      1. サイトメニューから、*［Configuration］* &rarr; *［Settings］*に移動します。
      1. ［一般］エリアで、*［Documents and Media］*セクションを展開します。

       ![以前のLiferay DXPバージョンでは、［Settings］セクションから［ディレクトリのインデックスを有効にする］オプションを変更します。](./securing-site-documents-content/images/01.png)

1. *［ Enable Directory Indexing］*設定を切り替えて、この設定を有効または無効にします。

    ```{important}
    有効にすると、表示権限を持つ管理者とユーザーのみが、Webサーバーのディレクトリインデックスにあるサイトのドキュメントとメディアファイルを閲覧できます。
    ```

1. *［保存］* をクリックします。

## 関連情報

- [Managing Content Sharing Globally](./managing-content-sharing-across-sites.md)
- [サイト設定UIリファレンス](../site-settings-ui-reference.md)
