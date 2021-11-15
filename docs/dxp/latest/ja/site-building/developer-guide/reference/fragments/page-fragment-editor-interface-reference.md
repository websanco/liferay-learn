# ページ フラグメントエディタのインターフェイスリファレンス

ページ フラグメントエディタのインターフェイスは、2 つのタブで構成されています。

  - [コードエディタ](#code-editor)
  - [設定](#configuration)

以下のセクションでは、インターフェイスのこれらの部分の使用方法について説明します。 ページ フラグメントの作成方法については、 [Developing Page Fragments](../../developing-page-fragments/developing-fragments-intro.md) を参照してください。

## コードエディタ

コードエディタは 4 つのペインに分割されます。

**HTML:** 動的動作を追加するために使用できる Liferay Portal 固有のタグおよび FreeMarker ([代替 (角括弧) 構文](https://freemarker.apache.org/docs/dgui_misc_alternativesyntax.html)を使用) とともに、標準の HTML をサポートします。

```{tip}
`$(` と入力すると、自動補完機能を使って変数名の検索を開始します。 自動補完機能を使用して taglib 名を検索するには、`[@` と入力します。
```

**CSS:** 標準の CSS をサポートします。

**JavaScript:** 標準の JavaScript および JQuery をサポートします。 JavaScript ペインの FreeMarker コンテキストで構成値にアクセスすることもできます。

**プレビュー:** コードを記述すると更新されるコンポーネントのライブ プレビューを提供します。 デスクトップ、モバイル、タブレット、および拡張ビューを切り替えることができます。

![フラグメント エディタは、フラグメントのすべての部分を作成するための環境を提供します。](./page-fragment-editor-interface-reference/images/01.png)

## 設定

```{note}
ページフラグメントの構成の定義は、Liferay DXP 7.2 SP1 以降および Liferay Portal CE GA2 以降で利用できます。
```

[設定] タブは、ページ フラグメントの設定メニューに設定オプションを追加するための入力を提供します。 これは[フラグメントツールキット](../../developing-page-fragments/using-the-fragments-toolkit.md)が生成する `configuration.json` ファイルに相当します。 たとえば、ページ フラグメントの設定オプションにセレクターを追加して、ユーザーがページ フラグメントの見出しの色を選択できるようにすることができます。 ページ フラグメントの設定オプションを定義すると、より柔軟になり、維持する必要のあるページ フラグメントの数が減ります。

```{note}
構成に加えられた変更は、コード エディタで自動的に使用できるようになります。 構成が無効な場合、ページ フラグメントを公開できません。 ページ フラグメントをプレビューまたは公開する前に、常に有効な JSON 構成を用意してください。
```

構成値は、HTML ペインの FreeMarker コンテキストを介してページ フラグメントで使用できるようになります。 設定オプションを HTML の条件値と組み合わせて、ユーザーに動的なエクスペリエンスを作成できます。 JavaScript を介してこれらの値にアクセスすることもできます。 詳細は、[Making Page Fragments Configurable](../../developing-page-fragments/adding-configuration-options-to-fragments.md) を参照してください。

DXP 7.3 以降では、ページの上部にあるチェックボックスを選択して、フラグメントをキャッシュ可能にすることもできます。 このオプションを有効にすると、フラグメントがページに追加されるときにフラグメントがキャッシュに追加され、これらのフラグメントを含むページのパフォーマンスが向上します。

![フラグメントをキャッシュしてページのパフォーマンスを向上させるには、[キャッシュ可能] オプションをオンにします。](./page-fragment-editor-interface-reference/images/02.png)
