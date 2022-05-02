# ウィジェットのルック＆フィールの設定

ウィジェットのルック＆フィール設定メニューにアクセスするには、次の手順に従います。

1.  ウィジェットの右上隅にある **オプション** アイコン（![Actions Button](../../../../images/icon-actions.png)）をクリックします。

1. ［**Look and Feel Configuration**］ を選択します。

    **ルック＆フィール設定** には、6つのタブがあります。
    - General
    - Text Styles
    - Background Styles
    - Border Styles
    - Margin and Padding
    - Advanced Styling

1. カスタマイゼーションが完了したら、 ［**Save**］ をクリックし、ページを更新すると変更が適用されます。

```{note}
表示ページテンプレートのコンテンツページのウィジェットでは、ルック＆フィールの設定オプションは使用できません。
```

<a name="general-settings" />

## 一般設定

［General］タブには、次のオプションがあります。

**Use Custome Title** を使用すると、ウィジェットのタイトルを変更できます。 タイトルボックスの値は、ウィジェットのデコレータに表示されます。 タイトルはローカライズ可能なので、さまざまな言語のタイトルの翻訳を提供できます。

**Application Decorators** では、 **Barebone** 、 **Borderless** 、そして **Decorate** の3つから選択できます。 Decorateアプリケーションデコレーターがデフォルトです。

```{important}
ウィジェットボーダーをオフにすると、テーマによってはウィジェットボーダーがオンになっていることを前提とし、正しく表示されない場合がありますので、ご注意ください。
```

![ルック＆フィール設定メニューの一般タブでは、カスタムウィジェットタイトルを定義し、デコレータを使用してウィジェットのコントラストオプションを選択することができます。](./configuring-widget-look-and-feel/images/01.png)

<a name="text-styles" />

## テキストのスタイル

［**Text Styles**］ ウィジェットに表示するテキストの形式を設定します。 オプションは次のとおりです。

**Font：** さまざまなフォントを選択します。 テキストは、太字、斜体、またはその両方を設定することができます。

**Size：** フォントサイズを0.1emから12emの範囲で設定します。 1emがデフォルトです。

**Color：** 任意の6桁の16進カラーコードに設定します。 テキストボックスをクリックすると、カラーパレットが表示されます。

**配置：****左** 、 **中央** 、 **右** 、または</em>両端揃え</em>に設定します。

**テキストの装飾：****下線** 、 **オーバーライン** 、または **取り消し線** に設定します。 デフォルトのテキスト装飾は **None** です。

![テキストスタイルタブでは、ウィジェットに表示されるテキストの形式を設定することができます。](./configuring-widget-look-and-feel/images/02.png)

**Word Spacing：** -1 emから0.95 emの範囲で設定します。0emがデフォルトです。

**Line Spacing：** 0emから12emの範囲で設定します。0emがデフォルトです。

**Letter Spacing：** -10pxから50pxの範囲で設定します。0pxがデフォルトです。

<a name="background-styles" />

## 背景のスタイル

［Background Styles］タブは、ウィジェットの背景色を指定します。 テキストスペースを選択すると、背景色を選択するためのカラーパレットが表示されます。または、6桁の16進カラーコードを手動で入力することもできます。

![背景スタイルタブでは、ウィジェットの背景色を指定することができます。](./configuring-widget-look-and-feel/images/03.png)

<a name="border-styles" />

## 枠線のスタイル

［Border Styles］タブでは、ウィジェットの枠線の幅、スタイル、そしてカラーを設定します。 これらの各属性について、 **Same for All** セレクターを有効にしておくと、上、右、下、左枠に同じ設定を適用することができます。

![枠線スタイルタブでは、ウィジェットの各辺の枠線の幅、スタイル、そして色を指定することができます。](./configuring-widget-look-and-feel/images/04.png)

枠線の幅は、%値、em値、px値を指定できます。 線のスタイルは、ダッシュ、二重線、点線、溝、非表示、差込み、着手、リッジ、ソリッドから選択可能です。 枠線の色は、文字色や背景色と同じように、6桁の16進数カラーコードを入力することができます。 また、カラーパレットを使用することもできます。

<a name="margin-and-padding" />

## マージンとパディング

［Margin and Padding］タブでは、ウィジェットの端のマージンとパディングの長さを指定します。 枠線と同様に、 **Same for All** セレクターを有効にしておくと、ウィジェットの各サイド（上、右、下、左）に同じ設定を適用することができます。

![マージンとパディングタブでは、ウィジェットのサイドのマージンとパディングの長さを指定することができます。](./configuring-widget-look-and-feel/images/05.png)

パディングとマージンの両方に、任意の％値、em値、またはpx値を指定できます。

<a name="advanced-styling" />

## 詳細設定

［Advanced Styling］タブは、ウィジェットのLiferay IDやCSSクラスなど、ウィジェットに関する現在の情報を表示します。

![図6：Advanced StylingタブにはウィジェットのLiferay IDが表示され、CSSコードを入力してウィジェットのルック＆フィールをカスタマイズすることが可能です。](./configuring-widget-look-and-feel/images/06.png)

また、ウィジェットのカスタムCSSクラス名とカスタムCSSコードを入力することができます。 **Add a CSS rule for just this portlet** または **Add a CSS rule for all portlets like this one** リンクをクリックすると、CSSコードシェルがカスタムCSSテキストボックス内に追加されます。

<a name="additional-information" />

## 追加情報

- [ウィジェットデータのエクスポート/インポート](../configuring-widgets/exporting-importing-widget-data.md)
