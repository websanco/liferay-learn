# 関連商品、アップセル、クロスセル

商品関係は、商品を結びつけるのに使用できます。 関連付けると、商品には他の商品へのリンクが表示されます。 すべての関連商品を商品関係タイプに割り当てる必要があります。 その後、これらの商品は、適切なタイプのソースデータに設定された[Product Publisher]ウィジェットで表示できます。

商品詳細ページに複数の[Product Publisher]ウィジェットを配置すると、関連する商品の複数のセットを表示できます。 たとえば、あるウィジェットには、ページに掲載されている商品の代替品である商品のセットを表示し、別のウィジェットには、掲載商品の付属品を表示できます。

注：サイトがアクセラレータを使用して構築された場合は、いくつかの商品関係タイプが既に配置されています。 デフォルトの商品関係のリストは次のとおりです。

  - **Up-Sell**：掲載商品に相当するが、販売価格がより高い商品を表示します。
  - **Spare**：掲載商品のスペア部品またはコンポーネントを表示します。
  - **Related**：キャッチオールセット。
  - **Accessories**：掲載商品と組み合わせて使用するアドオン商品を表示します。
  - **Cross-Sell**：掲載商品を補完する商品を表示します。

![商品関係：ブレーキローターとブレーキ液](./related-products-up-sells-and-cross-sells/images/05.png)

上の画像では、2つの関連商品が下部の[Product Publisher]ウィジェットに表示されています。 他のタイプの商品関係で設定された[Product Publisher]ウィジェットをさらに追加するオプションがあります。

## 新しい商品関係タイプの追加

必要に応じて、商品関係タイプをさらに追加できます。 これには、保証などの無形商品が含まれます。 新しい商品関係を追加するには：

1.  *[Control Panel]* → *[Configuration]* → *[System Settings]*に移動します。

2.  *[Commerce]*セクションで*[Catalog]*をクリックします。

3.  左側のメニューで *[Product Relations]* をクリックします。

4.  *[Add]*ボタンをクリックします。

5.  *[Type]*フィールドに新しい商品関係を入力します（保証）。

6.  *[Save]*をクリックします。

    ![新しい商品関係の作成](./related-products-up-sells-and-cross-sells/images/01.png)

新しい商品関係タイプが追加されました。

## 商品間の関係の作成

1.  *[Control Panel]* → *[Commerce]* → *[Products]*に移動します。

2.  商品を選択します（たとえば、*Brake Pads*）。

3.  *[Product Relations]*サブタブをクリックします。

4.  左側のメニューで、*[Related]*をクリックします。

    ![関連メニュー](./related-products-up-sells-and-cross-sells/images/02.png)

5.  （+）ボタンをクリックして、1つ以上の関係を追加します。 この例では、*[Brake Rotors]*と*[Brake Fluid]*です。

6.  *[Add]*をクリックします。

    ![2つの商品を追加する](./related-products-up-sells-and-cross-sells/images/04.png)

これらの2つの商品が*Brake Pads*に関連付けられました。 この関係は単方向であることに注意してください。 つまり、*Brake Pads*を表示すると、新しい商品関係が表示されますが、*Brake Rotors*または*Brake Fluid*の商品を表示すると、*Brake Pads*は関連商品としてリストされ*ません*。

商品関係を作成したら、[Product Publisher]ウィジェットを設定して関係を表示します。

## 商品関係の表示

[Product Publisher]ウィジェットを設定することにより、商品表示ページに商品関係を表示できます。 最初に[商品表示ページ](https://help.liferay.com/hc/en-us/articles/360017870292-Displaying-Product-Pages-)を用意する必要があります。

1.  商品を検索します（たとえば、*Brake Pads*）。

2.  *[Brake Pads]*をクリックして商品の詳細を表示します。

3.  ページの一番下までスクロールして、*[Product Publisher]*ウィジェットにリストされている関連商品を表示します。

    ![設定メニュー](./related-products-up-sells-and-cross-sells/images/06.png)

4.  *3ドット*アイコン（[Options]）→ *[Configuration]*をクリックします。

5.  *[Data Source]*ドロップダウンメニューから*[Product Relations related]*を選択します。

    ![データソースを選択](./related-products-up-sells-and-cross-sells/images/03.png)

6.  *[Save]*をクリックしてダイアログボックスを閉じます。

    ![商品関係：ブレーキローターとブレーキ液](./related-products-up-sells-and-cross-sells/images/05.png)

上記の例は、さまざまな商品がどのように関連しているかを示しており、お客様は、有用な可能性のある全範囲商品を確認できます。
