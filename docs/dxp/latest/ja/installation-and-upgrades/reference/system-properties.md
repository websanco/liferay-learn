# システムプロパティ

DXPは、アプリケーションサーバーのJVMで実行されます。 システムプロパティは、`-D[name1]=[value1]`形式で`java`に引数としてJVMに直接渡すことも、DXPシステムプロパティファイルを使用してロードすることもできます。 システムプロパティは、アプリケーションサーバーとそのすべてのアプリケーションで使用できるため、 [ポータルプロパティ](./portal-properties.md) とは異なります。

アプリケーションサーバーの規定のスクリプトは、システムプロパティを追加/変更するための最も安全な場所です。 システムプロパティを集中化するために使用できます。 すべてのプロパティをJVM引数として渡すと、アプリケーションサーバーの起動時にすべてのプロパティが設定されるため、タイミングの問題がなくなります。 アプリケーションサーバー、DXP、およびその他すべてのWebアプリケーションは、プロパティをすぐに使用できます。

ただし、DXPは、必要なプロパティを設定するための便宜として、 [`portal-impl.jar/system.properties`](https://docs.liferay.com/ce/portal/7.3-latest/propertiesdoc/system.properties.html) ファイルを使用します。 `system.properties` ファイルは、 `system-ext.properties` ファイルを使用して拡張またはオーバーライドできます。 システムプロパティファイルは、アプリケーションサーバーの起動後に [ポータルアプリケーションクラスローダー](../../liferay-internals/customizing-the-core/reference/portal-application-classloaders.md) によって読み込まれます。 DXPがシステムプロパティを使用して構成するいくつかの機能を次に示します。

  - ファイルのエンコーディング
  - ロギング
  - デフォルトのXMLパーサー構成
  - JAXBコンテキストファクトリ
  - JRubyネイティブ拡張を有効にする

DXPは、次の方法でシステムプロパティファイルを使用します。

  - DXP *は、システムプロパティ `system.properties.set` が `false`でない限り、 `system.properties` （および `system-ext.properties`）の新しいプロパティを使用して* プロパティを拡張します
  - DXP *は、システムプロパティ `system.properties.set.override` が `false`でない限り、 `system.properties` （および `system-ext.properties`）の新しい値を使用して* プロパティをオーバーライドします。

``` warning::
   アプリケーションサーバーの起動後のシステムプロパティの設定またはリセットにはリスクがあります

   *アプリケーションサーバーで権限が有効になっている場合、システム値の変更が禁止される場合があります。
   *システムプロパティは不変として扱われます。 アプリケーションサーバー上の別のプロセスは、DXPが値をリセットする前に、初期プロパティ値をキャッシュすることが考えられます。 このような場合、システムは同じプロパティに対して異なる値で動作しようとします。
```

ここでは、システムプロパティを指定する両方の方法を示します。

  - [システムプロパティを直接設定する](#setting-system-properties-directly)
  - [`system-ext.properties` ファイルの使用](#using-a-system-ext-properties-file)

## システムプロパティを直接設定する

システムプロパティを設定するためのアプリケーションサーバーの規定のスクリプトは、プロパティを追加および変更するための推奨場所です。

1.  次のシステムプロパティをアプリケーションサーバースクリプトのJVM引数として設定することにより、 `system.properties` および `system-ext.properties` ファイルの使用を無効にします。
   
        -Dsystem.properties.set=false -Dsystem.properties.set.override=false

2.  次の形式を使用して、新しい各プロパティをJVM引数として追加します。
   
        -D[name]=[value]

3.  既存のプロパティを変更します。

4.  アプリケーションサーバーを再起動します。

アプリケーションサーバーとDXPは新しいシステムプロパティを使用しています。

## `system-ext.properties` ファイルの使用

DXPの `system.properties` ファイルを使用し、システムプロパティを拡張/上書きする場合は、 `system-ext.properties` ファイルを使用します。 手順は次のとおりです。

1.  アプリケーションサーバーを停止します。

2.  プロパティを `system-ext.properties`というファイルに追加します。

3.  `system-ext.properties`ファイルをDXPのクラスパスに追加します。 DXPの`WEB-INF/classes`フォルダがクラスパスにあるため、`system-ext.properties`ファイルをそのフォルダに追加できます。 以下の2つの方法で追加できます:

    DXPがWebアプリケーションとしてアプリケーションサーバーにインストールされた場合は、`system-ext.properties`をDXPアプリケーションの`WEB-INF/classes`フォルダに追加してください。

    DXPがアプリケーションサーバーにWARファイルとしてインストールされた場合、WARファイルの内容を抽出し、`system-ext.properties`ファイルを`WEB-INF/classes`フォルダに追加します。 次に、コンテンツをWARファイルとして再パッケージ化します。

4.  アプリケーションサーバーを起動します。

5.  DXP Webアプリケーションを展開します。 詳細については、するための手順を参照してください [アプリケーションサーバーへのインストールを](../installing-liferay/installing_liferay_on_an_application_server.html)。

Liferay DXPはシステムプロパティを柔軟に処理するので、システムを自由に構成できます。

## 追加情報

  - [ポータルプロパティ](./portal-properties.md)
