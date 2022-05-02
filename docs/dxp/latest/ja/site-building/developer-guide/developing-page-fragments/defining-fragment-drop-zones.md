# フラグメントドロップゾーンの定義

ドロップゾーンは、コンテンツページの構築に不可欠です。 それらを使用して、ユーザーが他のフラグメントとウィジェットをドラッグアンドドロップできるフラグメント内の領域を定義することにより、独自のページレイアウトと動的表示を作成できます。

ドロップゾーンを定義するには、次の手順に従います。

1. *［サイト管理］* &rarr; *［デザイン］* &rarr; *［フラグメント］*へ移動します。

1. ［Fragment Sets］で、編集したいフラグメントのあるセットを選択します。

1. フラグメントの*アクション* (![Action](./../../../images/icon-actions.png)) ボタンをクリックして*［編集］*を選択し、[［フラグメントエディター］](./using-the-fragments-editor.md)を開きます。

1. HTMLコードエリアに、 `<lfr-drop-zone></lfr-drop-zone>` ラベルを追加して、フラグメント内にドロップゾーンを定義することができます。

   ```{important}
   フラグメント内の編集可能な要素にドロップゾーンを追加することはできません。
   ```

次のコードは、このラベルを使用して、タブフラグメント内にドロップゾーンを定義する方法を示しています。

   ```html
      <div class="tab-panel">
         [#list 0..configuration.numberOfTabs-1 as i]
         <div aria-labelledby="tab${i+1}" class="tab-panel-item d-none" data-fragment-namespace="${fragmentEntryLinkNamespace}" id="tabPanel${i+1}" role="tabpanel" tabindex="0">
            <lfr-drop-zone></lfr-drop-zone>
         </div>
         [/#list]
      </div>
   ```

この画像は、コンテントページエディターのサイドバーに結果を示しています。

![タブフラグメントは、コンテンツページエディタに4つの異なるドロップゾーンを表示します。](./defining-fragment-drop-zones/images/04.png)

定義したら、フラグメントまたはウィジェットをドロップゾーンにドラッグアンドドロップできます。

## フラグメントコードのドロップゾーンを特定する

> LiferayDXP7.4以降で利用できます。

ドロップゾーンを特定したい場合は、 `data-lfr-drop-zone-id` HTML属性を `<lfr-drop-zone></lfr-drop-zone>` ラベルに記述してください。 Liferay DXP 7.4以降で作成するフラグメントには、デフォルトで `data-lfr-drop-zone` id1HTML属性が含まれています。

![コンテンツページエディタのドロップゾーンレイアウトは、LiferayDXP7.3とLiferayDXP7.4以降で異なります。](./defining-fragment-drop-zones/images/03.png)

次の例は、 `data-lfr-drop-zone-id` 属性を使用して、タブフラグメント内のドロップゾーンを特定する方法を示しています。

   ```html
      <div class="tab-panel">
         [#list 0..configuration.numberOfTabs-1 as i]
         <div aria-labelledby="tab${i+1}-${fragmentEntryLinkNamespace}" class="tab-panel-item d-none" data-fragment-namespace="${fragmentEntryLinkNamespace}" id="tabPanel${i+1}-${fragmentEntryLinkNamespace}" role="tabpanel" tabindex="0">
            <lfr-drop-zone data-lfr-drop-zone-id="${i+1}"></lfr-drop-zone>
         </div>
         [/#list]
      </div>
   ```

   ```{tip}
   フラグメント内のドロップゾーンおよびその他の要素の順序を構成できます。 詳細については、[Setting the Order of Elements in a Fragment]（./setting-the-order-of-elements-in-a-fragment.md）を参照してください。
   ```

## 追加情報

- [フラグメントの開発](./developing-fragments-intro.md)
- [フラグメントエディターの使用](./using-the-fragments-editor.md)
- [フラグメントツールキットの使用](./using-the-fragments-toolkit.md)
- [フラグメント内の要素の順序を設定する](./setting-the-order-of-elements-in-a-fragment.md)
