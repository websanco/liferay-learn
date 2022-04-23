# Liferay Faces Portal

**Liferay Faces Portal** は`.jar`ファイルで配布されます。 ポートレットプロジェクトの依存関係としてLiferay Faces Portalを追加して、Liferay固有のユーティリティとUIコンポーネントを使用できます。 Liferay Faces PortalがJSFポートレットプロジェクトに含まれている場合、`com.liferay.faces.portal.[version].jar`ファイルはポートレットのライブラリに存在します。

![構成したJSF UI Component Suiteに基づいて、JSFポートレットに必要な<code>.jar</code>ファイルがダウンロードされます。](./liferay-faces-portal/images/01.png)

Liferay Faces Portalに含まれる機能の一部は次のとおりです。

* ユーティリティ：さまざまなポートレットAPIおよびLiferay固有の便利なメソッドを含む`LiferayPortletHelperUtil`を提供します。

* JSFコンポーネント：一般的なLiferay DXP JSPタグに相当するJSFのセットを提供します（すべてを網羅しているわけではありません）。
    * `liferay-ui:captcha` &rarr; `portal:captcha`
    * `liferay-ui:input-editor` &rarr; `portal:inputRichText`
    * `liferay-ui:search` &rarr; `portal:inputSearch`
    * `liferay-ui:header` &rarr; `portal:header`
    * `aui:nav` &rarr; `portal:nav`
    * `aui:nav-item` &rarr; `portal:navItem`
    * `aui:nav-bar` &rarr; `portal:navBar`
    * `liferay-security:permissionsURL` &rarr; `portal:permissionsURL`
    * `liferay-portlet:runtime` &rarr; `portal:runtime`

    詳細については、 [https://liferayfaces.org/web/guest/portal-showcase](https://liferayfaces.org/web/guest/portal-showcase) をご覧ください。

* 式言語：Liferay固有の情報を取得するための`liferay`や、Liferayのデフォルトの国際化されたメッセージと統合するための`i18n`など、ELキーワードのセットを追加します。

## 追加情報

* [Developing a JSF Portlet Application \(Help Center\)](https://help.liferay.com/hc/ja/articles/360029069451-Developing-a-JSF-Portlet-Application)
* [Liferay Faces Bridge](./liferay-faces-bridge.md)
* [Liferay Faces Alloy](./liferay-faces-alloy.md)