# ウィジェットテンプレートを使用したウィジェットのスタイル設定

ウィジェットテンプレートは、さまざまな既存のウィジェットの外観と機能をカスタマイズするために使用されます。 ウィジェットテンプレートを作成すると、スクリプトを使用してウィジェットの外観と機能を適合させることができます。 *Asset Publisher* や *Media Gallery* ウィジェットなど、Liferayですぐに使用できる多くのウィジェットのウィジェットテンプレートを作成できます。

## ウィジェットテンプレートの作成

次の手順に従って、ウィジェットテンプレートを作成します。

1.  製品メニューから、 *Site Builder* → *Widget Templates*をクリックします。

    ![ウィジェットテンプレートページ。](./styling-widgets-with-widget-templates/images/01.png)

2.  *追加* （![Add icon](../../../images/icon-add.png)）ボタンをクリックし、ウィジェットテンプレートを作成するウィジェットを選択します（例： *アセットパブリッシャー*）。

    ![ウィジェットテンプレート作成ページ。](./styling-widgets-with-widget-templates/images/02.png)

3.  ウィジェットテンプレートの名前を追加します。

4.  *Script* セクションの下で、テンプレートエディターの本文をクリックします。

5.  *フィールド* メニューのフィールドのひとつをクリックして、そのフィールドをエディターに挿入します。 ウィジェットテンプレートを使用すると、フィールドの値が表示されます。

    ``` note::
       Clicking any of the fields in the menu will insert the field name wherever the cursor is. You can also begin typing a field name yourself (starting with "${") to show suggestions for auto-completion.
    ```

6.  [ *保存* ]をクリックして、テンプレートを完成させます。

## ウィジェットテンプレートの適用

いずれかのページでウィジェット用のウィジェットテンプレートの準備ができたら、次の手順に従います。

1.  適切なウィジェットのアクション（![Actions icon](../../../images/icon-actions.png)）アイコンをクリックし、[ *構成]をクリックします。*

2.  [テンプレートの表示]ドロップダウンメニューを見つけ、目的のテンプレートの名前を選択します。

    ![Asset Publisher構成の[表示テンプレート]ドロップダウンボックス。](./styling-widgets-with-widget-templates/images/03.png)

    ``` note::
       This drop-down menu is on the default tab, Setup, for most widgets. For the Asset Publisher widget, this setting is found under the Display Settings tab.
    ```

3.  [ *保存*]をクリックします。
    
     <!-- screenshot -->

ウィジェットは、カスタムの外観を使用するように変更されています。 より高度なウィジェットテンプレートスクリプトを使用することにより、ウィジェットの機能をさらに強化できます。
