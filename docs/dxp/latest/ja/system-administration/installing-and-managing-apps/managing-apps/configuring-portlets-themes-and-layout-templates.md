# ポートレット、テーマ、およびレイアウトテンプレートの構成

コンポーネントリストは、[ポートレット](https://help.liferay.com/hc/articles/360029046351-Introduction-to-Portlets)、[テーマ](https://help.liferay.com/hc/articles/360035581011-UI-Architecture#themes)、および[レイアウトテンプレート](https://help.liferay.com/hc/articles/360028726612-Layout-Templates)を設定する最も簡単な方法です。 コンポーネントUIで、それらを有効/無効にしたり、それらの権限を設定したりできます。

1.  コントロールパネルの*[Configuration]* → *[Components]* に移動して、コンポーネントUIにアクセスします。 インストールされているポートレットの表が表示されます。

    ![コンポーネントUIでは、DXPインスタンスにインストールされているポートレット、テーマ、およびレイアウトテンプレートを管理できます。](./configuring-portlets-themes-and-layout-templates/images/01.png)

2.  表示するコンポーネントタイプのタブを選択します （*[Portlets]*、*[Themes]*、または*[Layout Templates]*）。

3.  コンポーネントを構成するには、その名前をクリックするか、そのアクションボタン（![Actions](./configuring-portlets-themes-and-layout-templates/images/02.png)）から*[Edit]* を選択します。 コンポーネントの構成画面が表示され、コンポーネントのモジュールIDとプラグインID、コンポーネントをアクティブ化/非アクティブ化するためのチェックボックス、およびコンポーネントに設定できるユーザーロールが表示されます。

    ![コンポーネントをアクティブまたは非アクティブにして、その権限を変更できます。](./configuring-portlets-themes-and-layout-templates/images/03.png)

4.  *[Active]* チェックボックスをそれぞれオンまたはオフにして、コンポーネントをアクティブまたは非アクティブにします。

5.  コンポーネントに対するロールの権限（たとえば、ポートレットに対するロールの*[Add to Page]* 権限）を変更するには、権限テーブルでロールの*[Change]* ボタンを選択します。 これにより、コントロールパネル → *[Users]* → *[Roles]* 画面が表示され、コンポーネントに対するロールの権限を変更できます。

コンポーネントリストを使用して、インストールされているポートレット、テーマ、およびレイアウトテンプレートを調査および管理できます。

## 追加情報

  - [Maintaining Clustered Installations](../../../installation-and-upgrades/maintaining-a-liferay-dxp-installation/maintaining-clustered-installations/maintaining-clustered-installations.md)
  - [アプリをブラックリストにする](./blacklisting-apps.md)
