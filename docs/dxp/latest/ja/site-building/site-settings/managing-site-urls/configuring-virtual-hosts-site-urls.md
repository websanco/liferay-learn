# バーチャルホストのサイトURLの設定

バーチャルホストは、ドメイン名（つまり、`www.my-site.com`）をサイトに接続します。 これは、完全なドメインまたはサブドメイン（たとえば、`developers.my-site.com`）にできます。 これを使用して、1つのLiferay DXPサーバー上で複数のWebサイトを個別のサイトとしてホストできます。 次の手順に従って、バーチャルホストのサイトURLを設定します。

1.  プロバイダーで、Liferay DXPインスタンスのIPアドレスを指すようにDNS名を設定します（例：`developers.my-site.com`）。 <!-- I think we can take this .5 step further by either linking to a often cited resource on how on a common domain name provider, a user would update the DNS name to point to a particular IP address. -->

2.  Liferay DXP内で、プロダクトメニューを開き、サイトメニューの*[設定]* → *[Settings]* に移動します。

3.  [一般]タブで、[サイトURL]パネルを展開します。

    ![バーチャルホストを設定する場合、サイトの公開ページと非公開ページを異なるドメインに設定できます。](./configuring-virtual-hosts-site-urls/images/01.png)

4.  開発者サイトの公開ページまたは非公開ページの入力で、ドメインまたはサブドメインを指すようにURLを設定します（例： `http://developers.my-site.com`）。

    ```{note}
    **Since Liferay 7.3 GA2**, optionally choose a default locale for the Site from the *Language* selector. For instance, if you have a domain for each translation of a Site (e.g. `unchien.ca` and `adog.ca`), you can add a virtual host for the public and private pages of each domain and specify the default locale for each.
    ```

    ![バーチャルホストを特定のロケールに指定できます。](./configuring-virtual-hosts-site-urls/images/02.png)

    ```{note}
    Locales are constrained to the Site's available languages, if defined. If a locale isn't specified, the default locale for the Site is used. The locale applies to Sites for all associated domains.
    ```

5.  **Liferay 7.3 GA2以降**では、複数のドメインを持っている場合、公開ページや非公開ページのURL入力の横にある`+`/`-`ボタンをクリックすると、そのページセットに新しいURLを追加したり、URLを削除したりできます。

    ![複数のバーチャルホストを追加して、異なるドメインを指すようにすることができます。](./configuring-virtual-hosts-site-urls/images/03.png)

    ```{important}
    A domain can only be applied to a single Site. You can't map a single domain to multiple Sites.
    ```

6.  変更を*保存*します。

これで、設定済みのドメインを使用してサイトにアクセスできるようになりました。
