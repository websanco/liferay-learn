# ナビゲーションメニューの使用

デフォルトでは、サイトは *ナビゲーションメニュー*ウィジェットから開始し、サイトの[公開ページ](../creating-pages/understanding-pages/understanding-pages.md)階層を表示するように設定されています。 場合によっては、デフォルト設定では不十分な場合に、サイトのナビゲーションメニューを定義および表示する方法をより直接的に制御したり柔軟にすることができます。 このような場合には、新しいナビゲーションメニューを作成および設定できます。

ナビゲーションメニューの使用を開始するには、次の操作を行います。

1.  [ナビゲーションメニューの作成](#creating-navigation-menus)
2.  [既存ページ](#assigning-pages-to-a-navigation-menu)、リンクの追加、サブメニューなどの項目をメニューに追加する
3.  オプションで、サイトで特定の機能を提供するように[ナビゲーションメニュー](#marking-a-navigation-menu)にマークを付ける
4.  ナビゲーションメニューウィジェット（まだ存在しない場合）をページに追加して[設定する](./configuring-navigation-menus.md)

たとえば、ナビゲーションメニューには、特定のページのすべての子ページを含めることができます。 親ページのナビゲーションメニューウィジェットを使用してメニューを表示できます。 これにより、選択した子ページのみを表示するナビゲーションが作成され、事実上ミニサイトが作成されます。

![ナビゲーションメニューウィジェットは、必要なページを表示するように設定できます。](./using-navigation-menus/images/09.png)

```{tip}
In DXP 7.3+, you can use Custom Fields to add more information to each item in your navigation menus (under `Site Navigation Menu Item` in the `Custom Fields` menu). You can then use Application Display Templates to use this information to further customize your navigation menus.
```

<!-- Add links to this annotation for Custom Fields and ADTs when available. -->

## ナビゲーションメニューの作成

1.  *[プロダクトメニュー]* (![Product Menu](../../images/icon-product-menu.png)) → *[サイトビルダー]* → *[Navigation Menus]* をクリックします。

2.  *追加*ボタン（![Add Page](../../images/icon-add.png)）をクリックして新しいメニューを追加します。

3.  名前を入力します（例： *New Menu*）。

    ![新しいナビゲーションメニューを作成します。](./using-navigation-menus/images/01.png)

4.  完了したら、*[保存]* をクリックします。

新しいナビゲーションメニューが作成されました。

## ナビゲーションメニューへのページの割り当て

既存のページをナビゲーションメニューに割り当てる主な方法は2つあり、*[New]* ボタンを追加するか、*追加*ボタン（![Add Page](../../images/icon-add.png)）をクリックします。

1.  *[New]* → *[Page]* の順にクリックします。

    ![新しいナビゲーションメニューを作成します。](./using-navigation-menus/images/02.png)

2.  このナビゲーションメニューに含めるページを選択します。この例では、非公開ページの3つの子ページを含めます。

    ![ナビゲーションメニューに含めるページを選択します。](./using-navigation-menus/images/03.png)

3.  完了したら、*[Add]* をクリックします。

4.  新しいナビゲーションメニューが作成されたことを確認します。

    ![新しいナビゲーションメニューに子ページがあることを確認します。](./using-navigation-menus/images/04.png)

### ナビゲーションメニューへの新しいページの割り当て

ナビゲーションメニューを作成する前に[ページを作成](../creating-pages/adding-pages/adding-a-page-to-a-site.md)する必要はありません。 最初にナビゲーションメニューを作成してから、後でページを作成するときにページを割り当てることができます。

![ページは既存のナビゲーションメニューに自動的に追加できます。](./using-navigation-menus/images/06.png)

ナビゲーションメニューに新しいページを自動的に追加する機能を無効にするには、

1.  目的の*ナビゲーションメニュー*（例：*New Menu*）の横にある（![Options](../../images/icon-options.png)）アイコンをクリックし、*[Edit]* をクリックします。

2.  （![Gears](../../images/icon-control-menu-gear.png)）アイコンをクリックします。

3.  チェックボックスをオフにします。

    ![このナビゲーションメニューにページが自動的に追加される設定を無効にします。](./using-navigation-menus/images/05.png)

4.  完了したら、*[保存]* をクリックします。

無効にすると、ページをナビゲーションメニューに追加するように求められることはありません。

![このナビゲーションメニューにページが自動的に追加される設定を無効にします。](./using-navigation-menus/images/08.png)

## ナビゲーションメニューのマーク

ナビゲーションメニューには、次の方法でマークを付けることができます。

| ナビゲーションメニューのタイプ    | 使用法                                                               |
| ------------------ | ----------------------------------------------------------------- |
| **プライマリ ナビゲーション**  | プライマリ ナビゲーションは、ページのメインナビゲーションです。                                  |
| **セカンダリー・ナビゲーション** | セカンダリー・ナビゲーションは、ナビゲーションの第2レベルであり、ページ内のサイドバーまたは個別のメニューが考えられます。     |
| **ソーシャルナビゲーション**   | ソーシャルナビゲーションは、ソーシャルメディアまたは同様のタスクでコンテンツを共有するためのリンクを含むメニューのためのものです。 |

1.  目的のナビゲーションメニュー（例：*New Menu*）の横にある（![Options](../../images/icon-options.png)）アイコンをクリックします。

2.  目的のナビゲーション（たとえば、 *プライマリナビゲーション*）をクリックします。

    ![ナビゲーションメニューをプライマリとしてマークします。](./using-navigation-menus/images/07.png)

ナビゲーションメニューのタイプが*[以下として設定する：]* フィールドに表示されます。 各タイプの詳細については、[Configuring Navigation Menus](./configuring-navigation-menus.md#navigation-menu)を参照してください。

## ナビゲーションメニューの削除

1.  目的のナビゲーションメニュー（例：*New Menu*）の横にある（![Options](../../images/icon-options.png)）アイコンをクリックし、*[Delete]* をクリックします。
2.  確認ウィンドウで*[OK]* をクリックします。

ナビゲーションメニューが削除されました。

## 追加情報

  - [Managing Site Navigation](./managing-site-navigation.md)
  - [Configuring Navigation Menus](./configuring-navigation-menus.md)
