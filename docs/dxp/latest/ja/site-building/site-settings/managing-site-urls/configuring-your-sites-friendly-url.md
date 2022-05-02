# サイトのフレンドリURLを設定する

フレンドリURLは、公開ページと非公開ページの両方に使用されます。 公開サイトのベースURLは`https://localhost:8080/web`であり、非公開ページのベースURLは`https://localhost:8080/group`です。 これにより、ユーザーは長いURLを思い出さなくても、サイトにすばやくアクセスできます。 フレンドリURLはそれぞれ一意である必要があります。 URLパスは、`https://localhost:8080/`のURLに追加されます（例： `https://localhost:8080/web/my-site/`）。

<a name="setting-a-sites-friendly-url" />

## サイトのフレンドリURLを設定する

サイトのフレンドリURLを更新するには、次の手順に従います。

1. 画面左側のサイトメニューから、 ［**設定**］ &rarr; ［**Settings**］ に移動します。

1. 下にスクロールし、 ［**Site URL**］ パネルを展開します。

1. ［**Friendly URL**］ 入力欄に新しいURLを入力し、 ［**保存**］ をクリックして変更を適用します。

![サイトのフレンドリURLを設定できます。](./configuring-your-sites-friendly-url/images/01.png)

サイトのホームページにフレンドリURLを追加する場合は、次のセクションの追加の手順に従ってください。

<a name="updating-your-instances-home-url" />

## インスタンスのホームURLを更新する

インスタンスのホーム ページにフレンドリなURLを追加した場合は、インスタンスの［Home URL］フィールドを更新して、`http://localhost:8080`へのページリクエストが適切にリダイレクトされるようにする必要があります。 <!-- What is a Home URL? Do I HAVE to do this? What does it impact? -->

1. [グローバルメニュー](../../../getting-started/navigating-dxp.md) ( ![Global Menu icon](../../../images/icon-applications-menu.png) ) を開き、 ［**コントロールパネル**］ &rarr; ［**Instance Settings**］ に移動します。

1. ［**プラットフォーム**］ 見出しの下で ［**Instance Configuration**］ を選択し、 ［**General**］ リンクをクリックします。

1. ［**Navigation**］ の下で、 ［**Home URL**］ フィールドに新しいフレンドリなホームURLを入力します。 たとえば、デフォルトのサイトのフレンドリURLを`/my-site`に設定すると、サイトの公開ホームページのURLは`https://localhost:8080/web/my-site/home`になります。 つまり、`/web/my-site/home`と入力します。

![更新されたホームURLを入力すると、新しいフレンドリURLにリダイレクトされます。](./configuring-your-sites-friendly-url/images/02.png)

この設定を行うと、`localhost:8080`へのページリクエストは、Liferay DXPインスタンスの新しいホームページのフレンドリURLにリダイレクトされます。
