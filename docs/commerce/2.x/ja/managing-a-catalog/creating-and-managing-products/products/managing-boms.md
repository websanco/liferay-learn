# BOMの管理

> サブスクライバー

部品表（BOM）図は、商品に属する構成部品を識別します。 ユーザーは、ストア内の商品を参照するLiferay CommerceでBOM図を作成して注釈を付けることができます。 その後、BOMダイアグラムをサイトページに表示できます。

BOMダイアグラムは、ダイアグラムとマップされた商品の2つの部分で構成されます。

## 新しいBOM図の追加

新しいBOM図を追加するには：

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  *BOM*をクリックします 。

    ![BOM管理メニュー](./managing-boms/images/01.png)

3.  [追加]（![Add Icon](../../../images/icon-add.png)）をクリックし、[ *[定義を追加]*クリックします。

4.  *クリックしてファイル* 選択するか、商品画像をドラッグアンドドロップします。

5.  商品名（汎用ディーゼルエンジン）を入力します。

6.  *[Save]*をクリックします。

## 商品をBOMダイアグラムにマッピングする

1.  [ *エントリ* ]タブをクリックします。

    ![BOMエントリタブ](./managing-boms/images/02.png)

2.  右側の *マップされた商品* には、すべてのサブパーツがリストされます。 ユーザーが図の上にマウスを置くと、プラス記号が表示されます。

    ![BOMエントリタブ](./managing-boms/images/03.png)

3.  商品に関連付けられている図の部分をクリックします（たとえば、 *Fuel Injector 1*）。

4.  次のように入力します：

      - **番号**：1（マップされた商品リストの数値順）

      - **商品**：MIN59999（商品名またはSKU）

        ``` tip::
           テキストのオートコンプリートでは、商品の名前を入力すると候補が表示されます。
        ```

    ![BOMダイアグラムで商品の関連付けを開始します。](./managing-boms/images/04.png)

5.  *[保存]*をクリックします。

残りの商品の追加を続けます。 同じ商品が複数ある場合でも、各エントリには、マップされた商品のリストで一意の数値識別子が必要です。 この例では、2つの燃料噴射装置があり、両方に異なる商品マッピング番号があります。

![BOMダイアグラムでの商品の関連付けを続けます。](./managing-boms/images/05.png)

## BOMの表示

Commerce BOMウィジェットは、BOMダイアグラムを表示するために使用されます。

1.  目的のストアサイトページに移動し、BOMウィジェットを追加します。 BOMウィジェットには、作成されたすべてのBOMとフォルダーが表示されます。

    ![BOMウィジェットをサイトページに展開します。](./managing-boms/images/06.png)

2.  BOMウィジェットで、BOMサムネイルをクリックしてBOM定義を選択します。

    ![Commerce BOMウィジェットに図が表示されます。](./managing-boms/images/07.png)

BOMウィジェットに図が表示されます。

## BOMフォルダーの追加

フォルダーを作成して、ストアのBOMをグループ化および編成できます。

フォルダを追加するには：

1.  追加（![Add Icon](../../../images/icon-add.png)）をクリックし、次に *フォルダの追加*クリックします。
2.  次のように入力します：
      - **名前**：フォルダ名（自動車部品）
3.  *[Save]*をクリックします。

## 追加情報

  - [SKUを商品に追加する](./adding-skus-to-your-products.md)
  - [サイトにページを追加する](https://learn.liferay.com/dxp/7.x/en/site-building/creating-pages/adding-pages/adding-a-page-to-a-site.html)
  - [ページへのウィジェットの追加](https://learn.liferay.com/dxp/7.x/en/site-building/creating-pages/using-widget-pages/adding-widgets-to-a-page.html)
