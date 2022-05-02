# バーチャルホストのサイトURLの設定

バーチャルホストは、ドメイン名（つまり、`www.my-site.com`）をサイトに接続します。 これは、完全なドメインまたはサブドメイン（たとえば、`developers.my-site.com`）にできます。 これを使用して、1つのLiferay DXPサーバー上で複数のWebサイトを個別のサイトとしてホストできます。 次の手順に従って、バーチャルホストのサイトURLを設定します。

1. プロバイダーで、Liferay DXPインスタンスのIPアドレスを指すようにDNS名を設定します（例：`developers.my-site.com`）。 <!-- I think we can take this .5 step further by either linking to a often cited resource on how on a common domain name provider, a user would update the DNS name to point to a particular IP address. -->
1. Liferay DXP内で、プロダクトメニューを開き、サイトメニューの*［Configuration］* &rarr; *［Settings］*に移動します。
1. ［一般］タブで、［Site URL］パネルを展開します。

    ![バーチャルホストを設定する場合、サイトの公開ページと非公開ページを異なるドメインに設定できます。](./configuring-virtual-hosts-site-urls/images/01.png)

1. 開発者サイトの公開ページまたは非公開ページの入力で、ドメインまたはサブドメインを指すようにURLを設定します（例： `http://developers.my-site.com`）。

    ```{note}
    **Liferay 7.3 GA2以降**、オプションで*言語*セレクターからサイトのデフォルトロケールを選択できます。 たとえば、サイトの翻訳ごとにドメインがある場合（たとえば、`unchien.ca`と`adog.ca`）、各ドメインの公開ページと非公開ページにバーチャルホストを追加し、それぞれのデフォルトロケールを指定できます。
    ```

      ![バーチャルホストを特定のロケールに指定できます。](./configuring-virtual-hosts-site-urls/images/02.png)

    ```{note}
    ロケールは、サイトで使用可能な言語に制限されます（定義されている場合）。 ロケールが指定されていない場合は、サイトのデフォルトのロケールが使用されます。 ロケールは、関連付けられているすべてのドメインのサイトに適用されます。
    ```

1. **Liferay 7.3 GA2以降**では、複数のドメインを持っている場合、公開ページや非公開ページのURL入力の横にある`+`/`-`ボタンをクリックすると、そのページセットに新しいURLを追加したり、URLを削除したりできます。

    ![複数のバーチャルホストを追加して、異なるドメインを指すようにすることができます。](./configuring-virtual-hosts-site-urls/images/03.png)

    ```{important}
    ドメインは単一のサイトにのみ適用できます。 1つのドメインを複数のサイトにマッピングすることはできません。
    ```

1. 変更を*保存*します。

これで、設定済みのドメインを使用してサイトにアクセスできるようになりました。
