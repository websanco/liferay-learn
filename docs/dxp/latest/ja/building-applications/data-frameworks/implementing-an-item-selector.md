# アイテムセレクターの実装

アイテムセレクターは、ドキュメント、ビデオ、ユーザーなどのアセットを選択するためのポップアップダイアログです。

アイテムセレクターの基準を構成し、その使用法を定義することにより、独自のアプリケーション用のアイテムセレクターダイアログを作成できます。

![ユーザーがアセットを選択できるように、アイテムセレクターがポップアップ表示されます。](./implementing-an-item-selector/images/01.png)

ここでは、アイテムセレクターを作成する方法を学習します。

## サンプルモジュールから始める

アイテムセレクターを実装するには、ウィジェット用のモジュールなど、アプリケーションに埋め込む必要があります。 この例では、JSPビューを含む[MVCポートレット](../developing-a-java-web-application/using-mvc/using-a-jsp-and-mvc-portlet.md)を使用しています。 アイテムセレクターには、選択するロールの一覧が表示されます。

1. サンプルモジュールをダウンロードします。

   ```bash
   curl https://learn.liferay.com/dxp/latest/en/building-applications/data-frameworks/liferay-f5d5.zip -O
   ```

   ```bash
   unzip liferay-f5d5.zip
   ```

1. 次のコマンドを使用して、Liferay DXP Dockerコンテナを起動します。

   ```bash
   docker run -it -m 8g -p 8080:8080 --name lrdev liferay/portal:7.3.2-ga3
   ```

1. モジュールのルートから次のコマンドを実行してビルドし、Dockerコンテナにデプロイします。

   ```bash
   ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

   ```{tip}
   このコマンドは、デプロイされたjarをDockerコンテナの`/opt/liferay/osgi/modules`にコピーするのと同じです。
   ```

1. Liferay Dockerコンテナコンソールでデプロイを確認します。

   ```bash
   STARTED com.acme.f5d5.web_1.0.0 [1017]
   ```

   サンプルのポートレットモジュールがデプロイされます。 これをページに追加すると、ボタンが1つある単純なポートレットになります。

   ![ポートレットには、アイテムセレクターを開くボタンが1つあります。](./implementing-an-item-selector/images/02.png)

1. _［Select］_ボタンをクリックすると、アイテムセレクターが表示されます。

   ![アイテムセレクターには、チェックボックスをオンにして選択できるアイテムが表示されます。](./implementing-an-item-selector/images/03.png)

1. アイテムを選択すると、そのアイテムの値がJavaScriptアラートボックスに表示されます。 このアイテムセレクターはロールを選択するため、値は選択されたロールのプライマリーキーとなります。

これで、アイテムセレクターがどのように機能するかを確認できます。

## コントローラーでアイテムセレクターの基準を設定する

`F5D5MVCPortlet.java`クラスを開きます。 MVCポートレットでは、ポートレットクラスはコントローラークラス（MVCではC）です。 クラスは以下の2つのことを実行する必要があります。

- セレクターに必要な基準を定義する（つまり、どのエンティティを選択するか）
- その基準のURLを作成する

1. クラスの最後に、OSGiは`@Reference`アノテーションのために[`ItemSelector`クラス](https://github.com/liferay/liferay-portal/blob/7.3.4-ga5/modules/apps/item-selector/item-selector-api/src/main/java/com/liferay/item/selector/ItemSelector.java)インスタンスを挿入します。

   ```java
   @Reference
   private ItemSelector _itemSelector;
   ```

1. ポートレットの`render`メソッドまでスクロールします。

1. アイテムセレクターに表示する目的のエンティティを表す基準クラスが作成されます。 基準クラスは、[`ItemSelectorCriterion`インターフェイス](http://docs.liferay.com/portal/7.3-latest/apps/item-selector-3.0.4/javadocs/com/liferay/item/selector/ItemSelectorCriterion.html)を実装する必要があります。

   この例では、[`RoleItemSelectorCriterion`](http://docs.liferay.com/portal/7.3-latest/apps/roles-3.0.4/javadocs/com/liferay/roles/item/selector/RoleItemSelectorCriterion.html)への参照を使用して、ロールがセレクターに表示されるようにします。 これは、クラスの新しいインスタンスを作成することによって定義されます。

   ```java
   ItemSelectorCriterion itemSelectorCriterion =
        new RoleItemSelectorCriterion();
   ```

   ```{tip}
   必要なエンティティのタイプに基準が存在しない場合は、[BaseItemSelectorCriterion]を拡張することで独自の`ItemSelectorCriterion`を定義できます。
   ```

1. 次に、ユーザーがエンティティを選択したときにエンティティが提供する情報を表す戻り値のタイプクラスが必要です。 戻り値のタイプクラスは、[`ItemSelectorReturnType`インターフェイス](http://docs.liferay.com/portal/7.3-latest/apps/item-selector-3.0.4/javadocs/com/liferay/item/selector/ItemSelectorReturnType.html)を実装する必要があります。  たとえば、戻り値のタイプクラスを使用して、エンティティのURL、UUID、またはプライマリーキーを返すことができます。 戻り値のタイプクラスは、以前に作成された基準クラスに追加されます。

   ```{important}
   使用されるすべての基準には、少なくとも1つの戻り値のタイプが関連付けられている必要があります。
   ```

   この例では、[`UUIDItemSelectorReturnType`](http://docs.liferay.com/portal/7.3-latest/apps/item-selector-3.0.4/javadocs/com/liferay/item/selector/criteria/UUIDItemSelectorReturnType.html)への参照を使用して、選択したロールの`UUID`値を、戻す重要なデータとして定義します。 複数のロールが選択されている場合、それらはコンマ区切りのリストとして返されます。

   ```{note}
   UUIDが使用できない場合は、プライマリーキーが返されます。
   ```

1. 戻り値のタイプをアイテム基準に登録して定義します。

   ```java
    itemSelectorCriterion.setDesiredItemSelectorReturnTypes(
        new UUIDItemSelectorReturnType());
   ```

   ```{tip}
   必要な情報のタイプの戻り値のタイプが存在しない場合は、独自の[ItemSelectorReturnType](https://github.com/liferay/liferay-portal/blob/7.3.4-ga5/modules/apps/item-selector/item-selector-api/src/main/java/com/liferay/item/selector/ItemSelectorReturnType.java)実装を定義できます。
   ```

   アイテムセレクターは、これら2つのクラスを使用して、表示するアイテムの選択ビュー（タブとして表示）と、各アイテムの識別方法を決定します。

1. これで、条件を使用してアイテムセレクターのURLを生成できます。 このURLは、フロントエンドコードにアイテムセレクターダイアログを作成します。

   [`RequestBackedPortletURLFactory`クラス](http://docs.liferay.com/portal/7.3-latest/javadocs/portal-kernel/com/liferay/portal/kernel/portlet/RequestBackedPortletURLFactory.html)は、次の基準を使用してアイテムセレクターのURLをすばやく生成できます。

   ```java
    PortletURL itemSelectorURL = _itemSelector.getItemSelectorURL(
        RequestBackedPortletURLFactoryUtil.create(renderRequest),
        renderResponse.getNamespace() + "selectRole",
        itemSelectorCriterion);
   ```

   ```{important}
   URLの生成に使用する文字列（この例では``selectRole``）は、ダイアログのイベント名です。 これは、後でフロントエンドコードでダイアログを作成するときに使用する値と一致する必要があります。
   ```

1. アイテムセレクターのURLを`renderRequest`に追加して、JSPで使用できるようにします。

   ```java
   renderRequest.setAttribute(F5D5WebKeys.ITEM_SELECTOR_URL, itemSelectorURL);
   ```

   `view.jsp`ファイルは、フロントエンドコードが定義されている場所です。 Javaクラスの`render`メソッドの`renderRequest`オブジェクトは、後でJSPファイルに渡されます。 定数を使用して、コントローラー（ポートレットクラス）とビュー（JSP）の両方でURLを一貫して識別するようにします。

1. 最後に、`MVCPortlet`の`render`メソッドを呼び出して、コードが実行されたらレンダリングプロセスを続行します。

   ```java
   super.render(renderRequest, renderResponse);
   ```

それがコントローラーコードです。 実行は、`view.jsp`ファイルに実装されているビューレイヤー（MVCではV）に渡されます。

## ビューでアイテムセレクターを使用する

1. アイテムセレクターを取得し、フロントエンドコードでそれを使用する方法を定義する必要があります。サンプルから`view.jsp`を開きます。

   ```jsp
   <%
      String itemSelectorURL = String.valueOf(request.getAttribute(F5D5WebKeys.ITEM_SELECTOR_URL));
   %>
   ```

   URLを取得したら、アイテムセレクターを開く方法を指定し、次に結果を使用する方法を定義する必要があります。

1. [Clayボタン](https://clayui.com/docs/components/button.html)タグを使用して、アイテムセレクターを開くためのボタンを作成できます。

   ```jsp
   <clay:button
     id='<%= liferayPortletResponse.getNamespace() + "selectRoleButton" %>'
     label="Select"
   />
   ```

   `clay:button`タグは、ウィジェット上にボタン（ID `selectRoleButton`とラベル*Select*が画面に表示されます）を作成します。 このボタンは、文字列`<portlet:namespace />selectRoleButton`で識別できます。

1. `<script>`タグを使用して、アイテムセレクターを開くJavaScriptを埋め込みます。

   ```jsp
   <script>
      var selectRoleButton = document.getElementById('<portlet:namespace />selectRoleButton');

      selectRoleButton.addEventListener(
         'click',
         function(event) {
            new Liferay.Util.openSelectionModal(
               {
                  onSelect: function (event) {
                     alert(event.value);
                  },
                  selectEventName: '<portlet:namespace />selectRole',
                  title: 'Select Role',
                  url: '<%= request.getAttribute(F5D5WebKeys.ITEM_SELECTOR_URL) %>'
               }
            );
         }
      );
   </script>
   ```

このJavaScriptのスニペットは、最初にその識別子（`portlet:namespace />selectRoleButton`）を介して［ロールの選択］ボタンを取得します。 次に、クリックされたときにアイテムセレクターダイアログを作成するためのイベントリスナーを追加します。

`Liferay.Util.openSelectionModal`メソッドは、ダイアログを作成します。

`onSelect`フィールドでは、クリックされたときに値を処理する関数を定義する必要があります。 ユーザーがこの関数内で選択を行ったときのダイアログの動作を定義します。 この実装は、選択された値を含むアラートボックスを表示します。

`eventName`フィールドの値は、Javaコードの`RequestBackedPortletURLFactory`で使用した文字列と一致する必要があります（この例では`selectRole`）。 コントローラーがアイテムを保存したリクエストからアイテムセレクターURLを取得し、同じ定数を使用してそれを識別し、`url`フィールドに入力する必要があります。

```{tip}
アイテムセレクターで複数アイテムの選択をサポートする場合は、`openSelectionModal`呼び出しに`multiple: true`を追加することで、複数選択を有効にできます。
```

`event`に保存されているアイテム選択を使用します。 結果に含まれるデータ型と情報は、Javaコードで使用した戻り値のタイプクラスによって異なります。 この例では`UUIDItemSelectorReturnType`を使用しているため、データは1つ以上の選択されたアイテムのUUIDを持つString値です。

選択関数内で、値の使用方法を実装します。 この例は、単純なJavaScriptアラートを示しています。

これでどのように機能するかを確認できたので、JavaScriptアラートをより便利なものに置き換えることができます。

## フォームを追加する

アイテムセレクターを使用する場合、選択した値をフォームに挿入したい場合があります。 その方法は以下の通りです。

1. `view.jsp`を開きます。

1. `<clay:button>`タグを探します。 次のようにフォームで囲みます。

   ```jsp
   <form name="<portlet:namespace/>fm">
      <input name="role" /> 
      <clay:button
         id='<%= liferayPortletResponse.getNamespace() + "selectRoleButton" %>'
         label="Select"
      />
   </form>
   ```

   `role`と呼ばれる1つの入力フィールドを持つフォームができました。

1. JavaScriptアラートまで下にスクロールします。 JavaScriptアラートをLiferayのフロントエンド`setFormValues`メソッドの呼び出しに置き換えます。

   ```jsp
   Liferay.Util.setFormValues(document.<portlet:namespace />fm, {
          role: event.value
   });
   ```

1. ポートレットを再デプロイします。

   ```bash
   ./gradlew deploy -Ddeploy.docker.container.id=$(docker ps -lq)
   ```

1. 先ほどと同じように、アイテムを選択します。 そのIDは、作成したフォーム項目に挿入されます。

## まとめ

　 これで、アイテムセレクターを実装する方法がわかりました。
