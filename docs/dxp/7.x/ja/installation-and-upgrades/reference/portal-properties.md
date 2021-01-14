# ポータルプロパティ

構成オプションは、 *ポータルプロパティ*を使用して指定されます*名前と値のペアのセットは、サーバーの起動時にプロパティファイルと環境変数から読み取られます。 [デフォルト値](https://docs.liferay.com/dxp/portal/7.2-latest/propertiesdoc/portal.properties.html)は`portal-impl.jar/portal.properties`ファイルで指定されます。</p>

一部のプロパティはユーザー インターフェイス (UI) を介して変更できますが、他のプロパティはプロパティ ファイルでのみ変更できます。 これらには、[Liferay Home](./liferay-home.md)フォルダの場所を宣言するデータベースへの接続が含まれます。 そして [ユーザーがどのように認証するかを変更する](../securing-liferay/authentication-basics.md#configuring-authentication-type-using-properties) (メールアドレスではなくスクリーン名で)。

慣例により、` portal-ext.properties ` ` [Liferay Home]（./ liferay-home.md）に作成する必要があります`フォルダまたは`[USER_HOME]`デフォルトのプロパティ値を上書きするフォルダ。 新規または変更されたプロパティファイルを適用するには、DXPを再起動する必要があります。

``` warning::
   ``portal-impl.jar/portal.properties`` ファイルを直接変更しないでください。変更したいプロパティを上書きするために別のファイルを作成してください。 `` portal-ext.properties``ファイルはこの目的のために定義されています。
```

`portal-ext.properties`ファイルを使用してデフォルトのプロパティをオーバーライドすると、以下の利点があります。

  - ファイルを他のLiferay DXP環境およびサーバーノードにコピーできます。
  - 構成をバージョン管理システムに保存して、構成管理を簡素化できます。
  - 最初の起動前にファイルにプロパティを設定することは、DXPを構成する最も簡単な方法です。

**内容：**

  - [ポータルプロパティの使用](#using-portal-properties)
  - [ポータルプロパティの優先度](#portal-property-priority)
  - [システム設定と構成ファイルの使用](#using-system-settings-and-configuration-files)

``` note::
   DXP 7.3以降、仮想インスタンスごとのポータルプロパティファイル機能は削除されました。 DXPは、「portal-[companyId].properties」形式のファイルのインスタンスごとのプロパティを会社IDと一致するインスタンスに適用しなくなりました。
```

## ポータルプロパティの使用

`[Liferay Home] /portal-ext.properties`を作成する場合、ベストプラクティスは、関連するセクションを `portal-impl.jar/portal.properties` から `portal-ext.properties` ファイルにコピーし、次に変更することですあなたが望むものへの価値。

``` note::
   `Setup Wizard <../installing-liferay/running-liferay-dxp-for-the-first-time.md>`_ , DXPは、``[Liferay Home]`` の `portal-setup-wizard.properties` というファイルにこれらのプロパティを設定します。
```

ここにいくつかの設定例があります。

### データベース接続の設定

データベース接続プロパティは通常、`portal-ext.properties`ファイルに設定されます。 たとえば、データベース接続を変更する場合は、`portal-ext.properties`ファイルを作成し、[データベース接続プロパティ](./database-templates.md)を必要な値に設定します。

``` properties
jdbc.default.driverClassName=org.mariadb.jdbc.Driver
jdbc.default.url=jdbc:mariadb://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
jdbc.default.username=joe.bloggs
jdbc.default.password=123456
```

データベース構成の詳細については、[データベース構成](./database-configurations.md)および[データベーステンプレート](./database-templates.md)を参照してください。

### Liferayホームの場所の設定

一部のアプリケーションサーバー(WebLogicなど)では、DXP WARファイルをデプロイする前に
Liferay Homeの場所をカスタマイズする必要があります。 一部のアプリケーションサーバー(WebLogicなど)では、DXP WARファイルをデプロイする前に`Liferay Homeの場所をカスタマイズする必要があります。</p>

<pre><code class="properties">liferay.home=/home/jbloggs/liferay
`</pre> 



### ユーザーの認証方法の変更

ユーザの認証方法を変更するには、次の `company.security.auth.type` プロパティ値のいずれかを追加してください。



``` properties
company.security.auth.type=emailAddress
```




``` properties
company.security.auth.type=screenName
```




``` properties
company.security.auth.type=userId
```




## ポータルプロパティの優先度

`include-and-override` という特別なプロパティは、プロパティオーバーライドの順序を定義します。

1.  プロパティソースは3つあります。
   
         - `portal-impl.jar/portal.properties`ファイル
      - 拡張プロパティファイル
      - Liferay Docker Env変数
2.  *共有プロパティ* （複数回定義されたプロパティ）に定義された最後の値が優先されます。

3.  プロパティソースは[決定論的順序](#configuration-processing)で読み込まれます。



### 構成処理

プロパティは次の順序で定義されます。



``` properties
portal-impl.jar/portal.properties
include-and-override=portal-bundle.properties
include-and-override=${liferay.home}/portal-bundle.properties
include-and-override=portal-ext.properties
include-and-override=${liferay.home}/portal-ext.properties
include-and-override=portal-setup-wizard.properties
include-and-override=${liferay.home}/portal-setup-wizard.properties
include-and-override=${external-properties}
include-and-override=${liferay.home}/${external-properties}
[Added `include-and-override` files]
[Liferay Docker Env variables]
```


`portal-impl.jar/portal.properties`ファイルは上記`include-and-override`定義を指定します。 DXPは各ファイルをチェックして、追加の `インクルードアンドオーバーライド` 定義がないか確認します。つまり、独自に定義できます。

![DXPサーバーが使用しているインクルード拡張ファイルのリストは、コントロールパネルの[構成]セクションの[サーバー管理]ページにあります。](./portal-properties/images/01.png)

`${external-properties}` 定義は、DXPのJavaプロパティ `外部プロパティ` （たとえば、 `-Dexternal-properties = some.properties`）に割り当てられたプロパティファイルを表します。

Liferay Dockerコンテナは、Liferay環境変数を、リストに追加されたポータルプロパティソースに集約します。



``` important::
   複数のファイルのプロパティをオーバーライドすると、**最後**に定義されたプロパティソースが優先されます。 他のすべては無視されます。
```


![DXPサーバーのすべてのポータルプロパティは、コントロールパネルの[構成]セクションの[サーバー管理]ページに表示できます。](./portal-properties/images/02.png)



### ポータルプロパティの優先度の例

次の例は、プロパティソースと特定のプロパティがDXPを構成する方法を示しています。



#### 例1： `portal-ext.properties` を使用してプロパティをオーバーライドする

アプリケーションサーバーにメールセッションを設定し、デフォルトの`portal-impl.jar/portalとは異なる名前の場合。 roperties`(`mail.session.jndi.name=mail/MailSession`)、`portal-ext.properties`ファイルでメールセッション名を指定します。

`portal-ext.properties`新しい値：



``` properties
mail.session.jndi.name=mail/SomeMailSession
```


結果のプロパティソースの順序：

1.  `portal-impl.jar/portal.properties`
2.  `[Liferay Home]/portal-ext.properties`

`mail.session.jndi.name`に定義された最後の値は、`[Liferay Home]/portal-ext.properties`にあります。

結果の構成：



``` properties
mail.session.jndi.name=mail/SomeMailSession
```




#### 例2：プロパティファイルの追加

開発環境など、特定の環境のプロパティファイルを追加できます。 その後、共通のプロパティには単一の `portal-ext.properties` を、他には環境固有の設定を使用することができます。

1.  任意の拡張子ファイルを作成します(例: `portal-development.properties`)。環境に環境特有のプロパティを追加します。 
   
   

    ``` properties
    mail.session.jndi.name=mail/DevMailSession
    ```


2.  この`include-and-override`プロパティを`portal-ext.properties`ファイルの先頭に追加することで、新しい拡張子ファイルをプロパティソースとして含めます: 
   
   

    ``` properties
    include-and-override=portal-development.properties
    ```


結果のプロパティソースの順序：

1.  `portal-impl.jar/portal.properties`
2.  `[Liferay Home]/portal-ext.properties`
3.  `[Liferay Home]/portal-development.properties`

`mail.session.jndi.name`に定義された最後の値は、`[Liferay Home]/portal-development.properties`にあります。

結果の構成：



``` properties
mail.session.jndi.name=mail/DevMailSession
```




``` tip::
   プロパティファイルを必要なだけ使用すると、DXP構成の管理が簡単になります。
```




## システム設定と構成ファイルの使用

一部のプロパティは[システム設定](../../system-administration/system-settings/system-settings.md)と[設定ファイル](../../system-administration/system-settings/using-configuration-files.md)を使用して設定できます。 たとえば、SAML認証プロパティは、システム設定で使用できるプロパティです。

DXPデータベースに格納されているプロパティは、ポータルプロパティファイルで設定されているプロパティよりも優先されます。

*設定*の*コントロールパネル*→ *システム設定*に移動してシステム設定を検索します。 システム設定は`.config`ファイルとしてエクスポートし、ソースコントロールに保存し、分散DXPインストールで使用できます。 システム設定を介して設定されたポータルプロパティと構成ファイルは、データベースに保存されます。 すぐに適用されるプロパティもあれば、サーバーの再起動が必要なプロパティもあります。



## 追加情報

  - [ポータルプロパティ](https://docs.liferay.com/ce/portal/7.3-latest/propertiesdoc/portal.properties.html)

  - [システム設定](../../system-administration/system-settings/system-settings.md)

  - [構成ファイルについて](../../system-administration/system-settings/using-configuration-files.md)
