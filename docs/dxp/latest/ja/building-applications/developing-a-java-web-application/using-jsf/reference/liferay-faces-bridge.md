# Liferay Faces Bridge

Liferay Faces Bridgeを使用すると、ポートレット固有のコードを記述せずに、JSF Webアプリをポートレットとしてデプロイできます。 また、ポートレットアプリケーション内でJSF 2.xの機能を活用できるようにする革新的な機能も含まれています。

Liferay Faces Bridgeは、`.jar`ファイルで配布されます。 Liferay Portal 5.2、6.0、6.1、6.2、7.0などのJSR 286（Portlet 2.0）に準拠したポートレットコンテナ内にJSF Webアプリケーションをポートレットとしてデプロイするために、Liferay Faces Bridgeをポートレットプロジェクトへの依存関係として追加できます。

Liferay Faces Bridgeプロジェクトのホームページは[こちら](https://community.liferay.com/-/faces)にあります。

Liferay Faces Bridgeを完全に理解するには、最初にポートレットブリッジ規格を理解する必要があります。 Portlet 1.0とJSF 1.0の仕様は基本的に同時に作成されていたため、JSF仕様のExpert Group（EG）は、ポートレットに準拠するようにJSFフレームワークを構築しました。 たとえば、[ExternalContext.getRequest()](https://javaee.github.io/javaee-spec/javadocs/javax/faces/context/ExternalContext.html#getRequest--)メソッドは、[javax.servlet.http.HttpServletRequest](https://javaee.github.io/javaee-spec/javadocs/javax/servlet/http/HttpServletRequest.html)の代わりに`Object`を返します。 このメソッドをポータルで使用する場合、`Object`を[javax.portlet.PortletRequest](http://portals.apache.org/pluto/portlet-2.0-apidocs/javax/portlet/PortletRequest.html)にキャストできます。 JSFの設計においてEGはポートレットの互換性を意識していましたが、ポートレットとJSFのライフサイクルのギャップを埋める必要がありました。

ポートレットブリッジの規格と実装は、時間の経過とともに進化しました。

2004年以降、JSF開発者がJSF Webアプリをポートレットとしてデプロイできるようにするために、いくつかの異なるJSFポートレットブリッジの実装が開発されました。 2006年、JCPは、標準のブリッジAPIと、ブリッジ実装の詳細な要件を定義するために、Portlet Bridge 1.0（[JSR 301](http://www.jcp.org/en/jsr/detail?id=301)）EGを作成しました。 JSR 301は、Portlet 1.0およびJSF 1.2を対象として2010年にリリースされました。
1. 2.

2008年にPortlet 2.0（[JSR 286](http://www.jcp.org/en/jsr/detail?id=286)）規格がリリースされた際、JCPはPortlet Bridge 2.0（[JSR 329](http://www.jcp.org/en/jsr/detail?id=329)）EGを形成する必要がありました。 また、JSR
1. がPortlet 2.0とJSF 1.2を対象として2010年にリリースされました。

[JSR 314](http://www.jcp.org/en/jsr/detail?id=314) EGが2009年にJSF 2.0をリリースし、2010年にJSF 2.1をリリースした後、Portlet Bridge 3.0規格が有益であることが明らかになりました。 2015年、JCPはPortlet 3.0とJSF 2.2のブリッジを定義する[JSR 378](http://www.jcp.org/en/jsr/detail?id=378)を形成しました。 Portlet 2.0およびJSF 1.2/2.1/2.2をサポートする*Liferay Faces Bridge*のバリアントもあります。

Liferay Faces Bridgeは、ポートレットブリッジ規格のリファレンス実装（RI）です。 また、ポートレットアプリケーション内でJSF 2.xの機能を活用できるようにする革新的な機能も含まれています。

JSFポートレットブリッジは、JSFライフサイクルの正しいフェーズをポートレットライフサイクルの各フェーズに合わせます。 たとえば、ブラウザがJSFポートレットを含むポータルページにHTTP GETリクエストを送信する場合、`RENDER_PHASE`はポートレットのライフサイクルで実行されます。 次に、JSFポートレットブリッジは、JSFライフサイクルの`RESTORE_VIEW`フェーズと`RENDER_RESPONSE`フェーズを開始します。 同様に、HTTP POSTがポートレットで実行され、ポートレットが`ACTION_PHASE`に入ると、完全なJSFライフサイクルがブリッジによって開始されます。

![ポートレットライフサイクルのどのフェーズが実行されているかに応じて、JSFライフサイクルのさまざまなフェーズが実行されます。](./liferay-faces-bridge/images/01.png)


<!-- Neil stated the following about the JSF Lifecycle image above:

"In the following image, we talk about JSR 286 (Portlet 2.0), but once we're
done with Portlet 3.0 in Liferay 7.1 (very soon) and JSR 378 (Portlet 3.0 Bridge for JSF 2.2, not until the end of Q2 2019), we will need to change that to JSR
1. and also add the HEADER_PHASE."

We'll need to update the image once this is available. -Cody -->

2つのライフサイクルが正しく接続されていることを確認するだけでなく、JSFポートレットブリッジは、ポータルURLジェネレータとJSFナビゲーションルールの間のメディエーターとしても機能します。 JSFポートレットブリッジは、ポータルによって作成されたURLがJSFナビゲーションルールに準拠していることを確認し、JSFポートレットが別のビューに切り替えることができるようにします。

JSR 329/378規格では、`javax.portlet.faces`ネームスペースのプレフィックスが付いたいくつかの構成オプションが定義されています。 Liferay Faces Bridgeは、`com.liferay.faces.bridge`ネームスペースのプレフィックスが付いた追加の実装固有のオプションを定義します。

Liferay Faces Bridgeは、Liferay DXP/PortalのJSF開発プロセスの重要な部分です。 詳細については、[Developing a JSF Portlet Application \(Help Center\)](https://help.liferay.com/hc/en-us/articles/360029069451-Developing-a-JSF-Portlet-Application)を参照してください。

## 追加情報

* [Liferay Faces Alloy](./liferay-faces-alloy.md)
* [Liferay Faces Portal](./liferay-faces-portal.md)
* [Service Builder](../../../data-frameworks/service-builder.md)