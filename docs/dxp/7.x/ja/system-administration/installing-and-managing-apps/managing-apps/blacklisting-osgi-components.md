# OSGiコンポーネントのブラックリスト登録

コンポーネントブラックリストは、複数の[OSGi宣言型サービスコンポーネント](https://help.liferay.com/hc/articles/360028846452-Declarative-Services)（コンポーネント）を管理する便利な方法です。 リストは、DXPがコンポーネントを無効にするために使用する[OSGi構成](../../system-settings/using-configuration-files.md#creating-configuration-files)です。 これらのリストを使用すると、[アプリケーションマネージャー](./using-the-app-manager.md)や[Gogoシェル](https://help.liferay.com/hc/articles/360029070351-Using-the-Felix-Gogo-Shell)で個別に変更する手間が省けます。

ブラックリストは、コントロールパネルからOSGi構成（`.config`）ファイルにエクスポートできます。 ファイルを変更してDXPにデプロイすると、次のような追加の効果があります。

  - DXPサーバーの起動時の変更を保持します
  - ローカルクラスターノードから他のすべてのノードに変更を伝播します。

UIと構成ファイルを使用してコンポーネントをブラックリストに登録する方法を示します。

## コンポーネントのブラックリスト登録

OSGiコンポーネントを無効にするには、次の手順に従います。

1.  コントロールパネルで、*[Configuration]* → *[System Settings]* → *[Module Container]* に移動します。 [Component Blacklist]画面が表示されます。

2.  [Component Blacklist]画面で、無効にするコンポーネントの名前を追加し、*[保存]* ボタンをクリックします。 コンポーネントはすぐに無効になります。

    ![このブラックリストは、コンポーネントcom.liferay.portal.security.ldap.internal.authenticator.LDAPAuthおよびcom.liferay.ip.geocoder.sample.web.internal.portlet.IPGeocoderSamplePortletを無効にします。](./blacklisting-osgi-components/images/01.png)

3.  ブラックリストをエクスポートするには、コンポーネントブラックリストモジュールのアクションボタン（![アクション](./blacklisting-osgi-components/images/02.png)) 、 *エクスポート* をクリックします。 次に、ブラックリスト構成ファイルがダウンロードされます（`com.liferay.portal.component.blacklist.internal．ComponentBlacklistConfiguration.config`を開きます。 サンプルのリストから作成されたファイルの内容は次のとおりです。

    ``` properties
    blacklistComponentNames=["com.liferay.portal.security.ldap.internal.authenticator.LDAPAuth","com.liferay.ip.geocoder.sample.web.internal.portlet.IPGeocoderSamplePortlet "]
    ```

4.  まだリストにない有効にしたくないコンポーネント（たとえば、まだインストールされていないモジュールのコンポーネント）の名前を追加します。

    ``` important::
       構成値に余分なスペースを含めることはできません。 余分なスペースがあると、リストが短絡したり、構成エントリが無効になったりする可能性があります。
    ```

5.  構成ファイルをデプロイするには、フォルダ`[Liferay Home]/osgi/configs`にコピーします。 Liferay Homeフォルダは通常、アプリケーションサーバーの親フォルダです。

## ブラックリストに登録されたコンポーネントを再度有効にする

ブラックリストに登録されたOSGiコンポーネントの再有効化と有効化を許可するには、次の手順に従います。

1.  構成ファイル`[Liferay Home]/osgi/configs/com.liferay.portal.component.blacklist.internal.ComponentBlacklistConfiguration.config`を開きます。

2.  `blacklistComponentNames`リストからコンポーネントの名前を削除し、ファイルを保存します。

ブラックリストに登録されている*すべての*コンポーネントを有効にするには、構成ファイルを削除します。

``` note::
   ブラックリストに登録されているコンポーネントを一時的に再度有効にするには、システム設定でコンポーネントブラックリスト設定モジュールからその名前を削除し、[*Update*]をクリックします。 If you're using a component blacklist config file (in the ``[Liferay Home]/osgi/configs`` folder) and want the component to enable on subsequent server startup, make sure to remove the component's name from the file.
```

これで、単純なリストを使用して複数のコンポーネントを管理できるようになりました。

## 追加情報

  - [Blacklisting Apps](./blacklisting-apps.md)
  - [アプリの管理](./using-the-app-manager.md)
  - [Felix Gogoシェルを使用](https://help.liferay.com/hc/articles/360029070351-Using-the-Felix-Gogo-Shell)
